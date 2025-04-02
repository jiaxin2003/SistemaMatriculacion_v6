package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;

import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.vista.grafica.LanzadoraVentanaPrincipal;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.texto.VistaTexto;

import static javafx.application.Application.launch;


public class MainApp {

    public static void main(String[] args) {
        LanzadoraVentanaPrincipal.main(args);

        Modelo modelo = procesarArgumentosFuenteDatos(args);
        VistaTexto vista = new VistaTexto();
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
    private Vista procesarArgumentosVista(String[] args) {
        if (args.length == 0) {
            System.out.println("Vista en Consola");
            return null;
            //return new VistaTexto();
        } else {
                System.out.println("Vista en Grafica");
            return new VistaGrafica();
        }
    }
}

