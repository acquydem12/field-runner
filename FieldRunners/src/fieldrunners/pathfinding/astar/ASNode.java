/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.astar;

import fieldrunners.pathfinding.core.Node;

/**
 *
 * @author Uchiha Salm
 */
public abstract class ASNode
    implements Node
{
    public ASNode()
    {
    }
    
    @Override
    public int getCost() {
        return pathCost() + heuristicCost();
    }
    
    public abstract int pathCost();
    public abstract int heuristicCost();
}
