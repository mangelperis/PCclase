package org.aguilar.ejemplowebservice;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EjemploWebService extends Activity {

	private EditText longitud;
	private EditText latitud;
	private Button boton;
	private TextView texto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        longitud = (EditText)findViewById(R.id.longitud);
        latitud = (EditText)findViewById(R.id.latitud);
        boton = (Button)findViewById(R.id.boton);
        texto = (TextView)findViewById(R.id.texto);
        
        //Escuchador para el bot�n
        boton.setOnClickListener(new OnClickListener() {
        	public void onClick(View view) {
        		try {
        			//Recuperamos los valores introducidos en los campos
        			//de latitud y longitud
        			String longitudCad = longitud.getText().toString();
        			String latitudCad = latitud.getText().toString();
        			String resultado = busquedaGoogle(longitudCad, latitudCad);
        			texto.setText(resultado);
        		} catch (Exception e) {
        			texto.setText("Error al conectar\n");
        			e.printStackTrace();
        		}
        	}
        });
    }
    
    //M�todo que conectar� con el WebService de Google que nos permite obtener
    //los datos de una localizaci�n dadas sus coordenadas
    String busquedaGoogle(String longitud, String latitud) {
    	String devuelve = "";
    	
    	//Creamos un nuevo objeto HttpClient que ser� el encargado de realizar la
    	//comunicaci�n HTTP con el servidor a partir de los datos que le damos.
    	HttpClient comunicacion = new DefaultHttpClient();
    	//Creamos una peticion GET indicando la URL de llamada al servicio.
    	String url = "http://maps.googleapis.com/maps/api/geocode/json?" +
				"latlng=" + latitud + "," + longitud + "&sensor=false";
    			//"latlng=38.15,-0.89&sensor=false");
    	HttpGet peticion = new HttpGet(url);
    	//Modificamos mediante setHeader el atributo http content-type para indicar
    	//que el formato de los datos que utilizaremos en la comunicaci�n ser� JSON.
    	peticion.setHeader("content-type", "application/json");

    	try {
    		//Ejecutamos la petici�n y obtenemos la respuesta en forma de cadena
    		HttpResponse respuesta = comunicacion.execute(peticion);
    		String respuestaCad = EntityUtils.toString(respuesta.getEntity());
    		//Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
    		JSONObject respuestaJSON = new JSONObject(respuestaCad);
    		//Accedemos al vector de resultados
    		JSONArray resultJSON = respuestaJSON.getJSONArray("results");
    		//Vamos obteniendo todos los campos que nos interesen.
    		//En este caso obtenemos la primera direcci�n de los resultados.
    		String direccion="SIN DATOS PARA ESA LONGITUD Y LATITUD";
    		if (resultJSON.length()>0){
    			direccion = resultJSON.getJSONObject(0).getString("formatted_address");
    		}
    		devuelve = "Direcci�n: " + direccion;
    	} catch(Exception e) {
    		Log.e("Error ies REST", "ERROR!!", e);
    	}
    	
    	return devuelve;
    }
}