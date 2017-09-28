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
import com.example.johnnyalexander.navigationdrawer2.Model.ClsEstratigrafia;
import com.example.johnnyalexander.navigationdrawer2.Model.ClsMaterialRecuperado;
import com.example.johnnyalexander.navigationdrawer2.View.FragmentsDialog.Fragment_Dialog_Estratigrafia;
import com.example.johnnyalexander.navigationdrawer2.R;
import com.example.johnnyalexander.navigationdrawer2.View.FragmentsDialog.Fragment_Dialog_Material_Recuperado;

import java.util.ArrayList;


public class Tab_Material_Recuperado extends Fragment {


    /*Referencia objetos*/
    Helper helper;
    CtlFichasArqueologicas ficha;
    /*END referencia objetos*/


    /*Elementos GUI*/
    FloatingActionButton btnfNuevoMaterialRecuperado;
    private ListView lstTipoMaterialRecuperado;
     /*END Elementos GUI*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_material_recuperado, container, false);

        helper = new Helper();
        ficha = new CtlFichasArqueologicas();

        configuracionGUI(view);
        configuracionListeners();

        cargarListadoMaterialRecuperado();

        return view;
    }


    /**
     * Carga en el listview el listado de todas los materiales recuperados
     */
    public void cargarListadoMaterialRecuperado() {

        ArrayList<String> listaPublica = new ArrayList<String>();

        for (int i = 0; i < ficha.fichaTemporal.materialesRecuperados.size(); i++) {
            listaPublica.add("Nivel no: " + ficha.fichaTemporal.materialesRecuperados.get(i).getNivel());
        }

        if (listaPublica.size() == 0) {
            listaPublica.add("No se ha añadido material");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, listaPublica.toArray(new String[listaPublica.size()]));

        lstTipoMaterialRecuperado.setAdapter(adapter);

        /*Solo si existe material, se asignan los listeners. */
        if (ficha.fichaTemporal.materialesRecuperados.size() > 0) {
            lstTipoMaterialRecuperado.setOnItemClickListener
                    (new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int posicion, long id) {

                            ClsMaterialRecuperado temp = ficha.fichaTemporal.materialesRecuperados.get(posicion);
                            abrirDialog(v, temp, posicion);

                        }
                    });


            lstTipoMaterialRecuperado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int pos, long id) {

                    helper.mostrarMensaje("Largo!!!", getContext());

                    return true;
                }
            });
        }
    }


    /**
     * Se asocian los eventos a los elementos graficos
     */
    public void configuracionListeners() {
        btnfNuevoMaterialRecuperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialog(v, null, -1);
            }
        });
    }


    /**
     * Se asocian los componentes graficos para su uso
     *
     * @param view Vista donde se encuentra los componentes graficos
     */
    public void configuracionGUI(View view) {
        /*Referencias GUI*/
        btnfNuevoMaterialRecuperado = (FloatingActionButton) view.findViewById(R.id.btnfNuevoMaterialRecuperado);
        lstTipoMaterialRecuperado = (ListView) view.findViewById(R.id.lstTipoMaterialRecuperado);
        /*END referencias GUI*/

    }


    /**
     * Abre un dialog para crear o editar un material
     *
     * @param view Vista donde se abrira el dialog
     * @param temp Objeto materialRecuperado, por si se va a cargar informacion para su respectiva
     *             edicion, si no hay ninguno se manda null
     * @param pos  Posicion donde se sobrescribira el objeto para editarolo, si no se va a editar se
     *             manda -1
     */
    public void abrirDialog(View view, ClsMaterialRecuperado temp, int pos) {

        Fragment_Dialog_Material_Recuperado newFragment;

        if (temp == null) {
            newFragment = Fragment_Dialog_Material_Recuperado.newInstance();
        } else {
            newFragment = Fragment_Dialog_Material_Recuperado.newInstance(temp, pos);
        }

        /*Con este se le asigna un listener, cuando se cierra el dialog fragment se ejecuta este bloque
        * de codigo*/
        newFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                /*Refrescamos la lista*/
                cargarListadoMaterialRecuperado();
            }
        });

        newFragment.show(getFragmentManager(), "dialog");
    }

}
