package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB("Grado D Ciclo Formativo de Grado Básico"), GDCFGM("Grado D Ciclo Formativo de Grado Medio"), GDCFGS("Grado D Ciclo Formativo de Grado Superior");

    private String cadenaAMostrar;

    Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        return cadenaAMostrar;
    }
}
