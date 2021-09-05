/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.specialInstructions.Return;
import valiente.orl2.phyton.table.TableOfValue;

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
    
    @Override
    public void execute() throws SemanticException{
        
        try {
            if(condition.execute().execute().getValue().equalsIgnoreCase("true")){
                for(int index=0; index< instructions.size(); index++){
                    if(instructions.get(index) instanceof Return && index != instructions.size()-1 ){
                        throw new ValueException("No se esperaban mas instrucciones adentro de while","Estado inalcanzable", getLine(), getColumn());
                    }else{                
                        instructions.get(index).execute();                    
                    }
                }
                TableOfValue.deleteAmbit(getIndentation()+1, this);
            }else if(condition.execute().execute().getValue().equalsIgnoreCase("false")){
                boolean founded=false;
                for(int index=0; index<elseifs.size(); index++){
                    if(elseifs.get(index).getCondition().execute().execute().getValue().equalsIgnoreCase("true")){
                        elseifs.get(index).execute();
                        founded=true;
                        break;
                    }
                }
                if(!founded){
                    if(elses!=null){
                        elses.execute();
                    }
                }
            }
        } catch (ValueException e) {
            
        }
    }
    
    public void setCondition(Condition condition){
        this.condition = condition;
    }
    
    public Condition getCondition(){
        return condition;
    }
    
}
