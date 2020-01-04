package com.joabaler.vin.Entidades;

public class csDiccionario {
    private String Tipo;
    private Double Valor;
    private String Tipo2;
    private Double Valor2;

    public csDiccionario(String tipo, Double valor,String tipo2, Double valor2) {
        Tipo = tipo;
        Valor = valor;
        Tipo2 = tipo2;
        Valor2 = valor2;
    }
    public csDiccionario(){}

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public void setTipo2(String tipo2) {
        Tipo2 = tipo2;
    }

    public void setValor2(Double valor2) {
        Valor2 = valor2;
    }

    public String getTipo() {
        return Tipo;
    }

    public Double getValor() {
        return Valor;
    }
    public String getTipo2() {
        return Tipo2;
    }

    public Double getValor2() {
        return Valor2;
    }
}

