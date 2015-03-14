/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import fieldrunners.gameobjects.ICompleteable;
import fieldrunners.states.State;
import tbgameframework.IUpdateable;
import tbgameframework.math.Vector2D;

/**
 * ^_^
 * Stand alone and update
 * However, you still have direction
 * and position
 * @author Uchiha Salm
 */
public interface FRBraceYourSelf
    extends IUpdateable, FRDirectional, ICompleteable
{
    Vector2D getPosition();
    
    void updateState(State st);
}
