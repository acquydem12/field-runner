/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import tbgameframework.collision.ICollisionObject;

/**
 *
 * @author Uchiha Salm
 */
public interface FRGotoWorld
    extends ICollisionObject
{
    void setBYS(FRBraceYourSelf bys);
    FRBraceYourSelf getBYS();
}
