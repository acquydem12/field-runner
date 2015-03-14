/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control.buttons;

/**
 *
 * @author MrKupi
 */
public interface IButtonInfo {
    
    String getName();
    String getOwner();
    String getImageName();
    String getImageNameOver();
    String getImageNameDisabled();
    String getFontName();
    boolean getIsUntransfom();
    String getType();
    int getWidth();
    int getHeight();
    String getClickMethod();
    String getOverMethod();
    String getReleaseMethod();
    
    static final String tagName = "Name";
    static final String tagOwner = "Owner";
    static final String tagImageName = "ImageName";
    static final String tagImageNameOver = "ImageNameOver";
    static final String tagImageNameDisabled = "ImageNameDisable";
    static final String tagFontName = "FontName";
    static final String tagIsUntransform = "IsUntransform";
    static final String tagType = "Type";
    static final String tagWidth = "Width";
    static final String tagHeight = "Height";
    static final String tagClick = "Click";
    static final String tagOver = "Over";
    static final String tagRelease = "Release";
}
