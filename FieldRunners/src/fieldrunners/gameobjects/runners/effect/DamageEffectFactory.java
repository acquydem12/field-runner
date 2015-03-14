/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners.effect;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class DamageEffectFactory
    implements FREffectFactory
{
    public static class Factory
        implements tbgameframework.utils.factory.Factory<DamageEffectFactory>
    {
        private Factory()
        {
            
        }
        
        private static DamageEffectFactory.Factory inst = new DamageEffectFactory.Factory();
        
        public static DamageEffectFactory.Factory getInst()
        {
            return inst;
        }

        @Override
        public DamageEffectFactory createProduct()
        {
            DamageEffectFactory xmlInst = new DamageEffectFactory();

            return xmlInst;
        }
    }
    
    private int damage;
    
    @Override
    public DamageEffect createProduct()
    {
        return new DamageEffect(this.damage);
    }

    private static final String tagDamage = "Damage";
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.damage = xmlLoader.getSubIntegerInstance(tagDamage);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        // so lazy
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        // so lazy
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
