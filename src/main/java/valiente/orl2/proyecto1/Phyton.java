/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.proyecto1;

import java.io.StringReader;
import valiente.orl2.phyton.parser.*;
import java.util.ArrayList;
import valiente.orl2.phyton.error.*;
/**
 *
 * @author camran1234
 */
public class Phyton {
    
    public void parse(String text){
        try {
            StringReader string = new StringReader(text);
            PhytonLexic lexico = new PhytonLexic(string);
            PhytonSyntax parser = new PhytonSyntax(lexico);
            parser.parse();
            ArrayList<LexicalError> lexicalError = lexico.getList();
            ArrayList<SyntaxError> syntaxError = parser.getList(); 
            System.out.println("Empezando");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
