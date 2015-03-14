/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import tbgameframework.math.Vector2D;
import tbgameframework.utils.factory.Factory;
import tbgameframework.xml.xmldata.XmlInstance;

/**
 *
 * @author Uchiha Salm
 */
public interface MissileMovementFactory
    extends Factory<MissileMovement>, XmlInstance
{
    Vector2D initPosition(MissileDynamicData mdd);
}
