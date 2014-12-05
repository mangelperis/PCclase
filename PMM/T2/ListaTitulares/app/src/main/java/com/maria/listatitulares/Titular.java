package com.maria.listatitulares;

/**
 * Created by Maria on 10/11/2014.
 */
public class Titular {

    private String titulo;
    private String subtitulo;

    public Titular(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }
    public String getTitulo(){
        return titulo;
    }

    public String getSubtitulo(){
        return subtitulo;
    }
    
}
