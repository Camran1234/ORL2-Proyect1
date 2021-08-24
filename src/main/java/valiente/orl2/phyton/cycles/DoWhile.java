/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.cycles;

import valiente.orl2.phyton.instructions.Instruction;

/**
 *
 * @author camran1234
 */
public class DoWhile extends Instruction{
    While conditionWhile;
    
    public DoWhile(int line, int column){
        super(line, column);
    }

    public While getConditionWhile() {
        return conditionWhile;
    }

    public void setConditionWhile(While conditionWhile) {
        this.conditionWhile = conditionWhile;
    }
    
    
    
}
