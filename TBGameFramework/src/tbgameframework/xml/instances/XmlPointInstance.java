/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.instances;

import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;
import java.awt.Point;
import org.w3c.dom.Element;

/**
 *
 * @author Uchiha Salm
 */
public class XmlPointInstance
    extends Point
    implements XmlInstance
{
    public XmlPointInstance()
    {
        super();
    }
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlPointInstance>
    {
        private Factory()
        {
            
        }
        
        private static Factory inst = new Factory();
        
        public static Factory getInst()
        {
            return inst;
        }

        @Override
        public XmlPointInstance createProduct()
        {
            XmlPointInstance xmlPoint = new XmlPointInstance();

            return xmlPoint;
        }
        
        public XmlPointInstance createProduct(Point location)
        {
            XmlPointInstance xmlPoint = createProduct();
            xmlPoint.setLocation(location);
            
            return xmlPoint;
        }
    }

    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.x = xmlLoader.getIntegerAttribute(tagX);
        this.y = xmlLoader.getIntegerAttribute(tagY);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        xmlSaver.addAttribute(tagX, x);
        xmlSaver.addAttribute(tagY, x);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        if (property.equals(tagX) && value instanceof Integer)
        {
            return this.x == (Integer) value;
        }
        else if (property.equals(tagY) && value instanceof Integer)
        {
            return this.y == (Integer) value;
        }
        
        return false;
    }
    
    private static final String tagX = "X";
    private static final String tagY = "Y";
}
