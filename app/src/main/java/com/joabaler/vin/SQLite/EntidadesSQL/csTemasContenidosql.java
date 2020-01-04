package com.joabaler.vin.SQLite.EntidadesSQL;

public class csTemasContenidosql {
    private Integer IdTemaContenido;
    private String NombreTema;
    private String DescripcionTema;
    private Integer PasoAPaso;
    private String ImgenEjemplo;
    private Integer IdCategoria;


    public csTemasContenidosql(Integer idTemaContenido, String nombreTema, String descripcionTema, Integer pasoAPaso, String imgenEjemplo, Integer idCategoria) {
        IdTemaContenido = idTemaContenido;
        NombreTema = nombreTema;
        DescripcionTema = descripcionTema;
        PasoAPaso = pasoAPaso;
        ImgenEjemplo = imgenEjemplo;
        IdCategoria = idCategoria;
    }
    public csTemasContenidosql(){  }


    public Integer getIdTemaContenido() {
        return IdTemaContenido;
    }

    public void setIdTemaContenido(Integer idTemaContenido) {
        IdTemaContenido = idTemaContenido;
    }

    public String getNombreTema() {
        return NombreTema;
    }

    public void setNombreTema(String nombreTema) {
        NombreTema = nombreTema;
    }

    public String getDescripcionTema() {
        return DescripcionTema;
    }

    public void setDescripcionTema(String descripcionTema) {
        DescripcionTema = descripcionTema;
    }

    public Integer getPasoAPaso() {
        return PasoAPaso;
    }

    public void setPasoAPaso(Integer pasoAPaso) {
        PasoAPaso = pasoAPaso;
    }

    public String getImgenEjemplo() {
        return ImgenEjemplo;
    }

    public void setImgenEjemplo(String imgenEjemplo) {
        ImgenEjemplo = imgenEjemplo;
    }

    public Integer getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        IdCategoria = idCategoria;
    }
}
