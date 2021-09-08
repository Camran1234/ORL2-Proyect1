/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.reproduccion;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author camran1234
 */
public class PistaReproduccion implements Serializable{
    private ArrayList<Reproduccion> sonidos = new ArrayList();
    private String text="";
    private String name="";
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public String getName(){
        return name;
    }
    
    public String getText(){
        return text;
    }
    
    public int getDuracionTotal(){
        int total=0;
        for(Reproduccion sonido:sonidos){
            total += sonido.getDuracion();
        }
        return total;
    }
    
    public ArrayList<Reproduccion> getSonidos() {
        return sonidos;
    }

    public void setSonidos(ArrayList<Reproduccion> sonidos) {
        this.sonidos = sonidos;
    }
    
    public void addNewSonido(Reproduccion reproduccion){
        if(reproduccion!=null){
            sonidos.add(reproduccion);
        }
    }
    
    
    
}
