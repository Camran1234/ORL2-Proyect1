/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.cycles;

import valiente.orl2.phyton.error.LoopException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.specialInstructions.Exit;
import valiente.orl2.phyton.specialInstructions.Return;
import valiente.orl2.phyton.table.TableOfValue;
/**
 *
 * @author camran1234
 */
public class DoWhile extends Instruction{
    While conditionWhile;
    
    public DoWhile(int line, int column){
        super(line, column);
    }
    
    @Override
    public void execute() throws SemanticException{
        boolean flag=false;
        try {
            do{
                try {
                    for(int index=0; index<instructions.size(); index++){
                        //instructions.size()-1 porque seria la ultima instruccion
                        if(instructions.get(index) instanceof Return){
                            if(index!=instructions.size()-1){
                                throw new ValueException("Hay mas instrucciones despues de return","Error en caso", getLine(), getColumn());
                            }else{
                                instructions.get(index).execute();
                            }
                        }else{
                            instructions.get(index).execute();
                        }
                    }
                    if(flag){
                        break;
                    }
                } catch (LoopException e) {
                    if(!e.getMood()){
                        break;
                    }
                }
            }while(Boolean.parseBoolean(conditionWhile.getCondition().execute().execute().getValue()));
        } catch (ValueException e) {
            
        }
        
        
    }

    public While getConditionWhile() {
        return conditionWhile;
    }

    public void setConditionWhile(While conditionWhile) {
        this.conditionWhile = conditionWhile;
    }
    
    
    
}
