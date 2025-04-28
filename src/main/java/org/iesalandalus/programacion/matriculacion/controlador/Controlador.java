package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.vista.texto.VistaTexto;

import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.util.List;

public class Controlador {

    private Vista vista;
    private final Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        if (vista == null || modelo == null) {
            throw new NullPointerException("ERROR: El modelo o la vista no pueden ser nulos");
        }
        this.vista = vista;
        this.modelo = modelo;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        this.modelo.comenzar();

    }

    public void terminar() {
        this.modelo.terminar();

    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException, SQLException {
        this.modelo.insertar(alumno);

    }

    public Alumno buscar(Alumno alumno) throws SQLException {
        return this.modelo.buscar(alumno);
    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException, SQLException {
        if (!getMatriculas(alumno).isEmpty())
            throw new OperationNotSupportedException("ERROR: No se puede borrar un alumno con matriculas.");
        this.modelo.borrar(alumno);

    }

    public List<Alumno> getAlumnos() throws SQLException {
        return this.modelo.getAlumnos();
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        this.modelo.insertar(asignatura);

    }

    public Asignatura buscar(Asignatura asignatura) throws SQLException {
        return this.modelo.buscar(asignatura);
    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        for (Matricula matricula : getMatriculas()) {
            if (matricula.getColeccionAsignaturas().contains(asignatura));
            {
                throw new OperationNotSupportedException("ERROR: No se puede borrar una asignatura con matriculas.");
            }
        }
        this.modelo.borrar(asignatura);

    }

    public List<Asignatura> getAsignaturas() throws SQLException {
        return this.modelo.getAsignaturas();
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        this.modelo.insertar(cicloFormativo);

    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws SQLException {
        return this.modelo.buscar(cicloFormativo);
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        if(!getMatriculas(cicloFormativo).isEmpty())
            throw new OperationNotSupportedException("ERROR: No se puede borrar un ciclo formativo con matriculas.");
        this.modelo.borrar(cicloFormativo);
    }

    public List<CicloFormativo> getCiclosFormativos() throws SQLException {
        return this.modelo.getCiclosFormativos();
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        this.modelo.insertar(matricula);

    }

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        return this.modelo.buscar(matricula);
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        this.modelo.borrar(matricula);

    }

    public List<Matricula> getMatriculas() throws OperationNotSupportedException, SQLException {
        return this.modelo.getMatriculas();
    }

    public List<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException, SQLException {
        return this.modelo.getMatriculas(alumno);
    }

    public List<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        return this.modelo.getMatriculas(cicloFormativo);
    }

    public List<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException, SQLException {
        return this.modelo.getMatriculas(cursoAcademico);
    }
}

