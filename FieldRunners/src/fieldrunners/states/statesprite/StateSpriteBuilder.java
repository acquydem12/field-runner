/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.states.statesprite;

import fieldrunners.states.State;
import tbgameframework.sprite.ISprite;
import tbgameframework.utils.factory.Factory;

/**
 *
 * @author Uchiha Salm
 */
public class StateSpriteBuilder
    implements Factory<StateSprite>
{
    protected SpriteMap sprMap = new SpriteMap();
    
    public void addState(State st, ISprite sp)
    {
        sprMap.addState(st, sp);
    }

    @Override
    public StateSprite createProduct()
    {
        return new StateSprite(sprMap);
    }
}
