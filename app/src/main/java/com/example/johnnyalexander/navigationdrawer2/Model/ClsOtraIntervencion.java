package com.example.johnnyalexander.navigationdrawer2.Model;

import java.util.ArrayList;

/**
 * Created by Johnny Alexander on 20/09/2017.
 */

public class ClsOtraIntervencion {

    String ampliaciones;
    int numeroApliaciones;
    boolean superior;
    boolean inferior;
    boolean derecha;
    boolean izquierda;
    String ampliacionesDescripcionGeneral;
    String corteArea;
    String corteAreaNomenclatura;
    String corteAreaDescripcionGeneral;
    String trinchera;
    String trincheraNomenclatura;
    String trincheraDescripcionGeneral;
    public ArrayList<String> fotografias = new ArrayList<String>();
    int totalFotografias;
    int totalDibujos;
    int otros;
    String fechaInicio;
    String fechaFin;

    public ClsOtraIntervencion(String ampliaciones, int numeroApliaciones, boolean superior, boolean inferior, boolean derecha, boolean izquierda, String ampliacionesDescripcionGeneral, String corteArea, String corteAreaNomenclatura, String corteAreaDescripcionGeneral, String trinchera, String trincheraNomenclatura, String trincheraDescripcionGeneral, ArrayList<String> fotografias, int totalFotografias, int totalDibujos, int otros, String fechaInicio, String fechaFin) {
        this.ampliaciones = ampliaciones;
        this.numeroApliaciones = numeroApliaciones;
        this.superior = superior;
        this.inferior = inferior;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.ampliacionesDescripcionGeneral = ampliacionesDescripcionGeneral;
        this.corteArea = corteArea;
        this.corteAreaNomenclatura = corteAreaNomenclatura;
        this.corteAreaDescripcionGeneral = corteAreaDescripcionGeneral;
        this.trinchera = trinchera;
        this.trincheraNomenclatura = trincheraNomenclatura;
        this.trincheraDescripcionGeneral = trincheraDescripcionGeneral;
        this.fotografias = fotografias;
        this.totalFotografias = totalFotografias;
        this.totalDibujos = totalDibujos;
        this.otros = otros;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ClsOtraIntervencion() {

    }

    public String isAmpliaciones() {
        return ampliaciones;
    }

    public void setAmpliaciones(String ampliaciones) {
        this.ampliaciones = ampliaciones;
    }

    public int getNumeroApliaciones() {
        return numeroApliaciones;
    }

    public void setNumeroApliaciones(int numeroApliaciones) {
        this.numeroApliaciones = numeroApliaciones;
    }

    public boolean isSuperior() {
        return superior;
    }

    public void setSuperior(boolean superior) {
        this.superior = superior;
    }

    public boolean isInferior() {
        return inferior;
    }

    public void setInferior(boolean inferior) {
        this.inferior = inferior;
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    public String getAmpliacionesDescripcionGeneral() {
        return ampliacionesDescripcionGeneral;
    }

    public void setAmpliacionesDescripcionGeneral(String ampliacionesDescripcionGeneral) {
        this.ampliacionesDescripcionGeneral = ampliacionesDescripcionGeneral;
    }

    public String isCorteArea() {
        return corteArea;
    }

    public void setCorteArea(String corteArea) {
        this.corteArea = corteArea;
    }

    public String getCorteAreaNomenclatura() {
        return corteAreaNomenclatura;
    }

    public void setCorteAreaNomenclatura(String corteAreaNomenclatura) {
        this.corteAreaNomenclatura = corteAreaNomenclatura;
    }

    public String getCorteAreaDescripcionGeneral() {
        return corteAreaDescripcionGeneral;
    }

    public void setCorteAreaDescripcionGeneral(String corteAreaDescripcionGeneral) {
        this.corteAreaDescripcionGeneral = corteAreaDescripcionGeneral;
    }

    public String isTrinchera() {
        return trinchera;
    }

    public void setTrinchera(String trinchera) {
        this.trinchera = trinchera;
    }

    public String getTrincheraNomenclatura() {
        return trincheraNomenclatura;
    }

    public void setTrincheraNomenclatura(String trincheraNomenclatura) {
        this.trincheraNomenclatura = trincheraNomenclatura;
    }

    public String getTrincheraDescripcionGeneral() {
        return trincheraDescripcionGeneral;
    }

    public void setTrincheraDescripcionGeneral(String trincheraDescripcionGeneral) {
        this.trincheraDescripcionGeneral = trincheraDescripcionGeneral;
    }

    public ArrayList<String> getFotografias() {
        return fotografias;
    }

    public void setFotografias(ArrayList<String> fotografias) {
        this.fotografias = fotografias;
    }

    public int getTotalFotografias() {
        return totalFotografias;
    }

    public void setTotalFotografias(int totalFotografias) {
        this.totalFotografias = totalFotografias;
    }

    public int getTotalDibujos() {
        return totalDibujos;
    }

    public void setTotalDibujos(int totalDibujos) {
        this.totalDibujos = totalDibujos;
    }

    public int getOtros() {
        return otros;
    }

    public void setOtros(int otros) {
        this.otros = otros;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}
