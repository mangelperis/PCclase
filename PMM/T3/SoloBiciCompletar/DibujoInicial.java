package com.maria.dibujoinicial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DibujoInicial extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MiDibujo(this));
    }

  class MiDibujo extends View {
      public MiDibujo(Context c){

          super(c);
      }

      protected void onDraw(Canvas lienzo){


          Paint miPincel= new Paint();
          miPincel.setColor(Color.RED);
          miPincel.setStyle(Paint.Style.STROKE);
          lienzo.drawCircle(500,500,300,miPincel);
        
          miPincel.setTextSize(60);
          lienzo.drawText("MI CIRCULO",500,900,miPincel);
        
         }
  }

}
