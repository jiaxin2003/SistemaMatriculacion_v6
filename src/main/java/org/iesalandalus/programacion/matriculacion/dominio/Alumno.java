package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {
    private String ER_TELEFONO;
    private String ER_CORREO;
    private String ER_DNI;
    public static String FORMATO_FECHA = "dd/MM/YYYY";
    private String ER_NIA;
    private int MIN_EDAD_ALUMNO;
    private String nombre;
    private String telefono;
    private String correo;
    private String dni;
    private String fechaNacimiento;
    private String nia;


    private StringBuilder formateaNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NullPointerException("ERROR: el nombre del alumnado no puede ser nulo.");

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
            throw new NullPointerException("ERROR: El dni del alumnado no puede ser nulo.");

        if (!dni.matches(dni))
            return false;


        String numeroDniString;
        int numeroDni, resto;
        char letraDni;

        Pattern p = Pattern.compile("^([0-9]{8})([a-zA-Z])$");
        Matcher m = p.matcher(dni);
        char[] LETRAS_DNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        if (!m.matches())
            return false;

        numeroDniString = dni.substring(0, dni.length() - 1);
        numeroDni = Integer.parseInt(numeroDniString);

        resto = numeroDni % 23;
        letraDni = dni.charAt(8);
        return LETRAS_DNI[resto] == letraDni;
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
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre no puede ser nulo ni vacío.");
        }

        String nombreFormateado = formateaNombre(nombreCompleto).toString();

        String[] palabras = nombreFormateado.split("\\s+");
        StringBuilder iniciales = new StringBuilder();

        for (String palabra : palabras) {
            iniciales.append(palabra.charAt(0)); // Ya está en mayúscula gracias a formateaNombre
        }

        return iniciales.toString();
    }

    public Alumno(String nombre, String dni, String correo, String telefono, String nia, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setNia(nia);
        setFechaNacimiento(fechaNacimiento);
    }

    public Alumno(Alumno alumno) {
        setNombre(nombre);
        setDni(dni);
        setCorreo(correo);
        setTelefono(telefono);
        setNia(nia);
        setFechaNacimiento(LocalDate.parse(fechaNacimiento));
    }
    public String imprimir(){
        return String.format(
                "Alumno: %s\nDNI: %s\nCorreo: %s\nTeléfono: %s\nFecha de Nacimiento: %s\nNIA: %s",
                nombre, dni, correo, telefono, fechaNacimiento, nia
        );
    }

    public String getER_TELEFONO() {
        return ER_TELEFONO;
    }

    public void setER_TELEFONO(String ER_TELEFONO) {
        this.ER_TELEFONO = ER_TELEFONO;
    }

    public String getER_DNI() {
        return ER_DNI;
    }

    public void setER_DNI(String ER_DNI) {
        this.ER_DNI = ER_DNI;
    }

    public String getER_CORREO() {
        return ER_CORREO;
    }

    public void setER_CORREO(String ER_CORREO) {
        this.ER_CORREO = ER_CORREO;
    }

    public String getER_NIA() {
        return ER_NIA;
    }

    public void setER_NIA(String ER_NIA) {
        this.ER_NIA = ER_NIA;
    }

    public int getMIN_EDAD_ALUMNO() {
        return MIN_EDAD_ALUMNO;
    }

    public void setMIN_EDAD_ALUMNO(int MIN_EDAD_ALUMNO) {
        this.MIN_EDAD_ALUMNO = MIN_EDAD_ALUMNO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) {
            throw new NullPointerException("ERROR: El telefono del alumnado no puede ser nulo.");
        } else if (telefono.length() != 9) {
            throw new IllegalArgumentException("ERROR: El telefono del alumnado debe tener 9 cifras.");
        }
        this.telefono = telefono;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.isEmpty()) {
            throw new NullPointerException("ERROR: El correo del alumnado no puede ser nulo.");
        } else if (!correo.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("ERROR: El correo del alumnado no tiene un formato válido.");
        }
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento.isAfter(LocalDate.now().minusYears(MIN_EDAD_ALUMNO))) {
            throw new IllegalArgumentException("ERROR: La edad del alumnado debe ser mayor o igual a 16.");
        }
        this.fechaNacimiento = String.valueOf(fechaNacimiento);
    }

    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nia='" + nia + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", dni='" + dni + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", MIN_EDAD_ALUMNO=" + MIN_EDAD_ALUMNO +
                ", ER_NIA='" + ER_NIA + '\'' +
                ", ER_DNI='" + ER_DNI + '\'' +
                ", ER_CORREO='" + ER_CORREO + '\'' +
                ", ER_TELEFONO='" + ER_TELEFONO + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno alumnos)) return false;
        return MIN_EDAD_ALUMNO == alumnos.MIN_EDAD_ALUMNO && Objects.equals(ER_TELEFONO, alumnos.ER_TELEFONO) && Objects.equals(ER_CORREO, alumnos.ER_CORREO) && Objects.equals(ER_DNI, alumnos.ER_DNI) && Objects.equals(ER_NIA, alumnos.ER_NIA) && Objects.equals(nombre, alumnos.nombre) && Objects.equals(telefono, alumnos.telefono) && Objects.equals(correo, alumnos.correo) && Objects.equals(dni, alumnos.dni) && Objects.equals(fechaNacimiento, alumnos.fechaNacimiento) && Objects.equals(nia, alumnos.nia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ER_TELEFONO, ER_CORREO, ER_DNI, ER_NIA, MIN_EDAD_ALUMNO, nombre, telefono, correo, dni, fechaNacimiento, nia);
    }

}


