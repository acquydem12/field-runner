/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public class Camera {
    
    final int BarSize = 30;
    
    Input gameInput = Board.getInstance().GameInput;
    static Camera camera;
    
    int wheel = 0;
    float scaling;
    float raising;
    
    float maxScale;
    float minScale;
    
    Vector2D absNew;
    Vector2D absOld;
    Vector2D distance = new Vector2D();
    Vector2D movement = new Vector2D();
    Vector2D moveDistance = new Vector2D();
    
    Vector2D eyes;        // View point
    
    Rectangle minArea;
    Rectangle scaleArea;
    Rectangle mapArea;
    
    Vector2D windowSize;
    
    /*
     * Default constructor reset camera to defaults
     */
    public Camera(){
        this.Reset();
    }
    
    /*
     * Reset camera to default
     */
    public void Reset(){
        windowSize = new Vector2D(Window.getInstance().getWidth(), Window.getInstance().getHeight());
        
        scaling = 1.0F;
        raising = 0.05F;
        
        maxScale = 1.0F;
        minScale = 0.25F;

        absNew = new Vector2D();
        absOld = new Vector2D();
        eyes = new Vector2D(windowSize.x/2.0, windowSize.y/2.0);
        
        minArea = new Rectangle();
        scaleArea = new Rectangle();
        mapArea = new Rectangle();
    }
    
    /*
     * Allow move camera follow direction
     * Parameter distanceX via direction at Ox coordinate
     * Parameter distanceY via direction at Oy coordinate
     */
    public void Move(double distanceX, double distanceY){
        Vector2D position = new Vector2D((movement.x - distance.x)*scaling, (movement.y - distance.y)*scaling);
        
        if( distanceX != 0 && (position.x + distanceX) >= scaleArea.x
            && (position.x + distanceX + windowSize.x) <= (scaleArea.x + scaleArea.width) ){
            movement.x += distanceX;
        }
        if(distanceY != 0 && (position.y + distanceY) >= scaleArea.y
            && (position.y + distanceY + windowSize.y) <= (scaleArea.y + scaleArea.height)  ){
            movement.y += distanceY;
        }
    }
    
    /*
     * Allow Camera zoom in with default scale raising
     */
    private void ZoomIn(){
        // Update center of viewArea
        UpdateEyesView();
        
        // Center in world
        absOld = GetEyesOfViewArea();
        
        // Update scale rate
        scaling += raising;
        
        // Check valid scale
        if(scaling > maxScale){
            scaling = maxScale;
        }else {
            // New center in world after zoom
            absNew = GetEyesOfViewArea();
            
            // Update view Area to distance move by center
            distance.x += absNew.x - absOld.x;
            distance.y += absNew.y - absOld.y;
        }
        // Check in fix area before transform
        ChangeFixArea();
    }
    
    /*
     * Allow Camera zoom in
     * parameter raising: raising scale
     */
    public void ZoomIn(float raising){
        this.raising = raising;
        this.ZoomIn();
    }
    
    /*
     * Allow Camera zoom out with default scale raising
     */
    private void ZoomOut(){
        // Update center of viewArea
        UpdateEyesView();
        
        // Center in world
        absOld = GetEyesOfViewArea();
        
        // Update scale rate
        scaling -= raising;
        
        // Check valid scale
        if(scaling < minScale){
            scaling = minScale;
        }else {
            // New center in world after zoom
            absNew = GetEyesOfViewArea();
            
            // Update view Area to distance move by center
            distance.x += absNew.x - absOld.x;
            distance.y += absNew.y - absOld.y;
        }
        // Check in fix area before transform
        ChangeFixArea();
    }
    
    /*
     * Allow Camera zoom out
     * parameter raising: raising scale
     */
    public void ZoomOut(float raising){
        this.raising = raising;
        this.ZoomOut();
    }
    
    /*
     * Camera transform to targer and zoom point
     * parameter g2D type of Graphics2D: graphics evironment to draw
     */
    public void Transform(Graphics2D g2D){
        // Default canvas, convert to real canvas like world
        g2D.translate(0, windowSize.y - BarSize);
        g2D.scale(1, -1);
        
        FixedArea();
        //g2D.translate( (-movement.x + distance.x),  (-movement.y + distance.y) );
        gameInput.setCameraScale(scaling);
        gameInput.setCameraTransform( (movement.x - distance.x), (movement.y - distance.y) );

    }
    
    /*
     * Input process move camera or zoom
     */
    public void InputProcess(){
        // Press key down or up to move camera
        if(Board.getInstance().GameInput.IsKeyDown(KeyEvent.VK_RIGHT)){
            Board.getInstance().camera.Move(moveDistance.x, 0);
        }
        else if(Board.getInstance().GameInput.IsKeyDown(KeyEvent.VK_LEFT)){
            Board.getInstance().camera.Move(-moveDistance.x, 0);
        }
        if(Board.getInstance().GameInput.IsKeyDown(KeyEvent.VK_UP)){
            Board.getInstance().camera.Move(0, moveDistance.y);
        }
        else if(Board.getInstance().GameInput.IsKeyDown(KeyEvent.VK_DOWN)){
            Board.getInstance().camera.Move(0, -moveDistance.y);
        }
        
        // Process mouse down and move
        if(Board.getInstance().GameInput.MouseDragged()){
            Vector2D mouseMovement = Board.getInstance().GameInput.MouseMovements();
            Board.getInstance().camera.Move(-mouseMovement.x, -mouseMovement.y);
        }
        
        // Process mouse wheel to zoom camera
        int i = Board.getInstance().GameInput.MouseWheelRotation();
        if(i > wheel){
            wheel = i;
            Board.getInstance().camera.ZoomOut();
        }
        else if(i < wheel){
            wheel = i;
            Board.getInstance().camera.ZoomIn();
        }
    }
    
    /*
     * Check a View Area have contained in a fix Area
     * if not contain, set be contained
     */
    private void FixedArea(){
        double posX = (movement.x - this.distance.x)*scaling;
        double posY = (movement.y - this.distance.y)*scaling;
        
        if(posX <= minArea.x)
        {
            distance.x = (minArea.x + movement.x*scaling)/scaling;
        }
        else if(posX + windowSize.x >= scaleArea.x + scaleArea.width)
        {
            distance.x = (movement.x*scaling + windowSize.x - scaleArea.x - scaleArea.width)/scaling;
        }
        
        if(posY <= minArea.y)
        {
            distance.y = (minArea.y + movement.y*scaling)/scaling;
        }
        else if(posY + windowSize.y >= scaleArea.y + scaleArea.height)
        {
            distance.y = (movement.y*scaling + windowSize.y - scaleArea.y - scaleArea.height)/scaling;
        }
    }
    
    /*
     * Change Fix Area with a scale has changed
     * Parameter scaleRateAmount: amount changed scale
     */
    private void ChangeFixArea(){
        this.scaleArea.width = (int)(this.mapArea.width*scaling);
        this.scaleArea.height = (int)(this.mapArea.height*scaling);
    }
    
    /*
     * Update center to newest view Area
     */
    private void UpdateEyesView(){
        eyes.x = (movement.x - distance.x + windowSize.x)/2;
        eyes.y = (movement.y - distance.y + windowSize.y)/2;
    }
    
    /*
     * Get the center at current view Area
     */
    private Vector2D GetEyesOfViewArea(){
        return new Vector2D(eyes.x/scaling, eyes.y/scaling);
    }
    
    /*
     * Allow set max scale raising zoom for camera
     * Parameter max: max scale raising
     */
    public void setMaxZoom(float max){
        if(max < minScale){
            this.maxScale = this.minScale;
        }
        else {
            this.maxScale = max;
        }
    }
    
    /*
     * Allow set min scale raising zoom for camera
     * Parameter min: min scale raising
     */
    public void setMinZoom(float min){
        if(min >= minScale && min <= maxScale){
            this.minScale = min;
        }
    }
    
    /*
     * Allow set fix Area for camera to operate
     * Parameter fixArea: fix rectangle for operate
     */
    public void setFixRectangleArea(Rectangle fixArea){
        if(fixArea.width*fixArea.width + fixArea.height*fixArea.height != 0){
            
            // Set Original Area
            this.mapArea.width = fixArea.width;
            this.mapArea.height = fixArea.height;
            
            // Calculate rate for map input
            float rate = (float)this.mapArea.width/(float)this.mapArea.height;
            
            // Try to scale min
            Vector2D fixWindow = new Vector2D();
            fixWindow.x = windowSize.x;
            fixWindow.y = this.mapArea.height / rate;
            if(fixWindow.y < windowSize.y)
            {
                fixWindow.y = windowSize.y;
                fixWindow.x = this.mapArea.width * rate;
            }
            
            // Calculate min scale for camera
            this.minScale = (float)(fixWindow.x / this.mapArea.width);
            this.scaling = this.minScale;
            
            // Update min Area for scale down
            this.minArea.width = (int)(fixWindow.x);
            this.minArea.height = (int)(fixWindow.y);
            // Update scale Area equal with min Area via deafault;
            this.scaleArea.width = this.minArea.width;
            this.scaleArea.height = this.minArea.height;
        }
    }
    
    /*
     * Set camera position
     */
    public void setCameraLocation(Vector2D position){
        this.Move(position.x, position.y);
    }
    
    /*
     * Set camera initialize rate scale
     */
    public void setRate(float rate){
        float inputRate = rate;
        if(rate < minScale){
            inputRate = minScale;
        }
        else if(rate > maxScale){
            inputRate = maxScale;
        }
        
        if(rate > scaling){
            scaling = inputRate;
            this.ZoomIn();
        }
        else if(rate < scaling){
            scaling = inputRate;
            this.ZoomOut();
        }
    }
    
    /*
     * Set camera zoom rate
     */
    public void setCameraZoomRate(float zoomRate){
        this.raising = zoomRate;
    }
    
    /*
     * Set camera move distance
     */
    public void setCameraMoveDistance(Vector2D movement){
        this.moveDistance = movement;
    }
    
    /*
     * 
     */
    public Vector2D getCameraTranslation(){
        return new Vector2D( - movement.x + distance.x, - movement.y + distance.y);
    }
    
    /*
     * 
     */
    public float getCameraScaling() {
        return this.scaling;
    }
}
