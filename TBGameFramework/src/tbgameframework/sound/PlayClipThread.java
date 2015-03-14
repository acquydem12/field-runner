/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sound;

import javax.sound.sampled.Clip;

/**
 *
 * @author MrKupi
 */

class PlayClipThread extends PlayThread
{
    private Clip clip = null;
    private boolean loop = false;

    public PlayClipThread(Clip clip)
    {
        this.clip = clip;
    }

    @Override
    public void run()
    {
        if (clip != null)
        {
            stopSound();

            // give the thread that is playing the clip a chance to
            // stop the clip before we restart it
            try
            {
                Thread.sleep(1);
            }
            catch(InterruptedException e)
            {
                // don't do anything if the thread was interrupted
            }

            playSound();
        }
    }

    @Override
    public void setLooping(boolean loop)
    {
        this.loop = loop;
    }

    @Override
    public void stopSound()
    {
        clip.stop();
    }

    @Override
    public void playSound()
    {
        clip.setFramePosition(0);
        if (loop)
        {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else
        {
            clip.start();
        }
    }
}
    
