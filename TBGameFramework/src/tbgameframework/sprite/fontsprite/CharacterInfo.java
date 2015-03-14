/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import java.awt.Rectangle;

/**
 *
 * @author MrKupi
 */
public class CharacterInfo implements ICharacterInfo{

    Character c;
    Rectangle rect;
    
    public CharacterInfo(Character c, Rectangle rect){
        this.c = new Character(c);
        this.rect = new Rectangle(rect);
    }
    
    @Override
    public Character getChar() {
        return this.c;
    }

    @Override
    public void setChar(Character c) {
        this.c = c;
    }
    
    @Override
    public Rectangle getRectangle() {
        return this.rect;
    }
    
    @Override
    public void setRectangle(Rectangle rect) {
        this.rect = rect;
    }
    
}
