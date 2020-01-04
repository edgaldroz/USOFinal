package com.joabaler.vin.SQLite.EntidadesSQL;

public class csMatrizSQL {

    private Integer IdRegistro;
    private Integer IdEjercicio;
    private Integer IdEcuacion;
    private Double w;
    private Double x;
    private Double y;
    private Double z;
    private Double Igual;
    private Integer Tamano;
    private String NombreTema;
    private String CategoriaTema;
    private String IdTema;

    public csMatrizSQL() {

    }

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

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double getIgual() {
        return Igual;
    }

    public void setIgual(Double igual) {
        Igual = igual;
    }

    public Integer getTamano() {
        return Tamano;
    }

    public void setTamano(Integer tamano) {
        Tamano = tamano;
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

    public String getIdTema() { return IdTema;  }

    public void setIdTema(String idTema) {  IdTema = idTema; }
}
