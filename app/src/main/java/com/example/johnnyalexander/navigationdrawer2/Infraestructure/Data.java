package com.example.johnnyalexander.navigationdrawer2.Infraestructure;

/**
 * Created by Johnny Alexander on 21/09/2017.
 */

public class Data {

    private String[] municipios = {"--Seleccione un municipio--", "Tebaida", "Calarca", "Armenia", "Pereira", "Manizales", "Bogota"};
    private String[] paisajes = {"--Seleccione paisaje--", "Terraza", "Colina", "Coluvio", "Lomerío", "Planicie", "Valle", "Ladera", "Otro"};
    private String[] microtopografia = {"--Seleccione una microtopografia--", "Plana", "Quebrada", "Ondulada"};
    private String[] coberturaVegetal = {"--Seleccione una cobertura--", "Pastos", "Cultivos", "Arbustales", "Rastrojo", "Vegetacion secundaria", "Otros"};
    private String[] gradoConservacion = {"--Seleccione un grado--", "Alto", "Medio", "Bajo", "Muy bajo"};
    private String[] resultado = {"--Seleccione resultado--", "Positivo", "Negativo"};
    private String[] textura = {"--Seleccione textura--", "Limos", "Arenas", "Arcillas", "Francos", "Francos-arenosos", "Franco-arcillosos", "Franco-arenoarcillosos", "Franco-arcilloarenosos"};
    private String[] estructura = {"--Seleccione estructura--", "Bloques", "Granular", "Suelto"};
    private String[] consistencia = {"--Seleccione consistencia--", "Dura", "Firme", "Blanda", "Muy friable"};
    private String[] niveles = {"--Seleccione nivel--", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};


    public String[] getMunicipios() {
        return municipios;
    }

    public void setMunicipios(String[] municipios) {
        this.municipios = municipios;
    }

    public String[] getPaisajes() {
        return paisajes;
    }

    public void setPaisajes(String[] paisajes) {
        this.paisajes = paisajes;
    }

    public String[] getMicrotopografia() {
        return microtopografia;
    }

    public void setMicrotopografia(String[] microtopografia) {
        this.microtopografia = microtopografia;
    }

    public String[] getCoberturaVegetal() {
        return coberturaVegetal;
    }

    public void setCoberturaVegetal(String[] coberturaVegetal) {
        this.coberturaVegetal = coberturaVegetal;
    }


    public String[] getGradoConservacion() {
        return gradoConservacion;
    }

    public void setGradoConservacion(String[] gradoConservacion) {
        this.gradoConservacion = gradoConservacion;
    }

    public String[] getResultado() {
        return resultado;
    }

    public void setResultado(String[] resultado) {
        this.resultado = resultado;
    }

    public String[] getTextura() {
        return textura;
    }

    public void setTextura(String[] textura) {
        this.textura = textura;
    }

    public String[] getEstructura() {
        return estructura;
    }

    public void setEstructura(String[] estructura) {
        this.estructura = estructura;
    }

    public String[] getConsistencia() {
        return consistencia;
    }

    public void setConsistencia(String[] consistencia) {
        this.consistencia = consistencia;
    }

    public String[] getNiveles() {
        return niveles;
    }

    public void setNiveles(String[] niveles) {
        this.niveles = niveles;
    }
}
