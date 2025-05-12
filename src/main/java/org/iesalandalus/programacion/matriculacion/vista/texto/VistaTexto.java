package org.iesalandalus.programacion.matriculacion.vista.texto;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.utilidades.Entrada;


import javax.naming.OperationNotSupportedException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class VistaTexto extends Vista {

    public VistaTexto() {
        Opcion.setVista(this);
    }


    public void comenzar() {
        getControlador().comenzar();
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
            getControlador().insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarAlumno() {
        try {
            Alumno alumno = getControlador().buscar(Consola.getAlumnoPorDni());
            if (alumno == null) {
                System.out.println("ERROR: No se pudo buscar el alumno.");
            } else {
                System.out.println(alumno);
            }
        } catch (IllegalArgumentException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            getControlador().borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAlumnos() {
        try {
            List<Alumno> alumnos = getControlador().getAlumnos();
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
            CicloFormativo cicloFormativo = getControlador().buscar(ciclo);
            Asignatura asignatura = Consola.leerAsignatura(cicloFormativo);
            getControlador().insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void buscarAsignatura() {
        try {
            Asignatura asignatura = getControlador().buscar(Consola.getAsignaturaPorCodigo());
            if (asignatura == null) {
                System.out.println("ERROR: No se pudo buscar la asignatura.");
            } else {
                System.out.println(asignatura);
            }
        } catch (IllegalArgumentException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            getControlador().borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void mostrarAsignaturas() {
        try {


            List<Asignatura> asignaturas = getControlador().getAsignaturas();
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
            getControlador().insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = getControlador().buscar(Consola.getCicloPorCodigo());
            if (ciclo == null) {
                System.out.println("ERROR: No se pudo buscar el ciclo formativo.");
            } else {
                System.out.println(ciclo);
            }
        } catch (IllegalArgumentException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            getControlador().borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarCiclosFormativos() {
        try {
            List<CicloFormativo> cicloFormativos = getControlador().getCiclosFormativos();
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
            Alumno alumnoMatricula = getControlador().buscar(alumno);
            ArrayList<Asignatura> asignatura = Consola.elegirAsignaturasMatricula(getControlador().getAsignaturas());
            Matricula matricula = Consola.leerMatricula(alumnoMatricula, asignatura);
            getControlador().insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula matriculaBuscar = getControlador().buscar(matricula);
            if (matriculaBuscar == null) {
                System.out.println("ERROR: No se pudo buscar la matrícula.");
            } else {
                System.out.println(matriculaBuscar);
            }
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            Matricula matriculaEncontrada = getControlador().buscar(matricula);

            if (matriculaEncontrada == null) {
                System.out.println("ERROR: No existe la matrícula indicada.");
            } else {
                if (matriculaEncontrada.getFechaAnulacion() != null) {
                    System.out.println("Matrícula ya anulada.");
                } else {
                    getControlador().borrar(matriculaEncontrada);
                    matriculaEncontrada.setFechaAnulacion(LocalDate.now()); // actualizamos en memoria
                    System.out.println("Matrícula anulada correctamente.");
                }
            }

        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void mostrarMatriculas() {
        try {
            List<Matricula> matriculas = getControlador().getMatriculas();
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
            Alumno alumnoEncontrado = getControlador().buscar(alumno);
            if (alumnoEncontrado == null) {
                System.out.println("ERROR: No hay alumnos con ese DNI.");
            } else {
                List<Matricula> matriculas = (getControlador().getMatriculas(alumnoEncontrado));
                if (matriculas.isEmpty()) {
                    System.out.println("No hay Matriculas con ese Alumno");
                } else
                    matriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed().thenComparing
                            (matriculaAlumno -> matriculaAlumno.getAlumno().getNombre()));
                for (Matricula matricula : matriculas) {
                    System.out.println(matricula);
                }
            }
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloPorCodigo();
            CicloFormativo ciclo = getControlador().buscar(cicloFormativo);
            if (ciclo == null) {
                System.out.println("ERROR: No hay ciclos con ese Codigo.");
            } else {
                List<Matricula> matriculas = (getControlador().getMatriculas(ciclo));
                if (matriculas.isEmpty()) {
                    System.out.println("No hay Matriculas con ese Ciclo Formativo");
                } else matriculas.sort(Comparator.comparing(Matricula::getFechaMatriculacion).reversed().thenComparing
                        (matriculaCiclo -> matriculaCiclo.getAlumno().getNombre()));
                for (Matricula matricula : matriculas) {
                    System.out.println(matricula);
                }
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
            List<Matricula> matriculas = getControlador().getMatriculas(cursoAcademmico);
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
