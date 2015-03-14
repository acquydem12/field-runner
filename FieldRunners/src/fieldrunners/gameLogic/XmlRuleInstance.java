/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameLogic;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlRuleInstance implements IRule, XmlInstance{

    String mapName;
    
    int heart;
    String heartImage;
    String heartFont;
    
    int money;
    String moneyFont;
    
    int score;
    String scoreFont;
    
    public XmlRuleInstance(){
    }

    static public class Factory
        implements tbgameframework.utils.factory.Factory<XmlRuleInstance>{
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
        public XmlRuleInstance createProduct() {
            return new XmlRuleInstance();
        }
        
        public XmlRuleInstance createInstance(IRule shop){
            XmlRuleInstance ruleInst = new XmlRuleInstance();
            
            ruleInst.mapName = shop.getMapName();
            
            ruleInst.money = shop.getMoney();
            ruleInst.moneyFont = shop.getMoneyFont();
            
            ruleInst.heart = shop.getHeart();
            ruleInst.heartImage = shop.getHeartImage();
            ruleInst.heartFont = shop.getHeartFont();
            
            ruleInst.score = shop.getScore();
            ruleInst.scoreFont = shop.getScoreFont();
            
            return ruleInst;
        }
        
    }
    
    @Override
    public void load(XmlLoader xmlLoader) {
        this.mapName = xmlLoader.getAttribute(tagMapName);
        this.money = xmlLoader.getIntegerAttribute(tagMoney);
        this.moneyFont = xmlLoader.getAttribute(tagMoneyFont);
        this.heart = xmlLoader.getIntegerAttribute(tagHeart);
        this.heartImage = xmlLoader.getAttribute(tagHeartImage);
        this.heartFont = xmlLoader.getAttribute(tagHeartFont);
        this.score = xmlLoader.getIntegerAttribute(tagScore);
        this.scoreFont = xmlLoader.getAttribute(tagScoreFont);
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagMapName, mapName);
        xmlSaver.addAttribute(tagHeart, heart);
        xmlSaver.addAttribute(tagHeartImage, heartImage);
        xmlSaver.addAttribute(tagHeartFont, heartFont);
        xmlSaver.addAttribute(tagMoney, money);
        xmlSaver.addAttribute(tagMoneyFont, moneyFont);
        xmlSaver.addAttribute(tagScore, score);
        xmlSaver.addAttribute(tagScoreFont, scoreFont);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
        if(property.equals(tagMapName) && value instanceof String){
            return mapName.equals(value);
        }
        else if(property.equals(tagMoney) && value instanceof Integer){
            return money == (int)value;
        }
        else if(property.equals(tagHeart) && value instanceof Integer){
            return heart == (int)value;
        }
        else if(property.equals(tagHeartImage) && value instanceof String){
            return heartImage.equals(value);
        }
        else if(property.equals(tagMoneyFont) && value instanceof String){
            return moneyFont.equals(value);
        }
        else if(property.equals(tagScoreFont) && value instanceof String){
            return scoreFont.equals(value);
        }
        else if(property.equals(tagScore) && value instanceof Integer){
            return score == (int)value;
        }
        else if(property.equals(tagHeartFont) && value instanceof Integer){
            return heartFont == value;
        }
        return false;
    }

    @Override
    public int getHeart() {
        return this.heart;
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public String getMapName() {
        return this.mapName;
    }

    @Override
    public String getHeartImage() {
        return this.heartImage;
    }

    @Override
    public String getHeartFont() {
        return this.heartFont;
    }

    @Override
    public String getMoneyFont() {
        return moneyFont;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getScoreFont() {
        return scoreFont;
    }
}
