package com.joabaler.vin.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joabaler.vin.Entidades.csTemasContenido;
import com.joabaler.vin.R;

import java.util.ArrayList;

public class DetailCategoryAdapterRecycler extends RecyclerView.Adapter<DetailCategoryAdapterRecycler.ViewHolderTopics> implements View.OnClickListener {

    private View.OnClickListener myClick;
    ArrayList<csTemasContenido> _listadatos;

    public DetailCategoryAdapterRecycler(ArrayList<csTemasContenido> _lst) {
        this._listadatos = _lst;
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

    @NonNull
    @Override
    public ViewHolderTopics onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_contenido,null,false);
        view.setOnClickListener(this); //
        return  new DetailCategoryAdapterRecycler.ViewHolderTopics(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCategoryAdapterRecycler.ViewHolderTopics holder, int position) {
        holder.tvTema.setText(_listadatos.get(position).getNombreTema());
        holder.tvDescripcion.setText(_listadatos.get(position).getDetalleTema());
    }

    @Override
    public int getItemCount() {
        return _listadatos.size();
    }

    public class ViewHolderTopics extends RecyclerView.ViewHolder{

        //declaracion de elementos del item
        //private TextView IdEjercicio;
        private TextView tvTema;
        private TextView tvDescripcion;

        public ViewHolderTopics(@NonNull View itemView) {
            super(itemView);
            tvTema = (TextView) itemView.findViewById(R.id.lblTema);
            tvDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion);
        }
    }
}
