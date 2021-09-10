/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.webconnection;

import valiente.orl2.socket.Server;

/**
 *
 * @author camran1234
 */
public class WebConnection {
    private static Server server;
    
    public static void startServer(int port){
        System.out.println("Creando servidor");
        server = new Server(port);
        System.out.println("Servidor creado");
        server.start();
        
    }
    
    public static void closeServer(){
        if(server!=null){
            System.out.println("Cerrando servidor");
            server.close();
        }
    }
    
    public static void sendPetition(String text){
        //call Cliente method
    }
    
    
}
