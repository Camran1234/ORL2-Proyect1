/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.reproduccion;

import java.io.Serializable;
import java.util.ArrayList;
import valiente.orl2.Lista.Manejador.ListaAnalyzer;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SyntaxError;

/**
 *
 * @author camran1234
 */
public class ListaReproduccion implements Serializable{
    private String nombre=null;
    private ArrayList<String> pistas = null;
    private Boolean circular=null;
    private Boolean random=null;
    private int line,column =0;
    private String text="";
    
    public ListaReproduccion(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    public int getLine(){
        return line;
    }
    
    public int getColumn(){
        return column;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public String getText(){
        return text;
    }
    
    public void deletePista(String namePista){
        for(int index=0; index<pistas.size(); index++){
            if(pistas.get(index).equals(namePista)){
                pistas.remove(index);
                break;
            }
        }
    }
    
    private void addError(String description, int line, int column){
        SemanticError error = new SemanticError("Valor repetido", line, column);
        error.setDescription(description);
        ListaAnalyzer.semanticErrors.add(error);
        
    }
    
    public String getNombre() {
        if(nombre==null){
            return "";
        }
        return nombre;
    }

    public void setNombre(String nombre, int line, int column) {
        if(this.nombre==null){
            this.nombre = nombre;
        }else{
            addError("Nombre colocado "+nombre, line, column);
        }
        
    }

    public ArrayList<String> getPistas() {
        if(pistas==null){
            return new ArrayList<String>();
        }
        return pistas;
    }

    public void setPistas(ArrayList<String> pistas, int line, int column) {
        if(this.pistas==null){
            this.pistas = pistas;
        }else{
            StringBuilder string = new StringBuilder();
            for(String pista:pistas){
                string.append(pista).append(" | ");
            }
            addError("Pistas colocadas "+string.toString(), line, column);
        }
        
    }

    public Boolean getCircular() {
        if(circular==null){
            return false;
        }
        return circular;
    }

    public void setCircular(Boolean circular, int line, int column) {
        if(this.circular==null){
            this.circular = circular;
        }else{
            addError("Opcion colocada en circular: "+Boolean.toString(circular), line, column);
        }
        
    }

    public Boolean getRandom() {
        if(random == null){
            return false;
        }
        return random;
    }

    public void setRandom(Boolean random, int line, int column) {
        if(this.random==null){
            this.random = random;
        }else{
            addError("Opcion colocada en random: "+Boolean.toString(circular), line, column);
        }
    }
    
    
    
    
}
