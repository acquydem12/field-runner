/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.astar.map2d;

import fieldrunners.pathfinding.core.LinkedPath;
import fieldrunners.pathfinding.core.MapState;
import fieldrunners.pathfinding.core.Node;
import fieldrunners.pathfinding.core.Path;
import fieldrunners.pathfinding.core.PathBuilder;
import java.util.LinkedList;

/**
 *
 * @author Uchiha Salm
 */
public class Map2DPathBuilder
    implements PathBuilder
{
    public Map2DPathBuilder(int nColumns, int nRows)
    {
        mapState = new MapState2D(nColumns, nRows, null);
    }
    
    @Override
    public void makeEdge(Node n1, Node n2)
    {
        mapState.setState(n1, n2);
    }

    @Override
    public Path createPath(Node start, Node goal)
    {
        LinkedList<Node> path = new LinkedList<>();
        PointNode current = (PointNode) goal;
        PointNode dest = (PointNode) start;
        while (current != dest)
        {
            if (current == null)
            {
                break;
            }
            
            path.addFirst(current);
            current = (PointNode) mapState.getState(current);
        }
        
        return new LinkedPath(path);
        
    }
    
    private MapState mapState;
}
