
package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class Consola {
    private void consola() {

    }

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion.toString());
        }
    }

    public static Opcion elegirOpcion() {
        int opcion;
        do {
            System.out.print("Elige una opcion: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > Opcion.values().length);
        return Opcion.values()[opcion];
    }

    public static Alumno leerAlumno() {


        String dni;
        String nombre;
        LocalDate fechaNacimiento;
        String telefono;
        String email;

        do {
            System.out.println("Introduce los datos del alumno");
            System.out.println("Nombre: ");
            nombre = Entrada.cadena();
            System.out.println("DNI: ");
            dni = Entrada.cadena();
            System.out.println("Telefono: ");
            telefono = Entrada.cadena();
            System.out.println("Email: ");
            email = Entrada.cadena();
            String fechaIntroducida = ("Fecha de nacimiento: ");
            fechaNacimiento = leerFecha(fechaIntroducida);
        } while (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty());
        return new Alumno(nombre, dni, telefono, email, fechaNacimiento);
    }

    public static LocalDate leerFecha(String fechaIntroducida) {
        LocalDate fecha = null;
        boolean fechaCorrecta = false;

        do {
            try {
                System.out.printf(fechaIntroducida, Alumno.FORMATO_FECHA);
                fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                fechaCorrecta = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: La fecha introducida no es correcta.");
            }
        }
        while (!fechaCorrecta);
        return fecha;
    }


    public static Alumno getAlumnoPorDni() {

        String dni;
        do {
            System.out.println("Introduce el DNI del alumno: ");
            dni = Entrada.cadena();

        } while (dni == null || dni.isEmpty());
        //return new Alumno(nombre, dni, telefono, correo, fechaNacimiento);
        return new Alumno("pepe perez", dni, "666333888", "PepePerez@gmail.com", LocalDate.of(2000, 12, 12));
    }

    public static TiposGrado leerTipoGrado() {
        int opcion;
        do {
            System.out.println("Elige el tipo de grado: ");
            for (TiposGrado tipo : TiposGrado.values()) {
                System.out.println(tipo.ordinal() + 1 + " " + tipo.name());
            }
            opcion = Entrada.entero()-1;

        } while (opcion < 0 || opcion >= TiposGrado.values().length);

        return TiposGrado.values()[opcion];
    }

    public static Modalidad leerModalidad() {
        int opcion;
        do {
            System.out.println("Elige el tipo de grado: ");
            for (Modalidad modalidad : Modalidad.values()) {
                System.out.println(modalidad.ordinal() + 1 + " " + modalidad.name());
            }
            opcion = Entrada.entero()-1;
        } while (opcion < 0 || opcion >= TiposGrado.values().length);
        return Modalidad.values()[opcion];
    }


    public static CicloFormativo leerCicloFormativo() {

        int codigo;
        String familiaProfesional;
        Grado grado;
        String nombre;
        int horas;

        System.out.println("Introduce los datos del ciclo formativo");
        do {
            System.out.println("Codigo: ");
            codigo = Entrada.entero();
            System.out.println("Familia profesional: ");
            familiaProfesional = Entrada.cadena();
            System.out.println("Nombre: ");
            nombre = Entrada.cadena();
            System.out.println("Horas: ");
            horas = Entrada.entero();
            grado = leerGrado();
        } while (codigo <= 0 || familiaProfesional.isEmpty() || nombre.isEmpty() || horas <= 0 || grado == null);

        return new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
    }

    public static Grado leerGrado() {

        String nombre;
        int anio;

        TiposGrado tiposGrado = leerTipoGrado();

        do {
            System.out.println("Introduce los datos del grado");
            System.out.println("Nombre del grado: ");
            nombre = Entrada.cadena();
            System.out.println("Introduce el Anio: ");
            System.out.println("Si tu grado es GradoD el año esta comprendido entre 2 y 3 y si es GradoE el año es 1");
            anio = Entrada.entero();
        } while (nombre.isBlank() || (tiposGrado == TiposGrado.GRADOD && (anio < 2 || anio > 3)) || (tiposGrado == TiposGrado.GRADOE && anio != 1));
        if (tiposGrado == TiposGrado.GRADOD) {
            Modalidad modalidad = leerModalidad();
            return new GradoD(nombre, anio, modalidad);
        } else {
            int numEdiciones = -1;
            do {
                System.out.println("Introduce el numero de ediciones: ");
                numEdiciones = Entrada.entero();
            } while (numEdiciones <= 0);
            return new GradoE(nombre, anio, numEdiciones);
        }
    }

    public static void mostrarCiclosFormativos(List<CicloFormativo> cicloFormativos) {
        if (cicloFormativos.isEmpty()) {
            System.out.println("No hay ciclos formativos.");
        } else {
            System.out.println(cicloFormativos.size() + " ciclos formativos.");
        }
    }


    public static CicloFormativo getCicloPorCodigo() {
        CicloFormativo cicloFormativo = null;
        int codigo;
        String familiaProfesional = "Informatica";
        Grado grado = new GradoD("Grado D", 2, Modalidad.PRESENCIAL);
        String nombre = "Pepe Perez";
        int horas = 250;
        
        try {
            do {
                System.out.println("Introduce el codigo del ciclo formativo: ");
                codigo = Entrada.entero();
            } while (codigo <= 0);
            cicloFormativo = new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("ERROR: Los datos introducidos no son correctos.");
        }
        return new CicloFormativo(cicloFormativo);
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        int opcion;
        do {
            System.out.println("Elige la especialidad del profesorado: ");
            for (EspecialidadProfesorado especialidadProfesorado : EspecialidadProfesorado.values()) {
                System.out.println(especialidadProfesorado.ordinal() + 1 + especialidadProfesorado.toString());
            }
            opcion = Entrada.entero()-1;
        } while (opcion < 0 || opcion > EspecialidadProfesorado.values().length);

        return EspecialidadProfesorado.values()[opcion];
    }

    public static Curso leerCurso() {
        int opcion;
        do {
            System.out.println("Elige el curso: ");
            for (Curso curso : Curso.values()) {
                System.out.println(curso.ordinal() + 1 + curso.toString());
            }
            opcion = Entrada.entero()-1;
        } while (opcion < 0 || opcion > Curso.values().length);


        return Curso.values()[opcion];
    }

    public static Asignatura leerAsignatura(CicloFormativo ciclosFormativo) {

        String codigo;
        String nombre;
        int horas;
        int horasDesdoble;
        Curso curso;
        EspecialidadProfesorado especialidadProfesorado;

        do {
            System.out.println("Introduce los datos de la asignatura");
            System.out.println("Codigo: ");
            codigo = Entrada.cadena();
            System.out.println("Nombre: ");
            nombre = Entrada.cadena();
            System.out.println("Horas: ");
            horas = Entrada.entero();
            System.out.println("Horas desdoble: ");
            horasDesdoble = Entrada.entero();

            curso = leerCurso();
            especialidadProfesorado = leerEspecialidadProfesorado();

        } while (codigo.isEmpty() || nombre.isEmpty() || horas <= 0 || horasDesdoble <= 0 || curso == null || especialidadProfesorado == null);

        return new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidadProfesorado, ciclosFormativo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        String codigo;
        String nombre = "Pepe Perez";
        int horasAnuales = 100;
        int horasDesdoble = 3;
        Curso curso = Curso.PRIMERO;
        EspecialidadProfesorado especialidadProfesorado = EspecialidadProfesorado.INFORMATICA;
        Grado grado = new GradoD("Grado D", 2, Modalidad.PRESENCIAL);
        CicloFormativo cicloFormativo = new CicloFormativo(1234, "Informatica", grado, "Pepe Perez", 250);

        do {
            System.out.println("Introduce el codigo de la asignatura: ");
            codigo = Entrada.cadena();
        } while (codigo.isEmpty());

        return new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
    }

    private static void mostrarAsignaturas(List<Asignatura> asignaturas) {
        if (asignaturas.isEmpty()) {
            System.out.println("No hay asignaturas matriculadas.");
        } else {
            for (Asignatura asignatura : asignaturas)
                if (asignatura != null) {
                    System.out.println(asignatura);
                }
        }
    }

    private static boolean asignaturaYaMatriculada(List<Asignatura> asignaturasMatricula, Asignatura asignatura) {
        if (asignaturasMatricula != null) {
            for (Asignatura asignaturas : asignaturasMatricula) {
                if (asignaturas != null && asignaturas.equals(asignatura)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Matricula leerMatricula(Alumno alumno, ArrayList<Asignatura> asignaturas) throws OperationNotSupportedException {

        if (alumno == null || alumno.getDni().isEmpty()) {
            throw new NullPointerException("ERROR: No se puede matricular a un alumno nulo.");
        }
        if (asignaturas == null || asignaturas.isEmpty()) {
            throw new NullPointerException("ERROR: La lista de asignaturas de una matrícula no puede ser nula.");
        }
        int idMatricula;
        String cursoAcademico;
        LocalDate fechaMatriculacion;


        do {
            System.out.println("Introduzca los datos de la Matricula:");
            System.out.println("IdMatricula: ");
            idMatricula = Entrada.entero();
            System.out.println("Curso Academico: ");
            cursoAcademico = Entrada.cadena();
            fechaMatriculacion = leerFecha("Fecha Matriculacion: ");


        } while (idMatricula < 0 || cursoAcademico.isEmpty() || fechaMatriculacion == null);

        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignaturas);
    }

    public static Matricula getMatriculaPorIdentificador() throws OperationNotSupportedException {
        Matricula matricula;
        CicloFormativo cicloFormativo;
        int idMatricula;
        String cursoAcademico = "24-25";
        LocalDate fechaMatriculacion = LocalDate.of(2025, 2, 15);
        Alumno alumno = new Alumno("Pepe Perez", "87654321x", "666555444", "PepePerez@gmail.com", LocalDate.of(2000, 12, 15));
        ArrayList<Asignatura> asignatura = new ArrayList<>();

        do {
            System.out.println("Introduzca el ID de la Matricula:");
            idMatricula = Entrada.entero();
        } while (idMatricula < 0);

        return new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, asignatura);
    }

    public static ArrayList<Asignatura> elegirAsignaturasMatricula(List<Asignatura> asignaturas) {
        if (asignaturas == null || asignaturas.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No hay asignaturas disponibles para seleccionar.");
        }

        ArrayList<Asignatura> asignaturasMatriculas = new ArrayList<>();
        int opcion = 0;


        do {
            mostrarAsignaturas(asignaturas);

            System.out.print("Introduzca el código de la asignatura: ");
            String codigo = Entrada.cadena();
            Asignatura asignatura = null;

            for (Asignatura asignaturaCodigo : asignaturas) {
                if (asignaturaCodigo != null && asignaturaCodigo.getCodigo().equals(codigo)) {
                    asignatura = asignaturaCodigo;
                    break;
                }
            }

            if (asignatura == null) {
                System.out.println("ERROR: La asignatura seleccionada no es válida.");
                continue;
            }

            if (asignaturaYaMatriculada(asignaturasMatriculas, asignatura)) {
                System.out.println("La asignatura ya está seleccionada.");
            } else {

                asignaturasMatriculas.add(asignatura);
                System.out.println("Asignatura añadida correctamente.");
            }

            System.out.print("¿Desea añadir otra asignatura? (0 = No, 1 = Sí): ");
            opcion = Entrada.entero();
        } while (opcion == 1 && asignaturasMatriculas.size() < asignaturas.size());

        return asignaturasMatriculas;
    }
}
