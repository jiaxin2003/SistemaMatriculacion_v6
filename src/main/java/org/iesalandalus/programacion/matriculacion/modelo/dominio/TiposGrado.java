package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum TiposGrado {
    GRADOD("Grado D"), GRADOE("Grado E");

    private final String cadenaAMostrar;

    private TiposGrado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        int digito;
        if (cadenaAMostrar == GRADOD.cadenaAMostrar) {
            digito=1;
        } else if (cadenaAMostrar == GRADOE.cadenaAMostrar){
            digito=2;
        } else {
            digito=3;
        }
        return digito+".-"+cadenaAMostrar;
    }

    @Override
    public String toString() {
        return this.ordinal() + ". -" +
                cadenaAMostrar;
    }
}
