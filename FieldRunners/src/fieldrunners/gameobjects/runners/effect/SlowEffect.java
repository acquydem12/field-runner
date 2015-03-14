/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners.effect;

import fieldrunners.gameobjects.runners.RunnerEffectableData;

/**
 *
 * @author Uchiha Salm
 */
public class SlowEffect
    implements FREffect
{
    float reducePrecent;
    int delay = 0;
    
    public SlowEffect(float rp, int delay)
    {
        this.reducePrecent = rp;
        this.delay = delay;
    }
    
    @Override
    public boolean isStackable()
    {
        return false;
    }

    @Override
    public RunnerEffectableData impact(RunnerEffectableData rED)
    {
        rED.changeSpeedBy(-this.reducePrecent);
        
        return rED;
    }

    @Override
    public float getCoeff()
    {
        return delay * this.reducePrecent;
    }

    @Override
    public boolean isCompleted()
    {
        return (this.delay <= 0);
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Update()
    {
        --delay;
    }
    
}
