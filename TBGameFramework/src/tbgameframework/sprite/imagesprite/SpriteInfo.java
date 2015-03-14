/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.imagesprite;

import java.awt.Rectangle;
import java.util.ArrayList;
import tbgameframework.math.Vector2D;

public class SpriteInfo implements ISpriteInfo {

    private String imageName;
    private String name;
    private Vector2D drawPoint = new Vector2D();
    private ArrayList<Rectangle> frame;
    
    public SpriteInfo(){
    }
    
    public SpriteInfo(String name, ArrayList<Rectangle> rect, Vector2D drawPoint)
    {
        this.name = name;
        this.drawPoint = drawPoint;
        this.frame = new ArrayList(rect);
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ArrayList<Rectangle> getFrame() {
        return this.frame;
    }

    @Override
    public String getImageName() {
        return this.imageName;
    }

    @Override
    public Vector2D getDrawPoint() {
        return this.drawPoint;
    }
    
}
