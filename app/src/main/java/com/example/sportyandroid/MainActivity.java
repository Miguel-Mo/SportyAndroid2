package com.example.sportyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Controlador miControlador;

    //La primera variable TypeFace la declaro para la fuente, lo demas (Textviev,edittext, etc.) lo declaro para poder aplicarle la fuente en el oncreate
    //en lo que refiere al spinner hay un problema que todavia no he podido solucionar, cuando pongo la fuente se bloque la app

    private Typeface Lemon;
    private TextView tvMinutos;
    private EditText etMinutos;
    private TextView tvKgs;
    private EditText etKgs;

    //private TextView spinner_text;

    private TextView resultado_string;
    private TextView resultado;
    private Button bCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner mySpinner=findViewById(R.id.cbEjercicios);

        //Le digo que acceda al singleton
        miControlador= Controlador.getSingleton(this,mySpinner);

        //aqui asigno valor y ruta a la fuente
        String fuente="Fonts/LEMONMILK-Regular.otf";
        this.Lemon=Typeface.createFromAsset(getAssets(),fuente);

        //aqui les digo a las ids que cojan esa fuente, primero las localizo y luego la adjudico, no se si hay un mecanismo mas eficiente
        tvMinutos=(TextView)findViewById(R.id.tvMinutos);
        tvMinutos.setTypeface(Lemon);
        etMinutos=(EditText) findViewById(R.id.etMinutos);
        etMinutos.setTypeface(Lemon);
        tvKgs=(TextView)findViewById(R.id.tvKgs);
        tvKgs.setTypeface(Lemon);
        etKgs=(EditText) findViewById(R.id.etKgs);
        etKgs.setTypeface(Lemon);

        //spinner_text=(TextView)findViewById(R.id.spinner_text);
        //spinner_text.setTypeface(Lemon);

        resultado_string=(TextView)findViewById(R.id.tvResultado_string);
        resultado_string.setTypeface(Lemon);
        resultado=(TextView)findViewById(R.id.tvResultado);
        resultado.setTypeface(Lemon);

        bCalcular=(Button)findViewById(R.id.bCalcular);
        bCalcular.setTypeface(Lemon);


        try{
            miControlador.iniciaDatos();
        }catch (IOException ex) {
           ex.printStackTrace();
           return;
        }
    }

    //metodo para que funcione el boton calcular, cojo los valores que me hacen falta y pongo el resultado
    public void onCalcularClick(View view){
        TextView tvResultado;
        Float minutos;
        Float kgs;
        String descr;
        String error="Se te ha olvidado insertar un dato";



        try {
            minutos=Float.valueOf(((EditText)findViewById(R.id.etMinutos)).getText().toString());
            kgs=Float.valueOf(((EditText)findViewById(R.id.etKgs)).getText().toString());
            descr=((Spinner)findViewById(R.id.cbEjercicios)).getSelectedItem().toString();

            tvResultado=findViewById(R.id.tvResultado);
            tvResultado.setText(miControlador.calculaKCal(minutos,kgs,descr)+" Kcal");

        }catch (Exception i){
            System.out.println(i);
            Toast mensaje =Toast.makeText(this,error,Toast.LENGTH_LONG);
            mensaje.show();
        }






    }
}
