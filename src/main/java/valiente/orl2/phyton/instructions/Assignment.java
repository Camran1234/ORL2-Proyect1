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
    
    /*Para arreglos*/
    Dimension dimension = null;
    
    
    public Assignment(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    
    /**
     *Retorna 0 para variables
     * Retorna 1 para arreglos
     * Retorna 2 para incrementos
     * @return 
     */
    public int selectMethod(){
        if(value!=null){
            return 0;
        }else if(dimension!=null){
            return 1;
        }else{
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
        return dimension;
    }
    
    public void setDimension(Dimension dimension){
        this.dimension = dimension;
    }
    
}
