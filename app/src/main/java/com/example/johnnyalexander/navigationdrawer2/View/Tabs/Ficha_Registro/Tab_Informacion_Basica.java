package com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;


import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Permisos;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.IOException;


public class Tab_Informacion_Basica extends Fragment {

    /*Referencia objetos*/
    Helper helper;
    CtlFichasArqueologicas ficha;
    Permisos permisos;
    /*END Referencia objetos*/

    /*Referencia persistencia*/
    SharedPreferences persistencia;
    /*END Referencia persistencia*/

    /*Elementos GUI*/
    EditText txtNumeroSitioInfoBasica, txtPredioInfoBasica, txtVeredaInfoBasica, txtPKInfoBasica,
            txtProfesionalInfoBasica, txtFuenteHidricaInfoBasica, txtCorteInfoBasica,
            txtNivelExcavacionInfoBasica, txtDescripcionSitioInfoBasica, txtWPInfoBasica,
            txtProfundidadInic1InfoBasica, txtProfundidadInic2InfoBasica, txtProfundidadInic3InfoBasica,
            txtProfundidadInic4InfoBasica, txtCoordenadaX, txtCoordenadaY, txtMunicipio;

    CheckBox chkAgriculturaInfoBasica, chkGanaderiaInfoBasica, chkErosionInfoBasica, chkInundacionInfoBasica,
            chkGuaqueriaInfoBasica, chkObraCivilInfoBasica, chkMovimientosMasivosInfoBasica, chkOtroInfoBasica;

    Button btnCalcularCoordenadas;

    FloatingActionButton btnfGuardarInfoBasica;

    Spinner spnPaisaje, spnMicrotopografia, spnCoberturaVegetal,
            spnConservacion, spnResultado;

    ScrollView scrollInformacionBasica;
    /*END Elementos GUI*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_informacion_basica, container, false);

        helper = new Helper();
        ficha = new CtlFichasArqueologicas();
        permisos = new Permisos();

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
        * defecto y el municipio*/
        persistencia = getActivity().getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

        if (persistencia.getString("nombres", null) != null && persistencia.getString("nombres", "") != null) {
            txtProfesionalInfoBasica.setText(persistencia.getString("nombres", "") + " " +
                    persistencia.getString("apellidos", ""));

            txtMunicipio.setText(persistencia.getString("municipioProyecto", ""));
        }


        /*Se valida si existe informacion previamente cargada para realizar una edicion de datos,
        * cargando en los datos del formulario la informacion*/
        try {
            if (!ficha.fichaTemporal.basica.getCorte().equals("") && !ficha.fichaTemporal.basica.getCorte().equals(null)) {
                cargarDatos();
            }
        } catch (Exception e) {
            helper.mostrarMensaje(e.getMessage().toString(), getContext());
        }


    }


    /**
     * Se asocian los listeners a los botones
     */
    public void configuracionListeners() {
        /*Actions - Listener*/
        btnCalcularCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularCoordenadas(v);
            }
        });

        btnfGuardarInfoBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    guardarInformacionBasica(view, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*End Actions - Listeners*/
    }


    //https://www.codota.com/android/scenarios/52fcbdd6da0a6fdfa46309f0/finding-current-location?tag=dragonfly

    /**
     * Calcula las coordenadas y las carga en los campos de coordenadas
     */
    public void calcularCoordenadas(View view) {

        if (permisos.verificarPermiso(Manifest.permission.ACCESS_FINE_LOCATION, permisos.LOCATION, getActivity())) {
            Location loc = helper.gpsCalcularCoordenadas(getActivity(), getContext(), view);
            if (loc != null) {
                txtCoordenadaX.setText((loc.getLatitude()) + "");
                txtCoordenadaY.setText((loc.getLongitude()) + "");
                helper.mostrarMensajeInferiorPantalla("Coordenadas calculadas", getView());
            }
        }
    }


    /**
     * Asocia los elementos de la interfaz grafica, carga combos y prepara otros componentes
     *
     * @param view Vista con los elementos que seran asociados
     */
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

        txtMunicipio = (EditText) view.findViewById(R.id.txtMunicipioInfoBasica);
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

        scrollInformacionBasica = (ScrollView) view.findViewById(R.id.scrollInformacionBasica);

        /*Se habilita para que el scroll se pueda mover en cualquier momento, es decir a cambiarle
        * el focus en cualquier momento*/
        scrollInformacionBasica.requestFocus(View.FOCUS_UP);
        /*END Referencias GUI*/


        /*Carga de Spinners*/
        //helper.spinnerCargarDatos(getActivity(), helper.getMunicipios(), txtMunicipio);
        helper.spinnerCargarDatos(getActivity(), helper.getPaisajes(), spnPaisaje);
        helper.spinnerCargarDatos(getActivity(), helper.getMicrotopografia(), spnMicrotopografia);
        helper.spinnerCargarDatos(getActivity(), helper.getCoberturaVegetal(), spnCoberturaVegetal);
        helper.spinnerCargarDatos(getActivity(), helper.getGradoConservacion(), spnConservacion);
        helper.spinnerCargarDatos(getActivity(), helper.getResultado(), spnResultado);
        /*END Carga de Spinners*/
    }


    /**
     * Guarda la informacion basica de una ficha arqueologica
     *
     * @param view    Vista con los elementos donde se obtendran los datos
     * @param mensaje Indica si se debe de mostrar mensajes al intententar guardar o no
     * @throws IOException
     */
    public void guardarInformacionBasica(View view, boolean mensaje) throws IOException {

        if (helper.editTextValidarObligatorioMensaje(txtNumeroSitioInfoBasica) &&
                helper.editTextValidarObligatorioMensaje(txtCorteInfoBasica)) {

            ficha.fichaTemporal.basica.setNombreProfesional(txtProfesionalInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setNumeroSitio(helper.editTextValidarNumeroEntero(txtNumeroSitioInfoBasica));
            ficha.fichaTemporal.basica.setCorte(txtCorteInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setPredio(txtPredioInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setVereda(txtVeredaInfoBasica.getText().toString());
            ficha.fichaTemporal.basica.setMunicipio(txtMunicipio.getText().toString());
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
                if (mensaje) {
                    helper.mostrarMensajeInferiorPantalla("Almacenado correctamente", view);

                    /*Se bloqueda la informacion primaria no modificable*/
                    txtNumeroSitioInfoBasica.setEnabled(false);
                    txtCorteInfoBasica.setEnabled(false);
                }
                /*Indicamos que ya se pueden guardar las otras pestañas*/
                ficha.infoBasicaRegistrada = true;
            } else {
                helper.mostrarMensajeInferiorPantalla("Error al almacenar", view);
            }
        } else {
            if (mensaje) {
                helper.mostrarMensajeInferiorPantalla("Verifique los campos obligatorios", getView());
                /*Se hace focus en la parte superior de la pantalla*/
                scrollInformacionBasica.scrollTo(0, 0);
            }
        }
    }


    /**
     * Carga informacion si existe previamente para su respectiva edicion
     */
    public void cargarDatos() {

        //helper.mostrarMensaje("Cargar datos info basica", getContext());

        txtProfesionalInfoBasica.setText(ficha.fichaTemporal.basica.getNombreProfesional());
        txtNumeroSitioInfoBasica.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getNumeroSitio()));
        txtCorteInfoBasica.setText(ficha.fichaTemporal.basica.getCorte());
        txtPredioInfoBasica.setText(ficha.fichaTemporal.basica.getPredio());
        txtVeredaInfoBasica.setText(ficha.fichaTemporal.basica.getVereda());
        txtPKInfoBasica.setText(ficha.fichaTemporal.basica.getPk());
        txtFuenteHidricaInfoBasica.setText(ficha.fichaTemporal.basica.getFuenteHidrica());
        txtNivelExcavacionInfoBasica.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getNivelesExcavacion()));
        txtDescripcionSitioInfoBasica.setText(ficha.fichaTemporal.basica.getDescripcionGeneralSitio());
        txtWPInfoBasica.setText(ficha.fichaTemporal.basica.getWp());
        txtCoordenadaX.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getCoordenaraX()));
        txtCoordenadaY.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getGetCoordenaraY()));
        txtProfundidadInic1InfoBasica.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getProfundidad1()));
        txtProfundidadInic2InfoBasica.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getProfundidad2()));
        txtProfundidadInic3InfoBasica.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getProfundidad3()));
        txtProfundidadInic4InfoBasica.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.basica.getProfundidad4()));

        txtMunicipio.setText(ficha.fichaTemporal.basica.getMunicipio());

        spnResultado.setSelection(helper.spinnerObtenerPosicionValor(spnResultado, ficha.fichaTemporal.basica.getResultado()));
        spnCoberturaVegetal.setSelection(helper.spinnerObtenerPosicionValor(spnCoberturaVegetal, ficha.fichaTemporal.basica.getCoverturaVegetal()));
        spnMicrotopografia.setSelection(helper.spinnerObtenerPosicionValor(spnMicrotopografia, ficha.fichaTemporal.basica.getMicrotopografia()));
        spnPaisaje.setSelection(helper.spinnerObtenerPosicionValor(spnPaisaje, ficha.fichaTemporal.basica.getElementoPaisaje()));
        spnConservacion.setSelection(helper.spinnerObtenerPosicionValor(spnConservacion, ficha.fichaTemporal.basica.getGradoConservacion()));

        chkAgriculturaInfoBasica.setChecked(ficha.fichaTemporal.basica.isAgriculturaFactorAlteracion());
        chkGanaderiaInfoBasica.setChecked(ficha.fichaTemporal.basica.isGanaderiaFactorAlteracion());
        chkErosionInfoBasica.setChecked(ficha.fichaTemporal.basica.isErocionFactorAlteracion());
        chkInundacionInfoBasica.setChecked(ficha.fichaTemporal.basica.isInundacionFactorAlteracion());
        chkGuaqueriaInfoBasica.setChecked(ficha.fichaTemporal.basica.isGuaqueriaFactorAlteracion());
        chkObraCivilInfoBasica.setChecked(ficha.fichaTemporal.basica.isObraCivilFactorAlteracion());
        chkMovimientosMasivosInfoBasica.setChecked(ficha.fichaTemporal.basica.isMovimientosMasivosFactorAlteracion());
        chkOtroInfoBasica.setChecked(ficha.fichaTemporal.basica.isOtroFactorAlteracion());

        /*Se bloqueda la informacion primaria no modificable*/
        txtNumeroSitioInfoBasica.setEnabled(false);
        txtCorteInfoBasica.setEnabled(false);

    }


    /**
     * Funcion que se ejecuta cuando el fragment deja de estar visible
     *
     * @param isVisibleToUser
     */
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //Log.e("**************", "Entreeeeeeeee2");
        } else {
            if (getView() != null) {
                try {
                    //Log.e("**************", "Entreeeeeeeee");
                    guardarInformacionBasica(getView(), false);
                } catch (IOException e) {
                    //Log.e("**************", "Entreeeeeeeee con error");
                    e.printStackTrace();
                }
            } else {
                //Log.e("**************", "Entreeeeeeeee estaba vacio");
            }

        }
    }

}
