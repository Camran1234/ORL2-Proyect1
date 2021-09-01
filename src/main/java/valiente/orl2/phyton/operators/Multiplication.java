/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.operators;

import valiente.orl2.phyton.table.TableOfValue;
import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.values.Value;
/**
 *
 * @author camran1234
 */
public class Multiplication {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    
    public Value MakeMultiplication(Value left, Value right, int line, int column) throws Exception{
        
         String typeL = left.getType();
        String typeR = right.getType();
        
        if(typeL.equalsIgnoreCase("entero")){
            if(typeR.equalsIgnoreCase("entero")){
                int leftV = Integer.parseInt(left.getValue());
                int rightV = Integer.parseInt(right.getValue());
                return new Value("entero",Integer.toString(leftV*rightV), line, column );
            }else if(typeR.equalsIgnoreCase("doble")){
                int leftV = Integer.parseInt(left.getValue());
                double rightV = Double.parseDouble(right.getValue());
                double result = rightV * leftV;
                return new Value("doble",Double.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("boolean")){
                int leftV = Integer.parseInt(left.getValue());
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int result=0;
                if(rightV){
                    result = leftV * 1;
                }else{
                    result = leftV * 0;
                }
                return new Value("entero",Integer.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("caracter")){
                int leftV = Integer.parseInt(left.getValue());
                char rightV = left.getValue().charAt(0);
                int result = leftV * rightV;
                return new Value("entero",Integer.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar un entero con una cadena");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }
        }else if(typeL.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(typeR.equalsIgnoreCase("entero")){
                
                int rightV = Integer.parseInt(right.getValue());
                double result = leftV * rightV;
                return new Value("doble", Double.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("doble")){
                
                double rightV = Double.parseDouble(right.getValue());
                double result = leftV * rightV;
                return new Value("doble",Double.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("boolean")){
                
                boolean rightV = Boolean.parseBoolean(right.getValue());
                if(!rightV){
                    leftV=0;
                }
                return new Value("doble", Double.toString(leftV), line, column);
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                double result = leftV * rightV;
                return new Value("doble", Double.toString(result), line, column);
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar un doble con una cadena");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }
        }else if(typeL.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(typeR.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());
                if(!leftV){
                    rightV=0;
                }
                return new Value("entero", Integer.toString(rightV), line, column);
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(!leftV){
                    rightV=0;
                }
                return new Value("doble",Double.toString(rightV), line, column);
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
                return new Value("entero", Integer.toString(leftParameter*rightParameter), line, column);   
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                int leftParameter = 0;
                if(leftV){
                    leftParameter++;
                }
                int resultado = leftParameter* rightV;
                return new Value("caracter",(char)(resultado)+"", line, column);
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar un doble con una cadena");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }
        }else if(typeL.equalsIgnoreCase("caracter")){
            char leftV = left.getValue().charAt(0);
            if(typeR.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());
                int resultado = leftV * rightV;
                return new Value("entero", Integer.toString(resultado), line, column);
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                double resultado = leftV * rightV;
                return new Value("doble",Double.toString(resultado), line, column);
            }else if(typeR.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rightParameter=0;
                if(rightV){
                    rightParameter++;
                }
                int resultado = leftV * rightParameter;
                return new Value("caracter",(char)(resultado)+"", line, column);
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                char resultado = (char) (leftV * rightV);
                return new Value("caracter", resultado+"", line, column);
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar un caracter con una cadena");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }
        }else if(typeL.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(typeR.equalsIgnoreCase("entero")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar una cadena con un entero");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }else if(typeR.equalsIgnoreCase("doble")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar una cadena con un doble");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }else if(typeR.equalsIgnoreCase("boolean")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar una cadena con un boolean");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }else if(typeR.equalsIgnoreCase("caracter")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar una cadena con un caracter");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }else if(typeR.equalsIgnoreCase("cadena")){
                SemanticError newError = new SemanticError("Tipos incompatibles",line, column);
                newError.setDescription("No se puede multiplicar entre cadenas");
                newError.setSolution("Cambiar a suma");
                semanticErrors.add(newError);
                return null;
            }
        }
        
        return null;
    }
    
}
