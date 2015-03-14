/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldrunners;

import tbgameframework.TBGameFramework;
import tbgameframework.utils.factory.FactoryAdapter;

/**
 *
 * @author Uchiha Salm
 */
public class FieldRunners
{
    public final static boolean IS_FULL_SCREEN = false;
    public final static String GAME_TITLE = "Field Runners";
    public final static int GAME_WIDTH = 1024;
    public final static int GAME_HEIGHT = 768;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        TBGameFramework.getInstance().setScreenMode(IS_FULL_SCREEN)
                .setTitle(GAME_TITLE)
                .setSize(GAME_WIDTH, GAME_HEIGHT)
                .setGame(FactoryAdapter.<tbgameframework.Game>adapt(Game.Factory.getInst()))
                .run();
    }
}
