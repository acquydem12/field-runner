/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.tower;

import fieldrunners.states.State;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Uchiha Salm
 */
public class TowerState
    implements State
{
    public static final int STATEID_ATTACK = 0;
    public static final int STATEID_LEVEL = 1;
    
    private int attackState = FRAttackState.IDLE;
    private int level = 1;
    
    public TowerState()
    {
        super();
        this.attackState = FRAttackState.IDLE;
    }
    
    @Override
    public int getState(int stateID)
    {
        switch (stateID)
        {
            case STATEID_ATTACK:
                return this.attackState;
            case STATEID_LEVEL:
                return this.level;
            default:
                break;
        }
        
        // throw invalid state ID exception
        return -1;
    }

    @Override
    public void setState(int stateID, int value)
    {
        switch (stateID)
        {
            case STATEID_ATTACK:
                this.attackState = value;
                break;
            case STATEID_LEVEL:
                this.level = value;
            default:
                break;
        }
        
        // throw invalid state ID exception
    }
    
    @Override
    public boolean equals(Object obj)
    {
        try
        {
            State stOther = (State) obj;

            return (((this.attackState == stOther.getState(STATEID_ATTACK)) && (this.level == stOther.getState(STATEID_LEVEL))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return false;
    }
    
    

    @Override
    public int hashCode()
    {
        int hash = 23;
        hash = 29 * hash + this.attackState;
        hash = 29 * hash + this.level;
        return hash;
    }

    @Override
    public Collection<Integer> getAllID()
    {
        Collection<Integer> IDs = new ArrayList<>();
        
        IDs.add(STATEID_ATTACK);
        IDs.add(STATEID_LEVEL);
        
        return IDs;
    }
}
