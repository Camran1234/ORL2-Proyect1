/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.UI;

import java.awt.BorderLayout;
import valiente.orl2.phyton.table.TableOfValue;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import valiente.orl2.phyton.error.LexicalError;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.proyecto1.PhytonFrame;
/**
 *
 * @author camran1234
 */
public class TableGenerator {
    private ArrayList<LexicalError> lexicalErrors = TableOfValue.lexicalErrors;
    private ArrayList<SyntaxError> syntaxErrors = TableOfValue.syntaxErrors;
    private ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    
    private static int numberErrors=0;
    
    public TableGenerator(){
        this.numberErrors=0;
    }
    
    public void addLexicalErrors(ArrayList<ArrayList<String>> errores){
        
        for(LexicalError lexicalError:lexicalErrors){
            ArrayList<String> data = new ArrayList();
            data.add(Integer.toString(numberErrors+1));
            data.add("Error Lexico");
            data.add(lexicalError.getDescription());
            data.add(Integer.toString(lexicalError.getLine()));
            data.add(Integer.toString(lexicalError.getColumn()));
            errores.add(data);
            numberErrors++;
        }
        
    }
    
    public void addSyntaxErrors(ArrayList<ArrayList<String>> errores){
        for(SyntaxError syntaxError:syntaxErrors){
            ArrayList<String> data = new ArrayList();
            data.add(Integer.toString(numberErrors+1));
            data.add("Error Sintactico");
            data.add(syntaxError.getDescription());
            data.add(Integer.toString(syntaxError.getLine()));
            data.add(Integer.toString(syntaxError.getColumn()));
            errores.add(data);
            numberErrors++;
        }
    }
    
    public void addSemanticErrors(ArrayList<ArrayList<String>> errores){
        for(SemanticError semanticError:semanticErrors){
            ArrayList<String> data = new ArrayList();
            data.add(Integer.toString(numberErrors+1));
            data.add("Error Semantico");
            data.add(semanticError.getDescription());
            data.add(Integer.toString(semanticError.getLine()));
            data.add(Integer.toString(semanticError.getColumn()));
            errores.add(data);
            numberErrors++;
        }
    }
    
    public void generarTabla(){
        ArrayList<ArrayList<String>> errores = new ArrayList();
        addLexicalErrors(errores);
        addSyntaxErrors(errores);
        addSemanticErrors(errores);
        int x = errores.size();
        int y = 5;
        String[][] values = new String[x][y];
        for(int index=0; index< errores.size(); index++){
            ArrayList<String> aux = errores.get(index);
            for(int j=0; j<aux.size(); j++){
                values[index][j] = aux.get(j);
            }
        }
        PhytonFrame.setTable(values);
        System.out.println("check");
    }
    
}
