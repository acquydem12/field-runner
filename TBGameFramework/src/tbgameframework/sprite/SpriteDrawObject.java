/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite;

import tbgameframework.math.Vector2D;

/**
 * interface: SpriteDrawObject
 * Describe an object using Sprite for draw
 * Object is position-able, scale-able and rotate-able
 * @author Less
 */
public interface SpriteDrawObject
{
    /**
     * Method: getPosition
     * Get position of object (object is position-able)
     * @return Object's position
     */
    Vector2D getPosition();
    
    /**
     * Method: getScale
     * Get scale ratio of object (object is scale-able)
     * @return Object's scale ratio
     */
    Vector2D getScale();
    
    /**
     * Method: getAngle
     * Get rotation angle of object (object is rotate-able)
     * (***** current Sprite is unrotate-able, this method is reserved *****)
     * (angle is aligned with Oy axis)
     * @return Object's rotation angle
     */
    float getAngle();
}
