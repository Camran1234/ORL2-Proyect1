/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Assignment;
import valiente.orl2.phyton.instructions.Dimension;
import valiente.orl2.phyton.instructions.Direction;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.table.Parameter;
import valiente.orl2.phyton.table.Symbol;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.table.Type;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Longitud extends Instruction{
    Operation array;/*variable*/
    Value size;
    
    public Longitud(int line, int column){
        super(line, column);
    }

    @Override
    public void execute() throws SemanticException{
        Value value = array.execute();
        Symbol newSymbol = TableOfValue.getSymbol(value.getRawValue(), "variable");
        boolean isArreglo= newSymbol.isArray();
        
        if(isArreglo){
            String type = value.getRawType();
            if(type.equalsIgnoreCase("variable")){
                String[] string = newSymbol.getData().getFirstRowLength();
                size = new Value("entero",Integer.toString(string.length),getLine(), getColumn());
            }else{
                //Esto es para cuando nos mandan un nested array
                Dimension dimension = value.getArray();
                ArrayList<Direction> directions = dimension.startHarvest();
                ArrayList<Integer> dimensiones = new ArrayList();
                int maxDimension =0;
                for(int index=0; index<maxDimension; index++){
                    dimensiones.add(0);
                }
                //Buscaremos en lo recolectado la posicion mas grande
                for(int index=0; index<directions.size(); index++){
                    Direction direction = directions.get(index);
                    ArrayList<Integer> enteros = direction.getDirection();
                    //Miramos cual es el mas grande de estos
                    for(int indexEntero=0; indexEntero<enteros.size(); indexEntero++){
                        if(enteros.get(indexEntero)>dimensiones.get(indexEntero)){
                            dimensiones.set(indexEntero, enteros.get(indexEntero));
                        }
                    }
                }
                //Le agregamos 1 a todas las direcciones porque obutivmos los valores como para indicarlos no para declararlos
                for(int index=0; index<dimensiones.size(); index++){
                    dimensiones.set(index, dimensiones.get(index)+1);
                }
                String dimensionType = dimension.getType();
                //Luego lo metemos a un simbolo pero sin meterlo a la tabla de valores y luego con sus mismos metodos obtenemos el resultado
                Type referencedType = new Type("",dimensionType,0, new ArrayList<Parameter>(), this.getFather(), dimensiones, this.getIndentation(), this);
                Symbol symbol = new Symbol(referencedType, this.getIndentation(), false, getLine(), getColumn());
                //Le agregamos el arreglo
                Assignment assignment = new Assignment(getLine(), getColumn());
                assignment.setValue(new Operation(new Value(dimension, getLine(), getColumn()), getLine(), getColumn()));
                symbol.setNewValue(assignment, true, getLine(), getColumn());
                String[] lista = symbol.getData().getFirstRowLength();
                size = new Value("entero", Integer.toString(lista.length), getLine(), getColumn());
            }
        }else{
            throw new SemanticException("No es un arreglo","Valor no reconocido", getLine(), getColumn());
        }
    }
    
    @Override
    public Value getValueSpecialFunction() throws ValueException {
        try {
            this.execute();
            return size;
        } catch (SemanticException e) {
            e.checkErrorAmbit();
        }
        
        Value value = size;
        
        if(value.isArray() || (value.getType().equalsIgnoreCase("cadena"))){
            if(value.isArray() && value.getRawType().equalsIgnoreCase("variable")){
                String nombre = value.getRawValue();
                Symbol symbol = TableOfValue.getSymbol(nombre, "variable");
                int data  = symbol.getSizeArray();
                return  new Value("entero", Integer.toString(data), getLine(), getColumn());
            }else{
                String cadena = value.getValue();
                int longitud = cadena.length();
                return new Value("entero",Integer.toString(longitud), getLine(), getColumn());
            }
        }else{
            throw new ValueException("Se esperaba una cadena", "Tipos incompatibles", getLine(), getColumn());
        }
    }
    
    public Operation getArray() {
        return array;
    }

    public void setArray(Operation array) {
        this.array = array;
    }
    
    
    
}
