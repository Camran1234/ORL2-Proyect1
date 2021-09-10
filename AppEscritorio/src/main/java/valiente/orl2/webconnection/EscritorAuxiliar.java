/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.webconnection;
import java.util.ArrayList;
/**
 *
 * @author camran1234
 */
class EscritorAuxiliar {
    private int channel =0;
    private ArrayList<String> notas = new ArrayList();
    
    public EscritorAuxiliar(int channel){
        this.channel=channel;
    }
    
    public void addNote(String nota){
        notas.add(nota);
    }

    public int getChannel() {
        return channel;
    }

    public ArrayList<String> getNotas() {
        return notas;
    }
    
}
