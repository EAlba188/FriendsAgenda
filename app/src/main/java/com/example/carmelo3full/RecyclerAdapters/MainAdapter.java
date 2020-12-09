package com.example.carmelo3full.RecyclerAdapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carmelo3full.ContactosMain;
import com.example.carmelo3full.Editar;
import com.example.carmelo3full.R;
import com.example.carmelo3full.db.User;
import com.example.carmelo3full.viewmodel.ViewModel;

import java.util.List;

import static com.example.carmelo3full.MainActivity.myFriends;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> implements View.OnClickListener {

    private List<User> contactosMains;
    private View.OnClickListener listener;
    private Context context;
    private Application application;

    public MainAdapter(Context context, Application application){
        this.context=context;
        this.application=application;
    }

    public void setMainList(List<User> lista){
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
    public MainAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainrows, parent, false);
        view.setOnClickListener(this);
        return new MainAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MyViewHolder holder, int position) {

        User contacts = contactosMains.get(position);
        holder.nombre.setText(contacts.name);
        holder.telefono.setText(contacts.phoneNumber);
        holder.numero.setText(Integer.toString(contacts.number));
        if(contacts.birthDate==null){
            holder.fecha.setText("Sin fecha");
        }else{
            holder.fecha.setText(contacts.birthDate);
        }


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Editar.class);
                intent.putExtra("name", contacts.name);
                intent.putExtra("phone", contacts.phoneNumber);
                if(contacts.birthDate==null){
                    intent.putExtra("bday", "Sin fecha");
                }else{
                    intent.putExtra("bday", contacts.birthDate);
                }
                context.startActivity(intent);

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewModel vm = new ViewModel(application);
                vm.delUser(contacts.name);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.contactosMains.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView nombre;
        TextView telefono;
        TextView fecha;
        TextView numero;
        Button edit;
        Button delete;

        public MyViewHolder(View view){
            super(view);
            nombre = view.findViewById(R.id.tvNombreMain);
            telefono = view.findViewById(R.id.tvTelefonoMain);
            fecha = view.findViewById(R.id.tvFechaMain);
            numero = view.findViewById(R.id.tvNumeroMain);
            edit = view.findViewById(R.id.btEditar);
            delete = view.findViewById(R.id.btBorrar);
        }
    }
}
