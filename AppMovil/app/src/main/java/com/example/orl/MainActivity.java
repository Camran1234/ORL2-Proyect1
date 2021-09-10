package com.example.orl;

import android.os.Bundle;

import com.example.orl.parser.Solicitudes;
import com.example.orl.pistas.Central;
import com.example.orl.pistas.ListaReproduccion;
import com.example.orl.pistas.PlayList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.orl.ui.main.SectionsPagerAdapter;
import com.example.orl.databinding.ActivityMainBinding;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    boolean statusServer=false;
    private ServerSocket server = null;
    private Socket socket = null;
    private DataInputStream in	 = null;
    public static AppCompatActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        statusServer=true;
        try{
            context=this;
            System.out.println("0");
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            System.out.println(sectionsPagerAdapter);
            ViewPager viewPager = binding.viewPager;
            System.out.println(viewPager);
            viewPager.setAdapter(sectionsPagerAdapter);
            System.out.println(sectionsPagerAdapter);
            TabLayout tabs = binding.tabs;
            System.out.println(tabs);
            tabs.setupWithViewPager(viewPager);
            System.out.println("fin");
        }catch(Exception ex){
            ex.printStackTrace();
        }

        Handler uiThreadHandler = new Handler() {
            public void handleMessage(Message msg){
                System.out.println("texto recibido");
                EditText text = (EditText) findViewById(R.id.respuesta);
                text.setText(msg.obj.toString());
            }
        };

        Handler uiThreadLista = new Handler(){
            public void handleMessage(Message msg){
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                PlayList playList = Central.getPlayList();
                ArrayList<ListaReproduccion> listaReproducciones = playList.getlistas();
                List<String> categories = new ArrayList<String>();
                for(int index=0; index<listaReproducciones.size(); index++){
                    categories.add(listaReproducciones.get(index).getNombre());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(MainActivity.context, android.R.layout.simple_spinner_item, categories);
                spinner.setAdapter(dataAdapter);


            }
        };
        Thread thread = new Thread(new Runnable() {
            private final int port=4555;
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
                            Message msg = uiThreadHandler.obtainMessage();
                            msg.obj = line;
                            uiThreadHandler.sendMessage(msg);
                            Thread.sleep(300);
                            Solicitudes solicitudes = new Solicitudes();
                            solicitudes.parse(line);
                            Message msgList = uiThreadLista.obtainMessage();
                            msg.obj = "";
                            uiThreadLista.sendMessage(msgList);
                        }
                        catch(IOException i)

                        {
                            System.out.println(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
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
        thread.start();

    }


}