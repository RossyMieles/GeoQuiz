package com.example.rossy.geoquiz;

/**
 * Created by Rossy on 13/07/2016.
 */
public class Question {


    private int pregunta;
    private boolean respuesta;
    public Question(int mpregunta, boolean mrespuesta) {
        pregunta = mpregunta;
        respuesta = mrespuesta;



    }

    public int getPregunta() {
        return pregunta;
    }

    public void setPregunta(int pregunta) {
        this.pregunta = pregunta;
    }

    public boolean isRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }



}
