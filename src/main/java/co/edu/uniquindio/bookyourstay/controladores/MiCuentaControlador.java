package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MiCuentaControlador {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    private final ControladorPrincipal controladorPrincipal;



    public MiCuentaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void initialize() {

        Usuario usuario = controladorPrincipal.getInstancia().getUsuarioActual();

        txtNombre.setText(usuario.getNombre());
        txtCedula.setText(usuario.getCedula());
        txtTelefono.setText(usuario.getTelefono());
        txtCorreo.setText(usuario.getEmail());
    }

    public void guardarCambios(ActionEvent e) {
        Usuario usuario = controladorPrincipal.getInstancia().getUsuarioActual();

        usuario.setNombre(txtNombre.getText());
        usuario.setCedula(txtCedula.getText());
        usuario.setTelefono(txtTelefono.getText());
        usuario.setEmail(txtCorreo.getText());

        controladorPrincipal.mostrarAlerta("Perfil actualizado con éxito", Alert.AlertType.INFORMATION);

        cerrarVentana();
    }

    public void cerrarVentana(){
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }



}

