/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orl.pistas;

import java.util.ArrayList;

/**
    *Clase contenedora de las listas de reproduccion y su nombre
 * @author camran1234
 */
public class PlayList {
    private ArrayList<ListaReproduccion> listas = new ArrayList();
    private String text="";
            
    public void addLista(ListaReproduccion lista){
        listas.add(lista);
    }

    /**
     * agregar nombre y cantidad de pistas
     * @param listas
     */
    public void agregarListas(ArrayList<ListaReproduccion> listas){
        if(this.listas.size()==0){
            this.listas = listas;
        }else{
            for(int index=0; index<listas.size(); index++){
                boolean flag=false;
                for(int indexLista=0; indexLista<this.listas.size(); indexLista++){
                    if(listas.get(index).getNombre().equals(this.listas.get(indexLista).getNombre())){
                        String nombre = listas.get(index).getNombre();
                        int noPistas = listas.get(index).getNumberPistas();
                        this.listas.get(indexLista).setNombre(nombre);
                        this.listas.get(indexLista).setNoPistas(noPistas);
                        this.listas.get(indexLista).setRandom(listas.get(index).getRandom());
                        this.listas.get(indexLista).setCircular(listas.get(index).getCircular());
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                    this.listas.add(listas.get(index));
                }

            }
        }
    }

    /**
     * Introducimos random, ciruclar y pistas
     * @param lista
     */
    public void agregarLista(ListaReproduccion lista){
        boolean flag=false;

        for(int index=0; index< listas.size(); index++){
            if(listas.get(index).getNombre().equals(lista.getNombre())){
                listas.get(index).setRandom(lista.getRandom());
                listas.get(index).setCircular(lista.getCircular());
                listas.get(index).setPistas(lista.getPistas());
                listas.get(index).setDuracionPistas(lista.getDuracionPistas());
                flag=true;
            }
        }
        if(!flag){
            listas.add(lista);
        }
    }
    
    public void setLista(ArrayList<ListaReproduccion> lista){
        if(lista!=null){
            this.listas = lista;
        }
    }
    
    public ArrayList<ListaReproduccion> getlistas(){
        return listas;
    }
 
    public void compareList(ArrayList<ListaReproduccion> listaReproduccion, ArrayList<PistaReproduccion> pistas){
        //Comprobamos semanticamente los parametros
        for(ListaReproduccion lista:listaReproduccion){
            comparePistas(lista, pistas);
        }
    }

    private void comparePistas(ListaReproduccion lista, ArrayList<PistaReproduccion> pistas){
        ArrayList<String> nombresPistas = lista.getPistas();
        boolean founded=false;
        for(int index=0; index<nombresPistas.size(); index++) {
            String comparacion = nombresPistas.get(index);
            for (PistaReproduccion pista : pistas) {
                if (comparacion.equals(pista.getName())) {
                    founded = true;
                }
            }

        }
    }
    
}
