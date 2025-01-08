package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Asignaturas {
    private int capacidad;
    private int tamano;
    private Asignatura[] coleccionAsignaturas;

    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignatura[capacidad];
    }

    public Asignatura[] get() {
        return copiaProfundaAsignaturas(coleccionAsignaturas);
    }

    private Asignatura[] copiaProfundaAsignaturas(Asignatura[] coleccionAsignaturas) {
        Asignatura[] copiaAsignaturas = new Asignatura[capacidad];
        for (int i = 0; !tamanoSuperado(i); i++) {
            copiaAsignaturas[i] = new Asignatura(this.coleccionAsignaturas[i]);
        }
        return copiaAsignaturas;
    }

    private boolean tamanoSuperado(int i) {
        return i >= tamano;
    }
    private boolean capacidadSuperada(int i) {
        return i >= capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new IllegalArgumentException("ERROR: La asignatura no puede ser nula.");
        }
        if (tamano >= capacidad) {
            throw new IllegalStateException("ERROR: No se pueden añadir más asignaturas, la capacidad está completa.");
        }
        if (buscarIndice(asignatura) != -1) {
            throw new OperationNotSupportedException("ERROR: La asignatura ya existe en la lista.");
        }
        coleccionAsignaturas[tamano] = new Asignatura(asignatura);
        tamano++;
    }

    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].getCodigo() == asignatura.getCodigo()) {
                return i;
            }
        }
        return -1;
    }

    public void borrar(Asignatura asignatura)  {
        Objects.requireNonNull(asignatura, "ERROR: No se puede borrar una asignatura nula.");
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: La asignatura no existe en la lista.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[tamano - 1] = null;
        tamano--;
    }

    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            return null;
        }
        return coleccionAsignaturas[indice];
    }
}