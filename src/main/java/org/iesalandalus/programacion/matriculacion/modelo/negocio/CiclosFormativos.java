package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;


public class CiclosFormativos {
    private final int capacidad;
    private int tamano;
    private final CicloFormativo[] coleccionCiclosFormativos;


    public CiclosFormativos(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionCiclosFormativos = new CicloFormativo[capacidad];
    }

    public void insertar(CicloFormativo cicloFormativo)throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más ciclos formativos.");
        }
        if (buscarIndice(cicloFormativo) != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
        coleccionCiclosFormativos[tamano] = new CicloFormativo(cicloFormativo);
        tamano++;
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = buscarIndice(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }else{
        desplazarUnaPosicionHaciaIzquierda(indice);
        }
    }



    private int buscarIndice(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        for (int i = 0; i < tamano; i++) {
            if (coleccionCiclosFormativos[i].getCodigo() == cicloFormativo.getCodigo()) {
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
            return null;
        }
        return coleccionCiclosFormativos[indice];
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
            coleccionCiclosFormativos[indice] = null;
            int i;
            for (i = indice; !tamanoSuperado(i); i++) {
                if (i<getTamano()-1) {
                    coleccionCiclosFormativos[i] = coleccionCiclosFormativos[i + 1];
                }
            }
            tamano--;
        }

    private boolean tamanoSuperado(int i) {
        return i >= getTamano();
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
