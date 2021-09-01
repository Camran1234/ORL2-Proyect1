/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.proyecto1;
import java.util.ArrayList;
import valiente.orl2.phyton.error.*;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.table.TableOfValue;
/**
 * Clase encargada de manejar la semantica del programa
 * Se requiere de establecer las listas de errores sintacticos y lexicos
 * Si estan vacios se empieza a analizar semanticamente 
 * @author camran1234
 */
public class PhytonSemantic {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    ArrayList<SyntaxError> syntaxErrors = TableOfValue.syntaxErrors;
    ArrayList<LexicalError> lexicalErrors = TableOfValue.lexicalErrors;
    boolean accesToSemantic=false;
    
    
    public PhytonSemantic(){
        if(syntaxErrors.size() == 0 && lexicalErrors.size() == 0){
            accesToSemantic=true;
        }
    }
    
    public void execute(){
        //Agregar de primero a la tabla de simbolos la pista que tiene el metodo principal
    }
    
    public boolean setSemantic(ArrayList<Instruction> instruction) throws SemanticException{
        if(accesToSemantic){
            //Operate instructions
            for(int index=0; index<instruction.size(); index++){
                execute();
            }
            if(semanticErrors.size() !=0){
                return false;
            }
        }
        return false;
    }
    
    
    
}
