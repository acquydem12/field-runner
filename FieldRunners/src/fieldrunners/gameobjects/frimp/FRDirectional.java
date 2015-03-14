/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import tbgameframework.math.Vector2D;

/**
 *
 * @author Uchiha Salm
 */
public interface FRDirectional
{
    Vector2D getDirection();
    float getAngle();
    
    void setDirection(Vector2D dir);
    void setAngle(float arg);
}
