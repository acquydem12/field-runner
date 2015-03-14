/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.effect;

import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public interface ISpriteEffect {
    
    boolean isEnd();
    Vector2D getStartScale();
    Vector2D getEndScale();
    void setStartScale(Vector2D start);
    void setEndScale(Vector2D end);
    
    void setLoopable(boolean isLoop);
    boolean getLoopable();
    
    void reset();
}
