package com.maria.ejemciclovida;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Maria on 15/12/2014.
 */
public class Pantalla2  extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState){

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pantalla2);

            final TextView saludoPantalla2= (TextView)findViewById(R.id.miMensaje2);

            Bundle miBundleRecoger = getIntent().getExtras();
            saludoPantalla2.setText(miBundleRecoger.getString("TEXTO2"));

        }

    }
