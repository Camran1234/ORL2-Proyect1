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
import valiente.orl2.central.Central;
import valiente.orl2.reproduccion.PistaReproduccion;
/**
 * Clase encargada de manejar la semantica del programa
 * Se requiere de establecer las listas de errores sintacticos y lexicos
 * Si estan vacios se empieza a analizar semanticamente 
 * @author camran1234
 */
public class PhytonSemantic {
    //Probably the pistas
    ArrayList<Instruction> pistas = new ArrayList();
    String text = "";
    public PhytonSemantic(String text){
        this.text = text;
    }
    
    public void checkNames(){
        for(int index=0; index<pistas.size(); index++){
            Pista pista = ((Pista)pistas.get(index));
            String theName = pista.getName();
            int veces=0;
            for(int indexPista=0; indexPista<pistas.size(); indexPista++){
                String name = ((Pista)pistas.get(index)).getName();
                if(theName.equals(name) && indexPista!=index){
                    veces++;
                    if(veces>1){
                        SemanticError error = new SemanticError("Pistas repetidas", pistas.get(index).getLine(), pistas.get(index).getColumn());
                        error.setDescription("Se repitio el nombre de la pista, nombre: "+name);
                        TableOfValue.semanticErrors.add(error);
                    }
                }
            }
        }
    }
    
    public void execute(){
        //declaramos primero
        
        checkNames();
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
            TableGenerator generator = new TableGenerator(TableOfValue.lexicalErrors, TableOfValue.syntaxErrors, TableOfValue.semanticErrors);
            generator.generarTabla();
            
        }else{
            if(PhytonFrame.compile){
                Central central = new Central();
                ArrayList<PistaReproduccion> thePistas= getPistas(pistas);
                central.updatePistas(thePistas);
            }
        }
        
    }
    
    private String removeLastIndex(){
        StringBuilder string = new StringBuilder(text);            
        for(int index=0; index<PhytonFrame.jumps; index++){        
            string.deleteCharAt(string.length()-1);           
        }
        return string.toString();
    }
    
    public ArrayList<PistaReproduccion> getPistas(ArrayList<Instruction> pistas){
        ArrayList<PistaReproduccion> list = new ArrayList();
        for(Instruction pista:pistas){
            String name = ((Pista)pista).getName();
            PistaReproduccion rep = ((Pista)pista).getSonido();
            rep.setName(name);
            rep.setText(removeLastIndex());
            list.add(rep);
        }
        return list;
    }
    
    public void setSemantic(ArrayList<Instruction> instruction) {
        this.pistas = instruction;
    }
    
    
    
}
