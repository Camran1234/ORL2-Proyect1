/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import java.util.logging.Level;
import java.util.logging.Logger;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Pista;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.reproduccion.Reproduccion;

/**
 *
 * @author camran1234
 */
public class Esperar extends Instruction{
    Operation milisegundos; /*En milisegundos*//*Entero*/
    Operation canal;/*Canal*//*Entero*/
    int line, column =0;
    
    public Esperar(int line, int column) {
        super(line, column);
    }

    @Override
    public void execute() throws SemanticException{
        Value newCanal = canal.execute();
        Value newTiempo = milisegundos.execute();        
        String valorCanal= "";
        String valorTiempo = "";
        
        try {
            if(!newTiempo.getType().equalsIgnoreCase("entero")){
                throw new ValueException("Se esperaba que se asignara un entero a reproducir", "Tipos incompatibles", newTiempo.getLine(), newTiempo.getColumn());
            }
            valorTiempo = newTiempo.getValue();
            if(Integer.parseInt(valorTiempo)<0){
               throw new ValueException("Se esperaba un entero positivo en tiempo", "Entero positivo esperado", newTiempo.getLine(), newTiempo.getColumn()); 
            }
        } catch (ValueException e) {
        }
        
        try {
            if(!newCanal.getType().equalsIgnoreCase("entero")){
                throw new ValueException("Se esperaba que se asignara un entero a reproducir", "Tipos incompatibles", newCanal.getLine(), newCanal.getColumn());
            }
            valorCanal = newCanal.getValue();
            if(Integer.parseInt(valorCanal)<0){
               throw new ValueException("Se esperaba un entero positivo en el canal", "Entero positivo esperado", newCanal.getLine(), newCanal.getColumn()); 
            }
        } catch (ValueException e) {
        }
        TableOfValue table= new TableOfValue();
            Pista newPista = (Pista)TableOfValue.getSelectedSymbol().getReference().getValue();
            //Asignamos los valores de reproduccion
            Reproduccion reproduccion;
        try {
            reproduccion = new Reproduccion("REST", 0, Integer.parseInt(valorTiempo), Integer.parseInt(valorCanal), 
                    getLine(), getColumn());
            newPista.setSonido(reproduccion);
        } catch (ValueException ex) {
            Logger.getLogger(Esperar.class.getName()).log(Level.SEVERE, null, ex);
        }
            
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

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    
    
}
