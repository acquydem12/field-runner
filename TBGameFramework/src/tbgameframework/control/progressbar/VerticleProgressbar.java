/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control.progressbar;

import java.awt.Graphics2D;
import tbgameframework.control.ImageDrawObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.imagesprite.ImageSprite;


public class VerticleProgressbar implements IProgressbar {

    protected boolean isEnable;
    protected boolean isVisible;
    protected float depth = 1.0f;
    
    protected ImageSprite bar;
    protected Vector2D position;
    protected Vector2D scale = new Vector2D(1.0, 1.0);
    
    public VerticleProgressbar(ImageSprite bar, Vector2D position){
        this.bar = bar;
        this.position = position;
        
        bar.setDrawObject(new ImageDrawObject(this.position, scale, 0.0f));
        bar.setIstransformable(false);
    }
    
    @Override
    public void Update() {
    }

    @Override
    public void Draw(Graphics2D g2D) {
        bar.Draw(g2D);
    }
    
    @Override
    public void setDefault(float percent) {
        this.scale.x = percent;
    }

    @Override
    public void increase(float percent) {
        this.scale.x += percent;
        if(scale.x >= 1.0){
            this.scale.x = 1.0;
        }
    }

    @Override
    public void decrease(float percent) {
        this.scale.x -= percent;
        if(scale.x <= 0.0){
            this.scale.x = 0.0;
        }
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }

    @Override
    public void setEnabled(boolean isEn) {
        this.isEnable = isEn;
    }

    @Override
    public boolean isVisibled() {
        return this.isVisible;
    }

    @Override
    public void setVisibled(boolean isVs) {
        this.isVisible = isVs;
    }

    @Override
    public float getDepth() {
        return this.depth;
    }

    @Override
    public void setDepth(float depth) {
        this.depth = depth;
    }
    
}
