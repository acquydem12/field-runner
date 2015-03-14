/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import fieldrunners.gameobjects.GPSObject;
import fieldrunners.map.frgrid.Cell;
import fieldrunners.map.frgrid.Grid2D;
import fieldrunners.pathfinding.astar.map2d.PointNode;
import fieldrunners.pathfinding.core.Node;
import fieldrunners.pathfinding.core.Path;
import java.awt.Point;
import java.util.Iterator;
import tbgameframework.math.Vector2D;

/**
 *
 * @author Uchiha Salm
 */
public abstract class FRExplorerImp
{
    private Grid2D grid2D;
    private Point goal = new Point();
    private Path path;
    private Iterator<Node> mileStone;
    private Cell currentCell;
    private boolean isRunning = true;
    private GPSObject gps;
    
    public FRExplorerImp(Grid2D grid, GPSObject gpsObj, Point goal)
    {
        this.grid2D = grid;
        this.gps = gpsObj;
        this.goal.setLocation(goal);
    }
    
    public void findNewPath()
    {
        this.mileStone = null;
        this.path = null;
        
        this.path = grid2D.find(this.grid2D.getCellPositionOf(gps.getPosition().asPoint()), goal);
        if (this.path != null && !this.path.isEmpty())
        {
            this.mileStone = this.path.iterator();
            this.findNext();
        }
    }
    
    private void findNext()
    {
        this.currentCell = this.findNextCell();
        if (currentCell != null)
        {
            Vector2D nextPos = this.currentCell.getCorner();
//            this.setDirection(nextPos.sub(this.getPosition()));
        }
    }
    
    
    private Cell findNextCell()
    {
        if (mileStone.hasNext())
        {
            PointNode node = (PointNode) mileStone.next();
            
            return grid2D.getCellAt(node.getCoordinate());
        }
        
        return null;
    }
}
