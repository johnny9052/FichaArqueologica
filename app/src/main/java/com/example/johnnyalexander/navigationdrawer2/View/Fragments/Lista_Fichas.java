package com.example.johnnyalexander.navigationdrawer2.View.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.MainActivity;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Lista_Fichas extends Fragment {

    Helper helper;
    CtlFichasArqueologicas ficha;

    private ListView lstFichas;

    private String[] listadoNombreArchivos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ficha_listado, container, false);
        lstFichas = (ListView) view.findViewById(R.id.lstFichas);
        helper = new Helper();
        ficha = new CtlFichasArqueologicas();
        cargarListadoFichas();
        return view;
    }

    /**
     * Funcion que obtiene todos los archivos almacenados y que seran listados
     */
    public void cargarListadoFichas() {

        /*Limpiamos si existen fichas previas*/
        ficha.fichas.clear();

        File[] files = helper.ArchivosListarExistentes(getContext(), "");
        String contenido;

        for (int i = 0; i < files.length; i++) {
            try {
                contenido = helper.archivoTextoCargarContenido(files[i], getContext());
                ficha.fichas.add(helper.JSON_JSONToObjectFichaArqueologica(contenido));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        listarFichasListView();
    }


    public void limpiarListView(ListView listview) {
        ArrayList<String> values = new ArrayList<String>();
        values.clear();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values.toArray(new String[values.size()]));
        listview.setAdapter(adapter);
    }


    /**
     * Obtiene el texto que se mostrara en el listview, se lo asigna a este y define el listener para
     * dicho listview
     */
    public void listarFichasListView() {


        final ArrayList<String> listaPublica = configurarListaPublica();

        if (listaPublica.size() == 0) {
            listaPublica.add("No se han añadido fichas");
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listaPublica.toArray(new String[listaPublica.size()]));

        lstFichas.setAdapter(adapter);

        if (ficha.fichas.size() > 0) {

            lstFichas.setOnItemClickListener
                    (new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int posicion, long id) {

                            String archivo = listadoNombreArchivos[posicion];
                            String fichaTemp = "";

                            try {
                            /*Se obtiene toda la informacion de la ficha seleccionada, y se almacena
                            * en la variable statica del controlador*/
                                fichaTemp = helper.archivoTextoCargarContenidoPorNombre(archivo, "json", getContext());
                                ficha.fichaTemporal = helper.JSON_JSONToObjectFichaArqueologica(fichaTemp);

                            /*Se envian 2 extras, el primero indica que se debe cargar el fragment
                            * de ficha de registro, debido a que se va a editar. El segundo indica
                            * que se va a editar sobre el formulario, no a crear un nuevo registro*/
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("fragment", "registroFicha");
                                intent.putExtra("edicion", "true");
                                startActivity(intent);
                            /*Se cierra la activity actual*/
                                getActivity().finish();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    });


            lstFichas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {

                    //helper.mostrarMensaje("Largo!!!", getContext());
                    final String archivo = listadoNombreArchivos[pos];
                    String fichaTemp = "";

                    new AlertDialog.Builder(getActivity())
                            //.setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Eliminar ficha")
                            .setMessage("¿Esta seguro que desea eliminar la ficha " + listaPublica.get(pos) +"?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    try {

                                        /*Se elimina la ficha*/
                                        if (helper.archivoTextoEliminarPorNombre(archivo, "json", getContext())) {
                                            helper.mostrarMensajeInferiorPantalla("Ficha eliminada", getView());
                                            cargarListadoFichas();
                                        } else {
                                            helper.mostrarMensajeInferiorPantalla("La ficha no se pudo eliminar", getView());
                                        }


                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            })
                            .setNegativeButton("No", null)
                            .show();


                    return true;
                }
            });

        }


    }


    /**
     * Recorre cada uno de los archivos encontrados, y saca la informacion necesaria que sera
     * mostrada en el listview para identificar cada registro. Ademas en un array global almacena
     * el nombre de cada archivo, para poder cargar posteriormente el archivo seleccionado en el
     * listview
     *
     * @return lista de String con la informacion a mostrar en el listview
     */
    public ArrayList<String> configurarListaPublica() {
        ArrayList<String> listaPublica = new ArrayList<String>();
        ArrayList<String> listadoNombreArchivosTemporal = new ArrayList<String>();
        for (int i = 0; i < ficha.fichas.size(); i++) {
            listaPublica.add("Corte: " + ficha.fichas.get(i).basica.getCorte() + " - Sitio: " + ficha.fichas.get(i).basica.getNumeroSitio());
            listadoNombreArchivosTemporal.add(helper.nombreArchivo(ficha.fichas.get(i)));
        }

        /*Se obtiene el listado de los nombres de los archivos en un array independiente*/
        listadoNombreArchivos = listadoNombreArchivosTemporal.toArray(new String[listaPublica.size()]);
        return listaPublica;
    }

}





