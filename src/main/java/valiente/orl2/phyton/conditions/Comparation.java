/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.values.LogicalOperator;
import valiente.orl2.phyton.values.Operation;

/**
 * Para realizar comparaciones con los simbolos ==, !=, 
 * @author camran1234
 */
public class Comparation {
    private int line,column=0;
    
    private Comparation leftValue;
    private String operador;
    private Comparation rightValue;
    
    private boolean unary;
    private Comparation comparation;
    
    private Operation value;
    
    public Comparation(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    public Comparation(boolean unary, Comparation comparation, int line, int column){
        this.unary = unary;
        this.comparation = comparation;
        this.line= line;
        this.column = column;
    }
    
    public Comparation (Operation value, int line, int column){
           this.value = value;
           this.line = line;
           this.column = column;
    }
    
    public Comparation(Comparation leftValue, Comparation rightValue, String operador, int line, int column){
           this.leftValue = leftValue;
           this.rightValue = rightValue;
           this.operador = operador;
           this.line = line;
           this.column = column;
    }
    
    public Operation execute() throws ValueException{
        if(leftValue!=null && rightValue!=null){
            Operation left = leftValue.execute();
            Operation right = rightValue.execute();
            return new LogicalOperator().MakeComparation(left, right, operador, line, column);
        }else if(comparation!=null){
            Operation operation  = comparation.execute();
            //logica para la comparacion            
            
        }else if(value!=null){
            return value;
        }
        
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

    public Comparation getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(Comparation leftValue) {
        this.leftValue = leftValue;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
         this.operador = operador;
    }

    public Comparation getRightValue() {
        return rightValue;
    }

    public void setRightValue(Comparation rightValue) {
        this.rightValue = rightValue;
    }

    public Operation getValue() {
        return value;
    }

    public void setValue(Operation value) {
        this.value = value;
    }
    
    
    


    
}
