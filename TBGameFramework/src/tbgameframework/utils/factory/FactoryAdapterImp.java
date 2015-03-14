/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.utils.factory;

/**
 *
 * @author Uchiha Salm
 */
class FactoryAdapterImp<E>
    implements Factory<E>
{
    private Factory<?> adapteeFactory;
    
    public FactoryAdapterImp(Factory<?> adaptee)
    {
        this.adapteeFactory = adaptee;
    }
    
    @Override
    public E createProduct()
    {
        try
        {
            return (E) this.adapteeFactory.createProduct();
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
}
