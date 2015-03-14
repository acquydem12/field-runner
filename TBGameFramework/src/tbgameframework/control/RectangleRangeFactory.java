/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

import java.awt.Rectangle;
import tbgameframework.math.Vector2D;


/**
 *
 * @author MrKupi
 */
public class RectangleRangeFactory implements IButtonRangeFactory {

    static RectangleRangeFactory factory = null;
    
    private RectangleRangeFactory(){
        
    }
    
    static public RectangleRangeFactory getInst(){
        if(factory == null){
            factory = new RectangleRangeFactory();
        }
        return factory;
    }
    
    @Override
    public void RePosition(Object range, Object newRange) {
        Rectangle rect = (Rectangle)range;
        Vector2D newRect = (Vector2D)newRange;
        rect.x = (int)newRect.x;
        rect.y = (int)newRect.y;
    }
    
}
