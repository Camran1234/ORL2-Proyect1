/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.proyecto1;
import java.util.ArrayList;
import valiente.orl2.phyton.error.*;
import valiente.orl2.phyton.instructions.Instruction;
import valiente.orl2.phyton.instructions.Pista;
import valiente.orl2.phyton.table.TableOfValue;
import valiente.orl2.UI.TableGenerator;
/**
 * Clase encargada de manejar la semantica del programa
 * Se requiere de establecer las listas de errores sintacticos y lexicos
 * Si estan vacios se empieza a analizar semanticamente 
 * @author camran1234
 */
public class PhytonSemantic {
    //Probably the pistas
    ArrayList<Instruction> pistas = new ArrayList();
    
    public PhytonSemantic(){}
    
    public void execute(){
        //declaramos primero
        try {                    
            for(int index=0; index<pistas.size(); index++){
                ((Pista)pistas.get(index)).declarar();
            }
        } catch (Exception e) {
            SemanticError error = new SemanticError("ERROR FATAL", 0,0);
            error.setDescription(e.getMessage());
            TableOfValue.semanticErrors.add(error);
        }
        TableOfValue value = new TableOfValue();
        if(TableOfValue.isRunable()){
            int workingIndex=0;
            try {
                for(int index=0; index<pistas.size(); index++){
                    workingIndex=index;
                    ((Pista)pistas.get(index)).execute();
                }
            } catch (Exception e) {
                SemanticError error = new SemanticError("ALERTA", pistas.get(workingIndex).getLine(),pistas.get(workingIndex).getColumn());
                error.setDescription(e.getMessage());
                TableOfValue.semanticErrors.add(error);
            }
        }
        //Volvemos a comprobar
        if(!TableOfValue.isRunable()){
            TableGenerator generator = new TableGenerator();
            generator.generarTabla();
            
        }else{
            //agregar las pistas o algo asi
        }
        
    }
    
    public void setSemantic(ArrayList<Instruction> instruction) {
        this.pistas = instruction;
    }
    
    
    
}
