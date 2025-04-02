package org.iesalandalus.programacion.matriculacion.vista.texto;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import org.iesalandalus.programacion.utilidades.Entrada;


import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;


public class VistaTexto {

    private Controlador controlador;


    public VistaTexto() {
        Opcion.setVista(this);
    }

    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() {
        controlador.comenzar();
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            opcion.ejecutar();
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Hasta pronto.");
        System.exit(0);
    }


    public void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            this.controlador.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAlumno() {
        try {
            Alumno alumno = this.controlador.buscar(Consola.getAlumnoPorDni());
            System.out.println(alumno);
        } catch (IllegalArgumentException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            this.controlador.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAlumnos() {
        try {
            List<Alumno> alumnos = this.controlador.getAlumnos();
            if (alumnos.isEmpty()) {
                System.out.println("No hay alumnos.");
            } else {
                alumnos.sort(Comparator.comparing(Alumno::getNombre));
                for (Alumno alumno : alumnos) {
                    System.out.println(alumno);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarAsignatura() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            CicloFormativo cicloFormativo = controlador.buscar(ciclo);
            Asignatura asignatura = Consola.leerAsignatura(cicloFormativo);
            this.controlador.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void buscarAsignatura() {
        try {
            Asignatura asignatura = this.controlador.buscar(Consola.getAsignaturaPorCodigo());
            System.out.println(asignatura);
        } catch (IllegalArgumentException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            this.controlador.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void mostrarAsignaturas() {
        try {


            List<Asignatura> asignaturas = this.controlador.getAsignaturas();
            if (asignaturas.isEmpty()) {
                System.out.println("No hay asignaturas.");
            } else {
                asignaturas.sort(Comparator.comparing(Asignatura::getNombre));
                for (Asignatura asignatura : asignaturas) {
                    System.out.println(asignatura);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            this.controlador.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = this.controlador.buscar(Consola.getCicloPorCodigo());
            if (ciclo == null) {
                System.out.println("ERROR: No se pudo buscar el ciclo formativo.");
            }
            System.out.println(ciclo);
        } catch (IllegalArgumentException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            this.controlador.borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarCiclosFormativos() {
        try {
            List<CicloFormativo> cicloFormativos = this.controlador.getCiclosFormativos();
            if (cicloFormativos.isEmpty()) {
                System.out.println("No hay ciclos formativos.");
            } else {
                cicloFormativos.sort(Comparator.comparing(CicloFormativo::getNombre));
                for (CicloFormativo cicloFormativo : cicloFormativos) {
                    System.out.println(cicloFormativo);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertarMatricula() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno alumnoMatricula = controlador.buscar(alumno);
            ArrayList<Asignatura> asignatura = Consola.elegirAsignaturasMatricula(controlador.getAsignaturas());
            Matricula matricula = Consola.leerMatricula(alumnoMatricula, asignatura);
            this.controlador.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula matriculaBuscar = this.controlador.buscar(matricula);
            if (matriculaBuscar == null) {
                System.out.println("ERROR: No se pudo buscar la matrícula.");
            }
            System.out.println(matriculaBuscar);
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula matriculaEncontrada = controlador.buscar(matricula);
            if (matricula.getFechaAnulacion() != null) {
                System.out.println("Matrícula ya anulada.");
            } else {
                do {
                    System.out.println("Introduce la fecha de anulación de la matrícula (dd/mm/aaaa).");
                    String fechaAnulacion = Entrada.cadena();
                    matriculaEncontrada.setFechaAnulacion(LocalDate.parse(fechaAnulacion, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    this.controlador.borrar(matriculaEncontrada);
                } while (matriculaEncontrada.getFechaAnulacion() == null);
            }
            System.out.println("Matrícula anulada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMatriculas() {
        try {
            List<Matricula> matriculas = this.controlador.getMatriculas();
            if (matriculas == null) {
                System.out.println("No hay matrículas.");
            } else {
                matriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed().thenComparing
                        (matricula -> matricula.getAlumno().getNombre()));

                for (Matricula matricula : matriculas) {
                    System.out.println(matricula);
                }
            }
        } catch (OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno alumnoEncontrado = controlador.buscar(alumno);
            List<Matricula> matriculas = (this.controlador.getMatriculas(alumnoEncontrado));
            if (matriculas.isEmpty()) {
                System.out.println("No hay Matriculas con ese Alumno");
            } else
                matriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed().thenComparing
                        (matriculaAlumno -> matriculaAlumno.getAlumno().getNombre()));
            for (Matricula matricula : matriculas) {
                System.out.println(matricula);
            }

        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloPorCodigo();
            CicloFormativo ciclo = controlador.buscar(cicloFormativo);
            List<Matricula> matriculas = (this.controlador.getMatriculas(ciclo));
            if (matriculas.isEmpty()) {
                System.out.println("No hay Matriculas con ese Ciclo Formativo");
            } else matriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed().thenComparing
                    (matriculaCiclo -> matriculaCiclo.getAlumno().getNombre()));
            for (Matricula matricula : matriculas) {
                System.out.println(matricula);
            }
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("Indique el curso academico ");
            System.out.println("Tiene que tener este formato YY-YY");
            String cursoAcademmico = Entrada.cadena();
            List<Matricula> matriculas = controlador.getMatriculas(cursoAcademmico);
            if (matriculas.isEmpty()) {
                System.out.println("no hay matriculas con ese curso academico");
            } else matriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed().thenComparing
                    (Matricula::getCursoAcademico));
            for (Matricula matricula : matriculas) {
                System.out.println(matricula);
            }
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
