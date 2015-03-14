/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import fieldrunners.gameobjects.runners.RunnerBYS;
import fieldrunners.gameobjects.tower.TowerBYS;
import tbgameframework.scene.Scene;

/**
 *
 * @author Uchiha Salm
 */
public interface MissileDynamicData
{
    TowerBYS getAttacker();
    RunnerBYS getTarget();
    
    Scene getScene();
}
