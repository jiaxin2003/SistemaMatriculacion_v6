package org.iesalandalus.programacion.matriculacion.dominio;

public enum Grado {
    GDCFGB("1. Grado D Ciclo Formativo de Grado Básico"), GDCFGM("2. Grado D Ciclo Formativo de Grado Medio"), GDCFGS("3. Grado D Ciclo Formativo de Grado Superior");

    private final String cadenaAMostrar;

    Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }


    public String imprimir() {
        return cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Grado{" +
                "cadenaAMostrar='" + cadenaAMostrar + '\'' +
                '}';
    }
}
