/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states.statesprite;

import fieldrunners.states.State;
import fieldrunners.states.StateBank;
import fieldrunners.states.XmlStateInstance;
import java.util.Collection;
import org.w3c.dom.Element;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.SpriteManager;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class XmlStateSpriteInstance
    extends StateSpriteBuilder
    implements XmlInstance
{
    private static final String tagName = "Name";
    private static final String tagState = "State";
    
    private static class XmlSpStateInstance
        implements XmlInstance
    {
        private static final String tagSprite = "Sprite";
        private static final String tagState = "State";
        
        private String sprite;
        private String state;
        
        static class Factory
            implements tbgameframework.utils.factory.Factory<XmlSpStateInstance>
        {

            @Override
            public XmlSpStateInstance createProduct()
            {
                return new XmlSpStateInstance();
            }

            private Factory()
            {

            }

            private static XmlSpStateInstance.Factory inst = new XmlSpStateInstance.Factory();

            public static XmlSpStateInstance.Factory getInst()
            {
                return inst;
            }
        }
        
        @Override
        public void load(XmlLoader xmlLoader)
        {
            this.sprite = xmlLoader.getAttribute(tagSprite);
            this.state = xmlLoader.getAttribute(tagState);
        }

        @Override
        public Element save(XmlSaver xmlSaver)
        {
            xmlSaver.addAttribute(tagSprite, sprite);
            xmlSaver.addAttribute(tagState, state);
            
            return xmlSaver.createElement();
        }

        @Override
        public boolean isMatch(String property, Object value)
        {
            return (tagSprite.equals(property) && sprite.equals(value) ||
                    tagState.equals(property) && state.equals(value));
        }
        
        public String getSpriteName()
        {
            return this.sprite;
        }
        
        public String getStateName()
        {
            return this.state;
        }
    }
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<XmlStateSpriteInstance>
    {

        @Override
        public XmlStateSpriteInstance createProduct()
        {
            return new XmlStateSpriteInstance();
        }

        private Factory()
        {

        }

        private static XmlStateSpriteInstance.Factory inst = new XmlStateSpriteInstance.Factory();

        public static XmlStateSpriteInstance.Factory getInst()
        {
            return inst;
        }
    }
    
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.name = xmlLoader.getAttribute(tagName);
        
        Collection<XmlSpStateInstance> states = xmlLoader.getAllSubInstances(tagState, XmlSpStateInstance.Factory.getInst());
        
        for (XmlSpStateInstance spSt : states)
        {
            System.out.println(StateBank.getInst().getState(spSt.getStateName()).hashCode());
            ISprite spr = SpriteManager.getInstance().getSprite(spSt.getSpriteName());
            this.addState(StateBank.getInst().getState(spSt.getStateName()),
                          spr);
        }
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        /* ^_^ suprise o.0 */
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
            return (tagName.equals(property) && name.equals(value));
    }
    
    private String name;
    
    public String getName()
    {
        return this.name;
    }
}
