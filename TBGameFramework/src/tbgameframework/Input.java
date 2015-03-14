/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{
    
    private enum MouseState {
        RELEASED,
        PRESSED,
        CLICKED,
    }
    
    private boolean keyboardState[] = new boolean[525];
    private boolean prekeyboardState[] = new boolean[525];
    private int pressedKeyboard[] = new int[525];
    
    static final private int MAX_MOUSE = 3;
    private boolean mouseState[] = new boolean[MAX_MOUSE];
    private MouseState pollState[] = new MouseState[MAX_MOUSE];
    private boolean isMouseClicked[] = new boolean[MAX_MOUSE];
    private boolean isDragged = false;
    private Vector2D mouseDragMovement = new Vector2D();
    
    private Vector2D lastScreenMouseLocation = new Vector2D();
    private Vector2D screenMouseLocation = new Vector2D();
    private Vector2D worldMouseLocation = new Vector2D();
    private Vector2D mouseLocation = new Vector2D();
    private double cameraMoveX = 0.0f;
    private double cameraMoveY = 0.0f;
    private double cameraScale = 0.0f;
    
    private int mouseWheel = 0;
    
    // Singleton Input
    private static Input instance;
    
    private Input(){
        for(int i = 0; i < MAX_MOUSE; ++i){
            pollState[i] = MouseState.RELEASED;
        }
    }
    
    public static Input getInstance(Component comp){
        
        if(instance == null){
            instance = new Input();
        }
        comp.addKeyListener(instance);
        comp.addMouseListener(instance);
        comp.addMouseMotionListener(instance);
        comp.addMouseWheelListener(instance);
        
        return instance;
    }
    
    public synchronized void Update(){
        this.lastScreenMouseLocation.x = screenMouseLocation.x;
        this.lastScreenMouseLocation.y = screenMouseLocation.y;
        
        for(int i = 0; i < MAX_MOUSE; ++i)
        {
            if(this.mouseState[i]){
                if(pollState[i] == MouseState.RELEASED)
                {
                    pollState[i] = MouseState.CLICKED;
                }
                else
                {
                    pollState[i] = MouseState.PRESSED;
                }
            }
            else 
            {
                pollState[i] = MouseState.RELEASED;
            }
        }
    }
    
    // Keyboards
    
    public boolean IsKeyDown(int keyCode){
        return (keyboardState[keyCode]);
    }
    
    public boolean IsKeyUp(int keyCode){
        return (!keyboardState[keyCode]);
    }
    
    public synchronized boolean IsKeyPressed(int keyCode){
        boolean isTrue = !keyboardState[keyCode] && prekeyboardState[keyCode];
        if(isTrue == true){
            prekeyboardState[keyCode] = false;
        }
        return isTrue;
    }
    
    @Override
    public void keyReleased(KeyEvent key){
        
        prekeyboardState[key.getKeyCode()] = keyboardState[key.getKeyCode()];
        keyboardState[key.getKeyCode()] = false;
    }
    
    @Override
    public void keyPressed(KeyEvent key){
        //pressedKeyboard[key.getKeyCode()]++;
        
        prekeyboardState[key.getKeyCode()] = keyboardState[key.getKeyCode()];
        keyboardState[key.getKeyCode()] = true;
    }
    
    @Override
    public void keyTyped(KeyEvent key){
    }
    
    // Mouse Listener
    
    /*
     * Return true when mouse left button pressed
     */
    public boolean IsLeftButtonDown(){
        return (pollState[0] == MouseState.CLICKED || pollState[0] == MouseState.PRESSED);
    }
    
    /*
     * Return true when mouse right button pressed
     */
    public boolean IsRightButtonDown(){
        return (pollState[2] == MouseState.CLICKED || pollState[2] == MouseState.PRESSED);
    }
    
    /*
     * Return true when mouse right button clicked
     */
    public boolean IsLeftButtonClicked(){
        return pollState[0] == MouseState.CLICKED;
    }
    
    /*
     * Return true when mouse right button clicked
     */
    public boolean IsRightButtonClicked(){
        return pollState[2] == MouseState.CLICKED;
    }
    
    /*
     * Return true when mouse middle button clicked
     */
    public boolean IsMiddleButtonClicked(){
        return pollState[1] == MouseState.CLICKED;
    }
    
    /*
     * Check mouse status when event mouse passing
     */
    private void mouseStatus(MouseEvent e, boolean status){
        
        if(e.getButton() == MouseEvent.BUTTON1){
            mouseState[0] = status;
        }
        else if(e.getButton() == MouseEvent.BUTTON2){
            mouseState[1] = status;
        }
        else if(e.getButton() == MouseEvent.BUTTON3){
            mouseState[2] = status;
        }
    }
    
    @Override
    public synchronized void mouseReleased(MouseEvent e){
        mouseStatus(e, false);
        isDragged = false;
    }
    
    @Override
    public synchronized void mousePressed(MouseEvent e){    
        mouseStatus(e, true);
    }
    
    @Override
    public synchronized void mouseClicked(MouseEvent e){
        isDragged = false;
//        if(e.getButton() == MouseEvent.BUTTON1){
//            isMouseClicked[0] = true;
//        }
//        else if(e.getButton() == MouseEvent.BUTTON2){
//            isMouseClicked[1] = true;
//        }
//        else if(e.getButton() == MouseEvent.BUTTON3){
//            isMouseClicked[2] = true;
//        }
    }
    
    @Override
    public synchronized void mouseExited(MouseEvent e){
        mouseMoved(e);
    }
    
    @Override
    public synchronized void mouseEntered(MouseEvent e){
        mouseMoved(e);
    }
    
    // Mouse Motion
    @Override 
    public synchronized void mouseDragged(MouseEvent e){
        isDragged = true;
        
        mouseDragMovement.x = e.getX() - this.screenMouseLocation.x;
        mouseDragMovement.y = (Window.getInstance().getHeight() - e.getY() - 30) - this.screenMouseLocation.y;
        
        mouseMoved(e);
    }
    
    @Override 
    public synchronized void mouseMoved(MouseEvent e){
        lastScreenMouseLocation.x = screenMouseLocation.x;
        lastScreenMouseLocation.y = screenMouseLocation.y;
        
        this.screenMouseLocation.x = e.getX();
        this.screenMouseLocation.y = Window.getInstance().getHeight() - e.getY() - 30;
    }
    
    /*
     * Get mouse on screen position
     */
    public Vector2D ScreenMousePosition(){
        return this.screenMouseLocation;
    }
    
    /*
     * Get mouse world position
     */
    public Vector2D WorldMousePosition(){
        worldMouseLocation.x = this.screenMouseLocation.x/(cameraScale != 0 ? cameraScale : 1.0) + this.cameraMoveX;
        worldMouseLocation.y = this.screenMouseLocation.y/(cameraScale != 0 ? cameraScale : 1.0) + this.cameraMoveY;
        return worldMouseLocation;
    }
    
    /*
     * Get mouse world position
     */
    public Vector2D MousePosition(){
        return WorldMousePosition();
    }
    
    /*
     * Get mouse movements on screen when drag
     */
    public Vector2D MouseMovements(){
        return this.mouseDragMovement;
    }
    
    /*
     * Return true when mouse drag on screen
     */
    public boolean MouseDragged(){       
        if(lastScreenMouseLocation.x == this.screenMouseLocation.x && 
            lastScreenMouseLocation.y == screenMouseLocation.y)
        {
            isDragged = false;
        }
        return this.isDragged;
    }
    
    /*
     * Convert mouse position from screen to world coordinate
     */
    public Vector2D ConvertFromScreenToWorld(Vector2D position){
        Vector2D newPos = new Vector2D();
        newPos.x = position.x/cameraScale + cameraMoveX;
        newPos.y = position.y/cameraScale + cameraMoveY;
        return newPos;
    }
    
    /*
     * Set camera transform
     */
    public void setCameraTransform(double x, double y){
        cameraMoveX = x;
        cameraMoveY = y;
    }
    
    /*
     * Set camera scaling (zoom in/out)
     */
    public void setCameraScale(double scale){
        cameraScale = scale;
    }
    
    // Mouse wheel
    @Override 
    public void mouseWheelMoved(MouseWheelEvent e){
        
        this.mouseWheel += e.getWheelRotation();
    }
    
    /*
     * Get mouse wheel rotation
     */
    public int MouseWheelRotation(){
        return this.mouseWheel;
    }
}