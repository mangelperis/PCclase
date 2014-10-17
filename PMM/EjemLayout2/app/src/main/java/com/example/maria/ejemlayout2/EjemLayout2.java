package com.example.maria.ejemlayout2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class EjemLayout2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejem_layout2);
        final Button botonLinear = (Button) findViewById(R.id.cmdLinear);
        final Button botonTabla = (Button) findViewById(R.id.cmdTabla);
        final Button botonRelativo = (Button) findViewById(R.id.cmdRelativo);
        final Button botonGrid = (Button) findViewById(R.id.cmdGrid);

        botonLinear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(EjemLayout2.this, Linear.class);
                startActivity(miIntent);
            }
        });
        botonTabla.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(EjemLayout2.this, tabla.class);
                startActivity(miIntent);
            }
        });
        botonRelativo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent miIntent = new Intent(EjemLayout2.this, Relativo.class);
                startActivity(miIntent);
            }
        });
    }

    }
