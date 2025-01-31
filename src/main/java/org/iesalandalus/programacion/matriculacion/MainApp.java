package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Arrays;


public class MainApp {
    /*
    public static final int CAPACIDAD = 3;
    private static Alumnos alumnos;
    private static Asignaturas asignaturas;
    public static CiclosFormativos ciclosFormativos;
    private static Matriculas matriculas;*/

    public static void main(String[] args) {

        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
        modelo.comenzar();
        vista.comenzar();
    }
}

/*
        alumnos = new Alumnos(CAPACIDAD);
        asignaturas = new Asignaturas(CAPACIDAD);
        ciclosFormativos = new CiclosFormativos(CAPACIDAD);
        matriculas = new Matriculas(CAPACIDAD);


        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);



    private static void ejecutarOpcion(Opcion opcion) {
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
                mostrarMatriculasPorCiclo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCurso();
                break;

        }
    }

    private static void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo insertar el alumno.");
        }
    }

    private static void buscarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.buscar(alumno);
            System.out.println("Alumno encontrado.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar el alumno.");
        }
    }

    private static void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            alumnos.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo borrar el alumno.");
        }
    }

    private static void mostrarAlumnos() {
        if (alumnos.getTamano() == 0 || alumnos.getTamano() == alumnos.getCapacidad()) {
            System.out.println("No hay alumnos registrados.");
        } else System.out.println("Alumnos:" + Arrays.toString(alumnos.get()));

    }


    private static void insertarAsignatura() {
        try {
            Asignatura asignatura = Consola.leerAsignatura(ciclosFormativos);
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo insertar la asignatura.");
        }
    }


    private static void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.buscar(asignatura);
            System.out.println("Asignatura encontrada.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar la asignatura.");
        }
    }

    private static void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo borrar la asignatura.");
        }
    }


    private static void mostrarAsignaturas() {
        if (asignaturas.getTamano() < 0) {
            System.out.println("No hay asignaturas registradas.");
        }
        if (asignaturas.getTamano() == asignaturas.getCapacidad())
            System.out.println("No esta esa asignatura.");
        else {
            System.out.println(Arrays.toString(asignaturas.get()));
        }
    }

    private static void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo insertar el ciclo formativo.");
        }
    }

    private static void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            CicloFormativo encontrado = ciclosFormativos.buscar(ciclo);
            System.out.println(encontrado != null ? encontrado : "Ciclo formativo no encontrado.");
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar el ciclo formativo.");
        }
    }

    private static void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            ciclosFormativos.borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo borrar el ciclo formativo.");
        }
    }

    private static void mostrarCiclosFormativos() {
        if (ciclosFormativos.getTamano() < 0 || ciclosFormativos.getTamano() == ciclosFormativos.getCapacidad()) {
            System.out.println("No hay ciclos formativos registrados.");
        } else {
            System.out.println("Ciclos Formativos :");
            Consola.mostrarCiclosFormativos(ciclosFormativos.get());
        }
    }

    private static void insertarMatricula() {
        try {
            Matricula matricula = Consola.leerMatricula(alumno, asignaturas);
            matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo insertar la matrícula.");
        }
    }

    private static void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matricula);
            System.out.println(encontrada != null ? encontrada : "Matrícula no encontrada.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar la matrícula.");
        }
    }

    private static void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            matriculas.borrar(matricula);
            if (matricula.getFechaAnulacion() != null) {
                System.out.println("Matrícula ya anulada.");
                return;
            } else {
                do {
                    System.out.println("Introduce la fecha de anulación de la matrícula (dd/mm/aaaa).");
                    String fechaAnulacion = Entrada.cadena();
                    matricula.setFechaAnulacion(LocalDate.parse(fechaAnulacion));
                } while (matricula.getFechaAnulacion() == null);
            }
            System.out.println("Matrícula anulada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo anular la matrícula.");
        }
    }

    private static void mostrarMatriculas() {
        try {
            if (Matriculas.getTamano() < 0) {
                System.out.println(Arrays.toString(matriculas.get()));
            }
        } catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
            System.out.println("No hay Matrículas registradas.");
        }

    }

    private static void mostrarMatriculasPorAlumno() {
        try {
            Consola.getAlumnoPorDni();
            System.out.println(Arrays.toString(matriculas.get()));
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por alumno.");
        }
    }

    private static void mostrarMatriculasPorCiclo() {
        try {
            Consola.getCicloPorCodigo();
            System.out.println(Arrays.toString(matriculas.get()));
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por ciclo.");
        }
    }

    private static void mostrarMatriculasPorCurso() {
        try {
            System.out.println(Arrays.toString(matriculas.get()));
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por curso.");
        }
    }


}
*/