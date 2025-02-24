package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    private static final String ER_TELEFONO = "\\d{9}";
    private static final String ER_CORREO = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final String ER_DNI = "^([0-9]{8})([A-Za-z])$";
    public static String FORMATO_FECHA = "dd/MM/yyyy";
    private final String ER_NIA = "[A-Za-z]{4}[0-9]{3}";
    private final int MIN_EDAD_ALUMNO = 16;


    public Alumno(String nombre, String dni, String telefono, String correo, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
        setCorreo(correo);
        setFechaNacimiento(fechaNacimiento);
        setNia();
    }

    public Alumno(Alumno alumno)  {
        if (alumno == null) {
            throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
        }
        setDni(alumno.dni);
        setNombre(alumno.nombre);
        setTelefono(alumno.telefono);
        setCorreo(alumno.correo);
        setFechaNacimiento(alumno.fechaNacimiento);
        setNia();
    }


    private String formateaNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        }
        String[] palabras = nombre.trim().toLowerCase().split("\\s+");

        StringBuilder nombreFormateado = new StringBuilder();


        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {

                nombreFormateado.append(Character.toUpperCase(palabra.charAt(0))).append(palabra.substring(1)).append(" ");
            }
        }
        return new StringBuilder(nombreFormateado.toString().trim()).toString();
    }


    private boolean comprobarLetraDni(String dni) {


        if (dni == null || dni.isEmpty())
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");

        if (!dni.matches(ER_DNI)) return false;
        try {
            String numeroDniString;
            int numeroDni, resto;
            char letraDni;

            Pattern p = Pattern.compile(ER_DNI);
            Matcher m = p.matcher(dni);
            char[] LETRAS_DNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

            if (!m.matches()) return false;

            numeroDniString = dni.substring(0, 8);
            numeroDni = Integer.parseInt(numeroDniString);

            resto = numeroDni % 23;
            letraDni = dni.charAt(8);
            return LETRAS_DNI[resto] == Character.toUpperCase(letraDni);
        } catch (Exception e) {
            return false;
        }
    }


    private String getIniciales(String nombreCompleto) {
        if (nombreCompleto == null) {
            throw new NullPointerException("ERROR: El nombre no puede ser nulo ni vacío.");
        } else if (nombreCompleto.trim().isBlank()) {
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
        return formateaNombre(nombre);
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new NullPointerException("ERROR: El nombre de un alumno no puede ser nulo.");
        } else if (nombre.trim().isBlank()) {
            throw new IllegalArgumentException("ERROR: El nombre de un alumno no puede estar vacío.");
        }

        this.nombre = formateaNombre(nombre);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new NullPointerException("ERROR: El teléfono de un alumno no puede ser nulo.");
        }
        if (!telefono.matches(ER_TELEFONO)) {
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

    private void setDni(String dni) {
        if (dni == null) {
            throw new NullPointerException("ERROR: El dni de un alumno no puede ser nulo.");
        } else if (!dni.matches(ER_DNI)) {
            throw new IllegalArgumentException("ERROR: El dni del alumno no tiene un formato válido.");
        }
        if (!comprobarLetraDni(dni.toUpperCase())) {
            throw new IllegalArgumentException("ERROR: La letra del dni del alumno no es correcta.");
        }
        this.dni = dni.toUpperCase();
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
        this.fechaNacimiento = fechaNacimiento;
    }

    private void setNia(String nia) {
        if (!dni.matches(ER_NIA)) {
            throw new IllegalArgumentException("ERROR: El nia del alumno no tiene un formato válido.");
        }
        this.nia = nia;
    }

    public String getNia() {
        return this.nia;
    }

    private void setNia() {
        if (nombre == null || nombre.length() < 3) {
            throw new NullPointerException("ERROR: El NIA de un alumno no puede ser nulo.");
        }
        if (dni == null || dni.length() < 8) {
            throw new IllegalArgumentException("ERROR: El DNI debe tener al menos 8 caracteres.");
        }
        String nNombre;
        nNombre = nombre.toLowerCase().substring(0, 4);
        nia = dni.substring(5, 8);
        this.nia = nNombre + nia;
    }


    public String imprimir() {
        return ("Alumno:" + nombre  + " DNI: "+ dni);
    }

    @Override
    public String toString() {
        return String.format("Número de Identificación del Alumnado (NIA)=%s " + "nombre=%s (%s), DNI=%s, correo=%s, teléfono=%s, fecha nacimiento=%s", this.nia, this.nombre, getIniciales(this.nombre), this.dni, this.correo, this.telefono, this.fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA)));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(dni, alumno.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
}


