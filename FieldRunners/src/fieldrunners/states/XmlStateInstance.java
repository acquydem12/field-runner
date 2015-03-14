/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states;

import java.util.Collection;
import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class XmlStateInstance
    implements XmlInstance, State
{
    private static final String tagName = "Name";
    private static final String tagType = "Type";
    private static final String tagSubState = "SubState";
    
    private State inst;
    private String name;
    private String type;

    @Override
    public int getState(int stateID)
    {
        return this.inst.getState(stateID);
    }

    @Override
    public void setState(int stateID, int value) 
    {
        this.inst.setState(stateID, value);
    }

    @Override
    public Collection<Integer> getAllID() 
    {
        return this.inst.getAllID();
    }
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlStateInstance>
    {

        @Override
        public XmlStateInstance createProduct()
        {
            return new XmlStateInstance();
        }
        
        private Factory()
        {
            
        }
        
        private static XmlStateInstance.Factory inst = new Factory();
        
        public static Factory getInst()
        {
            return inst;
        }
    }
    
    public XmlStateInstance()
    {
    }
    
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.name = xmlLoader.getAttribute(tagName);
        this.type = xmlLoader.getAttribute(tagType);
        
        this.inst = StateFactoryPool.getInst().getState(type);
        
        Collection<XmlSubStateInstance> subStates = xmlLoader.getAllSubInstances(tagSubState, XmlSubStateInstance.Factory.getInst());
        
        for (XmlSubStateInstance subState : subStates)
        {
            this.inst.setState(subState.getID(), subState.getValue());
        }
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        xmlSaver.addAttribute(tagName, this.name);
        xmlSaver.addAttribute(tagType, this.type);
        
        Collection<Integer> IDs = this.inst.getAllID();
        
        for (Integer id : IDs)
        {
            xmlSaver.addSubInstance(tagSubState, XmlSubStateInstance.Factory.getInst().createProduct(id, this.inst.getState(id)));
        }
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        return (tagName.equals(property) && value.equals(name));
    }
    
    public String GetName()
    {
        return this.name;
    }
    
    public String getType()
    {
        return this.type;
    }

    @Override
    public int hashCode()
    {
        return inst.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return inst.equals(obj);
    }
    
    
}
