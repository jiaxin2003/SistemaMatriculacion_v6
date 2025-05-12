package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA("Informatica"),
    FOL("Fol"),
    SISTEMAS("Sistemas");

    private final String cadenaAMostrar;

    EspecialidadProfesorado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        int digito;
        if (cadenaAMostrar == INFORMATICA.cadenaAMostrar) {
            digito=1;
        } else if (cadenaAMostrar == FOL.cadenaAMostrar){
            digito=2;
        } else if (cadenaAMostrar == SISTEMAS.cadenaAMostrar){
            digito=3;
        }else {
            digito=4;
        }
        return digito+".-"+cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }

}

