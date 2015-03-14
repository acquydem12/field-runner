/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.core;

/**
 *
 * @author Uchiha Salm
 */
public interface MapState
{
    Object getState(Node node);
    void setState(Node node, Object state);
}
