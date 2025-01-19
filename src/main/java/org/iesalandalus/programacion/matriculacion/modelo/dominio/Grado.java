package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Grado {
    GDCFGB(" Grado D Ciclo Formativo de Grado Basico"),
    GDCFGM(" Grado D Ciclo Formativo de Grado Medio"),
    GDCFGS(" Grado D Ciclo Formativo de Grado Superior");

    private final String cadenaAMostrar;

    Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }


    public String imprimir() {
        return cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
