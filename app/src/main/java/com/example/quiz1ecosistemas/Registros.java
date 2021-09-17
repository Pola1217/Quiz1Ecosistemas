package com.example.quiz1ecosistemas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registros extends AppCompatActivity {

    private EditText name, code;
    private Button nextBtn;
    private String names, codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        nextBtn = findViewById(R.id.next);

        nextBtn.setOnClickListener(

                (view) -> {

                    SharedPreferences PUser = getSharedPreferences("user",MODE_PRIVATE);

                    String nombre = PUser.getString("nombre", "");
                    String codigo = PUser.getString("codigo","");

                    names = name.getText().toString().trim();
                    codes = code.getText().toString().trim();


                    if(codes.isEmpty()==true){

                        Toast.makeText(this, " Completar datos", Toast.LENGTH_LONG).show();

                    }else{

                        if(codigo.contains(codes)){

                            Toast.makeText(this, "Usario registrado", Toast.LENGTH_LONG).show();

                        }else{

                            String nombres = nombre+":"+names;
                            String codigos = codigo+":"+codes;

                            PUser.edit().putString("nombre",nombres).apply();
                            PUser.edit().putString("codigo",codigos).apply();

                            Intent i = new Intent(this, Preparacion.class);
                            startActivity(i);
                            finish();

                        }
                    }

                }
        );
    }

}
