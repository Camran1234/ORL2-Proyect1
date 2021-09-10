package valiente.orl2.reproductor;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import valiente.orl2.proyecto1.ListasUI;

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
    private boolean apagado=false;
    private float frecuencia=0;
    private int canal=0;
    public Clock(Clip clip, int time){
        maxTime=time;
        this.clip=clip;
    }
    
    @Override
    public void run(){
        while(Reproductor.play){
            try {
                if(Sound.reproducir){
                    if(timeLapsed%500==0){
                        ListasUI.graphic.addDataSet("Canal "+Integer.toString(canal), frecuencia, timeLapsed);
                    }
                    if(timeLapsed>=maxTime){                
                        break;                    
                    }                
                    if(apagado){
                       clip.loop( Clip.LOOP_CONTINUOUSLY ); 
                    }
                    apagado=false;
                    Thread.sleep(10);                
                    timeLapsed+=10;
                }else{
                    apagado=true;
                    clip.stop();
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                finished=true;
                clip.stop();
                clip.close();
                e.printStackTrace();
            }
        }
        try {
            clip.stop();
            clip.close();
            finished=true;
        } catch (Exception e) {
        }
    }
    
    public boolean finished(){
        return finished;
    }

    void shutUp() {
        this.clip.stop();
        this.clip.close();
        clip=null;
    }

    void setFrecuencia(float frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    void setCanal(int canal){
        this.canal = canal;
    }
    
}
