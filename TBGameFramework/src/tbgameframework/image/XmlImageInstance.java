/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlImageInstance implements IImage, XmlInstance{

    private String imageName;
    private String link;
    
    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public String getID() {
        return this.imageName;
    }

    public static class Factory
    implements tbgameframework.utils.factory.Factory<XmlImageInstance>{
        private Factory()
        {
        }
        
        @Override
        public XmlImageInstance createProduct(){
            return new XmlImageInstance();
        }
        
        public XmlImageInstance createInstance(IImage imageTableElem)
        {
            XmlImageInstance xmlImage = new XmlImageInstance();
            xmlImage.link = imageTableElem.getLink();
            xmlImage.imageName = imageTableElem.getID();

            return xmlImage;
        }
        
        private static XmlImageInstance.Factory inst = new XmlImageInstance.Factory();
        
        public static XmlImageInstance.Factory getInst()
        {
            return inst;
        }
    }
    
    @Override
    public void load(XmlLoader xmlLoader) {
        this.link = xmlLoader.getAttribute(tagLink);
        this.imageName = xmlLoader.getAttribute(tagImageName);
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagLink, this.link);
        xmlSaver.addAttribute(tagImageName, this.imageName);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
        if (property.equals(tagLink) && value instanceof String)
        {
            return link.equals(value);
        }
        else if (property.equals(tagImageName) && value instanceof Integer)
        {
            return imageName == value;
        }
        return false;
    }
    
}
