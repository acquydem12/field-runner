/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sound;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.sound.sampled.*;

/**
 *
 * @author MrKupi
 */
public class SoundManager
{
    private static int MAX_SOUND = 5;
    private static HashMap<String, SoundData> soundMap = new HashMap();
    private static HashMap<String, String> nameMap = new HashMap();
    private static ArrayList<SoundData> loopData = new ArrayList();
    private static Queue<SoundData> noLoopData = new LinkedList<>();
    public static double vol = 0;

    public static boolean loadClip(String soundName, String filename)
    {
        try
        {
            return loadClip(soundName, AudioSystem.getAudioInputStream(new File(filename)));
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            return false;
        }
    }

    public static boolean loadClip(String soundName, URL url)
    {
        try
        {
            return loadClip(soundName, AudioSystem.getAudioInputStream(url));
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            return false;
        }        
    }

    private static boolean loadClip(String soundName, AudioInputStream audioInputStream)
    {
        boolean retVal = true;

        try
        {
            // convert the AudioInputStream to PCM format -- needed for loading
            // mp3 files and files in other formats
            audioInputStream = convertToPCM(audioInputStream);

            // get a line for the Clip and load the audio from the input stream
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);

            SoundData ad = new SoundData();
            ad.audioInputStream = audioInputStream;
            ad.dataLine = clip;

            soundMap.put(soundName, ad);
        }
        catch (LineUnavailableException | IOException e)
        {
            retVal = false;
        }
        finally
        {
            try
            {
                audioInputStream.close();
            }
            catch (IOException e)
            {
                retVal = false;
            }
        }

        return retVal;
    }
    
    private static AudioInputStream convertToPCM(AudioInputStream audioInputStream)
    {
        AudioFormat format = audioInputStream.getFormat();

        if ((format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) &&
            (format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED))
        {
            AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                format.getSampleRate(), 16,
                format.getChannels(), format.getChannels() * 2,
                format.getSampleRate(), format.isBigEndian());
            audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        }

        return audioInputStream;
    }

    public static boolean loadStream(String soundName, String filename)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
            return loadStream(soundName, audioInputStream);
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            return false;
        }
    }

    public static boolean loadStream(String soundName, URL url)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            return loadStream(soundName, audioInputStream);
        }
        catch (UnsupportedAudioFileException | IOException e)
        {
            return false;
        }
    }

    private static boolean loadStream(String soundName, AudioInputStream audioInputStream)
    {
        boolean retVal = true;

        try
        {
            // convert the audio input stream to a buffered input stream that supports
            // mark() and reset()
            BufferedInputStream bufferedInputStream = new BufferedInputStream(audioInputStream);
            audioInputStream = new AudioInputStream(bufferedInputStream, audioInputStream.getFormat(), 
                    audioInputStream.getFrameLength());
            
            try
            {
                // convert the AudioInputStream to PCM format -- needed for loading
                // mp3 files and files in other formats
                audioInputStream = convertToPCM(audioInputStream);

                DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
                SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);

                SoundData ad = new SoundData();
                ad.audioInputStream = audioInputStream;
                ad.dataLine = sourceDataLine;

                soundMap.put(soundName, ad);
                audioInputStream.mark(2000000000);
                sourceDataLine.open(audioInputStream.getFormat());
            }
            catch (Exception e)
            {
                retVal = false;
            }
        }
        catch (Exception e)
        {
            retVal = false;
        }

        return retVal;
    }

    public static boolean addSoundPath(String soundName, String soundPath){
        if(!nameMap.containsKey(soundName)){
            nameMap.put(soundName, soundPath);
            return true;
        }
        return false;
    }
    
    protected static SoundData getSoundData(String soundName){
        AudioInputStream audioInputStream = null;
        try
        {
            String fileName = nameMap.get(soundName);
            if(fileName != null){
                File mySoundFile = new File(fileName);
                audioInputStream = AudioSystem.getAudioInputStream(mySoundFile);
                try
                {
                    // convert the audio input stream to a buffered input stream that supports
                    // mark() and reset()
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(audioInputStream);
                    audioInputStream = new AudioInputStream(bufferedInputStream, audioInputStream.getFormat(), 
                                    audioInputStream.getFrameLength());
                    try
                    {
                        // convert the AudioInputStream to PCM format -- needed for loading
                        // mp3 files and files in other formats
                        audioInputStream = convertToPCM(audioInputStream);

                        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
                        SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);

                        SoundData ad = new SoundData();
                        ad.audioInputStream = audioInputStream;
                        ad.dataLine = sourceDataLine;

                        audioInputStream.mark(2000000000);
                        sourceDataLine.open(audioInputStream.getFormat());

                        return ad;
                    }
                    catch (Exception e){
                        try 
                        {
                            audioInputStream.close();
                        } catch (IOException ex){}
                    }
                }
                catch (Exception e){}
            }
        }
        catch (UnsupportedAudioFileException | IOException ex) {} 
        return null;
    }
    
    public static void play(String soundName, boolean loop){
        
        if ("".equals(soundName))
        {
            return;
        }
        
        SoundData soundData = getSoundData(soundName);
        if (soundData != null)
        {
            if ((soundData.thread == null) || (!soundData.thread.isAlive()))
            {
                if (soundData.dataLine instanceof SourceDataLine){
                    PlayStreamThread pt = new PlayStreamThread(soundData.audioInputStream, 
                                            (SourceDataLine) soundData.dataLine);
                    soundData.thread = pt;
                }
                else if (soundData.dataLine instanceof Clip){
                    PlayClipThread pt = new PlayClipThread((Clip) soundData.dataLine);
                    soundData.thread = pt;
                }
                else{
                    return;
                }

                if(loop){
                    loopData.add(soundData);
                }
                else{
                    if(noLoopData.size() > MAX_SOUND){
                        noLoopData.remove().thread.stopSound();
                    }
                    noLoopData.add(soundData);
                }
                
                soundData.thread.setLooping(loop);
                soundData.thread.start();
            }
            else{
                soundData.thread.stopSound();
                soundData.thread.setLooping(loop);
                soundData.thread.playSound();
            }
        }
    }

    public static void stop(){
        for (SoundData ad : loopData)
        {
            if (ad != null)
            {
                if (ad.thread != null)
                {
                    ad.thread.stopSound();
                    ad.dataLine.close();
                    try
                    {
                        ad.audioInputStream.close();
                    }
                    catch (Exception e)
                    {}
                }
            }
        }
        loopData.clear();
        for (SoundData ad : noLoopData)
        {
            if (ad != null)
            {
                if (ad.thread != null)
                {
                    ad.thread.stopSound();
                    ad.dataLine.close();
                    try
                    {
                        ad.audioInputStream.close();
                    }
                    catch (Exception e)
                    {}
                }
            }
        }
        noLoopData.clear();
    }
    
    public static void stop(String soundName){
        SoundData ad = soundMap.get(soundName);
        if (ad != null)
        {
            if (ad.thread != null)
            {
                ad.thread.stopSound();
            }
        }
    }
    
    public static void SetVolume(double vol)
    {
        SoundManager.vol = vol;
        for(SoundData sound : noLoopData){
            if(sound != null){
                VolumeControl(sound, vol);
            }
        }
        for(SoundData sound : loopData){
            if(sound != null){
                VolumeControl(sound, vol);
            }
        }
    }
    
    protected static void VolumeControl(SoundData soundData, double vol){
        try
        {
            if(soundData.dataLine != null){
                FloatControl volCtrl = (FloatControl) soundData.dataLine.getControl(FloatControl.Type.MASTER_GAIN);
                if(vol > 1.0){
                    vol = 1.0;
                }
                if(vol < 0.0){
                    vol = 0.0;
                }

                float dB = (float) (Math.log(vol) / Math.log(10.0) * 20.0);
                if(dB > volCtrl.getMaximum()){
                    dB = volCtrl.getMaximum();
                }
                if(dB < volCtrl.getMinimum()){
                    dB = volCtrl.getMinimum();
                }
                volCtrl.setValue(dB);
            }
        }
        catch(Exception e){}
    }
}
