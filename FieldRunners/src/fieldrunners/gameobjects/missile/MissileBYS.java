/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import fieldrunners.gameobjects.frimp.FRBraceYourSelf;
import fieldrunners.gameobjects.frimp.FRDirectional;
import fieldrunners.gameobjects.frimp.FRDirectionalImp;
import fieldrunners.states.State;
import java.awt.Point;
import java.awt.Rectangle;
import tbgameframework.math.Vector2D;

/**
 *
 * @author Uchiha Salm
 */
public class MissileBYS
    implements FRBraceYourSelf
{
    private boolean isEnable = true;
    private FRDirectional direction = new FRDirectionalImp();
    private Vector2D position = new Vector2D();
    
    private int timeCounter = 0;
    private int timeToLive = 0;
    // effect ???
    private int damage = 0;
    // chain
    
    private Rectangle bound;
    
    private MissileMovement posInterpolator;
    
    private boolean isCollideKill = true;
    private boolean canSuffuse = false;
    private boolean isCollided = false;
    private boolean isKilled = false;
    
    public MissileBYS(Vector2D pos, MissileMovement mov, Point size, boolean isCK, boolean canSuff, int ttl)
    {
        this.position.setValues(pos);
        this.posInterpolator = mov;
        
        this.isCollideKill = isCK;
        this.canSuffuse = canSuff;
        this.timeToLive = ttl;
        
        this.bound = new Rectangle((int) pos.x, (int) pos.y, size.x, size.y);
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
        this.posInterpolator.interpolate(position);
        
        ++this.timeCounter;
        
        if (this.timeCounter >= this.timeToLive)
        {
            this.isEnable = false;
        }
        
        if (this.isCollideKill && this.isCollided)
        {
            this.isKilled = true;
        }
        
        this.isCollided = false;
    }
    
    public void collide()
    {
        this.isCollided = true;
    }
    
    public boolean canCollide()
    {
        return !this.isKilled && (this.canSuffuse || !this.isCollided);
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
        this.bound.x = (int) this.position.x;
        this.bound.y = (int) this.position.y;
        
        return this.bound;
    }

    @Override
    public boolean isCompleted()
    {
        return !this.isEnable || this.isKilled;
    }

    @Override
    public void updateState(State st)
    {
    }
}
