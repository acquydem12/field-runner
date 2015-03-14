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
public class SlowEffectFactory
    implements FREffectFactory
{
    public static class Factory
        implements tbgameframework.utils.factory.Factory<SlowEffectFactory>
    {
        private Factory()
        {
            
        }
        
        private static SlowEffectFactory.Factory inst = new SlowEffectFactory.Factory();
        
        public static SlowEffectFactory.Factory getInst()
        {
            return inst;
        }

        @Override
        public SlowEffectFactory createProduct()
        {
            SlowEffectFactory xmlInst = new SlowEffectFactory();

            return xmlInst;
        }
    }
    
    private float slowprc;
    private int length;
    
    @Override
    public SlowEffect createProduct()
    {
        return new SlowEffect(this.slowprc, this.length);
    }

    private static final String tagSlow = "Slow";
    private static final String tagLength = "Length";
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.slowprc = (float) xmlLoader.getSubDoubleInstance(tagSlow);
        this.length = xmlLoader.getSubIntegerInstance(tagLength);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        // fun ^^ ???
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        // fun ???
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
