/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.utils.iterators;

import java.util.Iterator;

/**
 *
 * @author Uchiha Salm
 */
public class IteratorAdapter
{
    public static <E> Iterator<E> adapt(Iterator<? extends E> adaptee)
    {
        return new IteratorAdapterImp<E>(adaptee);
    }
}
