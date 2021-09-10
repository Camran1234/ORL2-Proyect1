/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.error;

/**
 * Exception to throw continues and breaks;
 * @author camran1234
 */
public class LoopException extends SemanticException{
    boolean mood = false;
    int line, column=0;
    /**
     * mood = true -> Throw a continue \\n
     * mood = false -> Throw a break \\n
     * @param message
     * @param mood 
     */
    public LoopException(String message, boolean mood, int line, int column){
        super(message); 
        this.mood=mood;
        this.line = line;
        this.column = column;
    }
    /**
     * mood = true -> Throw a continue 
     * mood = false -> Throw a break 
     * @return 
     */
    public boolean getMood(){
        return mood;
    }
    
    public int getLine(){
        return line;
    }
    
    public int getColumn(){
        return column;
    }
    
}
