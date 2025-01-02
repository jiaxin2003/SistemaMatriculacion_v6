package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

import java.time.LocalDate;
import java.util.Arrays;


public class MainApp {
    public static final int CAPACIDAD = 3;
    private Alumnos alumnos;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;
    private Matriculas matriculas;

    public static void main(String[] args) {

        Alumnos alumno= new Alumnos(CAPACIDAD);
        Asignaturas asignatura= new Asignaturas(CAPACIDAD);
        CiclosFormativos cicloFormativo= new CiclosFormativos(CAPACIDAD);
        Matriculas matricula= new Matriculas(CAPACIDAD);

        Consola.mostrarMenu();

        MainApp mainApp = new MainApp();
        mainApp.ejecutarOpcion(Consola.elegirOpcion());

        System.out.println("Hasta luego!!!!");
    }

    private void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
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
                mostrarMatriculasPorCiclo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCurso();
                break;
            case SALIR:
                System.out.println("Hasta pronto.");
                break;
        }
    }

    private void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo insertar el alumno.");
        }
    }

    private void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno encontrado = alumnos.buscar(alumno);
            System.out.println(encontrado != null ? encontrado : "Alumno no encontrado.");
        } catch (Exception e) {
            System.out.println("ERROR: no se pudo buscar el alumno.");
        }
    }

    private void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo borrar el alumno.");
        }
    }

    private void mostrarAlumnos() {
        if (alumnos.getTamano() > 0) {
            System.out.println(alumnos.toString());
        } else {
            System.out.println("No hay alumnos registrados.");
        }
    }

    private void insertarAsignatura() {
        try {
            Asignatura asignatura = Consola.leerAsignatura(ciclosFormativos);
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo insertar la asignatura.");
        }
    }


    private void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignaturas encontrada = asignaturas.buscar(asignatura);
            System.out.println(encontrada != null ? encontrada : "Asignatura no encontrada.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo buscar la asignatura.");
        }
    }

    private void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo borrar la asignatura.");
        }
    }


    private void mostrarAsignaturas() {
        if (asignaturas.getTamano() > 0) {
            System.out.println(asignaturas.toString());
        } else {
            System.out.println("No hay asignaturas registradas.");
        }
    }

    private void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo insertar el ciclo formativo.");
        }
    }

    private void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            CicloFormativo encontrado = ciclosFormativos.buscar(ciclo);
            System.out.println(encontrado != null ? encontrado : "Ciclo formativo no encontrado.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo buscar el ciclo formativo.");
        }
    }

    private void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            ciclosFormativos.borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo borrar el ciclo formativo.");
        }
    }

    private void mostrarCiclosFormativos() {
        if (ciclosFormativos.getTamano() > 0) {
            System.out.println(ciclosFormativos.toString());
        } else {
            System.out.println("No hay ciclos formativos registrados.");
        }
    }

    private void insertarMatricula() {
        try {
            Matricula matricula = Consola.leerMatricula(alumnos, asignaturas);
            matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo insertar la matrícula.");
        }
    }

    private void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matricula);
            System.out.println(encontrada != null ? encontrada : "Matrícula no encontrada.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo buscar la matrícula.");
        }
    }

    private void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            LocalDate fechaAnulacion = Consola.leerFecha("Fecha de anulación: ");
            System.out.println("Matrícula anulada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo anular la matrícula.");
        }
    }

    private void mostrarMatriculas() {
        if (Matriculas.getTamano() > 0) {
            System.out.println(matriculas.toString());
        } else {
            System.out.println("No hay matrículas registradas.");
        }
    }

    private void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            System.out.println(Arrays.toString(matriculas.getMatriculas(alumno)));
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por alumno.");
        }
    }

    private void mostrarMatriculasPorCiclo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            System.out.println(Arrays.toString(matriculas.getMatriculas(ciclo)));
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por ciclo.");
        }
    }

    private void mostrarMatriculasPorCurso() {
        try {
            Curso curso = Consola.leerCurso();
            System.out.println(Arrays.toString(matriculas.getMatriculas(curso)));
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por curso.");
        }
    }


}
