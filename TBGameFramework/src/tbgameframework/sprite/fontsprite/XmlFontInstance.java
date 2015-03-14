/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlFontInstance implements IFontInfo, XmlInstance{

    String imageName;
    String link;
    String name;
    Map<Character, Rectangle> characterList = new HashMap<>();
    
    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImageName() {
        return this.imageName;
    }

    @Override
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public Map<Character, Rectangle> getCharacterList() {
        return this.characterList;
    }

    @Override
    public void setCharacterList(Map<Character, Rectangle> characterList) {
        this.characterList = characterList;
    }

    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlFontInstance>{
        
        private Factory(){
        }
        
        @Override
        public XmlFontInstance createProduct(){
            return new XmlFontInstance();
        }
        
        public XmlFontInstance createInstance(IFontInfo fontInfo){
            XmlFontInstance xmlFontInstance = new XmlFontInstance();
            xmlFontInstance.name = fontInfo.getName();
            xmlFontInstance.imageName = fontInfo.getImageName();
            xmlFontInstance.characterList = new HashMap(fontInfo.getCharacterList());

            return xmlFontInstance;
        }
        
        private static XmlFontInstance.Factory inst = new XmlFontInstance.Factory();
        
        public static XmlFontInstance.Factory getInst(){
            return inst;
        }
    }
    
    @Override
    public void load(XmlLoader xmlLoader) {
        this.name = xmlLoader.getAttribute(tagName);
        this.imageName = xmlLoader.getAttribute(tagImageName);
        Collection<XmlInstance> collInfo = xmlLoader.<XmlInstance>getAllSubInstances(tagCharacterInfo, FactoryAdapter.<XmlInstance>adapt(XmlCharacterInstance.Factory.getInst()));
        for(XmlInstance xmlIns : collInfo){
            if(xmlIns instanceof XmlCharacterInstance){
                XmlCharacterInstance xmlCharInst = (XmlCharacterInstance)xmlIns;
                this.characterList.put(xmlCharInst.c, new Rectangle(xmlCharInst.x, xmlCharInst.y, 
                                                            xmlCharInst.width, xmlCharInst.height));
            }
        }
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagName, name);
        xmlSaver.addAttribute(tagImageName, imageName);
        for(Character c : characterList.keySet())
        {
            CharacterInfo charInfo = new CharacterInfo(c, characterList.get(c));
            xmlSaver.addSubInstance(tagCharacterInfo, XmlCharacterInstance.Factory.getInst().createInstance(charInfo));
        }
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
        if (property.equals(tagName) && value instanceof String)
        {
            return name.equals(value);
        }
        else if (property.equals(tagImageName) && value instanceof Integer)
        {
            return imageName == value;
        }
        return false;
    }
    
}
