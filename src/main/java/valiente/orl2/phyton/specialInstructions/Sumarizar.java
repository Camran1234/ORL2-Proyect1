/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import java.util.ArrayList;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.instructions.Assignment;
import valiente.orl2.phyton.instructions.Dimension;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.table.Symbol;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.instructions.Direction;
import valiente.orl2.phyton.table.Data;
import valiente.orl2.phyton.table.Parameter;
import valiente.orl2.phyton.table.Type;
import valiente.orl2.phyton.values.Value;

/**
 *
 * @author camran1234
 */
public class Sumarizar extends Instruction{
    Operation arreglo;/*variable*/
    String cadena="";
    
    public Sumarizar(int line, int column) {
        super(line, column);
    }
    
    public void execute(){
        Value value = arreglo.execute();
        if(value.getArray()!=null){

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
        }else{
            Symbol symbol = TableOfValue.getSymbol(value.getRawValue(), "variable");
            Data data = symbol.getData();
            String[] array = data.getArray();
            String type = symbol.getReference().getBase();
            int isEntero=0;
            double isDouble =0;
            for(int index=0; index<array.length; index++){
                if(array[index]==null){
                    continue;
                }else{
                    if(type.equalsIgnoreCase("entero")){
                        int doble;
                        if(array[index].equalsIgnoreCase("true") || array[index].equalsIgnoreCase("verdader")){
                            doble = 1;
                        }else if( array[index].equalsIgnoreCase("false") || array[index].equalsIgnoreCase("falso")){
                            doble=0;
                        }else{
                            doble = Integer.parseInt(array[index]);
                        }
                        
                        isEntero += doble;
                    }else if(type.equalsIgnoreCase("doble")){
                        double doble;
                        if(array[index].equalsIgnoreCase("true") || array[index].equalsIgnoreCase("verdader")){
                            doble = 1;
                        }else if( array[index].equalsIgnoreCase("false") || array[index].equalsIgnoreCase("falso")){
                            doble=0;
                        }else{
                            doble = Double.parseDouble(array[index]);
                        }
                        
                        isDouble += doble;
                    }else{
                        cadena+=array[index];
                    }
                }
            }
            if(type.equalsIgnoreCase("entero")){
                cadena = Integer.toString(isEntero);
            }else if(type.equalsIgnoreCase("doble")){
                cadena = Double.toString(isDouble);
            }
        }
        
        
    }
    
    @Override
    public Value getValueSpecialFunction(){
        try {
            this.execute();
            String cadena = this.cadena;
            this.cadena ="";
            return new Value("cadena",cadena, getLine(), getColumn());
        } catch (Exception e) {
            SemanticError error = new SemanticError("No se pudo sumarizar", getLine(), getColumn());
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
        return null;
    }
    
    public void setArreglo(Operation arreglo){
        this.arreglo = arreglo;
    }
    
    public Operation getArreglo(){
        return this.arreglo;
    }
    
    
}
