package org.iesalandalus.programacion.matriculacion.modelo.dominio;



import java.util.Objects;

public class Asignatura {
    public static final int MAX_NUM_HORAS_ANUALES=300;
    public static final int MAX_NUM_HORAS_DESDOBLES=6;
    private String ER_CODIGO="^[0-9]{4}$";
    private String codigo;
    private String nombre;
    private int horasAnuales;
    private int horasDesdoble;
    private Curso curso;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;


    public Asignatura(String codigo, String nombre, int horas,  Curso curso,int horasDesdobles, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) {
        setCodigo(codigo);
        setNombre(nombre);
        setHorasAnuales(horas);
        setCurso(curso);
        setHorasDesdoble(horasDesdobles);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    public Asignatura(Asignatura asignatura) {
        if(asignatura == null){
            throw new NullPointerException("ERROR: No es posible copiar una asignatura nula.");
        }
        setCodigo(asignatura.getCodigo());
        setNombre(asignatura.getNombre());
        setHorasAnuales(asignatura.getHorasAnuales());
        setCurso(asignatura.getCurso());
        setHorasDesdoble(asignatura.getHorasDesdoble());
        setEspecialidadProfesorado(asignatura.getEspecialidadProfesorado());
        setCicloFormativo(asignatura.getCicloFormativo());
    }


    public String getCodigo() {
        return codigo;
    }

    private void setCodigo(String codigo) {
        if (codigo == null){
            throw new NullPointerException("ERROR: El código de una asignatura no puede ser nulo.");
        }
        if (codigo.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El código de una asignatura no puede estar vacío.");
        }
        if (!codigo.matches(ER_CODIGO)) {
           throw new IllegalArgumentException("ERROR: El código de la asignatura no tiene un formato válido.");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null){
            throw new NullPointerException("ERROR: El nombre de una asignatura no puede ser nulo.");
    } else if (nombre.isBlank() || nombre.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El nombre de una asignatura no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales <= 0 || horasAnuales > MAX_NUM_HORAS_ANUALES) {
            throw new IllegalArgumentException("ERROR: El número de horas de una asignatura no puede ser menor o igual a 0 ni mayor a " + MAX_NUM_HORAS_ANUALES + ".");
        }
        this.horasAnuales = horasAnuales;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble < 0 || horasDesdoble > MAX_NUM_HORAS_DESDOBLES) {
            throw new IllegalArgumentException("ERROR: El número de horas de desdoble de una asignatura no puede ser menor a 0 ni mayor a " + MAX_NUM_HORAS_DESDOBLES + ".");
        }
        this.horasDesdoble = horasDesdoble;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null) {
            throw new NullPointerException("ERROR: El curso de una asignatura no puede ser nulo.");
        }
        this.curso = curso;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if (especialidadProfesorado == null) {
            throw new NullPointerException("ERROR: La especialidad del profesorado de una asignatura no puede ser nula.");
        }
        this.especialidadProfesorado = especialidadProfesorado;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new NullPointerException("ERROR: El ciclo formativo de una asignatura no puede ser nulo.");
        }
        this.cicloFormativo = cicloFormativo;
    }
    public String getER_CODIGO() {
        return ER_CODIGO;
    }

    public void setER_CODIGO(String ER_CODIGO) {
        if (codigo != null && codigo.length() != 4) {
            throw new IllegalArgumentException("ERROR: El código de una asignatura debe ser un número de 4 dígitos.");
        }
        this.ER_CODIGO = ER_CODIGO;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asignatura that)) return false;
        return horasAnuales == that.horasAnuales && horasDesdoble == that.horasDesdoble && Objects.equals(nombre, that.nombre) && curso == that.curso && especialidadProfesorado == that.especialidadProfesorado && Objects.equals(cicloFormativo, that.cicloFormativo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, horasAnuales, horasDesdoble, curso, especialidadProfesorado, cicloFormativo);
    }

    @Override
    public String toString() {
        return "Código=" + codigo +
                ", nombre=" + nombre +
                ", horas anuales=" + horasAnuales +
                ", curso=" + curso +
                ", horas desdoble=" + horasDesdoble +
                ", ciclo formativo=" + cicloFormativo.imprimir() +
                ", especialidad profesorado=" + especialidadProfesorado
                 ;
    }
    public String imprimir() {
        return "Código asignatura=" + codigo +
                ", nombre asignatura=" + nombre +
                ", ciclo formativo=" + cicloFormativo.imprimir();
    }


}

