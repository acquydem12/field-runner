/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

/**
 *
 * @author MrKupi
 * A factory for create button range
 */
public interface ButtonRange<T> {
    
    public boolean CheckRange(T range, boolean isScreen);
}
