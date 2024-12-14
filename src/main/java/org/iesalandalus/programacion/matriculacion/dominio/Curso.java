package org.iesalandalus.programacion.matriculacion.dominio;

public enum Curso {
    PRIMERO("Primero"), SEGUNDO("Segundo");

    private final String cadenaAMostrar;
    private Curso(String cadenaAMostrar)
    {
        this.cadenaAMostrar=cadenaAMostrar;
    }
    public String imprimir()
    {
        return cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
