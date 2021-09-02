/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Direction {
    private ArrayList<Integer> direction = new ArrayList();
    private Operation value;
    
    public Direction(Operation operation){
        this.value = value;
    }
    
    /**
     * Agrega en forma de pila
     */
    public void pushDirection(Integer data){
        ArrayList<Integer> aux = new ArrayList();
        for(int index=direction.size()-1; index>=0; index--){
            aux.add(direction.get(index));
        }
        aux.add(data);
        direction = aux;
    }
    
    /**
     * Agrega en forma de cola
     * @param newValue 
     */
    public void addDirection(Integer newValue){
        direction.add(newValue);
    }
    
    public Value getValueFromOperation(){
        return value.execute();
    }
    
    public Operation getOperation(){
        return value;
    }
    
    public ArrayList<Integer> getDirection(){
        return direction;
    }
    
    public void setDirection(ArrayList<Integer> direction){
        this.direction = direction;
    }
}
