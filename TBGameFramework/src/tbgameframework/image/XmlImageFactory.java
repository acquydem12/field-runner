/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

import tbgameframework.image.XmlImageInstance;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author MrKupi
 */
public class XmlImageFactory
    implements Factory<XmlImageInstance>{

    public static XmlImageFactory getInst()
    {
        return inst;
    }
    
    private XmlImageFactory()
    {
    }
    
    private static XmlImageFactory inst = new XmlImageFactory();

    @Override
    public XmlImageInstance createProduct() {
        return new XmlImageInstance();
    }
}
