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
public class If extends Instruction{
    ArrayList<ElseIf> elseifs = new ArrayList();
    ArrayList<Else> elses = new ArrayList();
    Condition condition;
    
    public If(Condition condition ,int line, int column) {
        super(line, column);
        this.condition = condition;
    }
    
        
    
    
    
    public void execute(){
        /*empty*/
    }
    
}
