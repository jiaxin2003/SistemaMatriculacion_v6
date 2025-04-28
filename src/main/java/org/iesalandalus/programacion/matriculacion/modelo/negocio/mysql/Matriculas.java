package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import javax.naming.OperationNotSupportedException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Matriculas implements IMatriculas {


    private Connection conexion;
    private static Matriculas instancia = null;


    public Matriculas() {
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

    public static Matriculas getInstancia() {
        if (instancia == null) {
            instancia = new Matriculas();
        }
        return instancia;
    }

    public ArrayList<Matricula> get() throws OperationNotSupportedException, SQLException {
        ArrayList<Matricula> copiaProfunda = new ArrayList<>();
        String sql = """
                SELECT m.idMatricula
                , m.fechaMatriculacion
                ,m.fechaAnulacion
                , m.cursoAcademico
                ,a.dni
                FROM matricula m
                LEFT JOIN alumno a on m.dni = a.dni
                order by m.idMatricula desc, a.nombre
                """;
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(sql);
        while (resultado.next()) {
            Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("ficticio", resultado.getString("dni"), "666555333", "ficticio@fake.com", LocalDate.of(2000, 1, 1)));
            Matricula matricula = new Matricula(
                    resultado.getInt("idMatricula"),
                    resultado.getString("cursoAcademico"),
                    resultado.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(resultado.getInt("idMatricula")));
            Date fechaBD = resultado.getDate("fechaAnulacion");
            LocalDate fechaAnulacion = (fechaBD == null) ? null : fechaBD.toLocalDate();
            if (fechaAnulacion != null) {
                matricula.setFechaAnulacion(fechaAnulacion);
            }

            copiaProfunda.add(matricula);
        }
        return copiaProfunda;
    }

    private ArrayList<Asignatura> getAsignaturasMatricula(int idMatricula) throws SQLException {
        String query = """
                SELECT a.codigo, a.nombre, a.horasAnuales, a.curso, a.horasDesdoble, a.especialidadProfesorado, a.codigoCicloFormativo
                FROM asignaturasMatricula am
                left join asignatura a on am.codigo = a.codigo
                WHERE idMatricula = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setInt(1, idMatricula);
        ResultSet resultado = preStatement.executeQuery();
        ArrayList<Asignatura> coleccionAsignaturas = new ArrayList<>();
        while (resultado.next()) {
            CicloFormativo cicloFormativo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(
                    resultado.getInt("codigoCicloFormativo"), "ficticio", new GradoE("gradoe", 1, 1), "ficticio", 1));
            Asignatura asignatura = new Asignatura(
                    resultado.getString("codigo")
                    , resultado.getString("nombre")
                    , resultado.getInt("horasAnuales")
                    , Curso.valueOf(resultado.getString("curso").toUpperCase())
                    , resultado.getInt("horasDesdoble")
                    , EspecialidadProfesorado.valueOf(resultado.getString("especialidadProfesorado").toUpperCase())
                    , cicloFormativo);
            coleccionAsignaturas.add(asignatura);
        }
        return coleccionAsignaturas;
    }


    public int getTamano() throws SQLException {
        String query = """
                SELECT count(idMatricula) as "Total Matriculas"
                FROM matricula
                """;
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(query);
        return resultado.getInt(1);
    }

    public void insertar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese dni.");
        }
        String query = """
                INSERT INTO matricula (idMatricula, cursoAcademico, fechaMatriculacion, fechaAnulacion, dni)
                VALUES (?,?,?,?,?)
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setInt(1, matricula.getIdMatricula());
        preStatement.setString(2, matricula.getCursoAcademico());
        preStatement.setDate(3, java.sql.Date.valueOf(matricula.getFechaMatriculacion()));
        if (matricula.getFechaAnulacion() == null) {
            preStatement.setNull(4, Types.DATE);
        } else {
            preStatement.setDate(4, java.sql.Date.valueOf(matricula.getFechaAnulacion()));
        }
        preStatement.setString(5, matricula.getAlumno().getDni());
        preStatement.executeUpdate();
        insertarAsignaturasMatricula(matricula.getIdMatricula(), matricula.getColeccionAsignaturas());
    }

    private void insertarAsignaturasMatricula(int idMatricula, ArrayList<Asignatura> coleccionAsigntauras) throws SQLException {
        String query = """
                INSERT INTO asignaturasMatricula (idMatricula, codigo)
                VALUES (?, ?)
                """;
        PreparedStatement pstmt = conexion.prepareStatement(query);
        for (Asignatura asignatura : coleccionAsigntauras) {
            pstmt.setInt(1, idMatricula);
            pstmt.setString(2, asignatura.getCodigo());
            pstmt.executeUpdate();
        }

    }


    public Matricula buscar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        String query = """
                SELECT idMatricula, cursoAcademico, fechaMatriculacion, fechaAnulacion, dni
                FROM matricula
                WHERE idMatricula = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setInt(1, matricula.getIdMatricula());
        ResultSet resultado = preStatement.executeQuery();
        if (resultado.next()) {
            Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("ficticio", resultado.getString("dni"), "666554433", "ficticio@fake.com", LocalDate.of(2000, 1, 1)));
            return new Matricula(resultado.getInt("idMatricula"),
                    resultado.getString("cursoAcademico"),
                    resultado.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(resultado.getInt("idMatricula")));
        }
        return null;
    }

    public void borrar(Matricula matricula) throws OperationNotSupportedException, SQLException {
        if (buscar(matricula) == null) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula con ese dni.");
        }
        String query = """
                UPDATE matricula SET fechaAnulacion = ?
                WHERE idMatricula = ?
                """;
        PreparedStatement preStatement = conexion.prepareStatement(query);
        preStatement.setDate(1, Date.valueOf(Date.valueOf(matricula.getFechaAnulacion()).toLocalDate()));
        preStatement.setInt(2, matricula.getIdMatricula());
        preStatement.executeUpdate();
    }


    public ArrayList<Matricula> get(Alumno alumno) throws OperationNotSupportedException, SQLException {
        ArrayList<Matricula> tempAlumno = new ArrayList<>();
        String query = """
                SELECT m.idMatricula,
                m.cursoAcademico,
                m.fechaMatriculacion,
                m.fechaAnulacion,
                m.dni
                FROM matricula m
                LEFT JOIN alumno a ON m.dni = a.dni
                WHERE a.dni = ?
                ORDER BY m.fechaMatriculacion DESC, a.nombre
                """;
        PreparedStatement pstmt = conexion.prepareStatement(query);
        pstmt.setString(1, alumno.getDni());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Matricula matricula = new Matricula(rs.getInt("idMatricula"),
                    rs.getString("cursoAcademico"),
                    rs.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(rs.getInt("idMatricula")));
            tempAlumno.add(matricula);
        }
        return tempAlumno;
    }

    public ArrayList<Matricula> get(String cursoAcademico) throws OperationNotSupportedException, SQLException {
        ArrayList<Matricula> copiaMatriculas = new ArrayList<>();
        String query = """
                SELECT m.idMatricula,
                m.cursoAcademico,
                m.fechaMatriculacion,
                m.fechaAnulacion,
                m.dni
                FROM matricula m
                LEFT JOIN alumno a ON m.dni = a.dni
                WHERE m.cursoAcademico = ?
                ORDER BY m.fechaMatriculacion DESC, a.nombre
                """;
        PreparedStatement pstmt = conexion.prepareStatement(query);
        pstmt.setString(1, cursoAcademico);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("ficticio", rs.getString("dni"), "666554433", "ficticio@fake.com", LocalDate.of(2000, 1, 1)));
            Matricula matricula = new Matricula(rs.getInt("idMatricula"),
                    rs.getString("cursoAcademico"),
                    rs.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(rs.getInt("idMatricula")));
            copiaMatriculas.add(matricula);
        }
        return copiaMatriculas;
    }


    public ArrayList<Matricula> get(CicloFormativo cicloFormativo) throws OperationNotSupportedException, SQLException {
        ArrayList<Matricula> tempCiclo = new ArrayList<>();
        String query = """
                SELECT m.idMatricula,
                m.cursoAcademico,
                m.fechaMatriculacion,
                m.fechaAnulacion,
                m.dni
                FROM matricula m
                LEFT JOIN asignaturasMatricula am ON m.idMatricula = am.idMatricula
                LEFT JOIN asignatura a ON am.codigo = a.codigo
                LEFT JOIN alumno al ON m.dni = al.dni
                WHERE a.codigoCicloFormativo = ?
                ORDER BY m.fechaMatriculacion DESC, a.nombre
                """;
        PreparedStatement pstmt = conexion.prepareStatement(query);
        pstmt.setInt(1, cicloFormativo.getCodigo());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Alumno alumno = Alumnos.getInstancia().buscar(new Alumno("ficticio", rs.getString("dni"), "666554433", "ficticio@fake.com", LocalDate.of(2000, 1, 1)));
            Matricula matricula = new Matricula(rs.getInt("idMatricula"),
                    rs.getString("cursoAcademico"),
                    rs.getDate("fechaMatriculacion").toLocalDate(),
                    alumno,
                    getAsignaturasMatricula(rs.getInt("idMatricula")));
            tempCiclo.add(matricula);
        }
        return tempCiclo;
    }


}
