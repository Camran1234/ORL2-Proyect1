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
import valiente.orl2.phyton.table.Character;
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
            e.printStackTrace();
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
        String l = left.getType();
        String r = right.getType();
        if(l.equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV == rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un entero con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV == rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un doble con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("caracter")){
            char leftV = Character.transform(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV == rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un caracter con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }       
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                if(leftV == rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un boolean con una cadena");
            }
        }else if(l.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(r.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                if(leftV.equals(rightV)){
                    return new Value("boolean","true",line, column);
                }else{
                    return new Value("boolean","false",line, column);
                }
            }else{
                throw new Exception("No se puede comparar un "+r+" con una cadena");
            }
            
        }
        return result;
    }
    
    private Value Differentiation(Value left, Value right, int line, int column) throws Exception{
        Value result = null;
        String l = left.getType();
        String r = right.getType();
        if(l.equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV != rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un entero con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV != rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un doble con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("caracter")){
            char leftV = Character.transform(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV != rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un caracter con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }       
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                if(leftV != rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un boolean con una cadena");
            }
        }else if(l.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(r.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                if(leftV.equals(rightV)==false){
                    return new Value("boolean","true",line, column);
                }else{
                    return new Value("boolean","false",line, column);
                }
            }else{
                throw new Exception("No se puede comparar un "+r+" con una cadena");
            }
        }
        return result;
    }
    
    private Value MoreThan(Value left, Value right, int line, int column) throws Exception{
        
        Value result = null;
         String l = left.getType();
        String r = right.getType();
        if(l.equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV > rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un entero con cadena ");
            }
            
        }else if(l.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV > rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un doble con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("caracter")){
            char leftV = Character.transform(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV > rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un caracter con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux > rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }       
            }else if(r.equalsIgnoreCase("boolean")){
                throw new Exception("No se puede comparar un booleano con un booleano");
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un boolean con una cadena");
            }
        }else if(l.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(r.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                int valoresLeft = leftV.length();
                int valoresRight = rightV.length();
                if(valoresLeft>valoresRight){
                    return new Value("boolean","true",line, column);
                }else{
                    return new Value("boolean","false",line, column);
                }
            }else{
                throw new Exception("No se puede comparar un "+r+" con una cadena");
            }
        }
        return result;
    }
    
    private Value EqualMoreThan(Value left, Value right, int line, int column) throws Exception{
        Value result = null;
         String l = left.getType();
        String r = right.getType();
        if(l.equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV >= rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un entero con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV >= rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un doble con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("caracter")){
            char leftV = Character.transform(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV >= rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un caracter con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux >= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }       
            }else if(r.equalsIgnoreCase("boolean")){
                throw new Exception("No se puede comparar un booleano con un booleano");
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un con una cadena");
            }
        }else if(l.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(r.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                int valoresLeft = leftV.length();
                int valoresRight = rightV.length();
                if(valoresLeft>=valoresRight){
                    return new Value("boolean","true",line, column);
                }else{
                    return new Value("boolean","false",line, column);
                }
            }else{
                throw new Exception("No se puede comparar un "+r+" con una cadena");
            }
        }
        return result;
    }
    
    private Value LesserThan(Value left, Value right, int line, int column) throws Exception{
    
        Value result = null;
         String l = left.getType();
        String r = right.getType();
        if(l.equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV < rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un entero con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV < rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un doble con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("caracter")){
            char leftV = Character.transform(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV < rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un caracter con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux < rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }       
            }else if(r.equalsIgnoreCase("boolean")){
                throw new Exception("No se puede comparar un booleano con un booleano");
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar con una cadena");
            }
        }else if(l.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(r.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                int valoresLeft = leftV.length();
                int valoresRight = rightV.length();
                if(valoresLeft<valoresRight){
                    return new Value("boolean","true",line, column);
                }else{
                    return new Value("boolean","false",line, column);
                }
            }else{
                throw new Exception("No se puede comparar un "+r+" con una cadena");
            }
        }
        return result;
    }
    
    private Value EqualLesserThan(Value left, Value right, int line, int column) throws Exception{
        Value result = null;
         String l = left.getType();
        String r = right.getType();
        if(l.equalsIgnoreCase("entero")){
            int leftV = Integer.parseInt(left.getValue());
            
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV <= rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un entero con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV <= rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un doble con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("caracter")){
            char leftV = Character.transform(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                if(leftV <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }        
            }else if(r.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rex = 0;
                if(rightV){
                    rex = 1;
                }
                if(leftV <= rex){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar un caracter con una cadena");
            }
            
        }else if(l.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(r.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());                
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }
            }else if(r.equalsIgnoreCase("caracter")){
                char rightV = Character.transform(right.getValue());
                int aux = 0;
                if(leftV){
                    aux=1;
                }
                if(aux <= rightV){
                    return new Value("boolean","true", line, column);
                }else{
                    return new Value("boolean","false", line, column);
                }       
            }else if(r.equalsIgnoreCase("boolean")){
                throw new Exception("No se puede comparar un booleano con un booleano");
            }else if(r.equalsIgnoreCase("cadena")){
                throw new Exception("No se puede comparar con una cadena");
            }
        }else if(l.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(r.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                int valoresLeft = leftV.length();
                int valoresRight = rightV.length();
                if(valoresLeft<=valoresRight){
                    return new Value("boolean","true",line, column);
                }else{
                    return new Value("boolean","false",line, column);
                }
            }else{
                throw new Exception("No se puede comparar un "+r+" con una cadena");
            }
        }
        return result;
    }
    
}
