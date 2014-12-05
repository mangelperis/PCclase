package com.example.mati.segundapantalla;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mati on 30/10/14.
 */
public class Pantalla2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView resultado = (TextView)findViewById(R.id.resultado);



        Bundle  miBundleRecoger = getIntent().getExtras();

        resultado.setText(miBundleRecoger.getString(""));




    }
}