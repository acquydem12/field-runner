/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import tbgameframework.Board;
import tbgameframework.IDrawable;
import tbgameframework.math.Vector2D;
import tbgameframework.sound.SoundManager;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.fontsprite.FontDrawObject;
import tbgameframework.sprite.fontsprite.FontDrawObjectImp;
import tbgameframework.sprite.fontsprite.FontSprite;

/**
 *
 * @author MrKupi
 * An implements of button interface
 */
public abstract class ButtonImp<T> implements IButton, IDrawable{

    public ImageDrawObject drawObject;
    public FontDrawObject fontDrawObject;
    
    protected String name;
    // Button's image
    protected ISprite buttonImage;
    protected ISprite buttonImageOver;
    protected ISprite buttonImageDisabled;
    // Button's name with position of name in button's image
    protected ISprite buttonName;
    protected Vector2D nameDistance;
    protected String text = "";
    
    // Button size
    protected Rectangle buttonSize;
    // Button center
    protected Vector2D center;
    
    // Button depth
    protected float depth = 1;
    // Button state is visible or invisible
    protected boolean isVisbled = true;
    // Button state is enable or disable
    protected boolean isEnabled = true;
    // Save button range
    protected T range;
    // Object to check when mouse in range of button
    protected ButtonRange buttonRange;
    
    // Method wanna do when button clicked or holded
    protected Method clickMethod;
    protected Method overMethod;
    protected Method releaseMethod;
    
    // Owner of button
    protected Object buttonOwner;
    
    // Allow set button transform state
    protected boolean isUntransform = true;
    // button state
    protected int buttonState = ButtonState.RELEASE;
    
    /*
     *  Constructor with
     * T : type of button (Rectangle, Circle...)
     * ButtonRange : an factory for calculte button range
     * ISprite : an image of button show on screen
     * boolean : a flag tell about button state is transformable or not
     */
    public ButtonImp(T range, ButtonRange btRange, ISprite buttonImage, ISprite buttonImageOver){
        this.range = range;
        this.buttonRange = btRange;
        this.buttonImage = buttonImage;
        this.buttonImageOver = buttonImageOver;
    }
    
    public ButtonImp(T range, ButtonRange btRange, ISprite buttonImage, ISprite buttonImageOver, boolean isUnstransform){
        this.range = range;
        this.buttonRange = btRange;
        this.buttonImage = buttonImage;
        this.buttonImageOver = buttonImageOver;
        this.isUntransform = isUnstransform;
    }
    
    @Override
    public boolean Over(Object obj, String methodName){
        try {
            Method buttonHoldDo = obj.getClass().getMethod(methodName);
            this.overMethod = buttonHoldDo;
            this.buttonOwner = obj;
            return true;
        } catch (NoSuchMethodException | SecurityException ex) {
            return false;
        }
    }

    @Override
    public boolean Click(Object obj, String methodName) {
        try {
            Method buttonClickDo = obj.getClass().getMethod(methodName);
            this.clickMethod = buttonClickDo;
            this.buttonOwner = obj;
            return true;
        } catch (NoSuchMethodException | SecurityException ex) {
            return false;
        }
    }

    @Override
    public boolean Release(Object obj, String methodName) {
        try {
            Method buttonClickDo = obj.getClass().getMethod(methodName);
            this.releaseMethod = buttonClickDo;
            this.buttonOwner = obj;
            return true;
        } catch (NoSuchMethodException | SecurityException ex) {
            return false;
        }
    }
    
    @Override
    public void Update() {
        // when button disabled, dont do anything
        if(!this.isEnabled){
            return;
        }
        if(this.buttonState != ButtonState.DISABLE){
            CheckClick();
            CheckOver();
            CheckRelease();
        }
    }

    @Override
    public void Draw(Graphics2D g2D) {
        if(this.isVisbled)
        {
            if(buttonImage != null){
                buttonImage.Draw(g2D);
            }
            switch(buttonState){
                case ButtonState.RELEASE:
                    break;
                case ButtonState.CLICK:
                    break;
                case ButtonState.OVER:
                    if(buttonImageOver != null){
                        buttonImageOver.Draw(g2D);
                    }
                    break;
                case ButtonState.DISABLE:
                    if(buttonImageDisabled != null){
                        buttonImageDisabled.Draw(g2D);
                    }
                    break;
            }
            if(buttonName != null){
                buttonName.Draw(g2D);
            }
        }
    }
    
    /*
     * Check when button be clicked
     */
    protected void CheckClick()
    {
        if(Board.getInstance().GameInput.IsLeftButtonClicked())
        {
            if(this.buttonRange.CheckRange(range, isUntransform) && clickMethod != null)
            {
                try
                {
                    SoundManager.play("UI_button", false);
                    this.buttonState = ButtonState.CLICK;
                    this.clickMethod.invoke(this.buttonOwner, (Object[]) null);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                {
                    System.out.println("Cannot handle");
                }
            }
        }
    }
    
    /*
     * Check when button be overed
     */
    protected void CheckOver(){
        if(this.buttonRange.CheckRange(range, isUntransform) && overMethod != null){
            try
            {
                this.buttonState = ButtonState.OVER;
                this.overMethod.invoke(this.buttonOwner, (Object[]) null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
            {
                System.out.println("Cannot handle");
            }
        }
    }
    
    /*
     * Check when button be released
     */
    protected void CheckRelease(){
        if(!this.buttonRange.CheckRange(range, isUntransform) && releaseMethod != null){
            try
            {
                this.buttonState = ButtonState.RELEASE;
                this.releaseMethod.invoke(this.buttonOwner, (Object[]) null);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
            {
                System.out.println("Cannot handle");
            }
        }
    }

    
    @Override
    public void setButtonState(int state){
        this.buttonState = state;
    }
    
    @Override
    public int getButtonState(){
        return this.buttonState;
    }
    
    /*
     * Check button in range
     */
    public boolean IsInRange(){
        if(Board.getInstance().GameInput.IsLeftButtonClicked())
        {
            if(this.buttonRange.CheckRange(range, isUntransform)){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public void setEnabled(boolean isEn) {
        this.isEnabled = isEn;
    }

    @Override
    public boolean isVisibled() {
        return this.isVisbled;
    }

    @Override
    public void setVisibled(boolean isVs) {
        this.isVisbled = isVs;
        if(this.buttonImage != null){
            buttonImage.setVisibled(isVs);
        }
        if(this.buttonName != null){
            buttonName.setVisibled(isVs);
        }
    }

    @Override
    public float getDepth() {
        return this.depth;
    }

    @Override
    public void setDepth(float depth) {
        this.depth = depth;
    }
    
    public void changeText(String text){
        if(buttonName != null){
            this.text = text;
            ((FontSprite)buttonName).setText(text);
            rePositionFont();
        }
    }
    
    public String getText(){
        return this.text;
    }
    
    public void setFont(ISprite buttonName, Vector2D position){
        this.buttonName = buttonName;
        this.fontDrawObject = new FontDrawObjectImp(((FontSprite)buttonName).getText(), new Vector2D(0.0, 0.0), new Vector2D(1.0, 1.0), 0.0f);
        this.nameDistance = new Vector2D(position);
        rePositionFont();
    }
    
    public void rePositionFont(){
        Vector2D pos = fontDrawObject.getPosition();
        pos.x = this.drawObject.getPosition().x + nameDistance.x;
        pos.y = this.drawObject.getPosition().y + nameDistance.y;
        this.buttonName.setDrawObject(fontDrawObject);
    }
    
    public void setDisableImage(ISprite buttonDisable){
        this.buttonImageDisabled = buttonDisable;
        this.buttonImageDisabled.setDrawObject(drawObject);
    }
    
    @Override
    public ImageDrawObject getDrawObject(){
        return this.drawObject;
    }
    
    @Override
    public void setDrawObject(ImageDrawObject drawObj){
        this.drawObject = drawObj;
        if(buttonImage != null){
            this.buttonImage.setDrawObject(this.drawObject);
        }
        if(buttonImageOver != null){
            this.buttonImageOver.setDrawObject(this.drawObject);
        }
        if(buttonImageDisabled != null){
            this.buttonImageDisabled.setDrawObject(drawObj);
        }
        if(buttonName != null){
            rePositionFont();
        }
    }
    
    @Override
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public String getName(){
        return this.name;
    }   
    
    /*
     * Get button range
     */
    public T getRange(){
        return this.range;
    }
    
    public ISprite getButtonImage(){
        return this.buttonImage;
    }
    
    public ISprite getButtonName(){
        return this.buttonName;
    }
}