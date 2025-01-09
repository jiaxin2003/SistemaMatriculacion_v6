package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

import javax.naming.OperationNotSupportedException;

public class Matriculas {
    private static int capacidad = 3;
    private static int tamano;
    private Matricula[] coleccionMatriculas;

    public Matriculas(int capacidad) {
        if (capacidad <= 0|| capacidad > 3) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        Matriculas.capacidad = capacidad;
        Matriculas.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        if (tamano >= capacidad) {
            throw new IllegalStateException("ERROR: No se pueden añadir más matriculas, la capacidad está completa.");
        }
        if (buscarIndice(matricula) != -1) {
            throw new IllegalArgumentException("ERROR: La matricula ya existe en la lista.");
        }
        coleccionMatriculas[tamano] = new Matricula(matricula);
        tamano++;
    }

    private int buscarIndice(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        int indice = -1;
        for (int i = 0; i < tamano && indice == -1; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                indice = i;
            }
        }
        return indice;
    }

    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            return null;
        }
        return coleccionMatriculas[indice];

    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: La matricula no existe en la lista.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
    }

    public Matricula[] get() {
        return copiaProfundaMatriculas(coleccionMatriculas);
    }

    private Matricula[] copiaProfundaMatriculas(Matricula[] matriculasOriginales) {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(matriculasOriginales[i]); // Constructor copia
        }
        return copia;
    }
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[i] = null;
        tamano--;
    }

    private boolean tamanoSuperado(int i) {
        return i >= tamano;
    }

    private boolean capacidadSuperada(int i) {
        return i >= capacidad;
    }


    public static int getCapacidad() {
        if (capacidad <= 0|| capacidad > 3) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        return capacidad;
    }
    public static int getTamano() {
        if (tamano <= 0|| tamano > capacidad) {
            throw new IllegalArgumentException("ERROR: El tamaño supera la capacidad que puede tener la lista.");
        }
        return tamano;
    }


    public Matricula[] get(Alumno alumno) {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copia;
    }
    public Matricula[] get(Curso cursoAcademico) {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copia;
    }
    public Matricula[] get(CicloFormativo cicloFormativo) {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }
        return copia;
    }



}
