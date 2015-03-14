/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Uchiha Salm
 */
public class LinkedPath
    implements Path
{
    public LinkedPath(Collection<Node> pathCollection)
    {
        this.path = new LinkedList(pathCollection);
    }
    
    @Override
    public Node getStart()
    {
        return path.getFirst();
    }

    @Override
    public Iterator<Node> getIteratorOf(Node node)
    {
        return path.listIterator(path.indexOf(node));
    }
    
    private LinkedList<Node> path;

    @Override
    public Node getGoal() {
        return path.getLast();
    }

    @Override
    public boolean isEmpty() {
        return path.isEmpty();
    }

    @Override
    public Iterator<Node> iterator() {
        return path.iterator();
    }
}
