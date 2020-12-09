package com.example.carmelo3full.RecyclerAdapters;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carmelo3full.ContactosMain;
import com.example.carmelo3full.Importar;
import com.example.carmelo3full.R;
import com.example.carmelo3full.db.User;
import com.example.carmelo3full.viewmodel.ViewModel;

import java.util.List;

import static com.example.carmelo3full.MainActivity.lista;
import static com.example.carmelo3full.MainActivity.myContacts;
import static com.example.carmelo3full.MainActivity.myFriends;

public class ImportAdapter extends RecyclerView.Adapter<ImportAdapter.MyViewHolder> implements View.OnClickListener{

    private List<ContactosMain> contactosMains;
    private View.OnClickListener listener;
    private Context context;
    private Application application;

    public ImportAdapter(Context context, Application application){
        this.context=context;
        this.application=application;
    }

    public void setMainList(List<ContactosMain> lista){
        this.contactosMains = lista;
        notifyDataSetChanged();


    }

    public List getList(){
        return contactosMains;
    }


    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @NonNull
    @Override
    public ImportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.importrows, parent, false);
        view.setOnClickListener(this);
        return new ImportAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImportAdapter.MyViewHolder holder, int position) {
        ContactosMain contacts = contactosMains.get(position);
        holder.nombre.setText(contacts.getContactName());
        holder.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewModel vm = new ViewModel(application);

                if(vm.getName(holder.nombre.getText().toString())>0){
                    Toast.makeText(context, "Already saved", Toast.LENGTH_SHORT).show();
                }else{
                    for(ContactosMain i: myContacts){
                        if(i.getContactName().equalsIgnoreCase(holder.nombre.getText().toString())){
                            User user = new User();
                            user.name = i.getContactName();
                            String cleanPhone = i.getContactNumber().get(0).replaceAll("[^0-9]", "");
                            user.phoneNumber = cleanPhone;
                            user.birthDate=null;
                            vm.insert(user, context);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.contactosMains.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView nombre;
        Button save;

        public MyViewHolder(View view){
            super(view);
            save = view.findViewById(R.id.btSaveImport);
            nombre = view.findViewById(R.id.tvNombreImport);
        }
    }
}
