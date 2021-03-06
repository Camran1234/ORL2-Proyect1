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
import valiente.orl2.reproduccion.Reproduccion;
import valiente.orl2.reproduccion.PistaReproduccion;

/**
 *
 * @author camran1234
 */
public class Pista extends Instruction{
    String nombre;
    ArrayList<Operation> extendeds;
    Symbol workingSymbol=null;
    PistaReproduccion reproductor = new PistaReproduccion();
    Symbol symbol;
    public Pista(int line, int column){
        super(line, column);
    }

    public void setSonido(Reproduccion reproduccion){
        reproductor.addNewSonido(reproduccion);
    }
    
    public PistaReproduccion getSonido(){
        return reproductor;
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
            TableOfValue.setWorkingSymbol(symbol);
            TableOfValue.setSelectedSymbol(symbol);
            for(int index=0; index<instructions.size(); index++){
                if(instructions.get(index) instanceof Principal){
                    instructions.get(index).execute();
                }
            }
            TableOfValue.setWorkingSymbol(null);
        } catch (SemanticException e) {
            
        }
        
    }
    
    public void declarar(){
        try {
            Type type = new Type(nombre, "pista", 0, new ArrayList<Parameter>(), this, new ArrayList<Integer>(), getIndentation(), this);
            Symbol symbol = new Symbol(type, 0, true, line, column);
            TableOfValue.addSymbol(symbol, line, column);
            this.symbol = symbol;
            //Agregamos el simbolo
            TableOfValue.setWorkingSymbol(symbol);
            TableOfValue.setSelectedSymbol(symbol);
            workingSymbol = symbol;
            int numberPrincipal=0;
            for(int index=0; index<instructions.size(); index++){
                if(instructions.get(index) instanceof Function || instructions.get(index) instanceof Principal || 
                        instructions.get(index) instanceof VariableChunk){
                    if(instructions.get(index) instanceof Principal){
                        numberPrincipal++;
                        if(numberPrincipal>1){
                            throw new Exception("Solo puede existir un metodo principal por cada pista");
                        }
                    }
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
