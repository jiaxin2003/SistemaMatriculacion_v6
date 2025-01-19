package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import java.util.Arrays;
import java.util.Objects;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import javax.naming.OperationNotSupportedException;

public class  Alumnos {
    private final int capacidad;
    private int tamano;
    private final Alumno [] coleccionAlumnos;


    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }


    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        if (getTamano() >= getCapacidad()) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
        }
        if (buscarIndice(alumno) != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
        coleccionAlumnos[tamano++] = new Alumno(alumno);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
                throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (Objects.equals(coleccionAlumnos[i].getDni(), alumno.getDni())) {
                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice) {
        return indice >= getTamano();
    }

    private boolean capacidadSuperada(int indice) {
        return indice >= getCapacidad();
    }

    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            return null;
        }
        return coleccionAlumnos[indice];
    }
    private void desplazarUnaPosicionHaciaIzquierda (int indice) {
        coleccionAlumnos[indice] = null;
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            if (i<getCapacidad()-1) {
                coleccionAlumnos[i] = coleccionAlumnos[i + 1];
            }
        }
        tamano--;
    }

    public int getCapacidad() {
        return capacidad;
    }


    public int getTamano() {
        return tamano;
    }


    public Alumno[] get() {
        return (copiaProfundaAlumnos());
    }

    private Alumno[] copiaProfundaAlumnos() {
        Alumno[] copia = new Alumno[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Alumno(coleccionAlumnos[i]);
        }
        return copia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacidad, tamano, Arrays.hashCode(coleccionAlumnos));
    }
}
