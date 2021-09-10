/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orl.pistas;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author camran1234
 */
public class PistaReproduccion implements Serializable{
    private ArrayList<Reproduccion> sonidos = new ArrayList();
    private String name="";
    private int duracion = 0;
    
    public void setName(String name){
        this.name = name;
    }

    
    public String getName(){
        return name;
    }

    public void setDuracion(int duracion){this.duracion = duracion;}

    public int getDuracion(){
        return duracion;
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


    public String canalesUsados() {
        StringBuilder string = new StringBuilder();
        ArrayList<Integer> canales = new ArrayList();
        for(int index=0; index<sonidos.size(); index++){
            if(canales.contains(sonidos.get(index).getCanal())==false){
                canales.add(sonidos.get(index).getCanal());
                string.append(sonidos.get(index).getCanal()).append(", ");
            }
        }

        return string.toString();
    }
}
