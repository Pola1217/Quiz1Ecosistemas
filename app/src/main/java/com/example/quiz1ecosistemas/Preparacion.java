package com.example.quiz1ecosistemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Preparacion extends AppCompatActivity {

    private CheckBox opc1, opc2, opc3;
    private Button nextBtn2;
    private int puntosP = 0;
    private boolean next = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparacion);

        opc1 = findViewById(R.id.check1);
        opc2 = findViewById(R.id.check2);
        opc3 = findViewById(R.id.check3);

        nextBtn2 = findViewById(R.id.next2);

        //cambios botones
        thread();

        nextBtn2.setOnClickListener(
                (view) -> {
                    next = false;

                    //puntos de los checks
                    if(opc1.isChecked() == true){
                        puntosP += 1;
                    }
                    if(opc2.isChecked() == true){
                        puntosP += 3;
                    }
                    if(opc3.isChecked() == true){
                        puntosP += 0;
                    }

                    if(opc1.isChecked() == true || opc2.isChecked() == true || opc3.isChecked() == true ){

                        Intent i = new Intent(this, Autoevaluacion.class);

                        //pPrep
                        i.putExtra("puntoN", puntosP);

                        startActivity(i);
                        finish();

                    }else{
                        Toast.makeText(this, "Escoja una opción", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void thread (){

        new Thread(
                () ->{

                    while(next == true){

                        runOnUiThread(

                                () -> {

                                    //si hace Check en "ninguna da las anteriores" no puede hacer check en el resto
                                    if(opc3.isChecked() == true){

                                        nextBtn2.setBackgroundColor(Color.rgb(226,0,45));

                                        opc1.setChecked(false);
                                        opc2.setChecked(false);

                                    }

                                    //boton cambia cuando hace check en alguna opcion
                                    if(opc1.isChecked() || opc2.isChecked() || opc3.isChecked() ) {

                                        nextBtn2.setBackgroundColor(Color.rgb(63,81,181));
                                        nextBtn2.setEnabled(true);

                                    }else{ //si no hace check no cambia

                                        nextBtn2.setBackgroundColor(Color.rgb(190,215,218));
                                        nextBtn2.setEnabled(false);
                                    }

                                }
                        );
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
        ).start();

    }
}