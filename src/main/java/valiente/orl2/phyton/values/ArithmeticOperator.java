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
public class ArithmeticOperator {
    ArrayList<SemanticError> semanticError = TableOfValue.semanticErrors;
    
    public Value MakeOperation(String operation, Value left, Value right){
        Value result = null;
        if(operation.equalsIgnoreCase("+")){
            result = MakeAddition(left, right);
        }else if(operation.equalsIgnoreCase("-")){
            result = MakeSubstraction(left, right);
        }else if(operation.equalsIgnoreCase("*")){
            result = MakeMultiply(left, right);
        }else if(operation.equalsIgnoreCase("/")){
            result = MakeDiv(left, right);
        }else if(operation.equalsIgnoreCase("^")){
            result = MakePow(left, right);
        }else if(operation.equalsIgnoreCase("%")){
            result = MakeMod(left, right);
        }
        return result;
    }
    
    private Value MakeAddition(Value left, Value right){
        Value result = null;
        
        
        return result;
    }

    private Value MakeSubstraction(Value left, Value right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Value MakeMultiply(Value left, Value right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Value MakeDiv(Value left, Value right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Value MakePow(Value left, Value right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Value MakeMod(Value left, Value right) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
