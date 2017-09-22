package com.example.johnnyalexander.navigationdrawer2.Model;

/**
 * Created by Johnny Alexander on 19/09/2017.
 */

public class ClsEstratigrafia {

    private int numeroHorizonte;
    private String horizonteInicial;
    private String horizonteFinal;
    private String color;
    private String textura;
    private String estructura;
    private String consistencia;
    private String inclusiones;

    public ClsEstratigrafia(int numeroHorizonte, String horizonteInicial, String horizonteFinal, String color, String textura, String estructura, String consistencia, String inclusiones) {
        this.numeroHorizonte = numeroHorizonte;
        this.horizonteInicial = horizonteInicial;
        this.horizonteFinal = horizonteFinal;
        this.color = color;
        this.textura = textura;
        this.estructura = estructura;
        this.consistencia = consistencia;
        this.inclusiones = inclusiones;
    }

    public ClsEstratigrafia() {

    }

    public int getNumeroHorizonte() {
        return numeroHorizonte;
    }

    public void setNumeroHorizonte(int numeroHorizonte) {
        this.numeroHorizonte = numeroHorizonte;
    }

    public String getHorizonteInicial() {
        return horizonteInicial;
    }

    public void setHorizonteInicial(String horizonteInicial) {
        this.horizonteInicial = horizonteInicial;
    }

    public String getHorizonteFinal() {
        return horizonteFinal;
    }

    public void setHorizonteFinal(String horizonteFinal) {
        this.horizonteFinal = horizonteFinal;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTextura() {
        return textura;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public String getConsistencia() {
        return consistencia;
    }

    public void setConsistencia(String consistencia) {
        this.consistencia = consistencia;
    }

    public String getInclusiones() {
        return inclusiones;
    }

    public void setInclusiones(String inclusiones) {
        this.inclusiones = inclusiones;
    }
}
