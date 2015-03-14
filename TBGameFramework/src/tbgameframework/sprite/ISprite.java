/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sprite;

import tbgameframework.IDrawable;
import tbgameframework.math.Vector2D;

/**
 *
 * @author MrKupi
 */
public interface ISprite extends tbgameframework.utils.prototype.Cloneable<ISprite>, IDrawable
{
    void setDrawObject(SpriteDrawObject sdObj);
    SpriteDrawObject getDrawObject();
    
    void setDrawPoint(Vector2D drawPoint);
    Vector2D getDrawPoint();
}
