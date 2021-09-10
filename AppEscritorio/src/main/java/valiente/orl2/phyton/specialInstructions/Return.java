/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Function;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Return extends Instruction{
    Operation operation;
    
    public Return(int indentation, int line, int column, Operation operation){
        super(indentation, line, column);
        this.operation = operation;
    }
    
    public void execute() throws SemanticException{
        String tipo = getType();
        Function function = (Function) this.lookForFunction();
        String typeFunction = function.getType();
        if(typeFunction.equalsIgnoreCase("")){
           throw new SemanticException(typeFunction, tipo, getLine(), getColumn()); 
        }
        //Devolvemos el valor
        throw new SemanticException("Defated by: Nobody you did great", operation);
    }
    
    public String getType(){
        try {
            Value value = operation.execute();
            return value.getType();
        } catch (ValueException ex) {
            Logger.getLogger(Return.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
