package com.example.johnnyalexander.navigationdrawer2.View.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnnyalexander.navigationdrawer2.Controller.CtlFichasArqueologicas;
import com.example.johnnyalexander.navigationdrawer2.Infraestructure.Helper;
import com.example.johnnyalexander.navigationdrawer2.Model.ClsFichaArqueologica;
import com.example.johnnyalexander.navigationdrawer2.R;
import com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro.Tab_Informacion_Basica;
import com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro.Tab_Estatigrafia;
import com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro.Tab_Material_Recuperado;
import com.example.johnnyalexander.navigationdrawer2.View.Tabs.Ficha_Registro.Tab_Otras_Intervenciones;


public class Ficha_Registro extends Fragment {

    /*Donde iran cada una de las pestañas*/
    private AppBarLayout appBar;
    /*Para las pestañas o taps*/
    private TabLayout tabs;
    /*Para mostrar el fragment respectivo*/
    private ViewPager viewPager;


    Helper helper;
    CtlFichasArqueologicas ficha;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ficha_registro, container, false);

        /*Agregar pestañas*/
        View contenedor = (View)container.getParent();
        appBar = (AppBarLayout) contenedor.findViewById(R.id.appbar);
        tabs = new TabLayout(getActivity());
        /*End agregar pestañas*/
        tabs.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        appBar.addView(tabs);

        /*Asignamos el contenido (fragments y pestañas) a un adapter*/
        viewPager= (ViewPager)view.findViewById(R.id.masterPager);
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        /*Configuramos que los fragments seran cargados en el viewpager del layout*/
        tabs.setupWithViewPager(viewPager);


        /*Indicamos que es un nuevo archivo el que se va a trabajar*/
        helper = new Helper();
        ficha = new CtlFichasArqueologicas();
        ficha.status = false;
        ficha.fichaTemporal = null;
        ficha.fichaTemporal = new ClsFichaArqueologica();

        return view;
    }


    /*Destruir el fragmento cuando se selecciona otra pestaña, con el fin de que no se sobreponga
    * una con la otra*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabs);
    }


    /*Gestion de las pestañas*/

    public class ViewPagerAdapter extends FragmentStatePagerAdapter{

        /*Titulos de las pestañas*/
        String[] tituloTabs = {"Informacion basica","Estratigrafia","Material recuperado","Otras intervenciones"};

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /*Retorna el fragmento segun la pestaña seleccionada*/
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new Tab_Informacion_Basica();
                case 1: return new Tab_Estatigrafia();
                case 2: return new Tab_Material_Recuperado();
                case 3: return new Tab_Otras_Intervenciones();
            }
            return null;
        }

        /*Cantidad de pestañas*/
        @Override
        public int getCount() {
            return 4;
        }

        /*Retorna los titulos de las pestañas*/
        @Override
        public CharSequence getPageTitle(int position) {
            return tituloTabs[position];
        }
    }



}
