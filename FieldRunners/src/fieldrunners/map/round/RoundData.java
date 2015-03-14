/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.round;

import fieldrunners.map.round.wave.*;
import java.util.ArrayDeque;
import java.util.Deque;
import org.w3c.dom.Element;
import tbgameframework.utils.factory.Factory;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class RoundData
    implements XmlInstance
{
    private static final String tagWave = "Wave";
    private static final String tagRoundName = "Name";
    
    Deque<LnWaveData> waves = new ArrayDeque<>();
    String name;
    
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.name = xmlLoader.getAttribute(tagRoundName);
        this.waves.addAll(xmlLoader.getAllSubInstances(tagWave, new Factory<LnWaveData>()
        {
            @Override
            public LnWaveData createProduct()
            {
                return new LnWaveData();
            }
        }));
    }
    
    //<editor-fold defaultstate="collapsed" desc="Unused XML">
    @Override
    public Element save(XmlSaver xmlSaver) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean isMatch(String property, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>
}
