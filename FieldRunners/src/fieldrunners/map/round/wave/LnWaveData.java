/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.round.wave;

import org.w3c.dom.Element;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.XmlLoader;
import tbgameframework.xml.xmldata.XmlSaver;

/**
 *
 * @author Uchiha Salm
 */
public class LnWaveData
    implements XmlInstance
{
    private static final String tagCreateDelay = "CreateDelay";
    private static final String tagnCreate = "nRunner";
    private static final String tagRunnerName = "Runner";
    private static final String tagTTL = "TTL";
    
    int createDelay;
    int nCreate;
    String runnerName;
    int timeToLive;
    
    @Override
    public void load(XmlLoader xmlLoader)
    {
        this.createDelay = xmlLoader.getIntegerAttribute(tagCreateDelay);
        this.nCreate = xmlLoader.getIntegerAttribute(tagnCreate);
        this.runnerName = xmlLoader.getAttribute(tagRunnerName);
        this.timeToLive = xmlLoader.getIntegerAttribute(tagTTL);
    }

    @Override
    public Element save(XmlSaver xmlSaver)
    {
        // !=,=
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isMatch(String property, Object value)
    {
        // :v
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
