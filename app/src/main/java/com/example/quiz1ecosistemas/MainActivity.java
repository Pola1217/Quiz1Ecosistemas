package com.example.quiz1ecosistemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView estudiantes;
    private Button registroBtn;
    private String nombre, code, Ptotales, lista;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciar
        estudiantes = findViewById(R.id.students);
        registroBtn = findViewById(R.id.registro);

        //clic boton registrar
        registroBtn.setOnClickListener(
                (view) -> {
                    Intent i = new Intent(this, Registros.class);
                    startActivity(i);
                }
        );
    }



    //antes que la actividad sea visible
    @Override
    protected void onResume() {
        super.onResume();

        //shared de los puntajes
        SharedPreferences preferencesPuntaje = getSharedPreferences("puntaje", MODE_PRIVATE);
        Ptotales = preferencesPuntaje.getString("todosLosPuntos", "");

        //shared de los estudiantes
        SharedPreferences preferencesUser = getSharedPreferences("user", MODE_PRIVATE);
        nombre = preferencesUser.getString("nombre", "");
        code = preferencesUser.getString("codigo", "");


        //variables estudiantes
        String[] codigos = code.split(":");
        String[] nombres = nombre.split(":");
        String[] puntos = Ptotales.split(":");

        //esta es una lista erronea pero que muestra que si guarda los nombres y codigos y que al hacerlo
        //no pueden repetir ni nombre ni codigo
        lista = nombres + "    " + puntos;
        estudiantes.setText(lista);

        //lista que por alguna razon ya no funciono
        for (int i = 0; i < nombres.length; i++) {
            contador = i;
            estudiantes.append(nombres[contador] + "     " + puntos[contador] + "\n");
        }
    }
    }

