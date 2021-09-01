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
import valiente.orl2.phyton.error.ValueException;
/**
 *
 * @author camran1234
 */
public class TableOfValue {
    public static ArrayList<SemanticError> semanticErrors = new ArrayList();
    public static ArrayList<SyntaxError> syntaxErrors = new ArrayList();
    public static ArrayList<LexicalError> lexicalErrors = new ArrayList();
    private static ArrayList<Symbol> symbols = new ArrayList();
    private static TableOfType types = new TableOfType();
    
    /**
     * Reinicia los valores de la lista
     */
    public static void resetErrorsList(){
        semanticErrors = new ArrayList();
        syntaxErrors = new ArrayList();
        lexicalErrors = new ArrayList();
        symbols = new ArrayList();
    }
 
    /**
     * Retorna el tipo de la variable
     * @param id
     * @return 
     */
    public static String findTypeSymbol(String id, int line, int column) throws ValueException{
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id)){
                return symbols.get(index).getType().getBase();
            }
        }
        throw new ValueException("No se encontro ningun simbolo con identificador"+id,"Simbolo no encontrado", line, column);
    }
    
    public static boolean isArray(String id){
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id)){
                if(symbols.get(index).getType().getDimension().size()>0){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    public static String getValue(String id, int line, int column) throws ValueException{
        for(int index = symbols.size()-1; index>=0; index-- ){
            if(symbols.get(index).getId().equalsIgnoreCase(id)){
                String result = symbols.get(index).getValue();
                return result;
            }
        }
        throw new ValueException("No se encontro ningun simbolo con identificador"+id,"Simbolo no encontrado", line, column);
    }
    
    /**
     * Remueve los simbolos de ese ambito
     * @param ambit 
     */
    public static void deleteAmbit(int ambit){
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getAmbit()==ambit){
                symbols.remove(index);
            }
        }
    }
    
    public static String convertCharArrayAsString(String id, int line, int column) throws ValueException{
        
        throw new ValueException("No se encontro ningun simbolo con identificador"+id, "Simbolo no encontrado", line, column);
    }
    
}
