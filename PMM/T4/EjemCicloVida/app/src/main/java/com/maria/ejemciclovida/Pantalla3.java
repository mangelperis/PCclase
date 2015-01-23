package com.maria.ejemciclovida;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by Maria on 15/12/2014.
 */
public class Pantalla3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);

        final TextView saludoPantalla3 = (TextView) findViewById(R.id.miMensaje3);

        Bundle miBundleRecoger = getIntent().getExtras();
        saludoPantalla3.setText(miBundleRecoger.getString("TEXTO3"));

    }
}
