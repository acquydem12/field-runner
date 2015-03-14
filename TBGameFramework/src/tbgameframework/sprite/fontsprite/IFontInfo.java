/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import java.awt.Rectangle;
import java.util.Map;

/**
 *
 * @author MrKupi
 */
public interface IFontInfo {
    
    String getLink();
    void setLink(String link);
    String getName();
    void setName(String name);
    String getImageName();
    void setImageName(String imagename);
    Map<Character, Rectangle> getCharacterList();
    void setCharacterList(Map<Character, Rectangle> characterList);
    
    static final String tagName = "Name";
    static final String tagImageName = "ImageName";
    static final String tagCharacterInfo = "Character";
}
