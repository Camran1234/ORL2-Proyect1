/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.values.Operation;

/**
 * Una forma mas comoda de agregar los parametros a las variables
 * @author camran1234
 */
public class Assignment {
    int line,column=0;
    /*Para variables primitivas*/
    Operation value;
    /*Si aumenta o se iguala el valor*/
    // ==, +=, ++
    String metodo="";
    
    /*Para arreglos*/
    Dimension dimension = null;
    
    
    public Assignment(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    public void execute(){
        
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
