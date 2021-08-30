/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

import valiente.orl2.phyton.table.TableOfValue;
import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
/**
 *
 * @author camran1234
 */
public class TypeParser {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    private String getHigherType(Value left, Value right){
        String typeL = left.getType();
        String typeR = right.getType();
        
        if(typeL.equalsIgnoreCase("entero")){
            if(typeR.equalsIgnoreCase("entero")){
                return "entero";
            }else if(typeR.equalsIgnoreCase("doble")){
                return "doble";
            }else if(typeR.equalsIgnoreCase("caracter")){
                return "enteror";
            }else if(typeR.equalsIgnoreCase("boolean")){
                return "entero";
            }else if(typeR.equalsIgnoreCase("cadena")){
                return "";
            }
        }else if(typeL.equalsIgnoreCase("doble")){
            if(typeR.equalsIgnoreCase("entero")){
                return "doble";
            }else if(typeR.equalsIgnoreCase("doble")){
                return "doble";
            }else if(typeR.equalsIgnoreCase("caracter")){
                return "doble";
            }else if(typeR.equalsIgnoreCase("boolean")){
                return "doble";
            }else if(typeR.equalsIgnoreCase("cadena")){
                return "";
            }
        }else if(typeL.equalsIgnoreCase("caracter")){
            if(typeR.equalsIgnoreCase("entero")){
                return "entero";
            }else if(typeR.equalsIgnoreCase("doble")){
                return "doble";
            }else if(typeR.equalsIgnoreCase("caracter")){
                return "caracter";
            }else if(typeR.equalsIgnoreCase("boolean")){
                return "caracter";
            }else if(typeR.equalsIgnoreCase("cadena")){
                return "";
            }
        }else if(typeL.equalsIgnoreCase("boolean")){
            if(typeR.equalsIgnoreCase("entero")){
                return "entero";
            }else if(typeR.equalsIgnoreCase("doble")){
                return "doble";
            }else if(typeR.equalsIgnoreCase("caracter")){
                return "caracter";
            }else if(typeR.equalsIgnoreCase("boolean")){
                return "boolean";
            }else if(typeR.equalsIgnoreCase("cadena")){
                return "";
            }
        }else if(typeL.equalsIgnoreCase("cadena")){
            if(typeR.equalsIgnoreCase("entero")){
                return "";
            }else if(typeR.equalsIgnoreCase("doble")){
                return "";
            }else if(typeR.equalsIgnoreCase("caracter")){
                return "";
            }else if(typeR.equalsIgnoreCase("boolean")){
                return "";
            }else if(typeR.equalsIgnoreCase("cadena")){
                return "cadena";
            }
        }
        return "";
    }
    
    public Value parseType(Value value, String typeToParse, int line, int column) throws Exception{
        Value newValue=null;
        String type = value.getType();
        String data = value.getValue();
        if(type.equalsIgnoreCase("doble")){
            if(typeToParse.equalsIgnoreCase("doble")){
                newValue = value;
            }else if(typeToParse.equalsIgnoreCase("entero")){
                int x = (int)Double.parseDouble(data);
                newValue = new Value("entero", Integer.toString(x));
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                
            }
        }else if(type.equalsIgnoreCase("entero")){
            if(typeToParse.equalsIgnoreCase("doble")){
                
            }else if(typeToParse.equalsIgnoreCase("entero")){
                
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                
            }
        }else if(type.equalsIgnoreCase("caracter")){
            if(typeToParse.equalsIgnoreCase("doble")){
                
            }else if(typeToParse.equalsIgnoreCase("entero")){
                
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                
            }
        }else if(type.equalsIgnoreCase("boolean")){
            if(typeToParse.equalsIgnoreCase("doble")){
                
            }else if(typeToParse.equalsIgnoreCase("entero")){
                
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                
            }
        }else if(type.equalsIgnoreCase("cadena")){
            if(typeToParse.equalsIgnoreCase("doble")){
                
            }else if(typeToParse.equalsIgnoreCase("entero")){
                
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                
            }
        }
        
        return newValue;
    }
    
}
