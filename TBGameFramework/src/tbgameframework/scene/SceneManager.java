/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.scene;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;

/**
 *
 * @author MrKupi
 */
public abstract class SceneManager implements IUpdateable, IDrawable{
    
    private Map<String, Scene> manager = new HashMap();
    static SceneManager instance;
    
    public SceneManager(){
    }
   
    @Override
    public void Update()
    {
        Map<String, Scene> copy = new HashMap(this.manager);
        for(Map.Entry<String, Scene> element : copy.entrySet()){
            if(element.getValue().isEnabled()){
                element.getValue().Update();
            }
        }
    }
    
    @Override
    public void Draw(Graphics2D g2D)
    {
        Map<String, Scene> copy = new HashMap(this.manager);
        for(Map.Entry<String, Scene> element : copy.entrySet()){
            if(element.getValue().isVisibled()){
                element.getValue().Draw(g2D);
            }
        }
    }
    
    public boolean addScene(Scene scene)
    {
        if(manager.put(scene.getName(), scene) != null)
        {
            return true;
        }
        return false;
    }
    
    public boolean removeScene(String sceneName){
        
        if(manager.remove(sceneName) != null){
            return true;
        }
        return false;
    }
    
    public boolean removeScene(Scene scene)
    {
        if(manager.remove(scene.getName()) != null)
        {
            return true;
        }
        return false;
    }
    
    public Scene getScene(String sceneName){
        return manager.get(sceneName);
    }
    
    @Override
    public boolean isVisibled() {
        return false;
    }

    @Override
    public void setVisibled(boolean isVs) {
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean isEn) {
    }
    
    
    @Override
    public float getDepth() {
        return 0.0F;
    }

    @Override
    public void setDepth(float depth) {
    }
}
