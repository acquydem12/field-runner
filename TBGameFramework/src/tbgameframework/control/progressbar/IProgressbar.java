/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.control.progressbar;

import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;

/**
 *
 * @author MrKupi
 */
public interface IProgressbar extends IUpdateable, IDrawable
{
    void setDefault(float percent);
    void increase(float percent);
    void decrease(float percent);
}
