/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects;

import fieldrunners.states.State;
import java.util.Collection;

/**
 *
 * @author Uchiha Salm
 */
public interface ObjectStateMap
{
    public Collection<State> getAllStates();
    
    public void addState(State state, Object obj);

    public void setState(State state, Object obj);

    public void removeState(State state);

    public Object getObject(State state);
}
