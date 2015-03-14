/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.core;

/**
 *
 * @author Uchiha Salm
 */
public interface PathBuilder
{
    void makeEdge(Node n1, Node n2);
    Path createPath(Node start, Node goal);
}