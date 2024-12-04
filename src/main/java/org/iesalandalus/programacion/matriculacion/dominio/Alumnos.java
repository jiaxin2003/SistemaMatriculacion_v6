package org.iesalandalus.programacion.matriculacion.dominio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumnos {
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


    private StringBuilder formateaNombre (String nombre){
        if (nombre==null||nombre.trim().isEmpty()){
            return new StringBuilder("");
        }
        String[] palabras = nombre.trim().toLowerCase().split("\\s+");

        StringBuilder nombreFormateado = new StringBuilder();


        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {

                nombreFormateado.append(Character.toUpperCase(palabra.charAt(0))).append(palabra.substring(1)).append(" ");
            }
        }


        return nombreFormateado;
    }




    private boolean comprobarLetraDni(String dni){
        int numero=0;

        if(dni==null|| dni.isEmpty())
            return false;

        
        String numeroDniString;
        int numeroDni, resto;
        char letraDni;

        Pattern p =Pattern.compile("^([0-9]{8})([a-zA-Z])$");
        Matcher m=p.matcher(dni);
        char[] LETRAS_DNI = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        if (!m.matches())
            return false;

        numeroDniString=dni.substring(0, dni.length() - 1);
        numeroDni=Integer.parseInt(numeroDniString);

        resto=numeroDni%23;
        letraDni=dni.charAt(8);
        return LETRAS_DNI[resto]==letraDni;
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
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
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

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
    }

    @Override
    public String toString() {
        return "Alumnos{" +
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
}





