package com.example.mati.examen2;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by mati on 29/01/15.
 */
public class Resultado extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultado);

        TextView tarifa = (TextView)findViewById(R.id.LblTarifa);

        Bundle b = getIntent().getExtras();

        tarifa.setText(b.getString("Tarifa"));

        double precio = b.getDouble("Precio");

        int ent=(int)precio;
    }

}
