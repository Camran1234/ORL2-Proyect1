/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticException;

/**
 *
 * @author camran1234
 */
public class VariableChunk extends Instruction{
    ArrayList<Variable> variables;
    
    
    public VariableChunk(ArrayList<Variable> list, Assignment assignment){
        for(int index=0; index<list.size(); index++){
            list.get(index).setValue(assignment);
            list.get(index).setDeclarado(false);
        }
        this.variables = list;
    }
    
    public VariableChunk(ArrayList<Variable> list, Assignment assignment, VariableIndicator indicator){
        for(int index=0; index<list.size(); index++){
            list.get(index).setValue(assignment);
            list.get(index).setParameters(indicator);
        }
        this.variables = list;
        
    }
    @Override
    public void execute() throws SemanticException{
        for(int index=0; index< variables.size(); index++){
            variables.get(index).setFather(father);
            variables.get(index).setIndentation(indentation);
            variables.get(index).execute();
        }
    }
    
    
    public void declarar() {
        for(int index=0; index< variables.size(); index++){
            variables.get(index).setFather(father);
            variables.get(index).setIndentation(indentation);
            variables.get(index).declarar();
        }
    }
    
    public boolean isGlobal(){
        return variables.get(0).getMode();
    }
    
}
