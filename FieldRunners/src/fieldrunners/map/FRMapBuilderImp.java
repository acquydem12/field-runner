/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map;

import fieldrunners.gameobjects.BackgroundObject;
import fieldrunners.gameobjects.CursorObject;
import fieldrunners.map.frgrid.Grid2D;
import fieldrunners.map.round.RoundData;
import fieldrunners.scenes.Survival;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashSet;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;
import tbgameframework.scene.SceneManager;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author Uchiha Salm
 */
class FRMapBuilderImp
    implements FRMapBuilder
{
    private ImageSprite background = null;
    private int playMode = Survival.PLAYMODE_SURVIVAL;
    private Vector2D beginPosition = new Vector2D(0, 0);
    private Point start = new Point(-1, -1);
    private Point goal = new Point(-1, -1);
    private Point size = new Point();
    private Vector2D totalSize = new Vector2D();
    private Point cellSize = new Point(100, 100);
    private Collection<RoundData> roundDatas;
    private String name;
    
    private Collection<Point> lockPositions = new HashSet<>();
    
    private SceneManager SM = null;
    
    public FRMapBuilderImp()
    {
        
    }
    
    @Override
    public void setMapBackground(String image)
    {
        this.background = (ImageSprite) SpriteManager.getInstance().getSprite(image);
    }

    @Override
    public void setPlayMode(int playMode)
    {
        this.playMode = playMode;
    }

    @Override
    public void setGridSize(Point size)
    {
        this.size.setLocation(size);
    }

    @Override
    public void setMapBeginPosition(Vector2D beginPosition)
    {
        this.beginPosition.setValues(beginPosition);
    }

    @Override
    public void setStart(Point startPosition)
    {
        this.start.setLocation(startPosition);
    }

    @Override
    public void setGoal(Point goalPosition)
    {
        this.goal.setLocation(goalPosition);
    }

    @Override
    public void Lock(Point lockPos)
    {
        this.lockPositions.add(lockPos);
    }

    @Override
    public void Open(Point openPos)
    {
        if (this.lockPositions.contains(openPos))
        {
            this.lockPositions.remove(openPos);
        }
    }

    @Override
    public Scene createMap()
    {
        if (!this.checkMap())
        {
            return null;
        }
        
        Grid2D grid = new Grid2D(new Rectangle((int) this.beginPosition.x, (int) this.beginPosition.y,
                this.cellSize.x * this.size.x, this.cellSize.y * this.size.y), this.size.x, this.size.y);
        for (Point lockPos : this.lockPositions)
        {
            grid.growTowerAt(lockPos, null);
        }
        
        Survival resMap = new Survival(this.SM, this.name, totalSize, grid);
        this.background.setDrawObject(BackgroundObject.createObject(this.background.getSize(), totalSize));
        resMap.setBackground(background);
        resMap.setStart(start);
        resMap.setGoal(goal);
        resMap.setRounds(this.roundDatas);
        
        return resMap;
    }
    
    private boolean checkMap()
    {
        if (this.SM == null)
        {
            return false;
        }
        else if ("".equals(this.name))
        {
            return false;
        }
        else if (this.background == null)
        {
            return false;
        }
        else if (this.beginPosition.x < 0 || this.beginPosition.y < 0)
        {
            return false;
        }
        else if (this.start.x < 0 || this.start.y < 0 ||
                this.goal.x < 0 || this.goal.y < 0 ||
                this.size.x <= 0 || this.size.y <= 0)
        {
            return false;
        }
        else if (totalSize.x <= 0 || totalSize.y <= 0)
        {
            return false;
        }
        else if (cellSize.x <= 0 || cellSize.y <= 0)
        {
            return false;
        }
        else if (this.roundDatas == null || this.roundDatas.isEmpty())
        {
            return true;
        }
        
        return true;
    }
    
    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public void setTotalMapSize(Point size)
    {
        this.totalSize.setValues(size.x, size.y);
    }
    
    @Override
    public void setCellSize(Point size)
    {
        this.cellSize.setLocation(size);
    }

    @Override
    public void setSceneManager(SceneManager sceneManager)
    {
        this.SM = sceneManager;
    }

    @Override
    public void setRounds(Collection<RoundData> rnds)
    {
        this.roundDatas = rnds;
    }
}
