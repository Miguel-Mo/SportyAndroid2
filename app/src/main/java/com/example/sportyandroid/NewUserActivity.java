package com.example.sportyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;

import com.example.sportyandroid.Controlador;
import com.example.sportyandroid.R;


public class NewUserActivity extends AppCompatActivity {

    protected Controlador miControlador;
    private EditText nombre;
    private EditText apellidos;
    private EditText dni;
    private EditText email;
    private EditText usuario;
    private EditText contrasena;

    private CharSequence fallo = "¡Error, este usuario ya existe!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        final Context context = this;

        nombre = (EditText) findViewById(R.id.etNombreR);
        apellidos = (EditText) findViewById(R.id.etApellidosR);
        dni = (EditText) findViewById(R.id.etDniR);
        email = (EditText) findViewById(R.id.etEmailR);
        usuario = (EditText) findViewById(R.id.etUsuarioR);
        contrasena = (EditText) findViewById(R.id.etPassR);

        miControlador = Controlador.getSingleton();

    }
    public void onRegistrarse (View view){
        String Snombre = nombre.getText().toString();
        String Sapellidos = apellidos.getText().toString();
        String Semail = email.getText().toString();
        String Susuario = usuario.getText().toString();
        String Scontrasena = contrasena.getText().toString();
        String Sdni = dni.getText().toString();
        TextView comprobacion = (TextView) findViewById(R.id.tvComprobacion);
        if (Snombre.isEmpty() || Sapellidos.isEmpty() || Semail.isEmpty() || Susuario.isEmpty() || Scontrasena.isEmpty() || Sdni.isEmpty()) {
            comprobacion.setText("No has introducido todos los datos");
        }else{
            if (miControlador.hacerRegistro(Susuario,Scontrasena,Snombre,Sapellidos,Sdni,Semail)){
                comprobacion.setText("El usuario ha sido creado correctamente");
            }else {
                comprobacion.setText("Error, este usuario ya existe");
            }

        }

    }
}
