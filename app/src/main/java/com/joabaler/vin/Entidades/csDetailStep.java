package com.joabaler.vin.Entidades;

import java.io.Serializable;

public class csDetailStep implements Serializable {
    private Integer Correlativo;
    private String Quesehizo;
    private String Porquesehizo;
    private String Procedimiento;

    public csDetailStep(Integer correlativo, String quesehizo, String porquesehizo, String procedimiento) {
        Correlativo = correlativo;
        Quesehizo = quesehizo;
        Porquesehizo = porquesehizo;
        Procedimiento = procedimiento;
    }
    public  csDetailStep(){ }

    public Integer getCorrelativo() {
        return Correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        Correlativo = correlativo;
    }

    public String getQuesehizo() {
        return Quesehizo;
    }

    public void setQuesehizo(String quesehizo) {
        Quesehizo = quesehizo;
    }

    public String getPorquesehizo() {
        return Porquesehizo;
    }

    public void setPorquesehizo(String porquesehizo) {
        Porquesehizo = porquesehizo;
    }

    public String getProcedimiento() {
        return Procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        Procedimiento = procedimiento;
    }
}
