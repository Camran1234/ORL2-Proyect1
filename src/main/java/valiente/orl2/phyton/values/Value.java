/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Dimension;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.specialInstructions.Longitud;
import valiente.orl2.phyton.specialInstructions.Ordenar;
import valiente.orl2.phyton.specialInstructions.Reproducir;
import valiente.orl2.phyton.specialInstructions.Sumarizar;
import valiente.orl2.phyton.table.TableOfValue;

/**
 * tipos: entero, doble, boolean, caracter, cadena, arreglo, funcion como pista, principal. roc, etc, variable, nota0
 * Encargada de almacenar los datos, su tipo
 * @author camran1234
 */
    public class Value {
    private int line, column=0;
    //specialFunction, function, pista, principal, 
    //El tipo arreglo se utiliza cuando se le introdujo un valor dimension
    //es decir es un array nested
    private String type="";
    private String value="";
    private ArrayList<Operation> dimension = new ArrayList();
    private Instruction specialValue; //Reserved for function
    //Para funciones
    private int sizeDimensions=0;
    private boolean isParameter=false;
    /*Para arreglos*/
    private boolean isArray=false;
    private Dimension array = null;
    
    
    public Value(Dimension dimension, int line,int column){
        this.type = "arreglo";
        this.array = dimension;
        this.line = line;
        this.column = column;
        this.isArray=true;
    }
    
    public Value(String type, Instruction specialValue, int line, int column){
        this.type = type;
        this.specialValue = specialValue;
        this.line = line;
        this.column = column;
    }
    
    
    public Value(String type, String value, int line, int column){
        this.type=type;
        this.value=value;
        this.line = line;
        this.column = column;
       // this.checkType();
    }
    
    public Value(String type, String value, int dimensions, int line, int column){
        this.type = type;
        this.value = value;
        this.sizeDimensions = dimensions;
        this.line = line;
        this.column = column;
        this.isParameter = true;
    }
    
    public Value(String type, String value, ArrayList<Operation> dimension, int line, int column){
        this.type = type;
        this.value = value;
        this.dimension = dimension;
        this.line = line;
        this.column = column;
        this.isArray = true;
    }
    
    public Value(String value, ArrayList<Operation> dimension, int line, int column){
        this.type="variable";
        this.value=value;
        this.dimension = dimension;
        if(dimension.size()>0){
            isArray = true;
        }
        this.line = line;
        this.column = column;
    }
    
    public ArrayList<Operation> getDimensions(){
        return this.dimension;
    }
    
    
    public boolean isParameter(){
        return isParameter;
    }
    
    public boolean isArray(){
        return isArray;
    }
    
    public Dimension getArray(){
        return array;
    }
    
    /**
     * Chequea el tipo de entero y lo compone 
     */
    public void checkType(){
        if(type.equalsIgnoreCase("entero")){
            try {
                int x= Integer.parseInt(this.value);
            } catch (Exception e) {
                double y = Double.parseDouble(this.value);
                int x = (int)y;
                this.value = Integer.toString(x);
            }
        }
    }
    
    public String getRawType(){
        return type;
    }
    
    public String getType() throws ValueException{
        //Sacar el valor de la tabla de valores
        if(type.equalsIgnoreCase("variable")){
            return TableOfValue.findTypeSymbol(value, line, column);
        }else{
            return type;
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRawValue(){
        return value;
    }
    
    public String getValue() throws ValueException{
        if(isParameter){
            return Integer.toString(sizeDimensions);
        }
        
        if(type.equalsIgnoreCase("variable")){
            if(TableOfValue.isArray(value)){
                if(dimension.size()>0){
                    return TableOfValue.getArrayValue(value, dimension, line, column);
                }else{
                    String tipo = getType();
                    if(tipo.equalsIgnoreCase("caracter")){
                        return TableOfValue.convertCharArrayAsString(value, line, column);
                    }else{
                        throw new ValueException("No se transformar los valores de un arreglo de tipo "+tipo+" a un solo valor","Error de variable", line, column);
                    }
                }
                
            }else{
                return TableOfValue.getValue(value, line, column);
            }
        }else if(type.equalsIgnoreCase("arreglo")){
           return array.toString();
        }else if(type.equalsIgnoreCase("specialFunction")){
            if(specialValue ==null){
                throw new ValueException("No se encontro una funcion especial", "Nulo no encontrado", getLine(), getColumn());
            }
            if(specialValue instanceof Reproducir || specialValue instanceof Ordenar || specialValue instanceof Sumarizar ||
                    specialValue instanceof Longitud){
                return specialValue.getValueSpecialFunction().getValue();
            }else{
                throw new ValueException("void no se puede transformar a un valor", "Tipos incompatibles", getLine(), getColumn());
            }
        }if(type.equalsIgnoreCase("boolean")){
            if(this.value.equalsIgnoreCase("1") || this.value.equalsIgnoreCase("true") || this.value.equalsIgnoreCase("verdadero")){
                return "true";
            }else if(this.value.equalsIgnoreCase("0") || this.value.equalsIgnoreCase("false") || this.value.equalsIgnoreCase("falso")){
                return "false";
            }
        }else if(type.equalsIgnoreCase("entero")){
            if(this.value.equalsIgnoreCase("true")){
                return "1";
            }else if(this.value.equalsIgnoreCase("false")){
                return "0";
            }
        }
        
        return value;
    }
    
    /**
     * Para obtener el valor de las funciones especiales
     * @return
     * @throws ValueException 
     */
    public Value getRefinatedValue() throws ValueException{
        if(type.equalsIgnoreCase("specialFunction")){
            if(specialValue ==null){
                throw new ValueException("No se encontro una funcion especial", "Nulo no encontrado", getLine(), getColumn());
            }
            if(specialValue instanceof Reproducir || specialValue instanceof Ordenar || specialValue instanceof Sumarizar ||
                    specialValue instanceof Longitud){
                return specialValue.getValueSpecialFunction();
            }else{
                throw new ValueException("void no se puede transformar a un valor", "Tipos incompatibles", getLine(), getColumn());
            }
        }
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    
    
    
}
