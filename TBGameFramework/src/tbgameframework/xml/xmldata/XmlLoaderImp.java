/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata;

import java.util.Collection;
import java.util.LinkedList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import tbgameframework.utils.factory.Factory;


class XmlLoaderImp
    implements XmlLoader
{
    Element elem;
    
    private XmlLoaderImp()
    {
        
    }
    
    public static XmlLoaderImp createInst(Element xmlElem)
    {
        XmlLoaderImp newInst = new XmlLoaderImp();
        newInst.elem = xmlElem;
        
        return newInst;
    }
    
    @Override
    public String getAttribute(String attribName)
    {
        return this.elem.getAttribute(attribName);
    }

    @Override
    public String getTextAttribute(String attribName)
    {
        return this.elem.getAttribute(attribName);
    }

    @Override
    public int getIntegerAttribute(String attribName)
    {
        return Integer.valueOf(this.elem.getAttribute(attribName));
    }

    @Override
    public double getDoubleAttribute(String attribName)
    {
        return Double.valueOf(this.elem.getAttribute(attribName));
    }

    @Override
    public void getAttribute(String attribName, XmlAttributeable attrib)
    {
        attrib.fromString(this.elem.getAttribute(attribName));
    }

    @Override
    public void getSubInstance(String instName, XmlInstance inst)
    {
        inst.load(XmlLoaderImp.createInst((Element) elem.getElementsByTagName(instName).item(0)));
    }
    
    @Override
    public <E> Collection<E> getAllSubInstances(String instName, Factory<E> instF)
    {
        Collection<E> instances = new LinkedList<E>();
        NodeList instNodes = elem.getElementsByTagName(instName);
        
        for (int i = 0; i < instNodes.getLength(); i++)
        {
            try
            {
                E e = instF.createProduct();
                XmlInstance inst = (XmlInstance) e;
                inst.load(XmlLoaderImp.createInst((Element) instNodes.item(i)));
                instances.add(e);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return instances;
    }

    @Override
    public String getSubTextInstance(String instName)
    {
        return elem.getElementsByTagName(instName).item(0).getTextContent();
    }

    @Override
    public int getSubIntegerInstance(String instName)
    {
        return Integer.valueOf(elem.getElementsByTagName(instName).item(0).getTextContent());
    }

    @Override
    public double getSubDoubleInstance(String instName)
    {
        return Double.valueOf(elem.getElementsByTagName(instName).item(0).getTextContent());
    }

    @Override
    public Collection<XmlLoader> getAllSubInstances(String instName)
    {
        Collection<XmlLoader> subInsts = new LinkedList<>();
        NodeList instNodes = elem.getElementsByTagName(instName);
        
        for (int i = 0; i < instNodes.getLength(); i++)
        {
            try
            {
                subInsts.add(XmlLoaderImp.createInst((Element) instNodes.item(i)));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return subInsts;
    }
}
