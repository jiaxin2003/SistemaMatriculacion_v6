package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoE extends Grado {
    private int numEdiciones;

    public GradoE(String nombre, int numAnio, int numEdiciones) {
        super(nombre);
        setNumAnio(numAnio);
        setNumEdiciones(numEdiciones);
    }

    public int getNumEdiciones() {
        return numEdiciones;
    }

    public void setNumEdiciones(int numEdiciones) {
        if (numEdiciones <= 0) {
            throw new IllegalArgumentException("ERROR: El numero de ediciones debe ser mayor que 0.");
        }
        this.numEdiciones = numEdiciones;
    }

    public void setNumAnio(int numAnio) {
        if (numAnio != 1) {
            throw new IllegalArgumentException("ERROR: El numero de anio debe ser 1.");
        }
        this.numAnio = numAnio;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + numEdiciones;
    }
}
