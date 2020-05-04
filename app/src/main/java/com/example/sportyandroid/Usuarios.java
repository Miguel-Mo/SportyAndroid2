package com.example.sportyandroid;

public class Usuarios {

    //ESTADO

    protected String usuario;
    protected String password;

    private String nombre;
    private String apellidos;
    private String dni;
    private String email;


    //Comportamientos

    //Constructor
    public Usuarios(String serializado) {
        String[] trozos;
        trozos = serializado.split(";");
        this.usuario = trozos[0];
        this.password = trozos[1];
        this.nombre=trozos[2];
        this.apellidos=trozos[3];
        this.dni=trozos[4];
        this.email=trozos[5];
    }

    public Usuarios(String usuario, String password, String nombre, String apellidos, String dni, String email) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
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

    public String serializar() {
        return usuario+";"+password+";"+nombre+";"+apellidos+";"+dni+";"+email;
    }


    public  String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }


}


