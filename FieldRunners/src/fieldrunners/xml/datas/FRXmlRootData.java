/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.xml.datas;

import tbgameframework.utils.factory.Factory;
import tbgameframework.xml.xmldata.XmlDataImp;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.builder.XmlBuilderFactory;

/**
 *
 * @author Uchiha Salm
 */
public class FRXmlRootData<E extends XmlInstance>
    extends XmlDataImp<E>
{
    public static final String path = "src/resources/xmls/";
    
    public FRXmlRootData(String dataName, Factory<E> xmlIF)
    {
        super(dataName, XmlBuilderFactory.getInst(), xmlIF);
    }
    
    @Override
    public void readFromFile(String fileName)
    {
        super.readFromFile(path + fileName);
    }
}
