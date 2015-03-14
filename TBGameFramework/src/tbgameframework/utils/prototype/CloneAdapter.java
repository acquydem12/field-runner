/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.utils.prototype;

/**
 *
 * @author Uchiha Salm
 */
public class CloneAdapter
{
    public static <E> Cloneable<E> adapt(Cloneable<?> cloneAdaptee)
    {
        return new CloneAdapterImp<E>(cloneAdaptee);
    }
}