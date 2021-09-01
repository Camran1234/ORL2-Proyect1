/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.table.TableOfValue;

/**
 *
 * @author camran1234
 */
public class RelationalOperator {
    ArrayList<SemanticError> semanticErros = TableOfValue.semanticErrors;
    
    public Operation MakeCondition(Operation leftOp, Operation rightOp, String operator, int line, int column){
        Operation result = null;
        Value left = leftOp.execute();
        Value right = rightOp.execute();
        boolean leftB;
        boolean rightB;
        try {
            leftB = Boolean.parseBoolean(left.getValue());
            rightB = Boolean.parseBoolean(right.getValue());
            Value newValue =null;
            if(operator.equalsIgnoreCase("&&")){
                //AND
                newValue = new Value("boolean", Boolean.toString(MakeAND(leftB, rightB)), line, column);
            }else if(operator.equalsIgnoreCase("!&&")){
                //NAND
                newValue = new Value("boolean", Boolean.toString(MakeNAND(leftB, rightB)), line, column);
            }else if(operator.equalsIgnoreCase("||")){
                //OR
                newValue = new Value("boolean", Boolean.toString(MakeOR(leftB, rightB)), line, column);
            }else if(operator.equalsIgnoreCase("!||")){
                //NOR
                newValue = new Value("boolean", Boolean.toString(MakeNOR(leftB, rightB)), line, column);
            }else if(operator.equalsIgnoreCase("&!")){
                //XOR
                newValue = new Value("boolean", Boolean.toString(MakeXOR(leftB, rightB)), line, column);
            }
            result = new Operation(newValue, line, column);
        } catch (Exception e) {
            SemanticError error = new SemanticError("Tipo invalido", line, column);
            error.setDescription("No se encontro una variable booleana en la condicion");
            semanticErros.add(error);
        }
        
        return result;
    }
    
    private boolean MakeAND(boolean left, boolean right){
        return (left&&right);
    }
    
    private boolean MakeNAND(boolean left, boolean right){
        return !(left&&right);
    }
    
    private boolean MakeOR(boolean left, boolean right){
        
        return (left || right);
    }
    
    private boolean MakeNOR(boolean left, boolean right){
        
        return !(left || right);
    }
    
    private boolean MakeXOR(boolean left, boolean right){
        if(left && right){
            return false;
        }else if(left && !right){            
            return true;
        }else if(!left && right){
            return true;
        }else if(!left && !right){
            return false;
        }
        return false;
    }
}
