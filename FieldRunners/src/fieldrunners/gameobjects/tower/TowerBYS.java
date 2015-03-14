/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.tower;

import fieldrunners.gameobjects.frimp.FRBraceYourSelf;
import fieldrunners.gameobjects.frimp.FRDirectional;
import fieldrunners.gameobjects.frimp.FRDirectionalImp;
import fieldrunners.states.State;
import java.awt.Point;
import tbgameframework.math.Vector2D;

/**
 *
 * @author Uchiha Salm
 */
public class TowerBYS
    implements FRBraceYourSelf
{
    private FRDirectional direction = new FRDirectionalImp();
    private Vector2D position = new Vector2D();
    private float angularSpeed = 0f;
    private boolean isEnable = true;
    private float searchRadius = 0f;
    private Vector2D attackPos = new Vector2D();
    
    private int level = 1;
    
    private int shootCounter = 0;
    private boolean isShooted = false;
    private int delay;
    
    private TowerInShop tis;
    
    public TowerBYS(float angSp, float sRad, Point attPos, Vector2D pos, int delay, int price)
    {
        this.position.setValues(pos);
        this.angularSpeed = angSp;
        this.searchRadius = sRad;
        this.delay = delay;
        this.attackPos.setValues(attPos.x, attPos.y);
        
        this.tis = new TowerInShop(price);
    }
    
    public boolean canShoot()
    {
        return (!isShooted);
    }
    
    public void setShooted(boolean val)
    {
        this.isShooted = val;
        
        if (this.isShooted)
        {
            this.shootCounter = 0;
        }
    }
    
    public float getSearchRadius()
    {
        return this.searchRadius;
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
        if (this.isShooted)
        {
            ++shootCounter;
            if (shootCounter > delay)
            {
                this.setShooted(false);
            }
        }
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

    @Override
    public boolean isCompleted()
    {
        return !this.isEnable;
    }

    @Override
    public void updateState(State st)
    {
        st.setState(TowerState.STATEID_ATTACK, (this.isShooted)?0:1);
        st.setState(TowerState.STATEID_LEVEL, this.level);
    }
    
    public int getLevel()
    {
        return this.level;
    }
    
    public static final int LEVEL_MAX = 3;
    public void upgrade()
    {
        if (this.level < LEVEL_MAX)
        {
            ++this.level;
        }
    }
    
    public Vector2D getAttackPosition()
    {
        return this.position.add(this.attackPos);
    }
    
    public TowerInShop getTowerInShop()
    {
        return this.tis;
    }
}
