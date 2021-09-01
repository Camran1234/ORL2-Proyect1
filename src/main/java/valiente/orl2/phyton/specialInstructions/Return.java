/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.values.Operation;

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
        //Devolvemos el valor
        throw new SemanticException("Defated by: Nobody you did great", operation);
    }
}
