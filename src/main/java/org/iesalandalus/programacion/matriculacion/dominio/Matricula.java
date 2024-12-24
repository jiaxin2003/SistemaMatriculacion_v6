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
    private String ER_CURSO_ACADEMICO;
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
        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(new Asignatura[matricula.getColeccionAsignaturas().length]);
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
        for (Asignatura coleccionAsignatura : coleccionAsignaturas) {
            asignaturas.append(coleccionAsignatura.getNombre()).append(", ");
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
        this.fechaAnulacion = fechaAnulacion;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        this.cursoAcademico = cursoAcademico;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Asignatura[] getColeccionAsignaturas() {
        return coleccionAsignaturas;
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) {
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
        return idMatricula == matricula.idMatricula && Objects.equals(cursoAcademico, matricula.cursoAcademico) && Objects.equals(fechaMatriculacion, matricula.fechaMatriculacion) && Objects.equals(fechaAnulacion, matricula.fechaAnulacion) && Objects.deepEquals(coleccionAsignaturas, matricula.coleccionAsignaturas);
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


