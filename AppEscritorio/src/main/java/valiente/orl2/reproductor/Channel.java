package valiente.orl2.reproductor;


import java.util.ArrayList;
import valiente.orl2.reproductor.Nota;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author camran1234
 */
public class Channel extends Thread{
    private ArrayList<Sound> sounds = new ArrayList();
    private int noChannel=0;
    public boolean closed=false;
    
    public Channel(int noChannel){
        this.noChannel = noChannel;
    }
    
    @Override
    public void run(){
        try {
            for(int index=0; index<sounds.size(); index++){
                if(!Reproductor.play){
                    break;
                }
                //Empezamos una cancion por cancion
                Sound sound = sounds.get(index);
                System.out.println("Empezando a reproducir en canal: "+noChannel);
                sound.start();
                sound.join();
                System.out.println("Terminando sound en canal: "+noChannel);
                
            }
            closed=true;
        } catch (InterruptedException ex) {  
            closed=true;
            System.out.println(ex.getMessage());
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addSound(Sound sound){
        this.sounds.add(sound);
    }
    
    public boolean isClosed(){
        return closed;
    }
    
    public int getCanal(){
        return noChannel;
    }

    /**
     * Calla a todos de este canal
     */
    void shutUp() {
        for(Sound sound:sounds){
            sound.shutUp();
        }
    }
}
