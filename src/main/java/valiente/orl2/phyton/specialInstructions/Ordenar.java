/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.table.Data;
import valiente.orl2.phyton.table.Symbol;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.phyton.error.SemanticError;

/**
 *
 * @author camran1234
 */
public class Ordenar extends Instruction{
    Operation array;/*Variable a ordenar*/
    Operation forma;/*Forma que ordenaremos el arreglo*/
    boolean result=false;
    HelperOrdenar ordenador = new HelperOrdenar();
    public Ordenar(int line, int column) {
        super(line, column);
    }

    public void execute(){
        Value valor = array.execute();
        Value forma = this.forma.execute();
        Symbol symbol = TableOfValue.getSymbol(valor.getRawValue(), "variable");
        Data data = symbol.getData();
        if(data.isArray()){
            if(data.isMultidimensional()){
                result = false;
            }else{
                result = OrderArray(data, forma.getRawValue());
            }
        }else{
            result = false;
        }
    }
    
    public boolean OrderArray(Data data, String forma ){
        boolean result=false;
        try {
            if(forma.equalsIgnoreCase("ascendente")){
                result = ordenador.ASCENDENTE(data);
            }else if(forma.equalsIgnoreCase("descendente")){
                result = ordenador.DESCENDENTE(data);
            }else if(forma.equalsIgnoreCase("pares")){
                result = ordenador.PARES(data);
            }else if(forma.equalsIgnoreCase("impares")){
                result = ordenador.IMPARES(data);
            }else if(forma.equalsIgnoreCase("primos")){
                result = ordenador.PRIMOS(data);
            }
        } catch (Exception e) {
            SemanticError error = new SemanticError("Valor nulo", getLine(), getColumn());
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
            
        return result;
    }
    
    
    @Override
    public Value getValueSpecialFunction(){
        this.execute();
        return new Value("boolean", Boolean.toString(result), getLine(), getColumn());
    }
    
    public Operation getArray() {
        return array;
    }

    public void setArray(Operation array) {
        this.array = array;
    }

    public Operation getForma() {
        return forma;
    }

    public void setForma(Operation forma) {
        this.forma = forma;
    }
    
    
    
}
