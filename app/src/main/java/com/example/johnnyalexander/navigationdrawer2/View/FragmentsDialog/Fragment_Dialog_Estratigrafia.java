package com.example.johnnyalexander.navigationdrawer2.View.FragmentsDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.Model.ClsEstratigrafia;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.IOException;


public class Fragment_Dialog_Estratigrafia extends DialogFragment {


    /*Elementos GUI*/
    EditText txtNumeroHorizonteEstratigrafia, txtHInicialEstratigrafia, txtHFinalEstratigrafia,
            txtColorEstratigrafia, txtInclusionesEstratigrafia;
    Spinner spnTexturaEstratigrafia, spnEstructuraEstratigrafia, spnConcistenciaEstratigrafia;

    Button btnGuardarEstratigrafia, btnCancelarEstratigrafia;
    /*END Elementos GUI*/


    Helper helper;
    CtlFichasArqueologicas ficha;


    /*FUNCION NECESARIA PARA MOSTRAR EL DIALOG*/
    public static Fragment_Dialog_Estratigrafia newInstance() {
        return new Fragment_Dialog_Estratigrafia();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_estratigrafia, container, false);

        helper = new Helper();
        ficha = new CtlFichasArqueologicas();

        configuracionGUI(view);
        configuracionListeners();

        return view;
    }


    public void configuracionListeners() {

        btnGuardarEstratigrafia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    guardarEstratigrafia(v);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCancelarEstratigrafia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarEstratigrafia();
            }
        });

    }


    public void configuracionGUI(View view) {

        txtNumeroHorizonteEstratigrafia = (EditText) view.findViewById(R.id.txtNumeroHorizonteEstratigrafia);
        txtHInicialEstratigrafia = (EditText) view.findViewById(R.id.txtHInicialEstratigrafia);
        txtHFinalEstratigrafia = (EditText) view.findViewById(R.id.txtHFinalEstratigrafia);
        txtColorEstratigrafia = (EditText) view.findViewById(R.id.txtColorEstratigrafia);
        spnTexturaEstratigrafia = (Spinner) view.findViewById(R.id.spnTexturaEstratigrafia);
        spnEstructuraEstratigrafia = (Spinner) view.findViewById(R.id.spnEstructuraEstratigrafia);
        spnConcistenciaEstratigrafia = (Spinner) view.findViewById(R.id.spnConcistenciaEstratigrafia);
        txtInclusionesEstratigrafia = (EditText) view.findViewById(R.id.txtInclusionesEstratigrafia);


        spnTexturaEstratigrafia = (Spinner) view.findViewById(R.id.spnTexturaEstratigrafia);
        spnEstructuraEstratigrafia = (Spinner) view.findViewById(R.id.spnEstructuraEstratigrafia);
        spnConcistenciaEstratigrafia = (Spinner) view.findViewById(R.id.spnConcistenciaEstratigrafia);

        btnGuardarEstratigrafia = (Button) view.findViewById(R.id.btnGuardarEstratigrafia);

        btnCancelarEstratigrafia = (Button) view.findViewById(R.id.btnCancelarEstratigrafia);


        helper.spinnerCargarDatos(this.getActivity(), helper.getTextura(), spnTexturaEstratigrafia);
        helper.spinnerCargarDatos(this.getActivity(), helper.getEstructura(), spnEstructuraEstratigrafia);
        helper.spinnerCargarDatos(this.getActivity(), helper.getConsistencia(), spnConcistenciaEstratigrafia);
    }


    public void guardarEstratigrafia(View view) throws IOException {

        if (ficha.infoBasicaRegistrada) {
            if (helper.editTextValidarObligatorioMensaje(txtNumeroHorizonteEstratigrafia)) {


                int numeroHorizonte = helper.editTextValidarNumeroEntero(txtNumeroHorizonteEstratigrafia);
                String horizonteInicial = txtHInicialEstratigrafia.getText().toString();
                String horizonteFinal = txtHFinalEstratigrafia.getText().toString();
                String color = txtColorEstratigrafia.getText().toString();
                String textura = spnTexturaEstratigrafia.getSelectedItem().toString();
                String estructura = spnEstructuraEstratigrafia.getSelectedItem().toString();
                String consistencia = spnConcistenciaEstratigrafia.getSelectedItem().toString();
                String inclusiones = txtInclusionesEstratigrafia.getText().toString();


                ficha.fichaTemporal.estratigrafias.add(new ClsEstratigrafia(numeroHorizonte, horizonteInicial, horizonteFinal, color, textura, estructura, consistencia, inclusiones));

                String json = helper.JSON_ObjetoToJSON(ficha.fichaTemporal);

                String nombreArchivo = helper.nombreArchivo(ficha.fichaTemporal);

                if (helper.ArchivoTextoCrear(json, nombreArchivo, getContext(), "json")) {
                    getDialog().dismiss();
                    helper.mostrarMensaje("Almacenado correctamente", getContext());
                } else {
                    helper.mostrarMensaje("Error al almacenar", getContext());
                }
            }
        } else {
            helper.mostrarMensaje("Primero debe almacenar la informacion basica", getContext());
        }
    }

    /**
     * Cierra el dialogo actual
     */
    public void cancelarEstratigrafia() {
        getDialog().dismiss();
    }


    /*CODIGO NECESARIO PARA QUE SE EJECUTE LA FUNCION EN EL FRAGMENT ANTERIOR CUANDO ESTE SE CIERRA*/
    private DialogInterface.OnDismissListener onDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }
    /*END CODIGO NECESARIO PARA QUE SE EJECUTE LA FUNCION EN EL FRAGMENT ANTERIOR CUANDO ESTE SE CIERRA*/


}
