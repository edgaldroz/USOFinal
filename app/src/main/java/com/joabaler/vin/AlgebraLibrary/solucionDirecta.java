package com.joabaler.vin.AlgebraLibrary;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

public class solucionDirecta {

    //Numeros Complejos
    public String divisionComplejos(double r1, double i1,double r2, double i2){
        String resultado="";
        DecimalFormat format = new DecimalFormat("#.##");
        if ((r2+i2)!=0){
            double ii2=i2;
            if (i2>0){
                ii2 *=-1;
            }else {
                ii2 *= -1;
            }
            double[] a = {(r1*r2)+((i1*ii2)*-1),(r1*ii2)+(i1*r2)};
            double b = (int)Math.pow(r2,2)+(int)Math.pow(i2,2);

            int i =2;
            double auxR = a[0];
            double auxR2 = a[1];
            double auxI = b;
            double auxb = b;
            int nd;
            int nb;
            int nd2;
            int nb2;
            if (a[0]<0){
                nd = -1;
                a[0]=a[0]*nd;
                auxR *=nd;
            }else {
                nd = 1;
                auxR *=nd;
            }
            if (b<0){
                nb = -1;
                b = b*nb;
                auxI *= nb;
            }else {
                nb = 1;
                auxI *= nb;
            }
            //---
            if (auxb<0){
                nb2 = -1;
                auxb = auxb*nb2;
                auxb *= nb2;
            }else {
                nb2 = 1;
                auxb *= nb2;
            }
            //---
            if (a[1]<0){
                nd2 = -1;
                a[1]=a[1]*nd2;
                auxR2 *=nd2;
            }else {
                nd2 = 1;
                auxR2 *=nd2;
            }

            while (i<=auxR && i<=auxI){
                if (a[0]%i==0 && b%i==0){
                    a[0] = a[0]/i;
                    b = b/i;
                }else if (i>=a[0] || i>=b){
                    break;
                }
                i++;
            }
            i=2;
            while (i<=auxR2 && i<=auxb){
                if (a[1]%i==0 && auxb%i==0){
                    a[1] = a[1]/i;
                    auxb = auxb/i;
                }else if (i>=a[1] || i>=auxb){
                    break;
                }
                i++;
            }

            a[0] = a[0]*nd;
            a[1] = a[1]*nd2;
            b = b*nb;

            if (i1>0 && i2>0){
                resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                        "Z2 = "+r2+"+"+i2+" i\n\n";
            }else if (i1<0 && i2>0){
                resultado = "Z1 = "+r1+" "+i1+" i\n" +
                        "Z2 = "+r2+"+"+i2+" i\n\n";
            }else if (i1>0 && i2<0){
                resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                        "Z2 = "+r2+" "+i2+" i\n\n";
            }else if (i1<0 && i2<0){
                resultado = "Z1 = "+r1+" "+i1+" i\n" +
                        "Z2 = "+r2+" "+i2+" i\n\n";
            }
            resultado += "Resultado: \n";
            if (a[1]>=0){
                resultado += "Z1/Z2 = "+(format.format(a[0])+"/"+format.format(b))+" + "+(format.format(a[1])+"/"+format.format(b))+" i";
            }else{
                resultado += "Z1/Z2 = "+(format.format(a[0])+"/"+format.format(b))+""+(format.format(a[1])+"/"+format.format(b))+" i";
            }
        }else {
            resultado = "Error, No divisible entre cero...";
        }
        return resultado;
    }

    public String productoComplejos(double r1, double i1,double r2, double i2){
        String resultado ="";
        DecimalFormat formato = new DecimalFormat("#.##");
        double[] z1 = {(r1*r2)+((i1*i2)*-1),(r1*i2)+(i1*r2)};
        //---
        if (i1>0 && i2>0){
            resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                    "Z2 = "+r2+"+"+i2+" i\n\n";
        }else if (i1<0 && i2>0){
            resultado = "Z1 = "+r1+" "+i1+" i\n" +
                    "Z2 = "+r2+"+"+i2+" i\n\n";
        }else if (i1>0 && i2<0){
            resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                    "Z2 = "+r2+" "+i2+" i\n\n";
        }else if (i1<0 && i2<0){
            resultado = "Z1 = "+r1+" "+i1+" i\n" +
                    "Z2 = "+r2+" "+i2+" i\n\n";
        }
        //---
        resultado += "Resultado: \n";
        if (z1[1]>0){
            resultado += "Z1*Z2 = "+ formato.format(z1[0])+" + "+formato.format(z1[1])+" i";
        }
        else if (z1[1]<0){
            resultado += "Z1*Z2 = "+formato.format(z1[0]) + " "+formato.format(z1[1])+" i";
        }else if (z1[1]==0){
            resultado += "Z1*Z2 = "+formato.format(z1[0]) + " + "+formato.format(z1[1])+" i";
        }else {
            resultado = "Error Inesperado...";
        }
        return resultado;
    }

    public String sumaComplejos(double n1, double r1, double i1,double n2, double r2, double i2){
        String resultado="";
        double[] z1 = {n1*r1,n1*i1};
        double[] z2 = {n2*r2,n2*i2};
        double[] a = {z1[0]+z2[0],z1[1]+z2[1]};
        DecimalFormat formato = new DecimalFormat("#.##");

        //---
        if (i1>0 && i2>0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                        "Z2 = "+r2+"+"+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" + "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+"+"+i2+" i)\n\n";
            }

        }else if (i1<0 && i2>0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" "+i1+" i\n" +
                        "Z2 = "+r2+"+"+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+"+"+i2+" i)\n\n";
            }

        }else if (i1>0 && i2<0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                        "Z2 = "+r2+" "+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" + "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+" "+i2+" i)\n\n";
            }

        }else if (i1<0 && i2<0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" "+i1+" i\n" +
                        "Z2 = "+r2+" "+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+" "+i2+" i)\n\n";
            }

        }
        resultado += "Resultado: \n";
        if(a[1]>=0){
            resultado += "Z1+Z2 = "+formato.format(a[0]) + " + "+formato.format(a[1])+" i";
        }else if (a[1]<0){
            resultado += "Z1+Z2 = "+formato.format(a[0]) + " "+formato.format(a[1])+" i";
        }else {
            resultado = "Error Inesperado...";
        }
        return resultado;
    }

    public String restaComplejos(double n1, double r1, double i1,double n2, double r2, double i2){
        String resultado="";
        double[] z1 = {n1*r1,n1*i1};
        double[] z2 = {n2*r2,n2*i2};
        double[] a = {z1[0]-z2[0],z1[1]-z2[1]};
        DecimalFormat formato = new DecimalFormat("#.##");
        //----
        if (i1>0 && i2>0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                        "Z2 = "+r2+"+"+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" + "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+"+"+i2+" i)\n\n";
            }

        }else if (i1<0 && i2>0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" "+i1+" i\n" +
                        "Z2 = "+r2+"+"+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+"+"+i2+" i)\n\n";
            }

        }else if (i1>0 && i2<0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" + "+i1+" i\n" +
                        "Z2 = "+r2+" "+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" + "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+" "+i2+" i)\n\n";
            }

        }else if (i1<0 && i2<0){
            if (n1==1 && n2==1){
                resultado = "Z1 = "+r1+" "+i1+" i\n" +
                        "Z2 = "+r2+" "+i2+" i\n\n";
            }else if (n1>1 || n2>1){
                resultado = "Z1 = "+n1+"("+r1+" "+i1+" i)\n" +
                        "Z2 = "+n2+"("+r2+" "+i2+" i)\n\n";
            }

        }
        //----
        resultado += "Resultado: \n";
        if(a[1]>=0){
            resultado += "Z1-Z2 = "+formato.format(a[0]) + " + "+formato.format(a[1])+" i";
        }else if (a[1]<0){
            resultado += "Z1-Z2 = "+formato.format(a[0]) + " "+formato.format(a[1])+" i";
        }else {
            resultado += "Error Inesperado...";
        }
        return resultado;
    }

    public String potenciasI(String n){

        String nm="";

        int exponente = Integer.parseInt(n);
        nm +="i ^ "+n+"\n\n";
        exponente%=4;

        nm += "Resultado: \n";
        if (exponente==0){
            nm +="i ^ "+n+" = "+"i ^ "+exponente+" \t= \t1";
        }
        else
        if(exponente==3){
            nm +="i ^ "+n+" = "+"i ^ "+exponente+" \t= \t-i";
        }else if (exponente==2){
            nm +="i ^ "+n+" = "+"i ^ "+exponente+" \t= \t-1";
        }else if (exponente==1){
            nm +="i ^ "+n+" = "+"i ^ "+exponente+" \t= \ti";
        }else if (n=="1"){
            nm +="i ^ "+n+" = "+"i ^ "+exponente+" \t= \ti";
        }else {
            nm +="Error Fatal...";
        }
        return nm;
    }

    public String rectangularAPolar(double R, double I){

        Double R1 = Double.valueOf(R);
        Double I1 = Double.valueOf(I);
        String resultado ="";
        Double r = Math.pow(R,2) + Math.pow(I,2);
        Double raiz = Math.sqrt(r);
        Double rr=0d;
        Double RR=R1;
        Double II=I1;
        String signo ="";
        Double zeta =0d;

        //Anexa Expresion Original
        if (I>0){
            resultado = "z1 = "+R+" + "+I+" i\n\n";
        }else {
            resultado = "z1 = "+R+" "+I+" i\n\n";
        }

        //Verifica si tiene raiz Exacta
        if (isInteger(raiz)){
            rr = Math.sqrt(r);
            signo = signo;
        }else {
            rr = r;
            signo = "√";
        }
        //pasa a positivo los numeros;
        if (R1>0){
            R1=R1;
        }else {
            R1 = R1*-1;
        }
        if (I1>0){
            I1=I1;
        }else {
            I1 = I1*-1;
        }
        DecimalFormat formato = new DecimalFormat("#.##");
        zeta = Math.atan(I1/R1)*(180/Math.PI);

        Double zeta2 = zeta;
        resultado += "Resultado: \n";

        //Cuadrante I
        if(RR>=0 && II>=0){
            resultado += "r = √"+formato.format(R1)+"^2 +"+ formato.format(I1)+"^2  = "+signo+formato.format(rr)+"\n" +
                    "θ = Tan^-1 ("+formato.format(I1)+"/"+formato.format(R1)+") = "+formato.format(zeta2)+"°\n" +
                    "z = "+signo+formato.format(rr)+" cis "+formato.format(zeta2)+"°";
        }
        //Cuadrante II
        else if(RR<0 && II>=0){
            zeta = 180-zeta;
            resultado += "r = √"+formato.format(R1)+"^2 +"+ formato.format(I1)+"^2  = "+signo+formato.format(rr)+"\n" +
                    "θ = Tan^-1 ("+formato.format(I1)+"/"+formato.format(R1)+") = "+formato.format(zeta2)+"°\n" +
                    "θ = 180° - "+formato.format(zeta2)+" = "+formato.format(zeta)+"°\n" +
                    "z = "+signo+formato.format(rr)+" cis "+formato.format(zeta)+"°";
        }
        //Cuadrante III
        else if (RR<0 && II<0){
            zeta = 180+zeta;

            resultado += "r = √"+formato.format(R1)+"^2 +"+ formato.format(I1)+"^2  = "+signo+formato.format(rr)+"\n" +
                    "θ = Tan^-1 ("+formato.format(I1)+"/"+formato.format(R1)+") = "+formato.format(zeta2)+"°\n" +
                    "θ = 180° + "+formato.format(zeta2)+" = "+formato.format(zeta)+"°\n" +
                    "z = "+signo+formato.format(rr)+" cis "+formato.format(zeta)+"°";
        }
        //Cuadrante IV
        else if (RR>=0 && II<0){
            zeta = 360-zeta;

            resultado += "r = √"+formato.format(R1)+"^2 +"+ formato.format(I1)+"^2  = "+signo+formato.format(rr)+"\n" +
                    "θ = Tan^-1 ("+formato.format(I1)+"/"+formato.format(R1)+") = "+formato.format(zeta2)+"°\n" +
                    "θ = 360° - "+formato.format(zeta2)+" = "+formato.format(zeta)+"°\n" +
                    "z = "+signo+formato.format(rr)+" cis "+formato.format(zeta)+"°";
        }
        return resultado;
    }

    public String polarARectangular(String num, String angulo){
        return  "";
    }

    public String productoPolares(String ecu1,String ecu2){

        String res="";

        String[] s1 =  ecu1.split("cis");
        String[] s2 = ecu2.split("cis");

        String s1_1 = s1[0];
        String s1_2 = s1[1];

        String s2_1 = s2[0];
        String s2_2 = s2[1];

        res ="Z1 = "+s1_1+" cis "+s1_2+
                "°\nZ2 = "+s2_1+" cis "+s2_2+
                "°\n\nZ1*Z2 = "+s1_1+" * "+s2_1+" cis ("+s1_2+"° + "+s2_2+"°)\n\n";

        String S1_1 = s1_1.replaceAll("[√]", "");
        String S1_2 = s1_2;

        String S2_1 = s2_1.replaceAll("[√]", "");
        String S2_2 = s2_2;

        res += "Resultado: \n";

        if (isNumeric(S1_1)==true && isNumeric(S2_1)==true && isNumeric(S1_2)==true && isNumeric(S2_2)==true){

            Double val = Double.parseDouble(s1_1.replaceAll("[√]+", ""));

            Double val2 = Double.parseDouble(s2_1.replaceAll("[√]+", ""));

            Double ang1_1 = Double.parseDouble(s1_2);
            Double ang2_1 = Double.parseDouble(s2_2);
            Double angulo = (ang1_1+ang2_1);
            Double ang = angulo;

            res += "Z1*Z2 = "+s1_1+" * "+s2_1+" cis ("+angulo+"°)\n\n";

            if (angulo>360){
                angulo -= 360;
                res += "Z1*Z2 = "+s1_1+" * "+s2_1+" cis ("+ang+"° - 360°)\n\n";
            }
            if (angulo<0){
                angulo += 360;
                res += "Z1*Z2 = "+s1_1+" * "+s2_1+" cis ("+ang+"° + 360°)\n\n";
            }

            DecimalFormat formato = new DecimalFormat("#.##");
            if (s1_1.charAt(0)=='√' && s2_1.charAt(0)!='√'){
                res += "Z1*Z2 = "+s2_1+s1_1+" cis "+formato.format(angulo)+"°";
            }else if (s1_1.charAt(0)!='√' && s2_1.charAt(0)=='√'){
                res += "Z1*Z2 = "+s1_1+s2_1+" cis "+formato.format(angulo)+"°";
            }else if (s1_1.charAt(0)=='√' && s2_1.charAt(0)=='√'){
                res += "Z1*Z2 = "+"√"+formato.format((val*val2))+" cis "+formato.format(angulo)+"°";
            }else if (s1_1.charAt(0)!='√' && s2_1.charAt(0)!='√'){
                res += "Z1*Z2 = "+formato.format((val*val2))+" cis "+formato.format(angulo)+"°";
            }
        }else {
            res="Error Fatal...";
        }

        return res;
    }

    public String divisionPolares(String ecu1,String ecu2){
        String res="";

        String[] s1 =  ecu1.split("cis");
        String[] s2 = ecu2.split("cis");

        String s1_1 = s1[0];
        String s1_2 = s1[1];

        String s2_1 = s2[0];
        String s2_2 = s2[1];

        res = "Z1 = "+s1_1+" cis "+s1_2+
                "°\nZ2 = "+s2_1+" cis "+s2_2+
                "°\n\nResultado:\nZ1 / Z2 = "+s1_1+" / "+s2_1+" cis ("+s1_2+"° - "+s2_2+"°)\n\n";

        String S1_1 = s1_1.replaceAll("[√]", "");
        String S1_2 = s1_2;

        String S2_1 = s2_1.replaceAll("[√]", "");
        String S2_2 = s2_2;

        if (isNumeric(S1_1)==true && isNumeric(S2_1)==true && isNumeric(S1_2)==true && isNumeric(S2_2)==true) {

            Double val = Double.parseDouble(s1_1.replaceAll("[√]+", ""));

            Double val2 = Double.parseDouble(s2_1.replaceAll("[√]+", ""));

            Double ang1_1 = Double.parseDouble(s1_2);
            Double ang2_1 = Double.parseDouble(s2_2);
            Double angulo = (ang1_1 - ang2_1);
            Double ang = angulo;

            DecimalFormat formato = new DecimalFormat("#.##");

            res += "Z1 / Z2 = "+s1_1+" / "+s2_1+" cis ("+formato.format(angulo)+"°)\n\n";

            if (angulo>360){
                angulo -= 360;
                res += "Z1 / Z2 = "+s1_1+" / "+s2_1+" cis ("+formato.format(ang)+"° - 360°)\n\n";
            }else if (angulo<0){
                angulo += 360;
                res += "Z1 / Z2 = "+s1_1+" / "+s2_1+" cis ("+formato.format(ang)+"° + 360°)\n\n";
            }


            if (s1_1.charAt(0)=='√' && s2_1.charAt(0)!='√'){
                res += "Z1 / Z2 = "+s2_1+" / "+s1_1+" cis "+formato.format(angulo)+"°";
            }else if (s1_1.charAt(0)!='√' && s2_1.charAt(0)=='√'){
                res += "Z1 / Z2 = "+s1_1+" / "+s2_1+" cis "+formato.format(angulo)+"°";
            }else if (s1_1.charAt(0)=='√' && s2_1.charAt(0)=='√'){
                res += "Z1 / Z2 = "+"√"+"("+formato.format(val)+" / "+formato.format(val2)+")"+" cis "+formato.format(angulo)+"°";
            }else if (s1_1.charAt(0)!='√' && s2_1.charAt(0)!='√'){
                res += "Z1 / Z2 = "+ formato.format(val)+" / "+formato.format(val2)+" cis "+formato.format(angulo)+"°";
            }
        }else {
            res="Error Fatal...";
        }
        return res;
    }

    public String raicesNegativas(String Raiz){
        String res=Raiz+"\n\n";

        if (Raiz.charAt(0)=='√'){
            String dat = Raiz.replaceAll("[√]+", "");

            int rN = Integer.parseInt(dat.replaceAll("[-]+", ""));
            Double num = Double.parseDouble(Raiz.replaceAll("[^0-9]+", ""));
            int num2 = Integer.parseInt(Raiz.replaceAll("[^0-9]+", ""));
            Double numR = (Double) Math.sqrt(num);
            int Num = (int)Math.round(numR);

            res += "Resultado:\n";
            //=================
            DecimalFormat formato = new DecimalFormat("#.###");
            if (isNumeric(dat)){

                if (Integer.parseInt(dat)<0){
                    //------
                    if (numR!=Num){
                        //------
                        if (isPrimo(num2)){

                            res += "√ -"+rN+" = ?\n\n"+ "√"+num2+" i \t ≈ \t "+ (formato.format(Math.sqrt(num2))+" i");

                        }else {
                            //-----

                            int cont=2;
                            int auxRaiz =1;
                            int exponente=0;
                            int calc=0;
                            int acumCalc=1;

                            while (num2!=1){
                                int contI=0;
                                while (num2%cont==0){

                                    num2 /= cont;
                                    contI++;
                                }

                                if (contI == 1) {
                                    auxRaiz *= cont;
                                }
                                else if (contI % 2 != 0) {
                                    contI = contI - 1;
                                    auxRaiz *= cont;
                                    exponente = (int) Math.pow(cont, contI);
                                    if (exponente == 2) {
                                        calc = exponente;
                                    } else {
                                        calc = (int) Math.sqrt(exponente);
                                    }
                                    acumCalc *= calc;
                                } else if (contI % 2 == 0) {
                                    if (contI!=0){
                                        exponente = (int) Math.pow(cont, contI);
                                        if (exponente == 2) {
                                            auxRaiz *= exponente;
                                        } else {
                                            calc = (int) Math.sqrt(exponente);
                                        }
                                        acumCalc *= calc;
                                    }
                                }
                                if (isPrimo(num2)) {

                                    auxRaiz *= num2;
                                    break;
                                }
                                cont++;
                            }
                            if (acumCalc!=1) {
                                res += "√ -"+rN+" = ?\n\n"+Raiz +" \t= \t"+ acumCalc + " √ " + auxRaiz + "\ti"+ " \t≈ \t"+formato.format(Math.sqrt(rN))+" i";
                            }else {
                                res +="√ -"+rN+" = ?\n\n"+"√" + auxRaiz + "\ti"+ " \t≈ \t"+formato.format(Math.sqrt(rN))+" i";
                            }
                            //------
                        }
                    }else {
                        res +="√ -"+rN+" = "+ Num+" i";
                    }
                    //------
                }else {
                    res += "No es una Raiz Negativa";
                }
                //------

            }else {
                res += "Error...";
            }

            //==================

        }else {
            res +="No es Una Raiz";
        }
        return res;
    }

    ////----- Matrices---//
    //Suma de matrices
    public String sumaMatrix(double[][] m1,double[][] m2){
        String resul="";
        String len="";
        DecimalFormat formato = new DecimalFormat("#.##");

        resul = "\nMatriz A:\n";
        for (int a=0; a<m1.length;a++){
            for (int b=0; b < m1[a].length; b++){
                resul += formato.format(m1[a][b]);
                len = ""+formato.format(m1[a][b]);

                if (len.length()==1){
                    resul += "\t\t\t";
                }else if (len.length()==2){
                    resul += "\t\t";
                }else if (len.length()==3){
                    resul += "\t\t";
                }else if (len.length()==4){
                    resul += "\t\t";
                }else if (len.length()==5){
                    resul += "\t\t";
                }else if (len.length()==6){
                    resul += "\t\t";
                }else if (len.length()==7){
                    resul += "\t\t";
                }
            }
            resul +="\n";
        }
        resul +="\nMatriz B:\n";

        for (int a=0; a<m2.length;a++){
            for (int b=0; b < m2[a].length; b++){
                resul += formato.format(m2[a][b]);
                len = ""+formato.format(m2[a][b]);

                if (len.length()==1){
                    resul += "\t\t\t";
                }else if (len.length()==2){
                    resul += "\t\t";
                }else if (len.length()==3){
                    resul += "\t\t";
                }else if (len.length()==4){
                    resul += "\t\t";
                }else if (len.length()==5){
                    resul += "\t\t";
                }else if (len.length()==6){
                    resul += "\t\t";
                }else if (len.length()==7){
                    resul += "\t\t";
                }
            }
            resul +="\n";
        }
        resul +="\n\nResultado:\n";



        if ((m1.length == m2.length)){

            for (int x=0; x < m1.length; x++) {
                for (int y=0; y < m1[x].length; y++) {
                    resul += ""+ formato.format((m1[x][y]+m2[x][y]));
                    len = ""+formato.format((m1[x][y]+m2[x][y]));
                    if (len.length()==1){
                        resul += "\t\t\t";
                    }else if (len.length()==2){
                        resul += "\t\t";
                    }else if (len.length()==3){
                        resul += "\t\t";
                    }else if (len.length()==4){
                        resul += "\t\t";
                    }else if (len.length()==5){
                        resul += "\t\t";
                    }else if (len.length()==6){
                        resul += "\t\t";
                    }else if (len.length()==7){
                        resul += "\t\t";
                    }

                }
                resul+="\n";
            }
            return resul;
        }else {
            resul = "Matrices de diferente dimensión";
        }
        return resul;
    }

    //Resta de matrices
    public String restaMatrix(double[][] m1,double[][] m2){
        String resul="",len="";

        DecimalFormat formato = new DecimalFormat("#.##");

        resul = "\nMatriz A:\n";

        for (int a=0; a<m1.length;a++){
            for (int b=0; b < m1[a].length; b++){
                resul += formato.format(m1[a][b]);
                len = ""+formato.format(m1[a][b]);

                if (len.length()==1){
                    resul += "\t\t\t";
                }else if (len.length()==2){
                    resul += "\t\t";
                }else if (len.length()==3){
                    resul += "\t\t";
                }else if (len.length()==4){
                    resul += "\t\t";
                }else if (len.length()==5){
                    resul += "\t\t";
                }else if (len.length()==6){
                    resul += "\t\t";
                }else if (len.length()==7){
                    resul += "\t\t";
                }
            }
            resul +="\n";
        }
        resul +="\nMatriz B:\n";

        for (int a=0; a<m2.length;a++){
            for (int b=0; b < m2[a].length; b++){
                resul += formato.format(m2[a][b]);
                len = ""+formato.format(m2[a][b]);

                if (len.length()==1){
                    resul += "\t\t\t";
                }else if (len.length()==2){
                    resul += "\t\t";
                }else if (len.length()==3){
                    resul += "\t\t";
                }else if (len.length()==4){
                    resul += "\t\t";
                }else if (len.length()==5){
                    resul += "\t\t";
                }else if (len.length()==6){
                    resul += "\t\t";
                }else if (len.length()==7){
                    resul += "\t\t";
                }
            }
            resul +="\n";
        }
        resul +="\n\nResultado:\n";

        if ((m1.length == m2.length)){

            for (int x=0; x < m1.length; x++) {
                for (int y=0; y < m1[x].length; y++) {
                    resul += ""+ formato.format((m1[x][y]-m2[x][y]));
                    len = ""+ formato.format((m1[x][y]-m2[x][y]));
                    resul += "\t";

                    if (len.length()==1){
                        resul += "\t\t\t";
                    }else if (len.length()==2){
                        resul+= "\t\t";
                    }else if (len.length()==3){
                        resul += "\t\t";
                    }else if (len.length()==4){
                        resul += "\t\t";
                    }else if (len.length()==5){
                        resul += "\t\t";
                    }else if (len.length()==6){
                        resul += "\t\t";
                    }else if (len.length()==7){
                        resul += "\t\t";
                    }
                }
                resul+="\n";
            }
            return resul;
        }else {
            resul = "Matrices de diferente dimensión";
        }
        return resul;
    }

    //Multiplicacion de matrices
    public String productMatrix(double[][] m1,double[][] m2){

        String datosM="";
        String len="";

        DecimalFormat formato = new DecimalFormat("#.##");

        datosM = "\nMatriz A:\n";
        for (int a=0; a<m1.length;a++){
            for (int b=0; b < m1[a].length; b++){
                datosM += formato.format(m1[a][b]);
                len = ""+formato.format(m1[a][b]);

                if (len.length()==1){
                    datosM += "\t\t\t";
                }else if (len.length()==2){
                    datosM += "\t\t";
                }else if (len.length()==3){
                    datosM += "\t\t";
                }else if (len.length()==4){
                    datosM += "\t\t";
                }else if (len.length()==5){
                    datosM += "\t\t";
                }else if (len.length()==6){
                    datosM += "\t\t";
                }else if (len.length()==7){
                    datosM += "\t\t";
                }
            }
            datosM +="\n";
        }
        datosM +="\nMatriz B:\n";

        for (int a=0; a<m2.length;a++){
            for (int b=0; b < m2[a].length; b++){
                datosM += formato.format(m2[a][b]);
                len = ""+formato.format(m2[a][b]);

                if (len.length()==1){
                    datosM += "\t\t\t";
                }else if (len.length()==2){
                    datosM += "\t\t";
                }else if (len.length()==3){
                    datosM += "\t\t";
                }else if (len.length()==4){
                    datosM += "\t\t";
                }else if (len.length()==5){
                    datosM += "\t\t";
                }else if (len.length()==6){
                    datosM += "\t\t";
                }else if (len.length()==7){
                    datosM += "\t\t";
                }
            }
            datosM +="\n";
        }
        datosM +="\n\nResultado:\n";
        DecimalFormat format = new DecimalFormat("#.##");

        int fil_m1 = m1.length; // cuenta, cuantas filas tiene la matriz
        int col_m1 = m1[0].length;// cuenta, cuantas columnas tiene la matriz

        int fil_m2 = m2.length; // cuenta, cuantas filas tiene la matriz
        int col_m2 = m2[0].length;// cuenta, cuantas columnas tiene la matriz

        if (col_m1 != fil_m2){
            datosM = "Matrices de distinta dimension";
        }else {
            double[][] multiplicacion = new double[fil_m1][col_m2];

            for (int x=0; x < multiplicacion.length; x++) {
                for (int y=0; y < multiplicacion[x].length; y++) {
                    for (int z=0; z<col_m1; z++) {
                        multiplicacion [x][y] += m1[x][z]*m2[z][y];
                        len = ""+format.format((m1[x][z]*m2[z][y]));

                    }
                    datosM += format.format(multiplicacion[x][y]);
                    if (len.length()==1){
                        datosM += "\t\t\t";
                    }else if (len.length()==2){
                        datosM += "\t\t";
                    }else if (len.length()==3){
                        datosM += "\t\t";
                    }else if (len.length()==4){
                        datosM += "\t\t";
                    }else if (len.length()==5){
                        datosM += "\t\t";
                    }else if (len.length()==6){
                        datosM += "\t\t";
                    }else if (len.length()==7){
                        datosM += "\t\t";
                    }

                }
                datosM +="\n";

            }
        }
        return datosM;
    }

    //ProductoMatriz por un escalar
    public static String productoMEscalar(double[][] m1, double n){
        String result="",len="";
        DecimalFormat formato = new DecimalFormat("#.##");

        result="Escalar: "+n+"\n\nMatriz A:\n";
        for (int a=0; a<m1.length;a++){
            for (int b=0; b < m1[a].length; b++){
                result += formato.format(m1[a][b]);
                len = ""+formato.format(m1[a][b]);

                if (len.length()==1){
                    result += "\t\t\t";
                }else if (len.length()==2){
                    result += "\t\t";
                }else if (len.length()==3){
                    result += "\t\t";
                }else if (len.length()==4){
                    result += "\t\t";
                }else if (len.length()==5){
                    result += "\t\t";
                }else if (len.length()==6){
                    result += "\t\t";
                }else if (len.length()==7){
                    result += "\t\t";
                }
            }
            result +="\n";
        }
        result +="\n\nResultado:\n";

        for (int x=0; x < m1.length; x++) {
            for (int y=0; y < m1[x].length; y++) {
                result +=""+ formato.format((n * m1[x][y]));
                len = ""+formato.format((n *m1[x][y]));
                if (len.length()==1){
                    result += "\t\t\t";
                }else if (len.length()==2){
                    result += "\t\t";
                }else if (len.length()==3){
                    result += "\t\t";
                }else if (len.length()==4){
                    result += "\t\t";
                }else if (len.length()==5){
                    result += "\t\t";
                }else if (len.length()==6){
                    result += "\t\t";
                }else if (len.length()==7){
                    result += "\t\t";
                }
            }
            result +="\n";
        }
        return result;
    }

    //Matrices Calc Determinante
    public static String determinante(double[][] mat)
    {
        String resultado="",len="";

        resultado = "\nMatriz A:\n";
        DecimalFormat formato = new DecimalFormat("#.##");

        for (int a=0; a<mat.length;a++){
            for (int b=0; b < mat[a].length; b++){
                resultado += formato.format(mat[a][b]);
                len = ""+formato.format(mat[a][b]);

                if (len.length()==1){
                    resultado += "\t\t\t";
                }else if (len.length()==2){
                    resultado += "\t\t";
                }else if (len.length()==3){
                    resultado += "\t\t";
                }else if (len.length()==4){
                    resultado += "\t\t";
                }else if (len.length()==5){
                    resultado += "\t\t";
                }else if (len.length()==6){
                    resultado += "\t\t";
                }else if (len.length()==7){
                    resultado += "\t\t";
                }
            }
            resultado +="\n";
        }
        resultado +="\nResultado:\n"+formato.format(det(mat));
        return resultado;
    }

    private static double det(double[][] mat) {
        if (mat.length == 1)
            return mat[0][0];
        if (mat.length == 2)
            return mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1];
        double sum = 0, sign = 1;
        int newN = mat.length - 1;
        double[][] temp = new double[newN][newN];
        for (int t = 0; t < newN; t++) {
            int q = 0;
            for (int i = 0; i < newN; i++) {
                for (int j = 0; j < newN; j++) {
                    temp[i][j] = mat[1 + i][q + j];
                }
                if (q == i)
                    q = 1;
            }
            sum += sign * mat[0][t] * det(temp);
            sign *= -1;
        }
        return sum;
    }

    /*---------Vectores---------*/
    //Multiplicacion por un escalar
    public String productoPuntoVector(String v,String w){
        String resultado="";
        resultado = productPunto(v,w);
        return "A-> "+v+"\n"+"B-> "+w+"\n\n"+"Resultado:\n "+resultado;
    }
    //Metodo separado para reutilizar en angulo entre vectores
    public String productPunto(String v, String w){
        w = w.replaceAll("\\s","");
        v = v.replaceAll("\\s","");

        String resultado="";

        String[] expre1=  v.split("[a-zA-Z]");
        String[] expre2 =  w.split("[a-zA-Z]");

        Collection expre11 = new ArrayList();
        Collection expre22 = new ArrayList();



        if (expre1[0].length()==0){
            for (int i=0;i<expre1.length;i++){
                if (expre1[i].equals("")){
                    ((ArrayList) expre11).add("1");
                }else{
                    ((ArrayList) expre11).add(expre1[i]);
                }
            }
        }else {
            for (int i=0;i<expre1.length;i++){
                ((ArrayList) expre11).add(expre1[i]);
            }
        }

        if (expre2[0].length()==0){
            for (int i=0;i<expre2.length;i++){
                if (expre2[i].equals("")){
                    ((ArrayList) expre22).add("1");
                }else{
                    ((ArrayList) expre22).add(expre2[i]);
                }
            }
        }else {
            for (int i=0;i<expre2.length;i++){
                ((ArrayList) expre22).add(expre2[i]);
            }
        }
        String[] expresionn = new String[expre11.size()];
        expre11.toArray(expresionn);

        String[] expresionn2 = new String[expre22.size()];
        expre22.toArray(expresionn2);

        /*String[] exp1= new String[3];
        String[] exp2= new String[3];*/

        Collection exp11 = new ArrayList();
        Collection exp22 = new ArrayList();
        //iguala valores de los array

        //---
        if (expresionn2.length==3 && expresionn.length==3){
            for (int i=0; i<expresionn2.length;i++){
                ((ArrayList) exp11).add(expresionn[i]);
            }
        }else if (expresionn2.length==3 && expresionn.length<3){
            int ii =0;
            while (ii<expresionn2.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length<3 && expresionn.length==3){
            int ii =0;
            while (ii<expresionn.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length==2 && expresionn.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }

        //----------
        if (expresionn2.length==3 && expresionn.length==3){
            for (int i=0;i<expresionn2.length;i++){
                ((ArrayList) exp22).add(expresionn2[i]);
            }
        }else if (expresionn2.length==3 && expresionn.length<3){
            int jj=0;
            while (jj<expresionn2.length){
                if(expresionn.length>jj){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[jj]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                jj++;
            }
        }else if (expresionn2.length<3 && expresionn.length==3){
            int jj=0;
            while (jj<expresionn.length){
                if(expresionn2.length>jj){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[jj]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");

                }
                jj++;
            }
        }
        else if (expresionn2.length==2 && expresionn.length==2){
            int jj=0;
            while (jj<3){
                if(expresionn.length>jj){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[jj]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                jj++;
            }
        }
        //---------
        String[] exp1= new String[exp11.size()];
        exp11.toArray(exp1);

        String[] exp2= new String[exp22.size()];
        exp22.toArray(exp2);

        String[] expresion= new String[3];
        String[] expresion2= new String[3];

        if (exp1.length == exp2.length){
            for (int l =0; l<exp1.length;l++){
                expresion[l] = exp1[l];
            }
        }else {
            resultado="Error Inesperado...";
        }

        if (exp2.length == exp1.length){
            for (int k =0; k<exp2.length;k++){
                expresion2[k] = exp2[k];
            }
        }else {
            resultado ="Error Inesperado...";
        }
        String[] tem2 = new String[3];
        String[] tem2_2 = new String[3];

        String temp3="";
        String temp3_2="";

        for (int i = 0; i < expresion.length; i++) {
            if (expresion[i].equals("-")) {
                tem2[i] = "-1";
            } else if (expresion[i].equals("+")) {
                tem2[i] = "1";
            } else {
                temp3 = expresion[i].replaceAll("[+]", "");
                tem2[i] = temp3;
            }
        }
        for (int i = 0; i < expresion2.length; i++) {
            if (expresion2[i].equals("-")) {
                tem2_2[i] = "-1";
            } else if (expresion2[i].equals("+")) {
                tem2_2[i] = "1";
            } else {
                temp3_2 = expresion2[i].replaceAll("[+]", "");
                tem2_2[i] = temp3_2;
            }
        }
        //-------
        //Proceso calc
        if (tem2.length==3 && tem2_2.length==3){
            if (isNumeric(tem2[0]) && isNumeric(tem2[1])
                    && isNumeric(tem2[2]) && isNumeric(tem2_2[0])
                    && isNumeric(tem2_2[1]) && isNumeric(tem2_2[2])) {
                DecimalFormat formato = new DecimalFormat("#.##");
                double temm2_0 = Double.parseDouble(tem2[0]);
                double temm2_2_0 = Double.parseDouble(tem2_2[0]);
                double temm2_1 = Double.parseDouble(tem2[1]);
                double temm2_2_1 = Double.parseDouble(tem2_2[1]);
                double temm2_2 = Double.parseDouble(tem2[2]);
                double temm2_2_2 = Double.parseDouble(tem2_2[2]);

                resultado = ""+formato.format((temm2_0*temm2_2_0+temm2_1*temm2_2_1+temm2_2*temm2_2_2));

            } else {
                resultado = "Expresión no válida";
            }
        }else {
            resultado = "Error Inesperado... :'(";
        }

        return resultado;
    }


    public String sumaVectorResultante(double n, String v,double m, String w){
        v = v.replaceAll("\\s","");
        w = w.replaceAll("\\s","");


        String resultado="";

        String[] expre1=  v.split("[a-zA-Z]");
        String[] expre2 =  w.split("[a-zA-Z]");

        Collection expre11 = new ArrayList();
        Collection expre22 = new ArrayList();

        if (expre1[0].length()==0){
            for (int i=0;i<expre1.length;i++){
                if (expre1[i].equals("")){
                    ((ArrayList) expre11).add("1");
                }else{
                    ((ArrayList) expre11).add(expre1[i]);
                }
            }
        }else {
            for (int i=0;i<expre1.length;i++){
                ((ArrayList) expre11).add(expre1[i]);
            }
        }

        if (expre2[0].length()==0){
            for (int i=0;i<expre2.length;i++){
                if (expre2[i].equals("")){
                    ((ArrayList) expre22).add("1");
                }else{
                    ((ArrayList) expre22).add(expre2[i]);
                }
            }
        }else {
            for (int i=0;i<expre2.length;i++){
                ((ArrayList) expre22).add(expre2[i]);
            }
        }
        String[] expresionn = new String[expre11.size()];
        expre11.toArray(expresionn);

        String[] expresionn2 = new String[expre22.size()];
        expre22.toArray(expresionn2);

        Collection exp11 = new ArrayList();
        Collection exp22 = new ArrayList();
        //iguala valores de los array

        //---
        if (expresionn2.length==3 && expresionn.length==3){
            for (int i=0; i<expresionn2.length;i++){
                ((ArrayList) exp11).add(expresionn[i]);
            }
        }else if (expresionn2.length==3 && expresionn.length<3){
            int ii =0;
            while (ii<expresionn2.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length<3 && expresionn.length==3){
            int ii =0;
            while (ii<expresionn.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length==2 && expresionn.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }

        //----exp2
        if (expresionn.length==3 && expresionn2.length==3 ){
            for (int i=0; i<expresionn2.length;i++){
                ((ArrayList) exp22).add(expresionn2[i]);
            }
        }else if (expresionn.length<3 && expresionn2.length==3){
            int ii =0;
            while (ii<expresionn2.length){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }else if (expresionn.length==3 && expresionn2.length<3){
            int ii =0;
            while (ii<expresionn.length){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }else if (expresionn.length==2 && expresionn2.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }


        //---------
        String[] exp1= new String[exp11.size()];
        exp11.toArray(exp1);

        String[] exp2= new String[exp22.size()];
        exp22.toArray(exp2);

        String[] expresion= new String[3];
        String[] expresion2= new String[3];

        if (exp1.length == exp2.length){
            for (int l =0; l<exp1.length;l++){
                expresion[l] = exp1[l];
            }
        }else {
            resultado="Error Inesperado...";
        }

        if (exp2.length == exp1.length){
            for (int k =0; k<exp2.length;k++){
                expresion2[k] = exp2[k];
            }
        }else {
            resultado ="Error Inesperado...";
        }
        String[] tem2 = new String[3];
        String[] tem2_2 = new String[3];

        String temp3="";
        String temp3_2="";

        for (int i = 0; i < expresion.length; i++) {
            if (expresion[i].equals("-")) {
                tem2[i] = "-1";
            } else if (expresion[i].equals("+")) {
                tem2[i] = "1";
            } else {
                temp3 = expresion[i].replaceAll("[+]", "");
                tem2[i] = temp3;
            }
        }
        for (int i = 0; i < expresion2.length; i++) {
            if (expresion2[i].equals("-")) {
                tem2_2[i] = "-1";
            } else if (expresion2[i].equals("+")) {
                tem2_2[i] = "1";
            } else {
                temp3_2 = expresion2[i].replaceAll("[+]", "");
                tem2_2[i] = temp3_2;
            }
        }

        //Calc
        if (tem2.length==3 && tem2_2.length==3){
            if (isNumeric(tem2[0]) && isNumeric(tem2[1])
                    && isNumeric(tem2[2]) && isNumeric(tem2_2[0])
                    && isNumeric(tem2_2[1]) && isNumeric(tem2_2[2])) {
                DecimalFormat format = new DecimalFormat("#.##");
                resultado = "A -> "+format.format(n)+" ( "+v+" )\nB -> "+format.format(m)+" ( "+w+" )\n\nResultado:\n";

                String i_i = ""+format.format((n*(Double.parseDouble(tem2[0]))+m*(Double.parseDouble(tem2_2[0]))));
                String j_j = ""+format.format((n*(Double.parseDouble(tem2[1]))+m*(Double.parseDouble(tem2_2[1]))));
                String k_k = ""+format.format((n*(Double.parseDouble(tem2[2]))+m*(Double.parseDouble(tem2_2[2]))));

               if (Double.parseDouble(j_j)<0 && Double.parseDouble(k_k)>=0){
                   resultado += "A+B -> "+i_i+"i "+j_j+"j +"+k_k+"k";
               }else if (Double.parseDouble(j_j)<0 && Double.parseDouble(k_k)<0){
                   resultado += "A+B -> "+i_i+"i "+j_j+"j "+k_k+"k";
               }else if(Double.parseDouble(j_j)>=0 && Double.parseDouble(k_k)<0){
                   resultado += "A+B -> "+i_i+" i +"+j_j+"j "+k_k+"k";
               }else if (Double.parseDouble(j_j)>=0 && Double.parseDouble(k_k)>=0){
                   resultado += "A+B -> "+i_i+"i +"+j_j+"j +"+k_k+"k";
               }

            } else {
                resultado = "Expresión no válida";
            }
        }else {
            resultado = "Error Inesperado... :'(";
        }

        return resultado;
    }

    public String restaVectorResultante(double n, String v,double m, String w){
        v = v.replaceAll("\\s","");
        w = w.replaceAll("\\s","");

        String resultado="";

        String[] expre1=  v.split("[a-zA-Z]");
        String[] expre2 =  w.split("[a-zA-Z]");

        Collection expre11 = new ArrayList();
        Collection expre22 = new ArrayList();

        if (expre1[0].length()==0){
            for (int i=0;i<expre1.length;i++){
                if (expre1[i].equals("")){
                    ((ArrayList) expre11).add("1");
                }else{
                    ((ArrayList) expre11).add(expre1[i]);
                }
            }
        }else {
            for (int i=0;i<expre1.length;i++){
                ((ArrayList) expre11).add(expre1[i]);
            }
        }

        if (expre2[0].length()==0){
            for (int i=0;i<expre2.length;i++){
                if (expre2[i].equals("")){
                    ((ArrayList) expre22).add("1");
                }else{
                    ((ArrayList) expre22).add(expre2[i]);
                }
            }
        }else {
            for (int i=0;i<expre2.length;i++){
                ((ArrayList) expre22).add(expre2[i]);
            }
        }
        String[] expresionn = new String[expre11.size()];
        expre11.toArray(expresionn);

        String[] expresionn2 = new String[expre22.size()];
        expre22.toArray(expresionn2);

        Collection exp11 = new ArrayList();
        Collection exp22 = new ArrayList();
        //iguala valores de los array

        //---
        if (expresionn2.length==3 && expresionn.length==3){
            for (int i=0; i<expresionn2.length;i++){
                ((ArrayList) exp11).add(expresionn[i]);
            }
        }else if (expresionn2.length==3 && expresionn.length<3){
            int ii =0;
            while (ii<expresionn2.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length<3 && expresionn.length==3){
            int ii =0;
            while (ii<expresionn.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length==2 && expresionn.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }

        //----exp2
        if (expresionn.length==3 && expresionn2.length==3 ){
            for (int i=0; i<expresionn2.length;i++){
                ((ArrayList) exp22).add(expresionn2[i]);
            }
        }else if (expresionn.length<3 && expresionn2.length==3){
            int ii =0;
            while (ii<expresionn2.length){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }else if (expresionn.length==3 && expresionn2.length<3){
            int ii =0;
            while (ii<expresionn.length){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }else if (expresionn.length==2 && expresionn2.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }
        //---------
        String[] exp1= new String[exp11.size()];
        exp11.toArray(exp1);

        String[] exp2= new String[exp22.size()];
        exp22.toArray(exp2);

        String[] expresion= new String[3];
        String[] expresion2= new String[3];

        if (exp1.length == exp2.length){
            for (int l =0; l<exp1.length;l++){
                expresion[l] = exp1[l];
            }
        }else {
            resultado="Error Inesperado...";
        }

        if (exp2.length == exp1.length){
            for (int k =0; k<exp2.length;k++){
                expresion2[k] = exp2[k];
            }
        }else {
            resultado ="Error Inesperado...";
        }
        String[] tem2 = new String[3];
        String[] tem2_2 = new String[3];

        String temp3="";
        String temp3_2="";

        for (int i = 0; i < expresion.length; i++) {
            if (expresion[i].equals("-")) {
                tem2[i] = "-1";
            } else if (expresion[i].equals("+")) {
                tem2[i] = "1";
            } else {
                temp3 = expresion[i].replaceAll("[+]", "");
                tem2[i] = temp3;
            }
        }
        for (int i = 0; i < expresion2.length; i++) {
            if (expresion2[i].equals("-")) {
                tem2_2[i] = "-1";
            } else if (expresion2[i].equals("+")) {
                tem2_2[i] = "1";
            } else {
                temp3_2 = expresion2[i].replaceAll("[+]", "");
                tem2_2[i] = temp3_2;
            }
        }

        //Calc
        if (tem2.length==3 && tem2_2.length==3){
            if (isNumeric(tem2[0]) && isNumeric(tem2[1])
                    && isNumeric(tem2[2]) && isNumeric(tem2_2[0])
                    && isNumeric(tem2_2[1]) && isNumeric(tem2_2[2])) {

                DecimalFormat format = new DecimalFormat("#.##");
                resultado = "A -> "+format.format(n)+" ( "+v+" )\nB -> "+format.format(m)+" ( "+w+" )\n\nResultado:\n";

                String i_i = ""+format.format((n*(Double.parseDouble(tem2[0]))-m*(Double.parseDouble(tem2_2[0]))));
                String j_j = ""+format.format((n*(Double.parseDouble(tem2[1]))-m*(Double.parseDouble(tem2_2[1]))));
                String k_k = ""+format.format((n*(Double.parseDouble(tem2[2]))-m*(Double.parseDouble(tem2_2[2]))));

                if (Double.parseDouble(j_j)<0 && Double.parseDouble(k_k)>=0){
                    resultado += "A-B -> "+i_i+"i "+j_j+"j +"+k_k+"k";
                }else if (Double.parseDouble(j_j)<0 && Double.parseDouble(k_k)<0){
                    resultado += "A-B -> "+i_i+"i "+j_j+"j "+k_k+"k";
                }else if(Double.parseDouble(j_j)>=0 && Double.parseDouble(k_k)<0){
                    resultado += "A-B -> "+i_i+"i +"+j_j+"j "+k_k+"k";
                }else if (Double.parseDouble(j_j)>=0 && Double.parseDouble(k_k)>=0){
                    resultado += "A-B -> "+i_i+"i +"+j_j+"j +"+k_k+"k";
                }

            } else {
                resultado = "Expresión no válida";
            }
        }else {
            resultado = "Error Inesperado... :'(";
        }

        return resultado;
    }

    //Vector Unitario
    public String vecRepresentacionUni(String v){

        v = v.replaceAll("\\s","");

        String resultado="";

        String[] expre1=  v.split("[a-zA-Z]");

        Collection expre11 = new ArrayList();



        if (expre1[0].length()==0){
            for (int i=0;i<expre1.length;i++){
                if (expre1[i].equals("")){
                    ((ArrayList) expre11).add("1");
                }else{
                    ((ArrayList) expre11).add(expre1[i]);
                }
            }
        }else {
            for (int i=0;i<expre1.length;i++){
                ((ArrayList) expre11).add(expre1[i]);
            }
        }


        String[] expresionn = new String[expre11.size()];
        expre11.toArray(expresionn);

        Collection exp11 = new ArrayList();

        ///#######
        if (expresionn.length==3){
            for (int i=0; i<expresionn.length;i++){
                ((ArrayList) exp11).add(expresionn[i]);
            }
        }else if (expresionn.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else {
            resultado = "Error...";
        }

        String[] exp1= new String[exp11.size()];
        exp11.toArray(exp1);

        String[] expresion= new String[3];

        String[] tem2 = new String[3];

        for (int l =0; l<exp1.length;l++){
            expresion[l] = exp1[l];
        }

        String temp3="";

        for (int i = 0; i < expresion.length; i++) {
            if (expresion[i].equals("-")) {
                tem2[i] = "-1";
            } else if (expresion[i].equals("+")) {
                tem2[i] = "1";
            } else {
                temp3 = expresion[i].replaceAll("[+]", "");
                tem2[i] = temp3;
            }
        }

        //Proceso calc
        if (tem2.length==3){
            if (isNumeric(tem2[0]) && isNumeric(tem2[1])
                    && isNumeric(tem2[2])) {

                Double a = 0d;
                Double b = 0d;
                Double c = 0d;
                Double d = 0d;

                Double zetaX = 0d;
                Double zetaY = 0d;
                Double zetaZ = 0d;
                DecimalFormat formato = new DecimalFormat("#.##");

                a = Math.sqrt(Math.pow(Double.parseDouble(tem2[0]),2)+Math.pow(Double.parseDouble(tem2[1]),2)+Math.pow(Double.parseDouble(tem2[2]),2));

                b = Double.parseDouble(tem2[0])/a;
                c = Double.parseDouble(tem2[1])/a;
                d = Double.parseDouble(tem2[2])/a;
                Double tan = Math.atan(Double.parseDouble(tem2[1])/Double.parseDouble(tem2[0]))* (180/Math.PI);
                if(tan<0){
                    tan = tan*-1;
                }
                if (Double.parseDouble(tem2[1])<0){
                    tem2[1] = formato.format ((Double.parseDouble(tem2[1])*-1))+"";
                }
                if (Double.parseDouble(tem2[0])<0){
                    tem2[0] = formato.format ((Double.parseDouble(tem2[0])*-1))+"";
                }
                String e1 = formato.format(b);
                String e2 = formato.format(c);
                String e3 = formato.format(d);
                if (Double.parseDouble(e1)>=0){
                    e1 = "+ "+e1;
                }
                if (Double.parseDouble(e2)>=0){
                    e2 = "+ "+e2;
                }
                if (Double.parseDouble(e3)>=0){
                    e3 = "+ "+e3;
                }

                resultado = "v = "+v+"\n\nResultado:\n"
                        +"θx = cos^-1 ("+formato.format(b)+")\n"
                        +"θx = "+formato.format(Math.acos(b))+" ≈ "+formato.format((Math.acos(b) * (180/Math.PI)))+"°"+"\n\n"+
                        "θy = cos^-1 ("+formato.format(c)+")\n"+
                        "θy = "+formato.format(Math.acos(c))+" ≈ "+formato.format((Math.acos(c) * (180/Math.PI)))+"°"+"\n\n"+
                        "θz = cos^-1 ("+formato.format(d)+")\n"+
                        "θz = "+formato.format(Math.acos(d))+" ≈ "+formato.format((Math.acos(d) * (180/Math.PI)))+"°"+"\n\n"+
                        "ê = "+e1+"i "+e2+"j "+e3+"k\n\n"+
                        "θx = tan^-1 ("+tem2[1]+"/"+tem2[0]+") = "+formato.format(tan)+"°";

            } else {
                resultado = "Expresión no válida";
            }
        }else {
            resultado = "Error Inesperado... :'(";
        }
        return resultado;
    }

    public String anguloEntreVectores(String v, String w){
        String v_w = productPunto(v, w);

        Double a_v = 0d;
        Double b_w = 0d;
        Double vw = 0d;

        //-----
        w = w.replaceAll("\\s","");
        v = v.replaceAll("\\s","");

        String resultado="";

        resultado = "v -> "+v+"\n w -> "+w+"\n\nResultado:\n";

        String[] expre1=  v.split("[a-zA-Z]");
        String[] expre2 =  w.split("[a-zA-Z]");

        Collection expre11 = new ArrayList();
        Collection expre22 = new ArrayList();



        if (expre1[0].length()==0){
            for (int i=0;i<expre1.length;i++){
                if (expre1[i].equals("")){
                    ((ArrayList) expre11).add("1");
                }else{
                    ((ArrayList) expre11).add(expre1[i]);
                }
            }
        }else {
            for (int i=0;i<expre1.length;i++){
                ((ArrayList) expre11).add(expre1[i]);
            }
        }

        if (expre2[0].length()==0){
            for (int i=0;i<expre2.length;i++){
                if (expre2[i].equals("")){
                    ((ArrayList) expre22).add("1");
                }else{
                    ((ArrayList) expre22).add(expre2[i]);
                }
            }
        }else {
            for (int i=0;i<expre2.length;i++){
                ((ArrayList) expre22).add(expre2[i]);
            }
        }
        String[] expresionn = new String[expre11.size()];
        expre11.toArray(expresionn);

        String[] expresionn2 = new String[expre22.size()];
        expre22.toArray(expresionn2);

        /*String[] exp1= new String[3];
        String[] exp2= new String[3];*/

        Collection exp11 = new ArrayList();
        Collection exp22 = new ArrayList();
        //iguala valores de los array

        //---
        //---
        if (expresionn2.length==3 && expresionn.length==3){
            for (int i=0; i<expresionn2.length;i++){
                ((ArrayList) exp11).add(expresionn[i]);
            }
        }else if (expresionn2.length==3 && expresionn.length<3){
            int ii =0;
            while (ii<expresionn2.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length<3 && expresionn.length==3){
            int ii =0;
            while (ii<expresionn.length){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }else if (expresionn2.length==2 && expresionn.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp11).add(expresionn[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp11).add("0");
                }
                ii++;
            }
        }

        //----exp2
        if (expresionn.length==3 && expresionn2.length==3 ){
            for (int i=0; i<expresionn2.length;i++){
                ((ArrayList) exp22).add(expresionn2[i]);
            }
        }else if (expresionn.length<3 && expresionn2.length==3){
            int ii =0;
            while (ii<expresionn2.length){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }else if (expresionn.length==3 && expresionn2.length<3){
            int ii =0;
            while (ii<expresionn.length){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }else if (expresionn.length==2 && expresionn2.length==2){
            int ii =0;
            while (ii<3){
                if(expresionn2.length>ii){
                    //exp1[ii] = expresionn[ii];
                    ((ArrayList) exp22).add(expresionn2[ii]);
                }else {
                    //exp1[ii]="0";
                    ((ArrayList) exp22).add("0");
                }
                ii++;
            }
        }


        //---------
        String[] exp1= new String[exp11.size()];
        exp11.toArray(exp1);

        String[] exp2= new String[exp22.size()];
        exp22.toArray(exp2);

        //=========
        String[] expresion= new String[3];
        String[] expresion2= new String[3];

        if (exp1.length == exp2.length){
            for (int l =0; l<exp1.length;l++){
                expresion[l] = exp1[l];
            }
        }else {
            resultado="Error Inesperado...";
        }

        if (exp2.length == exp1.length){
            for (int k =0; k<exp2.length;k++){
                expresion2[k] = exp2[k];
            }
        }else {
            resultado ="Error Inesperado...";
        }
        String[] tem2 = new String[3];
        String[] tem2_2 = new String[3];

        String temp3="";
        String temp3_2="";

        for (int i = 0; i < expresion.length; i++) {
            if (expresion[i].equals("-")) {
                tem2[i] = "-1";
            } else if (expresion[i].equals("+")) {
                tem2[i] = "1";
            } else {
                temp3 = expresion[i].replaceAll("[+]", "");
                tem2[i] = temp3;
            }
        }
        for (int i = 0; i < expresion2.length; i++) {
            if (expresion2[i].equals("-")) {
                tem2_2[i] = "-1";
            } else if (expresion2[i].equals("+")) {
                tem2_2[i] = "1";
            } else {
                temp3_2 = expresion2[i].replaceAll("[+]", "");
                tem2_2[i] = temp3_2;
            }
        }
        //=========
        DecimalFormat formato = new DecimalFormat("#.##");

        a_v = Math.pow(Double.parseDouble(tem2[0]),2)+Math.pow(Double.parseDouble(tem2[1]),2)+Math.pow(Double.parseDouble(tem2[2]),2);
        b_w = Math.pow(Double.parseDouble(tem2_2[0]),2)+Math.pow(Double.parseDouble(tem2_2[1]),2)+Math.pow(Double.parseDouble(tem2_2[2]),2);

        vw = Math.acos(Double.parseDouble(v_w)/Math.sqrt(a_v*b_w))*(180/Math.PI);

        resultado += "v.w = "+v_w+"\n\nv = √(("+tem2[0]+")^2 + ("+tem2[1]+")^2 + ("+tem2[2]+")^2) \nv = √"+formato.format(a_v)+"\n\n" +
                "w = √(("+tem2_2[0]+")^2 + ("+tem2_2[1]+")^2 + ("+tem2_2[2]+")^2) \nw = √"+formato.format(b_w)+"\n\n"+"θvw = cos^-1(v.w/v*w) \n"+"θvw = "+formato.format(vw)+" °";

        return resultado;
    }
    //Verificar si el contenido de un String es un numero
    public static boolean isNumeric(String cadena){
        boolean resultado;

        try {
            Double.parseDouble(cadena);
            resultado=true;
        }catch (NumberFormatException e){
            resultado=false;
        }
        return resultado;
    }

    //Verifica si un numero es primo para validacion en raiz
    public static boolean isPrimo(int num){
        int contador = 2;
        boolean primo =true;
        if (num!=1) {
            while ((primo) && (contador != num)) {
                if (num % contador == 0)
                    primo = false;
                contador++;
            }
        }else {
            primo=false;
        }

        return primo;
    }

    //Verifica si un numero es entero o decimal
    public static boolean isInteger(Double x){
        if (x%1==0){
            return true;
        }else {
            return false;
        }
    }


}
