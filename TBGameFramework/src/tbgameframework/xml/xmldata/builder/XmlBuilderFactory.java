/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata.builder;

import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class XmlBuilderFactory
    implements Factory<XmlBuilder>
{
    private XmlBuilderFactory()
    {
        
    }
    
    private static XmlBuilderFactory instance = new XmlBuilderFactory();
    
    public static XmlBuilderFactory getInst()
    {
        return instance;
    }
    
    @Override
    public XmlBuilder createProduct()
    {
        return new XmlBuilderImp();
    }
    
}
