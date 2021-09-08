/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.reproductor;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

/**
 *
 * @author camran1234
 */
public class Sound extends Thread{
    private Clip clip;
    private Clock clock;
    //Variable que nos indicara si reproducimos los sonidos
    public static boolean reproducir=false;
    
    
    public Sound(Clip clip, int duration){
        this.clip = clip;
        this.clock= new Clock(clip, duration);
    }
    
    
    @Override
    public void run(){
        while(true){            
            try {
                if(reproducir){
                    clip.setFramePosition(0);
                    clip.loop( Clip.LOOP_CONTINUOUSLY );
                    //Empezzamos el reloj
                    clock.start();
                }else{
                    clip.stop();
                }
                if(clock.finished()){
                    break;
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
