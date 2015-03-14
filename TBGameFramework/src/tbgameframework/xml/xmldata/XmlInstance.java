/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 *
 * @author Uchiha Salm
 */
public interface XmlInstance
{
    public void load(XmlLoader xmlLoader);
    public Element save(XmlSaver xmlSaver);
    
    public boolean isMatch(String property, Object value);
}
