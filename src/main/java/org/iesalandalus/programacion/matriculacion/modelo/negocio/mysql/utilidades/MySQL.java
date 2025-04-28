package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private static final String HOST ="programacion-2025.ccbintxjpoyj.us-east-1.rds.amazonaws.com";
    private static final String ESQUEMA = "sistemamatriculacion";
    private static final String USUARIO = "sistemamatriculacion";
    private static final String CONTRASENA = "sistemamatriculacion-2024";
    private static Connection conexion;


    private MySQL() {
    }

    public static Connection establecerConexion() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://" + HOST + ":3306/" + ESQUEMA;

                conexion = DriverManager.getConnection(url, USUARIO, CONTRASENA);
                System.out.println("Conexión establecida con éxito.");
            } catch (ClassNotFoundException e) {
                System.out.println("Error: No se encontró el driver de MySQL.");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos.");
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión.");
            }
        }
    }
}