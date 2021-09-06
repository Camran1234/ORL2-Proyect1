/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.cycles;

import java.util.ArrayList;
import valiente.orl2.phyton.conditions.Condition;
import valiente.orl2.phyton.error.LoopException;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.specialInstructions.Return;
import valiente.orl2.phyton.table.TableOfValue;

/**
 *
 * @author camran1234
 */
public class While extends Instruction{
    Condition condition;
    
    public While(int line, int column) {
        super(line, column);
        this.condition = condition;
    }
    @Override
    public void execute()throws SemanticException{
        try {            
            while(Boolean.parseBoolean(condition.execute().execute().getValue())){
                try {
                    for(int index=0; index< instructions.size(); index++){
                         if(instructions.get(index) instanceof Return){
                            if(index!=instructions.size()-1){
                                throw new ValueException("Hay mas instrucciones despues de return","Error en While", getLine(), getColumn());
                            }else{
                                instructions.get(index).execute();
                            }
                        }else{
                            instructions.get(index).execute();
                        }
                    }
                    TableOfValue.deleteAmbit(getIndentation()+1, this);
                } catch (LoopException e) {
                    if(!e.getMood()){
                        break;
                    }else{
                        TableOfValue.deleteAmbit(getIndentation()+1, this);
                    }
                }
                
            }
        } catch (ValueException e) {
        }
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    
}
