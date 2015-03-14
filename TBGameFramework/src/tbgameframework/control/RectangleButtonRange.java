/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

import java.awt.Rectangle;
import tbgameframework.Board;
import tbgameframework.math.Collision;
import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public class RectangleButtonRange implements ButtonRange<Rectangle> {

    static RectangleButtonRange Inst;
    
    private RectangleButtonRange(){
        
    }
    
    static public RectangleButtonRange getInst(){
        if(Inst == null){
            Inst = new RectangleButtonRange();
        }
        return Inst;
    }
    
    @Override
    public boolean CheckRange(Rectangle range, boolean isUntransform) {
        
        Vector2D mousePos;
        double miniDefault = 0.001;
        if(isUntransform){
            mousePos = new Vector2D(Board.getInstance().GameInput.ScreenMousePosition());
        }
        else{
            mousePos = new Vector2D(Board.getInstance().GameInput.MousePosition());
        }
        if(Collision.InRange(mousePos, range)){
            return true;
        }
        return false;
    }
}
