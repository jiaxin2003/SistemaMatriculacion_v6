package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

import static org.iesalandalus.programacion.matriculacion.vista.Consola.mostrarCiclosFormativos;


public class MainApp {
    public static final int CAPACIDAD=3;
    private Alumnos alumnos;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;
    private Matriculas matriculas;
    public static void main(String[] args) {

        Alumnos alumnos = new Alumnos(CAPACIDAD);
        Asignaturas asignaturas = new Asignaturas(CAPACIDAD);
        CiclosFormativos ciclosFormativos = new CiclosFormativos(CAPACIDAD);
        Matriculas matriculas = new Matriculas(CAPACIDAD);

        Consola.mostrarMenu();
        Consola.elegirOpcion();

        System.out.println("Hasta luego!!!!");
    }

    private void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_ALUMNO -> insertarAlumno();
            case BUSCAR_ALUMNO -> buscarAlumno();
            case BORRAR_ALUMNO -> borrarAlumno();
            case MOSTRAR_ALUMNOS -> mostrarAlumnos();
            case INSERTAR_ASIGNATURA -> insertarAsignatura();
            case BUSCAR_ASIGNATURA -> buscarAsignatura();
            case BORRAR_ASIGNATURA -> borrarAsignatura();
            case MOSTRAR_ASIGNATURAS -> mostrarAsignaturas();
            case INSERTAR_CICLO_FORMATIVO -> insertarCicloFormativo();
            case BUSCAR_CICLO_FORMATIVO -> buscarCicloFormativo();
            case BORRAR_CICLO_FORMATIVO -> borrarCicloFormativo();
            case MOSTRAR_CICLOS_FORMATIVOS -> mostrarCiclosFormativos();
            case INSERTAR_MATRICULA -> insertarMatricula();
            case BUSCAR_MATRICULA -> buscarMatricula();
            case ANULAR_MATRICULA -> anularMatricula();
            case MOSTRAR_MATRICULAS -> mostrarMatriculas();
            case SALIR -> System.out.println("Saliendo de la aplicación...");
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
            System.out.println("ERROR: No se pudo borrar el alumno." );
        }
    }
    private void mostrarAlumnos(){
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
            System.out.println("ERROR: No se pudo insertar la asignatura." );
        }
    }
    private void buscarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            Asignaturas encontrada = asignaturas.buscar(asignatura);
            System.out.println(encontrada != null ? encontrada : "Asignatura no encontrada.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo buscar la asignatura." );
        }
    }
    private void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            asignaturas.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo borrar la asignatura." );
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
            System.out.println("ERROR: No se pudo insertar el ciclo formativo." );
        }
    }
    private void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            CicloFormativo encontrado = ciclosFormativos.buscar(ciclo);
            System.out.println(encontrado != null ? encontrado : "Ciclo formativo no encontrado.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo buscar el ciclo formativo." );
        }
    }
    private void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            ciclosFormativos.borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo borrar el ciclo formativo." );
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
            System.out.println("ERROR: No se pudo insertar la matrícula." );
        }
    }
    private void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula encontrada = matriculas.buscar(matricula);
            System.out.println(encontrada != null ? encontrada : "Matrícula no encontrada.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo buscar la matrícula." );
        }
    }
    private void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            LocalDate fechaAnulacion = Consola.leerFecha("Fecha de anulación: ");
            System.out.println("Matrícula anulada correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo anular la matrícula." );
        }
    }
    private void mostrarMatriculas() {
        if (Matriculas.getTamano() > 0) {
            System.out.println(matriculas.toString());
        } else {
            System.out.println("No hay matrículas registradas.");
        }
    }


}
