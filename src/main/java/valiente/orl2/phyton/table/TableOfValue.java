/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;
import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.phyton.error.LexicalError;
/**
 *
 * @author camran1234
 */
public class TableOfValue {
    public static ArrayList<SemanticError> semanticErrors = new ArrayList();
    public static ArrayList<SyntaxError> syntaxErrors = new ArrayList();
    public static ArrayList<LexicalError> lexicalErrors = new ArrayList();
    
    /**
     * Reinicia los valores de la lista
     */
    public static void resetErrorsList(){
        semanticErrors = new ArrayList();
        syntaxErrors = new ArrayList();
        lexicalErrors = new ArrayList();
    }
    
}
