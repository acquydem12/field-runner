/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states.statesprite;

import java.awt.Graphics2D;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.SpriteDrawObject;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author Uchiha Salm
 */
public class StateSprite
    implements ISprite
{
    private SpriteMap spritesMap;
    private ImageSprite currentSprite = null;
    private StateSpriteDrawObject drawObj = null;
    
    StateSprite(SpriteMap sprMap)
    {
        spritesMap = sprMap;
    }
    
    // Get new sprite from drawobject state
    private void getNewSpriteByState()
    {
        try
        {
            this.currentSprite = (ImageSprite) spritesMap.getObject(drawObj);
            
            if (currentSprite == null)
            {
                System.err.println("Current sprite null");
            }
        }
        catch (Exception e)
        {
            System.err.println("Invalid state");
        }
    }
    
    public void Draw(Graphics2D g2D, Vector2D position, float angle)
    {
        getNewSpriteByState();
        if (currentSprite != null)
        {
            this.currentSprite.Draw(g2D, position, angle);
        }
        else
        {
            System.err.println("State sprite error: empty state");
            // throw invalid state exception
        }
    }
    
    @Override
    public void Draw(Graphics2D g2D)
    {
        getNewSpriteByState();
        this.currentSprite.Draw(g2D);
    }

    public void Reset()
    {
        this.currentSprite.Reset();
    }

    @Override
    public void setDrawObject(SpriteDrawObject sdObj)
    {
        if (sdObj instanceof StateSpriteDrawObject)
        {
            this.drawObj = (StateSpriteDrawObject) sdObj;
            
            for (ISprite spr : spritesMap)
            {
                spr.setDrawObject(drawObj);
            }
            
            getNewSpriteByState();
        }
        else
        {
            // throw invalid draw object exception
        }
    }

    @Override
    public StateSprite clone()
    {
        StateSprite cloned = new StateSprite(this.spritesMap.clone());
        
        return cloned;
    }
    
    @Override
    public void setVisibled(boolean isVs)
    {
        for (ISprite spr : spritesMap)
        {
            spr.setVisibled(isVs);
        }
    }

    @Override
    public boolean isVisibled()
    {
        return currentSprite.isVisibled();
    }

    @Override
    public float getDepth()
    {
        return currentSprite.getDepth();
    }

    @Override
    public void setDepth(float depth)
    {
        for (ISprite spr : spritesMap)
        {
            spr.setDepth(depth);
        }
    }

    @Override
    public SpriteDrawObject getDrawObject() {
        return this.drawObj;
    }

    @Override
    public void setDrawPoint(Vector2D drawPoint) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector2D getDrawPoint() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
