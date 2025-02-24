package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public interface IMatriculas {

    void comenzar();

    void terminar();

    ArrayList<Matricula> get() throws OperationNotSupportedException;

    void insertar(Matricula matricula) throws OperationNotSupportedException;

    Matricula buscar(Matricula matricula)throws OperationNotSupportedException;

    void borrar(Matricula matricula) throws OperationNotSupportedException;

    ArrayList<Matricula> get(Alumno alumno)throws OperationNotSupportedException;

    ArrayList<Matricula> get(String cursoAcademico)throws OperationNotSupportedException;

    ArrayList<Matricula> get(CicloFormativo cicloFormativo)throws OperationNotSupportedException;
}
