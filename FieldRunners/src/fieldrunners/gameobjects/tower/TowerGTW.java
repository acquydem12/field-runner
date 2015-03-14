/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.tower;

import fieldrunners.gameobjects.frimp.FRBraceYourSelf;
import fieldrunners.gameobjects.frimp.FRGotoWorld;
import fieldrunners.gameobjects.frimp.FROFBank;
import fieldrunners.gameobjects.frimp.FRObject;
import fieldrunners.gameobjects.missile.MissileDynamicData;
import fieldrunners.gameobjects.runners.RunnerBYS;
import java.awt.Rectangle;
import tbgameframework.collision.ICollisionObject;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;
import tbgameframework.sound.SoundManager;

/**
 *
 * @author Uchiha Salm
 */
public class TowerGTW
    implements FRGotoWorld
{
    TowerBYS bys = null;
    private Rectangle bound = new Rectangle();
    private Scene scene;
    private String missiles[];
    private String attSound;
    
    public TowerGTW(Scene s, String msName[], String aS)
    {
        this.scene = s;
        this.missiles = msName;
        this.attSound = aS;
    }
    
    @Override
    public void setBYS(FRBraceYourSelf bys)
    {
        if (bys instanceof TowerBYS)
        {
            this.bys = (TowerBYS) bys;
            
            //sync:
            float halfRad = this.bys.getSearchRadius() / 2;
            this.bound.x = (int) (this.bys.getPosition().x - halfRad);
            this.bound.y = (int) (this.bys.getPosition().y - halfRad);
            this.bound.width = (int) this.bys.getSearchRadius();
            this.bound.height = (int) this.bys.getSearchRadius();
        }
    }

    @Override
    public FRBraceYourSelf getBYS()
    {
        return this.bys;
    }

    @Override
    public boolean isCollidable(ICollisionObject object)
    {
        // check with runner
        if (object instanceof FRObject)
        {
            FRObject frObj = (FRObject) object;
            
            if (frObj.getBYS() instanceof RunnerBYS)
            {
                RunnerBYS Rbys = (RunnerBYS) frObj.getBYS();
                
                return (this.bys.getPosition().distance(Rbys.getPosition()) <= this.bys.getSearchRadius());
            }
        }
        
        return false;
    }

    @Override
    public void collisionDo(ICollisionObject object, Vector2D hitDir)
    {
        // shoot bullet
        if (!this.bys.canShoot())
        {
            return;
        }
        
        final FRObject frObj = (FRObject) object;
        FROFBank.getInst().setMissileDynamicData(new MissileDynamicData() 
        {
            @Override
            public TowerBYS getAttacker()
            {
                return bys;
            }

            @Override
            public RunnerBYS getTarget()
            {
                return (RunnerBYS) frObj.getBYS();
            }
            
            @Override
            public Scene getScene()
            {
                return scene;
            }
        });
        this.bys.setShooted(true);
        this.scene.addComponent(new FRObject(FROFBank.getInst().getFactory(this.missiles[this.bys.getLevel() - 1])));
        SoundManager.play(attSound, false);
    }

    @Override
    public Rectangle getBound()
    {
        return bound;
    }
    
}
