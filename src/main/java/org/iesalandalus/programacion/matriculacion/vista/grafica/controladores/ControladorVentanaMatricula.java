package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class ControladorVentanaMatricula {


    @FXML private Button btnAceptarMatricula;
    @FXML private Button btnCancelarMatricula;

    @FXML private ListView<Asignatura> lvAsignaturasMatriculas;

    @FXML private TextField tfAlumnoMatricula;
    @FXML private TextField tfCursoAcademicoMatricula;
    @FXML private DatePicker dtpFechaMatriculacion;
    @FXML private TextField tfIDMatricula;

    private final ObservableList<Asignatura> asignaturasMatriculas = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        setAsignaturasMatriculas();
        dtpFechaMatriculacion.setValue(LocalDate.now());
    }

    public void setAsignaturasMatriculas() {
        try {
            List<Asignatura> asignaturas = VistaGrafica.getInstancia().getControlador().getAsignaturas();
            asignaturasMatriculas.addAll(asignaturas);
            lvAsignaturasMatriculas.setItems(asignaturasMatriculas);
            lvAsignaturasMatriculas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lvAsignaturasMatriculas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> listViewActualizaAsignaturas(oldValue, newValue));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listViewActualizaAsignaturas(Asignatura oldValue, Asignatura newValue) {
        System.out.println("Modificado valor del ListView");
        System.out.println("El valor anteriormente seleccionado era: " + oldValue);
        System.out.println("El nuevo valor seleccionado es: " + newValue);
    }


    @FXML
    void aniadirMatricula(ActionEvent event) {
        try {
            String dniAlumno = tfAlumnoMatricula.getText().trim();
            String cursoAcademico = tfCursoAcademicoMatricula.getText().trim();
            LocalDate fechaMatricula = dtpFechaMatriculacion.getValue();
            int idMatricula;

            try {
                idMatricula = Integer.parseInt(tfIDMatricula.getText());
            } catch (NumberFormatException e) {
                Dialogos.mostrarDialogoError("Error", "El ID de matrícula debe ser un número válido");
                return;
            }

            ArrayList<Asignatura> asignatura = new ArrayList<>(lvAsignaturasMatriculas.getSelectionModel().getSelectedItems());
            Alumno alumno = VistaGrafica.getInstancia().getControlador().buscar(new Alumno("ficticio", dniAlumno, "666554433", "ficticio@fake.com", LocalDate.of(2000, 1, 1)));

            if (alumno == null) {
                Dialogos.mostrarDialogoError("Error", "No existe un alumno con ese DNI");
                return;
            }
            if (asignatura.size()>10){
                Dialogos.mostrarDialogoError("Error", "No se pueden matricular más de 10 asignaturas");
                return;
            }

            if (cursoAcademico.isEmpty() || dniAlumno.isEmpty() || idMatricula < 0 || fechaMatricula == null || asignatura.isEmpty()) {
                Dialogos.mostrarDialogoError("Error", "Los campos no pueden estar vacíos.");
                return;
            }
            if (!cursoAcademico.matches("^\\d{2}-\\d{2}")){
                Dialogos.mostrarDialogoError("Error", "El formato del curso académico debe ser YY-YY");
            }

            if (fechaMatricula.isAfter(LocalDate.now())) {
                Dialogos.mostrarDialogoError("Error", "La fecha de matrícula no puede ser posterior a la actual");
                return;
            }
            if(fechaMatricula.isBefore(LocalDate.now().minusDays(15))){
                Dialogos.mostrarDialogoError("Error", "La fecha de matrícula no puede ser anterior a 15 dias.");
                return;
            }

            if (VistaGrafica.getInstancia().getControlador().buscar(new Matricula(idMatricula, cursoAcademico, fechaMatricula, alumno, asignatura)) != null) {
                Dialogos.mostrarDialogoError("Error", "Ya existe una matrícula con ese código");
                return;
            }

            VistaGrafica.getInstancia().getControlador().insertar(new Matricula(idMatricula, cursoAcademico, fechaMatricula, alumno, asignatura));
            Dialogos.mostrarDialogoConfirmacion("Confirmación", "La matrícula se ha insertado correctamente");
            ((Stage) btnAceptarMatricula.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelarMatricula(ActionEvent event) {
        if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?")) {
            ((Stage)btnCancelarMatricula.getScene().getWindow()).close();
        }else
            event.consume();

    }

}
