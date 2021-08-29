/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SyntaxError;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;

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
   
    
    public void execute(){
        /*empty*/
    }
    
}
