/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Dimension {
    int dimension = 0;
    //Aqui van las dimensiones
    private ArrayList<Operation> data = new ArrayList();
    //Aqui van el resto de los arreglos
    private ArrayList<Dimension> dimensions = new ArrayList();
    private boolean isFirst=false;
    private boolean isLast=false;
    int line, column=0;
    private String type="";
    
    public Dimension(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    public int getDimension() {
        if(dimension ==0){
            int dimension = dimensions.get(0).getDimension();
        }
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
        if(dimension==0){
            isLast=true;
        }
    }

    public ArrayList<Operation> getData() {
        return data;
    }

    public void setData(ArrayList<Operation> data) {
        this.dimension = 1;
        isFirst = true;
        this.data = data;
        checkValues();
    }
    
    public void checkValues(){
        String type="";
        for(int index=0; index<data.size(); index++){
            try {
                Value valor = data.get(index).execute();
                if(type.equalsIgnoreCase("")){
                    type = valor.getType();
                }else{
                    if(!type.equalsIgnoreCase(valor.getType())){
                       throw new ValueException("No se puede convertir un "+valor.getType()+ " a "+type,"Tipos incompatibles", line, column);
                    }
                }
            } catch (ValueException e) {
            }
        } 
        this.type = type;
        
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }
    
    public String getType(){
        return type;
    }
    
    /**
     * Establece los arreglos aniados y comprueba su tipo
     * @param dimensions 
     */
    public void setDimensions(ArrayList<Dimension> dimensions){
        this.dimensions = dimensions;
        String type="";
        for(int index=0; index<dimensions.size(); index++){
            try {
                if(type.equalsIgnoreCase("")){
                    type = dimensions.get(index).getType();
                }else{
                    //Diferentes tipos
                    if(!dimensions.get(index).getType().equalsIgnoreCase(type)){
                        throw new ValueException("Se requeria un "+type+" pero se dio "+dimensions.get(index).getType(),"Tipos incompatibles", line, column);
                    }
                }
            } catch (ValueException e) {
            }

        }
        this.type = type;
    }

    /**
     * Establece la dimension del arreglo con sus valores, pero tambien comprueba el size
     * @param dimensions
     * @param direction 
     */
    public void setDimensions(ArrayList<Dimension> dimensions, ArrayList<Integer> direction) {
        this.dimensions = dimensions;
        if(dimensions.size()>0){
            this.dimension = dimensions.get(0).dimension+1;
        }
        for(int index=0; index<dimensions.size(); index++){
            if(this.dimension != dimensions.get(index).getDimension()){
                SyntaxError error = new SyntaxError("Error de grado en dimensiones","{", line, column);
                error.setExpectedTokens("");
                error.setDescription("Se esperaba que las dimensiones tienen un grado diferente");
                TableOfValue.syntaxErrors.add(error);
            }
        }
//        String type = "";
//        boolean flag=true;
//        for(int index=0; index<dimensions.size(); index++){
//            String dimensionType = dimensions.get(index).getType();
//            if(type.equalsIgnoreCase("")){
//                type = dimensionType;
//            }else{
//                if(!dimensionType.equalsIgnoreCase(type)){
//                    SemanticError error = new SemanticError("Tipos incompatibles", line, column);
//                    error.setDescription("Se agrego un arreglo con valores distintos");
//                    TableOfValue.semanticErrors.add(error);
//                    flag = false;
//                    break;
//                }
//            }
//            this.type = type;
//        }
    }
    
    /**
     * Recolecta todas las direcciones posibles para colocarlas en el arreglo
     * @return 
     */
    public ArrayList<Direction> startHarvest(){
        ArrayList<Direction> direcciones = new ArrayList();
        if(isFirst){
            for(int index=0; index< data.size(); index++){
                Direction direction = new Direction(data.get(index));
                direction.pushDirection(index);
                direcciones.add(direction);
            }
        }else{
            for(int index=0; index< dimensions.size(); index++){
                ArrayList<Direction> aux = new ArrayList();
                aux = dimensions.get(index).startHarvest();
                for(int indexAux=0; indexAux<aux.size(); indexAux++){
                        Direction direction = aux.get(indexAux);
                        //No le agregamos la direccion si este es el ultimo de la pila
                        if(!isLast){
                            direction.pushDirection(index);
                        }
                        direcciones.add(direction);
                }
            }
        }
        
        return direcciones;
    }
    
    
}
