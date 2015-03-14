/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners.effect;

import java.util.HashMap;
import java.util.Map;
import tbgameframework.utils.factory.Factory;
import tbgameframework.utils.factory.FactoryAdapter;

/**
 *
 * @author Uchiha Salm
 */
public class FREffectBank
{
    private Map<String, Factory<FREffectFactory>> effectTypes;
    
    private FREffectBank()
    {
        this.effectTypes = new HashMap<>();
        
        this.init();
    }
    
    private void init()
    {
        this.addEffectType("Damage", FactoryAdapter.<FREffectFactory>adapt(DamageEffectFactory.Factory.getInst()));
        this.addEffectType("Slow", FactoryAdapter.<FREffectFactory>adapt(SlowEffectFactory.Factory.getInst()));
    }
    
    private static FREffectBank inst = new FREffectBank();
    
    public static FREffectBank getInst()
    {
        return inst;
    }
    
    public void addEffectType(String type, Factory<FREffectFactory> effff)
    {
        this.effectTypes.put(type, effff);
    }
    
    public void removeEffectType(String type)
    {
        this.effectTypes.remove(type);
    }
    
    
    public FREffectFactory getEffect(String type)
    {
        return this.effectTypes.get(type).createProduct();
    }
}
