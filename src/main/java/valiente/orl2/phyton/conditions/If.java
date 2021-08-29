/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.phyton.instructions.Instruction;

/**
 *
 * @author camran1234
 */
public class If extends Instruction{
    ArrayList<ElseIf> elseifs = new ArrayList();
    Else elses = null;
    Condition condition;
    
    public If(Condition condition ,int line, int column) {
        super(line, column);
        this.condition = condition;
    }
    
    public If(int line, int column){
        super(line, column);
    }    
    
    /**
     * Agrega un nuevo else if o un nuevo else
     * Si hay errores con estos los arregla de una vez
     * @param instruction
     * @param list 
     */
    public void setNewElse(Instruction instruction, ArrayList<SyntaxError> list){
        if(instruction instanceof ElseIf){
            if(elses != null){
                SyntaxError error = new SyntaxError(instruction.getLine(), instruction.getColumn());
                error.setType("Else sin un if antes");
                error.setDescription("Agregar un if antes");
                list.add(error);
                System.err.println(error.getDescription());
            }else{
                //agregamos el elseif
                elseifs.add((ElseIf) instruction);
                instruction.setFather(this);
            }
        }else if(instruction instanceof Else){
            elses = (Else) instruction;
            elses.setFather(this);
        }
    }
    
    public void execute(){
        /*empty*/
    }
    
}
