package org.iesalandalus.programacion.matriculacion.vista.texto;

public enum Opcion {

    SALIR("Salir") {
        @Override
        public void ejecutar() {
            vista.terminar();
        }
    },
    INSERTAR_ALUMNO("Insertar alumno") {
        @Override
        public void ejecutar() {
            vista.insertarAlumno();
        }
    },
    BUSCAR_ALUMNO("Buscar alumno") {
        @Override
        public void ejecutar() {
            vista.buscarAlumno();
        }
    },
    BORRAR_ALUMNO("Borrar alumno") {
        @Override
        public void ejecutar() {
            vista.borrarAlumno();
        }
    },
    MOSTRAR_ALUMNOS("Mostrar alumnos") {
        @Override
        public void ejecutar() {
            vista.mostrarAlumnos();
        }
    },
    INSERTAR_ASIGNATURA("Insertar asignatura") {
        @Override
        public void ejecutar() {
            vista.insertarAsignatura();
        }
    },
    BUSCAR_ASIGNATURA("Buscar asignatura") {
        @Override
        public void ejecutar() {
            vista.buscarAsignatura();
        }
    },
    BORRAR_ASIGNATURA("Borrar asignatura") {
        @Override
        public void ejecutar() {
            vista.borrarAsignatura();
        }
    },
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas") {
        @Override
        public void ejecutar() {
            vista.mostrarAsignaturas();
        }
    },
    INSERTAR_CICLO_FORMATIVO("Insertar ciclo formativo") {
        @Override
        public void ejecutar() {
            vista.insertarCicloFormativo();
        }
    },
    BUSCAR_CICLO_FORMATIVO("Buscar ciclo formativo") {
        @Override
        public void ejecutar() {
            vista.buscarCicloFormativo();
        }
    },
    BORRAR_CICLO_FORMATIVO("Borrar ciclo formativo") {
        @Override
        public void ejecutar() {
            vista.borrarCicloFormativo();
        }
    },
    MOSTRAR_CICLOS_FORMATIVOS("Mostrar ciclos formativos") {
        @Override
        public void ejecutar() {
            vista.mostrarCiclosFormativos();
        }
    },
    INSERTAR_MATRICULA("Insertar matricula") {
        @Override
        public void ejecutar() {
            vista.insertarMatricula();
        }
    },
    BUSCAR_MATRICULA("Buscar matricula") {
        @Override
        public void ejecutar() {
            vista.buscarMatricula();
        }
    },
    ANULAR_MATRICULA("Anular matricula") {
        @Override
        public void ejecutar() {
            vista.anularMatricula();
        }
    },
    MOSTRAR_MATRICULAS("Mostrar matriculas") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculas();
        }
    },
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matriculas alumno") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculasPorAlumno();
        }
    },
    MOSTRAR_MATRICULAS_CICLO_FORMATIVO("Mostrar matriculas por ciclo formativo") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculasPorCicloFormativo();
        }
    },
    MOSTRAR_MATRICULAS_CURSO_ACADEMICO("Mostrar matriculas por curso academico") {
        @Override
        public void ejecutar() {
            vista.mostrarMatriculasPorCursoAcademico();
        }
    };


    private final String cadenaAMostrar;
    private static VistaTexto vista;

    private Opcion(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public abstract void ejecutar();

    public static void setVista(VistaTexto vista) {
        Opcion.vista = vista;
    }

    @Override
    public String toString() {
        return this.ordinal() + ". -" +
                cadenaAMostrar;
    }
}
