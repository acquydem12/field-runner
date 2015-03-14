/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Graphics2D;

/**
 *
 * @author MrKupi
 */
public interface IDrawable
{
    public boolean isVisibled();
    public void setVisibled(boolean isVs);
    public float getDepth();
    public void setDepth(float depth);
    public void Draw(Graphics2D g2D);
}