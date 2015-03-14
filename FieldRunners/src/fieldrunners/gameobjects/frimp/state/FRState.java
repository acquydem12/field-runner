/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp.state;

import fieldrunners.states.State;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Uchiha Salm
 */
public class FRState
    implements State
{
    private int direction;
    private int live;
    
    public static final int STATEID_DIRECTION = 0;
    public static final int STATEID_LIVE = 1;
    
    public static FRState createState(int dirState, int liveState)
    {
        return new FRState(dirState, liveState);
    }
    
    public FRState()
    {
        this.direction = FRDirection.WEST;
        this.live = FRLive.ALIVE;
    }
    
    public FRState(int dirState, int liveState)
    {
        this.direction = dirState;
        this.live = liveState;
    }
    
    @Override
    public int getState(int stateID)
    {
        switch (stateID)
        {
            case STATEID_DIRECTION:
                return this.direction;
            case STATEID_LIVE:
                return this.live;
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
            case STATEID_DIRECTION:
                this.direction = value;
                break;
            case STATEID_LIVE:
                this.live = value;
                break;
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

            return (this.direction == stOther.getState(STATEID_DIRECTION) && this.live == stOther.getState(STATEID_LIVE));
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
        int hash = 7;
        hash = 23 * hash + this.direction;
        hash = 23 * hash + this.live;
        return hash;
    }

    @Override
    public Collection<Integer> getAllID()
    {
        Collection<Integer> IDs = new ArrayList<>();
        
        IDs.add(STATEID_DIRECTION);
        IDs.add(STATEID_LIVE);
        
        return IDs;
    }
}