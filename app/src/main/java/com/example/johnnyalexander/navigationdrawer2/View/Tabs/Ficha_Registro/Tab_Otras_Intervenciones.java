package com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Permisos;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


public class Tab_Otras_Intervenciones extends Fragment {

    /*Referencia objetos*/
    Helper helper;
    CtlFichasArqueologicas ficha;
    Permisos permisos;
    /*END Referencia objetos*/

    /*Elementos GUI*/
    EditText txtFechaInicioOtrasIntervenciones, txtFechaFinOtrasIntervenciones;

    EditText txtNumeroAmpliacionesOtrasIntervenciones, txtDescripcionGeneralAmpliacionesOtrasIntervenciones, txtCorteAreaNomenclaturaOtrasIntervenciones,
            txtCorteAreaDescripcionOtrasIntervenciones, txtTrincheraNomenclaturaOtrasIntervenciones, txtTrincheraDescripcionGeneralOtrasIntervenciones,
            txtTotalFotografiasOtrasIntervenciones, txtTotalDibujosOtrasIntervenciones, txtTotalOtrosOtrasIntervenciones;

    RadioButton rdbApliacionNoOtrasIntervenciones, rdbApliacionSiOtrasIntervenciones, rdbCorteAreaSiOtrasIntervenciones,
            rdbCorteAreaNoOtrasIntervenciones, rdbTrincheraNoOtrasIntervenciones, rdbTrincheraSiOtrasIntervenciones;

    CheckBox chkSuperiorOtrasIntervenciones, chkInferiorOtrasIntervenciones, chkDerechaOtrasIntervenciones,
            chkIzquierdaOtrasIntervenciones;

    Button btnTomarFotografia1, btnTomarFotografia2;


    RadioGroup rdgApliacionOtrasIntervenciones, rdgCorteAreaOtrasIntervenciones, rdgTrincheraOtrasIntervenciones;

    ImageView imgView1, imgView2;

    FloatingActionButton btnfGuardarOtrasIntervenciones;
    /*END Elementos GUI*/


    /*Codigo de respuesta para el actionResult de la fotografia*/
    static final int REQUEST_IMAGE_CAPTURE = 1;
    /*Numero de la foto*/
    int numberPhoto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_otras_intervenciones, container, false);

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
        if (ficha.fichaTemporal.basica.getCorte() != "") {
            cargarDatos();
        }
    }

    /**
     * Se asocian los listeners a los botones
     */
    public void configuracionListeners() {

        helper.editTextToCalendar(txtFechaInicioOtrasIntervenciones, getActivity());
        helper.editTextToCalendar(txtFechaFinOtrasIntervenciones, getActivity());

         /*Actions - Listener*/
        btnTomarFotografia1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(1);
            }
        });


        btnTomarFotografia2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(2);
            }
        });


        btnfGuardarOtrasIntervenciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    guardarOtrasIntervenciones(view, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        /*End Actions - Listeners*/
    }


    /**
     * Asocia los elementos de la interfaz grafica, carga combos y prepara otros componentes
     *
     * @param view Vista con los elementos que seran asociados
     */
    public void configuracionGUI(View view) {
        /*Referencias GUI*/
        txtNumeroAmpliacionesOtrasIntervenciones = (EditText) view.findViewById(R.id.txtNumeroAmpliacionesOtrasIntervenciones);
        txtDescripcionGeneralAmpliacionesOtrasIntervenciones = (EditText) view.findViewById(R.id.txtDescripcionGeneralAmpliacionesOtrasIntervenciones);
        txtCorteAreaNomenclaturaOtrasIntervenciones = (EditText) view.findViewById(R.id.txtCorteAreaNomenclaturaOtrasIntervenciones);
        txtCorteAreaDescripcionOtrasIntervenciones = (EditText) view.findViewById(R.id.txtCorteAreaDescripcionOtrasIntervenciones);
        txtTrincheraNomenclaturaOtrasIntervenciones = (EditText) view.findViewById(R.id.txtTrincheraNomenclaturaOtrasIntervenciones);
        txtTrincheraDescripcionGeneralOtrasIntervenciones = (EditText) view.findViewById(R.id.txtTrincheraDescripcionGeneralOtrasIntervenciones);
        txtTotalFotografiasOtrasIntervenciones = (EditText) view.findViewById(R.id.txtTotalFotografiasOtrasIntervenciones);
        txtTotalDibujosOtrasIntervenciones = (EditText) view.findViewById(R.id.txtTotalDibujosOtrasIntervenciones);
        txtTotalOtrosOtrasIntervenciones = (EditText) view.findViewById(R.id.txtTotalOtrosOtrasIntervenciones);


        rdbApliacionNoOtrasIntervenciones = (RadioButton) view.findViewById(R.id.rdbApliacionNoOtrasIntervenciones);
        rdbApliacionSiOtrasIntervenciones = (RadioButton) view.findViewById(R.id.rdbApliacionSiOtrasIntervenciones);
        rdbCorteAreaSiOtrasIntervenciones = (RadioButton) view.findViewById(R.id.rdbCorteAreaSiOtrasIntervenciones);
        rdbCorteAreaNoOtrasIntervenciones = (RadioButton) view.findViewById(R.id.rdbCorteAreaNoOtrasIntervenciones);
        rdbTrincheraNoOtrasIntervenciones = (RadioButton) view.findViewById(R.id.rdbTrincheraNoOtrasIntervenciones);
        rdbTrincheraSiOtrasIntervenciones = (RadioButton) view.findViewById(R.id.rdbTrincheraSiOtrasIntervenciones);

        chkSuperiorOtrasIntervenciones = (CheckBox) view.findViewById(R.id.chkSuperiorOtrasIntervenciones);
        chkInferiorOtrasIntervenciones = (CheckBox) view.findViewById(R.id.chkInferiorOtrasIntervenciones);
        chkDerechaOtrasIntervenciones = (CheckBox) view.findViewById(R.id.chkDerechaOtrasIntervenciones);
        chkIzquierdaOtrasIntervenciones = (CheckBox) view.findViewById(R.id.chkIzquierdaOtrasIntervenciones);


        txtFechaInicioOtrasIntervenciones = (EditText) view.findViewById(R.id.txtFechaInicioOtrasIntervenciones);
        txtFechaFinOtrasIntervenciones = (EditText) view.findViewById(R.id.txtFechaFinOtrasIntervenciones);

        btnTomarFotografia1 = (Button) view.findViewById(R.id.btnTomarFoto1);
        btnTomarFotografia2 = (Button) view.findViewById(R.id.btnTomarFoto2);

        rdgApliacionOtrasIntervenciones = (RadioGroup) view.findViewById(R.id.rdgApliacionOtrasIntervenciones);
        rdgCorteAreaOtrasIntervenciones = (RadioGroup) view.findViewById(R.id.rdgCorteAreaOtrasIntervenciones);
        rdgTrincheraOtrasIntervenciones = (RadioGroup) view.findViewById(R.id.rdgTrincheraOtrasIntervenciones);


        btnfGuardarOtrasIntervenciones = (FloatingActionButton) view.findViewById(R.id.btnfGuardarOtrasIntervenciones);

        imgView1 = (ImageView) view.findViewById(R.id.imgView1);
        imgView2 = (ImageView) view.findViewById(R.id.imgView2);
        /*END Referencias GUI*/
    }

    /**
     * Guarda la informacion basica de otras intervensiones
     *
     * @param view    Vista con los elementos donde se obtendran los datos
     * @param mensaje Indica si se debe de mostrar mensajes al intententar guardar o no
     * @throws IOException
     */
    public void guardarOtrasIntervenciones(View view, boolean mensaje) throws IOException {

        if (ficha.infoBasicaRegistrada) {

            ArrayList<String> fotografias = new ArrayList<String>();

            ficha.fichaTemporal.otras.setFechaInicio(txtFechaInicioOtrasIntervenciones.getText().toString());
            ficha.fichaTemporal.otras.setFechaFin(txtFechaFinOtrasIntervenciones.getText().toString());
            ficha.fichaTemporal.otras.setSuperior(chkSuperiorOtrasIntervenciones.isChecked());
            ficha.fichaTemporal.otras.setInferior(chkInferiorOtrasIntervenciones.isChecked());
            ficha.fichaTemporal.otras.setDerecha(chkDerechaOtrasIntervenciones.isChecked());
            ficha.fichaTemporal.otras.setIzquierda(chkIzquierdaOtrasIntervenciones.isChecked());
            ficha.fichaTemporal.otras.setAmpliacionesDescripcionGeneral(txtDescripcionGeneralAmpliacionesOtrasIntervenciones.getText().toString());
            ficha.fichaTemporal.otras.setCorteAreaNomenclatura(txtCorteAreaNomenclaturaOtrasIntervenciones.getText().toString());
            ficha.fichaTemporal.otras.setCorteAreaDescripcionGeneral(txtCorteAreaDescripcionOtrasIntervenciones.getText().toString());
            ficha.fichaTemporal.otras.setTrincheraNomenclatura(txtTrincheraNomenclaturaOtrasIntervenciones.getText().toString());
            ficha.fichaTemporal.otras.setTrincheraDescripcionGeneral(txtTrincheraDescripcionGeneralOtrasIntervenciones.getText().toString());
            ficha.fichaTemporal.otras.setNumeroApliaciones(helper.editTextValidarNumeroEntero(txtNumeroAmpliacionesOtrasIntervenciones));
            ficha.fichaTemporal.otras.setTotalFotografias(helper.editTextValidarNumeroEntero(txtTotalFotografiasOtrasIntervenciones));
            ficha.fichaTemporal.otras.setTotalDibujos(helper.editTextValidarNumeroEntero(txtTotalDibujosOtrasIntervenciones));
            ficha.fichaTemporal.otras.setOtros(helper.editTextValidarNumeroEntero(txtTotalOtrosOtrasIntervenciones));

            ficha.fichaTemporal.otras.setAmpliaciones(helper.radioValorSeleccionado(rdgApliacionOtrasIntervenciones));
            ficha.fichaTemporal.otras.setCorteArea(helper.radioValorSeleccionado(rdgCorteAreaOtrasIntervenciones));
            ficha.fichaTemporal.otras.setTrinchera(helper.radioValorSeleccionado(rdgTrincheraOtrasIntervenciones));

            String json = helper.JSON_ObjetoToJSON(ficha.fichaTemporal);
            String nombreArchivo = helper.nombreArchivo(ficha.fichaTemporal);

            if (helper.ArchivoTextoCrear(json, nombreArchivo, getContext(), "json")) {
                if (mensaje) {
                    helper.mostrarMensajeInferiorPantalla("Almacenado correctamente", view);
                }
            } else {
                helper.mostrarMensajeInferiorPantalla("Error al almacenar", view);
            }
        } else {
            if (mensaje) {
                helper.mostrarMensaje("Primero debe almacenar la informacion basica", getContext());
            }
        }


    }


    /**
     * Activa la camara y retorna la imagen tomada en un actionResult
     *
     * @param numberPhoto
     */
    public void tomarFoto(int numberPhoto) {
        this.numberPhoto = numberPhoto;

        String nombreArchivo = "imagen.jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //File foto = new File(getActivity().getExternalFilesDir(null),nombreArchivo);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(foto));
        //startActivity(intent);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            if (numberPhoto == 1) {
                imgView1.setImageBitmap(imageBitmap);
            } else {
                imgView2.setImageBitmap(imageBitmap);
            }
        }
    }

    /**
     * Carga informacion si existe previamente para su respectiva edicion
     */
    public void cargarDatos() {
        //helper.mostrarMensaje("debemos cargar datos de interveciones", getContext());


        /*ArrayList<String> fotografias = new ArrayList<String>();
        */

        txtFechaInicioOtrasIntervenciones.setText(ficha.fichaTemporal.otras.getFechaInicio());
        txtNumeroAmpliacionesOtrasIntervenciones.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.otras.getNumeroApliaciones()));

        txtFechaFinOtrasIntervenciones.setText(ficha.fichaTemporal.otras.getFechaFin());
        txtDescripcionGeneralAmpliacionesOtrasIntervenciones.setText(ficha.fichaTemporal.otras.getAmpliacionesDescripcionGeneral());
        txtCorteAreaNomenclaturaOtrasIntervenciones.setText(ficha.fichaTemporal.otras.getCorteAreaNomenclatura());
        txtCorteAreaDescripcionOtrasIntervenciones.setText(ficha.fichaTemporal.otras.getCorteAreaDescripcionGeneral());
        txtTrincheraNomenclaturaOtrasIntervenciones.setText(ficha.fichaTemporal.otras.getTrincheraNomenclatura());
        txtTrincheraDescripcionGeneralOtrasIntervenciones.setText(ficha.fichaTemporal.otras.getTrincheraDescripcionGeneral());

        txtTotalFotografiasOtrasIntervenciones.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.otras.getTotalFotografias()));
        txtTotalDibujosOtrasIntervenciones.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.otras.getTotalDibujos()));
        txtTotalOtrosOtrasIntervenciones.setText(helper.numeroValidarCargaObligatorio(ficha.fichaTemporal.otras.getOtros()));

        chkSuperiorOtrasIntervenciones.setChecked(ficha.fichaTemporal.otras.isSuperior());
        chkInferiorOtrasIntervenciones.setChecked(ficha.fichaTemporal.otras.isInferior());
        chkDerechaOtrasIntervenciones.setChecked(ficha.fichaTemporal.otras.isDerecha());
        chkIzquierdaOtrasIntervenciones.setChecked(ficha.fichaTemporal.otras.isIzquierda());

        if (helper.radioGroupIndiceRadioSeleccionado(rdgApliacionOtrasIntervenciones, ficha.fichaTemporal.otras.isAmpliaciones()) != -1) {
            rdgApliacionOtrasIntervenciones.check(helper.radioGroupIndiceRadioSeleccionado(rdgApliacionOtrasIntervenciones, ficha.fichaTemporal.otras.isAmpliaciones()));
        }

        if (helper.radioGroupIndiceRadioSeleccionado(rdgCorteAreaOtrasIntervenciones, ficha.fichaTemporal.otras.isCorteArea()) != -1) {
            rdgCorteAreaOtrasIntervenciones.check(helper.radioGroupIndiceRadioSeleccionado(rdgCorteAreaOtrasIntervenciones, ficha.fichaTemporal.otras.isCorteArea()));
        }

        if (helper.radioGroupIndiceRadioSeleccionado(rdgTrincheraOtrasIntervenciones, ficha.fichaTemporal.otras.isTrinchera()) != -1) {
            rdgTrincheraOtrasIntervenciones.check(helper.radioGroupIndiceRadioSeleccionado(rdgTrincheraOtrasIntervenciones, ficha.fichaTemporal.otras.isTrinchera()));
        }

    }


    /*
    public void recuperarFoto(View v) {
        Bitmap bitmap1 =
                BitmapFactory.
                        decodeFile(getExternalFilesDir(null) +
                                "/" + txtNombreImagen.getText().
                                toString());

        imagen.setImageBitmap(bitmap1);
    }
    */



    /*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e("**************","Entreeeeeeeee3");
    }
    */


    /**
     * Funcion que se ejecuta cuando el fragment deja de estar visible
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //Log.e("**************", "Entreeeeeeeee2");
        } else {
            if (getView() != null) {
                try {
                    //Log.e("**************", "Entreeeeeeeee");
                    guardarOtrasIntervenciones(getView(), false);
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
