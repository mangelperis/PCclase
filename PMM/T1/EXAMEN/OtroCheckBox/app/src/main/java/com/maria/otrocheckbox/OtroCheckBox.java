package com.maria.otrocheckbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


public class OtroCheckBox extends Activity {

    CheckBox chkBoxCycling;
    CheckBox chkBoxTeaching;
    CheckBox chkBoxBlogging;
    TextView txtHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otro_check_box);

        initialUISetup();
    }

    public void initialUISetup()
    {
        chkBoxCycling = (CheckBox) findViewById(R.id.chkBoxCycling);
        chkBoxTeaching = (CheckBox) findViewById(R.id.chkBoxTeaching);
        chkBoxBlogging = (CheckBox) findViewById(R.id.chkBoxBlogging);

        TextView txtHobby = (TextView)findViewById(R.id.txtHobby);
        chkBoxCycling.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxTeaching.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
        chkBoxBlogging.setOnCheckedChangeListener(new myCheckBoxChangeClicker());
    }

    class myCheckBoxChangeClicker implements CheckBox.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            String mensaje="";
            if(isChecked)
            {
                if(buttonView==chkBoxCycling)
                {  mensaje+= "Cycling";
                    showTextNotification(mensaje);
                }

                if(buttonView==chkBoxTeaching)
                {mensaje+= " Teaching";
                    showTextNotification(mensaje);
                }

                if(buttonView==chkBoxBlogging)
                {mensaje+= " Blogging";
                    showTextNotification(mensaje);
                }
            }
        }
    }// clase interna


    public void showTextNotification(String msgToDisplay)
    { Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }
}
