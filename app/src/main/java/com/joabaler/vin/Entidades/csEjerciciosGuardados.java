package com.joabaler.vin.Entidades;

public class csEjerciciosGuardados {

    private Integer IdEjercicio;
    private String NombreEjercicio;
    private String DescripcionEjercicio;
    private Integer IdTema;
    private String DetalleDatos;

    public csEjerciciosGuardados(Integer idEjercicio, String nombreEjercicio, String descripcionEjercicio, Integer idTema, String detalleDatos) {
        IdEjercicio = idEjercicio;
        NombreEjercicio = nombreEjercicio;
        DescripcionEjercicio = descripcionEjercicio;
        IdTema = idTema;
        DetalleDatos = detalleDatos;
    }

    public csEjerciciosGuardados() {    }

    public Integer getIdEjercicio() {
        return IdEjercicio;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        IdEjercicio = idEjercicio;
    }

    public String getNombreEjercicio() {
        return NombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        NombreEjercicio = nombreEjercicio;
    }

    public String getDescripcionEjercicio() {
        return DescripcionEjercicio;
    }

    public void setDescripcionEjercicio(String descripcionEjercicio) {
        DescripcionEjercicio = descripcionEjercicio;
    }

    public Integer getIdTema() {
        return IdTema;
    }

    public void setIdTema(Integer idTema) {
        IdTema = idTema;
    }

    public String getDetalleDatos() {
        return DetalleDatos;
    }

    public void setDetalleDatos(String detalleDatos) {
        DetalleDatos = detalleDatos;
    }
}
