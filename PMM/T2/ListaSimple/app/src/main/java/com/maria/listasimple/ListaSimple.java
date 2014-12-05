package com.maria.listasimple;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class ListaSimple extends Activity {


        ListView lview;
        final static String semana[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_simple);

        String mensaje;
        lview = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> miAdaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, semana);
        lview.setAdapter(miAdaptador);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int position, long id) {
                String mensaje="";
                mensaje="Item clicked => " + semana[position];
                showToast(mensaje);

            }
        });
    }
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}


