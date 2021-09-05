/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.phyton.error.LexicalError;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Pista;
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
    //Pista que estamos trabajando
    private static Symbol workingSymbol = null;
    
    /**
     * Reinicia los valores de la lista
     */
    public static void resetErrorsList(){
        semanticErrors = new ArrayList();
        syntaxErrors = new ArrayList();
        lexicalErrors = new ArrayList();
        symbols = new ArrayList();
        workingSymbol = null;
        TableOfType.reset();
    }
    
    public static boolean isRunable(){
        if(semanticErrors.size()==0 && syntaxErrors.size()==0 && lexicalErrors.size()==0){
            return true;
        }
        return false;
    }
    
    public static void setWorkingSymbol(Symbol symbol){
        TableOfValue.workingSymbol = symbol;
    }
    
    public static Symbol getWorkingSymbol(){
        return TableOfValue.workingSymbol;
    }
    
    
    
    public static boolean compareParameters(ArrayList<Parameter> list1, ArrayList<Operation> list2){
        boolean flag=true;        
        for(int index=0; index<list1.size(); index++){
            try {
                Parameter parameter = list1.get(index);
                String type = list2.get(index).execute().getType();
                if(!parameter.compareType(type)){
                    flag=false;
                    break;
                }
            } catch (ValueException ex) {
                Logger.getLogger(TableOfValue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return flag;
    }
    
    
    /**
     * Comprueba que el simbolo enviado pertenece al contexto de la pista actual
     * @param symbol
     * @return 
     */
    public static boolean fromSamePista(Symbol symbol){
        boolean flag=false;
        //Solo comprobar el contenedor Pista
        Instruction actualPista = workingSymbol.getReference().getValue().lookForPista();
        //Esta es la funcion
        Instruction instructionReferenced = symbol.getReference().getValue();
        if(actualPista == instructionReferenced.lookForPista()){
            flag = true;
        }else{
            flag = isExtendedFromMainPista((Pista)actualPista, instructionReferenced);
        }
        return flag;
    }
    
    public static boolean isExtendedFromMainPista(Pista mainPista, Instruction referenced){
        boolean flag = false;
        
        try {
            ArrayList<Value> nameOfPistas = mainPista.getNumberOfExtendeds();
            for(int index=0; index<nameOfPistas.size(); index++){
                ArrayList<Symbol> symbols = getInstructionsFromPista(nameOfPistas.get(index).getValue());
                for( int indexSym=0; indexSym<symbols.size(); indexSym++){
                    //Comparar si se encuentra la instruccion y si es global
                    if(symbols.get(indexSym).getReference().getValue().equals(referenced)  && symbols.get(indexSym).isGlobal()){
                        return true;
                    }
                }
            }
        } catch (ValueException e) {
        }
        
        return flag;
    }
    
    public static ArrayList<Symbol> getInstructionsFromPista(String namePista){
        ArrayList<Symbol> lista = new ArrayList();
        for(int index=0; index<symbols.size(); index++){
            if(symbols.get(index).comparePista(namePista)){
                lista.add(symbols.get(index));
            }
        }
        return lista;
            
    }
    
    /**
     * Get the function that is required and do the semantic validation
     * @param id
     * @param operation
     * @return 
     */
    public static Instruction getFunctionOfSymbol(String id, ArrayList<Operation> operation){
        for(int index=0; index<symbols.size(); index++){
            String name = symbols.get(index).getId();
            String category = symbols.get(index).getCategory();
            int parametros = symbols.get(index).getNumberParameters();
            
            if(name.equalsIgnoreCase(id) && category.equalsIgnoreCase("function")){
                if(parametros==operation.size()){
                    ArrayList<Parameter> parameter = symbols.get(index).getParameters();
                    if(compareParameters(parameter, operation)){
                        if(fromSamePista(symbols.get(index))){
                            symbols.get(index);
                            return symbols.get(index).getReference().getValue();
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static Symbol getPistaSymbol(String id, String categoria) {
        Symbol symbol=null;
        
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) &&
                    symbols.get(index).getCategory().equalsIgnoreCase(categoria) && symbols.get(index).getReference().getValue() instanceof Pista){
                    symbol = symbols.get(index);
                    break;
            }
        }
        return symbol;
    }
    
    public static Symbol getSymbol(String id, String categoria){
        Symbol symbol=null;
        
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) &&
                    symbols.get(index).getCategory().equalsIgnoreCase(categoria)){
                if(fromSamePista(symbols.get(index))){
                    symbol = symbols.get(index);
                    break;
                }
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
                    if(fromSamePista(symbols.get(index))){
                        flag = true;
                    }
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
            if(symbols.get(index).getId().equalsIgnoreCase(id) && fromSamePista(symbols.get(index))){
                return symbols.get(index).getType();
            }
        }
        throw new ValueException("No se encontro ningun simbolo con identificador "+id,"Simbolo no encontrado", line, column);
    }
    
    public static boolean isArray(String id){
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) && symbols.get(index).isArray() && fromSamePista(symbols.get(index))){
                if(symbols.get(index).getReference().getDimension().size()>0){
                    return true;
                }else{
                    return false;
                }
            }
        }
        return false;
    }
    
    /**
     * Ayuda a extrar los parametros de una dimension
     * @param dimension
     * @return
     * @throws ValueException 
     */
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
    
    /**
     * Se encarga de obtener el valor de un arreglo
     * @param id
     * @param dimension
     * @param line
     * @param column
     * @return
     * @throws ValueException 
     */
    public static String getArrayValue(String id, ArrayList<Operation> dimension, int line, int column) throws ValueException{
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getId().equalsIgnoreCase(id) && symbols.get(index).getCategory().equalsIgnoreCase("variable")
                    && fromSamePista(symbols.get(index))){
                ArrayList<Integer> direction = getDimensionParams(dimension);
                return symbols.get(index).getValueArray(direction, line, column);
            }
        }
        return null;
    }
    
    /**
     * Obtiene el valor de una variable
     * @param id
     * @param line
     * @param column
     * @return
     * @throws ValueException 
     */
    public static String getValue(String id, int line, int column) throws ValueException{
        for(int index = symbols.size()-1; index>=0; index-- ){
            if(symbols.get(index).getId().equalsIgnoreCase(id) && symbols.get(index).getCategory().equalsIgnoreCase("variable")
                    && fromSamePista(symbols.get(index))){
                return symbols.get(index).getValue();
            }
        }
        throw new ValueException("No se encontro ningun simbolo con identificador"+id,"Simbolo no encontrado", line, column);
    }
    
    /**
     * Remueve los simbolos de ese ambito
     * @param ambit 
     */
    public static void deleteAmbit(int ambit, Instruction container){
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getAmbit()==ambit && !symbols.get(index).isGlobal()
                    && fromSamePista(symbols.get(index)) && symbols.get(index).getReference().getValue().lookForContainer().equals(container.lookForContainer()) ){
                symbols.remove(index);
            }
        }
    }
    
    public static void deleteAmbitBut(int ambit, Instruction container, String id){
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getAmbit()==ambit && !symbols.get(index).isGlobal() && !symbols.get(index).getId().equalsIgnoreCase(id)
                    && fromSamePista(symbols.get(index)) && symbols.get(index).getReference().getValue().lookForContainer().equals(container.lookForContainer()) ){
                symbols.remove(index);
            }
        }
    }
    
    public static void forceDelete(int ambit){
        for(int index=symbols.size()-1; index>=0; index--){
            if(symbols.get(index).getAmbit()==ambit && fromSamePista(symbols.get(index))){
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
                    symbols.get(index).isArray() == true && symbols.get(index).getCategory().equalsIgnoreCase("variable")
                    && fromSamePista(symbols.get(index))){
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
