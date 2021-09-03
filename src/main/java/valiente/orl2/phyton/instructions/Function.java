/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.table.Symbol;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.table.Parameter;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.table.Type;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.TypeParser;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Function extends Instruction{
    private boolean mode;
    private Value name;
    private ArrayList<Parameter> parameters=new ArrayList();
    //Dimension of the value to return
    //El tipo a retornar de la funcion
    private String type="void";
    private int dimension = 0;
    //The result to return
    private Value value;
    
    
    public Function(int line, int column){
        super(line, column);
    }
    
    public void setAssignments(ArrayList<Assignment> asignaciones){
        for(int index=0; index<parameters.size(); index++){
            parameters.get(index).setAssignment(asignaciones.get(index));
        }
    }
    
    @Override
    public void execute()  throws SemanticException{
        try {
            for(int index=0; index<instructions.size(); index++){
                instructions.get(index).execute();
            }
            TableOfValue.deleteAmbit(this.indentation+1);
        } catch (SemanticException e) {
            if(e.isReturn()){
                try {
                    TypeParser parser = new TypeParser();
                    Value value = parser.tryParse(e.getOperation().execute(), this.type, line, column);
                    this.value = value;
                } catch (Exception ex) {
                }
            }
        }
    }
    @Override
    public void declarar(){
        try {
            Type type = new Type(name.getValue(),this.type, parameters.size(), parameters, this.lookForContainer(), new ArrayList<Integer>(),
            this.getIndentation(), this);
            type.setFunction(true);
            Symbol symbol = new Symbol(type, indentation,mode );
            TableOfValue.addSymbol(symbol, line, column);
        } catch (Exception e) {
            SemanticError error = new SemanticError("Funcion no declarada", getLine(), getColumn());
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    public void setValueToParameters(ArrayList<Operation> operation){
        for(int index=0; index<parameters.size(); index++){
            parameters.get(index).setValue(operation.get(index));
            parameters.get(index).setFather(this);
            parameters.get(index).setIndentation(indentation+1);
            parameters.get(index).setLineColumn(line, column);
            parameters.get(index).declarar();
        }
    }
    
    /**
     * Get the value after the execute
     * @return 
     */
    public Value getValue() throws SemanticException{
        this.execute();
        Value value = this.value;
        this.value = null;
        return value;
    }
    
    public void reset(){
        this.value = null;
    }
    

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public Value getName() {
        return name;
    }

    public void setName(Value name) {
        this.name = name;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Operation> parameters) {
        for(int index=0; index<parameters.size(); index++){
            Value value = parameters.get(index).execute();
            Parameter parameter = new Parameter(value);
            this.parameters.add(parameter);
        }
    }

    public void setParamsIndicator(VariableIndicator indicator){
        this.type = indicator.getType();
        this.dimension = indicator.getSize();
        this.mode = indicator.getGlobal();
    }
    
    

    
}
