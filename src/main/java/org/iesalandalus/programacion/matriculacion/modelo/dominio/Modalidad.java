package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Modalidad {
    PRESENCIAL(" Presencial"),SEMIPRESENCIAL(" Semipresencial");

    private final String cadenaAMostrar;

    private Modalidad(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        int digito;
        if (cadenaAMostrar == PRESENCIAL.cadenaAMostrar) {
            digito=1;
        } else if (cadenaAMostrar == SEMIPRESENCIAL.cadenaAMostrar){
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
