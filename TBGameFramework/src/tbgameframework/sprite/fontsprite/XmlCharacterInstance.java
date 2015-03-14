/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import org.w3c.dom.Element;
import tbgameframework.xml.instances.XmlRectangleInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlCharacterInstance extends XmlRectangleInstance{
    
    static final String tagChar = "Char";
    char c;
    
    public XmlCharacterInstance(){
        
    }
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlCharacterInstance>{
        private Factory(){
        }
        
        @Override
        public XmlCharacterInstance createProduct(){
            return new XmlCharacterInstance();
        }
        
        public XmlCharacterInstance createInstance(CharacterInfo charInfo)
        {
            XmlCharacterInstance xmlCharacter = new XmlCharacterInstance();
            xmlCharacter.c = charInfo.c;
            xmlCharacter.x = charInfo.getRectangle().x;
            xmlCharacter.y = charInfo.getRectangle().y;
            xmlCharacter.width = charInfo.getRectangle().width;
            xmlCharacter.height = charInfo.getRectangle().height;

            return xmlCharacter;
        }
        
        private static XmlCharacterInstance.Factory inst = new XmlCharacterInstance.Factory();
        
        public static XmlCharacterInstance.Factory getInst()
        {
            return inst;
        }
    }
    
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.c = xmlLoader.getAttribute(tagChar).charAt(0);
        super.load(xmlLoader);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        xmlSaver.addAttribute(tagChar, Character.toString(c));
        
        return super.save(xmlSaver);
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        if (property.equals(tagChar) && value instanceof Character){
            return this.c == (Character) value;
        }
            
        return super.isMatch(property, value);
    }
}