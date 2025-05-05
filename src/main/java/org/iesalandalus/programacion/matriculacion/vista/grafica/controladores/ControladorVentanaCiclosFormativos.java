package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.matriculacion.vista.grafica.utilidades.Dialogos;

public class ControladorVentanaCiclosFormativos {

    @FXML private Button btnAceptarCiclo;
    @FXML private Button btnCancelarCiclo;


    @FXML private ChoiceBox<Modalidad> cbModalidad;

    @FXML private ListView<String> lvlTipoGrado;

    @FXML private Label lbAnios;
    @FXML private Label lbEdiciones;

    @FXML private Label lbModaidad;


    @FXML private TextField tfAnios;
    @FXML private TextField tfCodigoCiclo;
    @FXML private TextField tfEdiciones;
    @FXML private TextField tfFamiliaPro;
    @FXML private TextField tfNombreCiclo;
    @FXML private TextField tfNombreGrado;
    @FXML private TextField tfHorasCiclos;

    private final ObservableList<String> gradosObservables = FXCollections.observableArrayList();
    private final ObservableList<Modalidad> modalidadesObservables = FXCollections.observableArrayList();


    public void initialize() {
        setGrados();
        setModalidades();
        gradosObservables.addAll("Grado D", "Grado E");
        modalidadesObservables.addAll(Modalidad.values());
    }


    public void setGrados() {
        lvlTipoGrado.setItems(gradosObservables);
        lvlTipoGrado.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lvlTipoGrado.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->modificadoListaGrados(oldValue,newValue));
    }

    public void setModalidades() {
        cbModalidad.setItems(modalidadesObservables);
        cbModalidad.getSelectionModel().select(Modalidad.PRESENCIAL);
        cbModalidad.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->modificadoChoiceBoxListadoOpciones(oldValue,newValue));
    }
    private void modificadoChoiceBoxListadoOpciones(Modalidad oldValue, Modalidad newValue)
    {
        System.out.println("Modificado valor del ChoiceBox");
        System.out.println("El valor anteriormente seleccionado era: " + oldValue );
        System.out.println("El nuevo valor seleccionado es: " + newValue);
    }

    private void modificadoListaGrados(String oldValue, String newValue)
    {
        System.out.println("El nuevo valor seleccionado es: " + newValue);

        if (newValue.equals("Grado D")) {
            lbAnios.setVisible(true);
            tfAnios.setVisible(true);
            lbModaidad.setVisible(true);
            cbModalidad.setVisible(true);
            lbEdiciones.setVisible(false);
            tfEdiciones.setVisible(false);
        } else if (newValue.equals("Grado E")) {
            lbEdiciones.setVisible(true);
            tfEdiciones.setVisible(true);
            lbModaidad.setVisible(false);
            cbModalidad.setVisible(false);
            lbAnios.setVisible(true);
            tfAnios.setVisible(true);
        }
    }

    @FXML
    void aniadirCiclos(ActionEvent event) {
        try {
            Grado grado = null;
            int codigoCiclo;
            String familiaPro = tfFamiliaPro.getText();
            String nombreCiclo = tfNombreCiclo.getText();
            String nombreGrado = tfNombreGrado.getText();
            int horas;
            int anios;

            try {
                codigoCiclo = Integer.parseInt(tfCodigoCiclo.getText());
                horas = Integer.parseInt(tfHorasCiclos.getText());
                anios = Integer.parseInt(tfAnios.getText());
            }catch (NumberFormatException e) {
                Dialogos.mostrarDialogoError("Error", "El código debe ser un número entero");
                return;
            }

            if (familiaPro.isBlank() || nombreCiclo.isBlank() || nombreGrado.isBlank() || tfHorasCiclos.getText().isBlank() || tfAnios.getText().isBlank() || tfCodigoCiclo.getText().isBlank()) {
                Dialogos.mostrarDialogoError("Error", "Los campos no pueden estar vacíos.");
                return;
            }else if (codigoCiclo < 1000 || codigoCiclo > 9999) {
                Dialogos.mostrarDialogoError("Error", "El código de un ciclo formativo debe ser un número de 4 dígitos.");
                return;
            }else if (horas <= 0|| horas >2000) {
                Dialogos.mostrarDialogoError("Error", "Las horas deben estar comprendidas entre 0 y 2000.");
                return;
            }

            if (lvlTipoGrado.getSelectionModel().getSelectedItem().equals("Grado D")) {
                Modalidad modalidad = cbModalidad.getSelectionModel().getSelectedItem();
                grado = new GradoD(nombreGrado, anios, modalidad);
                if (anios <2 || anios > 3) {
                    Dialogos.mostrarDialogoError("Error", "Los anios deben estar comprendidos entre 2 y 3.");
                }
            } else if (lvlTipoGrado.getSelectionModel().getSelectedItem().equals("Grado E")) {
                int ediciones = Integer.parseInt(tfEdiciones.getText());
                if (ediciones <= 0) {
                    Dialogos.mostrarDialogoError("Error", "Las ediciones deben ser mayores que 0");
                }else if (anios != 1){
                    Dialogos.mostrarDialogoError("Error", "Los anios deben ser 1");
                }
                grado = new GradoE(nombreGrado, anios, ediciones);
            }

            CicloFormativo cicloFormativo = new CicloFormativo(codigoCiclo, familiaPro, grado, nombreCiclo, horas);
            VistaGrafica.getInstancia().getControlador().insertar(cicloFormativo);
            Dialogos.mostrarDialogoInformacion("Información", "Ciclo añadido correctamente");
            ((Stage) btnAceptarCiclo.getScene().getWindow()).close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancelarCiclos(ActionEvent event) {
        if (Dialogos.mostrarDialogoConfirmacion("Confirmación", "¿Seguro que quieres cerrar la ventana?")){
            ((Stage)btnCancelarCiclo.getScene().getWindow()).close();
        }else
            event.consume();
    }


}