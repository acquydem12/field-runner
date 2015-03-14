/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import fieldrunners.gameobjects.ICompleteable;
import java.awt.Graphics2D;
import tbgameframework.GameObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.SpriteDrawObject;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author Uchiha Salm
 */
class Explosion
    implements GameObject, ICompleteable, SpriteDrawObject
{
    private int ttl;
    private ISprite spr;
    private Vector2D position = new Vector2D();
    private boolean isVisibled = true;
    private float depth = 0f;
    
    public Explosion(String sprName, Vector2D anchor, Vector2D pos, int ttl)
    {
        this.spr = SpriteManager.getInstance().getSprite(sprName);
        this.position.setValues(pos);
        this.spr.setDrawPoint(anchor);
        this.spr.setDrawObject(this);
        
        if (this.spr instanceof ImageSprite)
        {
            ImageSprite imgSpr = (ImageSprite) spr;
            
            imgSpr.setLoopable(false);
        }
        
        this.ttl = ttl;
    }
    
    public void setPosition(Vector2D pos)
    {
        this.position.setValues(pos);
    }
    
    @Override
    public boolean isVisibled()
    {
        return this.isVisibled;
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        this.isVisibled = isVs;
    }

    @Override
    public float getDepth()
    {
        return this.depth;
    }

    @Override
    public void setDepth(float depth)
    {
        this.depth = depth;
    }

    @Override
    public void Draw(Graphics2D g2D)
    {
        this.spr.Draw(g2D);
    }

    @Override
    public Vector2D getPosition()
    {
        return this.position;
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

    @Override
    public boolean isCompleted()
    {
        return (ttl <= 0);
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
    }

    @Override
    public void Update()
    {
        --ttl;
    }
    
}
