/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.astar.map2d;

import fieldrunners.pathfinding.astar.ASStates;
import fieldrunners.pathfinding.core.FinderFactory;
import fieldrunners.pathfinding.core.MapState;
import fieldrunners.pathfinding.core.NeibourghsFactory;
import fieldrunners.pathfinding.core.Node;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

/**
 *
 * @author Uchiha Salm
 */
public class Map2D
    implements MapState, NeibourghsFactory
{
    public static final int MOVEABLE = 0;
    public static final int LOCKED = 1;
    
    public Map2D(int nColumns, int nRows)
    {
        map = new int[nColumns][nRows];
        
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; ++j)
            {
                map[i][j] = ASStates.MOVEABLE;
            }
        }
    }
    
    public Map2D(Map2D other)
    {
        map = new int[other.getNumberOfColumns()][other.getNumberOfRows()];
        
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[i].length; ++j)
            {
                map[i][j] = other.map[i][j];
            }
        }
    }
    
    int getNumberOfColumns()
    {
        return map.length;
    }
    
    int getNumberOfRows()
    {
        return map[0].length;
    }

    @Override
    public Object getState(Node node)
    {
        PointNode point = (PointNode) node;
        return map[point.getCoordinate().x][point.getCoordinate().y];
    }

    @Override
    public void setState(Node node, Object state)
    {
        PointNode point = (PointNode) node;
        map[point.getCoordinate().x][point.getCoordinate().y] = (Integer) state;
    }
    
    public void setState(Point pos, int state)
    {
        map[pos.x][pos.y] = state;
    }
    
    public int getState(Point pos)
    {
        return map[pos.x][pos.y];
    }
    
    private int[][] map;

    @Override
    public Vector<Node> getNeibourghsOf(Node node, FinderFactory fFactory)
    {
        Vector<Node> neibourghs = new Vector<>(4);
        PointNode point = (PointNode) node;
        if (point != null && (int)this.getState(point) != Map2D.LOCKED)
        {
            if (point.getCoordinate().x > 0 && (map[point.getCoordinate().x - 1][point.getCoordinate().y] == ASStates.MOVEABLE))
            {
                    neibourghs.add(new PointNode(new Point(point.getCoordinate().x - 1, point.getCoordinate().y), point, (PointNode) fFactory.getGoal()));
            }

            if (point.getCoordinate().x < map.length - 1 && (map[point.getCoordinate().x + 1][point.getCoordinate().y] == ASStates.MOVEABLE))
            {
                    neibourghs.add(new PointNode(new Point(point.getCoordinate().x + 1, point.getCoordinate().y), point, (PointNode) fFactory.getGoal()));
            }

            if (point.getCoordinate().y > 0 && (map[point.getCoordinate().x][point.getCoordinate().y - 1] == ASStates.MOVEABLE))
            {
                    neibourghs.add(new PointNode(new Point(point.getCoordinate().x, point.getCoordinate().y - 1), point, (PointNode) fFactory.getGoal()));
            }

            if (point.getCoordinate().y < map[point.getCoordinate().x].length - 1 && (map[point.getCoordinate().x][point.getCoordinate().y + 1] == ASStates.MOVEABLE))
            {
                    neibourghs.add(new PointNode(new Point(point.getCoordinate().x, point.getCoordinate().y + 1), point, (PointNode) fFactory.getGoal()));
            }
        }
        
        return neibourghs;
    }
}
