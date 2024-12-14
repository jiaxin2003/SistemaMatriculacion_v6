package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;

public class Matriculas {
    private static int capacidad = 3;
    private static int tamano;
    private Matricula[] matriculas;
    private Alumno[] alumno;
    private Curso[] curso;
    private CicloFormativo[] cicloFormativo;


    public Matriculas(int capacidad) {
        if (capacidad <= 0|| capacidad > 3) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        Matriculas.capacidad = capacidad;
        Matriculas.tamano = 0;
        this.matriculas = new Matricula[capacidad];
    }

    public Matriculas(Matriculas matriculas) {
        if (matriculas == null) {
            throw new NullPointerException("ERROR: No se puede copiar un objeto nulo.");
        }
        Matriculas.capacidad = getCapacidad();
        Matriculas.tamano = getTamano();
        this.matriculas = copiaProfundaMatriculas(matriculas.getMatriculas());
    }

    public void insertar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        if (tamano >= capacidad) {
            throw new IllegalStateException("ERROR: No se pueden añadir más matriculas, la capacidad está completa.");
        }
        if (buscarIndice(matricula) != -1) {
            throw new IllegalArgumentException("ERROR: La matricula ya existe en la lista.");
        }
        matriculas[tamano] = new Matricula(matricula);
        tamano++;
    }

    private int buscarIndice(Matricula matricula) {
        int indice = -1;
        for (int i = 0; i < tamano && indice == -1; i++) {
            if (matriculas[i].equals(matricula)) {
                indice = i;
            }
        }
        return indice;
    }

    public Matricula buscar(Matricula matricula) {
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            return null;
        }
        return matriculas[indice];

    }

    public void borrar(Matricula matricula) {
        int indice = buscarIndice(matricula);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: La matricula no existe en la lista.");
        }
        matriculas[indice] = null;
        tamano--;
    }

    public Matricula[] getMatriculas() {
        return copiaProfundaMatriculas(matriculas);
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
            matriculas[i] = matriculas[i + 1];
        }
        matriculas[i] = null;
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


    public Matricula[] getAlumnos(Alumno alumno) {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(matriculas[i]);
        }
        return copia;
    }
    public Matricula[] getAlumnos(Curso cursoAcademico) {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(matriculas[i]);
        }
        return copia;
    }
    public Matricula[] getAlumnos(CicloFormativo cicloFormativo) {
        Matricula[] copia = new Matricula[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(matriculas[i]);
        }
        return copia;
    }
}
