package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VerificacionControlador {

    @FXML
    private TextField txtCodigo;

    private final ControladorPrincipal controladorPincipal;

    private Cliente cliente;

    public void initDatos(Cliente cliente) {
        this.cliente = cliente;
    }

    public VerificacionControlador() {
        this.controladorPincipal = ControladorPrincipal.getInstancia();
    }


    public void verificar(ActionEvent a) {
        try {

            String codigo = txtCodigo.getText();
            controladorPincipal.getAutenticacionService().activarCuenta(cliente.getCedula(), codigo);

            controladorPincipal.mostrarAlerta("Cuenta activada correctamente", Alert.AlertType.INFORMATION);
            ((Stage) txtCodigo.getScene().getWindow()).close();

        } catch (Exception e) {
            controladorPincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}

