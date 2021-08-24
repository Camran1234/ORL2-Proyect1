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
public class Mensaje extends Instruction{
    Operation mensaje;/*String*/
    
    public Mensaje(int line, int column) {
        super(line, column);
    }

    public Operation getMensaje() {
        return mensaje;
    }

    public void setMensaje(Operation mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
