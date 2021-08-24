/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.cycles;

import java.util.ArrayList;
import valiente.orl2.phyton.conditions.Condition;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Variable;

/**
 *
 * @author camran1234
 */
public class For extends Instruction{
    ArrayList<Instruction> instructions = new ArrayList();
    Variable variable;
    Condition condition;
    Variable paso;
    
    public For( int line, int column) {
        super(line, column);
    }
    
    public void execute(){
        
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Variable getPaso() {
        return paso;
    }

    public void setPaso(Variable paso) {
        this.paso = paso;
    }
    
    
    
}
