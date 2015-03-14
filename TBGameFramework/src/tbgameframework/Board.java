/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author MrKupi
 */
public class Board
    extends Canvas
    implements Runnable
{
    private Game game;
    private static Board instance;
    private int FPS = 60;
    private long GAME_UPDATE_DELAY = 1000000000L / FPS;
    
    // Singleton
    public GameTime gameTime = GameTime.getInstance();
    public Input GameInput = Input.getInstance(this);
    public Camera camera;
    
    private Board()
    {
        super();
        
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                Initialize();
            }
        };
        
        thread.start();
    }
    
    public static Board getInstance()
    {
        if (instance == null)
        {
            instance = new Board();
        }
        
        return instance;
    }
    
    private void Initialize()
    {
        setBackground(Color.DARK_GRAY);
        setDoubleBuffered(true);
        
        gameTime.Start();
        
        System.out.println("Camera start init");
        camera = new Camera();
        System.out.println("Camera completed init");
    }
    
    private void LoadContent()
    {
    }
    
    private void GameLoop(){
        
        long beginTime, timeTaken, timeLeft;
        while(true){
            
            beginTime = gameTime.CurrentTimeInNano();
            
            if(game != null)
            {
                game.Update();
            }
            
            repaint();
            
            // Sleep time
            gameTime.Update();
            GameInput.Update();
            timeTaken = gameTime.CurrentTimeInNano() - beginTime;
            timeLeft = (GAME_UPDATE_DELAY - timeTaken) / 1000000L;
            if(timeLeft < 5){
                timeLeft = 5;
            }
            try{
                Thread.sleep(timeLeft);
            }catch(InterruptedException e){
            }
        }
    }
    
    @Override
    public synchronized void Draw(Graphics2D g2D){
        if(camera != null){
            camera.Transform(g2D);
        }
        // Draw game state by switch case
        if(game != null){
            game.Draw(g2D);
        }
    }
    
    public void setGame(Game game){
        this.game = game;
    }
    
    public void setFPS(int fps){
        this.FPS = fps;
        this.GAME_UPDATE_DELAY = 1000000000L / FPS;
    }
    
    @Override
    public void run()
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                GameLoop();
            }
        };
        
        thread.start();
    }
    
}
