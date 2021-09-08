/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import valiente.orl2.phyton.instructions.Function;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.conditions.ElseIf;
import valiente.orl2.phyton.error.LoopException;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
/**
 *
 * @author camran1234
 */
public class Test {
    
    public static void main (String args[]){
        
        String text = "Hola Mundo";
        StringBuilder string = new StringBuilder(text);
        for(int index=0; index<4; index++){
            string.deleteCharAt(string.length()-1);
        }
        System.out.println(string.toString());
        /*int n = JOptionPane.showConfirmDialog(
                            null, "Would you like green eggs and ham?",
                            "An Inane Question",
                            JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {

        } else if (n == JOptionPane.NO_OPTION) {

        } else {

        }*/
        
        /*String directory="./data/archivo";
        String fileName = "save.bin";
        File file = new File(directory);
        File file2 = new File(directory+"/"+fileName);
        if(!file.exists()){
            file.mkdirs();
            System.out.println("Creating");
        }
        System.out.println(file.getAbsolutePath());*/
    }
    
    public static void xD(){
        
    }
    
    
}

/*/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author camran1234
 */
/*
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone extends Thread{
    public static boolean reproducir = false;
    public Tone(){
        
    }
    @Override
    public void run() {
        try {
            final AudioFormat af =
                    new AudioFormat(Note.FREQUENCY, 8, 1, true, true);
            
            SourceDataLine line = AudioSystem.getSourceDataLine(af);
            line.open(af, Note.FREQUENCY);
            line.start();
            for  (Note n : Note.values()) {
                System.out.println(n);
                play(line, Note.Do, 500);
                //play(line, Note.REST, 10);
            }
            line.drain();
            line.close();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat af =
            new AudioFormat(Note.FREQUENCY, 8, 1, true, true);
        
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, Note.FREQUENCY);
        line.start();
        for  (Note n : Note.values()) {
            System.out.println(n);
            play(line, n, 500);
            //play(line, Note.REST, 10);
        }
        line.drain();
        line.close();
    }

    private static void play(SourceDataLine line, Note note, int ms) {
        ms = Math.min(ms, Note.SECONDS * 1000);
        int length = Note.FREQUENCY * ms / 1000;
        int count = line.write(note.data(), 0, length);
    }
}

enum Note {

    REST, Do, Re, Mi, Fa, Sol, La, Si, Do$, Re$, Mi$, Fa$, Sol$, La$, Si$;
    public final int FREQUENCY = 158; // ~16KHz
    public final int SECONDS = 2;
    private byte[] sin = new byte[SECONDS * FREQUENCY];

    Note() {
        int n = this.ordinal();
        if (n > 0) {
            double exp = ((double) n - 1) / 12d;
            double f = 440d * Math.pow(2d, exp);
            for (int i = 0; i < sin.length; i++) {
                double period = (double)FREQUENCY / f;
                double angle = 2.0 * Math.PI * i / period;
                sin[i] = (byte)(Math.sin(angle) * 127f);
            }
        }
    }

    public byte[] data() {
        return sin;
    }
    
    public int getSeconds(){
        
    }
}


    
*/