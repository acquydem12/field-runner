/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.collision;

import java.awt.Rectangle;

/**
 *
 * @author MrKupi
 */
public class QuadTreeNode {
    
    protected QuadTree parent;
    protected ICollisionObject node;
    
    public QuadTreeNode(ICollisionObject object){
        this.node = object;
    }
    
    public ICollisionObject getNode(){
        return node;
    }
    
    public Rectangle getBound(){
        return node.getBound();
    }
    
    public void setParent(QuadTree cparent){
        this.parent = cparent;
    }
    
    public void remove(){
        parent = null;
    }
}
