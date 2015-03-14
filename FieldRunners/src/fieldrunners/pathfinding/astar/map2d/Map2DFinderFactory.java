/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.astar.map2d;

import fieldrunners.pathfinding.core.FinderFactory;
import fieldrunners.pathfinding.core.MapState;
import fieldrunners.pathfinding.core.NeibourghsFactory;
import fieldrunners.pathfinding.core.Node;
import fieldrunners.pathfinding.core.PathBuilder;
import fieldrunners.pathfinding.core.PathFinder;
import java.awt.Point;

/**
 *
 * @author Uchiha Salm
 */
public class Map2DFinderFactory
    implements FinderFactory
{
    public Map2DFinderFactory(Point pstart, Point pgoal, Map2D map2D, PathFinder pFinder)
    {
        this.goal = new PointNode(pgoal, null, null);
        this.start = new PointNode(pstart, null, this.goal);
        this.map = map2D;
        this.pathBuilder = new Map2DPathBuilder(map.getNumberOfColumns(), map.getNumberOfRows());
    }
    
    @Override
    public Node getStart()
    {
        return start;
    }

    @Override
    public Node getGoal()
    {
        return goal;
    }

    @Override
    public PathBuilder getPathBuilder()
    {
        return pathBuilder;
    }

    @Override
    public NeibourghsFactory getNeibourghsFactory()
    {
        return map;
    }

    @Override
    public MapState createMapState(Object initValue)
    {
        return new MapState2D(map.getNumberOfColumns(), map.getNumberOfRows(), (Integer) initValue);
    }
    
    private PointNode start, goal;
    private Map2D map;
    private Map2DPathBuilder pathBuilder;
    private PathFinder pathFinder;

    @Override
    public PathFinder getPathFinder() {
        return pathFinder;
    }
}
