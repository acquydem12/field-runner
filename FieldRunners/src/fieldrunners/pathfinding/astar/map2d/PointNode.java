/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.astar.map2d;

import fieldrunners.pathfinding.astar.ASNode;
import java.awt.Point;

/**
 *
 * @author Uchiha Salm
 */
public class PointNode
    extends ASNode
{
    public PointNode(Point pos)
    {
        this.coord = pos;
        this.h = 0;
        this.g = 0;
    }
    
    public PointNode(Point pos, PointNode prev, PointNode goal)
    {
        this.coord = pos;
        
        this.calcCost(prev, goal);
    }
    
    @Override
    public int pathCost() 
    {
        return g;
    }

    @Override
    public int heuristicCost()
    {
        return h;
    }
    
    private void calcCost(PointNode prev, PointNode goal)
    {
        if (this.isNeibourgh(prev))
        {
            g = prev.pathCost() + 1;
        }
        else
        {
            g = 0;
        }
        
        if (goal != null)
        {
            h = 3 * Math.abs(this.coord.y - goal.coord.y);
        }
        else
        {
            h = 0;
        }
    }
    
    public Point getCoordinate()
    {
        return coord;
    }
    
    private int g, h;
    private Point coord;

    private boolean isNeibourgh(PointNode prev)
    {
        if (prev == null)
        {
            return false;
        }
        
        int dx = Math.abs(this.coord.x - prev.coord.x);
        int dy = Math.abs(this.coord.y - prev.coord.y);
        
        return (((dx == 0) && (dy == 1)) || ((dx == 1) && (dy == 0)));
    }
}
