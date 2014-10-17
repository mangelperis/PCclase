package com.example.mati.pruebamundo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by mati on 16/10/14.
 */
public class Linear extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);





    }

    public void cmdCerrarClick (View v){
        finish();
    }


}
