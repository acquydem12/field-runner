/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata;

import java.util.Collection;

/**
 *
 * @author Uchiha Salm
 */
public interface XmlData<E extends XmlInstance>
{
    void readFromFile(String fileName);
    void saveToFile(String fileName, String documentTag);
    
    Collection<E> queryInstances(String property, Object value);
    Collection<E> queryInstances(Collection<String> properties, Collection<Object> values);
    
    E queryFirstInstances(String property, Object value);
    E queryFirstInstances(Collection<String> properties, Collection<Object> values);
    
    Collection<E> getAllInstances();
}
