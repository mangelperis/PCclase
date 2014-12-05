package com.maria.ejemploshapedrawable;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
/**
 * Created by mati on 20/11/14.
 */
public class EjemploShapeDrawable2 extends Activity{

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
            miPincel.setColor(Color.MAGENTA);
            miPincel.setStyle(Paint.Style.STROKE);
            miPincel.setStrokeWidth(8);
            lienzo.drawCircle(lienzo.getWidth()/2,lienzo.getHeight()/2,125,miPincel);

            miPincel.setColor(Color.RED);
            miPincel.setStyle(Paint.Style.FILL_AND_STROKE);
            lienzo.drawRect(0, 0, 100, 100, miPincel);

            miPincel.setColor(Color.BLUE);
            miPincel.setStyle(Paint.Style.FILL_AND_STROKE);
            lienzo.drawRect(lienzo.getWidth()-100,0,lienzo.getWidth(),100, miPincel);

            miPincel.setColor(Color.CYAN);
            miPincel.setStyle(Paint.Style.FILL_AND_STROKE);
            miPincel.setStrokeWidth(10);
            lienzo.drawPoint(lienzo.getWidth() / 2, lienzo.getHeight() / 2, miPincel);

            miPincel.setColor(Color.BLACK);
            miPincel.setStyle(Paint.Style.FILL_AND_STROKE);
            lienzo.drawRect(0,lienzo.getHeight()-100, 100,lienzo.getHeight(), miPincel);

            miPincel.setColor(Color.GREEN);
            miPincel.setStyle(Paint.Style.FILL_AND_STROKE);
            lienzo.drawRect(lienzo.getWidth()-100,lienzo.getHeight()-100,lienzo.getWidth(),lienzo.getHeight(),miPincel);
        }
    }
}










