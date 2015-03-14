/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.utils.collections;

import tbgameframework.utils.factory.Factory;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Uchiha Salm
 */
public class CollectionConverter
{
    public static <E> Collection<E> convert(Collection<? extends E> convertee, Collection<E> converted)
    {
        for (E item : convertee)
        {
            converted.add(item);
        }
        
        return converted;
    }
    
    
    /**
     * default is Hash Set
     * @param <E>
     * @param convertee
     * @return
     */
    public static <E> Collection<E> convert(Collection<? extends E> convertee)
    {
        Collection<E> converted = new HashSet<>();
        
        return convert(convertee, converted);
    }
    
    public static <E> Collection<E> convert(Collection<? extends E> convertee, Factory<Collection<E>> convertedFactory)
    {
        Collection<E> converted = convertedFactory.createProduct();
        
        return convert(convertee, converted);
    }
}
