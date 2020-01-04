package com.joabaler.vin.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joabaler.vin.Entidades.csEjerciciosGuardados;
import com.joabaler.vin.R;

import java.util.ArrayList;

public class ExercisesAdapterRecycler extends RecyclerView.Adapter<ExercisesAdapterRecycler.ViewHolderEjercicios> implements View.OnClickListener{
    private View.OnClickListener myClick;
    ArrayList<csEjerciciosGuardados> _listadatos;

    public ExercisesAdapterRecycler(ArrayList<csEjerciciosGuardados> _listadatos) {
        this._listadatos = _listadatos;
    }

    @NonNull
    @Override
    public ViewHolderEjercicios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_ejercicios,null,false);

        view.setOnClickListener(this); //
        return  new ViewHolderEjercicios(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEjercicios holder, int position) {
        holder.NombreEjercicio.setText(_listadatos.get(position).getNombreEjercicio());
        holder.DescripcionEjercicio.setText(_listadatos.get(position).getDescripcionEjercicio());
        holder.IdEjercicio.setText(_listadatos.get(position).getIdEjercicio().toString());
        holder.IdTema.setText(_listadatos.get(position).getIdTema().toString());
        holder.DetalleDatos.setText(_listadatos.get(position).getDetalleDatos());
    }

    @Override
    public int getItemCount() {
        return _listadatos.size();
    }


    public void setOnClickListener(View.OnClickListener _click){
        this.myClick = _click;
    }

    @Override
    public void onClick(View view) {
        if (myClick != null){
            myClick.onClick(view);
        }
    }

    public class ViewHolderEjercicios extends RecyclerView.ViewHolder {

        private TextView IdEjercicio;
        private TextView NombreEjercicio;
        private TextView DescripcionEjercicio;
        private TextView IdTema;
        private TextView DetalleDatos;


        public ViewHolderEjercicios(@NonNull View itemView) {
            super(itemView);
            IdEjercicio = (TextView) itemView.findViewById(R.id.lblIdEjercicio);
            NombreEjercicio = (TextView) itemView.findViewById(R.id.lblEjercicioNombre);
            DescripcionEjercicio = (TextView) itemView.findViewById(R.id.lblEjercicioDescripcion);
            IdTema = (TextView) itemView.findViewById(R.id.lblIdTema);
            DetalleDatos = (TextView) itemView.findViewById(R.id.lblDetalleDatos);
        }
    }
}
