package com.example.maria.ejemradiobutton1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;


public class RadioButon1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buton1);


        final TextView lblMensaje = (TextView) findViewById(R.id.LblSeleccion);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.gruporb);
        rg.clearCheck();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String textoOpcion="";
                if( group.getCheckedRadioButtonId()==R.id.radio1)
                  textoOpcion +="OPCION 1 con ID:" + checkedId;
                if( group.getCheckedRadioButtonId()==R.id.radio2)
                    textoOpcion +="OPCION 2 con ID:" + checkedId ;
                lblMensaje.setText(textoOpcion);
            }
        });

    }
}
