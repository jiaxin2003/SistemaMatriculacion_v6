package org.iesalandalus.programacion.matriculacion.vista;


import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import org.iesalandalus.programacion.utilidades.Entrada;


import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Vista {

    private Controlador controlador;


    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new NullPointerException("ERROR: El controlador no puede ser nulo.");
        }
        this.controlador = controlador;
    }

    public void comenzar() {
        setControlador(new Controlador(this, new Modelo()));
        controlador.comenzar();
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("Hasta pronto.");
        System.exit(0);
    }

    private void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case SALIR:
                terminar();
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
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO_ACADEMICO:
                mostrarMatriculasPorCursoAcademico();
                break;

        }
    }

    private void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            this.controlador.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo insertar el alumno.");
        }
    }

    private void buscarAlumno() {
        try {
            Alumno alumno = this.controlador.buscar(Consola.getAlumnoPorDni());
            if (alumno == null) {
                System.out.println("ERROR: No se pudo buscar el alumno.");
            }
            System.out.println(alumno);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar el alumno.");
        }
    }

    private void borrarAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            this.controlador.borrar(alumno);
            System.out.println("Alumno borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo borrar el alumno.");
        }
    }

    private void mostrarAlumnos() {
        List<Alumno> alumnos = this.controlador.getAlumnos();
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos.");
        } else {
            alumnos.sort(new Comparator<Alumno>() {
                public int compare(Alumno alumno, Alumno alumno2) {
                    return alumno.getNombre().compareToIgnoreCase(alumno2.getNombre());
                }
            });
            for (Alumno alumno : alumnos) {
                System.out.println(alumno);
            }
        }
    }

    private void insertarAsignatura() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            CicloFormativo cicloFormativo = controlador.buscar(ciclo);
            Asignatura asignatura = Consola.leerAsignatura(cicloFormativo);
            this.controlador.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo insertar la asignatura.");
        }
    }


    private void buscarAsignatura() {
        try {
            Asignatura asignatura = this.controlador.buscar(Consola.getAsignaturaPorCodigo());
            if (asignatura == null) {
                System.out.println("ERROR: No se pudo buscar la asignatura.");
            }
            System.out.println(asignatura);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar la asignatura.");
        }
    }

    private void borrarAsignatura() {
        try {
            Asignatura asignatura = Consola.getAsignaturaPorCodigo();
            this.controlador.borrar(asignatura);
            System.out.println("Asignatura borrada correctamente.");
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo borrar la asignatura.");
        }
    }


    private void mostrarAsignaturas() {
        List<Asignatura> asignaturas = this.controlador.getAsignaturas();
        if (asignaturas.isEmpty()) {
            System.out.println("No hay asignaturas.");
        } else {
            asignaturas.sort(new Comparator<Asignatura>() {
                @Override
                public int compare(Asignatura asignatura1, Asignatura asignatura2) {
                    return asignatura1.getNombre().compareToIgnoreCase(asignatura2.getNombre());
                }
            });
            for (Asignatura asignatura : asignaturas) {
                System.out.println(asignatura);
            }
        }
    }

    private void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            this.controlador.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo insertar el ciclo formativo.");
        }
    }

    private void buscarCicloFormativo() {
        try {
            CicloFormativo ciclo = this.controlador.buscar(Consola.getCicloPorCodigo());
            if (ciclo == null) {
                System.out.println("ERROR: No se pudo buscar el ciclo formativo.");
            }
            System.out.println(ciclo);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar el ciclo formativo.");
        }
    }

    private void borrarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.getCicloPorCodigo();
            this.controlador.borrar(ciclo);
            System.out.println("Ciclo formativo borrado correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo borrar el ciclo formativo.");
        }
    }

    private void mostrarCiclosFormativos() {
        List<CicloFormativo> cicloFormativos = this.controlador.getCiclosFormativos();
        if (cicloFormativos.isEmpty()) {
            System.out.println("No hay ciclos formativos.");
        } else {
            cicloFormativos.sort(new Comparator<CicloFormativo>() {
                public int compare(CicloFormativo cicloFormativo1, CicloFormativo cicloFormativo2) {
                    return cicloFormativo1.getNombre().compareToIgnoreCase(cicloFormativo2.getNombre());
                }
            });
            for (CicloFormativo cicloFormativo : cicloFormativos) {
                System.out.println(cicloFormativo);
            }
        }

    }

    private void insertarMatricula() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno alumnoMatricula = controlador.buscar(alumno);
            ArrayList<Asignatura> asignatura = Consola.elegirAsignaturasMatricula(controlador.getAsignaturas());
            Matricula matricula = Consola.leerMatricula(alumnoMatricula, asignatura);
            this.controlador.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo insertar la matrícula.");
        }
    }

    private void buscarMatricula() {
        try {
            Matricula matricula = this.controlador.buscar(Consola.getMatriculaPorIdentificador());
            if (matricula == null) {
                System.out.println("ERROR: No se pudo buscar la matrícula.");
            }
            System.out.println(matricula);
        } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {
            System.out.println("ERROR: No se pudo buscar la matrícula.");
        }
    }

    private void anularMatricula() {
        try {
            Matricula matricula = Consola.getMatriculaPorIdentificador();
            this.controlador.borrar(matricula);
            if (matricula.getFechaAnulacion() != null) {
                System.out.println("Matrícula ya anulada.");
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

    private void mostrarMatriculas() {
        try {
            List<Matricula> matriculas = this.controlador.getMatriculas();
            if (matriculas == null) {
                System.out.println("No hay matrículas.");
            } else {
                matriculas.sort(new Comparator<Matricula>() {
                    public int compare(Matricula matricula1, Matricula matricula2) {
                        int comparador = -matricula1.getFechaMatriculacion().compareTo(matricula2.getFechaMatriculacion());
                        if (comparador == 0) {
                            comparador = matricula1.getAlumno().getNombre().compareToIgnoreCase(matricula2.getAlumno().getNombre());
                        }
                        return comparador;
                    }
                });
                for (Matricula matricula : matriculas) {
                    System.out.println(matricula);
                }
            }
        } catch (OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas.");
        }
    }

    private void mostrarMatriculasPorAlumno() {
        try {
            Alumno alumno = Consola.getAlumnoPorDni();
            Alumno alumnoEncontrado = controlador.buscar(alumno);
            List<Matricula> matriculas = (this.controlador.getMatriculas(alumnoEncontrado));
            if (matriculas == null) {
                System.out.println("No hay Matriculas con ese Alumno");
            } else
                matriculas.sort(new Comparator<Matricula>() {
                    public int compare(Matricula matricula1, Matricula matricula2) {
                        int comparador = -matricula1.getFechaMatriculacion().compareTo(matricula2.getFechaMatriculacion());
                        if (comparador == 0) {
                            comparador = matricula1.getAlumno().getNombre().compareToIgnoreCase(matricula2.getAlumno().getNombre());
                        }
                        return comparador;
                    }
                });
            if (matriculas != null)
                for (Matricula matricula : matriculas) {
                    System.out.println(matricula);
                }

        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por alumno.");
        }
    }

    private void mostrarMatriculasPorCicloFormativo() {
        try {
            CicloFormativo cicloFormativo = Consola.getCicloPorCodigo();
            CicloFormativo ciclo = controlador.buscar(cicloFormativo);
            List<Matricula> matriculas = (this.controlador.getMatriculas(ciclo));
            if (matriculas == null) {
                System.out.println("No hay Matriculas con ese Ciclo Formativo");
            } else matriculas.sort(new Comparator<Matricula>() {
                public int compare(Matricula matricula1, Matricula matricula2) {
                    int comparador = -matricula1.getFechaMatriculacion().compareTo(matricula2.getFechaMatriculacion());
                    if (comparador == 0) {
                        comparador = matricula1.getAlumno().getNombre().compareToIgnoreCase(matricula2.getAlumno().getNombre());
                    }
                    return comparador;
                }
            });
            if (matriculas != null) {
                for (Matricula matricula : matriculas) {
                    System.out.println(matricula);
                }
            }


        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void mostrarMatriculasPorCursoAcademico() {
        try {
            System.out.println("Indique el curso academico ");
            System.out.println("Tiene que tener este formato YY-YY");
            String cursoAcademmico = Entrada.cadena();
            List<Matricula> matriculas = controlador.getMatriculas(cursoAcademmico);
            if (matriculas == null) {
                System.out.println("no hay matriculas con ese curso academico");
            } else matriculas.sort(new Comparator<Matricula>() {
                public int compare(Matricula matricula1, Matricula matricula2) {
                    int comparador = -matricula1.getFechaMatriculacion().compareTo(matricula2.getFechaMatriculacion());
                    if (comparador == 0) {
                        comparador = matricula1.getAlumno().getNombre().compareToIgnoreCase(matricula2.getAlumno().getNombre());
                    }
                    return comparador;
                }
            });
            if (matriculas != null)
                for (Matricula matricula : matriculas) {
                    System.out.println(matricula);
                }
        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {
            System.out.println("ERROR: No se pudo mostrar las matrículas por curso.");
        }
    }
}
