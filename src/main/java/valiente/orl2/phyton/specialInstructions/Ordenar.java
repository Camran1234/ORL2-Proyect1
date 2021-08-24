/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.values.Operation;

/**
 *
 * @author camran1234
 */
public class Ordenar extends Instruction{
    Operation array;/*Variable a ordenar*/
    Operation forma;/*Forma que ordenaremos el arreglo*/
    
    public Ordenar(int line, int column) {
        super(line, column);
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
