/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Assignment;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Variable;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Parameter {
    private String type="";
    private boolean isArray=false;
    private String id="";
    private Operation value;
    private Instruction father;
    private ArrayList<Operation> dimension;
    private int indentation=0;
    private int line, column=0;
    private Variable variable;
    private Assignment assignment;
    
    public Parameter(String type, String id){
        this.type = type;
        this.id = id;
    }
    
    public Parameter(Value value){
        this.type = value.getRawType();
        this.id = value.getRawValue();
        if(isArray){
            ArrayList<Operation> operations = value.getDimensions();
            this.dimension = operations;
        }
    }
    
    public boolean compareType(String type){
        if(this.type.equalsIgnoreCase(type)){
            return true;
        }else{
            return false;
        }
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsArray() {
        return isArray;
    }

    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }

    public Operation getValue() {
        return value;
    }

    public void setValue(Operation value) {
        this.value = value;
    }
    
    public void declarar(){
        try {
            Variable variable = new Variable(line, column);
            variable.setName(id);
            variable.setType(type);
            variable.setDimension(dimension);
            variable.setValue(assignment);
            this.variable = variable;
            variable.declarar();
        } catch (Exception e) {
        }
    }
    
    public void setAssignment(Assignment asignacion){
        this.assignment = assignment;
    }

    public Instruction getFather() {
        return father;
    }

    public void setFather(Instruction father) {
        this.father = father;
    }
    
    public void setIndentation(int indentation){
        this.indentation = indentation;
    }
    
    public int getIndentation(){
        return this.indentation;
    }

    public void setLineColumn(int line, int column) {
        this.line = line;
        this.column = column;
    }
   
    
}
