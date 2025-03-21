package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import java.sql.*;
import java.util.ArrayList;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;

public class Alumnos implements IAlumnos {

    private Connection conexion;
    private static Alumnos instancia;


    public Alumnos() {
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
        conexion = MySQL.establecerConexion();
    }

    @Override
    public void terminar() {
        MySQL.cerrarConexion();
    }

    @Override
    public ArrayList<Alumno> get() throws SQLException {
        ArrayList<Alumno> copiaProfunda = new ArrayList<>();
        String query = """ 
                SELECT nombre, dni, telefono, correo , fechaNacimiento
                FROM alumno
                """;
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        while (resultado.next()) {
            Alumno alumno = new Alumno(
                    resultado.getString("nombre")
                    , resultado.getString("dni")
                    , resultado.getString("telefono")
                    , resultado.getString("correo")
                    , resultado.getDate("fechaNacimiento").toLocalDate());
            copiaProfunda.add(alumno);
        }
        return copiaProfunda;
    }

    @Override
    public int getTamano() throws SQLException {
        String query = """
                SELECT COUNT(dni) as "Total Alumnos"
                FROM alumno
                """;
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        return resultado.getInt(1);
    }

    @Override
    public void insertar(Alumno alumno) throws OperationNotSupportedException, SQLException {
        if (buscar(alumno) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese dni.");
        }
        String query = """
                INSERT INTO alumno (nombre, telefono, correo, dni, fechaNacimiento)
                VALUES (?, ?, ?, ?, ?)
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setString(1, alumno.getNombre());
        preStatement.setString(2, alumno.getTelefono());
        preStatement.setString(3, alumno.getCorreo());
        preStatement.setString(4, alumno.getDni());
        preStatement.setDate(5, java.sql.Date.valueOf(alumno.getFechaNacimiento()));
        preStatement.executeUpdate();

    }

    @Override
    public Alumno buscar(Alumno alumno) throws SQLException {
        String query = """
                SELECT nombre, dni, telefono, correo, fechaNacimiento
                FROM alumno
                WHERE dni = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setString(1, alumno.getDni());
        ResultSet resultado = preStatement.executeQuery();
        if (resultado.next()) {
            return new Alumno(
                    resultado.getString("nombre")
                    , resultado.getString("dni")
                    , resultado.getString("telefono")
                    , resultado.getString("correo")
                    , resultado.getDate("fechaNacimiento").toLocalDate());
        }
        return null;
    }

    @Override
    public void borrar(Alumno alumno) throws OperationNotSupportedException, SQLException {

        if (buscar(alumno) == null) {
            throw new OperationNotSupportedException("ERROR: No existe un alumno con ese dni.");
        }
        String query = """
                DELETE FROM alumno
                WHERE dni = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setString(1, alumno.getDni().toUpperCase());
        preStatement.executeUpdate();
    }


}
