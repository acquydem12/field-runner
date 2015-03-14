/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.collision;

/**
 *
 * @author MrKupi
 */
public class CObjectToQTNodeAdapter {
    
    static public QuadTreeNode converting(ICollisionObject o){
        return new QuadTreeNode(o);
    }
}
