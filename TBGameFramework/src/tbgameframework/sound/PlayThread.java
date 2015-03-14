/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sound;

/**
 *
 * @author MrKupi
 */
public abstract class PlayThread extends Thread
{
    public abstract void setLooping(boolean loop);
    public abstract void playSound();
    public abstract void stopSound();
}
