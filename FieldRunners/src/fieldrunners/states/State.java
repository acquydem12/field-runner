/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states;

import java.util.Collection;

/**
 *
 * @author Uchiha Salm
 */
public interface State
{
    int getState(int stateID);
    void setState(int stateID, int value);
    
    Collection<Integer> getAllID();
}
