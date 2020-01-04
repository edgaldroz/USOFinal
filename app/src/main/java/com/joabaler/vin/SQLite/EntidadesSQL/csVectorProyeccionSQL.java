package com.joabaler.vin.SQLite.EntidadesSQL;

public class csVectorProyeccionSQL {

    private Integer IdRegistro;
    private Integer IdEjercicio;
    private Integer IdEcuacion;
    private Integer Cuadrante;
    private String Direccion;
    private String Apunta;
    private Double Fuerza;
    private Double Angulo;
    private String AngRespectoA;
    private String NombreTema;
    private String CategoriaTema;
    private String IdTema;

    public csVectorProyeccionSQL(Integer idRegistro, Integer idEjercicio, Integer idEcuacion, Integer cuadrante, String direccion, String apunta, Double fuerza, Double angulo, String angRespectoA, String nombreTema, String categoriaTema, String idTema) {
        IdRegistro = idRegistro;
        IdEjercicio = idEjercicio;
        IdEcuacion = idEcuacion;
        Cuadrante = cuadrante;
        Direccion = direccion;
        Apunta = apunta;
        Fuerza = fuerza;
        Angulo = angulo;
        AngRespectoA = angRespectoA;
        NombreTema = nombreTema;
        CategoriaTema = categoriaTema;
        IdTema = idTema;
    }
    public csVectorProyeccionSQL(){ }

    public Integer getIdRegistro() {
        return IdRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        IdRegistro = idRegistro;
    }

    public Integer getIdEjercicio() {
        return IdEjercicio;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        IdEjercicio = idEjercicio;
    }

    public Integer getIdEcuacion() {
        return IdEcuacion;
    }

    public void setIdEcuacion(Integer idEcuacion) {
        IdEcuacion = idEcuacion;
    }

    public Integer getCuadrante() {
        return Cuadrante;
    }

    public void setCuadrante(Integer cuadrante) {
        Cuadrante = cuadrante;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getApunta() {
        return Apunta;
    }

    public void setApunta(String apunta) {
        Apunta = apunta;
    }

    public Double getFuerza() {
        return Fuerza;
    }

    public void setFuerza(Double fuerza) {
        Fuerza = fuerza;
    }

    public Double getAngulo() {
        return Angulo;
    }

    public void setAngulo(Double angulo) {
        Angulo = angulo;
    }

    public String getAngRespectoA() {
        return AngRespectoA;
    }

    public void setAngRespectoA(String angRespectoA) {
        AngRespectoA = angRespectoA;
    }

    public String getNombreTema() {
        return NombreTema;
    }

    public void setNombreTema(String nombreTema) {
        NombreTema = nombreTema;
    }

    public String getCategoriaTema() {
        return CategoriaTema;
    }

    public void setCategoriaTema(String categoriaTema) {
        CategoriaTema = categoriaTema;
    }

    public String getIdTema() {
        return IdTema;
    }

    public void setIdTema(String idTema) {
        IdTema = idTema;
    }
}
