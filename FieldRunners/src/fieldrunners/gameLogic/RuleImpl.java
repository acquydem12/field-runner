/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameLogic;


public class RuleImpl implements IRule{

    String mapName;
    
    int heart;
    String heartImage;
    String heartFont;
    
    int money;
    String moneyFont;
    
    int score;
    String scoreFont;
    
    public RuleImpl(String mapName, int heart, String heartImage, String heartFont, int money, String moneyFont, int score, String scoreFont){
        this.mapName = mapName;
        this.heart = heart;
        this.heartImage = heartImage;
        this.heartFont = heartFont;
        
        this.money = money;
        this.moneyFont = moneyFont;
        
        this.score = score;
        this.scoreFont = scoreFont;
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
