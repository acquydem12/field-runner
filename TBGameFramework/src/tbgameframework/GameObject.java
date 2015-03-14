/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

/**
 *
 * @author MrKupi
 */
public interface GameObject
    extends IDrawable, IUpdateable{
    
    void resume();
    
    void pause();
}
