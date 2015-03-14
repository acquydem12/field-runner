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
class IteratorAdapterImp<E>
    implements Iterator<E>
{
    Iterator<? extends E> adapteeIter;
    
    public IteratorAdapterImp(Iterator<? extends E> adaptee)
    {
        adapteeIter = adaptee;
    }

    @Override
    public boolean hasNext()
    {
        return adapteeIter.hasNext();
    }

    @Override
    public E next()
    {
        try
        {
            return (E) adapteeIter.next();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public void remove()
    {
        adapteeIter.remove();
    }
}
