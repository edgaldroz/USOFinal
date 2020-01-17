package com.joabaler.vin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.joabaler.vin.AlgebraLibrary.ExercisesSolotionsDetail;
import com.joabaler.vin.Entidades.csDetailStep;
import com.joabaler.vin.Entidades.csMatriz;
import com.joabaler.vin.Entidades.csTemasContenido;
import com.joabaler.vin.Entidades.csVectorProyeccion;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class CapturaDeDatos extends AppCompatActivity {

    //region Declaracion de Variables

    //region Generales
    private String regularExpression = "^-?[0-9]+(\\.?[0-9]+)?";
    private Integer RequestCodeCaptura = 3;
    private LinearLayout MatrizGauss, VectorFuerzaEntrePuntos, VectorProyeccion,ProductoConVectores,MatrizInverza,OperacionPolarPotencia,
                        RaizNumerosPolares,PolarAExponencial,OpcRepreExponencial;
    private TextView RespuestaDirecta, lblCapturaNombreTema;
    private csTemasContenido myExerciseToSolve;
    public static ArrayList<Double> myArrayDatos = new ArrayList<>();
    public static ArrayList<Double> myArrayDatosDatos = new ArrayList<>();
    //endregion

    //region NumerosComplejos

    //region OperacionPolarPotencia
    private LinearLayout OperacionPolarPotenciabtnSolucion, OperacionPolarPotenciaEjemplo,
                         OperacionPolarPotenciaLinearNO, OperacionPolarPotenciaLinearSI;
    private TextView OperacionPolarPotenciaEjemploExpo,
            OperacionPolarPotenciaEjemploAngulo,OperacionPolarPotenciaEjemploNumero,
            OperacionPolarPotenciaTextNO,OperacionPolarPotenciaTextSI;
    private EditText OperacionPolarPotenciaExponente,OperacionPolarPotenciaAngulo,OperacionPolarPotenciaCoficiente;
    private ImageView OperacionPolarPotenciaImgRaiz;
    private Double OperacionPolarPotenciaEsRaiz = 1.0;
    //endregion

    //region RaizNumerosPolares
    private LinearLayout RaizNumerosPolaresLinearNO,RaizNumerosPolaresLinearSI,RaizNumerosPolaresbtnSolucion,RaizNumerosPolaresLinearIndiceX;
    private TextView RaizNumerosPolaresEjemploAngulo,
            RaizNumerosPolaresEjemploC,
            RaizNumerosPolaresEjemploX,
            RaizNumerosPolaresEjemploA,
            RaizNumerosPolaresTextNO,
            RaizNumerosPolaresTextSI;
    private EditText RaizNumerosPolaresAngulo,
            RaizNumerosPolaresCoeficienteX,
            RaizNumerosPolaresIndiceX,
            RaizNumerosPolaresIndiceA;
    private ImageView RaizNumerosPolaresEjemploImg;
    private Double RaizNumerosPolaresTipoEjercicio = 1.0,RaizNumerosPolaresDobleRaiz = 1.0;
    //endregion

    //region DeclaraciónVariables
    private LinearLayout
            PolarAExponencialLinearSI,
            PolarAExponencialLinearNO,
            PolarAExponencialLinearIndiceX,
            PolarAExponencialbtnSolucion;
    private TextView
            PolarAExponencialTextSI,
            PolarAExponencialTextNO,
            PolarAExponencialEjemploX,
            PolarAExponencialEjemploC,
            PolarAExponencialEjemploAngulo;
    private EditText
            PolarAExponencialIndiceX,
            PolarAExponencialCoeficienteC,
            PolarAExponencialAngulo;
    private ImageView PolarAExponencialEjemploImg;
    private Double PolarAExponencialEsRaiz = 1.0;
    //endregion

    //region OpcRepreExponencial
    private LinearLayout
            OpcRepreExponencialLinearMulti,
            OpcRepreExponencialLinearDivision,
            OpcRepreExponencialLinearExpo,
            OpcRepreExponencialLinearRaiz,
            OpcRepreExponencialLinearMulti_Division,
            OpcRepreExponencialLinear_A_B,
            OpcRepreExponencialLinear_A_Raiz,
            OpcRepreExponencialLinear_Raiz_B,
            OpcRepreExponencialLinear_Raiz_Raiz,
            OpcRepreExponencialLinearExpo_Raiz,
            OpcRepreExponencialLinearSI,
            OpcRepreExponencialLinearNO,
            OpcRepreExponencialEditMulti_Division,
            OpcRepreExponencialLinearIndiceA,
            OpcRepreExponencialLinearCoeficienteA,
            OpcRepreExponencialLinearBaseA,
            OpcRepreExponencialLinearExpoA,
            OpcRepreExponencialLinearIndiceB,
            OpcRepreExponencialLinearCoeficienteB,
            OpcRepreExponencialLinearBaseB,
            OpcRepreExponencialLinearExpoB,
            OpcRepreExponencialEditRaizExpo,
            OpcRepreExponencialbtnSolucion,

            OpcRepreExponencialLinearPotIndiceA,
            OpcRepreExponencialLinearPotCoeficienteC,
            OpcRepreExponencialLinearPotBase,
            OpcRepreExponencialLinearPotExponente,
            OpcRepreExponencialLinearPotValorN,

            OpcRepreExponencialLinearEjemploPototencia,
            OpcRepreExponencialLinearEjemploRaiz;

    private TextView
            OpcRepreExponencialTextMulti,
            OpcRepreExponencialTextDivision,
            OpcRepreExponencialTextExpo,
            OpcRepreExponencialTextRaiz,
            OpcRepreExponencialText_A_B,
            OpcRepreExponencialText_A_Raiz,
            OpcRepreExponencialText_Raiz_B,
            OpcRepreExponencialText_Raiz_Raiz,
            OpcRepreExponencialTextSI,
            OpcRepreExponencialTextNO,
            OpcRepreExponencialEjemploCoefiA,
            OpcRepreExponencialEjemploIndA,
            OpcRepreExponencialEjemploBaseA,
            OpcRepreExponencialEjemploeExpoA,
            OpcRepreExponencialEjemploCoefiB,
            OpcRepreExponencialEjemploInB,
            OpcRepreExponencialEjemploBaseB,
            OpcRepreExponencialEjemploeExpoB,

            OpcRepreExponencialTextEjemploPotCoeficiente,
            OpcRepreExponencialTextEjemploPotIndice,
            OpcRepreExponencialTextEjemploPotR,
            OpcRepreExponencialTextEjemploPotE,
            OpcRepreExponencialTextEjemploPotN,

            OpcRepreExponencialTextPotRaizVar,
            OpcRepreExponencialEjemploRaizIndX,
            OpcRepreExponencialEjemploRaizIndA,
            OpcRepreExponencialEjemploRaizC,
            OpcRepreExponencialEjemploRaizN;


    private EditText
            OpcRepreExponencialEditIndiceA,
            OpcRepreExponencialEditCoeficienteA,
            OpcRepreExponencialEditBaseA,
            OpcRepreExponencialEditExpoA,
            OpcRepreExponencialEditIndiceB,
            OpcRepreExponencialEditCoeficienteB,
            OpcRepreExponencialEditBaseB,
            OpcRepreExponencialEditExpoB,

            OpcRepreExponencialEditPotIndice,
            OpcRepreExponencialEditPotC,
            OpcRepreExponencialEditPotR,
            OpcRepreExponencialEditPotE,
            OpcRepreExponencialEditPotN;
    private ImageView OpcRepreExponencialEjemploImgA,
                      OpcRepreExponencialEjemploImgB,
                      OpcRepreExponencialTextEjemploPotImg,
                      OpcRepreExponencialEjemploRaizImg;
    private Double OpcRepreExponencialTipoEjercicio = 1.0,OpcRepreExponencialEsRaizA = 0.0,OpcRepreExponencialEsRaizB = 0.0;
    //endregion

    //endregion

    //region Matrices

    //region MatrizGauss
    private Integer GaussTamano = 4;
    public static ArrayList<csMatriz> myMatrizGaussDatos = new ArrayList<>();
    private ArrayList<csMatriz> myMatrizGauss = new ArrayList<>();
    private EditText Gauss11, Gauss12, Gauss13, Gauss14, Gauss1Igual, Gauss21, Gauss22, Gauss23, Gauss24, Gauss2Igual,
            Gauss31, Gauss32, Gauss33, Gauss34, Gauss3Igual, Gauss41, Gauss42, Gauss43, Gauss44, Gauss4Igual;
    private LinearLayout btnSolucionGauss, LinearGaussFila3, LinearGaussFila4, LinearGauss2x2, LinearGauss3x3, LinearGauss4x4;
    private TextView lblGaussW, lblGaussz, tvGauss2x2, tvGauss3x3, tvGauss4x4;
    //endregion

    //region Matriz Inverza
    private Double MatrizInverzaTamano = 1.0;
    private EditText MatrizInverza11, MatrizInverza12, MatrizInverza13, MatrizInverza21, MatrizInverza22, MatrizInverza23,
            MatrizInverza31, MatrizInverza32, MatrizInverza33;
    private LinearLayout MatrizInverzabtnSolucion, MatrizInverzaLinear2x2, MatrizInverzaLinear3x3, MatrizInverzaFila3;
    private TextView MatrizInverzaText2x2, MatrizInverzaText3x3;
    //endregion

    //region Solucion Ecuaciones liniales
    private LinearLayout LinearSolucionEcuacionLineales,btnMGauss,btnMInversa;
    private TextView tvMGauss,tvMInversa;
    //endregion

    //endregion

    //region Vectores

    //region VectoresFuerzaResultante
    private EditText FResultX1, FResultX2, FResultY1, FResultY2, FResultZ1, FResultZ2, FResultFuerza;
    private LinearLayout FResultbtnSolucion;
    //endregion

    //region VectoresResultante
    private ArrayList<csVectorProyeccion> myVectorProyeccion = new ArrayList<>();
    public static ArrayList<csVectorProyeccion> myVectorProyeccionDatos = new ArrayList<>();
    private EditText VectorProyecciontxtAngulo, VectorProyecciontxtFuerza;
    private LinearLayout VectorProyeccionbtnSolucion, VectorProyeccionbtnAgregar,
            VProyeccionLinearCuadranteNinguno,VProyeccionLinearCuadranteI,VProyeccionLinearCuadranteII,
            VProyeccionLinearCuadranteIII,VProyeccionLinearCuadranteIV,VProyeccionLinearDireccionNinguno,
            VProyeccionLinearDireccionAsc,VProyeccionLinearDireccionDesc,VProyeccionLinearApuntaIzq,
            VProyeccionLinearApuntaDer,VProyeccionLinearApuntaArriba,VProyeccionLinearApuntaAbajo,
            VProyeccionLinearRespectoX,VProyeccionLinearRespectoY;
    private TextView VProyeccionTextCuadranteNinguno, VProyeccionTextCuadranteI, VProyeccionTextCuadranteII,
            VProyeccionTextCuadranteIII, VProyeccionTextCuadranteIV, VProyeccionTextDireccionNinguno,
            VProyeccionTextDireccionAsc, VProyeccionTextDireccionDesc,VProyeccionTextApuntaIzq,
            VProyeccionTextApuntaDer,VProyeccionTextApuntaArriba,VProyeccionTextApuntaAbajo,
            VProyeccionTextRespectoX,VProyeccionTextRespectoY,VectorProyeccionTextFuerza,VectorProyeccionTextAngulo;
    private Integer VProyeccionCuadrante = 0;
    private String VProyeccionDireccion = "",VProyeccionApuntaA = "",VProyeccionangRespectoA = "";
    //endregion

    //region ProductoConVectores
    private EditText ProductoConVectoresI1, ProductoConVectoresI2,ProductoConVectoresI3,
                     ProductoConVectoresJ1, ProductoConVectoresJ2,ProductoConVectoresJ3,
                     ProductoConVectoresK1, ProductoConVectoresK2,ProductoConVectoresK3;
    private LinearLayout ProductoConVectoresLinearCruz,ProductoConVectoresLinearPunto,ProductoConVectoresLinearMixto,
            ProductoConVectoresLinearVectorC,ProductoConVectoresbtnSolucion,ProductoConVectoresTipoCruz,
            ProductoConVectoresLinearCruzMatriz,ProductoConVectoresLinearCruzEcuaciones;
    private TextView ProductoConVectoresTextCruz,ProductoConVectoresTextPunto,ProductoConVectoresTextMixto,
            ProductoConVectoresTextCruzMatriz,ProductoConVectoresTextCruzEcuaciones;
    private Double ProductoConVectoresTipoEjercicio = 1.0,ProductoConVectoresTipoCruzTipo = 1.0;
    //endregion
    //endregion

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_de_datos);
        getSupportActionBar().hide(); //ocultar la barra

        btnSolucionGauss = findViewById(R.id.btnSolucionGauss);
        RespuestaDirecta = findViewById(R.id.lblRespuestaDirecta);
        lblCapturaNombreTema = findViewById(R.id.lblCapturaNombreTema);

        //region Linear para validar cual vamos a mostrar
        LinearSolucionEcuacionLineales = findViewById(R.id.LinearSolucionEcuacionLineales);
        MatrizGauss = findViewById(R.id.MatrizGauss);
        VectorFuerzaEntrePuntos = findViewById(R.id.VectorFuerzaEntrePuntos);
        VectorProyeccion = findViewById(R.id.VectorProyeccion);
        ProductoConVectores = findViewById(R.id.ProductoConVectores);
        MatrizInverza = findViewById(R.id.MatrizInverza);
        OperacionPolarPotencia = findViewById(R.id.OperacionPolarPotencia);
        RaizNumerosPolares = findViewById(R.id.RaizNumerosPolares);
        PolarAExponencial = findViewById(R.id.PolarAExponencial);
        OpcRepreExponencial = findViewById(R.id.OpcRepreExponencial);
        //endregion

        getParameter();
        InicializarMatrizGauss();
        InicializarFuerzaResultante();
        InicializarVectorFuProyeccion();
        InicializarProductoConVectores();
        SolucionEcuacionesLiniales();
        InicializarMatrizInverza();
        InicializarOperacionPolarPotencia();
        InicializarRaizNumerosPolares();
        InicializarPolarAExponencialNC();
        InicializarOpcRepreExponencial();
    }

    //region Metodos Generales
    private void getParameter() {

        //Obtencion del objeto como parametro
        Bundle _pObjeto = getIntent().getExtras();
        myExerciseToSolve = new csTemasContenido();
        if (_pObjeto != null) {
            myExerciseToSolve = (csTemasContenido) _pObjeto.getSerializable("KeyExercise");
            lblCapturaNombreTema.setText(myExerciseToSolve.getNombreTema());
        }
        OpcRepreExponencial.setVisibility(myExerciseToSolve.getIdTema().equals(101) ? View.VISIBLE : View.GONE);
        RaizNumerosPolares.setVisibility(myExerciseToSolve.getIdTema().equals(102) ? View.VISIBLE : View.GONE);
        OperacionPolarPotencia.setVisibility(myExerciseToSolve.getIdTema().equals(103) ? View.VISIBLE : View.GONE);
        PolarAExponencial.setVisibility(myExerciseToSolve.getIdTema().equals(104) ? View.VISIBLE : View.GONE);


        if (myExerciseToSolve.getIdTema().equals(201)){
            MatrizGauss.setVisibility(myExerciseToSolve.getIdTema().equals(201) ? View.VISIBLE : View.GONE);
            LinearSolucionEcuacionLineales.setVisibility(View.GONE);
        }else if (myExerciseToSolve.getIdTema().equals(204)){
            LinearSolucionEcuacionLineales.setVisibility(myExerciseToSolve.getIdTema().equals(204) ? View.VISIBLE : View.GONE);
            MatrizGauss.setVisibility(View.VISIBLE);
        }else if (myExerciseToSolve.getIdTema().equals(202)){
            MatrizInverza.setVisibility(myExerciseToSolve.getIdTema().equals(202) ? View.VISIBLE : View.GONE);
            LinearSolucionEcuacionLineales.setVisibility(View.GONE);
        }

        VectorFuerzaEntrePuntos.setVisibility(myExerciseToSolve.getIdTema().equals(301) ? View.VISIBLE : View.GONE);
        VectorProyeccion.setVisibility(myExerciseToSolve.getIdTema().equals(302) ? View.VISIBLE : View.GONE);
        ProductoConVectores.setVisibility(myExerciseToSolve.getIdTema().equals(303) ? View.VISIBLE : View.GONE);
    }

    private void openActivity() {
        Intent _OpenPaso = new Intent(getApplicationContext(), SolucionDetallada.class);
        Bundle _bundle = new Bundle();
        _bundle.putSerializable("KeyListaPasos", ExercisesSolotionsDetail.myListSteps);
        _bundle.putSerializable("KeyExercise", myExerciseToSolve);
        _bundle.putSerializable("KeybtnSave", 1);
        _OpenPaso.putExtras(_bundle);
        startActivityForResult(_OpenPaso, RequestCodeCaptura);
        ExercisesSolotionsDetail.myListSteps.clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodeCaptura) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("listasolucion")) {
                    Intent intentdatos = new Intent();
                    intentdatos.putExtra("categoria", true);
                    setResult(Activity.RESULT_OK, intentdatos);
                    finish();
                }
            }
        }
    }

    private void OpcionSeleccionada(LinearLayout _layoutselected, TextView _textselected){
        _layoutselected.setBackgroundResource(R.drawable.item_opc_seleccionado);
        _textselected.setTextColor(Color.parseColor("#008080"));
    }
    private void OpcionOriginal(LinearLayout _layoutselected, TextView _textselected){
        _layoutselected.setBackgroundResource(R.drawable.item_opc);
        _textselected.setTextColor(Color.parseColor("#465e5e"));
    }
    //endregion


    //region NUMEROS COMPLEJOS ===== 1

    //region Solucion Paso a paso

    //region OperacionPolarPotencia
    private void InicializarOperacionPolarPotencia(){
        //region Inicializacion
        OperacionPolarPotenciabtnSolucion = findViewById(R.id.OperacionPolarPotenciabtnSolucion);
        OperacionPolarPotenciaEjemplo = findViewById(R.id.OperacionPolarPotenciaEjemplo);
        OperacionPolarPotenciaLinearNO = findViewById(R.id.OperacionPolarPotenciaLinearNO);
        OperacionPolarPotenciaLinearSI = findViewById(R.id.OperacionPolarPotenciaLinearSI);

        //tv
        OperacionPolarPotenciaEjemploExpo = findViewById(R.id.OperacionPolarPotenciaEjemploExpo);
        OperacionPolarPotenciaEjemploAngulo = findViewById(R.id.OperacionPolarPotenciaEjemploAngulo);
        OperacionPolarPotenciaEjemploNumero = findViewById(R.id.OperacionPolarPotenciaEjemploNumero);
        OperacionPolarPotenciaTextNO = findViewById(R.id.OperacionPolarPotenciaTextNO);
        OperacionPolarPotenciaTextSI = findViewById(R.id.OperacionPolarPotenciaTextSI);

        OperacionPolarPotenciaImgRaiz = findViewById(R.id.OperacionPolarPotenciaImgRaiz);

        //ed
        OperacionPolarPotenciaExponente = findViewById(R.id.OperacionPolarPotenciaExponente);
        OperacionPolarPotenciaAngulo = findViewById(R.id.OperacionPolarPotenciaAngulo);
        OperacionPolarPotenciaCoficiente = findViewById(R.id.OperacionPolarPotenciaCoficiente);
        //endregion

        //region btnSolucion
        OperacionPolarPotenciabtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (OperacionPolarPotenciaValidar()){
                    myArrayDatos.add(Double.parseDouble(OperacionPolarPotenciaCoficiente.getText().toString()));
                    myArrayDatos.add(OperacionPolarPotenciaEsRaiz);
                    myArrayDatos.add(Double.parseDouble(OperacionPolarPotenciaAngulo.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(OperacionPolarPotenciaExponente.getText().toString()));
                    String _result = ExercisesSolotionsDetail.MenuMetodos(myExerciseToSolve.getIdTema(),myArrayDatos);
                    openActivity();
                    //myArrayDatos.clear();
                }
            }
        });
        //endregion
        //region Efecto visual
        OperacionPolarPotenciaLinearNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OperacionPolarPotenciaLinearNO,OperacionPolarPotenciaTextNO);
                OpcionOriginal(OperacionPolarPotenciaLinearSI,OperacionPolarPotenciaTextSI);
                OperacionPolarPotenciaEsRaiz = 0.0;
                OperacionPolarPotenciaImgRaiz.setVisibility(View.GONE);
            }
        });
        OperacionPolarPotenciaLinearSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OperacionPolarPotenciaLinearSI,OperacionPolarPotenciaTextSI);
                OpcionOriginal(OperacionPolarPotenciaLinearNO,OperacionPolarPotenciaTextNO);
                OperacionPolarPotenciaEsRaiz = 1.0;
                OperacionPolarPotenciaImgRaiz.setVisibility(View.VISIBLE);
            }
        });
        //endregion
        //region Armando visualmente el ejercicio
        OperacionPolarPotenciaExponente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OperacionPolarPotenciaEjemplo.setVisibility(View.VISIBLE);
                OperacionPolarPotenciaEjemploExpo.setText(OperacionPolarPotenciaExponente.getText().toString());
            }
        });
        OperacionPolarPotenciaAngulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OperacionPolarPotenciaEjemplo.setVisibility(View.VISIBLE);
                OperacionPolarPotenciaEjemploAngulo.setText("  "+OperacionPolarPotenciaAngulo.getText().toString()+"°  ");
            }
        });
        OperacionPolarPotenciaCoficiente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OperacionPolarPotenciaEjemplo.setVisibility(View.VISIBLE);
                OperacionPolarPotenciaEjemploNumero.setText("  "+OperacionPolarPotenciaCoficiente.getText().toString());
            }
        });
        //endregion

    }
    private boolean OperacionPolarPotenciaValidar(){
        boolean _result = true;
        String _error = "Ingresa el valor";
        if (OperacionPolarPotenciaExponente.getText().toString().isEmpty()) {
            OperacionPolarPotenciaExponente.setError(_error);
            _result = false;
        }
        if (OperacionPolarPotenciaAngulo.getText().toString().isEmpty()) {
            OperacionPolarPotenciaAngulo.setError(_error);
            _result = false;
        }
        if (OperacionPolarPotenciaCoficiente.getText().toString().isEmpty()) {
            OperacionPolarPotenciaCoficiente.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region RaizNumerosPolares
    private void InicializarRaizNumerosPolares(){

        //region Inicializacion
        //linear
        RaizNumerosPolaresLinearNO = findViewById(R.id.RaizNumerosPolaresLinearNO);
        RaizNumerosPolaresLinearSI = findViewById(R.id.RaizNumerosPolaresLinearSI);
        RaizNumerosPolaresbtnSolucion = findViewById(R.id.RaizNumerosPolaresbtnSolucion);
        RaizNumerosPolaresLinearIndiceX = findViewById(R.id.RaizNumerosPolaresLinearIndiceX);
        //text
        RaizNumerosPolaresEjemploAngulo = findViewById(R.id.RaizNumerosPolaresEjemploAngulo);
        RaizNumerosPolaresEjemploC = findViewById(R.id.RaizNumerosPolaresEjemploC);
        RaizNumerosPolaresEjemploX = findViewById(R.id.RaizNumerosPolaresEjemploX);
        RaizNumerosPolaresEjemploA = findViewById(R.id.RaizNumerosPolaresEjemploA);
        RaizNumerosPolaresTextNO = findViewById(R.id.RaizNumerosPolaresTextNO);
        RaizNumerosPolaresTextSI = findViewById(R.id.RaizNumerosPolaresTextSI);
        //edit
        RaizNumerosPolaresAngulo = findViewById(R.id.RaizNumerosPolaresAngulo);
        RaizNumerosPolaresCoeficienteX = findViewById(R.id.RaizNumerosPolaresCoeficienteX);
        RaizNumerosPolaresIndiceX = findViewById(R.id.RaizNumerosPolaresIndiceX);
        RaizNumerosPolaresIndiceA = findViewById(R.id.RaizNumerosPolaresIndiceA);
        //img
        RaizNumerosPolaresEjemploImg = findViewById(R.id.RaizNumerosPolaresEjemploImg);
        //endregion

        //region btnSolucion
        RaizNumerosPolaresbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RaizNumerosPolaresValidar()){
                   // myArrayDatos.clear();
                    myArrayDatos.add(RaizNumerosPolaresTipoEjercicio);
                    myArrayDatos.add(Double.parseDouble(RaizNumerosPolaresCoeficienteX.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(RaizNumerosPolaresIndiceA.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(RaizNumerosPolaresAngulo.getText().toString()));
                    myArrayDatos.add(RaizNumerosPolaresDobleRaiz);
                    myArrayDatos.add(Double.parseDouble(RaizNumerosPolaresIndiceX.getText().toString()));
                    String _result = ExercisesSolotionsDetail.MenuMetodos(myExerciseToSolve.getIdTema(),myArrayDatos);
                    openActivity();
                  //  myArrayDatos.clear();
                }
            }
        });
        //endregion

        //region Efecto visual
        RaizNumerosPolaresLinearNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(RaizNumerosPolaresLinearNO,RaizNumerosPolaresTextNO);
                OpcionOriginal(RaizNumerosPolaresLinearSI,RaizNumerosPolaresTextSI);
                RaizNumerosPolaresDobleRaiz = 0.0;
                RaizNumerosPolaresEjemploImg.setImageResource(RaizNumerosPolaresDobleRaiz.intValue() == 0?R.drawable.ic_raiz_2ble_simple:R.drawable.ic_raiz_2ble);
                RaizNumerosPolaresEjemploX.setVisibility(View.GONE);
                RaizNumerosPolaresLinearIndiceX.setVisibility(View.GONE);
            }
        });
        RaizNumerosPolaresLinearSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(RaizNumerosPolaresLinearSI,RaizNumerosPolaresTextSI);
                OpcionOriginal(RaizNumerosPolaresLinearNO,RaizNumerosPolaresTextNO);
                RaizNumerosPolaresDobleRaiz = 1.0;
                RaizNumerosPolaresEjemploImg.setImageResource(RaizNumerosPolaresDobleRaiz.intValue() == 1? R.drawable.ic_raiz_2ble:R.drawable.ic_raiz_2ble_simple);
                RaizNumerosPolaresEjemploX.setVisibility(View.VISIBLE);
                RaizNumerosPolaresLinearIndiceX.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        //region Visual - Creacion de la estructura del ejrcicio
        RaizNumerosPolaresAngulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RaizNumerosPolaresEjemploAngulo.setText(RaizNumerosPolaresAngulo.getText().toString()+"°");
            }
        });
        RaizNumerosPolaresCoeficienteX.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RaizNumerosPolaresEjemploC.setText(RaizNumerosPolaresCoeficienteX.getText().toString());
            }
        });
        RaizNumerosPolaresIndiceX.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RaizNumerosPolaresEjemploX.setText(RaizNumerosPolaresIndiceX.getText().toString());
            }
        });
        RaizNumerosPolaresIndiceA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                RaizNumerosPolaresEjemploA.setText(RaizNumerosPolaresIndiceA.getText().toString());
            }
        });
        //endregion
    }
    private boolean RaizNumerosPolaresValidar(){
        boolean _result = true;
        String _error = "Ingresa el valor";
        if (RaizNumerosPolaresAngulo.getText().toString().isEmpty()) {
            RaizNumerosPolaresAngulo.setError(_error);
            _result = false;
        }
        if (RaizNumerosPolaresCoeficienteX.getText().toString().isEmpty()) {
            RaizNumerosPolaresCoeficienteX.setError(_error);
            _result = false;
        }
        if (RaizNumerosPolaresIndiceA.getText().toString().isEmpty()) {
            RaizNumerosPolaresIndiceA.setError(_error);
            _result = false;
        }
        if (RaizNumerosPolaresDobleRaiz.intValue() == 1){
            if (RaizNumerosPolaresIndiceX.getText().toString().isEmpty()) {
                RaizNumerosPolaresIndiceX.setError(_error);
                _result = false;
            }
        }
        return _result;
    }
    //endregion

    //region PolarAExponencialNC
    private void InicializarPolarAExponencialNC(){

        //region Inicializacion de variables
        PolarAExponencialLinearSI = findViewById(R.id.PolarAExponencialLinearSI);
        PolarAExponencialLinearNO = findViewById(R.id.PolarAExponencialLinearNO);
        PolarAExponencialLinearIndiceX = findViewById(R.id.PolarAExponencialLinearIndiceX);
        PolarAExponencialbtnSolucion = findViewById(R.id.PolarAExponencialbtnSolucion);

        PolarAExponencialTextSI = findViewById(R.id.PolarAExponencialTextSI);
        PolarAExponencialTextNO  = findViewById(R.id.PolarAExponencialTextNO);
        PolarAExponencialEjemploX  = findViewById(R.id.PolarAExponencialEjemploX);
        PolarAExponencialEjemploC  = findViewById(R.id.PolarAExponencialEjemploC);
        PolarAExponencialEjemploAngulo  = findViewById(R.id.PolarAExponencialEjemploAngulo);

        PolarAExponencialIndiceX = findViewById(R.id.PolarAExponencialIndiceX);
        PolarAExponencialCoeficienteC = findViewById(R.id.PolarAExponencialCoeficienteC);
        PolarAExponencialAngulo = findViewById(R.id.PolarAExponencialAngulo);

        PolarAExponencialEjemploImg = findViewById(R.id.PolarAExponencialEjemploImg);
        //endregion

        //region btnSolucion
        PolarAExponencialbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PolarAExponencialValidar()){
                //    myArrayDatos.clear();
                    myArrayDatos.add(PolarAExponencialEsRaiz);
                    myArrayDatos.add(Double.parseDouble(PolarAExponencialCoeficienteC.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(PolarAExponencialAngulo.getText().toString()));
                    if (PolarAExponencialEsRaiz.intValue() == 1){
                        myArrayDatos.add(Double.parseDouble(PolarAExponencialIndiceX.getText().toString()));
                    }
                    String _result = ExercisesSolotionsDetail.MenuMetodos(myExerciseToSolve.getIdTema(),myArrayDatos);
                    openActivity();
                  //  myArrayDatos.clear();
                }
            }
        });
        //endregion

        //region Efectos visuales
        PolarAExponencialLinearSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(PolarAExponencialLinearSI,PolarAExponencialTextSI);
                OpcionOriginal(PolarAExponencialLinearNO,PolarAExponencialTextNO);
                PolarAExponencialEsRaiz = 1.0;
                PolarAExponencialLinearIndiceX.setVisibility(View.VISIBLE);
                PolarAExponencialEjemploImg.setVisibility(View.VISIBLE);
                PolarAExponencialEjemploX.setVisibility(View.VISIBLE);
            }
        });
        PolarAExponencialLinearNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(PolarAExponencialLinearSI,PolarAExponencialTextSI);
                OpcionOriginal(PolarAExponencialLinearNO,PolarAExponencialTextNO);
                PolarAExponencialEsRaiz = 0.0;
                PolarAExponencialLinearIndiceX.setVisibility(View.GONE);
                PolarAExponencialEjemploImg.setVisibility(View.GONE);
                PolarAExponencialEjemploX.setVisibility(View.GONE);
            }
        });
        //endregion

        //region Visual - Creacion del ejercicio
        PolarAExponencialIndiceX.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                PolarAExponencialEjemploX.setText(PolarAExponencialIndiceX.getText().toString());
            }
        });
        PolarAExponencialCoeficienteC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                PolarAExponencialEjemploC.setText(PolarAExponencialCoeficienteC.getText().toString());
            }
        });
        PolarAExponencialAngulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                PolarAExponencialEjemploAngulo.setText(PolarAExponencialAngulo.getText().toString());
            }
        });
        //endregion
    }
    private boolean PolarAExponencialValidar(){
        boolean _result = true;
        String _error = "Ingresa el valor";

        /*Pattern pattern = Pattern.compile(regularExpression);
        Matcher m = pattern.matcher(PolarAExponencialCoeficienteC.getText().toString());
        Toast.makeText(this, m.matches() ? "Ës numero valido" : "Que es esa mierda",Toast.LENGTH_LONG).show();*/

        if (PolarAExponencialCoeficienteC.getText().toString().isEmpty()) {
            PolarAExponencialCoeficienteC.setError(_error);
            _result = false;
        }
        if (PolarAExponencialAngulo.getText().toString().isEmpty()) {
            PolarAExponencialAngulo.setError(_error);
            _result = false;
        }
        if (PolarAExponencialEsRaiz.intValue() == 1){
            if (PolarAExponencialIndiceX.getText().toString().isEmpty()) {
                PolarAExponencialIndiceX.setError(_error);
                _result = false;
            }
        }
        return _result;
    }
    //endregion

    //region OpcRepreExponencial
    private void InicializarOpcRepreExponencial() {

        //region inicializacion
        //linear
        OpcRepreExponencialLinearMulti = findViewById(R.id.OpcRepreExponencialLinearMulti);
        OpcRepreExponencialLinearDivision = findViewById(R.id.OpcRepreExponencialLinearDivision);
        OpcRepreExponencialLinearExpo = findViewById(R.id.OpcRepreExponencialLinearExpo);
        OpcRepreExponencialLinearRaiz = findViewById(R.id.OpcRepreExponencialLinearRaiz);
        OpcRepreExponencialLinearMulti_Division = findViewById(R.id.OpcRepreExponencialLinearMulti_Division);
        OpcRepreExponencialLinear_A_B = findViewById(R.id.OpcRepreExponencialLinear_A_B);
        OpcRepreExponencialLinear_A_Raiz = findViewById(R.id.OpcRepreExponencialLinear_A_Raiz);
        OpcRepreExponencialLinear_Raiz_B = findViewById(R.id.OpcRepreExponencialLinear_Raiz_B);
        OpcRepreExponencialLinear_Raiz_Raiz = findViewById(R.id.OpcRepreExponencialLinear_Raiz_Raiz);
        OpcRepreExponencialLinearExpo_Raiz = findViewById(R.id.OpcRepreExponencialLinearExpo_Raiz);
        OpcRepreExponencialLinearSI = findViewById(R.id.OpcRepreExponencialLinearSI);
        OpcRepreExponencialLinearNO = findViewById(R.id.OpcRepreExponencialLinearNO);
        OpcRepreExponencialEditMulti_Division = findViewById(R.id.OpcRepreExponencialEditMulti_Division);
        OpcRepreExponencialLinearIndiceA = findViewById(R.id.OpcRepreExponencialLinearIndiceA);
        OpcRepreExponencialLinearCoeficienteA = findViewById(R.id.OpcRepreExponencialLinearCoeficienteA);
        OpcRepreExponencialLinearBaseA = findViewById(R.id.OpcRepreExponencialLinearBaseA);
        OpcRepreExponencialLinearExpoA = findViewById(R.id.OpcRepreExponencialLinearExpoA);
        OpcRepreExponencialLinearIndiceB = findViewById(R.id.OpcRepreExponencialLinearIndiceB);
        OpcRepreExponencialLinearCoeficienteB = findViewById(R.id.OpcRepreExponencialLinearCoeficienteB);
        OpcRepreExponencialLinearBaseB = findViewById(R.id.OpcRepreExponencialLinearBaseB);
        OpcRepreExponencialLinearExpoB = findViewById(R.id.OpcRepreExponencialLinearExpoB);
        OpcRepreExponencialbtnSolucion = findViewById(R.id.OpcRepreExponencialbtnSolucion);
        OpcRepreExponencialEditRaizExpo = findViewById(R.id.OpcRepreExponencialEditRaizExpo);

        OpcRepreExponencialLinearPotIndiceA = findViewById(R.id.OpcRepreExponencialLinearPotIndiceA);
        OpcRepreExponencialLinearPotCoeficienteC = findViewById(R.id.OpcRepreExponencialLinearPotCoeficienteC);
        OpcRepreExponencialLinearPotBase = findViewById(R.id.OpcRepreExponencialLinearPotBase);
        OpcRepreExponencialLinearPotExponente = findViewById(R.id.OpcRepreExponencialLinearPotExponente);
        OpcRepreExponencialLinearPotValorN = findViewById(R.id.OpcRepreExponencialLinearPotValorN);

        OpcRepreExponencialLinearEjemploPototencia = findViewById(R.id.OpcRepreExponencialLinearEjemploPototencia);
        OpcRepreExponencialLinearEjemploRaiz = findViewById(R.id.OpcRepreExponencialLinearEjemploRaiz);

        //text
        OpcRepreExponencialTextMulti = findViewById(R.id.OpcRepreExponencialTextMulti);
        OpcRepreExponencialTextDivision = findViewById(R.id.OpcRepreExponencialTextDivision);
        OpcRepreExponencialTextExpo = findViewById(R.id.OpcRepreExponencialTextExpo);
        OpcRepreExponencialTextRaiz = findViewById(R.id.OpcRepreExponencialTextRaiz);
        OpcRepreExponencialText_A_B = findViewById(R.id.OpcRepreExponencialText_A_B);
        OpcRepreExponencialText_A_Raiz = findViewById(R.id.OpcRepreExponencialText_A_Raiz);
        OpcRepreExponencialText_Raiz_B = findViewById(R.id.OpcRepreExponencialText_Raiz_B);
        OpcRepreExponencialText_Raiz_Raiz = findViewById(R.id.OpcRepreExponencialText_Raiz_Raiz);
        OpcRepreExponencialTextSI = findViewById(R.id.OpcRepreExponencialTextSI);
        OpcRepreExponencialTextNO = findViewById(R.id.OpcRepreExponencialTextNO);
        OpcRepreExponencialEjemploCoefiA = findViewById(R.id.OpcRepreExponencialEjemploCoefiA);
        OpcRepreExponencialEjemploIndA = findViewById(R.id.OpcRepreExponencialEjemploIndA);
        OpcRepreExponencialEjemploBaseA = findViewById(R.id.OpcRepreExponencialEjemploBaseA);
        OpcRepreExponencialEjemploeExpoA = findViewById(R.id.OpcRepreExponencialEjemploeExpoA);
        OpcRepreExponencialEjemploCoefiB = findViewById(R.id.OpcRepreExponencialEjemploCoefiB);
        OpcRepreExponencialEjemploInB = findViewById(R.id.OpcRepreExponencialEjemploIndB);
        OpcRepreExponencialEjemploBaseB = findViewById(R.id.OpcRepreExponencialEjemploBaseB);
        OpcRepreExponencialEjemploeExpoB = findViewById(R.id.OpcRepreExponencialEjemploeExpoB);

        OpcRepreExponencialTextEjemploPotCoeficiente  = findViewById(R.id.OpcRepreExponencialTextEjemploPotCoeficiente);
        OpcRepreExponencialTextEjemploPotIndice = findViewById(R.id.OpcRepreExponencialTextEjemploPotIndice);
        OpcRepreExponencialTextEjemploPotR = findViewById(R.id.OpcRepreExponencialTextEjemploPotR);
        OpcRepreExponencialTextEjemploPotE = findViewById(R.id.OpcRepreExponencialTextEjemploPotE);
        OpcRepreExponencialTextEjemploPotN = findViewById(R.id.OpcRepreExponencialTextEjemploPotN);

        OpcRepreExponencialTextPotRaizVar = findViewById(R.id.OpcRepreExponencialTextPotRaizVar);
        OpcRepreExponencialEjemploRaizIndX = findViewById(R.id.OpcRepreExponencialEjemploRaizIndX);
        OpcRepreExponencialEjemploRaizIndA = findViewById(R.id.OpcRepreExponencialEjemploRaizIndA);
        OpcRepreExponencialEjemploRaizC = findViewById(R.id.OpcRepreExponencialEjemploRaizC);
        OpcRepreExponencialEjemploRaizN = findViewById(R.id.OpcRepreExponencialEjemploRaizN);

        //edit
        OpcRepreExponencialEditIndiceA = findViewById(R.id.OpcRepreExponencialEditIndiceA);
        OpcRepreExponencialEditCoeficienteA = findViewById(R.id.OpcRepreExponencialEditCoeficienteA);
        OpcRepreExponencialEditBaseA = findViewById(R.id.OpcRepreExponencialEditBaseA);
        OpcRepreExponencialEditExpoA = findViewById(R.id.OpcRepreExponencialEditExpoA);
        OpcRepreExponencialEditIndiceB = findViewById(R.id.OpcRepreExponencialEditIndiceB);
        OpcRepreExponencialEditCoeficienteB = findViewById(R.id.OpcRepreExponencialEditCoeficienteB);
        OpcRepreExponencialEditBaseB = findViewById(R.id.OpcRepreExponencialEditBaseB);
        OpcRepreExponencialEditExpoB = findViewById(R.id.OpcRepreExponencialEditExpoB);

        OpcRepreExponencialEditPotIndice = findViewById(R.id.OpcRepreExponencialEditPotIndice);
        OpcRepreExponencialEditPotC = findViewById(R.id.OpcRepreExponencialEditPotC);
        OpcRepreExponencialEditPotR = findViewById(R.id.OpcRepreExponencialEditPotR);
        OpcRepreExponencialEditPotE = findViewById(R.id.OpcRepreExponencialEditPotE);
        OpcRepreExponencialEditPotN = findViewById(R.id.OpcRepreExponencialEditPotN);

        OpcRepreExponencialTextPotRaizVar = findViewById(R.id.OpcRepreExponencialTextPotRaizVar);
        OpcRepreExponencialEjemploRaizIndX = findViewById(R.id.OpcRepreExponencialEjemploRaizIndX);
        OpcRepreExponencialEjemploRaizIndA = findViewById(R.id.OpcRepreExponencialEjemploRaizIndA);
        OpcRepreExponencialEjemploRaizC = findViewById(R.id.OpcRepreExponencialEjemploRaizC);
        OpcRepreExponencialEjemploRaizN = findViewById(R.id.OpcRepreExponencialEjemploRaizN);

        //Iimg
        OpcRepreExponencialEjemploImgA = findViewById(R.id.OpcRepreExponencialEjemploImgA);
        OpcRepreExponencialEjemploImgB = findViewById(R.id.OpcRepreExponencialEjemploImgB);
        OpcRepreExponencialTextEjemploPotImg = findViewById(R.id.OpcRepreExponencialTextEjemploPotImg);
        OpcRepreExponencialEjemploRaizImg = findViewById(R.id.OpcRepreExponencialEjemploRaizImg);
        //endregion

        //region btnSolucion
        OpcRepreExponencialbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (OpcRepreExponencialValidar()) {

//                        myArrayDatosDatos.clear();
                        myArrayDatos.add(OpcRepreExponencialTipoEjercicio);

                        if (OpcRepreExponencialTipoEjercicio.intValue() == 1 || OpcRepreExponencialTipoEjercicio.intValue() == 2) {
                            //multi
                            myArrayDatos.add(OpcRepreExponencialEsRaizA);
                            myArrayDatos.add(OpcRepreExponencialEditIndiceA.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditIndiceA.getText().toString()));
                            myArrayDatos.add(OpcRepreExponencialEditBaseA.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditBaseA.getText().toString()));
                            myArrayDatos.add(OpcRepreExponencialEditExpoA.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditExpoA.getText().toString()));
                            myArrayDatos.add(OpcRepreExponencialEditCoeficienteA.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditCoeficienteA.getText().toString()));
                            myArrayDatos.add(OpcRepreExponencialEsRaizB);
                            myArrayDatos.add(OpcRepreExponencialEditIndiceB.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditIndiceB.getText().toString()));
                            myArrayDatos.add(OpcRepreExponencialEditBaseB.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditBaseB.getText().toString()));
                            myArrayDatos.add(OpcRepreExponencialEditExpoB.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditExpoB.getText().toString()));
                            myArrayDatos.add(OpcRepreExponencialEditCoeficienteB.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditCoeficienteB.getText().toString()));
                        }
                        else{
                            //POTENCIA
                            if (OpcRepreExponencialTipoEjercicio.intValue() == 3){
                                myArrayDatos.add(OpcRepreExponencialEsRaizA);
                                myArrayDatos.add(OpcRepreExponencialEditPotC.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotC.getText().toString()));
                                myArrayDatos.add(OpcRepreExponencialEditPotIndice.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotIndice.getText().toString()));
                                myArrayDatos.add(OpcRepreExponencialEditPotR.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotR.getText().toString()));
                                myArrayDatos.add(OpcRepreExponencialEditPotE.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotE.getText().toString()));
                                myArrayDatos.add(OpcRepreExponencialEditPotN.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotN.getText().toString()));
                            }else {
                                //RAIZ
                                myArrayDatos.add(OpcRepreExponencialEsRaizA);
                                myArrayDatos.add(OpcRepreExponencialEditPotIndice.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotIndice.getText().toString()));
                                myArrayDatos.add(OpcRepreExponencialEditPotR.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotR.getText().toString()));
                                myArrayDatos.add(OpcRepreExponencialEditPotE.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotE.getText().toString()));
                                myArrayDatos.add(OpcRepreExponencialEditPotN.getText().toString().isEmpty() ? 1.0 : Double.parseDouble(OpcRepreExponencialEditPotN.getText().toString()));
                            }
                        }
                        myArrayDatosDatos = myArrayDatos;
                        String _result = ExercisesSolotionsDetail.MenuMetodos(myExerciseToSolve.getIdTema(), myArrayDatos);
                        Toast.makeText(CapturaDeDatos.this, _result, Toast.LENGTH_LONG).show();
                        openActivity();
                        //myArrayDatos.clear();
                    }
                }catch(Exception  e) {
                    Toast.makeText(CapturaDeDatos.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        //endregion

        //region Efectos visuales - Seleccion

        //region Multiplicacion y Disivion
        //region Operaciones
        OpcRepreExponencialLinearMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OpcRepreExponencialLinearMulti,OpcRepreExponencialTextMulti);
                OpcionOriginal(OpcRepreExponencialLinearDivision,OpcRepreExponencialTextDivision);
                OpcionOriginal(OpcRepreExponencialLinearRaiz,OpcRepreExponencialTextRaiz);
                OpcionOriginal(OpcRepreExponencialLinearExpo,OpcRepreExponencialTextExpo);
                OpcRepreExponencialTipoEjercicio = 1.0;
                OpcRepreExponencialLinearMulti_Division.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearExpo_Raiz.setVisibility(View.GONE);
                OpcRepreExponencialText_A_B.setText("A•B");
                OpcRepreExponencialText_A_Raiz.setText("A•√B");
                OpcRepreExponencialText_Raiz_B.setText("√A•B");
                OpcRepreExponencialText_Raiz_Raiz.setText("√A•√B");
                OpcRepreExponencialEditMulti_Division.setVisibility(View.VISIBLE);
                OpcRepreExponencialEditRaizExpo.setVisibility(View.GONE);
            }
        });
        OpcRepreExponencialLinearDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OpcRepreExponencialLinearDivision,OpcRepreExponencialTextDivision);
                OpcionOriginal(OpcRepreExponencialLinearMulti,OpcRepreExponencialTextMulti);
                OpcionOriginal(OpcRepreExponencialLinearRaiz,OpcRepreExponencialTextRaiz);
                OpcionOriginal(OpcRepreExponencialLinearExpo,OpcRepreExponencialTextExpo);
                OpcRepreExponencialTipoEjercicio = 2.0;
                OpcRepreExponencialLinearMulti_Division.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearExpo_Raiz.setVisibility(View.GONE);
                OpcRepreExponencialText_A_B.setText("A÷B");
                OpcRepreExponencialText_A_Raiz.setText("A÷√B");
                OpcRepreExponencialText_Raiz_B.setText("√A÷B");
                OpcRepreExponencialText_Raiz_Raiz.setText("√A÷√B");
                OpcRepreExponencialEditMulti_Division.setVisibility(View.VISIBLE);
                OpcRepreExponencialEditRaizExpo.setVisibility(View.GONE);
            }
        });
        OpcRepreExponencialLinearExpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OpcRepreExponencialLinearExpo,OpcRepreExponencialTextExpo);
                OpcionOriginal(OpcRepreExponencialLinearMulti,OpcRepreExponencialTextMulti);
                OpcionOriginal(OpcRepreExponencialLinearRaiz,OpcRepreExponencialTextRaiz);
                OpcionOriginal(OpcRepreExponencialLinearDivision,OpcRepreExponencialTextDivision);
                OpcRepreExponencialTipoEjercicio = 3.0;
                OpcRepreExponencialEsRaizA = 1.0;
                OpcRepreExponencialLinearMulti_Division.setVisibility(View.GONE);
                OpcRepreExponencialLinearExpo_Raiz.setVisibility(View.VISIBLE);
                OpcRepreExponencialEditMulti_Division.setVisibility(View.GONE);
                OpcRepreExponencialEditRaizExpo.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearEjemploPototencia.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearEjemploRaiz.setVisibility(View.GONE);
                OpcRepreExponencialTextPotRaizVar.setText("Elevado a:");
                OpcRepreExponencialEditPotN.setHint("n");
                OpcRepreExponencialEditPotE.setHint("e");
                OpcRepreExponencialEditPotR.setHint("r");
                OpcRepreExponencialLinearPotCoeficienteC.setVisibility(View.VISIBLE);
            }
        });
        OpcRepreExponencialLinearRaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OpcRepreExponencialLinearRaiz,OpcRepreExponencialTextRaiz);
                OpcionOriginal(OpcRepreExponencialLinearMulti,OpcRepreExponencialTextMulti);
                OpcionOriginal(OpcRepreExponencialLinearExpo,OpcRepreExponencialTextExpo);
                OpcionOriginal(OpcRepreExponencialLinearDivision,OpcRepreExponencialTextDivision);
                OpcRepreExponencialTipoEjercicio = 4.0;
                OpcRepreExponencialEsRaizA = 1.0;
                OpcRepreExponencialLinearMulti_Division.setVisibility(View.GONE);
                OpcRepreExponencialLinearExpo_Raiz.setVisibility(View.VISIBLE);
                OpcRepreExponencialEditMulti_Division.setVisibility(View.GONE);
                OpcRepreExponencialEditRaizExpo.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearEjemploRaiz.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearEjemploPototencia.setVisibility(View.GONE);
                OpcRepreExponencialTextPotRaizVar.setText("Índice x:");
                OpcRepreExponencialEditPotN.setHint("x");
                OpcRepreExponencialEditPotE.setHint("n");
                OpcRepreExponencialEditPotR.setHint("c");
                OpcRepreExponencialLinearPotCoeficienteC.setVisibility(View.GONE);
            }
        });
        //endregion

        //region Efectos de forma ejercicio
        OpcRepreExponencialLinear_A_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcRepreExponencialEsRaizA = 0.0;
                OpcRepreExponencialEsRaizB = 0.0;
                OpcionSeleccionada(OpcRepreExponencialLinear_A_B,OpcRepreExponencialText_A_B);
                OpcionOriginal(OpcRepreExponencialLinear_A_Raiz,OpcRepreExponencialText_A_Raiz);
                OpcionOriginal(OpcRepreExponencialLinear_Raiz_B,OpcRepreExponencialText_Raiz_B);
                OpcionOriginal(OpcRepreExponencialLinear_Raiz_Raiz,OpcRepreExponencialText_Raiz_Raiz);
                OpcRepreExponencialEjemploImgA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.GONE);
                OpcRepreExponencialLinearIndiceA.setVisibility(View.GONE);
                OpcRepreExponencialLinearIndiceB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploInB.setVisibility(View.GONE);
                OpcRepreExponencialLinearCoeficienteA.setVisibility(View.GONE);
                OpcRepreExponencialLinearCoeficienteB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploCoefiA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploCoefiB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploImgA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploInB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.GONE);
            }
        });
        OpcRepreExponencialLinear_A_Raiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcRepreExponencialEsRaizA = 0.0;
                OpcRepreExponencialEsRaizB = 1.0;
                OpcionSeleccionada(OpcRepreExponencialLinear_A_Raiz,OpcRepreExponencialText_A_Raiz);
                OpcionOriginal(OpcRepreExponencialLinear_A_B,OpcRepreExponencialText_A_B);
                OpcionOriginal(OpcRepreExponencialLinear_Raiz_B,OpcRepreExponencialText_Raiz_B);
                OpcionOriginal(OpcRepreExponencialLinear_Raiz_Raiz,OpcRepreExponencialText_Raiz_Raiz);
                OpcRepreExponencialEjemploImgA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearIndiceA.setVisibility(View.GONE);
                OpcRepreExponencialLinearIndiceB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploInB.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearCoeficienteA.setVisibility(View.GONE);
                OpcRepreExponencialLinearCoeficienteB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploCoefiA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploCoefiB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploImgA.setVisibility(View.GONE);
                OpcRepreExponencialEjemploInB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.VISIBLE);
            }
        });
        OpcRepreExponencialLinear_Raiz_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcRepreExponencialEsRaizA = 1.0;
                OpcRepreExponencialEsRaizB = 0.0;
                OpcionSeleccionada(OpcRepreExponencialLinear_Raiz_B,OpcRepreExponencialText_Raiz_B);
                OpcionOriginal(OpcRepreExponencialLinear_A_B,OpcRepreExponencialText_A_B);
                OpcionOriginal(OpcRepreExponencialLinear_A_Raiz,OpcRepreExponencialText_A_Raiz);
                OpcionOriginal(OpcRepreExponencialLinear_Raiz_Raiz,OpcRepreExponencialText_Raiz_Raiz);
                OpcRepreExponencialEjemploImgA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.GONE);
                OpcRepreExponencialLinearIndiceA.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearIndiceB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploInB.setVisibility(View.GONE);
                OpcRepreExponencialLinearCoeficienteA.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearCoeficienteB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploCoefiA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploCoefiB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploImgA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploInB.setVisibility(View.GONE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.GONE);
            }
        });
        OpcRepreExponencialLinear_Raiz_Raiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcRepreExponencialEsRaizA = 1.0;
                OpcRepreExponencialEsRaizB = 1.0;
                OpcionSeleccionada(OpcRepreExponencialLinear_Raiz_Raiz,OpcRepreExponencialText_Raiz_Raiz);
                OpcionOriginal(OpcRepreExponencialLinear_A_B,OpcRepreExponencialText_A_B);
                OpcionOriginal(OpcRepreExponencialLinear_A_Raiz,OpcRepreExponencialText_A_Raiz);
                OpcionOriginal(OpcRepreExponencialLinear_Raiz_B,OpcRepreExponencialText_Raiz_B);
                OpcRepreExponencialEjemploImgA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearIndiceA.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearIndiceB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploInB.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearCoeficienteA.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearCoeficienteB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploCoefiA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploCoefiB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploIndA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploImgA.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploInB.setVisibility(View.VISIBLE);
                OpcRepreExponencialEjemploImgB.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        //region Cracion del ejemplo (ejercicio) multi y div
        OpcRepreExponencialEditIndiceA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploIndA.setText(OpcRepreExponencialEditIndiceA.getText().toString());
            }
        });
        OpcRepreExponencialEditCoeficienteA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploCoefiA.setText(OpcRepreExponencialEditCoeficienteA.getText().toString());
            }
        });
        OpcRepreExponencialEditBaseA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploBaseA.setText(OpcRepreExponencialEditBaseA.getText().toString());
            }
        });
        OpcRepreExponencialEditExpoA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploeExpoA.setText(OpcRepreExponencialEditExpoA.getText().toString());
            }
        });
        OpcRepreExponencialEditIndiceB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploInB.setText(OpcRepreExponencialEditIndiceB.getText().toString());
            }
        });
        OpcRepreExponencialEditCoeficienteB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploCoefiB.setText(OpcRepreExponencialEditCoeficienteB.getText().toString());
            }
        });
        OpcRepreExponencialEditBaseB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploBaseB.setText(OpcRepreExponencialEditBaseB.getText().toString());
            }
        });
        OpcRepreExponencialEditExpoB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialEjemploeExpoB.setText(OpcRepreExponencialEditExpoB.getText().toString());
            }
        });
        //endregion
        //endregion

        //region Efecto visual  - Selecccion Si - no
        OpcRepreExponencialLinearSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OpcRepreExponencialLinearSI,OpcRepreExponencialTextSI);
                OpcionOriginal(OpcRepreExponencialLinearNO,OpcRepreExponencialTextNO);
                OpcRepreExponencialEsRaizA = 1.0;
                OpcRepreExponencialTextEjemploPotImg.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearPotIndiceA.setVisibility(View.VISIBLE);
                OpcRepreExponencialLinearPotCoeficienteC.setVisibility(View.VISIBLE);
                if (OpcRepreExponencialTipoEjercicio.intValue() == 3){
                    OpcRepreExponencialTextEjemploPotIndice.setVisibility(View.VISIBLE);
                }
                OpcRepreExponencialTextEjemploPotCoeficiente.setVisibility(View.VISIBLE);
                if (OpcRepreExponencialTipoEjercicio.intValue() == 4){
                    OpcRepreExponencialEjemploRaizImg.setImageResource(R.drawable.ic_raiz_2ble);
                    OpcRepreExponencialEjemploRaizIndA.setVisibility(View.VISIBLE);
                }
            }
        });
        OpcRepreExponencialLinearNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(OpcRepreExponencialLinearNO,OpcRepreExponencialTextNO);
                OpcionOriginal(OpcRepreExponencialLinearSI,OpcRepreExponencialTextSI);
                OpcRepreExponencialEsRaizA = 0.0;
                OpcRepreExponencialTextEjemploPotImg.setVisibility(View.GONE);
                OpcRepreExponencialLinearPotIndiceA.setVisibility(View.GONE);
                OpcRepreExponencialLinearPotCoeficienteC.setVisibility(View.GONE);
                OpcRepreExponencialTextEjemploPotIndice.setVisibility(View.GONE);
                OpcRepreExponencialTextEjemploPotCoeficiente.setVisibility(View.GONE);
                if (OpcRepreExponencialTipoEjercicio.intValue() == 4){
                    OpcRepreExponencialEjemploRaizImg.setImageResource(R.drawable.ic_raiz_2ble_simple);
                    OpcRepreExponencialEjemploRaizIndA.setVisibility(View.GONE);
                }
            }
        });
        //endregion

        //region Creacion del ejemplo - Visual
        OpcRepreExponencialEditPotIndice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialTextEjemploPotIndice.setText(OpcRepreExponencialEditPotIndice.getText().toString());
                OpcRepreExponencialEjemploRaizIndA.setText(OpcRepreExponencialEditPotIndice.getText().toString());
            }
        });
        OpcRepreExponencialEditPotC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialTextEjemploPotCoeficiente.setText(OpcRepreExponencialEditPotC.getText().toString());
            }
        });
        OpcRepreExponencialEditPotR.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialTextEjemploPotR.setText(OpcRepreExponencialEditPotR.getText().toString());
                OpcRepreExponencialEjemploRaizC.setText(OpcRepreExponencialEditPotR.getText().toString());
            }
        });
        OpcRepreExponencialEditPotE.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialTextEjemploPotE.setText(OpcRepreExponencialEditPotE.getText().toString());
                OpcRepreExponencialEjemploRaizN.setText(OpcRepreExponencialEditPotE.getText().toString());
            }
        });
        OpcRepreExponencialEditPotN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                OpcRepreExponencialTextEjemploPotN.setText(OpcRepreExponencialEditPotN.getText().toString());
                OpcRepreExponencialEjemploRaizIndX.setText(OpcRepreExponencialEditPotN.getText().toString());
            }
        });
        //endregion

        //endregion
    }
    private boolean OpcRepreExponencialValidar(){
        boolean _result = true;
        String _error = "Ingresa el valor";

        //region Multi y division
        if (OpcRepreExponencialTipoEjercicio.intValue() == 1 || OpcRepreExponencialTipoEjercicio.intValue() == 2){
            if (OpcRepreExponencialEditBaseA.getText().toString().isEmpty()) {
                OpcRepreExponencialEditBaseA.setError(_error);
                _result = false;
            }
            if (OpcRepreExponencialEditExpoA.getText().toString().isEmpty()) {
                OpcRepreExponencialEditExpoA.setError(_error);
                _result = false;
            }
            if (OpcRepreExponencialEditBaseB.getText().toString().isEmpty()) {
                OpcRepreExponencialEditBaseB.setError(_error);
                _result = false;
            }
            if (OpcRepreExponencialEditExpoB.getText().toString().isEmpty()) {
                OpcRepreExponencialEditExpoB.setError(_error);
                _result = false;
            }
            if (OpcRepreExponencialEsRaizA.intValue() == 1){
                if (OpcRepreExponencialEditIndiceA.getText().toString().isEmpty()) {
                    OpcRepreExponencialEditIndiceA.setError(_error);
                    _result = false;
                }
                if (OpcRepreExponencialEditCoeficienteA.getText().toString().isEmpty()) {
                    OpcRepreExponencialEditCoeficienteA.setError(_error);
                    _result = false;
                }
            }
            if (OpcRepreExponencialEsRaizB.intValue() == 1){
                if (OpcRepreExponencialEditIndiceB.getText().toString().isEmpty()) {
                    OpcRepreExponencialEditIndiceB.setError(_error);
                    _result = false;
                }
                if (OpcRepreExponencialEditCoeficienteB.getText().toString().isEmpty()) {
                    OpcRepreExponencialEditCoeficienteB.setError(_error);
                    _result = false;
                }
            }
        }
        else {
            //Potencia
            if (OpcRepreExponencialTipoEjercicio.intValue() == 3){
                if (OpcRepreExponencialEditPotR.getText().toString().isEmpty()) {
                    OpcRepreExponencialEditPotR.setError(_error);
                    _result = false;
                }
                if (OpcRepreExponencialEditPotE.getText().toString().isEmpty()) {
                    OpcRepreExponencialEditPotE.setError(_error);
                    _result = false;
                }
                if (OpcRepreExponencialEditPotN.getText().toString().isEmpty()) {
                    OpcRepreExponencialEditPotN.setError(_error);
                    _result = false;
                }
                if(OpcRepreExponencialEsRaizA.intValue() == 1){
                    if (OpcRepreExponencialEditPotC.getText().toString().isEmpty()) {
                        OpcRepreExponencialEditPotC.setError(_error);
                        _result = false;
                    }
                    if (OpcRepreExponencialEditPotIndice.getText().toString().isEmpty()) {
                        OpcRepreExponencialEditPotIndice.setError(_error);
                        _result = false;
                    }
                }
            }
            else{//raiz

            }
        }
        //endregion
        return _result;
    }
    //endregion

    //endregion

    //endregion

    //region MATRICES ============== 2

    //region Solucion Paso a paso
    //region MatrizGauss
    private void ResolverEjercicio() {
        myMatrizGauss.clear();
        myMatrizGaussDatos.clear();
        ExercisesSolotionsDetail.myListSteps.clear();
        if (Validar()) {
            myMatrizGauss.add((new csMatriz(0, 0.0, 0.0, 0.0, 0.0, 0.0, 0)));
            myMatrizGaussDatos.add((new csMatriz(0, 0.0, 0.0, 0.0, 0.0, 0.0, 0)));
            int Id = 0;

            EditText _etEmpty = new EditText(getApplicationContext());
            _etEmpty.setText("");

            if (GaussTamano.equals(2)) {
                AddEcuacionMatrizGauss(_etEmpty, Gauss12, Gauss13, _etEmpty, Gauss1Igual, Id + 1, GaussTamano);
                AddEcuacionMatrizGauss(_etEmpty, Gauss22, Gauss23, _etEmpty, Gauss2Igual, Id + 1, GaussTamano);
            } else if (GaussTamano.equals(3)) {
                AddEcuacionMatrizGauss(_etEmpty, Gauss12, Gauss13, Gauss14, Gauss1Igual, Id + 1, GaussTamano);
                AddEcuacionMatrizGauss(_etEmpty, Gauss22, Gauss23, Gauss24, Gauss2Igual, Id + 1, GaussTamano);
                AddEcuacionMatrizGauss(_etEmpty, Gauss32, Gauss33, Gauss34, Gauss3Igual, Id + 1, GaussTamano);
            } else if (GaussTamano.equals(4)) {
                AddEcuacionMatrizGauss(Gauss11, Gauss12, Gauss13, Gauss14, Gauss1Igual, Id + 1, GaussTamano);
                AddEcuacionMatrizGauss(Gauss21, Gauss22, Gauss23, Gauss24, Gauss2Igual, Id + 1, GaussTamano);
                AddEcuacionMatrizGauss(Gauss31, Gauss32, Gauss33, Gauss34, Gauss3Igual, Id + 1, GaussTamano);
                AddEcuacionMatrizGauss(Gauss41, Gauss42, Gauss43, Gauss44, Gauss4Igual, Id + 1, GaussTamano);
            }
            String asd = ExercisesSolotionsDetail.MatrizdeGauss(myMatrizGauss);
            openActivity();
            RespuestaDirecta.setText(ExercisesSolotionsDetail.myListSteps.size() + " **** " + myMatrizGauss.size());
        }
    }

    private void SeleccionMatrizGauss(Integer _pGaussTam) {
        GaussTamano = _pGaussTam;
        if (GaussTamano.equals(2)) {
            MostraMatrizGauss2x2();
        } else if (GaussTamano.equals(3)) {
            MostraMatrizGauss3x3();
        } else if (GaussTamano.equals(4)) {
            MostraMatrizGauss4x4();
        }
    }

    private void MostraMatrizGauss2x2() {
        MostraMatrizGauss4x4();
        lblGaussW.setVisibility(View.GONE);
        lblGaussz.setVisibility(View.GONE);
        Gauss11.setVisibility(View.GONE);
        Gauss21.setVisibility(View.GONE);
        Gauss14.setVisibility(View.GONE);
        Gauss24.setVisibility(View.GONE);
        LinearGaussFila3.setVisibility(View.GONE);
        LinearGaussFila4.setVisibility(View.GONE);
        Gauss12.setHint("1,1");
        Gauss13.setHint("1,2");
        Gauss22.setHint("2,1");
        Gauss23.setHint("2,2");
    }

    private void MostraMatrizGauss3x3() {
        MostraMatrizGauss4x4();
        lblGaussW.setVisibility(View.GONE);
        LinearGaussFila4.setVisibility(View.GONE);
        Gauss31.setVisibility(View.VISIBLE);
        Gauss32.setVisibility(View.VISIBLE);
        Gauss33.setVisibility(View.VISIBLE);
        Gauss34.setVisibility(View.VISIBLE);
        Gauss3Igual.setVisibility(View.VISIBLE);
        Gauss11.setVisibility(View.GONE);
        Gauss21.setVisibility(View.GONE);
        Gauss31.setVisibility(View.GONE);
        Gauss12.setHint("1,1");
        Gauss13.setHint("1,2");
        Gauss14.setHint("1,3");
        Gauss22.setHint("2,1");
        Gauss23.setHint("2,2");
        Gauss24.setHint("2,3");
        Gauss32.setHint("3,1");
        Gauss33.setHint("3,2");
        Gauss34.setHint("3,3");
    }

    private void MostraMatrizGauss4x4() {

        lblGaussW.setVisibility(View.VISIBLE);
        lblGaussz.setVisibility(View.VISIBLE);

        LinearGaussFila3.setVisibility(View.VISIBLE);
        LinearGaussFila4.setVisibility(View.VISIBLE);
        Gauss11.setVisibility(View.VISIBLE);
        Gauss12.setVisibility(View.VISIBLE);
        Gauss13.setVisibility(View.VISIBLE);
        Gauss14.setVisibility(View.VISIBLE);
        Gauss1Igual.setVisibility(View.VISIBLE);

        Gauss21.setVisibility(View.VISIBLE);
        Gauss22.setVisibility(View.VISIBLE);
        Gauss23.setVisibility(View.VISIBLE);
        Gauss24.setVisibility(View.VISIBLE);
        Gauss2Igual.setVisibility(View.VISIBLE);

        Gauss31.setVisibility(View.VISIBLE);
        Gauss32.setVisibility(View.VISIBLE);
        Gauss33.setVisibility(View.VISIBLE);
        Gauss34.setVisibility(View.VISIBLE);
        Gauss3Igual.setVisibility(View.VISIBLE);

        Gauss41.setVisibility(View.VISIBLE);
        Gauss42.setVisibility(View.VISIBLE);
        Gauss43.setVisibility(View.VISIBLE);
        Gauss44.setVisibility(View.VISIBLE);
        Gauss4Igual.setVisibility(View.VISIBLE);

        Gauss11.setHint("1,1");
        Gauss12.setHint("1,2");
        Gauss13.setHint("1,3");
        Gauss14.setHint("1,4");
        Gauss21.setHint("2,1");
        Gauss22.setHint("2,2");
        Gauss23.setHint("2,3");
        Gauss24.setHint("2,4");
        Gauss31.setHint("3,1");
        Gauss32.setHint("3,2");
        Gauss33.setHint("3,3");
        Gauss34.setHint("3,4");
        Gauss41.setHint("4,1");
        Gauss42.setHint("4,2");
        Gauss43.setHint("4,3");
        Gauss44.setHint("4,4");
    }

    private boolean Validar() {
        boolean _result = true;
        String _error = "Ingresa el valor";

        if (GaussTamano.equals(2)) {
            if (Gauss12.getText().toString().isEmpty()) {
                Gauss12.setError(_error);
                _result = false;
            }
            if (Gauss13.getText().toString().isEmpty()) {
                Gauss13.setError(_error);
                _result = false;
            }
            if (Gauss1Igual.getText().toString().isEmpty()) {
                Gauss1Igual.setError(_error);
                _result = false;
            }
            ///////
            if (Gauss22.getText().toString().isEmpty()) {
                Gauss22.setError(_error);
                _result = false;
            }
            if (Gauss23.getText().toString().isEmpty()) {
                Gauss23.setError(_error);
                _result = false;
            }
            if (Gauss2Igual.getText().toString().isEmpty()) {
                Gauss2Igual.setError(_error);
                _result = false;
            }
        } else {
            if (GaussTamano.equals(3)) {

                if (Gauss12.getText().toString().isEmpty()) {
                    Gauss12.setError(_error);
                    _result = false;
                }
                if (Gauss13.getText().toString().isEmpty()) {
                    Gauss13.setError(_error);
                    _result = false;
                }
                if (Gauss14.getText().toString().isEmpty()) {
                    Gauss14.setError(_error);
                    _result = false;
                }
                if (Gauss1Igual.getText().toString().isEmpty()) {
                    Gauss1Igual.setError(_error);
                    _result = false;
                }
                ///////
                if (Gauss22.getText().toString().isEmpty()) {
                    Gauss22.setError(_error);
                    _result = false;
                }
                if (Gauss23.getText().toString().isEmpty()) {
                    Gauss23.setError(_error);
                    _result = false;
                }
                if (Gauss24.getText().toString().isEmpty()) {
                    Gauss24.setError(_error);
                    _result = false;
                }
                if (Gauss2Igual.getText().toString().isEmpty()) {
                    Gauss2Igual.setError(_error);
                    _result = false;
                }
                ///////
                if (Gauss32.getText().toString().isEmpty()) {
                    Gauss32.setError(_error);
                    _result = false;
                }
                if (Gauss33.getText().toString().isEmpty()) {
                    Gauss33.setError(_error);
                    _result = false;
                }
                if (Gauss34.getText().toString().isEmpty()) {
                    Gauss34.setError(_error);
                    _result = false;
                }
                if (Gauss3Igual.getText().toString().isEmpty()) {
                    Gauss3Igual.setError(_error);
                    _result = false;
                }
            } else {
                if (GaussTamano.equals(4)) {
                    if (Gauss11.getText().toString().isEmpty()) {
                        Gauss11.setError(_error);
                        _result = false;
                    }
                    if (Gauss12.getText().toString().isEmpty()) {
                        Gauss12.setError(_error);
                        _result = false;
                    }
                    if (Gauss13.getText().toString().isEmpty()) {
                        Gauss13.setError(_error);
                        _result = false;
                    }
                    if (Gauss14.getText().toString().isEmpty()) {
                        Gauss14.setError(_error);
                        _result = false;
                    }
                    if (Gauss1Igual.getText().toString().isEmpty()) {
                        Gauss1Igual.setError(_error);
                        _result = false;
                    }
                    ///////
                    if (Gauss21.getText().toString().isEmpty()) {
                        Gauss21.setError(_error);
                        _result = false;
                    }
                    if (Gauss22.getText().toString().isEmpty()) {
                        Gauss22.setError(_error);
                        _result = false;
                    }
                    if (Gauss23.getText().toString().isEmpty()) {
                        Gauss23.setError(_error);
                        _result = false;
                    }
                    if (Gauss24.getText().toString().isEmpty()) {
                        Gauss24.setError(_error);
                        _result = false;
                    }
                    if (Gauss2Igual.getText().toString().isEmpty()) {
                        Gauss2Igual.setError(_error);
                        _result = false;
                    }
                    ///////
                    if (Gauss31.getText().toString().isEmpty()) {
                        Gauss31.setError(_error);
                        _result = false;
                    }
                    if (Gauss32.getText().toString().isEmpty()) {
                        Gauss32.setError(_error);
                        _result = false;
                    }
                    if (Gauss33.getText().toString().isEmpty()) {
                        Gauss33.setError(_error);
                        _result = false;
                    }
                    if (Gauss34.getText().toString().isEmpty()) {
                        Gauss34.setError(_error);
                        _result = false;
                    }
                    if (Gauss3Igual.getText().toString().isEmpty()) {
                        Gauss3Igual.setError(_error);
                        _result = false;
                    }
                    ///////
                    if (Gauss41.getText().toString().isEmpty()) {
                        Gauss41.setError(_error);
                        _result = false;
                    }
                    if (Gauss42.getText().toString().isEmpty()) {
                        Gauss42.setError(_error);
                        _result = false;
                    }
                    if (Gauss43.getText().toString().isEmpty()) {
                        Gauss43.setError(_error);
                        _result = false;
                    }
                    if (Gauss44.getText().toString().isEmpty()) {
                        Gauss44.setError(_error);
                        _result = false;
                    }
                    if (Gauss4Igual.getText().toString().isEmpty()) {
                        Gauss4Igual.setError(_error);
                        _result = false;
                    }
                }
            }
        }
        return _result;
    }

    private void InicializarMatrizGauss() {

        //region Inicializacion
        Gauss11 = findViewById(R.id.Gauss11);
        Gauss12 = findViewById(R.id.Gauss12);
        Gauss13 = findViewById(R.id.Gauss13);
        Gauss14 = findViewById(R.id.Gauss14);
        Gauss1Igual = findViewById(R.id.Gauss1Igual);

        Gauss21 = findViewById(R.id.Gauss21);
        Gauss22 = findViewById(R.id.Gauss22);
        Gauss23 = findViewById(R.id.Gauss23);
        Gauss24 = findViewById(R.id.Gauss24);
        Gauss2Igual = findViewById(R.id.Gauss2Igual);

        Gauss31 = findViewById(R.id.Gauss31);
        Gauss32 = findViewById(R.id.Gauss32);
        Gauss33 = findViewById(R.id.Gauss33);
        Gauss34 = findViewById(R.id.Gauss34);
        Gauss3Igual = findViewById(R.id.Gauss3Igual);

        Gauss41 = findViewById(R.id.Gauss41);
        Gauss42 = findViewById(R.id.Gauss42);
        Gauss43 = findViewById(R.id.Gauss43);
        Gauss44 = findViewById(R.id.Gauss44);
        Gauss4Igual = findViewById(R.id.Gauss4Igual);

        LinearGaussFila3 = findViewById(R.id.GaussFila3);
        LinearGaussFila4 = findViewById(R.id.GaussFila4);
        LinearGauss2x2 = findViewById(R.id.linearGauss2x2);
        LinearGauss3x3 = findViewById(R.id.linearGauss3x3);
        LinearGauss4x4 = findViewById(R.id.linearGauss4x4);

        tvGauss2x2 = findViewById(R.id.tvGauss2x2);
        tvGauss3x3 = findViewById(R.id.tvGauss3x3);
        tvGauss4x4 = findViewById(R.id.tvGauss4x4);
        lblGaussW = findViewById(R.id.lblGaussW);
        lblGaussz = findViewById(R.id.lblGaussZ);
        //endregion

        LinearGauss2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionMatrizGauss(2);
                tvGauss2x2.setTextColor(Color.parseColor("#008080"));
                tvGauss3x3.setTextColor(Color.parseColor("#465e5e"));
                tvGauss4x4.setTextColor(Color.parseColor("#465e5e"));
                LinearGauss2x2.setBackgroundResource((R.drawable.item_opc_seleccionado));
                LinearGauss4x4.setBackgroundResource((R.drawable.item_opc));
                LinearGauss3x3.setBackgroundResource((R.drawable.item_opc));
            }
        });
        LinearGauss3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionMatrizGauss(3);
                tvGauss3x3.setTextColor(Color.parseColor("#008080"));
                tvGauss2x2.setTextColor(Color.parseColor("#465e5e"));
                tvGauss4x4.setTextColor(Color.parseColor("#465e5e"));
                LinearGauss3x3.setBackgroundResource((R.drawable.item_opc_seleccionado));
                LinearGauss4x4.setBackgroundResource((R.drawable.item_opc));
                LinearGauss2x2.setBackgroundResource((R.drawable.item_opc));
            }
        });
        LinearGauss4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeleccionMatrizGauss(4);
                tvGauss4x4.setTextColor(Color.parseColor("#008080"));
                LinearGauss4x4.setBackgroundResource((R.drawable.item_opc_seleccionado));
                LinearGauss3x3.setBackgroundResource((R.drawable.item_opc));
                LinearGauss2x2.setBackgroundResource((R.drawable.item_opc));
                tvGauss2x2.setTextColor(Color.parseColor("#465e5e"));
                tvGauss3x3.setTextColor(Color.parseColor("#465e5e"));
            }
        });
        btnSolucionGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResolverEjercicio();
            }
        });


    }

    private void AddEcuacionMatrizGauss(EditText _w, EditText _x, EditText _y, EditText _z, EditText _igual, Integer _id, Integer _tamano) {
        csMatriz ecuacion = new csMatriz();
        ecuacion.setIdEcuacion(_id);
        ecuacion.setW(_w.getText().toString().isEmpty() ? 0.0 : Double.parseDouble(_w.getText().toString()));
        ecuacion.setX(_x.getText().toString().isEmpty() ? 0.0 : Double.parseDouble(_x.getText().toString()));
        ecuacion.setY(_y.getText().toString().isEmpty() ? 0.0 : Double.parseDouble(_y.getText().toString()));
        ecuacion.setZ(_z.getText().toString().isEmpty() ? 0.0 : Double.parseDouble(_z.getText().toString()));
        ecuacion.setIgual(_igual.getText().toString().isEmpty() ? 0.0 : Double.parseDouble(_igual.getText().toString()));
        ecuacion.setTamano(_tamano);
        myMatrizGauss.add(ecuacion);
        myMatrizGaussDatos.add(ecuacion);
    }
    private void LimpiarCamposMatrizGauss(){
        Gauss11.setText("");Gauss12.setText(""); Gauss13.setText(""); Gauss14.setText(""); Gauss1Igual.setText(""); Gauss21.setText(""); Gauss22.setText(""); Gauss23.setText(""); Gauss24.setText(""); Gauss2Igual.setText("");
                Gauss31.setText(""); Gauss32.setText(""); Gauss33.setText(""); Gauss34.setText(""); Gauss3Igual.setText(""); Gauss41.setText(""); Gauss42.setText(""); Gauss43.setText(""); Gauss44.setText(""); Gauss4Igual.setText("");
    }
    //endregion

    private void SolucionEcuacionesLiniales(){

        btnMGauss = findViewById(R.id.btnMGauss);
        tvMGauss = findViewById(R.id.tvMGauss);

        btnMInversa = findViewById(R.id.btnMInversa);
        tvMInversa = findViewById(R.id.tvMInversa);

        btnMGauss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(btnMGauss,tvMGauss);
                OpcionOriginal(btnMInversa,tvMInversa);
                MatrizGauss.setVisibility(View.VISIBLE);
                MatrizInverza.setVisibility(View.GONE);
            }
        });
        btnMInversa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(btnMInversa,tvMInversa);
                OpcionOriginal(btnMGauss,tvMGauss);
                MatrizInverza.setVisibility(View.VISIBLE);
                MatrizGauss.setVisibility(View.GONE);
            }
        });

    }

    //region MatrizInverza
    private void InicializarMatrizInverza(){

        //region Inicializacion
        MatrizInverza11  = findViewById(R.id.MatrizInverza11);
        MatrizInverza12 = findViewById(R.id.MatrizInverza12);
        MatrizInverza13 = findViewById(R.id.MatrizInverza13);
        MatrizInverza21 = findViewById(R.id.MatrizInverza21);
        MatrizInverza22 = findViewById(R.id.MatrizInverza22);
        MatrizInverza23 = findViewById(R.id.MatrizInverza23);
        MatrizInverza31 = findViewById(R.id.MatrizInverza31);
        MatrizInverza32 = findViewById(R.id.MatrizInverza32);
        MatrizInverza33 = findViewById(R.id.MatrizInverza33);

        MatrizInverzabtnSolucion = findViewById(R.id.MatrizInverzabtnSolucion);
        MatrizInverzaLinear2x2 = findViewById(R.id.MatrizInverzaLinear2x2);
        MatrizInverzaLinear3x3 = findViewById(R.id.MatrizInverzaLinear3x3);
        MatrizInverzaFila3 = findViewById(R.id.MatrizInverzaFila3);

        MatrizInverzaText2x2 = findViewById(R.id.MatrizInverzaText2x2);
        MatrizInverzaText3x3 = findViewById(R.id.MatrizInverzaText3x3);
        //endregion

        //region BtnSolucionar
        MatrizInverzabtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MatrizInverzaValidar()){
                    myArrayDatos.add(MatrizInverzaTamano);

                    if(MatrizInverzaTamano.intValue() == 0){
                        //3x3
                        myArrayDatos.add(Double.parseDouble(MatrizInverza11.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza12.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza13.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza21.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza22.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza23.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza31.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza32.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza33.getText().toString()));
                    }
                    else {
                        //2x2
                        myArrayDatos.add(Double.parseDouble(MatrizInverza11.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza12.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza21.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(MatrizInverza22.getText().toString()));
                    }
                    String _result = ExercisesSolotionsDetail.MenuMetodos(myExerciseToSolve.getIdTema(),myArrayDatos);
                    openActivity();
                   //myArrayDatos.clear();
                }
            }
        });
        //endregion

        //region Efectos visuales
        MatrizInverzaLinear2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatrizInverzaFila3.setVisibility(View.GONE);
                MatrizInverza13.setVisibility(View.GONE);
                MatrizInverza23.setVisibility(View.GONE);
                OpcionSeleccionada(MatrizInverzaLinear2x2,MatrizInverzaText2x2);
                OpcionOriginal(MatrizInverzaLinear3x3,MatrizInverzaText3x3);
                MatrizInverzaTamano = 1.0;
            }
        });
        MatrizInverzaLinear3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatrizInverzaFila3.setVisibility(View.VISIBLE);
                MatrizInverza13.setVisibility(View.VISIBLE);
                MatrizInverza23.setVisibility(View.VISIBLE);
                OpcionSeleccionada(MatrizInverzaLinear3x3,MatrizInverzaText3x3);
                OpcionOriginal(MatrizInverzaLinear2x2,MatrizInverzaText2x2);
                MatrizInverzaTamano = 0.0;
            }
        });
        //endregion
    }
    private boolean MatrizInverzaValidar(){
        boolean _result = true;
        String _error = "Ingresa el valor";

        if (MatrizInverza11.getText().toString().isEmpty()) {
            MatrizInverza11.setError(_error);
            _result = false;
        }
        if (MatrizInverza12.getText().toString().isEmpty()) {
            MatrizInverza12.setError(_error);
            _result = false;
        }
        if (MatrizInverza21.getText().toString().isEmpty()) {
            MatrizInverza21.setError(_error);
            _result = false;
        }
        if (MatrizInverza22.getText().toString().isEmpty()) {
            MatrizInverza22.setError(_error);
            _result = false;
        }
        if (MatrizInverzaTamano.intValue() == 0){
            if (MatrizInverza13.getText().toString().isEmpty()) {
                MatrizInverza13.setError(_error);
                _result = false;
            }
            if (MatrizInverza23.getText().toString().isEmpty()) {
                MatrizInverza23.setError(_error);
                _result = false;
            }
            if (MatrizInverza31.getText().toString().isEmpty()) {
                MatrizInverza31.setError(_error);
                _result = false;
            }
            if (MatrizInverza32.getText().toString().isEmpty()) {
                MatrizInverza32.setError(_error);
                _result = false;
            }
            if (MatrizInverza33.getText().toString().isEmpty()) {
                MatrizInverza33.setError(_error);
                _result = false;
            }
        }
        return _result;
    }
    //endregion
    //endregion

    //endregion

    //region VECTORES  ============= 3

    //region Solucion Paso a paso

    //region Vector Fuerza entre dos puntos
    private void InicializarFuerzaResultante() {

        FResultbtnSolucion = findViewById(R.id.FResultbtnSolucion);
        FResultX1 = findViewById(R.id.FResultX1);
        FResultX2 = findViewById(R.id.FResultX2);
        FResultY1 = findViewById(R.id.FResultY1);
        FResultY2 = findViewById(R.id.FResultY2);
        FResultZ1 = findViewById(R.id.FResultZ1);
        FResultZ2 = findViewById(R.id.FResultZ2);
        FResultFuerza = findViewById(R.id.FResultFuerza);

        FResultbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // myArrayDatos.clear();
                if (FResultValidar()) {
                    myArrayDatos.add(Double.parseDouble(FResultX1.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(FResultY1.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(FResultZ1.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(FResultX2.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(FResultY2.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(FResultZ2.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(FResultFuerza.getText().toString()));

                    String _resultados = ExercisesSolotionsDetail.MenuMetodos(myExerciseToSolve.getIdTema(), myArrayDatos);
                    openActivity();
                }
            }
        });
    }
    private boolean FResultValidar() {

        boolean _result = true;
        String _error = "Ingresa el valor";
        if (FResultX1.getText().toString().isEmpty()) {
            FResultX1.setError(_error);
            _result = false;
        }
        if (FResultX2.getText().toString().isEmpty()) {
            FResultX2.setError(_error);
            _result = false;
        }
        if (FResultY1.getText().toString().isEmpty()) {
            FResultY1.setError(_error);
            _result = false;
        }
        if (FResultY2.getText().toString().isEmpty()) {
            FResultY2.setError(_error);
            _result = false;
        }
        if (FResultZ1.getText().toString().isEmpty()) {
            FResultZ1.setError(_error);
            _result = false;
        }
        if (FResultZ2.getText().toString().isEmpty()) {
            FResultZ2.setError(_error);
            _result = false;
        }
        if (FResultFuerza.getText().toString().isEmpty()) {
            FResultFuerza.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region VectorProyeccion
    private void InicializarVectorFuProyeccion() {

        //region Inicializacion
        VectorProyeccionbtnSolucion = findViewById(R.id.VectorProyeccionbtnSolucion);
        VectorProyeccionbtnAgregar = findViewById(R.id.VectorProyeccionbtnAgregar);
        VectorProyecciontxtFuerza = findViewById(R.id.VectorProyeccionFuerza);
        VectorProyecciontxtAngulo = findViewById(R.id.VectorProyeccionAngulo);
        VectorProyeccionTextFuerza = findViewById(R.id.VectorProyeccionTextFuerza);
        VectorProyeccionTextAngulo = findViewById(R.id.VectorProyeccionTextAngulo);

        //region Linear para seleccion
        VProyeccionLinearCuadranteNinguno = findViewById(R.id.VProyeccionLinearCuadranteNinguno);
        VProyeccionLinearCuadranteI = findViewById(R.id.VProyeccionLinearCuadranteI);
        VProyeccionLinearCuadranteII = findViewById(R.id.VProyeccionLinearCuadranteII);
        VProyeccionLinearCuadranteIII = findViewById(R.id.VProyeccionLinearCuadranteIII);
        VProyeccionLinearCuadranteIV = findViewById(R.id.VProyeccionLinearCuadranteIV);
        VProyeccionLinearDireccionNinguno = findViewById(R.id.VProyeccionLinearDireccionNinguno);
        VProyeccionLinearDireccionAsc = findViewById(R.id.VProyeccionLinearDireccionAsc);
        VProyeccionLinearDireccionDesc = findViewById(R.id.VProyeccionLinearDireccionDesc);
        VProyeccionLinearApuntaIzq = findViewById(R.id.VProyeccionLinearApuntaIzq);
        VProyeccionLinearApuntaDer = findViewById(R.id.VProyeccionLinearApuntaDer);
        VProyeccionLinearApuntaArriba = findViewById(R.id.VProyeccionLinearApuntaArriba);
        VProyeccionLinearApuntaAbajo = findViewById(R.id.VProyeccionLinearApuntaAbajo);
        VProyeccionLinearRespectoX = findViewById(R.id.VProyeccionLinearRespectoX);
        VProyeccionLinearRespectoY = findViewById(R.id.VProyeccionLinearRespectoY);
        //endregion

        //region Text para seleccion
        VProyeccionTextCuadranteNinguno = findViewById(R.id.VProyeccionTextCuadranteNinguno);
        VProyeccionTextCuadranteI = findViewById(R.id.VProyeccionTextCuadranteI);
        VProyeccionTextCuadranteII = findViewById(R.id.VProyeccionTextCuadranteII);
        VProyeccionTextCuadranteIII = findViewById(R.id.VProyeccionTextCuadranteIII);
        VProyeccionTextCuadranteIV = findViewById(R.id.VProyeccionTextCuadranteIV);
        VProyeccionTextDireccionNinguno = findViewById(R.id.VProyeccionTextDireccionNinguno);
        VProyeccionTextDireccionAsc =  findViewById(R.id.VProyeccionTextDireccionAsc);
        VProyeccionTextDireccionDesc =  findViewById(R.id.VProyeccionTextDireccionDesc);
        VProyeccionTextApuntaIzq = findViewById(R.id.VProyeccionTextApuntaIzq);
        VProyeccionTextApuntaDer = findViewById(R.id.VProyeccionTextApuntaDer);
        VProyeccionTextApuntaArriba = findViewById(R.id.VProyeccionTextApuntaArriba);
        VProyeccionTextApuntaAbajo = findViewById(R.id.VProyeccionTextApuntaAbajo);
        VProyeccionTextRespectoX = findViewById(R.id.VProyeccionTextRespectoX);
        VProyeccionTextRespectoY = findViewById(R.id.VProyeccionTextRespectoY);
        //endregion
        //endregion

        //region Eventos Add - Result
        VectorProyeccionbtnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VectorProyeccionValidar()){
                    AgregarVectorProyeccion(VProyeccionCuadrante,VProyeccionDireccion,VProyeccionApuntaA,VectorProyecciontxtFuerza,VectorProyecciontxtAngulo,VProyeccionangRespectoA);
                    Toast.makeText(getApplicationContext(), "Agregado...", Toast.LENGTH_LONG).show();
                    EstiloOriginalVectorProyeccion();
                }
            }
        });
        VectorProyeccionbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myVectorProyeccion.size() > 0){
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(CapturaDeDatos.this);
                    builder1.setMessage("Hasta el momento ha agregado "+myVectorProyeccion.size() +" vectores\n¿Desea ver el proceso de solución detallado?");
                    builder1.setCancelable(true);
                    builder1.setTitle("Vector Proyección");
                    builder1.setPositiveButton(
                            "Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String _result = ExercisesSolotionsDetail.VectorProyeccion(myVectorProyeccion);
                                    openActivity();
                                    myVectorProyeccion.clear();
                                    dialog.cancel();
                                }
                            });
                    builder1.setNegativeButton(
                            "Cancelar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }else {
                    Toast.makeText(getApplicationContext(), "Agregue un vector para poder resolver el ejercicio...", Toast.LENGTH_LONG).show();
                }
            }
        });
        //endregion

        //region Efecto Seleccion - Cuadrantes
        VProyeccionLinearCuadranteNinguno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearCuadranteNinguno,VProyeccionTextCuadranteNinguno);
                OpcionOriginal(VProyeccionLinearCuadranteI,VProyeccionTextCuadranteI);
                OpcionOriginal(VProyeccionLinearCuadranteII,VProyeccionTextCuadranteII);
                OpcionOriginal(VProyeccionLinearCuadranteIII,VProyeccionTextCuadranteIII);
                OpcionOriginal(VProyeccionLinearCuadranteIV,VProyeccionTextCuadranteIV);
                VProyeccionCuadrante = 0;
            }
        });
        VProyeccionLinearCuadranteI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearCuadranteI,VProyeccionTextCuadranteI);
                OpcionOriginal(VProyeccionLinearCuadranteNinguno,VProyeccionTextCuadranteNinguno);
                OpcionOriginal(VProyeccionLinearCuadranteII,VProyeccionTextCuadranteII);
                OpcionOriginal(VProyeccionLinearCuadranteIII,VProyeccionTextCuadranteIII);
                OpcionOriginal(VProyeccionLinearCuadranteIV,VProyeccionTextCuadranteIV);
                VProyeccionCuadrante = 1;
            }
        });
        VProyeccionLinearCuadranteII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearCuadranteII,VProyeccionTextCuadranteII);
                OpcionOriginal(VProyeccionLinearCuadranteNinguno,VProyeccionTextCuadranteNinguno);
                OpcionOriginal(VProyeccionLinearCuadranteI,VProyeccionTextCuadranteI);
                OpcionOriginal(VProyeccionLinearCuadranteIII,VProyeccionTextCuadranteIII);
                OpcionOriginal(VProyeccionLinearCuadranteIV,VProyeccionTextCuadranteIV);
                VProyeccionCuadrante = 2;
            }
        });
        VProyeccionLinearCuadranteIII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearCuadranteIII,VProyeccionTextCuadranteIII);
                OpcionOriginal(VProyeccionLinearCuadranteNinguno,VProyeccionTextCuadranteNinguno);
                OpcionOriginal(VProyeccionLinearCuadranteI,VProyeccionTextCuadranteI);
                OpcionOriginal(VProyeccionLinearCuadranteII,VProyeccionTextCuadranteII);
                OpcionOriginal(VProyeccionLinearCuadranteIV,VProyeccionTextCuadranteIV);
                VProyeccionCuadrante = 3;
            }
        });
        VProyeccionLinearCuadranteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearCuadranteIV,VProyeccionTextCuadranteIV);
                OpcionOriginal(VProyeccionLinearCuadranteNinguno,VProyeccionTextCuadranteNinguno);
                OpcionOriginal(VProyeccionLinearCuadranteI,VProyeccionTextCuadranteI);
                OpcionOriginal(VProyeccionLinearCuadranteII,VProyeccionTextCuadranteII);
                OpcionOriginal(VProyeccionLinearCuadranteIII,VProyeccionTextCuadranteIII);
                VProyeccionCuadrante = 4;
            }
        });
        //endregion

        //region Efecto Seleccion - Direccion Total del vector
        //region Direccion
        VProyeccionLinearDireccionNinguno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearDireccionNinguno,VProyeccionTextDireccionNinguno);
                OpcionOriginal(VProyeccionLinearDireccionAsc,VProyeccionTextDireccionAsc);
                OpcionOriginal(VProyeccionLinearDireccionDesc,VProyeccionTextDireccionDesc);
                VProyeccionDireccion = "Ninguna";

                VProyeccionLinearApuntaAbajo.setVisibility(View.GONE);
                VProyeccionLinearApuntaArriba.setVisibility(View.GONE);
            }
        });
        VProyeccionLinearDireccionAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearDireccionAsc,VProyeccionTextDireccionAsc);
                OpcionOriginal(VProyeccionLinearDireccionNinguno,VProyeccionTextDireccionNinguno);
                OpcionOriginal(VProyeccionLinearDireccionDesc,VProyeccionTextDireccionDesc);
                VProyeccionDireccion = "Asc";
                VProyeccionLinearApuntaAbajo.setVisibility(View.VISIBLE);
                VProyeccionLinearApuntaArriba.setVisibility(View.VISIBLE);
            }
        });
        VProyeccionLinearDireccionDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearDireccionDesc,VProyeccionTextDireccionDesc);
                OpcionOriginal(VProyeccionLinearDireccionNinguno,VProyeccionTextDireccionNinguno);
                OpcionOriginal(VProyeccionLinearDireccionAsc,VProyeccionTextDireccionAsc);
                VProyeccionDireccion = "Desc";
                VProyeccionLinearApuntaAbajo.setVisibility(View.VISIBLE);
                VProyeccionLinearApuntaArriba.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        //region Apunta a
        VProyeccionLinearApuntaIzq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearApuntaIzq,VProyeccionTextApuntaIzq);
                OpcionOriginal(VProyeccionLinearApuntaArriba,VProyeccionTextApuntaArriba);
                OpcionOriginal(VProyeccionLinearApuntaDer,VProyeccionTextApuntaDer);
                OpcionOriginal(VProyeccionLinearApuntaAbajo,VProyeccionTextApuntaAbajo);
                VProyeccionApuntaA = "Izq";
            }
        });
        VProyeccionLinearApuntaDer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearApuntaDer,VProyeccionTextApuntaDer);
                OpcionOriginal(VProyeccionLinearApuntaArriba,VProyeccionTextApuntaArriba);
                OpcionOriginal(VProyeccionLinearApuntaIzq,VProyeccionTextApuntaIzq);
                OpcionOriginal(VProyeccionLinearApuntaAbajo,VProyeccionTextApuntaAbajo);
                VProyeccionApuntaA = "Der";
            }
        });
        VProyeccionLinearApuntaArriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearApuntaArriba,VProyeccionTextApuntaArriba);
                OpcionOriginal(VProyeccionLinearApuntaDer,VProyeccionTextApuntaDer);
                OpcionOriginal(VProyeccionLinearApuntaIzq,VProyeccionTextApuntaIzq);
                OpcionOriginal(VProyeccionLinearApuntaAbajo,VProyeccionTextApuntaAbajo);
                VProyeccionApuntaA = "Arriba";
            }
        });
        VProyeccionLinearApuntaAbajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearApuntaAbajo,VProyeccionTextApuntaAbajo);
                OpcionOriginal(VProyeccionLinearApuntaArriba,VProyeccionTextApuntaArriba);
                OpcionOriginal(VProyeccionLinearApuntaDer,VProyeccionTextApuntaDer);
                OpcionOriginal(VProyeccionLinearApuntaIzq,VProyeccionTextApuntaIzq);
                VProyeccionApuntaA = "Abajo";
            }
        });
        //endregion
        //endregion

        //region Efecto Seleccion - Angulo Respecto a
        VProyeccionLinearRespectoX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearRespectoX,VProyeccionTextRespectoX);
                OpcionOriginal(VProyeccionLinearRespectoY,VProyeccionTextRespectoY);
                VProyeccionangRespectoA = "x";
            }
        });
        VProyeccionLinearRespectoY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(VProyeccionLinearRespectoY,VProyeccionTextRespectoY);
                OpcionOriginal(VProyeccionLinearRespectoX,VProyeccionTextRespectoX);
                VProyeccionangRespectoA = "y";
            }
        });
        //endregion

        //region Efectos de escritura - angulo y fuerza
        VectorProyecciontxtAngulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                VectorProyeccionTextAngulo.setVisibility((VectorProyecciontxtAngulo.getText().toString().trim().length() > 0) ? View.VISIBLE : View.GONE);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        VectorProyecciontxtFuerza.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                VectorProyeccionTextFuerza.setVisibility((VectorProyecciontxtFuerza.getText().toString().trim().length() > 0) ? View.VISIBLE : View.GONE);
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //endregion
    }

    private void AgregarVectorProyeccion(Integer _cuadrante,String _direccion, String _apuntaa, EditText _fuerza, EditText _angulo, String _angRespectoa){

        csVectorProyeccion _newVector = new csVectorProyeccion();
        _newVector.setCuadrante(_cuadrante);
        _newVector.setDireccion(_direccion);
        _newVector.setApunta(_apuntaa);
        _newVector.setAngRespectoA(_angRespectoa);
        _newVector.setFuerza(Double.parseDouble(_fuerza.getText().toString()));
        _newVector.setAngulo(Double.parseDouble(_angulo.getText().toString()));
        myVectorProyeccion.add(_newVector);
        myVectorProyeccionDatos.add(_newVector);
    }
    private void EstiloOriginalVectorProyeccion(){

        OpcionSeleccionada(VProyeccionLinearCuadranteNinguno,VProyeccionTextCuadranteNinguno);
        OpcionOriginal(VProyeccionLinearCuadranteI,VProyeccionTextCuadranteI);
        OpcionOriginal(VProyeccionLinearCuadranteII,VProyeccionTextCuadranteII);
        OpcionOriginal(VProyeccionLinearCuadranteIII,VProyeccionTextCuadranteIII);
        OpcionOriginal(VProyeccionLinearCuadranteIV,VProyeccionTextCuadranteIV);
        OpcionOriginal(VProyeccionLinearDireccionNinguno,VProyeccionTextDireccionNinguno);
        OpcionOriginal(VProyeccionLinearDireccionAsc,VProyeccionTextDireccionAsc);
        OpcionOriginal(VProyeccionLinearDireccionDesc,VProyeccionTextDireccionDesc);
        OpcionOriginal(VProyeccionLinearApuntaIzq,VProyeccionTextApuntaIzq);
        OpcionOriginal(VProyeccionLinearApuntaDer,VProyeccionTextApuntaDer);
        OpcionOriginal(VProyeccionLinearApuntaArriba,VProyeccionTextApuntaArriba);
        OpcionOriginal(VProyeccionLinearApuntaAbajo,VProyeccionTextApuntaAbajo);
        OpcionOriginal(VProyeccionLinearRespectoX,VProyeccionTextRespectoX);
        OpcionOriginal(VProyeccionLinearRespectoY,VProyeccionTextRespectoY);

        //re-set valores iniciales
        VProyeccionCuadrante = 0;
        VProyeccionDireccion = "";
        VProyeccionApuntaA = "";
        VProyeccionangRespectoA = "";
        VectorProyecciontxtFuerza.setText("");
        VectorProyecciontxtAngulo.setText("");
    }
    private boolean VectorProyeccionValidar(){
        boolean _result = true;
        String _error = "Ingresa el valor";
        if (VectorProyecciontxtFuerza.getText().toString().isEmpty()) {
            VectorProyecciontxtFuerza.setError(_error);
            _result = false;
        }
        if (VectorProyecciontxtAngulo.getText().toString().isEmpty()) {
            VectorProyecciontxtAngulo.setError(_error);
            _result = false;
        }
        if (VProyeccionangRespectoA.isEmpty()) {
            Toast.makeText(CapturaDeDatos.this,"Seleccione respecto a que eje esta el ángulo",Toast.LENGTH_SHORT).show();
            _result = false;
        }
        if (VProyeccionApuntaA.isEmpty()) {
            Toast.makeText(CapturaDeDatos.this,"Seleccione hacia donde va el vector (Dirección)",Toast.LENGTH_SHORT).show();
            _result = false;
        }
        if (VProyeccionDireccion.isEmpty()) {
            Toast.makeText(CapturaDeDatos.this,"Seleccione la dirección del vector",Toast.LENGTH_SHORT).show();
            _result = false;
        }
        return _result;
    }
    //endregion

    //region ProductoConVectores
    private void InicializarProductoConVectores() {

        //region Inicializaciones
        ProductoConVectoresI1 = findViewById(R.id.ProductoConVectoresI1);
        ProductoConVectoresI2 = findViewById(R.id.ProductoConVectoresI2);
        ProductoConVectoresI3 = findViewById(R.id.ProductoConVectoresI3);
        ProductoConVectoresJ1 = findViewById(R.id.ProductoConVectoresJ1);
        ProductoConVectoresJ2 = findViewById(R.id.ProductoConVectoresJ2);
        ProductoConVectoresJ3 = findViewById(R.id.ProductoConVectoresJ3);
        ProductoConVectoresK1 = findViewById(R.id.ProductoConVectoresK1);
        ProductoConVectoresK2 = findViewById(R.id.ProductoConVectoresK2);
        ProductoConVectoresK3 = findViewById(R.id.ProductoConVectoresK3);

        ProductoConVectoresTextCruz = findViewById(R.id.ProductoConVectoresTextCruz);
        ProductoConVectoresTextPunto = findViewById(R.id.ProductoConVectoresTextPunto);
        ProductoConVectoresTextMixto = findViewById(R.id.ProductoConVectoresTextMixto);
        ProductoConVectoresTextCruzMatriz = findViewById(R.id.ProductoConVectoresTextCruzMatriz);
        ProductoConVectoresTextCruzEcuaciones = findViewById(R.id.ProductoConVectoresTextCruzEcuaciones);

        ProductoConVectoresLinearCruz = findViewById(R.id.ProductoConVectoresLinearCruz);
        ProductoConVectoresLinearPunto = findViewById(R.id.ProductoConVectoresLinearPunto);
        ProductoConVectoresLinearMixto = findViewById(R.id.ProductoConVectoresLinearMixto);
        ProductoConVectoresLinearVectorC = findViewById(R.id.ProductoConVectoresLinearVectorC);
        ProductoConVectoresbtnSolucion = findViewById(R.id.ProductoConVectoresbtnSolucion);
        ProductoConVectoresTipoCruz = findViewById(R.id.ProductoConVectoresTipoCruz);
        ProductoConVectoresLinearCruzMatriz = findViewById(R.id.ProductoConVectoresLinearCruzMatriz);
        ProductoConVectoresLinearCruzEcuaciones = findViewById(R.id.ProductoConVectoresLinearCruzEcuaciones);

        //endregion

        //region Efecto visual de seleccion
        ProductoConVectoresLinearCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(ProductoConVectoresLinearCruz,ProductoConVectoresTextCruz);
                OpcionOriginal(ProductoConVectoresLinearPunto,ProductoConVectoresTextPunto);
                OpcionOriginal(ProductoConVectoresLinearMixto,ProductoConVectoresTextMixto);
                ProductoConVectoresLinearVectorC.setVisibility(View.GONE);
               // ProductoConVectoresTipoCruz.setVisibility(View.VISIBLE);
                ProductoConVectoresTipoEjercicio = 2.0;

            }
        });
        ProductoConVectoresLinearPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(ProductoConVectoresLinearPunto,ProductoConVectoresTextPunto);
                OpcionOriginal(ProductoConVectoresLinearCruz,ProductoConVectoresTextCruz);
                OpcionOriginal(ProductoConVectoresLinearMixto,ProductoConVectoresTextMixto);
                ProductoConVectoresLinearVectorC.setVisibility(View.GONE);
                ProductoConVectoresTipoCruz.setVisibility(View.GONE);
                ProductoConVectoresTipoEjercicio = 1.0;
            }
        });
        ProductoConVectoresLinearMixto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(ProductoConVectoresLinearMixto,ProductoConVectoresTextMixto);
                OpcionOriginal(ProductoConVectoresLinearCruz,ProductoConVectoresTextCruz);
                OpcionOriginal(ProductoConVectoresLinearPunto,ProductoConVectoresTextPunto);
                ProductoConVectoresLinearVectorC.setVisibility(View.VISIBLE);
                ProductoConVectoresTipoCruz.setVisibility(View.GONE);
                ProductoConVectoresTipoEjercicio = 3.0;
            }
        });
        ProductoConVectoresLinearCruzMatriz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(ProductoConVectoresLinearCruzMatriz,ProductoConVectoresTextCruzMatriz);
                OpcionOriginal(ProductoConVectoresLinearCruzEcuaciones,ProductoConVectoresTextCruzEcuaciones);
                ProductoConVectoresTipoCruzTipo = 1.0;
            }
        });
        ProductoConVectoresLinearCruzEcuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcionSeleccionada(ProductoConVectoresLinearCruzEcuaciones,ProductoConVectoresTextCruzEcuaciones);
                OpcionOriginal(ProductoConVectoresLinearCruzMatriz,ProductoConVectoresTextCruzMatriz);
                ProductoConVectoresTipoCruzTipo = 0.0;
            }
        });
        //endregion

        //region Btn Solucion
        ProductoConVectoresbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ProductoConVectoresValidar(ProductoConVectoresTipoEjercicio.intValue())){

                    myArrayDatos.add(ProductoConVectoresTipoEjercicio);
                    myArrayDatos.add(Double.parseDouble(ProductoConVectoresI1.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(ProductoConVectoresJ1.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(ProductoConVectoresK1.getText().toString()));

                    myArrayDatos.add(Double.parseDouble(ProductoConVectoresI2.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(ProductoConVectoresJ2.getText().toString()));
                    myArrayDatos.add(Double.parseDouble(ProductoConVectoresK2.getText().toString()));

                    if (ProductoConVectoresTipoEjercicio.intValue() == 2){
                        myArrayDatos.add(ProductoConVectoresTipoCruzTipo);
                    }
                    if (ProductoConVectoresTipoEjercicio.intValue() == 3){
                        myArrayDatos.add(Double.parseDouble(ProductoConVectoresI3.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(ProductoConVectoresJ3.getText().toString()));
                        myArrayDatos.add(Double.parseDouble(ProductoConVectoresK3.getText().toString()));
                    }
                    String _result = ExercisesSolotionsDetail.MenuMetodos(myExerciseToSolve.getIdTema(),myArrayDatos);
//                    Toast.makeText(CapturaDeDatos.this,_result +"",Toast.LENGTH_SHORT).show();
                    openActivity();
                    myArrayDatos.clear();
                }
            }
        });
        //endregion
    }
    private boolean ProductoConVectoresValidar(Integer _case){
        boolean _result = true;
        String _error = "Ingresa el valor";
        if (ProductoConVectoresI1.getText().toString().isEmpty()) {
            ProductoConVectoresI1.setError(_error);
            _result = false;
        }
        if (ProductoConVectoresJ1.getText().toString().isEmpty()) {
            ProductoConVectoresJ1.setError(_error);
            _result = false;
        }
        if (ProductoConVectoresK1.getText().toString().isEmpty()) {
            ProductoConVectoresK1.setError(_error);
            _result = false;
        }
        if (ProductoConVectoresI2.getText().toString().isEmpty()) {
            ProductoConVectoresI2.setError(_error);
            _result = false;
        }
        if (ProductoConVectoresJ2.getText().toString().isEmpty()) {
            ProductoConVectoresJ2.setError(_error);
            _result = false;
        }
        if (ProductoConVectoresK2.getText().toString().isEmpty()) {
            ProductoConVectoresK2.setError(_error);
            _result = false;
        }
        if (_case.equals(3)){
            if (ProductoConVectoresI3.getText().toString().isEmpty()) {
                ProductoConVectoresI3.setError(_error);
                _result = false;
            }
            if (ProductoConVectoresJ3.getText().toString().isEmpty()) {
                ProductoConVectoresJ3.setError(_error);
                _result = false;
            }
            if (ProductoConVectoresK3.getText().toString().isEmpty()) {
                ProductoConVectoresK3.setError(_error);
                _result = false;
            }
        }
        return _result;
    }
    //endregion
    //endregion
    //endregion
}
