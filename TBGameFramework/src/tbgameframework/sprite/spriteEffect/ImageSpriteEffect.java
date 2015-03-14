/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.spriteEffect;

import java.util.ArrayList;
import tbgameframework.IUpdateable;
import tbgameframework.sprite.effect.SpriteEffectImpl;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author MrKupi
 */
public class ImageSpriteEffect extends ImageSprite implements IUpdateable{
    
    private boolean isEnable = false;
    private ArrayList<SpriteEffectImpl> effects = new ArrayList<>();
    private SpriteEffectImpl current;
    
    protected int effectNumber = 0;
    protected boolean isStartAll = false;
    
    public ImageSpriteEffect(ImageSprite imageSprite)
    {
        super(imageSprite);
    }

    @Override
    public void Update() {
        if(isEnable){
            // Update current effect
            if(current != null && !current.isEnd()){
                current.Update();
            }
            
            
            // If choice all, update with turn
            if(isStartAll){
                this.UpdateAll();
            }
            else if(current != null && current.isEnd()){
                current.reset();
                this.stopAll();
            }
        }
    }
    
    private void UpdateAll(){
        if(current != null && current.isEnd()){
            current.reset();
            ++this.effectNumber;
            if(this.effectNumber >= effects.size()){
                effectNumber = 0;
                isStartAll = false;
                this.stopAll();
            }
            else {
                current = (SpriteEffectImpl)effects.get(effectNumber);
            }
        }
    }
    
    public void start(int number){
        if(number < effects.size()){
            current = (SpriteEffectImpl)effects.get(number);
            this.setEnabled(true);
        }    
    }
    
    public void startAll(){
        if(!effects.isEmpty())
        {
            this.effectNumber = 0;
            current = (SpriteEffectImpl)effects.get(effectNumber);
            this.isStartAll = true;
            this.setEnabled(true);
        }
        else {
            this.stopAll();
        }
    }
    
    public void stopAll(){
        this.setEnabled(false);
    }
    
    public void AddEffect(SpriteEffectImpl effect){
        effect.setStartScale(this.drawObject.getScale());
        effects.add(effect);
    }
    
    public void RemoveEffect(SpriteEffectImpl effect){
        effects.remove(effect);
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
