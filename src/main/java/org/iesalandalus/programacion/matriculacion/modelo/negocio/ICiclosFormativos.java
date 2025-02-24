package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public interface ICiclosFormativos {

    void comenzar();

    void terminar();

    ArrayList<CicloFormativo> get();

    int getTamano();

    void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException;

    CicloFormativo buscar(CicloFormativo cicloFormativo);

    void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException;
}
