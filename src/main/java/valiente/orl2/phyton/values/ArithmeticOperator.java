/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.values;
import valiente.orl2.phyton.operators.Addition;
import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.operators.Division;
import valiente.orl2.phyton.operators.Multiplication;
import valiente.orl2.phyton.operators.Substraction;
import valiente.orl2.phyton.operators.Pow;
import valiente.orl2.phyton.operators.Mod;
import valiente.orl2.phyton.table.TableOfValue;
/**
 *
 * @author camran1234
 */
public class ArithmeticOperator {
    ArrayList<SemanticError> semanticErrors = TableOfValue.semanticErrors;
    
    public Value MakeOperation(String operation, Value left, Value right, int line, int column){
        Value result = null;
        if(operation.equalsIgnoreCase("+")){
            result = MakeAddition(left, right, line, column);
        }else if(operation.equalsIgnoreCase("-")){
            result = MakeSubstraction(left, right, line, column);
        }else if(operation.equalsIgnoreCase("*")){
            result = MakeMultiply(left, right, line, column);
        }else if(operation.equalsIgnoreCase("/")){
            result = MakeDiv(left, right, line, column);
        }else if(operation.equalsIgnoreCase("^")){
            result = MakePow(left, right, line, column);
        }else if(operation.equalsIgnoreCase("%")){
            result = MakeMod(left, right, line, column);
        }
        return result;
    }
    
    private Value MakeAddition(Value left, Value right, int line, int column){
        return new Addition().MakeAddition(left, right, line, column);
    }

    private Value MakeSubstraction(Value left, Value right, int line, int column) {
        return new Substraction().MakeSubstraction(left, right, line, column);
    }

    private Value MakeMultiply(Value left, Value right, int line, int column) {
        return new Multiplication().MakeMultiplication(left, right, line, column);
    }

    private Value MakeDiv(Value left, Value right, int line, int column) {
        return new Division().MakeDivision(left, right, line, column);
    }

    private Value MakePow(Value left, Value right, int line, int column) {
        return new Pow().MakePow(left, right, line, column);
    }

    private Value MakeMod(Value left, Value right, int line, int column) {
        return new Mod().MakeMod(left, right, line, column);
    }
}
