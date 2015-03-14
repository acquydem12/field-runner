/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control;

import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteDrawObject;

/**
 *
 * @author MrKupi
 */
public class ImageDrawObject implements SpriteDrawObject{

    private Vector2D position;
    private Vector2D scale;
    private float angle = 0.0f;
    
    /*
     * Object hold all sprite draw info of button
     */
    public ImageDrawObject(SpriteDrawObject drawObj){
        this.position = drawObj.getPosition();
        this.scale = drawObj.getScale();
        this.angle = drawObj.getAngle();
    }
    
    public ImageDrawObject(Vector2D position, Vector2D scale, float angle){
        this.position = position;
        this.scale = scale;
        this.angle = angle;
    }
    
    @Override
    public Vector2D getPosition() {
        return this.position;
    }

    public void setPosition(Vector2D position){
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    @Override
    public Vector2D getScale() {
        return this.scale;
    }

    public void setScale(Vector2D scale){
        this.scale.x = scale.x;
        this.scale.y = scale.y;
    }
    
    @Override
    public float getAngle() {
        return this.angle;
    }
    
    public void setAngle(float angle){
        this.angle = angle;
    }
}
