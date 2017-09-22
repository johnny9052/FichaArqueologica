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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.View.FragmentsDialog.Fragment_Dialog_Estratigrafia;
import com.example.johnnyalexander.navigationdrawer2.R;
import com.example.johnnyalexander.navigationdrawer2.View.FragmentsDialog.Fragment_Dialog_Material_Recuperado;

import java.util.ArrayList;


public class Tab_Material_Recuperado extends Fragment {

    Helper helper;
    CtlFichasArqueologicas ficha;
    private ListView lstTipoMaterialRecuperado;


    /*Elementos GUI*/
    FloatingActionButton btnfNuevoMaterialRecuperado;
     /*END Elementos GUI*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_material_recuperado, container, false);

        helper = new Helper();
        ficha = new CtlFichasArqueologicas();

        /*Referencias GUI*/
        btnfNuevoMaterialRecuperado = (FloatingActionButton) view.findViewById(R.id.btnfNuevoMaterialRecuperado);
        lstTipoMaterialRecuperado = (ListView) view.findViewById(R.id.lstTipoMaterialRecuperado);

        configuracionListeners();
        configuracionGUI();

        cargarListadoMaterialRecuperado();

        return view;
    }


    public void cargarListadoMaterialRecuperado() {

        ArrayList<String> listaPublica = new ArrayList<String>();

        for (int i = 0; i < ficha.fichaTemporal.materialesRecuperados.size(); i++) {
            listaPublica.add("Nivel no: " + ficha.fichaTemporal.materialesRecuperados.get(i).getNivel());
        }

        if (listaPublica.size() == 0) {
            listaPublica.add("No se han añadido registros");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listaPublica.toArray(new String[listaPublica.size()]));

        lstTipoMaterialRecuperado.setAdapter(adapter);

        lstTipoMaterialRecuperado.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v,
                                            int posicion, long id) {

                    }
                });
    }



    public void configuracionListeners() {
        btnfNuevoMaterialRecuperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialog(v);
            }
        });
    }

    public void configuracionGUI() {

    }



    public void abrirDialog(View view) {
        // Create the fragment and show it as a dialog.
        Fragment_Dialog_Material_Recuperado newFragment = Fragment_Dialog_Material_Recuperado.newInstance();
        //Fragment_Dialog_Estratigrafia newFragment = new Fragment_Dialog_Estratigrafia();

           /*Con este se le asigna un listener, cuando se cierra el dialog fragment se ejecuta este bloque
        * de codigo*/
        newFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                cargarListadoMaterialRecuperado();
            }
        });


        newFragment.show(getFragmentManager(), "dialog");
    }

}
