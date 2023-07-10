package com.helcode.syncsqlite_mysql.model;

public class Contactos {
String Nombre,Apellido,Telefono;

    public Contactos(String nombre, String apellido, String telefono) {
        Nombre = nombre;
        Apellido = apellido;
        Telefono = telefono;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
