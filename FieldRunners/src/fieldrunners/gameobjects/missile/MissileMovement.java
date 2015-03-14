/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Element;
import tbgameframework.math.Vector2D;
import tbgameframework.utils.factory.Factory;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public interface MissileMovement
{
    void interpolate(Vector2D pos);
    
    public static class Bank
    {
        private Map<String, Factory<MissileMovementFactory>> movements;

        private Bank()
        {
            this.movements = new HashMap<>();
            
            this.init();
        }

        private void init()
        {
            this.addMovement("Fixed", new Factory<MissileMovementFactory>() 
            {
                @Override
                public MissileMovementFactory createProduct()
                {
                    return new MissileMovementFactory()
                    {
                        @Override
                        public Vector2D initPosition(MissileDynamicData mdd)
                        {
                            return mdd.getTarget().getPosition();
                        }

                        @Override
                        public MissileMovement createProduct()
                        {
                            return new MissileMovement()
                            {
                                @Override
                                public void interpolate(Vector2D pos)
                                {
                                }
                            };
                        }

                        @Override
                        public void load(XmlLoader xmlLoader)
                        {
                        }

                        @Override
                        public Element save(XmlSaver xmlSaver)
                        {
                            return xmlSaver.createElement();
                        }

                        @Override
                        public boolean isMatch(String property, Object value)
                        {
                            return false;
                        }
                    };
                }
            });
            
            this.addMovement("Linear", new Factory<MissileMovementFactory>() 
            {
                @Override
                public MissileMovementFactory createProduct()
                {
                    return new LnMissileMovementFactory();
                }
            });
        }

        private static Bank inst = new Bank();

        public static Bank getInst()
        {
            return inst;
        }

        public void addMovement(String name, Factory<MissileMovementFactory> mov)
        {
            this.movements.put(name, mov);
        }

        public void removeMovement(String name)
        {
            this.movements.remove(name);
        }


        public MissileMovementFactory getMovement(String name)
        {
            return this.movements.get(name).createProduct();
        }
    }
}
