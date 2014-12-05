package com.example.mati.sumarrestar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final EditText valor1 = (EditText) findViewById(R.id.valor1);
        final EditText valor2 = (EditText) findViewById(R.id.valor2);

        final RadioGroup rg = (android.widget.RadioGroup) findViewById(R.id.gruporb);

        final Button btnOperar = (Button) findViewById(R.id.btnOperar);

        final TextView resultado = (TextView) findViewById(R.id.resultado);

       final CheckBox cb = (CheckBox)findViewById(R.id.checkBox);

        final ImageView img = (ImageView)findViewById(R.id.miImg);

        btnOperar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int op1 = Integer.parseInt("" + valor1.getText());
                int op2 = Integer.parseInt("" + valor2.getText());


                if (rg.getCheckedRadioButtonId() == R.id.radioSumar) {
                    int res = op1 + op2;
                    //resultado.setText("" + (op1 + op2));
                    resultado.setText("" + res);
                }
                if (rg.getCheckedRadioButtonId() == R.id.radioRestar) {
                    int res = op1 - op2;
                    resultado.setText("" + res);
                }

            }

        });

      cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb.isChecked()){
                    img.setVisibility(View.VISIBLE);

                }else{
                    img.setVisibility(View.INVISIBLE);
                }

            }
        });


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
}
