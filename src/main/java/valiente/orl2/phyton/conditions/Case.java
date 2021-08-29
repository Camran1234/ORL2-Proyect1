/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.values.Operation;


/**
 *
 * @author camran1234
 */
public class Case extends Instruction{
    private Operation value;
    
    
    public Case(int indentation, int line, int column){
        super(indentation, line, column);
    }

    public Operation getValue() {
        return value;
    }

    public void setValue(Operation value) {
        this.value = value;
    }
    
    
    
    public void execute(){
        /*empty*/
    }
}
