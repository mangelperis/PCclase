package com.example.mati.ejercicio6swap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mati on 6/11/14.
 */
public class Pantalla2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        final TextView textView= (TextView)findViewById(R.id.textView2);
        final Button volverBtn= (Button)findViewById(R.id.buttonVolver2);

        Bundle  miBundleRecoger = getIntent().getExtras();
        textView.setText(miBundleRecoger.getString("TEXT"));

        final String completarSaludo=miBundleRecoger.getString("TEXT");
        volverBtn.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                Intent vueltaIntent= new Intent();
                //SIN BUNDLE
                setResult(2, vueltaIntent);
                finish();

            }

        });


    }


}
