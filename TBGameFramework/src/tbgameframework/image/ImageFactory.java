/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

import java.util.Collection;
import java.util.Map;
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
public class ImageFactory implements ISpriteFactory{

    public static ImageFactory factory = null;
    
    public static ImageFactory getInstance()
    {
        if(factory == null)
        {
            factory = new ImageFactory();
        }
        return factory;
    }
    
    private ImageFactory(){
    }
    
    @Override
    public Map<String, XmlInstance> Load(String xmlFile, String name) {
        
        XmlData xmldata = new XmlDataImp(name, XmlBuilderFactory.getInst(),
                                         FactoryAdapter.<XmlInstance>adapt(XmlImageFactory.getInst()));
        xmldata.readFromFile(xmlFile);
        
        Collection<XmlInstance> listInfo = xmldata.getAllInstances();
        for(XmlInstance info : listInfo)
        {
            if(info instanceof XmlImageInstance)
            {
                XmlImageInstance imageInstance = (XmlImageInstance)info;
                ImageTable.getInst().setAttribute(imageInstance.getID(), imageInstance.getLink());
            }
        }
        return null;
    }

    @Override
    public ISprite CreateSprite(XmlInstance instance) {
        return null;
    }
    
}
