/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import valiente.orl2.phyton.values.Operation;

/**
 *
 * @author camran1234
 */
public class Condition {
   
   private int line, column=0; 
   private Condition left;
   //And, Or, Xor...
   private String operador="";
   private Condition right;
   /*Unario*/
   private String unary;
   private Condition comparation;
   
   private Comparation value;
   
   public Condition(int line, int column){
       this.line = line;
       this.column = column;
   }
   
   public Condition(Condition left, String operador, Condition right, int line, int column){
       this.left = left;
       this.right = right;
       this.operador = operador;
       this.line = line;
       this.column = column;
   }
   
   public Condition(Comparation value, int line, int column){
       this.value = value;
       this.line = line;
       this.column = column;
   }

   public Operation execute(){
       
       return null;
   }
   
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Condition getLeft() {
        return left;
    }

    public void setLeft(Condition left) {
        this.left = left;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Condition getRight() {
        return right;
    }

    public void setRight(Condition right) {
        this.right = right;
    }

    public String getUnary() {
        return unary;
    }

    public void setUnary(String unary) {
        this.unary = unary;
    }

    public Condition getComparation() {
        return comparation;
    }

    public void setComparation(Condition comparation) {
        this.comparation = comparation;
    }

    public Comparation getValue() {
        return value;
    }

    public void setValue(Comparation value) {
        this.value = value;
    }
   
   
   
   
}
