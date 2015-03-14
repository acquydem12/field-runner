/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.effect;

/**
 *
 * @author MrKupi
 */
public class SwellEffect extends SpriteEffectImpl{

    private float rate = 0.1f;
    
    public SwellEffect(boolean isLoop){
        super(isLoop);
    }
    
    @Override
    public void Update()
    {
        if(isEnable)
        {
            this.currentScale.x += rate;
            this.currentScale.y += rate;
            if(this.currentScale.x >= this.endScale.x || this.currentScale.y >= this.endScale.y){
                this.currentScale.x = this.currentScale.x > this.endScale.x ? this.endScale.x : currentScale.x;
                this.currentScale.y = this.currentScale.y > this.endScale.y ? this.endScale.y : currentScale.y;
                if(isLoop){
                    setToDefault();
                }
                else{
                    setToDefault();
                    this.Stop();
                }
            }
        }
    }
    
    public void setRate(float rate){
        this.rate = rate;
    }
    
    protected void setToDefault(){
        this.currentScale.x = startScale.x;
        this.currentScale.y = startScale.y;
    }
}
