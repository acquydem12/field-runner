/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite.fontsprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Map;
import tbgameframework.Board;
import tbgameframework.Camera;
import tbgameframework.VisibleObject;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.SpriteDrawObject;

/**
 *
 * @author MrKupi
 */
public class FontSprite
    implements ISprite
{
    // Reference to camera
    public Camera camera = Board.getInstance().camera;
    
    protected VisibleObject visibleComponent = new VisibleObject();
    protected FontDrawObject drawObject = null;
    
    private Image image;
    
    protected Vector2D size;
    protected Vector2D totalSize = new Vector2D(0.0, 0.0);
    protected Vector2D drawPos;
    protected boolean isTransformable = true;
    
    protected float distance;
    
    protected char[] words;
    protected String text;
    protected Map<Character, Rectangle> fontInfo;
    
    public FontSprite(Image image, Map<Character, Rectangle> fontInfo)
    {
        this.image = image;
        this.fontInfo = fontInfo;
        this.drawPos = new Vector2D();
        this.distance = 0;
    }
    
    public FontSprite(FontSprite fontSpr)
    {
        this.image = fontSpr.image;
        this.fontInfo = fontSpr.fontInfo;
        this.drawPos = new Vector2D();
        this.distance = 0;
    }
    
    @Override
    public void Draw(Graphics2D g2D)
    {
        this.Draw(g2D, this.drawObject.getText(), this.drawObject.getPosition(), this.drawObject.getScale(), this.drawObject.getAngle());
    }
    
    public void Draw(Graphics2D g2D, String text, Vector2D position, float angle)
    {
        if(this.isVisibled() && text != null && !"".equals(text))
        {    
            this.text = text;
            // Get camera transform if object transformable
            Vector2D translate = new Vector2D();
            double scaling = 1.0;
            if(isTransformable)
            {
                translate = camera.getCameraTranslation();
                scaling = camera.getCameraScaling();
            }
            
            // transform with camera
            g2D.scale(scaling, scaling);
            g2D.translate( translate.x, translate.y );

            // Get array of word
            this.words = text.toCharArray();
            
            // Get object position
            Vector2D pos = new Vector2D(position);
            for(int i = 0; i < words.length; ++i)
            {
                // Get character position
                Rectangle frame = fontInfo.get(Character.valueOf(words[i]));
                
                // Make default size for non size
                if(size == null){
                    size = new Vector2D(frame.width, frame.height);
                }
                
                g2D.translate(pos.x - drawPos.x, pos.y + this.size.y - drawPos.y);
                g2D.scale(1, -1);
                g2D.rotate(angle);
            
                // draw image on screen
                g2D.drawImage(this.image, 0, 0, (int)this.size.x, (int)this.size.y, 
                            (int)frame.x, (int)frame.y, 
                            (int)frame.x + (int)frame.width, (int)frame.y + (int)frame.height, null);
                
                // rewind
                g2D.rotate(-angle);
                g2D.scale(1, -1);
                g2D.translate(- pos.x + drawPos.x, - pos.y - this.size.y + drawPos.y);

                pos.x += (distance != 0) ? this.distance : this.size.x;
            }
            g2D.translate( -translate.x, -translate.y);
            g2D.scale(1/scaling, 1/scaling);
        }
    }
    
    public void Draw(Graphics2D g2D, String text, Vector2D position, Vector2D scale, float angle)
    {
        if(this.isVisibled() && text != null && !"".equals(text))
        {
            this.text = text;
            // Get camera transform if object transformable
            Vector2D translate = new Vector2D();
            double scaling = 1.0;
            if(isTransformable)
            {
                translate = camera.getCameraTranslation();
                scaling = camera.getCameraScaling();
            }
            
            // transform with camera
            g2D.scale(scaling, scaling);
            g2D.translate( translate.x, translate.y);

            // Get array of word
            this.words = text.toCharArray();
            
            // Get object position
            Vector2D pos = new Vector2D(position);
            // Temp object calc all size of text
            Vector2D textSize = new Vector2D();
            for(int i = 0; i < words.length; ++i)
            {
                // Get character position
                Rectangle frame = fontInfo.get(Character.valueOf(words[i]));
                
                //  Make default size for non size
                if(size == null){
                    size = new Vector2D(frame.width, frame.height);
                }
                
                Vector2D scaleSize = new Vector2D(this.size.x*scale.x, this.size.y*scale.y);
                // Update total Size of text
                textSize.x += scaleSize.x;
                textSize.y = textSize.y > scaleSize.y ? textSize.y : scaleSize.y;
            
                g2D.translate(pos.x - drawPos.x, pos.y + scaleSize.y - drawPos.y);
                g2D.scale(1, -1);
                g2D.rotate(angle);
            
                // draw on screen
                g2D.drawImage(this.image, 0, 0, (int)scaleSize.x, (int)scaleSize.y, 
                            (int)frame.x, (int)frame.y, 
                            (int)frame.x + (int)frame.width, (int)frame.y + (int)frame.height, null);

                // rewind
                g2D.rotate(-angle);
                g2D.scale(1, -1);
                g2D.translate(- pos.x + drawPos.x, - pos.y - scaleSize.y + drawPos.y);

                // calculate next character position
                pos.x += (distance != 0) ? this.distance : scaleSize.x;
            }
            g2D.translate( -translate.x, -translate.y);
            g2D.scale(1/scaling, 1/scaling);
            
            // Update total text size;
            this.totalSize = textSize;
        }
    }
       
    public void setDistance(float dx)
    {
        this.distance = dx;
    }
    
    public void setSize(Vector2D nsize)
    {
        this.size.x = (int)nsize.x;
        this.size.y = (int)nsize.y;
    }
    
    public Vector2D getTextSize()
    {
        return this.totalSize;
    }
    
    @Override
    public boolean isVisibled()
    {
        return this.visibleComponent.isVisibled();
    }
    @Override
    public void setVisibled(boolean isVs)
    {
        this.visibleComponent.setVisibled(isVs);
    }
    
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

    public void setText(String text)
    {
        this.text = text;
        words = text.toUpperCase().toCharArray();
        if(drawObject != null)
        {
            this.drawObject.setText(text);
        }
    }
    
    public String getText(){
        return this.text;
    }

    @Override
    public void setDrawObject(SpriteDrawObject sdObj)
    {
        if (sdObj instanceof FontDrawObject)
        {
            this.drawObject = (FontDrawObject) sdObj;
        }
    }

    @Override
    public SpriteDrawObject getDrawObject() {
        return this.drawObject;
    }

    public void setIstransformable(boolean istrans)
    {
        this.isTransformable = istrans;
    }
    
    @Override
    public FontSprite clone()
    {
        FontSprite fontSprite = new FontSprite(this.image, this.fontInfo);
        return fontSprite;
    }

    @Override
    public float getDepth()
    {
        return this.visibleComponent.getDepth();
    }

    @Override
    public void setDepth(float depth)
    {
        visibleComponent.setDepth(depth);
    }

    @Override
    public Vector2D getDrawPoint() {
        return this.drawPos;
    }
}
