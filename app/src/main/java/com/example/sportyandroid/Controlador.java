package com.example.sportyandroid;

import android.content.res.AssetManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Controlador {
    //Estado
    //En este estado no necesitamos declarar el textview o demas como antes simplemente el controlador,android nos lo facilita

    private static Controlador singleton;

    protected TiposEjercicios coleccionEj;
    private static final String NOMBRE_FICHERO = "data.txt";
    protected Spinner cbEjercicios;


    protected MainActivity myApp;

    //Comportamientos

    //Constructor
    private Controlador(MainActivity myApp, Spinner cbEjercicios) {
        coleccionEj = new TiposEjercicios();
        this.cbEjercicios = cbEjercicios;
        this.myApp=myApp;

    }


    //Get singleton
    public static Controlador getSingleton(MainActivity myApp, Spinner cbEjercicios){
        if (singleton == null) {
            singleton=new Controlador(myApp,cbEjercicios);
        }
        return singleton;
    }

    //inicializar datos
    public void iniciaDatos() throws FileNotFoundException {

        ArrayList<String> ejercicios=new ArrayList<>();

        //Cargamos datos del fichero
        cargarFichero();

        //Introducimos en el JComboBox los datos
        for (int i=0;i<coleccionEj.size();i++) {
            ejercicios.add(coleccionEj.getEjercicioByIndex(i).getDescripcion());
        }

        //aqui le asignamos el layout al spinner, en este caso el xml de spinner item y el de dopdownmenu
        ArrayAdapter<String> itemsAdapter=
                new ArrayAdapter<String>(this.myApp,R.layout.spinner_item, ejercicios);
        itemsAdapter.setDropDownViewResource(R.layout.dropdownmenu);
        cbEjercicios.setAdapter(itemsAdapter);

        itemsAdapter.notifyDataSetChanged();

    }


    //Método principal de cálculo
    public String calculaKCal(float minutos, float kgs, String descr) {
        return coleccionEj.getEjercicioByDescripcion(descr).calcularKCal(minutos, kgs);
    }


    //Carga de datos desde fichero de texto.
    private void cargarFichero() throws FileNotFoundException {

        AssetManager myAssets=myApp.getApplicationContext().getAssets();

        InputStream is;
        //File miFichero = new File(NOMBRE_FICHERO);
        FileWriter erroresFichero;

        try{
            is=myAssets.open(NOMBRE_FICHERO);
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        Scanner in = new Scanner(is);

        Ejercicio nuevoEjer;
        String siguienteLinea;
        ComprobadorEntradaFichero comprobador = new ComprobadorEntradaFichero();
        String errores = "";
        int numLinea = 1;

        while (in.hasNextLine()) {

            siguienteLinea = in.nextLine();
            if (comprobador.test(siguienteLinea)) {
                nuevoEjer = new Ejercicio(siguienteLinea);
                coleccionEj.addEjercicio(nuevoEjer);
            } else {
                //Controlar cuántos errores va dando
                errores += "Error en la línea: " + String.valueOf(numLinea) + ". Datos: " + siguienteLinea + "\n";
            }
            numLinea++;
        }

        in.close();

        //condicional para errores
        if (errores != "") {

            //en el caso de que haya algún error salta un toast
            Toast mensaje =Toast.makeText(myApp,errores,Toast.LENGTH_LONG);
            mensaje.show();

        }

    }



}