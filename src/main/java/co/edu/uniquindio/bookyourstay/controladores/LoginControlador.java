package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.servicios.AutenticacionService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginControlador {
    @FXML
    private Label errorText;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;


    @FXML
    protected void onLoginClick() {
        AutenticacionService autenticacionService = new AutenticacionService();
        try {
            autenticacionService.iniciarSesion(emailField.getText(), passwordField.getText());
        } catch (Exception e)
        {
            errorText.setText(e.getMessage());
        }
    }

    @FXML
    protected void onRegisterClick() {
        errorText.setText("registro");
    }
}
