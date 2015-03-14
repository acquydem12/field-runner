/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects;

import tbgameframework.Board;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteDrawObject;

/**
 *
 * @author Uchiha Salm
 */
public class CursorObject
    implements SpriteDrawObject
{
    private static final Vector2D scaleRatio = new Vector2D(1.0, 1.0);
    
    @Override
    public Vector2D getPosition()
    {
        return Board.getInstance().GameInput.MousePosition();
    }

    @Override
    public Vector2D getScale()
    {
        return scaleRatio;
    }

    @Override
    public float getAngle()
    {
        return 0.f;
    }
    
    private CursorObject()
    {
        
    }
    
    private static CursorObject instance = new CursorObject();
    
    public static CursorObject getInst()
    {
        return instance;
    }
}
