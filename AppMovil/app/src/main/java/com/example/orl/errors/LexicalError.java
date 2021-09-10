/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orl.errors;

/**
 *
 * @author camran1234
 */
public class LexicalError extends Error{
    String lexema="";
    
    public LexicalError( int line, int column){
        super(line, column);
    }

    @Override
    public String getDescription(){
            StringBuilder string = new StringBuilder();
            string.append(description).append(": ").append(lexema);
            return string.toString();
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema += lexema;
    }
    
    
    
    
    
}
