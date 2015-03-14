/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import fieldrunners.gameobjects.*;
import fieldrunners.map.frgrid.Grid2D;
import java.awt.Point;
import tbgameframework.IUpdateable;
import tbgameframework.math.Vector2D;

/**
 * Explorer subject (1-1)
 * @author Uchiha Salm
 */
public interface FRExplorer
    extends IUpdateable
{
    // on map space have changed
    public void findNewPath();
    
    void setGPS(GPSObject gpsO);
    GPSObject getGPS();
    
    // get subject state
    public Vector2D getNearestMileStone();
    public Grid2D getGrid2D();
    
    public Point getGoal();
}
