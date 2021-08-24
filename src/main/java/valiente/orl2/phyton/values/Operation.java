/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

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
    
    
    
    public void execute(){
        /*empty*/
    }
}
