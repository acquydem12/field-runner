/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.shop;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;


public class XmlShopInstance implements IShop, XmlInstance {

    String towerName;
    String buttonName;
    String mapName;
    String shineName;
    
    public XmlShopInstance(){
        super();
    }
    
    static public class Factory
        implements tbgameframework.utils.factory.Factory<XmlShopInstance>{
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
        public XmlShopInstance createProduct() {
            return new XmlShopInstance();
        }
        
        public XmlShopInstance createInstance(IShop shop){
            XmlShopInstance shopInst = new XmlShopInstance();
            
            shopInst.buttonName = shop.getButtonName();
            shopInst.towerName = shop.getTowerName();
            shopInst.mapName = shop.getMapName();
            shopInst.shineName = shop.getShineName();
            
            return shopInst;
        }
        
    }
    
    @Override
    public String getTowerName() {
        return this.towerName;
    }

    @Override
    public String getButtonName() {
        return this.buttonName;
    }

    @Override
    public String getMapName(){
        return this.mapName;
    }
    
@Override
    public String getShineName(){
        return this.shineName;
    }
    
    @Override
    public void load(XmlLoader xmlLoader) {
        this.buttonName = xmlLoader.getAttribute(tagButtonName);
        this.towerName = xmlLoader.getAttribute(tagTowerName);
        this.mapName = xmlLoader.getAttribute(tagMapName);
        this.shineName = xmlLoader.getAttribute(tagShineName);
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagButtonName, buttonName);
        xmlSaver.addAttribute(tagTowerName, towerName);
        xmlSaver.addAttribute(tagMapName, mapName);
        xmlSaver.addAttribute(tagShineName, shineName);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
         if (property.equals(tagButtonName) && value instanceof String)
        {
            return this.buttonName.equals(value);
        }
        else if (property.equals(tagTowerName) && value instanceof String)
        {
            return this.towerName.equals(value);
        }
        else if (property.equals(tagMapName) && value instanceof String)
        {
            return this.mapName.equals(value);
        }
        else if (property.equals(tagShineName) && value instanceof String)
        {
            return this.shineName.equals(value);
        }
        return false;
    }
    
}

