package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Matricula {
    public static int MAXIMO_MESES_ANTERIOR_ANULACION = 6;
    public static int MAXIMO_DIAS_ANTERIOR_MATRICULA = 15;
    public static int MAXIMO_NUMERO_HORAS_MATRICULA = 1000;
    public static int MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA = 10;
    private static final String ER_CURSO_ACADEMICO = "^\\d{2}-\\d{2}$";
    public static String FORMATO_FECHA = "dd/MM/yyyy";
    private int idMatricula;
    private String cursoAcademico;
    private LocalDate fechaMatriculacion;
    private LocalDate fechaAnulacion;
    private ArrayList<Asignatura> coleccionAsignaturas;
    private Alumno alumno;

    public Matricula(int idMatricula, String cursoAcademico, LocalDate fechaMatriculacion, Alumno alumno, Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno de una matrícula no puede ser nulo.");
        }
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }
        if (idMatricula <= 0) {
            throw new IllegalArgumentException("ERROR: El identificador de una matrícula no puede ser menor o igual a 0.");
        }

        setIdMatricula(idMatricula);
        setCursoAcademico(cursoAcademico);
        setFechaMatriculacion(fechaMatriculacion);
        setAlumno(alumno);
        setColeccionAsignaturas(coleccionAsignaturas);
    }

    public Matricula(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No es posible copiar una matrícula nula.");
        }
        setIdMatricula(matricula.getIdMatricula());
        setCursoAcademico(matricula.getCursoAcademico());
        setFechaMatriculacion(matricula.getFechaMatriculacion());
        setAlumno(matricula.getAlumno());
        setColeccionAsignaturas(matricula.getColeccionAsignaturas());
        if (matricula.getFechaAnulacion() != null) {
            setFechaAnulacion(matricula.getFechaAnulacion());
        }
    }


    private boolean superaMaximoNumeroHorasMatricula(Asignatura[] asignaturasMatricula) {
        int horasMatriculadas = 0;
        for (Asignatura coleccionAsignatura : asignaturasMatricula) {
            if (coleccionAsignatura != null) {
                horasMatriculadas += coleccionAsignatura.getHorasAnuales();
            }
        }
        return horasMatriculadas > MAXIMO_NUMERO_HORAS_MATRICULA;
    }

    private String asignaturasMatricula() {
        String asignaturas="";
        for (int i = 0; i < coleccionAsignaturas.size(); i++) {
            if (coleccionAsignaturas.get(i) != null) {
                asignaturas += coleccionAsignaturas.get(i).getNombre();
            };
            if (i < coleccionAsignaturas.size() - 1) {
               asignaturas+=(", ");
            }
        }
        return asignaturas;
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

        if (fechaAnulacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser posterior a hoy.");
        }
        if (fechaAnulacion.isBefore(fechaMatriculacion)) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación no puede ser anterior a la fecha de matriculación.");
        }
        if (fechaAnulacion.isBefore(LocalDate.now().minusMonths(MAXIMO_MESES_ANTERIOR_ANULACION))) {
            throw new IllegalArgumentException("ERROR: La fecha de anulación de una matrícula no puede ser anterior a " + MAXIMO_MESES_ANTERIOR_ANULACION + " meses.");
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null) {
            throw new NullPointerException("ERROR: La fecha de matriculación de una mátricula no puede ser nula.");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser posterior a hoy.");
        }
        if (fechaMatriculacion.isBefore(LocalDate.now().minusDays(MAXIMO_DIAS_ANTERIOR_MATRICULA))) {
            throw new IllegalArgumentException("ERROR: La fecha de matriculación no puede ser anterior a " + MAXIMO_DIAS_ANTERIOR_MATRICULA + " días.");
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
        if (cursoAcademico.isBlank() || cursoAcademico.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El curso académico de una matrícula no puede estar vacío.");
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
        Asignatura[] copiaAsignaturas = new Asignatura[this.coleccionAsignaturas.size()];
        return this.coleccionAsignaturas.toArray(copiaAsignaturas);
    }

    public void setColeccionAsignaturas(Asignatura[] coleccionAsignaturas) throws OperationNotSupportedException {
        if (coleccionAsignaturas == null) {
            throw new NullPointerException("ERROR: La colección de asignaturas de una matrícula no puede ser nula.");
        }
        if (coleccionAsignaturas.length > MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA) {
            throw new OperationNotSupportedException("ERROR: La colección de asignaturas de una matrícula no puede tener más de " + MAXIMO_NUMERO_ASIGNATURAS_POR_MATRICULA + " asignaturas.");
        }
        if (superaMaximoNumeroHorasMatricula(coleccionAsignaturas)) {
            throw new OperationNotSupportedException("ERROR: No se puede realizar la matrícula ya que supera el máximo de horas permitidas (" + Matricula.MAXIMO_NUMERO_HORAS_MATRICULA + " horas).");
        }
        this.coleccionAsignaturas = new ArrayList<>();
        for (Asignatura asignatura : coleccionAsignaturas) {
            if (asignatura == null) continue;
            this.coleccionAsignaturas.add(new Asignatura(asignatura));
        }
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
        return Objects.hash(idMatricula, cursoAcademico, fechaMatriculacion, fechaAnulacion);
    }

    @Override
    public String toString() {
        return "Matricula" +
                "idMatricula=" + idMatricula +
                ", cursoAcademico='" + cursoAcademico + '\'' +
                ", fechaMatriculacion=" + fechaMatriculacion +
                ", fechaAnulacion=" + fechaAnulacion +
                ", coleccionAsignaturas=" + coleccionAsignaturas +
                ", alumno=" + alumno.imprimir();
    }
}


