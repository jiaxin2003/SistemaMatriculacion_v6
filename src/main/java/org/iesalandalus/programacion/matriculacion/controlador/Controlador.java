package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

public class Controlador {

    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    public void comenzar() {

    }

    public void terminar() {

    }

    public void insertar(Alumno alumno) {


    }

    public Alumno buscar(Alumno alumno) {
        return null;
    }

    public void borrar(Alumno alumno) {

    }

    public Alumno[] getAlumnos() {
        return null;
    }

    public void insertar(Asignatura asignatura) {

    }

    public Asignatura buscar(Asignatura asignatura) {
        return null;
    }

    public void borrar(Asignatura asignatura) {

    }

    public Asignatura[] getAsignaturas() {
        return null;
    }

    public void insertar(CicloFormativo cicloFormativo) {

    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        return null;
    }

    public void borrar(CicloFormativo cicloFormativo) {

    }

    public CicloFormativo[] getCiclosFormativos() {
        return null;
    }

    public void insertar(Matricula matricula) {

    }

    public Matricula buscar(Matricula matricula) {
        return null;
    }

    public void borrar(Matricula matricula) {

    }

    public Matricula[] getMatriculas() {
        return null;
    }

    public Matricula[] getMatriculas(Alumno alumno) {
        return null;
    }

    public Matricula[] getMatriculas(CicloFormativo cicloFormativo) {
        return null;
    }

    public Matricula[] getMatriculas(String cursoAcademico) {
        return null;
    }
}

