package com.joabaler.vin.Entidades;

import java.io.Serializable;

public class csTemasContenido implements Serializable {

    private Integer IdTema;
    private Integer CategoriaTema;
    private String NombreTema;
    private String DetalleTema;
    private Integer ImagenEjercicio;
    private Boolean PasoAPaso;
    private Boolean Grafica;


    public csTemasContenido(Integer idTema, Integer categoriaTema, String nombreTema, String detalleTema, Integer imagenEjercicio, Boolean pasoAPaso,Boolean grafica) {
        IdTema = idTema;
        CategoriaTema = categoriaTema;
        NombreTema = nombreTema;
        DetalleTema = detalleTema;
        ImagenEjercicio = imagenEjercicio;
        PasoAPaso = pasoAPaso;
        Grafica = grafica;
    }

    public csTemasContenido(){  }

    public Integer getIdTema() {
        return IdTema;
    }

    public void setIdTema(Integer idTema) {
        IdTema = idTema;
    }

    public Integer getCategoriaTema() {
        return CategoriaTema;
    }

    public void setCategoriaTema(Integer categoriaTema) {
        CategoriaTema = categoriaTema;
    }

    public String getNombreTema() {
        return NombreTema;
    }

    public void setNombreTema(String nombreTema) {
        NombreTema = nombreTema;
    }

    public String getDetalleTema() {
        return DetalleTema;
    }

    public void setDetalleTema(String detalleTema) {
        DetalleTema = detalleTema;
    }

    public Integer getImagenEjercicio() {
        return ImagenEjercicio;
    }

    public void setImagenEjercicio(Integer imagenEjercicio) {
        ImagenEjercicio = imagenEjercicio;
    }

    public Boolean getPasoAPaso() {
        return PasoAPaso;
    }

    public void setPasoAPaso(Boolean pasoAPaso) {
        PasoAPaso = pasoAPaso;
    }

    public Boolean getGrafica() {
        return Grafica;
    }

    public void setGrafica(Boolean grafica) {
        Grafica = grafica;
    }
}
