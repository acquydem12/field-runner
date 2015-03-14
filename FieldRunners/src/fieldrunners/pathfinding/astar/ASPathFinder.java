/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.astar;

import fieldrunners.pathfinding.core.FinderFactory;
import fieldrunners.pathfinding.core.MapState;
import fieldrunners.pathfinding.core.Node;
import fieldrunners.pathfinding.core.Path;
import fieldrunners.pathfinding.core.PathFinder;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 *
 * @author Uchiha Salm
 */
public class ASPathFinder
    implements PathFinder
{   
    private final int nInit = 500;
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<ASPathFinder>
    {

        @Override
        public ASPathFinder createProduct()
        {
            return new ASPathFinder();
        }

        private Factory()
        {

        }

        private static ASPathFinder.Factory inst = new ASPathFinder.Factory();

        public static ASPathFinder.Factory getInst()
        {
            return inst;
        }
    }
    
    @Override
    public Path find(FinderFactory finderFact)
    {
        ASNode start = (ASNode) finderFact.getStart();
        ASNode goal = (ASNode) finderFact.getGoal();
        
        MapState stateMap = finderFact.createMapState(ASStates.OPEN);
        
        PriorityQueue<ASNode> open = new PriorityQueue(nInit, new ASNodeComparator());
        open.add(start);
        stateMap.setState(start, ASStates.CLOSED);
        
        while (!open.isEmpty())
        {
            ASNode lowest = open.poll();
            if (lowest == goal)
            {
                break;
            }
            
            Vector<Node> neibourghs = finderFact.getNeibourghsFactory().getNeibourghsOf(lowest, finderFact);
            for (int i = 0; i < neibourghs.size(); i++)
            {
                Node aNeibourgh = neibourghs.elementAt(i);
                if ((int)stateMap.getState(aNeibourgh) == ASStates.OPEN)
                {
                    open.add((ASNode) aNeibourgh);
                    finderFact.getPathBuilder().makeEdge(aNeibourgh, lowest);
                    stateMap.setState(aNeibourgh, ASStates.CLOSED);
                }
            }
        }

        return finderFact.getPathBuilder().createPath(start, goal);
    }
    
    private class ASNodeComparator
        implements Comparator<ASNode>
    {
        @Override
        public int compare(ASNode t, ASNode t1) 
        {
            if (t.getCost() < t1.getCost())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
            
}