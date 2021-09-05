/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

import java.util.ArrayList;
import valiente.orl2.phyton.conditions.Condition;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.instructions.Function;
import valiente.orl2.phyton.instructions.ThrowFunction;
import valiente.orl2.phyton.table.TableOfValue;

/**
 *
 * @author camran1234
 */
public class Operation {
    int line,column=0;
    Operation leftValue;
    Operation rightValue;
    String operator="";
    
    Value value;
    Condition condition;
    ThrowFunction function;
    boolean isArray=false;
    boolean isMessage=false;
    
    
    public Operation(Operation leftValue, Operation rightValue, String operator, int line, int column){
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operator = operator;
        this.line = line;
        this.column = column;
    }

    public Operation(Value value, int line, int column){
        this.value = value;
        this.line= line;
        this.column= column;
        if(value.isArray()){
            isArray=true;
        }
    }
    
    public Operation(ThrowFunction function, int line, int column){
        this.function = function;
        this.line= line;
        this.column= column;
    }
    
    public Operation(Condition condition, int line, int column){
        this.condition = condition;
        this.line= line;
        this.column= column;
    }
    
    public void setMessage(boolean message){
        this.isMessage = message;
    }
    
    public boolean isArray(){
        return isArray;
    }
    
    public Value execute(){
        try {
            if(leftValue!=null && rightValue!=null){
                Value left = leftValue.execute();
                Value right = rightValue.execute();
                if(left.getRawType().equalsIgnoreCase("specialFunction")){
                    left = left.getRefinatedValue();
                }
                if(right.getRawType().equalsIgnoreCase("specialFunction")){
                    right = right.getRefinatedValue();
                }
                
                ArithmeticOperator arithmeticOperator = new ArithmeticOperator();
                
                return arithmeticOperator.MakeOperation(operator, left, right, line, column);
            }else if(condition!=null){
                Operation result = condition.execute();
                return result.execute();
            }else if(function!=null){
                Value result = function.getValue();
                return result;
            }else if(value!=null){
                return this.value;
            }
            SemanticError newError = new SemanticError("No se encontro el valor", line, column);
            newError.setDescription("No existe ningun valor dentro de la operacion");
            newError.setSolution("revise bien su entrada");
            TableOfValue.semanticErrors.add(newError);
        } catch (Exception e) {
            SemanticError newError = new SemanticError("Error en operacion", line, column);
            newError.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(newError);
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
    
    
    
}
