/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata;

import java.util.Collection;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public interface XmlLoader
{
    String getAttribute(String attribName);
    String getTextAttribute(String attribName);
    int getIntegerAttribute(String attribName);
    double getDoubleAttribute(String attribName);
    void getAttribute(String attribName, XmlAttributeable attrib);
    void getSubInstance(String instName, XmlInstance inst);
    String getSubTextInstance(String instName);
    int getSubIntegerInstance(String instName);
    double getSubDoubleInstance(String instName);
    public <E> Collection<E> getAllSubInstances(String instName, Factory<E> instF);
    public Collection<XmlLoader> getAllSubInstances(String instName);
}
