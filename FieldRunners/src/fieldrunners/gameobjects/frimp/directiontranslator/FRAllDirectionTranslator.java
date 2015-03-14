/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp.directiontranslator;

import fieldrunners.gameobjects.FRDirectionTranslator;


/**
 *
 * @author Uchiha Salm
 */
public class FRAllDirectionTranslator
    implements FRDirectionTranslator
{
    public static final int DEFAULT_NDIRECT = 4;
    public static final int NDIRECT64 = 64;
    public static final int NDIRECT32 = 32;
    public static final int NDIRECT16 = 16;
    public static final int NDIRECT8 = 8;
    public static final int NDIRECT4 = 4;
    public static final int NDIRECT0 = 1;
    
    private static final int MAXDIRECT = NDIRECT64;
    
    private static final float TwoPi = (float) (2 * Math.PI);
    
    private int nDirect = DEFAULT_NDIRECT;
    private float darg = TwoPi / nDirect;
    
    private FRAllDirectionTranslator()
    {
        this.changeNDirect(DEFAULT_NDIRECT);
    }
    
    public FRAllDirectionTranslator(int ndirect)
    {
        this.changeNDirect(ndirect);
    }
    
    public final void changeNDirect(int ndirect)
    {
        if (ndirect == NDIRECT0 || ndirect == NDIRECT4 || ndirect == NDIRECT8 || ndirect == NDIRECT16 || ndirect == NDIRECT32 || ndirect == NDIRECT64)
        {
            nDirect = ndirect;
            if (nDirect != 0)
            {
                darg = (float) TwoPi / nDirect;
            }
            else
            {
                darg = Float.POSITIVE_INFINITY;
            }
        }
    }
    
    @Override
    public int translate(float angle)
    {
        return (MAXDIRECT / this.nDirect) * (Math.round((angle % TwoPi) / darg) % this.nDirect);
    }
}