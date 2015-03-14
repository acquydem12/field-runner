/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import tbgameframework.scene.SceneManager;
import tbgameframework.sprite.SpriteManager;

/**
 *
 * @author MrKupi
 */
public abstract class Game
{
    protected Board board = Board.getInstance();
    protected SpriteManager sprMan = SpriteManager.getInstance();
    protected SceneManager sceneManager = null;
            
    public static enum GameState { INTRODUCING, LOADINGCONTENT, MAINMENU, PLAYING, OPTION, HELP, GAMEOVER, DETROYED};
    public static GameState gameState;
    
    public Game()
    {
        gameState = GameState.INTRODUCING;
        
        // Initialize variable, object...
        Initialize();
        // Loading game resource
        LoadContent();

        gameState = GameState.MAINMENU;
    }
    
    protected abstract void Initialize();
    
    protected abstract void LoadContent();
    
    public void Update()
    {
        if(Board.getInstance().GameInput.IsKeyDown(KeyEvent.VK_ESCAPE)){
            System.exit(0);
        }
        sceneManager.Update();
        Board.getInstance().camera.InputProcess();
    }
    
    public void Draw(Graphics2D g2D){
        //------------------------------------------
        sceneManager.Draw(g2D);
    }
}
