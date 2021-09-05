/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import valiente.orl2.phyton.error.LoopException;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.instructions.Instruction;

/**
 *
 * @author camran1234
 */
public class Continue extends Instruction{
   
    
    public Continue(int indentation, int line, int column){
        super(indentation, line, column);
    }
    

    
    public void execute() throws SemanticException{            
        if(this.lookForFatherExit()){        
            throw new LoopException("NobodyYouDidGreat!!!",true, getLine(), getColumn());            
        }
        
    }
    
}
