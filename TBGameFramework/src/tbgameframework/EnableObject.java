/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

/**
 *
 * @author Uchiha Salm
 */
public class EnableObject
    implements IUpdateable
{
    private boolean enable = true;
    
    @Override
    public boolean isEnabled()
    {
        return this.enable;
    }

    @Override
    public void setEnabled(boolean isEn)
    {
        this.enable = isEn;
    }

    @Override
    public void Update() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
