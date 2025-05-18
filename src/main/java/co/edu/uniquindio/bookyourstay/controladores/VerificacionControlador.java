package co.edu.uniquindio.bookyourstay.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class VerificacionControlador {

    @FXML
    private TextField txtCodigo;

    private final ControladorPrincipal controladorPincipal;

    public VerificacionControlador() {
        this.controladorPincipal = ControladorPrincipal.getInstancia();
    }


    public void verificar() {
        String codigo = txtCodigo.getText();

        try {
            controladorPincipal.getAutenticacionService().activarCuenta(cedulaCliente, codigo);
            controladorPincipal.mostrarAlerta("Cuenta activada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            controladorPincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}

