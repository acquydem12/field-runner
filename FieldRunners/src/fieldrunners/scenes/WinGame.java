/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.scenes;

import java.awt.Graphics2D;
import java.util.ArrayList;
import tbgameframework.Window;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;
import tbgameframework.scene.SceneManager;
import tbgameframework.sound.SoundManager;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author MrKupi
 */
public class WinGame extends Scene{

    private static final int TTL = 200;
    private Vector2D windowSize = new Vector2D(Window.getInstance().getWidth(), Window.getInstance().getHeight());
    private String effectName;
    private Vector2D vectScale = new Vector2D(1.0, 1.0);
    private ImageSprite background;
    private ImageSprite text;
    private ArrayList<ImageSprite> effects = new ArrayList<>();
    private int timeCounter;
    
    public WinGame(SceneManager sceneManager, String sName, String effectName)
    {
        super(sceneManager, sName);
        this.effectName = effectName;
        this.background = (ImageSprite) SpriteManager.getInstance().getSprite(BACKGROUND);
        this.background.setIstransformable(false);
        this.text = (ImageSprite) SpriteManager.getInstance().getSprite(TEXT);
        this.text.setIstransformable(false);
    }
    
    @Override
    public void Update()
    {
        --this.timeCounter;
        if (this.timeCounter <= 0)
        {
            this.Hide();
            ((FieldRunnerSM) this.sceneManager).nextLevel();
        }
        
        super.Update();
    }
    
    @Override
    public void Draw(Graphics2D g2D)
    {
        this.background.Draw(g2D, new Vector2D(0f, 0f), 0f);
        this.text.Draw(g2D, new Vector2D(170, 324), 0f);
        for(ImageSprite spr : effects)
        {
            if(spr != null){
                spr.Draw(g2D);
            }
        }
        super.Draw(g2D);
    }
    
    @Override
    public void Start()
    {
        this.effects.clear();
        for(int i = 0; i < MAX_EFFECT; ++i){
            ImageSprite img = (ImageSprite)SpriteManager.getInstance().getSprite(effectName);
            ImageDrawObject drawObj = new ImageDrawObject(new Vector2D((Math.random()*windowSize.x + 10), (Math.random()*windowSize.y + 10)),
                                                        vectScale, 0.0f);
            img.setDrawObject(drawObj);
            effects.add(img);
        }
        
        this.timeCounter = TTL;
    }

    @Override
    public void Reset() {
        
    }
    
    @Override
    public void Hide()
    {
        super.Hide();
        
        SoundManager.stop();
    }
    
    @Override
    public void Show()
    {
        super.Show();
        
        SoundManager.play("victory", true);
    }
    
    static final private int MAX_EFFECT = 10;
    static final private String BACKGROUND = "win_lost_bg";
    static final private String TEXT = "text_victory";
}
