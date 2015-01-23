package com.example.mati.comunicacion;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends Activity {

    private TextView salida;

    @Override
    public void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);
        salida = (TextView) findViewById(R.id.Textview01);
        peticionCliente();
    }


    private void peticionCliente(){
        //Socket = IP + PUERTO al que queremos conectarnos
        //En este caso utilizaremos un servidor que esta en el mismo ordenador
        String ip = "192.168.24.207";
        int puerto = 8080;
        try {
            Socket socket= new Socket(ip,puerto);
            //Para leer del socket
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader((socket.getInputStream()))
            );
            //Para escribir en el socket
            PrintWriter salida = new PrintWriter(

                    new OutputStreamWriter(socket.getOutputStream())


            );

            //Enviamos algo al socket, o sea, al servidor
            log("Enviamos una cadena al servidor de echo..."+entrada.readLine());
            socket.close();
        }catch (Exception e){
            log("Error: " +e.toString());
        }

    }

    private void log(String cadena){
        salida.append(cadena + "\n");
    }


}
