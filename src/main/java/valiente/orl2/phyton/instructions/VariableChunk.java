/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;

/**
 *
 * @author camran1234
 */
public class VariableChunk extends Instruction{
    ArrayList<Variable> variables;
    
    
    public VariableChunk(ArrayList<Variable> list, Assignment assignment){
        for(int index=0; index<list.size(); index++){
            list.get(index).setValue(assignment);
        }
    }
    
}
