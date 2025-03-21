package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;


import javax.naming.OperationNotSupportedException;

import java.sql.SQLException;
import java.util.List;

public class Modelo {


    private IAlumnos alumnos;
    private IAsignaturas asignaturas;
    private ICiclosFormativos ciclosFormativos;
    private IMatriculas matriculas;
    private IFuenteDatos fuenteDatos;


    public Modelo(FactoriaFuenteDatos factoriaFuenteDatos) {
        setFuenteDatos(factoriaFuenteDatos.crear());
    }

    private void setFuenteDatos(IFuenteDatos fuenteDatos) {
        this.fuenteDatos = fuenteDatos;
    }

    public void comenzar() {

        this.alumnos = fuenteDatos.crearAlumnos();
        this.asignaturas = fuenteDatos.crearAsignaturas();
        this.ciclosFormativos = fuenteDatos.crearCiclosFormativos();
        this.matriculas = fuenteDatos.crearMatriculas();



        /*try {
            Alumno a1 = new Alumno("Pepe", "12345678Z",  "666666666","asbvadvsa@aasdsad.com", LocalDate.of(2000, 1, 1));
            this.alumnos.insertar(a1);
            CicloFormativo cf1 = new CicloFormativo(1234, "DAW", new GradoD("Grado D", 2, Modalidad.PRESENCIAL), "Desarrollo Aplicaciones Web", 20);
            this.ciclosFormativos.insertar(cf1);
            Asignatura as1 = new Asignatura("5678", "Programacion", 30, Curso.PRIMERO, 2, EspecialidadProfesorado.INFORMATICA, cf1);
            this.asignaturas.insertar(as1);
            Matricula m1 = new Matricula(9876, "24-25", LocalDate.of(2025, 2, 15), a1, this.asignaturas.get());
            this.matriculas.insertar(m1);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    public void terminar() {
        this.alumnos.terminar();
        this.asignaturas.terminar();
        this.ciclosFormativos.terminar();
        this.matriculas.terminar();
    }

    public void insertar(Alumno alumno) throws OperationNotSupportedException, SQLException {
        this.alumnos.insertar(alumno);

    }

    public Alumno buscar(Alumno alumno) throws SQLException {
        return this.alumnos.buscar(alumno);

    }

    public void borrar(Alumno alumno) throws OperationNotSupportedException, SQLException {
        this.alumnos.borrar(alumno);
    }

    public List<Alumno> getAlumnos() throws SQLException {
        return this.alumnos.get();
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        this.asignaturas.insertar(asignatura);

    }

    public Asignatura buscar(Asignatura asignatura) throws SQLException {
        return this.asignaturas.buscar(asignatura);

    }

    public void borrar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        this.asignaturas.borrar(asignatura);

    }

    public List<Asignatura> getAsignaturas() throws SQLException {
        return this.asignaturas.get();
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        this.ciclosFormativos.insertar(cicloFormativo);

    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws SQLException {
        return this.ciclosFormativos.buscar(cicloFormativo);

    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        this.ciclosFormativos.borrar(cicloFormativo);

    }

    public List<CicloFormativo> getCiclosFormativos() throws SQLException {
        return this.ciclosFormativos.get();
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        this.matriculas.insertar(matricula);

    }

    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        return this.matriculas.buscar(matricula);

    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        this.matriculas.borrar(matricula);

    }

    public List<Matricula> getMatriculas() throws OperationNotSupportedException, SQLException {
        return this.matriculas.get();

    }

    public List<Matricula> getMatriculas(Alumno alumno) throws OperationNotSupportedException, SQLException {
        return this.matriculas.get(alumno);
    }

    public List<Matricula> getMatriculas(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        return this.matriculas.get(cicloFormativo);
    }

    public List<Matricula> getMatriculas(String cursoAcademico) throws OperationNotSupportedException, SQLException {
        return this.matriculas.get(cursoAcademico);
    }
}
