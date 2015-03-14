/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.utils.prototype;

/**
 *
 * @author Uchiha Salm
 */
class CloneAdapterImp<E>
    implements Cloneable<E>
{
    private Cloneable<?> adaptee;
    
    public CloneAdapterImp(Cloneable<?> adaptee)
    {
        this.adaptee = adaptee;
    }
    
    @Override
    public E clone()
    {
        try
        {
            return (E) adaptee.clone();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            
            return null;
        }
    }
    
}
