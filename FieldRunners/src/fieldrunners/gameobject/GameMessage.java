/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobject;

import java.awt.Graphics2D;
import tbgameframework.GameObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.fontsprite.FontSprite;

/**
 *
 * @author MrKupi
 */
public class GameMessage implements GameObject
{
    
    final static int DELAY = 200;
    protected boolean isEnable = false;
    protected boolean isVisible = false;
    protected float depth = 1.0f;
    
    protected double movement = 8.0;
    protected int delay = DELAY;
    
    protected Vector2D start;
    protected Vector2D end;
    protected Vector2D center;
    
    protected Vector2D position;
    protected Vector2D scale = new Vector2D(1.0, 1.0);
    protected String text;
    
    protected FontSprite message;
    
    public GameMessage(FontSprite font, Vector2D start, Vector2D end){
        this.message = font;
        this.message.setIstransformable(false);
        this.position = new Vector2D(start);
        this.start = start;
        this.end = end;
        this.center = new Vector2D((end.x - start.x - 200)/2, start.y);
    }

    @Override
    public void Update() {
        if(isEnable)
        {
            if(this.position.x >= this.center.x && delay != 0)
            {
                --delay;
            }
            else{
                position.x += movement;
            }
            if(this.position.x >= end.x)
            {
                    this.reset();
            }
        }
    }
    
    @Override
    public void Draw(Graphics2D g2D) {
        if(isVisible){
            if(text != null){
                this.message.Draw(g2D, text, position, scale, 0.0f);
            }
        }
    }

    public void setText(String text){
        this.text = text;
    }
    
    public void start(){
        this.reset();
        
        this.isEnable = true;
        this.isVisible = true;
    }
    
    public void reset(){
        this.isEnable = false;
        this.isVisible = false;
        
        this.delay = DELAY;
        this.position.x = this.start.x;
        this.position.y = this.start.y;
    }
    
    @Override
    public void resume() {
        this.setEnabled(true);
    }

    @Override
    public void pause() {
        this.setEnabled(false);
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
