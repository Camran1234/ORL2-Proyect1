/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.table;

import java.util.ArrayList;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.values.Operation;

/**
 *
 * @author camran1234
 */
public class TableOfType {
    public static ArrayList<Type> types = new ArrayList();
    
    public static void deleteType(Type type){
        types.remove(type);
    }
    
    public static void addType(Type type){
        try {
            String clase = type.getBase();
            if(clase.equalsIgnoreCase("funcion")){
                String id = type.getId();
                ArrayList<Parameter> parameters = type.getParameters();
                Type referenceType = existsFunction(id, clase, parameters);
                if(referenceType!=null){
                    throw new ValueException("Funcion ya declarada en "+type.getFather().lookForPista(),"identificador existente", type.getFather().getLine(), type.getFather().getColumn());
                }else{
                    types.add(type);
                }
            }else{
                Type referenceType = existsVariable(type);
                if(referenceType!=null){
                    throw new ValueException("Variable ya declarada en "+type.getFather().lookForPista(), "identificador existente", type.getFather().getLine(), type.getFather().getColumn());
                }else{
                    types.add(type);
                }
            }
        } catch (ValueException e) {
        }
        
    }
    
    public static void reset(){
        types = new ArrayList();
    }
    
    /**
     * Chequea si existe la variable
     * retorna nulo si no existe
     * @param type
     * @return 
     */
    public static Type existsVariable(Type type) throws ValueException{
        for(int index=types.size()-1; index>=0; index--){
            String name = types.get(index).getId();
            String base = types.get(index).getBase();
            if(name.equalsIgnoreCase(type.getId()) && base.equalsIgnoreCase(type.getBase())){
                return types.get(index);
            }
        }
        return null;
    }
    
    /**
     * Chequea si existe la funcion
     * retorna nulo si no existe
     * @param id
     * @param classId
     * @param parametersNum
     * @param parameters
     * @return 
     */
    public static Type existsFunction(String id, String classId, ArrayList<Parameter> parameters) throws ValueException{
        for(int index=types.size()-1; index>=0; index--){
            String name = types.get(index).getId();
            String type = types.get(index).getBase();
            int numberParameters = types.get(index).getNumberParameters();
            if(name.equalsIgnoreCase(id) && type.equalsIgnoreCase(classId)){                    
                if(types.get(index).compareParameters(parameters)){
                    return types.get(index);
                }
            }
            
        }
        return null;
    }
    
    
}
