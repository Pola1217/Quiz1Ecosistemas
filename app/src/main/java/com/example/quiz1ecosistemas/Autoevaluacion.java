
package com.example.quiz1ecosistemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Autoevaluacion extends AppCompatActivity {

    private CheckBox opc4, opc5, opc6;
    private Button nextBtn3, endBtn;
    private int puntosA = 0;
    private int puntosT = 0;
    private boolean next = true;
    private String PuntosT, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autoevaluacion);

        nextBtn3 = findViewById(R.id.next3);
        endBtn = findViewById(R.id.end);
        opc4 = findViewById(R.id.check4);
        opc5 = findViewById(R.id.check5);
        opc6 = findViewById(R.id.check6);


        puntosT = getIntent().getExtras().getInt("puntoP");

        //cambios botones
        thread();

        nextBtn3.setOnClickListener(
                (view) -> {
                    Toast.makeText(this, "Escoja una opción", Toast.LENGTH_LONG).show();
                }
        );

        endBtn.setOnClickListener(
                (view) -> {
                    next = false;

                    //puntos checks
                    if(opc4.isChecked() == true){
                        puntosA += 3;
                    }
                    if(opc5.isChecked() == true){
                        puntosA += 3;
                    }
                    if(opc6.isChecked() == true){
                        puntosA += 0;
                    }

                    puntosA += puntosT;


                    if(opc4.isChecked() == true || opc5.isChecked() == true || opc6.isChecked() == true ){

                        //shared preferences de los puntajes de los sintomas
                        SharedPreferences Puntaje = getSharedPreferences("puntaje", MODE_PRIVATE);
                        total = Puntaje.getString("todosLosPuntos","");

                        PuntosT = ""+puntosA;

                        String totalFinal = total+":"+PuntosT;
                        Puntaje.edit().putString("todosLosPuntos", totalFinal).apply();

                        Intent i = new Intent(this, MainActivity.class);


                        startActivity(i);
                        finish();

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
                                    if(opc6.isChecked() == true){

                                        endBtn.setVisibility(View.VISIBLE);
                                        nextBtn3.setVisibility(View.GONE);

                                        opc4.setChecked(false);
                                        opc5.setChecked(false);

                                    }

                                    //si escoge alguna opcion entonces el boton cambia de color a activo
                                    if( opc4.isChecked() || opc5.isChecked() || opc6.isChecked() ){

                                        endBtn.setVisibility(View.VISIBLE);
                                        endBtn.setEnabled(true);

                                        nextBtn3.setVisibility(View.GONE);
                                        nextBtn3.setEnabled(false);

                                    }else{

                                        endBtn.setVisibility(View.GONE);
                                        endBtn.setEnabled(false);

                                        nextBtn3.setVisibility(View.VISIBLE);
                                        nextBtn3.setEnabled(true);

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