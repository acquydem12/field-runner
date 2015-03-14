/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

/**
 * A factory to change range of button
 * @author MrKupi
 */
public interface IButtonRangeFactory<T> {
    void RePosition(T range, T newRange);
}
