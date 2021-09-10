/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

/**
 * Una forma mas comoda de agregar los parametros a las variables
 * @author camran1234
 */
public class Assignment {
    int line,column=0;
    /*Para variables primitivas*/
    Operation value;
    /*Si aumenta o se iguala el valor*/
    // --, ++, +=, =
    String metodo="=";
    Assignment assignment=null;
    //Esto lo enviamos para asignar valores al arreglo
    ArrayList<Integer> directionToAssign = new ArrayList();
    
    
    public Assignment(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    public void setDirectionToAssign(ArrayList<Integer> direction){
        this.directionToAssign = direction;
        if(assignment!=null){
            assignment.setDirectionToAssign(direction);
        }
    }
    
    public ArrayList<Integer> getDirectionToAssign(){
        return this.directionToAssign;
    }
        
    public void setAssignment(Assignment assignment){
        this.assignment = assignment;
    }
    
    /**
     *Retorna 0 para variables
     * Retorna 1 para arreglos
     * Retorna 2 para incrementos
     * @return 
     */
    public int selectMethod(){
        if(getDimension()!=null || directionToAssign.size()>0){
            return 1;
        } else if(value!=null){
            return 0;
        } else{
            return 2;
        }
    }
    
    public Value getValueFromOperation(){
        return value.execute();
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public Operation getValue() {
        return value;
    }

    public void setValue(Operation value) {
        this.value = value;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Dimension getDimension(){
        if(value==null){
            return null;
        }
        if(value.isArray()){
            return value.execute().getArray();
        } 
        return null;
    }

    ArrayList<Assignment> getAsignaciones() {
        ArrayList<Assignment> lista = new ArrayList();
        lista.add(this);
        if(assignment!=null){
            ArrayList<Assignment> aux = assignment.getAsignaciones();
            for(int index=0; index<aux.size(); index++){
                lista.add(aux.get(index));
            }
        }
        return lista;
    }
    
    
    
}
