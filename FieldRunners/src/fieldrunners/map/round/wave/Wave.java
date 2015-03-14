/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.round.wave;

import fieldrunners.gameobjects.ICompleteable;
import tbgameframework.IUpdateable;
import tbgameframework.scene.Scene;

/**
 *
 * @author Uchiha Salm
 */
public interface Wave
    extends ICompleteable, IUpdateable
{
    Scene getScene();
    boolean isEnd();
    
    String getRunnerName();
}
