package com.example.carmelo3full;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.carmelo3full.RecyclerAdapters.CallsAdapter;
import com.example.carmelo3full.RecyclerAdapters.MainAdapter;
import com.example.carmelo3full.db.User;
import com.example.carmelo3full.dbCalls.UserCalls;
import com.example.carmelo3full.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Calls extends AppCompatActivity {
    public static ViewModel v;
    public static List<UserCalls> lista;
    private CallsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        init();
    }

    private void init() {

        v = new ViewModelProvider(this).get(ViewModel.class);

        v.getListLiveData2().observe(this, new Observer<List<UserCalls>>() {

            @Override
            public void onChanged(List<UserCalls> users) {
                lista = new ArrayList<>();
                for (int i = 0; i < users.size(); i++){
                    lista.add(users.get(i));
                }

                initRecyclerView();

            }
        });


        Button main = findViewById(R.id.btMainCalls);
        TextView id = findViewById(R.id.tvIdCalls);
        TextView idAmigo = findViewById(R.id.tvIdAmigoCalls);
        TextView fecha = findViewById(R.id.tvFechaCalls);


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerCalls);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new CallsAdapter(this, getApplication());
        recyclerView.setAdapter(adapter);
        adapter.setMainList(lista);
    }
}