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
import valiente.orl2.central.Central;
import valiente.orl2.reproduccion.ListaReproduccion;
import valiente.orl2.reproduccion.PistaReproduccion;
import valiente.orl2.reproduccion.Reproduccion;

/**
 * Usar esta clase para generar sonidos
 * @author camran1234
 */
public class Reproductor extends Thread{
    ArrayList<Channel> channels = new ArrayList();
    String cancionActual="";
    public boolean reproduciendo=false;
    public static boolean play=true;
    
    //Para la lista
    ListaReproduccion lista;
    Central central;
    private boolean circular=false;
    private boolean random = false;
    
    
    public Reproductor(String cancionActual){
        channels = new ArrayList();
        this.cancionActual = cancionActual;
    }
    
    @Override
    public void run(){
        try {
             int index=0;
             ArrayList<String> pistas = lista.getPistas(); 
            while(true){
                int actual =index;
                if(random){
                    actual=getRandomNumber(pistas.size());
                }

                deleteChannels();
                PistaReproduccion pista = central.getPistaReproduccion(pistas.get(actual));
                generateChannels(pista);
                runSong();

                for(Channel channel:channels){
                    channel.join();
                }

                if(!circular && index==pistas.size()-1){
                    break;
                }if(circular && index==pistas.size()-1){
                    index=-1;
                }
                index++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Devuelve un valor de cero a rand-1
     * @param rand
     * @return 
     */
    public int getRandomNumber(int rand){        
        int min = 0;
        int max = rand-1;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }
    
    public String getCancionActual(){
        return cancionActual;
    }
    
    public void deleteChannels(){
        
        try {
            play=false;
            Thread.sleep(350);
            for(Channel channel:channels){
                channel.interrupt();
                //channel.shutUp();
            }
            play=true;
            Sound.reproducir=false;
            
        } catch (InterruptedException ex) {
            for(Channel channel:channels){
                channel.interrupt();
                //channel.shutUp();
            }
            play=true;
            Sound.reproducir=false;
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
        }
        channels = new ArrayList();
    }
    
    public void generateListas(ListaReproduccion lista, Central central){
        this.circular = lista.getCircular();
        this.random = lista.getRandom();
        this.lista = lista;
        this.central = central;
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
            channel.addSound(sound);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Reproductor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo reproducir la cancion", "Archivo corrupto", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Empieza todos los canales
     */
    public void runSong(){
        try {
            for(Channel channel:channels){
                //Cargamos los canales
                channel.start();
            }
            //Empezamos a reproducir
            //Al cambiar los parametros los hilos comenzaran a desplazarse
            reproduciendo=true;
            Sound.reproducir=true;
        } catch (IllegalThreadStateException e) {
            System.out.println("Explicacion: "+e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    public boolean checkChannels(){
            for(Channel channel:channels){
                if(!channel.isClosed()){
                    //Indica que no todos estan cerrados
                    return false;
                }
            }
            //Todos estan cerrados
            reproduciendo=false;
        return true;
    }
    
    public void changeChannels(){
        for(Channel channel:channels){
                channel.closed=false;
            }
    }
    
    public boolean startRe(){    
        Sound.reproducir=true;
        return checkChannels();
    }
    
    
    
}
