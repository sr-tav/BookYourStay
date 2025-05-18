package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.servicios.ClienteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroControlador {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtPassword;


    private final ControladorPrincipal controladorPrincipal;

    public RegistroControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void registrarCliente(ActionEvent actionEvent) {

        try {
            String nombre = txtNombre.getText();
            String cedula = txtCedula.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String contrasenia = txtPassword.getText();


            Cliente cliente = controladorPrincipal.getClienteService().registrarCliente(nombre, cedula, telefono, correo, contrasenia);
            irVerificacion(cliente);
            cerrarVentana();

        }catch (Exception e){
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormularioRegistro() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtPassword.setText("");
    }

    public void navegarVentana(String nombreArchivoFxml, String tituloVentana, Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            VerificacionControlador controller = loader.getController();
            controller.initDatos(cliente);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(tituloVentana);
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void irVerificacion(Cliente cliente) {
        navegarVentana("/verificacion.fxml", "BookYourStay - Iniciar Sesión", cliente);
    }

    public void cerrarVentana(){
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }


}
