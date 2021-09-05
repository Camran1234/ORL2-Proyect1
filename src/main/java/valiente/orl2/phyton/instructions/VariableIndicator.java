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
public class VariableIndicator {
    boolean global=false;
    String type="";
    //ArrayList<Operation> dimension = new ArrayList(); Depricated
    int dimensions=0;
    
    public VariableIndicator(boolean global, String type, ArrayList<Operation> dimension) {
        this.global = global;
        this.type = type;
       // this.dimension = dimension;
    }

    public void setSize(Integer enter){
        this.dimensions = enter;
    }
    
    public int getSize(){
        return dimensions;
    }
    
    public void setGlobal(boolean global){
        this.global = global;
    }
    
    public boolean getGlobal(){
        return global;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public ArrayList<Operation> getDimension() {
//        return dimension;
//    }
//
//    public void setDimension(ArrayList<Operation> dimension) {
//        this.dimension = dimension;
//    }
    
    
    
}
