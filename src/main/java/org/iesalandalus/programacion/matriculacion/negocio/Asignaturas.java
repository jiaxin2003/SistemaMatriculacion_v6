package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;


import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Asignaturas {
    private static int capacidad = 3;
    private static int tamano = 0;
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
            copia[i] = coleccionAsignaturas[i];
        }
        return copia;
    }

    public int getTamano() {
        return tamano;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void insertar(Asignatura asignaturas) throws OperationNotSupportedException {
        Objects.requireNonNull(asignaturas, "ERROR: No se puede insertar una asignatura nula.");
        if (capacidadSuperada(getTamano())) {
            throw new OperationNotSupportedException("ERROR: No se puede insertar una asignatura. Capacidad superada.");
        }
        coleccionAsignaturas[tamano] = new Asignaturas(asignaturas);
        tamano++;
    }

    private int buscarIndice(Asignaturas asignatura) {
        for (int i = 0; !tamanoSuperado(i); i++) {
            if (coleccionAsignaturas[i].equals(asignatura)) {
                return i;
            }
        }
        return getTamano();
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    public void borrar(Asignaturas asignatura) throws OperationNotSupportedException {
        Objects.requireNonNull(asignatura, "ERROR: No se puede borrar una asignatura nula.");

        int indice = buscarIndice(asignatura);
        if (tamanoSuperado(indice)) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        } else {
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionAsignaturas[i] = coleccionAsignaturas[i + 1];
        }
        coleccionAsignaturas[i] = null;
        tamano--;
    }

    public Asignaturas buscar(Asignatura asignatura) {
        if (tamanoSuperado(buscarIndice(asignatura))) {
            throw new IllegalArgumentException("ERROR: No existe ninguna asignatura como la indicada.");
        } else {
            for (int i = 0; !tamanoSuperado(i); i++) {
                if (coleccionAsignaturas[i].equals(asignatura)) {
                    return coleccionAsignaturas[i];
                }
            }
        }
        return new Asignaturas(0);
    }

}
