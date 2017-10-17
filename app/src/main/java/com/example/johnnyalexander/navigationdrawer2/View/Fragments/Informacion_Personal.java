package com.example.johnnyalexander.navigationdrawer2.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.MainActivity;
import com.example.johnnyalexander.navigationdrawer2.Model.ClsUsuario;
import com.example.johnnyalexander.navigationdrawer2.R;


public class Informacion_Personal extends Fragment {

    /*Persistencia*/
    SharedPreferences persistencia;
    /*END Persistencia*/

    /*Referencia Objetos*/
    ClsUsuario clsUsuario;
    Helper helper;
    /*END Referencia Objetos*/

    /*Referencias GUI*/
    EditText txtNombres, txtApellidos, txtEmail, txtProyecto, txtMunicipio;
    FloatingActionButton btnGuardar;
    /*END Referencias GUI*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_informacion_personal, container, false);

        helper = new Helper();

        configuracionGUI(view);
        configuracionListeners();
        verificarDatos();

        return view;
    }


    /**
     * Funcion que asocia todos los componentes graficos del Layout para poder utilizarlos
     *
     * @param view Vista sobre la cual se referenciaran los elementos graficos
     */
    public void configuracionGUI(View view) {
        /*Referencias cajas de texto*/
        txtNombres = (EditText) view.findViewById(R.id.txtNombresInfoPersonal);
        txtApellidos = (EditText) view.findViewById(R.id.txtApellidosInfoPersonal);
        txtEmail = (EditText) view.findViewById(R.id.txtEmailInfoPersonal);
        txtProyecto = (EditText) view.findViewById(R.id.txtProyectoInfoPersonal);
        txtMunicipio = (EditText) view.findViewById(R.id.txtMunicipioInfoPersonal);
        /*END Referencias cajas de texto*/

        /*Referencia botones*/
        btnGuardar = (FloatingActionButton) view.findViewById(R.id.btnfGuardarInfoPersonal);
        /*END Referencia botones*/
    }


    /**
     * Funcion que asocia los listeners a los componentes graficos
     */
    public void configuracionListeners() {
        /*Actions - Listener*/
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos(v);
            }
        });

        /*END Actions - Listener*/
    }


    /**
     * Verifica si ya existe informacion de usuario para cargarla
     */
    public void verificarDatos() {

        persistencia = getActivity().getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

        if (persistencia.getString("nombres", null) != null && !persistencia.getString("nombres", "").equals("")) {
            txtNombres.setText(persistencia.getString("nombres", ""));
            txtApellidos.setText(persistencia.getString("apellidos", ""));
            txtEmail.setText(persistencia.getString("correoElectronico", ""));
            txtProyecto.setText(persistencia.getString("nombreProyecto", ""));
            txtMunicipio.setText(persistencia.getString("municipioProyecto", ""));
        }
    }


    /**
     * Funcion que guarda los datos del usuario en un sharedPreferences
     *
     * @param view Vista de donde se obtendran los componentes graficos
     */
    public void guardarDatos(View view) {

        String nombre = txtNombres.getText().toString();
        String apellido = txtApellidos.getText().toString();
        String email = txtEmail.getText().toString();
        String proyecto = txtProyecto.getText().toString();
        String municipio = txtMunicipio.getText().toString();

        if (helper.editTextValidarObligatorioMensaje(txtNombres) &&
                helper.editTextValidarObligatorioMensaje(txtApellidos) &&
                helper.editTextValidarObligatorioMensaje(txtEmail) &&
                helper.editTextValidarObligatorioMensaje(txtProyecto) &&
                helper.editTextValidarObligatorioMensaje(txtMunicipio)) {


            clsUsuario = new ClsUsuario(nombre, apellido, email, proyecto, municipio);

            persistencia = getActivity().getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = persistencia.edit();

            editor.putString("nombres", clsUsuario.getNombres());
            editor.putString("apellidos", clsUsuario.getApellidos());
            editor.putString("correoElectronico", clsUsuario.getCorreoElectronico());
            editor.putString("nombreProyecto", clsUsuario.getNombreProyecto());
            editor.putString("municipioProyecto", clsUsuario.getMunicipio());

            helper.mostrarMensaje("Informacion guardada con exito", getContext());

            Intent i = new Intent(getContext(), MainActivity.class);
            i.putExtra("fragment", "informacionPersonal");
            startActivity(i);
            getActivity().finish();

            editor.commit();
        } else {
            helper.mostrarMensajeInferiorPantalla("Verifique los campos obligatorios", getView());
        }
    }

}
