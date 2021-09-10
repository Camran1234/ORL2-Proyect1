/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package valiente.orl2.socket;

// A Java program for a Client
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
  
public class Cliente
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private boolean flag = false;
    // constructor to put ip address and port
    private String addres="";
    private int port=0;
    private String paquete="";
    private Thread thread=null;
    
    public Cliente(String address, int port, String paquete)
    {
        this.port = port;
        this.addres = addres;
        this.paquete = paquete;
        thread = new Thread(new Runnable(){
            
            @Override
            public void run(){
                // establish a connection
                try
                {
                    System.out.println("Estableciendo direccion");
                    socket = new Socket(address, port);
                    System.out.println("Connected");  

                    // sends output to the socket
                    out    = new DataOutputStream(socket.getOutputStream());
                    flag=true;
                }
                catch(UnknownHostException u)
                {
                    System.out.println(u);
                    u.printStackTrace();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                    i.printStackTrace();
                }

                
                    if(paquete!=null){
                            if(flag){
                                try {
                                    out.writeUTF(paquete);
                                    System.out.println("Paquete enviado");
                                } catch (IOException ex) {
                                    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                                    System.out.println("No se envio el paquete");
                                }
                            }else{
                                System.out.println("Error no se pudo enviar el mensaje");
                            }

                        }

                // close the connection
                try
                {
                    //input.close();
                    out.close();
                    socket.close();
                }
                catch(IOException i)
                {
                    System.out.println("Incapaz de cerrar cliente "+i.getMessage());
                    i.printStackTrace();
                }
            }
            
        });
    }
    
    /**
     * Solo se necesita correr porque ya trae implementado su metodo para cerrarse
     */
    public void send(){
        if(thread!=null){
            thread.start();
        }
    }
  
    /*public static void main(String args[])
    {
        Cliente client = new Cliente("192.168.1.8", 8080);
    }*/
}