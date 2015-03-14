/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners;

import fieldrunners.gameobjects.GPSObject;
import fieldrunners.gameobjects.frimp.FRExplorer;
import fieldrunners.map.frgrid.Cell;
import fieldrunners.map.frgrid.Grid2D;
import fieldrunners.pathfinding.astar.map2d.Map2DFinderFactory;
import fieldrunners.pathfinding.astar.map2d.PointNode;
import fieldrunners.pathfinding.core.*;
import java.awt.Point;
import java.util.Iterator;
import tbgameframework.math.Vector2D;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class RunnerExplorer
    implements FRExplorer
{
    private Grid2D grid;
    private PathFinder pathFinder;
    private Point goal;
    private Iterator<Node> cMileStone;
    private Path path;
    private Cell cCell;
    private boolean isRunning = true;
    
    GPSObject gps;
    
    public RunnerExplorer(Grid2D grid, Point goal, Factory<PathFinder> fPf)
    {
        this.grid = grid;
        this.pathFinder = fPf.createProduct();
        this.goal = goal;
    }
    
    @Override
    public void findNewPath()
    {
        this.cMileStone = null;
        this.path = null;
        
        // check now :)
        Vector2D pos = gps.getPosition();
        Point cellPos = this.grid.getCellPositionOf(pos.asPoint());
        
        try
        {
            FinderFactory ff = new Map2DFinderFactory(cellPos, this.goal, this.grid, this.pathFinder);
            this.path = this.pathFinder.find(ff);
            if (this.path != null && !this.path.isEmpty())
            {
                this.cMileStone = this.path.iterator();
//                this.cMileStone.next();
                this.findNext();
            }
        }
        catch (Exception e)
        {
            this.isRunning = false;
            System.err.println("Find path incompleted!!!");
        }
    }
    
    private void findNext()
    {
        this.cCell = this.findNextCell();
        if (cCell != null)
        {
            Vector2D nextPos = this.cCell.getCorner();
            this.gps.pathChanged(this);
        }
        else
        {
            this.gps.pathCompleted(this);
        }
    }
    private Cell findNextCell()
    {
        if (cMileStone.hasNext())
        {
            PointNode node = (PointNode) cMileStone.next();
            
            return grid.getCellAt(node.getCoordinate());
        }
        
        return null;
    }

    @Override
    public void setGPS(GPSObject gpsO)
    {
        this.gps = gpsO;
        
        this.findNewPath();
    }

    @Override
    public GPSObject getGPS()
    {
        return this.gps;
    }

    @Override
    public Vector2D getNearestMileStone()
    {
        return this.cCell.getCorner();
    }

    @Override
    public Grid2D getGrid2D()
    {
        return this.grid;
    }

    @Override
    public Point getGoal()
    {
        return this.goal;
    }

    @Override
    public boolean isEnabled()
    {
        return this.isRunning;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.isRunning = isEn;
    }

    @Override
    public void Update()
    {
        if (this.isRunning)
        {
            if (this.isCompletedCell())
            {
                this.findNext();
            }
        }
    }
    
    
    private boolean isCompletedPath()
    {
        return (this.cCell == null);
    } 
    
    private boolean isCompletedCell()
    {
        try
        {
            Vector2D I = this.cCell.getCorner();
            Vector2D OI = new Vector2D(gps.getPosition().x - I.x, gps.getPosition().y - I.y);

            return (OI.scalarProduct(gps.getDirection()) >= 0);
        }
        catch (NullPointerException ex)
        {
        }
        
        return true;    //or false ???
    }
}
