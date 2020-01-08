package com.joabaler.vin.AlgebraLibrary;

import com.joabaler.vin.Entidades.csDetailStep;
import com.joabaler.vin.Entidades.csDiccionario;
import com.joabaler.vin.Entidades.csMatriz;
import com.joabaler.vin.Entidades.csVectorProyeccion;

import java.util.ArrayList;

public class ExercisesSolotionsDetail {
        /*
        myListSteps.add(new csDetailStep(#,
                                         "",
                                         "",
                                         ""));

        ( numero > 0)? "+ ":"" //validar los signos
    */
    public static ArrayList<csDetailStep> myListSteps = new ArrayList<>();

    public static String MenuMetodos(Integer pTema, ArrayList<Double> _pDatos){

        try{
            switch (pTema){

                case 101:
                    return OperacionesRepresentacionExponencial(_pDatos);
                case 102:
                    return RaizNumerosPolares(_pDatos);
                case 103:
                    return OperacionPolarPotencia(_pDatos);
                case 104:
                    return PolarAExponencialNC(_pDatos);


                case 202:
                    return MatrizInverza(_pDatos);
                case 204:
                    return MatrizInverza(_pDatos);


                case 301:
                    return FuerzaResultanteEnElEspacio(_pDatos);
                case 303:
                    return ProductoConVectores(_pDatos);
            }
            return "Error de ejecución";
        }
        catch(Exception  e) {
            return e.getMessage().toString();
        }
    }

    //region Numeros Complejos 4/4
    private static String OperacionPolarPotencia (ArrayList<Double> _pDatos) {
        try {

            String Respuesta = "",Porque = "", Procedimiento= "Número = ";
            Double _numero = _pDatos.get(0), _esraiz = _pDatos.get(1),
                    _angulo = _pDatos.get(2), exponente = _pDatos.get(3),  vAux = 0.0, rAux = 0.0;

            _angulo = _angulo * exponente;

            vAux = _angulo / 360;
            Integer entero = vAux.intValue();//Integer.parseInt(vAux.toString().substring(0,1)); //Integer.parseInt(vAux.toString());
            _angulo = ((vAux - entero)*360);

            if (_esraiz == 0.0 ){ //no es raiz
                _numero = Math.pow(_numero,exponente);
                Respuesta = String.format("%.1f <) %.2f",_numero,_angulo);
                Porque = "El valor no es una raíz sino un valor entero se eleva a la potencia del ejercicio";
                for (int i = 0; i < exponente; i++){
                    Procedimiento += exponente + " * ";
                }
                Procedimiento = Procedimiento.substring(0,Procedimiento.length() - 2);
                Procedimiento += " = " + _numero;
            }else {
                if (exponente % 2 == 0){ //entero
                    _numero = Math.pow(_numero, exponente - 2);
                    Respuesta = String.format("%.1f <) %.2f",_numero,_angulo);
                    Porque = "El valor es una raíz pero el exponente es par, se eleva el valor de R al expoente planteado en el ejercicio";
                    for (int i = 0; i < exponente; i++){
                        Procedimiento += exponente + " * ";
                    }
                    Procedimiento = Procedimiento.substring(0,Procedimiento.length() - 2);
                    Procedimiento += " = " + _numero;
                }
                else { //impar

                    rAux = _numero;
                    _numero = Math.pow(_numero, exponente - 3);
                    Respuesta = String.format("%.1f √(%.1f) <) %.2f",_numero,rAux,_angulo);
                    Porque = "El valor es una raíz y el exponente es impar, se toma un valor de R y se eleva el valor de R al (expoente - 1) planteado en el ejercicio";
                    for (int i = 0; i < exponente; i++){
                        Procedimiento += exponente + " * ";
                    }
                    Procedimiento = Procedimiento.substring(0,Procedimiento.length() - 2);
                    Procedimiento += " = " + _numero +"\n = "+_numero + " * "+rAux;
                }
            }

            //region Lista Pasos
            //region Paso 1
            myListSteps.add(new csDetailStep(1,
                    "Verificar si el valor R presentado en la expresión es un número entero o una raíz cuadratica",
                    "Saber que procedimiento ocupar para la solución del ejercicio. \n Si es raíz y la potencia es impar (se tomar un valor semejante a R y se eleva a la potencia - 1 el valor de R\n" +
                            "De lo contrario (potencia par y raíz o el valor de R es entero) se eleva a la potencia planteada en el ejercicio)",
                    "Esctructura del ejercicio:\n" +
                            " (R <) ángulo)^ exponente\n" +
                            "Ejemplo: ( √5 <) 35 )^ 3"));
            //endregion

            //region Paso 2
            myListSteps.add(new csDetailStep(2,
                    "Elevar el valor de R número al exponente",
                    Porque,
                    Procedimiento));
            //endregion

            //region Paso 3
            myListSteps.add(new csDetailStep(3,
                    "Se multiplicó el ángulo por el valor del exponente",
                    "Por proceso de formula",
                    String.format("%.2f * %.1f = %.2f",_pDatos.get(2),_pDatos.get(3),_pDatos.get(2)*_pDatos.get(3))));
            //endregion

            //region Paso 4
            myListSteps.add(new csDetailStep(4,
                    "Se calcular el valor equivalente en terminos menores que 180",
                    "El valor debe estar presentado en el intevalo entre 0 y 180",
                    String.format("%.2f / 360 = %.2f \n%.2f - %d = %.1f\n%.1f * 360 = %.2f",_pDatos.get(2)*_pDatos.get(3),vAux,vAux,entero,vAux-entero,vAux-entero,_angulo)));
            //endregion

            //region Paso 5
            myListSteps.add(new csDetailStep(5,
                    "Se le da la estructura de respuesta",
                    "Esa es la forma en que debe ser presentada",
                    Respuesta));
            //endregion
            //endregion
            return Respuesta +" *** "+ myListSteps.size();
        }
        catch(Exception  e) {
            return  e.getMessage().toString();
        }
    }

    private static String RaizNumerosPolares(ArrayList<Double> _pDatos){

        try{

            Double valorR = 0.0, baseRaiz = 0.0, anguloinicial = 0.0, prtReal = 0.0, prtImaginaria = 0.0, baseRaizNumero = 0.0;
            Double TipoEjercicio = _pDatos.get(0);
            Integer Correlativo = 0, valorN = 0;
            String ValorR_Raiz = "";

            //0 si el ejercicio esta en polar o en rectangular
                // si posicion 0 es 1
                //1 = valor de R (numero de raiz)
                //2 = base de la raiz
                //3 = angulo inicial
                //4 = Es raiz? checkbox 1 si 0 no
                //5 = valor de la raiz de cuanto

                // si posicion 0 es 0
                //1 = parte real
                //2 = parte imaginaria
                //3 = base de raiz

            //Si es mayor o sea 1 : es dato polar
            if (TipoEjercicio > 0){
                valorR = _pDatos.get(1);
                baseRaiz = _pDatos.get(2);
                anguloinicial = _pDatos.get(3);
            }else {
                //Si cero : es dato rectangular
                prtReal = _pDatos.get(1);
                prtImaginaria = _pDatos.get(2);
                baseRaiz = _pDatos.get(3);

                //pitagoras
                valorR = (double) Math.sqrt((Math.pow(prtReal,2) + Math.pow(prtImaginaria,2)));
                double aux = Math.atan2(prtImaginaria,prtReal);
                int cuadrante = (prtReal > 0 && prtImaginaria > 0) ? 1: (prtReal < 0 && prtImaginaria > 0) ? 2: (prtReal < 0 && prtImaginaria < 0) ? 3 : (prtReal > 0 && prtImaginaria < 0) ? 4 :0;
                anguloinicial = (cuadrante == 1) ? aux : (cuadrante == 2) ? 180 - aux : (cuadrante == 3) ? 180 + aux : (cuadrante == 4) ? 360 - aux : 0.0;
                String _cuadrante = (cuadrante == 1) ? aux+"" : (cuadrante == 2) ? "180 - "+aux+"" : (cuadrante == 3) ? "180 + "+aux+"" : (cuadrante == 4) ? "360 - "+aux+"" : "";

                Correlativo++;
                //region Paso 1
                myListSteps.add(new csDetailStep(Correlativo,
                        "Pasar el ejercicio de termino Rectangulares a terminos polares (es su equivalencia, su valor no cambia)",
                        "Para poder aplicar el método de calculo de la raíz de numeros pilares",
                        String.format("R = √((%f)² + (%f)² ) = %f \n" +
                                        "tan⁻¹(%f/%f) = %f | queda en el cuadrante #%d \n" +
                                        "<) = %s \n" +
                                        "Z = %f cis %f",
                                 prtReal ,
                                 prtImaginaria,
                                 valorR,
                                 prtImaginaria,
                                 prtReal,
                                 aux ,
                                cuadrante,_cuadrante,
                                 valorR,
                                 anguloinicial
                        )));
                //endregion
            }

            if (baseRaiz < 0){
                return "El valor de la raíz debe ser positivo";
            }else {
                if (baseRaiz.toString().equals("0.0")){
                    return "La raíz de base cero no es posible";
                }
                else {

                    if (_pDatos.get(4) > 0) {//Es raíz el numero

                        //region Paso 2
                        Correlativo++;
                        valorN = baseRaiz.intValue();
                        ValorR_Raiz = String.format("%.1f√(%.2f)",(baseRaiz*_pDatos.get(5)),_pDatos.get(1));
                        valorR = Math.pow(valorR,(1/baseRaiz));
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Calcular el valor de R (valor de raíz), se multiplican las bases de raíces y asigando el valor respectivo a N (raíz que se le aplica al ejercicio)",
                                "Conocer el valor de la raíz de R y asignar la cantidad total desubindices que tendra θ",
                                String.format("R = %d√[%d√(%.2f)] = %.1f√(%.2f)\n" +
                                                "N = %d",
                                        baseRaiz.intValue(),_pDatos.get(5).intValue(), _pDatos.get(1),
                                        (baseRaiz *_pDatos.get(5)),_pDatos.get(1),
                                        baseRaiz.intValue()
                                )));
                        //endregion

                    }else {
                        double auxR = valorR;
                        valorR = Math.pow(valorR,(1/baseRaiz));
                        valorN = Integer.parseInt(baseRaiz.toString().substring(0,1));

                        //region Paso 2
                        Correlativo++;
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Calcular el valor de R (valor de raíz) y asigando el valor respectivo a N (raíz)",
                                "Conocer el valor de la raíz de R y asignar la cantidad total desubindices que tendra θ",
                                String.format("R = %s√(%.1f) = %.1f\n" +
                                                "N = %d",
                                        baseRaiz.toString().substring(0,1),auxR,
                                        (double)Math.round( valorR * 100d) / 100d,valorN)));
                        //endregion
                    }

                    for (int i = 0; i< valorN; i++){

                        double _vTeta = 0.0;
                        String ZetaK = "";
                        _vTeta = (anguloinicial + (360 * i) )/valorN;
                       ZetaK = String.format("%s  cis  %.2f",(_pDatos.get(4)> 0)? ValorR_Raiz : valorR.toString(),_vTeta);

                        Correlativo++;
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Hacer la división de: la suma de "+anguloinicial+" (ángulo inicial) más el producto de 360x"+i+"; entre "+valorN,
                                "Encontrar el valor de θ"+i+" = "+ (double)Math.round( _vTeta * 100d) / 100d,
                                String.format("θk = (%.1f + (360 * %d))/%d\n" +
                                                "\t entonces:\n\t%s",
                                        anguloinicial,i,valorN,ZetaK
                                )));
                    }
                    return "";
                }
            }
        }
        catch(Exception  e) {
            return  e.getMessage().toString();
        }
    }

    private static String PolarAExponencialNC(ArrayList<Double> _pDatos){

        /*
             0 = is raiz? 1 = es si es
             1 = valor del numero
             2 = angulo
             3 = baseraiz
         */
        try{
            double _teta = 0.0;
            _teta = Math.toDegrees((_pDatos.get(2)) * Math.PI)/Math.toDegrees(180);
            if (_pDatos.get(0) > 0){ //si es raiz

                myListSteps.add(new csDetailStep(1,
                        "Dado el ejercicio se multiplica el valor de R por π(pi = 3.14159...) y luego se divide entre 180°",
                        "Se calcula el valor del exponente para representarlo de forma exponencial",
                        String.format("θ = ( %.2f x π)/180°\n" +
                                        "θ = %.2f\n\n" +
                                        "Z = √[%.2f] e^ %.2f",
                                _pDatos.get(2),_teta,
                                _pDatos.get(1),_teta
                        )
                ));

                myListSteps.add(new csDetailStep(2,
                        "Se multiplcó el la raíz por coseno y seno usando el ángulo teta calculado en el paso anterior ",
                        "Se realizaron estas operaciones para encontrar la parte real y la parte imaginaria",
                        String.format("    √[%.2f] Sen(%.2f) = %.2f\n" +
                                      "    √[%.2f] Cos(%.2f) = %.2f\n\n" +
                                      "Z = %.2f %s%.2fi",
                                _pDatos.get(1),_teta,(Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.sin(Math.toDegrees(_teta)) )  ,
                                _pDatos.get(1),_teta,(Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.cos(Math.toDegrees(_teta)) ),

                                (Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.sin(Math.toDegrees(_teta)) ),
                                ((Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.cos(Math.toDegrees(_teta)) )>0?"+ ":""),
                                (Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.cos(Math.toDegrees(_teta)) )
                        )
                ));

           }else { //no es raiz

                myListSteps.add(new csDetailStep(1,
                        "Dado el ejercicio se multiplica el valor de R por π(pi = 3.14159...) y luego se divide entre 180°",
                        "Se calcula el valor del exponente para representarlo de forma exponencial",
                        String.format("θ = ( %.2f x π)/180°\n" +
                                        "θ = %.2f\n\n" +
                                        "Z = %.2f e^ %.2f",
                                _pDatos.get(2),_teta,
                                _pDatos.get(1),_teta
                        )
                ));
                myListSteps.add(new csDetailStep(2,
                        "Se multiplcó el la raíz por coseno y seno usando el ángulo teta calculado en el paso anterior ",
                        "Se realizaron estas operaciones para encontrar la parte real y la parte imaginaria",
                        String.format("    %.2f Sen(%.2f) = %.2f\n" +
                                      "    %.2f Cos(%.2f) = %.2f\n\n" +
                                      "Z = %.2f %s%.2fi",
                                _pDatos.get(1),_teta,(Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.sin(Math.toDegrees(_teta)) )  ,
                                _pDatos.get(1),_teta,(Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.cos(Math.toDegrees(_teta)) ),

                                (Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.sin(Math.toDegrees(_teta)) ),
                                ((Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.cos(Math.toDegrees(_teta)) )>0?"+ ":""),
                                (Math.pow(_pDatos.get(1),(1/_pDatos.get(3))) * Math.cos(Math.toDegrees(_teta)) )
                        )
                ));
            }
            return "";
        }
        catch(Exception  e) {
            return e.getMessage().toString();
        }

    }

    private static String OperacionesRepresentacionExponencial (ArrayList<Double> _pDatos) {
        try {
            int TipoEjercicio = _pDatos.get(0).intValue(),Correlativo = 1;
            double _baseRaiz = 0.0,_baseRaiz2 = 0.0, numeroRaiz = 0.0, numero2 = 0.0, expo1= 0.0,expo2= 0.0,coeficienteraiz = 0.0,coeficienteraiz2 = 0.0;

            switch (TipoEjercicio){

                //region Multiplicacion
                case 1:
                    /*1=is raiz el uno; 0 no, 1 si
                      2=baseraiz del 1
                      3=numero del 1
                      4=exponente del 1
                      5=coeficinete del 1

                    * 6=is raiz el dos; 0 no, 1 si
                    * 7=baseraiz del 2
                      8=numero del 2
                      9=exponente del 2
                     10= coeficiente del 2
                     */
                    if (_pDatos.get(1).intValue() == 1 && _pDatos.get(6).intValue() == 1){
                        //Ambos son raices
                        _baseRaiz = _pDatos.get(2);
                        numeroRaiz = _pDatos.get(3);
                        expo1 = _pDatos.get(4);
                        coeficienteraiz = _pDatos.get(5);

                        _baseRaiz2 = _pDatos.get(7);
                        numero2 = _pDatos.get(8);
                        expo2 = _pDatos.get(9);
                        coeficienteraiz2 = _pDatos.get(10);

                        //region Formula de solución
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Según el tipo de operación exponencial se utiliza la fórmula siguiente",
                                "Las fórmulas varian según el tipo de operación exponencial (en este caso multiplicación)",
                                String.format("(r1 e^ θ1i) (r2 e^ θ2i) \n" +
                                        "r1 x r2  e^(θ1+θ2)i "
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Mostrar Formato
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se representan los terminos en forma de multiplicación",
                                "Para darle familiaridad del proceso",
                                String.format("(%.0f (%.0f√[%.2f]) e^ %.2f ) (%.0f (%.0f√[%.2f]) e^ %.2f )",
                                        coeficienteraiz,_baseRaiz,numeroRaiz,expo1,coeficienteraiz2,_baseRaiz2,numero2,expo2
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Multiplicar bases
                        String _multRaiz = "",respfinal="" ;
                        if (_baseRaiz == _baseRaiz2){
                            _multRaiz = String.format("= %.2f [%.0f√(%.2f)] x %.2f [%.0f√(%.2f)]\n" +
                                                      "= %.2f ∙ %.0f√(%.2f)",
                                    coeficienteraiz,_baseRaiz,numeroRaiz,
                                    coeficienteraiz2,_baseRaiz2,numero2,
                                    (coeficienteraiz*coeficienteraiz2), _baseRaiz,(numeroRaiz*numero2)
                                    );
                            respfinal = String.format("= %.2f ∙ %.0f√(%.2f)",
                                    (coeficienteraiz*coeficienteraiz2), _baseRaiz,(numeroRaiz*numero2));
                        }
                        else {
                            _multRaiz = String.format("= %.2f [%.0f√(%.2f)] x %.2f [%.0f√(%.2f)]\n" +
                                                      "= %.2f ∙ [ %.0f√(%.2f) ∙ %.0f√(%.2f) ]",
                                    coeficienteraiz,_baseRaiz,numeroRaiz,
                                    coeficienteraiz2,_baseRaiz2,numero2,
                                    (coeficienteraiz*coeficienteraiz2),_baseRaiz,numeroRaiz,_baseRaiz2,numero2
                            );
                            respfinal = String.format("= %.2f ∙ [ %.0f√(%.2f) ∙ %.0f√(%.2f) ]",
                                    (coeficienteraiz*coeficienteraiz2),_baseRaiz,numeroRaiz,_baseRaiz2,numero2);
                        }
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se multiplicaron entre sí los coeficientes de cada termino, donde: \n" +
                                        "Coeficiente a = "+coeficienteraiz+", indice a = "+_baseRaiz+", valor bajo la raíz a = "+numeroRaiz+
                                        ", Coeficiente b = "+coeficienteraiz2+", indice b = "+_baseRaiz2+", valor bajo la raíz b = "+numero2,
                                "Para obtener un coeficiente común para la expresión",
                                _multRaiz
                        ));
                        Correlativo++;
                        //endregion

                        //region Suma de exponenetes
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Sumar los exponentes de cada termino, donde: exponente 1 = "+expo1 +" y exponente 2 = "+expo2,
                                "Para obtener el exponente común para la expresión",
                                String.format("   e^%.2f + e^%.2f\n" +
                                                " = e^(%.2f + %.2f)\n" +
                                                " = e^%.2f",
                                        expo1,expo2,expo1,expo2,(expo1+expo2)
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Presentación de REspuesta
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                "Es una forma general de presentar el resultaod",
                                String.format("%s e^%.2f\n" +
                                              "%s e^%s",
                                        respfinal,(expo1+expo2),
                                        respfinal,Exponente_I(expo1+expo2)
                                )
                        ));
                        Correlativo++;
                        //endregion

                    }else {
                        if ((_pDatos.get(1).intValue() == 0 && _pDatos.get(6).intValue() == 1) || (_pDatos.get(1).intValue() == 1 && _pDatos.get(6).intValue() == 0)){

                            //region Identificacion de los valores
                            if (_pDatos.get(1).intValue() == 1){//primer numero es raiz
                                _baseRaiz = _pDatos.get(2);
                                numeroRaiz = _pDatos.get(3);
                                expo1 = _pDatos.get(4);
                                coeficienteraiz = _pDatos.get(5);

                                numero2 = _pDatos.get(8);
                                expo2 = _pDatos.get(9);

                            }
                            else {
                                _baseRaiz = _pDatos.get(7);
                                numeroRaiz = _pDatos.get(8);
                                expo1 = _pDatos.get(9);
                                coeficienteraiz = _pDatos.get(10);

                                numero2 = _pDatos.get(3);
                                expo2 = _pDatos.get(4);
                            }
                            //endregion

                            //region Formula de solución
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Según el tipo de operación exponencial se utiliza la fórmula siguiente",
                                    "Las fórmulas varian según el tipo de operación exponencial (en este caso multiplicación)",
                                    String.format("(r1 e^ θ1i) (r2 e^ θ2i) \n" +
                                            "r1 x r2  e^(θ1+θ2)i "
                                    )
                            ));
                            Correlativo++;
                            //endregion

                            //region Mostrar Formato
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se representan los terminos en forma de multiplicación",
                                    "Para darle familiaridad del proceso",
                                    String.format("(%.0f (%.0f√[%.2f]) e^ %.2f ) ( %.2f e^ %.2f)",
                                            coeficienteraiz,_baseRaiz,numeroRaiz,expo1,numero2,expo2
                                    )
                            ));
                            Correlativo++;
                            //endregion

                            //region Multiplicar bases
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se multiplicaron entre sí los coeficientes de cada termino, donde: \n" +
                                            "Coeficiente1 = "+numero2+ ", coeficiente 2 = "+coeficienteraiz+", indice de raíz = "+_baseRaiz +", base de raíz = "+numeroRaiz,
                                    "Para obtener un coeficiente común para la expresión",
                                    String.format("= %.0f x [ (%.2f)(%.0f√[%.2f])] \n" +
                                                  "= %.2f ∙ %.0f√(%.2f)",
                                            numero2,coeficienteraiz,_baseRaiz,numeroRaiz,
                                            (numero2*coeficienteraiz),_baseRaiz,numeroRaiz
                                    )
                            ));
                            Correlativo++;
                            //endregion

                            //region Suma de exponenetes
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Sumar los exponentes de cada termino, donde: exponente 1 = "+expo1 +" y exponente 2 = "+expo2,
                                    "Para obtener el exponente común para la expresión",
                                    String.format("   e^%.2f + e^%.2f\n" +
                                                  " = e^(%.2f + %.2f)\n" +
                                                  " = e^%.2f",
                                            expo1,expo2,expo1,expo2,(expo1+expo2)
                                    )
                            ));
                            Correlativo++;
                            //endregion

                            //region Presentación de REspuesta
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                    "Es una forma general de presentar el resultaod",
                                    String.format("=%.2f ∙ %.0f√(%.2f) e^%.2f\n" +
                                                  "=%.2f ∙ %.0f√(%.2f) e^%s",
                                            (numero2*coeficienteraiz),_baseRaiz,numeroRaiz,(expo1+expo2),
                                            (numero2*coeficienteraiz),_baseRaiz,numeroRaiz,Exponente_I(expo1+expo2)
                                    )
                            ));
                            Correlativo++;
                            //endregion

                        }else {
                            if ((_pDatos.get(1).intValue() == 0 && _pDatos.get(6).intValue() == 0)){
                                //ninguno de los dos es raiz

                                Double numero1 = 0.0;
                                numero1 = _pDatos.get(3);
                                expo1 = _pDatos.get(4);
                                numero2 = _pDatos.get(8);
                                expo2 = _pDatos.get(9);

                                //region Formula de solución
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Según el tipo de operación exponencial se utiliza la fórmula siguiente",
                                        "Las fórmulas varian según el tipo de operación exponencial (en este caso multiplicación)",
                                        String.format("(r1 e^ θ1i) (r2 e^ θ2i) \n" +
                                                      "r1 x r2  e^(θ1+θ2)i "
                                        )
                                ));
                                Correlativo++;
                                //endregion

                                //region Mostrar Formato
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Se representan los terminos en forma de multiplicación",
                                        "Para darle familiaridad del proceso",
                                        String.format("(%.2f e^ %.2f ) (%.2f e^ %.2f )",
                                                numero1,expo1,numero2,expo2
                                        )
                                ));
                                Correlativo++;
                                //endregion

                                //region Multiplicar bases
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Se multiplicaron entre sí los coeficientes de cada termino, donde: \n" +
                                                "Coeficiente A = "+numero1+", Coeficiente B = "+numero2,
                                        "Para obtener un coeficiente común para la expresión",
                                        String.format("= %.2f x %.2f \n" +
                                                        "= %.2f",
                                                numero1,numero2,(numero1 * numero2)
                                        )
                                ));
                                Correlativo++;
                                //endregion

                                //region Suma de exponenetes
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Sumar los exponentes de cada termino, donde: exponente \"a\" = "+expo1 +" y exponente \"b\" = "+expo2,
                                        "Para obtener el exponente común para la expresión",
                                        String.format("   e^%.2f + e^%.2f\n" +
                                                        " = e^(%.2f + %.2f)\n" +
                                                        " = e^%.2f",
                                                expo1,expo2,expo1,expo2,(expo1+expo2)
                                        )
                                ));
                                Correlativo++;
                                //endregion

                                //region Presentación de REspuesta
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                        "Es una forma general de presentar el resultaod",
                                        String.format("=%.2f e^%.2f\n" +
                                                        "=%.2f e^%s",
                                                (numero1*numero2),(expo1+expo2),(numero1*numero2),Exponente_I(expo1+expo2)
                                        )
                                ));
                                Correlativo++;
                                //endregion
                            }
                        }
                    }

                    break;
                //endregion

                //region Division
                case 2:
                    /*1=is raiz el uno; 0 no, 1 si
                      2=baseraiz del 1
                      3=numero del 1
                      4=exponente del 1
                      5=coeficinete del 1

                    * 6=is raiz el dos; 0 no, 1 si
                    * 7=baseraiz del 2
                      8=numero del 2
                      9=exponente del 2
                     10= coeficiente del 2
                     */

                    //region Formula de solución
                    myListSteps.add(new csDetailStep(Correlativo,
                            "Según el tipo de operación exponencial se utiliza la fórmula siguiente",
                            "Las fórmulas varian según el tipo de operación exponencial (en este caso división)",
                            String.format("(r1 e^ θ1i) \n______________\n(r2 e^ θ2i) \n\n" +
                                    "[ r1 / r2 ]  e^(θ1-θ2)i "
                            )
                    ));
                    Correlativo++;
                    //endregion

                    //las dos son raices
                    if (_pDatos.get(1) == 1 && _pDatos.get(6) == 1){
                        //ambos son raices
                        _baseRaiz = _pDatos.get(2);
                        numeroRaiz = _pDatos.get(3);
                        expo1 = _pDatos.get(4);
                        coeficienteraiz = _pDatos.get(5);

                        _baseRaiz2 = _pDatos.get(7);
                        numero2 = _pDatos.get(8);
                        expo2 = _pDatos.get(9);
                        coeficienteraiz2 = _pDatos.get(10);

                        //region Mostrar Formato
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se representan los terminos en forma de división",
                                "Para darle familiaridad del proceso",
                                String.format("(%.0f (%.0f√[%.2f]) e^ %.2f ) /  (%.0f (%.0f√[%.2f]) e^ %.2f )",
                                        coeficienteraiz,_baseRaiz,numeroRaiz,expo1,coeficienteraiz2,_baseRaiz2,numero2,expo2
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region division bases
                        String divRaiz = "",respfinal="" ;
                        if (_baseRaiz == _baseRaiz2){
                            divRaiz = String.format("= %.2f [%.0f√(%.2f)]    /    %.2f [%.0f√(%.2f)]\n" +
                                            "= %.2f/%.2f [%.0f√(%.2f /%.2f)]\n"+
                                            "= %.2f ∙ %.0f√(%.2f)",
                                    coeficienteraiz,_baseRaiz,numeroRaiz,
                                    coeficienteraiz2,_baseRaiz2,numero2,
                                    coeficienteraiz,coeficienteraiz2,_baseRaiz,numeroRaiz,numero2,
                                    (coeficienteraiz/coeficienteraiz2), _baseRaiz,(numeroRaiz/numero2)
                            );
                            respfinal = String.format("= %.2f ∙ %.0f√(%.2f)",
                                    (coeficienteraiz/coeficienteraiz2), _baseRaiz,(numeroRaiz/numero2));
                        }
                        else {
                            divRaiz = String.format("= %.2f [%.0f√(%.2f)]   /    %.2f [%.0f√(%.2f)]\n" +
                                            "= %.2f ∙ [ %.0f√(%.2f) / %.0f√(%.2f) ]",
                                    coeficienteraiz,_baseRaiz,numeroRaiz,
                                    coeficienteraiz2,_baseRaiz2,numero2,
                                    (coeficienteraiz/coeficienteraiz2),_baseRaiz,numeroRaiz,_baseRaiz2,numero2
                            );
                            respfinal = String.format("= %.2f ∙ [ %.0f√(%.2f) ∙ %.0f√(%.2f) ]",
                                    (coeficienteraiz*coeficienteraiz2),_baseRaiz,numeroRaiz,_baseRaiz2,numero2);
                        }
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se multiplicaron entre sí los coeficientes de cada termino, donde: \n" +
                                        "Coeficiente a = "+coeficienteraiz+", indice a = "+_baseRaiz+", valor bajo la raíz a = "+numeroRaiz+
                                        ", Coeficiente b = "+coeficienteraiz2+", indice b = "+_baseRaiz2+", valor bajo la raíz b = "+numero2,
                                "Para obtener un coeficiente común para la expresión",
                                divRaiz
                        ));
                        Correlativo++;
                        //endregion

                        //region Resta de exponenetes
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Sumar los exponentes de cada termino, donde: exponente 1 = "+expo1 +" y exponente 2 = "+expo2,
                                "Para obtener el exponente común para la expresión",
                                String.format("   e^%.2f - e^%.2f\n" +
                                                " = e^(%.2f - %.2f)\n" +
                                                " = e^%.2f",
                                        expo1,expo2,expo1,expo2,(expo1-expo2)
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Presentación de REspuesta
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                "Es una forma general de presentar el resultaod",
                                String.format("%s e^%.2f\n" +
                                              "%s e^%s",
                                        respfinal,(expo1-expo2),
                                        respfinal,Exponente_I(expo1-expo2)
                                )
                        ));
                        Correlativo++;
                        //endregion

                    }
                    else {
//====================================================================================================
                        //solo uno es raiz
                        if (_pDatos.get(1) == 1 && _pDatos.get(6) == 0){
                            //El primero es raiz

                            //region Identificacion de los valores
                            if (_pDatos.get(1).intValue() == 1){//primer numero es raiz
                                _baseRaiz = _pDatos.get(2);
                                numeroRaiz = _pDatos.get(3);
                                expo1 = _pDatos.get(4);
                                coeficienteraiz = _pDatos.get(5);

                                numero2 = _pDatos.get(8);
                                expo2 = _pDatos.get(9);
                            }
                            //endregion

                            //region Mostrar Formato
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se representan los terminos en forma de división",
                                    "Para darle familiaridad del proceso",
                                    String.format("(%.0f (%.0f√[%.2f]) e^ %.2f ) / ( %.2f e^ %.2f)",
                                            coeficienteraiz,_baseRaiz,numeroRaiz,expo1,numero2,expo2
                                    )
                            ));
                            Correlativo++;
                            //endregion

                            //region dividir bases
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se dividió entre sí los coeficientes de cada termino, donde: \n" +
                                            "Coeficiente1 = "+numero2+ ", coeficiente 2 = "+coeficienteraiz+", indice de raíz = "+_baseRaiz +", base de raíz = "+numeroRaiz,
                                    "Para obtener un coeficiente común para la expresión",
                                    String.format("= (%.0f ∙ [(%.0f√[%.2f])] ) / (%.2f) \n" +
                                                    "= (%.0f ∙ [(%.0f√[%.2f])] ) / (%.2f)",
                                            coeficienteraiz,_baseRaiz,numeroRaiz,numero2,
                                            coeficienteraiz,_baseRaiz,numeroRaiz,numero2
                                    )
                            ));
                            Correlativo++;
                            String _resultBase = "";
                            _resultBase = String.format("= (%.0f ∙ [(%.0f√[%.2f])] ) / (%.2f)",
                                    coeficienteraiz,_baseRaiz,numeroRaiz,numero2);
                            //endregion

                            //region Resta de exponenetes
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Restar los exponentes de cada termino, donde: exponente 1 = "+expo1 +" y exponente 2 = "+expo2,
                                    "Para obtener el exponente común para la expresión",
                                    String.format("   e^%.2f - e^%.2f\n" +
                                                    " = e^(%.2f - %.2f)\n" +
                                                    " = e^%.2f",
                                            expo1,expo2,expo1,expo2,(expo1 - expo2)
                                    )
                            ));
                            Correlativo++;
                            //endregion

                            //region Presentación de REspuesta
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                    "Es una forma general de presentar el resultaod",
                                    String.format("%s  e^%.2f\n" +
                                                    "%s e^%s",
                                            _resultBase,(expo1 - expo2),
                                            _resultBase,Exponente_I(expo1 - expo2)
                                    )
                            ));
                            Correlativo++;
                            //endregion

                        }
                        else {
//====================================================================================================
                            //solo el dos es raiz
                            if (_pDatos.get(1) == 0 && _pDatos.get(6) == 1){
                                //El segundo es raiz
                                //region Identificacion de los valores
                                if (_pDatos.get(6).intValue() == 1){//segundo numero es raiz

                                    numero2 = _pDatos.get(3);
                                    expo2 = _pDatos.get(4);

                                    _baseRaiz = _pDatos.get(7);
                                    numeroRaiz = _pDatos.get(8);
                                    expo1 = _pDatos.get(9);
                                    coeficienteraiz = _pDatos.get(10);
                                }
                                //endregion

                                //region Mostrar Formato
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Se representan los terminos en forma de división",
                                        "Para darle familiaridad del proceso",
                                        String.format("( %.2f e^ %.2f) / (%.0f (%.0f√[%.2f]) e^ %.2f )",
                                                numero2,expo2,coeficienteraiz,_baseRaiz,numeroRaiz,expo1
                                        )
                                ));
                                Correlativo++;
                                //endregion

                                //region dividir bases
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Nos disponemos a dividir los terminos de los coeficientes pero se necesita eliminarla la raíz del valor inferior",
                                        "Para simplificar y tener poder tener ese valor en la parte superior\nPase a siguiente paso",
                                        String.format("= (%.2f) / (%.0f ∙ [(%.0f√[%.2f])] )",
                                                numero2,coeficienteraiz,_baseRaiz,numeroRaiz
                                        )
                                ));
                                Correlativo++;

                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Multiplicmos tanto el numerador como el denominador por la raíz",
                                        "Para simplificar (eliminar) la raíz del denominador de la división",
                                        String.format("= %.2f / (%.2f√(%.2f))\n" +
                                                        "= [%.2f / (%.2f√(%.2f)] x [(%.2f√(%.2f) / (%.2f√(%.2f)] \n" +
                                                        "= [%.2f x (%.2f√(%.2f)] / [(%.2f√(%.2f) x (%.2f√(%.2f)]\n" +
                                                        "= [%.2f x %.2f ] / %.2f√(%.2f)\n" +
                                                        "= [%.2f] / %.2f√(%.2f)",
                                                numero2,_baseRaiz,numeroRaiz,
                                                numero2,_baseRaiz,numeroRaiz,_baseRaiz,numeroRaiz,_baseRaiz,numeroRaiz,
                                                numero2,_baseRaiz,numeroRaiz,_baseRaiz,numeroRaiz,_baseRaiz,numeroRaiz,
                                                numero2,numeroRaiz,_baseRaiz,numeroRaiz,
                                                (numero2*numeroRaiz),_baseRaiz,numeroRaiz
                                        )
                                ));
                                String _resultbase = "";
                                _resultbase = String.format("= [%.2f] / %.2f√(%.2f)",(numero2*numeroRaiz),_baseRaiz,numeroRaiz);
                                Correlativo++;

                                //endregion

                                //region Resta de exponenetes
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Restar los exponentes de cada termino, donde: exponente 1 = "+expo1 +" y exponente 2 = "+expo2,
                                        "Para obtener el exponente común para la expresión",
                                        String.format("   e^%.2f - e^%.2f\n" +
                                                        " = e^(%.2f - %.2f)\n" +
                                                        " = e^%.2f",
                                                expo1,expo2,expo1,expo2,(expo1 - expo2)
                                        )
                                ));
                                Correlativo++;
                                //endregion

                                //region Presentación de REspuesta
                                myListSteps.add(new csDetailStep(Correlativo,
                                        "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                        "Es una forma general de presentar el resultaod",
                                        String.format("%s e^%.2f\n" +
                                                        "%s e^%s",
                                                _resultbase,(expo1 - expo2),
                                                _resultbase,Exponente_I(expo1 - expo2)
                                        )
                                ));
                                Correlativo++;
                                //endregion
                            }
                            else {
//====================================================================================================
                                if (_pDatos.get(1) == 0 && _pDatos.get(6) == 0){
                                    //Ninguno es raiz
                                    Double numero1 = 0.0;
                                    numero1 = _pDatos.get(3);
                                    expo1 = _pDatos.get(4);
                                    numero2 = _pDatos.get(8);
                                    expo2 = _pDatos.get(9);

                                    //region Mostrar Formato
                                    myListSteps.add(new csDetailStep(Correlativo,
                                            "Se representan los terminos en forma de división",
                                            "Para darle familiaridad del proceso",
                                            String.format("(%.2f e^ %.2f ) / (%.2f e^ %.2f )",
                                                    numero1,expo1,numero2,expo2
                                            )
                                    ));
                                    Correlativo++;
                                    //endregion

                                    //region Dividir bases
                                    myListSteps.add(new csDetailStep(Correlativo,
                                            "Se dividieron entre sí los coeficientes de cada termino, donde: \n" +
                                                    "Coeficiente A = "+numero1+", Coeficiente B = "+numero2,
                                            "Para obtener un coeficiente común para la expresión",
                                            String.format("= %.2f / %.2f \n" +
                                                            "= %.2f",
                                                    numero1,numero2,(numero1 / numero2)
                                            )
                                    ));
                                    Correlativo++;
                                    //endregion

                                    //region Resta de exponenetes
                                    myListSteps.add(new csDetailStep(Correlativo,
                                            "Restar los exponentes de cada termino, donde: exponente \"a\" = "+expo1 +" y exponente \"b\" = "+expo2,
                                            "Para obtener el exponente común para la expresión",
                                            String.format("   e^%.2f - e^%.2f\n" +
                                                            " = e^(%.2f - %.2f)\n" +
                                                            " = e^%.2f",
                                                    expo1,expo2,expo1,expo2,(expo1 - expo2)
                                            )
                                    ));
                                    Correlativo++;
                                    //endregion

                                    //region Presentación de REspuesta
                                    myListSteps.add(new csDetailStep(Correlativo,
                                            "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                            "Es una forma general de presentar el resultaod",
                                            String.format("=%.2f e^%.2f\n" +
                                                            "=%.2f e^%s",
                                                    (numero1/numero2),(expo1-expo2),(numero1/numero2),Exponente_I(expo1-expo2)
                                            )
                                    ));
                                    Correlativo++;
                                    //endregion
                                }
                            }
                        }
                    }

                    break;
                //endregion

                //region Potencia
                case 3:
                    /*1 = es raiz? 1 si; 0 no
                    * 2 = coeficiente;
                    * 3 = indice raíz
                    * 4 = base raiz
                    * 5 = exponente
                    * 6 = potencia n
                    * */
                    //region Formula de solucion
                    myListSteps.add(new csDetailStep(Correlativo,
                            "Según el tipo de operación exponencial se utiliza la fórmula siguiente",
                            "Las fórmulas varian según el tipo de operación exponencial (en este caso potenciaa)",
                            String.format("[ (r e^ θ1i) ] ^ n \n" +
                                    "Y^n e^(nθi)"
                            )
                    ));
                    Correlativo++;
                    //endregion
                    if(_pDatos.get(1) > 0){
                        //es raíz

                        //region Mostrar Formato
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se representan los terminos en forma de potencia, \n donde:" +((_pDatos.get(2) > 1)?(_pDatos.get(2).intValue())+"":"")+" es coeficiente, " +
                                        ""+ _pDatos.get(3)+" índice de raíz, "+_pDatos.get(4)+" base de raíz, "+_pDatos.get(5)+" exponente del ejercicio, " + _pDatos.get(6) +
                                        " la pontencia (n) que se va a elevar la expresión",
                                "Para darle familiaridad del proceso",
                                String.format("[ %s %.2f√(%.2f) e^%.2fi] ^%.2f",
                                        ((_pDatos.get(2) > 1)?(_pDatos.get(2).intValue())+"":""),
                                        _pDatos.get(2),_pDatos.get(3),_pDatos.get(4),_pDatos.get(5)
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Multiplicacion de la base
                        String _mult = "";
                        String _respBASE = "";
                        if(_pDatos.get(6)%2 == 0){
                            //n es par
                            _mult = String.format("[ %.2f√(%.2f)] ^ %.2f\n" +
                                            "%.2f",
                                    _pDatos.get(3),_pDatos.get(4), _pDatos.get(6),
                                    (Math.pow(_pDatos.get(4), _pDatos.get(6)))
                            );
                            _respBASE = String.format("%.2f",(Math.pow(_pDatos.get(4), _pDatos.get(6))));
                        }
                        else {
                            //n es impar
                            _mult = String.format("%.2f√(%.2f) [%.2f√(%.2f)] ^ %.2f\n" +
                                            "%.2f√(%.2f) %.2f",
                                    _pDatos.get(3),_pDatos.get(4),_pDatos.get(3),_pDatos.get(4), _pDatos.get(6),
                                    _pDatos.get(3),_pDatos.get(4),(Math.pow(_pDatos.get(4),(_pDatos.get(6))-_pDatos.get(3)))
                            );
                            _respBASE = String.format("%.2f√(%.2f) %.2f",_pDatos.get(3),_pDatos.get(4),(Math.pow(_pDatos.get(4),(_pDatos.get(6))-_pDatos.get(3))));
                        }
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se elevó a \"n\" ("+_pDatos.get(6)+") la expresión",
                                "Para obtener el coeficiente de la respuesta final",
                                String.format("[%.2f√(%.2f)] ^ %.2f \n" +
                                              "%s",
                                        _pDatos.get(3),_pDatos.get(4),_pDatos.get(6),
                                        _mult
                                        )
                                ));
                        Correlativo++;
                        //endregion

                        //region Multi de exponentes
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se multiplicó el valor del exponente del ejercicio por el valor de \"n\" ("+_pDatos.get(5)+" y "+_pDatos.get(6)+ " respectivamente)",
                                "Para obtener el exponente de la respuesta final",
                                String.format(" (e ^ %.2f) ^ %.2f \n" +
                                              "= %.2f x %.2f\n" +
                                              "= %.2f",
                                        _pDatos.get(5),_pDatos.get(6),
                                        _pDatos.get(5),_pDatos.get(6),
                                        (_pDatos.get(5)*_pDatos.get(6))
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Presentación de Respuesta
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                "Es una forma general de presentar el resultaod",
                                String.format("%s e^%.2f\n" +
                                              "%s e^%s",
                                        _respBASE, (_pDatos.get(5)*_pDatos.get(6)),
                                        _respBASE, Exponente_I((_pDatos.get(5)*_pDatos.get(6)))
                                        )
                                ));
                        Correlativo++;
                        //endregion
                    }
                    else {
                        //no es raíz
                        //region Mostrar Formato
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se representan los terminos en forma de potencia, \n donde:" +
                                        ""+_pDatos.get(4)+" coeficiente, "+_pDatos.get(5)+" exponente del ejercicio, " + _pDatos.get(6) +
                                        " la pontencia (n) que se va a elevar la expresión",
                                "Para darle familiaridad del proceso",
                                String.format("[ %.2f e^%.2fi] ^%.2f",
                                        _pDatos.get(4),_pDatos.get(5),_pDatos.get(6)
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Multiplicacion de la base
                        String _respBASE = String.format("");
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se elevó a \"n\" ("+_pDatos.get(6)+") la expresión",
                                "Para obtener el coeficiente de la respuesta final",
                                String.format("[ %.2f ^ %.2f ] \n" +
                                                "= %.2f",
                                        _pDatos.get(4),_pDatos.get(6),
                                        Math.pow(_pDatos.get(4),_pDatos.get(6))
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Multi de exponentes
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se multiplicó el valor del exponente del ejercicio por el valor de \"n\" ("+_pDatos.get(5)+" y "+_pDatos.get(6)+ " respectivamente)",
                                "Para obtener el exponente de la respuesta final",
                                String.format(" (e ^ %.2f) ^ %.2f \n" +
                                                "= %.2f x %.2f\n" +
                                                "= %.2f",
                                        _pDatos.get(5),_pDatos.get(6),
                                        _pDatos.get(5),_pDatos.get(6),
                                        (_pDatos.get(5)*_pDatos.get(6))
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Presentación de Respuesta
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Con las respuestas obtenidas en los últimos dos pasos, se construye el formato de respuesta",
                                "Es una forma general de presentar el resultaod",
                                String.format("%.2f e^%.2f\n" +
                                                "%.2f e^%s",
                                        Math.pow(_pDatos.get(4),_pDatos.get(6)), (_pDatos.get(5)*_pDatos.get(6)),
                                        Math.pow(_pDatos.get(4),_pDatos.get(6)), Exponente_I((_pDatos.get(5)*_pDatos.get(6)))
                                )
                        ));
                        Correlativo++;
                        //endregion
                    }
                    break;
                //endregion

                //region Raiz
                case 4:
                   /* 1=Is raiz? natural
                    * 2=indice
                    * 3= numero
                    * 4. exponente
                    * 5. base del ejercicio
                    * */

                    //region Formula de solucion
                    myListSteps.add(new csDetailStep(Correlativo,
                            "Según el tipo de operación exponencial se utiliza la fórmula siguiente",
                            "Las fórmulas varian según el tipo de operación exponencial (en este caso potenciaa)",
                            String.format("[ n√( re^ θi) ] ^ n \n" +
                                          "n√(r) e^([θ+2πk]/n)"
                            )
                    ));
                    Correlativo++;
                    //endregion
                    if (_pDatos.get(1) > 0){
                        //es raiz

                        //region Mostrar Formato
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se representan los terminos en forma de potencia, \n donde:" +_pDatos.get(5)+" es la raíz (n) que se va a calcular la expresión, "
                                        + _pDatos.get(2)+" índice de raíz, "+_pDatos.get(3)+" base de raíz, "+_pDatos.get(4)+" exponente del ejercicio",
                                "Para darle familiaridad del proceso",
                                String.format(" %.2f√{ [ %.2f√( %.2fe^ %.2fi) ] }",
                                        _pDatos.get(5),_pDatos.get(2),_pDatos.get(3),_pDatos.get(4)
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Multiplicacion de la base
                        String _respBASE = "";
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se mmultipilcó  \"n\" ("+_pDatos.get(5)+") con el indice de la expresión  ("+_pDatos.get(2)+")",
                                "Para obtener el coeficiente de la respuesta final",
                                String.format("%.2f√[ %.2f√(%.2f)] \n" +
                                              "%.2f√(%.2f)",
                                                _pDatos.get(5),_pDatos.get(2),_pDatos.get(3),
                                                (_pDatos.get(5)*_pDatos.get(2)),_pDatos.get(3)
                                )
                        ));
                        _respBASE = String.format("%.2f√(%.2f)",(_pDatos.get(5)*_pDatos.get(2)),_pDatos.get(3));
                        Correlativo++;
                        //endregion

                        //region Calculo de angulos
                        ArrayList<String> _lstangulos = new ArrayList<>();
                        for (int i = 0; i<_pDatos.get(5).intValue();i++){
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se aplicó la fórmula , ([θ+2πk]/n) donde:\n" +
                                            "θ = "+_pDatos.get(4)+",k = "+ _pDatos.get(i)+" \n" +
                                            "Se hacen tantas iteraciones como sea el valor de \"n\" ("+_pDatos.get(5).intValue()+")",
                                    "Para calcular los ángulos del ejercicio, en este caso se calculo en ángulo (θ"+i+")",
                                    String.format("%.2f + (2π · %d])]/%.2f\n" +
                                                  "%.2f",
                                                  _pDatos.get(4),i,_pDatos.get(5),
                                                  ((_pDatos.get(4)+(i*Math.PI*2))/_pDatos.get(5))
                                            )
                                    ));
                            _lstangulos.add(String.format("e^%.2f",((_pDatos.get(4)+(i*Math.PI*2))/_pDatos.get(5))));
                            Correlativo++;
                        }
                        //endregion

                        //region Formato de respuesta
                        for (String _var : _lstangulos) {
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se muestra la respuesta por cada uno de los ángulos deñ ejercicio",
                                    "Es el formato de para presentar este tipo de ejercicio (forma rectangular)",
                                    String.format("%s %s",
                                            _respBASE,_var
                                    )
                            ));
                            Correlativo++;
                        }
                        //endregion

                    }
                    else{
                        //no es raiz
                        //region Mostrar Formato
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se representan los terminos en forma de potencia, \n donde:" +_pDatos.get(5)+" es la raíz (n) que se va a calcular la expresión, "
                                        +_pDatos.get(3)+" coeficiente del ejercicio, "+_pDatos.get(4)+" exponente del ejercicio",
                                "Para darle familiaridad del proceso",
                                String.format(" %.2f√[ %.2fe^ %.2fi ] ",
                                        _pDatos.get(5),_pDatos.get(3),_pDatos.get(4)
                                )
                        ));
                        Correlativo++;
                        //endregion

                        //region Multiplicacion de la base
                        String _respBASE = "";
                        myListSteps.add(new csDetailStep(Correlativo,
                                "Se claculo la raíz  \"n\" ("+_pDatos.get(5)+") de la expresión  ("+_pDatos.get(3)+")",
                                "Para obtener el coeficiente de la respuesta final",
                                String.format("%.2f√[ %.2f ]",
                                        _pDatos.get(5),_pDatos.get(3)
                                )
                        ));
                        _respBASE = String.format("%.2f√[ %.2f ]",_pDatos.get(5),_pDatos.get(3));
                        Correlativo++;
                        //endregion

                        //region Calculo de angulos
                        ArrayList<String> _lstangulos = new ArrayList<>();
                        for (int i = 0; i<_pDatos.get(5).intValue();i++){
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se aplicó la fórmula , ([θ+2πk]/n) donde:\n" +
                                            "θ = "+_pDatos.get(4)+",k = "+ _pDatos.get(i)+" \n" +
                                            "Se hacen tantas iteraciones como sea el valor de \"n\" ("+_pDatos.get(5).intValue()+")",
                                    "Para calcular los ángulos del ejercicio, en este caso se calculo en ángulo (θ"+i+")",
                                    String.format("%.2f + (2π · %d])]/%.2f\n" +
                                                    "%.2f",
                                            _pDatos.get(4),i,_pDatos.get(5),
                                            ((_pDatos.get(4)+(i*Math.PI*2))/_pDatos.get(5))
                                    )
                            ));
                            _lstangulos.add(String.format("e^%.2f",((_pDatos.get(4)+(i*Math.PI*2))/_pDatos.get(5))));
                            Correlativo++;
                        }
                        //endregion

                        //region Formato de respuesta
                        for (String _var : _lstangulos) {
                            myListSteps.add(new csDetailStep(Correlativo,
                                    "Se muestra la respuesta por cada uno de los ángulos deñ ejercicio",
                                    "Es el formato de para presentar este tipo de ejercicio (forma rectangular)",
                                    String.format("%s %s",
                                            _respBASE,_var
                                    )
                            ));
                            Correlativo++;
                        }
                        //endregion
                    }
                    break;
                //endregion
            }
            return "";
        }
        catch(Exception  e) {
            return e.getMessage().toString();
        }
    }

    //endregion


    //region Matrices 2/2
    private static String MatrizInverza (ArrayList<Double> _pDatos) {

        int Correlativo = 1;
        double determinante = 0.0,pos1 = 0.0,pos2 = 0.0,pos3 = 0.0,pos4 = 0.0,pos5 = 0.0,pos6 = 0.0,pos7 = 0.0,pos8 = 0.0,pos9 = 0.0;
        if (_pDatos.get(0).equals(0.0)){
            //matriz es 3x3
            //region Paso 1 :Calculo del determinante
            myListSteps.add(new csDetailStep(Correlativo,
                    "Calcular el valor del determinante, si el valor es igual a cero el ejercicio no tiene inverza (todo número entre cero es indefinido)",
                    "Se busca este valor para ser utilizado como denominador en el paso 12 (División de la matriz transpuesta).",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║",
                            _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),
                            _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),
                            _pDatos.get(7),_pDatos.get(8),_pDatos.get(9)
                            )
            ));
            Correlativo++;
            //endregion

            //region Paso 2 :Calculo del determinante - crammer
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dada la matriz 3x3 nos auxiliamos del método de Cramer (aumentar dos columnas, pasar de 3x3 a 3x5)",
                    "Para utilizar todos los elementos que componen a la matriz, (Cálculo del determinante)",
                    String.format("║ %.2f  %.2f  %.2f | %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f | %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f | %.2f  %.2f ║",
                            _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),_pDatos.get(1),_pDatos.get(2),
                            _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),_pDatos.get(4),_pDatos.get(5),
                            _pDatos.get(7),_pDatos.get(8),_pDatos.get(9),_pDatos.get(7),_pDatos.get(8)
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 3 y 4 :Calculo del determinante - crammer
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dada la matriz 3x3 nos auxiliamos del método de Cramer (aumentar dos columnas, pasar de 3x3 a 3x5)",
                    "Para utilizar todos los elementos que componen a la matriz, (Cálculo del determinante)",
                    String.format("[(%.2f×%.2f×%.2f) + (%.2f×%.2f×%.2f) + (%.2f×%.2f×%.2f)] \n- [(%.2f×%.2f×%.2f) + (%.2f×%.2f×%.2f) + (%.2f×%.2f×%.2f)]\n" +
                                  "\n=[(%.2f%s%.2f%s%.2f)] - [(%.2f%s%.2f%s%.2f)]",
                            _pDatos.get(1),_pDatos.get(5),_pDatos.get(9),
                            _pDatos.get(2),_pDatos.get(6),_pDatos.get(7),
                            _pDatos.get(3),_pDatos.get(4),_pDatos.get(8),
                            _pDatos.get(7),_pDatos.get(5),_pDatos.get(3),
                            _pDatos.get(8),_pDatos.get(6),_pDatos.get(1),
                            _pDatos.get(9),_pDatos.get(4),_pDatos.get(2),

                            (_pDatos.get(1)*_pDatos.get(5)*_pDatos.get(9)),
                            (_pDatos.get(2)*_pDatos.get(6)*_pDatos.get(7))<0?"":"+",
                            (_pDatos.get(2)*_pDatos.get(6)*_pDatos.get(7)),
                            (_pDatos.get(3)*_pDatos.get(4)*_pDatos.get(8))<0?"":"+",
                            (_pDatos.get(3)*_pDatos.get(4)*_pDatos.get(8)),

                            (_pDatos.get(7)*_pDatos.get(5)*_pDatos.get(3)),
                            (_pDatos.get(8)*_pDatos.get(6)*_pDatos.get(1))<0?"":"+",
                            (_pDatos.get(8)*_pDatos.get(6)*_pDatos.get(1)),
                            (_pDatos.get(9)*_pDatos.get(4)*_pDatos.get(2))<0?"":"+",
                            (_pDatos.get(9)*_pDatos.get(4)*_pDatos.get(2))
                    )
            ));
            Correlativo++;
            double termino1=0.0,termino2=0.0;
            termino1 = (_pDatos.get(1)*_pDatos.get(5)*_pDatos.get(9))+
                    (_pDatos.get(2)*_pDatos.get(6)*_pDatos.get(7))+
                    (_pDatos.get(3)*_pDatos.get(4)*_pDatos.get(8));

            termino2 = (_pDatos.get(7)*_pDatos.get(5)*_pDatos.get(3))+
                    (_pDatos.get(8)*_pDatos.get(6)*_pDatos.get(1))+
                    (_pDatos.get(9)*_pDatos.get(4)*_pDatos.get(2));
            myListSteps.add(new csDetailStep(Correlativo,
                    "Continuación del paso anterior\n\nDada la matriz 3x3 nos auxiliamos del método de Cramer (aumentar dos columnas, pasar de 3x3 a 3x5)",
                    "Para utilizar todos los elementos que componen a la matriz, (Cálculo del determinante)",
                    String.format("=(%.2f) - (%.2f)\n " +
                                  "= %.2f%s%.2f\n" +
                                  "= %.2f (determinante)",
                            termino1,termino2,
                            termino1,(termino2*-1)<0?"":"+",termino2*-1,
                            (termino1+(termino2*-1))
                    )
            ));
            determinante = (termino1+(termino2*-1));
            Correlativo++;
            //endregion

            //region Paso 5: Cofactor fila 1
            myListSteps.add(new csDetailStep(Correlativo,
                    "Aplicamos el método de cofactores a la matriz original, este caso lo hacemos en la fila 1",
                    " (El en la posición 'x' se calcula ocultando los valores de su fila y columna, creando una submatriz en cada posición de la matriz)",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║\n\n" +
                                  "║ +|%.2f %.2f| -|%.2f %.2f| +|%.2f %.2f| ║\n" +
                                  "║  |%.2f %.2f|  |%.2f %.2f|  |%.2f %.2f| ║",
                            _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),
                            _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),
                            _pDatos.get(7),_pDatos.get(8),_pDatos.get(9),
                            _pDatos.get(5),_pDatos.get(6),_pDatos.get(4),_pDatos.get(6),_pDatos.get(4),_pDatos.get(5),
                            _pDatos.get(8),_pDatos.get(9),_pDatos.get(7),_pDatos.get(9),_pDatos.get(7),_pDatos.get(8)
                    )
            ));
            pos1 = (_pDatos.get(5)*_pDatos.get(9)- (_pDatos.get(8)*_pDatos.get(6)));
            pos2 = (-1)*(_pDatos.get(4)*_pDatos.get(9)- (_pDatos.get(7)*_pDatos.get(6)));
            pos3 = (_pDatos.get(4)*_pDatos.get(8)- (_pDatos.get(7)*_pDatos.get(5)));
            Correlativo++;
            //endregion

            //region Paso 6: Cofactor fila 2
            myListSteps.add(new csDetailStep(Correlativo,
                    "Aplicamos el método de cofactores a la matriz original, este caso lo hacemos en la fila 2",
                    " (El en la posición 'x' se calcula ocultando los valores de su fila y columna, creando una submatriz en cada posición de la matriz)",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f  %.2f ║\n\n" +
                                    "║ -|%.2f %.2f| +|%.2f %.2f| -|%.2f %.2f| ║\n" +
                                    "║  |%.2f %.2f|  |%.2f %.2f|  |%.2f %.2f| ║",
                            _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),
                            _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),
                            _pDatos.get(7),_pDatos.get(8),_pDatos.get(9),
                            _pDatos.get(2),_pDatos.get(3),_pDatos.get(1),_pDatos.get(3),_pDatos.get(1),_pDatos.get(2),
                            _pDatos.get(8),_pDatos.get(9),_pDatos.get(7),_pDatos.get(9),_pDatos.get(7),_pDatos.get(8)
                    )
            ));
            pos4 = (-1)*(_pDatos.get(2)*_pDatos.get(9)- (_pDatos.get(8)*_pDatos.get(3)));
            pos5 = (_pDatos.get(1)*_pDatos.get(9)- (_pDatos.get(7)*_pDatos.get(3)));
            pos6 = (-1)*(_pDatos.get(1)*_pDatos.get(8)- (_pDatos.get(7)*_pDatos.get(2)));
            Correlativo++;
            //endregion

            //region Paso 7: Cofactor fila 3
            myListSteps.add(new csDetailStep(Correlativo,
                    "Aplicamos el método de cofactores a la matriz original, este caso lo hacemos en la fila 3",
                    " (El en la posición 'x' se calcula ocultando los valores de su fila y columna, creando una submatriz en cada posición de la matriz)",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f  %.2f ║\n\n" +
                                    "║ +|%.2f %.2f| -|%.2f %.2f| +|%.2f %.2f| ║\n" +
                                    "║  |%.2f %.2f|  |%.2f %.2f|  |%.2f %.2f| ║",
                            _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),
                            _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),
                            _pDatos.get(7),_pDatos.get(8),_pDatos.get(9),
                            _pDatos.get(2),_pDatos.get(3),_pDatos.get(1),_pDatos.get(3),_pDatos.get(1),_pDatos.get(2),
                            _pDatos.get(5),_pDatos.get(6),_pDatos.get(4),_pDatos.get(6),_pDatos.get(4),_pDatos.get(5)
                    )
            ));
            pos7 = (_pDatos.get(2)*_pDatos.get(6)- (_pDatos.get(5)*_pDatos.get(3)));
            pos8 = (-1)*(_pDatos.get(1)*_pDatos.get(6)- (_pDatos.get(4)*_pDatos.get(3)));
            pos9 = (_pDatos.get(1)*_pDatos.get(5)- (_pDatos.get(4)*_pDatos.get(2)));
            Correlativo++;
            //endregion

            //region Paso 8: Cofactor fila 1
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dada las submatriz de la fila 1 se multiplican cada  una de ellas en cruz",
                    "se obtiene los siguientes valores de la nueva matriz (Fila 1)",
                    String.format("║ +|%.2f %.2f| -|%.2f %.2f| +|%.2f %.2f| ║\n" +
                                  "║  |%.2f %.2f|  |%.2f %.2f|  |%.2f %.2f| ║\n\n" +
                                  "= (%.2f×%.2f) - (%.2f×%.2f) = %.2f\n" +
                                  "= (%.2f×%.2f) - (%.2f×%.2f) × -1 = %.2f\n" +
                                  "= (%.2f×%.2f) - (%.2f×%.2f) = %.2f",
                            _pDatos.get(5),_pDatos.get(6),_pDatos.get(4),_pDatos.get(6),_pDatos.get(4),_pDatos.get(5),
                            _pDatos.get(8),_pDatos.get(9),_pDatos.get(7),_pDatos.get(9),_pDatos.get(7),_pDatos.get(8),
                            _pDatos.get(5),_pDatos.get(9),_pDatos.get(8),_pDatos.get(6),pos1,
                            _pDatos.get(4),_pDatos.get(9),_pDatos.get(7),_pDatos.get(6),pos2,
                            _pDatos.get(4),_pDatos.get(8),_pDatos.get(7),_pDatos.get(5),pos3
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 9: Cofactor fila 2
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dada las submatriz de la fila 2 se multiplican cada  una de ellas en cruz",
                    "se obtiene los siguientes valores de la nueva matriz (Fila 2)",
                    String.format("║ +|%.2f %.2f| -|%.2f %.2f| +|%.2f %.2f| ║\n" +
                                    "║  |%.2f %.2f|  |%.2f %.2f|  |%.2f %.2f| ║\n\n" +
                                    "= (%.2f×%.2f) - (%.2f×%.2f) × -1 = %.2f\n" +
                                    "= (%.2f×%.2f) - (%.2f×%.2f) = %.2f\n" +
                                    "= (%.2f×%.2f) - (%.2f×%.2f) × -1 = %.2f",
                            _pDatos.get(2),_pDatos.get(3),_pDatos.get(1),_pDatos.get(3),_pDatos.get(1),_pDatos.get(2),
                            _pDatos.get(8),_pDatos.get(9),_pDatos.get(7),_pDatos.get(9),_pDatos.get(7),_pDatos.get(8),
                            _pDatos.get(2),_pDatos.get(9),_pDatos.get(8),_pDatos.get(3),pos4,
                            _pDatos.get(1),_pDatos.get(9),_pDatos.get(7),_pDatos.get(3),pos5,
                            _pDatos.get(1),_pDatos.get(8),_pDatos.get(7),_pDatos.get(2),pos6
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 10: Cofactor fila 3
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dada las submatriz de la fila 3 se multiplican cada  una de ellas en cruz",
                    "se obtiene los siguientes valores de la nueva matriz (Fila 3)",
                    String.format("║ +|%.2f %.2f| -|%.2f %.2f| +|%.2f %.2f| ║\n" +
                                    "║  |%.2f %.2f|  |%.2f %.2f|  |%.2f %.2f| ║\n\n" +
                                    "= (%.2f×%.2f) - (%.2f×%.2f) = %.2f\n" +
                                    "= (%.2f×%.2f) - (%.2f×%.2f) × -1 = %.2f\n" +
                                    "= (%.2f×%.2f) - (%.2f×%.2f) = %.2f",
                            _pDatos.get(2),_pDatos.get(3),_pDatos.get(1),_pDatos.get(3),_pDatos.get(1),_pDatos.get(2),
                            _pDatos.get(5),_pDatos.get(6),_pDatos.get(4),_pDatos.get(6),_pDatos.get(4),_pDatos.get(5),

                            _pDatos.get(2),_pDatos.get(6),_pDatos.get(5),_pDatos.get(3),pos7,
                            _pDatos.get(1),_pDatos.get(6),_pDatos.get(4),_pDatos.get(3),pos8,
                            _pDatos.get(1),_pDatos.get(5),_pDatos.get(4),_pDatos.get(2),pos9
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 11: matriz ya en terminos de cofactores
            myListSteps.add(new csDetailStep(Correlativo,
                    "Luego de aplicar el método de cofactores, obtenemos la matriz siguiente",
                    "Esta es la matriz que vamos a transponer",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f  %.2f ║",
                            pos1,pos2,pos3,
                            pos4,pos5,pos6,
                            pos7,pos8,pos9
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 12: Transponer la matriz
            myListSteps.add(new csDetailStep(Correlativo,
                    "Se trasnpone la matriz encotrada en el paso anterior",
                    "Paso de regla para econtrar la matriz inverza",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║",
                            pos1,pos4,pos7,
                            pos2,pos5,pos8,
                            pos3,pos6,pos9
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 13: Division de la matriz transpoueta entre el determinante
            myListSteps.add(new csDetailStep(Correlativo,
                    "Se divide la matriz transpoueta entre el determinante (valor calculado en el paso 4)",
                    "Paso de regla para econtrar la matriz inverza",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║\n" +
                                  "--------------------\n" +
                                  "        %.2f       ",
                            pos1,pos4,pos7,
                            pos2,pos5,pos8,
                            pos3,pos6,pos9,
                            determinante
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 14: Division de la matriz transpoueta entre el determinante
            myListSteps.add(new csDetailStep(Correlativo,
                    "Se divide cada elemento de la matriz entre el determiante",
                    "Para encontrar la resuesta final (Esta ya es una respuesta válida)",
                    String.format("║ %.1f/%.1f  %.1f/%.1f  %.1f/%.1f ║\n" +
                                  "║ %.1f/%.1f  %.1f/%.1f  %.1f/%.1f ║\n" +
                                  "║ %.1f/%.1f  %.1f/%.1f  %.1f/%.1f ║",
                            pos1,determinante,pos4,determinante,pos7,determinante,
                            pos2,determinante,pos5,determinante,pos8,determinante,
                            pos3,determinante,pos6,determinante,pos9,determinante
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 15: Division de la matriz transpoueta entre el determinante
            myListSteps.add(new csDetailStep(Correlativo,
                    "Se hace la división de cada elemento planteado en el paso anterior",
                    "Encontrar la respuesta final (Esta es otra forma de presentar el resultado)",
                    String.format("║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f  %.2f ║",
                            (pos1/determinante),(pos4/determinante),(pos7/determinante),
                            (pos2/determinante),(pos5/determinante),(pos8/determinante),
                            (pos3/determinante),(pos6/determinante),(pos9/determinante)
                    )
            ));
            Correlativo++;
            //endregion
        }
        else {  //2x2

            //region Paso 1 :Calculo del determinante
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dada la matriz 2x2 se calculó el valor del determinante, si el valor es igual a cero el ejercicio no tiene inverza (todo número entre cero es indefinido)",
                    "Se busca este valor para ser utilizado como denominador en el paso xx (División de la matriz transpuesta).",
                    String.format("║ %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f ║\n" +
                                  "= (%.2f×%.2f) - (%.2f×%.2f)\n" +
                                  "= %.2f%s%.2f\n" +
                                  "= %.2f (determinante)",
                            _pDatos.get(1),_pDatos.get(2),
                            _pDatos.get(3),_pDatos.get(4),
                            _pDatos.get(1),_pDatos.get(4),_pDatos.get(3),_pDatos.get(2),
                            (_pDatos.get(1)*_pDatos.get(4)),((-1)*(_pDatos.get(3)*_pDatos.get(2)))<0?"":"+",((-1)*(_pDatos.get(3)*_pDatos.get(2))),
                            ((_pDatos.get(1)*_pDatos.get(4))+((-1)*(_pDatos.get(3)*_pDatos.get(2))))

                    )
            ));
            determinante = ((_pDatos.get(1)*_pDatos.get(4))+((-1)*(_pDatos.get(3)*_pDatos.get(2))));
            Correlativo++;
            //endregion

            //region Paso 2: Cofactor
            myListSteps.add(new csDetailStep(Correlativo,
                    "Se sustituye en la posición del elemento \"A\" el valor de \"B\"  (el que queda libre ocultando los valores de la fila y columna de \"A\")",
                    "Aplicamos el método de cofactores a la matriz original, para encontrar los valores correspondientes a cada posición de la matriz",
                    String.format("║+ |%.2f|  -|%.2f| ║\n" +
                                  "║- |%.2f|  +|%.2f| ║\n\n" +
                                  "= ║ %.2f  %.2f ║\n" +
                                  "   ║ %.2f  %.2f ║",
                            _pDatos.get(1),_pDatos.get(2),
                            _pDatos.get(3),_pDatos.get(4),
                            _pDatos.get(4),_pDatos.get(3)*-1,
                            _pDatos.get(2)*-1,_pDatos.get(1)
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 3 : Transposner la matriz
            myListSteps.add(new csDetailStep(Correlativo,
                    "Cambiamos la ubicación de los elementos de la matriz donde las filas serán columnas y viceverza",
                    "A este procedimiento se le llama: Transposición de la matriz. Esta será la matriz que se dividirá con el determinante",
                    String.format("║ %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f ║\n\n" +
                                    "║ %.2f  %.2f ║\n" +
                                    "║ %.2f  %.2f ║",
                            _pDatos.get(4),_pDatos.get(3)*-1,
                            _pDatos.get(2)*-1,_pDatos.get(1),
                            _pDatos.get(4),_pDatos.get(2)*-1,
                            _pDatos.get(3)*-1,_pDatos.get(1)
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 4 : División de la matriz entre determiante
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dividimos la matriz transpuesta entre el determinante calculado en el paso 1",
                    "Encontramos la matriz inverza del ejercicio (esta puede ser una respuesta válida en forma representada)",
                    String.format("║ %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f ║\n" +
                                  "------------------------\n" +
                                  "      %.2f       \n\n" +
                                  "║ %.2f/%.2f  %.2f/%.2f ║\n" +
                                  "║ %.2f/%.2f  %.2f/%.2f ║",
                            _pDatos.get(4),_pDatos.get(2)*-1,
                            _pDatos.get(3)*-1,_pDatos.get(1),determinante,
                            _pDatos.get(4),determinante,_pDatos.get(2)*-1,determinante,
                            _pDatos.get(3)*-1,determinante,_pDatos.get(1),determinante
                    )
            ));
            Correlativo++;
            //endregion

            //region Paso 5 : División de la matriz entre determiante
            myListSteps.add(new csDetailStep(Correlativo,
                    "Dividimos la matriz transpuesta entre el determinante calculado en el paso 1",
                    "Encontramos la matriz inverza del ejercicio",
                    String.format("║ %.2f  %.2f ║\n" +
                                  "║ %.2f  %.2f ║",
                            (_pDatos.get(4)/determinante),(_pDatos.get(2)*-1/determinante),
                            (_pDatos.get(3)*-1/determinante),(_pDatos.get(1)/determinante)
                    )
            ));
            Correlativo++;
            //endregion


        }
        return "";
    }

    public static String MatrizdeGauss(ArrayList<csMatriz> _mtrz){

        try
        {
            //region Imprecion de Matriz Original
            String matriz_original = "", incognitas = "";
            matriz_original = "";
            incognitas = "";
            if (_mtrz.get(1).getTamano().equals(2)){
                incognitas = "\"x\", \"y\"";
                for (int iii = 1; iii < _mtrz.size(); iii++) {
                    matriz_original += String.format("║ %.1f  %.1f │ %.1f ║\n",
                            _mtrz.get(iii).getX(), _mtrz.get(iii).getY(), _mtrz.get(iii).getIgual());
                }
            }else {

                if (_mtrz.get(1).getTamano().equals(3)){
                    incognitas = "\"x\", \"y\", \"z\"";
                    for (int iii = 1; iii < _mtrz.size(); iii++) {
                        matriz_original += String.format("║ %.1f  %.1f  %.1f │ %.1f ║\n",
                                _mtrz.get(iii).getX(), _mtrz.get(iii).getY(),
                                _mtrz.get(iii).getZ(), _mtrz.get(iii).getIgual());
                    }
                }else {
                    if (_mtrz.get(1).getTamano().equals(4)){
                        incognitas = "\"w\", \"x\", \"y\", \"z\"";
                        for (int iii = 1; iii < _mtrz.size(); iii++) {
                            matriz_original += String.format("║ %.1f  %.1f  %.1f  %.1f │ %.1f ║\n",
                                    _mtrz.get(iii).getW(), _mtrz.get(iii).getX(), _mtrz.get(iii).getY(),
                                    _mtrz.get(iii).getZ(), _mtrz.get(iii).getIgual());
                        }
                    }
                }
            }
            myListSteps.add(new csDetailStep(1,
                    "Dada la matriz " + _mtrz.get(1).getTamano() + "×" + _mtrz.get(1).getTamano() + " se obtendrán los valores de las varibales de "+incognitas,
                    "Aplicando el método de Gauss podemos conocer el valor de cada una de las incognitas.",
                    matriz_original
            ));
            //endregion
            switch (_mtrz.get(1).getTamano()) {

                //region 4x4
                case 4:
                    String mult4[] = {"41", "31", "21", "42", "32", "43", "14", "24", "34", "13", "23", "12"};
                    String div4[] = {"11", "22", "33", "44"};
                    ResolverMatrizGauss(mult4,div4,_mtrz);
                    break;
                //endregion

                //region 3x3
                case 3:
                    String mult3[] = {"31","21","32","13","23","12"};
                    String div3[] = {"11","22","33"};
                    ResolverMatrizGauss(mult3,div3,_mtrz);
                    break;
                //endregion

                //region 2x2
                case 2:
                    String mult2[] = {"21","12"};
                    String div2[] = {"11","22"};
                    ResolverMatrizGauss(mult2,div2,_mtrz);
                    break;
                //endregion

                default:
                    break;
            }
            return "";
        }
        catch(Exception  e) {
            return  e.getMessage().toString();
        }
    }

    //endregion


    //region Vectores 3/3
    private static String FuerzaResultanteEnElEspacio (ArrayList<Double> _pDatos) {
        try {
            //region Fuerza Resultante en el espacio
            //region Paso 1
            Double X1 = _pDatos.get(0), Y1= _pDatos.get(1) ,Z1= _pDatos.get(2),X2= _pDatos.get(3), Y2= _pDatos.get(4),Z2= _pDatos.get(5), Fuerza = _pDatos.get(6), Magnitud=0.0;
            myListSteps.add(new csDetailStep(1,
                    "Ubicar las coordenadas exactas de los puntos a resolver dentro del espacio (Plano Cartesiano)",
                    "Concciendo las ubicaciones de los puntos se puede iniciar el proceso de resolución de la fuerza resultante en el espacio",
                    "Los valores son positivos desde el origen (0,0,0) hacia derecha (x),arriba (y) y afuera (z) y negativos con su respectivo opuesto"));
            //endregion

            //region Paso 2
            ArrayList<Double> _vectordistancia = new ArrayList<>();
            _vectordistancia.add(X1+(X2*-1));
            _vectordistancia.add(Y1+(Y2*-1));
            _vectordistancia.add(Z1+(Z2*-1));
            myListSteps.add(new csDetailStep(2,
                    "Se multiplica los valores equivalentes por cada eje del plano. Ejemplo x1 del punto A + x2 del punto B",
                    "Para obtener el vector  distancia entre las coordenadas (Posición Final -  Posición Inicial)",
                    String.format("%.1f - ( %.1f ) = %.1f \n%.1f - ( %.1f ) = %.1f \n %.1f - ( %.1f ) = %.1f \nVector Distancia = %.1fi %.1fj %.1fk",X1,X2,_vectordistancia.get(0),Y1,Y2,_vectordistancia.get(1),Z1,Z2,_vectordistancia.get(2),_vectordistancia.get(0),_vectordistancia.get(1),_vectordistancia.get(2))));
            //endregion

            //region Paso 3
            Double aux = 0.0;
            for (Double posVector:_vectordistancia) {
                Magnitud += posVector * posVector;
            }
            aux = Magnitud;
            Magnitud = Math.sqrt(Magnitud);
            myListSteps.add(new csDetailStep(3,
                    "Se aplico el teorema de pitagoras (la suma de los elementos elevados al cuadrados y a dicha sumatoria se le aplica la raíz cuadrada)",
                    "Obtener la Magnitud para obtener el vector Unitario",
                    String.format("√((%.1f)² (%.1f)² (%.1f)² )\n √(%.1f %.1f %.1f) \n √(%.1f) \n%.2f (Magnitud)",
                            _vectordistancia.get(0),_vectordistancia.get(1),_vectordistancia.get(2),
                            _vectordistancia.get(0)*_vectordistancia.get(0),_vectordistancia.get(1)*_vectordistancia.get(1),_vectordistancia.get(2)*_vectordistancia.get(2),
                            aux,Magnitud)));
            //endregion

            //region Paso 4
            ArrayList<Double> _vectorUnitario = new ArrayList<>();
            for (Double posVector: _vectordistancia) {
                Double _valor = posVector/Magnitud;
                _vectorUnitario.add(_valor);
            }
            myListSteps.add(new csDetailStep(4,
                    "Dividir el vector distancia entre la magnitud encontrada en el paso anterior",
                    "Para encontrar el vector unitario",
                    String.format("(%.1f i %.1f j %.1f k )/%.1f \n %.1f / %.1f = %f \n %.1f / %.1f = %f \n %.1f / %.1f = %f \n %fi %fj %fk",
                            _vectordistancia.get(0),_vectordistancia.get(1),_vectordistancia.get(2),Magnitud,
                            _vectordistancia.get(0),Magnitud,_vectordistancia.get(0)/Magnitud,
                            _vectordistancia.get(1),Magnitud,_vectordistancia.get(1)/Magnitud,
                            _vectordistancia.get(2),Magnitud,_vectordistancia.get(2)/Magnitud,
                            _vectorUnitario.get(0),_vectorUnitario.get(1),_vectorUnitario.get(2))));
            //endregion

            //region Paso 5
            ArrayList<Double> _vectorUXFuerza = new ArrayList<>();
            for (Double posVector: _vectorUnitario) {
                Double _valor = posVector * Fuerza;
                _vectorUXFuerza.add(_valor);
            }
            myListSteps.add(new csDetailStep(5,
                    "Multiplicar el vector unitario por la Fuerza que existe entre los dos puntos",
                    "Para encontrar la Fuerza resultante entre los puntos (las coordenadas ingresadas punto A y B)",
                    String.format("(%.1f i %.1f j %.1f k ) * %.1f \n %.1f * %.1f = %.1f \n %.1f * %.1f = %.1f \n %.1f * %.1f = %.1f \n %.1f i %.1f j %.1f k  (Resultado Entre punto A y B)",
                            _vectorUnitario.get(0),_vectorUnitario.get(1),_vectorUnitario.get(2),Fuerza,
                            _vectorUnitario.get(0),Fuerza,_vectorUnitario.get(0)* Fuerza,
                            _vectorUnitario.get(1),Fuerza,_vectorUnitario.get(1)*Fuerza,
                            _vectorUnitario.get(2),Fuerza,_vectorUnitario.get(2)*Fuerza,
                            _vectorUXFuerza.get(0),_vectorUXFuerza.get(1),_vectorUXFuerza.get(2))));
            //endregion

            //Resultado del Ejercicio
            return String.format("%.1f i  %.1f j  %.1f k",_vectorUXFuerza.get(0),_vectorUXFuerza.get(1),_vectorUXFuerza.get(2));
            //endregion
        }
        catch(Exception  e) {
            return  e.getMessage().toString();
        }
    }

    private static String ProductoConVectores(ArrayList<Double> _pDatos){

        try {

            int TipoEjercicio = _pDatos.get(0).intValue();
            switch (TipoEjercicio){

                //region Producto punto °
                case 1: //Producto punto °

                    //region Paso 1
                    myListSteps.add(new csDetailStep(1,
                            "Tomar la formula para la solución del ejercicio",
                            "Cada elemeto de cada vector se multiplica por su equivalente del otro vector",
                            " =>(Ax)(Bx) + (Ay)(By) + (Az)(Bz)"));
                    //endregion

                    //region Paso 2
                    myListSteps.add(new csDetailStep(2,
                            "Identificar los valores de cada vector",
                            "Ax = Ai; \nAy = Aj\nAz = Ak\nIgual forma con el otro vector",
                            String.format("Ax = %.1fi;\tBx = %.1fi;\nAx = %.1fi;\tBx = %.1fi;\nAx = %.1fi;\tBx = %.1fi;",
                                    _pDatos.get(1),_pDatos.get(4),_pDatos.get(2),_pDatos.get(5),_pDatos.get(3),_pDatos.get(6))));
                    //endregion

                    //region Paso 3
                    myListSteps.add(new csDetailStep(3,
                            "Agrupar los valores (coheficinetes) semejantes",
                            "Para poder multiplicarlos entre ellos",
                            String.format("(%.1f)(%.1f) + (%.1f)(%.1f) + (%.1f)(%.1f)\n" +
                                            "=> (%.1f) + (%.1f) + (%.1f)",
                                    _pDatos.get(1),_pDatos.get(4),_pDatos.get(2),_pDatos.get(5),_pDatos.get(3),_pDatos.get(6),
                                    _pDatos.get(1)*_pDatos.get(4),_pDatos.get(2)*_pDatos.get(5),_pDatos.get(3)*_pDatos.get(6))));
                    //endregion

                    //region Paso 4
                    myListSteps.add(new csDetailStep(4,
                            "Se suman los valores obtenidos del paso anterior",
                            "Para poder obtener el procuto punto entre los vectores",
                            String.format("=> (%.1f) + (%.1f) + (%.1f)\n" +
                                            "=> %s",
                                    _pDatos.get(1)*_pDatos.get(4),_pDatos.get(2)*_pDatos.get(5),_pDatos.get(3)*_pDatos.get(6),
                                    _pDatos.get(1)*_pDatos.get(4)+_pDatos.get(2)*_pDatos.get(5)+_pDatos.get(3)*_pDatos.get(6))));
                    //endregion
                    return "";
                //endregion

                //region Producto Cruz +
                case 2:
                    String dddd = "";
                     if ((_pDatos.get(7).intValue()) > 0) //tipo matriz
                     {
                         //region Paso 1
                         myListSteps.add(new csDetailStep(1,
                                 "Creaar la matriz madre ubicando los elementos en su posición correspondiente",
                                 "Facilitar las operaciones a nivel visual",
                                 String.format("║i   k   j║\n║ %.1f  %.1f  %.1f ║\n║ %.1f  %.1f  %.1f ║",
                                         _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),_pDatos.get(4),_pDatos.get(5),_pDatos.get(6))));
                         //endregion

                         //region Paso 2
                         myListSteps.add(new csDetailStep(2,
                                 "Ocultar la fila y columna de \"i\", obteniendo el siguiente resultado",
                                 "Obtener las submatriz para obtener los valores de  \"i\"",
                                 String.format("║ %.1f  %.1f ║\n║ %.1f  %.1f ║",
                                         _pDatos.get(2),_pDatos.get(3),_pDatos.get(5),_pDatos.get(6))));
                        //endregion

                         //region Paso 3
                         myListSteps.add(new csDetailStep(3,
                                 "Ocultar la fila y columna de \"j\", obteniendo el siguiente resultado",
                                 "Obtener las submatriz para obtener los valores de  \"j\"",
                                 String.format("║ %.1f  %.1f ║\n║ %.1f  %.1f ║",
                                         _pDatos.get(1),_pDatos.get(3),_pDatos.get(4),_pDatos.get(6))));
                         //endregion

                         //region Paso 4
                         myListSteps.add(new csDetailStep(4,
                                 "Ocultar la fila y columna de \"k\", obteniendo el siguiente resultado",
                                 "Obtener las submatriz para obtener los valores de  \"k\"",
                                 String.format("║ %.1f  %.1f ║\n║ %.1f  %.1f ║",
                                         _pDatos.get(1),_pDatos.get(2),_pDatos.get(4),_pDatos.get(5))));
                         //endregion

                         //region Paso 5
                         myListSteps.add(new csDetailStep(5,
                                 "Se multiplicó valores correspondientes de aplicando producto en \"x\" (segundo termino por regla es restado) ",
                                 "Para obtener el valor equivalente a \"i\"",
                                 String.format("(%.1f x %.1f) - (%.1f x %.1f)",
                                         _pDatos.get(2),_pDatos.get(6),_pDatos.get(5),_pDatos.get(3))));
                         //endregion

                         //region Paso 6
                         myListSteps.add(new csDetailStep(6,
                                 "Se multiplicó valores correspondientes de aplicando producto en \"x\" (segundo termino por regla es restado) ",
                                 "Para obtener el valor equivalente a \"j\"",
                                 String.format("(%.1f x %.1f) - (%.1f x %.1f)",
                                         _pDatos.get(1),_pDatos.get(6),_pDatos.get(4),_pDatos.get(3))));
                         //endregion

                         //region Paso 7
                         myListSteps.add(new csDetailStep(7,
                                 "Se multiplicó valores correspondientes de aplicando producto en \"x\" (segundo termino por regla es restado) ",
                                 "Para obtener el valor equivalente a \"k\"",
                                 String.format("(%.1f x %.1f) - (%.1f x %.1f)",
                                         _pDatos.get(1),_pDatos.get(5),_pDatos.get(4),_pDatos.get(2))));
                         //endregion

                         //region Paso 8
                         myListSteps.add(new csDetailStep(8,
                                 "Se realizo la multiplicación de los termino y se obtuvo el valor equivalente a \"i\"",
                                 "Para obtener el valor de \"i\"",
                                 String.format("(%.1f x %.1f) - (%.1f x %.1f)\n" +
                                                 "(%.1f) - (%.1f)\n" +
                                                 "=%.1f (valor de i)",
                                         _pDatos.get(2),_pDatos.get(6),_pDatos.get(5),_pDatos.get(3),
                                         (_pDatos.get(2)*_pDatos.get(6)),(_pDatos.get(5)*_pDatos.get(3)),
                                         _pDatos.get(2)*_pDatos.get(6)- (_pDatos.get(5)*_pDatos.get(3)))));
                         //endregion

                         //region Paso 9
                         myListSteps.add(new csDetailStep(9,
                                 "Se realizo la multiplicación de los termino y se obtuvo el valor equivalente a \"j\"",
                                 "Para obtener el valor de \"j\"",
                                 String.format("(%.1f x %.1f) - (%.1f x %.1f)\n" +
                                                 "-((%.1f) - (%.1f))\n" +
                                                 "=%.1f (valor de j)",
                                         _pDatos.get(1),_pDatos.get(6),_pDatos.get(4),_pDatos.get(3),
                                         (_pDatos.get(1)*_pDatos.get(6)),(_pDatos.get(4)*_pDatos.get(3)),
                                         (-1)*(_pDatos.get(1)*_pDatos.get(6)- (_pDatos.get(4)*_pDatos.get(3))))));
                         //endregion

                         //region Paso 10
                         myListSteps.add(new csDetailStep(10,
                                 "Se realizo la multiplicación de los termino y se obtuvo el valor equivalente a \"k\"",
                                 "Para obtener el valor de \"k\"",
                                 String.format("(%.1f x %.1f) - (%.1f x %.1f)\n" +
                                                 "(%.1f) - (%.1f)\n" +
                                                 "=%.1f (valor de k)",
                                         _pDatos.get(1),_pDatos.get(5),_pDatos.get(4),_pDatos.get(2),
                                         (_pDatos.get(1)*_pDatos.get(5)),(_pDatos.get(4)*_pDatos.get(2)),
                                         _pDatos.get(1)*_pDatos.get(5)- (_pDatos.get(4)*_pDatos.get(2)))));
                         //endregion

                         //region Paso 11
                         myListSteps.add(new csDetailStep(11,
                                 "Se muestra el resultado del ejercicio aplicando el método: producto cruz",
                                 "Representación del vector",
                                 String.format("%.1f i  %.1f j  %.1f k",
                                         _pDatos.get(2)*_pDatos.get(6)- (_pDatos.get(5)*_pDatos.get(3)),
                                         _pDatos.get(1)*_pDatos.get(6)- (_pDatos.get(4)*_pDatos.get(3)),
                                         _pDatos.get(1)*_pDatos.get(5)- (_pDatos.get(4)*_pDatos.get(2))
                                         )));
                         //endregion


                     }else {
                         //tipo multiplicacion
                         Integer Correlativo = 1;
                         ArrayList<csDiccionario> vctrUno = new ArrayList<>();
                         vctrUno.add(new csDiccionario("i",_pDatos.get(1),"null",0.0));
                         vctrUno.add(new csDiccionario("j",_pDatos.get(2),"null",0.0));
                         vctrUno.add(new csDiccionario("k",_pDatos.get(3),"null",0.0));

                         ArrayList<csDiccionario> vctrDos = new ArrayList<>();
                         vctrDos.add(new csDiccionario("i",_pDatos.get(4),"null",0.0));
                         vctrDos.add(new csDiccionario("j",_pDatos.get(5),"null",0.0));
                         vctrDos.add(new csDiccionario("k",_pDatos.get(6),"null",0.0));

                         //region Paso 1
                         myListSteps.add(new csDetailStep(Correlativo,
                                 "Analizar las reglas de solución de este método",
                                 "Para poder operar correctamente las valores entre de los vectores",
                                 String.format("i x i = 0;      i x j = k;      j x i = -k;\n" +
                                         "j x j = 0;      j x k = i;      k x j = -i;\n" +
                                         "k x k = 0;    k x i = j;    i x k = -j;")));
                         Correlativo++;
                         //endregion

                         //region Paso 2
                         myListSteps.add(new csDetailStep(Correlativo,
                                 "Representar la multiplicación de los vectores",
                                 "Se plantea la operación (producto cruz) mediante la forma (A)x(B)",
                                 String.format("(%.1f %.1f %.1f) x (%.1f %.1f %.1f)",
                                         _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),_pDatos.get(4),_pDatos.get(5),_pDatos.get(6))));
                         Correlativo++;
                         //endregion

                         //region Pasos 3,4 y 5
                         ArrayList<csDiccionario> ListaOperaciones = new ArrayList<>();
                         for (csDiccionario item1: vctrUno) {
                             String Operacion = "";
                             for (csDiccionario item2: vctrDos) {
                                 Operacion +="(" +item1.getValor() + item1.getTipo() +" * "+item2.getValor() + item2.getTipo()+") + ";
                                 ListaOperaciones.add(new csDiccionario(item1.getTipo(),item1.getValor(),item2.getTipo(),item2.getValor()));
                             }
                             Operacion = Operacion.substring(0, Operacion.length() - 2);
                             //region Paso 3,4 y 5
                             myListSteps.add(new csDetailStep(Correlativo,
                                     "Se multiplicó el valor de \""+item1.getTipo()+"\" por cada uno de los valores del segundo vector",
                                     "Procedimiento de regla",
                                     Operacion));
                             Correlativo++;
                             //endregion
                         }
                         //endregion

                         //region Paso 6
                         String Paso6 = "";
                         for (csDiccionario ope : ListaOperaciones) {
                             Paso6 += "("+ope.getValor()+ope.getTipo()+ " * " +ope.getValor2()+ope.getTipo2()+ ") + ";
                         }
                         Paso6 = Paso6.substring(0,Paso6.length() - 2 );
                         myListSteps.add(new csDetailStep(Correlativo,
                                 "Teniendo planteadas estas operaciones, se procede a relizar las multiplicaciones respectivas",
                                 "Para tener en cuenta cada una de las operaciones de los pasos anteriores",
                                 Paso6));
                         Correlativo++;
                         //endregion

                         //region Paso 7
                         ArrayList<csDiccionario> ListaReduccion = new ArrayList<>();
                         String Paso7 = "";
                         for (csDiccionario ope2: ListaOperaciones) {
                            if (ValidacionUnitarios(ope2.getTipo(),ope2.getTipo2()) != "0"){
                                Paso7 += "("+ ope2.getValor()*ope2.getValor2() + ")("+ValidacionUnitarios(ope2.getTipo(),ope2.getTipo2())+") + ";
                                ListaReduccion.add(new csDiccionario(ValidacionUnitarios(ope2.getTipo(),ope2.getTipo2()),(ope2.getValor()*ope2.getValor2()),"",0.0));
                            }else {
                                Paso7 += "0 + ";
                            }
                         }
                         Paso7 = Paso7.substring(0,Paso7.length() - 2);
                         myListSteps.add(new csDetailStep(Correlativo,
                                 "Se multiplicó cada uno de los términos aplicando las reglas del paso uno",
                                 "Reducir términos para sacar el resultado en términos de vector unitario",
                                 Paso7));
                         Correlativo++;
                         //endregion

                         //region Paso 8
                         String Paso8 = "";
                         ArrayList<csDiccionario> ListaFinal = new ArrayList<>();
                         for (csDiccionario multisigno : ListaReduccion) {
                            //Paso8 += (multisigno.getTipo().substring(0,1).equals("-"))? (multisigno.getValor()*(-1))+multisigno.getTipo().substring(1,multisigno.getTipo().length()) +" + " : multisigno.getValor()+multisigno.getTipo()+" + ";
                            if (multisigno.getTipo().substring(0,1).equals("-")){
                                Paso8 += (multisigno.getValor()*(-1))+multisigno.getTipo().substring(1,multisigno.getTipo().length()) +" + ";
                                if (multisigno.getValor() != 0){
                                    ListaFinal.add(new csDiccionario(multisigno.getTipo().substring(1,multisigno.getTipo().length()),(multisigno.getValor()*(-1)),"",0.0));
                                }
                            }else {
                                Paso8 += multisigno.getValor()+multisigno.getTipo()+" + ";
                                if (multisigno.getValor() != 0) {
                                    ListaFinal.add(new csDiccionario(multisigno.getTipo(), multisigno.getValor(), "", 0.0));
                                }
                            }
                         }
                         Paso8 = Paso8.substring(0,Paso8.length() - 2);

                         myListSteps.add(new csDetailStep(Correlativo,
                                 "Multiplicar los terminos según la ley de signos",
                                 "Para obtener los valores que posteriormente serán respresentados por cada término de vector unitario",
                                 Paso8));
                         Correlativo++;
                         //endregion

                         //region Paso 9
                         String Paso9 = "";
                         Double valori = 0.0, valorj =0.0, valork =0.0;
                         for (csDiccionario resp :ListaFinal) {
                             if (resp.getTipo().equals("i")){
                                 valori += resp.getValor();
                             }
                             if (resp.getTipo().equals("j")){
                                 valorj += resp.getValor();
                             }
                             if (resp.getTipo().equals("k")){
                                 valork += resp.getValor();
                             }
                             dddd += resp.getValor()+resp.getTipo() +"\n ";
                         }
                         Paso9 = "R./ "+valori + "i + "+valorj + "j + "+valork + "k" ;

                         myListSteps.add(new csDetailStep(Correlativo,
                                 "Se sumaron los términos que son semejantes (iguales en su base)",
                                 "Para obtener la respuesta final",
                                 Paso9));
                         Correlativo++;
                         //endregion
                     }
                    return dddd;
                //endregion

                //region Producto Mixto
                case 3:

                    //region Paso 1
                    myListSteps.add(new csDetailStep(1,
                            "Identificar los coeficientes de cada uno de los elementos de los vectores",
                            "Estos son los valores con lo que se va a realizar el ejercicio (Son los números que acompañan a las letras: \"i\", \"j\" y \"k\")",
                            String.format("A→ : %.1fi %.1fj %.1fk\n" +
                                            "B→: %.1fi %.1fj %.1fk\n" +
                                            "C→: %.1fi %.1fj %.1fk",
                                    _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),
                                    _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),
                                    _pDatos.get(7),_pDatos.get(8),_pDatos.get(9))));
                    //endregion

                    //region Paso 2
                    myListSteps.add(new csDetailStep(2,
                            "Se crea una matriz de 3x3 (filas y columnas respectivamente)",
                            "Se nos auxiliamos de una matriz las cuales sirven para describir sistemas de ecuaciones lineales",
                            String.format("║ %.1f %.1f %.1f ║\n" +
                                            "║ %.1f %.1f %.1f ║\n" +
                                            "║ %.1f %.1f %.1f ║",
                                    _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),
                                    _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),
                                    _pDatos.get(7),_pDatos.get(8),_pDatos.get(9))));
                    //endregion

                    //region Paso 3
                    myListSteps.add(new csDetailStep(3,
                            "Se crea una matriz de 3x5 (filas y columnas respectivamente) duplicando las primeras dos columnas",
                            "Para poder tomar todos los elementos de la matriz y aplicar el método de Gauss",
                            String.format("║ %.1f %.1f %.1f │ %.1f %.1f ║\n" +
                                            "║ %.1f %.1f %.1f │ %.1f %.1f ║\n" +
                                            "║ %.1f %.1f %.1f │ %.1f %.1f ║",
                                    _pDatos.get(1),_pDatos.get(2),_pDatos.get(3),_pDatos.get(1),_pDatos.get(2),
                                    _pDatos.get(4),_pDatos.get(5),_pDatos.get(6),_pDatos.get(4),_pDatos.get(5),
                                    _pDatos.get(7),_pDatos.get(8),_pDatos.get(9),_pDatos.get(7),_pDatos.get(8))));
                    //endregion

                    //region Paso 4
                    myListSteps.add(new csDetailStep(4,
                            "Dada la matriz 3x5, se multiplica en diagonal de forma descendente y correlativa hacia a la derecha y abajo, iniando desde valor ubicado en la posición 0,0 (fila 0 y columna 0)",
                            "Aplicación del método de solución, incluyendo los valores de las columnas aumentadas",
                            String.format("[(%.1f)(%.1f)(%.1f) + (%.1f)(%.1f)(%.1f) + (%.1f)(%.1f)(%.1f) ]",
                                    _pDatos.get(1),_pDatos.get(5),_pDatos.get(9),
                                    _pDatos.get(2),_pDatos.get(6),_pDatos.get(7),
                                    _pDatos.get(3),_pDatos.get(4),_pDatos.get(8))));
                    //endregion

                    //region Paso 5
                    myListSteps.add(new csDetailStep(5,
                            "Dada la matriz 3x5, se multiplica en diagonal ascendente y correlativa hacia la derecha y arriba, iniando desde valor ubicado en la posición 3,0 (fila 3 y columna 0) cambiandole el signo a dicho producto",
                            "Aplicación del método de solución, incluyendo valores ",
                            String.format("[(%.1f)(%.1f)(%.1f) + (%.1f)(%.1f)(%.1f) + (%.1f)(%.1f)(%.1f) ] - [(%.1f)(%.1f)(%.1f) + (%.1f)(%.1f)(%.1f) + (%.1f)(%.1f)(%.1f) ]",
                                    _pDatos.get(1),_pDatos.get(5),_pDatos.get(9),
                                    _pDatos.get(2),_pDatos.get(6),_pDatos.get(7),
                                    _pDatos.get(3),_pDatos.get(4),_pDatos.get(8),
                                    _pDatos.get(7),_pDatos.get(5),_pDatos.get(3),
                                    _pDatos.get(8),_pDatos.get(6),_pDatos.get(1),
                                    _pDatos.get(9),_pDatos.get(4),_pDatos.get(2))));
                    //endregion

                    //region Paso 6
                    myListSteps.add(new csDetailStep(6,
                            "Se realizaron las multiplicaciones correspondientes a raíz de las operaciones planteadas en los dos pasos anteriores",
                            "Para reducir términos y obtener los valores de los productos de la matriz",
                            String.format("[(%.1f) + (%.1f) + (%.1f) ] - [(%.1f) + (%.1f) + (%.1f) ]",
                                    (_pDatos.get(1) *_pDatos.get(5) *_pDatos.get(9)),
                                    (_pDatos.get(2) *_pDatos.get(6) *_pDatos.get(7)),
                                    (_pDatos.get(3) * _pDatos.get(4) *_pDatos.get(8)),
                                    (_pDatos.get(7)*_pDatos.get(5)*_pDatos.get(3)),
                                    (_pDatos.get(8)*_pDatos.get(6)*_pDatos.get(1)),
                                    (_pDatos.get(9)*_pDatos.get(4)*_pDatos.get(2))
                            )));
                    //endregion

                    //region Paso 7
                    myListSteps.add(new csDetailStep(7,
                            "Se realizó la multiplicación del signo negativo que afecta los términos surgidos del paso 5",
                            "Para obtener los valores de los productos de la matriz, los cuales serán sumados posteriormente",
                            String.format("[(%.1f) + (%.1f) + (%.1f) + (%.1f) + (%.1f) + (%.1f) ]",
                                    (_pDatos.get(1) *_pDatos.get(5) *_pDatos.get(9)),
                                    (_pDatos.get(2) *_pDatos.get(6) *_pDatos.get(7)),
                                    (_pDatos.get(3) * _pDatos.get(4) *_pDatos.get(8)),
                                    ((-1)*(_pDatos.get(7)*_pDatos.get(5)*_pDatos.get(3))),
                                    ((-1)*(_pDatos.get(8)*_pDatos.get(6)*_pDatos.get(1))),
                                    ((-1)*(_pDatos.get(9)*_pDatos.get(4)*_pDatos.get(2)))
                            )));
                    //endregion

                    //region Paso 8
                    myListSteps.add(new csDetailStep(8,
                            "Se suma todos los términos obtenidos de los pasos anteriores",
                            "Para obtener el resultado del producto mixto de vectores",
                            String.format("R./ (%.1f)",
                                    ((_pDatos.get(1) *_pDatos.get(5) *_pDatos.get(9))+
                                    (_pDatos.get(2) *_pDatos.get(6) *_pDatos.get(7))+
                                    (_pDatos.get(3) * _pDatos.get(4) *_pDatos.get(8))+
                                    ((-1)*(_pDatos.get(7)*_pDatos.get(5)*_pDatos.get(3)))+
                                    ((-1)*(_pDatos.get(8)*_pDatos.get(6)*_pDatos.get(1)))+
                                    ((-1)*(_pDatos.get(9)*_pDatos.get(4)*_pDatos.get(2))))
                            )));
                    //endregion
                    return "";

                //endregion

                default:
                    return "default";
            }
        }
        catch(Exception  e) {
            return e.getMessage().toString();
        }
    }

    public static String VectorProyeccion(ArrayList<csVectorProyeccion> myListaVectores){
        String _aux = "";
        ArrayList<csDiccionario> RepreRectangular = new ArrayList<>();
        try{
            int Correlativo = 1;
            //region Como se sacan el angulo aboluto
            for (csVectorProyeccion myVector: myListaVectores ) {
                csDiccionario _calculoAngulo = new csDiccionario();
                _calculoAngulo = ValidacionVectorProyeccion(myVector);
                myListSteps.add(new csDetailStep(Correlativo,
                        "Se observó que el vector estaba en el cuadrante "+myVector.getCuadrante()+", la orientación era "+myVector.getDireccion()+", que apunta hacia "+myVector.getApunta()+ " y el ángulo esta respecto al eje \""+myVector.getAngRespectoA()+"\"",
                        "Para poder calcular el ángulo absoluto ("+_calculoAngulo.getValor()+"°)",
                         _calculoAngulo.getValor() +"° : "+ _calculoAngulo.getTipo()));
                Correlativo++;
            }
            //endregion

            //region Calcular Vx y Vy del vector
            for (csVectorProyeccion myVector: myListaVectores ) {
                csDiccionario _calculoAngulo = new csDiccionario();
                _calculoAngulo = ValidacionVectorProyeccion(myVector);

                myListSteps.add(new csDetailStep(Correlativo,
                        "Se multiplicó la fuerza "+ myVector.getFuerza() + " N por seno y coseno de "+_calculoAngulo.getValor()+"°",
                        "Este procedimiento permite concer los valores de Vx y Vy (Representación rectangular)",
                        String.format("Vx = %.1f x Cos(%.1f) = %.2f i\n" +
                                      "Vy = %.1f x Sen(%.1f) = %.2f j",
                                myVector.getFuerza(),_calculoAngulo.getValor(),((myVector.getFuerza()) * Math.cos(Math.toRadians(_calculoAngulo.getValor()))),
                                myVector.getFuerza(),_calculoAngulo.getValor(),((myVector.getFuerza()) * Math.sin(Math.toRadians(_calculoAngulo.getValor()))))
                ));
                RepreRectangular.add(new csDiccionario("Vx",((myVector.getFuerza()) * Math.cos(Math.toRadians(_calculoAngulo.getValor()))),"Vy",((myVector.getFuerza()) * Math.sin(Math.toRadians(_calculoAngulo.getValor())))));
                Correlativo++;
            }
            //endregion

            //region Encontrando el valor de R (Suma de Vx y Vy)
            String sumaVx = "",sumaVy = "";
            Double valorVx = 0.0, valorVy =0.0;
            for (csDiccionario vec : RepreRectangular) {
                sumaVx += String.format("(%.2f) +",vec.getValor());
                sumaVy += String.format("(%.2f) +",vec.getValor2());
                valorVx += vec.getValor();
                valorVy += vec.getValor2();
            }
            sumaVx = sumaVx.substring(0,sumaVx.length() - 1);
            sumaVy = sumaVy.substring(0,sumaVy.length() - 1);
            myListSteps.add(new csDetailStep(Correlativo,
                    "Sumar los valores encontrados en los pasos anteriores, tanto de Vx como de Vy",
                    "Encontrar el valor de R",
                    String.format("Vx = %s\n" +
                                  "Vx = %.2f\n\n" +
                                  "Vy = %s\n" +
                                  "Vx = %.2f\n\n" +
                                    "R = %.2fi %s%.2fj",
                            sumaVx,valorVx,sumaVy,valorVy,valorVx,(valorVy > 0)? "+ ":"",valorVy)
            ));
            Correlativo++;
            //endregion

            //region Pitagoras para enconcontrar la fuerza
            myListSteps.add(new csDetailStep(Correlativo,
                    "Se aplicó el teorema de Pitagoras y la tangente inverza con los valores calculados en el paso anterior (Valor de R)",
                    "Pitágoras para encontrar la fuerza del vector proyección (expresado en Newton) y tangente inverza para enonctrar el ángulo de la fuerza en el espacio",
                    String.format("R = √[(%.2f)² + (%.2f)²]\n" +
                                    "R = %.2f N\n\n" +
                                    "θr = tan⁻¹[(%.2f)/(%.2f)]\n" +
                                    "θr = %.2f°",
                            valorVx,valorVy,Math.sqrt((Math.pow(valorVx,2) + Math.pow(valorVy,2))),
                            valorVy,valorVx,Math.atan2(valorVx,valorVy))
            ));
            Correlativo++;
            //endregion

            //region Mostrar Respuesta
            myListSteps.add(new csDetailStep(Correlativo,
                    "Se le da formato a la respuesta del ejercicio",
                    "Es una de las formas para indicar de manera simbólica el resultado",
                    String.format("R = %.2f N <) %.2f°",
                            Math.sqrt((Math.pow(valorVx,2) + Math.pow(valorVy,2))),
                            Math.atan2(valorVx,valorVy))
            ));
            Correlativo++;
            _aux = String.format("%.2f\n%.2f",Math.atan2(valorVy,valorVx),Math.atan2(valorVx,valorVy));
            //endregion

        }catch(Exception  e) {
            return e.getMessage().toString();
        }
        return _aux;
    }
    //endregion


    //region MetodoComplementarios ============================================================================

    //region MetodosComplementoNUMEROS_COMPLEJOS
    private static String Exponente_I(Double _pExp){
        String _result = "";
        int _exp = _pExp.intValue();
        _result = (_exp%4==0)?"°":(_exp%4==1)?"i":(_exp%4==2)?"-1":(_exp%4==3)?"-i":(_exp%4==4)?"1":"";
        return _result;
    }
    //endregion


    //region ComplementoMATRICES

    //region Matriz de Gauss
    private static void ResolverMatrizGauss(String _pCeldasToCeros[],String _pCeldasToUnos[],ArrayList<csMatriz> _mtrz){

        try{
            Integer TamanoEcuacion = _mtrz.get(1).getTamano();
            ArrayList<Double> CoeficientesEcuacion = new ArrayList<>();
            int Correlativo = 1, filaA = 0, filaB = 0,ReduccionEcuacion = 1;
            Integer valor = 1;
            String posicion="",matriz_modificada = "",multipliacionEcuaciones = "";
            csMatriz EcuA = new csMatriz();
            csMatriz EcuB = new csMatriz();
            Double _numeroMayor = 0.0;

            Double Coe1 = 1.0, Coe2 = 1.0, _newW = 0.0,_newX = 0.0,_newY = 0.0,_newZ = 0.0,_newIgual = 0.0;
            csDiccionario Coeficientes = new csDiccionario();

            //region Multiplicacion de coeficientes, para que den los 0
            for (int i = 0; i < _pCeldasToCeros.length; i++) {

                _numeroMayor = -999999999999999999999999999999999999999999999999999999999999999.0;
                Correlativo++;
                filaA = 0;

                posicion = _pCeldasToCeros[i];
                filaA = Integer.parseInt(posicion.substring(0, 1));
                filaB = Integer.parseInt(posicion.substring(1, posicion.length()));

                EcuA = _mtrz.get(filaA);
                EcuB = _mtrz.get(filaB);

                Coeficientes = CoeficienteMultiplicar(_mtrz, EcuA, EcuB);
                Coeficientes = Validacion(Coeficientes, _mtrz, filaA, filaB);


                Coe1 = Coeficientes.getValor();
                Coe2 = Coeficientes.getValor2();

                //region Proceso de multipliacion Visual
                if (EcuA.getTamano().equals(2)){

                    multipliacionEcuaciones = String.format("Fila%d×%.1f: %.1f  %.1f = %.1f\n" +
                                                            "Fila%d×%.1f: %.1f  %.1f = %.1f\n" +
                                                            "Suma filas: %.1f  %.1f = %.1f\n\n",
                                                            filaA,Coe2,(EcuA.getX() * Coe2),(EcuA.getY() * Coe2),(EcuA.getIgual() * Coe2),
                                                            filaB,Coe1,(EcuB.getX() * Coe1),(EcuB.getY() * Coe1),(EcuB.getIgual() * Coe1),
                                                            ((EcuA.getX() * Coe2)+(EcuB.getX() * Coe1)),((EcuA.getY() * Coe2)+(EcuB.getY() * Coe1)),((EcuA.getIgual() * Coe2)+(EcuB.getIgual() * Coe1))
                    );

                }else
                    {
                        if (EcuA.getTamano().equals(3)){
                            multipliacionEcuaciones = String.format("Fila%d×%.1f: %.1f  %.1f  %.1f = %.1f\n" +
                                                                    "Fila%d×%.1f: %.1f  %.1f  %.1f = %.1f\n" +
                                                                    "Suma filas: %.1f  %.1f  %.1f = %.1f\n\n",
                                                                    filaA,Coe2,(EcuA.getX() * Coe2),(EcuA.getY() * Coe2),(EcuA.getZ() * Coe2),(EcuA.getIgual() * Coe2),
                                                                    filaB,Coe1,(EcuB.getX() * Coe1),(EcuB.getY() * Coe1),(EcuB.getZ() * Coe1),(EcuB.getIgual() * Coe1),
                                                                    ((EcuA.getX() * Coe2)+(EcuB.getX() * Coe1)),((EcuA.getY() * Coe2)+(EcuB.getY() * Coe1)),((EcuA.getZ() * Coe2)+(EcuB.getZ() * Coe1)),((EcuA.getIgual() * Coe2)+(EcuB.getIgual() * Coe1))
                                    );
                        }else {
                            if (EcuA.getTamano().equals(4)){
                                multipliacionEcuaciones = String.format("Fila%d×%.1f: %.1f  %.1f  %.1f  %.1f = %.1f\n" +
                                                                        "Fila%d×%.1f: %.1f  %.1f  %.1f  %.1f = %.1f\n" +
                                                                        "Suma filas: %.1f  %.1f  %.1f  %.1f = %.1f\n\n",
                                        filaA,Coe2,(EcuA.getW() * Coe2),(EcuA.getX() * Coe2),(EcuA.getY() * Coe2),(EcuA.getZ() * Coe2),(EcuA.getIgual() * Coe2),
                                        filaB,Coe1,(EcuB.getW() * Coe1),(EcuB.getX() * Coe1),(EcuB.getY() * Coe1),(EcuB.getZ() * Coe1),(EcuB.getIgual() * Coe1),
                                        ((EcuA.getW() * Coe2)+(EcuB.getW() * Coe1)),((EcuA.getX() * Coe2)+(EcuB.getX() * Coe1)),((EcuA.getY() * Coe2)+(EcuB.getY() * Coe1)),((EcuA.getZ() * Coe2)+(EcuB.getZ() * Coe1)),((EcuA.getIgual() * Coe2)+(EcuB.getIgual() * Coe1))
                                );
                            }
                        }
                    }
                //endregion

                _newW = ((EcuA.getW() * Coe2) + (EcuB.getW() * Coe1));
                _newX = ((EcuA.getX() * Coe2) + (EcuB.getX() * Coe1));
                _newY = ((EcuA.getY() * Coe2) + (EcuB.getY() * Coe1));
                _newZ = ((EcuA.getZ() * Coe2) + (EcuB.getZ() * Coe1));
                _newIgual = ((EcuA.getIgual() * Coe2) + (EcuB.getIgual() * Coe1));

                csMatriz flAfectada = new csMatriz();
                flAfectada.setIdEcuacion(filaA);
                flAfectada.setW(_newW);
                flAfectada.setX(_newX);
                flAfectada.setY(_newY);
                flAfectada.setZ(_newZ);
                flAfectada.setIgual(_newIgual);
                flAfectada.setTamano(TamanoEcuacion);

                //region Llenado de Lista coeficientes de la ecuación afectada
                CoeficientesEcuacion.add(_newW);
                CoeficientesEcuacion.add(_newX);
                CoeficientesEcuacion.add(_newY);
                CoeficientesEcuacion.add(_newZ);
                CoeficientesEcuacion.add(_newIgual);
                //endregion

                //fila a modificar, valores
                _mtrz.set(filaA, flAfectada);

                //region Impresion de Ceros
                matriz_modificada = "";
                if (TamanoEcuacion.equals(2)){

                    for (int ii = 1; ii < _mtrz.size(); ii++) {
                        matriz_modificada += String.format("║ %.1f  %.1f │ %.1f ║\n",
                                _mtrz.get(ii).getX(), _mtrz.get(ii).getY(), _mtrz.get(ii).getIgual());
                    }

                }else {
                    if (TamanoEcuacion.equals(3)){

                        for (int ii = 1; ii < _mtrz.size(); ii++) {
                            matriz_modificada += String.format("║ %.1f  %.1f  %.1f │ %.1f ║\n",
                                    _mtrz.get(ii).getX(), _mtrz.get(ii).getY(),
                                    _mtrz.get(ii).getZ(), _mtrz.get(ii).getIgual());
                        }

                    }else {
                        if (TamanoEcuacion.equals(4)){

                            for (int ii = 1; ii < _mtrz.size(); ii++) {
                                matriz_modificada += String.format("║ %.1f  %.1f  %.1f  %.1f │ %.1f ║\n",
                                        _mtrz.get(ii).getW(), _mtrz.get(ii).getX(), _mtrz.get(ii).getY(),
                                        _mtrz.get(ii).getZ(), _mtrz.get(ii).getIgual());
                            }
                        }
                    }
                }
                myListSteps.add(new csDetailStep(Correlativo,
                        String.format("Multiplicar la fila %d×%.1f y la fila %d×%.1f",filaA,Coe2,filaB,Coe1),
                        "Para que la suma de la posición "+ filaA + filaB + " (fila y columna respectivamente) nos de como resultado cero",
                        String.format("%s %s", multipliacionEcuaciones, matriz_modificada)
                ));
                //endregion

                //region Validacion Visual (Reduccion numeros grandes)
                if ((flAfectada.getW().toString().length()>5) ||(flAfectada.getX().toString().length()>5) ||(flAfectada.getY().toString().length()>5) ||
                        (flAfectada.getZ().toString().length()>5) ||(flAfectada.getIgual().toString().length()>5)){
                    //_numeroMayor = -999999999999999999999999999999999999999999999999999999999999999.0;
                    for (Double item : CoeficientesEcuacion) {
                        if(item > _numeroMayor){
                            _numeroMayor = item;
                        }
                    }

                    if (_numeroMayor > 0 || _numeroMayor < 0){
                        //region Proceso de Divison Visual
                        if (flAfectada.getTamano().equals(2)){

                            multipliacionEcuaciones = String.format("Fila%d ÷ %.1f: %.1f  %.1f = %.1f\n\n",
                                    filaA,_numeroMayor,(flAfectada.getX() / _numeroMayor),(flAfectada.getY() / _numeroMayor),(flAfectada.getIgual() / _numeroMayor)
                            );

                        }else
                        {
                            if (flAfectada.getTamano().equals(3)){
                                multipliacionEcuaciones = String.format("Fila%d ÷ %.1f: %.1f  %.1f  %.1f = %.1f\n\n",
                                        filaA,_numeroMayor,(flAfectada.getX() / _numeroMayor),(flAfectada.getY() / _numeroMayor),(flAfectada.getZ() / _numeroMayor),(flAfectada.getIgual() / _numeroMayor)
                                );
                            }else {
                                if (flAfectada.getTamano().equals(4)){
                                    multipliacionEcuaciones = String.format("Fila%d ÷ %.1f: %.1f  %.1f  %.1f  %.1f = %.1f\n\n",
                                            filaA,_numeroMayor,(flAfectada.getW() / _numeroMayor),(flAfectada.getX() / _numeroMayor),(flAfectada.getY() / _numeroMayor),(flAfectada.getZ() / _numeroMayor),(flAfectada.getIgual() / _numeroMayor)
                                    );
                                }
                            }
                        }
                        //endregion

                        _newW = (flAfectada.getW()/_numeroMayor);
                        _newX = (flAfectada.getX()/_numeroMayor);
                        _newY = (flAfectada.getY()/_numeroMayor);
                        _newZ = (flAfectada.getZ()/_numeroMayor);
                        _newIgual = (flAfectada.getIgual()/_numeroMayor);

                        csMatriz flDividida = new csMatriz();
                        flDividida.setIdEcuacion(filaA);
                        flDividida.setW(_newW);
                        flDividida.setX(_newX);
                        flDividida.setY(_newY);
                        flDividida.setZ(_newZ);
                        flDividida.setIgual(_newIgual);
                        flDividida.setTamano(TamanoEcuacion);

                        //fila a modificar, valores
                        _mtrz.set(filaA, flDividida);

                        //region Impresion de Matriz si se divide
                        matriz_modificada = "";
                        if (TamanoEcuacion.equals(2)){

                            for (int y = 1; y < _mtrz.size(); y++) {
                                matriz_modificada += String.format("║ %.1f  %.1f │ %.1f ║\n",
                                        _mtrz.get(y).getX(), _mtrz.get(y).getY(), _mtrz.get(y).getIgual());
                            }

                        }else {
                            if (TamanoEcuacion.equals(3)){

                                for (int x = 1; x < _mtrz.size(); x++) {
                                    matriz_modificada += String.format("║ %.1f  %.1f  %.1f │ %.1f ║\n",
                                            _mtrz.get(x).getX(), _mtrz.get(x).getY(),
                                            _mtrz.get(x).getZ(), _mtrz.get(x).getIgual());
                                }

                            }else {
                                if (TamanoEcuacion.equals(4)){

                                    for (int w = 1; w < _mtrz.size(); w++) {
                                        matriz_modificada += String.format("║ %.1f  %.1f  %.1f  %.1f │ %.1f ║\n",
                                                _mtrz.get(w).getW(), _mtrz.get(w).getX(), _mtrz.get(w).getY(),
                                                _mtrz.get(w).getZ(), _mtrz.get(w).getIgual());
                                    }
                                }
                            }
                        }
                        Correlativo++;
                        myListSteps.add(new csDetailStep(Correlativo,
                                String.format("Dividir la Fila %d ÷ %.1f",filaA,_numeroMayor),
                                "Para poder realizar futuras operaciones con números pequeños",
                                String.format("%s %s",multipliacionEcuaciones,matriz_modificada)
                        ));

                        //endregion
                    }

                }
                //endregion
            }
            //endregion

            //region Division para que den los 1
            for (int i=0; i < _pCeldasToUnos.length;i++){
                multipliacionEcuaciones="";
                Correlativo++;
                filaA = 0;

                posicion = _pCeldasToUnos[i];
                filaA = Integer.parseInt(posicion.substring(0,1));
                filaB = Integer.parseInt(posicion.substring(1,posicion.length()));

                EcuA = _mtrz.get(filaA);
                EcuB = _mtrz.get(filaB);
                Coeficientes.setValor(1.0);
                Coeficientes.setValor2(1.0);
                Coe1 = 1.0; Coe2=1.0;
                Coeficientes = CoeficienteMultiplicar(_mtrz, EcuA, EcuB);

                Coe1 = Coeficientes.getValor();
                Coe2 = Coeficientes.getValor2();


                //region Proceso de Divison Visual
                if (EcuA.getTamano().equals(2)){

                    multipliacionEcuaciones = String.format("Fila%d ÷ %.1f: %.1f  %.1f = %.1f\n\n",
                            filaA,Coe1,(EcuA.getX() / Coe1),(EcuA.getY() / Coe1),(EcuA.getIgual() / Coe1)
                    );

                }else
                {
                    if (EcuA.getTamano().equals(3)){
                        multipliacionEcuaciones = String.format("Fila%d ÷ %.1f: %.1f  %.1f  %.1f = %.1f\n\n",
                                filaA,Coe1,(EcuA.getX() / Coe1),(EcuA.getY() / Coe1),(EcuA.getZ() / Coe1),(EcuA.getIgual() / Coe1)
                        );
                    }else {
                        if (EcuA.getTamano().equals(4)){
                            multipliacionEcuaciones = String.format("Fila%d ÷ %.1f: %.1f  %.1f  %.1f  %.1f = %.1f\n\n",
                                    filaA,Coe1,(EcuA.getW() / Coe1),(EcuA.getX() / Coe1),(EcuA.getY() / Coe1),(EcuA.getZ() / Coe1),(EcuA.getIgual() / Coe1)
                            );
                        }
                    }
                }
                //endregion


                _newW = (EcuA.getW() / Coe1);
                _newX = (EcuA.getX() / Coe1);
                _newY = (EcuA.getY() / Coe1);
                _newZ = (EcuA.getZ() / Coe1);
                _newIgual = (EcuA.getIgual() / Coe1);

                csMatriz flAfectada = new csMatriz();
                flAfectada.setIdEcuacion(filaA);
                flAfectada.setW(_newW);
                flAfectada.setX(_newX);
                flAfectada.setY(_newY);
                flAfectada.setZ(_newZ);
                flAfectada.setIgual(_newIgual);
                flAfectada.setTamano(TamanoEcuacion);

                //fila a modificar, valores
                _mtrz.set(filaA, flAfectada);


                //region Imprecion de Unos
                matriz_modificada = "";
                if (TamanoEcuacion.equals(2)){

                    for (int iii = 1; iii < _mtrz.size(); iii++) {
                        matriz_modificada += String.format("║ %.1f  %.1f │ %.1f ║\n",
                                _mtrz.get(iii).getX(), _mtrz.get(iii).getY(), _mtrz.get(iii).getIgual());
                    }

                }else {

                    if (TamanoEcuacion.equals(3)){

                        for (int iii = 1; iii < _mtrz.size(); iii++) {
                            matriz_modificada += String.format("║ %.1f  %.1f  %.1f │ %.1f ║\n",
                                    _mtrz.get(iii).getX(), _mtrz.get(iii).getY(),
                                    _mtrz.get(iii).getZ(), _mtrz.get(iii).getIgual());
                        }

                    }else {
                        if (TamanoEcuacion.equals(4)){

                            for (int iii = 1; iii < _mtrz.size(); iii++) {
                                matriz_modificada += String.format("║ %.1f  %.1f  %.1f  %.1f │ %.1f ║\n",
                                        _mtrz.get(iii).getW(), _mtrz.get(iii).getX(), _mtrz.get(iii).getY(),
                                        _mtrz.get(iii).getZ(), _mtrz.get(iii).getIgual());
                            }
                        }
                    }
                }
                myListSteps.add(new csDetailStep(Correlativo,
                        String.format("Dividir la fila %d entre %.1f",filaA,Coe1),
                        "Para que la posición" + filaA + filaB + " (fila y columna respectivamente) nos de como resultado uno",
                        String.format("%s %s", multipliacionEcuaciones, matriz_modificada)
                ));
                //endregion

            }
            //endregion
        }
        catch (Exception  e){
            e.getMessage();
        }
    }
    private static csDiccionario CoeficienteMultiplicar(ArrayList<csMatriz> mtrz, csMatriz ecuA, csMatriz ecuB) {

        Integer TamanoMatriz = mtrz.get(1).getTamano();
        csDiccionario result = new csDiccionario();
        if (TamanoMatriz.equals(2)){
            //=======
            if (ecuB.getIdEcuacion().equals(1)){
                result.setValor(ecuA.getX());
                result.setValor2(ecuB.getX());
            }else {
                if (ecuB.getIdEcuacion().equals(2)){
                    result.setValor(ecuA.getY());
                    result.setValor2(ecuB.getY());
                }
            }
            //=======
        }
        else {
            if (TamanoMatriz.equals(3)){

                //=======
                if (ecuB.getIdEcuacion().equals(1)){
                    result.setValor(ecuA.getX());
                    result.setValor2(ecuB.getX());
                }else {
                    if (ecuB.getIdEcuacion().equals(2)){
                        result.setValor(ecuA.getY());
                        result.setValor2(ecuB.getY());
                    }else {
                        if (ecuB.getIdEcuacion().equals(3)){
                            result.setValor(ecuA.getZ());
                            result.setValor2(ecuB.getZ());
                        }
                    }
                }
                //=======


            }else {
                if (TamanoMatriz.equals(4)){
                    //=======
                    if (ecuB.getIdEcuacion().equals(1)){
                        result.setValor(ecuA.getW());
                        result.setValor2(ecuB.getW());
                    }else {
                        if (ecuB.getIdEcuacion().equals(2)){
                            result.setValor(ecuA.getX());
                            result.setValor2(ecuB.getX());
                        }else {
                            if (ecuB.getIdEcuacion().equals(3)){
                                result.setValor(ecuA.getY());
                                result.setValor2(ecuB.getY());
                            }else {
                                if (ecuB.getIdEcuacion().equals(4)){
                                    result.setValor(ecuA.getZ());
                                    result.setValor2(ecuB.getZ());
                                }
                            }
                        }
                    }
                    //=======
                }
            }
        }
        result.setTipo("");
        result.setTipo2("");
        return result;
    }
    private static csDiccionario Validacion(csDiccionario coeficientes, ArrayList<csMatriz> mtrz, int filaA, int filaB) {

        Integer TamanoMatriz = mtrz.get(1).getTamano();

        csDiccionario Result = new csDiccionario();
        csMatriz ecuacion1 = new csMatriz();
        csMatriz ecuacion2 = new csMatriz();

        ecuacion1 = mtrz.get(filaA);
        ecuacion2 = mtrz.get(filaB);

        if (TamanoMatriz.equals(2)){
            switch (filaB){

                case 1:

                    if ((ecuacion1.getX()*coeficientes.getValor2() + ecuacion2.getX()*coeficientes.getValor()) == 0.0){
                        Result.setValor(coeficientes.getValor());
                        Result.setValor2(coeficientes.getValor2());
                    }else {
                        if (((ecuacion1.getX()*coeficientes.getValor2()*-1) + ecuacion2.getX()*coeficientes.getValor()) == 0.0){
                            Result.setValor(coeficientes.getValor());
                            Result.setValor2(coeficientes.getValor2()*-1);
                        }else {
                            if ((ecuacion1.getX()*coeficientes.getValor2() + (ecuacion2.getX()*coeficientes.getValor()*-1)) == 0.0){
                                Result.setValor(coeficientes.getValor()*-1);
                                Result.setValor2(coeficientes.getValor2());
                            }else {
                                if (((ecuacion1.getX()*coeficientes.getValor2()*-1) + (ecuacion2.getX()*coeficientes.getValor()*-1)) == 0.0){
                                    Result.setValor(coeficientes.getValor()*-1);
                                    Result.setValor2(coeficientes.getValor2()*-1);
                                }
                            }
                        }
                    }

                    break;

                case 2:

                    if ((ecuacion1.getY()*coeficientes.getValor2() + ecuacion2.getY()*coeficientes.getValor()) == 0.0){
                        Result.setValor(coeficientes.getValor());
                        Result.setValor2(coeficientes.getValor2());
                    }else {
                        if (((ecuacion1.getY()*coeficientes.getValor2()*-1) + ecuacion2.getY()*coeficientes.getValor()) == 0.0){
                            Result.setValor(coeficientes.getValor());
                            Result.setValor2(coeficientes.getValor2()*-1);
                        }else {
                            if ((ecuacion1.getY()*coeficientes.getValor2() + (ecuacion2.getY()*coeficientes.getValor()*-1)) == 0.0){
                                Result.setValor(coeficientes.getValor()*-1);
                                Result.setValor2(coeficientes.getValor2());
                            }else {
                                if (((ecuacion1.getY()*coeficientes.getValor2()*-1) + (ecuacion2.getY()*coeficientes.getValor()*-1)) == 0.0){
                                    Result.setValor(coeficientes.getValor()*-1);
                                    Result.setValor2(coeficientes.getValor2()*-1);
                                }
                            }
                        }
                    }

                    break;

                default:
                    Result.setValor(-1.0);
                    Result.setValor2(1.0);
                    break;
            }
        }
        else {
            if (TamanoMatriz.equals(3)){
                switch (filaB){

                    case 1:

                        if ((ecuacion1.getX()*coeficientes.getValor2() + ecuacion2.getX()*coeficientes.getValor()) == 0.0){
                            Result.setValor(coeficientes.getValor());
                            Result.setValor2(coeficientes.getValor2());
                        }else {
                            if (((ecuacion1.getX()*coeficientes.getValor2()*-1) + ecuacion2.getX()*coeficientes.getValor()) == 0.0){
                                Result.setValor(coeficientes.getValor());
                                Result.setValor2(coeficientes.getValor2()*-1);
                            }else {
                                if ((ecuacion1.getX()*coeficientes.getValor2() + (ecuacion2.getX()*coeficientes.getValor()*-1)) == 0.0){
                                    Result.setValor(coeficientes.getValor()*-1);
                                    Result.setValor2(coeficientes.getValor2());
                                }else {
                                    if (((ecuacion1.getX()*coeficientes.getValor2()*-1) + (ecuacion2.getX()*coeficientes.getValor()*-1)) == 0.0){
                                        Result.setValor(coeficientes.getValor()*-1);
                                        Result.setValor2(coeficientes.getValor2()*-1);
                                    }
                                }
                            }
                        }

                        break;

                    case 2:

                        if ((ecuacion1.getY()*coeficientes.getValor2() + ecuacion2.getY()*coeficientes.getValor()) == 0.0){
                            Result.setValor(coeficientes.getValor());
                            Result.setValor2(coeficientes.getValor2());
                        }else {
                            if (((ecuacion1.getY()*coeficientes.getValor2()*-1) + ecuacion2.getY()*coeficientes.getValor()) == 0.0){
                                Result.setValor(coeficientes.getValor());
                                Result.setValor2(coeficientes.getValor2()*-1);
                            }else {
                                if ((ecuacion1.getY()*coeficientes.getValor2() + (ecuacion2.getY()*coeficientes.getValor()*-1)) == 0.0){
                                    Result.setValor(coeficientes.getValor()*-1);
                                    Result.setValor2(coeficientes.getValor2());
                                }else {
                                    if (((ecuacion1.getY()*coeficientes.getValor2()*-1) + (ecuacion2.getY()*coeficientes.getValor()*-1)) == 0.0){
                                        Result.setValor(coeficientes.getValor()*-1);
                                        Result.setValor2(coeficientes.getValor2()*-1);
                                    }
                                }
                            }
                        }

                        break;

                    case 3:

                        if ((ecuacion1.getZ()*coeficientes.getValor2() + ecuacion2.getZ()*coeficientes.getValor()) == 0.0){
                            Result.setValor(coeficientes.getValor());
                            Result.setValor2(coeficientes.getValor2());
                        }else {
                            if (((ecuacion1.getZ()*coeficientes.getValor2()*-1) + ecuacion2.getZ()*coeficientes.getValor()) == 0.0){
                                Result.setValor(coeficientes.getValor());
                                Result.setValor2(coeficientes.getValor2()*-1);
                            }else {
                                if ((ecuacion1.getZ()*coeficientes.getValor2() + (ecuacion2.getZ()*coeficientes.getValor()*-1)) == 0.0){
                                    Result.setValor(coeficientes.getValor()*-1);
                                    Result.setValor2(coeficientes.getValor2());
                                }else {
                                    if (((ecuacion1.getZ()*coeficientes.getValor2()*-1) + (ecuacion2.getZ()*coeficientes.getValor()*-1)) == 0.0){
                                        Result.setValor(coeficientes.getValor()*-1);
                                        Result.setValor2(coeficientes.getValor2()*-1);
                                    }
                                }
                            }
                        }

                        break;

                    default:
                        Result.setValor(-1.0);
                        Result.setValor2(1.0);
                        break;
                }
            }
            else {
                if (TamanoMatriz.equals(4)){
                    switch (filaB){
                        case 1:
                            if ((ecuacion1.getW()*coeficientes.getValor2() + ecuacion2.getW()*coeficientes.getValor()) == 0.0){
                                Result.setValor(coeficientes.getValor());
                                Result.setValor2(coeficientes.getValor2());
                            }else {
                                if (((ecuacion1.getW()*coeficientes.getValor2()*-1) + ecuacion2.getW()*coeficientes.getValor()) == 0.0){
                                    Result.setValor(coeficientes.getValor());
                                    Result.setValor2(coeficientes.getValor2()*-1);
                                }else {
                                    if ((ecuacion1.getW()*coeficientes.getValor2() + (ecuacion2.getW()*coeficientes.getValor()*-1)) == 0.0){
                                        Result.setValor(coeficientes.getValor()*-1);
                                        Result.setValor2(coeficientes.getValor2());
                                    }else {
                                        if (((ecuacion1.getW()*coeficientes.getValor2()*-1) + (ecuacion2.getW()*coeficientes.getValor()*-1)) == 0.0){
                                            Result.setValor(coeficientes.getValor()*-1);
                                            Result.setValor2(coeficientes.getValor2()*-1);
                                        }
                                    }
                                }
                            }
                            break;

                        case 2:

                            if ((ecuacion1.getX()*coeficientes.getValor2() + ecuacion2.getX()*coeficientes.getValor()) == 0.0){
                                Result.setValor(coeficientes.getValor());
                                Result.setValor2(coeficientes.getValor2());
                            }else {
                                if (((ecuacion1.getX()*coeficientes.getValor2()*-1) + ecuacion2.getX()*coeficientes.getValor()) == 0.0){
                                    Result.setValor(coeficientes.getValor());
                                    Result.setValor2(coeficientes.getValor2()*-1);
                                }else {
                                    if ((ecuacion1.getX()*coeficientes.getValor2() + (ecuacion2.getX()*coeficientes.getValor()*-1)) == 0.0){
                                        Result.setValor(coeficientes.getValor()*-1);
                                        Result.setValor2(coeficientes.getValor2());
                                    }else {
                                        if (((ecuacion1.getX()*coeficientes.getValor2()*-1) + (ecuacion2.getX()*coeficientes.getValor()*-1)) == 0.0){
                                            Result.setValor(coeficientes.getValor()*-1);
                                            Result.setValor2(coeficientes.getValor2()*-1);
                                        }
                                    }
                                }
                            }

                            break;

                        case 3:

                            if ((ecuacion1.getY()*coeficientes.getValor2() + ecuacion2.getY()*coeficientes.getValor()) == 0.0){
                                Result.setValor(coeficientes.getValor());
                                Result.setValor2(coeficientes.getValor2());
                            }else {
                                if (((ecuacion1.getY()*coeficientes.getValor2()*-1) + ecuacion2.getY()*coeficientes.getValor()) == 0.0){
                                    Result.setValor(coeficientes.getValor());
                                    Result.setValor2(coeficientes.getValor2()*-1);
                                }else {
                                    if ((ecuacion1.getY()*coeficientes.getValor2() + (ecuacion2.getY()*coeficientes.getValor()*-1)) == 0.0){
                                        Result.setValor(coeficientes.getValor()*-1);
                                        Result.setValor2(coeficientes.getValor2());
                                    }else {
                                        if (((ecuacion1.getY()*coeficientes.getValor2()*-1) + (ecuacion2.getY()*coeficientes.getValor()*-1)) == 0.0){
                                            Result.setValor(coeficientes.getValor()*-1);
                                            Result.setValor2(coeficientes.getValor2()*-1);
                                        }
                                    }
                                }
                            }

                            break;

                        case 4:

                            if ((ecuacion1.getZ()*coeficientes.getValor2() + ecuacion2.getZ()*coeficientes.getValor()) == 0.0){
                                Result.setValor(coeficientes.getValor());
                                Result.setValor2(coeficientes.getValor2());
                            }else {
                                if (((ecuacion1.getZ()*coeficientes.getValor2()*-1) + ecuacion2.getZ()*coeficientes.getValor()) == 0.0){
                                    Result.setValor(coeficientes.getValor());
                                    Result.setValor2(coeficientes.getValor2()*-1);
                                }else {
                                    if ((ecuacion1.getZ()*coeficientes.getValor2() + (ecuacion2.getZ()*coeficientes.getValor()*-1)) == 0.0){
                                        Result.setValor(coeficientes.getValor()*-1);
                                        Result.setValor2(coeficientes.getValor2());
                                    }else {
                                        if (((ecuacion1.getZ()*coeficientes.getValor2()*-1) + (ecuacion2.getZ()*coeficientes.getValor()*-1)) == 0.0){
                                            Result.setValor(coeficientes.getValor()*-1);
                                            Result.setValor2(coeficientes.getValor2()*-1);
                                        }
                                    }
                                }
                            }

                            break;

                        default:
                            Result.setValor(-1.0);
                            Result.setValor2(1.0);
                            break;
                    }
                }
            }
        }
        Result.setTipo("");
        Result.setTipo2("");
        return Result;
    }

    //endregion

    //endregion


    //region MetodosCcmplementoVectores
    private static String ValidacionUnitarios(String _pUno, String _pDos){
        String Resultado = "";
        if (_pUno == "i"){
            Resultado = (_pDos == "i")? "0" :(_pDos == "j")? "k" : (_pDos == "k")? "-j" : "0";
        }else
        if (_pUno == "j"){
            Resultado = (_pDos == "j")? "0"  :(_pDos == "k")? "i" : (_pDos == "i")? "-k" : "0";
        }else
        if (_pUno == "k"){
            Resultado = (_pDos == "k")? "0"  :(_pDos == "i")? "j" : (_pDos == "j")? "-i" : "0";
        }
        return Resultado;
    }
    private static csDiccionario ValidacionVectorProyeccion(csVectorProyeccion _pVector){
        Double _angulo = 0.0;

        switch (_pVector.getCuadrante()){

            //region Vector esta en los ejes
            case 0:
                if (_pVector.getDireccion().equals("Ninguna")){
                    if (_pVector.getApunta().equals("Der")){
                        _angulo = 360.0;
                        InsertDictionay(_angulo,"El vector esta sobre el eje x positivo");

                    }else {
                        if (_pVector.getApunta().equals("Izq")){
                            _angulo = 180.0;
                            InsertDictionay(_angulo,"El vector esta sobre el eje x negativo");
                        }
                    }
                }else {
                    if (_pVector.getDireccion().equals("Desc")) {
                        _angulo = 270.0;
                        InsertDictionay(_angulo,"El vector esta sobre el eje y negativo");
                    }else {
                        _angulo = 90.0;
                        InsertDictionay(_angulo,"El vector esta sobre el eje y positivo");
                    }
                }
                break;
            //endregion
            //region Cuadrante I
            case 1:
                if (_pVector.getDireccion().equals("Asc")) {
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = _pVector.getAngulo();
                        InsertDictionay(_angulo,"El ángulo ya tiene su valor absoluto");
                    }else {
                        //respecto a y
                        _angulo = 90.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"90 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }else {
                    //Desc
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = 180.0 + _pVector.getAngulo();
                        InsertDictionay(_angulo,"180 + "+_pVector.getAngulo()+ " porque el ángulo esta respecto a x");
                    }else {
                        //respecto a y
                        _angulo = 270.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"270 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }
                break;
            //endregion
            //region Cuadrante II
            case 2:
                if (_pVector.getDireccion().equals("Asc")) {
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = 180.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"180 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a x");
                    }else {
                        //respecto a y
                        _angulo = 90.0 + _pVector.getAngulo();
                        InsertDictionay(_angulo,"90 + "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }else {
                    //Desc
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = 360.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"360 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a x");
                    }else {
                        //respecto a y
                        _angulo = 270.0 + _pVector.getAngulo();
                        InsertDictionay(_angulo,"270 + "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }
                break;
            //endregion
            //region Cuadrante III
            case 3:
                if (_pVector.getDireccion().equals("Desc")) {
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = 180.0 + _pVector.getAngulo();
                        InsertDictionay(_angulo,"180 + "+_pVector.getAngulo()+ " porque el ángulo esta respecto a x");
                    }else {
                        //respecto a y
                        _angulo = 270.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"270 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }else {
                    //Asc
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = _pVector.getAngulo();
                        InsertDictionay(_angulo,"El ángulo ya tiene su valor absoluto");
                    }else {
                        //respecto a y
                        _angulo = 90.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"90 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }
                break;
            //endregion
            //region Cuadrante IV
            case 4:
                if (_pVector.getDireccion().equals("Desc")) {
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = 360.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"360 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a x");
                    }else {
                        //respecto a y
                        _angulo = 270.0 + _pVector.getAngulo();
                        InsertDictionay(_angulo,"270 + "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }else {
                    //Asc
                    if (_pVector.getAngRespectoA().equals("x")){
                        _angulo = 180.0 - _pVector.getAngulo();
                        InsertDictionay(_angulo,"180 - "+_pVector.getAngulo()+ " porque el ángulo esta respecto a x");
                    }else {
                        //respecto a y
                        _angulo = 90.0 + _pVector.getAngulo();
                        InsertDictionay(_angulo,"90 + "+_pVector.getAngulo()+ " porque el ángulo esta respecto a y");
                    }
                }
                break;
            //endregion
        }
        return myResultValidacionVectorProyeccion;
    }
    public static csDiccionario myResultValidacionVectorProyeccion = new csDiccionario();
    private static void  InsertDictionay(Double _angulo,String _proceso){
        myResultValidacionVectorProyeccion.setTipo(_proceso);
        myResultValidacionVectorProyeccion.setValor(_angulo);
        myResultValidacionVectorProyeccion.setTipo2(_proceso);
        myResultValidacionVectorProyeccion.setValor2(_angulo);
    }
    //endregion

    //endregion ===============================================================================================
}
