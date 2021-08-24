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
public class Variable extends Instruction{
    boolean declarado=false;
    boolean mode =false;
    boolean array=false;
    String name ="";
    String type ="";
    Assignment value;
    /*Para arreglos*/
    ArrayList<Operation> dimension = new ArrayList();

    
    public Variable(int line, int column){
        this.line=line;
        this.column=column;
    }
    
    public void execute(){
        
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
