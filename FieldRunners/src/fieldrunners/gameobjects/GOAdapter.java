/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects;

import java.awt.Graphics2D;
import tbgameframework.GameObject;
import tbgameframework.IDrawable;
import tbgameframework.IUpdateable;

/**
 *
 * @author Uchiha Salm
 */
public class GOAdapter
    implements GameObject
{
    private IUpdateable updateCmp = null;
    private IDrawable drawCmp = null;
    
    public GOAdapter(IUpdateable updt, IDrawable drw)
    {
        this.updateCmp = updt;
        this.drawCmp = drw;
    }
    
    @Override
    public void resume()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public boolean isVisibled()
    {
        if (this.drawCmp != null)
        {
            return this.drawCmp.isVisibled();
        }
        
        return true;
    }

    @Override
    public void setVisibled(boolean isVs)
    {
        if (this.drawCmp != null)
        {
            this.drawCmp.setVisibled(isVs);
        }
    }

    @Override
    public float getDepth()
    {
        if (this.drawCmp != null)
        {
            return this.drawCmp.getDepth();
        }
        
        return 0f;
    }

    @Override
    public void setDepth(float depth)
    {
        if (this.drawCmp != null)
        {
            this.drawCmp.setDepth(depth);
        }
    }

    @Override
    public void Draw(Graphics2D g2D)
    {
        if (this.drawCmp != null)
        {
            this.drawCmp.Draw(g2D);
        }
    }

    @Override
    public boolean isEnabled()
    {
        if (this.updateCmp != null)
        {
            return this.updateCmp.isEnabled();
        }
        
        return true;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        if (this.updateCmp != null)
        {
            this.updateCmp.setEnabled(isEn);
        }
    }

    @Override
    public void Update()
    {
        if (this.updateCmp != null)
        {
            this.updateCmp.Update();
        }
    }
    
}
