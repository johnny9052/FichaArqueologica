package com.example.johnnyalexander.navigationdrawer2.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Lista_Fichas extends Fragment {

    Helper helper;
    CtlFichasArqueologicas ficha;

    private ListView lstFichas;

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

    public void cargarListadoFichas() {
        File[] files  = helper.ArchivosListarExistentes(getContext(),"");
        String contenido;

        for (int i = 0; i < files.length; i++)
        {
            try {
                contenido = helper.ArchivoTextoCargarContenido(files[i],getContext());
                ficha.fichas.add(helper.JSON_JSONToObjectFichaArqueologica(contenido));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        listarFichasListView();

    }

    public void listarFichasListView(){

        ArrayList<String> listaPublica = configurarListaPublica();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listaPublica.toArray(new String[listaPublica.size()]));

        lstFichas.setAdapter(adapter);

        lstFichas.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int posicion, long id) {

                    }
                });

    }


    public  ArrayList<String> configurarListaPublica(){
        ArrayList<String> listaPublica = new ArrayList<String>();
        for (int i = 0; i < ficha.fichas.size(); i++) {
            listaPublica.add("Corte: "+ficha.fichas.get(i).basica.getCorte() + " - Sitio: "+ficha.fichas.get(i).basica.getNumeroSitio());
        }
        return listaPublica;
        }

    }





