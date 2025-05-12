package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.iesalandalus.programacion.matriculacion.vista.grafica.controladores.ControladorVentanaAsignatura;
import org.iesalandalus.programacion.matriculacion.vista.texto.Consola;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Matriculas implements IMatriculas {
    private final ArrayList<Matricula> coleccionMatriculas;
    private static Matriculas instancia = null;

    private static final String RUTA_FICHERO = "datos/matriculas.xml";
    private static final String RAIZ = "Matriculas";
    private static final String MATRICULA = "Matricula";
    private static final String CURSO_ACADEMICO = "CursoAcademico";
    private static final String ID_MATRICULA = "Id";
    private static final String ALUMNO = "Alumno";
    private static final String ASIGNATURAS = "Asignaturas";
    private static final String ASIGNATURA = "Asignatura";
    private static final String FECHA_MATRICULACION = "FechaMatriculacion";
    private static final String FECHA_ANULACION = "FechaAnulacion";
    private static final String CODIGO_ASIGNATURA = "Codigo";



    public Matriculas() {
        this.coleccionMatriculas = new ArrayList<>();
        comenzar();
    }

    public static Matriculas getInstancia() {
        if (instancia == null) {
            instancia = new Matriculas();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        leerXML();
    }


    @Override
    public void terminar() {
        escribirXML();
    }


    private void leerXML() {
        Document document;
        NodeList matriculas;
        Node matriculaNodo;
        try {
            document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            if (document == null) {
                document = UtilidadesXML.crearDomVacio(RAIZ);
            }
            document.getDocumentElement().normalize();
            matriculas = document.getElementsByTagName(MATRICULA);
            for (int i = 0; i < matriculas.getLength(); i++) {
                matriculaNodo = matriculas.item(i);
                if (matriculaNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) matriculaNodo;
                    Matricula matricula = elementToMatricula(elemento);
                    coleccionMatriculas.add(matricula);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Matricula elementToMatricula(Element elemento) throws OperationNotSupportedException {

        if (elemento == null) {
            throw new NullPointerException("ERROR: No se puede convertir un elemento nulo en una matrícula.");
        }

        String idMatricula = elemento.getAttribute(ID_MATRICULA);
        String cursoAcademico = elemento.getElementsByTagName(CURSO_ACADEMICO).item(0).getTextContent();
        String dniAlumno = elemento.getAttribute(ALUMNO);
        String fechaMatriculacion = elemento.getElementsByTagName(FECHA_MATRICULACION).item(0).getTextContent();
        String fechaAnulacion = elemento.getElementsByTagName(FECHA_ANULACION).item(0).getTextContent();
        LocalDate fechaMatri = LocalDate.parse(fechaMatriculacion, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
        LocalDate fechaAnu = LocalDate.parse(fechaAnulacion, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));

        Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("null", dniAlumno, "null", "null", LocalDate.now()));
        if (alumno == null) {
            throw new IllegalArgumentException("ERROR: No se encuentra el alumno con DNI: " + dniAlumno);
        }



        ArrayList<Asignatura> asignaturasList = new ArrayList<>();
        NodeList asignaturasXML = ((Element) elemento.getElementsByTagName("Asignaturas").item(0)).getElementsByTagName("Asignatura");
        for (int i = 0; i < asignaturasXML.getLength(); i++) {
            Element asignaturaElement = (Element) asignaturasXML.item(i);
            String codigoAsignatura = asignaturaElement.getAttribute("Codigo");

            CicloFormativo cicloFormativo = new CicloFormativo(7771, "null", new GradoD("null", 2, Modalidad.PRESENCIAL), "null", 120);
            Asignatura asignaturaBuscar = new Asignatura(codigoAsignatura, "null", 200, Curso.PRIMERO, 2, EspecialidadProfesorado.FOL, cicloFormativo);

            Asignatura asignatura = Asignaturas.getInstancia().buscar(asignaturaBuscar);
            if (asignatura != null) {
                asignaturasList.add(asignatura);
            }
        }
        if (fechaAnu != null) {
           Matricula matricula = new Matricula(Integer.parseInt(idMatricula), cursoAcademico, fechaMatri, alumno, asignaturasList);
           matricula.setFechaAnulacion(fechaAnu);

        }

        return new Matricula(Integer.parseInt(idMatricula), cursoAcademico, fechaMatri, alumno, asignaturasList);
    }

    private void escribirXML() {
        Document document = UtilidadesXML.crearDomVacio(RAIZ);
        Element raiz = document.getDocumentElement();
        for (Matricula matricula : coleccionMatriculas) {
            raiz.appendChild(matriculaToElement(document, matricula));
        }
        UtilidadesXML.domToXml(document, RUTA_FICHERO);
    }

    private Element matriculaToElement(Document document, Matricula matricula) {

        Element matriculaElement = document.createElement(MATRICULA);
        matriculaElement.setAttribute(ID_MATRICULA, String.valueOf(matricula.getIdMatricula()));

        Element elemntoCursoAcademico = document.createElement(CURSO_ACADEMICO);
        elemntoCursoAcademico.setTextContent(matricula.getCursoAcademico());
        matriculaElement.appendChild(elemntoCursoAcademico);

        Element elementoAlumno = document.createElement(ALUMNO);
        elementoAlumno.setTextContent(matricula.getAlumno().getDni());
        matriculaElement.appendChild(elementoAlumno);

        Element elementoAsignaturas = document.createElement(ASIGNATURAS);
        for (Asignatura asignatura : matricula.getColeccionAsignaturas()) {
            Element elementoAsignatura = document.createElement(ASIGNATURA);
            elementoAsignatura.setAttribute(CODIGO_ASIGNATURA, asignatura.getCodigo());
            elementoAsignaturas.appendChild(elementoAsignatura);
        }
        matriculaElement.appendChild(elementoAsignaturas);

        Element elementoFechaMatriculacion = document.createElement(FECHA_MATRICULACION);
        elementoFechaMatriculacion.setTextContent(matricula.getFechaMatriculacion().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA)));
        matriculaElement.appendChild(elementoFechaMatriculacion);

        Element elementoFechaAnulacion = document.createElement(FECHA_ANULACION);
        if (matricula.getFechaAnulacion() != null) {
            elementoFechaAnulacion.setTextContent(matricula.getFechaAnulacion().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA)));
        }
        matriculaElement.appendChild(elementoFechaAnulacion);
        return matriculaElement;
    }


    public ArrayList<Matricula> get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }

    private ArrayList<Matricula> copiaProfundaMatriculas() throws OperationNotSupportedException {
        ArrayList<Matricula> copia = new ArrayList<>(this.coleccionMatriculas.size());
        for (Matricula matricula : this.coleccionMatriculas) {
            copia.add(new Matricula(matricula));
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
        return this.coleccionMatriculas.get(indice);

    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }
        int indice = this.coleccionMatriculas.indexOf(matricula);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
        this.coleccionMatriculas.get(indice).setFechaAnulacion(LocalDate.now());
    }


    public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException {
        ArrayList<Matricula> tempAlumno = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getAlumno().equals(alumno)) {
                tempAlumno.add(new Matricula(matricula));
            }
        }
        return tempAlumno;
    }

    public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException {
        ArrayList<Matricula> tempCurso = new ArrayList<>();
        for (Matricula matricula : coleccionMatriculas) {
            if (matricula != null && matricula.getCursoAcademico().equals(cursoAcademico)) {
                tempCurso.add(new Matricula(matricula));
            }
        }

        return tempCurso;
    }


    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
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

        return tempCiclo;
    }


}


