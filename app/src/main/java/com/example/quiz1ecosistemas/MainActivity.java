package com.example.quiz1ecosistemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView estudiantes;
    private Button registroBtn;
    private String nombre, code, Ptotales;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciar
        registroBtn = findViewById(R.id.registro);
        estudiantes = findViewById(R.id.students);

        //estudiantes.setMovementMethod(new ScrollingMovementMethod());

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
        SharedPreferences preferencesPuntaje = getSharedPreferences("puntos",MODE_PRIVATE);
        Ptotales = preferencesPuntaje.getString("todosLosPuntos","");

        //shared de los estudiantes
        SharedPreferences preferencesUser = getSharedPreferences("user",MODE_PRIVATE);
        nombre = preferencesUser.getString("nombres","");
        code = preferencesUser.getString("codigos","");


        //variables estudiantes
        String[] numbers = code.split(":");
        String[] nombres = nombre.split(":");
        String[] puntos = Ptotales.split(":");

        estudiantes.setText(" ");

        //
        for(int i=0; i < nombres.length; i++) {
            contador = i;
            estudiantes.append(nombres[contador] + "     " +puntos[contador] + "\n");

        }

    }
}
