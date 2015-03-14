/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;

/**
 *
 * @author MrKupi
 */
public class GTimer extends Observable implements ActionListener {
    
    Timer time;
    Observer observer;
    
    public GTimer(long timeDelay, Observer mObserver){
        
        time = new Timer((int)timeDelay, this);
        this.observer = mObserver;
        this.addObserver(observer);
    }
    
    public void Start(){
        time.start();
    }
    
    public void Stop(){
        time.stop();
    }
    
    public void Restart(){
        time.restart();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        time.stop();
        
        setChanged();
        notifyObservers();
    }
}
