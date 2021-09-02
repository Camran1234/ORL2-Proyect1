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
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
/**
 *
 * @author camran1234
 */
public class TableOfValue {
    public static ArrayList<SemanticError> semanticErrors = new ArrayList();
    public static ArrayList<SyntaxError> syntaxErrors = new ArrayList();
    public static ArrayList<LexicalError> lexicalErrors = new ArrayList();
    private static ArrayList<Symbol> symbols = new ArrayList();
    
    /**
     * Reinicia los valores de la lista
     */
    public static void resetErrorsList(){
        semanticErrors = new ArrayList();
        syntaxErrors = new ArrayList();
        lexicalErrors = new ArrayList();
        symbols = new ArrayList();
        TableOfType.reset();
    }
 
    public static Symbol getSymbol(String id, String categoria){
        Symbol symbol=null;
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) &&
                    symbols.get(index).getCategory().equalsIgnoreCase(categoria)){
                symbol = symbols.get(index);
                break;
            }
        }
        return symbol;
    }
    
    public static void addSymbol(Symbol symbol, int line, int column){
        try {
            String id = symbol.getId();
            String category = symbol.getCategory();
            boolean flag=false;
            for(int index=symbols.size()-1; index>=0; index--){
                if(id.equalsIgnoreCase(symbols.get(index).getId()) && category.equalsIgnoreCase(symbols.get(index).getCategory())){
                    flag = true;
                }
            }
            if(flag){
                throw new ValueException("Ya existe un identificador de tipo "+category + " y nombre "+id);
            }else{
                symbols.add(symbol);
            }
        } catch (ValueException e) {
            int x=0;
            SemanticError error = new SemanticError("Variable ya definida", line, column);
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    /**
     * Retorna el tipo de la variable
     * @param id
     * @return 
     */
    public static String findTypeSymbol(String id, int line, int column) throws ValueException{
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id)){
                return symbols.get(index).getType();
            }
        }
        throw new ValueException("No se encontro ningun simbolo con identificador"+id,"Simbolo no encontrado", line, column);
    }
    
    public static boolean isArray(String id){
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) && symbols.get(index).isArray()){
                if(symbols.get(index).getReference().getDimension().size()>0){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    private static ArrayList<Integer> getDimensionParams(ArrayList<Operation> dimension) throws ValueException{
        ArrayList<Integer> aux = new ArrayList();
        for(int index=0; index<dimension.size(); index++){
            Value value = dimension.get(index).execute();
            if(value.getType().equalsIgnoreCase("entero")){
                aux.add(Integer.parseInt(value.getValue()));
            }
        }
        return aux;
    }
    
    public static String getArrayValue(String id, ArrayList<Operation> dimension, int line, int column) throws ValueException{
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) && symbols.get(index).getCategory().equalsIgnoreCase("variable")){
                ArrayList<Integer> direction = getDimensionParams(dimension);
                return symbols.get(index).getValueArray(direction, line, column);
            }
        }
        return null;
    }
    
    public static String getValue(String id, int line, int column) throws ValueException{
        for(int index = symbols.size()-1; index>=0; index-- ){
            if(symbols.get(index).getId().equalsIgnoreCase(id) && symbols.get(index).getCategory().equalsIgnoreCase("variable")){
                return symbols.get(index).getValue();
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
            if(symbols.get(index).getAmbit()==ambit && !symbols.get(index).isGlobal()){
                TableOfType.deleteType(symbols.get(index).getReference());
                symbols.remove(index);
            }
        }
    }
    
    public static String convertCharArrayAsString(String id,int line, int column) throws ValueException{
        String arreglo="";
        String[] array=null;
        boolean flag=false;
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) && 
                    symbols.get(index).getType().equalsIgnoreCase("caracter") &&
                    symbols.get(index).isArray() == true && symbols.get(index).getCategory().equalsIgnoreCase("variable")){
                flag=true;
                array = symbols.get(index).getArray();
            }
        }
        if(flag){
            for(int index=0; index<array.length; index++){
                arreglo += array[index];
            }
        }else{
            throw new ValueException("No se encontro un arreglo en el id referenciado","Arreglo no encontrado", line, column);
        }
        return arreglo;        
    }

}
