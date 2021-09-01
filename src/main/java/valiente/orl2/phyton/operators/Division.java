/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.operators;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Division {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    public Value MakeDivision(Value left, Value right, int line, int column) throws Exception{
        String typeL = left.getType();
        String typeR = right.getType();
        
        if(typeL.equalsIgnoreCase("entero")){
            if(typeR.equalsIgnoreCase("entero")){
                int leftV = Integer.parseInt(left.getValue());
                int rightV = Integer.parseInt(right.getValue());
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }else{
                    return new Value("entero",Integer.toString(leftV/rightV), line, column );
                }
                
            }else if(typeR.equalsIgnoreCase("doble")){
                int leftV = Integer.parseInt(left.getValue());
                double rightV = Double.parseDouble(right.getValue());
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }else{
                    double result = leftV / rightV;
                    return new Value("doble",Double.toString(result), line, column);
                }
                
            }else if(typeR.equalsIgnoreCase("boolean")){
                int leftV = Integer.parseInt(left.getValue());
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int result=0;
                if(rightV){
                    result = leftV / 1;
                }else{
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el false");
                    semanticErrors.add(newError);
                    return null;
                }
                return new Value("entero",Integer.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("caracter")){
                int leftV = Integer.parseInt(left.getValue());
                char rightV = left.getValue().charAt(0);
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }else{
                    int result = leftV / rightV;
                    return new Value("entero",Integer.toString(result), line, column);
                }
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                    newError.setDescription("No se puede dividir un entero entre una cadena");
                    newError.setSolution("Cambiar a suma");
                    semanticErrors.add(newError);
                    return null;
            }
        }else if(typeL.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(typeR.equalsIgnoreCase("entero")){
                
                int rightV = Integer.parseInt(right.getValue());
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                double result = leftV / rightV;
                return new Value("doble", Double.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(rightV ==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                double result = leftV / rightV;
                return new Value("doble",Double.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("boolean")){
                
                boolean rightV = Boolean.parseBoolean(right.getValue());
                if(!rightV){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                return new Value("doble", Double.toString(leftV), line, column);
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                double result = leftV / rightV;
                return new Value("doble", Double.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipo incompatible",line, column);
                    newError.setDescription("No se puede realizar una division a un doble y una cadena");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
            }
        }else if(typeL.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(typeR.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                int resultado;
                if(leftV){
                    resultado = 1 / rightV;
                }else{
                    resultado = 0 / rightV;
                }
                return new Value("entero", Integer.toString(resultado), line, column);
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                double resultado;
                if(leftV){
                    resultado = 1 / rightV;
                }else{
                    resultado = 0 / rightV;
                }
                return new Value("doble",Double.toString(resultado), line, column);
            }else if(typeR.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int leftParameter = 0;
                int rightParameter=0;
                if(leftV){
                    leftParameter++;
                } 
                if(rightV){
                    rightParameter++;
                }
                if(rightParameter == 0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }else{
                    int resultado = leftParameter / rightParameter;
                    return new Value("entero", Integer.toString(resultado), line, column);   
                }                
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                int leftParameter = 0;
                if(leftV){
                    leftParameter++;
                }
                if(rightV == 0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                int resultado = leftParameter/ rightV;
                return new Value("caracter",(char)(resultado)+"", line, column);
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                    newError.setDescription("No se puede dividir un booleano con una cadena");
                    newError.setSolution("Cambiar a suma");
                    semanticErrors.add(newError);
                    return null;
            }
        }else if(typeL.equalsIgnoreCase("caracter")){
            char leftV = left.getValue().charAt(0);
            if(typeR.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                int resultado = leftV / rightV;
                return new Value("entero", Integer.toString(resultado), line, column);
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                double resultado = leftV / rightV;
                return new Value("doble",Double.toString(resultado), line, column);
            }else if(typeR.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rightParameter=0;
                if(rightV){
                    rightParameter++;
                }
                if(rightParameter==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                int resultado = leftV / rightParameter;
                return new Value("caracter",(char)(resultado)+"", line, column);
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                if(rightV==0){
                    SemanticError newError = new SemanticError("Division invalida",line, column);
                    newError.setDescription("No se puede dividir entre cero");
                    newError.setSolution("Cambiar el cero");
                    semanticErrors.add(newError);
                    return null;
                }
                char resultado = (char) (leftV / rightV);
                return new Value("caracter", resultado+"", line, column);
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                    newError.setDescription("No se puede dividir un caracter con una cadena");
                    newError.setSolution("Cambiar a suma");
                    semanticErrors.add(newError);
                    return null;
            }
        }else if(typeL.equalsIgnoreCase("cadena")){
            SemanticError newError = new SemanticError("Tipos incompatibles",line, column);                    
            newError.setDescription("No se puede dividir con un valor cadena");            
            newError.setSolution("Cambiar a suma");            
            semanticErrors.add(newError);            
            return null;
        }
        
        return null;
    }
}
