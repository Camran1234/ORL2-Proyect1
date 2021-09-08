/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.central;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Pista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;
import valiente.orl2.phyton.instructions.Pista;
import valiente.orl2.proyecto1.PhytonFrame;
import valiente.orl2.reproduccion.ListaReproduccion;
import valiente.orl2.reproduccion.PistaReproduccion;
import valiente.orl2.reproduccion.PlayList;
/**
 *
 * @author camran1234
 */
public class Central implements Serializable{
    ArrayList<PistaReproduccion> pistas = new ArrayList();
    PlayList playList = new PlayList();
    private final String directory = "./data";
    private final String path = directory+"/"+"archivo.bin";
    public Central(){
        downloadChanges();
    }
    
    public PlayList getPlayList(){
        return playList;
    }
    
    /**
     * Funcion para eliminar pista devuelve true si elimino la pista, devuelve false si no logro eliminar la pista
     * @param nombrePista
     * @return 
     */
    public boolean deletePista(String nombrePista){
        for(int index=0; index<pistas.size(); index++){
            PistaReproduccion pista = pistas.get(index);
            if(pista.getName().equals(nombrePista)){
                pistas.remove(index);
                removePistaFromListas(nombrePista);
                uploadChanges();
                return true;
            }
        }
        return false;
    }
    
    public PistaReproduccion getPistaReproduccion(String nombrePista){
        for(PistaReproduccion pista:pistas){
            if(pista.getName().equals(nombrePista)){
                return pista;
            }
        }
        return null;
    }
    
    public ListaReproduccion getListaReproduccion(String nombreLista){
        
        for(ListaReproduccion lista:playList.getlistas()){
            if(lista.getNombre().equals(nombreLista)){
                return lista;
            }
        }
        return null;
    }
    
    /**
     * Busca eliminar la pista de todas las listas
     * @param nombrePista 
     */
    private void removePistaFromListas(String nombrePista){    
        ArrayList<ListaReproduccion> listas = playList.getlistas();
        for(ListaReproduccion lista:listas){
            lista.deletePista(nombrePista);
        }
    }
    
    /**
     * Funcion para eliminar una lista devuevle true si elimino la pi
     * @param nombreLista
     * @return 
     */
    public boolean deleteLista(String nombreLista){
        ArrayList<ListaReproduccion> listas = playList.getlistas();
        for(int index=0; index<listas.size(); index++){
            ListaReproduccion lista = listas.get(index);
            if(lista.getNombre().equals(nombreLista)){
                listas.remove(index);
                uploadChanges();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obtiene el codigo de la pista indicada
     * @param nombre
     * @return 
     */
    public String getTextPista(String nombre){
        String resultado="";
        try {
            for(PistaReproduccion pista:pistas){
                if(pista.getName().equals(nombre)){
                    resultado = pista.getText();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    
    /**
     * Obtiene el codigo de la lista indicada
     * @param nombre
     * @return 
     */
    public String getTextLista(String nombre){
        String resultado ="";
        ArrayList<ListaReproduccion> listas = playList.getlistas();
        try {
            for(ListaReproduccion lista: listas){
                if(lista.getNombre().equals(nombre)){
                    resultado = lista.getText();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
    
    public void updateListas(PlayList list){
        ArrayList<ListaReproduccion> listaReproduccion = list.getlistas();
        tryAddList(listaReproduccion);
        uploadChanges();
    }
    
    public void tryAddList(ArrayList<ListaReproduccion> lista){
        ArrayList<ListaReproduccion> localLista = playList.getlistas();
        ArrayList<ListaReproduccion> aux = new ArrayList();
        for(int index=0; index<lista.size(); index++){
            String nameLista = lista.get(index).getNombre();
            boolean flag=false;
            for(int indexLista=0; indexLista<localLista.size(); indexLista++){
                if(nameLista.equals(localLista.get(indexLista).getNombre())){
                    boolean entry=confirmRewrite(nameLista,"lista");                     
                    if(entry){
                        aux.add(lista.get(index));
                    }else{
                        aux.add(localLista.get(indexLista));
                    }
                    flag=true;
                }
            }
            if(!flag){
                aux.add(lista.get(index));
            }
        }
        //Agregamos los valores viejos
        for(int index=0; index<localLista.size(); index++){
            String name = localLista.get(index).getNombre();
            boolean flag=false;
            for(int indexPista=0; indexPista<aux.size(); indexPista++){
                String newListaName = aux.get(indexPista).getNombre();
                if(name.equals(newListaName)){
                    flag=true;
                }
            }
            if(!flag){
                aux.add(localLista.get(index));
            }
        }
        
        this.playList.setLista(aux);
    }
    
    /**
     * Actualiza los valores de la pista
     * @param instructions 
     */
    public void updatePistas(ArrayList<PistaReproduccion> instructions){
        ArrayList<PistaReproduccion> list = new ArrayList();
        for(int index=0; index<instructions.size(); index++){
            PistaReproduccion pista = instructions.get(index);
            list.add(pista);
        }
        tryAddPista(list);
        uploadChanges();
    }
    
    public boolean confirmRewrite(String namePista, String formato){
        boolean entry=false;
        int n = JOptionPane.showConfirmDialog(
                                PhytonFrame.frame, "Quieres sobreescribir la "+formato+" "+namePista,
                                "Sobreescribir",
                                JOptionPane.YES_NO_OPTION);                    
        if (n == JOptionPane.YES_OPTION) {        
            entry=true;            
        } else if (n == JOptionPane.NO_OPTION) {
            entry=false;           
        }
        return entry;
    }

    
    public void tryAddPista(ArrayList<PistaReproduccion> list){
        ArrayList<PistaReproduccion> newLista = new ArrayList();
        //Agregamos los valores nuevos
        for(int index=0; index<list.size(); index++){
            String namePista = list.get(index).getName();
            boolean flag = false;
            for(int indexPista=0; indexPista<pistas.size(); indexPista++){
                if(namePista.equals(pistas.get(indexPista).getName())){
                    boolean entry=confirmRewrite(namePista,"Pista");                     
                    if(entry){
                        newLista.add(list.get(index));
                    }else{
                        newLista.add(pistas.get(indexPista));
                    }
                    flag=true;
                }
            }
            if(!flag){
                newLista.add(list.get(index));
            }
        }
        //Agregamos los valores viejos
        for(int index=0; index<pistas.size(); index++){
            String name = pistas.get(index).getName();
            boolean flag=false;
            for(int indexPista=0; indexPista<newLista.size(); indexPista++){
                String newListaName = newLista.get(indexPista).getName();
                if(name.equals(newListaName)){
                    flag=true;
                }
            }
            if(!flag){
                newLista.add(pistas.get(index));
            }
        }
        pistas = newLista;
    }
    
    
    private void uploadChanges(){
        try(ObjectOutputStream outPutStream = new ObjectOutputStream(new FileOutputStream(path))){
            outPutStream.writeObject(this);
            JOptionPane.showMessageDialog(PhytonFrame.frame, "Se compilo correctamente", "AVISO",JOptionPane.WARNING_MESSAGE);
        }catch(Throwable e){
            e.printStackTrace();
        } 
    }
    
    private void directoryExist(){
        File file = new File(directory);
        if(!file.exists()){
            file.mkdirs();
        }
    }
    
    private boolean fileExist(){
        File file = new File(path);
        if(!file.exists()){
            return false;
        }
        return true;
    }
    
    private void downloadChanges(){
        directoryExist();
        if(fileExist()){              
            try(ObjectInputStream inPutStream = new ObjectInputStream(new FileInputStream(path))){
                Object lista = inPutStream.readObject();
                this.pistas = ((Central)lista).getPistas(); 
                this.playList = ((Central)lista).getPlayList();
            }catch(Exception e){
                JOptionPane.showMessageDialog(PhytonFrame.frame, "Error archivos corruptos, no se pudo recuperar");
                e.printStackTrace();
            }
        }else{
            this.pistas = new ArrayList();
        }
    }
    
    /**
     * Son las canciones en general
     * @return 
     */
    public ArrayList<PistaReproduccion> getPistas(){
        return this.pistas;
    }
    
}
