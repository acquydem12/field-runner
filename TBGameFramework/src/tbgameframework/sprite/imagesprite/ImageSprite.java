/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.imagesprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import tbgameframework.Board;
import tbgameframework.Camera;
import tbgameframework.EnableObject;
import tbgameframework.GameTime;
import tbgameframework.VisibleObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.SpriteDrawObject;

/**
 * Store an image and draw it on screen
 * @author MrKupi
 */
public class ImageSprite
    implements ISprite
{
    // Reference to camera
    public Camera camera = Board.getInstance().camera;
    
    protected VisibleObject visibleComponent = new VisibleObject();
    protected EnableObject enableComponent = new EnableObject();
    
    private Image image;
    
    protected int currentFrameNumber;
    protected ISpriteInfo listImageInfo;
    
    protected Vector2D size;
    protected Rectangle sourceRect;
    protected Vector2D drawPos;
    protected boolean isTransformable = true;
    
    protected long delayTime; // in millisecond
    protected long lastTime;  // in millisecond
    protected long currentTime;  // in millisecond
    
    protected SpriteDrawObject drawObject = null;
    protected boolean loopable = true;
    
    /*
     * Init Image sprite with an image loaded and image info: rectangle list
     */
    public ImageSprite(Image cimage, ISpriteInfo imageInfo)
    {
        this.image = cimage;

        this.listImageInfo = imageInfo;
        Rectangle rect = imageInfo.getFrame().get(0);
        this.sourceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
        this.size = new Vector2D(rect.width, rect.height);

        this.drawPos = new Vector2D();

        this.lastTime = GameTime.getInstance().CurrentTimeInMillisecond();
        this.delayTime = 100;
    }
    
    public ImageSprite(ImageSprite imageSprite){
        this.image = imageSprite.image;

        this.listImageInfo = imageSprite.listImageInfo;
        Rectangle rect = imageSprite.listImageInfo.getFrame().get(0);
        this.sourceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
        this.size = new Vector2D(rect.width, rect.height);

        this.drawPos = new Vector2D();

        this.lastTime = GameTime.getInstance().CurrentTimeInMillisecond();
        this.delayTime = 100;
    }
    
    /*
     * Call to next frame let an image active animation
     */
    public void NextFrame()
    {
        if(!this.loopable)
        {
            if(currentFrameNumber + 1 >= listImageInfo.getFrame().size())
            {
                this.enableComponent.setEnabled(false);
            }
        }
        
        if(this.enableComponent.isEnabled())
        {    
            this.currentTime = GameTime.getInstance().CurrentTimeInMillisecond();
            
            if((this.currentTime - this.lastTime) >= this.delayTime)
            {
                // Update lastTime
                this.lastTime = this.currentTime;
                
                if(currentFrameNumber + 1 < listImageInfo.getFrame().size())
                {
                    ++currentFrameNumber;
                }
                else 
                {
                    currentFrameNumber = 0;
                }
                
                Rectangle info = listImageInfo.getFrame().get(this.currentFrameNumber);
                this.sourceRect.x = info.x;
                this.sourceRect.y = info.y;
                this.sourceRect.width = info.width;
                this.sourceRect.height = info.height;
                
                if(this.size != null)
                {
                    this.size.x = info.width;
                    this.size.y = info.height;
                }
            }
        }
    }
    
    /*
     * Implements
     */
    @Override
    public void Draw(Graphics2D g2D)
    {
        this.Draw(g2D, drawObject.getPosition(), drawObject.getAngle(), drawObject.getScale());
    }
    
    /*
     * Draw sprite with some parameter
     */
    public void Draw(Graphics2D g2D, Vector2D position, float angle)
    {
        this.NextFrame();
        if(this.isVisibled())
        {
            Vector2D translate = new Vector2D();
            double scaling = 1.0;
            
            // Get camera transform if object transformable
            if(isTransformable)
            {
                translate = camera.getCameraTranslation();
                scaling = camera.getCameraScaling();
            }
            
            g2D.scale(scaling, scaling);
            g2D.translate( translate.x, translate.y );
            
            g2D.translate(position.x - drawPos.x, position.y + this.size.y - drawPos.y);
            g2D.scale(1, -1);
            g2D.rotate(angle);
            
            g2D.drawImage(this.image, 0, 0, (int)this.size.x, (int)this.size.y, this.sourceRect.x, this.sourceRect.y, 
                        this.sourceRect.x + this.sourceRect.width, this.sourceRect.y + this.sourceRect.height, null);
            
            g2D.rotate(-angle);
            g2D.scale(1, -1);
            g2D.translate(- position.x + drawPos.x, - position.y - this.size.y + drawPos.y);
            
            g2D.translate( -translate.x, -translate.y);
            g2D.scale(1/scaling, 1/scaling);
        }
    }
    
    /*
     * Draw sprite with some parameter
     */
    public void Draw(Graphics2D g2D, Vector2D position, float angle, Vector2D scale)
    {
        this.NextFrame();
        if(this.isVisibled())
        {
            Vector2D translate = new Vector2D();
            double scaling = 1.0;
            
            // Get camera transform if object transformable
            if(isTransformable)
            {
                translate = camera.getCameraTranslation();
                scaling = camera.getCameraScaling();
            }
            
            Vector2D scaleSize = new Vector2D(this.size);
            scaleSize.x *= scale.x;
            scaleSize.y *= scale.y;
            
            g2D.scale(scaling, scaling);
            g2D.translate( translate.x, translate.y);
            
            g2D.translate(position.x - drawPos.x, position.y + scaleSize.y - drawPos.y);
            g2D.scale(1, -1);
            g2D.rotate(angle);
            
            g2D.drawImage(this.image, 0, 0, (int)scaleSize.x, (int)scaleSize.y, this.sourceRect.x, this.sourceRect.y, 
                        this.sourceRect.x + this.sourceRect.width, this.sourceRect.y + this.sourceRect.height, null);
            
            g2D.rotate(-angle);
            g2D.scale(1, -1);
            g2D.translate(- position.x + drawPos.x, - position.y - scaleSize.y + drawPos.y);
            
            g2D.translate( -translate.x, -translate.y);
            g2D.scale(1/scaling, 1/scaling);
        }
    }
    
    /*
     * Allow get image size width
     */
    public int getWidth()
    {
        return listImageInfo.getFrame().get(this.currentFrameNumber).width;
    }
    
    /*
     * Allow get image size height
     */
    public int getHeight()
    {
        return listImageInfo.getFrame().get(this.currentFrameNumber).height;
    }
    
    /*
     * Allow set delay for animation sprite, in millisecond
     */
    public void setDelay(int delay)
    {
        this.delayTime = delay;
    }
    
    /*
     * Allow set Size of sprite draw on screen
     */
    public void setSize(Vector2D nsize)
    {
        this.size.x = (int)nsize.x;
        this.size.y = (int)nsize.y;
    }
    
    /*
     * Return current image's size
     */
    public Vector2D getSize()
    {
        return this.size;
    }
    
    /*
     * Return current frame's number
     */
    public int getFrameNumber()
    {
        return currentFrameNumber;
    }
    
    /*
     * Allow set sprite loop or not
     */
    public void setLoopable(boolean isLoop)
    {
        this.loopable = isLoop;
    }
    
    /*
     * Reset sprite to begin animation
     */
    public void Reset()
    {
        this.currentFrameNumber = 0;
    }
    
    /*
     * Set draw point inside sprite
     */
    @Override
    public void setDrawPoint(Vector2D drawPoint)
    {
        this.drawPos.x = drawPoint.x;
        this.drawPos.y = drawPoint.y;
        if(this.drawPos.x < 0){
            this.drawPos.x = 0;
        }
        else if(this.drawPos.x > size.x){
            this.drawPos.x = size.x;
        }
        
        if(this.drawPos.y < 0){
            this.drawPos.y = 0;
        }
        else if(this.drawPos.y > size.y){
            this.drawPos.y = size.y;
        }
    }
    
    /*
     * Set sprite with type transform or not
     */
    public void setIstransformable(boolean istrans)
    {
        this.isTransformable = istrans;
    }
    
    @Override
    public ImageSprite clone()
    {
        ImageSprite imageSprite = new ImageSprite(this.image, this.listImageInfo);
        return imageSprite;
    }

    @Override
    public boolean isVisibled()
    {
        return visibleComponent.isVisibled();
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        this.visibleComponent.setVisibled(isVs);
    }

    @Override
    public float getDepth()
    {
        return this.visibleComponent.getDepth();
    }

    @Override
    public void setDepth(float depth)
    {
        this.visibleComponent.setDepth(depth);
    }

    @Override
    public void setDrawObject(SpriteDrawObject sdObj)
    {
        this.drawObject = sdObj;
    }
    
    @Override
    public SpriteDrawObject getDrawObject() {
        return this.drawObject;
    }

    @Override
    public Vector2D getDrawPoint() {
        return this.drawPos;
    }
}
