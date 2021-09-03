/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.instructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.table.Parameter;
import valiente.orl2.phyton.table.Symbol;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.table.Type;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

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

    public ArrayList<Value> getNumberOfExtendeds(){
        ArrayList<Value> values = new ArrayList();
        for(int index=0; index<extendeds.size(); index++){
            values.add(extendeds.get(index).execute());
        }
        return values;
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
            
        }
        
    }
    
    public void declarar(){
        try {
            Type type = new Type(nombre, "pista", 0, new ArrayList<Parameter>(), this, new ArrayList<Integer>(), getIndentation(), this);
            Symbol symbol = new Symbol(type, 0, true);
            TableOfValue.addSymbol(symbol, line, column);
            for(int index=0; index<instructions.size(); index++){
                if(instructions.get(index) instanceof Function || instructions.get(index) instanceof Principal || 
                        instructions.get(index) instanceof Variable){
                    instructions.get(index).declarar();
                }
            }
        } catch (Exception e) {
            SemanticError error = new SemanticError("Funcion no declarada", getLine(), getColumn());
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
    }
    
    
    
}
