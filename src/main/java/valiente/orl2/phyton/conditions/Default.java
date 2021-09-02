/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.specialInstructions.Exit;
import valiente.orl2.phyton.specialInstructions.Return;
import valiente.orl2.phyton.table.TableOfValue;

/**
 *
 * @author camran1234
 */
public class Default extends Instruction{
    
    public Default(int indentation, int line, int column) {
        super(indentation, line, column);
    }
    
    public void execute() throws SemanticException{
         try {
            for(int index=0; index<instructions.size(); index++){
                if(instructions.get(index) instanceof Return){
                    if(index!=instructions.size()-1){
                        throw new ValueException("Hay mas instrucciones despues de return","Error en caso", getLine(), getColumn());
                    }else{
                        instructions.get(index).execute();
                    }
                }else{
                    instructions.get(index).execute();
                }
            }
            TableOfValue.deleteAmbit(getIndentation()+1);
        } catch (ValueException e) {
        }
    }
    
    
}
