package com.joabaler.vin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joabaler.vin.Adaptadores.DetailSolutionAdapterRecycler;
import com.joabaler.vin.Entidades.csDetailStep;
import com.joabaler.vin.Entidades.csMatriz;
import com.joabaler.vin.Entidades.csTemasContenido;
import com.joabaler.vin.Entidades.csVectorProyeccion;
import com.joabaler.vin.SQLite.ConexionSQLHelper;
import com.joabaler.vin.SQLite.EntidadesSQL.csEjerciciosGeneralesSQL;
import com.joabaler.vin.SQLite.EntidadesSQL.csMatrizSQL;
import com.joabaler.vin.SQLite.EntidadesSQL.csVectorProyeccionSQL;
import com.joabaler.vin.SQLite.ScriptDB;

import java.util.ArrayList;

public class SolucionDetallada extends AppCompatActivity {

    private LinearLayout btnVerGrafico, btnGuardarEjercicio,linearVisibilidadGrafica;
    private RecyclerView myRecyclerViewDetailSteps;
    private DetailSolutionAdapterRecycler myAdapterDetailSolutions;
    private ArrayList<csDetailStep> mylistaPasosSolucion;
    private Integer RequestCodeSolucion = 4;
    private csTemasContenido myExerciseSolvented;
    private TextView lblNombreEjercicio,lblSolucionCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solucion_detallada);
        getSupportActionBar().hide(); //ocultar la barra

        //region Inicializacion de variables
        btnVerGrafico = findViewById(R.id.btnVerGrafico);
        btnGuardarEjercicio = findViewById(R.id.btnGuardarEjercico);
        myRecyclerViewDetailSteps = findViewById(R.id.lstPasosSolucion);
        myRecyclerViewDetailSteps.setLayoutManager(new LinearLayoutManager(this));
        lblNombreEjercicio = findViewById(R.id.lblSolucionNombreEjercicio);
        lblSolucionCategoria = findViewById(R.id.lblSolucionCategoria);
        linearVisibilidadGrafica = findViewById(R.id.linearVisibilidadGrafica);
        //endregion

        //region set Eventos
        btnVerGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerGrafico();
            }
        });
        btnGuardarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarEjercicio();
            }
        });
        //endregion

        getData();
        myAdapterDetailSolutions = new DetailSolutionAdapterRecycler(mylistaPasosSolucion);
        myRecyclerViewDetailSteps.setAdapter(myAdapterDetailSolutions);
        myAdapterDetailSolutions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                csDetailStep _pPasoSeleccionado = mylistaPasosSolucion.get(myRecyclerViewDetailSteps.getChildAdapterPosition(view));
                Intent _OpenPaso = new Intent(getApplicationContext(), DetalleDelPaso.class);
                Bundle _bundle = new Bundle();
                _bundle.putSerializable("KeyListaPasos", mylistaPasosSolucion);
                _bundle.putSerializable("KeyPaso", _pPasoSeleccionado);
                _OpenPaso.putExtras(_bundle);
                startActivityForResult(_OpenPaso, RequestCodeSolucion);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodeSolucion) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.hasExtra("pasoapaso")) {
                    Intent intentListaPasos = new Intent();
                    intentListaPasos.putExtra("listasolucion", true);
                    setResult(Activity.RESULT_OK, intentListaPasos);
                    finish();
                }
            }
        }
    }

    private void VerGrafico() {
        Toast.makeText(getApplicationContext(), "Click Ver Gráfico", Toast.LENGTH_LONG).show();
    }

    private void GuardarEjercicio() {

        switch (myExerciseSolvented.getIdTema()){

            //region E J E R C I C I O S    P A S O    A   P A S O  ====================================
            //////////////  N U M E R O S   C O M P L E J O S  ////////////////
            //region OperacionesRepresentacionExponencial
            case 101:
                SaveExcerciseGeneral();
                break;
            //endregion //OK
            //region RaizNumerosPolares
            case 102:
                SaveExcerciseGeneral();
                break;
            //endregion
            //region OperacionPolarPotencia
            case 103:
                SaveExcerciseGeneral();
                break;
            //endregion
            //region PolarAExponencialNC
            case 104:
            SaveExcerciseGeneral();
            break;
            //endregion

            //////////////  M A T R I C E S  ////////////////
            //region Matriz de Gauss
            case 201:
                try{
                    ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                    SQLiteDatabase mydbvin = myConn.getWritableDatabase();
                    Integer _contador = 0, _idEjercicio = 0;
                    _idEjercicio = ValidarIdEjercicios(myExerciseSolvented.getIdTema());
                    String _msj = "";

                    for (csMatriz _ecuacion: CapturaDeDatos.myMatrizGaussDatos) {
                        ContentValues _datos = new ContentValues();

                        _datos.put(ScriptDB.cMIdEjercicio,_idEjercicio);
                        _datos.put(ScriptDB.cMIdEcuacion,_ecuacion.getIdEcuacion());
                        _datos.put(ScriptDB.cMw,_ecuacion.getW());
                        _datos.put(ScriptDB.cMx,_ecuacion.getX());
                        _datos.put(ScriptDB.cMy,_ecuacion.getY());
                        _datos.put(ScriptDB.cMz,_ecuacion.getZ());
                        _datos.put(ScriptDB.cMIgual,_ecuacion.getIgual());
                        _datos.put(ScriptDB.cMTamano,_ecuacion.getTamano());
                        _datos.put(ScriptDB.cMNombreTema,lblNombreEjercicio.getText().toString());
                        _datos.put(ScriptDB.cMCategoriaTema,lblSolucionCategoria.getText().toString());
                        _datos.put(ScriptDB.cMIdTema,myExerciseSolvented.getIdTema());
                        Long _insert = mydbvin.insert(ScriptDB.tMATRIZ,ScriptDB.cMIdRegistro,_datos);
                        _contador++;
                    }
                    CapturaDeDatos.myVectorProyeccionDatos.clear();
                    _msj = _contador.equals(CapturaDeDatos.myMatrizGaussDatos.size()) ? "Ejercicio guardado..." : "¡Error de almacenamiento!" ;
                    myConn.close();
                    Toast.makeText(SolucionDetallada.this, _msj, Toast.LENGTH_LONG).show();
                }
                catch(Exception  e) {
                    e.getMessage().toString();
                }
                break;
            //endregion
            //region MatrizInverza
            case 202:
                SaveExcerciseGeneral();
                break;
            //endregion

            //////////////  V E C T O R E S  ////////////////
            //region FuerzaResultanteEnElEspacio
            case 301:
                SaveExcerciseGeneral();
                break;
            //endregion
            //region Vector proyeccion -  Proyección de un vector sobre otro
            case 302:
                try{
                    ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                    SQLiteDatabase mydbvin = myConn.getWritableDatabase();

                    Integer _contador = 0, _idEjercicio = 0,_idecuacion = 1;
                    _idEjercicio = ValidarIdEjercicios(myExerciseSolvented.getIdTema());
                    String _msj = "";

                    for (csVectorProyeccion _ecuacion: CapturaDeDatos.myVectorProyeccionDatos) {
                        ContentValues _datos = new ContentValues();

                        _datos.put(ScriptDB.cVPIdEjercicio,_idEjercicio);
                        _datos.put(ScriptDB.cVPIdEcuacion,_idecuacion);
                        _datos.put(ScriptDB.cVPCuadrante,_ecuacion.getCuadrante());
                        _datos.put(ScriptDB.cVPDireccion,_ecuacion.getDireccion());
                        _datos.put(ScriptDB.cVPApunta,_ecuacion.getApunta());
                        _datos.put(ScriptDB.cVPFuerza,_ecuacion.getFuerza());
                        _datos.put(ScriptDB.cVPAngulo,_ecuacion.getAngulo());
                        _datos.put(ScriptDB.cVPAngRespectoA,_ecuacion.getAngRespectoA());
                        _datos.put(ScriptDB.cVPNombreTema,myExerciseSolvented.getNombreTema());
                        _datos.put(ScriptDB.cVPCategoriaTema,lblSolucionCategoria.getText().toString());
                        _datos.put(ScriptDB.cVPIdTema,myExerciseSolvented.getIdTema());

                        Long _insert = mydbvin.insert(ScriptDB.tVECTORPROYECCION,ScriptDB.cVPIdRegistro,_datos);
                        _contador++;
                        _idecuacion++;
                    }
                    _msj = _contador.equals(CapturaDeDatos.myVectorProyeccionDatos.size()) ? "Ejercicio guardado..." : "¡Error de almacenamiento!" ;
                    myConn.close();
                    Toast.makeText(SolucionDetallada.this, _msj, Toast.LENGTH_LONG).show();
                }
                catch(Exception  e) {
                    e.getMessage().toString();
                }
                break;
            //endregion
            //region ProductoConVectores
                case 303:
                    SaveExcerciseGeneral();
                    break;
            //endregion

            //endregion
                default:
                    Toast.makeText(SolucionDetallada.this, "Error de conexión con base de datos", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void SaveExcerciseGeneral()
    {
        try
        {
            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvin = myConn.getWritableDatabase();
            Integer _contador = 0, _idEjercicio = 0;
            _idEjercicio = ValidarIdEjercicios(myExerciseSolvented.getIdTema());
            String _msj = "";

            ContentValues _informacion = new ContentValues();
            _informacion.put(ScriptDB.CAMPO_IDEXERCISE,_idEjercicio);
            _informacion.put(ScriptDB.CAMPO_NAMETOPIC,myExerciseSolvented.getNombreTema());
            _informacion.put(ScriptDB.CAMPO_NAMECATEGORY,(myExerciseSolvented.getCategoriaTema()) == 1 ? "Números Complejos" : myExerciseSolvented.getCategoriaTema() == 2 ? "Matrices" : "Vectores");
            _informacion.put(ScriptDB.CAMPO_IDCATEGORY,myExerciseSolvented.getCategoriaTema());
            _informacion.put(ScriptDB.CAMPO_IDTOPIC,myExerciseSolvented.getIdTema());
            _informacion.put(ScriptDB.CAMPO_GRAPHIC,myExerciseSolvented.getGrafica() == true ? 1 : 0);
            _informacion.put(ScriptDB.CAMPO_PASO,myExerciseSolvented.getPasoAPaso() == true ? 1 : 0);
            Long _insertGeneral = mydbvin.insert(ScriptDB.TABLE_INFO_GENERAL,ScriptDB.CAMPO_IDREG,_informacion);

            int cant = CapturaDeDatos.myArrayDatos.size();
            int cant2 = CapturaDeDatos.myArrayDatosDatos.size();

            for (Double _dato : CapturaDeDatos.myArrayDatos) {
                ContentValues _datos = new ContentValues();

                _datos.put(ScriptDB.CAMPO_IDFK_EXERCISE,_idEjercicio);
                _datos.put(ScriptDB.CAMPO_VALOR,_dato);
                _datos.put(ScriptDB.CAMPO_POSICION,_contador);

                Long _insertdata = mydbvin.insert(ScriptDB.TABLE_DATOS,ScriptDB.CAMPO_IDDATO,_datos);
                _contador++;
            }
            _msj = _contador.equals(CapturaDeDatos.myArrayDatos.size()) ? "Ejercicio guardado..." : "¡Error de almacenamiento!" ;
            Toast.makeText(SolucionDetallada.this, _msj, Toast.LENGTH_LONG).show();
            CapturaDeDatos.myArrayDatos.clear();
            myConn.close();

        }
        catch(Exception  e) {
            e.getMessage().toString();
        }
    }

    private void getData() {
        //Obtencion del objeto como parametro
        Bundle _pObjeto = getIntent().getExtras();
        {
            mylistaPasosSolucion = new ArrayList<>();
            myExerciseSolvented = new csTemasContenido();
            if (_pObjeto != null) {
                mylistaPasosSolucion = (ArrayList<csDetailStep>) _pObjeto.getSerializable("KeyListaPasos");
                myExerciseSolvented = (csTemasContenido) _pObjeto.getSerializable("KeyExercise");
                Integer bloqueobtn = (Integer) _pObjeto.getSerializable("KeybtnSave");

                //Set datos
                lblNombreEjercicio.setText(myExerciseSolvented.getNombreTema());
                lblSolucionCategoria.setText(myExerciseSolvented.getCategoriaTema().equals(1)?"Números Complejos":
                                             myExerciseSolvented.getCategoriaTema().equals(2)?"Matrices":"Vectores");
               linearVisibilidadGrafica.setVisibility(myExerciseSolvented.getGrafica().equals(true) ? View.VISIBLE : View.GONE);
               btnGuardarEjercicio.setVisibility(bloqueobtn == 1 ? View.VISIBLE : View.GONE);
            }
        }
    }

    private Integer ValidarIdEjercicios(Integer _tema){
        Integer _id = 0;
        if (_tema == 201){//Gauss
            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvin = myConn.getReadableDatabase();

            Cursor _allCategorias = mydbvin.rawQuery("SELECT * FROM "+ScriptDB.tMATRIZ,null);
            ArrayList<csMatrizSQL> myListaEjercicios = new ArrayList<csMatrizSQL>();
            csMatrizSQL _ecuacion = new csMatrizSQL();
            while (_allCategorias.moveToNext()){
                _ecuacion.setIdEjercicio(_allCategorias.getInt(1));
                myListaEjercicios.add(_ecuacion);
            }
            _id = (myListaEjercicios.size() > 0) ? myListaEjercicios.get(myListaEjercicios.size() - 1).getIdEjercicio() + 1 : 1 ;
            myConn.close();
        }
        if (_tema == 302){//Vector Resultante
            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvin = myConn.getReadableDatabase();

            Cursor _allEjercicios = mydbvin.rawQuery("SELECT * FROM "+ScriptDB.tVECTORPROYECCION,null);
            ArrayList<csVectorProyeccionSQL> myListaEjercicios = new ArrayList<csVectorProyeccionSQL>();
            csVectorProyeccionSQL _ecuacion = new csVectorProyeccionSQL();
            while (_allEjercicios.moveToNext()){
                _ecuacion.setIdEjercicio(_allEjercicios.getInt(1));
                myListaEjercicios.add(_ecuacion);
            }
            _id = (myListaEjercicios.size() > 0) ? myListaEjercicios.get(myListaEjercicios.size() - 1).getIdEjercicio() + 1 : 1 ;
            myConn.close();
        }
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

    @Override
    public void onBackPressed() {
        CapturaDeDatos.myArrayDatos.clear();
        CapturaDeDatos.myArrayDatosDatos.clear();
        CapturaDeDatos.myMatrizGaussDatos.clear();
        CapturaDeDatos.myVectorProyeccionDatos.clear();
        super.onBackPressed();
    }
}