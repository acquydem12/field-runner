/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.round.wave;

import fieldrunners.gameobjects.frimp.FROFBank;
import fieldrunners.gameobjects.frimp.FRObject;
import fieldrunners.gameobjects.runners.RunnerDynamicData;
import tbgameframework.scene.Scene;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class LnWave
    implements Wave
{
    private Scene scene;
    private int createDelay;
    private int nCreate;
    private Factory<RunnerDynamicData> dynDataF;
    private String runnerName;
    private int timeToLive;
    
    private int timeCounter = 0;
    
    public LnWave(Scene s, int crDelay, int nCre, Factory<RunnerDynamicData> ddF, String rName, int ttl)
    {
        this.scene = s;
        this.createDelay = crDelay;
        this.nCreate = nCre;
        this.dynDataF = ddF;
        this.runnerName = rName;
        this.timeToLive = ttl;
    }
    
    public LnWave(Scene s, Factory<RunnerDynamicData> ddf, LnWaveData wd)
    {
        this.createDelay = wd.createDelay;
        this.nCreate = wd.nCreate;
        this.runnerName = wd.runnerName;
        this.timeToLive = wd.timeToLive;
        this.scene = s;
        this.dynDataF = ddf;
    }
    
    @Override
    public Scene getScene()
    {
        return this.scene;
    }

    @Override
    public boolean isEnd()
    {
        return ((this.timeToLive < 0) && (this.nCreate <= 0)) || (this.timeToLive == 0);
    }

    @Override
    public boolean isCompleted()
    {
        return (this.nCreate <= 0);
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        
    }

    @Override
    public void Update()
    {
        if (this.timeToLive > 0)
        {
            --this.timeToLive;
        }
        
        ++this.timeCounter;
        if (this.timeCounter >= this.createDelay)
        {
            this.createRunner();
            this.timeCounter = 0;
        }
    }

    @Override
    public String getRunnerName()
    {
        return this.runnerName;
    }
    
    private void createRunner()
    {
        if (this.nCreate > 0)
        {
            FROFBank.getInst().setRunnerDynamicData(this.dynDataF.createProduct());
            
            this.scene.addComponent(new FRObject(FROFBank.getInst().getFactory(runnerName)));
            
            --this.nCreate;
        }
    }
}
