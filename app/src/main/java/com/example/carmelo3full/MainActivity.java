package com.example.carmelo3full;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.carmelo3full.RecyclerAdapters.ImportAdapter;
import com.example.carmelo3full.RecyclerAdapters.MainAdapter;
import com.example.carmelo3full.db.User;
import com.example.carmelo3full.viewmodel.ViewModel;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.carmelo3full.AppConstants.PERMISSION_CALL_PHONE;
import static com.example.carmelo3full.AppConstants.PERMISSION_PHONE_STATE;
import static com.example.carmelo3full.AppConstants.PERMISSION_READ_CONTACT;

public class MainActivity extends AppCompatActivity {
    private MainAdapter adapter;
    public static List<ContactosMain> myContacts = new ArrayList<>();
    public static List<ContactosMain> myFriends = new ArrayList<>();
    public static ViewModel v;
    public static List<User> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        v = new ViewModelProvider(this).get(ViewModel.class);

        v.getListLiveData().observe(this, new Observer<List<User>>() {

            @Override
            public void onChanged(List<User> users) {
                lista = new ArrayList<>();
                for (int i = 0; i < users.size(); i++){
                    lista.add(users.get(i));
                }

                initRecyclerView();

            }
        });


    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerMain);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MainAdapter(this, getApplication());
        recyclerView.setAdapter(adapter);
        adapter.setMainList(lista);
    }

    private void init() {
        checkPermission();
        Button importar = findViewById(R.id.btImportMain);
        Button llamadas = findViewById(R.id.btCallsMain);
        Button insertar = findViewById(R.id.btInsertMain);


        importar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Importar.class);
                startActivity(intent);

            }
        });

        llamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calls.class);
                startActivity(intent);
            }
        });

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Insertar.class);
                startActivity(intent);
            }
        });

    }


    private void checkPermission() {
        boolean contactReadPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
        boolean callPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
        boolean readPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
        if (contactReadPermission && callPhonePermission && readPhoneState) {
            getContactInfo();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},PERMISSION_READ_CONTACT);
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CALL_PHONE);
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},PERMISSION_PHONE_STATE);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_READ_CONTACT && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContactInfo();
        }

    }

    private void getContactInfo() {
        myContacts.clear();

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);

        if(cursor!=null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                String CONTACT_ID =cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                ContactosMain contacts = new ContactosMain();

                if(hasPhoneNumber>0){
                    contacts.setContactName(name);

                    Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = ?",
                            new String[]{CONTACT_ID}, null);
                    List<String> contactList = new ArrayList<>();
                    assert phoneCursor != null;
                    phoneCursor.moveToFirst();
                    while (!phoneCursor.isAfterLast()){
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace(" ","");
                        contactList.add(phoneNumber);
                        phoneCursor.moveToNext();
                    }
                    contacts.setContactNumber(contactList);
                    myContacts.add(contacts);
                    phoneCursor.close();

                }
            }
        }
        cursor.close();

    }














}