/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.webconnection;

import javax.swing.JOptionPane;
import java.io.StringReader;
import valiente.orl2.communication.*;
import java.util.ArrayList;
import valiente.orl2.phyton.error.*;
import valiente.orl2.socket.Cliente;
/**
 *
 * @author camran1234
 */
public class Solicitudes {
    
    
    public void readRequest(String text){
        try {
            StringReader string = new StringReader(text);
            CommunicationLexic lexic = new CommunicationLexic(string);
            CommunicationSyntax syntax = new CommunicationSyntax(lexic);
            syntax.parse();
            ArrayList<LexicalError> lexicalErrors = lexic.getList();
            ArrayList<SyntaxError> syntaxErrors = syntax.getList();
            Solicitud solicitud = syntax.getSolicitud();
            System.out.println("Generando");
            
            if(lexicalErrors.size()==0 && syntaxErrors.size()==0){
                if(solicitud.isSolicitud()){
                    System.out.println("2");
                    makeRequest(solicitud);
                }else{
                    
                }
            }else{
                StringBuilder errores = new StringBuilder();
                for(int index=0; index<lexicalErrors.size(); index++){
                    errores.append("Error lexico: ").append(lexicalErrors.get(index).getDescription()).append("; ").append("en linea: ");
                    errores.append(lexicalErrors.get(index).getLine()).append(", columna: ").append(lexicalErrors.get(index).getColumn()).append("\n");
                }
                for(SyntaxError syntaxError: syntaxErrors){
                    errores.append(syntaxError.getDescriptionWithoutTokens());
                }
                this.sendRequest(errores.toString());
            }
            
            
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    
    private void makeRequest(Solicitud solicitud){
        boolean sentAll = false;
        String paquete="";
        System.out.println("3");
        if(solicitud.getNombre().equalsIgnoreCase("")){
            //Enviamos todas
            System.out.println("VERDADERO");
            sentAll=true;
        }
        Escritor escritor = new Escritor();
        if(solicitud.getTipo().equalsIgnoreCase("Lista")){
            if(sentAll){
                paquete = escritor.escribirListas();
            }else{
                paquete = escritor.escribirDatosLista(solicitud.getNombre());
            }
        }else if(solicitud.getTipo().equalsIgnoreCase("Pista")){
            if(sentAll){
                paquete = escritor.escribirPistas();
            }else{
                paquete = escritor.escribirDatosPista(solicitud.getNombre());
            }
        }
        System.out.println("4");
        sendRequest(paquete);
    }
    
    private void sendRequest(String paquete){
        //El servidor de nuestro telefono se encuentra en esa direccion
        Cliente cliente = new Cliente("192.168.1.40",4555, paquete);
        cliente.send();
        System.out.println("Solicitud Enviada");
        
    }
            
    
}
