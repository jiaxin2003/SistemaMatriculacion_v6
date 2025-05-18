package org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.fichero.utilidades.UtilidadesXML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Date;


public class CiclosFormativos implements ICiclosFormativos {
    private final ArrayList<CicloFormativo> coleccionCiclosFormativos;
    private static CiclosFormativos instancia = null;

    private static final String RUTA_FICHERO = "datos/ciclosFormativos.xml";
    private static final String RAIZ = "CiclosFormativos";
    private static final String CICLO_FORMATIVO = "CicloFormativo";
    private static final String CODIGO = "Codigo";
    private static final String NOMBRE = "Nombre";
    private static final String GRADO = "Grado";
    private static final String TIPO = "Tipo";
    private static final String NOMBRE_GRADO = "Nombre";
    private static final String NUMERO_ANIOS = "NumAnios";
    private static final String MODALIDAD = "Modalidad";
    private static final String NUMERO_EDICIONES = "NumEdiciones";
    private static final String FAMILIA_PROFESIONAL = "FamiliaProfesional";
    private static final String HORAS = "Horas";




    public CiclosFormativos() {
        this.coleccionCiclosFormativos = new ArrayList<>();
        comenzar();
    }

    public static CiclosFormativos getInstancia() {
        if (instancia == null) {
            instancia = new CiclosFormativos();
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
        NodeList ciclosFormativos;
        Node cicloFormativoNodo;
        try {
            document = UtilidadesXML.xmlToDom(RUTA_FICHERO);
            if (document == null) {
                document = UtilidadesXML.crearDomVacio(RAIZ);
            }
            document.getDocumentElement().normalize();
            ciclosFormativos = document.getElementsByTagName(CICLO_FORMATIVO);
            for (int i = 0; i < ciclosFormativos.getLength(); i++) {
                cicloFormativoNodo = ciclosFormativos.item(i);
                if (cicloFormativoNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) cicloFormativoNodo;
                    CicloFormativo cicloFormativo = elementToCicloFormativo(elemento);
                    coleccionCiclosFormativos.add(cicloFormativo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CicloFormativo elementToCicloFormativo(Element elemento) {
        if (elemento == null) {
            throw new NullPointerException("ERROR: El elemento no puede ser nulo.");
        }

        Grado grado = null;

        String codigo = elemento.getAttribute(CODIGO);
        String nombre = elemento.getElementsByTagName(NOMBRE).item(0).getTextContent();
        String familiaProfesional = elemento.getElementsByTagName(FAMILIA_PROFESIONAL).item(0).getTextContent();
        String horas = elemento.getElementsByTagName(HORAS).item(0).getTextContent();

        Element elementoGrado = (Element) elemento.getElementsByTagName("Grado").item(0);
        String tipoGrado = elementoGrado.getAttribute(TIPO);

        String nombreGrado = elementoGrado.getElementsByTagName(NOMBRE_GRADO).item(0).getTextContent();
        String numAnios = elementoGrado.getElementsByTagName(NUMERO_ANIOS).item(0).getTextContent();

        if (tipoGrado.equals("GradoD")) {
            String modalidad = elementoGrado.getElementsByTagName(MODALIDAD).item(0).getTextContent().trim();
            grado = new GradoD(nombreGrado, Integer.parseInt(numAnios), Modalidad.valueOf(modalidad.toUpperCase()));
        } else if (tipoGrado.equals("GradoE")) {
            String numEdiciones = elementoGrado.getElementsByTagName(NUMERO_EDICIONES).item(0).getTextContent();
            grado = new GradoE(nombreGrado, Integer.parseInt(numAnios), Integer.parseInt(numEdiciones));
        }

        return new CicloFormativo(Integer.parseInt(codigo), familiaProfesional, grado, nombre, Integer.parseInt(horas));
    }



    private void escribirXML() {
        Document document = UtilidadesXML.crearDomVacio(RAIZ);
        for (CicloFormativo cicloFormativo : coleccionCiclosFormativos) {
            Element cicloFormativoElemento = cicloFormativoToElement(document, cicloFormativo);
            document.getDocumentElement().appendChild(cicloFormativoElemento);
        }
        UtilidadesXML.domToXml(document, RUTA_FICHERO);
    }

    private Element cicloFormativoToElement(Document document, CicloFormativo cicloFormativo) {
        if (cicloFormativo == null || document == null) {
            throw new NullPointerException("ERROR: El ciclo formativo o el documento no pueden ser nulos.");
        }

        Element elementoCicloFormativo = document.createElement(CICLO_FORMATIVO);
        elementoCicloFormativo.setAttribute(CODIGO, String.valueOf(cicloFormativo.getCodigo()));

        // <Nombre>
        Element elementoNombre = document.createElement(NOMBRE);
        elementoNombre.setTextContent(cicloFormativo.getNombre());
        elementoCicloFormativo.appendChild(elementoNombre);

        // <FamiliaProfesional>
        Element elementoFamilia = document.createElement(FAMILIA_PROFESIONAL);
        elementoFamilia.setTextContent(cicloFormativo.getFamiliaProfesional());
        elementoCicloFormativo.appendChild(elementoFamilia);

        // <Horas>
        Element elementoHoras = document.createElement(HORAS);
        elementoHoras.setTextContent(String.valueOf(cicloFormativo.getHoras()));
        elementoCicloFormativo.appendChild(elementoHoras);

        // <Grado Tipo="...">
        Element elementoGrado = document.createElement(GRADO);
        elementoGrado.setAttribute(TIPO, cicloFormativo.getGrado().getClass().getSimpleName());

        // Subnodos dentro de <Grado>
        Element elementoNombreGrado = document.createElement(NOMBRE);
        elementoNombreGrado.setTextContent(cicloFormativo.getGrado().getNombre());
        elementoGrado.appendChild(elementoNombreGrado);

        Element elementoNumAnios = document.createElement("NumAnios");
        elementoNumAnios.setTextContent(String.valueOf(cicloFormativo.getGrado().getNumAnios()));
        elementoGrado.appendChild(elementoNumAnios);

        if (cicloFormativo.getGrado() instanceof GradoD) {
            GradoD gradoD = (GradoD) cicloFormativo.getGrado();
            Element elementoModalidad = document.createElement("Modalidad");
            elementoModalidad.setTextContent(String.valueOf(gradoD.getModalidad()));
            elementoGrado.appendChild(elementoModalidad);
        } else if (cicloFormativo.getGrado() instanceof GradoE) {
            GradoE gradoE = (GradoE) cicloFormativo.getGrado();
            Element elementoNumEdiciones = document.createElement("NumEdiciones");
            elementoNumEdiciones.setTextContent(String.valueOf(gradoE.getNumEdiciones()));
            elementoGrado.appendChild(elementoNumEdiciones);
        }

        elementoCicloFormativo.appendChild(elementoGrado);

        return elementoCicloFormativo;
    }

    public ArrayList<CicloFormativo> get() {
        return copiaProfundaCiclosFormativos();
    }

    private ArrayList<CicloFormativo> copiaProfundaCiclosFormativos() {
        ArrayList<CicloFormativo> copia = new ArrayList<>(this.coleccionCiclosFormativos.size());
        for (CicloFormativo cicloFormativo : this.coleccionCiclosFormativos) {
            copia.add(new CicloFormativo(cicloFormativo));
        }
        return copia;
    }

    public int getTamano() {
        return this.coleccionCiclosFormativos.size();
    }

    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede insertar un ciclo formativo nulo.");
        }
        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);

        if (indice != -1) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese código.");
        }
        this.coleccionCiclosFormativos.add(new CicloFormativo(cicloFormativo));
        escribirXML();
    }

    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo no puede ser nulo.");
        }
        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice == -1) {
            throw new NullPointerException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        return new CicloFormativo(this.coleccionCiclosFormativos.get(indice));
    }

    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: No se puede borrar un ciclo formativo nulo.");
        }
        int indice = this.coleccionCiclosFormativos.indexOf(cicloFormativo);
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ningún ciclo formativo como el indicado.");
        }
        this.coleccionCiclosFormativos.remove(indice);
        escribirXML();
    }

}
