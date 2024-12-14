package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import java.util.Arrays;

public class CiclosFormativos {
    private int capacidad;
    private int tamano;
    private final CicloFormativo[] ciclosFormativos;


    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.ciclosFormativos = new CicloFormativo[capacidad];
    }

    public CiclosFormativos(CiclosFormativos otrosCiclosFormativos) {
        if (otrosCiclosFormativos == null) {
            throw new NullPointerException("ERROR: No se puede copiar un objeto nulo.");
        }
        this.capacidad = otrosCiclosFormativos.getCapacidad();
        this.tamano = otrosCiclosFormativos.getTamano();
        this.ciclosFormativos = copiaProfundaCiclosFormativos(otrosCiclosFormativos.get());
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
        ciclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
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
        for (int i = 0; i < tamano; i++) {
            if (ciclosFormativos[i].equals(cicloFormativo)) {
                return i;
            }
        }
        return -1;
    }
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new IllegalArgumentException("ERROR: El ciclo formativo no existe en la lista.");
        }
        return ciclosFormativos[indice];
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        System.arraycopy(ciclosFormativos, indice + 1, ciclosFormativos, indice, tamano - indice - 1);
        tamano--;
        ciclosFormativos[tamano] = null;
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos(CicloFormativo[] ciclosOriginales) {
        CicloFormativo[] copia = new CicloFormativo[tamano];
        for (int i = 0; i < tamano; i++) {
            copia[i] = new CicloFormativo(ciclosOriginales[i]); // Constructor copia
        }
        return copia;
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos(ciclosFormativos);
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamano() {
        return tamano;
    }

    @Override
    public String toString() {
        return "CiclosFormativos{" +
                "capacidad=" + capacidad +
                ", tamano=" + tamano +
                ", ciclosFormativos=" + Arrays.toString(ciclosFormativos) +
                '}';
    }


}
