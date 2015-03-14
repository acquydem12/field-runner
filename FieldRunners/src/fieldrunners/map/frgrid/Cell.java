/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.frgrid;

import fieldrunners.gameobjects.tower.TowerBYS;
import java.awt.Rectangle;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteDrawObject;

/**
 *
 * @author Uchiha Salm
 */
public class Cell
    implements SpriteDrawObject
{
    private TowerBYS cellTower;
    
    Cell(Rectangle bound)
    {
        this.bound = bound;
        this.center = new Vector2D(bound.x + bound.width / 2, bound.y + bound.height / 2);
    }
    
    public Rectangle getBound()
    {
        return bound;
    }
    
    public Vector2D getCenter()
    {
        return this.center;
    }
    
    public Vector2D getCorner()
    {
        return new Vector2D(this.bound.x, this.bound.y);
    }
    
    private Rectangle bound;
    private Vector2D center;

    @Override
    public Vector2D getPosition()
    {
        return this.getCorner();
    }

    private static final Vector2D scale = new Vector2D(1f, 1f);
    @Override
    public Vector2D getScale()
    {
        return scale;
    }

    @Override
    public float getAngle()
    {
        return 0f;
    }
    
    public void setTower(TowerBYS tower)
    {
        this.cellTower = tower;
    }
    
    public TowerBYS getCellTower()
    {
        return this.cellTower;
    }
    
    public void killTower()
    {
        if (this.cellTower != null)
        {
            this.cellTower.setEnabled(false);
            this.cellTower = null;
        }
    }
}