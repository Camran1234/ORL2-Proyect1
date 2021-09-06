/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
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
        char a = 'a';
        int b = 97;
        if(a == b){
            System.out.println("YES");
        }
        System.out.println(((double)a/b)+" =" +a+", "+b);
    }
    
    public static void xD(){
        
    }
    
    
}
