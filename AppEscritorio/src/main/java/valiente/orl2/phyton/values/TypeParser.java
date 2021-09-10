/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

import valiente.orl2.phyton.table.TableOfValue;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.table.Character;
/**
 *
 * @author camran1234
 */
public class TypeParser {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    public String getHigherType(Value left, Value right, int line, int column){
        try {
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
        } catch (Exception ex) {
            SemanticError error = new SemanticError("Error inesperado en tipos",line, column);
            error.setDescription(ex.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
        return "";
    }
    
    public Value parseType(Value value, String typeToParse, int line, int column) throws Exception{
        Value newValue=null;
        String type = value.getType();
        String data = value.getValue();
        if(type.equalsIgnoreCase("doble")){
            if(typeToParse.equalsIgnoreCase("doble")){
                newValue = new Value(type, data, line, column);
            }else if(typeToParse.equalsIgnoreCase("entero")){
                throw new Exception("No se pudo convertir un doble a entero");
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo convertir un doble a caracter");
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo convertir un doble a boolean");
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo convertir un doble a cadena");
            }
        }else if(type.equalsIgnoreCase("entero")){
            if(typeToParse.equalsIgnoreCase("doble")){
                double x = Double.parseDouble(data);
                newValue = new Value("doble", Double.toString(x), line, column);
            }else if(typeToParse.equalsIgnoreCase("entero")){
                newValue = new Value(type, data, line, column);
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo convertir un entero a caracter");
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo convertir un entero a boolean");
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo convertir un entero a cadena");
            }
        }else if(type.equalsIgnoreCase("caracter")){
            char aux = Character.transform(data);
            int caracter = (int)aux;
            if(typeToParse.equalsIgnoreCase("doble")){
                double x = (double)caracter;
                newValue = new Value("doble",Double.toString(x), line, column);
            }else if(typeToParse.equalsIgnoreCase("entero")){
                newValue = new Value("entero", Integer.toString((char)caracter), line, column);
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                newValue = new Value(type, data, line, column);
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo convertir un caracter a boolean");
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo convertir un caracter a cadena");
            }
        }else if(type.equalsIgnoreCase("boolean")){
            boolean boleano = Boolean.parseBoolean(data);
            if(typeToParse.equalsIgnoreCase("doble")){
                double x=0;
                if(boleano){
                    x=1;
                }
                newValue=new Value("doble", Double.toString(x), line, column);
            }else if(typeToParse.equalsIgnoreCase("entero")){
                int x = 0;
                if(boleano){
                    x=1;
                }
                newValue = new Value("entero",Integer.toString(x), line, column);
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                char x=0;
                if(boleano){
                    x=1;
                }
                newValue = new Value("caracter", (char)(x)+"", line, column);
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                newValue = new Value(type, data, line, column);
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo convertir un boolean a cadena");
            }
        }else if(type.equalsIgnoreCase("cadena")){
            if(typeToParse.equalsIgnoreCase("doble")){
                throw new Exception("No se pudo convertir una cadena a doble");
            }else if(typeToParse.equalsIgnoreCase("entero")){
                throw new Exception("No se pudo convertir una cadena a entero");
            }else if(typeToParse.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo convertir una cadena a caracter");
            }else if(typeToParse.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo convertir una cadena a boolean");
            }else if(typeToParse.equalsIgnoreCase("cadena")){
                newValue = new Value(type, data, line, column);
            }
        }
        
        return newValue;
    }
    
    public void checkAssignation(String type, String typeToParse, int line, int column) throws Exception{
        if(typeToParse.equalsIgnoreCase("doble")){
            if(type.equalsIgnoreCase("doble")){
                
            }else if(type.equalsIgnoreCase("entero")){
                
            }else if(type.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo asignar un caracter a un doble");
            }else if(type.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo asignar un boolean a un doble");
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a una doble");
            }
        }else if(typeToParse.equalsIgnoreCase("entero")){
            if(type.equalsIgnoreCase("doble")){
                
            }else if(type.equalsIgnoreCase("entero")){
                
            }else if(type.equalsIgnoreCase("caracter")){
                
            }else if(type.equalsIgnoreCase("boolean")){
                
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a un doble");
            }
        }else if(typeToParse.equalsIgnoreCase("caracter")){
            if(type.equalsIgnoreCase("doble")){
                throw new Exception("No se pudo asignar un doble a un caracter");
            }else if(type.equalsIgnoreCase("entero")){
                
            }else if(type.equalsIgnoreCase("caracter")){
                
            }else if(type.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo asignar un boolean a un caracter");
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a un caracter");
            }
        }else if(typeToParse.equalsIgnoreCase("boolean")){
            if(type.equalsIgnoreCase("doble")){
                throw new Exception("No se pudo asignar un doble a un boolean");
            }else if(type.equalsIgnoreCase("entero")){
                throw new Exception("No se pudo asignar un entero a un boolean");
            }else if(type.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo asignar un caracter a un boolean");
            }else if(type.equalsIgnoreCase("boolean")){
                
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a un boolean");
            }
        }else if(typeToParse.equalsIgnoreCase("cadena")){
            if(type.equalsIgnoreCase("doble")){
                throw new Exception("No se pudo asignar un doble a una cadena");
            }else if(type.equalsIgnoreCase("entero")){
                throw new Exception("No se pudo asignar un entero a una cadena");
            }else if(type.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo asignar un caracter a una cadena");
            }else if(type.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo asignar un boolean a una cadena");
            }else if(type.equalsIgnoreCase("cadena")){
                
            }
        }
        
    }
    
    public Value tryParse(Value value, String typeToParse, int line, int column) throws Exception{
        Value newValue=null;
        String type = value.getType();
        
        String data = value.getValue();
        if(typeToParse.equalsIgnoreCase("doble")){
            if(type.equalsIgnoreCase("doble")){
                newValue = new Value(type, data, line, column);
            }else if(type.equalsIgnoreCase("entero")){
                newValue = new Value("doble", Integer.toString((int) Double.parseDouble(data)), line, column);
            }else if(type.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo asignar un caracter a un doble");
            }else if(type.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo asignar un boolean a un doble");
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a una doble");
            }
        }else if(typeToParse.equalsIgnoreCase("entero")){
            if(type.equalsIgnoreCase("doble")){
                double x = Double.parseDouble(data);
                newValue = new Value("entero", Integer.toString((int)x), line, column);
            }else if(type.equalsIgnoreCase("entero")){
                newValue = new Value(type, data, line, column);
            }else if(type.equalsIgnoreCase("caracter")){
                char x = Character.transform(data);
                newValue = new Value("entero", Integer.toString((int)x), line, column);
            }else if(type.equalsIgnoreCase("boolean")){
                boolean x = Boolean.parseBoolean(data);
                int y = 0;
                if(x)
                    y=1;
                newValue = new Value("entero",Integer.toString(y), line, column);
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a un doble");
            }
        }else if(typeToParse.equalsIgnoreCase("caracter")){
            if(type.equalsIgnoreCase("doble")){
                throw new Exception("No se pudo asignar un doble a un caracter");
            }else if(type.equalsIgnoreCase("entero")){
                char y = Character.transform(data);
                newValue = new Value("caracter", y+"", line, column);
            }else if(type.equalsIgnoreCase("caracter")){
                newValue = new Value(type, data, line, column);
            }else if(type.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo asignar un boolean a un caracter");
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a un caracter");
            }
        }else if(typeToParse.equalsIgnoreCase("boolean")){
            if(type.equalsIgnoreCase("doble")){
                throw new Exception("No se pudo asignar un doble a un boolean");
            }else if(type.equalsIgnoreCase("entero")){
                throw new Exception("No se pudo asignar un entero a un boolean");
            }else if(type.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo asignar un caracter a un boolean");
            }else if(type.equalsIgnoreCase("boolean")){
                newValue = new Value(type, data, line, column);
            }else if(type.equalsIgnoreCase("cadena")){
                throw new Exception("No se pudo asignar una cadena a un boolean");
            }
        }else if(typeToParse.equalsIgnoreCase("cadena")){
            if(type.equalsIgnoreCase("doble")){
                throw new Exception("No se pudo asignar un doble a una cadena");
            }else if(type.equalsIgnoreCase("entero")){
                throw new Exception("No se pudo asignar un entero a una cadena");
            }else if(type.equalsIgnoreCase("caracter")){
                throw new Exception("No se pudo asignar un caracter a una cadena");
            }else if(type.equalsIgnoreCase("boolean")){
                throw new Exception("No se pudo asignar un boolean a una cadena");
            }else if(type.equalsIgnoreCase("cadena")){
                newValue = new Value(type, data, line, column);
            }
        }
        
        return newValue;
    }
    
}
