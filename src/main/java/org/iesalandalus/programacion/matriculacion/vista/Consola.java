
package org.iesalandalus.programacion.matriculacion.vista;
import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;




    public class Consola {
        private void consola() {

        }
        public static Consola mostrarMenu() {
            for (Opcion opcion : Opcion.values()) {
                System.out.println(opcion.toString());
            }
            return new Consola();
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

            Alumno alumno;
            String dni;
            String nombre;
            LocalDate fechaNacimiento;
            String telefono;
            String email;

            do {
                System.out.println("Introduce los datos del alumno");
                System.out.println("DNI: ");
                dni = Entrada.cadena();
                System.out.println("Nombre: ");
                nombre = Entrada.cadena();
                System.out.println("Telefono: ");
                telefono = Entrada.cadena();
                System.out.println("Email: ");
                email = Entrada.cadena();
                String fechaIntroducida=("Fecha de nacimiento: ");
                fechaNacimiento = leerFecha(fechaIntroducida);
            }while (dni.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty());
            alumno = new Alumno(dni, nombre, telefono, email, fechaNacimiento);
            return new Alumno(alumno);
        }

        public static LocalDate leerFecha (String fechaIntroducida){
            LocalDate fecha =null;
            boolean fechaCorrecta= false;

            do{
                try {
                    System.out.printf(fechaIntroducida, Alumno.FORMATO_FECHA);
                    fecha = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA));
                    fechaCorrecta = true;
                }
                catch (DateTimeParseException e){
                    System.out.println("ERROR: La fecha introducida no es correcta.");}
            }
            while (!fechaCorrecta);
            return fecha;
        }


        public static Alumno getAlumnoPorDni() {

            Alumno alumno = null;
            String nombre = "Pepe Perez";
            String telefono = "666333888";
            String correo = "PepePerez@gmail.com";
            String dni = "789456132B";
            LocalDate fechaNacimiento = LocalDate.now();

            do {
                System.out.println("Introduce el DNI del alumno: ");
                dni = Entrada.cadena();
            } while (dni==null||dni.isEmpty());
            try {
                alumno = new Alumno(dni, nombre, telefono, correo, fechaNacimiento);
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("ERROR: Los datos introducidos no son correctos.");
            }
            return new Alumno(alumno);
        }

        public static Grado leerGrado() {

            System.out.println("Introduce el grado del ciclo formativo: ");
            String grado;
            do {
                System.out.println("Grado: ");
                grado = Entrada.cadena();
            } while (grado.isEmpty());
            return Grado.valueOf(grado);

        }

        public static CicloFormativo leerCicloFormativo() {

            CicloFormativo cicloFormativo;
            int codigo;
            String familiaProfesional;
            Grado grado;
            String nombre;
            int horas;

            System.out.println("Introduce los datos del ciclo formativo");
            do {
                System.out.println("Código: ");
                codigo = Entrada.entero();
                System.out.println("Familia profesional: ");
                familiaProfesional = Entrada.cadena();
                System.out.println("Nombre: ");
                nombre = Entrada.cadena();
                System.out.println("Horas: ");
                horas = Entrada.entero();
                String gradoIntroducido = ("Grado: ");
                grado = leerGrado();
            } while (codigo <= 0 || familiaProfesional.isEmpty() || grado == null || nombre.isEmpty() || horas <= 0);
            cicloFormativo= new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);

            return new CicloFormativo(cicloFormativo);
        }
        public static void mostrarCiclosFormativos (CiclosFormativos ciclosFormativos){
            System.out.println(ciclosFormativos.toString());
        }

        public CicloFormativo getCicloFormativoPorCodigo () {
            return null;
        }

        public static CicloFormativo getCicloPorCodigo() {
            CicloFormativo cicloFormativo = null;
            int codigo;
            String familiaProfesional = "Informatica";
            Grado grado= Grado.GDCFGB;
            String nombre= "Pepe Perez";
            int horas=250;

            do {
                System.out.println("Introduce el codigo del ciclo formativo: ");
                codigo = Entrada.entero();
            } while (codigo <= 0);
            try {
                cicloFormativo = new CicloFormativo(codigo, familiaProfesional, grado, nombre, horas);
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("ERROR: Los datos introducidos no son correctos.");
            }
            return new CicloFormativo(cicloFormativo);
        }
        public static Curso leerCurso(){
            Curso curso = null;

            while (curso== null) {
                System.out.println("Introduce el curso: ");
                String cursoIntroducido = Entrada.cadena();
                try {
                    curso = Curso.valueOf(cursoIntroducido);
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println("ERROR: El curso introducido no es correcto.");
                }
            }
            return curso;
        }
        public static EspecialidadProfesorado leerEspecialidadProfesorado(){
            EspecialidadProfesorado especialidadProfesorado = null;

            while (especialidadProfesorado== null) {
                System.out.println("Introduce la especialidad del profesorado: ");
                String especialidadProfesoradoIntroducido = Entrada.cadena();
                try {
                    especialidadProfesorado = EspecialidadProfesorado.valueOf(especialidadProfesoradoIntroducido);
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println("ERROR: La especialidad introducida no es correcta.");
                }
            }
            return especialidadProfesorado;
        }

        public static Asignatura leerAsignatura(CiclosFormativos ciclosFormativos) {
            Asignatura asignatura;
            do {
                CicloFormativo cicloFormativos;
                System.out.println("Introduce los datos de la asignatura");
                System.out.println("Código: ");
                String codigo = Entrada.cadena();
                System.out.println("Nombre: ");
                String nombre = Entrada.cadena();
                System.out.println("Horas: ");
                int horas = Entrada.entero();
                System.out.println("Horas desdoble: ");
                int horasDesdoble = Entrada.entero();

                Curso curso = leerCurso();
                EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();
                cicloFormativos = leerCicloFormativo();
                cicloFormativos  = ciclosFormativos.buscar(cicloFormativos);

                asignatura = new Asignatura(codigo, nombre, horas, curso, horasDesdoble, especialidadProfesorado, cicloFormativos);

            }while (asignatura.getHorasAnuales() > Asignatura.MAX_NUM_HORAS_ANUALES || asignatura.getHorasDesdoble() > Asignatura.MAX_NUM_HORAS_DESDOBLES);

            return new Asignatura(asignatura);
        }

        public static Asignatura getAsignaturaPorCodigo() {
            Asignatura asignatura= null;
            String codigo;
            String nombre= "Pepe Perez";
            int horasAnuales=100;
            int horasDesdoble=3;
            Curso curso= Curso.PRIMERO;
            EspecialidadProfesorado especialidadProfesorado= EspecialidadProfesorado.INFORMATICA;
            CicloFormativo cicloFormativo= new CicloFormativo(1, "Informatica", Grado.GDCFGB, "Pepe Perez", 250);

            do {
                System.out.println("Introduce el codigo de la asignatura: ");
                codigo = Entrada.cadena();
            } while (codigo.isEmpty());
            try {
                asignatura = new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdoble, especialidadProfesorado, cicloFormativo);
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println("ERROR: Los datos introducidos no son correctos.");
            }
            return new Asignatura(asignatura);
        }
        private void mostrarAsignaturas (Asignaturas asignaturas){
            System.out.println(asignaturas.toString());
        }
        private boolean asignaturaYaMatriculada(Asignatura[] asignaturasMatricula, Asignatura asignatura) {
            for (Asignatura coleccionAsignatura : asignaturasMatricula) {
                if (coleccionAsignatura.getCodigo().equals(asignatura.getCodigo())) {
                    return true;
                }
            }
            return false;
        }

        public static Matricula leerMatricula(Alumnos alumnos, Asignaturas asignaturas) {
            int idMatricula;
            String cursoAcademico;
            LocalDate fechaMatriculacion;
            Alumno alumno;
            Matricula matricula;

            do {
                System.out.println("Introduzca los datos de la Matricula:");
                System.out.println("IdMatricula: ");
                idMatricula=Entrada.entero();
                System.out.println("Curso Academico: ");
                cursoAcademico=Entrada.cadena();
                System.out.println("Fecha Matriculacion: ");
                fechaMatriculacion=leerFecha("Fecha Matriculacion: ");
                alumno=leerAlumno();
                alumno=alumnos.buscar(alumno);
                matricula=new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, new Asignatura[asignaturas.size()]);
            }while (alumno==null||idMatricula<0||cursoAcademico.isEmpty()||fechaMatriculacion==null);

            return new Matricula(matricula);
        }

        public static Matricula getMatriculaPorIdentificador() {
            Matricula matricula= null;
            int idMatricula=2009;
            String cursoAcademico="Primero";
            LocalDate fechaMatriculacion=LocalDate.now();
            Alumno alumno=new Alumno("Pepe Perez", "666333888", "PepePerez@gmail.com", "789456132B", LocalDate.now());
            Asignatura[] coleccionAsignaturas=new Asignatura[10];


            do {
                System.out.println("Introduzca el ID de la Matricula:");
                idMatricula=Entrada.entero();
            }while (idMatricula<0);

            matricula=new Matricula(idMatricula, cursoAcademico, fechaMatriculacion, alumno, coleccionAsignaturas);
            return new Matricula(matricula);
        }
    }
