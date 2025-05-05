package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import  javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class ControladorVentanaPrincipal {


    @FXML private Button btnSalir;
    @FXML private Button btn_SalirCiclos;
    @FXML private Button btnSalirAsignatura;
    @FXML private Button btnSalirMatricula;


    @FXML private TableColumn<Alumno, String> clm_correo_alumno;
    @FXML private TableColumn<Alumno, String> clm_dni_alumno;
    @FXML private TableColumn<Alumno, String> clm_fechaNac;
    @FXML private TableColumn<Alumno, String> clm_nombre_alumno;
    @FXML private TableColumn<Alumno, String> clm_telefono_alumno;

    @FXML private TableColumn<CicloFormativo, Integer> clm_CodigoCiclo;
    @FXML private TableColumn<CicloFormativo, String> clm_FamProCiclo;
    @FXML private TableColumn<CicloFormativo, Grado> clm_GradoCiclo;
    @FXML private TableColumn<CicloFormativo, Integer> clm_horasCiclo;
    @FXML private TableColumn<CicloFormativo, String> clm_nombreCiclo;

    @FXML private TableColumn<Asignatura, CicloFormativo> clmCicloFormativoAsignatura;
    @FXML private TableColumn<Asignatura, String> clmCodigoAsignatura;
    @FXML private TableColumn<Asignatura, Curso> clmCursoAsignatura;
    @FXML private TableColumn<Asignatura, EspecialidadProfesorado> clmEspeProfeAsignatura;
    @FXML private TableColumn<Asignatura, Integer> clmHorasAnualesAsignatura;
    @FXML private TableColumn<Asignatura, Integer> clmHorasDesdoAsignatura;
    @FXML private TableColumn<Asignatura, String> clmNombreAsignatura;

    @FXML private TableColumn<Matricula, String> clmIDMatricula;
    @FXML private TableColumn<Matricula, Alumno> clmAlumnoMatricula;
    @FXML private TableColumn<Matricula, String> clmCursoAcademico;
    @FXML private TableColumn<Matricula, String> clmAsignaturaMatricula;
    @FXML private TableColumn<Matricula, String> clmFecahAnuMatricula;
    @FXML private TableColumn<Matricula, String> clmFechaMatricula;

    @FXML private TableColumn<Matricula, String> clmAlumnoMatriculaVer;
    @FXML private TableColumn<Matricula, String> clmDNIAlumnoMatriculaVer;
    @FXML private TableColumn<Matricula, String> clmFechaAnulacionAlumnoVer;
    @FXML private TableColumn<Matricula, String> clmFechaMatriculacionAlumnoVer;
    @FXML private TableColumn<Matricula, Integer> clmIDMatriculaAlumnoVer;
    @FXML private TableColumn<Matricula, String> clmAsignaturasAlumnoMatriculasVer;


    @FXML private TableColumn<Matricula, String> clmCicloMatricula;
    @FXML private TableColumn<Matricula, Integer> clmCodigoCicloMatricula;
    @FXML private TableColumn<Matricula, String> clmFechaAnulacionCiclo;
    @FXML private TableColumn<Matricula, String> clmFechaMatriculacionCiclo;
    @FXML private TableColumn<Matricula, Integer> clmIdMatriculaCiclo;
    @FXML private TableColumn<Matricula, String> clmAsignaturasCicloMatriculas;


    @FXML private TableColumn<Matricula, String> clmFechaAnulacionPorCursoAcademico;
    @FXML private TableColumn<Matricula, String> clmFechaMaticulaPorCursoAcademico;
    @FXML private TableColumn<Matricula, Integer> clmIdMatriculaPorCursoAcademico;
    @FXML private TableColumn<Matricula, String> clmMatriculaPorCursoAcademico;
    @FXML private TableColumn<Matricula, String> clmAsignaturasCursoAcademicoMatricula;
    @FXML private TextField tfCursoAcademicoMatricula;

    @FXML private TableView<Matricula> tvAlumnoMatricula;
    @FXML private TableView<Matricula> tvMatriculaCiclos;
    @FXML private TableView<Matricula> tvMatriculasPorCursoAcademico;

    @FXML private TableView<Alumno> table_Alumnos;
    @FXML private TableView<CicloFormativo> tv_cicloFormativo;
    @FXML private TableView<Asignatura> tvAsignatura;
    @FXML private TableView<Matricula> tvMatricula;


    @FXML private TextField tf_alumnoBuscar;
    @FXML private TextField tf_cicloFormativo;
    @FXML private TextField tfAsignatura;
    @FXML private TextField tfBuscarMatricula;

    private List<Alumno> coleccionAlumnos = new ArrayList<>();
    private final ObservableList<Alumno> alumnosObservable = FXCollections.observableArrayList();

    private List<CicloFormativo> coleccionCiclos = new ArrayList<>();
    private final ObservableList<CicloFormativo> ciclosObservable = FXCollections.observableArrayList();

    private List<Asignatura> coleccionAsignaturas = new ArrayList<>();
    private final ObservableList<Asignatura> asignaturasObservable = FXCollections.observableArrayList();

    private List<Matricula> coleccionMatriculas = new ArrayList<>();
    private final ObservableList<Matricula> matriculasObservable = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        mostrarAlumnoTabla();
        mostrarCicloTabla();
        mostrarAsignaturas();
        mostrarMatriculas();
        tf_alumnoBuscar.textProperty().addListener((obs, oldVal, newVal) -> filtraAlumnos(newVal));
        tf_cicloFormativo.textProperty().addListener((obs, oldVal, newVal) -> filtraCiclos(newVal));
        tfAsignatura.textProperty().addListener((obs, oldVal, newVal) -> filtraAsignaturas(newVal));
        tfBuscarMatricula.textProperty().addListener((obs, oldVal, newVal) -> filtraMatriculas(newVal));
        table_Alumnos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarMatriculaAlumno();
            } else {
                tvAlumnoMatricula.getItems().clear();
            }
        });
        tv_cicloFormativo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarMatriculaCiclos();
            } else {
                tvMatriculaCiclos.getItems().clear();
            }
        });
        tfCursoAcademicoMatricula.textProperty().addListener((observable, oldValue, newValue) -> {
            mostrarMatriculaPorCursoAcademico(newValue);
        });

    }

    @FXML
    void aniadirAlumno(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaAlumnos.fxml"));
            Parent raiz = fxmlLoader.load();

            ControladorVentanaAlumno controlador = fxmlLoader.getController();
            controlador.cargarAlumnos(coleccionAlumnos, alumnosObservable);

            Stage ventanaAlumno = new Stage();
            Scene escena = new Scene(raiz);
            ventanaAlumno.setTitle("Añadir Alumno");
            ventanaAlumno.setScene(escena);
            ventanaAlumno.initModality(Modality.APPLICATION_MODAL);
            ventanaAlumno.setResizable(false);
            ventanaAlumno.showAndWait();
            coleccionAlumnos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAlumnos());
            alumnosObservable.setAll(coleccionAlumnos);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void mostrarAlumnoTabla() {
        try {
            clm_nombre_alumno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("nombre"));
            clm_dni_alumno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("dni"));
            clm_correo_alumno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("correo"));
            clm_telefono_alumno.setCellValueFactory(new PropertyValueFactory<Alumno, String>("telefono"));
            clm_fechaNac.setCellValueFactory(alumno -> new SimpleStringProperty(alumno.getValue().getFechaNacimiento().format(DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA))));

            table_Alumnos.setItems(alumnosObservable);
            coleccionAlumnos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAlumnos());
            alumnosObservable.setAll(coleccionAlumnos);

            table_Alumnos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraAlumnoSeleccionada(newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void muestraAlumnoSeleccionada(Alumno newValue) {
        System.out.println("El Alumno seleccionado de la tabla es: " + newValue);
    }

    @FXML
    void borrarAlumno(ActionEvent event) {
        try {
            Alumno alumno = table_Alumnos.getSelectionModel().getSelectedItem();
            if (alumno == null) {
                Dialogos.mostrarDialogoAdvertencia("Error", "No se puede borrar un alumno si no se ha seleccionado uno.");
            } else {
                boolean tieneMatriculas = false;
                List<Matricula> matriculas = VistaGrafica.getInstancia().getControlador().getMatriculas();
                for (Matricula matricula : matriculas) {
                    if (matricula.getAlumno().equals(alumno)) {
                        tieneMatriculas = true;
                        break;
                    }
                }

                if (tieneMatriculas) {
                    Dialogos.mostrarDialogoAdvertencia("Error", "No se puede borrar al alumno " + alumno.getNombre() + " porque tiene matrículas.");
                    return;
                }

                if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres borrar al alumno " + alumno.getNombre() + "?")) {
                    VistaGrafica.getInstancia().getControlador().borrar(alumno);
                    alumnosObservable.remove(alumno);
                    coleccionAlumnos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAlumnos());
                    alumnosObservable.setAll(coleccionAlumnos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void buscarAlumno(ActionEvent event) {
        try {
            String alumnoBuscar = tf_alumnoBuscar.getText().toLowerCase();
            if (alumnoBuscar.isBlank()) {
                coleccionAlumnos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAlumnos());
                alumnosObservable.setAll(coleccionAlumnos);
            } else {
                List<Alumno> coleccionAlumnos = new ArrayList<>();

                for (Alumno alumno : VistaGrafica.getInstancia().getControlador().getAlumnos()) {
                    if (alumno.getNombre().toLowerCase().contains(alumnoBuscar)) {
                        coleccionAlumnos.add(alumno);
                    }
                }
                alumnosObservable.setAll(coleccionAlumnos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filtraAlumnos(String newValue) {
        FilteredList<Alumno> alumnosBusqueda = new FilteredList<>(alumnosObservable, alumno -> true);

        alumnosBusqueda.setPredicate(alumno -> {

            if (newValue.isBlank())
                return true;

            String alumnoFiltrado = newValue.toLowerCase();

            return alumno.getNombre().toLowerCase().contains(alumnoFiltrado);

        });

        table_Alumnos.setItems(alumnosBusqueda);
    }


    @FXML
    void cerrarVentanaAlumno(ActionEvent event) {
        if (event.getSource() == btnSalir){
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?"))
            stage.close();
        else
            event.consume();
        }else if (event.getSource() == btnSalirAsignatura){
            Stage stage = (Stage) btnSalirAsignatura.getScene().getWindow();
            if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?"))
                stage.close();
            else
                event.consume();
        }else if (event.getSource() == btn_SalirCiclos){
            Stage stage = (Stage) btn_SalirCiclos.getScene().getWindow();
            if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?"))
                stage.close();
            else
                event.consume();
        }else if (event.getSource() == btnSalirMatricula){
            Stage stage = (Stage) btnSalirMatricula.getScene().getWindow();
            if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?"))
                stage.close();
            else
                event.consume();
        }

    }


    private void mostrarCicloTabla() {
        try {
            clm_nombreCiclo.setCellValueFactory(new PropertyValueFactory<CicloFormativo, String>("nombre"));
            clm_CodigoCiclo.setCellValueFactory(new PropertyValueFactory<CicloFormativo, Integer>("codigo"));
            clm_FamProCiclo.setCellValueFactory(new PropertyValueFactory<CicloFormativo,String>("familiaProfesional"));
            clm_GradoCiclo.setCellValueFactory(new PropertyValueFactory<CicloFormativo,Grado>("grado"));
            clm_horasCiclo.setCellValueFactory(new PropertyValueFactory<CicloFormativo,Integer>("horas"));


            tv_cicloFormativo.setItems(ciclosObservable);
            coleccionCiclos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getCiclosFormativos());
            ciclosObservable.setAll(coleccionCiclos);

            tv_cicloFormativo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraCicloSeleccionada(newValue));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void muestraCicloSeleccionada(CicloFormativo newValue) {
        System.out.println("El Ciclo seleccionadO de la tabla es: " + newValue);
    }


    @FXML
    void aniadirCiclo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaCiclosFormativos.fxml"));
            Parent raiz = fxmlLoader.load();

            ControladorVentanaCiclosFormativos controlador = fxmlLoader.getController();

            Scene escena = new Scene(raiz);
            Stage ventanaCiclo = new Stage();
            ventanaCiclo.setTitle("Añadir Ciclo");
            ventanaCiclo.setScene(escena);
            ventanaCiclo.initModality(Modality.APPLICATION_MODAL);
            ventanaCiclo.setResizable(false);
            ventanaCiclo.showAndWait();
            coleccionCiclos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getCiclosFormativos());
            ciclosObservable.setAll(coleccionCiclos);


        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void borrarCiclo(ActionEvent event) {
        try {
            CicloFormativo cicloFormativo = tv_cicloFormativo.getSelectionModel().getSelectedItem();

            if (cicloFormativo == null) {
                Dialogos.mostrarDialogoAdvertencia("Error", "No se puede borrar un ciclo si no se ha seleccionado uno.");
                return;
            }

            boolean tieneMatriculas = false;
            List<Matricula> matriculas = VistaGrafica.getInstancia().getControlador().getMatriculas();

            for (Matricula matricula : matriculas) {
                List<Asignatura> asignaturas = matricula.getColeccionAsignaturas();
                for (Asignatura asignatura : asignaturas) {
                    if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                        tieneMatriculas = true;
                        break;
                    }
                }
                if (tieneMatriculas) {
                    break;
                }
            }

            if (tieneMatriculas) {
                Dialogos.mostrarDialogoAdvertencia("Error", "No se puede borrar el ciclo " + cicloFormativo.getNombre() + " porque tiene matrículas asociadas.");
                return;
            }

            if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres borrar el ciclo " + cicloFormativo.getNombre() + "?")) {
                VistaGrafica.getInstancia().getControlador().borrar(cicloFormativo);
                ciclosObservable.remove(cicloFormativo);
                coleccionCiclos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getCiclosFormativos());
                ciclosObservable.setAll(coleccionCiclos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buscarCiclo(ActionEvent event) {
        try {
            String cicloBuscar = tf_cicloFormativo.getText().toLowerCase();
            if (cicloBuscar.isBlank()) {
                coleccionCiclos = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getCiclosFormativos());
                ciclosObservable.setAll(coleccionCiclos);
            } else {
                List<CicloFormativo> coleccionCiclos = new ArrayList<>();

                for (CicloFormativo cicloFormativo : VistaGrafica.getInstancia().getControlador().getCiclosFormativos()) {
                    if (cicloFormativo.getNombre().toLowerCase().contains(cicloBuscar)) {
                        coleccionCiclos.add(cicloFormativo);
                    }
                }
                ciclosObservable.setAll(coleccionCiclos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void filtraCiclos(String newValue) {
        FilteredList<CicloFormativo> cicloFormativosBusqueda = new FilteredList<>(ciclosObservable, cicloFormativo -> true);

        cicloFormativosBusqueda.setPredicate(cicloFormativo-> {

            if (newValue.isBlank())
                return true;

            String cicloFiltrado = newValue.toLowerCase();

            return cicloFormativo.getNombre().toLowerCase().contains(cicloFiltrado);

        });

        tv_cicloFormativo.setItems(cicloFormativosBusqueda);
    }

    public void mostrarAsignaturas() {
        try{
        clmNombreAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, String>("nombre"));
        clmCodigoAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, String>("codigo"));
        clmCursoAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, Curso>("curso"));
        clmEspeProfeAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, EspecialidadProfesorado>("especialidadProfesorado"));
        clmHorasAnualesAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, Integer>("horasAnuales"));
        clmHorasDesdoAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, Integer>("horasDesdoble"));
        clmCicloFormativoAsignatura.setCellValueFactory(new PropertyValueFactory<Asignatura, CicloFormativo>("cicloFormativo"));

        tvAsignatura.setItems(asignaturasObservable);
        coleccionAsignaturas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAsignaturas());
        asignaturasObservable.setAll(coleccionAsignaturas);

        tvAsignatura.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> muestraAsignaturaSeleccionada(newValue));
        }catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void muestraAsignaturaSeleccionada(Asignatura newValue) {
        System.out.println("La Asignatura seleccionada de la tabla es: " + newValue);
    }


    @FXML
    void aniadirAsignatura(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaAsignatura.fxml"));
            Parent raiz = fxmlLoader.load();

            ControladorVentanaAsignatura controlador = fxmlLoader.getController();

            Scene escena = new Scene(raiz);
            Stage ventanaAsignatura = new Stage();
            ventanaAsignatura.setTitle("Añadir Asignatura");
            ventanaAsignatura.setScene(escena);
            ventanaAsignatura.initModality(Modality.APPLICATION_MODAL);
            ventanaAsignatura.setResizable(false);
            ventanaAsignatura.showAndWait();
            coleccionAsignaturas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAsignaturas());
            asignaturasObservable.setAll(coleccionAsignaturas);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void borrarAsignatura(ActionEvent event) {
        try{
            Asignatura asignatura = tvAsignatura.getSelectionModel().getSelectedItem();
            if (asignatura == null) {
                Dialogos.mostrarDialogoAdvertencia("Error", "No se puede borrar una asignatura si no se ha seleccionado una");
            } else {
                if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres borrar la asignatura " + asignatura.getNombre() + "?"))
                    VistaGrafica.getInstancia().getControlador().borrar(asignatura);
                asignaturasObservable.remove(asignatura);
                coleccionAsignaturas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAsignaturas());
                asignaturasObservable.setAll(coleccionAsignaturas);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarAsignatura () {
        try{
            String asignaturaBuscar = tfAsignatura.getText().toLowerCase();
            if (asignaturaBuscar.isBlank()) {
                coleccionAsignaturas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getAsignaturas());
                asignaturasObservable.setAll(coleccionAsignaturas);
            } else {
                List<Asignatura> coleccionAsignaturas = new ArrayList<>();

                for (Asignatura asignatura : VistaGrafica.getInstancia().getControlador().getAsignaturas()) {
                    if (asignatura.getNombre().toLowerCase().contains(asignaturaBuscar)) {
                        coleccionAsignaturas.add(asignatura);
                    }
                }
                asignaturasObservable.setAll(coleccionAsignaturas);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filtraAsignaturas(String newValue) {
        FilteredList<Asignatura> asignaturasBusqueda = new FilteredList<>(asignaturasObservable, asignatura -> true);

        asignaturasBusqueda.setPredicate(asignatura-> {

            if (newValue.isBlank())
                return true;

            String asignaturaFiltrada = newValue.toLowerCase();

            return asignatura.getNombre().toLowerCase().contains(asignaturaFiltrada);

        });

        tvAsignatura.setItems(asignaturasBusqueda);
    }

    public void mostrarMatriculas() {
        try{


            clmIDMatricula.setCellValueFactory(new PropertyValueFactory<Matricula, String>("idMatricula"));
            clmCursoAcademico.setCellValueFactory(new PropertyValueFactory<Matricula, String>("cursoAcademico"));
            clmFechaMatricula.setCellValueFactory(matricula-> new SimpleStringProperty(matricula.getValue().getFechaMatriculacion().format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA))));
            clmAlumnoMatricula.setCellValueFactory(new PropertyValueFactory<Matricula, Alumno>("alumno"));
            clmAsignaturaMatricula.setCellValueFactory(matricula -> {
                List<Asignatura> asignaturas = matricula.getValue().getColeccionAsignaturas();
                if (asignaturas != null && !asignaturas.isEmpty()) {
                    String asignaturasTexto = asignaturas.stream()
                            .map(a -> a.getNombre() + " (" + a.getCurso().toString() + ")")
                            .reduce((a1, a2) -> a1 + ", " + a2)
                            .orElse("Sin asignaturas");
                    return new SimpleStringProperty(asignaturasTexto);
                } else {
                    return new SimpleStringProperty("Sin asignaturas");
                }
            });
            clmFecahAnuMatricula.setCellValueFactory(fechaAnulacion-> new SimpleStringProperty((fechaAnulacion.getValue().getFechaAnulacion()==null ? " - " : fechaAnulacion.getValue().getFechaAnulacion().format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)))));
            for (Matricula m : coleccionMatriculas) {
                System.out.println("ID: " + m.getIdMatricula() + " - Fecha Anulación: " + m.getFechaAnulacion());
            }

            tvMatricula.setItems(matriculasObservable);
            coleccionMatriculas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getMatriculas());
            matriculasObservable.setAll(coleccionMatriculas);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void buscarMatricula(ActionEvent event) {
        try {
            String matriculaBuscar = tfBuscarMatricula.getText().toLowerCase();
            if (matriculaBuscar.isBlank()) {
                coleccionMatriculas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getMatriculas());
                matriculasObservable.setAll(coleccionMatriculas);
            } else {
                List<Matricula> coleccionMatriculas = new ArrayList<>();

                for (Matricula matricula : VistaGrafica.getInstancia().getControlador().getMatriculas()) {
                    if ((matricula.getIdMatricula() + "").toLowerCase().contains(matriculaBuscar)) {
                        coleccionMatriculas.add(matricula);
                    }
                }
                matriculasObservable.setAll(coleccionMatriculas);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void filtraMatriculas(String newValue) {
        FilteredList<Matricula> matriculasBusqueda = new FilteredList<>(matriculasObservable, matricula -> true);

        matriculasBusqueda.setPredicate(matricula-> {

            if (newValue.isBlank())
                return true;

            String matriculaFiltrada = newValue.toLowerCase();
            return (matricula.getIdMatricula()+ "").toLowerCase().contains(matriculaFiltrada);

        });

        tvMatricula.setItems(matriculasBusqueda);
    }

    @FXML
    void aniadirMatricula(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaMatricula.fxml"));
            Parent raiz = loader.load();


            Scene escena = new Scene(raiz);
            Stage ventanaMatricula = new Stage();
            ventanaMatricula.setTitle("Añadir Matricula");
            ventanaMatricula.setScene(escena);
            ventanaMatricula.initModality(Modality.APPLICATION_MODAL);
            ventanaMatricula.setResizable(false);
            ventanaMatricula.showAndWait();
            coleccionMatriculas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getMatriculas());
            matriculasObservable.setAll(coleccionMatriculas);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void borrarMatricula(ActionEvent event) {
        try{
            Matricula matricula = tvMatricula.getSelectionModel().getSelectedItem();
            if (matricula == null) {
                Dialogos.mostrarDialogoAdvertencia("Error", "No se puede borrar una matricula si no se ha seleccionado una");
            } else {
                if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres anular la matricula " + matricula.getIdMatricula() + "?")) {
                    matricula.setFechaAnulacion(LocalDate.now());
                    VistaGrafica.getInstancia().getControlador().borrar(matricula);
                }

                coleccionMatriculas = new ArrayList<>(VistaGrafica.getInstancia().getControlador().getMatriculas());
                matriculasObservable.setAll(coleccionMatriculas);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void mostrarMatriculaAlumno() {
        try {
            Alumno alumnoSeleccionado = table_Alumnos.getSelectionModel().getSelectedItem();


            List<Matricula> matriculasAlumno = VistaGrafica.getInstancia().getControlador().getMatriculas()
                    .stream()
                    .filter(m -> m.getAlumno().equals(alumnoSeleccionado))
                    .toList();

            ObservableList<Matricula> matriculasAlumnoObservable = FXCollections.observableArrayList(matriculasAlumno);
            tvAlumnoMatricula.setItems(matriculasAlumnoObservable);

            clmAlumnoMatriculaVer.setCellValueFactory(nombreAlumno ->
                    new SimpleStringProperty(nombreAlumno.getValue().getAlumno().getNombre()));

            clmDNIAlumnoMatriculaVer.setCellValueFactory(dniAlumno ->
                    new SimpleStringProperty(dniAlumno.getValue().getAlumno().getDni()));

            clmIDMatriculaAlumnoVer.setCellValueFactory(idMatricula ->
                    new SimpleIntegerProperty(idMatricula.getValue().getIdMatricula()).asObject());

            clmFechaMatriculacionAlumnoVer.setCellValueFactory(fechaMatriculacion -> {
                LocalDate fecha = fechaMatriculacion.getValue().getFechaMatriculacion();
                return new SimpleStringProperty(fecha.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)));
            });

            clmFechaAnulacionAlumnoVer.setCellValueFactory(fechaAnulacion-> {
                LocalDate fecha = fechaAnulacion.getValue().getFechaAnulacion();
                return new SimpleStringProperty(
                        (fecha != null) ? fecha.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)) : "No anulada"
                );
            });
            clmAsignaturasAlumnoMatriculasVer.setCellValueFactory(asignaturas -> {
                List<Asignatura> listaAsignaturas = asignaturas.getValue().getColeccionAsignaturas();
                StringBuilder nombres = new StringBuilder();
                for (int i = 0; i < listaAsignaturas.size(); i++) {
                    nombres.append(listaAsignaturas.get(i).getNombre());
                    if (i < listaAsignaturas.size() - 1) {
                        nombres.append(", ");
                    }
                }
                return new SimpleStringProperty(nombres.toString());
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarMatriculaCiclos() {
        try {
            CicloFormativo cicloSeleccionado = tv_cicloFormativo.getSelectionModel().getSelectedItem();

            if (cicloSeleccionado == null) {
                return;
            }

            List<Matricula> matriculasCiclos = VistaGrafica.getInstancia().getControlador().getMatriculas()
                    .stream()
                    .filter(m -> m.getColeccionAsignaturas().stream()
                            .anyMatch(a -> a.getCicloFormativo().equals(cicloSeleccionado)))
                    .toList();

            ObservableList<Matricula> matriculasCicloObservable = FXCollections.observableArrayList(matriculasCiclos);
            tvMatriculaCiclos.setItems(matriculasCicloObservable);

            clmCicloMatricula.setCellValueFactory(nombreCiclo ->
                    new SimpleStringProperty(nombreCiclo.getValue().getColeccionAsignaturas().getFirst().getCicloFormativo().getNombre()
                    ));

            clmCodigoCicloMatricula.setCellValueFactory(codigoCicloMatricula -> {
                ArrayList<Asignatura> asignaturas = codigoCicloMatricula.getValue().getColeccionAsignaturas();

                if (asignaturas != null && !asignaturas.isEmpty()) {
                    int codigoCiclo = asignaturas.getFirst().getCicloFormativo().getCodigo();
                    return new SimpleIntegerProperty(codigoCiclo).asObject();
                } else {
                    return new SimpleIntegerProperty(0).asObject();
                }
            });

            clmIdMatriculaCiclo.setCellValueFactory(idMatriculaCiclo ->
                    new SimpleIntegerProperty(idMatriculaCiclo.getValue().getIdMatricula()).asObject());

            clmFechaMatriculacionCiclo.setCellValueFactory(fechaMatriculacionCiclo -> {
                LocalDate fecha = fechaMatriculacionCiclo.getValue().getFechaMatriculacion();
                return new SimpleStringProperty(fecha.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)));
            });

            clmFechaAnulacionCiclo.setCellValueFactory(fechaAnulacionCiclo -> {
                LocalDate fecha = fechaAnulacionCiclo.getValue().getFechaAnulacion();
                return new SimpleStringProperty(
                        (fecha != null) ? fecha.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)) : "No anulada"
                );
            });
            clmAsignaturasCicloMatriculas.setCellValueFactory(asignaturas -> {
                List<Asignatura> listaAsignaturas = asignaturas.getValue().getColeccionAsignaturas();
                StringBuilder nombres = new StringBuilder();
                for (int i = 0; i < listaAsignaturas.size(); i++) {
                    nombres.append(listaAsignaturas.get(i).getNombre());
                    if (i < listaAsignaturas.size() - 1) {
                        nombres.append(", ");
                    }
                }
                return new SimpleStringProperty(nombres.toString());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarMatriculaPorCursoAcademico(String cursoAcademicoTexto) {
        try {
            if (cursoAcademicoTexto == null || cursoAcademicoTexto.isBlank()) {
                tvMatriculasPorCursoAcademico.getItems().clear();
                return;
            }

            List<Matricula> matriculasPorCursoAcademico = VistaGrafica.getInstancia().getControlador().getMatriculas()
                    .stream()
                    .filter(m -> m.getCursoAcademico().equalsIgnoreCase(cursoAcademicoTexto.trim()))
                    .toList();

            ObservableList<Matricula> matriculasObservable = FXCollections.observableArrayList(matriculasPorCursoAcademico);
            tvMatriculasPorCursoAcademico.setItems(matriculasObservable);

            clmIdMatriculaPorCursoAcademico.setCellValueFactory(idMatricula ->
                    new SimpleIntegerProperty(idMatricula.getValue().getIdMatricula()).asObject());

            clmMatriculaPorCursoAcademico.setCellValueFactory(matricula ->
                    new SimpleStringProperty(matricula.getValue().getCursoAcademico()));

            clmFechaMaticulaPorCursoAcademico.setCellValueFactory(fechaMatriculacion -> {
                LocalDate fecha = fechaMatriculacion.getValue().getFechaMatriculacion();
                return new SimpleStringProperty(fecha.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)));
            });

            clmFechaAnulacionPorCursoAcademico.setCellValueFactory(fechaAnulacion -> {
                LocalDate fecha = fechaAnulacion.getValue().getFechaAnulacion();
                return new SimpleStringProperty(
                        (fecha != null) ? fecha.format(DateTimeFormatter.ofPattern(Matricula.FORMATO_FECHA)) : "No anulada"
                );
            });
            clmAsignaturasCursoAcademicoMatricula.setCellValueFactory(asignaturas -> {
                List<Asignatura> listaAsignaturas = asignaturas.getValue().getColeccionAsignaturas();
                StringBuilder nombres = new StringBuilder();
                for (int i = 0; i < listaAsignaturas.size(); i++) {
                    nombres.append(listaAsignaturas.get(i).getNombre());
                    if (i < listaAsignaturas.size() - 1) {
                        nombres.append(", ");
                    }
                }
                return new SimpleStringProperty(nombres.toString());
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
