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
public class SemanticError extends Error{
    private String solution="";
    
    public SemanticError(String type, int line, int column){
        super(type, line, column);
    }
    
    @Override
    public String execute(){
        return "";
    }

    public String getDescription(){
        StringBuilder string = new StringBuilder();
        string.append(type).append(": ").append(description);
        return string.toString();
    }
    
    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    
    
    
    
}
