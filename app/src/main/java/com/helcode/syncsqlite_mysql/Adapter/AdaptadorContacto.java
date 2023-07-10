package com.helcode.syncsqlite_mysql.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.helcode.syncsqlite_mysql.R;
import com.helcode.syncsqlite_mysql.model.Contactos;

import java.util.ArrayList;

public class AdaptadorContacto extends RecyclerView.Adapter<AdaptadorContacto.MyViewHolder> {

    ArrayList<Contactos> arrayList = new ArrayList<>();

    public AdaptadorContacto(ArrayList<Contactos> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdaptadorContacto.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorContacto.MyViewHolder holder, int position) {
        holder.Nombre.setText(arrayList.get(position).getNombre()+ " "+arrayList.get(position).getApellido());
        holder.Telefono.setText(arrayList.get(position).getTelefono());
        holder.avatar.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre,Telefono;
        ImageView avatar;
        public MyViewHolder( View itemView) {
            super(itemView);
            Nombre = itemView.findViewById(R.id.tvNombre);
            Telefono = itemView.findViewById(R.id.tvTelefono);
            avatar = itemView.findViewById(R.id.imageContacto);
        }
    }
}
