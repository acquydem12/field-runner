/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
class XmlSubStateInstance
    implements XmlInstance
{
    private int ID;
    private int value;
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlSubStateInstance>
    {

        @Override
        public XmlSubStateInstance createProduct()
        {
            return new XmlSubStateInstance();
        }
        
        public XmlSubStateInstance createProduct(int ID, int value)
        {
            XmlSubStateInstance xssI = new XmlSubStateInstance();
            
            xssI.ID = ID;
            xssI.value = value;
            
            return xssI;
        }
        
        private Factory()
        {
            
        }
        
        private static Factory inst = new Factory();
        
        public static Factory getInst()
        {
            return inst;
        }
    }
    
    private XmlSubStateInstance()
    {
        
    }
    
    public int getID()
    {
        return ID;
    }
    
    public int getValue()
    {
        return value;
    }
    
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.ID = xmlLoader.getIntegerAttribute("ID");
        this.value = xmlLoader.getIntegerAttribute("Value");
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        xmlSaver.addAttribute("ID", ID);
        xmlSaver.addAttribute("Value", value);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        if ("ID".equals(property) && value.equals(this.ID))
        {
            return true;
        }
        else if ("Value".equals(property) && value.equals(this.value))
        {
            return true;
        }
        
        return false;
    }
}
