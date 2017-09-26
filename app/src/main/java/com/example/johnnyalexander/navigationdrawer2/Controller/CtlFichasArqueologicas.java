package com.example.johnnyalexander.navigationdrawer2.Controller;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.johnnyalexander.navigationdrawer2.Model.ClsFichaArqueologica;
import com.example.johnnyalexander.navigationdrawer2.Model.ClsInformacionBasica;
import com.example.johnnyalexander.navigationdrawer2.View.Fragments.Ficha_Registro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny Alexander on 14/09/2017.
 */

public class CtlFichasArqueologicas {

    /*Variable que indica si la informacion basica ya ha sido registrada*/
    public static boolean infoBasicaRegistrada = false;

    /*Esto lo voy a utilizar cuando necesite listarlas*/
    public ArrayList<ClsFichaArqueologica> fichas = new ArrayList<ClsFichaArqueologica>();

    /*Ficha para controlar la ficha del proceso de registro o manipulacion*/
    static public ClsFichaArqueologica fichaTemporal = new ClsFichaArqueologica();

    public void resetControlador() {
        infoBasicaRegistrada = false;
        fichaTemporal = null;
        fichaTemporal = new ClsFichaArqueologica();
    }

}
