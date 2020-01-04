package com.joabaler.vin.Entidades;

public class csMatriz {

    private Integer IdEcuacion;
    private Double w;
    private Double x;
    private Double y;
    private Double z;
    private Double Igual;
    private Integer Tamano;

    public csMatriz(Integer idEcuacion, Double w, Double x, Double y, Double z, Double igual, Integer tamano) {
        IdEcuacion = idEcuacion;
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        Igual = igual;
        Tamano = tamano;
    }

    public csMatriz(){  }

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
}
