package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Asignaturas {
    private int capacidad;
    private int tamano;
    private Asignaturas[] coleccionAsignaturas;

    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAsignaturas = new Asignaturas[capacidad];
    }

    public Asignaturas[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignaturas[] copiaProfundaAsignaturas() {
        Asignaturas[] copia = new Asignaturas[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Asignaturas(coleccionAsignaturas[i].getCapacidad());
        }
        return copia;
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
        coleccionAsignaturas[tamano] = new Asignaturas(1);
        coleccionAsignaturas[tamano].insertar(asignatura);
        tamano++;
    }

    private int buscarIndice(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAsignaturas[i].get()[0].equals(asignatura)) {
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

    public Asignaturas buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            return null;
        }
        return coleccionAsignaturas[indice].get()[0];
    }
}