package com.joabaler.vin.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.joabaler.vin.Entidades.csDetailStep;
import com.joabaler.vin.R;

import java.util.ArrayList;

public class DetailSolutionAdapterRecycler extends RecyclerView.Adapter<DetailSolutionAdapterRecycler.ViewHolderListSteps> implements View.OnClickListener {
    private View.OnClickListener myClick;
    ArrayList<csDetailStep> _listadatos;


    public DetailSolutionAdapterRecycler(ArrayList<csDetailStep> _listadatos) {
        this._listadatos = _listadatos;
    }

    @NonNull
    @Override
    public ViewHolderListSteps onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_pasos,null,false);

        view.setOnClickListener(this); //
        return  new DetailSolutionAdapterRecycler.ViewHolderListSteps(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListSteps holder, int position) {
        holder.tvNumPaso.setText("Paso " + _listadatos.get(position).getCorrelativo());
        holder.tvQuesehizo.setText(_listadatos.get(position).getQuesehizo());
        holder.tvPorquesehizo.setText(_listadatos.get(position).getPorquesehizo());
        holder.tvProcedimiento.setText(_listadatos.get(position).getProcedimiento());
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

    public class ViewHolderListSteps extends RecyclerView.ViewHolder {

        private TextView tvNumPaso;
        private TextView tvQuesehizo;
        private TextView tvPorquesehizo;
        private TextView tvProcedimiento;

        public ViewHolderListSteps(@NonNull View itemView) {
            super(itemView);
            tvNumPaso = (TextView) itemView.findViewById(R.id.lblPasoNumero);
            tvQuesehizo = (TextView) itemView.findViewById(R.id.lblPasoQuesehizo);
            tvPorquesehizo = (TextView) itemView.findViewById(R.id.lblPasoPorquesehizo);
            tvProcedimiento = (TextView) itemView.findViewById(R.id.lblPasoProcedimiento);
        }
    }
}
