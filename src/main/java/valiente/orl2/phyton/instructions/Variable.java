/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.table.TableOfType;
import valiente.orl2.phyton.table.Type;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.phyton.table.Parameter;
import valiente.orl2.phyton.table.Symbol;
import valiente.orl2.phyton.table.TableOfValue;
/**
 * Clase para declarar y asignar variables
 * @author camran1234
 */
public class Variable extends Instruction{
    boolean declarado=false;
    boolean mode =false;
    boolean array=false;
    //identificador de la variable
    String name ="";
    //Si tiene tipo se declara
    String type ="";
    //El valor asignado a esta variable
    Assignment value = null;
    /*Para arreglos*/
    //Size of the array
    ArrayList<Operation> dimension = new ArrayList();

    
    public Variable(int line, int column){
        this.line=line;
        this.column=column;
    }
  
    @Override
    public void execute() throws SemanticException{
        try {
            //Si es una declaracion o no
            if(declarado){
                //Generamos el tipo
                ArrayList<Integer> dimensiones = new ArrayList();
                for(int index=0; index<dimension.size(); index++){
                    Value value = dimension.get(index).execute();
                   if(value.getType().equalsIgnoreCase("entero")){
                       dimensiones.add(Integer.parseInt(value.getValue()));
                   }else{
                       throw new ValueException("Las dimensiones de los arreglos solo pueden ser declaradas por numeros"
                               + "","Valor no reconocido", getLine(), getColumn());
                   }
                }
                Type type = new Type(name, this.type, 0, new ArrayList<Parameter>(), lookForContainer(), dimensiones, getIndentation(), this);
                //TableOfType.addType(type);
                Symbol symbol = new Symbol(type, getIndentation(),mode);
                if(value!=null){
                    symbol.setNewValue(value, declarado, getLine(), getColumn());
                }
                TableOfValue.addSymbol(symbol, getLine(), getColumn());
            }else{
                //Reasignar un nuevo valor
                Symbol symbol = TableOfValue.getSymbol(name, "variable");
                //Actualizamos el valor desde aqui
                symbol.setNewValue(value, false, getLine(), getColumn());
            }
        } catch (ValueException e) {
            
        }
    }
    
    
    public void declarar(){
        try {
            execute();
        } catch (SemanticException ex) {
            Logger.getLogger(Variable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParameters(VariableIndicator indicator){
        this.mode = indicator.getGlobal();
        this.type = indicator.getType();
        if(!indicator.getDimension().isEmpty()){
            array=true;
        }
        this.dimension = indicator.getDimension();
        this.declarado=true;
    }
    
    public void setValue(Assignment value){
        this.value = value;
    }
    
    public Assignment getValue(){
        return value;
    }
    
    public Boolean getMode() {
        return mode;
    }

    public void setMode(Boolean mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Operation> getDimension() {
        return dimension;
    }

    public void setDimension(ArrayList<Operation> dimension) {
        this.dimension = dimension;
    }

    
    
}
