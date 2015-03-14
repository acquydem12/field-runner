/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states;

import java.util.HashMap;
import java.util.Map;
import tbgameframework.utils.factory.Factory;

/**
 * Create state by it's type
 * @author Uchiha Salm
 */
public class StateFactoryPool
{
    private Map<String, Factory<State>> pool = new HashMap<>();
    
    private StateFactoryPool()
    {
        
    }
    
    private static StateFactoryPool inst = new StateFactoryPool();
    
    public static StateFactoryPool getInst()
    {
        return inst;
    }
    
    public State getState(String type)
    {
        return pool.get(type).createProduct();
    }
    
    public void addFactory(String type, Factory<State> fState)
    {
        pool.put(type, fState);
    }
    
    public void removeFactory(String type)
    {
        pool.remove(type);
    }
}
