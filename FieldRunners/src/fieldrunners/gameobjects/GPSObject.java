/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects;

import fieldrunners.gameobjects.frimp.FRExplorer;
import tbgameframework.math.Vector2D;

/**
 * an explorer observer
 * @author Uchiha Salm
 */
public interface GPSObject
{
    Vector2D getPosition();
    Vector2D getDirection();
    
    void pathChanged(FRExplorer explr);
    void pathCompleted(FRExplorer explr);
}
