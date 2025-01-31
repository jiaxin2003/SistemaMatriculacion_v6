package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;

public class Matriculas {
    private final ArrayList<Matricula> coleccionMatriculas;

    public Matriculas() {
        this.coleccionMatriculas = new ArrayList<>();
    }

    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() throws OperationNotSupportedException {
        Matricula[] copia = new Matricula[this.coleccionMatriculas.size()];
        for (int i = 0; i < coleccionMatriculas.size(); i++) {
            copia[i] = new Matricula(this.coleccionMatriculas.get(i));
        }
        return copia;
    }


    public int getTamano() {
        return this.coleccionMatriculas.size();
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }
        this.coleccionMatriculas.add(new Matricula(matricula));
    }


    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: La matricula no puede ser nulo.");
        }
        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            return null;
        }
        return new Matricula(this.coleccionMatriculas.get(indice));

    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        this.coleccionMatriculas.remove(indice);
    }


    public Matricula[] get(Alumno alumno) throws OperationNotSupportedException {
        ArrayList<Matricula> tempAlumno = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                tempAlumno.add(new Matricula(matricula));
            }
        }
        Matricula[] matriculasAlumnos = new Matricula[tempAlumno.size()];
        return tempAlumno.toArray(matriculasAlumnos);
    }

    public Matricula[] get(String cursoAcademico) throws OperationNotSupportedException {
        ArrayList<Matricula> tempCurso = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getCursoAcademico().equals(cursoAcademico)) {
                tempCurso.add(new Matricula(matricula));
            }
        }
        Matricula[] matriculasCursoAcademico = new Matricula[tempCurso.size()];
        return tempCurso.toArray(matriculasCursoAcademico);
    }


    public Matricula[] get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        ArrayList<Matricula> tempCiclo = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null) {
                for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {
                    if (asignatura != null && asignatura.getCicloFormativo().equals(cicloFormativo)) {
                        tempCiclo.add(matricula);
                        break;
                    }
                }
            }
        }
        Matricula[] matriculasCicloFormativo = new Matricula[tempCiclo.size()];
        return tempCiclo.toArray(matriculasCicloFormativo);
    }


}
