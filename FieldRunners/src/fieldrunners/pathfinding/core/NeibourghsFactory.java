/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.core;

import java.util.Vector;

/**
 *
 * @author Uchiha Salm
 */
public interface NeibourghsFactory
{
    Vector<Node> getNeibourghsOf(Node node, FinderFactory fFactory);
}
