/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;

/**
 *
 * @author camran1234
 */
public class Pista extends Instruction{
    String nombre;
    ArrayList<Operation> extendeds;
    
    public Pista(int line, int column){
        super(line, column);
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Operation> getExtendeds() {
        return extendeds;
    }

    public void setExtendeds(ArrayList<Operation> extendeds) {
        this.extendeds = extendeds;
    }

    public void execute() throws SemanticException{
        try {
            for(int index=0; index<instructions.size(); index++){
                instructions.get(index).execute();
            }
        } catch (SemanticException e) {
            e.checkErrorAmbit();
        }
        
    }
    
    
    
}
