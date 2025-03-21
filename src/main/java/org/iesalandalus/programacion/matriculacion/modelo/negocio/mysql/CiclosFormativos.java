package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.util.ArrayList;


public class CiclosFormativos implements ICiclosFormativos {


    private Connection conexion;
    private static CiclosFormativos instancia = null;


    public CiclosFormativos() {
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

    public static CiclosFormativos getInstancia() {
        if (instancia == null) {
            instancia = new CiclosFormativos();
        }
        return instancia;
    }

    public Grado getGrado(String tipoGrado, String nombreGrado, int numAniosGrado, String modalidad, int numEdiciones) {
        Grado grado = null;
        if (tipoGrado.equals("gradod")) {
            Modalidad modalidadGrado = Modalidad.valueOf(modalidad.toUpperCase());
            grado = new GradoD(nombreGrado, numAniosGrado, modalidadGrado);
        } else {
            grado = new GradoE(nombreGrado, numAniosGrado, numEdiciones);
        }
        return grado;
    }

    public ArrayList<CicloFormativo> get() throws SQLException {
        ArrayList<CicloFormativo> copiaProfunda = new ArrayList<>();
        String query = """
                SELECT c.codigo
                , c.familiaProfesional
                , c.grado
                , c.nombre
                , c.horas
                , c.nombreGrado
                , c.numAniosGrado
                , c.modalidad
                , c.numEdiciones
                FROM cicloFormativo c
                ORDER BY c.nombre""";
        PreparedStatement preStatement = conexion.prepareStatement(query);
        ResultSet resultado = preStatement.executeQuery();
        while (resultado.next()) {
            Grado grado = getGrado(resultado.getString("grado")
                    , resultado.getString("nombreGrado")
                    , resultado.getInt("numAniosGrado")
                    , resultado.getString("modalidad")
                    , resultado.getInt("numEdiciones"));
            CicloFormativo cicloFormativo = new CicloFormativo(
                    resultado.getInt("codigo")
                    , resultado.getString("familiaProfesional")
                    , grado
                    , resultado.getString("nombre")
                    , resultado.getInt("horas"));
            copiaProfunda.add(cicloFormativo);
        }
        return copiaProfunda;
    }


    public int getTamano() throws SQLException {
        String query = """
                SELECT COUNT(1) as "Total ciclos formativos"
                FROM cicloFormativo""";
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        return resultado.getInt(1);
    }


    public void insertar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        if (buscar(cicloFormativo) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un ciclo formativo con ese codigo.");
        }
        String query = """
                INSERT INTO cicloFormativo (codigo
                , familiaProfesional
                , grado
                , nombre
                , horas
                , nombreGrado
                , numAniosGrado
                , modalidad
                , numEdiciones)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setInt(1, cicloFormativo.getCodigo());
        preStatement.setString(2, cicloFormativo.getFamiliaProfesional());
        preStatement.setString(3, cicloFormativo.getGrado().getClass().getSimpleName().toLowerCase());
        preStatement.setString(4, cicloFormativo.getNombre());
        preStatement.setInt(5, cicloFormativo.getHoras());
        preStatement.setString(6, cicloFormativo.getGrado().getNombre());
        preStatement.setInt(7, cicloFormativo.getGrado().getNumAnios());
        if (cicloFormativo.getGrado() instanceof GradoD) {
            preStatement.setString(8, ((GradoD) cicloFormativo.getGrado()).getModalidad().name().toLowerCase());
            preStatement.setNull(9, Types.INTEGER);
        } else {
            preStatement.setNull(8, Types.VARCHAR);
            preStatement.setInt(9, ((GradoE) cicloFormativo.getGrado()).getNumEdiciones());
        }
        preStatement.executeUpdate();
    }

    @Override
    public CicloFormativo buscar(CicloFormativo cicloFormativo) throws SQLException {
        String query = """
                SELECT codigo
                , familiaProfesional
                , grado
                , nombre
                , horas
                , nombreGrado
                , numAniosGrado
                , modalidad
                , numEdiciones
                FROM cicloFormativo
                WHERE codigo = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setInt(1, cicloFormativo.getCodigo());
        ResultSet resultado = preStatement.executeQuery();
        if (resultado.next()) {
            Grado grado = null;
            if (resultado.getString("grado").equals("gradod")) {
                grado = new GradoD(
                        resultado.getString("nombreGrado")
                        , resultado.getInt("numAniosGrado")
                        , Modalidad.valueOf(resultado.getString("modalidad").toUpperCase()));
            } else {
                grado = new GradoE(
                        resultado.getString("nombreGrado")
                        , resultado.getInt("numAniosGrado")
                        , resultado.getInt("numEdiciones"));
            }
            return new CicloFormativo(
                    resultado.getInt("codigo")
                    , resultado.getString("familiaProfesional")
                    , grado
                    , resultado.getString("nombre")
                    , resultado.getInt("horas"));
        }
        return null;
    }


    public void borrar(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {

        if (buscar(cicloFormativo) == null) {
            throw new OperationNotSupportedException("ERROR: No existe un ciclo formativo con ese codigo.");
        }
        String query = """
                DELETE FROM cicloFormativo
                WHERE codigo = ?""";
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setInt(1, cicloFormativo.getCodigo());
        preStatement.executeUpdate();

    }

}
