package com.maria.listaopcionesxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class ListaOpcionesXml extends Activity {

    private String mItemSelectedMessageTemplate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_opciones_xml);
        mItemSelectedMessageTemplate =
                getString(R.string.lista_message_template);
        ListView lista1 = (ListView)findViewById(R.id.lista1);
      lista1.setOnItemClickListener(new ListaInfo());
        // Code for spinner2 shown later
    }
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private class ListaInfo implements AdapterView.OnItemClickListener {
        private boolean isFirst = true;
        @Override
        public void onItemClick(AdapterView<?> lista, View selectedView,
                                   int selectedIndex, long id) {
            if (isFirst) {
                isFirst = false;
            } else {
                String selection =
                        lista.getItemAtPosition(selectedIndex).toString();
                String message =
                        String.format(mItemSelectedMessageTemplate, selection);
                showToast(message);
            }
        }

        public void onNothingSelected(AdapterView<?> lista) {
             // Won’t be invoked unless you programmatically remove entries
        }
    } //clase interna

    }
