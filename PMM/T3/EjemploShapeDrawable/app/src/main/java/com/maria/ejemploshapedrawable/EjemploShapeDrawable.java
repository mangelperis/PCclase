package com.maria.ejemploshapedrawable;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.View;

public class EjemploShapeDrawable extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VistaAMedida(this));
    }

    public class VistaAMedida extends View {
        private ShapeDrawable miDrawable;
        private ShapeDrawable miDrawable1;
        public VistaAMedida(Context contexto) {
            super(contexto);
            int x=10, y=10;
            int ancho=300, alto=300;
            miDrawable = new ShapeDrawable(new OvalShape());
            miDrawable.getPaint().setColor(0xff0000ff);
             miDrawable.setBounds(x, y, x + ancho, y + alto);

            miDrawable1 = new ShapeDrawable(new OvalShape());
            miDrawable1.getPaint().setColor(Color.BLACK);
            miDrawable1.setBounds(x, y, x + 200, y + 200);

        }

        protected void onDraw(Canvas canvas) {
                miDrawable.draw(canvas);
           }
    }

}
