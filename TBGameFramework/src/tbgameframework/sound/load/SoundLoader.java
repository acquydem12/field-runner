/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sound.load;

import java.util.Collection;
import tbgameframework.control.buttons.XmlButtonInstance;
import tbgameframework.sound.SoundManager;
import tbgameframework.utils.factory.FactoryAdapter;
import tbgameframework.xml.xmldata.XmlData;
import tbgameframework.xml.xmldata.XmlDataImp;
import tbgameframework.xml.xmldata.XmlInstance;
import tbgameframework.xml.xmldata.builder.XmlBuilderFactory;

/**
 *
 * @author MrKupi
 */
public class SoundLoader {
    final String roof = "SoundInfo";
    
    static SoundLoader inst;
    
    private SoundLoader(){
    }
    
    static public SoundLoader getInst(){
        if(inst == null){
            inst = new SoundLoader();
        }
        return inst;
    }
    
    public void readFromFile(String xmlFile){
        XmlData data = new XmlDataImp(roof, XmlBuilderFactory.getInst(), 
                FactoryAdapter.<XmlInstance>adapt(XmlSoundInfo.Factory.getInst()));
        data.readFromFile(xmlFile);
        
        Collection<XmlInstance> sounds = data.getAllInstances();
        for(XmlInstance s : sounds){
            if(s instanceof XmlSoundInfo)
            {
                XmlSoundInfo sInfo = (XmlSoundInfo)s;
                SoundManager.addSoundPath(sInfo.getName(), sInfo.getLink());
            }
        }
    }
}
