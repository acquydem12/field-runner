/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameLogic;

import fieldrunners.shop.IShopImpl;
import fieldrunners.shop.XmlShopInstance;
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
public class GameRuleBank {
    
    private final String tagInfo = "Info";
    private static GameRuleBank inst;
    private Map<String, IRule> ruleInfos = new HashMap<>();
    
    private GameRuleBank(){
    }
    
    static public GameRuleBank getInst()
    {
        if(inst == null){
            inst = new GameRuleBank();
        }
        return inst;
    }
    
    public IRule getGameRule(String mapName){
        try
        {
            IRule rule = ruleInfos.get(mapName);
            RuleImpl currentRule = null;
            if(rule != null){
                currentRule = new RuleImpl(rule.getMapName(), rule.getHeart(), rule.getHeartImage(), rule.getHeartFont(),
                        rule.getMoney(), rule.getMoneyFont(), rule.getScore(), rule.getScoreFont());
            }
            return currentRule;
        }catch(Exception ex)
        {
            System.out.println("Can not found game rule corrective");
            return null;
        }
    }
    
    public void readFromFile(String xmlFile)
    {
        XmlData xmldata = new XmlDataImp(tagInfo, XmlBuilderFactory.getInst(),
                                         FactoryAdapter.<XmlInstance>adapt(XmlRuleInstance.Factory.getInst()));
        xmldata.readFromFile(xmlFile);
        
        Collection<XmlInstance> ruleInfo = xmldata.getAllInstances();
        for(XmlInstance xml : ruleInfo)
        {
            if(xml instanceof XmlRuleInstance)
            {
                XmlRuleInstance rule = (XmlRuleInstance)xml;
                this.ruleInfos.put(rule.getMapName(), new RuleImpl(rule.getMapName(), rule.getHeart(), 
                        rule.getHeartImage(), rule.getHeartFont(),
                        rule.getMoney(), rule.getMoneyFont(), rule.getScore(), rule.getScoreFont()));
            }
        }
    }
}
