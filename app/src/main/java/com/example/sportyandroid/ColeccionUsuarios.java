package com.example.sportyandroid;

import java.util.ArrayList;

public class ColeccionUsuarios {

        //ESTADO

        //Lista de ejercicios disponibles
        protected ArrayList<Usuarios> listaUsuarios;



        //Comportamiento


        //Constructor
        public ColeccionUsuarios() {
            listaUsuarios = new ArrayList();
        }

        //Añadir un ejercicio
        public void addUsuario(Usuarios nuevoU) {
            listaUsuarios.add(nuevoU);
        }

        //¿Cuántos hay?
        public int size() {
            return listaUsuarios.size();
        }

        //Dar el usuario de la posición i
        public Usuarios getUsuarioByIndex(int index) {
            return listaUsuarios.get(index);
        }

        public Usuarios getUsuarioByUser(String user) {
            Usuarios usuario = null;

            int i = 0;

            while (usuario==null && i<listaUsuarios.size()) {
                if (listaUsuarios.get(i).getUsuario() == user) {
                    usuario = listaUsuarios.get(i);
                } else
                    i++;
            }
            return usuario;
        }

    public boolean comprobarRegistro(String usuario) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getUsuario().compareTo(usuario)==0) {
                return true;
            }
        }
        return false;

    }

}

