/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners;

import fieldrunners.gameLogic.GameScore;
import fieldrunners.gameobject.MoneyShow;
import fieldrunners.gameobjects.FRDirectionTranslator;
import fieldrunners.gameobjects.GPSObject;
import fieldrunners.gameobjects.frimp.*;
import fieldrunners.gameobjects.frimp.directiontranslator.FRAllDirectionTranslator;
import fieldrunners.gameobjects.frimp.state.FRLive;
import fieldrunners.gameobjects.frimp.state.FRState;
import fieldrunners.gameobjects.runners.effect.EffectManager;
import fieldrunners.states.State;
import java.awt.Point;
import java.awt.Rectangle;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;
import tbgameframework.sound.SoundManager;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.fontsprite.FontSprite;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class RunnerBYS
    implements FRBraceYourSelf, GPSObject
{
    private boolean isEnable = true;
    private FRDirectional direction = new FRDirectionalImp();
    private Vector2D position = new Vector2D();
    private FRExplorer explorer;
    private Point size;
    private RunnerEffectableData effData;
    private EffectManager effManager;
    private GameScore gs;
    private int price;
    private Scene scene;
    private String deathSound;
    
    FRDirectionTranslator dirTrans;

    @Override
    public void updateState(State st)
    {
        st.setState(FRState.STATEID_DIRECTION, this.dirTrans.translate(this.direction.getAngle()));
        st.setState(FRState.STATEID_LIVE, FRLive.ALIVE);
    }

    private void addMoney()
    {
        gs.AddMoney(price);
        scene.addComponent(new MoneyShow("+" + Integer.toString(price), (FontSprite)SpriteManager.getInstance().getSprite("font_gold"), this.getPosition()));
    }
    
    //<editor-fold defaultstate="collapsed" desc="EffectableData">
    private static class EffectableDataImp
        implements RunnerEffectableData
    {
        private int hp;
        private float speed;
        
        public EffectableDataImp(int hp, float sp)
        {
            this.hp = hp;
            this.speed = sp;
        }
        
        @Override
        public int getHP()
        {
            return this.hp;
        }

        @Override
        public void setHP(int hp)
        {
            if (hp < 0)
            {
                hp = 0;
            }
            
            this.hp = hp;
        }

        @Override
        public void changeHP(int dhp)
        {
            this.hp += dhp;
            
            if (this.hp < 0)
            {
                this.hp = 0;
            }
        }

        @Override
        public float getSpeed()
        {
            return this.speed;
        }

        @Override
        public void setSpeed(float sp)
        {
            this.speed = sp;
            
            if (this.speed < 0.001)
            {
                this.speed = 0;
            }
        }

        @Override
        public void changeSpeed(float dsp)
        {
            this.speed += dsp;
            
            if (this.speed < 0.001)
            {
                this.speed = 0;
            }
        }

        @Override
        public void changeSpeedBy(float dpsp)
        {
            this.speed *= (1f + dpsp);
            
            if (this.speed < 0.001)
            {
                this.speed = 0;
            }
        }

        @Override
        public RunnerEffectableData clone()
        {
            return new EffectableDataImp(hp, speed);
        }
    }
    //</editor-fold>
    
    public RunnerBYS(Vector2D startPos, float sp, Point size, int hp, int nDir, Factory<FRExplorer> explrF, GameScore gs, int price, Scene s, String dS)
    {
        this.explorer = explrF.createProduct();
        this.position.setValues(startPos);
        this.size = size;
        this.effData = new EffectableDataImp(hp, sp);
        this.effManager = new EffectManager();
        
        this.explorer.setGPS(this);
        
        this.dirTrans = new FRAllDirectionTranslator(nDir);
        this.gs = gs;
        
        this.price = price;
        this.scene = s;
        
        this.deathSound = dS;
    }
    
    public RunnerEffectableData getEffectableData()
    {
        return this.effData;
    }
    
    public EffectManager getEffectManager()
    {
        return this.effManager;
    }
    
    @Override
    public boolean isEnabled()
    {
        return isEnable;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.isEnable = isEn;
    }

    @Override
    public void Update()
    {
        this.explorer.Update();
        
        RunnerEffectableData affectedData = this.effManager.impact(effData);
        this.effManager.Update();
        
        if (this.effData.getHP() <= 0)
        {
            this.addMoney();
            this.setEnabled(false);
            this.gs.AddScore((int)(Math.random() * 300));
            SoundManager.play(deathSound, false);
        }
        
        // after change
        this.position.selfAdd(this.direction.getDirection().mul(affectedData.getSpeed()));
    }

    @Override
    public Vector2D getDirection()
    {
        return direction.getDirection();
    }

    @Override
    public float getAngle()
    {
        return direction.getAngle();
    }

    @Override
    public void setDirection(Vector2D dir)
    {
        direction.setDirection(dir);
    }

    @Override
    public void setAngle(float arg)
    {
        direction.setAngle(arg);
    }

    @Override
    public Vector2D getPosition()
    {
        return this.position;
    }
    
    public Rectangle getBound()
    {
        return new Rectangle((int) this.position.x, (int) this.position.y, this.size.x, this.size.y);
    }

    public void mapChanged()
    {
        this.explorer.findNewPath();
    }

    @Override
    public void pathChanged(FRExplorer explr)
    {
        this.setDirection(explr.getNearestMileStone().sub(position));
    }

    @Override
    public void pathCompleted(FRExplorer explr)
    {
        this.setEnabled(false);
        this.effData.setSpeed(0f);
        this.explorer.setEnabled(false);
        this.gs.MinusHeart(1);
        
        //hc hearting sound
    }

    @Override
    public boolean isCompleted()
    {
        return !this.isEnable;
    }
}