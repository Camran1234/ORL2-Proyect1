/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import valiente.orl2.phyton.instructions.Function;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.conditions.ElseIf;
/**
 *
 * @author camran1234
 */
public class Test {
    
    public static void main (String args[]){
        ElseIf elseIf = new ElseIf(0,0);
        try {
            if(elseIf instanceof Instruction){
                System.out.println("true");
            }else{
                System.out.println("false");
            }
        } catch (Exception e) {
        }
        
    }
    
}
