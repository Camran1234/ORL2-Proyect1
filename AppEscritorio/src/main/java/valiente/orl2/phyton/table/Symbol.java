/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;
import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.instructions.Assignment;
import valiente.orl2.phyton.instructions.Dimension;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Pista;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
/**
 *
 * @author camran1234
 */
public class Symbol {
    private TableOfValue tabla = new TableOfValue();
    private boolean keep=false;
    private String id="";
    private Type referencedType = null;
    //Clase para funciones
    private Data value;
    //For functions
    private int numberParameters=0;
    private ArrayList<Parameter> parameters = new ArrayList();
    private int ambit = 0;
    
    /**
     * Declarar simbolo
     * @param type 
     */
    public Symbol(Type type, int indentation, boolean keep, int line, int column){
        
        this.ambit = indentation;
        this.referencedType = type;
        this.id = referencedType.getId();
        parameters = referencedType.getParameters();
        numberParameters = referencedType.getNumberParameters();
        this.keep = keep;
        if(type.isArray()){
            ArrayList<Integer> dimension = type.getDimension();
            value = new Data(type.getBase(), dimension, line, column);
            
        }else{
            value = new Data(type.getBase(), "", line, column);
        }
        
        
    }
    
    public Data getData(){
        return value;
    }
    
    public boolean comparePista(String pista){
        if(referencedType.getValue() instanceof Pista || !isGlobal()){
            return false;
        }
        Instruction instruction = referencedType.getValue().lookForPista();
        if(((Pista)instruction).getName().equals(pista)){
            return true;
        }
        return false;
    }
    
    
    public void setNewValue(Assignment assignment, boolean declarado, int line, int column){
            int choice = assignment.selectMethod();
            //Para asignar el valor de los arreglos
            if(choice ==1){
                Value valor = assignment.getValueFromOperation();    
                if(declarado){                    
                    value.addMultipleValuesFromDimension(assignment.getDimension(), line, column);
                }else{
                    value.setArrayValue(valor, assignment.getDirectionToAssign(), assignment.getMetodo(), line, column);
                }

            }else if(choice==0){
                //Para asignar el valor de los variables
                Value valor = assignment.getValueFromOperation();
                value.setValue(id, valor, assignment.getMetodo(), line, column);
            }else if(choice==2){
                value.setValue(id, null,assignment.getMetodo(), line, column);
            }
    }
    
    public Boolean isGlobal(){
        return keep;
    }
    
    public String[] getArray(){
        return value.getArray();
    }
    
    public String getValueArray(ArrayList<Integer> dimension, int line, int column){
        return value.getArrayValue(dimension, line, column);
    }
    
    /**
     * Returns the size of the array
     * @return 
     */
    public int getSizeArray(){
        int size=0;
        Data data = this.value;
        String[] string = data.getFirstRowLength();
        size = string.length;
        return size;
    }
    
    /**
     * Obtiene la categoria de este simbolo ya sea variable, methodo o funcion
     * @return 
     */
    public String getCategory(){
        return referencedType.getCategory();
    }
    
    public boolean isArray(){
        return this.value.isArray();
    }
    
    public void addMultipleValuesFromDimension(Dimension dimension){
        
    }
    
    public String getValue(){
        return this.value.getValue();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getReference(){
        return this.referencedType;
    }
    
    public void setReference(Type referencedType){
        this.referencedType = referencedType;
    }
    
    public String getType() {
        return referencedType.getBase();
    }

    public void setType(Type type) {
        this.referencedType = type;
    }

    public int getNumberParameters() {
        return numberParameters;
    }

    public void setNumberParameters(int numberParameters) {
        this.numberParameters = numberParameters;
    }

    public ArrayList<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Get the value of memory of this symbol
     * @return 
     */
    public Object getDirection() {
        return this.value.getValue();
    }

    public int getAmbit() {
        return ambit;
    }

    public void setAmbit(int ambit) {
        this.ambit = ambit;
    }
    
    
}
