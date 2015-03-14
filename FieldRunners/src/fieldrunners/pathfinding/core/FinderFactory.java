/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.core;

/**
 *
 * @author Uchiha Salm
 */
public interface FinderFactory
{
    Node getStart();
    Node getGoal();
    
    PathFinder getPathFinder();
    
    PathBuilder getPathBuilder();
    NeibourghsFactory getNeibourghsFactory();
    
    MapState createMapState(Object initValue);
}