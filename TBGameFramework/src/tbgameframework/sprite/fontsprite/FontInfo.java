/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import java.awt.Rectangle;
import java.util.Map;
import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class FontInfo implements IFontInfo, XmlInstance{

    String imageName;
    String link;
    String name;
    Map<Character, Rectangle> characterList;
    
    public FontInfo(){
        
    }
    
    public FontInfo(String name, Map<Character, Rectangle> characterList){
        this.name = name;
        this.characterList = characterList;
    }
    
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

    @Override
    public void load(XmlLoader xmlLoader) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
