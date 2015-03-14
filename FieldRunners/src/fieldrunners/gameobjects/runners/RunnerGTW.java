/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners;

import fieldrunners.gameobjects.frimp.FRBraceYourSelf;
import fieldrunners.gameobjects.frimp.FRGotoWorld;
import java.awt.Rectangle;
import tbgameframework.collision.ICollisionObject;
import tbgameframework.math.Vector2D;

/**
 *
 * @author Uchiha Salm
 */
public class RunnerGTW
    implements FRGotoWorld
{
    private RunnerBYS bys = null;
    
    @Override
    public void setBYS(FRBraceYourSelf bys)
    {
        if (bys instanceof RunnerBYS)
        {
            this.bys = (RunnerBYS) bys;
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
        return false;
    }

    @Override
    public void collisionDo(ICollisionObject object, Vector2D hitDir)
    {
    }

    @Override
    public Rectangle getBound()
    {
        return bys.getBound();
    }
    
}
