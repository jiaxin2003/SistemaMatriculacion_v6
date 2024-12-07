package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;

public class MainApp {
    public static final int CAPACIDAD=3;

    public static void main(String[] args) {

        Alumnos alumnos = new Alumnos(CAPACIDAD);

        System.out.println("Hasta luego!!!!");
    }


}
