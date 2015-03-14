/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;

/**
 * (I'm here again ^^)
 * The sun shining everything, 
 * and you can see me because the
 * reflection of light
 * I'm dependence draw-able object 
 * you should shining me by a AbstractMirror object
 * @author Uchiha Salm
 */
public interface FRShine
    extends IDrawable
{
    void setAbstractMirror(IUpdateable am);
}