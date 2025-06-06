package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;


public class Asignaturas implements IAsignaturas {
    private final ArrayList<Asignatura> coleccionAsignaturas;
    private static Asignaturas instancia = null;

    private static final String RUTA_FICHERO = "datos/asignaturas.xml";
    private static final String RAIZ = "Asignaturas";
    private static final String ASIGNATURA = "Asignatura";
    private static final String NOMBRE = "Nombre";
    private static final String CURSO = "Curso";
    private static final String CODIGO = "Codigo";
    private static final String ESPECIALIDAD_PROFESORADO = "EspecialidadProfesorado";
    private static final String CICLO_FORMATIVO = "CicloFormativo";
    private static final String HORAS = "Horas";
    private static final String ANUALES = "Anuales";
    private static final String DESDOBLE = "Desdobles";


    public Asignaturas() {
        this.coleccionAsignaturas = new ArrayList<>();
        comenzar();
    }

    public static Asignaturas getInstancia() {
        if (instancia == null) {
            instancia = new Asignaturas();
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
        NodeList asignaturas;
        Node asignaturaNodo;
        try {
            document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            if (document == null) {
                document = UtilidadesXML.crearDomVacio(RAIZ);
            }
            document.getDocumentElement().normalize();

            asignaturas = document.getElementsByTagName(ASIGNATURA);

            for (int i = 0; i < asignaturas.getLength(); i++) {
                asignaturaNodo = asignaturas.item(i);
                if (asignaturaNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) asignaturaNodo;
                    Asignatura asignatura = elementToAsignatura(elemento);
                    coleccionAsignaturas.add(asignatura);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Asignatura elementToAsignatura(Element elemento) {
        if (elemento == null) {
            throw new NullPointerException("ERROR: El elemento no puede ser nulo.");
        }


        String codigo = elemento.getAttribute(CODIGO);
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("ERROR: El atributo 'Codigo' es obligatorio.");
        }

        Node nombreNodo = elemento.getElementsByTagName(NOMBRE).item(0);
        if (nombreNodo == null) {
            throw new IllegalArgumentException("ERROR: Falta el nodo <Nombre>.");
        }
        String nombre = nombreNodo.getTextContent().trim();

        Node cursoNodo = elemento.getElementsByTagName(CURSO).item(0);
        if (cursoNodo == null) {
            throw new IllegalArgumentException("ERROR: Falta el nodo <Curso>.");
        }
        String cursoTexto = cursoNodo.getTextContent().trim();
        Curso curso;
        if (cursoTexto.equalsIgnoreCase("Primero")) {
            curso = Curso.PRIMERO;
        } else if (cursoTexto.equalsIgnoreCase("Segundo")) {
            curso = Curso.SEGUNDO;
        } else {
            throw new IllegalArgumentException("Curso no válido: " + cursoTexto);
        }

        Node especialidadNodo = elemento.getElementsByTagName(ESPECIALIDAD_PROFESORADO).item(0);
        if (especialidadNodo == null) {
            throw new IllegalArgumentException("ERROR: Falta el nodo <EspecialidadProfesorado>.");
        }
        String especialidadTexto = especialidadNodo.getTextContent().trim();
        EspecialidadProfesorado especialidad;
        if (especialidadTexto.equalsIgnoreCase("Informatica")) {
            especialidad = EspecialidadProfesorado.INFORMATICA;
        } else if (especialidadTexto.equalsIgnoreCase("Fol")) {
            especialidad = EspecialidadProfesorado.FOL;
        } else if (especialidadTexto.equalsIgnoreCase("Sistemas")) {
            especialidad = EspecialidadProfesorado.SISTEMAS;
        } else {
            throw new IllegalArgumentException("Especialidad no válida: " + especialidadTexto);
        }

        Node cicloNodo = elemento.getElementsByTagName(CICLO_FORMATIVO).item(0);
        if (cicloNodo == null) {
            throw new IllegalArgumentException("ERROR: Falta el nodo <CicloFormativo>.");
        }
        int codigoCiclo = Integer.parseInt(cicloNodo.getTextContent().trim());
        CicloFormativo cicloEncontrado = null;
        for (CicloFormativo ciclo : CiclosFormativos.getInstancia().get()) {
            if (ciclo.getCodigo() == codigoCiclo) {
                cicloEncontrado = ciclo;
                break;
            }
        }
        if (cicloEncontrado == null) {
            throw new IllegalArgumentException("ERROR: No se encuentra el ciclo formativo con código " + codigoCiclo);
        }

        Element horasElemento = (Element) elemento.getElementsByTagName(HORAS).item(0);
        if (horasElemento == null) {
            throw new IllegalArgumentException("ERROR: Falta el nodo <Horas>.");
        }

        Node anualesNodo = horasElemento.getElementsByTagName(ANUALES).item(0);
        if (anualesNodo == null) {
            throw new IllegalArgumentException("ERROR: Falta el nodo <Anuales>.");
        }
        int horasAnuales = Integer.parseInt(anualesNodo.getTextContent().trim());

        Node desdobleNodo = horasElemento.getElementsByTagName(DESDOBLE).item(0);
        if (desdobleNodo == null) {
            throw new IllegalArgumentException("ERROR: Falta el nodo <Desdoble>.");
        }
        int horasDesdoble = Integer.parseInt(desdobleNodo.getTextContent().trim());

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidad, cicloEncontrado);
    }


    private void escribirXML() {
        Document document = UtilidadesXML.crearDomVacio(RAIZ);
        for (Asignatura asignatura : coleccionAsignaturas) {
            Element elementoAsignatura = asignaturaToElement(document, asignatura);
            document.getDocumentElement().appendChild(elementoAsignatura);
        }
        UtilidadesXML.domToXml(document, RUTA_FICHERO);
    }

    private Element asignaturaToElement(Document document, Asignatura asignatura) {
        if (asignatura == null || document == null) {
            return null;
        }

        Element elementoAsignatura = document.createElement(ASIGNATURA);
        elementoAsignatura.setAttribute(CODIGO, asignatura.getCodigo());

        Element elementoNombre = document.createElement(NOMBRE);
        elementoNombre.setTextContent(asignatura.getNombre());
        elementoAsignatura.appendChild(elementoNombre);

        Element elementoCurso = document.createElement(CURSO);
        elementoCurso.setTextContent(asignatura.getCurso().toString());
        elementoAsignatura.appendChild(elementoCurso);

        Element elementoEspecialidadProfesorado = document.createElement(ESPECIALIDAD_PROFESORADO);
        elementoEspecialidadProfesorado.setTextContent(asignatura.getEspecialidadProfesorado().toString());
        elementoAsignatura.appendChild(elementoEspecialidadProfesorado);

        Element elementoCicloFormativo = document.createElement(CICLO_FORMATIVO);
        elementoCicloFormativo.setTextContent(String.valueOf(asignatura.getCicloFormativo().getCodigo()));
        elementoAsignatura.appendChild(elementoCicloFormativo);

        Element elementoHoras = document.createElement(HORAS);

        Element elementoAnuales = document.createElement(ANUALES);
        elementoAnuales.setTextContent(String.valueOf(asignatura.getHorasAnuales()));
        elementoHoras.appendChild(elementoAnuales);

        Element elementoDesdoble = document.createElement(DESDOBLE);
        elementoDesdoble.setTextContent(String.valueOf(asignatura.getHorasDesdoble()));
        elementoHoras.appendChild(elementoDesdoble);

        elementoAsignatura.appendChild(elementoHoras);

        return elementoAsignatura;
    }


    public ArrayList<Asignatura> get() {
        return copiaProfundaAsignaturas();
    }

    private ArrayList<Asignatura> copiaProfundaAsignaturas() {
        ArrayList<Asignatura> copia = new ArrayList<>(this.coleccionAsignaturas.size());
        for (Asignatura coleccionAsignatura : this.coleccionAsignaturas) {
            copia.add(new Asignatura(coleccionAsignatura));
        }
        return copia;
    }

    public int getTamano() {
        return this.coleccionAsignaturas.size();
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede insertar una asignatura nula.");
        }
        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese código.");
        }
        this.coleccionAsignaturas.add(new Asignatura(asignatura));
        escribirXML();
    }

    public Asignatura buscar(Asignatura asignatura) {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: La asignatura no puede ser nula.");
        }
        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice == -1) {
            return null;
        }
        return new Asignatura(this.coleccionAsignaturas.get(indice));
    }


    public void borrar(Asignatura asignatura) throws OperationNotSupportedException {
        if (asignatura == null) {
            throw new NullPointerException("ERROR: No se puede borrar una asignatura nula.");
        }
        int indice = this.coleccionAsignaturas.indexOf(asignatura);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna asignatura como la indicada.");
        }
        this.coleccionAsignaturas.remove(indice);
        escribirXML();
    }


}