/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.conditions;

import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.values.RelationalOperator;
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
   private String unary="";
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
       if(left!=null && right!=null){
           try {
               Operation leftOp = left.execute();
               Operation rightOp = right.execute();
               RelationalOperator relationalOperator = new RelationalOperator();
               Operation operation = relationalOperator.MakeCondition(leftOp, rightOp, operador, line, column);
               Value theValor = operation.execute();
               if(theValor.getType().equalsIgnoreCase("boolean") && unary.equals("!")){
                   boolean valor = Boolean.parseBoolean(theValor.getValue());
                   operation = new Operation(new Value("boolean", Boolean.toString(valor), line, column), line, column);
               }
               return operation; 
           } catch (ValueException ex) {
               Logger.getLogger(Condition.class.getName()).log(Level.SEVERE, null, ex);
           }
       }else if(comparation !=null){
           try {
               Operation result = comparation.execute();
               Value newValue = result.execute();
               if(newValue.getType().equalsIgnoreCase("boolean")){
                   boolean valor=false;
                       valor = Boolean.parseBoolean(newValue.getValue());
                   if(valor){
                       boolean asignacion = false;
                       if(unary.equals("!")){
                           asignacion=true;
                       }
                       System.out.println("VALOR ES "+Boolean.toString(asignacion));
                       newValue = new Value("boolean",Boolean.toString(asignacion), line, column);
                   }else{
                       boolean asignacion = true;
                       if(unary.equals("!")){
                           asignacion=false;
                       }
                       System.out.println("VALOR ES "+Boolean.toString(asignacion));
                       newValue = new Value("boolean",Boolean.toString(asignacion), line, column);
                   }
               }else{
                   throw new ValueException("El valor no era boolean", "Tipo incompatible en condicion", line, column);
               }
               result = new Operation(newValue, result.getLine(), result.getColumn());
               return result;
           } catch (ValueException ex) {
           }
       }else if(value !=null){
           try {
               Operation operation = this.value.execute();
               Value theValor = operation.execute();
               if(theValor.getType().equalsIgnoreCase("boolean") && unary.equals("!")){
                   boolean valor = Boolean.parseBoolean(theValor.getValue());
                   int line = theValor.getLine();
                   int column = theValor.getColumn();
                   if(valor){
                       valor=false;
                   }else{
                       valor=true;
                   }
                   operation = new Operation(new Value("boolean", Boolean.toString(valor), line, column), line, column);
               }
               
               return operation;
           } catch (ValueException ex) {
               
           }
       }
       
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
