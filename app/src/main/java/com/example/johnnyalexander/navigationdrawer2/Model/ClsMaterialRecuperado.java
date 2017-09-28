package com.example.johnnyalexander.navigationdrawer2.Model;

/**
 * Created by Johnny Alexander on 20/09/2017.
 */

public class ClsMaterialRecuperado {


    String nivel;
    int ceramicaNoBolsas;
    int ceramicaNoFragmentos;
    int liticoNoBolsas;
    int liticoNoFragmentos;
    int carbonNoBolsas;
    int restosOseosNoBolsas;
    int restosOseosNoFragmentos;
    int sueloNoBolsas;
    int vidrioNoBolsas;
    int vidrioNoFragmentos;
    int otroNoBolsas;
    String descripcion;



    public ClsMaterialRecuperado(String nivel, int ceramicaNoBolsas, int ceramicaNoFragmentos, int liticoNoBolsas, int liticoNoFragmentos, int carbonNoBolsas, int restosOseosNoBolsas, int restosOseosNoFragmentos, int sueloNoBolsas, int vidrioNoBolsas, int vidrioNoFragmentos, int otroNoBolsas, String descripcion) {
        this.nivel = nivel;
        this.ceramicaNoBolsas = ceramicaNoBolsas;
        this.ceramicaNoFragmentos = ceramicaNoFragmentos;
        this.liticoNoBolsas = liticoNoBolsas;
        this.liticoNoFragmentos = liticoNoFragmentos;
        this.carbonNoBolsas = carbonNoBolsas;
        this.restosOseosNoBolsas = restosOseosNoBolsas;
        this.restosOseosNoFragmentos = restosOseosNoFragmentos;
        this.sueloNoBolsas = sueloNoBolsas;
        this.vidrioNoBolsas = vidrioNoBolsas;
        this.vidrioNoFragmentos = vidrioNoFragmentos;
        this.otroNoBolsas = otroNoBolsas;
        this.descripcion = descripcion;
    }

    public ClsMaterialRecuperado() {

    }


    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getCeramicaNoBolsas() {
        return ceramicaNoBolsas;
    }

    public void setCeramicaNoBolsas(int ceramicaNoBolsas) {
        this.ceramicaNoBolsas = ceramicaNoBolsas;
    }

    public int getCeramicaNoFragmentos() {
        return ceramicaNoFragmentos;
    }

    public void setCeramicaNoFragmentos(int ceramicaNoFragmentos) {
        this.ceramicaNoFragmentos = ceramicaNoFragmentos;
    }

    public int getLiticoNoBolsas() {
        return liticoNoBolsas;
    }

    public void setLiticoNoBolsas(int liticoNoBolsas) {
        liticoNoBolsas = liticoNoBolsas;
    }

    public int getLiticoNoFragmentos() {
        return liticoNoFragmentos;
    }

    public void setLiticoNoFragmentos(int liticoNoFragmentos) {
        liticoNoFragmentos = liticoNoFragmentos;
    }

    public int getCarbonNoBolsas() {
        return carbonNoBolsas;
    }

    public void setCarbonNoBolsas(int carbonNoBolsas) {
        this.carbonNoBolsas = carbonNoBolsas;
    }

    public int getRestosOseosNoBolsas() {
        return restosOseosNoBolsas;
    }

    public void setRestosOseosNoBolsas(int restosOseosNoBolsas) {
        this.restosOseosNoBolsas = restosOseosNoBolsas;
    }

    public int getRestosOseosNoFragmentos() {
        return restosOseosNoFragmentos;
    }

    public void setRestosOseosNoFragmentos(int restosOseosNoFragmentos) {
        this.restosOseosNoFragmentos = restosOseosNoFragmentos;
    }

    public int getSueloNoBolsas() {
        return sueloNoBolsas;
    }

    public void setSueloNoBolsas(int sueloNoBolsas) {
        this.sueloNoBolsas = sueloNoBolsas;
    }

    public int getVidrioNoBolsas() {
        return vidrioNoBolsas;
    }

    public void setVidrioNoBolsas(int vidrioNoBolsas) {
        this.vidrioNoBolsas = vidrioNoBolsas;
    }

    public int getVidrioNoFragmentos() {
        return vidrioNoFragmentos;
    }

    public void setVidrioNoFragmentos(int vidrioNoFragmentos) {
        this.vidrioNoFragmentos = vidrioNoFragmentos;
    }

    public int getOtroNoBolsas() {
        return otroNoBolsas;
    }

    public void setOtroNoBolsas(int otroNoBolsas) {
        this.otroNoBolsas = otroNoBolsas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
