/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orl.errors;

/**
 * Clase para invocar errores sintacticos
 * Donde tipo es la descripcion corta del error
 * Token error es el token que genero el error
 * Description es la descripcion del error
 * @author camran1234
 */
public class SyntaxError extends Error{
    public static int lastLine = 0;
    public static int lastColumn=0;
    String tokenError="";
    String expectedTokens = "";
    
    
    public SyntaxError(String type,String tokenError, int line, int column) {
        super(type, line, column);
        if(line==-1){
            setLine(lastLine);
        }
        if(column==-1){
            setColumn(lastColumn);
        }
        
        this.tokenError = tokenError;
    }
    
    public SyntaxError( int line, int column){
        super(line, column);
        if(line==-1){
            setLine(lastLine);
        }
        if(column==-1){
            setColumn(lastColumn);
        }
        
    }
    
    @Override
    public String getDescription(){
            StringBuilder string = new StringBuilder();
            string.append(type+": "+description+", POSIBLES SOLUCIONES: "+expectedTokens);
            return string.toString();
    }

    public void setExpectedTokens(String tokens){
        this.expectedTokens = tokens;
        checkValues();
    }
    
    //Funcion para cambiarle el nombre a los tokens
    public void checkValues(){
      //  expectedTokens = tokenParser.checkValue(expectedTokens);
    }
    
    public String getExpectedTokens(){
        return this.expectedTokens;
    }
    
    public String getTokenError() {
        return tokenError;
    }

    public void setTokenError(String tokenError) {
        this.tokenError = tokenError;
    }
    
    
    
    
    
}
