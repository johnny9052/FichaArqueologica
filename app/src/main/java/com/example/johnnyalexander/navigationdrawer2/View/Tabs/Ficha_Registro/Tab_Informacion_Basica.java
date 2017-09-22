package com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.IOException;


public class Tab_Informacion_Basica extends Fragment {

    Helper helper;
    CtlFichasArqueologicas ficha;
    SharedPreferences persistencia;

    /*Elementos GUI*/

    EditText txtNumeroSitioInfoBasica, txtPredioInfoBasica, txtVeredaInfoBasica, txtPKInfoBasica,
            txtProfesionalInfoBasica, txtFuenteHidricaInfoBasica, txtCorteInfoBasica,
            txtNivelExcavacionInfoBasica, txtDescripcionSitioInfoBasica, txtWPInfoBasica,
            txtProfundidadInic1InfoBasica, txtProfundidadInic2InfoBasica, txtProfundidadInic3InfoBasica,
            txtProfundidadInic4InfoBasica, txtCoordenadaX, txtCoordenadaY;

    CheckBox chkAgriculturaInfoBasica, chkGanaderiaInfoBasica, chkErosionInfoBasica, chkInundacionInfoBasica,
            chkGuaqueriaInfoBasica, chkObraCivilInfoBasica, chkMovimientosMasivosInfoBasica, chkOtroInfoBasica;

    Button btnCalcularCoordenadas;

    FloatingActionButton btnfGuardarInfoBasica;

    Spinner spnMunicipios, spnPaisaje, spnMicrotopografia, spnCoberturaVegetal,
            spnConservacion, spnResultado;

    /*END Elementos GUI*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_informacion_basica, container, false);

        helper = new Helper();
        ficha = new CtlFichasArqueologicas();

        configuracionGUI(view);
        configuracionListeners();
        verificarDatos();



        return view;
    }


    /**
     * Verifica si ya existe informacion de usuario para cargarla
     */
    public void verificarDatos() {

        /*Se verifica el nombre registrado en la aplicacion para ponerlo como profesional por
        * defecto*/
        persistencia = getActivity().getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

        if (persistencia.getString("nombres", null) != null) {
            txtProfesionalInfoBasica.setText(persistencia.getString("nombres", "") + " " +
                    persistencia.getString("apellidos", ""));
        }


        /*Se valida si existe informacion previamente cargada para realizar una edicion de datos,
        * cargando en los datos del formulario la informacion*/
        if(ficha.fichaTemporal.basica.getCorte()!="" && ficha.fichaTemporal.basica.getCorte()!=null){
            helper.mostrarMensaje("debemos cargar datos",getContext());
        }

    }


    public void configuracionListeners() {
        /*Actions - Listener*/

        btnCalcularCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularCoordenadas();
            }
        });


        btnfGuardarInfoBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    guardarInformacionBasica(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*End Actions - Listeners*/
    }


    //https://www.codota.com/android/scenarios/52fcbdd6da0a6fdfa46309f0/finding-current-location?tag=dragonfly
    public void calcularCoordenadas() {
        Location loc = helper.gpsCalcularCoordenadas(getActivity(), getContext());
        if (loc != null) {
            txtCoordenadaX.setText((loc.getLatitude()) + "");
            txtCoordenadaY.setText((loc.getLongitude()) + "");
            helper.mostrarMensaje("Coordenadas calculadas", getActivity());
        }
    }


    public void configuracionGUI(View view) {

         /*Referencias GUI*/

        txtNumeroSitioInfoBasica = (EditText) view.findViewById(R.id.txtNumeroSitioInfoBasica);
        txtPredioInfoBasica = (EditText) view.findViewById(R.id.txtPredioInfoBasica);
        txtVeredaInfoBasica = (EditText) view.findViewById(R.id.txtVeredaInfoBasica);
        txtPKInfoBasica = (EditText) view.findViewById(R.id.txtPKInfoBasica);
        txtProfesionalInfoBasica = (EditText) view.findViewById(R.id.txtProfesionalInfoBasica);
        txtFuenteHidricaInfoBasica = (EditText) view.findViewById(R.id.txtFuenteHidricaInfoBasica);
        txtCorteInfoBasica = (EditText) view.findViewById(R.id.txtCorteInfoBasica);
        txtNivelExcavacionInfoBasica = (EditText) view.findViewById(R.id.txtNivelExcavacionInfoBasica);
        txtDescripcionSitioInfoBasica = (EditText) view.findViewById(R.id.txtDescripcionSitioInfoBasica);
        txtWPInfoBasica = (EditText) view.findViewById(R.id.txtWPInfoBasica);
        txtProfundidadInic1InfoBasica = (EditText) view.findViewById(R.id.txtProfundidadInic1InfoBasica);
        txtProfundidadInic2InfoBasica = (EditText) view.findViewById(R.id.txtProfundidadInic2InfoBasica);
        txtProfundidadInic3InfoBasica = (EditText) view.findViewById(R.id.txtProfundidadInic3InfoBasica);
        txtProfundidadInic4InfoBasica = (EditText) view.findViewById(R.id.txtProfundidadInic4InfoBasica);

        txtCoordenadaX = (EditText) view.findViewById(R.id.txtCoordenadaXInfoBasica);
        txtCoordenadaY = (EditText) view.findViewById(R.id.txtCoordenadaYInfoBasica);

        spnMunicipios = (Spinner) view.findViewById(R.id.spnMunicipioInfoBasica);
        spnPaisaje = (Spinner) view.findViewById(R.id.spnPaisajeInfoBasica);
        spnMicrotopografia = (Spinner) view.findViewById(R.id.spnMicrotopografiaInfoBasica);
        spnCoberturaVegetal = (Spinner) view.findViewById(R.id.spnCoberturaVegetalInfoBasica);
        spnConservacion = (Spinner) view.findViewById(R.id.spnConservacionInfoBasica);
        spnResultado = (Spinner) view.findViewById(R.id.spnResultadoInfoBasica);

        btnCalcularCoordenadas = (Button) view.findViewById(R.id.btnCalcularCoordenadasInfoBasica);


        btnfGuardarInfoBasica = (FloatingActionButton) view.findViewById(R.id.btnfGuardarInfoBasica);


        chkAgriculturaInfoBasica = (CheckBox) view.findViewById(R.id.chkAgriculturaInfoBasica);
        chkGanaderiaInfoBasica = (CheckBox) view.findViewById(R.id.chkGanaderiaInfoBasica);
        chkErosionInfoBasica = (CheckBox) view.findViewById(R.id.chkErosionInfoBasica);
        chkInundacionInfoBasica = (CheckBox) view.findViewById(R.id.chkInundacionInfoBasica);
        chkGuaqueriaInfoBasica = (CheckBox) view.findViewById(R.id.chkGuaqueriaInfoBasica);
        chkObraCivilInfoBasica = (CheckBox) view.findViewById(R.id.chkObraCivilInfoBasica);
        chkMovimientosMasivosInfoBasica = (CheckBox) view.findViewById(R.id.chkMovimientosMasivosInfoBasica);
        chkOtroInfoBasica = (CheckBox) view.findViewById(R.id.chkOtroInfoBasica);
        /*END Referencias GUI*/


        /*Carga de Spinners*/
        helper.spinnerCargarDatos(getActivity(), helper.getMunicipios(), spnMunicipios);
        helper.spinnerCargarDatos(getActivity(), helper.getPaisajes(), spnPaisaje);
        helper.spinnerCargarDatos(getActivity(), helper.getMicrotopografia(), spnMicrotopografia);
        helper.spinnerCargarDatos(getActivity(), helper.getCoberturaVegetal(), spnCoberturaVegetal);
        helper.spinnerCargarDatos(getActivity(), helper.getGradoConservacion(), spnConservacion);
        helper.spinnerCargarDatos(getActivity(), helper.getResultado(), spnResultado);
    }


    public void guardarInformacionBasica(View view) throws IOException {

        if (helper.editTextValidarObligatorioMensaje(txtNumeroSitioInfoBasica) &&
                helper.editTextValidarObligatorioMensaje(txtCorteInfoBasica)) {

            ficha.fichaTemporal.basica.setNombreProfesional(txtProfesionalInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setNumeroSitio(helper.editTextValidarNumeroEntero(txtNumeroSitioInfoBasica));
            ficha.fichaTemporal.basica.setCorte(txtCorteInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setPredio(txtPredioInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setVereda(txtVeredaInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setMunicipio(spnMunicipios.getSelectedItem().toString());
            ficha.fichaTemporal.basica.setPk(txtPKInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setFuenteHidrica(txtFuenteHidricaInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setNivelesExcavacion(helper.editTextValidarNumeroEntero(txtNivelExcavacionInfoBasica));
            ficha.fichaTemporal.basica.setElementoPaisaje(spnPaisaje.getSelectedItem().toString());
            ficha.fichaTemporal.basica.setMicrotopografia(spnMicrotopografia.getSelectedItem().toString());
            ficha.fichaTemporal.basica.setCoverturaVegetal(spnCoberturaVegetal.getSelectedItem().toString());
            ficha.fichaTemporal.basica.setDescripcionGeneralSitio(txtDescripcionSitioInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setAgriculturaFactorAlteracion(chkAgriculturaInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setGanaderiaFactorAlteracion(chkGanaderiaInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setErocionFactorAlteracion(chkErosionInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setInundacionFactorAlteracion(chkInundacionInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setGuaqueriaFactorAlteracion(chkGuaqueriaInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setObraCivilFactorAlteracion(chkObraCivilInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setMovimientosMasivosFactorAlteracion(chkMovimientosMasivosInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setOtroFactorAlteracion(chkOtroInfoBasica.isChecked());
            ficha.fichaTemporal.basica.setGradoConservacion(spnConservacion.getSelectedItem().toString());
            ficha.fichaTemporal.basica.setWp(txtWPInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setCoordenaraX(helper.editTextValidarNumeroReal(txtCoordenadaX));
            ficha.fichaTemporal.basica.setGetCoordenaraY(helper.editTextValidarNumeroReal(txtCoordenadaY));
            ficha.fichaTemporal.basica.setResultado(spnResultado.getSelectedItem().toString());
            ficha.fichaTemporal.basica.setProfundidad1(helper.editTextValidarNumeroReal(txtProfundidadInic1InfoBasica));
            ficha.fichaTemporal.basica.setProfundidad2(helper.editTextValidarNumeroReal(txtProfundidadInic2InfoBasica));
            ficha.fichaTemporal.basica.setProfundidad3(helper.editTextValidarNumeroReal(txtProfundidadInic3InfoBasica));
            ficha.fichaTemporal.basica.setProfundidad4(helper.editTextValidarNumeroReal(txtProfundidadInic4InfoBasica));

            String json = helper.JSON_ObjetoToJSON(ficha.fichaTemporal);

            String nombreArchivo = helper.nombreArchivo(ficha.fichaTemporal);

            if (helper.ArchivoTextoCrear(json, nombreArchivo, getContext(), "json")) {
                helper.mostrarMensajeInferiorPantalla("Almacenado correctamente", view);
                /*Indicamos que ya se pueden guardar las otras pestañas*/
                ficha.infoBasicaRegistrada =true;
            } else {
                helper.mostrarMensajeInferiorPantalla("Error al almacenar", view);
            }
        } else {
            helper.mostrarMensajeInferiorPantalla("Verifique los campos obligatorios", getView());
        }


    }


}
