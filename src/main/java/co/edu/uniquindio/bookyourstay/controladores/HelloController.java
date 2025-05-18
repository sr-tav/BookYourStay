package co.edu.uniquindio.bookyourstay.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("¡Hola desde JavaFX!");
    }
}
