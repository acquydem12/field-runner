 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states.statesprite;

import fieldrunners.gameobjects.ObjectStateMap;
import fieldrunners.states.State;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import tbgameframework.sprite.imagesprite.ImageSprite;

/**
 *
 * @author Uchiha Salm
 */
public class SpriteMap
    implements ObjectStateMap, Iterable<ImageSprite>, tbgameframework.utils.prototype.Cloneable<SpriteMap>
{
    private Map<State, ImageSprite> statesMap = new HashMap<>();
    
    SpriteMap()
    {
    }
    
    @Override
    public Collection<State> getAllStates()
    {
        return this.statesMap.keySet();
    }
    
    @Override
    public void addState(State state, Object obj)
    {
        try
        {
            ImageSprite spr = (ImageSprite) obj;
            if (spr != null)
            {
                statesMap.put(state, spr);
            }
        }
        catch (Exception e)
        {
            System.err.println("err: must be sprite");
        }
    }

    @Override
    public void setState(State state, Object obj)
    {
        try
        {
            ImageSprite spr = (ImageSprite) obj;
            if (spr != null)
            {
                statesMap.put(state, spr);
            }
        }
        catch (Exception e)
        {
            System.err.println("err: must be sprite");
        }
    }

    @Override
    public void removeState(State state)
    {
        statesMap.remove(state);
    }

    @Override
    public Object getObject(State state)
    {
        return statesMap.get(state);
    }

    @Override
    public Iterator<ImageSprite> iterator()
    {
        return this.statesMap.values().iterator();
    }

    @Override
    public SpriteMap clone()
    {
        SpriteMap cloned = new SpriteMap();
        
        Set<Entry<State, ImageSprite>> entries = this.statesMap.entrySet();
        for (Entry<State, ImageSprite> stEntry : entries)
        {
            cloned.addState(stEntry.getKey(), stEntry.getValue().clone());
        }
        
        return cloned;
    }
}
