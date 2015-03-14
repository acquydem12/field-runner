/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners.gameobjects.frimp;

import fieldrunners.gameobjects.FRDirectionTranslator;
import fieldrunners.states.State;

/**
 *
 * @author Uchiha Salm
 */
public interface FRObjectFactory
{
    public String getName();
    
    public FRShine createShine();
    public FRBraceYourSelf createBYS();
    public FRGotoWorld createGTW();
    
    public State createState();
    
    public FRDirectionTranslator createDirectionTranslator();
}