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
    
    public void updateListas(PlayList list){
        ArrayList<ListaReproduccion> listaReproduccion = list.getlistas();
        tryAddList(listaReproduccion);
        uploadChanges();
    }
    
    public void tryAddList(ArrayList<ListaReproduccion> lista){
        ArrayList<ListaReproduccion> localLista = playList.getlistas();
        ArrayList<ListaReproduccion> newList = new ArrayList();
        for(int index=0; index<lista.size(); index++){
            String nameLista = lista.get(index).getNombre();
            boolean flag=false;
            for(int indexLista=0; indexLista<localLista.size(); indexLista++){
                if(nameLista.equals(localLista.get(indexLista).getNombre())){
                    boolean entry=confirmRewrite(nameLista,"lista");                     
                    if(entry){
                        newList.add(lista.get(index));
                    }else{
                        newList.add(localLista.get(index));
                    }
                    flag=true;
                }
            }
            if(!flag){
                newList.add(lista.get(index));
            }
        }
        this.playList.setLista(lista);
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
        ArrayList<PistaReproduccion> newList = new ArrayList();
        for(int index=0; index<list.size(); index++){
            String namePista = list.get(index).getName();
            boolean flag=false;
            for(int indexPista=0; indexPista<pistas.size(); indexPista++){
                if(namePista.equals(pistas.get(indexPista).getName())){
                    boolean entry=confirmRewrite(namePista,"Pista");                     
                    if(entry){
                        newList.add(list.get(index));
                    }else{
                        newList.add(pistas.get(index));
                    }
                    flag=true;
                }
            }
            if(!flag){
                newList.add(list.get(index));
            }
        }
        this.pistas = newList;
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
                this.pistas = ((Central)lista).getPistas(); ;
                this.playList = ((Central)lista).getPlayList();
            }catch(Exception e){
                JOptionPane.showMessageDialog(PhytonFrame.frame, "Error archivos corruptos, no se pudo recuperar");
                e.printStackTrace();
            }
        }else{
            this.pistas = new ArrayList();
        }
    }
    
    public ArrayList<PistaReproduccion> getPistas(){
        return this.pistas;
    }
    
}
