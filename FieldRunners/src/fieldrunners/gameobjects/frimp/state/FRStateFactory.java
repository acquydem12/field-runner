/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp.state;

import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class FRStateFactory
    implements Factory<FRState>
{
    @Override
    public FRState createProduct()
    {
        return new FRState();
    }
    
    private FRStateFactory()
    {
        
    }
    
    static private FRStateFactory inst = new FRStateFactory();
    
    static public FRStateFactory getInst()
    {
        return inst;
    }
}
