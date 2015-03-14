/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import tbgameframework.utils.factory.Factory;

/**
 *
 * @author MrKupi
 */
public class XmlFontFactory
    implements Factory<XmlFontInstance>{

    @Override
    public XmlFontInstance createProduct() {
        return new XmlFontInstance();
    }
    
    public static XmlFontFactory getInst()
    {
        return inst;
    }
    
    private XmlFontFactory()
    {
        
    }
    
    private static XmlFontFactory inst = new XmlFontFactory();
    
}
