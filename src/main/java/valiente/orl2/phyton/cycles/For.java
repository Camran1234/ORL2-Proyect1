/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.cycles;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.conditions.Condition;
import valiente.orl2.phyton.error.LoopException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Variable;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.specialInstructions.Return;
import valiente.orl2.phyton.table.TableOfValue;
/**
 *
 * @author camran1234
 */
public class For extends Instruction{
    Variable variable;
    Condition condition;
    Variable paso;
    
    public For( int line, int column) {
        super(line, column);
    }
    
    @Override
    public void execute() throws SemanticException{
        try {
            //Ejecutamos la variable para que se asigne en la tabla de simbolos
            this.variable.setIndentation(this.getIndentation()+1);
            this.paso.setIndentation(this.getIndentation()+1);
            variable.execute();
            //Ejecutamos la condicion
            //Que nos da una operacion y la ejecutamos para obtener el valor
            while(Boolean.parseBoolean(condition.execute().execute().getValue())){
                try {
                    //Ejecutamos las instrucciones
                    for(int index=0; index< instructions.size(); index++){                    
                        if(instructions.get(index) instanceof Return && index != instructions.size()-1 ){                
                            throw new ValueException("No se esperaban mas instrucciones adentro de while","Estado inalcanzable", getLine(), getColumn());                    
                        }else{                
                            instructions.get(index).execute();                    
                        }                
                    }
                    paso.execute();
                } catch (LoopException e) {
                    if(!e.getMood()){
                        break;
                    }
                }
            }
        } catch (ValueException e) {
        }
         
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Variable getPaso() {
        return paso;
    }

    public void setPaso(Variable paso) {
        this.paso = paso;
    }
    
    
    
}
