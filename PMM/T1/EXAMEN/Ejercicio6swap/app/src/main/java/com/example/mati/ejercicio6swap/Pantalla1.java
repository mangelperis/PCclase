package com.example.mati.ejercicio6swap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Pantalla1 extends Activity {

    public static int COD_RESPUESTA=0;
    TextView txtview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);


        final Button buttonIr2 = (Button)findViewById(R.id.buttonIr2);
        final Button buttonIr3 = (Button)findViewById(R.id.buttonIr3);
        txtview1 = (TextView)findViewById(R.id.textView);

        //Si regreso de otra actividad
        if (savedInstanceState!=null){
            String mensajePasado=savedInstanceState.getString("TEXTO");
            txtview1.setText("Pantalla 1, vuelta de la pantalla"+mensajePasado);

        }

        buttonIr2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent = new Intent(Pantalla1.this, Pantalla2.class);
                Bundle miBundle = new Bundle();
                String mensajePaso ="Pantalla 2 , vengo de Pantalla 1";
                miBundle.putString("TEXT",mensajePaso);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
            }
        });

        buttonIr3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent = new Intent(Pantalla1.this, Pantalla3.class);
                   //PARA PASASR DE 1 a 2ó3 SI  QUE UTILIZO LOS BUNDLE para los txtview
                Bundle miBundle = new Bundle();
                String mensajePaso ="Pantalla 3 , vengo de Pantalla 1";
                miBundle.putString("TEXT",mensajePaso);
                miIntent.putExtras(miBundle);
                startActivityForResult(miIntent, COD_RESPUESTA);
            }
        });

    }
    public void onActivityResult(int cod_resp, int cod_result,Intent intent){
        if (cod_result== 2) {

            txtview1.setText("Pantalla 1, vengo del 2");
        }
        if (cod_result== 3 ) {

            txtview1.setText("Pantalla 2, vengo del 3");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pantalla1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
