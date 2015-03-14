/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteDrawObject;

/**
 *
 * @author MrKupi
 */
public class FontDrawObjectImp implements FontDrawObject{

    private Vector2D position;
    private Vector2D scale;
    private float angle;
    private String text;
    
    /*
     * Object hold all sprite draw info of button
     */
    public FontDrawObjectImp(String text, SpriteDrawObject drawObj){
        this.position = drawObj.getPosition();
        this.scale = drawObj.getScale();
        this.angle = drawObj.getAngle();
        this.text = text;
    }
    
    public FontDrawObjectImp(String text, Vector2D position, Vector2D scale, float angle)
    {
        this.position = position;
        this.scale = scale;
        this.angle = angle;
        this.text = text;
    }
    
    @Override
    public Vector2D getPosition() {
        return this.position;
    }
    
    @Override
    public void setPosition(Vector2D position){
        this.position = position;
    }

    @Override
    public Vector2D getScale() {
        return this.scale;
    }

    @Override
    public float getAngle() {
        return this.angle;
    }
    
    @Override
    public String getText() {
        return this.text;
    }
    
    @Override
    public void setText(String text) {
        this.text = text;
    }
}
