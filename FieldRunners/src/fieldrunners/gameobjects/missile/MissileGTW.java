/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import fieldrunners.gameobjects.frimp.FRBraceYourSelf;
import fieldrunners.gameobjects.frimp.FRGotoWorld;
import fieldrunners.gameobjects.frimp.FROFBank;
import fieldrunners.gameobjects.frimp.FRObject;
import fieldrunners.gameobjects.runners.RunnerBYS;
import fieldrunners.gameobjects.runners.effect.FREffectFactory;
import java.awt.Rectangle;
import java.util.Collection;
import tbgameframework.collision.ICollisionObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sound.SoundManager;

/**
 *
 * @author Uchiha Salm
 */
class MissileGTW
    implements FRGotoWorld
{
    private MissileBYS bys;
    private Collection<FREffectFactory> effects;
    private ExplosionFactory explosion;
    private String collideSound;
    
    /**
     *
     * @param effs
     * @param exp
     */
    public MissileGTW(Collection<FREffectFactory> effs, ExplosionFactory exp, String collS)
    {
        this.effects = effs;
        this.explosion = exp;
        this.collideSound = collS;
    }
    
    @Override
    public void setBYS(FRBraceYourSelf bys)
    {
        if (bys instanceof MissileBYS)
        {
            this.bys = (MissileBYS) bys;
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
            
            return (frObj.getBYS() instanceof RunnerBYS);
        }
        
        return false;
    }

    private static final int ExpTTL = 200;
    @Override
    public void collisionDo(ICollisionObject object, Vector2D hitDir)
    {
        if (object instanceof FRObject)
        {
            FRObject frObj = (FRObject) object;
            
            if (this.bys.canCollide() && frObj.getBYS() instanceof RunnerBYS)
            {
                RunnerBYS rBYS = (RunnerBYS) frObj.getBYS();
                
                this.bys.collide();
                for (FREffectFactory effF : this.effects)
                {
                    rBYS.getEffectManager().addEffects(effF.createProduct());
                }
                
                if (this.explosion != null)
                {
                    FROFBank.getInst().getMissileDynamicData().getScene().addComponent(this.explosion.createProduct(this.bys.getPosition()));
                }
                
                SoundManager.play(collideSound, false);
            }
        }
    }

    @Override
    public Rectangle getBound()
    {
        return bys.getBound();
    }
    
}
