package com.joabaler.vin.Entidades;

public class csVectorProyeccion {
    private Integer Cuadrante;
    private String Direccion;
    private String Apunta;
    private Double Fuerza;
    private Double Angulo;
    private String AngRespectoA;

    public csVectorProyeccion(Integer cuadrante, String direccion, String apunta, Double fuerza, Double angulo, String angRespectoA) {
        Cuadrante = cuadrante;
        Direccion = direccion;
        Apunta = apunta;
        Fuerza = fuerza;
        Angulo = angulo;
        AngRespectoA = angRespectoA;
    }
    public csVectorProyeccion(){    }

    public Integer getCuadrante() {
        return Cuadrante;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getApunta() {
        return Apunta;
    }

    public Double getFuerza() {
        return Fuerza;
    }

    public Double getAngulo() {
        return Angulo;
    }

    public String getAngRespectoA() {
        return AngRespectoA;
    }

    public void setCuadrante(Integer cuadrante) {
        Cuadrante = cuadrante;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public void setApunta(String apunta) {
        Apunta = apunta;
    }

    public void setFuerza(Double fuerza) {
        Fuerza = fuerza;
    }

    public void setAngulo(Double angulo) {
        Angulo = angulo;
    }

    public void setAngRespectoA(String angRespectoA) {
        AngRespectoA = angRespectoA;
    }
}
