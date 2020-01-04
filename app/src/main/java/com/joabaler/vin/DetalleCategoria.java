package com.joabaler.vin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joabaler.vin.Adaptadores.DetailCategoryAdapterRecycler;
import com.joabaler.vin.Entidades.csTemasContenido;

import java.util.ArrayList;

public class DetalleCategoria extends AppCompatActivity {

    private RecyclerView myRecyclerViewDetailTopics;
    private TextView txtNumUnidad,txtNombreCategoria,txtDetalleCategoria;
    private ArrayList<csTemasContenido> myDataBaseTopics;
    private ArrayList<csTemasContenido> myListTopics;
    private DetailCategoryAdapterRecycler myAdaptador;
    private Integer RequestCodeCategory = 2;
    private AlertDialog myAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_categoria);

        getSupportActionBar().hide(); //ocultar la barra

        txtNumUnidad = findViewById(R.id.lblUnidadCategoria);
        txtNombreCategoria= findViewById(R.id.lblTituloCategoria);
        txtDetalleCategoria= findViewById(R.id.lblDetalleTipoCategoria);
        myRecyclerViewDetailTopics = findViewById(R.id.lstContenido);
        myRecyclerViewDetailTopics.setLayoutManager(new LinearLayoutManager(this));

        getParameter();

        myAdaptador = new DetailCategoryAdapterRecycler(myListTopics);
        myRecyclerViewDetailTopics.setAdapter(myAdaptador);

        //Selecciona un item de la lista y muestra el dialog
        myAdaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final csTemasContenido _objSelccionado = myListTopics.get(myRecyclerViewDetailTopics.getChildAdapterPosition(view));

                //Creacion del cuadro de dialogo
                AlertDialog.Builder myDialogInformation = new AlertDialog.Builder(DetalleCategoria.this);

                View myViewDialog = getLayoutInflater().inflate(R.layout.dialog_tema,null);

                //Creacion de variables finales
                final TextView tvTitulo = (TextView) myViewDialog.findViewById(R.id.lblTemaDialog);
                final TextView tvDescripcion = (TextView) myViewDialog.findViewById(R.id.lblDescripcionDialog);
                final LinearLayout btnCerrar = (LinearLayout) myViewDialog.findViewById(R.id.btnDialogCerrar);
                final LinearLayout btnResolver = (LinearLayout) myViewDialog.findViewById(R.id.btnDialogResolver);

                //Establecimiento de datos
                tvTitulo.setText(_objSelccionado.getNombreTema());
                tvDescripcion.setText(_objSelccionado.getDetalleTema());
                btnCerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myAlertDialog.dismiss();
                    }
                });

                btnResolver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myAlertDialog.dismiss();
                        Intent _openCaptura = new Intent(getApplicationContext(),_objSelccionado.getPasoAPaso() ? CapturaDeDatos.class : captura_de_datos_directa.class);
                        //put extra objeto del ejercico
                        Bundle _bundle = new Bundle();
                        _bundle.putSerializable("KeyExercise", _objSelccionado);
                        _openCaptura.putExtras(_bundle);
                        startActivityForResult(_openCaptura,RequestCodeCategory);
                    }
                });

                myDialogInformation.setView(myViewDialog);
                myAlertDialog = myDialogInformation.create();
                myAlertDialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodeCategory){
            if (resultCode == Activity.RESULT_OK){
                if (data.hasExtra("categoria")){
                    Intent _intentCategoria = new Intent();
                    _intentCategoria.putExtra("pageMain",true);
                    setResult(Activity.RESULT_OK,_intentCategoria);
                    finish();
                }
            }
        }
    }

    private void getParameter() {
        Bundle _extras = getIntent().getExtras();//recupera el parametro de la categoria selccionada
        if (_extras != null){
            Integer myPCategory;
            myPCategory = (Integer) _extras.getSerializable("KeyCategory");
            getListTopics(myPCategory);//Filtra la lista segun la categoria
            txtNumUnidad.setText( (myPCategory == 1) ? "Unidad I" : (myPCategory == 2) ? "Unidad II" : "Unidad III" );
            txtNombreCategoria.setText( (myPCategory == 1) ? "Números Complejos" : (myPCategory == 2) ? "Matrices" : "Vectores");
            txtDetalleCategoria.setText( (myPCategory == 1) ? "\"Conformar un grupo de cifras resultantes de la suma entre un número real y uno de tipo imaginario\"" :
                                         (myPCategory == 2) ? "\"Es un arreglo bidimensional de números consistente en cantidades abstractas que pueden sumarse y multiplicarse entre sí\"" :
                                                              "\"Es todo segmento de recta que está dirigido en el espacio definidos por los componentes de módulo, dirección, sentido y punto de aplicación\"");
        }
    }

    void getDataBase(){
        myDataBaseTopics = new ArrayList<csTemasContenido>();
        //Complejos
        myDataBaseTopics.add(new csTemasContenido(101,1,"Representación exponencial de números complejos","Una variante de la forma polar se obtiene al tener en cuenta la conocida como fórmula de Euler: e = cos θ + sen θ",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(102,1,"Raíces de números complejos","Sean nn un número natural y z un complejo, siendo |z| y θ el módulo y el argumento de z, respectivamente. Las raíces n-ésimas de z (o raíces de orden n) ",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(103,1,"Operaciones con polares: potencia","Para elevar a una potencia un número complejo en forma polar se eleva el módulo al exponente y se multiplica el argumento por el exponente.",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(104,1,"Representación polar  *a exponencial *de complejos","Se puede representar un número complejo cualquiera (z = a +bi ) en forma polar, dando su módulo y su argumento. Esta forma también se llama forma trigonométrica.",R.drawable.ic_matriz,true,false));

        myDataBaseTopics.add(new csTemasContenido(105,1,"Operaciones con polares: División","Para dividir números complejos, basta dividir sus módulos y restar sus argumentos",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(106,1,"Operaciones con números complejos: División","Para dividir dos complejos, se multiplica el dividendo y el divisor por el conjugado de éste, así el divisor pasará a ser un número real",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(110,1,"Operaciones con números complejos: Producto","Formalmente presentamos la definición de la multiplicación de dos números complejos.\n(a+bi)⋅(c+di) = (ac−bd)+(ad+bc)i",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(112,1,"Operaciones con números complejos: Suma","Descipcion por defecto",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(114,1,"Operaciones con números complejos: Resta","Descipcion por defecto",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(111,1,"Números Imaginarios: Potencias de “i”","Sea z=(a +bi) un número complejo cualquiera. Llamaremos módulo del número complejo z, al número real dado por",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(115,1,"Rectangular a Polar","Calcula la forma polar equivalente de un número en forma rectangular.",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(108,1,"Operaciones con polares: Producto"," Multiplicación, primero se multiplican los módulos y los ángulos se sumarán: \nZt=Z1*Z2 = Modulo  \n Zt = |Z1| * |Z2| \n  Argumento θT = θ1 + θ2",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(109,1,"Raíces de números negativos","Si el contenido de la raíz o radicando es positivo, la raíz tendrá dos soluciones una positiva y otra negativa, pero si el contenido de la raíz es negativo, la raíz no tendrá solución real.",R.drawable.ic_matriz,false,false));
        //Incompleto----2--
        //myDataBaseTopics.add(new csTemasContenido(107,1,"Números complejos. Representación. Opuesto y conjugado","Los números complejos expresan la suma entre un número real y un número imaginario. Un número real es aquel que puede ser expresado por un número entero.",R.drawable.ic_matriz,false,false));
        //myDataBaseTopics.add(new csTemasContenido(113,1,"Números reales","Este conjunto está integrado por todos los números que podemos representar sobre una recta numérica, por esta razón a la recta numérica también se le conoce como Recta Real o también  Recta de los Números Reales.",R.drawable.ic_matriz,false,false));

        //matrices
        myDataBaseTopics.add(new csTemasContenido(201,2,"Métodos de Gauss","Este método consiste en colocar junto a la matriz de partida (A) la matriz identidad (I) y hacer operaciones por filas, afectando esas operaciones tanto a A como a I, con el objeto de transformar la matriz A en la matriz identidad, la matriz resultante de las operaciones sobre I es la inversa de A (A-1)",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(202,2,"Matriz inversa","La inversa del producto de dos matrices es el producto de las inversas cambiando el orden:\n(A.B)^(-1)=B^(-1).A",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(203,2,"Aplicaciones de Sistemas de Ecuaciones Lineales","Es un conjunto de ecuaciones lineales (es decir, un sistema de ecuaciones en donde cada ecuación es de primer grado), definidas sobre un cuerpo o un anillo conmutativo.",R.drawable.ic_matriz,true,false));
//        myDataBaseTopics.add(new csTemasContenido(204,2,"Solución de sistemas de ecuaciones lineales","Los sistemas de ecuaciones se clasifican o se distinguen según el número de soluciones que tengan",R.drawable.ic_matriz,true,false));

        myDataBaseTopics.add(new csTemasContenido(205,2,"Operaciones con matrices","Suma, resta, multiplicación, producto por un escalar y determinante",R.drawable.ic_matriz,false,false));

        //vectores
        myDataBaseTopics.add(new csTemasContenido(305,3,"Producto interno o producto escalar.","El producto de un escalar por un vector da por resultado otro vector, con la misma dirección que el primero. Al hacer la multiplicación, el escalar cambia el módulo del vector (gráficamente el largo) y en caso de ser negativo cambia también el sentido.",R.drawable.ic_matriz,false,false));


        myDataBaseTopics.add(new csTemasContenido(301,3,"Fuerza Resultante en el espacio - Proyección de un vector sobre otro. Vector proyección","Una fuerza F en un espacio tridimensional se puede descomponer en componentes rectangulares Fx, Fy y Fz",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(302,3,"VectorProyeccion - Proyección de un vector sobre otro. Vector proyección","Para obtener el vector proyección vamos a multiplicar el módulo del vector proyección por un vector unitario que tenga la misma dirección que tiene el vector proyección.",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(303,3,"Producto mixto o triple producto escalar. Definición y propiedades","Sean A = (Ax, Ay, Az), B = (Bx, By, Bz) y C = (Cx, Cy, Cz) la operación entre estos tres vectores que combina el producto escalar con el producto vectorial, se le denomina producto escalar triple o producto mixto",R.drawable.ic_matriz,true,false));
        myDataBaseTopics.add(new csTemasContenido(304,3,"Producto vectorial de dos vectores en R3. Definición y propiedades","si A = (a1, a2, a3) y B = (b1, b2, b3) son vectores, entonces el producto vectorial de A y B, al que denotaremos como AxB, es:\nAxB = (a2b3 – a3b2 , a3b1 – a1b3 , a1b2 – a2b1)\n",R.drawable.ic_matriz,true,false));

        myDataBaseTopics.add(new csTemasContenido(307,3,"Suma. Vector resultante","Se define el vector suma de ambos (w) a otro vector cuyas componentes se calculan sumando las componentes de cada uno de ellos",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(308,3,"Resta. Vector resultante","Se define el vector Resta de ambos (w) a otro vector cuyas componentes se calculan sumando las componentes de cada uno de ellos",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(309,3,"Vector Unitario","Un vector unitario es aquél que tiene módulo 1. Para hallar un vector unitario a partir de cualquier vector, hay que dividir este último por su módulo.",R.drawable.ic_matriz,false,false));
        myDataBaseTopics.add(new csTemasContenido(310,3,"Angulo entre vectores.","θab = cos^-1((a.b)/(ab))",R.drawable.ic_matriz,false,false));
    }

    void getListTopics(Integer _pCategorySelected) {
        getDataBase();
        myListTopics = new ArrayList<csTemasContenido>();
        for (csTemasContenido _item : myDataBaseTopics) {

            if (_item.getCategoriaTema() == _pCategorySelected){
                myListTopics.add(_item);
            }
        }
    }
}
