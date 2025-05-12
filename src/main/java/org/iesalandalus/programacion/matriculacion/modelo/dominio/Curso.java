package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Curso {
    PRIMERO("Primero"), SEGUNDO("Segundo");

    private final String cadenaAMostrar;

    private Curso(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        int digito;
        if (cadenaAMostrar == PRIMERO.cadenaAMostrar) {
            digito=1;
        } else if (cadenaAMostrar == SEGUNDO.cadenaAMostrar){
            digito=2;
        } else {
            digito=3;
        }
        return digito+".-"+cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
