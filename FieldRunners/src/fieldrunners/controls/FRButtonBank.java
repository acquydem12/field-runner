/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.controls;

import java.awt.Rectangle;
import tbgameframework.control.IButton;
import tbgameframework.control.buttons.IButtonInfo;
import tbgameframework.math.Vector2D;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.sprite.fontsprite.FontSprite;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author MrKupi
 */
public class FRButtonBank {
    
    final private Vector2D fontPosition = new Vector2D(58.0, 8.0);
    static private FRButtonBank inst;
    
    private FRButtonBank(){
        
    }
    
    static public FRButtonBank getInst(){
        if(inst == null){
            inst = new FRButtonBank();
        }
        return inst;
    }
    
    public IButton CreateProduct(Object obj, IButtonInfo buttonInfo, Vector2D position){
        
        IButton button = null;
        
        ImageSprite spr = null;
        if(!"".equals(buttonInfo.getImageName())){
            spr = (ImageSprite)SpriteManager.getInstance().getSprite(buttonInfo.getImageName());
            spr.setIstransformable(!buttonInfo.getIsUntransfom());
        }

        ImageSprite sprOver = null;
        if(!"".equals(buttonInfo.getImageNameOver())){
            sprOver = (ImageSprite)SpriteManager.getInstance().getSprite(buttonInfo.getImageNameOver());
            sprOver.setIstransformable(!buttonInfo.getIsUntransfom());
        }
        
        ImageSprite sprDis = null;
        if(!"".equals(buttonInfo.getImageNameDisabled())){
            sprDis = (ImageSprite)SpriteManager.getInstance().getSprite(buttonInfo.getImageNameDisabled());
            sprDis.setIstransformable(!buttonInfo.getIsUntransfom());
        }
        
        FontSprite font = null;
        if(!"".equals(buttonInfo.getFontName()))
        {
            font = (FontSprite)SpriteManager.getInstance().getSprite(buttonInfo.getFontName());
            font.setIstransformable(!buttonInfo.getIsUntransfom());
        }
        
        try{
            switch(buttonInfo.getType())
            {
            case "Rectangle":
                Rectangle rect = new Rectangle((int)position.x, (int)position.y, buttonInfo.getWidth(), buttonInfo.getHeight());
                
                button = new RectangleButton(rect, spr, sprOver, buttonInfo.getIsUntransfom());
                if(sprDis != null){
                    ((RectangleButton)button).setDisableImage(sprDis);
                }
                if(font != null){
                    ((RectangleButton)button).setFont(font, fontPosition);
                }
                break;
            }
            
            if(!"".equals(buttonInfo.getClickMethod())){
                button.Click(obj, buttonInfo.getClickMethod());
            }
            if(!"".equals(buttonInfo.getOverMethod())){
                button.Over(obj, buttonInfo.getOverMethod());
            }
            if(!"".equals(buttonInfo.getReleaseMethod())){
                button.Release(obj, buttonInfo.getReleaseMethod());
            }
            button.setName(buttonInfo.getName());
        
        }catch(Exception ex){
            System.out.println("Error button info - " + ex);
            return null;
        }
        
        return button;
    }
}
