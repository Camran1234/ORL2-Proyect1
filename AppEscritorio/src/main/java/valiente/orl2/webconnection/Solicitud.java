/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.webconnection;
import java.util.ArrayList;
import valiente.orl2.phyton.error.SyntaxError;
/**
 *
 * @author camran1234
 */
public class Solicitud {
    //Para solicitudes
    private boolean solicitud;
    private String tipo="";
    private boolean insertedType=false;
    private String nombre="";
    private boolean insertedNombre=false;
    //private boolean lectura
    //Variables para lectura de pistas y listas
    private ArrayList<SyntaxError> lista = new ArrayList();

    public Solicitud(ArrayList<SyntaxError> lista){
        this.lista = lista;
    }
    public Solicitud(){}
    
    public boolean isSolicitud() {
        return solicitud;
    }

    public void setSolicitud(boolean solicitud) {
        this.solicitud = solicitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo, int line, int column) {
        if(!insertedType){
            this.tipo = tipo;
            insertedType=true;
        }else{
            SyntaxError error = new SyntaxError(line, column);
            error.setType("Valores ya definidos");
            error.setDescription("Se establecio el tipo mas de una vez de la solicitud");
        }
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre, int line, int column) {
        if(!insertedNombre){
            this.nombre = nombre;
            insertedNombre=true;
        }else{
            SyntaxError error = new SyntaxError(line, column);
            error.setType("Valores ya definidos");
            error.setDescription("Se establecio el nombre mas de una vez");
        }
        
        
    }
    
    
    
    
    
}
