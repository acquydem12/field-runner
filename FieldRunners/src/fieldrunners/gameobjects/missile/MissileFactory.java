/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.missile;

import fieldrunners.gameobjects.FRDirectionTranslator;
import fieldrunners.gameobjects.frimp.FRBraceYourSelf;
import fieldrunners.gameobjects.frimp.FRGotoWorld;
import fieldrunners.gameobjects.frimp.FROFBank;
import fieldrunners.gameobjects.frimp.FRObjectFactory;
import fieldrunners.gameobjects.frimp.FRShine;
import fieldrunners.gameobjects.frimp.directiontranslator.FRAllDirectionTranslator;
import fieldrunners.gameobjects.frimp.state.FRState;
import fieldrunners.gameobjects.runners.effect.FREffectBank;
import fieldrunners.gameobjects.runners.effect.FREffectFactory;
import fieldrunners.states.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.w3c.dom.Element;
import tbgameframework.math.Vector2D;
import tbgameframework.xml.instances.XmlPointInstance;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class MissileFactory
    implements FRObjectFactory, XmlInstance
{
    @Override
    public Element save(XmlSaver xmlSaver)
    {
        // pay for me ^^!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        // pay for me ^^!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static class Factory
        implements tbgameframework.utils.factory.Factory<MissileFactory>
    {
        private Factory()
        {
            
        }
        
        private static MissileFactory.Factory inst = new MissileFactory.Factory();
        
        public static MissileFactory.Factory getInst()
        {
            return inst;
        }

        @Override
        public MissileFactory createProduct()
        {
            MissileFactory xmlPoint = new MissileFactory();

            return xmlPoint;
        }
    }
    
    //tag
    private static final String tagName = "Name";
    private static final String tagShineName = "ShineName";
    private static final String tagAnchor = "Anchor";
    private static final String tagIsLoop = "LoopAnim";
    private static final String tagAnimDelay = "DelayAnim";
    private static final String tagNumberOfDirect = "nOfDirect";
    
    private static final String tagMovement = "Move";
    private static final String tagCollideKill = "CollideKill";
    private static final String tagSuffuse = "Suffuse";
    private static final String tagTTL = "TTL";
    private static final String tagSize = "Size";
    
    private static final String tagEffect = "Effect";
    private static final String tagEffectType = "Type";
    private static final String tagExplosion = "Explosion";
    private static final String tagCollideSound = "CollideSound";
    
    private String name;
    private String shineName;
    private Vector2D anchor = new Vector2D();
    private boolean isLoop = true;
    private int animDelay = 0;
    private int nDirect = 1;
    
    private MissileMovementFactory movement;
    private boolean isCollideKill;
    private boolean canSuffuse;
    private int ttl;
    private XmlPointInstance size;
    
    private ExplosionFactory explosion = null;
    private Collection<FREffectFactory> effects = new ArrayList<>();
    private String collSound;
            
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.name = xmlLoader.getAttribute(tagName);
        this.shineName = xmlLoader.getAttribute(tagShineName);
        XmlPointInstance pAnchor = new XmlPointInstance();
        xmlLoader.getSubInstance(tagAnchor, pAnchor);
        this.anchor.setValues(pAnchor.x, pAnchor.y);
        this.isLoop = !(xmlLoader.getIntegerAttribute(tagIsLoop) == 0);
        this.animDelay = xmlLoader.getIntegerAttribute(tagAnimDelay);
        this.nDirect = xmlLoader.getIntegerAttribute(tagNumberOfDirect);

        this.movement = MissileMovement.Bank.getInst().getMovement(xmlLoader.getAttribute(tagMovement));
        this.movement.load(xmlLoader);
        this.size = new XmlPointInstance();
        Collection<XmlLoader> szLoader = xmlLoader.getAllSubInstances(tagSize);
        if (!szLoader.isEmpty())
        {
            this.size.load(szLoader.iterator().next());
        }
        else
        {
            this.size.setLocation(1, 1);
        }
        
        
        this.isCollideKill = !(xmlLoader.getIntegerAttribute(tagCollideKill) == 0);
        this.canSuffuse = !(xmlLoader.getIntegerAttribute(tagSuffuse) == 0);
        this.ttl = xmlLoader.getIntegerAttribute(tagTTL);
        
        Collection<XmlLoader> expLoader = xmlLoader.getAllSubInstances(tagExplosion);
        if (!expLoader.isEmpty())
        {
            this.explosion = new ExplosionFactory();
            this.explosion.load(expLoader.iterator().next());
        }

        Collection<XmlLoader> xmlEffs = xmlLoader.getAllSubInstances(tagEffect);
        for (Iterator<XmlLoader> it = xmlEffs.iterator(); it.hasNext();)
        {
            XmlLoader xmlEffLoader = it.next();
            String type = xmlEffLoader.getAttribute(tagEffectType);
            FREffectFactory effF = FREffectBank.getInst().getEffect(type);
            effF.load(xmlEffLoader);
            this.effects.add(effF);
        }
        this.collSound = xmlLoader.getAttribute(tagCollideSound);
    }
    
    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public FRShine createShine()
    {
        return new MissileShine(this.shineName, this.anchor, this.isLoop, this.animDelay);
    }

    @Override
    public FRBraceYourSelf createBYS()
    {
        return new MissileBYS(this.movement.initPosition(FROFBank.getInst().getMissileDynamicData()), this.movement.createProduct(), this.size, this.isCollideKill, this.canSuffuse, this.ttl);
    }

    @Override
    public FRGotoWorld createGTW()
    {
        return new MissileGTW(this.effects, this.explosion, this.collSound);
    }

    @Override
    public State createState()
    {
        return new FRState();
    }

    @Override
    public FRDirectionTranslator createDirectionTranslator()
    {
        return new FRAllDirectionTranslator(this.nDirect);
    }
}