/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.imagesprite;

import java.awt.Rectangle;
import java.util.ArrayList;
import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public interface ISpriteInfo {
    String getName();
    String getImageName();
    Vector2D getDrawPoint();
    ArrayList<Rectangle> getFrame();
    
    static final String tagName = "Name";
    static final String tagImageName = "ImageName";
    static final String tagDrawPointX = "PointX";
    static final String tagDrawPointY = "PointY";
    static final String tagFrame = "Frame";
}
