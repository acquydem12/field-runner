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
public interface ICharacterInfo {
    
    Character getChar();
    void setChar(Character c);
    Rectangle getRectangle();
    void setRectangle(Rectangle rect);
}
