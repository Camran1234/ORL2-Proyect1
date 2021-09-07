/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        
        String text = "Hola Mundo";
        StringBuilder string = new StringBuilder(text);
        for(int index=0; index<4; index++){
            string.deleteCharAt(string.length()-1);
        }
        System.out.println(string.toString());
        /*int n = JOptionPane.showConfirmDialog(
                            null, "Would you like green eggs and ham?",
                            "An Inane Question",
                            JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {

        } else if (n == JOptionPane.NO_OPTION) {

        } else {

        }*/
        
        /*String directory="./data/archivo";
        String fileName = "save.bin";
        File file = new File(directory);
        File file2 = new File(directory+"/"+fileName);
        if(!file.exists()){
            file.mkdirs();
            System.out.println("Creating");
        }
        System.out.println(file.getAbsolutePath());*/
    }
    
    public static void xD(){
        
    }
    
    
}
