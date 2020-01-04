package com.joabaler.vin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joabaler.vin.Entidades.csDetailStep;

import java.util.ArrayList;

public class DetalleDelPaso extends AppCompatActivity {

    //region Declaracion de Variables
    private LinearLayout btnAnterior, btnSiguiente;
    private TextView tvQueSeHizo, tvPorqueSeHizo, tvProcesoPaso, tvSiguiente, tvAnterior,tvNumeroPaso;
    private ImageView imgAnterior, imgSiguiente, btnHome;
    private ArrayList<csDetailStep> myListaPasos;
    private csDetailStep myStepSelected;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_del_paso);
        getSupportActionBar().hide(); //ocultar la barra

        //region Inicializacion de variables
        tvNumeroPaso = findViewById(R.id.tvNumeroPaso);
        btnAnterior = findViewById(R.id.btnPasoAnterior);
        btnSiguiente = findViewById(R.id.btnPasoSiguiente);
        tvQueSeHizo = findViewById(R.id.lblPageQue);
        tvPorqueSeHizo = findViewById(R.id.lblPagePorque);
        tvProcesoPaso = findViewById(R.id.lblPageProceso);
        imgAnterior = findViewById(R.id.imgAnterior);
        imgSiguiente = findViewById(R.id.imgSiguiente);
        tvAnterior = findViewById(R.id.lblPasoAnterior);
        tvSiguiente = findViewById(R.id.lblPasoSiguiente);
        btnHome = findViewById(R.id.btnHome);
        //endregion

        //region Eventos Clicks
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasoAnterior();
            }
        });
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasoSiguiente();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegresarMenuPrincipal();
            }
        });
        //endregion

        getParameter();
    }

    //region Metodos y Funciones
    private void PasoAnterior() {

        try {
            if (tvAnterior.getText().toString().equals("Cerrar")){
                finish();
            }else {
                String correlativo = myStepSelected.getCorrelativo()+"";
                if (correlativo.equals("2")){

                    CambiarAnterior(0);
                    myStepSelected = myListaPasos.get(0);
                }else {
                    CambiarAnterior(1);
                    myStepSelected = myListaPasos.get(myStepSelected.getCorrelativo() - 2);
                }
                SetDatos();
                if (myStepSelected.getCorrelativo() < myListaPasos.size()){
                    CambiarSiguiente(1);
                }
            }
        }catch(Exception  e) {
            Toast.makeText(getApplicationContext(),"Error: Desboramiento de memoria",Toast.LENGTH_LONG).show();
        }
    }

    private void PasoSiguiente() {
        try{
            if (tvSiguiente.getText().toString().equals("Cerrar")){
                finish();
            }else {
                String resp = (myListaPasos.size() - myStepSelected.getCorrelativo())+"";
                if (resp.equals("1")){

                    CambiarSiguiente(0);
                    myStepSelected = myListaPasos.get(myListaPasos.size() - 1);
                }else {
                    CambiarSiguiente(1);
                    myStepSelected = myListaPasos.get(myStepSelected.getCorrelativo());
                }
                SetDatos();
                if (myStepSelected.getCorrelativo() > 1){
                    CambiarAnterior(1);
                }
            }
        }catch(Exception  e) {
            Toast.makeText(getApplicationContext(),"Error: Desboramiento de memoria",Toast.LENGTH_LONG).show();
        }
    }

    private void getParameter() {

        //Obtencion del objeto como parametro
        Bundle _pObjeto = getIntent().getExtras();
        myListaPasos = new ArrayList<>();
        myStepSelected = new csDetailStep();
        if (_pObjeto!=null)
        {
            myListaPasos = (ArrayList<csDetailStep>) _pObjeto.getSerializable("KeyListaPasos");
            myStepSelected = (csDetailStep) _pObjeto.getSerializable("KeyPaso");
        }

        //set de paso seleccionado
        SetDatos();

        if (myStepSelected.getCorrelativo().equals(myListaPasos.size())){

            CambiarSiguiente(0);

        }else {
            if (myStepSelected.getCorrelativo().equals(myListaPasos.get(0).getCorrelativo())){

                CambiarAnterior(0);
            }
        }
    }

    private void CambiarAnterior(Integer mOriginal){

        if (mOriginal == 0){
            imgAnterior.setVisibility(View.GONE);
            tvAnterior.setText(R.string.RegresarPagina);
        }else {
            imgAnterior.setVisibility(View.VISIBLE);
            tvAnterior.setText(R.string.Anterior);
        }
    }

    private void CambiarSiguiente(Integer mOriginal){

        if (mOriginal == 0){
            imgSiguiente.setVisibility(View.GONE);
            tvSiguiente.setText(R.string.RegresarPagina);
        }else {
            imgSiguiente.setVisibility(View.VISIBLE);
            tvSiguiente.setText(R.string.Siguiente);
        }
    }

    private void SetDatos(){
        tvNumeroPaso.setText("Paso número "+myStepSelected.getCorrelativo());
        tvQueSeHizo.setText(myStepSelected.getQuesehizo());
        tvPorqueSeHizo.setText(myStepSelected.getPorquesehizo());
        tvProcesoPaso.setText(myStepSelected.getProcedimiento());
    }

    private void RegresarMenuPrincipal(){
        Toast.makeText(getApplicationContext(),"Click Home",Toast.LENGTH_SHORT).show();

        Intent intentPaso = new Intent();
        intentPaso.putExtra("pasoapaso",true);
        setResult(Activity.RESULT_OK,intentPaso);
        finish();
    }
    //endregion
}
