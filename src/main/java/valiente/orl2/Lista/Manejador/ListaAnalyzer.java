/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.Lista.Manejador;

import java.io.StringReader;
import java.util.ArrayList;
import valiente.orl2.phyton.error.LexicalError;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.Lista.parser.PlayLexic;
import valiente.orl2.Lista.parser.PlaySyntax;
import valiente.orl2.reproduccion.PlayList;
import valiente.orl2.UI.TableGenerator;
import valiente.orl2.central.Central;
import valiente.orl2.proyecto1.PhytonFrame;
import valiente.orl2.reproduccion.PistaReproduccion;
/**
 *
 * @author camran1234
 */
public class ListaAnalyzer {
    private String text="";
    public static ArrayList<LexicalError> lexicalErrors = new ArrayList();
    public static ArrayList<SyntaxError> syntaxErrors = new ArrayList();
    public static ArrayList<SemanticError> semanticErrors = new ArrayList();
    
    public ListaAnalyzer(String text){
        this.text = text;
        lexicalErrors = new ArrayList();
        syntaxErrors = new ArrayList();
        semanticErrors = new ArrayList();
    }
    
    
    private boolean isRunable(){
        if(lexicalErrors.size()==0 && syntaxErrors.size()==0 && semanticErrors.size()==0){
            return true;
        }
        return false;
    }
    
    public void execute(){
        try {
            StringReader string = new StringReader(text);
            PlayLexic lexic = new PlayLexic(string);
            PlaySyntax syntax = new PlaySyntax(lexic);
            syntax.parse();
            lexicalErrors = lexic.getList();
            syntaxErrors = syntax.getList();
            PlayList list = syntax.getLista();
            Central central = new Central();
            PlayList localList = central.getPlayList();
            ArrayList<PistaReproduccion> pistas = central.getPistas();
            localList.compareList(list.getlistas(), pistas);
            
            if(!isRunable()){
                TableGenerator generator = new TableGenerator(lexicalErrors, syntaxErrors, semanticErrors);
                generator.generarTabla();
            }else{
                if(PhytonFrame.compile){
                    list.setText(text);
                    central.updateListas(list);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
