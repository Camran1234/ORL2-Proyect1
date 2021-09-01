/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.table.TableOfValue;

/**
 *
 * @author camran1234
 */
public class LogicalOperator {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;

    
    public Operation MakeComparation(Operation leftOp, Operation rightOp, String operation, int line, int column){
        Value result=null;
        Value left = leftOp.execute();
        Value right = rightOp.execute();
        TypeParser typeParser = new TypeParser();
        String higherType = typeParser.getHigherType(left, right, line, column);
        Value parsedLeft=null;
        Value parsedRight=null;
        try {
            parsedLeft = typeParser.parseType(left, higherType, line, column);
        } catch (Exception e) {
            SemanticError newErrors = new SemanticError("Tranformacion invalida", line, column);
            newErrors.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(newErrors);
        }
        try {
            parsedRight = typeParser.parseType(right, higherType, line, column);
        } catch (Exception e) {
            SemanticError newErrors = new SemanticError("Tranformacion invalida", line, column);
            newErrors.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(newErrors);
        }
        
        if(operation.equalsIgnoreCase("==")){
            try {
                result = Equalization(parsedLeft, parsedRight, line, column);
            } catch (Exception ex) {
                SemanticError newError = new SemanticError("Error en la comparacion", line, column);
                newError.setDescription(ex.getMessage());
                TableOfValue.semanticErrors.add(newError);
            }
        }else if(operation.equalsIgnoreCase("!=")){
            try {
                result = Differentiation(parsedLeft, parsedRight, line, column);
            } catch (Exception ex) {
                SemanticError newError = new SemanticError("Error en la comparacion", line, column);
                newError.setDescription(ex.getMessage());
                TableOfValue.semanticErrors.add(newError);
            }
        }else if(operation.equalsIgnoreCase(">")){
            try {
                result = MoreThan(parsedLeft, parsedRight, line, column);
            } catch (Exception ex) {
                SemanticError newError = new SemanticError("Error en la comparacion", line, column);
                newError.setDescription(ex.getMessage());
                TableOfValue.semanticErrors.add(newError);
            }
        }else if(operation.equalsIgnoreCase(">=")){
            try {
                result = EqualMoreThan(parsedLeft, parsedRight, line, column);
            } catch (Exception ex) {
                SemanticError newError = new SemanticError("Error en la comparacion", line, column);
                newError.setDescription(ex.getMessage());
                TableOfValue.semanticErrors.add(newError);
            }
        }else if(operation.equalsIgnoreCase("<")){
            try {
                result = LesserThan(parsedLeft, parsedRight, line, column);
            } catch (Exception ex) {
                SemanticError newError = new SemanticError("Error en la comparacion", line, column);
                newError.setDescription(ex.getMessage());
                TableOfValue.semanticErrors.add(newError);
            }
        }else if(operation.equalsIgnoreCase("<=")){
            try {
                result = EqualLesserThan(parsedLeft, parsedRight, line, column);
            } catch (Exception ex) {
                SemanticError newError = new SemanticError("Error en la comparacion", line, column);
                newError.setDescription(ex.getMessage());
                TableOfValue.semanticErrors.add(newError);
            }
        }
        Operation finalOperation = null;
        if(result!=null){
             finalOperation = new Operation(result, line, column);
        }
        return finalOperation;
    }
    
    /**
     * Determinate a boolean value of the comparation result
     * @param left
     * @param right
     * @param line
     * @param column
     * @return 
     */
    private Value Equalization(Value left, Value right, int line, int column) throws Exception{
        Value result = null;
        if(left.getType().equalsIgnoreCase("entero") && right.getType().equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            int rightV = Integer.parseInt(right.getValue());
            if(leftV == rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("doble") && right.getType().equalsIgnoreCase("entero")){
            double leftV = Double.parseDouble(left.getValue());
            double rightV = Double.parseDouble(right.getValue());
            if(leftV == rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("caracter") && right.getType().equalsIgnoreCase("entero")){
            char leftV = left.getValue().charAt(0);
            char rightV = right.getValue().charAt(0);
            if(leftV == rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
            
        }else if(left.getType().equalsIgnoreCase("boolean") && right.getType().equalsIgnoreCase("entero")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            boolean rightV = Boolean.parseBoolean(right.getValue());
            if(leftV == rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("string") && right.getType().equalsIgnoreCase("entero")){
            String leftV = left.getValue();
            String rightV = right.getValue();
            if(leftV.equals(rightV)){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }
        return result;
    }
    
    private Value Differentiation(Value left, Value right, int line, int column) throws Exception{
        Value result = null;
        if(left.getType().equalsIgnoreCase("entero") && right.getType().equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            int rightV = Integer.parseInt(right.getValue());
            if(leftV != rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("doble") && right.getType().equalsIgnoreCase("entero")){
            double leftV = Double.parseDouble(left.getValue());
            double rightV = Double.parseDouble(right.getValue());
            if(leftV != rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("caracter") && right.getType().equalsIgnoreCase("entero")){
            char leftV = left.getValue().charAt(0);
            char rightV = right.getValue().charAt(0);
            if(leftV != rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
            
        }else if(left.getType().equalsIgnoreCase("boolean") && right.getType().equalsIgnoreCase("entero")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            boolean rightV = Boolean.parseBoolean(right.getValue());
            if(leftV != rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("string") && right.getType().equalsIgnoreCase("entero")){
            String leftV = left.getValue();
            String rightV = right.getValue();
            if(!leftV.equals(rightV)){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }
        return result;
    }
    
    private Value MoreThan(Value left, Value right, int line, int column) throws Exception{
        
        Value result = null;
        if(left.getType().equalsIgnoreCase("entero") && right.getType().equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            int rightV = Integer.parseInt(right.getValue());
            if(leftV > rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("doble") && right.getType().equalsIgnoreCase("entero")){
            double leftV = Double.parseDouble(left.getValue());
            double rightV = Double.parseDouble(right.getValue());
            if(leftV > rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("caracter") && right.getType().equalsIgnoreCase("entero")){
            char leftV = left.getValue().charAt(0);
            char rightV = right.getValue().charAt(0);
            if(leftV > rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
            
        }else if(left.getType().equalsIgnoreCase("boolean") && right.getType().equalsIgnoreCase("entero")){
            throw new Exception("No se puede comparar dos booleanos con el operador >");
        }else if(left.getType().equalsIgnoreCase("string") && right.getType().equalsIgnoreCase("entero")){
            String leftV = left.getValue();
            String rightV = right.getValue();
            int leftChar = leftV.length();
            int rightChar = rightV.length();
            if(leftChar > rightChar){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }
        return result;
    }
    
    private Value EqualMoreThan(Value left, Value right, int line, int column) throws Exception{
        Value result = null;
        if(left.getType().equalsIgnoreCase("entero") && right.getType().equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            int rightV = Integer.parseInt(right.getValue());
            if(leftV >= rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("doble") && right.getType().equalsIgnoreCase("entero")){
            double leftV = Double.parseDouble(left.getValue());
            double rightV = Double.parseDouble(right.getValue());
            if(leftV >= rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("caracter") && right.getType().equalsIgnoreCase("entero")){
            char leftV = left.getValue().charAt(0);
            char rightV = right.getValue().charAt(0);
            if(leftV >= rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
            
        }else if(left.getType().equalsIgnoreCase("boolean") && right.getType().equalsIgnoreCase("entero")){
            throw new Exception("No se puede comparar dos booleanos con el operador >=");
        }else if(left.getType().equalsIgnoreCase("string") && right.getType().equalsIgnoreCase("entero")){
            String leftV = left.getValue();
            String rightV = right.getValue();
            int leftChar = leftV.length();
            int rightChar = rightV.length();
            if(leftChar >= rightChar){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }
        return result;
    }
    
    private Value LesserThan(Value left, Value right, int line, int column) throws Exception{
    
        Value result = null;
        if(left.getType().equalsIgnoreCase("entero") && right.getType().equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            int rightV = Integer.parseInt(right.getValue());
            if(leftV < rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("doble") && right.getType().equalsIgnoreCase("entero")){
            double leftV = Double.parseDouble(left.getValue());
            double rightV = Double.parseDouble(right.getValue());
            if(leftV < rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("caracter") && right.getType().equalsIgnoreCase("entero")){
            char leftV = left.getValue().charAt(0);
            char rightV = right.getValue().charAt(0);
            if(leftV < rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
            
        }else if(left.getType().equalsIgnoreCase("boolean") && right.getType().equalsIgnoreCase("entero")){
            throw new Exception("No se puede comparar dos booleanos con el operador <");
        }else if(left.getType().equalsIgnoreCase("string") && right.getType().equalsIgnoreCase("entero")){
            String leftV = left.getValue();
            String rightV = right.getValue();
            int leftChar = leftV.length();
            int rightChar = rightV.length();
            if(leftChar < rightChar){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }
        return result;
    }
    
    private Value EqualLesserThan(Value left, Value right, int line, int column) throws Exception{
        Value result = null;
        if(left.getType().equalsIgnoreCase("entero") && right.getType().equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            int rightV = Integer.parseInt(right.getValue());
            if(leftV <= rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("doble") && right.getType().equalsIgnoreCase("entero")){
            double leftV = Double.parseDouble(left.getValue());
            double rightV = Double.parseDouble(right.getValue());
            if(leftV <= rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }else if(left.getType().equalsIgnoreCase("caracter") && right.getType().equalsIgnoreCase("entero")){
            char leftV = left.getValue().charAt(0);
            char rightV = right.getValue().charAt(0);
            if(leftV <= rightV){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
            
        }else if(left.getType().equalsIgnoreCase("boolean") && right.getType().equalsIgnoreCase("entero")){
            throw new Exception("No se puede comparar dos booleanos con el operador <=");
        }else if(left.getType().equalsIgnoreCase("string") && right.getType().equalsIgnoreCase("entero")){
            String leftV = left.getValue();
            String rightV = right.getValue();
            int leftChar = leftV.length();
            int rightChar = rightV.length();
            if(leftChar <= rightChar){
                result = new Value("boolean","true", line, column);
            }else{
                result = new Value("boolean","false", line, column);
            }
        }
        return result;
    }
    
}
