/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.controls;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import tbgameframework.Board;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.control.ButtonImp;
import tbgameframework.control.IButton;
import tbgameframework.control.IButtonContainer;
import tbgameframework.control.IButtonRangeFactory;
import tbgameframework.control.SubButtonPosition;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.ISprite;

/**
 *
 * @author MrKupi
 */
public class FRRectangleButton extends RectangleButton implements IButtonContainer{

    protected ArrayList<IButton> buttons = new ArrayList();
    protected int radius = 0;
    
    public FRRectangleButton(Rectangle rect, ISprite buttonImage, ISprite buttonImageOver, int radius){
        super(rect, buttonImage, buttonImageOver);
        
        this.radius = radius;
    }
    
    public FRRectangleButton(Rectangle rect, ISprite buttonImage, ISprite buttonImageOver, boolean isUnstranform, int radius){
        super(rect, buttonImage, buttonImageOver, isUnstranform);
        
        this.radius = radius;
    }
    
    @Override
    public void Update(){
        for(IButton bt : buttons){
            bt.Update();
        }
        super.Update();
    }
    
    @Override
    public void Draw(Graphics2D g2D){
        for(IButton bt : buttons){
            if(bt instanceof ButtonImp){
                ButtonImp btImp = (ButtonImp)bt;
                btImp.Draw(g2D);
            }
        }
        super.Draw(g2D);
    }
    
    @Override
    public Collection<IButton> getAll() {
        return this.buttons;
    }

    @Override
    public void AddButton(IButton button, IButtonRangeFactory factory, int pos) {
        Vector2D position = new Vector2D(drawObject.getPosition());
        // decide position
        if(pos == SubButtonPosition.NORTH){
            position.y += this.Height() + radius;
        }else if(pos == SubButtonPosition.EAST){
            position.x += this.Width() + radius;
        }else if(pos == SubButtonPosition.SOUTH){
            position.y -= this.Height() + radius;
        }else{
            position.x -= this.Width() + radius;
        }
        
        // Set button state
        ButtonImp buttonImp = (ButtonImp)button;
        buttonImp.setVisibled(false);
        buttonImp.setEnabled(false);
        
        // Re-Position
        factory.RePosition(buttonImp.getRange(), position);
        ImageDrawObject btDrawObj = new ImageDrawObject(position, drawObject.getScale(), drawObject.getAngle());
        ISprite image = buttonImp.getButtonImage();
        ISprite name = buttonImp.getButtonName();
        if(image != null){
            image.setDrawObject(btDrawObj);
        }
        if(name != null){
            name.setDrawObject(btDrawObj);
        }
        this.buttons.add(button);
    }

    @Override
    public void RemoveButton(IButton button) {
        if(buttons.contains(button)){
            buttons.remove(button);
        }
    }
    
    @Override
    protected void CheckClick(){
        if(Board.getInstance().GameInput.IsLeftButtonClicked())
        {
            if(this.buttonRange.CheckRange(range, isUntransform))
            {
                for(IButton bt : buttons)
                {
                    if(bt instanceof ButtonImp)
                    {
                        ButtonImp btImp = (ButtonImp)bt;
                        btImp.setVisibled(true);
                        btImp.setEnabled(true);
                    }
                }
            }
            else
            {
                boolean mouseOnButton = false;
                for(IButton bt : buttons){
                    if(bt instanceof ButtonImp)
                    {
                        if(((ButtonImp)bt).IsInRange()){
                            mouseOnButton = true;
                        }
                    }
                }
                if(!mouseOnButton)
                {
                    for(IButton bt : buttons){
                        if(bt instanceof ButtonImp)
                        {
                            ButtonImp btImp = (ButtonImp)bt;
                            btImp.setVisibled(false);
                            btImp.setEnabled(false);
                        }
                    }
                }
            }
        }
        super.CheckClick();
    }
    
    public int Radius(){
        return this.radius;
    }

    @Override
    public void Reset() {
        for(IButton bt : buttons)
        {
            if(bt instanceof ButtonImp)
            {
                ButtonImp btImp = (ButtonImp)bt;
                btImp.setVisibled(false);
                btImp.setEnabled(false);
            }
        }
    }
}
