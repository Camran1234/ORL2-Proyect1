/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Function extends Instruction{
    private boolean mode;
    private String type;
    private Value name;
    private ArrayList<Operation> parameters=new ArrayList();
    //Dimension of the value to return
    private ArrayList<Operation> dimension = new ArrayList();
    //The result to return
    private Value value;
    
    
    public Function(int line, int column){
        super(line, column);
    }
    
    @Override
    public void execute()  throws SemanticException{
        try {
            for(int index=0; index<instructions.size(); index++){
                instructions.get(index).execute();
            }
            TableOfValue.deleteAmbit(this.indentation+1);
        } catch (SemanticException e) {
            if(e.isReturn()){
               value = e.getOperation().execute();
            }
        }
    }
    
    /**
     * Get the value after the execute
     * @return 
     */
    public Value getValue() throws SemanticException{
        this.execute();
        return this.value;
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
