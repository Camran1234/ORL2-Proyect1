package com.example.orl.socket;
// A Java program for a Client
import android.os.StrictMode;

import java.net.*;
import java.io.*;
  
public class Client
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private String address = "";
    private String text = "";
    private int port=0;
    // constructor to put ip address and port
    public Client(String address, int port, String text)
    {
        this.address = address;
        this.port=port;
        this.text=text;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean flag=false;
                // establish a connection
                try
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    System.out.println("Conectando");
                    System.out.println("YES");
                    socket = new Socket(address, port);
                    System.out.println("Connected");

                    // takes input from terminal
                    //input  = new DataInputStream(System.in);

                    // sends output to the socket
                    out    = new DataOutputStream(socket.getOutputStream());
                    flag=true;
                }
                catch(UnknownHostException u)
                {

                    System.out.println(u);
                    System.out.println("No se encontro el host UNKNOWHOSTEXCEPTION");
                    u.printStackTrace();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                    System.out.println("No se encontro el host IOEXCEPTION");
                    i.printStackTrace();
                }

                // string to read message from input
                String line = "";

                // keep reading until "Over" is input
                    try
                    {
                        if(text!=null){
                            System.out.println(text);
                            line = text;
                            if(flag){
                                out.writeUTF(line);
                            }else{
                                System.out.println("Error no se pudo enviar el mensaje");
                            }

                        }
                    }
                    catch(IOException i)
                    {
                        System.out.println(i);
                        i.printStackTrace();
                    }


                // close the connection
                try
                {
                    if(flag){
                        out.close();
                        socket.close();
                    }
                }
                catch(IOException i)
                {
                    System.out.println(i);
                    i.printStackTrace();
                }
            }
        });
        thread.run();
    }


   /*public static void main(String args[])
    {
        //Client client = new Client("127.0.0.1", 5000);
    }*/
}