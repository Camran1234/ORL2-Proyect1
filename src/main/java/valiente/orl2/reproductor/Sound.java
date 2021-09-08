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
    private boolean clockStarted=false;
    public boolean finished = false;
    
    public Sound(Clip clip, int duration){
        this.clip = clip;
        this.clock= new Clock(clip, duration);
    }
    
    
    @Override
    public void run(){         
        try {
            if(reproducir){
                clip.setFramePosition(0);
                clip.loop( Clip.LOOP_CONTINUOUSLY );
                
                if(!clockStarted){
                    //Empezzamos el reloj
                    clockStarted=true;
                    clock.start();
                }
            }else{
                clip.stop();
            }
            while(Reproductor.play){
                if(clock.finished()){
                    break;
                }
                Thread.sleep(1);
            }
            finished=true;
            clip.stop();
            clip.close();
        } catch (InterruptedException ex) {
            finished=true;
            clip.stop();
            clip.close();
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                
    }

    void shutUp() {
        this.clip.stop();
        clip.close();
        clock.shutUp();
        clip=null;
        
    }

}
