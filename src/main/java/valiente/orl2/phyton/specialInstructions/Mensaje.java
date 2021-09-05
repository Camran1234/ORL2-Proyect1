/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.phyton.specialInstructions;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import valiente.orl2.phyton.error.SemanticError;
import valiente.orl2.phyton.error.SemanticException;
import valiente.orl2.phyton.error.ValueException;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.phyton.values.Operation;
import valiente.orl2.phyton.values.TypeParser;
import valiente.orl2.phyton.values.Value;
import valiente.orl2.proyecto1.PhytonFrame;
/**
 *
 * @author camran1234
 */
public class Mensaje extends Instruction{
    Operation mensaje;/*String*/
    
    public Mensaje(int line, int column) {
        super(line, column);
    }

    @Override
    public void execute() throws SemanticException{
        try {
            if(mensaje==null){
                throw new ValueException("No se a asignado un mensaje", "Funcion invalida", getLine(), getColumn());
            }else{
                JTextArea textArea = PhytonFrame.areaTexto;
                Value value = mensaje.execute();
                try {
                        String texto = value.getValue();
                        
                        texto = comprobacionSemantica(texto);
                        textArea.append(texto);
                } catch (Exception ex) {
                    SemanticError error = new SemanticError("Tipos incompatibles", getLine(), getColumn());
                    error.setDescription(ex.getMessage());
                    TableOfValue.semanticErrors.add(error);
                    throw new SemanticException("error");
                }
                
            }
        } catch (ValueException e) {
        }        
    }
    
    @Override
    public Value getValueSpecialFunction(){
        SemanticError error = new SemanticError("Tipos incompatibles", getLine(), getColumn());
        error.setDescription("Mensaje es de tipo void");
        TableOfValue.semanticErrors.add(error);
        return null;
    }
    /**
     * Si posee signos especiales como #t o #n los modificamos
     * @return 
     */
    public String comprobacionSemantica(String texto){
        StringBuilder string = new StringBuilder();
        
        String cadena="";
        for(int index=0; index<texto.length(); index++){
            if(cadena.equals("#")){
                if(texto.charAt(index) == '#' || texto.charAt(index) == 'n' || texto.charAt(index) == 't'){
                    cadena += texto.charAt(index);
                    System.out.println(cadena);
                    cadena = tranformarCaracteres(cadena);
                    string.append(cadena);
                }else{
                    cadena ="";
                    string.append(texto.charAt(index));
                }
            }else if(texto.charAt(index) == '#'){
                cadena += texto.charAt(index);
            }else if(texto.charAt(index) == '\t'){
                string.append("\\t");
            }else if(texto.charAt(index) == '\n'){
                string.append("\\n");
            }else{
                string.append(texto.charAt(index));
            }
        }
        string.append(cadena);
        return string.toString();
    }
    
    private String tranformarCaracteres(String texto){
        String aux=texto;
        if(aux.equals("##")){
            aux = "#";
        }else if(aux.equals("\n")){
            aux = "\\n";
        }else if(aux.equals("\t")){
            aux = "\\t";
        }else if(aux.equals("#n")){
            aux = "\n";
        }else if(aux.equals("#t")){
            aux = "\t";
        }else if(aux.equals("#")){
            aux = "";
        }
        return aux;
    }
    
    public Operation getMensaje() {
        return mensaje;
    }

    public void setMensaje(Operation mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
