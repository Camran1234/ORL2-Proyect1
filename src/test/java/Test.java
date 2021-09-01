/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import valiente.orl2.phyton.instructions.Function;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.conditions.ElseIf;
import valiente.orl2.phyton.error.LoopException;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
/**
 *
 * @author camran1234
 */
public class Test {
    
    public static void main (String args[]){
        int[] newArray = new int[2];
        System.out.println(newArray.length);
        try {
            int[] array = new int[4];
            array[0] = 2;
            array[1] = 4;
            array[2] = 3;
            array[3] = 6;
            for(int index=0; index<=array.length; index++){
                try {
                    execute(array[index]);
                } catch (LoopException e) {
                    System.out.println("LoopException" + e.getMessage());
                }
            }
            
        } catch (SemanticException e) {
            System.out.println("SemantixException"+e.getMessage());
        }
        
    }
    
    
    public static void execute(int x) throws SemanticException{
        if(x%2 == 0){
            throw new LoopException("Hello",true);
        }else{
            throw new SemanticException("ASD");
        }
    }
    
}
