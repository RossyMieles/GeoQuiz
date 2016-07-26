package com.example.rossy.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

     // constate para el par de clave
    private static final String KEY_INDEX = "index";

    private static final int codigoSolicitud = 0;


    private Button TrueButton;
    private Button FalseButton;
    private Button NextButton;
    private TextView QuestionTextView;
    private Button CheatButton;


    //matriz de preguntas llamando al constructor
    private Question[] bancoPregunta = new Question[] {
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    //indice de la matrix
    private int contador  = 0;
    //variable para mantener el valor de CheatActivity
    private boolean trampa;


    //metodo de actualizacion
    private void actualizarPregunta() {
                       //enviar  valor como un extra en la intención que se pasa a startActivity
        int question = bancoPregunta[contador].getPregunta();
        QuestionTextView.setText(question);
    }

    //metodo para ver si al pulsar el usuario la pregunta es correcta o no
    private void checkAnswer(boolean verdadero) {
        boolean respuestaVerdadera = bancoPregunta[contador].isRespuesta();
        int mensaje = 0;

        //verificar  si el usuario engañado responder apropiadamente
        if (trampa) {
            mensaje = R.string.judgment_toast;
        }

        else {

            if (verdadero == respuestaVerdadera) {
            mensaje= R.string.correct_toast;
        } else {
            mensaje= R.string.incorrect_toast;
             }
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT)
                .show();
    }


    //metodo de instancia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //infla un diseño
        setContentView(R.layout.activity_main);

          //boton ver respuesta
        CheatButton = (Button)findViewById(R.id.cheat_button);
        CheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                boolean answerIsTrue = bancoPregunta[contador].isRespuesta();

                //intento de la clase CheatActivity pasa a startActivity
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);


                //metodo cuando se vuelve a escuchar de la actividad del niño
                startActivity(i);
                startActivityForResult(i, codigoSolicitud);


            }
        });


        QuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //inditificador de recurso
        TrueButton = (Button) findViewById(R.id.true_button);

        TrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                checkAnswer(true);


            }
        });

        FalseButton = (Button) findViewById(R.id.false_button);
        FalseButton.setOnClickListener(new View.OnClickListener(){
           @Override
             public  void onClick( View v ){

             checkAnswer(false);
                      }
        });
                                //acepta un identificador
        NextButton = (Button) findViewById(R.id.next_button);
        NextButton.setOnClickListener(new View.OnClickListener() {


            //se incrementa el indice y se actualiza el texto
            @Override
            public void onClick(View v) {
                contador = (contador + 1) % bancoPregunta.length;

                trampa= false;

                actualizarPregunta();
            }
        });
        actualizarPregunta();
    }


    
    @Override
    protected void onActivityResult(int codigo, int resultadoCodigo, Intent dato){
        if (resultadoCodigo != Activity.RESULT_OK) {
        return;
        }
        if (codigo == codigoSolicitud) {
        if (dato == null) {
        return;
        }
        trampa = CheatActivity.wasAnswerShown(dato);

        }
        }
     }



