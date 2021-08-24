/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.cycles;

import java.util.ArrayList;
import valiente.orl2.phyton.conditions.Condition;
import valiente.orl2.phyton.instructions.Instruction;

/**
 *
 * @author camran1234
 */
public class While extends Instruction{
    Condition condition;
    
    public While(int line, int column) {
        super(line, column);
        this.condition = condition;
    }
    
    public void execute(){
        
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    
}
