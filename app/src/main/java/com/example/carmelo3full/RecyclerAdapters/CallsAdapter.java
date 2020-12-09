package com.example.carmelo3full.RecyclerAdapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carmelo3full.Editar;
import com.example.carmelo3full.R;
import com.example.carmelo3full.db.User;
import com.example.carmelo3full.dbCalls.UserCalls;
import com.example.carmelo3full.viewmodel.ViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CallsAdapter extends RecyclerView.Adapter<CallsAdapter.MyViewHolder> implements View.OnClickListener {


    private List<UserCalls> contactosMains;
    private View.OnClickListener listener;
    private Context context;
    private Application application;

    public CallsAdapter(Context context, Application application){
        this.context=context;
        this.application=application;
    }

    public void setMainList(List<UserCalls> lista){
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
    public CallsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.callsrows, parent, false);
        view.setOnClickListener(this);
        return new CallsAdapter.MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CallsAdapter.MyViewHolder holder, int position) {

        UserCalls contacts = contactosMains.get(position);
        holder.id.setText(Integer.toString(contacts.uid));
        holder.idAmigo.setText(Integer.toString(contacts.idAmigo));

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        holder.fecha.setText(strDate);


    }

    @Override
    public int getItemCount() {
        return this.contactosMains.size();
    }


    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView id;
        TextView idAmigo;
        TextView fecha;

        public MyViewHolder(View view){
            super(view);
            id = view.findViewById(R.id.tvIdCalls);
            idAmigo = view.findViewById(R.id.tvIdAmigoCalls);
            fecha = view.findViewById(R.id.tvFechaCalls);
        }
    }

}
