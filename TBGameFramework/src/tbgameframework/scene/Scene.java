/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.scene;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import tbgameframework.ComparerGameObject;
import tbgameframework.GameObject;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;
import tbgameframework.collision.CObjectToQTNodeAdapter;
import tbgameframework.collision.ICollisionObject;
import tbgameframework.collision.QuadTree;
import tbgameframework.collision.QuadTreeNode;
import tbgameframework.math.Collision;
import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public abstract class Scene
    implements IUpdateable, IDrawable, Iterable<GameObject>
{
    
    protected SceneManager sceneManager;
    protected ArrayList<GameObject> components = new ArrayList();
    protected QuadTree quadtree = new QuadTree(new Rectangle(0, 0, 0, 0));
    protected boolean enabled = false;
    protected boolean visibled = false;
    
    private String name;
    
    public Scene(SceneManager sceneMan, String name)
    {
        this.sceneManager = sceneMan;
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void CreateGameQuadTree(Rectangle rect)
    {
        this.quadtree = new QuadTree(rect);
    }
    
    public abstract void Start();
    
    public abstract void Reset();
    
    public void Show(){
        this.enabled = true;
        this.visibled = true;
    }
    
    public void Hide(){
        this.enabled = false;
        this.visibled = false;
    }
    
    public void Pause()
    {
        this.enabled = false;
        this.visibled = true;
        for(int i = 0; i < this.components.size(); ++i){
            this.components.get(i).pause();
        }
    }
    
    public void Resume()
    {
        this.enabled = false;
        this.visibled = true;
        for(int i = 0; i < this.components.size(); ++i){
            this.components.get(i).resume();
        }
    }
    
    public boolean addComponent(Object o){
        // Add component and Add to Collision Manager
        try{
            ICollisionObject co = (ICollisionObject)o;
            if(co != null){
                quadtree.addNode(CObjectToQTNodeAdapter.converting(co));
            }
        }catch(Exception e){
        }
        try{
            components.add((GameObject)o);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    public boolean removeComponent(Object o){
        try{
            if(components.contains((GameObject)o)){
                // Remove from Collision Manager
                try{
                    ICollisionObject co = (ICollisionObject)o;
                    if(co != null){
                        quadtree.removeNode(CObjectToQTNodeAdapter.converting(co));
                    }
                }
                catch(Exception e){
                }
                components.remove((GameObject)o);
                return true;
            }
        }
        catch(Exception e)
        {
        }
        
        return false;
    }
    
    @Override
    public void Update(){
        if(this.enabled)
        {
            synchronized(this.components)
            {
                // Update All game components
                for(int i = 0; i < this.components.size(); ++i)
                {
                    this.components.get(i).Update();
                }

                // For quadtree, write for yourself
                // Clean quadtree
                this.quadtree.clean();
                // Re-add to quadtree
                for(int i = 0; i < this.components.size(); ++i)
                {
                    if (this.components.get(i) instanceof ICollisionObject)
                    {
                        ICollisionObject co = (ICollisionObject)this.components.get(i);
                        if(co != null){
                            quadtree.addNode(CObjectToQTNodeAdapter.converting(co));
                        }
                    }
                }
                //<editor-fold defaultstate="collapsed" desc="Collision Detect">
                // Collision detected
                for(int i = 0; i < this.components.size(); ++i)
                {
                    try
                    {
                        ICollisionObject co = (ICollisionObject)this.components.get(i);
                        QuadTreeNode qtnode = CObjectToQTNodeAdapter.converting(co);
                        ArrayList<QuadTreeNode> nodes;
                        try
                        {
                            nodes = this.quadtree.getNodeRelative(qtnode);
                        }catch(Exception e){
                            nodes = null;
                        }
                        if(nodes != null && nodes.size() >= 2)
                        {
                            for(int j = 0; j < nodes.size(); ++j)
                            {
                                Vector2D hitDir = new Vector2D();
                                if(qtnode.getNode() != nodes.get(j).getNode() && co.isCollidable(nodes.get(j).getNode()) &&
                                        Collision.IsIntersect(qtnode.getBound(), nodes.get(j).getBound()))
                                {
                                    co.collisionDo(nodes.get(j).getNode(), hitDir);
                                }
                            }
                        }
                    }
                    catch(Exception ex){
                    }
                }
                //</editor-fold>
                this.components.notifyAll();
            }
        }
    }
    
    @Override
    synchronized public void Draw(Graphics2D g2D)
    {
        if(visibled)
        {
            synchronized(this.components)
            {
                Collections.sort(components, new ComparerGameObject());
                for(int i = 0; i < components.size(); ++i)
                {
                    components.get(i).Draw(g2D);
                }
                this.components.notifyAll();
            }
        }
    }
    
    @Override
    public boolean isEnabled(){
        return this.enabled;
    }
    
    @Override
    public boolean isVisibled() {
        return this.visibled;
    }
    @Override
    public void setVisibled(boolean isVs) {
        this.visibled = isVs;
    }
    @Override
    public void setEnabled(boolean isEn) {
    }
    
    @Override
    public float getDepth() {
        return 1.0F;
    }

    @Override
    public void setDepth(float depth)
    {
    }
    
    @Override
    public Iterator<GameObject> iterator()
    {
        return this.components.iterator();
    }
    
    public SceneManager getSM()
    {
        return this.sceneManager;
    }
}
