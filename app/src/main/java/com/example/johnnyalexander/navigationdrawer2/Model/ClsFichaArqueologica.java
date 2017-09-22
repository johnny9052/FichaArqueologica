package com.example.johnnyalexander.navigationdrawer2.Model;

import java.util.ArrayList;

/**
 * Created by Johnny Alexander on 14/09/2017.
 */

public class ClsFichaArqueologica {

    public ClsInformacionBasica basica;

    public ArrayList<ClsEstratigrafia> estratigrafias;

    public ArrayList<ClsMaterialRecuperado> materialesRecuperados;

    public ClsOtraIntervencion otras;

    public ClsFichaArqueologica() {
        basica = new ClsInformacionBasica();
        estratigrafias = new ArrayList<ClsEstratigrafia>();
        materialesRecuperados = new ArrayList<ClsMaterialRecuperado>();
        otras = new ClsOtraIntervencion();
    }


}
