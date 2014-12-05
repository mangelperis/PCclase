package com.maria.ejemplocanvas;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



public class EjemploCanvas extends Activity {

    private BitmapDrawable miImagen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {
        public EjemploView(Context contexto) {
            super(contexto);
            Resources res = contexto.getResources();
            miImagen = (BitmapDrawable) res.getDrawable(R.drawable.logo_cefire);
            miImagen.setBounds(new Rect(30,30,200,200));
        }
        @Override
        protected void onDraw(Canvas canvas) {
            //Dentro de este método utilizamos los métodos para dibujar BitmapDrawable
            miImagen.draw(canvas);
        }
    }

}
