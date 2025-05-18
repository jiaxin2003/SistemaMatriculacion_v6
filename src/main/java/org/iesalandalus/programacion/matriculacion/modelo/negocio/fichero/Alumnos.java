package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;


public class Alumnos implements IAlumnos {
    private final ArrayList<Alumno> coleccionAlumnos;
    private static Alumnos instancia = null;

    private static final String RUTA_FICHERO = "datos/alumnos.xml";
    private static final String RAIZ = "Alumnos";
    private static final String ALUMNO = "Alumno";
    private static final String DNI = "Dni";
    private static final String NOMBRE = "Nombre";
    private static final String TELEFONO = "Telefono";
    private static final String CORREO = "Correo";
    private static final String FECHA_NACIMIENTO = "FechaNacimiento";


    public Alumnos() {
        this.coleccionAlumnos = new ArrayList<>();
        comenzar();
    }

    public static Alumnos getInstancia() {
        if (instancia == null) {
            instancia = new Alumnos();
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
        NodeList alumnos;
        Node alumnoNodo;
        try {
            document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            if (document == null) {
                document = UtilidadesXML.crearDomVacio(RAIZ);
            }
            document.getDocumentElement().normalize();

            alumnos = document.getElementsByTagName(ALUMNO);

            for (int i = 0; i < alumnos.getLength(); i++) {
                alumnoNodo = alumnos.item(i);
                if (alumnoNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) alumnoNodo;
                    Alumno alumno = elementToAlumno(elemento);
                    coleccionAlumnos.add(alumno);
                }
            }


        } catch (Exception e) {
            e.getMessage();
        }
    }

    private Alumno elementToAlumno(Element elemento) {
        if (elemento == null) {
            return null;
        }

        String dni = elemento.getAttribute(DNI);
        String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
        String telefono = elemento.getElementsByTagName(TELEFONO).item(0).getTextContent();
        String correo = elemento.getElementsByTagName(CORREO).item(0).getTextContent();
        String fechaNacimiento = elemento.getElementsByTagName(FECHA_NACIMIENTO).item(0).getTextContent();
        LocalDate fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));

        Alumno alumno = new Alumno(nombre, dni, telefono, correo, fecha);
        return alumno;
    }

    private void escribirXML() {
        Document documento = UtilidadesXML.crearDomVacio(RAIZ);
        for (Alumno alumno : coleccionAlumnos) {
            Element elementoAlumno = alumnoToElement(documento, alumno);
            documento.getDocumentElement().appendChild(elementoAlumno);
        }
        UtilidadesXML.domToXml(documento, RUTA_FICHERO);
    }

    private Element alumnoToElement(Document documento, Alumno alumno) {
        if (documento == null || alumno == null) {
            return null;
        }

        Element elementoAlumno = documento.createElement(ALUMNO);
        elementoAlumno.setAttribute(DNI, alumno.getDni());

        Element elementoNombre = documento.createElement(NOMBRE);
        elementoNombre.setTextContent(alumno.getNombre());
        elementoAlumno.appendChild(elementoNombre);

        Element elementoTelefono = documento.createElement(TELEFONO);
        elementoTelefono.setTextContent(alumno.getTelefono());
        elementoAlumno.appendChild(elementoTelefono);

        Element elementoCorreo = documento.createElement(CORREO);
        elementoCorreo.setTextContent(alumno.getCorreo());
        elementoAlumno.appendChild(elementoCorreo);

        Element elementoFecha = documento.createElement(FECHA_NACIMIENTO);
        elementoFecha.setTextContent(alumno.getFechaNacimiento().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA)));
        elementoAlumno.appendChild(elementoFecha);
        return elementoAlumno;
    }

    public ArrayList<Alumno> get() {
        return (copiaProfundaAlumnos());
    }

    private ArrayList<Alumno> copiaProfundaAlumnos() {
        ArrayList<Alumno> copia = new ArrayList<>(this.coleccionAlumnos.size());
        for (Alumno alumno : this.coleccionAlumnos) {
            copia.add(new Alumno(alumno));
        }
        return copia;
    }

    public int getTamano() {
        return this.coleccionAlumnos.size();
    }


    public void insertar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
        }
        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        } else {
            this.coleccionAlumnos.add(new Alumno(alumno));
            escribirXML();
        }
    }


    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
        }
        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice == -1) {
            return null;
        }
        return new Alumno(this.coleccionAlumnos.get(indice));
    }


    public void borrar(Alumno alumno) throws OperationNotSupportedException {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No se puede borrar un alumno nulo.");
        }
        int indice = this.coleccionAlumnos.indexOf(alumno);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún alumno como el indicado.");
        }
        this.coleccionAlumnos.remove(indice);
        escribirXML();
    }


}
