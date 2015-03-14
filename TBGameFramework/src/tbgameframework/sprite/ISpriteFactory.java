/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite;

import java.util.Map;
import tbgameframework.xml.xmldata.XmlInstance;

/**
 *
 * @author MrKupi
 */
public interface ISpriteFactory
{
    /*
     * Load content from xml file
     * and save content to xmlInstance dictionary
     */
    public Map<String, XmlInstance> Load(String xmlFile, String name);
    
    /*
     * Create sprite from XmlInstance via parametter
     */
    public ISprite CreateSprite(XmlInstance instance);
}