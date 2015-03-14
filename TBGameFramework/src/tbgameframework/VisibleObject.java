/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Graphics2D;

/**
 *
 * @author Uchiha Salm
 */
public class VisibleObject
    implements IDrawable
{
    private boolean visible = true;
    private float depth = 1.0f;
    
    @Override
    public boolean isVisibled()
    {
        return this.visible;
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        this.visible = isVs;
    }

    @Override
    public float getDepth()
    {
        return this.depth;
    }

    @Override
    public void setDepth(float depth)
    {
        this.depth = depth;
    }

    @Override
    public void Draw(Graphics2D g2D)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
