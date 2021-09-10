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
        ArrayList<Integer> canales = new ArrayList();
        ArrayList<Integer> duracionCanal = new ArrayList();        
        for(Reproduccion sonido:sonidos){
            int canal = sonido.getCanal();
            boolean flag=false;
            for(int index=0; index<canales.size(); index++){
                int entero = canales.get(index);
                if(entero==canal){
                    flag=true;
                    duracionCanal.add(index, duracionCanal.get(index)+sonido.getDuracion());
                }
            }
            if(!flag){
               canales.add(canal);
               duracionCanal.add(sonido.getDuracion());
            }
        }
        
        for(Integer entero:duracionCanal){
            if(total<entero){
                total = entero;
            }
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
