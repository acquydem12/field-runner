/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map;

/**
 *
 * @author Uchiha Salm
 */
public class FRMapBuilderFactory
{
    public FRMapBuilder createMapBuilder()
    {
        return new FRMapBuilderImp();
    }
    
    private FRMapBuilderFactory()
    {
        
    }
    
    private static FRMapBuilderFactory inst = new FRMapBuilderFactory();
    
    public static FRMapBuilderFactory getInst()
    {
        return inst;
    }
}
