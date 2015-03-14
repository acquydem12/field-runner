/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.map.round;

import fieldrunners.gameobjects.GOAdapter;
import fieldrunners.gameobjects.ICompleteable;
import fieldrunners.gameobjects.runners.RunnerDynamicData;
import java.util.*;
import tbgameframework.*;
import tbgameframework.scene.Scene;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class RoundManager
    implements ICompleteable, IUpdateable
{
    private Scene scene;
    private Factory<RunnerDynamicData> rDynDataF;
    private Deque<Round> rounds = new ArrayDeque<>();
    private Round cRound;
    
    boolean isEnable = true;
    
    public RoundManager(Scene s, Factory<RunnerDynamicData> ddf)
    {
        this.scene = s;
        this.rDynDataF = ddf;
    }
    
    public Deque<Round> getRounds()
    {
        return this.rounds;
    }
    
    public void addRounds(Collection<RoundData> rds)
    {
        for (RoundData rd : rds)
        {
            this.rounds.addLast(new Round(this.scene, this.rDynDataF, rd));
        }
    }
    
    public void addRound(RoundData rd)
    {
        this.rounds.addLast(new Round(this.scene, this.rDynDataF, rd));
    }
    
    public Round getCurrentRound()
    {
        return this.cRound;
    }
    
    public boolean isRoundCompleted()
    {
        return ((this.cRound == null) || (this.cRound.isCompleted()));
    }
    
    public Round nextRound()
    {
        if (!this.rounds.isEmpty())
        {
            this.cRound = this.rounds.removeFirst();
            this.scene.addComponent(new GOAdapter(this.cRound, null));
        }
        
        return null;
    }

    @Override
    public boolean isCompleted()
    {
        return (this.cRound == null && this.rounds.isEmpty());
    }

    @Override
    public boolean isEnabled()
    {
        return this.isEnable;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.isEnable = isEn;
    }

    @Override
    public void Update()
    {
        if (this.cRound != null && this.cRound.isCompleted())
        {
            this.cRound = null;
        }
    }
}
