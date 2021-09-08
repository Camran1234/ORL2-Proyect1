    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.reproductor;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;
import valiente.orl2.reproduccion.PistaReproduccion;
import valiente.orl2.reproduccion.Reproduccion;

/**
 *
 * @author camran1234
 */
public class Reproductor {
    ArrayList<Channel> channels = new ArrayList();
    
    public Reproductor(){
        channels = new ArrayList();
    }
    
    /**
     * Genera los canales para las pistas indicadas
     * Cada pista es transformada en un sonido
     */
    public void generateChannels(PistaReproduccion pistaReproduccion){
        ArrayList<Reproduccion> reproducciones = pistaReproduccion.getSonidos();
        for(Reproduccion reproduccion:reproducciones){
            channalExists(reproduccion);
        }
    }
    /**
     * Comprueba si el canal existe, si existe agrega el sonido
     * Si no existe crea el canal y agrega el sonido
     */
    private boolean channalExists(Reproduccion reproduccion){            
        for(int index=0; index<channels.size(); index++){        
            if(reproduccion.getCanal() == channels.get(index).getCanal()){            
                addSound(reproduccion, channels.get(index));                
                return true;                
            }            
        }        
        Channel channel = new Channel(reproduccion.getCanal());        
        channels.add(channel);        
        addSound(reproduccion, channel);
        return false;
    }
    
    private void addSound(Reproduccion reproduccion, Channel channel){
        try {
            String stringNota = reproduccion.getNotaMusical();
            int nota = Nota.getNota(stringNota);
            int octava = reproduccion.getOctava();
            Nota note = new Nota(nota, octava);
            int duration = reproduccion.getDuracion();
            Clip clip = note.generateClip();
            Sound sound = new Sound(clip, duration);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo reproducir la cancion", "Archivo corrupto", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Empieza todos los canales
     */
    public void run(){
        for(Channel channel:channels){
            channel.start();
        }
    }
    
}
