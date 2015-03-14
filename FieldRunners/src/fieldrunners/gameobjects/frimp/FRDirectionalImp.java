/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import tbgameframework.math.Vector2D;

/**
 *
 * @author Uchiha Salm
 */
public class FRDirectionalImp
    implements FRDirectional
{
    private static final float TwoPi = (float) (2 * Math.PI);
    
    private Vector2D direction;
    private float angle;
    
    public FRDirectionalImp()
    {
        this.direction = new Vector2D();
        
        this.setAngle(0);
    }
    
    private void reCalcAngle()
    {
        this.angle = ((float) Math.atan2(direction.y, direction.x) + TwoPi) % TwoPi;
    }
    
    private void reCalcDirect()
    {
        this.direction.x = Math.cos(angle);
        this.direction.y = Math.sin(angle);
    }
    
    @Override
    final public void setDirection(Vector2D newDirect)
    {
        this.direction = newDirect.normalize();
        
        reCalcAngle();
    }
    
    @Override
    final public void setAngle(float newAngle)
    {
        this.angle = (Math.abs(newAngle) % TwoPi + TwoPi) % TwoPi;
        
        reCalcDirect();
    }
    
    @Override
    final public Vector2D getDirection()
    {
        return this.direction;
    }

    @Override
    final public float getAngle()
    {
        return this.angle;
    }
    
}
