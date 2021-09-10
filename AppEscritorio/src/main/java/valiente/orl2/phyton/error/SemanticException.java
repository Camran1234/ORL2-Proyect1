/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.error;

import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;

/**
 *
 * @author camran1234
 */
public class SemanticException extends Exception{
    SemanticError newError = null;
    Operation returnOperation=null;
    //This is better to throw exceptions
    public SemanticException(String description, String type, int line, int column){
        super(description);
        SemanticError newError = new SemanticError(type, line, column);
        newError.setDescription(description);
        this.newError = newError;
    }
    
    public SemanticException(String message, Operation returnOperation){
        super(message);
        this.returnOperation = returnOperation;
    }
    
    public Operation getOperation(){
        return returnOperation;
    }
    
    public SemanticException(String message){
        super(message);
    }
    
    /**
     * Devuelve el error semantico generado
     * @return 
     */
    public SemanticError getError(){
        return newError;
    }
    
    public boolean isReturn(){
        if(newError==null){
            return true;
        }else{
            return false;
        }
    }

    public void checkErrorAmbit() {
        if(!isReturn()){
                TableOfValue.semanticErrors.add(getError());
        }else if(this instanceof LoopException){
            LoopException ex = (LoopException)this;
            String instruction="";
            if(ex.getMood()){
                instruction = "continuar";
            }else{
                instruction = "salir";
            }
            SemanticError error = new SemanticError("Problema con ", ex.getLine(), ex.getColumn());            
            error.setDescription("No se invoco dentro de un ciclo");            
            TableOfValue.semanticErrors.add(error);
        }
        
    }
    
}
