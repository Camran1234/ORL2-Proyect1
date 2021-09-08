package valiente.orl2.reproductor;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Representa la duracion de un clip
 * @author camran1234
 */
public class Clock extends Thread{
    private int timeLapsed=0;
    private int maxTime=0;
    private Clip clip;
    private boolean finished=false;
    
    public Clock(Clip clip, int time){
        maxTime=time;
        this.clip=clip;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                if(Sound.reproducir){
                    if(timeLapsed>=maxTime){                
                        break;                    
                    }                
                    Thread.sleep(100);                
                    timeLapsed+=100;
                }else{
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        clip.stop();
        finished=true;
    }
    
    public boolean finished(){
        return finished();
    }
    
}
