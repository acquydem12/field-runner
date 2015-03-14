/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.math;

import java.awt.Rectangle;

/**
 *
 * @author MrKupi
 */
public class Collision {
    
    static public boolean InRange(Vector2D vector, Rectangle rect){
        if(rect.x > vector.x || vector.x > (rect.x + rect.width) ||
                rect.y > vector.y || vector.y > (rect.y + rect.height)){
            return false;
        }
        return true;
    }
    
    static public boolean InRange(float x, float val1, float val2 ){ 
        
            return (( val1 <= x && x <= val2 ) ? true : false );
    }
    
    static public double Square(double x){
        return (x * x);
    }
    
    static void PerpendicularPoint( Vector2D original, Vector2D mVector, Vector2D outPoint){
	outPoint.x = ( Square(mVector.y)*original.x - mVector.x*mVector.y*original.y ) / ( Square(mVector.x) + Square(mVector.y) );
	outPoint.y = ( - ( mVector.x * outPoint.x )/ mVector.y );
    }
    
    static public boolean RectanglevsRectangle(Rectangle rect1, Vector2D velocity1, Rectangle rect2,
                                Vector2D velocity2, Vector2D hitDir){
        
        Vector2D velocity = new Vector2D();
        if(velocity1.x * velocity2.x > 0){
            velocity.x = Math.max(velocity1.x, velocity2.x) - Math.min(velocity1.x, velocity2.x);
            velocity.y = Math.max(velocity1.y, velocity2.y) - Math.min(velocity1.y, velocity2.y);
        }
        else{
            velocity.x = Math.max(velocity1.x, velocity2.x) + Math.min(velocity1.x, velocity2.x);
            velocity.y = Math.max(velocity1.y, velocity2.y) + Math.min(velocity1.y, velocity2.y);
        }
        
        // On Ox Axis
	if( !InRange( rect2.x, rect1.x, rect1.x + rect1.width + (float)velocity.x ) && 
                !InRange( rect2.x + rect2.width, rect1.x, rect1.x + rect1.width + (float)velocity.x ) ){
		return false;
        }

	// On Oy Axis
	if ( !InRange( rect2.y, rect1.y, rect1.y + rect1.height + (float)velocity.y ) && 
                !InRange( rect2.y + rect2.height, rect1.y, rect1.y + rect1.height + (float)velocity.y ) ){
		return false;
        }

	if ((velocity.x * velocity.y) != 0)
	{
		hitDir.x = ( velocity.x != 0 ? ( velocity.x > 0 ? 1.f : -1.f) : 0.f);
		hitDir.y = ( velocity.y != 0 ? ( velocity.y > 0 ? 1.f : -1.f) : 0.f);
		return true;
	}

	// On Axis Perpendicular with vector velocity
	Vector2D minPoint1 = new Vector2D();
        Vector2D maxPoint1 = new Vector2D();
        Vector2D minPoint2 = new Vector2D();
        Vector2D maxPoint2 = new Vector2D();
	//-----Point choosing
	if( velocity.x * velocity.y < 0 )
	{
		minPoint1.x = rect1.x; 
                minPoint1.y = rect1.y;
		maxPoint1.x = rect1.x + rect1.width + velocity.x;
                maxPoint1.y = rect1.y + rect1.height + velocity.y;
		minPoint2.x = rect2.x; 
                minPoint2.y = rect2.y;
		maxPoint2.x = rect2.x + rect2.width; 
                maxPoint2.y = rect2.y + rect2.height;
	}
	else if( velocity.x * velocity.y > 0 )
	{
		minPoint1.x = rect1.x; 
                minPoint1.y = rect1.y + rect1.height;
		maxPoint1.x = rect1.x + rect1.width + velocity.x;
                maxPoint1.y = rect1.y + velocity.y;
		minPoint2.x = rect2.x;
                minPoint2.y = rect2.y + rect2.height;
		maxPoint2.x = rect2.x + rect2.width;
                maxPoint2.x = rect2.y;
	}

	PerpendicularPoint( minPoint1, velocity, minPoint1 );
	PerpendicularPoint( maxPoint1, velocity, maxPoint1 );
	PerpendicularPoint( minPoint2, velocity, minPoint2 );
	PerpendicularPoint( maxPoint2, velocity, maxPoint2 );

	if ( !InRange((float)minPoint2.x, (float)minPoint1.x, (float)maxPoint1.x ) && 
                !InRange((float)maxPoint2.x, (float)minPoint1.x, (float)maxPoint1.x )){
		return false;
        }

	hitDir.x = ( velocity.x != 0 ? ( velocity.x > 0 ? 1.f : -1.f) : 0.f);
	hitDir.y = ( velocity.y != 0 ? ( velocity.y > 0 ? 1.f : -1.f) : 0.f);

	return true;
    }

    static public boolean IsIntersect(Rectangle container, Rectangle rect){
        
        return !(rect.x > container.x + container.width || rect.y > container.y + container.height ||
                rect.x + rect.width < container.x || rect.y + rect.height < container.y);
    }
    
    static public boolean IsContains(Rectangle container, Rectangle rect){
        
        return (rect.x > container.x && rect.y > container.y && 
                rect.x + rect.width < container.x + container.width &&
                rect.y + rect.height < container.y + container.height);
    }
    
    static public boolean IsEqual(Rectangle rect1, Rectangle rect2){
        return (rect1.x == rect2.x && rect1.y == rect2.y && 
                rect1.width == rect2.width && rect1.height == rect2.height);
    }
}
