/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orl.pistas;

import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class ListaReproduccion {
    private String nombre="";
    private ArrayList<String> pistas = new ArrayList<>();
    private ArrayList<Integer> duracionPistas = new ArrayList<>();
    private Boolean circular= false;
    private Boolean random= false;
    private int numberPistas=0;
    private int line,column =0;
    
    public ListaReproduccion(int line, int column){
        this.line = line;
        this.column = column;
    }

    public ListaReproduccion(){}

    public ArrayList<Integer> getDuracionPistas(){
        return duracionPistas;
    }

    public void setDuracionPistas(ArrayList<Integer> duracionPistas){
        this.duracionPistas = duracionPistas;
    }

    public void addPistas(ArrayList<PistaReproduccion> thePistas){
        for(PistaReproduccion pista:thePistas){
            pistas.add(pista.getName());
            duracionPistas.add(pista.getDuracion());
            numberPistas++;
        }
    }


    public void setNoPistas(int psitas){
        this.numberPistas = psitas;
    }

    public int getNumberPistas(){
        return numberPistas;
    }



    public int getLine(){
        return line;
    }
    
    public int getColumn(){
        return column;
    }
    
    public void deletePista(String namePista){
        for(int index=0; index<pistas.size(); index++){
            if(pistas.get(index).equals(namePista)){
                pistas.remove(index);
                break;
            }
        }
    }

    
    public String getNombre() {
        if(nombre==null){
            return "";
        }
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getPistas() {
        if(pistas==null){
            return new ArrayList<String>();
        }
        return pistas;
    }

    public void setPistas(ArrayList<String> pistas) {


            this.pistas = pistas;
    }

    public Boolean getCircular() {
        if(circular==null){
            return false;
        }
        return circular;
    }

    public void setCircular(Boolean circular) {
        this.circular = circular;
    }

    public Boolean getRandom() {
        if(random == null){
            return false;
        }
        return random;
    }

    public void setRandom(Boolean random) {
        this.random = random;
    }
    
    
    
    
}
