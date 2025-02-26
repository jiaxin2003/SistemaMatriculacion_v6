package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;

public class Alumnos implements IAlumnos {
    private final ArrayList<Alumno> coleccionAlumnos;
    private Connection conexion;
    private static Alumnos instancia;


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
        conexion = MySQL.establecerConexion();
    }

    @Override
    public void terminar() {
        MySQL.cerrarConexion();
    }

    public ArrayList<Alumno> get() throws SQLException {
        ArrayList<Alumno> copiaProfunda = new ArrayList<>();
        String query = """ 
                SELECT nombre
                      , dni
                      , telefono 
                      , email
                      , fechaNacimiento 
                      FROM alumnos""";
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        while (resultado.next()) {
            Alumno alumno = new Alumno(
                      resultado.getString("nombre")
                    , resultado.getString("dni")
                    , resultado.getString("telefono")
                    , resultado.getString("email")
                    , resultado.getDate("fechaNacimiento").toLocalDate());
            copiaProfunda.add(alumno);
        }
        return copiaProfunda;
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
    }


}
