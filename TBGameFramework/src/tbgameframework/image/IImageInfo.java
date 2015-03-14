/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

import java.awt.Rectangle;
import java.util.ArrayList;
import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public interface IImageInfo {
    
    /*
     * Allow get Link image
     */
    String getLink();
    /*
     * Allow set Link image
     */
    void setLink(String link);
    
    /* 
     * Allow get Name of sprite
     */
    String getName();
    /*
     * Allow set name for sprite
     */
    void setName(String name);
    
    /*
     * allow get Image ID correct
     */
    String getImageID();
    /*
     * allow set ImageID correct with image
     */
    void setImageName(String imageName);
    
    /*
     * allow get list frame of state
     */
    ArrayList<Rectangle> getFrame();
    /*
     * allow set list frame of state
     */
    void setFrame(ArrayList<Rectangle> rects);
    
    Vector2D getDrawPoint();
    
    void setDrawPoint(Vector2D drawPoint);
}
