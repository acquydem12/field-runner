/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.collision;

import tbgameframework.math.Vector2D;
import java.awt.Rectangle;

/**
 *
 * @author MrKupi
 */
public interface ICollisionObject{
    
    public abstract boolean isCollidable(ICollisionObject object);
    public abstract void collisionDo(ICollisionObject object, Vector2D hitDir);
    public abstract Rectangle getBound();
}
