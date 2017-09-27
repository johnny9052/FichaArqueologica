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

    SharedPreferences persistencia;

    ClsUsuario clsUsuario;
    Helper helper;

    /*Referencias GUI*/
    EditText txtNombres, txtApellidos, txtEmail, txtProyecto;
    FloatingActionButton btnGuardar, btnReset;
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


    public void configuracionGUI(View view) {
        txtNombres = (EditText) view.findViewById(R.id.txtNombresInfoPersonal);
        txtApellidos = (EditText) view.findViewById(R.id.txtApellidosInfoPersonal);
        txtEmail = (EditText) view.findViewById(R.id.txtEmailInfoPersonal);
        txtProyecto = (EditText) view.findViewById(R.id.txtProyectoInfoPersonal);

        btnGuardar = (FloatingActionButton) view.findViewById(R.id.btnfGuardarInfoPersonal);
        btnReset = (FloatingActionButton) view.findViewById(R.id.btnfResetInfoPersonal);
    }


    public void configuracionListeners() {
        /*Actions - Listener*/
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos(v);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
    }


    /**
     * Verifica si ya existe informacion de usuario para cargarla
     */
    public void verificarDatos() {

        persistencia = getActivity().getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

        if (persistencia.getString("nombres", null) != null) {
            txtNombres.setText(persistencia.getString("nombres", ""));
            txtApellidos.setText(persistencia.getString("apellidos", ""));
            txtEmail.setText(persistencia.getString("correoElectronico", ""));
            txtProyecto.setText(persistencia.getString("nombreProyecto", ""));
        }

    }


    /**
     * Guarda los datos del usuario
     */
    public void guardarDatos(View view) {

        String nombre = txtNombres.getText().toString();
        String apellido = txtApellidos.getText().toString();
        String email = txtEmail.getText().toString();
        String proyecto = txtProyecto.getText().toString();


        if (helper.editTextValidarObligatorioMensaje(txtNombres) &&
                helper.editTextValidarObligatorioMensaje(txtApellidos) &&
                helper.editTextValidarObligatorioMensaje(txtEmail) &&
                helper.editTextValidarObligatorioMensaje(txtProyecto)) {


            clsUsuario = new ClsUsuario(nombre, apellido, email, proyecto);

            persistencia = getActivity().getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = persistencia.edit();

            editor.putString("nombres", clsUsuario.getNombres());
            editor.putString("apellidos", clsUsuario.getApellidos());
            editor.putString("correoElectronico", clsUsuario.getCorreoElectronico());
            editor.putString("nombreProyecto", clsUsuario.getNombreProyecto());

            helper.mostrarMensajeInferiorPantalla("Guardado con exito", view);

            editor.commit();
        } else {
            helper.mostrarMensajeInferiorPantalla("Verifique los campos obligatorios", getView());
        }
    }

}
