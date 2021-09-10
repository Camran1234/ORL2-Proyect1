/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;
import valiente.orl2.phyton.values.Operation;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.conditions.Condition;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.table.Symbol;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Value;
/**
 *
 * @author camran1234
 */
public class ThrowFunction extends Instruction{
    
    //parametros de la funcion
    ArrayList<Operation> parameters = new ArrayList();
    String id = "";
    int line,column=0;
    Value value=null;
    
    public ThrowFunction(String id, int line, int column){
        this.id = id;
        this.line = line;
        this.column = column;
        
    }

    /**
     * We sent the parameters to the function and do the semantic validation
     * @throws SemanticException 
     */
    public void execute() throws SemanticException{
        Symbol previousSymbol = TableOfValue.getWorkingSymbol();
        Instruction container = TableOfValue.getContainer();
        Function function = (Function) TableOfValue.getFunctionOfSymbol(id, parameters);
        if(function==null){
            throw new SemanticException("No se encontro la funcion", "funcion no declarada", line, column);
        }
        ArrayList<Assignment> asignaciones = new ArrayList();
        for(int index=0; index<parameters.size(); index++){
            Assignment assignment = new Assignment(line, column);
            assignment.setValue(parameters.get(index));
            asignaciones.add(assignment);
        }
        //ArrayList<Symbol> lista = TableOfValue.getSymbolListAfterFunctions();
        
        //Eliminamos todas las variables debajo de als funciones
        try {
            function.setAssignments(asignaciones);
            
            if(function.getType().equalsIgnoreCase("void")){
                function.declararParametros();
                function.execute();
            }else{                
                this.value = function.getValue();
            }
        } catch (SemanticException e) {
            SemanticError error = new SemanticError("Error en funcion", line, column);
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
        
        TableOfValue.setWorkingSymbol(previousSymbol);
        TableOfValue.setContainer(container);
    }
    
    public Value getValue(){
        try {
            this.execute();
            return value;
        } catch (SemanticException ex) {
            TableOfValue.semanticErrors.add(ex.getError());
        }
        return null;
    }
    
    public ArrayList<Operation> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Operation> parameters) {
        this.parameters = parameters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
