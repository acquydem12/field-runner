/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.pathfinding.astar.map2d;

import fieldrunners.pathfinding.core.MapState;
import fieldrunners.pathfinding.core.Node;

/**
 *
 * @author Uchiha Salm
 */
public class MapState2D
    implements MapState
{
    public MapState2D(int nColumns, int nRows, Object initValue)
    {
        mapState = new Object[nColumns][nRows];
        
        for (int i = 0; i < mapState.length; i++) 
        {
            for (int j = 0; j < mapState[i].length; ++j)
            {
                mapState[i][j] = initValue;
            }
        }
    }
    
    @Override
    public Object getState(Node node)
    {
        PointNode point = (PointNode) node;
        if (point != null)
        {
            return mapState[point.getCoordinate().x][point.getCoordinate().y];
        }
        
        return null;
    }

    @Override
    public void setState(Node node, Object state) 
    {
        PointNode point = (PointNode) node;
        if (point != null)
        {
            mapState[point.getCoordinate().x][point.getCoordinate().y] = state;
        }
    }
    
    private Object[][] mapState;
}
