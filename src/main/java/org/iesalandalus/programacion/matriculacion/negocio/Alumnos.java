package org.iesalandalus.programacion.matriculacion.negocio;

import java.util.Arrays;
import java.util.Objects;

public class Alumnos {
    private int capacidad = 3;
    private int tamano = 0;
    String[] alumnos;


    public Alumnos(int capacidad) {

        setCapacidad(capacidad);
        setTamano(tamano);
        alumnos = new String[capacidad];
    }

    public Alumnos(Alumnos nuevosAlumnos) {
        if (nuevosAlumnos == null) {
            throw new NullPointerException("ERROR: Los alumnos no pueden ser nulos");
        }
        this.capacidad=nuevosAlumnos.getCapacidad();
        this.tamano=nuevosAlumnos.getTamano();
        this.alumnos = Arrays.copyOf(nuevosAlumnos.alumnos, nuevosAlumnos.alumnos.length);
    }



    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getTamano() {
        return tamano=0;
    }

    public void setTamano(int tamano) {
        if (tamano>capacidad) {
            throw new IllegalArgumentException("ERROR: El tamaño supera la capacidad que puede tener la lista.");
        }
        this.tamano = tamano;
    }

    public String[] getAlumnos() {
        return Arrays.copyOf(alumnos, tamano);
    }

    public void setAlumnos(String[] nuevosAlumnos) {
        if (nuevosAlumnos == null) {
            throw new NullPointerException("ERROR: El array de alumnos no puede ser nulo.");
        }
        if (nuevosAlumnos.length > capacidad) {
            throw new IllegalArgumentException("ERROR: El array excede la capacidad.");
        }
        this.alumnos = Arrays.copyOf(nuevosAlumnos, nuevosAlumnos.length);
        this.tamano = nuevosAlumnos.length;
    }
    public void insertarAlumno(String alumno) {
        if (alumno == null || alumno.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El alumno no puede ser nulo o vacío.");
        }
        if (tamano >= capacidad) {
            throw new IllegalStateException("ERROR: No se pueden añadir más alumnos, la capacidad está completa.");
        }
        alumnos[tamano] = alumno;
        tamano++;
    }
    public void eliminarAlumno(String alumno) {
        if (alumno == null || alumno.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El alumno no puede ser nulo o vacío.");
        }
        int indice = buscarIndice(alumno);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: El alumno no existe en la lista.");
        }
        System.arraycopy(alumnos, indice + 1, alumnos, indice, tamano - indice - 1);
        tamano--;
        alumnos[tamano] = null;
    }
    public int buscarIndice(String alumno) {
        if (alumno == null || alumno.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El alumno no puede ser nulo o vacío.");
        }
        for (int i = 0; i < tamano; i++) {
            if (alumnos[i].equals(alumno)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumnos alumnos1)) return false;
        return capacidad == alumnos1.capacidad && tamano == alumnos1.tamano && Objects.deepEquals(alumnos, alumnos1.alumnos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacidad, tamano, Arrays.hashCode(alumnos));
    }
}
