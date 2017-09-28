package com.example.johnnyalexander.navigationdrawer2.Infraestructure;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by Johnny Alexander on 28/09/2017.
 */

public class Permisos {

    /*Orden aleatorio, de como van a aparecer los permisos en el dispositivo*/
    /*El 0x1....0x2....0x3 genera numeros aleatorios*/
    static final Integer MESSAGE = 0x1;
    public static final Integer LOCATION = 0x2;
    static final Integer CALL = 0x3;
    static final Integer WRITE_EXST = 0x4;
    static final Integer READ_EXST = 0x5;
    public static final Integer CAMERA = 0x6;
    static final Integer ACCOUNTS = 0x7;


    public boolean verificarPermiso(String permission, Integer requestCode, Activity activity) {

        /*El permiso que quiero activar ya se encuentra activado?*/
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            /*Se muestra un mensaje en pantalla solicitando el permiso*/
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                /*Si le da permitir se activa el permiso*/
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                return true;
            } else {
                /*Si le da rechazar igual se manda pero de manera negativa*/
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                return false;
            }
        } else {
            return true;
        }
    }
}
