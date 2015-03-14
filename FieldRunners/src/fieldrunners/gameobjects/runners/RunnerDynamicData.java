/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners;

import fieldrunners.gameLogic.GameScore;
import fieldrunners.map.frgrid.Grid2D;
import java.awt.Point;
import tbgameframework.math.Vector2D;
import tbgameframework.scene.Scene;

/**
 *
 * @author Uchiha Salm
 */
public interface RunnerDynamicData
{
    Vector2D startPosition();
    Grid2D Grid();
    Point goal();
    GameScore gameScore();
    Scene scene();
}