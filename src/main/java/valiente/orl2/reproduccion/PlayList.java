/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.reproduccion;

import java.io.Serializable;
import java.util.ArrayList;
import valiente.orl2.Lista.Manejador.ListaAnalyzer;
import valiente.orl2.phyton.error.ListaException;
import valiente.orl2.phyton.instructions.Pista;

/**
 *
 * @author camran1234
 */
public class PlayList implements Serializable{
    private ArrayList<ListaReproduccion> listas = new ArrayList();
    private String text="";
            
    public void addLista(ListaReproduccion lista){
        listas.add(lista);
    }
    
    public void setText(String text){
        for(ListaReproduccion lista: listas){
            lista.setText(text);
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
            try {
                compareNombre(lista);
                comparePistas(lista, pistas);
            } catch (ListaException e) {
                ListaAnalyzer.semanticErrors.add(e.getError());
            }
            
        }
    }

    private void compareNombre(ListaReproduccion lista) throws ListaException{
        for(ListaReproduccion list:listas){
            if(list.getNombre().equals(lista.getNombre())){
                throw new ListaException("Nombre repetido","Cambiar de nombre a la lista "+list.getNombre(), lista.getLine(), lista.getColumn());
            }
        }
    }

    private void comparePistas(ListaReproduccion lista, ArrayList<PistaReproduccion> pistas){
        ArrayList<String> nombresPistas = lista.getPistas();
        boolean founded=false;
        for(int index=0; index<nombresPistas.size(); index++){
            try {
                String comparacion = nombresPistas.get(index);
                for(PistaReproduccion pista:pistas){
                    if(comparacion.equals(pista.getName())){
                        founded=true;
                    }
                }
                if(!founded){
                    throw new ListaException("Pista no existe","La pista "+comparacion+" no existe", lista.getLine(), lista.getColumn());
                }
            } catch (ListaException e) {
                ListaAnalyzer.semanticErrors.add(e.getError());
            }
        }
        
    }
    
}
