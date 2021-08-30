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
public class Addition {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    
    public Value MakeAddition(Value left, Value right, int line, int column){
        String typeL = left.getType();
        String typeR = right.getType();
        
        if(typeL.equalsIgnoreCase("entero")){
            if(typeR.equalsIgnoreCase("entero")){
                int leftV = Integer.parseInt(left.getValue());
                int rightV = Integer.parseInt(right.getValue());
                return new Value("entero",Integer.toString(leftV+rightV) );
            }else if(typeR.equalsIgnoreCase("doble")){
                int leftV = Integer.parseInt(left.getValue());
                double rightV = Double.parseDouble(right.getValue());
                double result = rightV + leftV;
                return new Value("doble",Double.toString(result));
            }else if(typeR.equalsIgnoreCase("boolean")){
                int leftV = Integer.parseInt(left.getValue());
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int result=0;
                if(rightV){
                    result = leftV + 1;
                }else{
                    result = leftV;
                }
                return new Value("entero",Integer.toString(result));
            }else if(typeR.equalsIgnoreCase("caracter")){
                int leftV = Integer.parseInt(left.getValue());
                char rightV = left.getValue().charAt(0);
                int result = leftV + rightV;
                return new Value("entero",Integer.toString(result));
            }else if(typeR.equalsIgnoreCase("cadena")){
                int leftV = Integer.parseInt(left.getValue());
                char rightV = right.getValue().charAt(0);
                int result = leftV + rightV;
                return new Value("entero",Integer.toString(result));
            }
        }else if(typeL.equalsIgnoreCase("doble")){
            double leftV = Double.parseDouble(left.getValue());
            if(typeR.equalsIgnoreCase("entero")){
                
                int rightV = Integer.parseInt(right.getValue());
                double result = leftV + rightV;
                return new Value("doble", Double.toString(result));
            }else if(typeR.equalsIgnoreCase("doble")){
                
                double rightV = Double.parseDouble(right.getValue());
                double result = leftV + rightV;
                return new Value("doble",Double.toString(result));
            }else if(typeR.equalsIgnoreCase("boolean")){
                
                boolean rightV = Boolean.parseBoolean(right.getValue());
                if(rightV){
                    leftV++;
                }
                return new Value("doble", Double.toString(leftV));
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                double result = leftV + rightV;
                return new Value("doble", Double.toString(result));
            }else if(typeR.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                String result = leftV + rightV;
                return new Value("cadena", result);
            }
        }else if(typeL.equalsIgnoreCase("boolean")){
            boolean leftV = Boolean.parseBoolean(left.getValue());
            if(typeR.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());
                if(leftV){
                    rightV++;
                }
                return new Value("entero", Integer.toString(rightV));
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                if(leftV){
                    rightV++;
                }
                return new Value("doble",Double.toString(rightV));
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
                return new Value("entero", Integer.toString(leftParameter+rightParameter));   
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                int leftParameter = 0;
                if(leftV){
                    leftParameter++;
                }
                int resultado = leftParameter+ rightV;
                char result = (char) resultado;
                return new Value("caracter",result+"");
            }else if(typeR.equalsIgnoreCase("cadena")){
                String rightV = right.getRawValue();
                int leftParameter=0;
                if(leftV){
                    leftParameter++;
                }
                String result = leftParameter + rightV;
                return new Value("cadena", result);
            }
        }else if(typeL.equalsIgnoreCase("caracter")){
            char leftV = left.getValue().charAt(0);
            if(typeR.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());
                int resultado = leftV + rightV;
                return new Value("entero", Integer.toString(resultado));
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                double resultado = leftV + rightV;
                return new Value("doble",Double.toString(resultado));
            }else if(typeR.equalsIgnoreCase("boolean")){
                boolean rightV = Boolean.parseBoolean(right.getValue());
                int rightParameter=0;
                if(rightV){
                    rightParameter++;
                }
                int resultado = leftV + rightParameter;
                return new Value("caracter",(char)(resultado)+"");
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                char resultado = (char) (leftV + rightV);
                return new Value("caracter", resultado+"");
            }else if(typeR.equalsIgnoreCase("cadena")){
                String rightV = right.getValue();
                String resultado = leftV + rightV;
                return new Value("cadena", resultado);
            }
        }else if(typeL.equalsIgnoreCase("cadena")){
            String leftV = left.getValue();
            if(typeR.equalsIgnoreCase("entero")){
                int rightV = Integer.parseInt(right.getValue());
                return new Value("cadena", leftV + rightV);
            }else if(typeR.equalsIgnoreCase("doble")){
                double rightV = Double.parseDouble(right.getValue());
                return new Value("cadena", leftV + rightV);
            }else if(typeR.equalsIgnoreCase("boolean")){
                return new Value("cadena", leftV + right.getRawValue());
            }else if(typeR.equalsIgnoreCase("caracter")){
                char rightV = right.getValue().charAt(0);
                return new Value("cadena" , leftV+ rightV);
            }else if(typeR.equalsIgnoreCase("cadena")){
                return new Value("cadena", leftV + right.getValue());
            }
        }
        
        return null;
    }
    
}
