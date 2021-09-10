/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.error;

/**
 *
 * @author camran1234
 */
public class ListaException extends Exception{
    private SemanticError error = null;
    
    public ListaException(String description){
        super(description);
    }
    
    public ListaException(String type, String description, int line, int column){
        SemanticError error = new SemanticError(type, line, column);
        error.setDescription(description);
        this.error = error;
    }
    
    public SemanticError getError(){
        return this.error;
    }
    
    
}
