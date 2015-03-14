/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.decider;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class Store implements IStore, XmlInstance{

    private String towerName;
    private int value;
    
    @Override
    public String getTowerName() {
        return this.towerName;
    }

    @Override
    public int getValue() {
        return this.value;
    }
    
    static public class Factory
        implements tbgameframework.utils.factory.Factory<Store>{
        static private Factory inst;
        
        private Factory(){
            
        }
        
        static public Factory getInst(){
            if(inst == null){
                inst = new Factory();
            }
            return inst;
        }

        @Override
        public Store createProduct() {
            return new Store();
        }
        
        public Store createInstance(IStore shop){
            Store shopInst = new Store();
            
            shopInst.towerName = shop.getTowerName();
            shopInst.value = shop.getValue();
            
            return shopInst;
        }
        
    }
    
    @Override
    public void load(XmlLoader xmlLoader) {
        this.towerName = xmlLoader.getAttribute(tagTowerName);
        this.value = xmlLoader.getIntegerAttribute(tagValue);
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagTowerName, towerName);
        xmlSaver.addAttribute(tagValue, value);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
        if (property.equals(tagTowerName) && value instanceof String)
        {
            return this.towerName.equals(value);
        }
        else if (property.equals(tagValue) && value instanceof Integer)
        {
            return this.value == value;
        }
        return false;
    }
    
}
