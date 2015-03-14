/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import fieldrunners.gameobjects.frimp.FROFBank;
import org.w3c.dom.Element;
import tbgameframework.math.Vector2D;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
class LnMissileMovementFactory
    implements MissileMovementFactory
{
    private static final String tagSpeed = "Speed";

    private float speed = 0f;
            
            
    @Override
    public MissileMovement createProduct()
    {
        return (new MissileMovement()
        {   
            private Vector2D vel;
            
            @Override
            public void interpolate(Vector2D pos)
            {
                pos.selfAdd(vel);
            }
            
            public MissileMovement init(float speed)
            {
                vel = FROFBank.getInst().getMissileDynamicData().getTarget().getPosition()
                         .sub(FROFBank.getInst().getMissileDynamicData().getAttacker().getPosition())
                         .normalize()
                         .mul(speed);
                
                return this;
            }
        }.init(this.speed));
    }

    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.speed = (float) xmlLoader.getSubDoubleInstance(tagSpeed);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        xmlSaver.addSubInstance(tagSpeed, this.speed);
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        return false;
    }

    @Override
    public Vector2D initPosition(MissileDynamicData mdd)
    {
        return mdd.getAttacker().getAttackPosition();
    }
    
}
