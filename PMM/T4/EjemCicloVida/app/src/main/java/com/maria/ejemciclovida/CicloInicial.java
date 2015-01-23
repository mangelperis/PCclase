package com.maria.ejemciclovida;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class CicloInicial extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_inicial);

        Button botonUno=(Button)this.findViewById(R.id.btnUno);
        botonUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent= new Intent(CicloInicial.this, Pantalla2.class);
                Bundle miBundle=new Bundle();
                miBundle.putString("TEXTO2", "Paso a la pantalla2");
                miIntent.putExtras(miBundle);
                startActivity(miIntent);


            }
        });
        Button botonDos=(Button)this.findViewById(R.id.btnDos);
        botonDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent= new Intent(CicloInicial.this, Pantalla3.class);
                Bundle miBundle=new Bundle();
                miBundle.putString("TEXTO3", "Paso a la pantalla3");
                miIntent.putExtras(miBundle);
                startActivity(miIntent);

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        Toast.makeText(this,"onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"onStop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        Toast.makeText(this,"onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }



}
