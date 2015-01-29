package com.example.mati.examen2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Destino[] destinos = new Destino[]{
            new Destino("Zona A", "Asia/Oceania", "30"),
            new Destino("Zona B", "America/Africa", "20"),
            new Destino("Zona C", "Europa", "10")};


    RadioButton tarnormal, tarurgente;
    static double tarifa;
    int peso = 0;
    int precio;
    String zona, continente, resultado, clase, decoracion;
    CheckBox tarjeta, regalo;


    class AdaptadorDestino extends ArrayAdapter<Destino> {

        Activity context;

       public AdaptadorDestino(Activity context) {
            super(context, R.layout.resultado, destinos);
            this.context = context;
        }

        public View getDropDownView ( int position, View convertView, ViewGroup parent){
            return getView (position, convertView, parent);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.destinos, null);
            // ZONA + CONTINENTE + PRECIO
            final TextView lblZona = (TextView)item.findViewById(R.id.LabelZona);
            lblZona.setText(destinos[position].getZona()+"\n ("+destinos[position].getContinente()+")\n"+destinos[position].getPrecio());

            //PRECIO
           // final TextView lblPrecio = (TextView)item.findViewById(R.id.LabelPrecio);
          //  lblPrecio.setText("Precio: ");

            return(item);
        }
    }

    //PANTALLA PPAL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Spinner spDestinos = (Spinner)findViewById(R.id.destinos);

        AdaptadorDestino adaptador = new AdaptadorDestino(this);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spDestinos.setAdapter(adaptador);

        spDestinos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        zona = destinos[position].getZona();
                        continente = destinos[position].getContinente();
                        precio = Integer.parseInt(destinos[position].getPrecio());
                    }
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


        final Button btnCalc = (Button)findViewById(R.id.BotonCalcular);
        tarurgente=(RadioButton)findViewById(R.id.tarurgente);
        tarnormal=(RadioButton)findViewById(R.id.tarnormal);
        tarjeta = (CheckBox) findViewById(R.id.checktarjeta);
        regalo = (CheckBox) findViewById(R.id.checkregal);

        //final RadioGroup radiog = (RadioGroup)findViewById(R.id.radiogroup1);
        final EditText txtpeso = (EditText)findViewById(R.id.textpeso);

        btnCalc.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String pes = txtpeso.getText().toString();
                peso = Integer.parseInt(pes);

                if (peso <= 5){
                    tarifa = precio + (peso * 1);
                }else {
                    if (peso >= 6 && peso <= 10){
                        tarifa = precio + (peso * 1.5);
                    }else {
                        if (peso > 10){
                            tarifa = precio + (peso * 2);
                        }
                    }
                }
                //Caja regalo
                if (regalo.isChecked() && !(tarjeta.isChecked())){
                    decoracion = "Con caja regalo";
                }else {
                    //Dedicada
                    if (tarjeta.isChecked() && !(regalo.isChecked())){
                        decoracion = "Con tarjeta dedicada";
                    }else {
                        //AMBOS marcados
                        if (regalo.isChecked() && tarjeta.isChecked()){
                            decoracion = " Con caja regalo y dedicatoria";
                        }else {
                            //Nada marcado
                            decoracion = "Sin decoracion";
                        }
                    }
                }

                //URGENTE = 30 % extra
                if (tarurgente.isChecked()){
                    tarifa = tarifa + (tarifa * 0.3);
                    clase = "urgente";
                }else {
                    clase = "normal";
                }


                resultado = ("Zona: " + zona + " (" + continente +") \n Tarifa: " + clase + "\nPeso: " + peso +
                        " Kg" + "\n\nDecoracion: " + decoracion + "\nCOSTE FINAL: " + tarifa + " €");


                Intent intent = new Intent(MainActivity.this, Resultado.class);
                Bundle b = new Bundle();


                b.putString("Tarifa", resultado);
                b.putDouble("Precio", tarifa);

                intent.putExtras(b);


                startActivity(intent);

            }
        });

    }

}



