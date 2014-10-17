package com.example.mati.pruebamundo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        final Button boton1 = (Button) findViewById(R.id.button1);
        final Button boton2 = (Button) findViewById(R.id.button2);

        boton1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                    Intent miIntent = new Intent(MyActivity.this, Linear.class);
                     startActivity(miIntent);
              }



        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(MyActivity.this, Relative.class);
                startActivity(miIntent);
            }
        });



    }



}
