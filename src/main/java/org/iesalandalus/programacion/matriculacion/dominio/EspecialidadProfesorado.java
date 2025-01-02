package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA("1. Informática"), FOL("2. Formación y Orientación Laboral"), SISTEMAS("3. Sistemas y Aplicaciones Informáticas");

    private final String cadenaAMostrar;

    EspecialidadProfesorado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        return cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "EspecialidadProfesorado{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
