package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

public class Asignaturas {
    private static int capacidad = 3;
    private static int tamano = 0;
    private Asignaturas[] asignaturas;

    public Asignaturas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        Asignaturas.capacidad = capacidad;
        Asignaturas.tamano = 0;
        this.asignaturas = new Asignaturas[capacidad];
    }

    public Asignaturas(Asignaturas asignaturas) {
        if (asignaturas == null) {
            throw new NullPointerException("ERROR: No se puede copiar un objeto nulo.");
        }
        Asignaturas.capacidad = getCapacidad();
        Asignaturas.tamano = getTamano();
        this.asignaturas = copiaProfundaAsignaturas();
    }

    private Asignaturas[]copiaProfundaAsignaturas() {
        Asignaturas[] copiaAsignaturas = new Asignaturas[capacidad];
        for (int i = 0; i < tamano; i++) {
            copiaAsignaturas[i] = new Asignaturas(asignaturas[i]);
        }
        return copiaAsignaturas;

    }

    public void insertar(Asignatura asignatura) {
        if (buscarIndice(asignatura) != -1) {
            throw new IllegalArgumentException("ERROR: La asignatura ya está insertada.");
        }
        if (tamanoSuperado(getTamano())) {
            Asignaturas[] copiaAsignaturas = copiaProfundaAsignaturas();
            copiaAsignaturas[capacidad - 1] = new Asignaturas(asignaturas[tamano]);
            this.asignaturas = copiaAsignaturas;
        } else {
            asignaturas[tamano] = new Asignaturas(asignaturas[tamano]);
            tamano++;
        }
    }

    public Asignaturas[] get() {
        return copiaProfundaAsignaturas();
    }



    public Asignaturas buscar(Asignatura asignatura) {
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            return null;
        } else {
            return new Asignaturas(asignaturas[indice]);
        }
    }

    public void borrar(Asignatura asignatura) {
        int indice = buscarIndice(asignatura);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: La asignatura no existe en la lista.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
        tamano--;
    }

    public int buscarIndice(Asignatura asignatura) {
        int indice = -1;
        for (int i = 0; i < tamano && indice == -1; i++) {
            if (asignaturas[i].equals(asignatura)) {
                indice = i;
            }
        }
        return indice;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        int i;
        for (i = indice; !tamanoSuperado(i); i++) {
            asignaturas[i] = asignaturas[i + 1];
        }
        asignaturas[i] = null;
        tamano--;
    }

    private boolean tamanoSuperado(int i) {
        return i >= tamano;
    }

    private boolean capacidadSuperada(int i) {
        return i >= capacidad;
    }

    public int getCapacidad() {
        if (capacidadSuperada(getTamano())) {
            return Asignaturas.capacidad * 2;
        }
        return Asignaturas.capacidad;
    }

    public int getTamano() {
        if (tamanoSuperado(getTamano())) {
            return Asignaturas.tamano * 2;
        }
        return Asignaturas.tamano;
    }


    public int size() {
        return getTamano();
    }
}
