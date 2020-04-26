package com.example.sportyandroid;

public class Usuarios {

    //ESTADO

    protected String usuario;
    protected String password;


    //Comportamientos

    //Constructor
    public Usuarios(String serializado) {
        String[] trozos;
        trozos = serializado.split(";");
        usuario = trozos[0];
        password = trozos[1];
    }


    //Comprueba registro
    public Boolean CompruebaUsuario(String user) {
        if(usuario.equalsIgnoreCase(user)){
            return true;
        }else {
            return false;
        }

    }

    public Boolean CompruebaPasword(String pass){
        if( password.equals(pass)){
            return true;
        }else {
            return false;
        }
    }


    public  String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }
}


