package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ControladorVentanaAlumno {

    @FXML
    private VBox vbox_añiadirAlumno;
    @FXML
    private GridPane gdp_Alumnos;

    @FXML
    private Button btn_AñadirAlumno;
    @FXML
    private Button btn_cancelarAlumno;
    @FXML
    private Button btnSalir;

    @FXML
    private Label lb_DniAlumno;
    @FXML
    private Label lb_correoAlumno;
    @FXML
    private Label lb_fechaNacAlumno;
    @FXML
    private Label lb_nombreAlumno;
    @FXML
    private Label lb_telefonoAlumno;

    @FXML
    private TextField tf_correoAlumno;
    @FXML
    private TextField tf_dniAlumno;
    @FXML
    private TextField tf_nombreAlumno;
    @FXML
    private TextField tf_telefonoAlumno;
    @FXML
    private DatePicker dtp_fechaNacAlumno;

private List<Alumno> coleccionAlumnos;
private ObservableList<Alumno> alumnosObservable = FXCollections.observableArrayList();



    @FXML
    public void initialize() {
        dtp_fechaNacAlumno.setValue(LocalDate.now());
    }


    @FXML
    void añiadirAlumno(ActionEvent event) {
        try {
            String nombre = tf_nombreAlumno.getText();
            String dni = tf_dniAlumno.getText();
            String telefono = tf_telefonoAlumno.getText();
            String correo = tf_correoAlumno.getText();
            LocalDate fechaNacimiento = dtp_fechaNacAlumno.getValue();


            if (nombre.trim().isBlank() || dni.trim().isBlank() || telefono.trim().isBlank() || correo.trim().isBlank() || fechaNacimiento == null) {
                Dialogos.mostrarDialogoAdvertencia("Error", "Los campos no pueden estar vacíos");
                return; // Detenemos la ejecución
            }

            if (!dni.matches("[0-9]{8}[A-Za-z]")) {
                Dialogos.mostrarDialogoAdvertencia("Error", "El DNI no tiene un formato válido");
                return;
            }

            if (!telefono.matches("\\d{9}")) {
                Dialogos.mostrarDialogoAdvertencia("Error", "El teléfono no tiene un formato válido");
                return;
            }

            if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                Dialogos.mostrarDialogoAdvertencia("Error", "El correo electrónico no tiene un formato válido");
                return;
            }

            if (VistaGrafica.getInstancia().getControlador().buscar(new Alumno("Ficticio", dni, "666554433", "ficticio@fake.com", LocalDate.of(2000, 1, 1))) != null) {
                Dialogos.mostrarDialogoAdvertencia("Error", "Ya existe un alumno con ese DNI");
            }
            else {
                VistaGrafica.getInstancia().getControlador().insertar(new Alumno(nombre, dni, telefono, correo, fechaNacimiento));
                Dialogos.mostrarDialogoInformacion("Información", "Alumno añadido correctamente");
                ((Stage)btn_AñadirAlumno.getScene().getWindow()).close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void cargarAlumnos(List<Alumno> coleccionAlumnos, ObservableList<Alumno> alumnosObservable) {
        try {
            this.coleccionAlumnos = VistaGrafica.getInstancia().getControlador().getAlumnos();
            this.alumnosObservable.setAll(coleccionAlumnos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cancelarAlumno(ActionEvent event) {
        if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?")) {
            ((Stage)btn_cancelarAlumno.getScene().getWindow()).close();
        }else
            event.consume();
    }



}
