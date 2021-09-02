/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;

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
    int line, column=0;
    
    public Dimension(int line, int column){
        this.line = line;
        this.column = column;
    }
    
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public ArrayList<Operation> getData() {
        return data;
    }

    public void setData(ArrayList<Operation> data) {
        this.dimension = 1;
        isFirst = true;
        this.data = data;
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }

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
    }
    
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
                        direction.pushDirection(index);
                        direcciones.add(direction);
                }
            }
        }
        
        return direcciones;
    }
    
    
}
