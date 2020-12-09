package com.example.carmelo3full;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.example.carmelo3full.RecyclerAdapters.ImportAdapter;
import com.example.carmelo3full.RecyclerAdapters.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.carmelo3full.MainActivity.myContacts;

public class Importar extends AppCompatActivity {
    private ImportAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_importar);

        init();
        initRecyclerView();
    }



    private void init() {
        Button main = findViewById(R.id.btMainImportar);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void initRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recyclerImport);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new ImportAdapter(this, getApplication());
        recyclerView.setAdapter(adapter);
        adapter.setMainList(myContacts);

    }

















}