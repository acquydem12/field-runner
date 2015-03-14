/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import fieldrunners.gameobjects.FRDirectionTranslator;
import fieldrunners.gameobjects.ICompleteable;
import fieldrunners.gameobjects.frimp.state.FRState;
import fieldrunners.states.State;
import fieldrunners.states.statesprite.StateSpriteDrawObject;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collection;
import tbgameframework.*;
import tbgameframework.collision.ICollisionObject;
import tbgameframework.math.Vector2D;

/**
 * 
 * @author Uchiha Salm
 */
public class FRObject
    implements GameObject, StateSpriteDrawObject, ICollisionObject, ICompleteable
{
    private FRShine rSS;    //reflect sun shine
    private FRBraceYourSelf bys;    // bracing your self
    private State characteristic;
    private FRDirectionTranslator translator;
    private FRGotoWorld gtw;
    
    public FRObject(FRObjectFactory frObjFact)
    {
        this.rSS = frObjFact.createShine();
        this.bys = frObjFact.createBYS();
        this.characteristic = frObjFact.createState();
        this.translator = frObjFact.createDirectionTranslator();
        this.gtw = frObjFact.createGTW();
        
        this.gtw.setBYS(this.bys);
        this.rSS.setAbstractMirror(this);
    }

    public FRBraceYourSelf getBYS()
    {
        return this.bys;
    }

    public FRShine getShine()
    {
        return this.rSS;
    }
    
    @Override
    public void Draw(Graphics2D g2D)
    {
        rSS.Draw(g2D);
    }

    @Override
    public void Update()
    {
        bys.Update();
        // sync
        this.bys.updateState(characteristic);
    }

    @Override
    public float getAngle()
    {
        return 0.f;
    }

    @Override
    public Vector2D getPosition()
    {
        return this.bys.getPosition();
    }

    @Override
    public boolean isVisibled()
    {
        return this.rSS.isVisibled();
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        this.rSS.setVisibled(isVs);
    }

    @Override
    public float getDepth()
    {
        return this.rSS.getDepth();
    }

    @Override
    public void setDepth(float depth)
    {
        this.rSS.setDepth(depth);
    }

    @Override
    public boolean isEnabled()
    {
        return this.bys.isEnabled();
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.bys.setEnabled(isEn);
    }
    
    private static final Vector2D myScale = new Vector2D(1, 1);
    @Override
    public Vector2D getScale()
    {
        return myScale;
    }

    @Override
    public int getState(int stateID)
    {
        return this.characteristic.getState(stateID);
    }

    @Override
    public void setState(int stateID, int value)
    {
        this.characteristic.setState(stateID, value);
    }
    
    @Override
    public int hashCode()
    {
        int hash = this.characteristic.hashCode();
        return this.characteristic.hashCode();
    }

    /**
     *
     * @param obj
     * @return equal of its state
     */
    @Override
    public boolean equals(Object obj)
    {
        return characteristic.equals(obj);
    }

    @Override
    public Collection<Integer> getAllID()
    {
        return characteristic.getAllID();
    }

    @Override
    public void resume()
    {
        
    }

    @Override
    public void pause()
    {
        
    }

    @Override
    public boolean isCollidable(ICollisionObject object)
    {
        return this.gtw.isCollidable(object);
    }

    @Override
    public void collisionDo(ICollisionObject object, Vector2D hitDir)
    {
        this.gtw.collisionDo(object, hitDir);
    }

    @Override
    public Rectangle getBound()
    {
        return this.gtw.getBound();
    }

    @Override
    public boolean isCompleted()
    {
        return this.bys.isCompleted();
    }
}