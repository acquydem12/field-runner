/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map;

import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class FRMapDataFactory
    implements Factory<FRMapData>
{
    @Override
    public FRMapData createProduct()
    {
        return new FRMapXmlData();
    }
    
    public static FRMapDataFactory getInst()
    {
        return inst;
    }
    
    private FRMapDataFactory()
    {
    }
    
    private static FRMapDataFactory inst = new FRMapDataFactory();
}
