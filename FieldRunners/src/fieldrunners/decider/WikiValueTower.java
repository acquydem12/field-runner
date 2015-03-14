/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.decider;

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
 public class WikiValueTower {
    
    final String tagButton = "ShopItem";
    static WikiValueTower inst = null;
    
    public Map<String, Integer> wikies = new HashMap<>();
    
    private WikiValueTower(){
        
    }
    
    static public WikiValueTower getInst(){
        if(inst == null){
            inst = new WikiValueTower();
        }
        return inst;
    }
    
    public Integer getTowerValue(String towerName){
        try{
            return wikies.get(towerName);
        }catch(Exception ex){
            System.out.println("Can not found - " + ex);
        }
        return null;
    }
    
    public void readFromFile(String xmlFile){
        
        XmlData xmldata = new XmlDataImp(tagButton, XmlBuilderFactory.getInst(),
                                         FactoryAdapter.<XmlInstance>adapt(Store.Factory.getInst()));
        xmldata.readFromFile(xmlFile);
        
        Collection<XmlInstance> buttonsInfo = xmldata.getAllInstances();
        for(XmlInstance xml : buttonsInfo)
        {
            if(xml instanceof Store){
                this.wikies.put(((Store)xml).getTowerName(), ((Store)xml).getValue());
            }
        }
    }
}

