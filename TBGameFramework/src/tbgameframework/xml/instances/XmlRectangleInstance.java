/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.instances;

import java.awt.Rectangle;
import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlRectangleInstance extends Rectangle
implements XmlInstance{
    
    public XmlRectangleInstance(){
        super();
    }
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlRectangleInstance>{
        private Factory(){
        }
        
        @Override
        public XmlRectangleInstance createProduct(){
            return new XmlRectangleInstance();
        }
        
        public XmlRectangleInstance createInstance(Rectangle rect)
        {
            XmlRectangleInstance xmlRectangle = new XmlRectangleInstance();
            xmlRectangle.x = rect.x;
            xmlRectangle.y = rect.y;
            xmlRectangle.width = rect.width;
            xmlRectangle.height = rect.height;

            return xmlRectangle;
        }
        
        private static XmlRectangleInstance.Factory inst = new XmlRectangleInstance.Factory();
        
        public static XmlRectangleInstance.Factory getInst()
        {
            return inst;
        }
    }

    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.x = xmlLoader.getIntegerAttribute(tagX);
        this.y = xmlLoader.getIntegerAttribute(tagY);
        this.width = xmlLoader.getIntegerAttribute(tagWidth);
        this.height = xmlLoader.getIntegerAttribute(tagHeight);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        xmlSaver.addAttribute(tagX, x);
        xmlSaver.addAttribute(tagY, x);
        xmlSaver.addAttribute(tagWidth, width);
        xmlSaver.addAttribute(tagHeight, height);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        if (property.equals(tagX) && value instanceof Integer){
            return this.x == (Integer) value;
        }
        else if (property.equals(tagY) && value instanceof Integer){
            return this.y == (Integer) value;
        }
        else if(property.equals(tagWidth) && value instanceof Integer){
            return this.width == (Integer) value;
        }
        else if(property.equals(tagHeight) && value instanceof Integer){
            return this.height == (Integer) value;
        }
            
        return false;
    }
    
    private static final String tagX = "X";
    private static final String tagY = "Y";
    private static final String tagWidth = "Width";
    private static final String tagHeight = "Height";
}
