/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.values.LogicalOperator;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

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
    
    public Comparation(boolean unary, Operation value, int line, int column){
        this.unary = unary;
        this.value = value;
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
            Value variable = comparation.execute().execute();
            Value result = null;
            if(!variable.getRawType().equalsIgnoreCase("variable")){
                
            }
            if(variable.getValue()==null){
                result = new Value("boolean","true",line, column);
            }else{
                result = new Value("boolean","false",line, column);
            }
            return new Operation(result, line, column);
        }else if(value!=null){
            Operation operation = value;
            Value valor = operation.execute();
            if(unary){
                if(valor.getRawType().equalsIgnoreCase("variable")==false){
                    throw new ValueException("El valor referenciado no es una variable","Operador nulo mal establecido", line, column);
                }
                if(valor.getValue()==null){
                    operation = new Operation(new Value("boolean","true", value.getLine(), value.getColumn()), operation.getLine(), operation.getColumn());
                }else{
                    operation = new Operation(new Value("boolean","false", value.getLine(), value.getColumn()), operation.getLine(), operation.getColumn());
                }
            }
            return operation;
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
