/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.scenes;

import fieldrunners.controls.FRButtonBank;
import fieldrunners.controls.RectangleButton;
import java.awt.Graphics2D;
import java.util.ArrayList;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.control.buttons.ButtonBank;
import tbgameframework.control.progressbar.VerticleProgressbar;
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
public class FROptionScene extends Scene
{

    private float currentVol = 0.5f;
    private ImageSprite background;
    private ImageSprite optionMenu;
    
    private VerticleProgressbar bar;
    private RectangleButton increase;
    private RectangleButton decrease;
    
    ArrayList<RectangleButton> buttons = new ArrayList<>();
    
    private ImageDrawObject drawObj;
    
    private RectangleButton ok;
    
    public FROptionScene(SceneManager sceneManager, String sName)
    {
        super(sceneManager, sName);
    }
    
    private void Init(){
        drawObj = new ImageDrawObject(new Vector2D(), new Vector2D(1.0, 1.0), 0.0f);
        
        this.background = (ImageSprite)SpriteManager.getInstance().getSprite(BACKGROUND);
        this.background.setDrawObject(drawObj);
        this.background.setIstransformable(false);
        
        this.optionMenu = (ImageSprite)SpriteManager.getInstance().getSprite(OPTIONMENU);
        this.optionMenu.setDrawObject(drawObj);
        this.optionMenu.setIstransformable(false);
        
        this.bar = new VerticleProgressbar((ImageSprite)SpriteManager.getInstance().getSprite(SOUNDBAR), barPosition);
        this.bar.setDefault(currentVol);
        SoundManager.SetVolume(currentVol);
        
        this.increase = (RectangleButton)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(INCREASE), increasePosition);
        this.decrease = (RectangleButton)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(DECREASE), decreasePosition);
        this.ok = (RectangleButton)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(OK), okPosition);
        
        buttons.add(increase);
        buttons.add(decrease);
        buttons.add(ok);
    }
    
    @Override
    public void Start() {
        this.Init();
    }

    @Override
    public void Reset() {
        
    }
 
    @Override
    public void Update(){
        for(RectangleButton bt : buttons){
            bt.Update();
        }
        super.Update();
    }
    
    @Override
    public void Draw(Graphics2D g2D){
        //background.Draw(g2D);
        optionMenu.Draw(g2D);
        
        this.bar.Draw(g2D);
        
        for(RectangleButton bt : buttons){
            bt.Draw(g2D);
        }
        super.Draw(g2D);
    }
    
    public void increase_Click(){
        bar.increase(0.1f);
        SoundManager.SetVolume(currentVol += 0.1f);
    }
    
    public void decrease_Click(){
        bar.decrease(0.1f);
        SoundManager.SetVolume(currentVol -= 0.1f);
    }
    
    public void ok_Click()
    {
        this.Hide();
        this.sceneManager.getScene("MainMenu").Show();
    }
    
    public void Over(){
    }
    
    public void Release(){
    }
    
    
    final private Vector2D increasePosition = new Vector2D(750.0, 355.0);
    final private Vector2D decreasePosition = new Vector2D(231.0, 355.0);
    final private Vector2D okPosition = new Vector2D(398.0, 175.0);
    
    final private Vector2D barPosition = new Vector2D(295.0, 364.0);
    final private String BACKGROUND = "GrassLand";
    final private String OPTIONMENU = "option";
    final private String SOUNDBAR = "loading_bar";
    final private String INCREASE = "increase";
    final private String DECREASE = "decrease";
    final private String OK = "ok";
}
