/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.imagesprite;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import org.w3c.dom.Element;
import tbgameframework.math.Vector2D;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.instances.XmlRectangleInstance;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author MrKupi
 */
public class XmlSpriteDataInstance implements ISpriteInfo, XmlInstance{

    private String imageName;
    private String name;
    private Vector2D drawPoint = new Vector2D();
    private ArrayList<Rectangle> arrInfo = new ArrayList();
    
    public XmlSpriteDataInstance(){
        super();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArrayList<Rectangle> getFrame() {
        return this.arrInfo;
    }

    @Override
    public String getImageName() {
        return this.imageName;
    }

    @Override
    public Vector2D getDrawPoint() {
        return this.drawPoint;
    }
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlSpriteDataInstance>{
        private Factory()
        {
        }
        
        @Override
        public XmlSpriteDataInstance createProduct(){
            return new XmlSpriteDataInstance();
        }
        
        public XmlSpriteDataInstance createInstance(ISpriteInfo imageInfo)
        {
            XmlSpriteDataInstance xmlSpriteDataInstance = new XmlSpriteDataInstance();
            xmlSpriteDataInstance.name = imageInfo.getName();
            xmlSpriteDataInstance.imageName = imageInfo.getImageName();
            xmlSpriteDataInstance.drawPoint = imageInfo.getDrawPoint();
            xmlSpriteDataInstance.arrInfo = new ArrayList(imageInfo.getFrame());

            return xmlSpriteDataInstance;
        }
        
        private static XmlSpriteDataInstance.Factory inst = new XmlSpriteDataInstance.Factory();
        
        public static XmlSpriteDataInstance.Factory getInst()
        {
            return inst;
        }
    }
    
    @Override
    public void load(XmlLoader xmlLoader) {
        this.name = xmlLoader.getAttribute(tagName);
        this.imageName = xmlLoader.getAttribute(tagImageName);
        this.drawPoint.x = xmlLoader.getDoubleAttribute(tagDrawPointX);
        this.drawPoint.y = xmlLoader.getDoubleAttribute(tagDrawPointY);
        Collection<XmlInstance> collInfo = xmlLoader.<XmlInstance>getAllSubInstances(tagFrame, FactoryAdapter.<XmlInstance>adapt(XmlRectangleInstance.Factory.getInst()));
        for(XmlInstance xmlRect : collInfo){
            if(xmlRect instanceof XmlRectangleInstance){
                arrInfo.add((Rectangle)xmlRect);
            }
        }
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        xmlSaver.addAttribute(tagName, name);
        xmlSaver.addAttribute(tagImageName, imageName);
        xmlSaver.addAttribute(tagDrawPointX, drawPoint.x);
        xmlSaver.addAttribute(tagDrawPointY, drawPoint.y);
        for(Rectangle rect : arrInfo){
            xmlSaver.addSubInstance(tagFrame, XmlRectangleInstance.Factory.getInst().createInstance(rect));
        }
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value) {
        if (property.equals(tagName) && value instanceof String)
        {
            return name.equals(value);
        }
        else if (property.equals(tagImageName) && value instanceof Integer)
        {
            return imageName == value;
        }
        else if (property.equals(tagDrawPointX) && value instanceof Float)
        {
            return drawPoint.x == (int)value;
        }
        else if (property.equals(tagDrawPointY) && value instanceof Float)
        {
            return drawPoint.y == (int)value;
        }
        return false;
    }
}
