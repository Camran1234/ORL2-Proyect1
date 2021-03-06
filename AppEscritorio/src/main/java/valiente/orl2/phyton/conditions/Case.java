/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.specialInstructions.Exit;
import valiente.orl2.phyton.specialInstructions.Return;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;


/**
 *
 * @author camran1234
 */
public class Case extends Instruction{
    private Operation value;
    
    
    public Case(int indentation, int line, int column){
        super(indentation, line, column);
    }

    public Operation getValue() {
        return value;
    }

    public void setValue(Operation value) {
        this.value = value;
    }
    
    /**
     * Devuelve verdadero si son iguales o no es valido el valor
     * @param value
     * @return 
     */
    public boolean comprobarValores(Operation value){
        Value localValue = this.value.execute();
        if(localValue.getRawType().equalsIgnoreCase("variable")){
            return true;
        }
        if(localValue.getRawValue().equals(value.execute().getRawValue())){
            return true;
        }else{
            return false;
        }
    }
    
    
    public void execute() throws SemanticException{
        try {
            for(int index=0; index<instructions.size(); index++){    
                if(instructions.get(index) instanceof Exit){
                    if(index!=instructions.size()-1){
                        throw new ValueException("Hay mas instrucciones despues de exit","Error en caso",getLine(), getColumn());
                    }else{
                        break;
                    }
                }else if(instructions.get(index) instanceof Return){
                    if(index!=instructions.size()-1){
                        throw new ValueException("Hay mas instrucciones despues de return","Error en caso", getLine(), getColumn());
                    }else{
                        instructions.get(index).execute();
                    }
                }else{
                    instructions.get(index).execute();
                }
            }
            TableOfValue.deleteAmbit(getIndentation()+1, this);
        } catch (ValueException e) {
        }
        
    }
}
