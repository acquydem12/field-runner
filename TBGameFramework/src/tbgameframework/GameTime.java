/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

/**
 *
 * @author MrKupi
 */
public class GameTime {
    
    static private GameTime gameTime;
    private long startTime;
    
    // In Millisecond
    private long currentTime;
    private long lastTime;
    private long updateTime;
    
    private GameTime(){
        
    }
    
    static public GameTime getInstance()
    {
        if (gameTime == null)
        {
            gameTime = new GameTime();
        }
        
        return gameTime;
    }
    
    // Set your time to begin = 0
    public void Start(){    
        startTime = System.currentTimeMillis();
    }
    
    // Reset your game time
    public void Restart(){
        startTime = System.currentTimeMillis();
    }
    
    // Call in game update to update your game time
    public void Update(){
        lastTime = currentTime;
        currentTime = System.currentTimeMillis();
    }
    
    /////// get Current Time
    public long CurrentTimeInMillisecond(){
        return (currentTime - startTime);
    }
    
    public long CurrentTimeInMicrosecond(){
        return (currentTime - startTime)*1000;
    }
    
    public long CurrentTimeInSecond(){
        return (currentTime - startTime)/1000;
    }
    
    public long CurrentTimeInNano(){
        return (currentTime - startTime)*1000000;
    }
    
    // get time between update time
    public long getUpdateTime(){
        updateTime = currentTime - lastTime;
        return updateTime;
    }
}
