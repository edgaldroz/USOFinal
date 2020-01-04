package com.joabaler.vin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.joabaler.vin.Adaptadores.ExercisesAdapterRecycler;
import com.joabaler.vin.AlgebraLibrary.ExercisesSolotionsDetail;
import com.joabaler.vin.AlgebraLibrary.solucionDirecta;
import com.joabaler.vin.Entidades.csEjerciciosGuardados;
import com.joabaler.vin.Entidades.csMatriz;
import com.joabaler.vin.Entidades.csTemasContenido;
import com.joabaler.vin.Entidades.csVectorProyeccion;
import com.joabaler.vin.SQLite.ConexionSQLHelper;
import com.joabaler.vin.SQLite.ScriptDB;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //region Declaracion de variables
    solucionDirecta sdirecta = new solucionDirecta();
    private LinearLayout btnNumerosComplejos, btnMatrices, btnVectores,
            btnEjercicios, btnMostrarMenu,btnEliminarTodosEjercicios,
            viewMenuPrincipal,viewListaEjercicios;
    private TextView tvNumerosComplejos, tvMatrices, tvVectores;
    private RecyclerView myRecyclerViewExercise;
    private ExercisesAdapterRecycler myAdapterEjercicios;
    private ScrollView scrollMenu;
    private ArrayList<csEjerciciosGuardados> myListaEjerciciosGuardados;
    private Map<String, String> myDictionary;
    private Boolean myRefresh = true;
    private long backpressedtime;
    private Toast MessageClose;
    private Integer RequestCodeMain = 1;
    private csTemasContenido myExerciseSolvented = new csTemasContenido();
    ArrayList<csMatriz> myMatrizReMake = new ArrayList<>();
    ArrayList<csVectorProyeccion> myVectorProyeccionReMake = new ArrayList<>();
    ArrayList<Double> myDataValues = new ArrayList<>();

    //ArrayList<csCategorias> myListaCategorias;
    //ArrayList<csTemasContenidosql> myListaTemasContenido;
    //csCategorias myCategory;
    //csTemasContenidosql myTopicCategory;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); //ocultar la barra
        //EjecutarScriptDataBase();
        //ConsultarPersistencia();

        //region Inicializacion de variables
        btnNumerosComplejos = findViewById(R.id.btnMenuNumerosComplejos);
        btnMatrices = findViewById(R.id.btnMenuMatrices);
        btnVectores = findViewById(R.id.btnMenuVectores);
        btnEjercicios = findViewById(R.id.btnMenuEjercicios);
        btnMostrarMenu = findViewById(R.id.btnRegresarMenu);
        btnEliminarTodosEjercicios = findViewById(R.id.btnEliminarTodosEjercicios);
        viewMenuPrincipal = findViewById(R.id.viewMenuPrincipal);
        viewListaEjercicios = findViewById(R.id.viewListaEjercicios);
        tvNumerosComplejos = findViewById(R.id.lblMenuNumComplejos);
        tvMatrices = findViewById(R.id.lblMenuMatrices);
        tvVectores = findViewById(R.id.lblMenuVectores);
        scrollMenu = findViewById(R.id.scrollMenu);
        myRecyclerViewExercise = findViewById(R.id.lstEjerciciosGuardados);
        myRecyclerViewExercise.setLayoutManager(new GridLayoutManager(this,2));
        //endregion

        //region Eventos Click

        //region Click por categoria de algebra
        btnNumerosComplejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { AbrirDetalleCategoria(1); }
        });
        btnMatrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirDetalleCategoria(2);
            }
        });
        btnVectores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirDetalleCategoria(3);
            }
        });
        //endregion

        //region Ver Lista Ejercicios
        btnEjercicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myListaEjerciciosGuardados = new ArrayList<csEjerciciosGuardados>();
                getDataSQLite();
                if (myListaEjerciciosGuardados.size() > 0)
                {
                    AbrirEjerciciosGuardados();
                }else {
                    Toast.makeText(getApplicationContext(),"No hay ningún ejercicio guardado",Toast.LENGTH_LONG).show();
                }
            }
        });
        //endregion

        //region Ocultar Lista Ejercicios
        btnMostrarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewListaEjercicios.setVisibility(View.GONE);
                //viewMenuPrincipal.setVisibility(View.VISIBLE);
                btnEjercicios.setVisibility(View.VISIBLE);
            }
        });
        //endregion

        //region Eliminar Todos los ejercicios
        btnEliminarTodosEjercicios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                dialogo1.setTitle("Eliminar todos registros");

                String _detalleMensaje = myListaEjerciciosGuardados.size() > 1 ?"¿Desea eliminar los "+ myListaEjerciciosGuardados.size() +" ejercicios guardados?":"¿Desea eliminar el ejercicio guardado?";
                dialogo1.setMessage(_detalleMensaje);
                dialogo1.setCancelable(true);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                        ConexionSQLHelper myConn = new ConexionSQLHelper(MainActivity.this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                        SQLiteDatabase mydbvin = myConn.getReadableDatabase();
                        mydbvin.execSQL("DELETE FROM "+ScriptDB.tMATRIZ);
                        mydbvin.execSQL("DELETE FROM "+ScriptDB.tVECTORPROYECCION);
                        mydbvin.execSQL("DELETE FROM "+ScriptDB.TABLE_INFO_GENERAL);
                        mydbvin.execSQL("DELETE FROM "+ScriptDB.TABLE_DATOS);

                        myConn.close();
                        viewListaEjercicios.setVisibility(View.GONE);
                        btnEjercicios.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Los registros fueron eliminados",Toast.LENGTH_LONG).show();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        dialogo1.dismiss();
                    }
                });
                dialogo1.show();
            }
        });
        //endregion

        //endregion

        CreateDictionary();
        GenerarDetalleCategoria();
        RecargarMensajes();
        EstablecerAnimacion();
    }

    private void getDataSQLite() {
        try{
            getMatriz();
            getVector();
            getGeneral();
        }catch (Exception e){
            e.getMessage();
        }
    }

    private void getMatriz(){
        try{
            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvin = myConn.getReadableDatabase();

            //region Matriz de Gauss
            ArrayList<csEjerciciosGuardados> myListExercisedb = new ArrayList<csEjerciciosGuardados>();
            ArrayList<Integer> myIdsExercises = new ArrayList<>();
            Cursor _getMatrices = mydbvin.rawQuery("SELECT * FROM "+ScriptDB.tMATRIZ,null);
            int i = 0,ubicacion = 0;
            if (_getMatrices.getCount() > 0){
                while (_getMatrices.moveToNext())
                {
                    csEjerciciosGuardados _ejercicio = new csEjerciciosGuardados();
                    _ejercicio.setIdEjercicio(_getMatrices.getInt(1));
                    _ejercicio.setNombreEjercicio(_getMatrices.getString(9));
                    _ejercicio.setDescripcionEjercicio(_getMatrices.getString(10) +" "+_getMatrices.getString(8).toString()+"×"+_getMatrices.getString(8).toString());
                    _ejercicio.setIdTema(_getMatrices.getInt(11));
                    _ejercicio.setDetalleDatos("Primera ecuación del ejercicio: \n"+_getMatrices.getString(3)+"w "+_getMatrices.getString(4)+"x "+_getMatrices.getString(5)+"y "+_getMatrices.getString(6)+"z  = "+_getMatrices.getString(7));
                    myListExercisedb.add(_ejercicio);
                    //  myListaEjerciciosGuardados.add(_ejercicio);

                    //tomo los ubicacion inicial de los ejercicios distintos (ID)
                    if((ubicacion != _ejercicio.getIdEjercicio())){
                        ubicacion = _ejercicio.getIdEjercicio();
                        myIdsExercises.add(i);
                    }
                    i++;
                }
            }
            //Toast.makeText(MainActivity.this,i+ " ecuaciones ",Toast.LENGTH_LONG).show();
            //endregion

            //region set los datos en la lista de ejercicios guardados tomando los id de arriba (+1 para evitar el 0x0 de la matriz)
            //matriz gauss
            for (Integer _posicionM : myIdsExercises) {
                csEjerciciosGuardados _ejercicioguardado = new csEjerciciosGuardados();
                _ejercicioguardado = myListExercisedb.get(_posicionM + 1);
                myListaEjerciciosGuardados.add(_ejercicioguardado);
            }
            myConn.close();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    private void getVector(){
        try{
            //region Vector Proyeccion
            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvin = myConn.getReadableDatabase();
            ArrayList<csEjerciciosGuardados> myListExercisedbVP = new ArrayList<csEjerciciosGuardados>();
            ArrayList<Integer> myIdsExercisesVP = new ArrayList<>();
            Cursor _getVecotes = mydbvin.rawQuery("SELECT * FROM "+ScriptDB.tVECTORPROYECCION,null);
            int y = 0, ubicacion2 =0;

            if (_getVecotes.getCount() > 0){
                while (_getVecotes.moveToNext())
                {
                    csEjerciciosGuardados _ejercicio2 = new csEjerciciosGuardados();
                    _ejercicio2.setIdEjercicio(_getVecotes.getInt(1));
                    _ejercicio2.setNombreEjercicio(_getVecotes.getString(9));
                    _ejercicio2.setDescripcionEjercicio(_getVecotes.getString(10));
                    _ejercicio2.setIdTema(_getVecotes.getInt(11));
                    _ejercicio2.setDetalleDatos("Info del primer vector:\n Ubicado en el cuadrante "+_getVecotes.getString(3)+", posee dirección "+_getVecotes.getString(4)+", con una fuerza de "+_getVecotes.getString(6)+" N y un ángulo de "+_getVecotes.getString(7)+"° respecto al eje "+_getVecotes.getString(8));
                    myListExercisedbVP.add(_ejercicio2);
                    //    myListaEjerciciosGuardados.add(_ejercicio);

                    //tomo los ubicacion inicial de los ejercicios distintos (ID)
                    if((ubicacion2 != _ejercicio2.getIdEjercicio())){
                        ubicacion2 = _ejercicio2.getIdEjercicio();
                        myIdsExercisesVP.add(y);
                    }
                    y++;
                }
            }
            //endregion
            //vector proyeccion
            for (Integer _posicion : myIdsExercisesVP) {
                csEjerciciosGuardados _ejercicioguardado = new csEjerciciosGuardados();
                _ejercicioguardado = myListExercisedbVP.get(_posicion);
                myListaEjerciciosGuardados.add(_ejercicioguardado);
            }
            myConn.close();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    private void getGeneral(){
        try{
            ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
            SQLiteDatabase mydbvin = myConn.getReadableDatabase();
            ArrayList<csEjerciciosGuardados> myListGeneralesdb = new ArrayList<csEjerciciosGuardados>();

            Cursor _getGenerales = mydbvin.rawQuery("SELECT * FROM "+ScriptDB.TABLE_INFO_GENERAL,null);
            while (_getGenerales.moveToNext())
            {
                csEjerciciosGuardados _objGeneral = new csEjerciciosGuardados();
                _objGeneral.setIdEjercicio(_getGenerales.getInt(1));
                _objGeneral.setNombreEjercicio(_getGenerales.getString(2));
                _objGeneral.setDescripcionEjercicio(_getGenerales.getString(3));
                _objGeneral.setIdTema(_getGenerales.getInt(5));
                _objGeneral.setDetalleDatos(_getGenerales.getInt(7) == 1 ? "Su presentación es en modo detallado (paso por paso)" : "La respuesta se muestra de forma directa (Datos y resultado)");
                myListGeneralesdb.add(_objGeneral);
            }
            //endregion

            for (csEjerciciosGuardados general : myListGeneralesdb) {
                csEjerciciosGuardados _ejercicioguardado = new csEjerciciosGuardados();
                _ejercicioguardado = general;
                myListaEjerciciosGuardados.add(_ejercicioguardado);
            }
            //endregion

            myConn.close();
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    //region Estados de la app
    //Minimizar la app
    @Override
    protected void onPause() {
        super.onPause();
        myRefresh = false;
        RecargarMensajes();
    }

    //Reingresar a la app
    @Override
    protected void onResume() {
        super.onResume();
        myRefresh = true;
        RecargarMensajes();
    }
    //endregion

    //region Validar para salir de app
    @Override
    public void onBackPressed() {

        if (backpressedtime + 2000 > System.currentTimeMillis()){
            MessageClose.cancel();
            super.onBackPressed();
            return;
        }else {
            MessageClose = Toast.makeText(getApplicationContext(),"Presiona de nuevo para cerrar",Toast.LENGTH_SHORT);
            MessageClose.show();
        }
        backpressedtime = System.currentTimeMillis();
    }
    //endregion

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodeMain){
            if (resultCode == Activity.RESULT_OK){
                if (data.hasExtra("pageMain")){
                    scrollMenu.scrollTo(0,scrollMenu.getTop());
                }
            }
        }
    }

    //region Metodos y funciones
    private void AbrirEjerciciosGuardados() {
        try {

            if (myListaEjerciciosGuardados.size() > 0)
            {
                myAdapterEjercicios = new ExercisesAdapterRecycler(myListaEjerciciosGuardados);
                myRecyclerViewExercise.setAdapter(myAdapterEjercicios);
                //viewMenuPrincipal.setVisibility(View.GONE);
                btnEjercicios.setVisibility(View.GONE);
                viewListaEjercicios.setVisibility(View.VISIBLE);

                scrollMenu.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollMenu.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                },500);

            }else {
                Toast.makeText(getApplicationContext(),"No hay ningún ejercicio guardado",Toast.LENGTH_LONG).show();
            }

            //region Click Item Guardados
            myAdapterEjercicios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Integer _idTema = Integer.parseInt(myListaEjerciciosGuardados.
                            get(myRecyclerViewExercise.getChildAdapterPosition(view)).
                            getIdTema().toString());

                    final Integer _idEjercicio = Integer.parseInt(myListaEjerciciosGuardados.
                            get(myRecyclerViewExercise.getChildAdapterPosition(view)).
                            getIdEjercicio().toString());

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle(myListaEjerciciosGuardados.
                                    get(myRecyclerViewExercise.getChildAdapterPosition(view)).
                                    getNombreEjercicio().toString())
                            .setCancelable(true)
                            .setMessage("¿Que deseas hacer con el ejercicio de "+myListaEjerciciosGuardados.
                                    get(myRecyclerViewExercise.getChildAdapterPosition(view)).
                                    getDescripcionEjercicio().toString()+"? \n\nDetalle del problema algebráico:\n"+myListaEjerciciosGuardados.
                                    get(myRecyclerViewExercise.getChildAdapterPosition(view)).
                                    getDetalleDatos().toString())
                           // .setIcon(R.drawable.ic_logovin)
                            .setNeutralButton("Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                            .setNegativeButton("Eliminar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            //region Validadcion para eliminar
                                            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
                                            dialogo1.setTitle("Eliminar Ejercicio");
                                            dialogo1.setMessage("¿Desea eliminar el ejercicio guardado?");
                                            dialogo1.setCancelable(true);
                                            dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialogo1, int id) {

                                                    //region Eliminar ejercicio
                                                    Toast.makeText(getApplicationContext(),"Eliminar todos los registros del ejercicio cone ese ID. \nIdTema = "+_idTema, Toast.LENGTH_SHORT).show();
                                                    EliminarEjercicioSQLite(_idTema,_idEjercicio);
                                                    dialogo1.cancel();
                                                    Toast.makeText(getApplicationContext(),"Los registros fueron eliminados",Toast.LENGTH_LONG).show();
                                                    //endregion
                                                }
                                            });
                                            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialogo1, int id) {
                                                    dialogo1.dismiss();
                                                }
                                            });
                                            dialogo1.show();
                                            //endregion
                                        }
                                    })
                            .setPositiveButton("Ver Ejercicio",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            getEjercicioSQLite(_idTema,_idEjercicio);
                                            dialog.cancel();
                                        }
                                    })
                            .show();
                }
            });
            //endregion
        }
        catch(Exception  e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void getEjercicioSQLite(Integer idTema, Integer idEjercicio) {
        try
        {
            if (idTema == 201 ){
                //region Gauss
                    ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                    SQLiteDatabase mydbvin = myConn.getReadableDatabase();
                    String _mesj = "";

                    Cursor _getRemakeMatriz = mydbvin.rawQuery("SELECT * FROM "+ScriptDB.tMATRIZ +" WHERE "+ScriptDB.cMIdEjercicio +" = "+idEjercicio,null);
                    myMatrizReMake.clear();
                    while (_getRemakeMatriz.moveToNext())
                    {
                        csMatriz _ecuacion = new csMatriz();
                        _ecuacion.setIdEcuacion(_getRemakeMatriz.getInt(2));
                        _ecuacion.setW(_getRemakeMatriz.getDouble(3));
                        _ecuacion.setX(_getRemakeMatriz.getDouble(4));
                        _ecuacion.setY(_getRemakeMatriz.getDouble(5));
                        _ecuacion.setZ(_getRemakeMatriz.getDouble(6));
                        _ecuacion.setIgual(_getRemakeMatriz.getDouble(7));
                        _ecuacion.setTamano(_getRemakeMatriz.getInt(8));
                        myMatrizReMake.add(_ecuacion);

                        myExerciseSolvented.setCategoriaTema(2);
                        myExerciseSolvented.setGrafica(false);
                        myExerciseSolvented.setNombreTema(_getRemakeMatriz.getString(9));

                        _mesj += "IdEjercicio = "+_getRemakeMatriz.getInt(2)+ "  |  Tamaño = "+_getRemakeMatriz.getInt(8)+ "\n";
                    }
                    _mesj += "\nTotal "+myMatrizReMake.size();
                    myConn.close();
                    String _result = ExercisesSolotionsDetail.MatrizdeGauss(myMatrizReMake);
                    openActivity();
                //endregion
            }else if (idTema == 302){
                //region Vector Proyeccion
                    ConexionSQLHelper myConnVP = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                    SQLiteDatabase mydbvinVP = myConnVP.getReadableDatabase();
                    String _mesj2 = "";

                    Cursor _getRemakeVectorProyeccion = mydbvinVP.rawQuery("SELECT * FROM "+ScriptDB.tVECTORPROYECCION +" WHERE "+ScriptDB.cVPIdEjercicio +" = "+idEjercicio,null);
                    while (_getRemakeVectorProyeccion.moveToNext())
                    {
                        csVectorProyeccion _ecuacion = new csVectorProyeccion();
                        _ecuacion.setCuadrante(_getRemakeVectorProyeccion.getInt(3));
                        _ecuacion.setDireccion(_getRemakeVectorProyeccion.getString(4));
                        _ecuacion.setApunta(_getRemakeVectorProyeccion.getString(5));
                        _ecuacion.setFuerza(_getRemakeVectorProyeccion.getDouble(6));
                        _ecuacion.setAngulo(_getRemakeVectorProyeccion.getDouble(7));
                        _ecuacion.setAngRespectoA(_getRemakeVectorProyeccion.getString(8));
                        myVectorProyeccionReMake.add(_ecuacion);

                        myExerciseSolvented.setCategoriaTema(3);
                        myExerciseSolvented.setGrafica(false);
                        myExerciseSolvented.setNombreTema(_getRemakeVectorProyeccion.getString(9));
                    }
                    myConnVP.close();
                    String _resultVP = ExercisesSolotionsDetail.VectorProyeccion(myVectorProyeccionReMake);
                    openActivity();
                //endregion
            }else if (idTema != 201 && idTema != 302){
                //region Generales
                ConexionSQLHelper myConnVP = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                SQLiteDatabase mydbvinVP = myConnVP.getReadableDatabase();
                String _mesj2 = "";
                myDataValues.clear();

                //region Consulta de datos (valores de array)
                Cursor _getRemakeGenerales = mydbvinVP.rawQuery("SELECT * FROM "+ScriptDB.TABLE_DATOS +" WHERE "+ScriptDB.CAMPO_IDFK_EXERCISE +" = "+idEjercicio,null);
                while (_getRemakeGenerales.moveToNext())
                {
                    Double value = _getRemakeGenerales.getDouble(2);
                    myDataValues.add(value);
                }
                //endregion

                //region Consulta de datos (valores de array)
                Cursor _getInfoGenerales = mydbvinVP.rawQuery("SELECT * FROM "+ScriptDB.TABLE_INFO_GENERAL +" WHERE "+ScriptDB.CAMPO_IDEXERCISE +" = "+idEjercicio,null);
                while (_getInfoGenerales.moveToNext()){
                    myExerciseSolvented.setIdTema(_getInfoGenerales.getInt(5));
                    myExerciseSolvented.setCategoriaTema(_getInfoGenerales.getInt(4));
                    myExerciseSolvented.setGrafica((_getInfoGenerales.getInt(6) == 1) ? true : false);
                    myExerciseSolvented.setNombreTema(_getInfoGenerales.getString(2));
                    myExerciseSolvented.setPasoAPaso(_getInfoGenerales.getInt(7) == 1 ? true : false);
                }
                //endregion

                myConnVP.close();
                if (myExerciseSolvented.getPasoAPaso()){
                    String _resultVP = ExercisesSolotionsDetail.MenuMetodos(idTema,myDataValues);
                    openActivity();
                }else {
                    //codigo del gato
                    remakeDirect(myExerciseSolvented,myDataValues);
                }
                //endregion
            }
        }
        catch(Exception  e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void remakeDirect(csTemasContenido myExerciseSolvented, ArrayList<Double> myDataValues) {

        String result =  "";

        switch (myExerciseSolvented.getIdTema()){
            case 105:
                    String raizD="",raiz2D="";
                DecimalFormat formato = new DecimalFormat("#.##");
                    if (myDataValues.get(0).intValue()==1){
                        raizD="√";
                    }else {
                        raizD="";
                    }
                    if (myDataValues.get(3).intValue()==1){
                        raiz2D="√";
                    }else {
                        raiz2D="";
                    }

                    String z1d = raizD + formato.format(myDataValues.get(1)) + "cis" + formato.format(myDataValues.get(2));
                    String z2d = raiz2D + formato.format(myDataValues.get(4)) + "cis" + formato.format(myDataValues.get(5));

                    result = sdirecta.divisionPolares(z1d,z2d);
                break;
            case 106:
                result = sdirecta.divisionComplejos(myDataValues.get(0),
                                                    myDataValues.get(1),
                                                    myDataValues.get(2),
                                                    myDataValues.get(3)); //oooooo
                break;
            case 108:
                    String raiz="",raiz2="";
                    if (myDataValues.get(0).intValue()==1){
                        raiz="√";
                    }else {
                        raiz="";
                    }
                    if (myDataValues.get(3).intValue()==1){
                        raiz2="√";
                    }else {
                        raiz2="";
                    }

                    String z1 = raiz + myDataValues.get(1) + "cis" + myDataValues.get(2);
                    String z2 = raiz2 + myDataValues.get(4) + "cis" + myDataValues.get(5);

                    result = sdirecta.productoPolares(z1,z2);
                break;
            case 109:
                    String n = "√" + myDataValues.get(0).intValue();
                    result = sdirecta.raicesNegativas(n);
                break;
            case 110:
                    result = sdirecta.productoComplejos(myDataValues.get(0),
                                                        myDataValues.get(1),
                                                        myDataValues.get(2),
                                                        myDataValues.get(3));
                break;
            case 111:
                result = sdirecta.potenciasI(""+myDataValues.get(0).intValue());
                break;
            case 112:
                result = sdirecta.sumaComplejos(myDataValues.get(0),
                                                myDataValues.get(1),
                                                myDataValues.get(2),
                                                myDataValues.get(3),
                                                myDataValues.get(4),
                                                myDataValues.get(5));
                break;
            case 114:
                result = sdirecta.restaComplejos(myDataValues.get(0),
                                                myDataValues.get(1),
                                                myDataValues.get(2),
                                                myDataValues.get(3),
                                                myDataValues.get(4),
                                                myDataValues.get(5));
                break;
            case 115:
                result = sdirecta.rectangularAPolar(myDataValues.get(0),
                                                myDataValues.get(1));
                break;
            case 205:
                //region Matrices
                int fila = myDataValues.get(1).intValue();
                int columna = myDataValues.get(2).intValue();

                int fila2 = myDataValues.get(3).intValue();
                int columna2 = myDataValues.get(4).intValue();

                double matriz1[][] = new double[fila][columna],matriz2[][] = new double[fila][columna],matriz2p[][] = new double[fila2][columna2];

                if (myDataValues.get(0).intValue()==1){
                    int i = 3;
                    //Para llenar matriz 1
                        for (int x=0;x<fila;x++){
                            for (int y=0; y<columna;y++){
                                    double nM = myDataValues.get(i);
                                    matriz1[x][y]= nM;
                                    i++;
                            }
                        }
                     //Para matriz 2
                    for (int x=0;x<fila;x++){
                        for (int y=0; y<columna;y++){
                                matriz2[x][y]=myDataValues.get(i);
                                i++;

                        }
                    }
                    result = sdirecta.sumaMatrix(matriz1,matriz2);
                }else if(myDataValues.get(0).intValue()==2){
                    int i = 3;
                    //Para llenar matriz 1
                    for (int x=0;x<fila;x++){
                        for (int y=0; y<columna;y++){
                            double nM = myDataValues.get(i);
                            matriz1[x][y]= nM;
                            i++;
                        }
                    }
                    //Para matriz 2
                    for (int x=0;x<myDataValues.get(1).intValue();x++){
                        for (int y=0; y<myDataValues.get(2).intValue();y++){
                            matriz2[x][y]=myDataValues.get(i);
                            i++;

                        }
                    }
                    result = sdirecta.restaMatrix(matriz1,matriz2);
                }else if (myDataValues.get(0).intValue()==3){
                    int i = 3;
                    //Para llenar matriz 1
                    for (int x=0;x<fila;x++){
                        for (int y=0; y<columna;y++){
                            double nM = myDataValues.get(i);
                            matriz1[x][y]= nM;
                            i++;
                        }
                    }
                    result = sdirecta.determinante(matriz1);
                }else if (myDataValues.get(0).intValue()==4){
                //-----
                    int i = 5;
                    //Para llenar matriz 1
                    for (int x=0;x<fila;x++){
                        for (int y=0; y<columna;y++){
                            double nM = myDataValues.get(i);
                            matriz1[x][y]= nM;
                            i++;
                        }
                    }
                    //Para matriz 2
                    for (int x=0;x<fila2;x++){
                        for (int y=0; y<columna2;y++){
                            matriz2p[x][y]=myDataValues.get(i);
                            i++;

                        }
                    }
                    result = sdirecta.productMatrix(matriz1,matriz2p);
                }else if (myDataValues.get(0).intValue()==5){
                    int i = 4;
                    double escalar = myDataValues.get(3);
                    //Para llenar matriz 1
                    for (int x=0;x<fila;x++){
                        for (int y=0; y<columna;y++){
                            double nM = myDataValues.get(i);
                            matriz1[x][y]= nM;
                            i++;
                        }
                    }
                    result = sdirecta.productoMEscalar(matriz1,escalar);
                }
                //endregion
                break;
            case 305:
                String v="", w="";
                DecimalFormat forma = new DecimalFormat("#.##");
                if (myDataValues.get(1)<0 && myDataValues.get(2)<0){
                    v = forma.format(myDataValues.get(0))+"i"+forma.format(myDataValues.get(1))+"j"+forma.format(myDataValues.get(2))+"k";
                }else if (myDataValues.get(1)<0 && myDataValues.get(2).intValue()>=0){
                    v = forma.format(myDataValues.get(0))+"i"+forma.format(myDataValues.get(1))+"j"+"+"+forma.format(myDataValues.get(2))+"k";
                }else if (myDataValues.get(1)>=0 && myDataValues.get(2)<0){
                    v = forma.format(myDataValues.get(0))+"i"+"+"+forma.format(myDataValues.get(1))+"j"+forma.format(myDataValues.get(2))+"k";
                }else if (myDataValues.get(1)>=0 && myDataValues.get(2)>=0){
                    v = forma.format(myDataValues.get(0))+"i"+"+"+forma.format(myDataValues.get(1))+"j"+"+"+forma.format(myDataValues.get(2))+"k";
                }

                if (myDataValues.get(4)<0 && myDataValues.get(5)<0){
                    w = forma.format(myDataValues.get(3))+"i"+forma.format(myDataValues.get(4))+"j"+forma.format(myDataValues.get(5))+"k";
                }else if (myDataValues.get(4)<0 && myDataValues.get(5)>=0){
                    w = forma.format(myDataValues.get(3))+"i"+forma.format(myDataValues.get(4))+"j"+"+"+forma.format(myDataValues.get(5))+"k";
                }else if (myDataValues.get(4)>=0 && myDataValues.get(5)<0){
                    w = forma.format(myDataValues.get(3))+"i"+"+"+forma.format(myDataValues.get(4))+"j"+forma.format(myDataValues.get(5))+"k";
                }else if (myDataValues.get(4)>=0 && myDataValues.get(5)>=0){
                    w = forma.format(myDataValues.get(3))+"i"+"+"+forma.format(myDataValues.get(4))+"j"+"+"+forma.format(myDataValues.get(5))+"k";
                }

                result = sdirecta.productoPuntoVector(v,w);
                break;
            case 307:
                //region Suma. Vector Resultante
                String vv="", ww="";
                double n1 = myDataValues.get(0);
                if (myDataValues.get(2)<0 && myDataValues.get(3)<0){
                    vv = myDataValues.get(1)+"i"+myDataValues.get(2)+"j"+myDataValues.get(3)+"k";
                }else if (myDataValues.get(2)<0 && myDataValues.get(3)>=0){
                    vv = myDataValues.get(1)+"i"+myDataValues.get(2)+"j"+"+"+myDataValues.get(3)+"k";
                }else if (myDataValues.get(2)>=0 && myDataValues.get(3)<0){
                    vv = myDataValues.get(1)+"i"+"+"+myDataValues.get(2)+"j"+myDataValues.get(3)+"k";
                }else if (myDataValues.get(2)>=0 && myDataValues.get(3)>=0){
                    vv = myDataValues.get(1)+"i"+"+"+myDataValues.get(2)+"j"+"+"+myDataValues.get(3)+"k";
                }


                double n2 = myDataValues.get(4);
                if (myDataValues.get(6)<0 && myDataValues.get(7)<0){
                    ww = myDataValues.get(5)+"i"+myDataValues.get(6)+"j"+myDataValues.get(7)+"k";
                }else if (myDataValues.get(6)<0 && myDataValues.get(7)>=0){
                    ww = myDataValues.get(5)+"i"+myDataValues.get(6)+"j"+"+"+myDataValues.get(7)+"k";
                }else if (myDataValues.get(6)>=0 && myDataValues.get(7)<0){
                    ww = myDataValues.get(5)+"i"+"+"+myDataValues.get(6)+"j"+myDataValues.get(7)+"k";
                }else if (myDataValues.get(6)>=0 && myDataValues.get(7)>=0){
                    ww = myDataValues.get(5)+"i"+"+"+myDataValues.get(6)+"j"+"+"+myDataValues.get(7)+"k";
                }
                result = sdirecta.sumaVectorResultante(n1,vv,n2,ww);
                //endregion
                break;
            case 308:
                //region Resta. vector Resultante
                String vvv="", www="";
                double nn1 = myDataValues.get(0);
                if (myDataValues.get(2)<0 && myDataValues.get(3)<0){
                    vvv = myDataValues.get(1)+"i"+myDataValues.get(2)+"j"+myDataValues.get(3)+"k";
                }else if (myDataValues.get(2)<0 && myDataValues.get(3)>=0){
                    vvv = myDataValues.get(1)+"i"+myDataValues.get(2)+"j"+"+"+myDataValues.get(3)+"k";
                }else if (myDataValues.get(2)>=0 && myDataValues.get(3)<0){
                    vvv = myDataValues.get(1)+"i"+"+"+myDataValues.get(2)+"j"+myDataValues.get(3)+"k";
                }else if (myDataValues.get(2)>=0 && myDataValues.get(3)>=0){
                    vvv = myDataValues.get(1)+"i"+"+"+myDataValues.get(2)+"j"+"+"+myDataValues.get(3)+"k";
                }


                double nn2 = myDataValues.get(4);
                if (myDataValues.get(6)<0 && myDataValues.get(7)<0){
                    www = myDataValues.get(5)+"i"+myDataValues.get(6)+"j"+myDataValues.get(7)+"k";
                }else if (myDataValues.get(6)<0 && myDataValues.get(7)>=0){
                    www = myDataValues.get(5)+"i"+myDataValues.get(6)+"j"+"+"+myDataValues.get(7)+"k";
                }else if (myDataValues.get(6)>=0 && myDataValues.get(7)<0){
                    www = myDataValues.get(5)+"i"+"+"+myDataValues.get(6)+"j"+myDataValues.get(7)+"k";
                }else if (myDataValues.get(6)>=0 && myDataValues.get(7)>=0){
                    www = myDataValues.get(5)+"i"+"+"+myDataValues.get(6)+"j"+"+"+myDataValues.get(7)+"k";
                }
                result = sdirecta.restaVectorResultante(nn1,vvv,nn2,www);
                //endregion
                break;
            case 309:
                //region Vector Unitario
                String k="";
                if (myDataValues.get(1)<0 && myDataValues.get(2)<0){
                    k = myDataValues.get(0)+"i"+myDataValues.get(1)+"j"+myDataValues.get(2)+"k";
                }else if (myDataValues.get(1)<0 && myDataValues.get(2)>=0){
                    k = myDataValues.get(0)+"i"+myDataValues.get(1)+"j"+"+"+myDataValues.get(2)+"k";
                }else if (myDataValues.get(1)>=0 && myDataValues.get(2)<0){
                    k = myDataValues.get(0)+"i"+"+"+myDataValues.get(1)+"j"+myDataValues.get(2)+"k";
                }else if (myDataValues.get(1)>=0 && myDataValues.get(2)>=0){
                    k = myDataValues.get(0)+"i"+"+"+myDataValues.get(1)+"j"+"+"+myDataValues.get(2)+"k";
                }
                result = sdirecta.vecRepresentacionUni(k);
                //endregion
                break;
            case 310:
                //region Angulo entre vectores
                String kv="", km="";
                if (myDataValues.get(1)<0 && myDataValues.get(2)<0){
                    kv = myDataValues.get(0)+"i"+myDataValues.get(1)+"j"+myDataValues.get(2)+"k";
                }else if (myDataValues.get(1)<0 && myDataValues.get(2)>=0){
                    kv = myDataValues.get(0)+"i"+myDataValues.get(1)+"j"+"+"+myDataValues.get(2)+"k";
                }else if (myDataValues.get(1)>=0 && myDataValues.get(2)<0){
                    kv = myDataValues.get(0)+"i"+"+"+myDataValues.get(1)+"j"+myDataValues.get(2)+"k";
                }else if (myDataValues.get(1)>=0 && myDataValues.get(2)>=0){
                    kv = myDataValues.get(0)+"i"+"+"+myDataValues.get(1)+"j"+"+"+myDataValues.get(2)+"k";
                }

                if (myDataValues.get(4)<0 && myDataValues.get(5)<0){
                    km = myDataValues.get(3)+"i"+myDataValues.get(4)+"j"+myDataValues.get(5)+"k";
                }else if (myDataValues.get(4)<0 && myDataValues.get(5)>=0){
                    km = myDataValues.get(3)+"i"+myDataValues.get(4)+"j"+"+"+myDataValues.get(5)+"k";
                }else if (myDataValues.get(4)>=0 && myDataValues.get(5)<0){
                    km = myDataValues.get(3)+"i"+"+"+myDataValues.get(4)+"j"+myDataValues.get(5)+"k";
                }else if (myDataValues.get(4)>=0 && myDataValues.get(5)>=0){
                    km = myDataValues.get(3)+"i"+"+"+myDataValues.get(4)+"j"+"+"+myDataValues.get(5)+"k";
                }

                result = sdirecta.anguloEntreVectores(kv,km);
                //endregion
                break;

        }


        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
        dialogo1.setTitle(myExerciseSolvented.getNombreTema());
        dialogo1.setMessage("Ejercicio: \n"+result);
        dialogo1.setCancelable(true);
        dialogo1.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.dismiss();
            }
        });
        dialogo1.show();
    }

    private void EliminarEjercicioSQLite(Integer _idTema,Integer _idEjercicio){

        try{

            if (_idTema == 201) {
                //region Matriz Gauss

                    ConexionSQLHelper myConn = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                    SQLiteDatabase mydbvin = myConn.getReadableDatabase();
                    String _mesj = "";

                    ArrayList<Integer> myIdToDelete = new ArrayList<>();
                    Cursor _getIdsDelete = mydbvin.rawQuery("SELECT * FROM "+ScriptDB.tMATRIZ +" WHERE "+ScriptDB.cMIdEjercicio +" = "+_idEjercicio,null);
                    while (_getIdsDelete.moveToNext())
                    {
                        myIdToDelete.add(_getIdsDelete.getInt(0));
                        _mesj += _getIdsDelete.getInt(0)+", ";
                    }

                    for (Integer _idregistro : myIdToDelete) {
                        mydbvin.execSQL("DELETE FROM "+ScriptDB.tMATRIZ+" WHERE "+ScriptDB.cMIdRegistro+" = "+_idregistro);
                    }
                    myConn.close();
                    //getDataSQLite();
                    viewListaEjercicios.setVisibility(View.GONE);
                    btnEjercicios.setVisibility(View.VISIBLE);

                //endregion
            }else if (_idTema == 302){
                //region Vector
                ConexionSQLHelper myConnVP = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                SQLiteDatabase mydbvinVP = myConnVP.getReadableDatabase();
                String _mesj2 = "";

                ArrayList<Integer> myIdToDeleteVP = new ArrayList<>();
                Cursor _getIdsDeleteVP = mydbvinVP.rawQuery("SELECT * FROM "+ScriptDB.tVECTORPROYECCION +" WHERE "+ScriptDB.cVPIdEjercicio +" = "+_idEjercicio,null);
                while (_getIdsDeleteVP.moveToNext())
                {
                    myIdToDeleteVP.add(_getIdsDeleteVP.getInt(0));
                    _mesj2 += _getIdsDeleteVP.getInt(0)+", ";
                }

                for (Integer _idregistro : myIdToDeleteVP) {
                    mydbvinVP.execSQL("DELETE FROM "+ScriptDB.tVECTORPROYECCION+" WHERE "+ ScriptDB.cVPIdRegistro +" = "+_idregistro);
                }
                myConnVP.close();
                //getDataSQLite();
                viewListaEjercicios.setVisibility(View.GONE);
                btnEjercicios.setVisibility(View.VISIBLE);

                //endregion
            }else if (_idTema != 201 && _idTema != 302){
                //region Generales
                ConexionSQLHelper myConnVP = new ConexionSQLHelper(this, ScriptDB.nameDataBase, null, ScriptDB.numVersionDataBase);
                SQLiteDatabase myDBVinGeneral = myConnVP.getReadableDatabase();
                String _mesj2 = "";

                //region Borrar Datos (Valores Array)
                ArrayList<Integer> myIDsDeleteData = new ArrayList<>();
                Cursor _getDeleteData = myDBVinGeneral.rawQuery("SELECT * FROM "+ScriptDB.TABLE_DATOS +" WHERE " + ScriptDB.CAMPO_IDFK_EXERCISE +" = "+_idEjercicio,null);
                while (_getDeleteData.moveToNext())
                {
                    myIDsDeleteData.add(_getDeleteData.getInt(0));
                    _mesj2 += _getDeleteData.getInt(0)+", ";
                }

                for (Integer _idregistro : myIDsDeleteData) {
                    myDBVinGeneral.execSQL("DELETE FROM "+ScriptDB.TABLE_DATOS+" WHERE "+ ScriptDB.CAMPO_IDDATO +" = "+_idregistro);
                }
                //endregion

                //region Borrar Datos (Inforamcion General)
                ArrayList<Integer> myIDsDeleteGeneral = new ArrayList<>();
                Cursor _getDeleteGeneral = myDBVinGeneral.rawQuery("SELECT * FROM "+ScriptDB.TABLE_INFO_GENERAL +" WHERE " + ScriptDB.CAMPO_IDEXERCISE +" = "+_idEjercicio,null);
                while (_getDeleteGeneral.moveToNext())
                {
                    int idreg =  _getDeleteGeneral.getInt(0);
                    int ideje =  _getDeleteGeneral.getInt(1);
                    myIDsDeleteGeneral.add(_getDeleteGeneral.getInt(0));
                }
                for (Integer _id : myIDsDeleteGeneral) {
                    myDBVinGeneral.execSQL("DELETE FROM " + ScriptDB.TABLE_INFO_GENERAL + " WHERE " + ScriptDB.CAMPO_IDREG  + " = " + _id );
                }
                //endregion
                myConnVP.close();

                viewListaEjercicios.setVisibility(View.GONE);
                btnEjercicios.setVisibility(View.VISIBLE);

                //endregion
            }
        }
        catch(Exception  e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    private void AbrirDetalleCategoria(Integer _pCategoria){

        try {
            Intent _OpenPage = new Intent(getApplicationContext(), DetalleCategoria.class);
            _OpenPage.putExtra("KeyCategory",_pCategoria);
            startActivityForResult(_OpenPage,RequestCodeMain);
            viewListaEjercicios.setVisibility(View.GONE);
            btnEjercicios.setVisibility(View.VISIBLE);
        }
        catch(Exception  e) {
            Toast.makeText(getApplicationContext(),"Seleccione una categoria para ver su detalle",Toast.LENGTH_LONG).show();
        }
    }

    private void CreateDictionary(){

        myDictionary = new HashMap<String, String>();
        myDictionary.put("1", "Se aplican en electricidad y diseño de circuitos eléctricos por ejemplo amplificadores (aparatos electrónicos)");
        myDictionary.put("2", "Se aplican en señales electrónicas como por ejemplo la trasmisión en banda ancha o amplificador de señales");
        myDictionary.put("3", "Se aplican en la distribución de temperatura en cuerpos metálicos (las estufas eléctricas y planchas de ropa)");
        myDictionary.put("4", "En la medicina están presentes en el instrumento de electrocardiograma utilizado para monitorear el ritmo cardíaco");
        myDictionary.put("5", "Se aplica en la corriente alterna para poder ser utilizadas por electrodomésticos de casa como los televisores");

        myDictionary.put("6", "La ingeniería civil las utiliza para diseñar puentes, vías y cansadas, entre otras");
        myDictionary.put("7", "La ingeniería robótica la utiliza para calcular los movimientos ej.: brazo mecánico");
        myDictionary.put("8", "En la administración se utiliza para hacer análisis conocido como FODA");
        myDictionary.put("9", "En informática se emplean en la compresión de imágenes y cifrado de las mismas");
        myDictionary.put("10", "Para cifrar y descifrar información (multi. por una matriz y su inversa respec.)");

        myDictionary.put("11", "Se manifiesta en la vida común al levantar un objeto pesado, jugar billar, nadar, conducir o arrojar una piedra");
        myDictionary.put("12", "Se manifiesta haciendo deporte como patear un penalti, encestar en basquetbol o lanzar una bola de béisbol");
        myDictionary.put("13", "Por las mañanas convivimos con ellos en la ducha, las gotas de agua caen en ciertas direcciones");
        myDictionary.put("14", "Sin darnos cuenta al seguir una ruta hacia un lugar o simplemente al caminar estamos siendo vectores");
        myDictionary.put("15", "La rama de la Física, la Dinámica se dedica al estudio de la relación que existe entre la fuerza y el movimiento");
    }

    private void GenerarDetalleCategoria(){
        ArrayList<Integer> _numGenerados = new ArrayList<>();
        Random rd = new Random();
        _numGenerados.add(1 + rd.nextInt((5+1) - 1));
        _numGenerados.add(6 + rd.nextInt((10+1) - 6));
        _numGenerados.add(11 + rd.nextInt((15+1) - 11));

        //Set de los TextView
        tvNumerosComplejos.setText(myDictionary.get(_numGenerados.get(0).toString()));
        tvMatrices.setText(myDictionary.get(_numGenerados.get(1).toString()));
        tvVectores.setText(myDictionary.get(_numGenerados.get(2).toString()));
    }

    private void RecargarMensajes(){
        if (myRefresh.equals(true)){
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    GenerarDetalleCategoria();
                    EstablecerAnimacion();
                    RecargarMensajes();
                }
            }, 8000);
        }
    }

    private void EstablecerAnimacion(){
        Animation alpha_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha_in);
        btnNumerosComplejos.startAnimation(alpha_in);
        btnMatrices.startAnimation(alpha_in);
        btnVectores.startAnimation(alpha_in);
    }

    private void openActivity() {
        Intent _OpenPaso = new Intent(getApplicationContext(), SolucionDetallada.class);
        Bundle _bundle = new Bundle();
        _bundle.putSerializable("KeyListaPasos", ExercisesSolotionsDetail.myListSteps);
        _bundle.putSerializable("KeyExercise", myExerciseSolvented);
        _bundle.putSerializable("KeybtnSave", 0);
        _OpenPaso.putExtras(_bundle);
        startActivity(_OpenPaso);
        ExercisesSolotionsDetail.myListSteps.clear();
    }
    //endregion
}
