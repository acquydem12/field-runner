/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners.effect;

import fieldrunners.gameobjects.ICompleteable;
import fieldrunners.gameobjects.runners.RunnerEffectableData;
import tbgameframework.IUpdateable;

/**
 *
 * @author Uchiha Salm
 */
public interface FREffect
    extends ICompleteable, IUpdateable
{
    boolean isStackable();
    
    RunnerEffectableData impact(RunnerEffectableData rED);
    
    float getCoeff();
}
