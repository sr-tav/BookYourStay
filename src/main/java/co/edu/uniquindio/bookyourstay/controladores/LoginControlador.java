package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.servicios.AutenticacionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlador {
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtContrasena;

    private final ControladorPrincipal controladorPrincipal;

    public LoginControlador(){
        this.controladorPrincipal = ControladorPrincipal.getInstancia(); // Usa el Singleton
    }

    /**
     * metodo para iniciar sesion
     * @param
     */
    public void iniciarSesion(ActionEvent event) {
        try {
            String email = txtEmail.getText().trim();
            String contrasena = txtContrasena.getText().trim();

            Usuario usuario = controladorPrincipal.getAutenticacionService().iniciarSesion(email, contrasena);
            controladorPrincipal.setUsuarioActual(usuario);

            if (usuario instanceof Administrador) {
                irPanelAdmin();
            } else {
                irPanelCliente();
            }

            controladorPrincipal.mostrarAlerta("Bienvenido " + usuario.getNombre(), Alert.AlertType.INFORMATION);

            cerrarVentana();

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();  // Esto imprimirá la pila completa de la excepción
        }
    }


    /**
     * metodo para ir al panel de usuario
     */
    public void irPanelCliente() {
        navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente");

    }

    public void irPanelAdmin() {
        navegarVentana("/panelAdministrador.fxml", "Banco - Panel Cliente");

    }


    /**
     * metodo para navegar entre ventanas
     * @param nombreArchivoFxml
     * @param tituloVentana
     */
    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {

            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo para cerrar la ventana
     */
    public void cerrarVentana(){
        Stage stage = (Stage) txtEmail.getScene().getWindow();
        stage.close();
    }


}
