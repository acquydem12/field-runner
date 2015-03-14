/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map;

import fieldrunners.map.round.RoundData;
import fieldrunners.scenes.Survival;
import java.awt.Point;
import java.util.*;
import org.w3c.dom.Element;
import tbgameframework.math.Vector2D;
import tbgameframework.utils.factory.Factory;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.instances.XmlPointInstance;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
class FRMapXmlData
    implements FRMapData, XmlInstance
{
    private String name;
    private String background;
    private String cursor;
    private int playMode = Survival.PLAYMODE_SURVIVAL;
    private XmlPointInstance beginPosition = new XmlPointInstance();
    private XmlPointInstance start = new XmlPointInstance();
    private XmlPointInstance goal = new XmlPointInstance();
    private XmlPointInstance size = new XmlPointInstance();
    private XmlPointInstance totalSize = new XmlPointInstance();
    private XmlPointInstance cellSize = new XmlPointInstance();
    
    private Collection<Point> lockedPositions;
    private Collection<RoundData> rounds;
    
    @Override
    public String getMapBackground()
    {
        return this.background;
    }
    
    @Override
    public int getPlayMode()
    {
        return this.playMode;
    }

    @Override
    public Point getGridSize()
    {
        return this.size;
    }

    @Override
    public Point getCellSize()
    {
        return this.cellSize;
    }

    @Override
    public Point getTotalMapSize()
    {
        return this.totalSize;
    }

    @Override
    public Vector2D getMapBeginPosition()
    {
        return new Vector2D(this.beginPosition.x, this.beginPosition.y);
    }

    @Override
    public Point getStart()
    {
        return this.start;
    }

    @Override
    public Point getGoal()
    {
        return this.goal;
    }

    @Override
    public Collection<Point> getLockedPositions()
    {
        return this.lockedPositions;
    }
    
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public void load(XmlLoader xmlLoader)
    {
        name = xmlLoader.getAttribute(tagName);
        background = xmlLoader.getSubTextInstance(tagBackground);
        playMode = xmlLoader.getSubIntegerInstance(tagPlayMode);
        xmlLoader.getSubInstance(tagBeginPosition, beginPosition);
        xmlLoader.getSubInstance(tagStart, start);
        xmlLoader.getSubInstance(tagGoal, goal);
        xmlLoader.getSubInstance(tagSize, size);
        xmlLoader.getSubInstance(tagTotalSize, totalSize);
        xmlLoader.getSubInstance(tagCellSize, cellSize);
        
        // get locked cells:
        this.lockedPositions = xmlLoader.<Point>getAllSubInstances(tagLockedPosition, FactoryAdapter.<Point>adapt(XmlPointInstance.Factory.getInst()));
        this.rounds = xmlLoader.getAllSubInstances(tagRound, new Factory<RoundData>() {

            @Override
            public RoundData createProduct()
            {
                return new RoundData();
            }
        });
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        xmlSaver.addAttribute(tagName, this.name);
        xmlSaver.addSubInstance(tagBackground, this.background);
        xmlSaver.addSubInstance(tagPlayMode, playMode);
        xmlSaver.addSubInstance(tagBeginPosition, beginPosition);
        xmlSaver.addSubInstance(tagStart, start);
        xmlSaver.addSubInstance(tagGoal, goal);
        xmlSaver.addSubInstance(tagSize, size);
        xmlSaver.addSubInstance(tagTotalSize, totalSize);
        xmlSaver.addSubInstance(tagCellSize, cellSize);
        
        for (Point locked : this.lockedPositions)
        {
            xmlSaver.addSubInstance(tagLockedPosition, XmlPointInstance.Factory.getInst().createProduct(locked));
        }
        
        return xmlSaver.createElement();
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        if (property.equals(tagName) && value instanceof String)
        {
            return name.equals(value);
        }
        if (property.equals(tagBackground) && value instanceof String)
        {
            return background.equals(value);
        }
        else if (property.equals(tagPlayMode) && value instanceof Integer)
        {
            return playMode == (Integer) value;
        }
        else if (property.equals(tagBeginPosition) && value instanceof Point)
        {
            return beginPosition.equals(value);
        }
        else if (property.equals(tagStart) && value instanceof Point)
        {
            return start.equals(value);
        }
        else if (property.equals(tagGoal) && value instanceof Point)
        {
            return goal.equals(value);
        }
        else if (property.equals(tagSize) && value instanceof Point)
        {
            return size.equals(value);
        }
        else if (property.equals(tagTotalSize) && value instanceof Point)
        {
            return totalSize.equals(value);
        }
        else if (property.equals(tagCellSize) && value instanceof Point)
        {
            return cellSize.equals(value);
        }
        
        return false;
    }

    @Override
    public Collection<RoundData> getRoundDatas()
    {
        return this.rounds;
    }
    
}
