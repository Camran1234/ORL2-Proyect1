/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;

/**
 *
 * @author camran1234
 */
public class Default extends Case{
    
    public Default(int indentation, int line, int column) {
        super(indentation, line, column);
    }
    
    public void execute(){
        /*empty*/
    }
    
    public ArrayList<Instruction> getInstructions(){
        return this.instructions;
    }
    
    public void setInstructions(ArrayList<Instruction> instructions){
        this.instructions = instructions;
    }
    
}
