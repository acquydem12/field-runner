/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import org.w3c.dom.Element;
import tbgameframework.math.Vector2D;
import tbgameframework.utils.factory.Factory;
import tbgameframework.xml.instances.XmlPointInstance;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
class ExplosionFactory
    implements XmlInstance
{
    private static final String tagShineName = "ShineName";
    private static final String tagTTL = "TTL";
    private static final String tagAnchor = "Anchor";
    
    private String shineName;
    private int ttl;
    private XmlPointInstance anchor = XmlPointInstance.Factory.getInst().createProduct();
    
    public Explosion createProduct(Vector2D pos)
    {
        return new Explosion(shineName, new Vector2D(this.anchor.x, this.anchor.y), pos, ttl);
    }

    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.shineName = xmlLoader.getAttribute(tagShineName);
        this.ttl = xmlLoader.getIntegerAttribute(tagTTL);
        
        xmlLoader.getSubInstance(tagAnchor, this.anchor);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
