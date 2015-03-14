/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.round;

import fieldrunners.gameobjects.GOAdapter;
import fieldrunners.gameobjects.ICompleteable;
import fieldrunners.gameobjects.frimp.FRObject;
import fieldrunners.gameobjects.runners.*;
import fieldrunners.map.round.wave.*;
import java.awt.Graphics2D;
import java.util.*;
import tbgameframework.GameObject;
import tbgameframework.scene.Scene;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class Round
    implements GameObject, ICompleteable
{
    private static final String tagWave = "Wave";
    private static final String tagRoundName = "Name";
    
    private Deque<Wave> waves = new ArrayDeque<>();
    private Wave currentWave = null;
    private String name;
    
    private Scene scene;
    private Factory<RunnerDynamicData> dynDataF;
    
    public Round(Scene s, Factory<RunnerDynamicData> ddf, RoundData rd)
    {
        this.name = rd.name;
        this.scene = s;
        this.dynDataF = ddf;
        
        for (LnWaveData wd : rd.waves)
        {
            this.waves.addLast(new LnWave(this.scene, this.dynDataF, wd));
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Unused IDrawable">
    @Override
    public void resume() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void pause() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean isVisibled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void setVisibled(boolean isVs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public float getDepth() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void setDepth(float depth) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void Draw(Graphics2D g2D) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void setEnabled(boolean isEn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>

    @Override
    public void Update()
    {
        this.checkWave();
    }
    
    private boolean isHasNoRunner()
    {
        for (GameObject go : this.scene)
        {
            if (go instanceof FRObject && ((FRObject) go).getBYS() instanceof RunnerBYS)
            {
                return false;
            }
        }
        
        return true;
    }
    
    private void checkWave()
    {
        if (this.currentWave == null || this.currentWave.isEnd())
        {
            if (this.waves.isEmpty())
            {
                this.currentWave = null;
                return;
            }
            
            this.currentWave = this.waves.removeFirst();
            this.scene.addComponent(new GOAdapter(this.currentWave, null));
        }
    }
    public String getName()
    {
        return this.name;
    }

    @Override
    public boolean isCompleted()
    {
        return (this.currentWave == null && this.waves.isEmpty() && this.isHasNoRunner());
    }
}
