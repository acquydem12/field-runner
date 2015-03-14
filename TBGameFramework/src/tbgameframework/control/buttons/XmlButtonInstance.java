/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control.buttons;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlButtonInstance implements IButtonInfo, XmlInstance{

    String name;
    String owner;
    String imageName;
    String imageNameOver;
    String imageNameDisabled;
    String fontName;
    boolean isUntransform;
    String type;
    int width;
    int height;
    String clickMethod, overMethod, releaseMethod;
    
    public XmlButtonInstance(){
        super();
    }
    
    public static class Factory 
        implements tbgameframework.utils.factory.Factory<XmlButtonInstance>{

        static private Factory inst = null;
        
        private Factory(){
        }
        
        static public Factory getInst(){
            if(inst == null){
                inst = new Factory();
            }
            return inst;
        }
        
        @Override
        public XmlButtonInstance createProduct() {
            return new XmlButtonInstance();
        }
        
        public XmlButtonInstance createInstance(IButtonInfo buttonInfo){
            XmlButtonInstance buttonInst = new XmlButtonInstance();
            
            buttonInst.name = buttonInfo.getName();
            buttonInst.owner = buttonInfo.getOwner();
            buttonInst.imageName = buttonInfo.getImageName();
            buttonInst.imageNameOver = buttonInfo.getImageNameOver();
            buttonInst.imageNameDisabled = buttonInfo.getImageNameDisabled();
            buttonInst.fontName = buttonInfo.getFontName();
            buttonInst.isUntransform = buttonInfo.getIsUntransfom();
            buttonInst.type = buttonInfo.getType();
            buttonInst.width = buttonInfo.getWidth();
            buttonInst.height = buttonInfo.getHeight();
            buttonInst.clickMethod = buttonInfo.getClickMethod();
            buttonInst.releaseMethod = buttonInfo.getReleaseMethod();
            buttonInst.overMethod = buttonInfo.getOverMethod();
            
            return buttonInst;
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getOwner() {
        return this.owner;
    }
    
    @Override
    public String getImageName() {
        return this.imageName;
    }

    @Override
    public String getImageNameOver() {
        return this.imageNameOver;
    }
    
    @Override
    public String getImageNameDisabled() {
        return this.imageNameDisabled;
    }

    @Override
    public String getFontName() {
        return this.fontName;
    }

    @Override
    public boolean getIsUntransfom() {
        return this.isUntransform;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public String getClickMethod() {
        return this.clickMethod;
    }

    @Override
    public String getOverMethod() {
        return this.overMethod;
    }

    @Override
    public String getReleaseMethod() {
        return this.releaseMethod;
    }

    @Override
    public void load(XmlLoader xmlLoader) {
        this.name = xmlLoader.getAttribute(tagName);
        this.owner = xmlLoader.getAttribute(tagOwner);
        this.imageName = xmlLoader.getAttribute(tagImageName);
        this.imageNameOver = xmlLoader.getAttribute(tagImageNameOver);
        this.imageNameDisabled = xmlLoader.getAttribute(tagImageNameDisabled);
        this.fontName = xmlLoader.getAttribute(tagFontName);
        this.isUntransform = xmlLoader.getIntegerAttribute(tagIsUntransform) == 1 ? true : false;
        this.type = xmlLoader.getAttribute(tagType);
        this.width = xmlLoader.getIntegerAttribute(tagWidth);
        this.height = xmlLoader.getIntegerAttribute(tagHeight);
        this.clickMethod = xmlLoader.getAttribute(tagClick);
        this.overMethod = xmlLoader.getAttribute(tagOver);
        this.releaseMethod = xmlLoader.getAttribute(tagRelease);
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagImageName, this.imageName);
        xmlSaver.addAttribute(tagImageNameOver, this.imageNameOver);
        xmlSaver.addAttribute(tagImageNameDisabled, this.imageNameDisabled);
        xmlSaver.addAttribute(tagFontName, this.fontName);
        xmlSaver.addAttribute(tagIsUntransform, this.isUntransform == true ? 1 : 0);
        xmlSaver.addAttribute(tagType, this.type);
        xmlSaver.addAttribute(tagWidth, this.width);
        xmlSaver.addAttribute(tagHeight, this.height);
        xmlSaver.addAttribute(tagClick, this.clickMethod);
        xmlSaver.addAttribute(tagOver, this.overMethod);
        xmlSaver.addAttribute(tagRelease, this.releaseMethod);
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
        if (property.equals(tagImageName) && value instanceof String)
        {
            return this.imageName.equals(value);
        }
        else if (property.equals(tagImageNameOver) && value instanceof String)
        {
            return this.imageNameOver.equals(value);
        }
        else if (property.equals(tagImageNameDisabled) && value instanceof String)
        {
            return this.imageNameDisabled.equals(value);
        }
        else if (property.equals(tagFontName) && value instanceof String)
        {
            return this.fontName == value;
        }
        else if (property.equals(tagIsUntransform) && value instanceof Integer)
        {
            return (this.isUntransform == true ? 1 : 0) == (int)value;
        }
        else if (property.equals(tagType) && value instanceof String)
        {
            return this.type == value;
        }
        else if (property.equals(tagWidth) && value instanceof Integer)
        {
            return this.width == (int)value;
        }
        else if (property.equals(tagHeight) && value instanceof Integer)
        {
            return this.height == (int)value;
        }
        else if (property.equals(tagClick) && value instanceof String)
        {
            return this.clickMethod == value;
        }
        else if (property.equals(tagOver) && value instanceof String)
        {
            return this.overMethod == value;
        }
        else if (property.equals(tagRelease) && value instanceof String)
        {
            return this.releaseMethod == value;
        }
        return false;
    }
    
}
