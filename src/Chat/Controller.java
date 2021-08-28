package Chat;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;



public class Controller implements Initializable {

    @FXML public TextField tfChat;
    @FXML public Button btnOK;
    @FXML public Slider charSlider;
    @FXML private TextField tfNombre;
    @FXML private ChoiceBox<String> chGen;

    @FXML private TextField tfMensaje;



    @FXML public void accionAceptar() {
        String nombre = tfNombre.getText() + ": ";
        String mensaje = tfMensaje.getText();
        String gen = chGen.getValue();
        tfChat.setText(nombre+mensaje+gen);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfNombre.setText("---");
        chGen.getItems().add("Masculino");
        chGen.getItems().add("Femenino");
    }
}
