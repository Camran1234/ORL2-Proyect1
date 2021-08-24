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
public class Reproducir extends Instruction{
    Operation nota;/*nota*/
    Operation octava;
    Operation milisegundos;
    Operation canal;
    
    public Reproducir(int line, int column) {
        super(line, column);
    }

    public Operation getNota() {
        return nota;
    }

    public void setNota(Operation nota) {
        this.nota = nota;
    }

    public Operation getOctava() {
        return octava;
    }

    public void setOctava(Operation octava) {
        this.octava = octava;
    }

    public Operation getMilisegundos() {
        return milisegundos;
    }

    public void setMilisegundos(Operation milisegundos) {
        this.milisegundos = milisegundos;
    }

    public Operation getCanal() {
        return canal;
    }

    public void setCanal(Operation canal) {
        this.canal = canal;
    }
    
    
    
}
