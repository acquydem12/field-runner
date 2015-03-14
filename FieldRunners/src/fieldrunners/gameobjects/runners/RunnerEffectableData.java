/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners;

/**
 *
 * @author Uchiha Salm
 */
public interface RunnerEffectableData
    extends tbgameframework.utils.prototype.Cloneable<RunnerEffectableData>
{
    // what runner's data can be effteted ???
    // just now, hp and speed
    
    int getHP();
    void setHP(int hp);
    void changeHP(int dhp);
    
    float getSpeed();
    void setSpeed(float sp);
    void changeSpeed(float dsp);
    void changeSpeedBy(float dpsp);
}
