package com.example.mati.linearlayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        final Button setCol = (Button) findViewById(R.id.buttonSet);
        final Button borrar = (Button)findViewById(R.id.button2);
        //NO ES NECESARIO LOS RADIO
        //final RadioButton rojo = (RadioButton)findViewById(R.id.radioRojo);
        //final RadioButton azul = (RadioButton)findViewById(R.id.radioAzul);
        //final RadioButton verde = (RadioButton)findViewById(R.id.radioVerde);

        final TextView textview = (TextView)findViewById(R.id.textview);


       setCol.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             if (rg.getCheckedRadioButtonId()==R.id.radioRojo){
                 textview.setBackgroundColor(Color.RED);

             }
             if (rg.getCheckedRadioButtonId()==R.id.radioAzul){
                 textview.setBackgroundColor(Color.BLUE);

             }
             if (rg.getCheckedRadioButtonId()==R.id.radioVerde){
                 textview.setBackgroundColor(Color.GREEN);

             }

                                     }
                                 });

       borrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               textview.setBackgroundColor(Color.WHITE);
           }
       });

    }




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
