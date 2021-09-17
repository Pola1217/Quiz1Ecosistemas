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

                    String nombre = PUser.getString("nombree", "");
                    String codigo = PUser.getString("codigoo","");

                    names = name.getText().toString().trim();
                    codes = code.getText().toString().trim();


                    if(codes.isEmpty()==true){

                        Toast.makeText(this, "Llene los datos", Toast.LENGTH_LONG).show();

                    }else{

                        if(codigo.contains(codes)){

                            Toast.makeText(this, "Usario ya registrado", Toast.LENGTH_LONG).show();

                        }else{

                            String nombres = nombre+":"+names;
                            String codigos = codigo+":"+codes;

                            PUser.edit().putString("nombres",nombres).apply();
                            PUser.edit().putString("codigos",codigos).apply();

                            Intent i = new Intent(this, Preparacion.class);
                            startActivity(i);
                            finish();

                        }
                    }

                }
        );
    }

}
