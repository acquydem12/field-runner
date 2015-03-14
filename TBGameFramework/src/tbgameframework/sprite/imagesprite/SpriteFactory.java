/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.imagesprite;

import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import tbgameframework.image.ImageInfo;
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
public class SpriteFactory implements ISpriteFactory{

    public static SpriteFactory factory = null;
    
    public static SpriteFactory getInstance(){
        if(factory == null)
        {
            factory = new SpriteFactory();
        }
        return factory;
    }
    
    private SpriteFactory(){
    }
    
    @Override
    public Map<String, XmlInstance> Load(String xmlFile, String name){
        
        Map<String, XmlInstance> instances = new HashMap();
        
        XmlData xmldata = new XmlDataImp(name, XmlBuilderFactory.getInst(),
                                         FactoryAdapter.<XmlInstance>adapt(XmlSpriteFactory.getInst()));
        xmldata.readFromFile(xmlFile);
        
        Collection<XmlInstance> listInfo = xmldata.getAllInstances();
        for(XmlInstance info : listInfo)
        {
            if(info instanceof XmlSpriteDataInstance)
            {
                XmlSpriteDataInstance spriteInstance = (XmlSpriteDataInstance)info;
                ImageInfo imageInfo = new ImageInfo();
                String link = (String)ImageTable.getInst().getAttribute(spriteInstance.getImageName());
                if(link != null)
                {
                    imageInfo.setName(spriteInstance.getName());
                    imageInfo.setLink(link);
                    imageInfo.setImageName(spriteInstance.getImageName());
                    imageInfo.setDrawPoint(spriteInstance.getDrawPoint());
                    imageInfo.setFrame(spriteInstance.getFrame());
                    // put to pool
                    instances.put(imageInfo.getName(), imageInfo);
                }
                else {
                    System.out.println("Image not found, maybe lost. Require re-load image before load sprite");
                }
            }
        }
        return instances;
    }

    @Override
    public ISprite CreateSprite(XmlInstance instance){
        
        if(instance instanceof ImageInfo)
        {
            ImageInfo imageInstance = (ImageInfo)instance;
            Image image = ImageLoader.getInstance().Load(imageInstance.getLink());
            SpriteInfo spriteInfo;
            
            if(image != null)
            {
                spriteInfo = new SpriteInfo(imageInstance.getName(), imageInstance.getFrame(),
                        imageInstance.getDrawPoint());
                return new ImageSprite(image, spriteInfo);
            }
        }
        return null;
    }
    
}

