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
import com.example.johnnyalexander.navigationdrawer2.Model.ClsMaterialRecuperado;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.IOException;


public class Fragment_Dialog_Material_Recuperado extends DialogFragment {


    EditText txtCeramicaNoBolsasMaterial, txtCeramicaNoFragmentosMaterial, txtLiticoNoBolsasMaterial,
            txtLiticoNoFragmentosMaterial, txtCarbonNoBolsasMaterial, txtRestosOseosNoBolsasMaterial,
            txtRestosOseosNoFragmentosMaterial, txtSueloNoBolsasMaterial, txtVidrioNoBolsasMaterial,
            txtVidrioNoFragmentosMaterial, txtOtroNoBolsasMaterial, txtDescripcionMaterial;
    Spinner spnNivelMaterialRecuperado;
    Button btnGuardarMaterialRecuperado, btnCancelarMaterialRecuperado;

    Helper helper;
    CtlFichasArqueologicas ficha;

    public static Fragment_Dialog_Material_Recuperado newInstance() {
        return new Fragment_Dialog_Material_Recuperado();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_material_recuperado, container, false);

        helper = new Helper();
        ficha = new CtlFichasArqueologicas();

        btnGuardarMaterialRecuperado = (Button) view.findViewById(R.id.btnGuardarMaterialRecuperado);

        btnCancelarMaterialRecuperado = (Button) view.findViewById(R.id.btnCancelarMaterialRecuperado);

        spnNivelMaterialRecuperado = (Spinner) view.findViewById(R.id.spnNivelMaterialRecuperado);


        txtCeramicaNoBolsasMaterial = (EditText) view.findViewById(R.id.txtCeramicaNoBolsasMaterial);
        txtCeramicaNoFragmentosMaterial = (EditText) view.findViewById(R.id.txtCeramicaNoFragmentosMaterial);
        txtLiticoNoBolsasMaterial = (EditText) view.findViewById(R.id.txtLiticoNoBolsasMaterial);
        txtLiticoNoFragmentosMaterial = (EditText) view.findViewById(R.id.txtLiticoNoFragmentosMaterial);
        txtCarbonNoBolsasMaterial = (EditText) view.findViewById(R.id.txtCarbonNoBolsasMaterial);
        txtRestosOseosNoBolsasMaterial = (EditText) view.findViewById(R.id.txtRestosOseosNoBolsasMaterial);
        txtRestosOseosNoFragmentosMaterial = (EditText) view.findViewById(R.id.txtRestosOseosNoFragmentosMaterial);
        txtSueloNoBolsasMaterial = (EditText) view.findViewById(R.id.txtSueloNoBolsasMaterial);
        txtVidrioNoBolsasMaterial = (EditText) view.findViewById(R.id.txtVidrioNoBolsasMaterial);
        txtVidrioNoFragmentosMaterial = (EditText) view.findViewById(R.id.txtVidrioNoFragmentosMaterial);
        txtOtroNoBolsasMaterial = (EditText) view.findViewById(R.id.txtOtroNoBolsasMaterial);
        txtDescripcionMaterial = (EditText) view.findViewById(R.id.txtDescripcionMaterial);

        configuracionGUI(view);
        configuracionListeners();

        return view;
    }


    public void configuracionListeners() {

        btnGuardarMaterialRecuperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    guardarMaterialRecuperado();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnCancelarMaterialRecuperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarMaterialRecuperado();
            }
        });

    }


    public void configuracionGUI(View view) {
        helper.spinnerCargarDatos(this.getActivity(), helper.getNiveles(), spnNivelMaterialRecuperado);
    }


    public void guardarMaterialRecuperado() throws IOException {

        if (ficha.infoBasicaRegistrada) {
            if (helper.spinnerValidarObligatorioMensaje(spnNivelMaterialRecuperado)) {

                String nivel = spnNivelMaterialRecuperado.getSelectedItem().toString();
                int ceramicaNoBolsas = helper.editTextValidarNumeroEntero(txtCeramicaNoBolsasMaterial);
                int ceramicaNoFragmentos = helper.editTextValidarNumeroEntero(txtCeramicaNoFragmentosMaterial);
                int liticoNoBolsas = helper.editTextValidarNumeroEntero(txtLiticoNoBolsasMaterial);
                int liticoNoFragmentos = helper.editTextValidarNumeroEntero(txtLiticoNoFragmentosMaterial);
                int carbonNoBolsas = helper.editTextValidarNumeroEntero(txtCarbonNoBolsasMaterial);
                int restosOseosNoBolsas = helper.editTextValidarNumeroEntero(txtRestosOseosNoBolsasMaterial);
                int restosOseosNoFragmentos = helper.editTextValidarNumeroEntero(txtRestosOseosNoFragmentosMaterial);
                int sueloNoBolsas = helper.editTextValidarNumeroEntero(txtSueloNoBolsasMaterial);
                int vidrioNoBolsas = helper.editTextValidarNumeroEntero(txtVidrioNoBolsasMaterial);
                int vidrioNoFragmentos = helper.editTextValidarNumeroEntero(txtVidrioNoFragmentosMaterial);
                int otroNoBolsas = helper.editTextValidarNumeroEntero(txtOtroNoBolsasMaterial);
                String descripcion = txtDescripcionMaterial.getText().toString();

                ficha.fichaTemporal.materialesRecuperados.add(new ClsMaterialRecuperado(nivel, ceramicaNoBolsas, ceramicaNoFragmentos, liticoNoBolsas, liticoNoFragmentos, carbonNoBolsas, restosOseosNoBolsas, restosOseosNoFragmentos, sueloNoBolsas, vidrioNoBolsas, vidrioNoFragmentos, otroNoBolsas, descripcion));

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

    public void cancelarMaterialRecuperado() {
        getDialog().dismiss();
    }


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


}
