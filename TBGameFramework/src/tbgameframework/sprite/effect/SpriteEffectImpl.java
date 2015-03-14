/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.effect;

import tbgameframework.IUpdateable;
import tbgameframework.math.Vector2D;


public abstract class SpriteEffectImpl implements ISpriteEffect, IUpdateable {

    protected boolean isLoop = false;
    protected boolean isEndEfftect = false;
    protected Vector2D currentScale = new Vector2D();
    protected Vector2D startScale = new Vector2D();
    protected Vector2D endScale = new Vector2D();
    protected Vector2D defaultScale;
    protected boolean isEnable = true;
    
    public SpriteEffectImpl(boolean isLoop){
        this.isLoop = isLoop;
    }
    
    @Override
    public boolean isEnd(){
        return this.isEndEfftect;
    }
    
    public void Start(){
        isEndEfftect = false;
        this.setEnabled(true);
    }
    
    public void Stop(){
        isEndEfftect = true;
    }
    
    @Override
    public void reset(){
        this.isEndEfftect = false;
        this.setEnabled(true);
    }
    
    @Override
    public void setLoopable(boolean isLoop){
        this.isLoop = isLoop;
    }
    
    @Override
    public boolean getLoopable(){
        return this.isLoop;
    }
    
    @Override
    public void setStartScale(Vector2D start) {
        this.currentScale = start;
        this.startScale = new Vector2D(start);
    }

    @Override
    public void setEndScale(Vector2D end) {
        this.endScale = end;
    }

    @Override
    public Vector2D getStartScale() {
        return this.startScale;
    }

    @Override
    public Vector2D getEndScale() {
        return this.endScale;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }

    @Override
    public void setEnabled(boolean isEn) {
        this.isEnable = isEn;
    }
}
