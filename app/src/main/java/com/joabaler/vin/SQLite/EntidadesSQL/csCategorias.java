package com.joabaler.vin.SQLite.EntidadesSQL;

public class csCategorias {

    private Integer Idcategoria;
    private String NombreCategoria;
    private String DetalleCategoria;
    private String UnidadCategoria;
    private String ImagenEjemplo;

    public csCategorias(Integer idcategoria, String nombreCategoria, String detalleCategoria, String unidadCategoria, String imagenEjemplo) {
        Idcategoria = idcategoria;
        NombreCategoria = nombreCategoria;
        DetalleCategoria = detalleCategoria;
        UnidadCategoria = unidadCategoria;
        ImagenEjemplo = imagenEjemplo;
    }

    public csCategorias() {  }

    public Integer getIdcategoria() {
        return Idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        Idcategoria = idcategoria;
    }

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        NombreCategoria = nombreCategoria;
    }

    public String getDetalleCategoria() {
        return DetalleCategoria;
    }

    public void setDetalleCategoria(String detalleCategoria) {
        DetalleCategoria = detalleCategoria;
    }

    public String getUnidadCategoria() {
        return UnidadCategoria;
    }

    public void setUnidadCategoria(String unidadCategoria) {
        UnidadCategoria = unidadCategoria;
    }

    public String getImagenEjemplo() {
        return ImagenEjemplo;
    }

    public void setImagenEjemplo(String imagenEjemplo) {
        ImagenEjemplo = imagenEjemplo;
    }
}
