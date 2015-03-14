/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.mapSystem;

import fieldrunners.controls.FRButtonBank;
import fieldrunners.scenes.Survival;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import tbgameframework.Board;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;
import tbgameframework.control.ButtonImp;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.control.buttons.ButtonBank;
import tbgameframework.math.Vector2D;
import tbgameframework.sound.SoundManager;

/**
 *
 * @author MrKupi
 */
public class FRSystem implements IUpdateable, IDrawable{
    
    
    private double distance = 0;
    // Inheritance
    private boolean isEnable = true;
    private boolean isVisible = true;
    private float depth = 1;
    public Survival currentScene;
    
    private boolean preventDeadlock = false;
    
    protected ButtonImp btmenu;
    
    protected ButtonImp btplay;
    protected ButtonImp btpause;
    
    protected ButtonImp btspeed;
    protected ButtonImp btspeedFast;
    private int normalSpeed = 45;
    private int fastSpeed = 90;
    private int currentSpeed = normalSpeed;
    
    protected ButtonImp btsoundOn;
    protected ButtonImp btsoundOff;
    protected double currentVolume = 0.0;
    
    protected Vector2D position;
    protected ArrayList<ButtonImp> buttons = new ArrayList<>();
    
    public FRSystem(Survival currentScene, Vector2D shopPosition)
    {
        this.position = shopPosition;
        this.currentScene = currentScene;
    }
    
    public void InitShop()
    {    
        btmenu = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(menu), position);
        this.Add(btmenu);
        
        btplay = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(playName), position);
        btpause = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(pauseName), position);
        this.Add(btplay);
        btpause.setDrawObject(btplay.getDrawObject());
        btpause.setEnabled(false);
        btpause.setVisibled(false);
        buttons.add(btpause);
                
        btspeed = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(speed), position);
        btspeedFast = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(speedFast), position);
        this.Add(btspeed);
        btspeedFast.setDrawObject(btspeed.getDrawObject());
        btspeedFast.setEnabled(false);
        btspeedFast.setVisibled(false);
        buttons.add(btspeedFast);
        
        btsoundOn = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(soundOn), position);
        btsoundOff = (ButtonImp)FRButtonBank.getInst().CreateProduct(this, ButtonBank.getInst().getButton(soundOff), position);
        this.Add(btsoundOn);
        btsoundOff.setDrawObject(btsoundOff.getDrawObject());
        btsoundOff.setEnabled(false);
        btsoundOff.setVisibled(false);
        buttons.add(btsoundOff);
        
        Board.getInstance().setFPS(this.normalSpeed);
    }
    
    @Override
    public void Update()
    {
        for(ButtonImp bt : buttons){
            bt.Update();
        }
        
        if (Board.getInstance().GameInput.IsKeyPressed(KeyEvent.VK_SPACE))
        {
            this.play_Click();
        }
        
        preventDeadlock = false;
    }
    
    @Override
    public void Draw(Graphics2D g2D) 
    {
        for(ButtonImp bt : buttons){
            bt.Draw(g2D);
        }
    }
    
    public void menu_Click()
    {
        // Back to menu
        this.currentScene.Hide();
        this.currentScene.getSM().removeScene(this.currentScene);
        
        this.currentScene.getSM().getScene("MainMenu").Show();
    }
    
    public void play_Click()
    {
        this.currentScene.setPaused(!this.currentScene.isPaused());
    }
    
    public void speed_Click()
    {
        if(!preventDeadlock)
        {
            if(currentSpeed == normalSpeed)
            {
                Swap(btspeedFast, btspeed);
            }
            else 
            {
                Swap(btspeed, btspeedFast);
            }
            currentSpeed = (currentSpeed == normalSpeed) ? fastSpeed : normalSpeed;
            Board.getInstance().setFPS(currentSpeed);
            preventDeadlock = !preventDeadlock;
        }
    }
    
    public void sound_Click()
    {
        if(!preventDeadlock){
            if(currentVolume == 0){
                Swap(btsoundOn, btsoundOff);
                currentVolume = 0.5;
                SoundManager.SetVolume(currentVolume);
            }
            else 
            {
                Swap(btsoundOff, btsoundOn);
                currentVolume = 0;
                SoundManager.SetVolume(currentVolume);
            }
            preventDeadlock = !preventDeadlock;
        }
    }
    
    public void Over()
    {
    }
    
    public void Release()
    {
    }
        
    public void Add(ButtonImp button)
    {
        Vector2D currentPos = new Vector2D(this.position);
        ImageDrawObject drawObj = new ImageDrawObject(currentPos, 
                button.getDrawObject().getScale(), button.getDrawObject().getAngle());
        button.setDrawObject(drawObj);
        this.position.x += button.Width() + distance;
        
        buttons.add(button);
    }
    
    public void Swap(ButtonImp b1, ButtonImp b2){
        b1.setEnabled(true);
        b1.setVisibled(true);

        b2.setEnabled(false);
        b2.setVisibled(false);
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
    public void setDepth(float depth) {
        this.depth = depth;
    }
    
    final private String playName = "SYS_PLAY";
    final private String pauseName = "SYS_PAUSE";
    final private String speed = "SYS_SPEED";
    final private String speedFast = "SYS_SPEED_FAST";
    final private String soundOn = "SYS_SOUND_ON";
    final private String soundOff = "SYS_SOUND_OFF";
    final private String menu = "SYS_MENU";
}