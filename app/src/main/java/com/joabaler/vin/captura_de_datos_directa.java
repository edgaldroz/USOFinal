package com.joabaler.vin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.joabaler.vin.AlgebraLibrary.solucionDirecta;
import com.joabaler.vin.Entidades.csMatriz;
import com.joabaler.vin.Entidades.csTemasContenido;
import com.joabaler.vin.Entidades.csVectorProyeccion;
import com.joabaler.vin.SQLite.ConexionSQLHelper;
import com.joabaler.vin.SQLite.EntidadesSQL.csEjerciciosGeneralesSQL;
import com.joabaler.vin.SQLite.ScriptDB;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class captura_de_datos_directa extends AppCompatActivity {
    private csTemasContenido myExerciseToSolve;
    private TextView lblCapturaNombreTema2;
    solucionDirecta sdirecta = new solucionDirecta();
    public static ArrayList<Double> arrayListDatos = new ArrayList<>();

    //region Division Numeros Complejos
    private EditText R1DC, I1DC, R2DC, I2DC;
    private LinearLayout OpcLinearDivComplejosbtnSolucion, LinearResultadoDivisionComplejos, LinearGuardarEjercicioDivComplejos, OpcLinear1DivisionComplejos;
    private TextView ResultadoDivComplejos;
    //endregion

    //region Producto Numeros Complejos
    private EditText R1PC, I1PC, R2PC, I2PC;
    private LinearLayout OpcLinearProdComplejosbtnSolucion, LinearResultadoProductoComplejos, LinearGuardarEjercicioProdComplejos, OpcLinear1ProductoComplejos;
    private TextView ResultadoProductoComplejos;
    //endregion

    //region Suma Numeros Complejos
    private EditText R1SC, I1SC, R2SC, I2SC, etOpcEscalar1Suma, etOpcEscalar2Suma, n1Suma, n2Suma;
    private LinearLayout OpcLinearSumaComplejosbtnSolucion, LinearResultadoSumaComplejos,
            LinearGuardarEjercicioSumaComplejos, OpcLinear1SumaComplejos, OpcLinearSinEscalarSuma, OpcLinearEscalarSuma,
            OpcLinearSumaEscalarN1, OpcLinearSumaEscalarN2;
    private TextView ResultadoSumaComplejos, tvOpcEscalarSuma, tvOpcSinEscalarSuma, tvViewResultadoSuma;
    //endregion

    //region Resta Numeros Complejos
    private EditText R1RC, I1RC, R2RC, I2RC, etOpcEscalar1Resta, etOpcEscalar2Resta, n1Resta, n2Resta;
    private LinearLayout OpcLinearRestaComplejosbtnSolucion, LinearResultadoRestaComplejos,
            LinearGuardarEjercicioRestaComplejos, OpcLinear1RestaComplejos, OpcLinearSinEscalarResta, OpcLinearEscalarResta,
            OpcLinearRestaEscalarN1, OpcLinearRestaEscalarN2;
    private TextView ResultadoRestaComplejos, tvOpcEscalarResta, tvOpcSinEscalarResta, tvViewResultadoResta;
    //endregion

    //region Potencia de I
    private EditText etOpcPotenciaI;
    private LinearLayout OpcLinear1PotenciasI, OpcPotenciaIbtnSolucion, LinearPotenciaIResultado, btnGuardarEjercicioPotenciaI;
    private TextView tvPotenciaResultado;
    //endregion

    //region Rectangular a Polar
    private EditText etOpcRealRectangularPolar, etOpcImaginarioRectangularPolar;
    private LinearLayout OpcRectangularpolarbtnSolucion, LinearRectangularPolarResultado, btnGuardarEjercicioRectangularPolar, OpcLinear1RectangularPolar;
    private TextView tvRectangularPolarResultado;
    //endregion

    //region Producto de Polares
    private EditText R1PP, Ang1PP, R2PP, Ang2PP;
    private LinearLayout OpcLinear1ProductoPolares, OpcProductoPolaresbtnSolucion,
            OpcLinearSiRaizProductoPolaresZ1, OpcLinearNoRaizProductoPolaresZ1,
            OpcLinearSiRaizProductoPolaresZ2, OpcLinearNoRaizProductoPolaresZ2,
            LinearProductoPolaresResultado, btnGuardarEjercicioProductoPolares;
    private TextView tvProductoPolaresR1, tvProductoPolaresR1Raiz,
            tvProductoPolaresR2, tvProductoPolaresR2Raiz, tvResultadoProductoPolares,
            tvOpcSiRaizProductoPolaresZ1, tvOpcNoRaizProductoPolaresZ1,
            tvOpcSiRaizProductoPolaresZ2, tvOpcNoRaizProductoPolaresZ2;
    private String raiz = "√", raiz2 = "√";
    //endregion

    //region Division de Polares
    private EditText R1DP, Ang1DP, R2DP, Ang2DP;
    private LinearLayout OpcLinear1DivisionPolares, OpcDivisionPolaresbtnSolucion,
            OpcLinearSiRaizDivisionPolaresZ1, OpcLinearNoRaizDivisionPolaresZ1,
            OpcLinearSiRaizDivisionPolaresZ2, OpcLinearNoRaizDivisionPolaresZ2,
            LinearDivisionPolaresResultado, btnGuardarEjercicioDivisionPolares;
    private TextView tvDivisionPolaresR1, tvDivisionPolaresR1Raiz,
            tvDivisionPolaresR2, tvDivisionPolaresR2Raiz, tvResultadoDivisionPolares,
            tvOpcSiRaizDivisionPolaresZ1, tvOpcNoRaizDivisionPolaresZ1,
            tvOpcSiRaizDivisionPolaresZ2, tvOpcNoRaizDivisionPolaresZ2;
    private String raizD = "√", raiz2D = "√";
    //endregion

    //region Raices Negativas
    private EditText etOpcRaicesNegativas;
    private LinearLayout OpcLinear1RaicesNegativas, OpcRaicesNegativasbtnSolucion, LinearRaicesNegativasResultado, btnGuardarEjercicioRaicesNegativas;
    private TextView tvRaicesNegativasResultado;
    //endregion

    //region Operaciones de Matrices
    private EditText etMatriz1Campo00, etMatriz1Campo01, etMatriz1Campo02, etMatriz1Campo03, etMatriz1Campo04, etMatriz1Campo05, etMatriz1Campo06,
            etMatriz1Campo10, etMatriz1Campo11, etMatriz1Campo12, etMatriz1Campo13, etMatriz1Campo14, etMatriz1Campo15, etMatriz1Campo16,
            etMatriz1Campo20, etMatriz1Campo21, etMatriz1Campo22, etMatriz1Campo23, etMatriz1Campo24, etMatriz1Campo25, etMatriz1Campo26,
            etMatriz1Campo30, etMatriz1Campo31, etMatriz1Campo32, etMatriz1Campo33, etMatriz1Campo34, etMatriz1Campo35, etMatriz1Campo36,
            etMatriz1Campo40, etMatriz1Campo41, etMatriz1Campo42, etMatriz1Campo43, etMatriz1Campo44, etMatriz1Campo45, etMatriz1Campo46,
            etMatriz1Campo50, etMatriz1Campo51, etMatriz1Campo52, etMatriz1Campo53, etMatriz1Campo54, etMatriz1Campo55, etMatriz1Campo56,
            etMatriz1Campo60, etMatriz1Campo61, etMatriz1Campo62, etMatriz1Campo63, etMatriz1Campo64, etMatriz1Campo65, etMatriz1Campo66, etProductxEscalar;

    private EditText etMatriz2Campo00, etMatriz2Campo01, etMatriz2Campo02, etMatriz2Campo03, etMatriz2Campo04, etMatriz2Campo05, etMatriz2Campo06,
            etMatriz2Campo10, etMatriz2Campo11, etMatriz2Campo12, etMatriz2Campo13, etMatriz2Campo14, etMatriz2Campo15, etMatriz2Campo16,
            etMatriz2Campo20, etMatriz2Campo21, etMatriz2Campo22, etMatriz2Campo23, etMatriz2Campo24, etMatriz2Campo25, etMatriz2Campo26,
            etMatriz2Campo30, etMatriz2Campo31, etMatriz2Campo32, etMatriz2Campo33, etMatriz2Campo34, etMatriz2Campo35, etMatriz2Campo36,
            etMatriz2Campo40, etMatriz2Campo41, etMatriz2Campo42, etMatriz2Campo43, etMatriz2Campo44, etMatriz2Campo45, etMatriz2Campo46,
            etMatriz2Campo50, etMatriz2Campo51, etMatriz2Campo52, etMatriz2Campo53, etMatriz2Campo54, etMatriz2Campo55, etMatriz2Campo56,
            etMatriz2Campo60, etMatriz2Campo61, etMatriz2Campo62, etMatriz2Campo63, etMatriz2Campo64, etMatriz2Campo65, etMatriz2Campo66;

    private LinearLayout OpcSumaMatricesbtnSolucion, OpcSumaMatricesbtnLimpiar, LinearSumaMatrizResultado, btnGuardarEjercicioSumaMatriz,
            LinearOpcDimensionMatrizSuma3x3, LinearOpcDimensionMatrizSuma4x4, LinearOpcDimensionMatrizSuma5x5,
            LinearOpcDimensionMatrizSuma6x6, LinearOpcDimensionMatrizSuma7x7,
            LinearOpcMatrizSUMA, LinearOpcMatrizRESTA, LinearOpcMatrizDETERMINANTE, LinearOpcMatrizProductxEscalar, LinearMatriz2Suma,
            LinearOpcMatrizPRODUCTO, LinearMatrizProductoDimension, LinearDimensionOcultar, LinearProductxEscalar, OpcLinearMatrices;

    private TextView tvSumaMatrizResultado,
            tvOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma5x5,
            tvOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma7x7, tvOpcMatrizSUMA, tvOpcMatrizRESTA, tvOpcMatrizDETERMINANTE, tvOpcMatrizProductxEscalar, tvMatriz2,
            tvOpcMatrizPRODUCTO, tvsumaMatriz, tvrestaMatriz, tvdeterminanteMatriz,lblOperacion;
    private int opc = 1, opcOperacion = 1;
    private double matriz1[][], matriz2[][], M1[][], M2[][];
    private String operacion="Suma.";
    private Spinner spinnerProductoM1_1, spinnerProductoM1_2, spinnerProductoM2_1, spinnerProductoM2_2;
    //endregion Operciones de Matrices

    //region Producto Punto Vectores
    private EditText etOpcI1ProductoPunto,etOpcJ1ProductoPunto,etOpcK1ProductoPunto,
                     etOpcI2ProductoPunto,etOpcJ2ProductoPunto,etOpcK2ProductoPunto;
    private  TextView tvProductoPuntoResultado;
    private LinearLayout OpcProductoPuntobtnSolucion,LinearProductoPuntoResultado,btnGuardarEjercicioProductoPunto,OpcLinear1ProductoPunto;
    //endregion

    //region Suma Vector Resultante
    private EditText etOpcI1SumaVectorR,etOpcJ1SumaVectorR,etOpcK1SumaVectorR,
                     etOpcI2SumaVectorR,etOpcJ2SumaVectorR,etOpcK2SumaVectorR,
                     etOpcN1SumaVectorR,etOpcM1SumaVectorR;
    private  TextView tvSumaVectorRResultado;
    private LinearLayout OpcSumaVectorRbtnSolucion,LinearSumaVectorRResultado,btnGuardarEjercicioSumaVectorR,OpcLinear1SumaVectorR;
    //endregion

    //region Resta Vector Resultante
    private EditText etOpcI1RestaVectorR,etOpcJ1RestaVectorR,etOpcK1RestaVectorR,
                     etOpcI2RestaVectorR,etOpcJ2RestaVectorR,etOpcK2RestaVectorR,
                     etOpcN1RestaVectorR,etOpcM1RestaVectorR;
    private  TextView tvRestaVectorRResultado;
    private LinearLayout OpcRestaVectorRbtnSolucion,LinearRestaVectorRResultado,btnGuardarEjercicioRestaVectorR,OpcLinear1RestaVectorR;
    //endregion

    //region Vector Unitario
    private EditText etOpcI1VectorUnitario,etOpcJ1VectorUnitario,etOpcK1VectorUnitario;
    private  TextView tvVectorUnitarioResultado;
    private LinearLayout OpcVectorUnitariobtnSolucion,LinearVectorUnitarioResultado,btnGuardarEjercicioVectorUnitario,OpcLinear1VectorUnitario;
    //endregion

    //region Angulo Entre Vectores
    private EditText etOpcI1AnguloEntreVectores,etOpcJ1AnguloEntreVectores,etOpcK1AnguloEntreVectores,
            etOpcI2AnguloEntreVectores,etOpcJ2AnguloEntreVectores,etOpcK2AnguloEntreVectores;
    private  TextView tvAnguloEntreVectoresResultado;
    private LinearLayout OpcAnguloEntreVectoresbtnSolucion,LinearAnguloEntreVectoresResultado,btnGuardarEjercicioAnguloEntreVectores,OpcLinear1AnguloEntreVectores;
    //endregion

    //private csTemasContenido myExerciseSolvented;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_captura_de_datos_directa);
        lblCapturaNombreTema2 = findViewById(R.id.lblCapturaNombreTema2);
        lblOperacion = findViewById(R.id.lblOperacion);

        InicializarOpcComplejoDivision();
        InicializarOpcComplejoProducto();
        InicializarOpcComplejoSuma();
        InicializarOpcComplejoResta();
        InicializarOpcPotenciaI();
        InicializarOpcRectangularPolar();
        InicializarOpcProductoPolares();
        InicializarOpcDivisionPolares();
        InicializarOpcRaicesNegativas();
        InicializarOpcSumaMatriz();
        InicializarProductoPuntoVectores();
        InicializarSumaVectorRVectores();
        InicializarRestaVectorRVectores();
        InicializarVectorUnitario();
        InicializarAnguloEntreVectores();
        getParameter();
    }

    //region Division Numeros Complejos
    public void InicializarOpcComplejoDivision() {
        //region Division Numeros Complejos

        R1DC = (EditText) findViewById(R.id.etOpcRealDiv1);
        I1DC = (EditText) findViewById(R.id.etOpcImaginarioDiv1);
        R2DC = (EditText) findViewById(R.id.etOpcRealDiv2);
        I2DC = (EditText) findViewById(R.id.etOpcImaginarioDiv2);
        LinearResultadoDivisionComplejos = findViewById(R.id.LinearDivisionComplejosResultado);
        LinearGuardarEjercicioDivComplejos = findViewById(R.id.btnGuardarEjercicioDivisionComplejos);
        OpcLinear1DivisionComplejos = findViewById(R.id.OpcLinear1DivisionComplejos);
        ResultadoDivComplejos = (TextView) findViewById(R.id.tvDivisionComplejoResultado);

        //OpcLinear1DivisionComplejos.setVisibility(View.GONE);//Oculta xml de DIvision Complejos

        OpcLinearDivComplejosbtnSolucion = findViewById(R.id.OpcDivComplejosbtnSolucion);
        //endregion

        OpcLinearDivComplejosbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarComplejoDivision()) {

                    LinearResultadoDivisionComplejos.setVisibility(View.VISIBLE);
                    double real1 = Double.parseDouble(R1DC.getText().toString());
                    double imaginario1 = Double.parseDouble(I1DC.getText().toString());
                    double real2 = Double.parseDouble(R2DC.getText().toString());
                    double imaginario2 = Double.parseDouble(I2DC.getText().toString());

                    ResultadoDivComplejos.setText(sdirecta.divisionComplejos(real1, imaginario1, real2, imaginario2));
                    arrayListDatos.add(Double.parseDouble(R1DC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I1DC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(R2DC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I2DC.getText().toString()));
                    R1DC.setText("");
                    I1DC.setText("");
                    R2DC.setText("");
                    I2DC.setText("");
                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollDivisionComplejos);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }
            }
        });

        LinearGuardarEjercicioDivComplejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearResultadoDivisionComplejos.setVisibility(View.GONE);
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarComplejoDivision() {
        boolean _result = true;
        String _error = "Ingresa el valor";

        if (R1DC.getText().toString().isEmpty()) {
            R1DC.setError(_error);
            _result = false;
        }
        if (I1DC.getText().toString().isEmpty()) {
            I1DC.setError(_error);
            _result = false;
        }
        if (R2DC.getText().toString().isEmpty()) {
            R2DC.setError(_error);
            _result = false;
        }
        if (I2DC.getText().toString().isEmpty()) {
            I2DC.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Producto Numeros Complejos
    public void InicializarOpcComplejoProducto() {
        //region Multiplicacion Numeros Complejos
        R1PC = (EditText) findViewById(R.id.etOpcRealProd1);
        I1PC = (EditText) findViewById(R.id.etOpcImaginarioProd1);
        R2PC = (EditText) findViewById(R.id.etOpcRealProd2);
        I2PC = (EditText) findViewById(R.id.etOpcImaginarioProd2);
        LinearResultadoProductoComplejos = findViewById(R.id.LinearProdComplejoResultado);
        LinearGuardarEjercicioProdComplejos = findViewById(R.id.btnGuardarEjercicioProductoComplejo);
        ResultadoProductoComplejos = (TextView) findViewById(R.id.tvResultadoProductoComplejo);
        OpcLinear1ProductoComplejos = findViewById(R.id.OpcLinear1ProductoComplejos);


        //OpcLinear1ProductoComplejos.setVisibility(View.GONE);//Oculta xml de producto Complejos

        OpcLinearProdComplejosbtnSolucion = findViewById(R.id.OpcProdComplejosbtnSolucion);
        //endregion

        OpcLinearProdComplejosbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarComplejoProducto()) {

                    LinearResultadoProductoComplejos.setVisibility(View.VISIBLE);
                    double real1 = Double.parseDouble(R1PC.getText().toString());
                    double imaginario1 = Double.parseDouble(I1PC.getText().toString());
                    double real2 = Double.parseDouble(R2PC.getText().toString());
                    double imaginario2 = Double.parseDouble(I2PC.getText().toString());

                    ResultadoProductoComplejos.setText(sdirecta.productoComplejos(real1, imaginario1, real2, imaginario2));

                    arrayListDatos.add(Double.parseDouble(R1PC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I1PC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(R2PC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I2PC.getText().toString()));

                    R1PC.setText("");
                    I1PC.setText("");
                    R2PC.setText("");
                    I2PC.setText("");
                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollProductoComplejos);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }
            }
        });

        LinearGuardarEjercicioProdComplejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearResultadoProductoComplejos.setVisibility(View.GONE);
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarComplejoProducto() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (R1PC.getText().toString().isEmpty()) {
            R1PC.setError(_error);
            _result = false;
        }
        if (I1PC.getText().toString().isEmpty()) {
            I1PC.setError(_error);
            _result = false;
        }
        if (R2PC.getText().toString().isEmpty()) {
            R2PC.setError(_error);
            _result = false;
        }
        if (I2PC.getText().toString().isEmpty()) {
            I2PC.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Suma Numeros Complejos

    public void InicializarOpcComplejoSuma() {
        //region Suma Numeros Complejos

        R1SC = (EditText) findViewById(R.id.etOpcRealSuma1);
        I1SC = (EditText) findViewById(R.id.etOpcImaginarioSuma1);
        R2SC = (EditText) findViewById(R.id.etOpcRealSuma2);
        I2SC = (EditText) findViewById(R.id.etOpcImaginarioSuma2);
        etOpcEscalar1Suma = findViewById(R.id.etOpcEscalar1Suma);
        etOpcEscalar2Suma = findViewById(R.id.etOpcEscalar2Suma);
        tvOpcSinEscalarSuma = findViewById(R.id.tvOpcSinEscalarSuma);
        tvOpcEscalarSuma = findViewById(R.id.tvOpcEscalarSuma);
        tvViewResultadoSuma = findViewById(R.id.tvViewResultadoSuma);
        n1Suma = findViewById(R.id.etOpcEscalar1Suma);
        n2Suma = findViewById(R.id.etOpcEscalar2Suma);
        n1Suma.setText("1");
        n2Suma.setText("1");

        LinearResultadoSumaComplejos = findViewById(R.id.LinearSumaComplejoResultado);
        LinearGuardarEjercicioSumaComplejos = findViewById(R.id.btnGuardarEjercicioSumaComplejo);
        ResultadoSumaComplejos = (TextView) findViewById(R.id.tvResultadoSumaComplejo);
        OpcLinear1SumaComplejos = findViewById(R.id.OpcLinear1SumaComplejos);
        OpcLinearEscalarSuma = findViewById(R.id.OpcLinearEscalarSuma);
        OpcLinearSinEscalarSuma = findViewById(R.id.OpcLinearSinEscalarSuma);
        OpcLinearSumaEscalarN1 = findViewById(R.id.OpcLinearSumaEscalarN1);
        OpcLinearSumaEscalarN2 = findViewById(R.id.OpcLinearSumaEscalarN2);

        //OpcLinear1SumaComplejos.setVisibility(View.GONE);//Oculta xml de producto Complejos

        LinearResultadoSumaComplejos.setVisibility(View.GONE);


        OpcLinearSumaComplejosbtnSolucion = findViewById(R.id.OpcSumaComplejosbtnSolucion);
        //endregion

        OpcLinearEscalarSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcLinearSumaEscalarN1.setVisibility(View.VISIBLE);
                OpcLinearSumaEscalarN2.setVisibility(View.VISIBLE);

                OpcionSeleccionada(OpcLinearEscalarSuma, tvOpcEscalarSuma);
                OpcionOriginal(OpcLinearSinEscalarSuma, tvOpcSinEscalarSuma);
                n1Suma.setText("");
                n2Suma.setText("");
            }
        });
        OpcLinearSinEscalarSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcLinearSumaEscalarN1.setVisibility(View.GONE);
                OpcLinearSumaEscalarN2.setVisibility(View.GONE);
                OpcionSeleccionada(OpcLinearSinEscalarSuma, tvOpcSinEscalarSuma);
                OpcionOriginal(OpcLinearEscalarSuma, tvOpcEscalarSuma);
                n1Suma.setText("1");
                n2Suma.setText("1");
            }
        });

        OpcLinearSumaComplejosbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarComplejoSuma()) {
                    double n1 = 0, n2 = 0;

                    n1 = Double.parseDouble(n1Suma.getText().toString());
                    n2 = Double.parseDouble(n2Suma.getText().toString());


                    LinearResultadoSumaComplejos.setVisibility(View.VISIBLE);
                    double real1 = Double.parseDouble(R1SC.getText().toString());
                    double imaginario1 = Double.parseDouble(I1SC.getText().toString());
                    double real2 = Double.parseDouble(R2SC.getText().toString());
                    double imaginario2 = Double.parseDouble(I2SC.getText().toString());
                    ResultadoSumaComplejos.setText(sdirecta.sumaComplejos(n1, real1, imaginario1, n2, real2, imaginario2));

                    arrayListDatos.add(Double.parseDouble(n1Suma.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(R1SC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I1SC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(n2Suma.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(R2SC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I2SC.getText().toString()));

                    R1SC.setText("");
                    I1SC.setText("");
                    R2SC.setText("");
                    I2SC.setText("");

                    if (OpcLinearSumaEscalarN1.getVisibility() == View.VISIBLE && OpcLinearSumaEscalarN2.getVisibility() == View.VISIBLE) {
                        n1Suma.setText("");
                        n2Suma.setText("");
                    }

                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollSuma);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }
            }
        });

        LinearGuardarEjercicioSumaComplejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearResultadoSumaComplejos.setVisibility(View.GONE);
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarComplejoSuma() {
        boolean _result = true;
        String _error = "Campo vacío";
        if (OpcLinearSumaEscalarN1.getVisibility() == View.VISIBLE) {
            if (n1Suma.getText().toString().isEmpty()) {
                n1Suma.setError(_error);
                _result = false;
            }
        }
        if (R1SC.getText().toString().isEmpty()) {
            R1SC.setError(_error);
            _result = false;
        }
        if (I1SC.getText().toString().isEmpty()) {
            I1SC.setError(_error);
            _result = false;
        }
        if (OpcLinearSumaEscalarN2.getVisibility() == View.VISIBLE) {
            if (n2Suma.getText().toString().isEmpty()) {
                n2Suma.setError(_error);
                _result = false;
            }
        }

        if (R2SC.getText().toString().isEmpty()) {
            R2SC.setError(_error);
            _result = false;
        }
        if (I2SC.getText().toString().isEmpty()) {
            I2SC.setError(_error);
            _result = false;
        }
        return _result;
    }

    //endregion

    //region Resta Numeros Complejos

    public void InicializarOpcComplejoResta() {
        //region Resta Numeros Complejos
        R1RC = (EditText) findViewById(R.id.etOpcRealResta1);
        I1RC = (EditText) findViewById(R.id.etOpcImaginarioResta1);
        R2RC = (EditText) findViewById(R.id.etOpcRealResta2);
        I2RC = (EditText) findViewById(R.id.etOpcImaginarioResta2);
        etOpcEscalar1Resta = findViewById(R.id.etOpcEscalar1Resta);
        etOpcEscalar2Resta = findViewById(R.id.etOpcEscalar2Resta);
        tvOpcSinEscalarResta = findViewById(R.id.tvOpcSinEscalarResta);
        tvOpcEscalarResta = findViewById(R.id.tvOpcEscalarResta);
        tvViewResultadoResta = findViewById(R.id.tvViewResultadoResta);
        n1Resta = findViewById(R.id.etOpcEscalar1Resta);
        n2Resta = findViewById(R.id.etOpcEscalar2Resta);
        n1Resta.setText("1");
        n2Resta.setText("1");

        LinearResultadoRestaComplejos = findViewById(R.id.LinearRestaComplejoResultado);
        LinearGuardarEjercicioRestaComplejos = findViewById(R.id.btnGuardarEjercicioRestaComplejo);
        ResultadoRestaComplejos = (TextView) findViewById(R.id.tvResultadoRestaComplejo);
        OpcLinear1RestaComplejos = findViewById(R.id.OpcLinear1RestaComplejos);
        OpcLinearEscalarResta = findViewById(R.id.OpcLinearEscalarResta);
        OpcLinearSinEscalarResta = findViewById(R.id.OpcLinearSinEscalarResta);
        OpcLinearRestaEscalarN1 = findViewById(R.id.OpcLinearRestaEscalarN1);
        OpcLinearRestaEscalarN2 = findViewById(R.id.OpcLinearRestaEscalarN2);

        // OpcLinear1RestaComplejos.setVisibility(View.GONE);//Oculta xml de resta Complejos

        OpcLinearRestaComplejosbtnSolucion = findViewById(R.id.OpcRestaComplejosbtnSolucion);
        //endregion

        OpcLinearEscalarResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcLinearRestaEscalarN1.setVisibility(View.VISIBLE);
                OpcLinearRestaEscalarN2.setVisibility(View.VISIBLE);

                OpcionSeleccionada(OpcLinearEscalarResta, tvOpcEscalarResta);
                OpcionOriginal(OpcLinearSinEscalarResta, tvOpcSinEscalarResta);
                n1Resta.setText("");
                n2Resta.setText("");
            }
        });
        OpcLinearSinEscalarResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpcLinearRestaEscalarN1.setVisibility(View.GONE);
                OpcLinearRestaEscalarN2.setVisibility(View.GONE);
                OpcionSeleccionada(OpcLinearSinEscalarResta, tvOpcSinEscalarResta);
                OpcionOriginal(OpcLinearEscalarResta, tvOpcEscalarResta);
                n1Resta.setText("1");
                n2Resta.setText("1");
            }
        });

        OpcLinearRestaComplejosbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarComplejoResta()) {
                    double n1 = 0, n2 = 0;

                    n1 = Double.parseDouble(n1Resta.getText().toString());
                    n2 = Double.parseDouble(n2Resta.getText().toString());


                    LinearResultadoRestaComplejos.setVisibility(View.VISIBLE);
                    double real1 = Double.parseDouble(R1RC.getText().toString());
                    double imaginario1 = Double.parseDouble(I1RC.getText().toString());
                    double real2 = Double.parseDouble(R2RC.getText().toString());
                    double imaginario2 = Double.parseDouble(I2RC.getText().toString());
                    ResultadoRestaComplejos.setText(sdirecta.restaComplejos(n1, real1, imaginario1, n2, real2, imaginario2));

                    arrayListDatos.add(Double.parseDouble(n1Resta.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(R1RC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I1RC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(n2Resta.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(R2RC.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(I2RC.getText().toString()));

                    R1RC.setText("");
                    I1RC.setText("");
                    R2RC.setText("");
                    I2RC.setText("");

                    if (OpcLinearRestaEscalarN1.getVisibility() == View.VISIBLE && OpcLinearRestaEscalarN2.getVisibility() == View.VISIBLE) {
                        n1Resta.setText("");
                        n2Resta.setText("");
                    }

                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollRestaComplejos);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }
            }
        });

        LinearGuardarEjercicioRestaComplejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearResultadoRestaComplejos.setVisibility(View.GONE);
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarComplejoResta() {
        boolean _result = true;
        String _error = "Campo vacío";
        if (OpcLinearRestaEscalarN1.getVisibility() == View.VISIBLE) {
            if (n1Resta.getText().toString().isEmpty()) {
                n1Resta.setError(_error);
                _result = false;
            }
        }
        if (R1RC.getText().toString().isEmpty()) {
            R1RC.setError(_error);
            _result = false;
        }
        if (I1RC.getText().toString().isEmpty()) {
            I1RC.setError(_error);
            _result = false;
        }
        if (OpcLinearRestaEscalarN2.getVisibility() == View.VISIBLE) {
            if (n2Resta.getText().toString().isEmpty()) {
                n2Resta.setError(_error);
                _result = false;
            }
        }

        if (R2RC.getText().toString().isEmpty()) {
            R2RC.setError(_error);
            _result = false;
        }
        if (I2RC.getText().toString().isEmpty()) {
            I2RC.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //----****-----
    //region Potencia de i
    public void InicializarOpcPotenciaI() {
        OpcLinear1PotenciasI = findViewById(R.id.OpcLinear1PotenciasI);
        OpcPotenciaIbtnSolucion = findViewById(R.id.OpcPotenciaIbtnSolucion);
        LinearPotenciaIResultado = findViewById(R.id.LinearPotenciaIResultado);
        btnGuardarEjercicioPotenciaI = findViewById(R.id.btnGuardarEjercicioPotenciaI);
        etOpcPotenciaI = findViewById(R.id.etOpcPotenciaI);
        tvPotenciaResultado = findViewById(R.id.tvPotenciaIResultado);

        //OpcLinear1PotenciasI.setVisibility(View.GONE); //Oculata Operacion potencia I

        OpcPotenciaIbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarPotenciaI()) {
                    String n = etOpcPotenciaI.getText().toString();
                    tvPotenciaResultado.setText(sdirecta.potenciasI(n));
                    arrayListDatos.add(Double.parseDouble(etOpcPotenciaI.getText().toString()));
                    etOpcPotenciaI.setText("");

                    LinearPotenciaIResultado.setVisibility(View.VISIBLE);
                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollPotenciasI);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }

            }
        });
        btnGuardarEjercicioPotenciaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearPotenciaIResultado.setVisibility(View.GONE);
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarPotenciaI() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcPotenciaI.getText().toString().isEmpty()) {
            etOpcPotenciaI.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Rectangular a Polar
    public void InicializarOpcRectangularPolar() {
        OpcRectangularpolarbtnSolucion = findViewById(R.id.OpcRectangularpolarbtnSolucion);
        LinearRectangularPolarResultado = findViewById(R.id.LinearRectangularPolarResultado);
        OpcLinear1RectangularPolar = findViewById(R.id.OpcLinear1RectangularPolar);
        btnGuardarEjercicioRectangularPolar = findViewById(R.id.btnGuardarEjercicioRectangularPolar);
        etOpcRealRectangularPolar = findViewById(R.id.etOpcRealRectangularPolar);
        etOpcImaginarioRectangularPolar = findViewById(R.id.etOpcImaginarioRectangularPolar);
        tvRectangularPolarResultado = findViewById(R.id.tvRectangularPolarResultado);

        //OpcLinear1RectangularPolar.setVisibility(View.GONE); //Oculta operacion rectangular a polar

        OpcRectangularpolarbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarRectangularPolar()) {
                    double r = Double.parseDouble(etOpcRealRectangularPolar.getText().toString());
                    double i = Double.parseDouble(etOpcImaginarioRectangularPolar.getText().toString());
                    tvRectangularPolarResultado.setText(sdirecta.rectangularAPolar(r, i));

                    arrayListDatos.add(Double.parseDouble(etOpcRealRectangularPolar.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcImaginarioRectangularPolar.getText().toString()));

                    etOpcRealRectangularPolar.setText("");
                    etOpcImaginarioRectangularPolar.setText("");

                    LinearRectangularPolarResultado.setVisibility(View.VISIBLE);
                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollRectangularPolar);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }

            }
        });
        btnGuardarEjercicioRectangularPolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearRectangularPolarResultado.setVisibility(View.GONE);
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarRectangularPolar() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcRealRectangularPolar.getText().toString().isEmpty()) {
            etOpcRealRectangularPolar.setError(_error);
            _result = false;
        }
        if (etOpcImaginarioRectangularPolar.getText().toString().isEmpty()) {
            etOpcImaginarioRectangularPolar.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Producto de Polares

    public void InicializarOpcProductoPolares() {
        //region Producto Polares
        R1PP = (EditText) findViewById(R.id.etOpcR1Productopolares);
        Ang1PP = (EditText) findViewById(R.id.etOpcAngulo1Productopolares);
        R2PP = (EditText) findViewById(R.id.etOpcR2Productopolares);
        Ang2PP = (EditText) findViewById(R.id.etOpcAngulo2Productopolares);

        tvProductoPolaresR1 = findViewById(R.id.tvProductoPolaresR1);
        tvProductoPolaresR1Raiz = findViewById(R.id.tvProductoPolaresR1Raiz);
        tvProductoPolaresR2 = findViewById(R.id.tvProductoPolaresR2);
        tvProductoPolaresR2Raiz = findViewById(R.id.tvProductoPolaresR2Raiz);
        tvResultadoProductoPolares = findViewById(R.id.tvResultadoProductoPolares);
        tvOpcSiRaizProductoPolaresZ1 = findViewById(R.id.tvOpcSiRaizProductoPolaresZ1);
        tvOpcNoRaizProductoPolaresZ1 = findViewById(R.id.tvOpcNoRaizProductoPolaresZ1);
        tvOpcSiRaizProductoPolaresZ2 = findViewById(R.id.tvOpcSiRaizProductoPolaresZ2);
        tvOpcNoRaizProductoPolaresZ2 = findViewById(R.id.tvOpcNoRaizProductoPolaresZ2);

        OpcLinear1ProductoPolares = findViewById(R.id.OpcLinear1ProductoPolares);
        OpcProductoPolaresbtnSolucion = findViewById(R.id.OpcProductoPolaresbtnSolucion);
        OpcLinearSiRaizProductoPolaresZ1 = findViewById(R.id.OpcLinearSiRaizProductoPolaresZ1);
        OpcLinearNoRaizProductoPolaresZ1 = findViewById(R.id.OpcLinearNoRaizProductoPolaresZ1);

        OpcLinearSiRaizProductoPolaresZ2 = findViewById(R.id.OpcLinearSiRaizProductoPolaresZ2);
        OpcLinearNoRaizProductoPolaresZ2 = findViewById(R.id.OpcLinearNoRaizProductoPolaresZ2);

        LinearProductoPolaresResultado = findViewById(R.id.LinearProductoPolaresResultado);
        btnGuardarEjercicioProductoPolares = findViewById(R.id.btnGuardarEjercicioProductoPolares);

        // OpcLinear1ProductoPolares.setVisibility(View.GONE);//Oculta xml de producto Polares

        //endregion

        OpcLinearSiRaizProductoPolaresZ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvProductoPolaresR1Raiz.setVisibility(View.VISIBLE);
                tvProductoPolaresR1.setVisibility(View.GONE);
                OpcionSeleccionada(OpcLinearSiRaizProductoPolaresZ1, tvOpcSiRaizProductoPolaresZ1);
                OpcionOriginal(OpcLinearNoRaizProductoPolaresZ1, tvOpcNoRaizProductoPolaresZ1);
                raiz = raiz;
            }
        });
        OpcLinearNoRaizProductoPolaresZ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvProductoPolaresR1Raiz.setVisibility(View.GONE);
                tvProductoPolaresR1.setVisibility(View.VISIBLE);
                OpcionSeleccionada(OpcLinearNoRaizProductoPolaresZ1, tvOpcNoRaizProductoPolaresZ1);
                OpcionOriginal(OpcLinearSiRaizProductoPolaresZ1, tvOpcSiRaizProductoPolaresZ1);

                raiz = "";
            }
        });
        OpcLinearSiRaizProductoPolaresZ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvProductoPolaresR2Raiz.setVisibility(View.VISIBLE);
                tvProductoPolaresR2.setVisibility(View.GONE);
                OpcionSeleccionada(OpcLinearSiRaizProductoPolaresZ2, tvOpcSiRaizProductoPolaresZ2);
                OpcionOriginal(OpcLinearNoRaizProductoPolaresZ2, tvOpcNoRaizProductoPolaresZ2);
                raiz2 = raiz2;
            }
        });
        OpcLinearNoRaizProductoPolaresZ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvProductoPolaresR2Raiz.setVisibility(View.GONE);
                tvProductoPolaresR2.setVisibility(View.VISIBLE);
                OpcionSeleccionada(OpcLinearNoRaizProductoPolaresZ2, tvOpcNoRaizProductoPolaresZ2);
                OpcionOriginal(OpcLinearSiRaizProductoPolaresZ2, tvOpcSiRaizProductoPolaresZ2);
                raiz2 = "";
            }
        });

        OpcProductoPolaresbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarProductoPolares()) {
                    String z1 = raiz + R1PP.getText().toString() + "cis" + Ang1PP.getText().toString();
                    String z2 = raiz2 + R2PP.getText().toString() + "cis" + Ang2PP.getText().toString();
                    tvResultadoProductoPolares.setText(sdirecta.productoPolares(z1, z2));
                    Double ifRaiz=0.0,ifRaiz2=0.0;

                    if (raiz.equals("√")){
                        ifRaiz=1.0;
                    }
                    else if (raiz.equals("")){
                        ifRaiz = 0.0;
                    }

                    if (raiz2.equals("√")){
                        ifRaiz2=1.0;
                    }
                    else if (raiz2.equals("")){
                        ifRaiz2 = 0.0;
                    }

                    arrayListDatos.add(ifRaiz);
                    arrayListDatos.add(Double.parseDouble(R1PP.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(Ang1PP.getText().toString()));

                    arrayListDatos.add(ifRaiz2);
                    arrayListDatos.add(Double.parseDouble(R2PP.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(Ang2PP.getText().toString()));

                    R1PP.setText("");
                    Ang1PP.setText("");
                    R2PP.setText("");
                    Ang2PP.setText("");
                    LinearProductoPolaresResultado.setVisibility(View.VISIBLE);

                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollProductoPolares);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }
            }
        });

        btnGuardarEjercicioProductoPolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearProductoPolaresResultado.setVisibility(View.GONE);
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollProductoPolares);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarProductoPolares() {
        boolean _result = true;
        String _error = "Campo vacío";
        if (R1PP.getText().toString().isEmpty()) {
            R1PP.setError(_error);
            _result = false;
        }
        if (Ang1PP.getText().toString().isEmpty()) {
            Ang1PP.setError(_error);
            _result = false;
        }
        if (R2PP.getText().toString().isEmpty()) {
            R2PP.setError(_error);
            _result = false;
        }
        if (Ang2PP.getText().toString().isEmpty()) {
            Ang2PP.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Division de Polares.....

    public void InicializarOpcDivisionPolares() {
        //region Producto Polares
        R1DP = (EditText) findViewById(R.id.etOpcR1Divisionpolares);
        Ang1DP = (EditText) findViewById(R.id.etOpcAngulo1Divisionpolares);
        R2DP = (EditText) findViewById(R.id.etOpcR2Divisionpolares);
        Ang2DP = (EditText) findViewById(R.id.etOpcAngulo2Divisionpolares);

        tvDivisionPolaresR1 = findViewById(R.id.tvDivisionPolaresR1);
        tvDivisionPolaresR1Raiz = findViewById(R.id.tvDivisionPolaresR1Raiz);
        tvDivisionPolaresR2 = findViewById(R.id.tvDivisionPolaresR2);
        tvDivisionPolaresR2Raiz = findViewById(R.id.tvDivisionPolaresR2Raiz);
        tvResultadoDivisionPolares = findViewById(R.id.tvResultadoDivisionPolares);
        tvOpcSiRaizDivisionPolaresZ1 = findViewById(R.id.tvOpcSiRaizDivisionPolaresZ1);
        tvOpcNoRaizDivisionPolaresZ1 = findViewById(R.id.tvOpcNoRaizDivisionPolaresZ1);
        tvOpcSiRaizDivisionPolaresZ2 = findViewById(R.id.tvOpcSiRaizDivisionPolaresZ2);
        tvOpcNoRaizDivisionPolaresZ2 = findViewById(R.id.tvOpcNoRaizDivisionPolaresZ2);

        OpcLinear1DivisionPolares = findViewById(R.id.OpcLinear1DivisionPolares);
        OpcDivisionPolaresbtnSolucion = findViewById(R.id.OpcDivisionPolaresbtnSolucion);
        OpcLinearSiRaizDivisionPolaresZ1 = findViewById(R.id.OpcLinearSiRaizDivisionPolaresZ1);
        OpcLinearNoRaizDivisionPolaresZ1 = findViewById(R.id.OpcLinearNoRaizDivisionPolaresZ1);

        OpcLinearSiRaizDivisionPolaresZ2 = findViewById(R.id.OpcLinearSiRaizDivisionPolaresZ2);
        OpcLinearNoRaizDivisionPolaresZ2 = findViewById(R.id.OpcLinearNoRaizDivisionPolaresZ2);

        LinearDivisionPolaresResultado = findViewById(R.id.LinearDivisionPolaresResultado);
        btnGuardarEjercicioDivisionPolares = findViewById(R.id.btnGuardarEjercicioDivisionPolares);

        // OpcLinear1DivisionPolares.setVisibility(View.GONE);//Oculta xml de division Polares

        //endregion

        OpcLinearSiRaizDivisionPolaresZ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDivisionPolaresR1Raiz.setVisibility(View.VISIBLE);
                tvDivisionPolaresR1.setVisibility(View.GONE);
                OpcionSeleccionada(OpcLinearSiRaizDivisionPolaresZ1, tvOpcSiRaizDivisionPolaresZ1);
                OpcionOriginal(OpcLinearNoRaizDivisionPolaresZ1, tvOpcNoRaizDivisionPolaresZ1);
                raizD = raizD;
            }
        });
        OpcLinearNoRaizDivisionPolaresZ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDivisionPolaresR1Raiz.setVisibility(View.GONE);
                tvDivisionPolaresR1.setVisibility(View.VISIBLE);
                OpcionSeleccionada(OpcLinearNoRaizDivisionPolaresZ1, tvOpcNoRaizDivisionPolaresZ1);
                OpcionOriginal(OpcLinearSiRaizDivisionPolaresZ1, tvOpcSiRaizDivisionPolaresZ1);

                raizD = "";
            }
        });
        OpcLinearSiRaizDivisionPolaresZ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDivisionPolaresR2Raiz.setVisibility(View.VISIBLE);
                tvDivisionPolaresR2.setVisibility(View.GONE);
                OpcionSeleccionada(OpcLinearSiRaizDivisionPolaresZ2, tvOpcSiRaizDivisionPolaresZ2);
                OpcionOriginal(OpcLinearNoRaizDivisionPolaresZ2, tvOpcNoRaizDivisionPolaresZ2);
                raiz2D = raiz2D;
            }
        });
        OpcLinearNoRaizDivisionPolaresZ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDivisionPolaresR2Raiz.setVisibility(View.GONE);
                tvDivisionPolaresR2.setVisibility(View.VISIBLE);
                OpcionSeleccionada(OpcLinearNoRaizDivisionPolaresZ2, tvOpcNoRaizDivisionPolaresZ2);
                OpcionOriginal(OpcLinearSiRaizDivisionPolaresZ2, tvOpcSiRaizDivisionPolaresZ2);
                raiz2D = "";
            }
        });

        OpcDivisionPolaresbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarDivisionPolares()) {
                    String z1 = raizD + R1DP.getText().toString() + "cis" + Ang1DP.getText().toString();
                    String z2 = raiz2D + R2DP.getText().toString() + "cis" + Ang2DP.getText().toString();
                    tvResultadoDivisionPolares.setText(sdirecta.divisionPolares(z1, z2));

                    Double ifRaiz=0.0,ifRaiz2=0.0;

                    if (raizD.equals("√")){
                        ifRaiz=1.0;
                    }
                    else if (raizD.equals("")){
                        ifRaiz = 0.0;
                    }

                    if (raiz2D.equals("√")){
                        ifRaiz2=1.0;
                    }
                    else if (raiz2D.equals("")){
                        ifRaiz2 = 0.0;
                    }

                    arrayListDatos.add(ifRaiz);
                    arrayListDatos.add(Double.parseDouble(R1DP.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(Ang1DP.getText().toString()));

                    arrayListDatos.add(ifRaiz2);
                    arrayListDatos.add(Double.parseDouble(R2DP.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(Ang2DP.getText().toString()));

                    R1DP.setText("");
                    Ang1DP.setText("");
                    R2DP.setText("");
                    Ang2DP.setText("");
                    LinearDivisionPolaresResultado.setVisibility(View.VISIBLE);

                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollDivisionPolares);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }
            }
        });

        btnGuardarEjercicioDivisionPolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearDivisionPolaresResultado.setVisibility(View.GONE);
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollDivisionPolares);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarDivisionPolares() {
        boolean _result = true;
        String _error = "Campo vacío";
        if (R1DP.getText().toString().isEmpty()) {
            R1DP.setError(_error);
            _result = false;
        }
        if (Ang1DP.getText().toString().isEmpty()) {
            Ang1DP.setError(_error);
            _result = false;
        }
        if (R2DP.getText().toString().isEmpty()) {
            R2DP.setError(_error);
            _result = false;
        }
        if (Ang2DP.getText().toString().isEmpty()) {
            Ang2DP.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Raices Negativas
    public void InicializarOpcRaicesNegativas() {
        OpcLinear1RaicesNegativas = findViewById(R.id.OpcLinear1RaicesNegativas);
        OpcRaicesNegativasbtnSolucion = findViewById(R.id.OpcRaicesNegativasbtnSolucion);
        LinearRaicesNegativasResultado = findViewById(R.id.LinearRaicesNegativasResultado);
        btnGuardarEjercicioRaicesNegativas = findViewById(R.id.btnGuardarEjercicioRaicesNegativas);
        etOpcRaicesNegativas = findViewById(R.id.etOpcRaicesNegativas);
        tvRaicesNegativasResultado = findViewById(R.id.tvRaicesNegativasResultado);

        //OpcLinear1RaicesNegativas.setVisibility(View.GONE);//oculta Operacion raices negativas

        OpcRaicesNegativasbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarRaicesNegativas()) {
                    String n = "√" + etOpcRaicesNegativas.getText().toString();
                    tvRaicesNegativasResultado.setText(sdirecta.raicesNegativas(n));

                    arrayListDatos.add(Double.parseDouble(etOpcRaicesNegativas.getText().toString()));

                    etOpcRaicesNegativas.setText("");

                    LinearRaicesNegativasResultado.setVisibility(View.VISIBLE);
                    final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollRaicesNegativas);
                    myScroller.post(new Runnable() {
                        @Override
                        public void run() {
                            myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                        }
                    });
                }

            }
        });

        btnGuardarEjercicioRaicesNegativas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearRaicesNegativasResultado.setVisibility(View.GONE);
                SaveExcerciseGeneral();
            }
        });
    }

    private Boolean validarRaicesNegativas() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcRaicesNegativas.getText().toString().isEmpty()) {
            etOpcRaicesNegativas.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Operaciones con Matrices + - * y Det
    public void InicializarOpcSumaMatriz() {
        LinearOpcDimensionMatrizSuma3x3 = findViewById(R.id.LinearOpcDimensionMatrizSuma3x3);
        LinearOpcDimensionMatrizSuma4x4 = findViewById(R.id.LinearOpcDimensionMatrizSuma4x4);
        LinearOpcDimensionMatrizSuma5x5 = findViewById(R.id.LinearOpcDimensionMatrizSuma5x5);
        LinearOpcDimensionMatrizSuma6x6 = findViewById(R.id.LinearOpcDimensionMatrizSuma6x6);
        LinearOpcDimensionMatrizSuma7x7 = findViewById(R.id.LinearOpcDimensionMatrizSuma7x7);
        OpcLinearMatrices = findViewById(R.id.OpcLinearMatrices);

        final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
        myScroller.post(new Runnable() {
            @Override
            public void run() {
                myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
            }
        });

        //OpcLinearMatrices.setVisibility(View.GONE);//Oculta operaciones matrices

        LinearMatrizProductoDimension = findViewById(R.id.LinearMatrizProductoDimension);

        LinearOpcMatrizSUMA = findViewById(R.id.LinearOpcMatrizSUMA);
        LinearOpcMatrizRESTA = findViewById(R.id.LinearOpcMatrizRESTA);
        LinearOpcMatrizDETERMINANTE = findViewById(R.id.LinearOpcMatrizDETERMINANTE);
        LinearMatriz2Suma = findViewById(R.id.LinearMatriz2Suma);
        LinearOpcMatrizPRODUCTO = findViewById(R.id.LinearOpcMatrizPRODUCTO);
        LinearOpcMatrizProductxEscalar = findViewById(R.id.LinearOpcMatrizProductxEscalar);

        tvOpcMatrizSUMA = findViewById(R.id.tvOpcMatrizSUMA);
        tvOpcMatrizRESTA = findViewById(R.id.tvOpcMatrizRESTA);
        tvOpcMatrizPRODUCTO = findViewById(R.id.tvOpcMatrizPRODUCTO);
        tvOpcMatrizDETERMINANTE = findViewById(R.id.tvOpcMatrizDETERMINANTE);
        tvOpcMatrizProductxEscalar = findViewById(R.id.tvOpcMatrizProductxEscalar);
        tvMatriz2 = findViewById(R.id.tvMatriz2);

        lblOperacion.setVisibility(View.VISIBLE);
        lblOperacion.setText(operacion);

        tvsumaMatriz = findViewById(R.id.tvsumaMatriz);
        tvrestaMatriz = findViewById(R.id.tvrestaMatriz);
        tvdeterminanteMatriz = findViewById(R.id.tvdeterminanteMatriz);

        OpcSumaMatricesbtnSolucion = findViewById(R.id.OpcSumaMatricesbtnSolucion);
        OpcSumaMatricesbtnLimpiar = findViewById(R.id.OpcSumaMatricesbtnLimpiar);
        LinearSumaMatrizResultado = findViewById(R.id.LinearSumaMatrizResultado);
        btnGuardarEjercicioSumaMatriz = findViewById(R.id.btnGuardarEjercicioSumaMatriz);

        LinearDimensionOcultar = findViewById(R.id.LinearDimensionOcultar);
        LinearProductxEscalar = findViewById(R.id.LinearProductxEscalar);

        spinnerProductoM1_1 = findViewById(R.id.spinnerProductoM1_1);
        spinnerProductoM1_2 = findViewById(R.id.spinnerProductoM1_2);
        spinnerProductoM2_1 = findViewById(R.id.spinnerProductoM2_1);
        spinnerProductoM2_2 = findViewById(R.id.spinnerProductoM2_2);

        //region Area de spinner
        String[] nums = {"3", "4", "5", "6", "7"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item_producto_m, nums);
        spinnerProductoM1_1.setAdapter(adapter);
        spinnerProductoM1_2.setAdapter(adapter);
        spinnerProductoM2_1.setAdapter(adapter);
        spinnerProductoM2_2.setAdapter(adapter);

        spinnerProductoM1_1();
        spinnerProductoM1_2();
        spinnerProductoM2_1();
        spinnerProductoM2_2();

        //endregion

        //region Matriz 1
        etProductxEscalar = findViewById(R.id.etProductxEscalar);
        etMatriz1Campo00 = findViewById(R.id.etMatriz1Campo00);
        etMatriz1Campo01 = findViewById(R.id.etMatriz1Campo01);
        etMatriz1Campo02 = findViewById(R.id.etMatriz1Campo02);
        etMatriz1Campo03 = findViewById(R.id.etMatriz1Campo03);
        etMatriz1Campo04 = findViewById(R.id.etMatriz1Campo04);
        etMatriz1Campo05 = findViewById(R.id.etMatriz1Campo05);
        etMatriz1Campo06 = findViewById(R.id.etMatriz1Campo06);

        etMatriz1Campo10 = findViewById(R.id.etMatriz1Campo10);
        etMatriz1Campo11 = findViewById(R.id.etMatriz1Campo11);
        etMatriz1Campo12 = findViewById(R.id.etMatriz1Campo12);
        etMatriz1Campo13 = findViewById(R.id.etMatriz1Campo13);
        etMatriz1Campo14 = findViewById(R.id.etMatriz1Campo14);
        etMatriz1Campo15 = findViewById(R.id.etMatriz1Campo15);
        etMatriz1Campo16 = findViewById(R.id.etMatriz1Campo16);

        etMatriz1Campo20 = findViewById(R.id.etMatriz1Campo20);
        etMatriz1Campo21 = findViewById(R.id.etMatriz1Campo21);
        etMatriz1Campo22 = findViewById(R.id.etMatriz1Campo22);
        etMatriz1Campo23 = findViewById(R.id.etMatriz1Campo23);
        etMatriz1Campo24 = findViewById(R.id.etMatriz1Campo24);
        etMatriz1Campo25 = findViewById(R.id.etMatriz1Campo25);
        etMatriz1Campo26 = findViewById(R.id.etMatriz1Campo26);

        etMatriz1Campo30 = findViewById(R.id.etMatriz1Campo30);
        etMatriz1Campo31 = findViewById(R.id.etMatriz1Campo31);
        etMatriz1Campo32 = findViewById(R.id.etMatriz1Campo32);
        etMatriz1Campo33 = findViewById(R.id.etMatriz1Campo33);
        etMatriz1Campo34 = findViewById(R.id.etMatriz1Campo34);
        etMatriz1Campo35 = findViewById(R.id.etMatriz1Campo35);
        etMatriz1Campo36 = findViewById(R.id.etMatriz1Campo36);

        etMatriz1Campo40 = findViewById(R.id.etMatriz1Campo40);
        etMatriz1Campo41 = findViewById(R.id.etMatriz1Campo41);
        etMatriz1Campo42 = findViewById(R.id.etMatriz1Campo42);
        etMatriz1Campo43 = findViewById(R.id.etMatriz1Campo43);
        etMatriz1Campo44 = findViewById(R.id.etMatriz1Campo44);
        etMatriz1Campo45 = findViewById(R.id.etMatriz1Campo45);
        etMatriz1Campo46 = findViewById(R.id.etMatriz1Campo46);

        etMatriz1Campo50 = findViewById(R.id.etMatriz1Campo50);
        etMatriz1Campo51 = findViewById(R.id.etMatriz1Campo51);
        etMatriz1Campo52 = findViewById(R.id.etMatriz1Campo52);
        etMatriz1Campo53 = findViewById(R.id.etMatriz1Campo53);
        etMatriz1Campo54 = findViewById(R.id.etMatriz1Campo54);
        etMatriz1Campo55 = findViewById(R.id.etMatriz1Campo55);
        etMatriz1Campo56 = findViewById(R.id.etMatriz1Campo56);

        etMatriz1Campo60 = findViewById(R.id.etMatriz1Campo60);
        etMatriz1Campo61 = findViewById(R.id.etMatriz1Campo61);
        etMatriz1Campo62 = findViewById(R.id.etMatriz1Campo62);
        etMatriz1Campo63 = findViewById(R.id.etMatriz1Campo63);
        etMatriz1Campo64 = findViewById(R.id.etMatriz1Campo64);
        etMatriz1Campo65 = findViewById(R.id.etMatriz1Campo65);
        etMatriz1Campo66 = findViewById(R.id.etMatriz1Campo66);
        //endregion

        //region Matriz 2
        etMatriz2Campo00 = findViewById(R.id.etMatriz2Campo00);
        etMatriz2Campo01 = findViewById(R.id.etMatriz2Campo01);
        etMatriz2Campo02 = findViewById(R.id.etMatriz2Campo02);
        etMatriz2Campo03 = findViewById(R.id.etMatriz2Campo03);
        etMatriz2Campo04 = findViewById(R.id.etMatriz2Campo04);
        etMatriz2Campo05 = findViewById(R.id.etMatriz2Campo05);
        etMatriz2Campo06 = findViewById(R.id.etMatriz2Campo06);

        etMatriz2Campo10 = findViewById(R.id.etMatriz2Campo10);
        etMatriz2Campo11 = findViewById(R.id.etMatriz2Campo11);
        etMatriz2Campo12 = findViewById(R.id.etMatriz2Campo12);
        etMatriz2Campo13 = findViewById(R.id.etMatriz2Campo13);
        etMatriz2Campo14 = findViewById(R.id.etMatriz2Campo14);
        etMatriz2Campo15 = findViewById(R.id.etMatriz2Campo15);
        etMatriz2Campo16 = findViewById(R.id.etMatriz2Campo16);

        etMatriz2Campo20 = findViewById(R.id.etMatriz2Campo20);
        etMatriz2Campo21 = findViewById(R.id.etMatriz2Campo21);
        etMatriz2Campo22 = findViewById(R.id.etMatriz2Campo22);
        etMatriz2Campo23 = findViewById(R.id.etMatriz2Campo23);
        etMatriz2Campo24 = findViewById(R.id.etMatriz2Campo24);
        etMatriz2Campo25 = findViewById(R.id.etMatriz2Campo25);
        etMatriz2Campo26 = findViewById(R.id.etMatriz2Campo26);

        etMatriz2Campo30 = findViewById(R.id.etMatriz2Campo30);
        etMatriz2Campo31 = findViewById(R.id.etMatriz2Campo31);
        etMatriz2Campo32 = findViewById(R.id.etMatriz2Campo32);
        etMatriz2Campo33 = findViewById(R.id.etMatriz2Campo33);
        etMatriz2Campo34 = findViewById(R.id.etMatriz2Campo34);
        etMatriz2Campo35 = findViewById(R.id.etMatriz2Campo35);
        etMatriz2Campo36 = findViewById(R.id.etMatriz2Campo36);

        etMatriz2Campo40 = findViewById(R.id.etMatriz2Campo40);
        etMatriz2Campo41 = findViewById(R.id.etMatriz2Campo41);
        etMatriz2Campo42 = findViewById(R.id.etMatriz2Campo42);
        etMatriz2Campo43 = findViewById(R.id.etMatriz2Campo43);
        etMatriz2Campo44 = findViewById(R.id.etMatriz2Campo44);
        etMatriz2Campo45 = findViewById(R.id.etMatriz2Campo45);
        etMatriz2Campo46 = findViewById(R.id.etMatriz2Campo46);

        etMatriz2Campo50 = findViewById(R.id.etMatriz2Campo50);
        etMatriz2Campo51 = findViewById(R.id.etMatriz2Campo51);
        etMatriz2Campo52 = findViewById(R.id.etMatriz2Campo52);
        etMatriz2Campo53 = findViewById(R.id.etMatriz2Campo53);
        etMatriz2Campo54 = findViewById(R.id.etMatriz2Campo54);
        etMatriz2Campo55 = findViewById(R.id.etMatriz2Campo55);
        etMatriz2Campo56 = findViewById(R.id.etMatriz2Campo56);

        etMatriz2Campo60 = findViewById(R.id.etMatriz2Campo60);
        etMatriz2Campo61 = findViewById(R.id.etMatriz2Campo61);
        etMatriz2Campo62 = findViewById(R.id.etMatriz2Campo62);
        etMatriz2Campo63 = findViewById(R.id.etMatriz2Campo63);
        etMatriz2Campo64 = findViewById(R.id.etMatriz2Campo64);
        etMatriz2Campo65 = findViewById(R.id.etMatriz2Campo65);
        etMatriz2Campo66 = findViewById(R.id.etMatriz2Campo66);
        //endregion

        //region Dimensiones de matrices opciones
        tvSumaMatrizResultado = findViewById(R.id.tvSumaMatrizResultado);
        tvOpcDimensionMatrizSuma3x3 = findViewById(R.id.tvOpcDimensionMatrizSuma3x3);
        tvOpcDimensionMatrizSuma4x4 = findViewById(R.id.tvOpcDimensionMatrizSuma4x4);
        tvOpcDimensionMatrizSuma5x5 = findViewById(R.id.tvOpcDimensionMatrizSuma5x5);
        tvOpcDimensionMatrizSuma6x6 = findViewById(R.id.tvOpcDimensionMatrizSuma6x6);
        tvOpcDimensionMatrizSuma7x7 = findViewById(R.id.tvOpcDimensionMatrizSuma7x7);
        //endregion
        tvsumaMatriz.setVisibility(View.VISIBLE);

        //region LinearOpcMatrizSUMA.setOnClickListener
        LinearOpcMatrizSUMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacion = "Suma.";
                lblOperacion.setVisibility(View.VISIBLE);
                lblOperacion.setText(operacion);
                opcOperacion = 1;
                OpcionSeleccionada(LinearOpcMatrizSUMA, tvOpcMatrizSUMA);
                OpcionOriginal(LinearOpcMatrizRESTA, tvOpcMatrizRESTA);
                OpcionOriginal(LinearOpcMatrizDETERMINANTE, tvOpcMatrizDETERMINANTE);
                OpcionOriginal(LinearOpcMatrizPRODUCTO, tvOpcMatrizPRODUCTO);
                OpcionOriginal(LinearOpcMatrizProductxEscalar, tvOpcMatrizProductxEscalar);
                LinearMatriz2Suma.setVisibility(View.VISIBLE);
                tvMatriz2.setVisibility(View.VISIBLE);

                tvsumaMatriz.setVisibility(View.VISIBLE);
                tvrestaMatriz.setVisibility(View.GONE);
                tvdeterminanteMatriz.setVisibility(View.GONE);

                etProductxEscalar.setVisibility(View.GONE);

                LinearDimensionOcultar.setVisibility(View.VISIBLE);
                LinearMatrizProductoDimension.setVisibility(View.GONE);
                LinearProductxEscalar.setVisibility(View.GONE);
                clearErrorM1xM2();

                OpcionSeleccionada(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);

                invisibleM17x7();
                invisibleM27x7();
                limpiarM1xM2();
                etMatriz1Campo00.requestFocus();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                OpcSumaMatricesbtnLimpiar.setVisibility(View.GONE);

                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
            }
        });
        //endregion

        //region LinearOpcMatrizRESTA.setOnClickListener
        LinearOpcMatrizRESTA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblOperacion.setVisibility(View.VISIBLE);
                operacion = "Resta.";
                lblOperacion.setText(operacion);
                opcOperacion = 2;
                OpcionOriginal(LinearOpcMatrizSUMA, tvOpcMatrizSUMA);
                OpcionSeleccionada(LinearOpcMatrizRESTA, tvOpcMatrizRESTA);
                OpcionOriginal(LinearOpcMatrizDETERMINANTE, tvOpcMatrizDETERMINANTE);
                OpcionOriginal(LinearOpcMatrizPRODUCTO, tvOpcMatrizPRODUCTO);
                OpcionOriginal(LinearOpcMatrizProductxEscalar, tvOpcMatrizProductxEscalar);
                clearErrorM1xM2();
                LinearMatriz2Suma.setVisibility(View.VISIBLE);
                tvMatriz2.setVisibility(View.VISIBLE);
                LinearDimensionOcultar.setVisibility(View.VISIBLE);
                LinearMatrizProductoDimension.setVisibility(View.GONE);

                tvsumaMatriz.setVisibility(View.GONE);
                tvrestaMatriz.setVisibility(View.VISIBLE);
                tvdeterminanteMatriz.setVisibility(View.GONE);

                etProductxEscalar.setVisibility(View.GONE);

                OpcionSeleccionada(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);

                invisibleM17x7();
                invisibleM27x7();
                limpiarM1xM2();
                etMatriz1Campo00.requestFocus();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                OpcSumaMatricesbtnLimpiar.setVisibility(View.GONE);
                LinearProductxEscalar.setVisibility(View.GONE);
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
            }
        });
        //endregion

        //region LinearOpcMatrizDETERMINANTE.setOnClickListener
        LinearOpcMatrizDETERMINANTE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblOperacion.setVisibility(View.VISIBLE);
                operacion = "Determinante.";
                lblOperacion.setText(operacion);
                opcOperacion = 3;
                OpcionSeleccionada(LinearOpcMatrizDETERMINANTE, tvOpcMatrizDETERMINANTE);
                OpcionOriginal(LinearOpcMatrizSUMA, tvOpcMatrizSUMA);
                OpcionOriginal(LinearOpcMatrizRESTA, tvOpcMatrizRESTA);
                OpcionOriginal(LinearOpcMatrizPRODUCTO, tvOpcMatrizPRODUCTO);
                OpcionOriginal(LinearOpcMatrizProductxEscalar, tvOpcMatrizProductxEscalar);
                LinearMatriz2Suma.setVisibility(View.GONE);
                tvMatriz2.setVisibility(View.GONE);
                LinearDimensionOcultar.setVisibility(View.VISIBLE);
                LinearMatrizProductoDimension.setVisibility(View.GONE);

                tvsumaMatriz.setVisibility(View.GONE);
                tvrestaMatriz.setVisibility(View.GONE);
                tvdeterminanteMatriz.setVisibility(View.VISIBLE);

                etProductxEscalar.setVisibility(View.GONE);

                OpcionSeleccionada(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);

                invisibleM17x7();
                limpiarM1xM2();
                clearErrorM1xM2();
                etMatriz1Campo00.requestFocus();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                OpcSumaMatricesbtnLimpiar.setVisibility(View.GONE);
                LinearProductxEscalar.setVisibility(View.GONE);

                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
            }
        });
        //endregion

        //region LinearOpcMatrizProductxEscalar.setOnClickListener
        LinearOpcMatrizProductxEscalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblOperacion.setVisibility(View.VISIBLE);
                operacion = "Producto por un escalar.";
                lblOperacion.setText(operacion);
                opcOperacion = 5;
                OpcionSeleccionada(LinearOpcMatrizProductxEscalar, tvOpcMatrizProductxEscalar);
                OpcionOriginal(LinearOpcMatrizDETERMINANTE, tvOpcMatrizDETERMINANTE);
                OpcionOriginal(LinearOpcMatrizSUMA, tvOpcMatrizSUMA);
                OpcionOriginal(LinearOpcMatrizRESTA, tvOpcMatrizRESTA);
                OpcionOriginal(LinearOpcMatrizPRODUCTO, tvOpcMatrizPRODUCTO);
                LinearMatriz2Suma.setVisibility(View.GONE);
                tvMatriz2.setVisibility(View.GONE);
                LinearDimensionOcultar.setVisibility(View.VISIBLE);
                LinearMatrizProductoDimension.setVisibility(View.GONE);

                tvsumaMatriz.setVisibility(View.GONE);
                tvrestaMatriz.setVisibility(View.GONE);
                tvdeterminanteMatriz.setVisibility(View.VISIBLE);

                LinearProductxEscalar.setVisibility(View.VISIBLE);
                etProductxEscalar.setVisibility(View.VISIBLE);

                OpcionSeleccionada(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);

                invisibleM17x7();
                limpiarM1xM2();
                clearErrorM1xM2();
                etProductxEscalar.requestFocus();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                OpcSumaMatricesbtnLimpiar.setVisibility(View.GONE);
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
            }
        });
        //endregion

        //region LinearOpcMatrizPRODUCTO.setOnClickListener
        LinearOpcMatrizPRODUCTO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lblOperacion.setVisibility(View.VISIBLE);
                operacion = "Producto.";
                lblOperacion.setText(operacion);
                opcOperacion = 4;
                OpcionSeleccionada(LinearOpcMatrizPRODUCTO, tvOpcMatrizPRODUCTO);
                OpcionOriginal(LinearOpcMatrizDETERMINANTE, tvOpcMatrizDETERMINANTE);
                OpcionOriginal(LinearOpcMatrizSUMA, tvOpcMatrizSUMA);
                OpcionOriginal(LinearOpcMatrizRESTA, tvOpcMatrizRESTA);
                OpcionOriginal(LinearOpcMatrizProductxEscalar, tvOpcMatrizProductxEscalar);
                tvMatriz2.setVisibility(View.VISIBLE);

                tvsumaMatriz.setVisibility(View.GONE);
                tvrestaMatriz.setVisibility(View.GONE);
                tvdeterminanteMatriz.setVisibility(View.GONE);

                LinearDimensionOcultar.setVisibility(View.GONE);
                LinearMatrizProductoDimension.setVisibility(View.VISIBLE);
                LinearMatriz2Suma.setVisibility(View.VISIBLE);
                spinnerProductoM1_1.setSelection(0);
                spinnerProductoM1_2.setSelection(0);
                spinnerProductoM2_1.setSelection(0);
                spinnerProductoM2_2.setSelection(0);
                invisibleM17x7();
                invisibleM27x7();
                limpiarM1xM2();
                clearErrorM1xM2();
                etMatriz1Campo00.requestFocus();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                OpcSumaMatricesbtnLimpiar.setVisibility(View.GONE);

                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
            }
        });
        //endregion

        //region LinearOpcDimensionMatrizSuma3x3.setOnClickListener
        LinearOpcDimensionMatrizSuma3x3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                invisible4x4();
                invisible5x5();
                invisible6x6();
                invisible7x7();
                clearErrorM1xM2();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                if (opcOperacion == 5) {
                    etProductxEscalar.requestFocus();
                } else {
                    etMatriz1Campo00.requestFocus();
                }
                opc = 1;
                OpcionSeleccionada(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);
            }
        });
        //endregion

        //region LinearOpcDimensionMatrizSuma4x4.setOnClickListener
        LinearOpcDimensionMatrizSuma4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visible4x4();
                invisible5x5();
                invisible6x6();
                invisible7x7();
                clearErrorM1xM2();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                if (opcOperacion == 5) {
                    etProductxEscalar.requestFocus();
                } else {
                    etMatriz1Campo00.requestFocus();
                }
                opc = 2;
                OpcionOriginal(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionSeleccionada(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);
            }
        });
        //endregion

        //region LinearOpcDimensionMatrizSuma5x5.setOnClickListener
        LinearOpcDimensionMatrizSuma5x5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visible4x4();
                visible5x5();
                invisible6x6();
                invisible7x7();
                clearErrorM1xM2();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                if (opcOperacion == 5) {
                    etProductxEscalar.requestFocus();
                } else {
                    etMatriz1Campo00.requestFocus();
                }
                opc = 3;
                OpcionOriginal(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionSeleccionada(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);
            }
        });
        //endregion

        //region LinearOpcDimensionMatrizSuma6x6.setOnClickListener
        LinearOpcDimensionMatrizSuma6x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visible4x4();
                visible5x5();
                visible6x6();
                invisible7x7();
                clearErrorM1xM2();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                if (opcOperacion == 5) {
                    etProductxEscalar.requestFocus();
                } else {
                    etMatriz1Campo00.requestFocus();
                }
                opc = 4;
                OpcionOriginal(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionSeleccionada(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionOriginal(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);
            }
        });
        //endregion

        //region LinearOpcDimensionMatrizSuma7x7.setOnClickListener
        LinearOpcDimensionMatrizSuma7x7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visible4x4();
                visible5x5();
                visible6x6();
                visible7x7();
                clearErrorM1xM2();
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                if (opcOperacion == 5) {
                    etProductxEscalar.requestFocus();
                } else {
                    etMatriz1Campo00.requestFocus();
                }
                opc = 5;
                OpcionOriginal(LinearOpcDimensionMatrizSuma3x3, tvOpcDimensionMatrizSuma3x3);
                OpcionOriginal(LinearOpcDimensionMatrizSuma4x4, tvOpcDimensionMatrizSuma4x4);
                OpcionOriginal(LinearOpcDimensionMatrizSuma5x5, tvOpcDimensionMatrizSuma5x5);
                OpcionOriginal(LinearOpcDimensionMatrizSuma6x6, tvOpcDimensionMatrizSuma6x6);
                OpcionSeleccionada(LinearOpcDimensionMatrizSuma7x7, tvOpcDimensionMatrizSuma7x7);
            }
        });
        //endregion


        //region OpcSumaMatricesbtnSolucion.setOnClickListener
        OpcSumaMatricesbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (opcOperacion) {
                    case 1:
                        switch (opc) {
                            case 1:
                                if (validarMatrizSuma3x3(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 2:
                                if (validarMatrizSuma4x4(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 3:
                                if (validarMatrizSuma5x5(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 4:
                                if (validarMatrizSuma6x6(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 5:
                                if (validarMatrizSuma7x7(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                        }
                        break;
                    case 2:
                        switch (opc) {
                            case 1:
                                if (validarMatrizSuma3x3(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 2:
                                if (validarMatrizSuma4x4(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 3:
                                if (validarMatrizSuma5x5(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 4:
                                if (validarMatrizSuma6x6(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 5:
                                if (validarMatrizSuma7x7(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                        }
                        break;
                    case 3:
                        switch (opc) {
                            case 1:
                                if (validarMatrizSuma3x3(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 2:
                                if (validarMatrizSuma4x4(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 3:
                                if (validarMatrizSuma5x5(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 4:
                                if (validarMatrizSuma6x6(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 5:
                                if (validarMatrizSuma7x7(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                        }
                        break;
                    case 4:
                        if (validarM1xM2()) {
                            calcMProducto();
                            OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                            LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                        }
                        break;
                    case 5:
                        switch (opc) {
                            case 1:
                                if (validarMatrizSuma3x3(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 2:
                                if (validarMatrizSuma4x4(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 3:
                                if (validarMatrizSuma5x5(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 4:
                                if (validarMatrizSuma6x6(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                            case 5:
                                if (validarMatrizSuma7x7(opcOperacion)) {
                                    calcMatriz(opc, opcOperacion);
                                    LinearSumaMatrizResultado.setVisibility(View.VISIBLE);
                                    OpcSumaMatricesbtnLimpiar.setVisibility(View.VISIBLE);
                                }
                                break;
                        }
                        break;
                }

                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                    }
                });

            }
        });
        //endregion
        OpcSumaMatricesbtnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarM1xM2();
                clearErrorM1xM2();
                if (opcOperacion == 5) {
                    etProductxEscalar.requestFocus();
                } else {
                    etMatriz1Campo00.requestFocus();
                }

                OpcSumaMatricesbtnLimpiar.setVisibility(View.GONE);
                LinearSumaMatrizResultado.setVisibility(View.GONE);
            }
        });
        //region btnGuardarEjercicioSumaMatriz.setOnClickListener
        btnGuardarEjercicioSumaMatriz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearSumaMatrizResultado.setVisibility(View.GONE);
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollMatrices);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();

            }
        });
        //endregion
    }

    public void spinnerProductoM1_1() {
        spinnerProductoM1_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);

                int fila1 = Integer.parseInt(item.toString());
                int pos1 = Integer.parseInt(spinnerProductoM1_2.getSelectedItem().toString());
                //spinnerProductoM1_2();
                clearErrorM1xM2();
                switch (fila1) {
                    case 3:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM13x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM13x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM13x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM13x7();
                                break;
                        }
                        break;
                    case 4:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM14x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM14x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM14x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM14x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM14x7();
                                break;
                        }
                        break;
                    case 5:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM15x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM15x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM15x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM15x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM15x7();
                                break;
                        }
                        break;
                    case 6:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM16x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM16x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM16x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM16x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM16x7();
                                break;
                        }
                        break;
                    case 7:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM17x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM17x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM17x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM17x6();
                                break;
                            case 7:
                                visibleM17x7();
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void spinnerProductoM1_2() {

        spinnerProductoM1_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                int pos1 = Integer.parseInt(item.toString());
                int fila1 = Integer.parseInt(spinnerProductoM1_1.getSelectedItem().toString());

                clearErrorM1xM2();

                int iten = Integer.parseInt(spinnerProductoM1_2.getItemAtPosition(i).toString());
                if (iten == 3) {
                    spinnerProductoM2_1.setSelection(0);
                } else if (iten == 4) {
                    spinnerProductoM2_1.setSelection(1);
                } else if (iten == 5) {
                    spinnerProductoM2_1.setSelection(2);
                } else if (iten == 6) {
                    spinnerProductoM2_1.setSelection(3);
                } else if (iten == 7) {
                    spinnerProductoM2_1.setSelection(4);
                }

                switch (fila1) {
                    case 3:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM13x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM13x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM13x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM13x7();
                                break;
                        }
                        break;
                    case 4:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM14x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM14x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM14x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM14x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM14x7();
                                break;
                        }
                        break;
                    case 5:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM15x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM15x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM15x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM15x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM15x7();
                                break;
                        }
                        break;
                    case 6:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM16x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM16x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM16x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM16x6();
                                break;
                            case 7:
                                invisibleM17x7();
                                visibleM16x7();
                                break;
                        }
                        break;
                    case 7:
                        switch (pos1) {
                            case 3:
                                invisibleM17x7();
                                visibleM17x3();
                                break;
                            case 4:
                                invisibleM17x7();
                                visibleM17x4();
                                break;
                            case 5:
                                invisibleM17x7();
                                visibleM17x5();
                                break;
                            case 6:
                                invisibleM17x7();
                                visibleM17x6();
                                break;
                            case 7:
                                visibleM17x7();
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void spinnerProductoM2_1() {
        spinnerProductoM2_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                int fila1 = Integer.parseInt(item.toString());
                int pos1 = Integer.parseInt(spinnerProductoM2_2.getSelectedItem().toString());

                clearErrorM1xM2();

                int iten = Integer.parseInt(spinnerProductoM2_1.getItemAtPosition(i).toString());
                if (iten == 3) {
                    spinnerProductoM1_2.setSelection(0);
                } else if (iten == 4) {
                    spinnerProductoM1_2.setSelection(1);
                } else if (iten == 5) {
                    spinnerProductoM1_2.setSelection(2);
                } else if (iten == 6) {
                    spinnerProductoM1_2.setSelection(3);
                } else if (iten == 7) {
                    spinnerProductoM1_2.setSelection(4);
                }
                switch (fila1) {
                    case 3:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM23x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM23x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM23x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM23x7();
                                break;
                        }
                        break;
                    case 4:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM24x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM24x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM24x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM24x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM24x7();
                                break;
                        }
                        break;
                    case 5:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM25x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM25x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM25x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM25x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM25x7();
                                break;
                        }
                        break;
                    case 6:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM26x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM26x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM26x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM26x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM26x7();
                                break;
                        }
                        break;
                    case 7:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM27x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM27x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM27x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM27x6();
                                break;
                            case 7:
                                visibleM27x7();
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void spinnerProductoM2_2() {
        spinnerProductoM2_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object item = adapterView.getItemAtPosition(i);
                int pos1 = Integer.parseInt(item.toString());
                int fila1 = Integer.parseInt(spinnerProductoM2_1.getSelectedItem().toString());

                clearErrorM1xM2();

                switch (fila1) {
                    case 3:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM23x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM23x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM23x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM23x7();
                                break;
                        }
                        break;
                    case 4:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM24x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM24x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM24x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM24x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM24x7();
                                break;
                        }
                        break;
                    case 5:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM25x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM25x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM25x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM25x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM25x7();
                                break;
                        }
                        break;
                    case 6:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM26x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM26x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM26x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM26x6();
                                break;
                            case 7:
                                invisibleM27x7();
                                visibleM26x7();
                                break;
                        }
                        break;
                    case 7:
                        switch (pos1) {
                            case 3:
                                invisibleM27x7();
                                visibleM27x3();
                                break;
                            case 4:
                                invisibleM27x7();
                                visibleM27x4();
                                break;
                            case 5:
                                invisibleM27x7();
                                visibleM27x5();
                                break;
                            case 6:
                                invisibleM27x7();
                                visibleM27x6();
                                break;
                            case 7:
                                visibleM27x7();
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void visible4x4() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);

        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
    }

    public void invisible4x4() {
        etMatriz1Campo03.setVisibility(View.GONE);
        etMatriz1Campo13.setVisibility(View.GONE);
        etMatriz1Campo23.setVisibility(View.GONE);

        etMatriz1Campo30.setVisibility(View.GONE);
        etMatriz1Campo31.setVisibility(View.GONE);
        etMatriz1Campo32.setVisibility(View.GONE);
        etMatriz1Campo33.setVisibility(View.GONE);

        etMatriz2Campo03.setVisibility(View.GONE);
        etMatriz2Campo13.setVisibility(View.GONE);
        etMatriz2Campo23.setVisibility(View.GONE);

        etMatriz2Campo30.setVisibility(View.GONE);
        etMatriz2Campo31.setVisibility(View.GONE);
        etMatriz2Campo32.setVisibility(View.GONE);
        etMatriz2Campo33.setVisibility(View.GONE);
    }

    public void visible5x5() {
//        matriz 1
        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
//        Matriz 2
        etMatriz2Campo04.setVisibility(View.VISIBLE);

        etMatriz2Campo14.setVisibility(View.VISIBLE);

        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo34.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
    }

    public void invisible5x5() {
        //        matriz 1
        etMatriz1Campo04.setVisibility(View.GONE);

        etMatriz1Campo14.setVisibility(View.GONE);

        etMatriz1Campo24.setVisibility(View.GONE);

        etMatriz1Campo34.setVisibility(View.GONE);

        etMatriz1Campo40.setVisibility(View.GONE);
        etMatriz1Campo41.setVisibility(View.GONE);
        etMatriz1Campo42.setVisibility(View.GONE);
        etMatriz1Campo43.setVisibility(View.GONE);
        etMatriz1Campo44.setVisibility(View.GONE);
//        Matriz 2
        etMatriz2Campo04.setVisibility(View.GONE);

        etMatriz2Campo14.setVisibility(View.GONE);

        etMatriz2Campo24.setVisibility(View.GONE);

        etMatriz2Campo34.setVisibility(View.GONE);

        etMatriz2Campo40.setVisibility(View.GONE);
        etMatriz2Campo41.setVisibility(View.GONE);
        etMatriz2Campo42.setVisibility(View.GONE);
        etMatriz2Campo43.setVisibility(View.GONE);
        etMatriz2Campo44.setVisibility(View.GONE);
    }

    public void visible6x6() {
//        matriz 1
        etMatriz1Campo05.setVisibility(View.VISIBLE);

        etMatriz1Campo15.setVisibility(View.VISIBLE);

        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo35.setVisibility(View.VISIBLE);

        etMatriz1Campo45.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
        etMatriz1Campo54.setVisibility(View.VISIBLE);
        etMatriz1Campo55.setVisibility(View.VISIBLE);
//        Matriz 2
        etMatriz2Campo05.setVisibility(View.VISIBLE);

        etMatriz2Campo15.setVisibility(View.VISIBLE);

        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo35.setVisibility(View.VISIBLE);

        etMatriz2Campo45.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
        etMatriz2Campo54.setVisibility(View.VISIBLE);
        etMatriz2Campo55.setVisibility(View.VISIBLE);
    }

    public void invisible6x6() {
        //        matriz 1
        etMatriz1Campo05.setVisibility(View.GONE);
        etMatriz1Campo15.setVisibility(View.GONE);
        etMatriz1Campo25.setVisibility(View.GONE);

        etMatriz1Campo35.setVisibility(View.GONE);

        etMatriz1Campo45.setVisibility(View.GONE);

        etMatriz1Campo50.setVisibility(View.GONE);
        etMatriz1Campo51.setVisibility(View.GONE);
        etMatriz1Campo52.setVisibility(View.GONE);
        etMatriz1Campo53.setVisibility(View.GONE);
        etMatriz1Campo54.setVisibility(View.GONE);
        etMatriz1Campo55.setVisibility(View.GONE);
//        Matriz 2
        etMatriz2Campo05.setVisibility(View.GONE);

        etMatriz2Campo15.setVisibility(View.GONE);

        etMatriz2Campo25.setVisibility(View.GONE);

        etMatriz2Campo35.setVisibility(View.GONE);

        etMatriz2Campo45.setVisibility(View.GONE);

        etMatriz2Campo50.setVisibility(View.GONE);
        etMatriz2Campo51.setVisibility(View.GONE);
        etMatriz2Campo52.setVisibility(View.GONE);
        etMatriz2Campo53.setVisibility(View.GONE);
        etMatriz2Campo54.setVisibility(View.GONE);
        etMatriz2Campo55.setVisibility(View.GONE);
    }

    public void visible7x7() {
//        matriz 1
        etMatriz1Campo06.setVisibility(View.VISIBLE);
        etMatriz1Campo16.setVisibility(View.VISIBLE);
        etMatriz1Campo26.setVisibility(View.VISIBLE);
        etMatriz1Campo36.setVisibility(View.VISIBLE);
        etMatriz1Campo46.setVisibility(View.VISIBLE);

        etMatriz1Campo56.setVisibility(View.VISIBLE);

        etMatriz1Campo60.setVisibility(View.VISIBLE);
        etMatriz1Campo61.setVisibility(View.VISIBLE);
        etMatriz1Campo62.setVisibility(View.VISIBLE);
        etMatriz1Campo63.setVisibility(View.VISIBLE);
        etMatriz1Campo64.setVisibility(View.VISIBLE);
        etMatriz1Campo65.setVisibility(View.VISIBLE);
        etMatriz1Campo66.setVisibility(View.VISIBLE);
//        Matriz 2
        etMatriz2Campo06.setVisibility(View.VISIBLE);

        etMatriz2Campo16.setVisibility(View.VISIBLE);

        etMatriz2Campo26.setVisibility(View.VISIBLE);

        etMatriz2Campo36.setVisibility(View.VISIBLE);

        etMatriz2Campo46.setVisibility(View.VISIBLE);

        etMatriz2Campo56.setVisibility(View.VISIBLE);

        etMatriz2Campo60.setVisibility(View.VISIBLE);
        etMatriz2Campo61.setVisibility(View.VISIBLE);
        etMatriz2Campo62.setVisibility(View.VISIBLE);
        etMatriz2Campo63.setVisibility(View.VISIBLE);
        etMatriz2Campo64.setVisibility(View.VISIBLE);
        etMatriz2Campo65.setVisibility(View.VISIBLE);
        etMatriz2Campo66.setVisibility(View.VISIBLE);
    }

    public void invisible7x7() {
        //        matriz 1
        etMatriz1Campo06.setVisibility(View.GONE);

        etMatriz1Campo16.setVisibility(View.GONE);

        etMatriz1Campo26.setVisibility(View.GONE);

        etMatriz1Campo36.setVisibility(View.GONE);

        etMatriz1Campo46.setVisibility(View.GONE);

        etMatriz1Campo56.setVisibility(View.GONE);

        etMatriz1Campo60.setVisibility(View.GONE);
        etMatriz1Campo61.setVisibility(View.GONE);
        etMatriz1Campo62.setVisibility(View.GONE);
        etMatriz1Campo63.setVisibility(View.GONE);
        etMatriz1Campo64.setVisibility(View.GONE);
        etMatriz1Campo65.setVisibility(View.GONE);
        etMatriz1Campo66.setVisibility(View.GONE);
//        Matriz 2
        etMatriz2Campo06.setVisibility(View.GONE);

        etMatriz2Campo16.setVisibility(View.GONE);

        etMatriz2Campo26.setVisibility(View.GONE);

        etMatriz2Campo36.setVisibility(View.GONE);

        etMatriz2Campo46.setVisibility(View.GONE);

        etMatriz2Campo56.setVisibility(View.GONE);

        etMatriz2Campo60.setVisibility(View.GONE);
        etMatriz2Campo61.setVisibility(View.GONE);
        etMatriz2Campo62.setVisibility(View.GONE);
        etMatriz2Campo63.setVisibility(View.GONE);
        etMatriz2Campo64.setVisibility(View.GONE);
        etMatriz2Campo65.setVisibility(View.GONE);
        etMatriz2Campo66.setVisibility(View.GONE);
    }

    //region PARA PRODUCTO MATRIZ 1
    public void visibleM13x4() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);
    }

    public void visibleM13x5() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);
    }

    public void visibleM13x6() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);
    }

    public void visibleM13x7() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo06.setVisibility(View.VISIBLE);
        etMatriz1Campo16.setVisibility(View.VISIBLE);
        etMatriz1Campo26.setVisibility(View.VISIBLE);
    }

    //--------
    public void visibleM14x3() {
        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
    }

    public void visibleM14x4() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);

    }

    public void visibleM14x5() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
    }

    public void visibleM14x6() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);
    }

    public void visibleM14x7() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo06.setVisibility(View.VISIBLE);
        etMatriz1Campo16.setVisibility(View.VISIBLE);
        etMatriz1Campo26.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);
        etMatriz1Campo36.setVisibility(View.VISIBLE);
    }

    //---
    public void visibleM15x3() {
        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
    }

    public void visibleM15x4() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);

    }

    public void visibleM15x5() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
    }

    public void visibleM15x6() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
        etMatriz1Campo45.setVisibility(View.VISIBLE);

    }

    public void visibleM15x7() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo06.setVisibility(View.VISIBLE);
        etMatriz1Campo16.setVisibility(View.VISIBLE);
        etMatriz1Campo26.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);
        etMatriz1Campo36.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
        etMatriz1Campo45.setVisibility(View.VISIBLE);
        etMatriz1Campo46.setVisibility(View.VISIBLE);
    }

    //---
    public void visibleM16x3() {
        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
    }

    public void visibleM16x4() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
    }

    public void visibleM16x5() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
        etMatriz1Campo54.setVisibility(View.VISIBLE);
    }

    public void visibleM16x6() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
        etMatriz1Campo45.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
        etMatriz1Campo54.setVisibility(View.VISIBLE);
        etMatriz1Campo55.setVisibility(View.VISIBLE);
    }

    public void visibleM16x7() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo06.setVisibility(View.VISIBLE);
        etMatriz1Campo16.setVisibility(View.VISIBLE);
        etMatriz1Campo26.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);
        etMatriz1Campo36.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
        etMatriz1Campo45.setVisibility(View.VISIBLE);
        etMatriz1Campo46.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
        etMatriz1Campo54.setVisibility(View.VISIBLE);
        etMatriz1Campo55.setVisibility(View.VISIBLE);
        etMatriz1Campo56.setVisibility(View.VISIBLE);
    }

    //---
    public void visibleM17x3() {
        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);

        etMatriz1Campo60.setVisibility(View.VISIBLE);
        etMatriz1Campo61.setVisibility(View.VISIBLE);
        etMatriz1Campo62.setVisibility(View.VISIBLE);
    }

    public void visibleM17x4() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);

        etMatriz1Campo60.setVisibility(View.VISIBLE);
        etMatriz1Campo61.setVisibility(View.VISIBLE);
        etMatriz1Campo62.setVisibility(View.VISIBLE);
        etMatriz1Campo63.setVisibility(View.VISIBLE);
    }

    public void visibleM17x5() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
        etMatriz1Campo54.setVisibility(View.VISIBLE);

        etMatriz1Campo60.setVisibility(View.VISIBLE);
        etMatriz1Campo61.setVisibility(View.VISIBLE);
        etMatriz1Campo62.setVisibility(View.VISIBLE);
        etMatriz1Campo63.setVisibility(View.VISIBLE);
        etMatriz1Campo64.setVisibility(View.VISIBLE);
    }

    public void visibleM17x6() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
        etMatriz1Campo45.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
        etMatriz1Campo54.setVisibility(View.VISIBLE);
        etMatriz1Campo55.setVisibility(View.VISIBLE);

        etMatriz1Campo60.setVisibility(View.VISIBLE);
        etMatriz1Campo61.setVisibility(View.VISIBLE);
        etMatriz1Campo62.setVisibility(View.VISIBLE);
        etMatriz1Campo63.setVisibility(View.VISIBLE);
        etMatriz1Campo64.setVisibility(View.VISIBLE);
        etMatriz1Campo65.setVisibility(View.VISIBLE);
    }

    public void visibleM17x7() {
        etMatriz1Campo03.setVisibility(View.VISIBLE);
        etMatriz1Campo13.setVisibility(View.VISIBLE);
        etMatriz1Campo23.setVisibility(View.VISIBLE);

        etMatriz1Campo04.setVisibility(View.VISIBLE);
        etMatriz1Campo14.setVisibility(View.VISIBLE);
        etMatriz1Campo24.setVisibility(View.VISIBLE);

        etMatriz1Campo05.setVisibility(View.VISIBLE);
        etMatriz1Campo15.setVisibility(View.VISIBLE);
        etMatriz1Campo25.setVisibility(View.VISIBLE);

        etMatriz1Campo06.setVisibility(View.VISIBLE);
        etMatriz1Campo16.setVisibility(View.VISIBLE);
        etMatriz1Campo26.setVisibility(View.VISIBLE);

        etMatriz1Campo30.setVisibility(View.VISIBLE);
        etMatriz1Campo31.setVisibility(View.VISIBLE);
        etMatriz1Campo32.setVisibility(View.VISIBLE);
        etMatriz1Campo33.setVisibility(View.VISIBLE);
        etMatriz1Campo34.setVisibility(View.VISIBLE);
        etMatriz1Campo35.setVisibility(View.VISIBLE);
        etMatriz1Campo36.setVisibility(View.VISIBLE);

        etMatriz1Campo40.setVisibility(View.VISIBLE);
        etMatriz1Campo41.setVisibility(View.VISIBLE);
        etMatriz1Campo42.setVisibility(View.VISIBLE);
        etMatriz1Campo43.setVisibility(View.VISIBLE);
        etMatriz1Campo44.setVisibility(View.VISIBLE);
        etMatriz1Campo45.setVisibility(View.VISIBLE);
        etMatriz1Campo46.setVisibility(View.VISIBLE);

        etMatriz1Campo50.setVisibility(View.VISIBLE);
        etMatriz1Campo51.setVisibility(View.VISIBLE);
        etMatriz1Campo52.setVisibility(View.VISIBLE);
        etMatriz1Campo53.setVisibility(View.VISIBLE);
        etMatriz1Campo54.setVisibility(View.VISIBLE);
        etMatriz1Campo55.setVisibility(View.VISIBLE);
        etMatriz1Campo56.setVisibility(View.VISIBLE);

        etMatriz1Campo60.setVisibility(View.VISIBLE);
        etMatriz1Campo61.setVisibility(View.VISIBLE);
        etMatriz1Campo62.setVisibility(View.VISIBLE);
        etMatriz1Campo63.setVisibility(View.VISIBLE);
        etMatriz1Campo64.setVisibility(View.VISIBLE);
        etMatriz1Campo65.setVisibility(View.VISIBLE);
        etMatriz1Campo66.setVisibility(View.VISIBLE);
    }

    public void invisibleM17x7() {
        etMatriz1Campo03.setVisibility(View.GONE);
        etMatriz1Campo13.setVisibility(View.GONE);
        etMatriz1Campo23.setVisibility(View.GONE);

        etMatriz1Campo04.setVisibility(View.GONE);
        etMatriz1Campo14.setVisibility(View.GONE);
        etMatriz1Campo24.setVisibility(View.GONE);

        etMatriz1Campo05.setVisibility(View.GONE);
        etMatriz1Campo15.setVisibility(View.GONE);
        etMatriz1Campo25.setVisibility(View.GONE);

        etMatriz1Campo06.setVisibility(View.GONE);
        etMatriz1Campo16.setVisibility(View.GONE);
        etMatriz1Campo26.setVisibility(View.GONE);

        etMatriz1Campo30.setVisibility(View.GONE);
        etMatriz1Campo31.setVisibility(View.GONE);
        etMatriz1Campo32.setVisibility(View.GONE);
        etMatriz1Campo33.setVisibility(View.GONE);
        etMatriz1Campo34.setVisibility(View.GONE);
        etMatriz1Campo35.setVisibility(View.GONE);
        etMatriz1Campo36.setVisibility(View.GONE);

        etMatriz1Campo40.setVisibility(View.GONE);
        etMatriz1Campo41.setVisibility(View.GONE);
        etMatriz1Campo42.setVisibility(View.GONE);
        etMatriz1Campo43.setVisibility(View.GONE);
        etMatriz1Campo44.setVisibility(View.GONE);
        etMatriz1Campo45.setVisibility(View.GONE);
        etMatriz1Campo46.setVisibility(View.GONE);

        etMatriz1Campo50.setVisibility(View.GONE);
        etMatriz1Campo51.setVisibility(View.GONE);
        etMatriz1Campo52.setVisibility(View.GONE);
        etMatriz1Campo53.setVisibility(View.GONE);
        etMatriz1Campo54.setVisibility(View.GONE);
        etMatriz1Campo55.setVisibility(View.GONE);
        etMatriz1Campo56.setVisibility(View.GONE);

        etMatriz1Campo60.setVisibility(View.GONE);
        etMatriz1Campo61.setVisibility(View.GONE);
        etMatriz1Campo62.setVisibility(View.GONE);
        etMatriz1Campo63.setVisibility(View.GONE);
        etMatriz1Campo64.setVisibility(View.GONE);
        etMatriz1Campo65.setVisibility(View.GONE);
        etMatriz1Campo66.setVisibility(View.GONE);
    }
    //endregion

    //region PARA PRODUCTO MATRIZ 2
    public void visibleM23x4() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);
    }

    public void visibleM23x5() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);
    }

    public void visibleM23x6() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);
    }

    public void visibleM23x7() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo06.setVisibility(View.VISIBLE);
        etMatriz2Campo16.setVisibility(View.VISIBLE);
        etMatriz2Campo26.setVisibility(View.VISIBLE);
    }

    //--------
    public void visibleM24x3() {
        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
    }

    public void visibleM24x4() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);

    }

    public void visibleM24x5() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
    }

    public void visibleM24x6() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);
    }

    public void visibleM24x7() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo06.setVisibility(View.VISIBLE);
        etMatriz2Campo16.setVisibility(View.VISIBLE);
        etMatriz2Campo26.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);
        etMatriz2Campo36.setVisibility(View.VISIBLE);
    }

    //---
    public void visibleM25x3() {
        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
    }

    public void visibleM25x4() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);

    }

    public void visibleM25x5() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
    }

    public void visibleM25x6() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
        etMatriz2Campo45.setVisibility(View.VISIBLE);

    }

    public void visibleM25x7() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo06.setVisibility(View.VISIBLE);
        etMatriz2Campo16.setVisibility(View.VISIBLE);
        etMatriz2Campo26.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);
        etMatriz2Campo36.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
        etMatriz2Campo45.setVisibility(View.VISIBLE);
        etMatriz2Campo46.setVisibility(View.VISIBLE);
    }

    //---
    public void visibleM26x3() {
        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
    }

    public void visibleM26x4() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
    }

    public void visibleM26x5() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
        etMatriz2Campo54.setVisibility(View.VISIBLE);
    }

    public void visibleM26x6() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
        etMatriz2Campo45.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
        etMatriz2Campo54.setVisibility(View.VISIBLE);
        etMatriz2Campo55.setVisibility(View.VISIBLE);
    }

    public void visibleM26x7() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo06.setVisibility(View.VISIBLE);
        etMatriz2Campo16.setVisibility(View.VISIBLE);
        etMatriz2Campo26.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);
        etMatriz2Campo36.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
        etMatriz2Campo45.setVisibility(View.VISIBLE);
        etMatriz2Campo46.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
        etMatriz2Campo54.setVisibility(View.VISIBLE);
        etMatriz2Campo55.setVisibility(View.VISIBLE);
        etMatriz2Campo56.setVisibility(View.VISIBLE);
    }

    //---
    public void visibleM27x3() {
        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);

        etMatriz2Campo60.setVisibility(View.VISIBLE);
        etMatriz2Campo61.setVisibility(View.VISIBLE);
        etMatriz2Campo62.setVisibility(View.VISIBLE);
    }

    public void visibleM27x4() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);

        etMatriz2Campo60.setVisibility(View.VISIBLE);
        etMatriz2Campo61.setVisibility(View.VISIBLE);
        etMatriz2Campo62.setVisibility(View.VISIBLE);
        etMatriz2Campo63.setVisibility(View.VISIBLE);
    }

    public void visibleM27x5() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
        etMatriz2Campo54.setVisibility(View.VISIBLE);

        etMatriz2Campo60.setVisibility(View.VISIBLE);
        etMatriz2Campo61.setVisibility(View.VISIBLE);
        etMatriz2Campo62.setVisibility(View.VISIBLE);
        etMatriz2Campo63.setVisibility(View.VISIBLE);
        etMatriz2Campo64.setVisibility(View.VISIBLE);
    }

    public void visibleM27x6() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
        etMatriz2Campo45.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
        etMatriz2Campo54.setVisibility(View.VISIBLE);
        etMatriz2Campo55.setVisibility(View.VISIBLE);

        etMatriz2Campo60.setVisibility(View.VISIBLE);
        etMatriz2Campo61.setVisibility(View.VISIBLE);
        etMatriz2Campo62.setVisibility(View.VISIBLE);
        etMatriz2Campo63.setVisibility(View.VISIBLE);
        etMatriz2Campo64.setVisibility(View.VISIBLE);
        etMatriz2Campo65.setVisibility(View.VISIBLE);
    }

    public void visibleM27x7() {
        etMatriz2Campo03.setVisibility(View.VISIBLE);
        etMatriz2Campo13.setVisibility(View.VISIBLE);
        etMatriz2Campo23.setVisibility(View.VISIBLE);

        etMatriz2Campo04.setVisibility(View.VISIBLE);
        etMatriz2Campo14.setVisibility(View.VISIBLE);
        etMatriz2Campo24.setVisibility(View.VISIBLE);

        etMatriz2Campo05.setVisibility(View.VISIBLE);
        etMatriz2Campo15.setVisibility(View.VISIBLE);
        etMatriz2Campo25.setVisibility(View.VISIBLE);

        etMatriz2Campo06.setVisibility(View.VISIBLE);
        etMatriz2Campo16.setVisibility(View.VISIBLE);
        etMatriz2Campo26.setVisibility(View.VISIBLE);

        etMatriz2Campo30.setVisibility(View.VISIBLE);
        etMatriz2Campo31.setVisibility(View.VISIBLE);
        etMatriz2Campo32.setVisibility(View.VISIBLE);
        etMatriz2Campo33.setVisibility(View.VISIBLE);
        etMatriz2Campo34.setVisibility(View.VISIBLE);
        etMatriz2Campo35.setVisibility(View.VISIBLE);
        etMatriz2Campo36.setVisibility(View.VISIBLE);

        etMatriz2Campo40.setVisibility(View.VISIBLE);
        etMatriz2Campo41.setVisibility(View.VISIBLE);
        etMatriz2Campo42.setVisibility(View.VISIBLE);
        etMatriz2Campo43.setVisibility(View.VISIBLE);
        etMatriz2Campo44.setVisibility(View.VISIBLE);
        etMatriz2Campo45.setVisibility(View.VISIBLE);
        etMatriz2Campo46.setVisibility(View.VISIBLE);

        etMatriz2Campo50.setVisibility(View.VISIBLE);
        etMatriz2Campo51.setVisibility(View.VISIBLE);
        etMatriz2Campo52.setVisibility(View.VISIBLE);
        etMatriz2Campo53.setVisibility(View.VISIBLE);
        etMatriz2Campo54.setVisibility(View.VISIBLE);
        etMatriz2Campo55.setVisibility(View.VISIBLE);
        etMatriz2Campo56.setVisibility(View.VISIBLE);

        etMatriz2Campo60.setVisibility(View.VISIBLE);
        etMatriz2Campo61.setVisibility(View.VISIBLE);
        etMatriz2Campo62.setVisibility(View.VISIBLE);
        etMatriz2Campo63.setVisibility(View.VISIBLE);
        etMatriz2Campo64.setVisibility(View.VISIBLE);
        etMatriz2Campo65.setVisibility(View.VISIBLE);
        etMatriz2Campo66.setVisibility(View.VISIBLE);
    }

    public void invisibleM27x7() {
        etMatriz2Campo03.setVisibility(View.GONE);
        etMatriz2Campo13.setVisibility(View.GONE);
        etMatriz2Campo23.setVisibility(View.GONE);

        etMatriz2Campo04.setVisibility(View.GONE);
        etMatriz2Campo14.setVisibility(View.GONE);
        etMatriz2Campo24.setVisibility(View.GONE);

        etMatriz2Campo05.setVisibility(View.GONE);
        etMatriz2Campo15.setVisibility(View.GONE);
        etMatriz2Campo25.setVisibility(View.GONE);

        etMatriz2Campo06.setVisibility(View.GONE);
        etMatriz2Campo16.setVisibility(View.GONE);
        etMatriz2Campo26.setVisibility(View.GONE);

        etMatriz2Campo30.setVisibility(View.GONE);
        etMatriz2Campo31.setVisibility(View.GONE);
        etMatriz2Campo32.setVisibility(View.GONE);
        etMatriz2Campo33.setVisibility(View.GONE);
        etMatriz2Campo34.setVisibility(View.GONE);
        etMatriz2Campo35.setVisibility(View.GONE);
        etMatriz2Campo36.setVisibility(View.GONE);

        etMatriz2Campo40.setVisibility(View.GONE);
        etMatriz2Campo41.setVisibility(View.GONE);
        etMatriz2Campo42.setVisibility(View.GONE);
        etMatriz2Campo43.setVisibility(View.GONE);
        etMatriz2Campo44.setVisibility(View.GONE);
        etMatriz2Campo45.setVisibility(View.GONE);
        etMatriz2Campo46.setVisibility(View.GONE);

        etMatriz2Campo50.setVisibility(View.GONE);
        etMatriz2Campo51.setVisibility(View.GONE);
        etMatriz2Campo52.setVisibility(View.GONE);
        etMatriz2Campo53.setVisibility(View.GONE);
        etMatriz2Campo54.setVisibility(View.GONE);
        etMatriz2Campo55.setVisibility(View.GONE);
        etMatriz2Campo56.setVisibility(View.GONE);

        etMatriz2Campo60.setVisibility(View.GONE);
        etMatriz2Campo61.setVisibility(View.GONE);
        etMatriz2Campo62.setVisibility(View.GONE);
        etMatriz2Campo63.setVisibility(View.GONE);
        etMatriz2Campo64.setVisibility(View.GONE);
        etMatriz2Campo65.setVisibility(View.GONE);
        etMatriz2Campo66.setVisibility(View.GONE);
    }

    //endregion
    //valida toda la matriz
    private Boolean validarM1xM2() {
        boolean _result = true;
        String _error = "Campo vacío";

        //region Matriz 1
        if (etMatriz1Campo00.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
        }

        if (etMatriz1Campo01.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo02.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo03.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo04.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo05.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo05.getText().toString().isEmpty()) {
                etMatriz1Campo05.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo06.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo06.getText().toString().isEmpty()) {
                etMatriz1Campo06.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo10.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo11.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo12.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo13.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo14.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo15.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo15.getText().toString().isEmpty()) {
                etMatriz1Campo15.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo16.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo16.getText().toString().isEmpty()) {
                etMatriz1Campo16.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo20.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo21.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo22.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo23.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo24.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo25.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo25.getText().toString().isEmpty()) {
                etMatriz1Campo25.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo26.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo26.getText().toString().isEmpty()) {
                etMatriz1Campo26.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo30.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo31.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo32.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo33.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo34.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo35.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo35.getText().toString().isEmpty()) {
                etMatriz1Campo35.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo36.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo36.getText().toString().isEmpty()) {
                etMatriz1Campo36.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo40.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo41.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo42.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo43.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo44.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo45.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo45.getText().toString().isEmpty()) {
                etMatriz1Campo45.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo46.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo46.getText().toString().isEmpty()) {
                etMatriz1Campo46.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo50.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo50.getText().toString().isEmpty()) {
                etMatriz1Campo50.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo51.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo51.getText().toString().isEmpty()) {
                etMatriz1Campo51.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo52.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo52.getText().toString().isEmpty()) {
                etMatriz1Campo52.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo53.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo53.getText().toString().isEmpty()) {
                etMatriz1Campo53.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo54.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo54.getText().toString().isEmpty()) {
                etMatriz1Campo54.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo55.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo55.getText().toString().isEmpty()) {
                etMatriz1Campo55.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo56.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo56.getText().toString().isEmpty()) {
                etMatriz1Campo56.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo60.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo60.getText().toString().isEmpty()) {
                etMatriz1Campo60.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo61.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo61.getText().toString().isEmpty()) {
                etMatriz1Campo61.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo62.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo62.getText().toString().isEmpty()) {
                etMatriz1Campo62.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo63.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo63.getText().toString().isEmpty()) {
                etMatriz1Campo63.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo64.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo64.getText().toString().isEmpty()) {
                etMatriz1Campo64.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo65.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo65.getText().toString().isEmpty()) {
                etMatriz1Campo65.setError(_error);
                _result = false;
            }
        }
        if (etMatriz1Campo66.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo66.getText().toString().isEmpty()) {
                etMatriz1Campo66.setError(_error);
                _result = false;
            }
        }

        //endregion

        //region Matriz 2
        if (etMatriz2Campo00.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo00.getText().toString().isEmpty()) {
                etMatriz2Campo00.setError(_error);
                _result = false;
            }
        }

        if (etMatriz2Campo01.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo01.getText().toString().isEmpty()) {
                etMatriz2Campo01.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo02.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo02.getText().toString().isEmpty()) {
                etMatriz2Campo02.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo03.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo03.getText().toString().isEmpty()) {
                etMatriz2Campo03.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo04.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo04.getText().toString().isEmpty()) {
                etMatriz2Campo04.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo05.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo05.getText().toString().isEmpty()) {
                etMatriz2Campo05.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo06.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo06.getText().toString().isEmpty()) {
                etMatriz2Campo06.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo10.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo10.getText().toString().isEmpty()) {
                etMatriz2Campo10.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo11.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo11.getText().toString().isEmpty()) {
                etMatriz2Campo11.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo12.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo12.getText().toString().isEmpty()) {
                etMatriz2Campo12.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo13.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo13.getText().toString().isEmpty()) {
                etMatriz2Campo13.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo14.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo14.getText().toString().isEmpty()) {
                etMatriz2Campo14.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo15.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo15.getText().toString().isEmpty()) {
                etMatriz2Campo15.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo16.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo16.getText().toString().isEmpty()) {
                etMatriz2Campo16.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo20.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo20.getText().toString().isEmpty()) {
                etMatriz2Campo20.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo21.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo21.getText().toString().isEmpty()) {
                etMatriz2Campo21.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo22.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo22.getText().toString().isEmpty()) {
                etMatriz2Campo22.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo23.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo23.getText().toString().isEmpty()) {
                etMatriz2Campo23.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo24.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo24.getText().toString().isEmpty()) {
                etMatriz2Campo24.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo25.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo25.getText().toString().isEmpty()) {
                etMatriz2Campo25.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo26.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo26.getText().toString().isEmpty()) {
                etMatriz2Campo26.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo30.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo30.getText().toString().isEmpty()) {
                etMatriz2Campo30.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo31.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo31.getText().toString().isEmpty()) {
                etMatriz2Campo31.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo32.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo32.getText().toString().isEmpty()) {
                etMatriz2Campo32.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo33.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo33.getText().toString().isEmpty()) {
                etMatriz2Campo33.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo34.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo34.getText().toString().isEmpty()) {
                etMatriz2Campo34.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo35.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo35.getText().toString().isEmpty()) {
                etMatriz2Campo35.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo36.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo36.getText().toString().isEmpty()) {
                etMatriz2Campo36.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo40.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo40.getText().toString().isEmpty()) {
                etMatriz2Campo40.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo41.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo41.getText().toString().isEmpty()) {
                etMatriz2Campo41.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo42.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo42.getText().toString().isEmpty()) {
                etMatriz2Campo42.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo43.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo43.getText().toString().isEmpty()) {
                etMatriz2Campo43.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo44.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo44.getText().toString().isEmpty()) {
                etMatriz2Campo44.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo45.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo45.getText().toString().isEmpty()) {
                etMatriz2Campo45.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo46.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo46.getText().toString().isEmpty()) {
                etMatriz2Campo46.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo50.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo50.getText().toString().isEmpty()) {
                etMatriz2Campo50.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo51.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo51.getText().toString().isEmpty()) {
                etMatriz2Campo51.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo52.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo52.getText().toString().isEmpty()) {
                etMatriz2Campo52.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo53.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo53.getText().toString().isEmpty()) {
                etMatriz2Campo53.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo54.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo54.getText().toString().isEmpty()) {
                etMatriz2Campo54.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo55.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo55.getText().toString().isEmpty()) {
                etMatriz2Campo55.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo56.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo56.getText().toString().isEmpty()) {
                etMatriz2Campo56.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo60.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo60.getText().toString().isEmpty()) {
                etMatriz2Campo60.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo61.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo61.getText().toString().isEmpty()) {
                etMatriz2Campo61.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo62.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo62.getText().toString().isEmpty()) {
                etMatriz2Campo62.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo63.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo63.getText().toString().isEmpty()) {
                etMatriz2Campo63.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo64.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo64.getText().toString().isEmpty()) {
                etMatriz2Campo64.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo65.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo65.getText().toString().isEmpty()) {
                etMatriz2Campo65.setError(_error);
                _result = false;
            }
        }
        if (etMatriz2Campo66.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo66.getText().toString().isEmpty()) {
                etMatriz2Campo66.setError(_error);
                _result = false;
            }
        }

        //endregion

        return _result;
    }

    private void clearErrorM1xM2() {
        //region Matriz 1
        if (etMatriz1Campo00.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(null);
            }
        }

        if (etMatriz1Campo01.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(null);
            }
        }
        if (etMatriz1Campo02.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(null);
            }
        }
        if (etMatriz1Campo03.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(null);
            }
        }
        if (etMatriz1Campo04.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(null);
            }
        }
        if (etMatriz1Campo05.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo05.getText().toString().isEmpty()) {
                etMatriz1Campo05.setError(null);
            }
        }
        if (etMatriz1Campo06.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo06.getText().toString().isEmpty()) {
                etMatriz1Campo06.setError(null);
            }
        }
        if (etMatriz1Campo10.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(null);
            }
        }
        if (etMatriz1Campo11.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(null);
            }
        }
        if (etMatriz1Campo12.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(null);
            }
        }
        if (etMatriz1Campo13.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(null);
            }
        }
        if (etMatriz1Campo14.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(null);
            }
        }
        if (etMatriz1Campo15.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo15.getText().toString().isEmpty()) {
                etMatriz1Campo15.setError(null);
            }
        }
        if (etMatriz1Campo16.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo16.getText().toString().isEmpty()) {
                etMatriz1Campo16.setError(null);
            }
        }
        if (etMatriz1Campo20.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(null);
            }
        }
        if (etMatriz1Campo21.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(null);
            }
        }
        if (etMatriz1Campo22.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(null);
            }
        }
        if (etMatriz1Campo23.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(null);
            }
        }
        if (etMatriz1Campo24.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(null);
            }
        }
        if (etMatriz1Campo25.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo25.getText().toString().isEmpty()) {
                etMatriz1Campo25.setError(null);
            }
        }
        if (etMatriz1Campo26.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo26.getText().toString().isEmpty()) {
                etMatriz1Campo26.setError(null);
            }
        }
        if (etMatriz1Campo30.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(null);
            }
        }
        if (etMatriz1Campo31.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(null);
            }
        }
        if (etMatriz1Campo32.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(null);
            }
        }
        if (etMatriz1Campo33.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(null);
            }
        }
        if (etMatriz1Campo34.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(null);
            }
        }
        if (etMatriz1Campo35.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo35.getText().toString().isEmpty()) {
                etMatriz1Campo35.setError(null);
            }
        }
        if (etMatriz1Campo36.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo36.getText().toString().isEmpty()) {
                etMatriz1Campo36.setError(null);
            }
        }
        if (etMatriz1Campo40.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(null);
            }
        }
        if (etMatriz1Campo41.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(null);
            }
        }
        if (etMatriz1Campo42.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(null);
            }
        }
        if (etMatriz1Campo43.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(null);
            }
        }
        if (etMatriz1Campo44.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(null);
            }
        }
        if (etMatriz1Campo45.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo45.getText().toString().isEmpty()) {
                etMatriz1Campo45.setError(null);
            }
        }
        if (etMatriz1Campo46.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo46.getText().toString().isEmpty()) {
                etMatriz1Campo46.setError(null);
            }
        }
        if (etMatriz1Campo50.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo50.getText().toString().isEmpty()) {
                etMatriz1Campo50.setError(null);
            }
        }
        if (etMatriz1Campo51.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo51.getText().toString().isEmpty()) {
                etMatriz1Campo51.setError(null);
            }
        }
        if (etMatriz1Campo52.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo52.getText().toString().isEmpty()) {
                etMatriz1Campo52.setError(null);
            }
        }
        if (etMatriz1Campo53.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo53.getText().toString().isEmpty()) {
                etMatriz1Campo53.setError(null);
            }
        }
        if (etMatriz1Campo54.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo54.getText().toString().isEmpty()) {
                etMatriz1Campo54.setError(null);
            }
        }
        if (etMatriz1Campo55.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo55.getText().toString().isEmpty()) {
                etMatriz1Campo55.setError(null);
            }
        }
        if (etMatriz1Campo56.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo56.getText().toString().isEmpty()) {
                etMatriz1Campo56.setError(null);
            }
        }
        if (etMatriz1Campo60.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo60.getText().toString().isEmpty()) {
                etMatriz1Campo60.setError(null);
            }
        }
        if (etMatriz1Campo61.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo61.getText().toString().isEmpty()) {
                etMatriz1Campo61.setError(null);
            }
        }
        if (etMatriz1Campo62.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo62.getText().toString().isEmpty()) {
                etMatriz1Campo62.setError(null);
            }
        }
        if (etMatriz1Campo63.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo63.getText().toString().isEmpty()) {
                etMatriz1Campo63.setError(null);
            }
        }
        if (etMatriz1Campo64.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo64.getText().toString().isEmpty()) {
                etMatriz1Campo64.setError(null);
            }
        }
        if (etMatriz1Campo65.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo65.getText().toString().isEmpty()) {
                etMatriz1Campo65.setError(null);
            }
        }
        if (etMatriz1Campo66.getVisibility() == View.VISIBLE) {
            if (etMatriz1Campo66.getText().toString().isEmpty()) {
                etMatriz1Campo66.setError(null);
            }
        }

        //endregion

        //region Matriz 2
        if (etMatriz2Campo00.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo00.getText().toString().isEmpty()) {
                etMatriz2Campo00.setError(null);
            }
        }

        if (etMatriz2Campo01.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo01.getText().toString().isEmpty()) {
                etMatriz2Campo01.setError(null);
            }
        }
        if (etMatriz2Campo02.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo02.getText().toString().isEmpty()) {
                etMatriz2Campo02.setError(null);
            }
        }
        if (etMatriz2Campo03.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo03.getText().toString().isEmpty()) {
                etMatriz2Campo03.setError(null);
            }
        }
        if (etMatriz2Campo04.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo04.getText().toString().isEmpty()) {
                etMatriz2Campo04.setError(null);
            }
        }
        if (etMatriz2Campo05.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo05.getText().toString().isEmpty()) {
                etMatriz2Campo05.setError(null);
            }
        }
        if (etMatriz2Campo06.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo06.getText().toString().isEmpty()) {
                etMatriz2Campo06.setError(null);
            }
        }
        if (etMatriz2Campo10.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo10.getText().toString().isEmpty()) {
                etMatriz2Campo10.setError(null);
            }
        }
        if (etMatriz2Campo11.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo11.getText().toString().isEmpty()) {
                etMatriz2Campo11.setError(null);
            }
        }
        if (etMatriz2Campo12.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo12.getText().toString().isEmpty()) {
                etMatriz2Campo12.setError(null);
            }
        }
        if (etMatriz2Campo13.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo13.getText().toString().isEmpty()) {
                etMatriz2Campo13.setError(null);
            }
        }
        if (etMatriz2Campo14.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo14.getText().toString().isEmpty()) {
                etMatriz2Campo14.setError(null);
            }
        }
        if (etMatriz2Campo15.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo15.getText().toString().isEmpty()) {
                etMatriz2Campo15.setError(null);
            }
        }
        if (etMatriz2Campo16.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo16.getText().toString().isEmpty()) {
                etMatriz2Campo16.setError(null);
            }
        }
        if (etMatriz2Campo20.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo20.getText().toString().isEmpty()) {
                etMatriz2Campo20.setError(null);
            }
        }
        if (etMatriz2Campo21.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo21.getText().toString().isEmpty()) {
                etMatriz2Campo21.setError(null);
            }
        }
        if (etMatriz2Campo22.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo22.getText().toString().isEmpty()) {
                etMatriz2Campo22.setError(null);
            }
        }
        if (etMatriz2Campo23.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo23.getText().toString().isEmpty()) {
                etMatriz2Campo23.setError(null);
            }
        }
        if (etMatriz2Campo24.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo24.getText().toString().isEmpty()) {
                etMatriz2Campo24.setError(null);
            }
        }
        if (etMatriz2Campo25.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo25.getText().toString().isEmpty()) {
                etMatriz2Campo25.setError(null);
            }
        }
        if (etMatriz2Campo26.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo26.getText().toString().isEmpty()) {
                etMatriz2Campo26.setError(null);
            }
        }
        if (etMatriz2Campo30.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo30.getText().toString().isEmpty()) {
                etMatriz2Campo30.setError(null);
            }
        }
        if (etMatriz2Campo31.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo31.getText().toString().isEmpty()) {
                etMatriz2Campo31.setError(null);
            }
        }
        if (etMatriz2Campo32.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo32.getText().toString().isEmpty()) {
                etMatriz2Campo32.setError(null);
            }
        }
        if (etMatriz2Campo33.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo33.getText().toString().isEmpty()) {
                etMatriz2Campo33.setError(null);
            }
        }
        if (etMatriz2Campo34.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo34.getText().toString().isEmpty()) {
                etMatriz2Campo34.setError(null);
            }
        }
        if (etMatriz2Campo35.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo35.getText().toString().isEmpty()) {
                etMatriz2Campo35.setError(null);
            }
        }
        if (etMatriz2Campo36.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo36.getText().toString().isEmpty()) {
                etMatriz2Campo36.setError(null);
            }
        }
        if (etMatriz2Campo40.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo40.getText().toString().isEmpty()) {
                etMatriz2Campo40.setError(null);
            }
        }
        if (etMatriz2Campo41.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo41.getText().toString().isEmpty()) {
                etMatriz2Campo41.setError(null);
            }
        }
        if (etMatriz2Campo42.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo42.getText().toString().isEmpty()) {
                etMatriz2Campo42.setError(null);
            }
        }
        if (etMatriz2Campo43.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo43.getText().toString().isEmpty()) {
                etMatriz2Campo43.setError(null);
            }
        }
        if (etMatriz2Campo44.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo44.getText().toString().isEmpty()) {
                etMatriz2Campo44.setError(null);
            }
        }
        if (etMatriz2Campo45.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo45.getText().toString().isEmpty()) {
                etMatriz2Campo45.setError(null);
            }
        }
        if (etMatriz2Campo46.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo46.getText().toString().isEmpty()) {
                etMatriz2Campo46.setError(null);
            }
        }
        if (etMatriz2Campo50.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo50.getText().toString().isEmpty()) {
                etMatriz2Campo50.setError(null);
            }
        }
        if (etMatriz2Campo51.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo51.getText().toString().isEmpty()) {
                etMatriz2Campo51.setError(null);
            }
        }
        if (etMatriz2Campo52.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo52.getText().toString().isEmpty()) {
                etMatriz2Campo52.setError(null);
            }
        }
        if (etMatriz2Campo53.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo53.getText().toString().isEmpty()) {
                etMatriz2Campo53.setError(null);
            }
        }
        if (etMatriz2Campo54.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo54.getText().toString().isEmpty()) {
                etMatriz2Campo54.setError(null);
            }
        }
        if (etMatriz2Campo55.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo55.getText().toString().isEmpty()) {
                etMatriz2Campo55.setError(null);
            }
        }
        if (etMatriz2Campo56.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo56.getText().toString().isEmpty()) {
                etMatriz2Campo56.setError(null);
            }
        }
        if (etMatriz2Campo60.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo60.getText().toString().isEmpty()) {
                etMatriz2Campo60.setError(null);
            }
        }
        if (etMatriz2Campo61.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo61.getText().toString().isEmpty()) {
                etMatriz2Campo61.setError(null);
            }
        }
        if (etMatriz2Campo62.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo62.getText().toString().isEmpty()) {
                etMatriz2Campo62.setError(null);
            }
        }
        if (etMatriz2Campo63.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo63.getText().toString().isEmpty()) {
                etMatriz2Campo63.setError(null);
            }
        }
        if (etMatriz2Campo64.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo64.getText().toString().isEmpty()) {
                etMatriz2Campo64.setError(null);
            }
        }
        if (etMatriz2Campo65.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo65.getText().toString().isEmpty()) {
                etMatriz2Campo65.setError(null);
            }
        }
        if (etMatriz2Campo66.getVisibility() == View.VISIBLE) {
            if (etMatriz2Campo66.getText().toString().isEmpty()) {
                etMatriz2Campo66.setError(null);
            }
        }
        //endregion
    }

    private void limpiarM1xM2() {

        //region Matriz 1
        if (etProductxEscalar.getVisibility() == View.VISIBLE) {
            etProductxEscalar.setText("");
        }
        if (etMatriz1Campo00.getVisibility() == View.VISIBLE) {
            etMatriz1Campo00.setText("");
        }
        if (etMatriz1Campo01.getVisibility() == View.VISIBLE) {
            etMatriz1Campo01.setText("");
        }
        if (etMatriz1Campo02.getVisibility() == View.VISIBLE) {
            etMatriz1Campo02.setText("");
        }
        if (etMatriz1Campo03.getVisibility() == View.VISIBLE) {
            etMatriz1Campo03.setText("");
        }
        if (etMatriz1Campo04.getVisibility() == View.VISIBLE) {
            etMatriz1Campo04.setText("");
        }
        if (etMatriz1Campo05.getVisibility() == View.VISIBLE) {
            etMatriz1Campo05.setText("");
        }
        if (etMatriz1Campo06.getVisibility() == View.VISIBLE) {
            etMatriz1Campo06.setText("");
        }
        if (etMatriz1Campo10.getVisibility() == View.VISIBLE) {
            etMatriz1Campo10.setText("");
        }
        if (etMatriz1Campo11.getVisibility() == View.VISIBLE) {
            etMatriz1Campo11.setText("");
        }
        if (etMatriz1Campo12.getVisibility() == View.VISIBLE) {
            etMatriz1Campo12.setText("");
        }
        if (etMatriz1Campo13.getVisibility() == View.VISIBLE) {
            etMatriz1Campo13.setText("");
        }
        if (etMatriz1Campo14.getVisibility() == View.VISIBLE) {
            etMatriz1Campo14.setText("");
        }
        if (etMatriz1Campo15.getVisibility() == View.VISIBLE) {
            etMatriz1Campo15.setText("");
        }
        if (etMatriz1Campo16.getVisibility() == View.VISIBLE) {
            etMatriz1Campo16.setText("");
        }
        if (etMatriz1Campo20.getVisibility() == View.VISIBLE) {
            etMatriz1Campo20.setText("");
        }
        if (etMatriz1Campo21.getVisibility() == View.VISIBLE) {
            etMatriz1Campo21.setText("");
        }
        if (etMatriz1Campo22.getVisibility() == View.VISIBLE) {
            etMatriz1Campo22.setText("");
        }
        if (etMatriz1Campo23.getVisibility() == View.VISIBLE) {
            etMatriz1Campo23.setText("");
        }
        if (etMatriz1Campo24.getVisibility() == View.VISIBLE) {
            etMatriz1Campo24.setText("");
        }
        if (etMatriz1Campo25.getVisibility() == View.VISIBLE) {
            etMatriz1Campo25.setText("");
        }
        if (etMatriz1Campo26.getVisibility() == View.VISIBLE) {
            etMatriz1Campo26.setText("");
        }
        if (etMatriz1Campo30.getVisibility() == View.VISIBLE) {
            etMatriz1Campo30.setText("");
        }
        if (etMatriz1Campo31.getVisibility() == View.VISIBLE) {
            etMatriz1Campo31.setText("");
        }
        if (etMatriz1Campo32.getVisibility() == View.VISIBLE) {
            etMatriz1Campo32.setText("");
        }
        if (etMatriz1Campo33.getVisibility() == View.VISIBLE) {
            etMatriz1Campo33.setText("");
        }
        if (etMatriz1Campo34.getVisibility() == View.VISIBLE) {
            etMatriz1Campo34.setText("");
        }
        if (etMatriz1Campo35.getVisibility() == View.VISIBLE) {
            etMatriz1Campo35.setText("");
        }
        if (etMatriz1Campo36.getVisibility() == View.VISIBLE) {
            etMatriz1Campo36.setText("");
        }
        if (etMatriz1Campo40.getVisibility() == View.VISIBLE) {
            etMatriz1Campo40.setText("");
        }
        if (etMatriz1Campo41.getVisibility() == View.VISIBLE) {
            etMatriz1Campo41.setText("");
        }
        if (etMatriz1Campo42.getVisibility() == View.VISIBLE) {
            etMatriz1Campo42.setText("");
        }
        if (etMatriz1Campo43.getVisibility() == View.VISIBLE) {
            etMatriz1Campo43.setText("");
        }
        if (etMatriz1Campo44.getVisibility() == View.VISIBLE) {
            etMatriz1Campo44.setText("");
        }
        if (etMatriz1Campo45.getVisibility() == View.VISIBLE) {
            etMatriz1Campo45.setText("");
        }
        if (etMatriz1Campo46.getVisibility() == View.VISIBLE) {
            etMatriz1Campo46.setText("");
        }
        if (etMatriz1Campo50.getVisibility() == View.VISIBLE) {
            etMatriz1Campo50.setText("");
        }
        if (etMatriz1Campo51.getVisibility() == View.VISIBLE) {
            etMatriz1Campo51.setText("");
        }
        if (etMatriz1Campo52.getVisibility() == View.VISIBLE) {
            etMatriz1Campo52.setText("");
        }
        if (etMatriz1Campo53.getVisibility() == View.VISIBLE) {
            etMatriz1Campo53.setText("");
        }
        if (etMatriz1Campo54.getVisibility() == View.VISIBLE) {
            etMatriz1Campo54.setText("");
        }
        if (etMatriz1Campo55.getVisibility() == View.VISIBLE) {
            etMatriz1Campo55.setText("");
        }
        if (etMatriz1Campo56.getVisibility() == View.VISIBLE) {
            etMatriz1Campo56.setText("");
        }
        if (etMatriz1Campo60.getVisibility() == View.VISIBLE) {
            etMatriz1Campo60.setText("");
        }
        if (etMatriz1Campo61.getVisibility() == View.VISIBLE) {
            etMatriz1Campo61.setText("");
        }
        if (etMatriz1Campo62.getVisibility() == View.VISIBLE) {
            etMatriz1Campo62.setText("");
        }
        if (etMatriz1Campo63.getVisibility() == View.VISIBLE) {
            etMatriz1Campo63.setText("");
        }
        if (etMatriz1Campo64.getVisibility() == View.VISIBLE) {
            etMatriz1Campo64.setText("");
        }
        if (etMatriz1Campo65.getVisibility() == View.VISIBLE) {
            etMatriz1Campo65.setText("");
        }
        if (etMatriz1Campo66.getVisibility() == View.VISIBLE) {
            etMatriz1Campo66.setText("");
        }

        //endregion

        //region Matriz 2
        if (etMatriz2Campo00.getVisibility() == View.VISIBLE) {
            etMatriz2Campo00.setText("");
        }
        if (etMatriz2Campo01.getVisibility() == View.VISIBLE) {
            etMatriz2Campo01.setText("");
        }
        if (etMatriz2Campo02.getVisibility() == View.VISIBLE) {
            etMatriz2Campo02.setText("");
        }
        if (etMatriz2Campo03.getVisibility() == View.VISIBLE) {
            etMatriz2Campo03.setText("");
        }
        if (etMatriz2Campo04.getVisibility() == View.VISIBLE) {
            etMatriz2Campo04.setText("");
        }
        if (etMatriz2Campo05.getVisibility() == View.VISIBLE) {
            etMatriz2Campo05.setText("");
        }
        if (etMatriz2Campo06.getVisibility() == View.VISIBLE) {
            etMatriz2Campo06.setText("");
        }
        if (etMatriz2Campo10.getVisibility() == View.VISIBLE) {
            etMatriz2Campo10.setText("");
        }
        if (etMatriz2Campo11.getVisibility() == View.VISIBLE) {
            etMatriz2Campo11.setText("");
        }
        if (etMatriz2Campo12.getVisibility() == View.VISIBLE) {
            etMatriz2Campo12.setText("");
        }
        if (etMatriz2Campo13.getVisibility() == View.VISIBLE) {
            etMatriz2Campo13.setText("");
        }
        if (etMatriz2Campo14.getVisibility() == View.VISIBLE) {
            etMatriz2Campo14.setText("");
        }
        if (etMatriz2Campo15.getVisibility() == View.VISIBLE) {
            etMatriz2Campo15.setText("");
        }
        if (etMatriz2Campo16.getVisibility() == View.VISIBLE) {
            etMatriz2Campo16.setText("");
        }
        if (etMatriz2Campo20.getVisibility() == View.VISIBLE) {
            etMatriz2Campo20.setText("");
        }
        if (etMatriz2Campo21.getVisibility() == View.VISIBLE) {
            etMatriz2Campo21.setText("");
        }
        if (etMatriz2Campo22.getVisibility() == View.VISIBLE) {
            etMatriz2Campo22.setText("");
        }
        if (etMatriz2Campo23.getVisibility() == View.VISIBLE) {
            etMatriz2Campo23.setText("");
        }
        if (etMatriz2Campo24.getVisibility() == View.VISIBLE) {
            etMatriz2Campo24.setText("");
        }
        if (etMatriz2Campo25.getVisibility() == View.VISIBLE) {
            etMatriz2Campo25.setText("");
        }
        if (etMatriz2Campo26.getVisibility() == View.VISIBLE) {
            etMatriz2Campo26.setText("");
        }
        if (etMatriz2Campo30.getVisibility() == View.VISIBLE) {
            etMatriz2Campo30.setText("");
        }
        if (etMatriz2Campo31.getVisibility() == View.VISIBLE) {
            etMatriz2Campo31.setText("");
        }
        if (etMatriz2Campo32.getVisibility() == View.VISIBLE) {
            etMatriz2Campo32.setText("");
        }
        if (etMatriz2Campo33.getVisibility() == View.VISIBLE) {
            etMatriz2Campo33.setText("");
        }
        if (etMatriz2Campo34.getVisibility() == View.VISIBLE) {
            etMatriz2Campo34.setText("");
        }
        if (etMatriz2Campo35.getVisibility() == View.VISIBLE) {
            etMatriz2Campo35.setText("");
        }
        if (etMatriz2Campo36.getVisibility() == View.VISIBLE) {
            etMatriz2Campo36.setText("");
        }
        if (etMatriz2Campo40.getVisibility() == View.VISIBLE) {
            etMatriz2Campo40.setText("");
        }
        if (etMatriz2Campo41.getVisibility() == View.VISIBLE) {
            etMatriz2Campo41.setText("");
        }
        if (etMatriz2Campo42.getVisibility() == View.VISIBLE) {
            etMatriz2Campo42.setText("");
        }
        if (etMatriz2Campo43.getVisibility() == View.VISIBLE) {
            etMatriz2Campo43.setText("");
        }
        if (etMatriz2Campo44.getVisibility() == View.VISIBLE) {
            etMatriz2Campo44.setText("");
        }
        if (etMatriz2Campo45.getVisibility() == View.VISIBLE) {
            etMatriz2Campo45.setText("");
        }
        if (etMatriz2Campo46.getVisibility() == View.VISIBLE) {
            etMatriz2Campo46.setText("");
        }
        if (etMatriz2Campo50.getVisibility() == View.VISIBLE) {
            etMatriz2Campo50.setText("");
        }
        if (etMatriz2Campo51.getVisibility() == View.VISIBLE) {
            etMatriz2Campo51.setText("");
        }
        if (etMatriz2Campo52.getVisibility() == View.VISIBLE) {
            etMatriz2Campo52.setText("");
        }
        if (etMatriz2Campo53.getVisibility() == View.VISIBLE) {
            etMatriz2Campo53.setText("");
        }
        if (etMatriz2Campo54.getVisibility() == View.VISIBLE) {
            etMatriz2Campo54.setText("");
        }
        if (etMatriz2Campo55.getVisibility() == View.VISIBLE) {
            etMatriz2Campo55.setText("");
        }
        if (etMatriz2Campo56.getVisibility() == View.VISIBLE) {
            etMatriz2Campo56.setText("");
        }
        if (etMatriz2Campo60.getVisibility() == View.VISIBLE) {
            etMatriz2Campo60.setText("");
        }
        if (etMatriz2Campo61.getVisibility() == View.VISIBLE) {
            etMatriz2Campo61.setText("");
        }
        if (etMatriz2Campo62.getVisibility() == View.VISIBLE) {
            etMatriz2Campo62.setText("");
        }
        if (etMatriz2Campo63.getVisibility() == View.VISIBLE) {
            etMatriz2Campo63.setText("");
        }
        if (etMatriz2Campo64.getVisibility() == View.VISIBLE) {
            etMatriz2Campo64.setText("");
        }
        if (etMatriz2Campo65.getVisibility() == View.VISIBLE) {
            etMatriz2Campo65.setText("");
        }
        if (etMatriz2Campo66.getVisibility() == View.VISIBLE) {
            etMatriz2Campo66.setText("");
        }

        //endregion
    }

    private void calcMProducto() {
        int fila1 = Integer.parseInt(spinnerProductoM1_1.getSelectedItem().toString());
        int columna1 = Integer.parseInt(spinnerProductoM1_2.getSelectedItem().toString());

        int fila2 = Integer.parseInt(spinnerProductoM2_1.getSelectedItem().toString());
        int columna2 = Integer.parseInt(spinnerProductoM2_2.getSelectedItem().toString());

        double m1f00 = 0, m1f01 = 0, m1f02 = 0, m1f03 = 0, m1f04 = 0, m1f05 = 0, m1f06 = 0,
                m1f10 = 0, m1f11 = 0, m1f12 = 0, m1f13 = 0, m1f14 = 0, m1f15 = 0, m1f16 = 0,
                m1f20 = 0, m1f21 = 0, m1f22 = 0, m1f23 = 0, m1f24 = 0, m1f25 = 0, m1f26 = 0,
                m1f30 = 0, m1f31 = 0, m1f32 = 0, m1f33 = 0, m1f34 = 0, m1f35 = 0, m1f36 = 0,
                m1f40 = 0, m1f41 = 0, m1f42 = 0, m1f43 = 0, m1f44 = 0, m1f45 = 0, m1f46 = 0,
                m1f50 = 0, m1f51 = 0, m1f52 = 0, m1f53 = 0, m1f54 = 0, m1f55 = 0, m1f56 = 0,
                m1f60 = 0, m1f61 = 0, m1f62 = 0, m1f63 = 0, m1f64 = 0, m1f65 = 0, m1f66 = 0;

        double m2f00 = 0, m2f01 = 0, m2f02 = 0, m2f03 = 0, m2f04 = 0, m2f05 = 0, m2f06 = 0,
                m2f10 = 0, m2f11 = 0, m2f12 = 0, m2f13 = 0, m2f14 = 0, m2f15 = 0, m2f16 = 0,
                m2f20 = 0, m2f21 = 0, m2f22 = 0, m2f23 = 0, m2f24 = 0, m2f25 = 0, m2f26 = 0,
                m2f30 = 0, m2f31 = 0, m2f32 = 0, m2f33 = 0, m2f34 = 0, m2f35 = 0, m2f36 = 0,
                m2f40 = 0, m2f41 = 0, m2f42 = 0, m2f43 = 0, m2f44 = 0, m2f45 = 0, m2f46 = 0,
                m2f50 = 0, m2f51 = 0, m2f52 = 0, m2f53 = 0, m2f54 = 0, m2f55 = 0, m2f56 = 0,
                m2f60 = 0, m2f61 = 0, m2f62 = 0, m2f63 = 0, m2f64 = 0, m2f65 = 0, m2f66 = 0;

        //region Matriz 1
        if (etMatriz1Campo00.getVisibility() == View.VISIBLE) {
            m1f00 = Double.parseDouble(etMatriz1Campo00.getText().toString());
        }
        if (etMatriz1Campo01.getVisibility() == View.VISIBLE) {
            m1f01 = Double.parseDouble(etMatriz1Campo01.getText().toString());
        }
        if (etMatriz1Campo02.getVisibility() == View.VISIBLE) {
            m1f02 = Double.parseDouble(etMatriz1Campo02.getText().toString());
        }
        if (etMatriz1Campo03.getVisibility() == View.VISIBLE) {
            m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
        }
        if (etMatriz1Campo04.getVisibility() == View.VISIBLE) {
            m1f04 = Double.parseDouble(etMatriz1Campo04.getText().toString());
        }
        if (etMatriz1Campo05.getVisibility() == View.VISIBLE) {
            m1f05 = Double.parseDouble(etMatriz1Campo05.getText().toString());
        }
        if (etMatriz1Campo06.getVisibility() == View.VISIBLE) {
            m1f06 = Double.parseDouble(etMatriz1Campo06.getText().toString());
        }
        if (etMatriz1Campo10.getVisibility() == View.VISIBLE) {
            m1f10 = Double.parseDouble(etMatriz1Campo10.getText().toString());
        }
        if (etMatriz1Campo11.getVisibility() == View.VISIBLE) {
            m1f11 = Double.parseDouble(etMatriz1Campo11.getText().toString());
        }
        if (etMatriz1Campo12.getVisibility() == View.VISIBLE) {
            m1f12 = Double.parseDouble(etMatriz1Campo12.getText().toString());
        }
        if (etMatriz1Campo13.getVisibility() == View.VISIBLE) {
            m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
        }
        if (etMatriz1Campo14.getVisibility() == View.VISIBLE) {
            m1f14 = Double.parseDouble(etMatriz1Campo14.getText().toString());
        }
        if (etMatriz1Campo15.getVisibility() == View.VISIBLE) {
            m1f15 = Double.parseDouble(etMatriz1Campo15.getText().toString());
        }
        if (etMatriz1Campo16.getVisibility() == View.VISIBLE) {
            m1f16 = Double.parseDouble(etMatriz1Campo16.getText().toString());
        }
        if (etMatriz1Campo20.getVisibility() == View.VISIBLE) {
            m1f20 = Double.parseDouble(etMatriz1Campo20.getText().toString());
        }
        if (etMatriz1Campo21.getVisibility() == View.VISIBLE) {
            m1f21 = Double.parseDouble(etMatriz1Campo21.getText().toString());
        }
        if (etMatriz1Campo22.getVisibility() == View.VISIBLE) {
            m1f22 = Double.parseDouble(etMatriz1Campo22.getText().toString());
        }
        if (etMatriz1Campo23.getVisibility() == View.VISIBLE) {
            m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());
        }
        if (etMatriz1Campo24.getVisibility() == View.VISIBLE) {
            m1f24 = Double.parseDouble(etMatriz1Campo24.getText().toString());
        }
        if (etMatriz1Campo25.getVisibility() == View.VISIBLE) {
            m1f25 = Double.parseDouble(etMatriz1Campo25.getText().toString());
        }
        if (etMatriz1Campo26.getVisibility() == View.VISIBLE) {
            m1f26 = Double.parseDouble(etMatriz1Campo26.getText().toString());
        }
        if (etMatriz1Campo30.getVisibility() == View.VISIBLE) {
            m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
        }
        if (etMatriz1Campo31.getVisibility() == View.VISIBLE) {
            m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
        }
        if (etMatriz1Campo32.getVisibility() == View.VISIBLE) {
            m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
        }
        if (etMatriz1Campo33.getVisibility() == View.VISIBLE) {
            m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
        }
        if (etMatriz1Campo34.getVisibility() == View.VISIBLE) {
            m1f34 = Double.parseDouble(etMatriz1Campo34.getText().toString());
        }
        if (etMatriz1Campo35.getVisibility() == View.VISIBLE) {
            m1f35 = Double.parseDouble(etMatriz1Campo35.getText().toString());
        }
        if (etMatriz1Campo36.getVisibility() == View.VISIBLE) {
            m1f36 = Double.parseDouble(etMatriz1Campo36.getText().toString());
        }
        if (etMatriz1Campo40.getVisibility() == View.VISIBLE) {
            m1f40 = Double.parseDouble(etMatriz1Campo40.getText().toString());
        }
        if (etMatriz1Campo41.getVisibility() == View.VISIBLE) {
            m1f41 = Double.parseDouble(etMatriz1Campo41.getText().toString());
        }
        if (etMatriz1Campo42.getVisibility() == View.VISIBLE) {
            m1f42 = Double.parseDouble(etMatriz1Campo42.getText().toString());
        }
        if (etMatriz1Campo43.getVisibility() == View.VISIBLE) {
            m1f43 = Double.parseDouble(etMatriz1Campo43.getText().toString());
        }
        if (etMatriz1Campo44.getVisibility() == View.VISIBLE) {
            m1f44 = Double.parseDouble(etMatriz1Campo44.getText().toString());
        }
        if (etMatriz1Campo45.getVisibility() == View.VISIBLE) {
            m1f45 = Double.parseDouble(etMatriz1Campo45.getText().toString());
        }
        if (etMatriz1Campo46.getVisibility() == View.VISIBLE) {
            m1f46 = Double.parseDouble(etMatriz1Campo46.getText().toString());
        }
        if (etMatriz1Campo50.getVisibility() == View.VISIBLE) {
            m1f50 = Double.parseDouble(etMatriz1Campo50.getText().toString());
        }
        if (etMatriz1Campo51.getVisibility() == View.VISIBLE) {
            m1f51 = Double.parseDouble(etMatriz1Campo51.getText().toString());
        }
        if (etMatriz1Campo52.getVisibility() == View.VISIBLE) {
            m1f52 = Double.parseDouble(etMatriz1Campo52.getText().toString());
        }
        if (etMatriz1Campo53.getVisibility() == View.VISIBLE) {
            m1f53 = Double.parseDouble(etMatriz1Campo53.getText().toString());
        }
        if (etMatriz1Campo54.getVisibility() == View.VISIBLE) {
            m1f54 = Double.parseDouble(etMatriz1Campo54.getText().toString());
        }
        if (etMatriz1Campo55.getVisibility() == View.VISIBLE) {
            m1f55 = Double.parseDouble(etMatriz1Campo55.getText().toString());
        }
        if (etMatriz1Campo56.getVisibility() == View.VISIBLE) {
            m1f56 = Double.parseDouble(etMatriz1Campo56.getText().toString());
        }
        if (etMatriz1Campo60.getVisibility() == View.VISIBLE) {
            m1f60 = Double.parseDouble(etMatriz1Campo60.getText().toString());
        }
        if (etMatriz1Campo61.getVisibility() == View.VISIBLE) {
            m1f61 = Double.parseDouble(etMatriz1Campo61.getText().toString());
        }
        if (etMatriz1Campo62.getVisibility() == View.VISIBLE) {
            m1f62 = Double.parseDouble(etMatriz1Campo62.getText().toString());
        }
        if (etMatriz1Campo63.getVisibility() == View.VISIBLE) {
            m1f63 = Double.parseDouble(etMatriz1Campo63.getText().toString());
        }
        if (etMatriz1Campo64.getVisibility() == View.VISIBLE) {
            m1f64 = Double.parseDouble(etMatriz1Campo64.getText().toString());
        }
        if (etMatriz1Campo65.getVisibility() == View.VISIBLE) {
            m1f65 = Double.parseDouble(etMatriz1Campo65.getText().toString());
        }
        if (etMatriz1Campo66.getVisibility() == View.VISIBLE) {
            m1f66 = Double.parseDouble(etMatriz1Campo66.getText().toString());
        }

        //endregion

        //region Matriz 2
        if (etMatriz2Campo00.getVisibility() == View.VISIBLE) {
            m2f00 = Double.parseDouble(etMatriz2Campo00.getText().toString());
        }
        if (etMatriz2Campo01.getVisibility() == View.VISIBLE) {
            m2f01 = Double.parseDouble(etMatriz2Campo01.getText().toString());
        }
        if (etMatriz2Campo02.getVisibility() == View.VISIBLE) {
            m2f02 = Double.parseDouble(etMatriz2Campo02.getText().toString());
        }
        if (etMatriz2Campo03.getVisibility() == View.VISIBLE) {
            m2f03 = Double.parseDouble(etMatriz2Campo03.getText().toString());
        }
        if (etMatriz2Campo04.getVisibility() == View.VISIBLE) {
            m2f04 = Double.parseDouble(etMatriz2Campo04.getText().toString());
        }
        if (etMatriz2Campo05.getVisibility() == View.VISIBLE) {
            m2f05 = Double.parseDouble(etMatriz2Campo05.getText().toString());
        }
        if (etMatriz2Campo06.getVisibility() == View.VISIBLE) {
            m2f06 = Double.parseDouble(etMatriz2Campo06.getText().toString());
        }
        if (etMatriz2Campo10.getVisibility() == View.VISIBLE) {
            m2f10 = Double.parseDouble(etMatriz2Campo10.getText().toString());
        }
        if (etMatriz2Campo11.getVisibility() == View.VISIBLE) {
            m2f11 = Double.parseDouble(etMatriz2Campo11.getText().toString());
        }
        if (etMatriz2Campo12.getVisibility() == View.VISIBLE) {
            m2f12 = Double.parseDouble(etMatriz2Campo12.getText().toString());
        }
        if (etMatriz2Campo13.getVisibility() == View.VISIBLE) {
            m2f13 = Double.parseDouble(etMatriz2Campo13.getText().toString());
        }
        if (etMatriz2Campo14.getVisibility() == View.VISIBLE) {
            m2f14 = Double.parseDouble(etMatriz2Campo14.getText().toString());
        }
        if (etMatriz2Campo15.getVisibility() == View.VISIBLE) {
            m2f15 = Double.parseDouble(etMatriz2Campo15.getText().toString());
        }
        if (etMatriz2Campo16.getVisibility() == View.VISIBLE) {
            m2f16 = Double.parseDouble(etMatriz2Campo16.getText().toString());
        }
        if (etMatriz2Campo20.getVisibility() == View.VISIBLE) {
            m2f20 = Double.parseDouble(etMatriz2Campo20.getText().toString());
        }
        if (etMatriz2Campo21.getVisibility() == View.VISIBLE) {
            m2f21 = Double.parseDouble(etMatriz2Campo21.getText().toString());
        }
        if (etMatriz2Campo22.getVisibility() == View.VISIBLE) {
            m2f22 = Double.parseDouble(etMatriz2Campo22.getText().toString());
        }
        if (etMatriz2Campo23.getVisibility() == View.VISIBLE) {
            m2f23 = Double.parseDouble(etMatriz2Campo23.getText().toString());
        }
        if (etMatriz2Campo24.getVisibility() == View.VISIBLE) {
            m2f24 = Double.parseDouble(etMatriz2Campo24.getText().toString());
        }
        if (etMatriz2Campo25.getVisibility() == View.VISIBLE) {
            m2f25 = Double.parseDouble(etMatriz2Campo25.getText().toString());
        }
        if (etMatriz2Campo26.getVisibility() == View.VISIBLE) {
            m2f26 = Double.parseDouble(etMatriz2Campo26.getText().toString());
        }
        if (etMatriz2Campo30.getVisibility() == View.VISIBLE) {
            m2f30 = Double.parseDouble(etMatriz2Campo30.getText().toString());
        }
        if (etMatriz2Campo31.getVisibility() == View.VISIBLE) {
            m2f31 = Double.parseDouble(etMatriz2Campo31.getText().toString());
        }
        if (etMatriz2Campo32.getVisibility() == View.VISIBLE) {
            m2f32 = Double.parseDouble(etMatriz2Campo32.getText().toString());
        }
        if (etMatriz2Campo33.getVisibility() == View.VISIBLE) {
            m2f33 = Double.parseDouble(etMatriz2Campo33.getText().toString());
        }
        if (etMatriz2Campo34.getVisibility() == View.VISIBLE) {
            m2f34 = Double.parseDouble(etMatriz2Campo34.getText().toString());
        }
        if (etMatriz2Campo35.getVisibility() == View.VISIBLE) {
            m2f35 = Double.parseDouble(etMatriz2Campo35.getText().toString());
        }
        if (etMatriz2Campo36.getVisibility() == View.VISIBLE) {
            m2f36 = Double.parseDouble(etMatriz2Campo36.getText().toString());
        }
        if (etMatriz2Campo40.getVisibility() == View.VISIBLE) {
            m2f40 = Double.parseDouble(etMatriz2Campo40.getText().toString());
        }
        if (etMatriz2Campo41.getVisibility() == View.VISIBLE) {
            m2f41 = Double.parseDouble(etMatriz2Campo41.getText().toString());
        }
        if (etMatriz2Campo42.getVisibility() == View.VISIBLE) {
            m2f42 = Double.parseDouble(etMatriz2Campo42.getText().toString());
        }
        if (etMatriz2Campo43.getVisibility() == View.VISIBLE) {
            m2f43 = Double.parseDouble(etMatriz2Campo43.getText().toString());
        }
        if (etMatriz2Campo44.getVisibility() == View.VISIBLE) {
            m2f44 = Double.parseDouble(etMatriz2Campo44.getText().toString());
        }
        if (etMatriz2Campo45.getVisibility() == View.VISIBLE) {
            m2f45 = Double.parseDouble(etMatriz2Campo45.getText().toString());
        }
        if (etMatriz2Campo46.getVisibility() == View.VISIBLE) {
            m2f46 = Double.parseDouble(etMatriz2Campo46.getText().toString());
        }
        if (etMatriz2Campo50.getVisibility() == View.VISIBLE) {
            m2f50 = Double.parseDouble(etMatriz2Campo50.getText().toString());
        }
        if (etMatriz2Campo51.getVisibility() == View.VISIBLE) {
            m2f51 = Double.parseDouble(etMatriz2Campo51.getText().toString());
        }
        if (etMatriz2Campo52.getVisibility() == View.VISIBLE) {
            m2f52 = Double.parseDouble(etMatriz2Campo52.getText().toString());
        }
        if (etMatriz2Campo53.getVisibility() == View.VISIBLE) {
            m2f53 = Double.parseDouble(etMatriz2Campo53.getText().toString());
        }
        if (etMatriz2Campo54.getVisibility() == View.VISIBLE) {
            m2f54 = Double.parseDouble(etMatriz2Campo54.getText().toString());
        }
        if (etMatriz2Campo55.getVisibility() == View.VISIBLE) {
            m2f55 = Double.parseDouble(etMatriz2Campo55.getText().toString());
        }
        if (etMatriz2Campo56.getVisibility() == View.VISIBLE) {
            m2f56 = Double.parseDouble(etMatriz2Campo56.getText().toString());
        }
        if (etMatriz2Campo60.getVisibility() == View.VISIBLE) {
            m2f60 = Double.parseDouble(etMatriz2Campo60.getText().toString());
        }
        if (etMatriz2Campo61.getVisibility() == View.VISIBLE) {
            m2f61 = Double.parseDouble(etMatriz2Campo61.getText().toString());
        }
        if (etMatriz2Campo62.getVisibility() == View.VISIBLE) {
            m2f62 = Double.parseDouble(etMatriz2Campo62.getText().toString());
        }
        if (etMatriz2Campo63.getVisibility() == View.VISIBLE) {
            m2f63 = Double.parseDouble(etMatriz2Campo63.getText().toString());
        }
        if (etMatriz2Campo64.getVisibility() == View.VISIBLE) {
            m2f64 = Double.parseDouble(etMatriz2Campo64.getText().toString());
        }
        if (etMatriz2Campo65.getVisibility() == View.VISIBLE) {
            m2f65 = Double.parseDouble(etMatriz2Campo65.getText().toString());
        }
        if (etMatriz2Campo66.getVisibility() == View.VISIBLE) {
            m2f66 = Double.parseDouble(etMatriz2Campo66.getText().toString());
        }

        //endregion

        //region Matriz 1 Captura datos
        if (fila1 == 3 && columna1 == 3) {
            M1 = new double[][]{{m1f00, m1f01, m1f02}, {m1f10, m1f11, m1f12}, {m1f20, m1f21, m1f22}};
        } else if (fila1 == 3 && columna1 == 4) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03}, {m1f10, m1f11, m1f12, m1f13}, {m1f20, m1f21, m1f22, m1f23}};
        } else if (fila1 == 3 && columna1 == 5) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04}, {m1f10, m1f11, m1f12, m1f13, m1f14}, {m1f20, m1f21, m1f22, m1f23, m1f24}};
        } else if (fila1 == 3 && columna1 == 6) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25}};
        } else if (fila1 == 3 && columna1 == 7) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05, m1f06}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15, m1f16}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25, m1f26}};
        } else if (fila1 == 4 && columna1 == 3) {
            M1 = new double[][]{{m1f00, m1f01, m1f02}, {m1f10, m1f11, m1f12}, {m1f20, m1f21, m1f22}, {m1f30, m1f31, m1f32}};
        } else if (fila1 == 4 && columna1 == 4) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03}, {m1f10, m1f11, m1f12, m1f13}, {m1f20, m1f21, m1f22, m1f23}, {m1f30, m1f31, m1f32, m1f33}};
        } else if (fila1 == 4 && columna1 == 5) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04}, {m1f10, m1f11, m1f12, m1f13, m1f14}, {m1f20, m1f21, m1f22, m1f23, m1f24}, {m1f30, m1f31, m1f32, m1f33, m1f34}};
        } else if (fila1 == 4 && columna1 == 6) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35}};
        } else if (fila1 == 4 && columna1 == 7) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05, m1f06}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15, m1f16}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25, m1f26}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35, m1f36}};
        } else if (fila1 == 5 && columna1 == 3) {
            M1 = new double[][]{{m1f00, m1f01, m1f02}, {m1f10, m1f11, m1f12}, {m1f20, m1f21, m1f22}, {m1f30, m1f31, m1f32}, {m1f40, m1f41, m1f42}};
        } else if (fila1 == 5 && columna1 == 4) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03}, {m1f10, m1f11, m1f12, m1f13}, {m1f20, m1f21, m1f22, m1f23}, {m1f30, m1f31, m1f32, m1f33}, {m1f40, m1f41, m1f42, m1f43}};
        } else if (fila1 == 5 && columna1 == 5) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04}, {m1f10, m1f11, m1f12, m1f13, m1f14}, {m1f20, m1f21, m1f22, m1f23, m1f24}, {m1f30, m1f31, m1f32, m1f33, m1f34}, {m1f40, m1f41, m1f42, m1f43, m1f44}};
        } else if (fila1 == 5 && columna1 == 6) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45}};
        } else if (fila1 == 5 && columna1 == 7) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05, m1f06}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15, m1f16}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25, m1f26}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35, m1f36}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45, m1f46}};
        } else if (fila1 == 6 && columna1 == 3) {
            M1 = new double[][]{{m1f00, m1f01, m1f02}, {m1f10, m1f11, m1f12}, {m1f20, m1f21, m1f22}, {m1f30, m1f31, m1f32}, {m1f40, m1f41, m1f42}, {m1f50, m1f51, m1f52}};
        } else if (fila1 == 6 && columna1 == 4) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03}, {m1f10, m1f11, m1f12, m1f13}, {m1f20, m1f21, m1f22, m1f23}, {m1f30, m1f31, m1f32, m1f33}, {m1f40, m1f41, m1f42, m1f43}, {m1f50, m1f51, m1f52, m1f53}};
        } else if (fila1 == 6 && columna1 == 5) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04}, {m1f10, m1f11, m1f12, m1f13, m1f14}, {m1f20, m1f21, m1f22, m1f23, m1f24}, {m1f30, m1f31, m1f32, m1f33, m1f34}, {m1f40, m1f41, m1f42, m1f43, m1f44}, {m1f50, m1f51, m1f52, m1f53, m1f54}};
        } else if (fila1 == 6 && columna1 == 6) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55}};
        } else if (fila1 == 6 && columna1 == 7) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05, m1f06}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15, m1f16}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25, m1f26}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35, m1f36}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45, m1f46}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55, m1f56}};
        } else if (fila1 == 7 && columna1 == 3) {
            M1 = new double[][]{{m1f00, m1f01, m1f02}, {m1f10, m1f11, m1f12}, {m1f20, m1f21, m1f22}, {m1f30, m1f31, m1f32}, {m1f40, m1f41, m1f42}, {m1f50, m1f51, m1f52}, {m1f60, m1f61, m1f62}};
        } else if (fila1 == 7 && columna1 == 4) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03}, {m1f10, m1f11, m1f12, m1f13}, {m1f20, m1f21, m1f22, m1f23}, {m1f30, m1f31, m1f32, m1f33}, {m1f40, m1f41, m1f42, m1f43}, {m1f50, m1f51, m1f52, m1f53}, {m1f60, m1f61, m1f62, m1f63}};
        } else if (fila1 == 7 && columna1 == 5) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04}, {m1f10, m1f11, m1f12, m1f13, m1f14}, {m1f20, m1f21, m1f22, m1f23, m1f24}, {m1f30, m1f31, m1f32, m1f33, m1f34}, {m1f40, m1f41, m1f42, m1f43, m1f44}, {m1f50, m1f51, m1f52, m1f53, m1f54}, {m1f60, m1f61, m1f62, m1f63, m1f64}};
        } else if (fila1 == 7 && columna1 == 6) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55}, {m1f60, m1f61, m1f62, m1f63, m1f64, m1f65}};
        } else if (fila1 == 7 && columna1 == 7) {
            M1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05, m1f06}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15, m1f16}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25, m1f26}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35, m1f36}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45, m1f46}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55, m1f56}, {m1f60, m1f61, m1f62, m1f63, m1f64, m1f65, m1f66}};
        }
        //endregion

        //region Matriz 2 Captura datos
        if (fila2 == 3 && columna2 == 3) {
            M2 = new double[][]{{m2f00, m2f01, m2f02}, {m2f10, m2f11, m2f12}, {m2f20, m2f21, m2f22}};
        } else if (fila2 == 3 && columna2 == 4) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03}, {m2f10, m2f11, m2f12, m2f13}, {m2f20, m2f21, m2f22, m2f23}};
        } else if (fila2 == 3 && columna2 == 5) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04}, {m2f10, m2f11, m2f12, m2f13, m2f14}, {m2f20, m2f21, m2f22, m2f23, m2f24}};
        } else if (fila2 == 3 && columna2 == 6) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25}};
        } else if (fila2 == 3 && columna2 == 7) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05, m2f06}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15, m2f16}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25, m2f26}};
        } else if (fila2 == 4 && columna2 == 3) {
            M2 = new double[][]{{m2f00, m2f01, m2f02}, {m2f10, m2f11, m2f12}, {m2f20, m2f21, m2f22}, {m2f30, m2f31, m2f32}};
        } else if (fila2 == 4 && columna2 == 4) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03}, {m2f10, m2f11, m2f12, m2f13}, {m2f20, m2f21, m2f22, m2f23}, {m2f30, m2f31, m2f32, m2f33}};
        } else if (fila2 == 4 && columna2 == 5) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04}, {m2f10, m2f11, m2f12, m2f13, m2f14}, {m2f20, m2f21, m2f22, m2f23, m2f24}, {m2f30, m2f31, m2f32, m2f33, m2f34}};
        } else if (fila2 == 4 && columna2 == 6) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35}};
        } else if (fila2 == 4 && columna2 == 7) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05, m2f06}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15, m2f16}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25, m2f26}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35, m2f36}};
        } else if (fila2 == 5 && columna2 == 3) {
            M2 = new double[][]{{m2f00, m2f01, m2f02}, {m2f10, m2f11, m2f12}, {m2f20, m2f21, m2f22}, {m2f30, m2f31, m2f32}, {m2f40, m2f41, m2f42}};
        } else if (fila2 == 5 && columna2 == 4) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03}, {m2f10, m2f11, m2f12, m2f13}, {m2f20, m2f21, m2f22, m2f23}, {m2f30, m2f31, m2f32, m2f33}, {m2f40, m2f41, m2f42, m2f43}};
        } else if (fila2 == 5 && columna2 == 5) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04}, {m2f10, m2f11, m2f12, m2f13, m2f14}, {m2f20, m2f21, m2f22, m2f23, m2f24}, {m2f30, m2f31, m2f32, m2f33, m2f34}, {m2f40, m2f41, m2f42, m2f43, m2f44}};
        } else if (fila2 == 5 && columna2 == 6) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45}};
        } else if (fila2 == 5 && columna2 == 7) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05, m2f06}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15, m2f16}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25, m2f26}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35, m2f36}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45, m2f46}};
        } else if (fila2 == 6 && columna2 == 3) {
            M2 = new double[][]{{m2f00, m2f01, m2f02}, {m2f10, m2f11, m2f12}, {m2f20, m2f21, m2f22}, {m2f30, m2f31, m2f32}, {m2f40, m2f41, m2f42}, {m2f50, m2f51, m2f52}};
        } else if (fila2 == 6 && columna2 == 4) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03}, {m2f10, m2f11, m2f12, m2f13}, {m2f20, m2f21, m2f22, m2f23}, {m2f30, m2f31, m2f32, m2f33}, {m2f40, m2f41, m2f42, m2f43}, {m2f50, m2f51, m2f52, m2f53}};
        } else if (fila2 == 6 && columna2 == 5) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04}, {m2f10, m2f11, m2f12, m2f13, m2f14}, {m2f20, m2f21, m2f22, m2f23, m2f24}, {m2f30, m2f31, m2f32, m2f33, m2f34}, {m2f40, m2f41, m2f42, m2f43, m2f44}, {m2f50, m2f51, m2f52, m2f53, m2f54}};
        } else if (fila2 == 6 && columna2 == 6) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45}, {m2f50, m2f51, m2f52, m2f53, m2f54, m2f55}};
        } else if (fila2 == 6 && columna2 == 7) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05, m2f06}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15, m2f16}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25, m2f26}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35, m2f36}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45, m2f46}, {m2f50, m2f51, m2f52, m2f53, m2f54, m2f55, m2f56}};
        } else if (fila2 == 7 && columna2 == 3) {
            M2 = new double[][]{{m2f00, m2f01, m2f02}, {m2f10, m2f11, m2f12}, {m2f20, m2f21, m2f22}, {m2f30, m2f31, m2f32}, {m2f40, m2f41, m2f42}, {m2f50, m2f51, m2f52}, {m2f60, m2f61, m2f62}};
        } else if (fila2 == 7 && columna2 == 4) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03}, {m2f10, m2f11, m2f12, m2f13}, {m2f20, m2f21, m2f22, m2f23}, {m2f30, m2f31, m2f32, m2f33}, {m2f40, m2f41, m2f42, m2f43}, {m2f50, m2f51, m2f52, m2f53}, {m2f60, m2f61, m2f62, m2f63}};
        } else if (fila2 == 7 && columna2 == 5) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04}, {m2f10, m2f11, m2f12, m2f13, m2f14}, {m2f20, m2f21, m2f22, m2f23, m2f24}, {m2f30, m2f31, m2f32, m2f33, m2f34}, {m2f40, m2f41, m2f42, m2f43, m2f44}, {m2f50, m2f51, m2f52, m2f53, m2f54}, {m2f60, m2f61, m2f62, m2f63, m2f64}};
        } else if (fila2 == 7 && columna2 == 6) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45}, {m2f50, m2f51, m2f52, m2f53, m2f54, m2f55}, {m2f60, m2f61, m2f62, m2f63, m2f64, m2f65}};
        } else if (fila2 == 7 && columna2 == 7) {
            M2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05, m2f06}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15, m2f16}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25, m2f26}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35, m2f36}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45, m2f46}, {m2f50, m2f51, m2f52, m2f53, m2f54, m2f55, m2f56}, {m2f60, m2f61, m2f62, m2f63, m2f64, m2f65, m2f66}};
        }
        //endregion

        arrayListDatos.clear();
        tvSumaMatrizResultado.setText(sdirecta.productMatrix(M1, M2));

        int filaM1=0,columnaM1=0;
        int filaM2=0,columnaM2=0;

        filaM1 = M1.length;
        filaM2 = M2.length;

        for (int i=0;i<M1.length;i++){
            columnaM1 = M1[i].length;
        }
        for (int ii=0;ii<M2.length;ii++){
            columnaM2 = M2[ii].length;
        }

        arrayListDatos.add(4.0);
        arrayListDatos.add(filaM1*1.0);
        arrayListDatos.add(columnaM1*1.0);

        arrayListDatos.add(filaM2*1.0);
        arrayListDatos.add(columnaM2*1.0);

        for (int i=0;i<M1.length;i++){
            for (int j=0;j<M1[i].length;j++){
                arrayListDatos.add(M1[i][j]*1.0);
            }
        }
        for (int x=0;x<M2.length;x++){
            for (int y=0;y<M2[x].length;y++){
                arrayListDatos.add(M2[x][y]*1.0);
            }
        }

    }

    public void calcMatriz(int n, int m) {
        double m1f00 = 0, m1f01 = 0, m1f02 = 0, m1f03 = 0, m1f04 = 0, m1f05 = 0, m1f06 = 0,
                m1f10 = 0, m1f11 = 0, m1f12 = 0, m1f13 = 0, m1f14 = 0, m1f15 = 0, m1f16 = 0,
                m1f20 = 0, m1f21 = 0, m1f22 = 0, m1f23 = 0, m1f24 = 0, m1f25 = 0, m1f26 = 0,
                m1f30 = 0, m1f31 = 0, m1f32 = 0, m1f33 = 0, m1f34 = 0, m1f35 = 0, m1f36 = 0,
                m1f40 = 0, m1f41 = 0, m1f42 = 0, m1f43 = 0, m1f44 = 0, m1f45 = 0, m1f46 = 0,
                m1f50 = 0, m1f51 = 0, m1f52 = 0, m1f53 = 0, m1f54 = 0, m1f55 = 0, m1f56 = 0,
                m1f60 = 0, m1f61 = 0, m1f62 = 0, m1f63 = 0, m1f64 = 0, m1f65 = 0, m1f66 = 0, m1esc = 0;

        double m2f00 = 0, m2f01 = 0, m2f02 = 0, m2f03 = 0, m2f04 = 0, m2f05 = 0, m2f06 = 0,
                m2f10 = 0, m2f11 = 0, m2f12 = 0, m2f13 = 0, m2f14 = 0, m2f15 = 0, m2f16 = 0,
                m2f20 = 0, m2f21 = 0, m2f22 = 0, m2f23 = 0, m2f24 = 0, m2f25 = 0, m2f26 = 0,
                m2f30 = 0, m2f31 = 0, m2f32 = 0, m2f33 = 0, m2f34 = 0, m2f35 = 0, m2f36 = 0,
                m2f40 = 0, m2f41 = 0, m2f42 = 0, m2f43 = 0, m2f44 = 0, m2f45 = 0, m2f46 = 0,
                m2f50 = 0, m2f51 = 0, m2f52 = 0, m2f53 = 0, m2f54 = 0, m2f55 = 0, m2f56 = 0,
                m2f60 = 0, m2f61 = 0, m2f62 = 0, m2f63 = 0, m2f64 = 0, m2f65 = 0, m2f66 = 0;

        if (etProductxEscalar.getVisibility() == View.VISIBLE) {
            m1esc = Double.parseDouble(etProductxEscalar.getText().toString());
        }
        if (m == 3 || m == 5) {
            //region Matriz 1
            m1f00 = Double.parseDouble(etMatriz1Campo00.getText().toString());
            m1f01 = Double.parseDouble(etMatriz1Campo01.getText().toString());
            m1f02 = Double.parseDouble(etMatriz1Campo02.getText().toString());

            m1f10 = Double.parseDouble(etMatriz1Campo10.getText().toString());
            m1f11 = Double.parseDouble(etMatriz1Campo11.getText().toString());
            m1f12 = Double.parseDouble(etMatriz1Campo12.getText().toString());

            m1f20 = Double.parseDouble(etMatriz1Campo20.getText().toString());
            m1f21 = Double.parseDouble(etMatriz1Campo21.getText().toString());
            m1f22 = Double.parseDouble(etMatriz1Campo22.getText().toString());
            //endregion
        } else {
            //region Matriz 1
            m1f00 = Double.parseDouble(etMatriz1Campo00.getText().toString());
            m1f01 = Double.parseDouble(etMatriz1Campo01.getText().toString());
            m1f02 = Double.parseDouble(etMatriz1Campo02.getText().toString());

            m1f10 = Double.parseDouble(etMatriz1Campo10.getText().toString());
            m1f11 = Double.parseDouble(etMatriz1Campo11.getText().toString());
            m1f12 = Double.parseDouble(etMatriz1Campo12.getText().toString());

            m1f20 = Double.parseDouble(etMatriz1Campo20.getText().toString());
            m1f21 = Double.parseDouble(etMatriz1Campo21.getText().toString());
            m1f22 = Double.parseDouble(etMatriz1Campo22.getText().toString());
            //endregion

            //region Matriz 2
            m2f00 = Double.parseDouble(etMatriz2Campo00.getText().toString());
            m2f01 = Double.parseDouble(etMatriz2Campo01.getText().toString());
            m2f02 = Double.parseDouble(etMatriz2Campo02.getText().toString());

            m2f10 = Double.parseDouble(etMatriz2Campo10.getText().toString());
            m2f11 = Double.parseDouble(etMatriz2Campo11.getText().toString());
            m2f12 = Double.parseDouble(etMatriz2Campo12.getText().toString());

            m2f20 = Double.parseDouble(etMatriz2Campo20.getText().toString());
            m2f21 = Double.parseDouble(etMatriz2Campo21.getText().toString());
            m2f22 = Double.parseDouble(etMatriz2Campo22.getText().toString());
            //endregion
        }

        if (n == 1) {
            if (m == 3 || m == 5) {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02}, {m1f10, m1f11, m1f12}, {m1f20, m1f21, m1f22}};
            } else {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02}, {m1f10, m1f11, m1f12}, {m1f20, m1f21, m1f22}};
                matriz2 = new double[][]{{m2f00, m2f01, m2f02}, {m2f10, m2f11, m2f12}, {m2f20, m2f21, m2f22}};
            }
        } else if (n == 2) {
            if (m == 3 || m == 5) {
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
            } else {
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());

                m2f03 = Double.parseDouble(etMatriz2Campo03.getText().toString());
                m2f13 = Double.parseDouble(etMatriz2Campo13.getText().toString());
                m2f23 = Double.parseDouble(etMatriz2Campo23.getText().toString());

                m2f30 = Double.parseDouble(etMatriz2Campo30.getText().toString());
                m2f31 = Double.parseDouble(etMatriz2Campo31.getText().toString());
                m2f32 = Double.parseDouble(etMatriz2Campo32.getText().toString());
                m2f33 = Double.parseDouble(etMatriz2Campo33.getText().toString());

            }

            if (m == 3 || m == 5) {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03}, {m1f10, m1f11, m1f12, m1f13}, {m1f20, m1f21, m1f22, m1f23}, {m1f30, m1f31, m1f32, m1f33}};
            } else {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03}, {m1f10, m1f11, m1f12, m1f13}, {m1f20, m1f21, m1f22, m1f23}, {m1f30, m1f31, m1f32, m1f33}};
                matriz2 = new double[][]{{m2f00, m2f01, m2f02, m2f03}, {m2f10, m2f11, m2f12, m2f13}, {m2f20, m2f21, m2f22, m2f23}, {m2f30, m2f31, m2f32, m2f33}};
            }
        } else if (n == 3) {
            if (m == 3 || m == 5) {
                //region Matriz1
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f04 = Double.parseDouble(etMatriz1Campo04.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f14 = Double.parseDouble(etMatriz1Campo14.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());
                m1f24 = Double.parseDouble(etMatriz1Campo24.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
                m1f34 = Double.parseDouble(etMatriz1Campo34.getText().toString());

                m1f40 = Double.parseDouble(etMatriz1Campo40.getText().toString());
                m1f41 = Double.parseDouble(etMatriz1Campo41.getText().toString());
                m1f42 = Double.parseDouble(etMatriz1Campo42.getText().toString());
                m1f43 = Double.parseDouble(etMatriz1Campo43.getText().toString());
                m1f44 = Double.parseDouble(etMatriz1Campo44.getText().toString());
                //endregion
            } else {
                //region Matriz1
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f04 = Double.parseDouble(etMatriz1Campo04.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f14 = Double.parseDouble(etMatriz1Campo14.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());
                m1f24 = Double.parseDouble(etMatriz1Campo24.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
                m1f34 = Double.parseDouble(etMatriz1Campo34.getText().toString());

                m1f40 = Double.parseDouble(etMatriz1Campo40.getText().toString());
                m1f41 = Double.parseDouble(etMatriz1Campo41.getText().toString());
                m1f42 = Double.parseDouble(etMatriz1Campo42.getText().toString());
                m1f43 = Double.parseDouble(etMatriz1Campo43.getText().toString());
                m1f44 = Double.parseDouble(etMatriz1Campo44.getText().toString());
                //endregion

                //region Matriz 2
                m2f03 = Double.parseDouble(etMatriz2Campo03.getText().toString());
                m2f04 = Double.parseDouble(etMatriz2Campo04.getText().toString());
                m2f13 = Double.parseDouble(etMatriz2Campo13.getText().toString());
                m2f14 = Double.parseDouble(etMatriz2Campo14.getText().toString());
                m2f23 = Double.parseDouble(etMatriz2Campo23.getText().toString());
                m2f24 = Double.parseDouble(etMatriz2Campo24.getText().toString());

                m2f30 = Double.parseDouble(etMatriz2Campo30.getText().toString());
                m2f31 = Double.parseDouble(etMatriz2Campo31.getText().toString());
                m2f32 = Double.parseDouble(etMatriz2Campo32.getText().toString());
                m2f33 = Double.parseDouble(etMatriz2Campo33.getText().toString());
                m2f34 = Double.parseDouble(etMatriz2Campo34.getText().toString());

                m2f40 = Double.parseDouble(etMatriz2Campo40.getText().toString());
                m2f41 = Double.parseDouble(etMatriz2Campo41.getText().toString());
                m2f42 = Double.parseDouble(etMatriz2Campo42.getText().toString());
                m2f43 = Double.parseDouble(etMatriz2Campo43.getText().toString());
                m2f44 = Double.parseDouble(etMatriz2Campo44.getText().toString());
                //endregion
            }
            if (m == 3 || m == 5) {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04}, {m1f10, m1f11, m1f12, m1f13, m1f14}, {m1f20, m1f21, m1f22, m1f23, m1f24}, {m1f30, m1f31, m1f32, m1f33, m1f34}, {m1f40, m1f41, m1f42, m1f43, m1f44}};
            } else {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04}, {m1f10, m1f11, m1f12, m1f13, m1f14}, {m1f20, m1f21, m1f22, m1f23, m1f24}, {m1f30, m1f31, m1f32, m1f33, m1f34}, {m1f40, m1f41, m1f42, m1f43, m1f44}};
                matriz2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04}, {m2f10, m2f11, m2f12, m2f13, m2f14}, {m2f20, m2f21, m2f22, m2f23, m2f24}, {m2f30, m2f31, m2f32, m2f33, m2f34}, {m2f40, m2f41, m2f42, m2f43, m2f44}};
            }

        } else if (n == 4) {
            if (m == 3 || m == 5) {
                //region Matriz 1
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f04 = Double.parseDouble(etMatriz1Campo04.getText().toString());
                m1f05 = Double.parseDouble(etMatriz1Campo05.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f14 = Double.parseDouble(etMatriz1Campo14.getText().toString());
                m1f15 = Double.parseDouble(etMatriz1Campo15.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());
                m1f24 = Double.parseDouble(etMatriz1Campo24.getText().toString());
                m1f25 = Double.parseDouble(etMatriz1Campo25.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
                m1f34 = Double.parseDouble(etMatriz1Campo34.getText().toString());
                m1f35 = Double.parseDouble(etMatriz1Campo35.getText().toString());

                m1f40 = Double.parseDouble(etMatriz1Campo40.getText().toString());
                m1f41 = Double.parseDouble(etMatriz1Campo41.getText().toString());
                m1f42 = Double.parseDouble(etMatriz1Campo42.getText().toString());
                m1f43 = Double.parseDouble(etMatriz1Campo43.getText().toString());
                m1f44 = Double.parseDouble(etMatriz1Campo44.getText().toString());
                m1f45 = Double.parseDouble(etMatriz1Campo45.getText().toString());

                m1f50 = Double.parseDouble(etMatriz1Campo50.getText().toString());
                m1f51 = Double.parseDouble(etMatriz1Campo51.getText().toString());
                m1f52 = Double.parseDouble(etMatriz1Campo52.getText().toString());
                m1f53 = Double.parseDouble(etMatriz1Campo53.getText().toString());
                m1f54 = Double.parseDouble(etMatriz1Campo54.getText().toString());
                m1f55 = Double.parseDouble(etMatriz1Campo55.getText().toString());
                //endregion
            } else {
                //region Matriz 1
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f04 = Double.parseDouble(etMatriz1Campo04.getText().toString());
                m1f05 = Double.parseDouble(etMatriz1Campo05.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f14 = Double.parseDouble(etMatriz1Campo14.getText().toString());
                m1f15 = Double.parseDouble(etMatriz1Campo15.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());
                m1f24 = Double.parseDouble(etMatriz1Campo24.getText().toString());
                m1f25 = Double.parseDouble(etMatriz1Campo25.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
                m1f34 = Double.parseDouble(etMatriz1Campo34.getText().toString());
                m1f35 = Double.parseDouble(etMatriz1Campo35.getText().toString());

                m1f40 = Double.parseDouble(etMatriz1Campo40.getText().toString());
                m1f41 = Double.parseDouble(etMatriz1Campo41.getText().toString());
                m1f42 = Double.parseDouble(etMatriz1Campo42.getText().toString());
                m1f43 = Double.parseDouble(etMatriz1Campo43.getText().toString());
                m1f44 = Double.parseDouble(etMatriz1Campo44.getText().toString());
                m1f45 = Double.parseDouble(etMatriz1Campo45.getText().toString());

                m1f50 = Double.parseDouble(etMatriz1Campo50.getText().toString());
                m1f51 = Double.parseDouble(etMatriz1Campo51.getText().toString());
                m1f52 = Double.parseDouble(etMatriz1Campo52.getText().toString());
                m1f53 = Double.parseDouble(etMatriz1Campo53.getText().toString());
                m1f54 = Double.parseDouble(etMatriz1Campo54.getText().toString());
                m1f55 = Double.parseDouble(etMatriz1Campo55.getText().toString());
                //endregion

                //region Matriz 2
                m2f03 = Double.parseDouble(etMatriz2Campo03.getText().toString());
                m2f04 = Double.parseDouble(etMatriz2Campo04.getText().toString());
                m2f05 = Double.parseDouble(etMatriz2Campo05.getText().toString());
                m2f13 = Double.parseDouble(etMatriz2Campo13.getText().toString());
                m2f14 = Double.parseDouble(etMatriz2Campo14.getText().toString());
                m2f15 = Double.parseDouble(etMatriz2Campo15.getText().toString());
                m2f23 = Double.parseDouble(etMatriz2Campo23.getText().toString());
                m2f24 = Double.parseDouble(etMatriz2Campo24.getText().toString());
                m2f25 = Double.parseDouble(etMatriz2Campo25.getText().toString());

                m2f30 = Double.parseDouble(etMatriz2Campo30.getText().toString());
                m2f31 = Double.parseDouble(etMatriz2Campo31.getText().toString());
                m2f32 = Double.parseDouble(etMatriz2Campo32.getText().toString());
                m2f33 = Double.parseDouble(etMatriz2Campo33.getText().toString());
                m2f34 = Double.parseDouble(etMatriz2Campo34.getText().toString());
                m2f35 = Double.parseDouble(etMatriz2Campo35.getText().toString());

                m2f40 = Double.parseDouble(etMatriz2Campo40.getText().toString());
                m2f41 = Double.parseDouble(etMatriz2Campo41.getText().toString());
                m2f42 = Double.parseDouble(etMatriz2Campo42.getText().toString());
                m2f43 = Double.parseDouble(etMatriz2Campo43.getText().toString());
                m2f44 = Double.parseDouble(etMatriz2Campo44.getText().toString());
                m2f45 = Double.parseDouble(etMatriz2Campo45.getText().toString());

                m2f50 = Double.parseDouble(etMatriz2Campo50.getText().toString());
                m2f51 = Double.parseDouble(etMatriz2Campo51.getText().toString());
                m2f52 = Double.parseDouble(etMatriz2Campo52.getText().toString());
                m2f53 = Double.parseDouble(etMatriz2Campo53.getText().toString());
                m2f54 = Double.parseDouble(etMatriz2Campo54.getText().toString());
                m2f55 = Double.parseDouble(etMatriz2Campo55.getText().toString());
                //endregion
            }
            if (m == 3 || m == 5) {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55}};
            } else {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55}};
                matriz2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45}, {m2f50, m2f51, m2f52, m2f53, m2f54, m2f55}};
            }

        } else if (n == 5) {
            if (m == 3 || m == 5) {
                //region Matriz 1
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f04 = Double.parseDouble(etMatriz1Campo04.getText().toString());
                m1f05 = Double.parseDouble(etMatriz1Campo05.getText().toString());
                m1f06 = Double.parseDouble(etMatriz1Campo06.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f14 = Double.parseDouble(etMatriz1Campo14.getText().toString());
                m1f15 = Double.parseDouble(etMatriz1Campo15.getText().toString());
                m1f16 = Double.parseDouble(etMatriz1Campo16.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());
                m1f24 = Double.parseDouble(etMatriz1Campo24.getText().toString());
                m1f25 = Double.parseDouble(etMatriz1Campo25.getText().toString());
                m1f26 = Double.parseDouble(etMatriz1Campo26.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
                m1f34 = Double.parseDouble(etMatriz1Campo34.getText().toString());
                m1f35 = Double.parseDouble(etMatriz1Campo35.getText().toString());
                m1f36 = Double.parseDouble(etMatriz1Campo36.getText().toString());

                m1f40 = Double.parseDouble(etMatriz1Campo40.getText().toString());
                m1f41 = Double.parseDouble(etMatriz1Campo41.getText().toString());
                m1f42 = Double.parseDouble(etMatriz1Campo42.getText().toString());
                m1f43 = Double.parseDouble(etMatriz1Campo43.getText().toString());
                m1f44 = Double.parseDouble(etMatriz1Campo44.getText().toString());
                m1f45 = Double.parseDouble(etMatriz1Campo45.getText().toString());
                m1f46 = Double.parseDouble(etMatriz1Campo46.getText().toString());

                m1f50 = Double.parseDouble(etMatriz1Campo50.getText().toString());
                m1f51 = Double.parseDouble(etMatriz1Campo51.getText().toString());
                m1f52 = Double.parseDouble(etMatriz1Campo52.getText().toString());
                m1f53 = Double.parseDouble(etMatriz1Campo53.getText().toString());
                m1f54 = Double.parseDouble(etMatriz1Campo54.getText().toString());
                m1f55 = Double.parseDouble(etMatriz1Campo55.getText().toString());
                m1f56 = Double.parseDouble(etMatriz1Campo56.getText().toString());

                m1f60 = Double.parseDouble(etMatriz1Campo60.getText().toString());
                m1f61 = Double.parseDouble(etMatriz1Campo61.getText().toString());
                m1f62 = Double.parseDouble(etMatriz1Campo62.getText().toString());
                m1f63 = Double.parseDouble(etMatriz1Campo63.getText().toString());
                m1f64 = Double.parseDouble(etMatriz1Campo64.getText().toString());
                m1f65 = Double.parseDouble(etMatriz1Campo65.getText().toString());
                m1f66 = Double.parseDouble(etMatriz1Campo66.getText().toString());
                //endregion
            } else {
                //region Matriz 1
                m1f03 = Double.parseDouble(etMatriz1Campo03.getText().toString());
                m1f04 = Double.parseDouble(etMatriz1Campo04.getText().toString());
                m1f05 = Double.parseDouble(etMatriz1Campo05.getText().toString());
                m1f06 = Double.parseDouble(etMatriz1Campo06.getText().toString());
                m1f13 = Double.parseDouble(etMatriz1Campo13.getText().toString());
                m1f14 = Double.parseDouble(etMatriz1Campo14.getText().toString());
                m1f15 = Double.parseDouble(etMatriz1Campo15.getText().toString());
                m1f16 = Double.parseDouble(etMatriz1Campo16.getText().toString());
                m1f23 = Double.parseDouble(etMatriz1Campo23.getText().toString());
                m1f24 = Double.parseDouble(etMatriz1Campo24.getText().toString());
                m1f25 = Double.parseDouble(etMatriz1Campo25.getText().toString());
                m1f26 = Double.parseDouble(etMatriz1Campo26.getText().toString());

                m1f30 = Double.parseDouble(etMatriz1Campo30.getText().toString());
                m1f31 = Double.parseDouble(etMatriz1Campo31.getText().toString());
                m1f32 = Double.parseDouble(etMatriz1Campo32.getText().toString());
                m1f33 = Double.parseDouble(etMatriz1Campo33.getText().toString());
                m1f34 = Double.parseDouble(etMatriz1Campo34.getText().toString());
                m1f35 = Double.parseDouble(etMatriz1Campo35.getText().toString());
                m1f36 = Double.parseDouble(etMatriz1Campo36.getText().toString());

                m1f40 = Double.parseDouble(etMatriz1Campo40.getText().toString());
                m1f41 = Double.parseDouble(etMatriz1Campo41.getText().toString());
                m1f42 = Double.parseDouble(etMatriz1Campo42.getText().toString());
                m1f43 = Double.parseDouble(etMatriz1Campo43.getText().toString());
                m1f44 = Double.parseDouble(etMatriz1Campo44.getText().toString());
                m1f45 = Double.parseDouble(etMatriz1Campo45.getText().toString());
                m1f46 = Double.parseDouble(etMatriz1Campo46.getText().toString());

                m1f50 = Double.parseDouble(etMatriz1Campo50.getText().toString());
                m1f51 = Double.parseDouble(etMatriz1Campo51.getText().toString());
                m1f52 = Double.parseDouble(etMatriz1Campo52.getText().toString());
                m1f53 = Double.parseDouble(etMatriz1Campo53.getText().toString());
                m1f54 = Double.parseDouble(etMatriz1Campo54.getText().toString());
                m1f55 = Double.parseDouble(etMatriz1Campo55.getText().toString());
                m1f56 = Double.parseDouble(etMatriz1Campo56.getText().toString());

                m1f60 = Double.parseDouble(etMatriz1Campo60.getText().toString());
                m1f61 = Double.parseDouble(etMatriz1Campo61.getText().toString());
                m1f62 = Double.parseDouble(etMatriz1Campo62.getText().toString());
                m1f63 = Double.parseDouble(etMatriz1Campo63.getText().toString());
                m1f64 = Double.parseDouble(etMatriz1Campo64.getText().toString());
                m1f65 = Double.parseDouble(etMatriz1Campo65.getText().toString());
                m1f66 = Double.parseDouble(etMatriz1Campo66.getText().toString());
                //endregion

                //region Matriz 2
                m2f03 = Double.parseDouble(etMatriz2Campo03.getText().toString());
                m2f04 = Double.parseDouble(etMatriz2Campo04.getText().toString());
                m2f05 = Double.parseDouble(etMatriz2Campo05.getText().toString());
                m2f06 = Double.parseDouble(etMatriz2Campo06.getText().toString());
                m2f13 = Double.parseDouble(etMatriz2Campo13.getText().toString());
                m2f14 = Double.parseDouble(etMatriz2Campo14.getText().toString());
                m2f15 = Double.parseDouble(etMatriz2Campo15.getText().toString());
                m2f16 = Double.parseDouble(etMatriz2Campo16.getText().toString());
                m2f23 = Double.parseDouble(etMatriz2Campo23.getText().toString());
                m2f24 = Double.parseDouble(etMatriz2Campo24.getText().toString());
                m2f25 = Double.parseDouble(etMatriz2Campo25.getText().toString());
                m2f26 = Double.parseDouble(etMatriz2Campo26.getText().toString());

                m2f30 = Double.parseDouble(etMatriz2Campo30.getText().toString());
                m2f31 = Double.parseDouble(etMatriz2Campo31.getText().toString());
                m2f32 = Double.parseDouble(etMatriz2Campo32.getText().toString());
                m2f33 = Double.parseDouble(etMatriz2Campo33.getText().toString());
                m2f34 = Double.parseDouble(etMatriz2Campo34.getText().toString());
                m2f35 = Double.parseDouble(etMatriz2Campo35.getText().toString());
                m2f36 = Double.parseDouble(etMatriz2Campo36.getText().toString());

                m2f40 = Double.parseDouble(etMatriz2Campo40.getText().toString());
                m2f41 = Double.parseDouble(etMatriz2Campo41.getText().toString());
                m2f42 = Double.parseDouble(etMatriz2Campo42.getText().toString());
                m2f43 = Double.parseDouble(etMatriz2Campo43.getText().toString());
                m2f44 = Double.parseDouble(etMatriz2Campo44.getText().toString());
                m2f45 = Double.parseDouble(etMatriz2Campo45.getText().toString());
                m2f46 = Double.parseDouble(etMatriz2Campo46.getText().toString());

                m2f50 = Double.parseDouble(etMatriz2Campo50.getText().toString());
                m2f51 = Double.parseDouble(etMatriz2Campo51.getText().toString());
                m2f52 = Double.parseDouble(etMatriz2Campo52.getText().toString());
                m2f53 = Double.parseDouble(etMatriz2Campo53.getText().toString());
                m2f54 = Double.parseDouble(etMatriz2Campo54.getText().toString());
                m2f55 = Double.parseDouble(etMatriz2Campo55.getText().toString());
                m2f56 = Double.parseDouble(etMatriz2Campo56.getText().toString());

                m2f60 = Double.parseDouble(etMatriz2Campo60.getText().toString());
                m2f61 = Double.parseDouble(etMatriz2Campo61.getText().toString());
                m2f62 = Double.parseDouble(etMatriz2Campo62.getText().toString());
                m2f63 = Double.parseDouble(etMatriz2Campo63.getText().toString());
                m2f64 = Double.parseDouble(etMatriz2Campo64.getText().toString());
                m2f65 = Double.parseDouble(etMatriz2Campo65.getText().toString());
                m2f66 = Double.parseDouble(etMatriz2Campo66.getText().toString());
                //endregion
            }

            if (m == 3 || m == 5) {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05, m1f06}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15, m1f16}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25, m1f26}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35, m1f36}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45, m1f46}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55, m1f56}, {m1f60, m1f61, m1f62, m1f63, m1f64, m1f65, m1f66}};
            } else {
                matriz1 = new double[][]{{m1f00, m1f01, m1f02, m1f03, m1f04, m1f05, m1f06}, {m1f10, m1f11, m1f12, m1f13, m1f14, m1f15, m1f16}, {m1f20, m1f21, m1f22, m1f23, m1f24, m1f25, m1f26}, {m1f30, m1f31, m1f32, m1f33, m1f34, m1f35, m1f36}, {m1f40, m1f41, m1f42, m1f43, m1f44, m1f45, m1f46}, {m1f50, m1f51, m1f52, m1f53, m1f54, m1f55, m1f56}, {m1f60, m1f61, m1f62, m1f63, m1f64, m1f65, m1f66}};
                matriz2 = new double[][]{{m2f00, m2f01, m2f02, m2f03, m2f04, m2f05, m2f06}, {m2f10, m2f11, m2f12, m2f13, m2f14, m2f15, m2f16}, {m2f20, m2f21, m2f22, m2f23, m2f24, m2f25, m2f26}, {m2f30, m2f31, m2f32, m2f33, m2f34, m2f35, m2f36}, {m2f40, m2f41, m2f42, m2f43, m2f44, m2f45, m2f46}, {m2f50, m2f51, m2f52, m2f53, m2f54, m2f55, m2f56}, {m2f60, m2f61, m2f62, m2f63, m2f64, m2f65, m2f66}};
            }
        }
        DecimalFormat formato = new DecimalFormat("#.##");
        if (m == 1) {
            arrayListDatos.clear();
            tvSumaMatrizResultado.setText(sdirecta.sumaMatrix(matriz1, matriz2));

            int fila=0,columna=0;
            fila = matriz1.length;
            for (int ii=0;ii<matriz1.length;ii++){
                columna = matriz1[ii].length;
            }

            arrayListDatos.add(1.0);
            arrayListDatos.add(fila*1.0);
            arrayListDatos.add(columna*1.0);

            for (int i=0;i<matriz1.length;i++){
                for (int j=0;j<matriz1[i].length;j++){
                    arrayListDatos.add(matriz1[i][j]*1.0);
                }
            }
            for (int x=0;x<matriz2.length;x++){
                for (int y=0;y<matriz2[x].length;y++){
                    arrayListDatos.add(matriz2[x][y]*1.0);
                }
            }

        } else if (m == 2) {
            arrayListDatos.clear();
            tvSumaMatrizResultado.setText(sdirecta.restaMatrix(matriz1, matriz2));

            int fila=0,columna=0;
            fila = matriz1.length;
            for (int i=0;i<matriz1.length;i++){
                columna = matriz1[i].length;
            }

            arrayListDatos.add(2.0);
            arrayListDatos.add(fila*1.0);
            arrayListDatos.add(columna*1.0);

            for (int i=0;i<matriz1.length;i++){
                for (int j=0;j<matriz1[i].length;j++){
                    arrayListDatos.add(matriz1[i][j]*1.0);
                }
            }
            for (int x=0;x<matriz2.length;x++){
                for (int y=0;y<matriz2[x].length;y++){
                    arrayListDatos.add(matriz2[x][y]*1.0);
                }
            }

        } else if (m == 3) {
            arrayListDatos.clear();
            tvSumaMatrizResultado.setText(sdirecta.determinante(matriz1));

            int fila=0,columna=0;
            fila = matriz1.length;
            for (int i=0;i<matriz1.length;i++){
                columna = matriz1[i].length;
            }

            arrayListDatos.add(3.0);
            arrayListDatos.add(fila*1.0);
            arrayListDatos.add(columna*1.0);

            for (int i=0;i<matriz1.length;i++){
                for (int j=0;j<matriz1[i].length;j++){
                    arrayListDatos.add(matriz1[i][j]*1.0);
                }
            }

        } else if (m == 5) {
            arrayListDatos.clear();
            tvSumaMatrizResultado.setText(sdirecta.productoMEscalar(matriz1, m1esc));

            int fila=0,columna=0;
            fila = matriz1.length;
            for (int i=0;i<matriz1.length;i++){
                columna = matriz1[i].length;
            }

            arrayListDatos.add(5.0);
            arrayListDatos.add(fila*1.0);
            arrayListDatos.add(columna*1.0);
            arrayListDatos.add(m1esc*1.0);

            for (int i=0;i<matriz1.length;i++){
                for (int j=0;j<matriz1[i].length;j++){
                    arrayListDatos.add(matriz1[i][j]*1.0);
                }
            }
        }
    }

    private Boolean validarMatrizSuma3x3(int m) {
        boolean _result = true;
        String _error = "Campo vacío";
        if (m == 3 || m == 5) {
            //region Matriz 1
            if (etProductxEscalar.getVisibility() == View.VISIBLE) {
                if (etProductxEscalar.getText().toString().isEmpty()) {
                    etProductxEscalar.setError(_error);
                    _result = false;
                }
            }
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            //endregion
        } else {
            //region Matriz 1
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            //endregion

            //region Matriz 2
            if (etMatriz2Campo00.getText().toString().isEmpty()) {
                etMatriz2Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo01.getText().toString().isEmpty()) {
                etMatriz2Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo02.getText().toString().isEmpty()) {
                etMatriz2Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo10.getText().toString().isEmpty()) {
                etMatriz2Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo11.getText().toString().isEmpty()) {
                etMatriz2Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo12.getText().toString().isEmpty()) {
                etMatriz2Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo20.getText().toString().isEmpty()) {
                etMatriz2Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo21.getText().toString().isEmpty()) {
                etMatriz2Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo22.getText().toString().isEmpty()) {
                etMatriz2Campo22.setError(_error);
                _result = false;
            }
            //endregion
        }

        return _result;
    }

    private Boolean validarMatrizSuma4x4(int m) {
        boolean _result = true;
        String _error = "Campo vacío";
        if (m == 3 || m == 5) {
            //region Matriz 1
            if (etProductxEscalar.getVisibility() == View.VISIBLE) {
                if (etProductxEscalar.getText().toString().isEmpty()) {
                    etProductxEscalar.setError(_error);
                    _result = false;
                }
            }
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            //endregion
        } else {
            //region Matriz 1
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            //endregion

            //region Matriz 2
            if (etMatriz2Campo00.getText().toString().isEmpty()) {
                etMatriz2Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo01.getText().toString().isEmpty()) {
                etMatriz2Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo02.getText().toString().isEmpty()) {
                etMatriz2Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo03.getText().toString().isEmpty()) {
                etMatriz2Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo10.getText().toString().isEmpty()) {
                etMatriz2Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo11.getText().toString().isEmpty()) {
                etMatriz2Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo12.getText().toString().isEmpty()) {
                etMatriz2Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo13.getText().toString().isEmpty()) {
                etMatriz2Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo20.getText().toString().isEmpty()) {
                etMatriz2Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo21.getText().toString().isEmpty()) {
                etMatriz2Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo22.getText().toString().isEmpty()) {
                etMatriz2Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo23.getText().toString().isEmpty()) {
                etMatriz2Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo30.getText().toString().isEmpty()) {
                etMatriz2Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo31.getText().toString().isEmpty()) {
                etMatriz2Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo32.getText().toString().isEmpty()) {
                etMatriz2Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo33.getText().toString().isEmpty()) {
                etMatriz2Campo33.setError(_error);
                _result = false;
            }
            //endregion
        }

        return _result;
    }

    private Boolean validarMatrizSuma5x5(int m) {
        boolean _result = true;
        String _error = "Campo vacío";
        if (m == 3 || m == 5) {
            //region Matriz 1
            if (etProductxEscalar.getVisibility() == View.VISIBLE) {
                if (etProductxEscalar.getText().toString().isEmpty()) {
                    etProductxEscalar.setError(_error);
                    _result = false;
                }
            }
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(_error);
                _result = false;
            }
            //endregion
        } else {
            //region Matriz 1
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(_error);
                _result = false;
            }
            //endregion

            //region Matriz 2
            if (etMatriz2Campo00.getText().toString().isEmpty()) {
                etMatriz2Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo01.getText().toString().isEmpty()) {
                etMatriz2Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo02.getText().toString().isEmpty()) {
                etMatriz2Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo03.getText().toString().isEmpty()) {
                etMatriz2Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo04.getText().toString().isEmpty()) {
                etMatriz2Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo10.getText().toString().isEmpty()) {
                etMatriz2Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo11.getText().toString().isEmpty()) {
                etMatriz2Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo12.getText().toString().isEmpty()) {
                etMatriz2Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo13.getText().toString().isEmpty()) {
                etMatriz2Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo14.getText().toString().isEmpty()) {
                etMatriz2Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo20.getText().toString().isEmpty()) {
                etMatriz2Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo21.getText().toString().isEmpty()) {
                etMatriz2Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo22.getText().toString().isEmpty()) {
                etMatriz2Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo23.getText().toString().isEmpty()) {
                etMatriz2Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo24.getText().toString().isEmpty()) {
                etMatriz2Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo30.getText().toString().isEmpty()) {
                etMatriz2Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo31.getText().toString().isEmpty()) {
                etMatriz2Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo32.getText().toString().isEmpty()) {
                etMatriz2Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo33.getText().toString().isEmpty()) {
                etMatriz2Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo34.getText().toString().isEmpty()) {
                etMatriz2Campo34.setError(_error);
                _result = false;
            }

            if (etMatriz2Campo40.getText().toString().isEmpty()) {
                etMatriz2Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo41.getText().toString().isEmpty()) {
                etMatriz2Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo42.getText().toString().isEmpty()) {
                etMatriz2Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo43.getText().toString().isEmpty()) {
                etMatriz2Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo44.getText().toString().isEmpty()) {
                etMatriz2Campo44.setError(_error);
                _result = false;
            }
            //endregion
        }

        return _result;
    }

    private Boolean validarMatrizSuma6x6(int m) {
        boolean _result = true;
        String _error = "Campo vacío";
        if (m == 3 || m == 5) {
            //region Matriz 1
            if (etProductxEscalar.getVisibility() == View.VISIBLE) {
                if (etProductxEscalar.getText().toString().isEmpty()) {
                    etProductxEscalar.setError(_error);
                    _result = false;
                }
            }
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo05.getText().toString().isEmpty()) {
                etMatriz1Campo05.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo15.getText().toString().isEmpty()) {
                etMatriz1Campo15.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo25.getText().toString().isEmpty()) {
                etMatriz1Campo25.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo35.getText().toString().isEmpty()) {
                etMatriz1Campo35.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo45.getText().toString().isEmpty()) {
                etMatriz1Campo45.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo50.getText().toString().isEmpty()) {
                etMatriz1Campo50.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo51.getText().toString().isEmpty()) {
                etMatriz1Campo51.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo52.getText().toString().isEmpty()) {
                etMatriz1Campo52.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo53.getText().toString().isEmpty()) {
                etMatriz1Campo53.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo54.getText().toString().isEmpty()) {
                etMatriz1Campo54.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo55.getText().toString().isEmpty()) {
                etMatriz1Campo55.setError(_error);
                _result = false;
            }
            //endregion
        } else {
            //region Matriz 1
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo05.getText().toString().isEmpty()) {
                etMatriz1Campo05.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo15.getText().toString().isEmpty()) {
                etMatriz1Campo15.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo25.getText().toString().isEmpty()) {
                etMatriz1Campo25.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo35.getText().toString().isEmpty()) {
                etMatriz1Campo35.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo45.getText().toString().isEmpty()) {
                etMatriz1Campo45.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo50.getText().toString().isEmpty()) {
                etMatriz1Campo50.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo51.getText().toString().isEmpty()) {
                etMatriz1Campo51.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo52.getText().toString().isEmpty()) {
                etMatriz1Campo52.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo53.getText().toString().isEmpty()) {
                etMatriz1Campo53.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo54.getText().toString().isEmpty()) {
                etMatriz1Campo54.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo55.getText().toString().isEmpty()) {
                etMatriz1Campo55.setError(_error);
                _result = false;
            }
            //endregion

            //region Matriz 2
            if (etMatriz2Campo00.getText().toString().isEmpty()) {
                etMatriz2Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo01.getText().toString().isEmpty()) {
                etMatriz2Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo02.getText().toString().isEmpty()) {
                etMatriz2Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo03.getText().toString().isEmpty()) {
                etMatriz2Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo04.getText().toString().isEmpty()) {
                etMatriz2Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo05.getText().toString().isEmpty()) {
                etMatriz2Campo05.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo10.getText().toString().isEmpty()) {
                etMatriz2Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo11.getText().toString().isEmpty()) {
                etMatriz2Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo12.getText().toString().isEmpty()) {
                etMatriz2Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo13.getText().toString().isEmpty()) {
                etMatriz2Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo14.getText().toString().isEmpty()) {
                etMatriz2Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo15.getText().toString().isEmpty()) {
                etMatriz2Campo15.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo20.getText().toString().isEmpty()) {
                etMatriz2Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo21.getText().toString().isEmpty()) {
                etMatriz2Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo22.getText().toString().isEmpty()) {
                etMatriz2Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo23.getText().toString().isEmpty()) {
                etMatriz2Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo24.getText().toString().isEmpty()) {
                etMatriz2Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo25.getText().toString().isEmpty()) {
                etMatriz2Campo25.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo30.getText().toString().isEmpty()) {
                etMatriz2Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo31.getText().toString().isEmpty()) {
                etMatriz2Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo32.getText().toString().isEmpty()) {
                etMatriz2Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo33.getText().toString().isEmpty()) {
                etMatriz2Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo34.getText().toString().isEmpty()) {
                etMatriz2Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo35.getText().toString().isEmpty()) {
                etMatriz2Campo35.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo40.getText().toString().isEmpty()) {
                etMatriz2Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo41.getText().toString().isEmpty()) {
                etMatriz2Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo42.getText().toString().isEmpty()) {
                etMatriz2Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo43.getText().toString().isEmpty()) {
                etMatriz2Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo44.getText().toString().isEmpty()) {
                etMatriz2Campo44.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo45.getText().toString().isEmpty()) {
                etMatriz2Campo45.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo50.getText().toString().isEmpty()) {
                etMatriz2Campo50.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo51.getText().toString().isEmpty()) {
                etMatriz2Campo51.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo52.getText().toString().isEmpty()) {
                etMatriz2Campo52.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo53.getText().toString().isEmpty()) {
                etMatriz2Campo53.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo54.getText().toString().isEmpty()) {
                etMatriz2Campo54.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo55.getText().toString().isEmpty()) {
                etMatriz2Campo55.setError(_error);
                _result = false;
            }
            //endregion
        }

        return _result;
    }

    private Boolean validarMatrizSuma7x7(int m) {
        boolean _result = true;
        String _error = "Campo vacío";
        if (m == 3 || m == 5) {
            //region Matriz 1
            if (etProductxEscalar.getVisibility() == View.VISIBLE) {
                if (etProductxEscalar.getText().toString().isEmpty()) {
                    etProductxEscalar.setError(_error);
                    _result = false;
                }
            }
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo05.getText().toString().isEmpty()) {
                etMatriz1Campo05.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo06.getText().toString().isEmpty()) {
                etMatriz1Campo06.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo15.getText().toString().isEmpty()) {
                etMatriz1Campo15.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo16.getText().toString().isEmpty()) {
                etMatriz1Campo16.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo25.getText().toString().isEmpty()) {
                etMatriz1Campo25.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo26.getText().toString().isEmpty()) {
                etMatriz1Campo26.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo35.getText().toString().isEmpty()) {
                etMatriz1Campo35.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo36.getText().toString().isEmpty()) {
                etMatriz1Campo36.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo45.getText().toString().isEmpty()) {
                etMatriz1Campo45.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo46.getText().toString().isEmpty()) {
                etMatriz1Campo46.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo50.getText().toString().isEmpty()) {
                etMatriz1Campo50.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo51.getText().toString().isEmpty()) {
                etMatriz1Campo51.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo52.getText().toString().isEmpty()) {
                etMatriz1Campo52.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo53.getText().toString().isEmpty()) {
                etMatriz1Campo53.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo54.getText().toString().isEmpty()) {
                etMatriz1Campo54.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo55.getText().toString().isEmpty()) {
                etMatriz1Campo55.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo56.getText().toString().isEmpty()) {
                etMatriz1Campo56.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo60.getText().toString().isEmpty()) {
                etMatriz1Campo60.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo61.getText().toString().isEmpty()) {
                etMatriz1Campo61.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo62.getText().toString().isEmpty()) {
                etMatriz1Campo62.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo63.getText().toString().isEmpty()) {
                etMatriz1Campo63.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo64.getText().toString().isEmpty()) {
                etMatriz1Campo64.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo65.getText().toString().isEmpty()) {
                etMatriz1Campo65.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo66.getText().toString().isEmpty()) {
                etMatriz1Campo66.setError(_error);
                _result = false;
            }
            //endregion
        } else {
            //region Matriz 1
            if (etMatriz1Campo00.getText().toString().isEmpty()) {
                etMatriz1Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo01.getText().toString().isEmpty()) {
                etMatriz1Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo02.getText().toString().isEmpty()) {
                etMatriz1Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo03.getText().toString().isEmpty()) {
                etMatriz1Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo04.getText().toString().isEmpty()) {
                etMatriz1Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo05.getText().toString().isEmpty()) {
                etMatriz1Campo05.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo06.getText().toString().isEmpty()) {
                etMatriz1Campo06.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo10.getText().toString().isEmpty()) {
                etMatriz1Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo11.getText().toString().isEmpty()) {
                etMatriz1Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo12.getText().toString().isEmpty()) {
                etMatriz1Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo13.getText().toString().isEmpty()) {
                etMatriz1Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo14.getText().toString().isEmpty()) {
                etMatriz1Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo15.getText().toString().isEmpty()) {
                etMatriz1Campo15.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo16.getText().toString().isEmpty()) {
                etMatriz1Campo16.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo20.getText().toString().isEmpty()) {
                etMatriz1Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo21.getText().toString().isEmpty()) {
                etMatriz1Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo22.getText().toString().isEmpty()) {
                etMatriz1Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo23.getText().toString().isEmpty()) {
                etMatriz1Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo24.getText().toString().isEmpty()) {
                etMatriz1Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo25.getText().toString().isEmpty()) {
                etMatriz1Campo25.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo26.getText().toString().isEmpty()) {
                etMatriz1Campo26.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo30.getText().toString().isEmpty()) {
                etMatriz1Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo31.getText().toString().isEmpty()) {
                etMatriz1Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo32.getText().toString().isEmpty()) {
                etMatriz1Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo33.getText().toString().isEmpty()) {
                etMatriz1Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo34.getText().toString().isEmpty()) {
                etMatriz1Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo35.getText().toString().isEmpty()) {
                etMatriz1Campo35.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo36.getText().toString().isEmpty()) {
                etMatriz1Campo36.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo40.getText().toString().isEmpty()) {
                etMatriz1Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo41.getText().toString().isEmpty()) {
                etMatriz1Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo42.getText().toString().isEmpty()) {
                etMatriz1Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo43.getText().toString().isEmpty()) {
                etMatriz1Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo44.getText().toString().isEmpty()) {
                etMatriz1Campo44.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo45.getText().toString().isEmpty()) {
                etMatriz1Campo45.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo46.getText().toString().isEmpty()) {
                etMatriz1Campo46.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo50.getText().toString().isEmpty()) {
                etMatriz1Campo50.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo51.getText().toString().isEmpty()) {
                etMatriz1Campo51.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo52.getText().toString().isEmpty()) {
                etMatriz1Campo52.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo53.getText().toString().isEmpty()) {
                etMatriz1Campo53.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo54.getText().toString().isEmpty()) {
                etMatriz1Campo54.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo55.getText().toString().isEmpty()) {
                etMatriz1Campo55.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo56.getText().toString().isEmpty()) {
                etMatriz1Campo56.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo60.getText().toString().isEmpty()) {
                etMatriz1Campo60.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo61.getText().toString().isEmpty()) {
                etMatriz1Campo61.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo62.getText().toString().isEmpty()) {
                etMatriz1Campo62.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo63.getText().toString().isEmpty()) {
                etMatriz1Campo63.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo64.getText().toString().isEmpty()) {
                etMatriz1Campo64.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo65.getText().toString().isEmpty()) {
                etMatriz1Campo65.setError(_error);
                _result = false;
            }
            if (etMatriz1Campo66.getText().toString().isEmpty()) {
                etMatriz1Campo66.setError(_error);
                _result = false;
            }
            //endregion

            //region Matriz 2
            if (etMatriz2Campo00.getText().toString().isEmpty()) {
                etMatriz2Campo00.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo01.getText().toString().isEmpty()) {
                etMatriz2Campo01.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo02.getText().toString().isEmpty()) {
                etMatriz2Campo02.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo03.getText().toString().isEmpty()) {
                etMatriz2Campo03.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo04.getText().toString().isEmpty()) {
                etMatriz2Campo04.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo05.getText().toString().isEmpty()) {
                etMatriz2Campo05.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo06.getText().toString().isEmpty()) {
                etMatriz2Campo06.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo10.getText().toString().isEmpty()) {
                etMatriz2Campo10.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo11.getText().toString().isEmpty()) {
                etMatriz2Campo11.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo12.getText().toString().isEmpty()) {
                etMatriz2Campo12.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo13.getText().toString().isEmpty()) {
                etMatriz2Campo13.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo14.getText().toString().isEmpty()) {
                etMatriz2Campo14.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo15.getText().toString().isEmpty()) {
                etMatriz2Campo15.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo16.getText().toString().isEmpty()) {
                etMatriz2Campo16.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo20.getText().toString().isEmpty()) {
                etMatriz2Campo20.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo21.getText().toString().isEmpty()) {
                etMatriz2Campo21.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo22.getText().toString().isEmpty()) {
                etMatriz2Campo22.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo23.getText().toString().isEmpty()) {
                etMatriz2Campo23.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo24.getText().toString().isEmpty()) {
                etMatriz2Campo24.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo25.getText().toString().isEmpty()) {
                etMatriz2Campo25.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo26.getText().toString().isEmpty()) {
                etMatriz2Campo26.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo30.getText().toString().isEmpty()) {
                etMatriz2Campo30.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo31.getText().toString().isEmpty()) {
                etMatriz2Campo31.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo32.getText().toString().isEmpty()) {
                etMatriz2Campo32.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo33.getText().toString().isEmpty()) {
                etMatriz2Campo33.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo34.getText().toString().isEmpty()) {
                etMatriz2Campo34.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo35.getText().toString().isEmpty()) {
                etMatriz2Campo35.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo36.getText().toString().isEmpty()) {
                etMatriz2Campo36.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo40.getText().toString().isEmpty()) {
                etMatriz2Campo40.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo41.getText().toString().isEmpty()) {
                etMatriz2Campo41.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo42.getText().toString().isEmpty()) {
                etMatriz2Campo42.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo43.getText().toString().isEmpty()) {
                etMatriz2Campo43.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo44.getText().toString().isEmpty()) {
                etMatriz2Campo44.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo45.getText().toString().isEmpty()) {
                etMatriz2Campo45.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo46.getText().toString().isEmpty()) {
                etMatriz2Campo46.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo50.getText().toString().isEmpty()) {
                etMatriz2Campo50.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo51.getText().toString().isEmpty()) {
                etMatriz2Campo51.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo52.getText().toString().isEmpty()) {
                etMatriz2Campo52.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo53.getText().toString().isEmpty()) {
                etMatriz2Campo53.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo54.getText().toString().isEmpty()) {
                etMatriz2Campo54.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo55.getText().toString().isEmpty()) {
                etMatriz2Campo55.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo56.getText().toString().isEmpty()) {
                etMatriz2Campo56.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo60.getText().toString().isEmpty()) {
                etMatriz2Campo60.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo61.getText().toString().isEmpty()) {
                etMatriz2Campo61.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo62.getText().toString().isEmpty()) {
                etMatriz2Campo62.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo63.getText().toString().isEmpty()) {
                etMatriz2Campo63.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo64.getText().toString().isEmpty()) {
                etMatriz2Campo64.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo65.getText().toString().isEmpty()) {
                etMatriz2Campo65.setError(_error);
                _result = false;
            }
            if (etMatriz2Campo66.getText().toString().isEmpty()) {
                etMatriz2Campo66.setError(_error);
                _result = false;
            }
            //endregion
        }

        return _result;
    }

    //endregion

    //region Producto Punto Vectores(Producto interno o producto escalar)
    public void InicializarProductoPuntoVectores(){

        etOpcI1ProductoPunto = findViewById(R.id.etOpcI1ProductoPunto);
        etOpcJ1ProductoPunto = findViewById(R.id.etOpcJ1ProductoPunto);
        etOpcK1ProductoPunto = findViewById(R.id.etOpcK1ProductoPunto);
        etOpcI2ProductoPunto = findViewById(R.id.etOpcI2ProductoPunto);
        etOpcJ2ProductoPunto = findViewById(R.id.etOpcJ2ProductoPunto);
        etOpcK2ProductoPunto = findViewById(R.id.etOpcK2ProductoPunto);
        tvProductoPuntoResultado = findViewById(R.id.tvProductoPuntoResultado);
        OpcProductoPuntobtnSolucion = findViewById(R.id.OpcProductoPuntobtnSolucion);
        LinearProductoPuntoResultado = findViewById(R.id.LinearProductoPuntoResultado);
        btnGuardarEjercicioProductoPunto = findViewById(R.id.btnGuardarEjercicioProductoPunto);
        OpcLinear1ProductoPunto = findViewById(R.id.OpcLinear1ProductoPunto);

        OpcProductoPuntobtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarProductoPunto()){
                    String v="", w="";
                    if (Double.parseDouble(etOpcJ1ProductoPunto.getText().toString())<0 && Double.parseDouble(etOpcK1ProductoPunto.getText().toString())<0){
                        v = etOpcI1ProductoPunto.getText().toString()+"i"+etOpcJ1ProductoPunto.getText().toString()+"j"+etOpcK1ProductoPunto.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1ProductoPunto.getText().toString())<0 && Double.parseDouble(etOpcK1ProductoPunto.getText().toString())>=0){
                        v = etOpcI1ProductoPunto.getText().toString()+"i"+etOpcJ1ProductoPunto.getText().toString()+"j"+"+"+etOpcK1ProductoPunto.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1ProductoPunto.getText().toString())>=0 && Double.parseDouble(etOpcK1ProductoPunto.getText().toString())<0){
                        v = etOpcI1ProductoPunto.getText().toString()+"i"+"+"+etOpcJ1ProductoPunto.getText().toString()+"j"+etOpcK1ProductoPunto.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1ProductoPunto.getText().toString())>=0 && Double.parseDouble(etOpcK1ProductoPunto.getText().toString())>=0){
                        v = etOpcI1ProductoPunto.getText().toString()+"i"+"+"+etOpcJ1ProductoPunto.getText().toString()+"j"+"+"+etOpcK1ProductoPunto.getText().toString()+"k";
                    }

                    if (Double.parseDouble(etOpcJ2ProductoPunto.getText().toString())<0 && Double.parseDouble(etOpcK2ProductoPunto.getText().toString())<0){
                        w = etOpcI2ProductoPunto.getText().toString()+"i"+etOpcJ2ProductoPunto.getText().toString()+"j"+etOpcK2ProductoPunto.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2ProductoPunto.getText().toString())<0 && Double.parseDouble(etOpcK2ProductoPunto.getText().toString())>=0){
                        w = etOpcI2ProductoPunto.getText().toString()+"i"+etOpcJ2ProductoPunto.getText().toString()+"j"+"+"+etOpcK2ProductoPunto.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2ProductoPunto.getText().toString())>=0 && Double.parseDouble(etOpcK2ProductoPunto.getText().toString())<0){
                        w = etOpcI2ProductoPunto.getText().toString()+"i"+"+"+etOpcJ2ProductoPunto.getText().toString()+"j"+etOpcK2ProductoPunto.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2ProductoPunto.getText().toString())>=0 && Double.parseDouble(etOpcK2ProductoPunto.getText().toString())>=0){
                        w = etOpcI2ProductoPunto.getText().toString()+"i"+"+"+etOpcJ2ProductoPunto.getText().toString()+"j"+"+"+etOpcK2ProductoPunto.getText().toString()+"k";
                    }

                    tvProductoPuntoResultado.setText(sdirecta.productoPuntoVector(v,w));
                    LinearProductoPuntoResultado.setVisibility(View.VISIBLE);

                    arrayListDatos.add(Double.parseDouble(etOpcI1ProductoPunto.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ1ProductoPunto.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK1ProductoPunto.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcI2ProductoPunto.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ2ProductoPunto.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK2ProductoPunto.getText().toString()));

                    etOpcI1ProductoPunto.setText("");
                    etOpcJ1ProductoPunto.setText("");
                    etOpcK1ProductoPunto.setText("");

                    etOpcI2ProductoPunto.setText("");
                    etOpcJ2ProductoPunto.setText("");
                    etOpcK2ProductoPunto.setText("");
                }
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollProductoPunto);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                    }
                });

            }
        });
        btnGuardarEjercicioProductoPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearProductoPuntoResultado.setVisibility(View.GONE);
                etOpcI1ProductoPunto.requestFocus();
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollProductoPunto);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();
            }
        });
    }
    private Boolean validarProductoPunto() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcI1ProductoPunto.getText().toString().isEmpty()) {
            etOpcI1ProductoPunto.setError(_error);
            _result = false;
        }
        if (etOpcJ1ProductoPunto.getText().toString().isEmpty()) {
            etOpcJ1ProductoPunto.setError(_error);
            _result = false;
        }
        if (etOpcK1ProductoPunto.getText().toString().isEmpty()) {
            etOpcK1ProductoPunto.setError(_error);
            _result = false;
        }
        if (etOpcI2ProductoPunto.getText().toString().isEmpty()) {
            etOpcI2ProductoPunto.setError(_error);
            _result = false;
        }
        if (etOpcJ2ProductoPunto.getText().toString().isEmpty()) {
            etOpcJ2ProductoPunto.setError(_error);
            _result = false;
        }
        if (etOpcK2ProductoPunto.getText().toString().isEmpty()) {
            etOpcK2ProductoPunto.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Suma Vectores Resultante
    public void InicializarSumaVectorRVectores(){

        etOpcI1SumaVectorR = findViewById(R.id.etOpcI1SumaVectorR);
        etOpcJ1SumaVectorR = findViewById(R.id.etOpcJ1SumaVectorR);
        etOpcK1SumaVectorR = findViewById(R.id.etOpcK1SumaVectorR);
        etOpcI2SumaVectorR = findViewById(R.id.etOpcI2SumaVectorR);
        etOpcJ2SumaVectorR = findViewById(R.id.etOpcJ2SumaVectorR);
        etOpcK2SumaVectorR = findViewById(R.id.etOpcK2SumaVectorR);
        etOpcN1SumaVectorR = findViewById(R.id.etOpcN1SumaVectorR);
        etOpcM1SumaVectorR = findViewById(R.id.etOpcM1SumaVectorR);
        tvSumaVectorRResultado = findViewById(R.id.tvSumaVectorRResultado);
        OpcSumaVectorRbtnSolucion = findViewById(R.id.OpcSumaVectorRbtnSolucion);
        LinearSumaVectorRResultado = findViewById(R.id.LinearSumaVectorRResultado);
        btnGuardarEjercicioSumaVectorR = findViewById(R.id.btnGuardarEjercicioSumaVectorR);
        OpcLinear1SumaVectorR = findViewById(R.id.OpcLinear1SumaVectorR);

        OpcSumaVectorRbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarSumaVectorR()){
                    String v="", w="";
                    double n,m;

                    if (etOpcN1SumaVectorR.getText().toString().isEmpty()){
                        n = 1;
                    }else {
                        n = Double.parseDouble(etOpcN1SumaVectorR.getText().toString());
                    }
                    if (etOpcM1SumaVectorR.getText().toString().isEmpty()){
                        m = 1;
                    }else {
                        m = Double.parseDouble(etOpcM1SumaVectorR.getText().toString());
                    }
                    if (Double.parseDouble(etOpcJ1SumaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK1SumaVectorR.getText().toString())<0){
                        v = etOpcI1SumaVectorR.getText().toString()+"i"+etOpcJ1SumaVectorR.getText().toString()+"j"+etOpcK1SumaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1SumaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK1SumaVectorR.getText().toString())>=0){
                        v = etOpcI1SumaVectorR.getText().toString()+"i"+etOpcJ1SumaVectorR.getText().toString()+"j"+"+"+etOpcK1SumaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1SumaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK1SumaVectorR.getText().toString())<0){
                        v = etOpcI1SumaVectorR.getText().toString()+"i"+"+"+etOpcJ1SumaVectorR.getText().toString()+"j"+etOpcK1SumaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1SumaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK1SumaVectorR.getText().toString())>=0){
                        v = etOpcI1SumaVectorR.getText().toString()+"i"+"+"+etOpcJ1SumaVectorR.getText().toString()+"j"+"+"+etOpcK1SumaVectorR.getText().toString()+"k";
                    }

                    if (Double.parseDouble(etOpcJ2SumaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK2SumaVectorR.getText().toString())<0){
                        w = etOpcI2SumaVectorR.getText().toString()+"i"+etOpcJ2SumaVectorR.getText().toString()+"j"+etOpcK2SumaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2SumaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK2SumaVectorR.getText().toString())>=0){
                        w = etOpcI2SumaVectorR.getText().toString()+"i"+etOpcJ2SumaVectorR.getText().toString()+"j"+"+"+etOpcK2SumaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2SumaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK2SumaVectorR.getText().toString())<0){
                        w = etOpcI2SumaVectorR.getText().toString()+"i"+"+"+etOpcJ2SumaVectorR.getText().toString()+"j"+etOpcK2SumaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2SumaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK2SumaVectorR.getText().toString())>=0){
                        w = etOpcI2SumaVectorR.getText().toString()+"i"+"+"+etOpcJ2SumaVectorR.getText().toString()+"j"+"+"+etOpcK2SumaVectorR.getText().toString()+"k";
                    }

                    tvSumaVectorRResultado.setText(sdirecta.sumaVectorResultante(n,v,m,w));
                    LinearSumaVectorRResultado.setVisibility(View.VISIBLE);

                    arrayListDatos.add(Double.parseDouble(etOpcN1SumaVectorR.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcI1SumaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ1SumaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK1SumaVectorR.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcM1SumaVectorR.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcI2SumaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ2SumaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK2SumaVectorR.getText().toString()));

                    etOpcN1SumaVectorR.setText("");
                    etOpcI1SumaVectorR.setText("");
                    etOpcJ1SumaVectorR.setText("");
                    etOpcK1SumaVectorR.setText("");

                    etOpcM1SumaVectorR.setText("");
                    etOpcI2SumaVectorR.setText("");
                    etOpcJ2SumaVectorR.setText("");
                    etOpcK2SumaVectorR.setText("");
                }
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollSumaVectorR);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                    }
                });

            }
        });
        btnGuardarEjercicioSumaVectorR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearSumaVectorRResultado.setVisibility(View.GONE);
                etOpcI1SumaVectorR.requestFocus();
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollSumaVectorR);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();
            }
        });
    }
    private Boolean validarSumaVectorR() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcI1SumaVectorR.getText().toString().isEmpty()) {
            etOpcI1SumaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcJ1SumaVectorR.getText().toString().isEmpty()) {
            etOpcJ1SumaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcK1SumaVectorR.getText().toString().isEmpty()) {
            etOpcK1SumaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcI2SumaVectorR.getText().toString().isEmpty()) {
            etOpcI2SumaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcJ2SumaVectorR.getText().toString().isEmpty()) {
            etOpcJ2SumaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcK2SumaVectorR.getText().toString().isEmpty()) {
            etOpcK2SumaVectorR.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Resta Vectores Resultante
    public void InicializarRestaVectorRVectores(){

        etOpcI1RestaVectorR = findViewById(R.id.etOpcI1RestaVectorR);
        etOpcJ1RestaVectorR = findViewById(R.id.etOpcJ1RestaVectorR);
        etOpcK1RestaVectorR = findViewById(R.id.etOpcK1RestaVectorR);
        etOpcI2RestaVectorR = findViewById(R.id.etOpcI2RestaVectorR);
        etOpcJ2RestaVectorR = findViewById(R.id.etOpcJ2RestaVectorR);
        etOpcK2RestaVectorR = findViewById(R.id.etOpcK2RestaVectorR);
        etOpcN1RestaVectorR = findViewById(R.id.etOpcN1RestaVectorR);
        etOpcM1RestaVectorR = findViewById(R.id.etOpcM1RestaVectorR);
        tvRestaVectorRResultado = findViewById(R.id.tvRestaVectorRResultado);
        OpcRestaVectorRbtnSolucion = findViewById(R.id.OpcRestaVectorRbtnSolucion);
        LinearRestaVectorRResultado = findViewById(R.id.LinearRestaVectorRResultado);
        btnGuardarEjercicioRestaVectorR = findViewById(R.id.btnGuardarEjercicioRestaVectorR);
        OpcLinear1RestaVectorR = findViewById(R.id.OpcLinear1RestaVectorR);

        OpcRestaVectorRbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarRestaVectorR()){
                    String v="", w="";
                    double n,m;

                    if (etOpcN1RestaVectorR.getText().toString().isEmpty()){
                        n = 1;
                    }else {
                        n = Double.parseDouble(etOpcN1RestaVectorR.getText().toString());
                    }
                    if (etOpcM1RestaVectorR.getText().toString().isEmpty()){
                        m = 1;
                    }else {
                        m = Double.parseDouble(etOpcM1RestaVectorR.getText().toString());
                    }
                    if (Double.parseDouble(etOpcJ1RestaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK1RestaVectorR.getText().toString())<0){
                        v = etOpcI1RestaVectorR.getText().toString()+"i"+etOpcJ1RestaVectorR.getText().toString()+"j"+etOpcK1RestaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1RestaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK1RestaVectorR.getText().toString())>=0){
                        v = etOpcI1RestaVectorR.getText().toString()+"i"+etOpcJ1RestaVectorR.getText().toString()+"j"+"+"+etOpcK1RestaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1RestaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK1RestaVectorR.getText().toString())<0){
                        v = etOpcI1RestaVectorR.getText().toString()+"i"+"+"+etOpcJ1RestaVectorR.getText().toString()+"j"+etOpcK1RestaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1RestaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK1RestaVectorR.getText().toString())>=0){
                        v = etOpcI1RestaVectorR.getText().toString()+"i"+"+"+etOpcJ1RestaVectorR.getText().toString()+"j"+"+"+etOpcK1RestaVectorR.getText().toString()+"k";
                    }

                    if (Double.parseDouble(etOpcJ2RestaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK2RestaVectorR.getText().toString())<0){
                        w = etOpcI2RestaVectorR.getText().toString()+"i"+etOpcJ2RestaVectorR.getText().toString()+"j"+etOpcK2RestaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2RestaVectorR.getText().toString())<0 && Double.parseDouble(etOpcK2RestaVectorR.getText().toString())>=0){
                        w = etOpcI2RestaVectorR.getText().toString()+"i"+etOpcJ2RestaVectorR.getText().toString()+"j"+"+"+etOpcK2RestaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2RestaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK2RestaVectorR.getText().toString())<0){
                        w = etOpcI2RestaVectorR.getText().toString()+"i"+"+"+etOpcJ2RestaVectorR.getText().toString()+"j"+etOpcK2RestaVectorR.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2RestaVectorR.getText().toString())>=0 && Double.parseDouble(etOpcK2RestaVectorR.getText().toString())>=0){
                        w = etOpcI2RestaVectorR.getText().toString()+"i"+"+"+etOpcJ2RestaVectorR.getText().toString()+"j"+"+"+etOpcK2RestaVectorR.getText().toString()+"k";
                    }

                    tvRestaVectorRResultado.setText(sdirecta.restaVectorResultante(n,v,m,w));
                    LinearRestaVectorRResultado.setVisibility(View.VISIBLE);

                    arrayListDatos.add(Double.parseDouble(etOpcN1RestaVectorR.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcI1RestaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ1RestaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK1RestaVectorR.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcM1RestaVectorR.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcI2RestaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ2RestaVectorR.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK2RestaVectorR.getText().toString()));

                    etOpcN1RestaVectorR.setText("");
                    etOpcI1RestaVectorR.setText("");
                    etOpcJ1RestaVectorR.setText("");
                    etOpcK1RestaVectorR.setText("");

                    etOpcM1RestaVectorR.setText("");
                    etOpcI2RestaVectorR.setText("");
                    etOpcJ2RestaVectorR.setText("");
                    etOpcK2RestaVectorR.setText("");
                }
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollRestaVectorR);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                    }
                });

            }
        });
        btnGuardarEjercicioRestaVectorR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearRestaVectorRResultado.setVisibility(View.GONE);
                etOpcI1RestaVectorR.requestFocus();
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollRestaVectorR);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();
            }
        });
    }
    private Boolean validarRestaVectorR() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcI1RestaVectorR.getText().toString().isEmpty()) {
            etOpcI1RestaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcJ1RestaVectorR.getText().toString().isEmpty()) {
            etOpcJ1RestaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcK1RestaVectorR.getText().toString().isEmpty()) {
            etOpcK1RestaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcI2RestaVectorR.getText().toString().isEmpty()) {
            etOpcI2RestaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcJ2RestaVectorR.getText().toString().isEmpty()) {
            etOpcJ2RestaVectorR.setError(_error);
            _result = false;
        }
        if (etOpcK2RestaVectorR.getText().toString().isEmpty()) {
            etOpcK2RestaVectorR.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Vector Unitario
    public void InicializarVectorUnitario(){

        etOpcI1VectorUnitario = findViewById(R.id.etOpcI1VectorUnitario);
        etOpcJ1VectorUnitario = findViewById(R.id.etOpcJ1VectorUnitario);
        etOpcK1VectorUnitario = findViewById(R.id.etOpcK1VectorUnitario);

        tvVectorUnitarioResultado = findViewById(R.id.tvVectorUnitarioResultado);
        OpcVectorUnitariobtnSolucion = findViewById(R.id.OpcVectorUnitariobtnSolucion);
        LinearVectorUnitarioResultado = findViewById(R.id.LinearVectorUnitarioResultado);
        btnGuardarEjercicioVectorUnitario = findViewById(R.id.btnGuardarEjercicioVectorUnitario);
        OpcLinear1VectorUnitario = findViewById(R.id.OpcLinear1VectorUnitario);

        OpcVectorUnitariobtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarVectorUnitario()){
                    String v="";

                    if (Double.parseDouble(etOpcJ1VectorUnitario.getText().toString())<0 && Double.parseDouble(etOpcK1VectorUnitario.getText().toString())<0){
                        v = etOpcI1VectorUnitario.getText().toString()+"i"+etOpcJ1VectorUnitario.getText().toString()+"j"+etOpcK1VectorUnitario.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1VectorUnitario.getText().toString())<0 && Double.parseDouble(etOpcK1VectorUnitario.getText().toString())>=0){
                        v = etOpcI1VectorUnitario.getText().toString()+"i"+etOpcJ1VectorUnitario.getText().toString()+"j"+"+"+etOpcK1VectorUnitario.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1VectorUnitario.getText().toString())>=0 && Double.parseDouble(etOpcK1VectorUnitario.getText().toString())<0){
                        v = etOpcI1VectorUnitario.getText().toString()+"i"+"+"+etOpcJ1VectorUnitario.getText().toString()+"j"+etOpcK1VectorUnitario.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1VectorUnitario.getText().toString())>=0 && Double.parseDouble(etOpcK1VectorUnitario.getText().toString())>=0){
                        v = etOpcI1VectorUnitario.getText().toString()+"i"+"+"+etOpcJ1VectorUnitario.getText().toString()+"j"+"+"+etOpcK1VectorUnitario.getText().toString()+"k";
                    }

                    tvVectorUnitarioResultado.setText(sdirecta.vecRepresentacionUni(v));
                    LinearVectorUnitarioResultado.setVisibility(View.VISIBLE);

                    arrayListDatos.add(Double.parseDouble(etOpcI1VectorUnitario.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ1VectorUnitario.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK1VectorUnitario.getText().toString()));

                    etOpcI1VectorUnitario.setText("");
                    etOpcJ1VectorUnitario.setText("");
                    etOpcK1VectorUnitario.setText("");

                }
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollVectorUnitario);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                    }
                });

            }
        });
        btnGuardarEjercicioVectorUnitario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearVectorUnitarioResultado.setVisibility(View.GONE);
                etOpcI1VectorUnitario.requestFocus();
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollVectorUnitario);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();
            }
        });
    }
    private Boolean validarVectorUnitario() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcI1VectorUnitario.getText().toString().isEmpty()) {
            etOpcI1VectorUnitario.setError(_error);
            _result = false;
        }
        if (etOpcJ1VectorUnitario.getText().toString().isEmpty()) {
            etOpcJ1VectorUnitario.setError(_error);
            _result = false;
        }
        if (etOpcK1VectorUnitario.getText().toString().isEmpty()) {
            etOpcK1VectorUnitario.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    //region Angulo Entre Vectores
    public void InicializarAnguloEntreVectores(){

        etOpcI1AnguloEntreVectores = findViewById(R.id.etOpcI1AnguloEntreVectores);
        etOpcJ1AnguloEntreVectores = findViewById(R.id.etOpcJ1AnguloEntreVectores);
        etOpcK1AnguloEntreVectores = findViewById(R.id.etOpcK1AnguloEntreVectores);
        etOpcI2AnguloEntreVectores = findViewById(R.id.etOpcI2AnguloEntreVectores);
        etOpcJ2AnguloEntreVectores = findViewById(R.id.etOpcJ2AnguloEntreVectores);
        etOpcK2AnguloEntreVectores = findViewById(R.id.etOpcK2AnguloEntreVectores);
        tvAnguloEntreVectoresResultado = findViewById(R.id.tvAnguloEntreVectoresResultado);
        OpcAnguloEntreVectoresbtnSolucion = findViewById(R.id.OpcAnguloEntreVectoresbtnSolucion);
        LinearAnguloEntreVectoresResultado = findViewById(R.id.LinearAnguloEntreVectoresResultado);
        btnGuardarEjercicioAnguloEntreVectores = findViewById(R.id.btnGuardarEjercicioAnguloEntreVectores);
        OpcLinear1AnguloEntreVectores = findViewById(R.id.OpcLinear1AnguloEntreVectores);

        OpcAnguloEntreVectoresbtnSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarAnguloEntreVectores()){
                    String v="", w="";
                    if (Double.parseDouble(etOpcJ1AnguloEntreVectores.getText().toString())<0 && Double.parseDouble(etOpcK1AnguloEntreVectores.getText().toString())<0){
                        v = etOpcI1AnguloEntreVectores.getText().toString()+"i"+etOpcJ1AnguloEntreVectores.getText().toString()+"j"+etOpcK1AnguloEntreVectores.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1AnguloEntreVectores.getText().toString())<0 && Double.parseDouble(etOpcK1AnguloEntreVectores.getText().toString())>=0){
                        v = etOpcI1AnguloEntreVectores.getText().toString()+"i"+etOpcJ1AnguloEntreVectores.getText().toString()+"j"+"+"+etOpcK1AnguloEntreVectores.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1AnguloEntreVectores.getText().toString())>=0 && Double.parseDouble(etOpcK1AnguloEntreVectores.getText().toString())<0){
                        v = etOpcI1AnguloEntreVectores.getText().toString()+"i"+"+"+etOpcJ1AnguloEntreVectores.getText().toString()+"j"+etOpcK1AnguloEntreVectores.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ1AnguloEntreVectores.getText().toString())>=0 && Double.parseDouble(etOpcK1AnguloEntreVectores.getText().toString())>=0){
                        v = etOpcI1AnguloEntreVectores.getText().toString()+"i"+"+"+etOpcJ1AnguloEntreVectores.getText().toString()+"j"+"+"+etOpcK1AnguloEntreVectores.getText().toString()+"k";
                    }

                    if (Double.parseDouble(etOpcJ2AnguloEntreVectores.getText().toString())<0 && Double.parseDouble(etOpcK2AnguloEntreVectores.getText().toString())<0){
                        w = etOpcI2AnguloEntreVectores.getText().toString()+"i"+etOpcJ2AnguloEntreVectores.getText().toString()+"j"+etOpcK2AnguloEntreVectores.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2AnguloEntreVectores.getText().toString())<0 && Double.parseDouble(etOpcK2AnguloEntreVectores.getText().toString())>=0){
                        w = etOpcI2AnguloEntreVectores.getText().toString()+"i"+etOpcJ2AnguloEntreVectores.getText().toString()+"j"+"+"+etOpcK2AnguloEntreVectores.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2AnguloEntreVectores.getText().toString())>=0 && Double.parseDouble(etOpcK2AnguloEntreVectores.getText().toString())<0){
                        w = etOpcI2AnguloEntreVectores.getText().toString()+"i"+"+"+etOpcJ2AnguloEntreVectores.getText().toString()+"j"+etOpcK2AnguloEntreVectores.getText().toString()+"k";
                    }else if (Double.parseDouble(etOpcJ2AnguloEntreVectores.getText().toString())>=0 && Double.parseDouble(etOpcK2AnguloEntreVectores.getText().toString())>=0){
                        w = etOpcI2AnguloEntreVectores.getText().toString()+"i"+"+"+etOpcJ2AnguloEntreVectores.getText().toString()+"j"+"+"+etOpcK2AnguloEntreVectores.getText().toString()+"k";
                    }

                    tvAnguloEntreVectoresResultado.setText(sdirecta.anguloEntreVectores(v,w));
                    LinearAnguloEntreVectoresResultado.setVisibility(View.VISIBLE);

                    arrayListDatos.add(Double.parseDouble(etOpcI1AnguloEntreVectores.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ1AnguloEntreVectores.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK1AnguloEntreVectores.getText().toString()));

                    arrayListDatos.add(Double.parseDouble(etOpcI2AnguloEntreVectores.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcJ2AnguloEntreVectores.getText().toString()));
                    arrayListDatos.add(Double.parseDouble(etOpcK2AnguloEntreVectores.getText().toString()));


                    etOpcI1AnguloEntreVectores.setText("");
                    etOpcJ1AnguloEntreVectores.setText("");
                    etOpcK1AnguloEntreVectores.setText("");

                    etOpcI2AnguloEntreVectores.setText("");
                    etOpcJ2AnguloEntreVectores.setText("");
                    etOpcK2AnguloEntreVectores.setText("");
                }
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollAnguloEntreVectores);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getBottom());
                    }
                });

            }
        });
        btnGuardarEjercicioAnguloEntreVectores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearAnguloEntreVectoresResultado.setVisibility(View.GONE);
                etOpcI1AnguloEntreVectores.requestFocus();
                final ScrollView myScroller = (ScrollView) findViewById(R.id.scrollAnguloEntreVectores);
                myScroller.post(new Runnable() {
                    @Override
                    public void run() {
                        myScroller.smoothScrollTo(0, myScroller.getChildAt(0).getTop());
                    }
                });
                SaveExcerciseGeneral();
            }
        });
    }
    private Boolean validarAnguloEntreVectores() {
        boolean _result = true;
        String _error = "Campo vacío";

        if (etOpcI1AnguloEntreVectores.getText().toString().isEmpty()) {
            etOpcI1AnguloEntreVectores.setError(_error);
            _result = false;
        }
        if (etOpcJ1AnguloEntreVectores.getText().toString().isEmpty()) {
            etOpcJ1AnguloEntreVectores.setError(_error);
            _result = false;
        }
        if (etOpcK1AnguloEntreVectores.getText().toString().isEmpty()) {
            etOpcK1AnguloEntreVectores.setError(_error);
            _result = false;
        }
        if (etOpcI2AnguloEntreVectores.getText().toString().isEmpty()) {
            etOpcI2AnguloEntreVectores.setError(_error);
            _result = false;
        }
        if (etOpcJ2AnguloEntreVectores.getText().toString().isEmpty()) {
            etOpcJ2AnguloEntreVectores.setError(_error);
            _result = false;
        }
        if (etOpcK2AnguloEntreVectores.getText().toString().isEmpty()) {
            etOpcK2AnguloEntreVectores.setError(_error);
            _result = false;
        }
        return _result;
    }
    //endregion

    private void getParameter() {
        //Obtencion del objeto como parametro
        lblOperacion.setVisibility(View.GONE);
        Bundle _pObjeto = getIntent().getExtras();
        myExerciseToSolve = new csTemasContenido();
        if (_pObjeto != null) {
            myExerciseToSolve = (csTemasContenido) _pObjeto.getSerializable("KeyExercise");
            lblCapturaNombreTema2.setText(myExerciseToSolve.getNombreTema());
        }
        if (myExerciseToSolve.getIdTema().equals(205)){
            lblCapturaNombreTema2.setText(myExerciseToSolve.getNombreTema()+":");
            lblOperacion.setVisibility(View.VISIBLE);
            OpcLinearMatrices.setVisibility(myExerciseToSolve.getIdTema().equals(205) ? View.VISIBLE : View.GONE);
        }
        OpcLinear1DivisionComplejos.setVisibility(myExerciseToSolve.getIdTema().equals(106) ? View.VISIBLE : View.GONE);
        OpcLinear1ProductoComplejos.setVisibility(myExerciseToSolve.getIdTema().equals(110) ? View.VISIBLE : View.GONE);
        OpcLinear1SumaComplejos.setVisibility(myExerciseToSolve.getIdTema().equals(112) ? View.VISIBLE : View.GONE);
        OpcLinear1RestaComplejos.setVisibility(myExerciseToSolve.getIdTema().equals(114) ? View.VISIBLE : View.GONE);
        OpcLinear1PotenciasI.setVisibility(myExerciseToSolve.getIdTema().equals(111) ? View.VISIBLE : View.GONE);
        OpcLinear1RectangularPolar.setVisibility(myExerciseToSolve.getIdTema().equals(115) ? View.VISIBLE : View.GONE);
        OpcLinear1ProductoPolares.setVisibility(myExerciseToSolve.getIdTema().equals(108) ? View.VISIBLE : View.GONE);
        OpcLinear1DivisionPolares.setVisibility(myExerciseToSolve.getIdTema().equals(105) ? View.VISIBLE : View.GONE);
        OpcLinear1RaicesNegativas.setVisibility(myExerciseToSolve.getIdTema().equals(109) ? View.VISIBLE : View.GONE);
        OpcLinear1ProductoPunto.setVisibility(myExerciseToSolve.getIdTema().equals(305) ? View.VISIBLE : View.GONE);
        OpcLinear1SumaVectorR.setVisibility(myExerciseToSolve.getIdTema().equals(307) ? View.VISIBLE : View.GONE);
        OpcLinear1RestaVectorR.setVisibility(myExerciseToSolve.getIdTema().equals(308) ? View.VISIBLE : View.GONE);
        OpcLinear1VectorUnitario.setVisibility(myExerciseToSolve.getIdTema().equals(309) ? View.VISIBLE : View.GONE);
        OpcLinear1AnguloEntreVectores.setVisibility(myExerciseToSolve.getIdTema().equals(310) ? View.VISIBLE : View.GONE);

}

    private void SaveExcerciseGeneral()
    {
        try
        {
            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvin = myConn.getWritableDatabase();
            Integer _contador = 0, _idEjercicio = 0;

            _idEjercicio = ValidarIdEjercicios(myExerciseToSolve.getIdTema());
            String _msj = "";

            ContentValues _informacion = new ContentValues();
            _informacion.put(ScriptDB.CAMPO_IDEXERCISE,_idEjercicio);


            if (lblOperacion.getVisibility()==View.VISIBLE){
                String opeMatrizDirecta = operacion;

                _informacion.put(ScriptDB.CAMPO_NAMETOPIC,myExerciseToSolve.getNombreTema()+opeMatrizDirecta);
            }else {
                _informacion.put(ScriptDB.CAMPO_NAMETOPIC,myExerciseToSolve.getNombreTema());
            }

            _informacion.put(ScriptDB.CAMPO_NAMECATEGORY,(myExerciseToSolve.getCategoriaTema()) == 1 ? "Números Complejos" : myExerciseToSolve.getCategoriaTema() == 2 ? "Matrices" : "Vectores");
            _informacion.put(ScriptDB.CAMPO_IDCATEGORY,myExerciseToSolve.getCategoriaTema());
            _informacion.put(ScriptDB.CAMPO_IDTOPIC,myExerciseToSolve.getIdTema());
            _informacion.put(ScriptDB.CAMPO_GRAPHIC,myExerciseToSolve.getGrafica() == true ? 1 : 0);
            Long _insertGeneral = mydbvin.insert(ScriptDB.TABLE_INFO_GENERAL,ScriptDB.CAMPO_IDREG,_informacion);

            for (Double _dato : arrayListDatos) {
                ContentValues _datos = new ContentValues();

                _datos.put(ScriptDB.CAMPO_IDFK_EXERCISE,_idEjercicio);
                _datos.put(ScriptDB.CAMPO_VALOR,_dato);
                _datos.put(ScriptDB.CAMPO_POSICION,_contador);

                Long _insertdata = mydbvin.insert(ScriptDB.TABLE_DATOS,ScriptDB.CAMPO_IDDATO,_datos);
                _contador++;
                //Toast.makeText(getApplicationContext(), "Insert ????? "+_insertdata.toString(), Toast.LENGTH_LONG).show();
            }
            _msj = _contador.equals(arrayListDatos.size()) ? "Ejercicio guardado..." : "¡Error de almacenamiento!" ;
            Toast.makeText(captura_de_datos_directa.this, _msj, Toast.LENGTH_LONG).show();
            arrayListDatos.clear();
            myConn.close();

        }
        catch(Exception  e) {
            e.getMessage().toString();
        }
    }

    private Integer ValidarIdEjercicios(Integer _tema){
        Integer _id = 0;
        if(_tema != 302 && _tema != 201){

            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvinEG = myConn.getReadableDatabase();

            Cursor query = mydbvinEG.rawQuery("SELECT * FROM "+ScriptDB.TABLE_INFO_GENERAL,null);
            ArrayList<csEjerciciosGeneralesSQL> myListaEjerciciosG = new ArrayList<csEjerciciosGeneralesSQL>();
            csEjerciciosGeneralesSQL _ejercicio = new csEjerciciosGeneralesSQL();
            while (query.moveToNext()){
                _ejercicio.setIdEjercicio(query.getInt(1));
                myListaEjerciciosG.add(_ejercicio);
            }
            _id = (myListaEjerciciosG.size() > 0) ? myListaEjerciciosG.get(myListaEjerciciosG.size() - 1).getIdEjercicio() + 1 : 1 ;
            myConn.close();
        }
        return _id;
    }

    //region *****
    private void OpcionSeleccionada(LinearLayout _layoutselected, TextView _textselected){
        _layoutselected.setBackgroundResource(R.drawable.item_opc_seleccionado);
        _textselected.setTextColor(Color.parseColor("#008080"));
    }
    private void OpcionOriginal(LinearLayout _layoutselected, TextView _textselected){
        _layoutselected.setBackgroundResource(R.drawable.item_opc);
        _textselected.setTextColor(Color.parseColor("#465e5e"));
    }
    //endregion

}
