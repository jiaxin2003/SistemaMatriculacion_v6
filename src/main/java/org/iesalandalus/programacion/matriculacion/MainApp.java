package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;

import org.iesalandalus.programacion.matriculacion.vista.Vista;


public class MainApp {

    public static void main(String[] args) {

        Modelo modelo = procesarArgumentosFuenteDatos(args);
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista, modelo);
        modelo.comenzar();
        controlador.comenzar();
        vista.comenzar();
    }

    private static Modelo procesarArgumentosFuenteDatos(String[] args) {
        if (args.length == 0) {
            System.out.println("Fuente de Datos en Memoria");
            return new Modelo(FactoriaFuenteDatos.MEMORIA);
        } else {
            System.out.println("Fuente de Datos MySQL");
            if (args[0].equalsIgnoreCase("-fdmysql")) {
                return new Modelo(FactoriaFuenteDatos.MySQL);
            } else if (args[0].equalsIgnoreCase("-fdmemoria")) {
                System.out.println("Fuente de Datos en Memoria");
                return new Modelo(FactoriaFuenteDatos.MEMORIA);
            } else {
                System.out.println("Fuente de Datos no Reconocida");
                return new Modelo(FactoriaFuenteDatos.MEMORIA);
            }
        }
    }
}

