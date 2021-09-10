/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.orl.socket;
// A Java program for a Server
import android.app.Activity;
import android.os.Message;
import android.os.StrictMode;
import android.widget.EditText;

import com.example.orl.MainActivity;

import java.net.*;
import java.io.*;

public class Server extends Activity
{
	//initialize socket and input stream
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in	 = null;
        private Thread thread=null;
        private static boolean statusServer=false;
        private EditText editor;

	// constructor with port
	public Server(int port, EditText respuesta)
	{
        this.editor = respuesta;


            Thread thread = new Thread(new Runnable() {
                @Override
                public void run(){
                    // starts server and waits for a connection
                    try
                    {
                            server = new ServerSocket(port);
                            System.out.println("Server started");
                            while(statusServer){
                            System.out.println("Waiting for a client ...");
                                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                StrictMode.setThreadPolicy(policy);
                            socket = server.accept();
                            System.out.println("Client accepted");
                            // takes input from the client socket
                            in = new DataInputStream(
                                    new BufferedInputStream(socket.getInputStream()));
                            String line = "";
                            // reads message from client until "Over" is sent
                            try
                            {
                                line = in.readUTF();
                                System.out.println(line);
                                editor.setText(line);
                            }
                            catch(IOException i)

                            {
                                System.out.println(i);
                            }
                        }

                            System.out.println("Closing connection");

                            // close connection
                            socket.close();
                            in.close();
                    }
                    catch(IOException i)
                    {
                            System.out.println(i);
                    }
                }

            });
            this.thread= thread;
	}
        /**
         * Activa el servidor
         */
        public void start(){
            if(thread!=null){
                System.out.println("Activando servidor");
                statusServer=true;
                thread.start();
                System.out.println("Server inicializado");
            }
        }

        private void runThread(){
            runOnUiThread(thread);
        }


        
        /**
         * Cierra el servidor
         */
        public void close(){
            if(thread!=null){
                statusServer=false;
                thread.interrupt();
            }
        }

	/*public static void main(String args[])
	{
		Server server = new Server(8080);
        }*/
}
