package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import java.util.Arrays;

public class CiclosFormativos {
    private int capacidad;
    private int tamano;
    private final CicloFormativo[] coleccionCiclosFormativos;


    public CiclosFormativos(int capacidad) {
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    public void insertar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        if (tamano >= capacidad) {
            throw new IllegalStateException("ERROR: No se pueden añadir más ciclos, la capacidad está completa.");
        }
        if (buscarIndice(cicloFormativo) != -1) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo ya existe en la lista.");
        }
        coleccionCiclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
        tamano++;
    }

    public void borrar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no existe en la lista.");
        }
        desplazarUnaPosicionHaciaIzquierda(indice);
    }



    private int buscarIndice(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        return -1;
    }
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no existe en la lista.");
        }
        return coleccionCiclosFormativos[indice];
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        System.arraycopy(coleccionCiclosFormativos, indice + 1, coleccionCiclosFormativos, indice, tamano - indice - 1);
        tamano--;
        coleccionCiclosFormativos[tamano] = null;
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos(CicloFormativo[] ciclosFormativos) {
        CicloFormativo[] copia = new CicloFormativo[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new CicloFormativo(ciclosFormativos[i]);
        }
        return copia;
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos(coleccionCiclosFormativos);
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }


}
