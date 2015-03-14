/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

import java.awt.Rectangle;
import java.util.ArrayList;
import org.w3c.dom.Element;
import tbgameframework.math.Vector2D;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;


public class ImageInfo implements IImageInfo, XmlInstance {

    private String link;
    private String name;
    private String imageName;
    private Vector2D drawPoint = new Vector2D();
    private ArrayList<Rectangle> listRects;
    
    @Override
    public String getLink() {
        return this.link;
    }

    @Override
    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImageID() {
        return this.imageName;
    }

    @Override
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public ArrayList<Rectangle> getFrame() {
        return this.listRects;
    }

    @Override
    public void setFrame(ArrayList<Rectangle> rects) {
        this.listRects = rects;
    }

    @Override
    public void load(XmlLoader xmlLoader) {
    }

    @Override
    public Element save(XmlSaver xmlSaver) {
        return null;
    }

    @Override
    public boolean isMatch(String property, Object value) {
        return true;
    }

    @Override
    public Vector2D getDrawPoint() {
        return this.drawPoint;
    }

    @Override
    public void setDrawPoint(Vector2D drawPoint) {
        this.drawPoint = drawPoint;
    }
    
}
