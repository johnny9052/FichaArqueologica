package com.example.johnnyalexander.navigationdrawer2.Model;

/**
 * Created by Johnny Alexander on 14/09/2017.
 */

public class ClsUsuario {

    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String nombreProyecto;


    public ClsUsuario(String nombres, String apellidos, String correoElectronico, String nombreProyecto) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.nombreProyecto = nombreProyecto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }
}
