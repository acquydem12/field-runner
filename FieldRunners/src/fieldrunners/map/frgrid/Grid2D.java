/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.frgrid;

import fieldrunners.gameobjects.tower.TowerBYS;
import fieldrunners.pathfinding.astar.ASPathFinder;
import fieldrunners.pathfinding.astar.map2d.Map2D;
import fieldrunners.pathfinding.astar.map2d.Map2DFinderFactory;
import fieldrunners.pathfinding.core.FinderFactory;
import fieldrunners.pathfinding.core.Path;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import tbgameframework.IDrawable;
import tbgameframework.VisibleObject;
import tbgameframework.sprite.ISprite;

/**
 *
 * @author Uchiha Salm
 */
public class Grid2D
    extends Map2D
    implements IDrawable
{
    private VisibleObject visibleComponent = new VisibleObject();
    private ISprite light;
    private ISprite dark;
    
    public Grid2D(Rectangle bound, int nColumns, int nRows)
    {
        super(nColumns, nRows);
        this.bound = bound;
        this.cells = new Cell[nColumns][nRows];
        cellWidth = bound.width / nColumns;
        cellHeight = bound.height / nRows;
        
        Point cCorner = new Point();
        cCorner.x = bound.x;
        for (int i = 0; i < cells.length; i++) 
        {
            cCorner.y = bound.y;
            for (int j = 0; j < cells[i].length; ++j)
            {
                cells[i][j] = new Cell(new Rectangle(cCorner.x, cCorner.y, cellWidth - 1, cellHeight - 1));
                cCorner.y += cellHeight;
            }
            
            cCorner.x += cellWidth;
        }
    }
    
    public Cell getCellAt(Point p)
    {
        return cells[p.x][p.y];
    }
    
    public Cell getCellAt(int x, int y)
    {
        return cells[x][y];
    }
    
    public Point getCellPositionOf(Point p)
    {
        return new Point((p.x - bound.x) / cellWidth, (p.y - bound.y) / cellHeight);
    }
    
    public Point getCellPositionOf(int x, int y)
    {
        return new Point((x - bound.x) / cellWidth, (y - bound.y) / cellHeight);
    }
    
    public boolean isHasTowerAt(Point p)
    {
        return (super.getState(p) == Map2D.LOCKED); 
    }
    
    public void growTowerAt(Point p, TowerBYS tower)
    {
        super.setState(p, Map2D.LOCKED);
        this.getCellAt(p).setTower(tower);
    }
    
    public void growTowerAt(int x, int y, TowerBYS tower)
    {
        this.growTowerAt(new Point(x, y), tower);
    }
    
    public void removeTowerAt(Point p)
    {
        super.setState(p, Map2D.MOVEABLE);
        this.getCellAt(p).killTower();
    }
    
    public void removeTowerAt(int x, int y)
    {
        super.setState(new Point(x, y), Map2D.MOVEABLE);
    }
    
    public Rectangle getBound()
    {
        return this.bound;
    }
    
    private Rectangle bound;
    private int cellWidth, cellHeight;
    private Cell[][] cells;
    private ASPathFinder pathFinder = new ASPathFinder();

    @Override
    public void Draw(Graphics2D g2D)
    {
        for (int i = 0; i < cells.length; ++i)
        {
            for (int j = 0; j < cells[i].length; ++j)
            {
                if (!this.isHasTowerAt(new Point(i, j)))
                {
                    this.DrawCell(g2D, i, j);
                }
            }
        }
    }
    
    private void DrawCell(Graphics2D g2D, int y, int x)
    {
        ISprite img = light;
        try
        {
            int pos = (x + y) % 2;
            if (pos == 0)
            {
                img = dark;
            }
            img.setDrawObject(cells[y][x]);
            img.Draw(g2D);
        }
        catch (Exception e)
        {
        }
        
    }

    public Path find(Point start, Point goal)
    {
        try
        {
            FinderFactory ff = new Map2DFinderFactory(start, goal, this, this.pathFinder);
            return this.pathFinder.find(ff);
        }
        catch (Exception ex)
        {
            return null;
        }
    }

    @Override
    public boolean isVisibled()
    {
        return visibleComponent.isVisibled();
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        visibleComponent.setVisibled(isVs);
    }

    @Override
    public float getDepth() 
    {
        return visibleComponent.getDepth();
    }

    @Override
    public void setDepth(float depth)
    {
        visibleComponent.setDepth(depth);
    }
}