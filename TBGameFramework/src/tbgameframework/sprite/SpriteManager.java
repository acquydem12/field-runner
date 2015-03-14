/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tbgameframework.Board;
import tbgameframework.xml.xmldata.XmlInstance;

/**
 *
 * @author MrKupi
 */
public class SpriteManager
{
    Board board = Board.getInstance();
    static private SpriteManager instance = new SpriteManager();
    
    private Map<String, ISprite> spriteDict = new HashMap();
    private Map<String, ISpriteFactory> factoryDict = new HashMap();
    private Map<String, XmlInstance> containerDict = new HashMap();
    private List<String> listXmlLoaded = new ArrayList();
    
    private SpriteManager()
    {
    }
    
    /*
     * Implements Singleton pattern allow get unique object type of
     * SpriteManager
     * 
     */
    static public SpriteManager getInstance()
    {
        return instance;
    }
    
    /*
     * Load Xml Content and save to container
     * by factory parametter
     */
    public boolean LoadXml(String xmlFile, String tagName, ISpriteFactory factory)
    {
        if(!listXmlLoaded.contains(xmlFile)){
            Map<String, XmlInstance> instanceDict = factory.Load(xmlFile, tagName);
            if(instanceDict != null)
            {
                this.containerDict.putAll(instanceDict);
                for(String name : instanceDict.keySet())
                {
                    factoryDict.put(name, factory);
                }
                return true;
            }
            listXmlLoaded.add(xmlFile);
        }
        return false;
    }
    
    /*
     * Load an content like resource thought name parametter
     * and factory in library
     */
    public boolean LoadContent(String name)
    {
        if(!spriteDict.containsKey(name))
        {
            ISpriteFactory factory = factoryDict.get(name);
            if(factory != null)
            {
                try
                {
                    spriteDict.put(name, factory.CreateSprite(containerDict.get(name)));
                }catch(Exception ex)
                {
                    System.out.println("Content not found - " + ex);
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    /*
     * Allow get Sprite in library by name
     */
    public ISprite getSprite(String name)
    {
        ISprite sprite = null;
        if(spriteDict.containsKey(name))
        {
            sprite = spriteDict.get(name).clone();
        }
        else 
        {
            if(this.LoadContent(name))
            {
                sprite = this.getSprite(name);
            }
        }
        return sprite;
    }
}
