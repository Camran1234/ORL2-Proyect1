/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.table.TableOfValue;

/**
 *
 * @author camran1234
 */
public class Principal extends Instruction{
    
    public Principal(int line, int column){
        super(line, column);
    }
 
    @Override
    public void execute() throws SemanticException{
        try {
            for(int index=0; index<instructions.size(); index++){
                instructions.get(index).execute();
            }
            TableOfValue.deleteAmbit(this.indentation+1);
        } catch (SemanticException e) {
            e.checkErrorAmbit();
        }
        
    }
    
}
