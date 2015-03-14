/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import tbgameframework.image.ImageLoader;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.ISpriteFactory;
import tbgameframework.table.ImageTable;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.xmldata.XmlData;
import tbgameframework.xml.xmldata.XmlDataImp;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.builder.XmlBuilderFactory;

/**
 *
 * @author MrKupi
 */
public class FontFactory implements ISpriteFactory{

    public static FontFactory factory = null;
    
    public static FontFactory getInstance(){
        if(factory == null)
        {
            factory = new FontFactory();
        }
        return factory;
    }
    
    private FontFactory(){
    }
    
    @Override
    public Map<String, XmlInstance> Load(String xmlFile, String name) 
    {
        Map<String, XmlInstance> instances = new HashMap();
        
        // Load with factory of XmlFont
        XmlData xmldata = new XmlDataImp(name, XmlBuilderFactory.getInst(),
                                         FactoryAdapter.<XmlInstance>adapt(XmlFontFactory.getInst()));
        xmldata.readFromFile(xmlFile);
        
        Collection<XmlInstance> listInfo = xmldata.getAllInstances();
        for(XmlInstance info : listInfo)
        {
            if(info instanceof XmlFontInstance)
            {
                XmlFontInstance fontInstance = (XmlFontInstance)info;
                
                FontInfo fontInfo = new FontInfo();
                String link = (String)ImageTable.getInst().getAttribute(fontInstance.getImageName());
                if(link != null)
                {
                    fontInfo.setName(fontInstance.getName());
                    fontInfo.setLink(link);
                    fontInfo.setImageName(fontInstance.getImageName());
                    fontInfo.setCharacterList(fontInstance.getCharacterList());
                    instances.put(fontInfo.getName(), fontInfo);
                }
                else {
                    System.out.println("Image not found, maybe lost. Require re-load image before load sprite");
                }
            }
        }
        return instances;
    }

    @Override
    public ISprite CreateSprite(XmlInstance instance) {
                
        if(instance instanceof FontInfo)
        {
            FontInfo fontInstance = (FontInfo)instance;
            Image image = ImageLoader.getInstance().Load(fontInstance.getLink());
            
            if(image != null)
            {
                return new FontSprite(image, fontInstance.getCharacterList());
            }
        }
        return null;
    }
}
