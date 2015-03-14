/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.core;

import java.util.Iterator;

/**
 *
 * @author Uchiha Salm
 */
public interface Path
    extends Iterable<Node>
{
    Node getStart();
    Node getGoal();
    
    boolean isEmpty();
    
    Iterator<Node> getIteratorOf(Node node);
}
