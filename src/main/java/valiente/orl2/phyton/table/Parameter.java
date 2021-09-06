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
import valiente.orl2.phyton.values.TypeParser;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.instructions.Assignment;
import valiente.orl2.phyton.instructions.Dimension;
/**
 *
 * @author camran1234
 */
public class Parameter {
    private String type="";
    private boolean isArray=false;
    private String id="";
    private Value value;
    private Instruction father;
    private String functionName="";
    private ArrayList<Operation> dimension;
    private int dimensiones=0;
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
        if(value.isParameter()){
            try {
                dimensiones = Integer.parseInt(value.getValue());
            } catch (ValueException ex) {
                Logger.getLogger(Parameter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setFunctionName(String name){
        this.functionName = name;
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
    
    public void declarar(){
        try {
            TableOfValue.setWorkingInParameter(true);
            Value value = assignment.getValueFromOperation();
                Variable variable = new Variable(line, column);
                variable.setName(id);
                variable.setType(type);
                if(dimensiones>0){
                    ArrayList<Integer> enteros = TableOfValue.getSymbol(value.getRawValue(), value.getRawType()).getReference().getDimension();
                    ArrayList<Operation> operations  = new ArrayList();
                    for(int index=0; index<enteros.size(); index++){
                        String valor = Integer.toString(enteros.get(index));
                        Value newValue = new Value("entero", valor , line, column);
                        Operation op = new Operation(newValue, line, column);
                        operations.add(op);
                    }
                    variable.setDimension(operations);
                }else{
                    if(dimension!=null){
                        variable.setDimension(dimension);
                    }
                }
                
                Assignment assignment = new Assignment(line, column);
                assignment.setValue(new Operation(value, line, column));
                variable.setValue(assignment);
                variable.setDeclarado(true);
                variable.setFather(father);
                this.variable = variable;
                variable.declarar();
                TableOfValue.setWorkingInParameter(false);
            
            
        } catch (Exception ex) {
            SemanticError error = new SemanticError("Error de asignacion", line, column);
            error.setDescription(ex.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    public void setAssignment(Assignment asignacion){
        this.assignment = asignacion;
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
