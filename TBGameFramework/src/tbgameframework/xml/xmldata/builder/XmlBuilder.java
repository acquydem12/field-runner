/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata.builder;

import org.w3c.dom.Document;

/**
 *
 * @author Uchiha Salm
 */
public interface XmlBuilder
{
    public static final int OPEN_IF_EXIST = 0;
    public static final int OPEN_CREATE = 1;
    public static final int CREATE_ALWAYS = 2;
    
    public Document open(String fileName, int mode);
    public void updateToFile();
}
