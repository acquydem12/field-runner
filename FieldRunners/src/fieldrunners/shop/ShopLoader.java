/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.shop;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.xmldata.XmlData;
import tbgameframework.xml.xmldata.XmlDataImp;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.builder.XmlBuilderFactory;

/**
 *
 * @author MrKupi
 */
public class ShopLoader {
    
    final String tagButton = "ShopItem";
    static ShopLoader inst = null;
    
    public LinkedList<IShopImpl> shops = new LinkedList<>();
    
    private ShopLoader()
    {
    }
    
    static public ShopLoader getInst()
    {
        if(inst == null){
            inst = new ShopLoader();
        }
        return inst;
    }
    
    public List<IShopImpl> getShops(String mapName)
    {
        List<IShopImpl> list = new LinkedList<>();
        for(IShopImpl shopMember : shops){
            if(shopMember.getMapName().equals(mapName))
            {
                list.add(shopMember);
            }
        }
        return list;
    }
    
    public String getTower(String buttonName, String mapName)
    {
        try
        {
            for(IShopImpl shopMember : shops){
                if(shopMember.getMapName().equals(mapName) && shopMember.getButtonName().equals(buttonName))
                {
                    return shopMember.getTowerName();
                }
            }
        }catch(Exception ex)
        {
            System.out.println("Tower not found - " + ex);
        }
        return null;
    }
    
    public String getShineName(String buttonName, String mapName)
    {
        try
        {
            for(IShopImpl shopMember : shops){
                if(shopMember.getMapName().equals(mapName) && shopMember.getButtonName().equals(buttonName))
                {
                    return shopMember.getShineName();
                }
            }
        }catch(Exception ex)
        {
            System.out.println("ShineName not found - " + ex);
        }
        return null;
    }
    
    public void readFromFile(String xmlFile)
    {
        XmlData xmldata = new XmlDataImp(tagButton, XmlBuilderFactory.getInst(),
                                         FactoryAdapter.<XmlInstance>adapt(XmlShopInstance.Factory.getInst()));
        xmldata.readFromFile(xmlFile);
        
        Collection<XmlInstance> buttonsInfo = xmldata.getAllInstances();
        for(XmlInstance xml : buttonsInfo)
        {
            if(xml instanceof XmlShopInstance)
            {
                XmlShopInstance xmlShop = (XmlShopInstance)xml;
                this.shops.add(new IShopImpl(xmlShop.getMapName(), xmlShop.getButtonName(), xmlShop.getTowerName(), xmlShop.getShineName()));
            }
        }
    }
}
