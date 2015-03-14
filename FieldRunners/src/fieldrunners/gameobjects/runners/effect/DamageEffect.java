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
public class DamageEffect
    implements FREffect
{
    private int damage;
    
    public DamageEffect(int dmg)
    {
        this.damage = dmg;
    }
    
    @Override
    public boolean isStackable()
    {
        return true;
    }

    @Override
    public RunnerEffectableData impact(RunnerEffectableData rED)
    {
        rED.changeHP(-damage);
        
        return rED;
    }

    @Override
    public float getCoeff()
    {
        return this.damage;
    }

    @Override
    public boolean isCompleted()
    {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Update()
    {
    }
    
}
