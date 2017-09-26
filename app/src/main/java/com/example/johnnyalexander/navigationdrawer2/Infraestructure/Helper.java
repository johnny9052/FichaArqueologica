package com.example.johnnyalexander.navigationdrawer2.Infraestructure;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnnyalexander.navigationdrawer2.Model.ClsFichaArqueologica;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Johnny Alexander on 12/09/2017.
 */

public class Helper extends Data {

    /*************************/
    /**ESPECIFICOS PROYECTO***/
    /*************************/


    public String nombreArchivo(ClsFichaArqueologica temp) {
        return temp.basica.getNumeroSitio() + "-" + temp.basica.getCorte();
    }


    /**************************/
    /*END ESPECIFICOS PROYECTO*/
    /**************************/


    /*************************/
    /****FUNCIONES DATOS******/
    /*************************/

    public String numeroValidarCargaObligatorio(int num) {
        if (num == -1) {
            return "";
        } else {
            return num + "";
        }
    }


    public String numeroValidarCargaObligatorio(double num) {
        if (num == -1) {
            return "";
        } else {
            return num + "";
        }
    }

    /*************************/
    /*END FUNCIONES DATOS*****/
    /*************************/


    /*************************/
    /******FUNCIONES GUI******/
    /*************************/


    public int spinnerObtenerPosicionValor(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }


    public boolean editTextValidarObligatorioMensaje(EditText editText) {

        if (editText.getText().toString().trim().equalsIgnoreCase("")) {
            editText.setError("Este campo es obligatorio");
            return false;
        } else {
            return true;
        }
    }


    public boolean spinnerValidarObligatorioMensaje(Spinner spinner) {
        if (spinner.getSelectedItemPosition() == 0) {
            ((TextView) spinner.getSelectedView()).setError("Este campo es obligatorio");
            return false;
        } else {
            return true;
        }
    }


    /**
     * Funcion que se encarga de llenar un Spinner con datos
     *
     * @param activity Actividad que ejecuta la funcion
     * @param datos    Array de String con los datos
     * @param spinner  Spinner que sera llenado
     */
    public void spinnerCargarDatos(Activity activity, String[] datos, Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, datos);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }


    /**
     * Asigna un calendario a una caja de texto
     *
     * @param txtTemp  EditText que sera convertido
     * @param activity Actividad que ejecuta la funcion
     */
    public void editTextToCalendar(final EditText txtTemp, final Activity activity) {
        txtTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        txtTemp.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                    }
                }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Seleccione su fecha");
                mDatePicker.show();
            }
        });
    }


    /**
     * Valida si un campo ha sido diligenciado
     *
     * @param editText EditText que sera validado
     * @return Retorna true si el campo ha sido diligenciado, false si no
     */
    public boolean editTextValidarObligatorio(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Muestra un Toast
     *
     * @param mensaje mensaje que se desea mostrar
     * @param context contexto donde es ejecutado
     */
    public void mostrarMensaje(String mensaje, Context context) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }


    /**
     * Muestra un mensaje emergente en la parte inferior de la pantalla
     *
     * @param mensaje Mensaje que se desea mostrar
     * @param view    view donde se ejecuta la funcion
     */
    public void mostrarMensajeInferiorPantalla(String mensaje, View view) {
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    /**
     * Retorna el valor seleccionado de un radioGroup
     *
     * @param radioGroup RadioGroup que contiene todos los radio
     * @return Valor seleccionado del radio, si no hay ninguno seleccionado retorna vacio
     */
    public String radioValorSeleccionado(RadioGroup radioGroup) {
        String selectedtext = "";
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        try {
            RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
            selectedtext = (String) radioButton.getText();
        } catch (Exception e) {
            return "";
        }
        return selectedtext;
    }


    public int radioGroupIndiceRadioSeleccionado(RadioGroup group, String value) {
        String text;
        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            View o = group.getChildAt(i);
            if (o instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) group.findViewById(o.getId());
                text = (String) radioButton.getText();
                if (text.equals(value)) {
                    return o.getId();
                }
            }
        }

        return -1;
    }


    public int editTextValidarNumeroEntero(EditText editText) {
        return editTextValidarObligatorio(editText) ? Integer.parseInt(editText.getText().toString()) : -1;
    }

    public double editTextValidarNumeroReal(EditText editText) {
        return editTextValidarObligatorio(editText) ? Double.parseDouble(editText.getText().toString()) : -1;
    }


    /*************************/
    /***END FUNCIONES GUI*****/
    /*************************/


    /*************************/
    /***** FUNCIONES FILE*****/
    /*************************/


    /**
     * Lista todos los archivos existentes en una ruta contenida en la carpeta Android/data
     *
     * @param context Contexto donde se ejecuta la funcion
     * @param folder  Ruta interna de ser necesaria (Navegacion entre carpetas)
     * @return Retorna un array de archivos con todos los encontrados
     */
    public File[] ArchivosListarExistentes(Context context, String folder) {
        String path = context.getExternalFilesDir(null).toString() + "/" + folder;
        //Log.d("Files********", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        //Log.d("Files", "Size: " + files.length);
        //for (int i = 0; i < files.length; i++) {
        //Log.d("Files", "FileName:" + files[i].getName());
        //}

        return files;
    }


    /**
     * Crea un archivo de texto en la carpeta Android/data
     *
     * @param contenido     Texto que sera almacenado en el archivo
     * @param nombreArchivo Nombre del archivo
     * @param context       Contexto que ejecuta la funcion
     * @param formato       Formato del archivo
     * @return True si crea el archivo, false si no pudo
     * @throws IOException
     */
    public boolean ArchivoTextoCrear(String contenido, String nombreArchivo, Context context, String formato) throws IOException {

        File path = context.getExternalFilesDir(null);
        File file = new File(path, nombreArchivo + "." + formato);

        FileOutputStream stream = new FileOutputStream(file);

        try {
            stream.write((contenido).getBytes());
            //Log.v("************", contenido);
            //Log.v("************", nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            stream.close();
        }

        return true;
    }


    /**
     * Retorna el contenido de un archivo de texto almacenado en la carpeta Android/data
     *
     * @param nombreArchivo Nombre del archivo que sera cargado
     * @param formato       Formato del archivo que sera cargado
     * @param context       Contexto que ejecuta la funcion
     * @return Texto que contiene el archivo
     * @throws IOException
     */
    public String archivoTextoCargarContenidoPorNombre(String nombreArchivo, String formato, Context context) throws IOException {

        File path = context.getExternalFilesDir(null);
        File file = new File(path, nombreArchivo + "." + formato);

        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = new FileInputStream(file);

        try {
            in.read(bytes);
        } finally {
            in.close();
        }

        String contents = new String(bytes);
        return contents;
    }


    /**
     * Retorna el contenido de un archivo de texto enviado por parametro
     *
     * @param file    Archivo que sera leido
     * @param context Contexto que ejecuta la funcion
     * @return Texto contenido en el archivo
     * @throws IOException
     */
    public String archivoTextoCargarContenido(File file, Context context) throws IOException {
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        FileInputStream in = new FileInputStream(file);

        try {
            in.read(bytes);
        } finally {
            in.close();
        }

        String contents = new String(bytes);
        return contents;
    }

    /*************************/
    /**END FUNCIONES FILE*****/
    /*************************/


    /*************************/
    /***** FUNCIONES JSON*****/
    /*************************/


    /**
     * @param object Objeto que sera convertido a JSON
     * @return String con el JSON del objeto
     */
    public String JSON_ObjetoToJSON(Object object) {
        //LAS 2 PRIMERAS LINEAS SON NECESARIAS PARA CONVERTIR OBJETOS STATICOS
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        //Gson gson = new Gson();
        String json = gson.toJson(object);

        return json;
    }


    /**
     * Convierte un JSON String a una ficha arqueologica
     *
     * @param json JSON String que representa una ficha arqueologica
     * @return Objeto ficha arqueologica
     */
    public ClsFichaArqueologica JSON_JSONToObjectFichaArqueologica(String json) {
        //LAS 2 PRIMERAS LINEAS SON NECESARIAS PARA CONVERTIR OBJETOS STATICOS
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = gsonBuilder.create();
        //Gson gson = new Gson();
        return gson.fromJson(json, ClsFichaArqueologica.class);
    }


    /*************************/
    /**END FUNCIONES JSON*****/
    /*************************/


    /*************************/
    /***** FUNCIONES GPS******/
    /*************************/

    /**
     * Calcula las coordenadas del dispositivo
     *
     * @param activity Actividad que ejecuta la funcion
     * @param context  Contexto desde donde se ejecuta la funcion
     * @return Objeto Location que contiene la posicion del dispositivo
     */
    public Location gpsCalcularCoordenadas(Activity activity, Context context) {

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            mostrarMensaje("No ha otorgado permisos para esto", activity);

        } else {

            int status = context.getPackageManager().checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION,
                    context.getPackageName());
            if (status == PackageManager.PERMISSION_GRANTED) {
                LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                List<String> providers = mgr.getAllProviders();
                if (providers != null && providers.contains(LocationManager.NETWORK_PROVIDER)) {
                    Location loc = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (loc != null) {
                        return loc;
                    }
                }
            }
        }
        return null;
    }

    /*************************/
    /***END FUNCIONES GPS*****/
    /*************************/


}
