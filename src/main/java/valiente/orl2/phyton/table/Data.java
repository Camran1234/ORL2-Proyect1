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
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Dimension;
import valiente.orl2.phyton.instructions.Direction;
import valiente.orl2.phyton.operators.Addition;
import valiente.orl2.phyton.values.TypeParser;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.phyton.operators.Substraction;

/**
 *
 * @author camran1234
 */
public class Data {
    private ArrayList<SemanticError> errores = TableOfValue.semanticErrors;
    private String type="";
    private String value=null;
    private String[] array;
    //Helpers
    private boolean isArray=false;
    private ArrayList<Integer> dimension =null;
    private int line, column=0;
    
    /**
     * An empty constructor for data to acces the functions
     */
    public Data(){
        
    }
    
    /**
     * Si son variables de un solo valor
     * @param type
     * @param value 
     */
    public Data(String type, String value, int line, int column) {
        try {
            this.type = type;
            this.value = value;
            this.isArray = false;
            this.line = line;
            this.column = column;
            //checkValue(value ,line, column);
        } catch (Exception ex) {
            SemanticError error = new SemanticError("Tipos incompatibles", line, column);
            error.setDescription("Se requeria un valor de tipo "+type);
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    /**
     * Si es arreglo usar este constructor
     * @param type
     * @param dimension
     * @param line
     * @param column 
     */
    public Data(String type, ArrayList<Integer> dimension, int line , int column){
        this.isArray=true;
        this.type = type;
        this.dimension = dimension;
        this.line = line;
            this.column = column;
        checkArray(line, column);
    }
    
    
    
    /** 
     * Como los arreglos los manejamos en codigo de 3 direcciones entonces calculamos 
     * @param direction
     * @return 
     */
    private int calculateDirection(ArrayList<Integer> direction) throws Exception{
        int result=0;
        if(direction.size() != dimension.size()){
            throw new Exception("Se establecio la posicion de un arreglo de "+direction.size()+ ", cuando el tamano del arreglo es de "+dimension.size());
        }
        for(int index=0; index<direction.size(); index++){
            if(direction.get(index) >= dimension.get(index)){
                throw new Exception("Arreglo fuera de los limites");
            }
        }
        result = generateFormula(direction);
        return result;
    }
    
    private int generateFormula(ArrayList<Integer> dimension){
        int result=0;
        ArrayList<Integer> aux = new ArrayList();
        if(dimension.size()>0){
            int coord = dimension.get(0);
            result = coord;
            for(int index=1; index<dimension.size(); index++){
                result *= this.dimension.get(index);
                aux.add(dimension.get(index));
            }
            result += generateFormula(aux);
        }
        return result;
    }
    
    public String getArrayValue(ArrayList<Integer> dimension, int line, int column){
        if(!isArray){
            SemanticError error = new SemanticError("Variable requerida", line, column);
            error.setDescription("Se encontro un arreglo");
            TableOfValue.semanticErrors.add(error);
        }else{
            try {
                int direction = calculateDirection(dimension);
                return this.array[direction];
            } catch (Exception e) {
                SemanticError error = new SemanticError("Dimension fuera del limite", line, column);
                error.setDescription(e.getMessage());
                TableOfValue.semanticErrors.add(error);
            }
        }
        return null;
    }
    
    
    /**
     * Establece el valor si es arreglo
     * @param value
     * @param dimension
     * @param line
     * @param column 
     */
    public void setArrayValue(Value value,ArrayList<Integer> dimension, String metodo, int line, int column){
        if(!isArray){
            SemanticError error = new SemanticError("Variable requerido", line, column);
            error.setDescription("Se encontro un arreglo");
            TableOfValue.semanticErrors.add(error);
        }else{
            try {                                 
                int direction = calculateDirection(dimension);                
                Value result =null;                
                if(metodo.equalsIgnoreCase("+=")){                
                    result = new Addition().MakeAddition(new Value(type, this.value, line, column), value, line, column);                    
                    result = new TypeParser().tryParse(value, type, line, column);                    
                    array[direction] = result.getRawValue();                    
                }else if(metodo.equalsIgnoreCase("=")){                
                    result = value;                    
                    result = new TypeParser().tryParse(value, type, line, column);                                        
                    array[direction] = result.getRawValue();                    
                }else if(metodo.equalsIgnoreCase("++")||metodo.equalsIgnoreCase("--")){                
                    increm(metodo, direction, line, column);                    
                }
                
                
                
            } catch (Exception e) {
                SemanticError error = new SemanticError("Dimension fuera del limite", line, column);
                error.setDescription(e.getMessage());
                TableOfValue.semanticErrors.add(error);
            }
        }        
    }
    
    /**
     * Establece un valor si es variable
     * @param name
     * @param value
     * @param line
     * @param column 
     */
    public void setValue(String name, Value value, String metodo, int line, int column){
        Symbol symbol = null;
        boolean theRightValue=false;
        
        if(value.getRawType().equalsIgnoreCase("variable")){
            symbol = TableOfValue.getSymbol(value.getRawValue(), "variable");
            theRightValue = symbol.getData().isArray;
        }
        if(value.getDimensions().size()>0){
            theRightValue=false;
        }
        if(isArray && !theRightValue){
            SemanticError error = new SemanticError("Arreglo requerido", line, column);
            error.setDescription("Se encontro una variable de tipo "+type);
            TableOfValue.semanticErrors.add(error);
        }else{
            try {
                if(theRightValue){
                    if(metodo.equalsIgnoreCase("=")){
                        
                        if(symbol.getReference().getBase().equalsIgnoreCase(this.type)){
                            ArrayList<Integer> thisDimension = this.dimension;
                            ArrayList<Integer> referencedDimension = symbol.getReference().getDimension();
                            if(thisDimension.size() == referencedDimension.size()){
                                for(int index=0; index<thisDimension.size(); index++){
                                    if(!(thisDimension.get(index) == referencedDimension.get(index))){
                                        throw new Exception("No se pudo asignar el arreglo por dimensiones diferentes");
                                    }
                                }
                                //Igualamos los arreglos siempre comparando los tipos
                                if(this.type.equalsIgnoreCase(symbol.getReference().getBase())){
                                    this.array = symbol.getArray();
                                }else{
                                    throw new Exception("Los arreglos son de diferentes tipos se esperaba "+this.type);
                                }
                            }else{
                                throw new Exception("No se pudo asignar el arreglo por dimensiones diferentes");
                            }
                        }else{
                            if(symbol.getReference().getBase().equalsIgnoreCase("caracter") && this.type.equalsIgnoreCase("cadena")){
                                StringBuilder string = new StringBuilder();
                                String[] arreglo = symbol.getArray();
                                for(int index=0; index<arreglo.length; index++){
                                    if(arreglo[index]==null){
                                        continue;
                                    }
                                    string.append(arreglo[index]);
                                }
                                this.value = string.toString();
                            }else{
                                SemanticError error = new SemanticError("Tipos Incompatibles", line, column);
                                error.setDescription("La igualacion es de tipos diferentes entre los arreglos");
                                TableOfValue.semanticErrors.add(error);
                            }
                            
                        }
                    }else{
                        SemanticError error = new SemanticError("Igualacion requerida", line, column);
                        error.setDescription("En igualacion de un arreglo a otro arreglo directamente solo se puede igualar no sumar");
                        TableOfValue.semanticErrors.add(error);
                    }
                }else{
                    if(metodo.equalsIgnoreCase("+=")){
                        Addition addition = new Addition();
                        Value asignacion = addition.MakeAddition(new Value(type, this.value, line, column), value, line, column);
                        TypeParser typeParser = new TypeParser();
                        asignacion = typeParser.tryParse(asignacion, type, line, column);
                        this.value = asignacion.getRawValue();
                    }else if(metodo.equalsIgnoreCase("=")){
                        Value asignacion=value;
                        if(value.getRawType().equalsIgnoreCase("specialFunction")){
                            asignacion = value.getRefinatedValue();
                        }
                        asignacion = new TypeParser().tryParse(asignacion, type, line, column);
                        this.value = asignacion.getRawValue();
                    }else if(metodo.equalsIgnoreCase("++") || metodo.equalsIgnoreCase("--")){
                        increm(metodo, line, column);
                    }
                }
                
            } catch (Exception e) {
                SemanticError error = new SemanticError("Tipos incompatibles", line, column);
                error.setDescription(e.getMessage());
                TableOfValue.semanticErrors.add(error);
            }
        }
    }
    
    /**
     * Metodo para declarar inicialmente una variable
     * @param dimension 
     */
    public void addMultipleValuesFromDimension(Dimension dimension, int line, int column){
        ArrayList<Direction> direction = new ArrayList();
        direction = dimension.startHarvest();
        for(int index=0; index<direction.size(); index++){
            Direction aux = direction.get(index);
            this.setArrayValue(aux.getValueFromOperation(), aux.getDirection(), "=", line, column);
        }

    }
    
    /**
     * Incrementa la variable
     * @param metodo
     * @param line
     * @param column 
     */
    public void increm(String metodo,int line, int column){
        try {
            Value newValue = new Value(this.type, this.value, line, column);
            Value valor = new Value("entero", "1", line, column);
            TypeParser parser = new TypeParser();
            if(metodo.equalsIgnoreCase("++")){
                Addition addition = new Addition();
                
                Value newAssignation = addition.MakeAddition(newValue, valor, line, column );
                newAssignation = parser.tryParse(newAssignation, type, line, column);
                this.value = newAssignation.getRawValue();
            }else if(metodo.equalsIgnoreCase("--")){
                Substraction substraction = new Substraction();
                Value newAssignation = substraction.MakeSubstraction(newValue, valor, line, column);
                newAssignation = parser.tryParse(newAssignation, type, line, column);
                this.value = newAssignation.getRawValue();
            }
            
        } catch (Exception e) {
            SemanticError error = new SemanticError("Operacion invalida", line, column);                
            error.setDescription(e.getMessage());            
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    public void increm(String metodo, int direccion, int line,int column){
        try {
            Value newValue = new Value(this.type, this.value, line, column);
            Value valor = new Value("entero", "1", line, column);
            TypeParser parser = new TypeParser();
            if(metodo.equalsIgnoreCase("++")){
                Addition addition = new Addition();
                
                Value newAssignation = addition.MakeAddition(newValue, valor, line, column );
                newAssignation = parser.tryParse(newAssignation, type, line, column);
                array[direccion] = newAssignation.getRawValue();
            }else if(metodo.equalsIgnoreCase("--")){
                Substraction substraction = new Substraction();
                Value newAssignation = substraction.MakeSubstraction(newValue, valor, line, column);
                newAssignation = parser.tryParse(newAssignation, type, line, column);
                array[direccion] = newAssignation.getRawValue();
            }
        } catch (Exception e) {
            SemanticError error = new SemanticError("Operacion invalida", line, column);                
            error.setDescription(e.getMessage());            
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    public boolean isArray(){
        return isArray;
    }
    
    public String getType(){
        return this.type;
    }
    
    public String[] getArray(){
        if(dimension.size()>1){
            return array;
        }else{            
            return getFirstRowLength();
        }
    }
    
    public void setArray(String[] array){
        this.array = array;
    }
    
    public String getValue(){
        if(isArray){
            return this.toString();
        }else{
            return value;
        }
    }
    
    public String getDirection(){
        return this.toString();
    }
    
    public String[] getFirstRowLength(){
        int firstRowSize = dimension.get(0);
        ArrayList<String> list = new ArrayList();
        for(int index=0; index<firstRowSize; index++){
            list.add(array[index]);
        }
        String[] string = new String[dimension.get(0)];
        for(int index=0; index<list.size(); index++){
            string[index] = list.get(index);
        }
        return string;
    }
    
    public boolean isMultidimensional(){
        if(dimension.size()>1){
            return true;
        }else{
            return false;
        }
    }
    
    private void checkArray(int line, int column){
        try {
            int sizeList=1;
            for(int index=0; index<dimension.size(); index++){
                sizeList *= dimension.get(index);
            }
            array = new String[sizeList];
        } catch (Exception e) {
            SemanticError error = new SemanticError("Arreglo incompatible", line, column);
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    
    private void checkValue(String value,int line, int column) throws Exception{
            if(type.equalsIgnoreCase("entero")){
                int test = Integer.parseInt(value);
            }else if(type.equalsIgnoreCase("doble")){
                double test = Double.parseDouble(value);
            }else if(type.equalsIgnoreCase("caracter")){
                if(value.length()!=1){
                    throw new Exception();
                }
            }else if(type.equalsIgnoreCase("boolean")){
                if(value.equalsIgnoreCase("1") || value.equalsIgnoreCase("0") || value.equalsIgnoreCase("true") ||
                        value.equalsIgnoreCase("verdadero") || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("falso")){
                    //Nothing >:D
                }else{
                  throw new Exception();  
                }
            }
        
    }
}
