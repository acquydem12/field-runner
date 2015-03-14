/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import java.awt.Graphics2D;
import tbgameframework.IUpdateable;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.SpriteDrawObject;
import tbgameframework.sprite.SpriteManager;

/**
 *
 * @author Uchiha Salm
 */
public class SpriteShine
    implements FRShine
{
    private IUpdateable absMirror;
    
    private ISprite sprite;
    
    public SpriteShine(String spriteName)
    {
        this.sprite = SpriteManager.getInstance().getSprite(spriteName);
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
