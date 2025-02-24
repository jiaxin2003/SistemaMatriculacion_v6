package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public interface IAsignaturas {

    void comenzar();

    void terminar();

    ArrayList<Asignatura> get();

    void insertar(Asignatura asignatura) throws OperationNotSupportedException;

    Asignatura buscar(Asignatura asignatura);

    void borrar(Asignatura asignatura) throws OperationNotSupportedException;
}
