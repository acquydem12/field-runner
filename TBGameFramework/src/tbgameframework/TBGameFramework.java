/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import javax.swing.SwingUtilities;
import tbgameframework.utils.factory.Factory;

/**
 * The framework facade - create main thread to run framework
 * @author Uchiha Salm
 */
public class TBGameFramework
    implements Runnable
{
    private String title;
    private int sizex, sizey;
    private Factory<Game> fGame;
    private boolean isFullScreen = false;
    
    private TBGameFramework()
    {
        
    }
    
    private static TBGameFramework instance = new TBGameFramework();
    
    public static TBGameFramework getInstance()
    {
        return instance;
    }
    
    public TBGameFramework setTitle(String title)
    {
        this.title = title;
        
        return this;
    }
    
    public TBGameFramework setSize(int x, int y)
    {
        this.sizex = x;
        this.sizey = y;
        
        return this;
    }
    
    public TBGameFramework setGame(Factory<Game> fGame)
    {
        this.fGame = fGame;
        
        return this;
    }
    
    public TBGameFramework setScreenMode(boolean isFull)
    {
        this.isFullScreen = isFull;
        
        return this;
    }
    
    @Override
    public void run()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Window window = Window.getInstance();
                window.setFullScreen(TBGameFramework.instance.isFullScreen);
                window.setWindowTitle(TBGameFramework.instance.title);
                window.setWindowsSize(TBGameFramework.instance.sizex, TBGameFramework.instance.sizey);
                window.Initialize();
                window.setGameWindow(TBGameFramework.instance.fGame.createProduct());
                
                Board.getInstance().run();
            }
        });
    }
}
