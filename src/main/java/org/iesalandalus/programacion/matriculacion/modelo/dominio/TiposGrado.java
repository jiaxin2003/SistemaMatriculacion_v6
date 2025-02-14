package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum TiposGrado {
    GRADOD("Grado D"), GRADOE("Grado E");

    private final String cadenaAMostrar;

    private TiposGrado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        return cadenaAMostrar;
    }

    @Override
    public String toString() {
        return this.ordinal() + ". -" +
                cadenaAMostrar;
    }
}
