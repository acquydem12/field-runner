/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.xml.xmldata;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Uchiha Salm
 */
class XmlSaverImp
    implements XmlSaver
{
    Element elem;
    Document xmlDoc;
    
    private XmlSaverImp()
    {
        
    }
    
    public static XmlSaverImp createInst(Document xmlDoc, Element xmlElem)
    {
        XmlSaverImp newInst = new XmlSaverImp();
        newInst.elem = xmlElem;
        newInst.xmlDoc = xmlDoc;
        
        return newInst;
    }

    @Override
    public void addAttribute(String attribName, Object attrib)
    {
        this.elem.appendChild(this.xmlDoc.createElement(attribName).appendChild(xmlDoc.createAttribute(attrib.toString())));
    }

    @Override
    public void addSubInstance(String instName, Object instValue)
    {
        this.elem.appendChild(this.xmlDoc.createElement(instName).appendChild(xmlDoc.createTextNode(instValue.toString())));
    }

    @Override
    public void addSubInstance(String instName, XmlInstance inst)
    {
        Element subElem = this.xmlDoc.createElement(instName);
        this.elem.appendChild(inst.save(XmlSaverImp.createInst(xmlDoc, subElem)));
    }

    @Override
    public Element createElement()
    {
        return this.elem;
    }
    
    
}
