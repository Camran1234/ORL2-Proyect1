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
public class Function extends Instruction{
    private boolean mode;
    private boolean cast;
    private String type;
    private Value name;
    private ArrayList<Operation> parameters=new ArrayList();
    private ArrayList<Operation> dimension = new ArrayList();
    
    public Function(int line, int column){
        super(line, column);
    }
    
    public Function(boolean cast,int line, int column){
        super(line, column);
        this.cast = cast;
    }
    
    
    
    public void execute(){
        
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public Value getName() {
        return name;
    }

    public void setName(Value name) {
        this.name = name;
    }

    public ArrayList<Operation> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Operation> parameters) {
        this.parameters = parameters;
    }

    public void setParamsIndicator(VariableIndicator indicator){
        this.type = indicator.getType();
        this.dimension = indicator.getDimension();
        this.mode = indicator.getGlobal();
    }
    
    

    
}
