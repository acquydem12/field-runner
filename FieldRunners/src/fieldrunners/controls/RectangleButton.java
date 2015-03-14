/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.controls;

import java.awt.Rectangle;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.control.ButtonImp;
import tbgameframework.control.RectangleButtonRange;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.ISprite;

/**
 *
 * @author MrKupi
 */
public class RectangleButton extends ButtonImp
{
    public RectangleButton(Rectangle rect, ISprite buttonImage, ISprite buttonImageOver)
    {
        
        super(rect, RectangleButtonRange.getInst(), buttonImage, buttonImageOver);
        
        buttonSize = rect;
        this.center = new Vector2D(buttonSize.x + buttonSize.width/2, buttonSize.y + buttonSize.height/2);
        drawObject = new ImageDrawObject(new Vector2D(buttonSize.x, buttonSize.y), new Vector2D(1.0, 1.0), 0);
        if(buttonImage != null){
            buttonImage.setDrawObject(drawObject);
        }
        if(buttonImageOver != null){
            buttonImageOver.setDrawObject(drawObject);
        }
    }
    
    public RectangleButton(Rectangle rect, ISprite buttonImage, ISprite buttonImageOver, boolean isUnstranform)
    {
        
        super(rect, RectangleButtonRange.getInst(), buttonImage, buttonImageOver, isUnstranform);
        
        buttonSize = rect;
        this.center = new Vector2D(buttonSize.x + buttonSize.width/2, buttonSize.y + buttonSize.height/2);
        
        drawObject = new ImageDrawObject(new Vector2D(buttonSize.x, buttonSize.y), new Vector2D(1.0, 1.0), 0);
        if(buttonImage != null){
            buttonImage.setDrawObject(drawObject);
        }
        if(buttonImageOver != null){
            buttonImageOver.setDrawObject(drawObject);
        }
    }
    
    @Override
    public void setDrawObject(ImageDrawObject drawObj)
    {
        if(this.range instanceof Rectangle){
            ((Rectangle)range).x = (int)drawObj.getPosition().x;
            ((Rectangle)range).y = (int)drawObj.getPosition().y;
        }
        super.setDrawObject(drawObj);
    }
    
    @Override
    public int Width() 
    {
        return buttonSize.width;
    }
    
    @Override
    public int Height() 
    {
        return buttonSize.height;
    }

    @Override
    public Vector2D Center() 
    {
        return this.center;
    }
}
