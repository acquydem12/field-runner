/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import tbgameframework.sprite.ISpriteFactory;
import tbgameframework.sprite.ResourceContent;
import tbgameframework.sprite.SpriteManager;

/**
 * 
 * @author MrKupi
 */
public abstract class ImageResourceContent implements ResourceContent{
    
    public Map<String, String> list = new LinkedHashMap();
    public Map<String, ISpriteFactory> listFactory = new LinkedHashMap();
    
    /**
     * Constructor: ImageResourceContent
     * Do nothing
     */
    public ImageResourceContent()
    {
        this.InputString();
    }
    
    /**
     * Implemented class using AddString to add file
     * xml resources to this
     */
    public abstract void InputString();
    
    public void LoadingResource()
    {
        for(String str : list.keySet())
        {
            SpriteManager.getInstance().LoadXml(str, list.get(str), listFactory.get(str));
        }
    }
    
    @Override
    public void AddString(String xmlFile, String name, ISpriteFactory factory){
        list.put(xmlFile, name);
        listFactory.put(xmlFile, factory);
    }
    
    public Map<String, String> getList(){
        return this.list;
    }
}