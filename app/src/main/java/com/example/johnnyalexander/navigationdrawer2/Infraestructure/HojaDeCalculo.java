package com.example.johnnyalexander.navigationdrawer2.Infraestructure;


import android.content.Context;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by Johnny Alexander on 17/10/2017.
 *
 * @Examples https://www.mkyong.com/java/jexcel-api-reading-and-writing-excel-file-in-java/
 */

public class HojaDeCalculo {

    /*Ruta del archivo del excel*/
    private String EXCEL_FILE_LOCATION;
    /*Archivo excel*/
    WritableWorkbook myFirstWbook;
    /*Pestaña en la cual se esta trabajando*/
    WritableSheet excelSheet;
    /*Contexto donde se gestiona el archivo*/
    Context context;

    public HojaDeCalculo(Context context) {
        this.context = context;
        validarFolder(context);
        myFirstWbook = null;
    }


    /**
     * Se encarga de validar si existe la carpeta hojas de calculo, si no existe la crea
     *
     * @param context Contexto que llama la funcion
     */
    public void validarFolder(Context context) {
         /*Nos paramos en la carpeta de Android/data*/
        File path = context.getExternalFilesDir(null);
        /*Dentro de android/data apuntamos en memoria a la ruta imagenes*/
        File file = new File(path + "/HojasDeCalculo/");
            /*Si el archivo apuntando en memoria no existe creamos las carpetas*/
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * Funcion que crea un archivo de excel en la ruta que se especifique.
     *
     * @param nombreArchivo Nobre del archivo de excel que se va a crear
     * @return returna true si puede crearlo
     * @throws IOException
     */
    public boolean crearArchivo(String nombreArchivo) throws IOException {
        EXCEL_FILE_LOCATION = context.getExternalFilesDir(null).toString() + "/HojasDeCalculo/" + nombreArchivo;
        myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
        return true;
    }


    /**
     * Cierra el archivo, almacenando todos los cambios sobre el.
     *
     * @return true si puede escribir todo sobre el archivo
     * @throws IOException
     */
    public boolean cerrarArchivo() throws IOException {
        myFirstWbook.write();

        if (myFirstWbook != null) {
            try {
                myFirstWbook.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        } else {
            return false;
        }

        return false;
    }

    /**
     * Agrega una pestaña a la hoja de excel.
     *
     * @param nombre nombre de la pestaña a crear
     * @param pos    posicion donde se creara la pestaña
     */
    public void agregarPestaña(String nombre, int pos) {
        excelSheet = myFirstWbook.createSheet(nombre, pos);
    }


    /**
     * Agrega un texto a una celda de excel
     *
     * @param columna columna donde se agregara
     * @param fila    fila donde se agregara
     * @param texto   texto añadir
     * @param formato estilos a aplicar en la celda
     * @throws WriteException
     */
    public void agregarTexto(int columna, int fila, String texto, WritableCellFormat formato) throws WriteException {

        Label label;

        if (formato == null) {
            label = new Label(columna, fila, texto);
        } else {
            label = new Label(columna, fila, texto, formato);
        }

        excelSheet.addCell(label);
    }

    /**
     * Agrega un texto a una celda de excel
     *
     * @param columna columna donde se agregara
     * @param fila    fila donde se agregara
     * @param valor   numero a añadir
     * @param formato estilos de la hoja de calculo
     * @throws WriteException
     */
    public void agregarNumero(int columna, int fila, int valor, WritableCellFormat formato) throws WriteException {

        Number number;

        if (formato == null) {
            number = new Number(columna, fila, valor);
        } else {
            number = new Number(columna, fila, valor, formato);
        }

        excelSheet.addCell(number);
    }


    /**
     * Define el estilo de las cabeceras del excel y las retorna
     *
     * @return el estilo listo para aplicar
     */
    public WritableCellFormat getEstiloCabecera() {
        WritableCellFormat cFormat = new WritableCellFormat();
        WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
        cFormat.setFont(font);
        return cFormat;
    }


    public boolean crearArchivoPrueba() {

        try {

            myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));

            // Se crea una pestaña llamada Sheet 1
            excelSheet = myFirstWbook.createSheet("Sheet 1", 0);

            /*Definimos un estilo para añadirlo a libertad en cada uno de los textos*/
            WritableCellFormat cFormat = new WritableCellFormat();
            WritableFont font = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
            cFormat.setFont(font);
            /**/

            // Añadimos un texto en el excel (Columna, fila, texto)
            Label label = new Label(0, 0, "Test Count");
            excelSheet.addCell(label);

            // Añadimos un numero en el excel (Columna, fila, numero)
            Number number = new Number(0, 1, 1);
            excelSheet.addCell(number);

            label = new Label(1, 0, "Result", cFormat);
            excelSheet.addCell(label);

            label = new Label(1, 1, "Passed");
            excelSheet.addCell(label);

            number = new Number(0, 2, 2);
            excelSheet.addCell(number);

            label = new Label(1, 2, "Passed 2");
            excelSheet.addCell(label);

            myFirstWbook.write();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            } else {
                return false;
            }


        }

        return false;
    }

}
