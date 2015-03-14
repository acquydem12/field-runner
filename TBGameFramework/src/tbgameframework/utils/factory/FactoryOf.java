/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.utils.factory;

/**
 *
 * @author Uchiha Salm
 */
public class FactoryOf<E>
    implements Factory<E>
{
    private E inst;
    
    private FactoryOf(E obj)
    {
        inst = obj;
    }
    
    public static <E> FactoryOf<E> make(E obj)
    {
        return new FactoryOf(obj);
    }
    
    @Override
    public E createProduct()
    {
        return inst;
    }
    
}
