/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.ArrayList;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.values.Operation;

/**
 *
 * @author camran1234
 */
public class Switch extends Instruction{
    ArrayList<Case> cases = new ArrayList();
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

    public void setCases(ArrayList<Case> cases) {
        ArrayList<Case> aux = new ArrayList();
        for(int index=cases.size()-1; index>=0; index--){
            aux.add(cases.get(index));
        }
        this.cases = aux;
    }    
    
    public void execute(){
        /*empty*/
    }
    
}
