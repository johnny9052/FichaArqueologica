package com.example.johnnyalexander.navigationdrawer2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.johnnyalexander.navigationdrawer2.View.Fragments.Lista_Fichas;
import com.example.johnnyalexander.navigationdrawer2.View.Fragments.Ficha_Registro;
import com.example.johnnyalexander.navigationdrawer2.View.Fragments.Informacion_Personal;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    /*Referencias GUI*/
    FragmentManager fragmentManager;
    TextView txtNombreUsuarioNavigationDrawer, txtCorreoNavigationDrawer, txtProyectoNavigationDrawer;
    /*END Referencias GUI*/

    /*Referencia Persistencia*/
    SharedPreferences persistencia;
    /*END Referencia Persistencia*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Formulario que se carga por defecto*/
        fragmentManager = getSupportFragmentManager();

        cargarFormularioPorDefecto();
        cargarInformacionUsuario(navigationView);

    }

    /**
     * Carga la informacion del usuario en el menu de navegacion
     *
     * @param navigationView Menu de navegacion de donde se va a actualizar la informacion
     */
    public void cargarInformacionUsuario(NavigationView navigationView) {


        View headerView = navigationView.getHeaderView(0);

        txtNombreUsuarioNavigationDrawer = (TextView) headerView.findViewById(R.id.txtNombreUsuarioNavigationDrawer);
        txtCorreoNavigationDrawer = (TextView) headerView.findViewById(R.id.txtCorreoNavigationDrawer);
        txtProyectoNavigationDrawer = (TextView) headerView.findViewById(R.id.txtProyectoNavigationDrawer);

        persistencia = getApplicationContext().getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

        if (persistencia.getString("nombres", null) != null) {
            txtNombreUsuarioNavigationDrawer.setText(persistencia.getString("nombres", "") + " " +
                    persistencia.getString("apellidos", ""));
        } else {
            txtNombreUsuarioNavigationDrawer.setText("No existe usuario");
        }

        if (persistencia.getString("correoElectronico", null) != null) {
            txtCorreoNavigationDrawer.setText(persistencia.getString("correoElectronico", ""));
        } else {
            txtCorreoNavigationDrawer.setText("No existe correo");
        }

        if (persistencia.getString("nombreProyecto", null) != null) {
            txtProyectoNavigationDrawer.setText(persistencia.getString("nombreProyecto", null));
        } else {
            txtProyectoNavigationDrawer.setText("No existe proyecto");
        }


    }


    /**
     * Se carga el formulario - fragment por defecto, el cual si se solicita uno puntual se recibe
     * mediante un extra. Por defecto carga el listado de fichas
     */
    public void cargarFormularioPorDefecto() {

        Bundle bundle = getIntent().getExtras();

        String fragmentDefecto = "";

        try {

            if (bundle != null) {
                fragmentDefecto = bundle.getString("fragment");
            }

            persistencia = getSharedPreferences("ClsUsuario", Context.MODE_PRIVATE);

            String xxx = persistencia.getString("nombres", null);

            if (persistencia.getString("nombres", null) == null || persistencia.getString("nombres", "").equals("")) {
                fragmentDefecto = "informacionPersonal";
            }


            switch (fragmentDefecto) {
                case "registroFicha":
                    fragmentManager.beginTransaction().replace(R.id.masterLayout, new Ficha_Registro()).commit();
                    break;

                case "informacionPersonal":
                    fragmentManager.beginTransaction().replace(R.id.masterLayout, new Informacion_Personal()).commit();
                    break;

                default:
                    fragmentManager.beginTransaction().replace(R.id.masterLayout, new Lista_Fichas()).commit();
                    break;
            }
        } catch (Exception e) {
            fragmentManager.beginTransaction().replace(R.id.masterLayout, new Lista_Fichas()).commit();
        }
    }


    /**
     * Funcion que se ejecuta cuando se trata de cerrar la aplicacion presionando el boton de atras
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                //.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Salir de la aplicacion")
                .setMessage("¿Esta seguro que desea salir de la aplicacion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        if (drawer.isDrawerOpen(GravityCompat.START)) {
                            drawer.closeDrawer(GravityCompat.START);
                        } else {
                            //super.onBackPressed();
                        }

                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.formulario_1) {
            fragmentManager.beginTransaction().replace(R.id.masterLayout, new Ficha_Registro()).commit();
        } else if (id == R.id.formulario_2) {
            fragmentManager.beginTransaction().replace(R.id.masterLayout, new Lista_Fichas()).commit();
        } else if (id == R.id.formulario_3) {
            fragmentManager.beginTransaction().replace(R.id.masterLayout, new Informacion_Personal()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
