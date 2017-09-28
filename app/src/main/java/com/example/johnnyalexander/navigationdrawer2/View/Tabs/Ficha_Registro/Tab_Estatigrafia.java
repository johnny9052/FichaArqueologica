package com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.Model.ClsEstratigrafia;
import com.example.johnnyalexander.navigationdrawer2.View.FragmentsDialog.Fragment_Dialog_Estratigrafia;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Tab_Estatigrafia extends Fragment {

    Helper helper;
    CtlFichasArqueologicas ficha;

    /*Elementos GUI*/
    private ListView lstEstratigrafias;
    FloatingActionButton btnfNuevaEstratigrafia;
    /*END Elementos GUI*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_estatigrafia, container, false);

        helper = new Helper();
        ficha = new CtlFichasArqueologicas();


        configuracionGUI(view);
        configuracionListeners();

        cargarListadoEstratigrafias();

        return view;
    }


    public void cargarListadoEstratigrafias() {

        ArrayList<String> listaPublica = new ArrayList<String>();

        for (int i = 0; i < ficha.fichaTemporal.estratigrafias.size(); i++) {
            listaPublica.add("Horizonte no: " + ficha.fichaTemporal.estratigrafias.get(i).getNumeroHorizonte());
        }

        if (listaPublica.size() == 0) {
            listaPublica.add("No se han añadido estratigrafias");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listaPublica.toArray(new String[listaPublica.size()]));

        lstEstratigrafias.setAdapter(adapter);

        if (ficha.fichaTemporal.estratigrafias.size() > 0) {
            lstEstratigrafias.setOnItemClickListener
                    (new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int posicion, long id) {
                            ClsEstratigrafia temp = ficha.fichaTemporal.estratigrafias.get(posicion);
                            abrirDialog(v, temp, posicion);
                        }
                    });
        }
    }


    public void configuracionListeners() {
        btnfNuevaEstratigrafia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialog(v, null, -1);
            }
        });
    }


    public void configuracionGUI(View view) {
        btnfNuevaEstratigrafia = (FloatingActionButton) view.findViewById(R.id.btnfNuevaEstratigrafia);
        lstEstratigrafias = (ListView) view.findViewById(R.id.lstEstratigrafias);
    }


    public void abrirDialog(View view, ClsEstratigrafia temp, int pos) {


        Fragment_Dialog_Estratigrafia newFragment;

        if (temp == null) {
            newFragment = Fragment_Dialog_Estratigrafia.newInstance();
        } else {
            newFragment = Fragment_Dialog_Estratigrafia.newInstance(temp, pos);
        }
        //Fragment_Dialog_Estratigrafia newFragment = new Fragment_Dialog_Estratigrafia();

        /*Con este se le asigna un listener, cuando se cierra el dialog fragment se ejecuta este bloque
        * de codigo*/
        newFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                /*Refrescamos la lista*/
                cargarListadoEstratigrafias();
            }
        });

        newFragment.show(getFragmentManager(), "dialog");
    }


}
