/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects;

import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteDrawObject;

/**
 *
 * @author Uchiha Salm
 */
public class BackgroundObject
    implements SpriteDrawObject
{
    private static final Vector2D position = new Vector2D();
    private Vector2D scaleRatio = new Vector2D();
    
    private BackgroundObject()
    {
        
    }
    
    public static BackgroundObject createObject(Vector2D oldSize, Vector2D newSize)
    {
        BackgroundObject bObj = new BackgroundObject();
        
        bObj.scaleRatio.x = newSize.x / oldSize.x;
        bObj.scaleRatio.y = newSize.y / oldSize.y;
        
        return bObj;
    }

    @Override
    public Vector2D getPosition()
    {
        return position;
    }

    @Override
    public Vector2D getScale()
    {
        return this.scaleRatio;
    }

    @Override
    public float getAngle()
    {
        return 0.f;
    }
}
