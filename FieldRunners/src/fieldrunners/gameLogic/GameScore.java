/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameLogic;

import java.awt.Graphics2D;
import tbgameframework.Board;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;
import tbgameframework.Window;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sound.SoundManager;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.effect.SwellEffect;
import tbgameframework.sprite.fontsprite.FontSprite;
import tbgameframework.sprite.imagesprite.ImageSprite;
import tbgameframework.sprite.spriteEffect.ImageSpriteEffect;

/**
 *
 * @author MrKupi
 */
public class GameScore implements IDrawable, IUpdateable
{
    public Vector2D windowSize = new Vector2D(Window.getInstance().getWidth(), Window.getInstance().getHeight());
    private Vector2D scale = new Vector2D(1.0, 1.0);
    final public int SIDEBAR = 30;
    private boolean isVisible;
    private boolean isEnable;
    private float depth;

    private String heartImageName;
    private ImageSpriteEffect heartImage;
    private Vector2D heartImagePosition;
    private Vector2D heartPosition = new Vector2D(940.0, 660.0);
    
    private int heart = 0;
    private String heartFontName;
    private Vector2D heartFontPosition;
    private FontSprite heartSprite;
    
    private int money = 0;
    private String moneyImageName;
    private FontSprite moneySprite;
    private Vector2D moneyPosition = new Vector2D(30.0, 660.0);
    
    private int score = 0;
    private String scoreImageName;
    private FontSprite scoreSprite;
    private Vector2D scorePosition;
    
    static public class Factory
    {
        static private Factory inst;
        
        private Factory(){
        }
        
        static public Factory getInst(){
            if(inst == null){
                inst = new Factory();
            }
            return inst;
        }
        
        public GameScore createProduct(String mapName)
        {
            GameScore gameScore = new GameScore();
            IRule rule = GameRuleBank.getInst().getGameRule(mapName);
            gameScore.heart = rule.getHeart();
            gameScore.heartImageName = rule.getHeartImage();
            gameScore.heartFontName = rule.getHeartFont();
            
            gameScore.money = rule.getMoney();
            gameScore.moneyImageName = rule.getMoneyFont();
                    
            gameScore.score = rule.getScore();
            gameScore.scoreImageName = rule.getScoreFont();
            
            gameScore.Init();
            
            return gameScore;
        }
    }
    
    private GameScore()
    {
    }
    
    private void Init()
    {
        if(this.heartImageName != null)
        {
            heartImage = new ImageSpriteEffect((ImageSprite)SpriteManager.getInstance().getSprite(heartImageName));
            heartImagePosition = new Vector2D(windowSize.x - heartImage.getWidth(), windowSize.y - heartImage.getHeight() - SIDEBAR);
            heartImage.setDrawObject(new ImageDrawObject(heartImagePosition, new Vector2D(1, 1), 0.0f));
            heartImage.setIstransformable(false);

            SwellEffect swell = new SwellEffect(false);
            swell.setStartScale(heartImage.getDrawObject().getScale());
            swell.setEndScale(new Vector2D(1.5, 1.5));
            heartImage.AddEffect(swell);
        }
        if(this.heartFontName != null){
            heartSprite = (FontSprite)SpriteManager.getInstance().getSprite(heartFontName);
            heartSprite.setIstransformable(false);
            heartFontPosition = new Vector2D(windowSize.x - heartSprite.getTextSize().x, windowSize.y - SIDEBAR - heartSprite.getTextSize().y);
            if(heartImageName != null)
            {
                heartFontPosition.x -= heartImage.getWidth();
                heartFontPosition.y = heartImagePosition.y + SIDEBAR;
            }
        }
        if(this.moneyImageName != null){
            moneySprite = (FontSprite)SpriteManager.getInstance().getSprite(moneyImageName);
            moneySprite.setIstransformable(false);
            moneyPosition = new Vector2D(SIDEBAR, windowSize.y - moneySprite.getTextSize().y - 2*SIDEBAR);
        }
        if(this.scoreImageName != null){
            scoreSprite = (FontSprite)SpriteManager.getInstance().getSprite(scoreImageName);
            scoreSprite.setIstransformable(false);
            scorePosition = new Vector2D(windowSize.x/2 - scoreSprite.getTextSize().x/2, windowSize.y - 2*SIDEBAR - scoreSprite.getTextSize().y);
        }
    }
    
    @Override
    synchronized public void Update() 
    {
        heartImage.Update();
        UpdatePosition();
    }

    @Override
    synchronized  public void Draw(Graphics2D g2D) 
    {
        if(heartImage != null){
            heartImage.Draw(g2D);
        }
        if(heartSprite != null){
            heartSprite.Draw(g2D, Integer.toString(heart), heartFontPosition, scale, 0.0f);
        }
        if(moneySprite != null){
            moneySprite.Draw(g2D, Integer.toString(money), moneyPosition, scale, 0.0f);
        }
        if(scoreSprite != null){
            scoreSprite.Draw(g2D, Integer.toString(score), scorePosition, scale, 0.0f);
        }
    }

    private void UpdatePosition()
    {
        heartFontPosition.x = windowSize.x - heartSprite.getTextSize().x;
        heartFontPosition.y = windowSize.y - SIDEBAR - heartSprite.getTextSize().y;
        if(heartImageName != null)
        {
            heartFontPosition.x -= heartImage.getWidth();
            heartFontPosition.y = heartImagePosition.y + SIDEBAR;
        }
        
        moneyPosition.x = SIDEBAR;
        moneyPosition.y = windowSize.y - moneySprite.getTextSize().y - 2*SIDEBAR;
        
        scorePosition.x = windowSize.x/2 - scoreSprite.getTextSize().x/2;
        scorePosition.y = windowSize.y - 2*SIDEBAR - scoreSprite.getTextSize().y;
    }
    
    public void MinusHeart(int heart)
    {
        this.heart -= heart;
        if(this.heart < 0)
        {
            this.heart = 0;
        }
        else{
            heartImage.startAll();
        SoundManager.play("escape", false);
        }
        this.heartSprite.setText(Integer.toString(this.heart));
        
    }
    
    public void AddMoney(int money)
    {
        this.money += money;
        this.moneySprite.setText(Integer.toString(money));
    }
    
    public void AddScore(int score)
    {
        this.score += score;
        this.scoreSprite.setText(Integer.toString(score));
    }
    
    public int getMoney(){
        return this.money;
    }
    
    public int getHeart(){
        return this.heart;
    }
    
    public int getScore(){
        return this.score;
    }
    
    @Override
    public boolean isVisibled() 
    {
        return this.isVisible;
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        this.isVisible = isVs;
    }

    @Override
    public float getDepth()
    {
        return this.depth;
    }

    @Override
    public void setDepth(float depth)
    {
        this.depth = depth;
    }
    
    @Override
    public boolean isEnabled()
    {
        return this.isEnable;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.isEnable = isEn;
    }
}
