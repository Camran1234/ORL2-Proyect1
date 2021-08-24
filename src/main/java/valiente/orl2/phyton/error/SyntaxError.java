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
public class SyntaxError extends Error{
    String tokenError="";
    String expectedTokens = "";
    
    public SyntaxError(String type,String tokenError, int line, int column) {
        super(type, line, column);
        this.tokenError = tokenError;
    }
    
    @Override
    public String execute(){
        return "";
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
