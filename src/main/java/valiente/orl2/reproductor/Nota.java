package valiente.orl2.reproductor;


import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author camran1234
 */

public final class Nota{
    private final int REST=0;
    private final int Do=1;
    private final int Do$=2;
    private final int Re=3;
    private final int Re$=4;
    private final int Mi=5;
    private final int Fa=6;
    private final int Fa$=7;
    private final int Sol=8;
    private final int Sol$=9;
    private final int La=10;
    private final int La$=11;
    private final int Si=12;
    private final int SAMPLE_RATE = 16000;
    private int selectedItem =0;
    private int octava=0;
    
    public Nota(int selectedItem, int octava){
        this.selectedItem=selectedItem;
        this.octava=octava;
    }
    
    public static int getNota(String nota){
        if(nota.equalsIgnoreCase("Do")){
            return 1;
        }else if(nota.equalsIgnoreCase("Do#")){
            return 2;
        }else if(nota.equalsIgnoreCase("Re")){
            return 3;
        }else if(nota.equalsIgnoreCase("Re#")){
            return 4;
        }else if(nota.equalsIgnoreCase("Mi")){
            return 5;
        }else if(nota.equalsIgnoreCase("Mi#")){
            return 5;
        }else if(nota.equalsIgnoreCase("Fa")){
            return 6;
        }else if(nota.equalsIgnoreCase("Fa#")){
            return 7;
        }else if(nota.equalsIgnoreCase("Sol")){
            return 8;
        }else if(nota.equalsIgnoreCase("Sol#")){
            return 9;
        }else if(nota.equalsIgnoreCase("La")){
            return 10;
        }else if(nota.equalsIgnoreCase("La#")){
            return 11;
        }else if(nota.equalsIgnoreCase("Si")){
            return 12;
        }else if(nota.equalsIgnoreCase("Si#")){
            return 1;
        }
        return 0;
    }
    
    /**
     * Genera un audio pregrabado con los valores dados
     * @return
     * @throws LineUnavailableException 
     */
    public Clip generateClip() throws LineUnavailableException{
        Clip clip=null;
        if ( clip!=null ) {             
            clip.stop();            
            clip.close();            
        } else {        
            clip = AudioSystem.getClip();            
        }
        int intSR = SAMPLE_RATE;
        int frequency = (int) frequency();
        int intFPW;
        if(frequency==0){
            intFPW =0;
        }else{
            intFPW = intSR/frequency;
        }
        
        float sampleRate = (float)intSR;
        // oddly, the sound does not loop well for less than            
        // around 5 or so, wavelengths
        int wavelengths=20;
        byte[] buf = new byte[2*intFPW*wavelengths];
        AudioFormat af = new AudioFormat(
                sampleRate,
                8,  // sample size in bits
                2,  // channels
                true,  // signed
                true  // bigendian
                );    
        int maxVol = 127;
        //agregamos los bits del sonido
        for(int index=0; index<intFPW*wavelengths; index++){
            double angle = ((float)(index*2)/((float)intFPW))*(Math.PI);
            buf[index*2]  = getByteValue(angle);
        }
        
        try {
            byte[] b = buf;
            AudioInputStream ais = new AudioInputStream(
                new ByteArrayInputStream(b),
                af,
                buf.length/2);
            clip.open(ais);
        } catch (Exception e) {
        }
        
        return clip;
    }
    
    /** Provides the byte value for this point in the sinusoidal wave. */
    private static byte getByteValue(double angle) {
        int maxVol = 127;
        return (new Integer(
            (int)Math.round(
            Math.sin(angle)*maxVol))).
            byteValue();
    }
    
    public double frequency(){
        switch(selectedItem){
           /* case 1:
                return 16.3516*Math.pow(2,octava);
                    
            case 2:
                return 17.3239*Math.pow(2,octava);
                                
            case 3:
                return 18.3541*Math.pow(2,octava);
                
            case 4:
                return 19.4454*Math.pow(2,octava);
                
            case 5:
                return 20.6017*Math.pow(2,octava);
                
            case 6:
                return 21.8268*Math.pow(2,octava);
                
            case 7:
                return 23.1247*Math.pow(2,octava);
                
            case 8:
                return 24.4997*Math.pow(2,octava);
                
            case 9:
                return 25.9565*Math.pow(2,octava);
                
            case 10:
                return 27.5000*Math.pow(2,octava);
                
            case 11:
                return 29.1353*Math.pow(2,octava);
                
            case 12:
                return 30.8677*Math.pow(2,octava);*/
            case 1:
                if(octava==0){
                    return 0;
                }
                return 16.3516*Math.pow(2,octava);
                    
            case 2:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 17.3239*Math.pow(2,octava);
                                
            case 3:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 18.3541*Math.pow(2,octava);
                
            case 4:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 19.4454*Math.pow(2,octava);
                
            case 5:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 20.6017*Math.pow(2,octava);
                
            case 6:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 21.8268*Math.pow(2,octava);
                
            case 7:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 23.1247*Math.pow(2,octava);
                
            case 8:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 24.4997*Math.pow(2,octava);
                
            case 9:
                if(octava==0){
                    return 0;
                }
                if(octava==8){
                    return 0;
                }
                return 25.9565*Math.pow(2,octava);
                
            case 10:
                if(octava==8){
                    return 0;
                }
                return 27.5000*Math.pow(2,octava);
                
            case 11:
                if(octava==8){
                    return 0;
                }
                return 29.1353*Math.pow(2,octava);
                
            case 12:
                if(octava==8){
                    return 0;
                }
                return 30.8677*Math.pow(2,octava);
        }
        return 0;
    }
    
    
}