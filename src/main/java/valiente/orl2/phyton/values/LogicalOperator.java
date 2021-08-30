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
public class LogicalOperator {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;

    
    public Operation MakeComparation(Operation leftOp, Operation rightOp, String operation, int line, int column){
        Value left = leftOp.execute();
        Value right = rightOp.execute();
        if(){
            
        }
        
        return null;
    }
    
    public Value Equalization(Value left, Value right, int line, int column){
        
        return null;
    }
    
    public Value Differentitation(Value left, Value right, int line, int column){
        
        return null;
    }
    
    public Value MoreThan(Value left, Value right, int line, int column){
        
        return null;
    }
    
    public Value EqualMoreThan(Value left, Value right, int line, int column){
        
        return null;
    }
    
    public Value LesserThan(Value left, Value right, int line, int column){
    
        return null;
    }
    
    
    
}
