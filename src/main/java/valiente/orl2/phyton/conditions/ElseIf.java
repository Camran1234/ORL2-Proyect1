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
public class ElseIf extends If{
    Condition condition;

    public ElseIf(Condition condition, int line, int column){
        super(line, column);
        this.condition = condition;
    }
    
    public ElseIf(int line, int column){
        super(line, column);
    }
    
    public void execute(){
        /*empty*/
    }
    
}
