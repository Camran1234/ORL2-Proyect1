/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import valiente.orl2.phyton.values.Operation;

/**
 *
 * @author camran1234
 */
public class Comparation {
    private int line,column=0;
    
    private Operation leftValue;
    private String operador;
    private Operation rightValue;
    
    private Operation value;
    
    public Comparation(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    public Comparation (Operation value, int line, int column){
           this.value = value;
           this.line = line;
           this.column = column;
    }
    
    public Comparation(Operation leftValue, Operation rightValue, String operador, int line, int column){
           this.leftValue = leftValue;
           this.rightValue = rightValue;
           this.operador = operador;
           this.line = line;
           this.column = column;
    }
    
    public Operation execute(){
        return null;
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

    public Operation getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(Operation leftValue) {
        this.leftValue = leftValue;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
         this.operador = operador;
    }

    public Operation getRightValue() {
        return rightValue;
    }

    public void setRightValue(Operation rightValue) {
        this.rightValue = rightValue;
    }

    public Operation getValue() {
        return value;
    }

    public void setValue(Operation value) {
        this.value = value;
    }
    
    
    


    
}
