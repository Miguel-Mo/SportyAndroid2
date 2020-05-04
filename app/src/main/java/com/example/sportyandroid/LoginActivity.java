package com.example.sportyandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    protected Controlador miControlador;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login);

      miControlador=Controlador.getSingleton();

  }


  public void onLoginClickac(View view){
      TextView tvError;
      tvError=findViewById(R.id.tvErrorLog);
      EditText usuario,password;
      usuario=findViewById(R.id.etNombre);
      password=findViewById(R.id.etPass);



      if (miControlador.TodoOk(usuario.getText().toString(),password.getText().toString())){
          finish();
      }else{
         //mensaje de error
          tvError.setText("se ha producido un error en el logeo" );
      }
  }


    public void  onRegistrarOnClick(View view){
        Intent myintent = new Intent(LoginActivity.this, NewUserActivity.class);
        startActivity(myintent);
    }



}

