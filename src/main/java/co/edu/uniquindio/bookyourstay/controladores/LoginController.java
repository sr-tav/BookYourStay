package co.edu.uniquindio.bookyourstay.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    private Label errorText;

    @FXML
    protected void onLoginClick() {
        errorText.setText("¡Hola desde JavaFX!");
    }

    @FXML
    protected void onRegisterClick() {
        errorText.setText("registro");
    }
}
