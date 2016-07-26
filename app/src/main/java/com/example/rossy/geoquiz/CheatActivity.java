package com.example.rossy.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends AppCompatActivity {


//constante de llave extras

    private static final String RESPUESTAEXTRAVERDAD ="com.example.rossy.geoquiz.answer_is_true";


    private static final String RESPUESTAEXTRA = "com.bignerdranch.android.geoquiz.answer_shown";


    //metodo de intento
    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(RESPUESTAEXTRAVERDAD  , answerIsTrue);
        return i;


    }

    //decodificar el extra
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra( RESPUESTAEXTRA, false);
    }

     //varriable para recuperar el valor extra
    private boolean respuetaVerdadera;

    //   ver respuesta
    private TextView AnswerTextView;

    // boton de ver respuesta
    private Button ShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

                            //devuelve una intencion desde qinicia una actividad
        respuetaVerdadera= getIntent().getBooleanExtra(RESPUESTAEXTRAVERDAD,true);
        getIntent().getBooleanExtra(RESPUESTAEXTRAVERDAD, false);


        AnswerTextView= (TextView) findViewById(R.id.answer_text_view);
        ShowAnswer = (Button) findViewById(R.id.show_answer_button);

        //metodo de ver la respuesta
        ShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (respuetaVerdadera) {
                    AnswerTextView.setText(R.string.true_button);
                } else {
                    AnswerTextView.setText(R.string.false_button);
                }

                setAnswerShownResult(true);
            }
        });

    }
           //metodo privado para pasar los datos
        private void setAnswerShownResult(boolean respuestaMostrada) {
            Intent data = new Intent();
            data.putExtra( RESPUESTAEXTRA, respuestaMostrada);
            setResult(RESULT_OK, data);
        }



    }

