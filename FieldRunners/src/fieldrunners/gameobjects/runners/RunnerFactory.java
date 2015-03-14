/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.runners;

import fieldrunners.gameobjects.FRDirectionTranslator;
import fieldrunners.gameobjects.frimp.*;
import fieldrunners.gameobjects.frimp.directiontranslator.FRAllDirectionTranslator;
import fieldrunners.gameobjects.frimp.state.FRState;
import fieldrunners.pathfinding.astar.ASPathFinder;
import fieldrunners.pathfinding.core.PathFinder;
import fieldrunners.states.State;
import java.awt.Point;
import org.w3c.dom.Element;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.instances.XmlPointInstance;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class RunnerFactory
    implements FRObjectFactory, XmlInstance
{
    public static class Factory
        implements tbgameframework.utils.factory.Factory<RunnerFactory>
    {
        private Factory()
        {
            
        }
        
        private static RunnerFactory.Factory inst = new RunnerFactory.Factory();
        
        public static RunnerFactory.Factory getInst()
        {
            return inst;
        }

        @Override
        public RunnerFactory createProduct()
        {
            RunnerFactory xmlPoint = new RunnerFactory();

            return xmlPoint;
        }
    }
    
    private static final String tagName = "Name";
    private static final String tagShineName = "ShineName";
    private static final String tagMoveSpeed = "MoveSpeed";
    private static final String tagNumberOfDirect = "nOfDirect";
    private static final String tagSize = "Size";
    private static final String tagHP = "HP";
    private static final String tagPrice = "Price";
    private static final String tagDeathSound = "DeathSound";
    
    // static
    private String name;
    private String shineName;
    private float speed = 0f;
    private int nDirect;
    private Point size;
    private int hp = 0;
    private int price = 0;
    private String deathSound;
    
    // dynamic
    
    RunnerFactory()
    {
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }
    
    @Override
    public FRShine createShine()
    {
        return new SpriteShine(shineName);
    }
    
    @Override
    public FRBraceYourSelf createBYS()
    {
        final RunnerDynamicData rDD = FROFBank.getInst().getRunnerDynamicData();
        return new RunnerBYS(rDD.startPosition(), speed, size, hp, nDirect, new tbgameframework.utils.factory.Factory<FRExplorer>()
        {
            @Override
            public FRExplorer createProduct()
            {
                return new RunnerExplorer(rDD.Grid(), rDD.goal(),
                        FactoryAdapter.<PathFinder>adapt(ASPathFinder.Factory.getInst()));
            }
        }, rDD.gameScore(), price, rDD.scene(), this.deathSound);
    }

    @Override
    public State createState()
    {
        return new FRState();
    }

    @Override
    public FRDirectionTranslator createDirectionTranslator()
    {
        return new FRAllDirectionTranslator(nDirect);
    }

    @Override
    public FRGotoWorld createGTW()
    {
        return new RunnerGTW();
    }

    @Override
    public void load(XmlLoader xmlLoader)
    {
        // Vector2D startPos; dynamic
        // Grid2D grid; dynamic
        // Point goal; dynamic

        name = xmlLoader.getAttribute(tagName);
        shineName = xmlLoader.getAttribute(tagShineName);
        speed = (float) xmlLoader.getDoubleAttribute(tagMoveSpeed);
        nDirect = xmlLoader.getIntegerAttribute(tagNumberOfDirect);
        XmlPointInstance sz = new XmlPointInstance();
        xmlLoader.getSubInstance(tagSize, sz);
        this.size = sz;
        this.hp = xmlLoader.getIntegerAttribute(tagHP);
        this.price = xmlLoader.getIntegerAttribute(tagPrice);
        this.deathSound = xmlLoader.getAttribute(tagDeathSound);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        // i'm, so lazy
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        // i'm, so lazy
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
