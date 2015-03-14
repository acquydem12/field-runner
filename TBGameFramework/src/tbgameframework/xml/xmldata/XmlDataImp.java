/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tbgameframework.utils.factory.Factory;
import tbgameframework.xml.xmldata.builder.XmlBuilder;

/**
 *
 * @author Uchiha Salm
 */
public class XmlDataImp<E extends XmlInstance>
    implements XmlData<E>
{
    private Collection<E> instances;
    private Factory<XmlBuilder> builderFactory;
    private Factory<E> instanceFactory;
    private String dataName;
        
    protected Collection<E> createCollection()
    {
        return new LinkedList<E>();
    }
    
    public XmlDataImp(String dataName, Factory<XmlBuilder> xmlBF, Factory<E> xmlIF)
    {
        this.dataName = dataName;
        this.builderFactory = xmlBF;
        this.instanceFactory = xmlIF;
    }

    @Override
    public void readFromFile(String fileName)
    {
        XmlBuilder xmlBuilder = this.builderFactory.createProduct();
        
        Document xmlDoc = xmlBuilder.open(fileName, XmlBuilder.OPEN_IF_EXIST);
        Element rootElem = xmlDoc.getDocumentElement();
        this.instances = XmlLoaderImp.createInst(rootElem).getAllSubInstances(dataName, instanceFactory);
    }
    
    @Override
    public void saveToFile(String fileName, String documentTag)
    {
        XmlBuilder xmlBuilder = this.builderFactory.createProduct();
        Document xmlDoc = xmlBuilder.open(fileName, XmlBuilder.CREATE_ALWAYS);
        Element rootElem = xmlDoc.createElement(documentTag);
        for (XmlInstance instance : instances)
        {
            instance.save(XmlSaverImp.createInst(xmlDoc, rootElem));
        }
    }

    @Override
    public Collection<E> getAllInstances()
    {
        return this.instances;
    }

    @Override
    public Collection<E> queryInstances(String property, Object value)
    {
        Collection<E> result = this.createCollection();
        for (E xmlInst : this.instances)
        {
            if (xmlInst.isMatch(property, value))
            {
                result.add(xmlInst);
            }
        }
        
        return result;
    }

    @Override
    public Collection<E> queryInstances(Collection<String> properties, Collection<Object> values)
    {
        Collection<E> result = this.createCollection();
        if (properties.size() > values.size())
        {
            return result;
        }
        
        for (E xmlInst : this.instances)
        {
            Iterator<String> iProp = properties.iterator();
            Iterator<Object> iValue = values.iterator();
            
            while (iProp.hasNext() && iValue.hasNext())
            {
                if (!xmlInst.isMatch(iProp.next(), iValue.next()))
                {
                    break;
                }
            }
            
            if (!iProp.hasNext())
            {
                result.add(xmlInst);
            }
        }
        
        return result;
    }

    @Override
    public E queryFirstInstances(String property, Object value)
    {
        for (E xmlInst : this.instances)
        {
            if (xmlInst.isMatch(property, value))
            {
                return xmlInst;
            }
        }
        
        return null;
    }

    @Override
    public E queryFirstInstances(Collection<String> properties, Collection<Object> values)
    {
        if (properties.size() > values.size())
        {
            return null;
        }
        
        for (E xmlInst : this.instances)
        {
            Iterator<String> iProp = properties.iterator();
            Iterator<Object> iValue = values.iterator();
            
            while (iProp.hasNext() && iValue.hasNext())
            {
                if (!xmlInst.isMatch(iProp.next(), iValue.next()))
                {
                    break;
                }
            }
            
            if (!iProp.hasNext())
            {
                return xmlInst;
            }
        }
        
        return null;
    }
}
