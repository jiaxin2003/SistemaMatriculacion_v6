package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;

import org.iesalandalus.programacion.matriculacion.vista.FactoriaVista;
import org.iesalandalus.programacion.matriculacion.vista.Vista;



public class MainApp {

    public static void main(String[] args) {
        Modelo modelo = procesarArgumentosFuenteDatos(args);
        Vista vista = procesarArgumentosVista(args);
        Controlador controlador = new Controlador(vista, modelo);
        modelo.comenzar();
        controlador.comenzar();
        vista.comenzar();
    }

    private static Modelo procesarArgumentosFuenteDatos(String[] args) {
        try {
            for (String arg : args) {
                if (arg.equalsIgnoreCase("-fdmysql")) {
                    System.out.println("Fuente de Datos en MySQL");
                    return new Modelo(FactoriaFuenteDatos.MySQL);
                } else if (arg.equalsIgnoreCase("-fdmemoria")) {
                    System.out.println("Fuente de Datos en Memoria");
                    return new Modelo(FactoriaFuenteDatos.MEMORIA);
                } else if (arg.equalsIgnoreCase("-fdfichero")) {
                    System.out.println("Fuende de Datos en Fichero");
                    return new Modelo(FactoriaFuenteDatos.FICHERO);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Fuente de Datos no Reconocida, Usando Memoria por defecto.");
        return new Modelo(FactoriaFuenteDatos.MEMORIA);

    }

    private static Vista procesarArgumentosVista(String[] args) {

            for (String arg : args) {
                if (arg.equalsIgnoreCase("-vgrafica")) {
                    System.out.println("Vista en Grafica");
                    return FactoriaVista.GRAFICA.crear();
                } else if (arg.equalsIgnoreCase("-vtexto")) {
                    System.out.println("Vista en Texto");
                    return FactoriaVista.TEXTO.crear();
                }
            }
        System.out.println("Vista no Reconocida, Usando Texto por defecto.");
        return FactoriaVista.TEXTO.crear();

    }
}

