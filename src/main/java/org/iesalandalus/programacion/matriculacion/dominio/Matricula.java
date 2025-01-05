package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public class Matricula {
    public static int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    private static final String ER_CURSO_ACADEMICO = "^\\d{4}/\\d{4}$";
    public static String FORMATO_FECHA = "dd/MM/yyyy";
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private Asignatura[] coleccionAsignaturas;
    private Alumno alumno;

    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) {
        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(new Asignatura[coleccionAsignaturas.length]);
    }

    public Matricula(Matricula matricula) {
        setIdMatricula(matricula.idMatricula);
        setCursoAcademico(matricula.cursoAcademico);
        setFechaMatriculacion(matricula.fechaMatriculacion);
        setFechaAnulacion(matricula.fechaAnulacion);
        setAlumno(matricula.alumno);
        setColeccionAsignaturas(new Asignatura[matricula.coleccionAsignaturas.length]);
    }


    private boolean superaMaximoNumeroHorasMatricula() {
        int horasMatriculadas = 0;
        for (Asignatura coleccionAsignatura : coleccionAsignaturas) {
            horasMatriculadas += coleccionAsignatura.getHorasAnuales();
        }
        return horasMatriculadas > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    private String asignaturasMatricula() {
        StringBuilder asignaturas = new StringBuilder();
        for (int i = 0; i < coleccionAsignaturas.length; i++) {
            asignaturas.append(coleccionAsignaturas[i].getNombre());
            if (i < coleccionAsignaturas.length - 1) {
                asignaturas.append(", ");
            }
        }
        return asignaturas.toString();
    }

    public String imprimir() {

        return String.format("idMatricula=%d, curso académico=%s, fecha matriculación=%s, alumno=%s, Asignaturas={ %s}",
                getIdMatricula(), getCursoAcademico(),
                getFechaMatriculacion().format(DateTimeFormatter.ofPattern(FORMATO_FECHA)),
                getAlumno().imprimir(), asignaturasMatricula());
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion == null) {
            throw new NullPointerException("ERROR: La fecha de anulación de una matrícula no puede ser nula.");
        }
        if (fechaAnulacion.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null) {
            throw new NullPointerException("ERROR: La fecha de matriculación de una matrícula no puede ser nula.");
        }
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null) {
            throw new NullPointerException("ERROR: El curso académico de una matrícula no puede ser nulo.");
        }
        if (!cursoAcademico.matches(ER_CURSO_ACADEMICO)) {
            throw new IllegalArgumentException("ERROR: El formato del curso académico no es correcto.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        if (idMatricula < 0) {
            throw new IllegalArgumentException("El id de la matricula no puede ser negativo.");
        }
        this.idMatricula = idMatricula;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La colección de asignaturas de una matrícula no puede ser nula.");
        }
        if (coleccionAsignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new IllegalArgumentException("ERROR: La colección de asignaturas de una matrícula no puede tener más de " + MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + " asignaturas.");
        }
        this.coleccionAsignaturas = coleccionAsignaturas;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula matricula)) return false;
        return Objects.equals(cursoAcademico, matricula.cursoAcademico) && Objects.equals(fechaMatriculacion, matricula.fechaMatriculacion) && Objects.equals(fechaAnulacion, matricula.fechaAnulacion) && Objects.deepEquals(coleccionAsignaturas, matricula.coleccionAsignaturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatricula, cursoAcademico, fechaMatriculacion, fechaAnulacion, Arrays.hashCode(coleccionAsignaturas));
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "idMatricula=" + idMatricula +
                ", cursoAcademico='" + cursoAcademico + '\'' +
                ", fechaMatriculacion=" + fechaMatriculacion +
                ", fechaAnulacion=" + fechaAnulacion +
                ", coleccionAsignaturas=" + Arrays.toString(coleccionAsignaturas) +
                ", alumno=" + alumno +
                '}';
    }
}


