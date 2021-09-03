/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;

import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Type {
    private Instruction value=null;
    private String id="";
    //Tipos de bases: entero, doble, caracter, boolean, string, funcion, pista, principio
    private String base = "";
    private boolean isFunction=false;
    //Para funciones
    private int numberParameters=0;
    private ArrayList<Parameter> parameters = new ArrayList();
    //La estructura o metodo al que pertenecen
    private Instruction father=null;
    //Las dimensiones por si es arreglo
    private ArrayList<Integer> dimension = new ArrayList();
    private int min=0;
    private int max=0;
    private int ambit=0;

    /**
     * Genera una instancia de tipo Type
     * El id es el identificador
     * Base es el tipo de variable (entero, doble, caracter, boolean, cadena) o function, pista, principal
     * numberParameters numero de parametros si es una funcion
     * parameters son los parametros que se esperan recibir si es una funcion
     * father es el metodo o estructura al cual pertenece
     * dimension el numero de dimensiones que posee
     * value la instruccion
     * @param id
     * @param base
     * @param numberParameters
     * @param parameters
     * @param father
     * @param dimension
     * @param ambit
     * @param value 
     */
    public Type(String id, String base, int numberParameters, ArrayList<Parameter> parameters, Instruction father,
            ArrayList<Integer> dimension, int ambit , Instruction value){
        this.id = id;
        this.base = base;
        this.numberParameters = numberParameters;
        this.parameters = parameters;
        this.father = father;
        this.dimension = dimension;
        this.ambit = ambit;
        this.value = value;
    }
    
    
    public void setFunction(boolean flag){
        isFunction = true;
    }
    
    /**
     * Compara los parametros de la funcion
     * Retorna verdadero si son iguales
     * Retorna falso si no son iguales
     * @return 
     */
    public boolean compareParameters(ArrayList<Parameter> parameters){
        if(parameters.size() == this.parameters.size()){
            for(int index=0; index<parameters.size(); index++){
                if(!parameters.get(index).getType().equalsIgnoreCase(this.parameters.get(index).getType())){
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Retorna la funcion perteneciente a este simbolo
     * @return 
     */
    public Instruction getValue() {
        return value;
    }

    public void setValue(Instruction value) {
        this.value = value;
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
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instruction getFather() {
        return father;
    }

    public void setFather(Instruction father) {
        this.father = father;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public ArrayList<Integer> getDimension() {
        return dimension;
    }

    public void setDimension(ArrayList<Integer> dimension) {
        this.dimension = dimension;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getAmbit() {
        return ambit;
    }

    public void setAmbit(int ambit) {
        this.ambit = ambit;
    }
    
    /**
     * Obtiene la categoria de este simbolo
     * variable, method, class, function...
     * @return 
     */
    public String getCategory(){
        String categoria = base;
        if(categoria.equalsIgnoreCase("pista") || categoria.equalsIgnoreCase("principal")){
            return categoria;
        }else if(isFunction){
            return "function";
        }else{
            return "variable";
        }
    }
    
}
