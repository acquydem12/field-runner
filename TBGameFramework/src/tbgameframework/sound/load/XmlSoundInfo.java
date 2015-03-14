/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sound.load;

import org.w3c.dom.Element;
import tbgameframework.control.buttons.XmlButtonInstance;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlSoundInfo implements ISoundInfo, XmlInstance{

    private String name;
    private String link;
    
    public XmlSoundInfo(){
    }
    
    public static class Factory 
        implements tbgameframework.utils.factory.Factory<XmlSoundInfo>{

        static Factory factory;
        
        static public Factory getInst(){
            if(factory == null){
                factory = new Factory();
            }
            return factory;
        }
        
        public XmlSoundInfo CreateInstance(ISoundInfo soundInfo){
            
            XmlSoundInfo sInfo = new XmlSoundInfo();
            
            sInfo.name = soundInfo.getName();
            sInfo.link = soundInfo.getLink();
            
            return sInfo;
        }
        
        @Override
        public XmlSoundInfo createProduct() {
            return new XmlSoundInfo();
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public void load(XmlLoader xmlLoader) {
        this.name = xmlLoader.getAttribute(tagName);
        this.link = xmlLoader.getAttribute(tagLink);
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagName, this.name);
        xmlSaver.addAttribute(tagLink, this.link);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
        if(property.equals(tagName) && value instanceof String){
            return this.name.equals(value);
        }
        else if(property.equals(tagLink) && value instanceof String){
            return this.link.equals(value);
        }
        return false;
    }
    
}
