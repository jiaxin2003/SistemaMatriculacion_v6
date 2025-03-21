package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoD extends Grado {
    private Modalidad modalidad;

    public GradoD(String nombre, int numAnio, Modalidad modalidad) {
        super(nombre);
        setNumAnio(numAnio);
        setModalidad(modalidad);
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        if (modalidad == null)
            throw new NullPointerException("ERROR: La modalidad no puede ser nula.");
        this.modalidad = modalidad;
    }

    @Override
    public void setNumAnio(int numAnio) {
        if (numAnio < 2 || numAnio > 3) {
            throw new IllegalArgumentException("ERROR: El grado D debe tener un año comprendido entre 2 y 3.");
        }
        this.numAnio = numAnio;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + "Numero de anios: " + numAnio + " - " + modalidad;
    }
}
