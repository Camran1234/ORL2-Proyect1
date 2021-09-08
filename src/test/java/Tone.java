/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author camran1234
 */
import valiente.orl2.reproductor.Channel;
import valiente.orl2.reproductor.Nota;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {
    
    public static void main(String[] args) throws LineUnavailableException {
        
        /*final AudioFormat af =
            new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, Note.SAMPLE_RATE);
        line.start();*/
        System.out.println("Empezando");
        int ms = Nota.SECONDS*1000;
        Channel channel1 = new Channel(Nota.Do, ms);
        /*Channel channel2 = new Channel(Note.Re, ms);
        Channel channel3 = new Channel(Note.Mi, ms);*/
        Channel.go=true;
        channel1.start();
        /*channel2.start();
        channel3.start();*/
        /*play(line, Note.Do, ms);
        line.drain();
        line.close();*/
    }

    private static void play(SourceDataLine line, Nota note, int ms) {
        ms = Math.min(ms, Nota.SECONDS * 1000);
        int length = Nota.SAMPLE_RATE * ms / 1000;
        int count = line.write(note.data(), 0, length);
    }
}



