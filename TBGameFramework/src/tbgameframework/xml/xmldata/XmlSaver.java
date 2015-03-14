/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata;

import org.w3c.dom.Element;

/**
 *
 * @author Uchiha Salm
 */
public interface XmlSaver
{
    void addAttribute(String attribName, Object attrib);
    void addSubInstance(String instName, Object inst);
    void addSubInstance(String instName, XmlInstance inst);
    
    Element createElement();
}
