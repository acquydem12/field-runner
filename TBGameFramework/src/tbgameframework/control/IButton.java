/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

import tbgameframework.IUpdateable;
import tbgameframework.math.Vector2D;
/**
 *
 * @author MrKupi
 * Create an game button for handle an delegate in game
 */
public interface IButton extends IUpdateable{
    
    void setName(String name);
    String getName();
    
    // Delegate when button be Overed
    boolean Over(Object obj, String methodName);
    
    // Delegate when button be Clicked
    boolean Click(Object obj, String methodName);
    
    // Delegate when button be Release
    boolean Release(Object obj, String methodName);
    
    // get button drawObj
    ImageDrawObject getDrawObject();
    
    // set button drawObj
    void setDrawObject(ImageDrawObject drawObj);
    
    // set button state when be interact
    void setButtonState(int state);
    
    // get current state
    int getButtonState();
    
    // Get Button center
    Vector2D Center();
    
    // Get Button Size
    int Width();
    int Height();
}
