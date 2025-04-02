package org.iesalandalus.programacion.matriculacion.vista.grafica.controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorVentanaPrincipal {

        @FXML
        private Button btn_prueba;

        @FXML
        private Label lb_prueba;

        @FXML
        private TextField tf_prueba;

        @FXML
        void muestraTexto(ActionEvent event) {
                System.out.println("El texto introducido es: " + tf_prueba.getText());
        }



}
