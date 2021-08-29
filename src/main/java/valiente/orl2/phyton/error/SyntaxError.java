/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.error;

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
        this.tokenError = tokenError;
    }
    
    public SyntaxError( int line, int column){
        super(line, column);
    }
    
    @Override
    public String getDescription(){
            StringBuilder string = new StringBuilder();          
            if(line<0 && column<0){
                line = lastLine;
                column = lastColumn;
            }
            string.append("Error sintactico ").append(description).append(", tipo: ").append(type).append(": ").append(tokenError).append(", linea: ").append(line).append(", column: ").append(column);
            string.append("\t\n").append("Excpected Tokens: ").append(expectedTokens);
            return string.toString();
    }

    public void setExpectedTokens(String tokens){
        this.expectedTokens = tokens;
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
