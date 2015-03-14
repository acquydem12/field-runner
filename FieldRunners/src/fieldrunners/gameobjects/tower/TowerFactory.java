/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.tower;

import fieldrunners.gameobjects.FRDirectionTranslator;
import fieldrunners.gameobjects.frimp.FRBraceYourSelf;
import fieldrunners.gameobjects.frimp.FRGotoWorld;
import fieldrunners.gameobjects.frimp.FROFBank;
import fieldrunners.gameobjects.frimp.FRObjectFactory;
import fieldrunners.gameobjects.frimp.FRShine;
import fieldrunners.gameobjects.frimp.SpriteShine;
import fieldrunners.gameobjects.frimp.directiontranslator.FRAllDirectionTranslator;
import fieldrunners.states.State;
import org.w3c.dom.Element;
import tbgameframework.xml.instances.XmlPointInstance;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class TowerFactory
    implements FRObjectFactory, XmlInstance
{
    public static class Factory
        implements tbgameframework.utils.factory.Factory<TowerFactory>
    {
        private Factory()
        {
            
        }
        
        private static TowerFactory.Factory inst = new TowerFactory.Factory();
        
        public static TowerFactory.Factory getInst()
        {
            return inst;
        }

        @Override
        public TowerFactory createProduct()
        {
            TowerFactory xmlPoint = new TowerFactory();

            return xmlPoint;
        }
    }
    
    //tag
    private static final String tagName = "Name";
    private static final String tagShineName = "ShineName";
    private static final String tagNumberOfDirect = "nOfDirect";
    private static final String tagSearchRadius = "SearchRadius";
    private static final String tagShootDelay = "ShootDelay";
    private static final String tagPrice = "Price";
    private static final String tagAttackPosition = "AttackPosition";
    private static final String tagAttackSound = "AttackSound";
    
    private static final String tagMissileLevel1 = "MissileLv1";
    private static final String tagMissileLevel2 = "MissileLv2";
    private static final String tagMissileLevel3 = "MissileLv3";
    private static final int LEVEL_MAX = 3;
    
    //static
    private String name;
    private String shineName;
    private float angSpeed = 0f;    // unsupported yet
    private int nDirect;
    private float searchRadius = 0f;
    private int delay;
    private int price;
    private XmlPointInstance attPos = new XmlPointInstance();
    private String attSound;
    
    private String missiles[] = new String[LEVEL_MAX];
    
    
    //dynamic
    
    public TowerFactory()
    {
    }
    
    @Override
    public FRShine createShine()
    {
        return new SpriteShine(shineName);
    }

    @Override
    public FRBraceYourSelf createBYS()
    {
        return new TowerBYS(angSpeed, searchRadius, this.attPos, FROFBank.getInst().getTowerDynamicData().getPosition(), delay, price);
    }

    @Override
    public State createState()
    {
        return new TowerState();
    }

    @Override
    public FRDirectionTranslator createDirectionTranslator()
    {
        return new FRAllDirectionTranslator(nDirect);
    }

    @Override
    public FRGotoWorld createGTW()
    {
        return new TowerGTW(FROFBank.getInst().getTowerDynamicData().getScene(), this.missiles, this.attSound);
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.name = xmlLoader.getAttribute(tagName);
        this.shineName = xmlLoader.getAttribute(tagShineName);
        this.nDirect = xmlLoader.getIntegerAttribute(tagNumberOfDirect);
        this.searchRadius = (float) xmlLoader.getDoubleAttribute(tagSearchRadius);
        this.delay = xmlLoader.getIntegerAttribute(tagShootDelay);
        this.price = xmlLoader.getIntegerAttribute(tagPrice);
        xmlLoader.getSubInstance(tagAttackPosition, this.attPos);
        
        this.missiles[0] = xmlLoader.getSubTextInstance(tagMissileLevel1);
        this.missiles[1] = xmlLoader.getSubTextInstance(tagMissileLevel2);
        this.missiles[2] = xmlLoader.getSubTextInstance(tagMissileLevel3);
        
        this.attSound = xmlLoader.getAttribute(tagAttackSound);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        // ^^ :)
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        // ^^ :)
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
