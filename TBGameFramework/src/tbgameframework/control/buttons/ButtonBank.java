/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control.buttons;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.xmldata.XmlData;
import tbgameframework.xml.xmldata.XmlDataImp;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.builder.XmlBuilderFactory;

/**
 *
 * @author MrKupi
 */
public class ButtonBank {
    
    final String tagButton = "ButtonInfo";
    static ButtonBank inst = null;
    
    public Map<String, IButtonInfo> buttons = new HashMap<>();
    
    private ButtonBank(){
        
    }
    
    static public ButtonBank getInst(){
        if(inst == null){
            inst = new ButtonBank();
        }
        return inst;
    }
    
    public IButtonInfo getButton(String buttonName){
        try{
            return buttons.get(buttonName);
        }catch(Exception ex){
            System.out.println("Button not found - " + ex);
            return null;
        }
    }
    
    public void readFromFile(String xmlFile){
        
        XmlData xmldata = new XmlDataImp(tagButton, XmlBuilderFactory.getInst(),
                                         FactoryAdapter.<XmlInstance>adapt(XmlButtonInstance.Factory.getInst()));
        xmldata.readFromFile(xmlFile);
        
        Collection<XmlInstance> buttonsInfo = xmldata.getAllInstances();
        for(XmlInstance xml : buttonsInfo)
        {
            if(xml instanceof XmlButtonInstance){
                this.buttons.put(((XmlButtonInstance)xml).getName(), ((XmlButtonInstance)xml));
            }
        }
    }
}
