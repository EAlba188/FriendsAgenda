package com.example.carmelo3full;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carmelo3full.db.User;
import com.example.carmelo3full.viewmodel.ViewModel;

public class Editar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        init();
    }

    private void init() {

        TextView name = findViewById(R.id.tvNameEdit);
        TextView phone = findViewById(R.id.tvPhoneEdit);
        TextView bday = findViewById(R.id.tvBday);
        Button editBday = findViewById(R.id.btEditBday);
        Button editName = findViewById(R.id.btEditName);
        Button editPhone = findViewById(R.id.btEditPhone);
        Bundle bundle = getIntent().getExtras();
        String nameBundle = bundle.getString("name");
        String phoneBundle = bundle.getString("phone");
        String bdayBundle = bundle.getString("bday");
        Button goBack = findViewById(R.id.btAtras);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Editar.this, MainActivity.class));
            }
        });

        name.setText(nameBundle);
        phone.setText(phoneBundle);
        bday.setText(bdayBundle);

        editBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Cambiar fecha?");
                String nuevo = bday.getText().toString();
                builder.setMessage("¿Cambiar a "+nuevo+"?");
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ViewModel vm = new ViewModel(getApplication());
                        int id = vm.getId(nameBundle);
                        vm.updateBday(nuevo, id, getApplicationContext());

                        Toast.makeText(Editar.this, "Saved", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("Cancelar",null);

                AlertDialog alert = builder.create();
                builder.show();
            }
        });

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Cambiar nombre");
                String nuevo = name.getText().toString();
                builder.setMessage("¿Cambiar a "+nuevo+"?");
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(name.getText().toString().equalsIgnoreCase("")){
                            Toast.makeText(Editar.this, "El nombre debe ser rellenado", Toast.LENGTH_SHORT).show();
                        }else{
                            ViewModel vm = new ViewModel(getApplication());
                            User user = new User();
                            user.name = name.getText().toString();
                            user.phoneNumber = phone.getText().toString();

                            if(vm.getName(name.getText().toString())>0){
                                Toast.makeText(Editar.this, "Ya hay alguien con ese nombre", Toast.LENGTH_SHORT).show();
                            }else{
                                int id = vm.getId(nameBundle);
                                vm.updateName(nuevo, id, getApplicationContext());

                                Toast.makeText(Editar.this, "Saved", Toast.LENGTH_SHORT).show();
                            }
                        }



                    }
                });

                builder.setNegativeButton("Cancelar",null);

                AlertDialog alert = builder.create();
                builder.show();
            }
        });

        editPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Cambiar nombre");
                String nuevo = phone.getText().toString();
                builder.setMessage("¿Cambiar a "+nuevo+"?");
                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ViewModel vm = new ViewModel(getApplication());
                        int id = vm.getId(nameBundle);
                        vm.updatePhone(nuevo, id, getApplicationContext());


                        Toast.makeText(Editar.this, "Saved", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("Cancelar",null);

                AlertDialog alert = builder.create();
                builder.show();

            }
        });



    }
}