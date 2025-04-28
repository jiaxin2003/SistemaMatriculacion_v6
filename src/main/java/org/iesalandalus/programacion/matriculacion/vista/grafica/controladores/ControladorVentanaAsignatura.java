package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

public class ControladorVentanaAsignatura {

    @FXML private Button btnAceptarAsignatura;
    @FXML private Button btnCancelarAsignatura;

    @FXML private ChoiceBox<Curso> cbCursoAsignatura;

    @FXML private ChoiceBox<EspecialidadProfesorado> cbEspeProfeAsignatura;


    @FXML private TextField tfCodigoAsignatura;
    @FXML private TextField tfCodigoCicloAsignatura;
    @FXML private TextField tfHorasAnualesAsignatura;
    @FXML private TextField tfHorasDesdoAsignatura;
    @FXML private TextField tfNombreAsignatura;

    private final ObservableList<Curso> cursoObservables = FXCollections.observableArrayList();
    private final ObservableList<EspecialidadProfesorado> espeProfeObservable = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setCurso();
        setEspecialidadProfesorado();
        cbCursoAsignatura.setItems(cursoObservables);
        cbEspeProfeAsignatura.setItems(espeProfeObservable);
        cursoObservables.addAll(Curso.values());
        espeProfeObservable.addAll(EspecialidadProfesorado.values());
    }

    public void setCurso() {
        cbCursoAsignatura.setItems(cursoObservables);
        cbCursoAsignatura.getSelectionModel().select(Curso.PRIMERO);
        cbCursoAsignatura.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->modificadoChoiceBoxListadoOpciones(oldValue,newValue));
    }
    private void modificadoChoiceBoxListadoOpciones(Curso oldValue, Curso newValue)
    {
        System.out.println("Modificado valor del ChoiceBox");
        System.out.println("El valor anteriormente seleccionado era: " + oldValue );
        System.out.println("El nuevo valor seleccionado es: " + newValue);
    }

    public void setEspecialidadProfesorado() {
        cbEspeProfeAsignatura.setItems(espeProfeObservable);
        cbEspeProfeAsignatura.getSelectionModel().select(EspecialidadProfesorado.INFORMATICA);
        cbEspeProfeAsignatura.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->modChoiceBoxListOpcionesEpeProfe(oldValue,newValue));
    }
    private void modChoiceBoxListOpcionesEpeProfe(EspecialidadProfesorado oldValue, EspecialidadProfesorado newValue)
    {
        System.out.println("Modificado valor del ChoiceBox");
        System.out.println("El valor anteriormente seleccionado era: " + oldValue );
        System.out.println("El nuevo valor seleccionado es: " + newValue);
    }


    @FXML
    void aniadirAsignatura(ActionEvent event) {
        try {

            if (tfNombreAsignatura.getText().isBlank() || tfCodigoAsignatura.getText().isBlank() || tfHorasAnualesAsignatura.getText().isBlank() || tfHorasDesdoAsignatura.getText().isBlank() || tfCodigoCicloAsignatura.getText().isBlank()) {
                Dialogos.mostrarDialogoError("Error", "Los campos no pueden estar vacíos");
                return;
            }

            String nombre = tfNombreAsignatura.getText();
            String codigo = tfCodigoAsignatura.getText();
            int horasAnuales;
            int horasDesdo;

            try {
                horasAnuales = Integer.parseInt(tfHorasAnualesAsignatura.getText());
                horasDesdo = Integer.parseInt(tfHorasDesdoAsignatura.getText());
            } catch (NumberFormatException e) {
                Dialogos.mostrarDialogoError("Error", "Las horas deben ser números enteros.");
                return;
            }


            Curso curso = cbCursoAsignatura.getValue();
            EspecialidadProfesorado espeProfe = cbEspeProfeAsignatura.getValue();

            CicloFormativo cicloFormativo = VistaGrafica.getInstancia().getControlador().buscar(new CicloFormativo(Integer.parseInt(tfCodigoCicloAsignatura.getText()), "null", new GradoD("null", 2, Modalidad.PRESENCIAL), "null", 120));

            if (cicloFormativo == null) {
                Dialogos.mostrarDialogoError("Error", "No existe un ciclo con ese código.");
                return;
            }

            if (!codigo.matches("^[0-9]{4}$")){
                Dialogos.mostrarDialogoError("Error", "El codigo de la asignatura debe ser de 4 cifras.");
                return;
            }

            if (VistaGrafica.getInstancia().getControlador().buscar(new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdo, espeProfe, cicloFormativo)) != null) {
                Dialogos.mostrarDialogoError("Error", "Ya existe una asignatura con ese código.");
                return;
            }

            Asignatura asignatura = new Asignatura(codigo, nombre, horasAnuales, curso, horasDesdo, espeProfe, cicloFormativo);
            VistaGrafica.getInstancia().getControlador().insertar(asignatura);

            Dialogos.mostrarDialogoInformacion("Información", "Asignatura añadida correctamente");
            ((Stage) btnAceptarAsignatura.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelarAsignatura(ActionEvent event) {
        if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?")) {
            ((Stage)btnCancelarAsignatura.getScene().getWindow()).close();
        }else
            event.consume();

    }

}
