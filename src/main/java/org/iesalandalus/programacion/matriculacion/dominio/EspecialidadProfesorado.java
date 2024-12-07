package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA("Informática"), FOL("Formación y Orientación Laboral"), SISTEMAS("Sistemas y Aplicaciones Informáticas");

    private String cadenaAMostrar;

    EspecialidadProfesorado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "EspecialidadProfesorado{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
