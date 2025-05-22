package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CambioContraseniaControlador {

    @FXML
    private Label lblMensaje;

    @FXML
    private TextField txtCodigo;

    @FXML
    private PasswordField txtNuevaContrasena;

    private final ControladorPrincipal controladorPrincipal;

    public CambioContraseniaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void confirmarCambio(ActionEvent e){
        try{
            Usuario usuario = controladorPrincipal.getUsuarioActual();
            String codigo = txtCodigo.getText();
            String nuevaContrasena = txtNuevaContrasena.getText();

            controladorPrincipal.getAutenticacionService().confirmarCambioContrasena(usuario.getEmail(), codigo, nuevaContrasena);
            controladorPrincipal.mostrarAlerta("contraseña cambiada con exito", Alert.AlertType.INFORMATION);
            cerrarVentana();

        } catch (Exception ex){
            controladorPrincipal.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void cerrarVentana(){
        Stage stage = (Stage) txtCodigo.getScene().getWindow();
        stage.close();
    }
}
