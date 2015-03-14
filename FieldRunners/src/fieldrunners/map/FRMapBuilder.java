/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map;

import fieldrunners.map.round.RoundData;
import java.awt.Point;
import java.util.Collection;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;
import tbgameframework.scene.SceneManager;

/**
 *
 * @author Uchiha Salm
 */
public interface FRMapBuilder
{
    void setName(String name);
    void setMapBackground(String image);
    void setPlayMode(int playMode);
    void setGridSize(Point size);
    void setCellSize(Point size);
    void setTotalMapSize(Point size);
    void setMapBeginPosition(Vector2D beginPosition);
    void setStart(Point startPosition);
    void setGoal(Point goalPosition);
    void setRounds(Collection<RoundData> rnds);
    
    void Lock(Point lockPos);
    void Open(Point openPos);
    
    void setSceneManager(SceneManager sceneManager);
    
    Scene createMap();
}
