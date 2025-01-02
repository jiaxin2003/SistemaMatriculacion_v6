package org.iesalandalus.programacion.matriculacion.negocio;

import java.util.Arrays;
import java.util.Objects;
import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

public class Alumnos {
    private int capacidad = 3;
    private int tamano = 0;
    private final Alumno [] coleccionAlumnos;


    public Alumnos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionAlumnos = new Alumno[capacidad];
    }


    public void insertar(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: El alumno no puede ser nulo.");
        }
        if (tamano >= capacidad) {
            throw new IllegalStateException("ERROR: No se pueden añadir más alumnos, la capacidad está completa.");
        }
        if (buscarIndice(alumno) != -1) {
            throw new IllegalArgumentException("ERROR: El alumno ya existe en la lista.");
        }
        coleccionAlumnos[tamano] = new Alumno(alumno);
        tamano++;
    }

    public void borrar(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: El alumno no puede ser nulo.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: El alumno no existe en la lista.");
        }else{
            desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }

    private int buscarIndice(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionAlumnos[i].equals(alumno)) {
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
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionAlumnos[i] = coleccionAlumnos[i + 1];
        }
        coleccionAlumnos[i] = null;
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
