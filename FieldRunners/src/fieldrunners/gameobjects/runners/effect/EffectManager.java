/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners.effect;

import fieldrunners.gameobjects.runners.RunnerEffectableData;
import java.util.*;

/**
 *
 * @author Uchiha Salm
 */
public class EffectManager
    implements FREffect
{
    private Collection<FREffect> delayEffects = new LinkedList<>();
    private Collection<FREffect> imEffects = new LinkedList<>();
    
    @Override
    public boolean isStackable()
    {
        return true;
    }

    @Override
    public RunnerEffectableData impact(RunnerEffectableData rED)
    {
        for (FREffect eff : imEffects)
        {
            eff.impact(rED);
        }
        
        RunnerEffectableData newData = rED.clone();
        
        for (FREffect eff : delayEffects)
        {
            eff.impact(newData);
        }
        
        return newData;
    }
    
    public void addEffects(FREffect eff)
    {
        if (eff.isCompleted())
        {
            this.imEffects.add(eff);
        }
        else
        {
            if (eff.isStackable())
            {
                this.delayEffects.add(eff);
            }
            else
            {
                FREffect sEff = this.findSameEffect(eff);
                if (sEff != null)
                {
                    if (eff.getCoeff() >= sEff.getCoeff())
                    {
                        this.delayEffects.remove(sEff);
                        this.delayEffects.add(eff);
                        return;
                    }
                }
                
                this.delayEffects.add(eff);
            }
        }
    }
    
    private FREffect findSameEffect(FREffect eff)
    {
        for (FREffect oEff : this.delayEffects)
        {
            if (oEff.getClass() == eff.getClass())
            {
                return oEff;
            }
        }
        
        return null;
    }

    @Override
    public boolean isCompleted()
    {
            for (FREffect eff : delayEffects)
            {
                if (!eff.isCompleted())
                {
                    return false;
                }
            }
            
            return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
    }

    @Override
    public float getCoeff()
    {
        return 0f;
    }

    @Override
    public void Update()
    {
        this.imEffects.clear();
        
        Collection<FREffect> copy = new LinkedList<>(delayEffects);
        this.delayEffects.clear();
        for (FREffect eff : copy)
        {
            eff.Update();
            if (!eff.isCompleted())
            {
                this.delayEffects.add(eff);
            }
        }
    }
    
}
