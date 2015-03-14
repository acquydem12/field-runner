/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.imagesprite;

import tbgameframework.utils.factory.Factory;

/**
 *
 * @author MrKupi
 */
public class XmlSpriteFactory
    implements Factory<XmlSpriteDataInstance>{

    @Override
    public XmlSpriteDataInstance createProduct() {
        return new XmlSpriteDataInstance();
    }
    
    public static XmlSpriteFactory getInst()
    {
        return inst;
    }
    
    private XmlSpriteFactory()
    {
        
    }
    
    private static XmlSpriteFactory inst = new XmlSpriteFactory();
    
}
