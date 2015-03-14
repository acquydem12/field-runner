/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import fieldrunners.gameobjects.frimp.FRShine;
import java.awt.Graphics2D;
import tbgameframework.IUpdateable;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteDrawObject;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author Uchiha Salm
 */
public class MissileShine
    implements FRShine
{
    private IUpdateable absMirror;
    private ImageSprite sprite;
    
    public MissileShine(String spriteName, Vector2D anchor, boolean isLoop, int delay)
    {
        this.sprite = (ImageSprite) SpriteManager.getInstance().getSprite(spriteName);
        this.sprite.setDrawPoint(anchor);
        this.sprite.setLoopable(isLoop);
        this.sprite.setDelay(delay);
    }
    
    @Override
    public void setAbstractMirror(IUpdateable am)
    {
        this.absMirror = am;
        
        try
        {
            this.sprite.setDrawObject((SpriteDrawObject) am);
        }
        catch (Exception e)
        {
            System.err.println("Sprite shine need a abstract mirror implement SpriteDrawObject");
        }
    }

    @Override
    public boolean isVisibled()
    {
        return this.sprite.isVisibled();
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        this.sprite.setVisibled(isVs);
    }

    @Override
    public float getDepth()
    {
        return this.sprite.getDepth();
    }

    @Override
    public void setDepth(float depth)
    {
        this.sprite.setDepth(depth);
    }

    @Override
    public void Draw(Graphics2D g2D)
    {
        this.sprite.Draw(g2D);
    }
}
