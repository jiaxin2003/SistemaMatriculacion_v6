package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;

public class Vista {

    private void setControlador(Controlador controlador) {
        controlador.comenzar();
    }

    public void comenzar() {
        setControlador(new Controlador(this, new Modelo()));
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        }while (opcion != Opcion.SALIR);
    }

    public void terminar() {

    }

    private void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                System.out.println("Hasta pronto.");
                System.exit(0);
                break;
            case INSERTAR_ALUMNO:
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                break;
            case INSERTAR_CICLO_FORMATIVO:
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO_FORMATIVO:
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO_FORMATIVO:
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS_FORMATIVOS:
                mostrarCiclosFormativos();
                break;
            case INSERTAR_MATRICULA:
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO_FORMATIVO:
                mostrarMatriculasPorCiclosFormativos();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCursoAcademico();
                break;

        }

    }

    private void insertarAlumno() {

    }

    private void buscarAlumno() {

    }

    private void borrarAlumno() {

    }

    private void mostrarAlumnos() {

    }

    private void insertarAsignatura() {

    }

    private void buscarAsignatura() {

    }

    private void borrarAsignatura() {

    }

    private void mostrarAsignaturas() {

    }

    private void insertarCicloFormativo() {

    }

    private void buscarCicloFormativo() {

    }

    private void borrarCicloFormativo() {

    }

    private void mostrarCiclosFormativos() {

    }

    private void insertarMatricula() {

    }

    private void buscarMatricula() {

    }

    private void anularMatricula() {

    }

    private void mostrarMatriculas() {

    }

    private void mostrarMatriculasPorAlumno() {

    }

    private void mostrarMatriculasPorCiclosFormativos() {

    }

    private void mostrarMatriculasPorCursoAcademico() {

    }


}
