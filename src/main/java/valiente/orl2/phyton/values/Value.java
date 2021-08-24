/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;

/**
 * tipos: entero, doble, boolean, caracter, cadena, arreglo, funcion como pista, principal. roc, etc, variable, nota0
 * Encargada de almacenar los datos, su tipo
 * @author camran1234
 */
public class Value {
    private int line, column=0;
    private String type="";
    private String value="";
    private ArrayList<Operation> dimension = new ArrayList();
    private Instruction specialValue; //Reserved for function
    
    public Value(String type, Instruction specialValue){
        this.type = type;
        this.specialValue = specialValue;
    }
    
    
    public Value(String type, String value){
        this.type=type;
        this.value=value;
    }
    
    public Value(String value, ArrayList<Operation> dimension){
        this.type="variable";
        this.value=value;
        this.dimension = dimension;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
