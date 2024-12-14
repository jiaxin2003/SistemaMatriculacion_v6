package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
    private static final String ER_TELEFONO="^[67]\\d{8}$";
    private static final String ER_CORREO="^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final String ER_DNI="^([0-9]{8})([A-Za-z])$";
    public static String FORMATO_FECHA = "dd/MM/yyyy";
    private String ER_NIA;
    private final int MIN_EDAD_ALUMNO=16;
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;


    public Alumno(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setFechaNacimiento(fechaNacimiento);
    }

    public Alumno(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }
        setNombre(alumno.nombre);
        setDni(alumno.dni);
        setCorreo(alumno.correo);
        setTelefono(alumno.telefono);
        setFechaNacimiento(alumno.fechaNacimiento);
        setNia();
    }


    private StringBuilder formateaNombre(String nombre)  {
        if (nombre == null  ) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        }
        String[] palabras = nombre.trim().toLowerCase().split("\\s+");

        StringBuilder nombreFormateado = new StringBuilder();


        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {

                nombreFormateado.append(Character.toUpperCase(palabra.charAt(0))).append(palabra.substring(1)).append(" ");
            }
        }
        return new StringBuilder(nombreFormateado.toString().trim());
    }


    private boolean comprobarLetraDni(String dni) {
        int numero = 0;

        if (dni == null || dni.isEmpty())
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        if (!dni.matches(ER_DNI))
            return false;
        try {
        String numeroDniString;
        int numeroDni, resto;
        char letraDni;

        Pattern p = Pattern.compile(ER_DNI);
        Matcher m = p.matcher(dni);
        char[] LETRAS_DNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        if (!m.matches())
            return false;

        numeroDniString = dni.substring(0, dni.length() - 1);
        numeroDni = Integer.parseInt(numeroDniString);

        resto = numeroDni % 23;
        letraDni = dni.charAt(8);
        return LETRAS_DNI[resto] == Character.toUpperCase(letraDni);
    } catch (NumberFormatException e) {
        return false;
    }
    }

    public String getnia(String nia) {
        String nNombre;
        String nDni;
        nNombre = nombre.substring(0, 3);
        nDni = dni.substring(6, 8);
        nia = nNombre + nDni;
        return nia;
    }

    private String getIniciales(String nombreCompleto) {
        if (nombreCompleto == null ) {
            throw new NullPointerException("ERROR: El nombre no puede ser nulo ni vacío.");
        } else if (nombreCompleto.trim().isEmpty()||nombreCompleto.trim().isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre no puede ser vacío.");
        }

        String nombreFormateado = formateaNombre(nombreCompleto).toString();

        String[] palabras = nombreFormateado.split("\\s+");
        StringBuilder iniciales = new StringBuilder();

        for (String palabra : palabras) {
            iniciales.append(palabra.charAt(0));
        }

        return iniciales.toString();
    }





    public String getNombre() {
        return formateaNombre(nombre).toString();
    }

    public void setNombre(String nombre) {
        if (nombre == null ) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        }else if (nombre.trim().isEmpty()||nombre.trim().isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }

        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null ) {
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        } else if (telefono.length() != 9) {
            throw new IllegalArgumentException("ERROR: El teléfono del alumno no tiene un formato válido.");
        }
        this.telefono = telefono;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new NullPointerException("ERROR: El correo de un alumno no puede ser nulo.");
        } else if (!correo.matches(ER_CORREO)) {
            throw new IllegalArgumentException("ERROR: El correo del alumno no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        } else if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        }
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new NullPointerException("ERROR: La fecha de nacimiento de un alumno no puede ser nula.");
        }

        long edad = ChronoUnit.YEARS.between(fechaNacimiento, LocalDate.now());

        if (edad < MIN_EDAD_ALUMNO) {
            throw new IllegalArgumentException("ERROR: La edad del alumno debe ser mayor o igual a 16 años.");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        this.fechaNacimiento = LocalDate.parse((fechaNacimiento.format(formatter)));
    }

    public String getNia() {
        return nia;
    }

    public void setNia() {
        if (nombre == null||nombre.length()<3) {
            throw new NullPointerException("ERROR: El NIA de un alumno no puede ser nulo.");
        }
        if (dni == null || dni.length() < 8) {
            throw new IllegalArgumentException("ERROR: El DNI debe tener al menos 8 caracteres.");
        }
        String nNombre;
        String nDni;
        nNombre = nombre.substring(0, 4).toLowerCase();
        nDni = dni.substring(5, 8);
        this.nia =nNombre + nDni;
    }
    public String imprimir(){
        return String.format(
                "Alumno: %s\nDNI: %s\nCorreo: %s\nTeléfono: %s\nFecha de Nacimiento: %s\nNIA: %s",
                nombre, dni, correo, telefono, fechaNacimiento, nia
        );
    }

    @Override
    public String toString() {
        return "Número de Identificación del Alumnado " +
                "(NIA)=" + nia +
                " nombre=" + nombre +
                " DNI=" + dni +
                " correo=" + correo +
                " telefono=" + telefono+
                " fechaNacimiento=" + fechaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumnos)) return false;
        return  Objects.equals(nombre, alumnos.nombre) && Objects.equals(telefono, alumnos.telefono) && Objects.equals(correo, alumnos.correo) && Objects.equals(dni, alumnos.dni) && Objects.equals(fechaNacimiento, alumnos.fechaNacimiento) && Objects.equals(nia, alumnos.nia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, telefono, correo, dni, fechaNacimiento, nia);
    }

}


