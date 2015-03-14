/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.math;

import java.awt.Point;

/**
 *
 * @author MrKupi
 */
public class Vector2D
    extends Point.Double
{
    public Vector2D()
    {
        super();
    }

    public Vector2D(double cx, double cy)
    {
        super(cx, cy);
    }
    
    public Vector2D( Vector2D other ){
        super(other.x, other.y);
    }
    
    public double distance()
    {
        return (double)Math.sqrt((x*x)+(y*y));
    }
    
    public Vector2D normalize()
    {
        
        Vector2D vector2D = new Vector2D();
        double length = this.distance();
        vector2D.x = this.x / length;
        vector2D.y = this.y / length;
        
        return vector2D;
    }
    
    public void setValues(Vector2D other)
    {
        this.x = other.x;
        this.y = other.y;
    }
    
    public void setValues(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Point asPoint()
    {
        return new Point((int) x, (int) y);
    }
    
    public void selfAdd(Vector2D other)
    {
        this.x += other.x;
        this.y += other.y;
    }
    
    public Vector2D add(Vector2D other)
    {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    
    public Vector2D mul(float scaleRatio)
    {
        return new Vector2D(this.x * scaleRatio, this.y * scaleRatio);
    }
    
    public Vector2D sub(Vector2D other)
    {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    
    public double scalarProduct(Vector2D other)
    {
        return (this.x * other.x + this.y * other.y);
    }
    
    public String toString()
    {
        return "x = " + this.x + "; y = " + this.y;
    }
}
