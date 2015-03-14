/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.image;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author MrKupi
 */
public class ImageLoader {
    
    private Map<String, Image> imageLibrary = new HashMap();
    public static ImageLoader loader = null;
    
    public static ImageLoader getInstance(){
        if(loader == null){
            loader = new ImageLoader();
        }
        return loader;
    }
    
    public Image Load(String file){
        if(imageLibrary.containsKey(file)){
            return imageLibrary.get(file);
        }
        try{
            Image image;
            ImageIcon icon = new ImageIcon(tbgameframework.Board.getInstance().getClass().getResource(file));
            image = icon.getImage();
            
            imageLibrary.put(file, image);
            
            return image;
            
        }catch(Exception e){
            return null;
        }
    }
}
