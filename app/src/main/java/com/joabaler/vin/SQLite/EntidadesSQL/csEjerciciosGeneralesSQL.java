package com.joabaler.vin.SQLite.EntidadesSQL;

public class csEjerciciosGeneralesSQL {

    private Integer IdRegistro;
    private Integer IdEjercicio;
    private String NombreTema;
    private String NombreCaegoria;
    private Integer IdCategoria;
    private Integer IdTema;


    public csEjerciciosGeneralesSQL(Integer idRegistro, Integer idEjercicio, String nombreTema, String nombreCaegoria, Integer idCategoria, Integer idTema) {
        IdRegistro = idRegistro;
        IdEjercicio = idEjercicio;
        NombreTema = nombreTema;
        NombreCaegoria = nombreCaegoria;
        IdCategoria = idCategoria;
        IdTema = idTema;
    }
    public csEjerciciosGeneralesSQL(){  }

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

    public String getNombreTema() {
        return NombreTema;
    }

    public void setNombreTema(String nombreTema) {
        NombreTema = nombreTema;
    }

    public String getNombreCaegoria() {
        return NombreCaegoria;
    }

    public void setNombreCaegoria(String nombreCaegoria) {
        NombreCaegoria = nombreCaegoria;
    }

    public Integer getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        IdCategoria = idCategoria;
    }

    public Integer getIdTema() {
        return IdTema;
    }

    public void setIdTema(Integer idTema) {
        IdTema = idTema;
    }
}
