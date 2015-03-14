/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteDrawObject;

/**
 * interface: FontDrawObject
 * extends interface SpriteDrawObject
 * Describe a sprite font object which has a text
 * and will be draw by font
 * This object is text-able
 * @author Less
 */
public interface FontDrawObject
    extends SpriteDrawObject
{
    /**
     * Get text of object (object is text-able)
     * @return Object's text
     */
    void setPosition(Vector2D position);
    String getText();
    void setText(String text);
}
