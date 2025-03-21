package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;


public class Asignaturas implements IAsignaturas {

    private Connection conexion;
    private static Asignaturas instancia = null;

    public Asignaturas() {
        comenzar();
    }

    @Override
    public void comenzar() {
        conexion = MySQL.establecerConexion();
    }

    @Override
    public void terminar() {
        MySQL.cerrarConexion();
    }

    public static Asignaturas getInstancia() {
        if (instancia == null) {
            instancia = new Asignaturas();
        }
        return instancia;
    }

    public Curso getCurso(String curso) {
        return Curso.valueOf(curso.toUpperCase());
    }

    public EspecialidadProfesorado getEspecialidadProfesorado(String especialidadProfesorado) {
        return EspecialidadProfesorado.valueOf(especialidadProfesorado.toUpperCase());
    }

    public ArrayList<Asignatura> get() throws SQLException {
        ArrayList<Asignatura> copiaProfunda = new ArrayList<>();
        String query = """
                SELECT a.codigo
                , a.nombre
                , a.horasAnuales
                , a.curso
                , a.horasDesdoble
                , a.especialidadProfesorado
                , a.codigoCicloFormativo
                FROM asignatura a
                ORDER BY a.nombre
                """;
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        while (resultado.next()) {
            CicloFormativo codigoCicloFormativo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(resultado.getInt("codigoCicloFormativo"), "ficticio", new GradoE("gradoe", 1, 1), "ficticio", 1));
            Asignatura asignatura = new Asignatura(
                    resultado.getString("codigo")
                    , resultado.getString("nombre")
                    , resultado.getInt("horasAnuales")
                    , getCurso(resultado.getString("curso"))
                    , resultado.getInt("horasDesdoble")
                    , getEspecialidadProfesorado(resultado.getString("especialidadProfesorado"))
                    , codigoCicloFormativo);
            copiaProfunda.add(asignatura);
        }
        return copiaProfunda;
    }


    public int getTamano() throws SQLException {
        String query = """
                SELECT COUNT(1) as "Total Asignaturas"
                FROM asignatura
                """;
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        return resultado.getInt(1);
    }

    public void insertar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        if (buscar(asignatura) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una asignatura con ese codigo.");
        }
        String query = """
                INSERT INTO asignatura (codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, codigoCicloFormativo)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setString(1, asignatura.getCodigo());
        preStatement.setString(2, asignatura.getNombre());
        preStatement.setInt(3, asignatura.getHorasAnuales());
        preStatement.setString(4, asignatura.getCurso().name().toLowerCase());
        preStatement.setInt(5, asignatura.getHorasDesdoble());
        preStatement.setString(6, asignatura.getEspecialidadProfesorado().name().toLowerCase());
        preStatement.setInt(7, asignatura.getCicloFormativo().getCodigo());
        preStatement.executeUpdate();
    }

    public Asignatura buscar(Asignatura asignatura) throws SQLException {
        String query = """
                SELECT codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, codigoCicloFormativo
                FROM asignatura
                WHERE codigo = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setString(1, asignatura.getCodigo());
        ResultSet resultado = preStatement.executeQuery();
        if (resultado.next()) {
            CicloFormativo cicloFormativo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(resultado.getInt("codigoCicloFormativo"), "ficticio", new GradoE("gradoe", 1, 1), "ficticio", 1));
            return new Asignatura(
                    resultado.getString("codigo")
                    , resultado.getString("nombre")
                    , resultado.getInt("horasAnuales")
                    , getCurso(resultado.getString("curso"))
                    , resultado.getInt("horasDesdoble")
                    , getEspecialidadProfesorado(resultado.getString("especialidadProfesorado"))
                    , cicloFormativo);
        }
        return null;
    }


    public void borrar(Asignatura asignatura) throws OperationNotSupportedException, SQLException {
        if (buscar(asignatura) == null) {
            throw new OperationNotSupportedException("ERROR: No se puede borrar una asignatura nula.");
        }
        String query = """
                DELETE FROM asignatura
                WHERE codigo = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setString(1, asignatura.getCodigo());
        preStatement.executeUpdate();
    }


}