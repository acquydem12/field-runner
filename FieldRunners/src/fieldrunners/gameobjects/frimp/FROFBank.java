/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import fieldrunners.gameobjects.runners.RunnerDynamicData;
import fieldrunners.gameobjects.runners.RunnerFactory;
import fieldrunners.gameobjects.missile.MissileFactory;
import fieldrunners.gameobjects.missile.MissileDynamicData;
import fieldrunners.gameobjects.tower.TowerDynamicData;
import fieldrunners.gameobjects.tower.TowerFactory;
import fieldrunners.xml.datas.FRXmlRootData;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import tbgameframework.xml.xmldata.XmlData;

/**
 *
 * @author Uchiha Salm
 */
public class FROFBank
{
    private Map<String, FRObjectFactory> bank;
    private RunnerDynamicData rDD;
    private TowerDynamicData tDD;
    private MissileDynamicData mDD;
    
    private FROFBank()
    {
        bank = new HashMap<>();
    }
    
    private static FROFBank inst = new FROFBank();
    
    public static FROFBank getInst()
    {
        return inst;
    }
    
    public void setRunnerDynamicData(RunnerDynamicData dynamicData)
    {
        this.rDD = dynamicData;
    }
    
    public RunnerDynamicData getRunnerDynamicData()
    {
        return this.rDD;
    }
    
    public void setTowerDynamicData(TowerDynamicData dynamicData)
    {
        this.tDD = dynamicData;
    }
    
    public TowerDynamicData getTowerDynamicData()
    {
        return this.tDD;
    }
    
    public void setMissileDynamicData(MissileDynamicData dynamicData)
    {
        this.mDD = dynamicData;
    }
    
    public MissileDynamicData getMissileDynamicData()
    {
        return this.mDD;
    }
    
    public void addFactory(FRObjectFactory frOF)
    {
        this.bank.put(frOF.getName(), frOF);
    }
    
    public void removeFactory(String name)
    {
        this.bank.remove(name);
    }
    
    public FRObjectFactory getFactory(String name)
    {
        return this.bank.get(name);
    }
    
    private static final String tagRunner = "Runner";
    public void addRunnerFromFile(String fileName)
    {
        XmlData<RunnerFactory> runnerData = new FRXmlRootData<>(tagRunner, RunnerFactory.Factory.getInst());
        runnerData.readFromFile(fileName);
        
        Collection<RunnerFactory> rFactories = runnerData.getAllInstances();
        for (RunnerFactory rF : rFactories)
        {
            this.addFactory(rF);
        }
    }
    
    private static final String tagTower = "Tower";
    public void addTowerFromFile(String fileName)
    {
        XmlData<TowerFactory> towerData = new FRXmlRootData<>(tagTower, TowerFactory.Factory.getInst());
        towerData.readFromFile(fileName);
        
        Collection<TowerFactory> tFactories = towerData.getAllInstances();
        for (TowerFactory rF : tFactories)
        {
            this.addFactory(rF);
        }
    }
    private static final String tagMissile = "Missile";
    public void addMissileFromFile(String fileName)
    {
        XmlData<MissileFactory> missileData = new FRXmlRootData<>(tagMissile, MissileFactory.Factory.getInst());
        missileData.readFromFile(fileName);
        
        Collection<MissileFactory> mFactories = missileData.getAllInstances();
        for (MissileFactory mF : mFactories)
        {
            this.addFactory(mF);
        }
    }
    
    public void clear()
    {
        this.bank.clear();
        this.rDD = null;
        this.tDD = null;
    }
}
