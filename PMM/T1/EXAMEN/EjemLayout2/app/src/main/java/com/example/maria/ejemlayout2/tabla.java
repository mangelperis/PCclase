package com.example.maria.ejemlayout2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Maria on 27/09/2014.
 */
public class tabla extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla);

    }
    public void cmdCerrarClick( View v){
        finish();
    }
}
