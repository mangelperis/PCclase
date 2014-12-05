package com.example.mati.figurasaleatorias;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DrawingExampleLauncher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


   public void launchDrawShapes1(View clickedButton){
       Intent activityIntent = new Intent(this,DrawShapes1.class);
       startActivity(activityIntent);

   }

    public void launchDrawShapes2(View clickedButton){
        Intent activityIntent = new Intent(this, DrawShapes2.class);
        startActivity(activityIntent);
    }
}
