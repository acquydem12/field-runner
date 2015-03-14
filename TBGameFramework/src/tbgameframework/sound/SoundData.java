/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tbgameframework.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;

/**
 *
 * @author MrKupi
 */
public class SoundData
{
    public SoundData(){
    }
    
    public SoundData(SoundData data){
        
        //<editor-fold defaultstate="collapsed" desc="Example">
        /*if(data != null){
         * BufferedInputStream bufferedInputStream = new BufferedInputStream(data.audioInputStream);
         * this.audioInputStream = new AudioInputStream(bufferedInputStream, data.audioInputStream.getFormat(),
         * data.audioInputStream.getFrameLength());
         * 
         * DataLine.Info info = new DataLine.Info(SourceDataLine.class, this.audioInputStream.getFormat());
         * try {
         * SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
         * sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
         * this.dataLine = sourceDataLine;
         * this.audioInputStream.mark(2000000000);
         * sourceDataLine.open(this.audioInputStream.getFormat());
         * } catch (LineUnavailableException ex) {
         * }
         * }*/
        //</editor-fold>
    }
    
    public AudioInputStream audioInputStream = null;
    public DataLine dataLine = null;
    public PlayThread thread = null;
}