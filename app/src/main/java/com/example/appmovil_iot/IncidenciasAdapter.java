package com.example.appmovil_iot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class IncidenciasAdapter extends RecyclerView.Adapter<IncidenciasAdapter.ViewHolderIncidencias> {

    List<Incidencias> listaincidencias;

    public IncidenciasAdapter(List<Incidencias> incidencia){
        this.listaincidencias = incidencia;
    }

    @NonNull
    @Override
    public IncidenciasAdapter.ViewHolderIncidencias onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personalizado,parent,false);
        ViewHolderIncidencias holder = new ViewHolderIncidencias(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IncidenciasAdapter.ViewHolderIncidencias holder, int position){
        Incidencias incidencias = listaincidencias.get(position);
        holder.FECHA.setText(incidencias.getFecha());
        holder.HORA.setText(incidencias.getHora());
        holder.TIPO.setText(incidencias.getTipo());
        holder.MOVIMIENTO.setText(incidencias.getMovimiento());
    }

    @Override
    public int getItemCount(){
        return listaincidencias.size();
    }

    public class ViewHolderIncidencias extends RecyclerView.ViewHolder{
        TextView FECHA, HORA, TIPO, MOVIMIENTO;
        public ViewHolderIncidencias(@NonNull View itemView){
            super(itemView);

            FECHA = itemView.findViewById(R.id.txt_fecha);
            HORA = itemView.findViewById(R.id.txt_hora);
            TIPO = itemView.findViewById(R.id.txt_tipo);
            MOVIMIENTO = itemView.findViewById(R.id.txt_movimiento);

        }

    }

}
