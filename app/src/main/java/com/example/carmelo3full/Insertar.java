package com.example.carmelo3full;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carmelo3full.db.User;
import com.example.carmelo3full.viewmodel.ViewModel;

public class Insertar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        init();
    }

    private void init() {

        Button atras = findViewById(R.id.btAtrasInsert);
        Button save = findViewById(R.id.btGuardarInsert);
        EditText bday = findViewById(R.id.etBdayInsert);
        EditText name = findViewById(R.id.etNombreInsert);
        EditText phone = findViewById(R.id.etPhoneInsert);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(Insertar.this, "El nombre debe ser rellenado", Toast.LENGTH_SHORT).show();
                }else{
                    ViewModel vm = new ViewModel(getApplication());
                    User user = new User();
                    user.name = name.getText().toString();
                    user.phoneNumber = phone.getText().toString();
                    user.birthDate = bday.getText().toString();

                    if(vm.getName(name.getText().toString())>0){
                        Toast.makeText(Insertar.this, "Ya hay alguien con ese nombre", Toast.LENGTH_SHORT).show();
                    }else{
                        vm.insert(user, getApplicationContext());
                        Toast.makeText(Insertar.this, "Guardado con exito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Insertar.this, MainActivity.class));
                    }
                }

            }
        });

    }
}