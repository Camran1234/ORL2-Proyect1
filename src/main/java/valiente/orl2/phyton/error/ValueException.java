/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.error;

import valiente.orl2.phyton.table.TableOfValue;

/**
 * Para valores y errores semanticos adentro de las instrucciones
 * Se recomeinda lanzar un error de este tipo para errores de valores o lanzar un break
 * @author camran1234
 */
public class ValueException extends Exception{
    SemanticError newError = null;
    //This is better to throw exceptions
    public ValueException(String description, String type, int line, int column){
        super(description);
        SemanticError newError = new SemanticError(type, line, column);
        newError.setDescription(description);
        checkErrorAmbit();
    }
    
    public ValueException(String nothing, boolean isReturn){
        super(nothing);
    }
    
    public ValueException(String text){
        super(text);
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
        }
    }
}
