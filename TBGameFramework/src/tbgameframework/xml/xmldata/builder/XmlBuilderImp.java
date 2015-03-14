/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata.builder;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author Uchiha Salm
 */
class XmlBuilderImp
    implements XmlBuilder
{
    private Document xmlDoc = null;
    private String fileName;
    
    @Override
    public Document open(String fileName, int mode)
    {
        File fXml = new File(fileName);
        
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            this.xmlDoc = dBuilder.parse(fXml);
            this.fileName = fileName;
            
            return xmlDoc;
        
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        return null;
    }

    @Override
    public void updateToFile()
    {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(this.xmlDoc);
            StreamResult result = new StreamResult(new File(fileName));
            
            transformer.transform(source, result);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
