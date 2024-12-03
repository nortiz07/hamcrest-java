package org.example;

public class Persona {

    private String nombre;
    private String apellido;
    private Integer edad;
    private String ciudad;
    private boolean habilitado;

    public Persona(String nombre, String apellido, Integer edad, String ciudad, boolean habilitado ){
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.habilitado = habilitado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public boolean isHabilitado() {
        return habilitado;
    }
}
