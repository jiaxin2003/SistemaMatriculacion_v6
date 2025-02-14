package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public abstract class Grado {
    protected String nombre;
    protected String iniciales;
    protected int numAnio;

    public Grado(String nombre) {
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        if (nombre == null)
            throw new NullPointerException("ERROR: El nombre de un grado no puede ser nulo.");
        else if (nombre.isBlank())
            throw new IllegalArgumentException("ERROR: El nombre de un grado no puede estar vacío.");
        this.nombre = nombre;
        setIniciales();
    }


    private void setIniciales() {
        String[] palabras = nombre.split("[ ]+");
        String inicial = "";
        for (String palabra : palabras) {
            inicial += palabra.charAt(0);
        }
        this.iniciales = inicial.toUpperCase();
    }


    public abstract void setNumAnio(int numAnio);

    @Override
    public String toString() {
        return "(" + iniciales + ") - " + nombre;
    }
}
