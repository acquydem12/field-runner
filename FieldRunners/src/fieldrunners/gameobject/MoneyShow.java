/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobject;

import fieldrunners.gameobjects.ICompleteable;
import java.awt.Graphics2D;
import tbgameframework.GameObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.fontsprite.FontSprite;

/**
 *
 * @author MrKupi
 */
public class MoneyShow implements GameObject, ICompleteable{

    final static int DELAY = 50;
    protected boolean isEnable = true;
    protected boolean isVisible = true;
    protected boolean isCompleted = false;
    protected float depth = 1.0f;
    
    protected double movement = 0.1;
    protected int delay = DELAY;
    
    protected Vector2D start;
    
    protected Vector2D position;
    protected String text;
    
    protected FontSprite messageFont;
    
    public MoneyShow(String message, FontSprite fontSprite, Vector2D startPos)
    {
        this.messageFont = fontSprite;
        fontSprite.setText(message);
        this.text = message;
        this.position = new Vector2D(startPos);
    }

    @Override
    public void Update() {
        if (isEnable){
            this.position.y += movement;
            --delay;
            if(delay <= 0){
                this.isCompleted = true;
            }
        }
    }

    @Override
    public void Draw(Graphics2D g2D) {
        if(isVisible){
            this.messageFont.Draw(g2D, text, position, 0.0f);
        }
    }

    public void start(){
    }
    
    @Override
    public void resume() {
    }

    @Override
    public void pause() {
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
    
    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }
}
