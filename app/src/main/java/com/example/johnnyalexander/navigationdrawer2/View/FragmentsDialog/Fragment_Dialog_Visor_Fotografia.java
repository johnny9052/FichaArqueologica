package com.example.johnnyalexander.navigationdrawer2.View.FragmentsDialog;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.Model.ClsMaterialRecuperado;
import com.example.johnnyalexander.navigationdrawer2.R;

import java.io.File;
import java.io.IOException;


public class Fragment_Dialog_Visor_Fotografia extends DialogFragment {


    /*Elementos GUI*/
    Button btnCancelarMaterialRecuperado;
    ImageView imgViewFotografia;
    /*END Elementos GUI*/


    /*Referencia objetos*/
    Helper helper;
    /*END Referencia objetos*/



    /*FUNCIONES NECESARIA PARA MOSTRAR EL DIALOG*/

    /**
     * Instancia el dialogo
     *
     * @return Dialogo solicitado
     */
    public static Fragment_Dialog_Visor_Fotografia newInstance() {
        return new Fragment_Dialog_Visor_Fotografia();
    }


    public static Fragment_Dialog_Visor_Fotografia newInstance(String rutaFotografia) {

        Fragment_Dialog_Visor_Fotografia f = new Fragment_Dialog_Visor_Fotografia();

        Helper helperTemp = new Helper();
        Bundle args = new Bundle();
        args.putString("rutaFotografia", rutaFotografia);
        f.setArguments(args);

        return f;
    }

    /*END FUNCION NECESARIA PARA MOSTRAR EL DIALOG*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dialog_fotografia_visor, container, false);

        helper = new Helper();

        configuracionGUI(view);
        configuracionListeners();

        cargarDatos();

        return view;
    }


    /**
     * Se determina si existe un objeto para cargar los datos en los campos
     */
    public void cargarDatos() {
        Bundle args = getArguments();

        try {
            String rutaFotografia = args.getString("rutaFotografia", "");

            if (rutaFotografia != "" && rutaFotografia != null) {
                File file = new File(rutaFotografia);
                imgViewFotografia.setImageBitmap(
                        Bitmap.createScaledBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()), 480, 600, false));
            }

        } catch (Exception e) {

        } finally {

        }
    }

    /**
     * Se configuran los listeners de los objetos
     */
    public void configuracionListeners() {

        btnCancelarMaterialRecuperado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarMaterialRecuperado();
            }
        });

    }

    /**
     * Se asocian los elementos graficos y otras confuguraciones
     */
    public void configuracionGUI(View view) {
        btnCancelarMaterialRecuperado = (Button) view.findViewById(R.id.btnCancelarMaterialRecuperado);
        imgViewFotografia = (ImageView) view.findViewById(R.id.imgViewFotografia);
    }


    /**
     * Cierra el dialogo actual
     */
    public void cancelarMaterialRecuperado() {
        getDialog().dismiss();
    }


    /*CODIGO NECESARIO PARA QUE SE EJECUTE LA FUNCION EN EL FRAGMENT ANTERIOR CUANDO ESTE SE CIERRA*/
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

    /*END CODIGO NECESARIO PARA QUE SE EJECUTE LA FUNCION EN EL FRAGMENT ANTERIOR CUANDO ESTE SE CIERRA*/

}
