/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map;

import fieldrunners.map.round.RoundData;
import java.awt.Point;
import java.util.Collection;
import tbgameframework.math.Vector2D;

/**
 *
 * @author Uchiha Salm
 */
public interface FRMapData
{
    String getName();
    String getMapBackground();
    int getPlayMode();
    Point getGridSize();
    Point getCellSize();
    Point getTotalMapSize();
    Vector2D getMapBeginPosition();
    Point getStart();
    Point getGoal();
    
    Collection<Point> getLockedPositions();
    
    Collection<RoundData> getRoundDatas();
    
    static final String tagBackground = "Background";
    static final String tagPlayMode = "PlayMode";
    static final String tagBeginPosition = "BeginPosition";
    static final String tagStart = "Start";
    static final String tagGoal = "Goal";
    static final String tagSize = "GridSize";
    static final String tagTotalSize = "TotalSize";
    static final String tagCellSize = "CellSize";
    static final String tagLockedPosition = "LockedCell";
    static final String tagName = "Name";
    static final String tagRound = "Round";
}
