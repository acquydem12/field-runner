/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states;

import fieldrunners.xml.datas.FRXmlRootData;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import tbgameframework.xml.xmldata.XmlData;

/**
 *
 * @author Uchiha Salm
 */
public class StateBank
{
    private Map<String, State> bank = new HashMap<>();
    
    private static final String tagName = "State";
    
    private StateBank()
    {
        
    }
    
    private static StateBank inst = new StateBank();
    
    public static StateBank getInst()
    {
        return inst;
    }
    
    public void addState(String name, State st)
    {
        this.bank.put(name, st);
    }
    
    public void removeState(String name)
    {
        this.bank.remove(name);
    }
    
    public State getState(String name)
    {
        return this.bank.get(name);
    }
    
    public void readFromFile(String fileName)
    {
        XmlData<XmlStateInstance> xmlStates = new FRXmlRootData(tagName, XmlStateInstance.Factory.getInst());
        xmlStates.readFromFile(fileName);
        
        Collection<XmlStateInstance> states = xmlStates.getAllInstances();
        
        for (XmlStateInstance stInst : states)
        {
            this.addState(stInst.GetName(), stInst);
        }
    }
}