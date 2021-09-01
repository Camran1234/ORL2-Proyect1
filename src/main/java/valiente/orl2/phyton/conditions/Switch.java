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
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
/**
 *
 * @author camran1234
 */
public class Switch extends Instruction{
    ArrayList<Case> cases = new ArrayList();
    Default finalCase = null;
    /*variable  a considerar*/
    Operation variable;
    
    public Switch( int line, int column) {
        super( line, column);
    }
    
    @Override
    public void execute() throws SemanticException{
        
        try {
            Value value = variable.execute();
            String type = value.getType();
            boolean founded=false;
            for(int index=0; index<cases.size(); index++){
                String compare=cases.get(index).getValue().execute().getType();
                //Comparamos el tipo de variable
                if(!compare.equalsIgnoreCase(type)){
                    throw new ValueException("se encontro en los casos un simbolo tipo "+compare+" cuando el switch maneja una variable tipo "+type,"Error en switch", getLine(), getColumn());
                }else{
                    String valor = cases.get(index).getValue().execute().getValue();
                    if(valor.equalsIgnoreCase(value.getValue())){
                        cases.get(index).execute();
                        founded=true;
                        break;
                    }
                }
            }
            if(finalCase!=null){
                if(!founded){
                    finalCase.execute();
                }
            }
            
        } catch (ValueException e) {
            SemanticError error = new SemanticError("Error en switch", getLine(), getColumn());
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
    }

    public Operation getVariable() {
        return variable;
    }

    public void setVariable(Operation variable) {
        this.variable = variable;
    }
    

    public ArrayList<Case> getCases() {
        return cases;
    }

    public void addCase(Case newCase){
        if(newCase!=null){
            if(finalCase!=null){
                SyntaxError newError = new SyntaxError(newCase.getLine(), newCase.getColumn());
                newError.setType("Operaciones repetidas");
                newError.setDescription("Se encontro mas de una declaracion default dentro del switch");
                TableOfValue.syntaxErrors.add(newError);
                System.err.println(newError);
            }else{
                this.cases.add(newCase);
                newCase.setFather(this);
            }
        }
    }
    
    public void setDefault(Default option){
        if(finalCase!=null){
            SyntaxError newError = new SyntaxError(option.getLine(), option.getColumn());
            newError.setType("Operaciones repetidas");
            newError.setDescription("Se encontro mas de una declaracion default dentro del switch");
            TableOfValue.syntaxErrors.add(newError);
            System.err.println(newError);
        }else{
            this.finalCase = option;
            option.setFather(this);
        }
    }
    
    public Default getDefault(){
        return this.finalCase;
    }
    
}
