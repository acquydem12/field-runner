/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameLogic;

/**
 *
 * @author MrKupi
 */
public interface IRule {
    
    int getHeart();
    String getHeartImage();
    String getHeartFont();
    
    int getMoney();
    String getMoneyFont();
    
    int getScore();
    String getScoreFont();
    
    String getMapName();
    
    final String tagHeart = "Heart";
    final String tagHeartImage = "HeartImage";
    final String tagHeartFont = "HeartFont";
    
    final String tagMoney = "Money";
    final String tagMoneyFont = "MoneyFont";
    
    final String tagScore = "Score";
    final String tagScoreFont = "ScoreFont";
    
    final String tagMapName = "MapName";
}
