package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {
    INSERTAR_ALUMNO("1. Insertar alumno"),BUSCAR_ALUMNO("2. Buscar alumno"),BORRAR_ALUMNO("3. Borrar alumno"),MOSTRAR_ALUMNOS("4. Mostrar alumnos"),
    INSERTAR_ASIGNATURA("5. Insertar asignatura"),BUSCAR_ASIGNATURA("6. Buscar asignatura"),BORRAR_ASIGNATURA("7. Borrar asignatura"),MOSTRAR_ASIGNATURAS("8. Mostrar asignaturas"),
    INSERTAR_CICLO_FORMATIVO("9. Insertar ciclo formativo"),BUSCAR_CICLO_FORMATIVO("10. Buscar ciclo formativo"),BORRAR_CICLO_FORMATIVO("11. Borrar ciclo formativo"),MOSTRAR_CICLOS_FORMATIVOS("12. Mostrar ciclos formativos"),
    INSERTAR_MATRICULA("13. Insertar matricula"),BUSCAR_MATRICULA("14. Buscar matricula"),ANULAR_MATRICULA("15 Anular matricula"),MOSTRAR_MATRICULAS("16. Mostrar matriculas"),
    MOSTRAR_MATRICULAS_POR_ALUMNO("17. Mostrar matriculas por alumno"),MOSTRAR_MATRICULAS_POR_CICLO("18. Mostrar matriculas por ciclo formativo"),MOSTRAR_MATRICULAS_POR_CURSO("19. Mostrar matriculas por curso academico"),
    SALIR("18. Salir");

    private final String cadenaAMostrar;
    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return "Opcion:" +
                " " + cadenaAMostrar +
                ' ';
    }
}
