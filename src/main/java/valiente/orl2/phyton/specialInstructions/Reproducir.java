/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Pista;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.TypeParser;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.reproduccion.Reproduccion;
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

    public void execute(){
        Value newNota = nota.execute();            
        Value newOctava = octava.execute();        
        Value newTiempo = milisegundos.execute();        
        Value newCanal = canal.execute();
        String valorNota = "";
        String valorOctava = "";
        String valorTiempo = "";
        String valorCanal= "";
        try {
            if(!newNota.getRawType().equalsIgnoreCase("nota")){
                throw new ValueException("Se esperaba que se asignara una nota a reproducir", "Tipos incompatibles", newNota.getLine(), newNota.getColumn());
            }
            valorNota = newNota.getRawValue();
        } catch (ValueException e) {
        }
        try {
            if(!newOctava.getType().equalsIgnoreCase("entero")){
                throw new ValueException("Se esperaba que se asignara un entero a reproducir", "Tipos incompatibles", newOctava.getLine(), newOctava.getColumn());
            }
            int valor = Integer.parseInt(newOctava.getValue());
            if(valor<=0 && valor>=8){
               throw new ValueException("Se esperaba que se asignara la octava en un rango de 0 a 8", "Rango superior", newOctava.getLine(), newOctava.getColumn());
            }
            valorOctava = newOctava.getValue();
        } catch (ValueException e) {
        }
        try {
            if(!newTiempo.getType().equalsIgnoreCase("entero")){
                throw new ValueException("Se esperaba que se asignara un entero a reproducir", "Tipos incompatibles", newTiempo.getLine(), newTiempo.getColumn());
            }
            valorTiempo = newTiempo.getValue();
            if(Integer.parseInt(valorTiempo)<0){
               throw new ValueException("Se esperaba un entero positivo en tiempo", "Entero positivo esperado", newOctava.getLine(), newOctava.getColumn()); 
            }
        } catch (ValueException e) {
        }
        try {
            if(!newCanal.getType().equalsIgnoreCase("entero")){
                throw new ValueException("Se esperaba que se asignara un entero a reproducir", "Tipos incompatibles", newCanal.getLine(), newCanal.getColumn());
            }
            valorCanal = newCanal.getValue();
            if(Integer.parseInt(valorCanal)<0){
               throw new ValueException("Se esperaba un entero positivo en el canal", "Entero positivo esperado", newOctava.getLine(), newOctava.getColumn()); 
            }
        } catch (ValueException e) {
        }
        
        try {
            Pista newPista = (Pista)TableOfValue.getWorkingSymbol().getReference().getValue();
            //Asignamos los valores de reproduccion
            Reproduccion reproduccion = new Reproduccion(valorNota, Integer.parseInt(valorOctava), Integer.parseInt(valorTiempo), Integer.parseInt(valorCanal), 
            getLine(), getColumn());
            newPista.setSonido(reproduccion);
        } catch (ValueException e) {
        }
        
        
        
    }
    
    @Override
    public Value getValueSpecialFunction(){
        try {
            Value valor = milisegundos.execute();
            valor = new TypeParser().tryParse(valor, "entero", getLine(), getColumn());
            this.execute();
            return valor;
        } catch (Exception e) {
            SemanticError error = new SemanticError("Tipos incompatibles", getLine(), getColumn());
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
        return null;
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
