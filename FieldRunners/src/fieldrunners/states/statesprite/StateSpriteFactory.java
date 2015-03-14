/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states.statesprite;

import fieldrunners.xml.datas.FRXmlRootData;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import tbgameframework.sprite.ISprite;
import tbgameframework.sprite.ISpriteFactory;
import tbgameframework.xml.xmldata.XmlData;
import tbgameframework.xml.xmldata.XmlInstance;

/**
 *
 * @author Uchiha Salm
 */
public class StateSpriteFactory
    implements ISpriteFactory
{
    private static final String nodeName = "StateSprite";

    private StateSpriteFactory()
    {
        
    }
    
    private static StateSpriteFactory inst = new StateSpriteFactory();
    
    public static StateSpriteFactory getInst()
    {
        return inst;
    }

    @Override
    public Map<String, XmlInstance> Load(String xmlFile, String name) {
        Map<String, XmlInstance> result = new HashMap<>();
        if (nodeName.equals(name))
        {
            XmlData<XmlStateSpriteInstance> sspInstsData = new FRXmlRootData(name, XmlStateSpriteInstance.Factory.getInst());
            sspInstsData.readFromFile(xmlFile);
            
            Collection<XmlStateSpriteInstance> sspInsts = sspInstsData.getAllInstances();
            
            for (XmlStateSpriteInstance sspInst : sspInsts)
            {
                result.put(sspInst.getName(), sspInst);
                //result.put(sspInst.getName(), sspInst.createProduct());
            }
        }
        
        return result;
    }

    @Override
    public ISprite CreateSprite(XmlInstance instance) {
                
        if(instance instanceof XmlStateSpriteInstance)
        {
            return ((XmlStateSpriteInstance)instance).createProduct();
        }
        return null;
    }
}
