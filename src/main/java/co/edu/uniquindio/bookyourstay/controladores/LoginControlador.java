package co.edu.uniquindio.bookyourstay.controladores;

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
    private TextField txtIdentificacion;

    @FXML
    private PasswordField txtContrasena;

    private final ControladorPrincipal controladorPrincipal;

    public LoginControlador(){
        this.controladorPrincipal = ControladorPrincipal.getInstancia(); // Usa el Singleton
    }

    /**
     * metodo para iniciar sesion
     * @param e
     */
    public void iniciarSesion(ActionEvent e){
        String id= txtIdentificacion.getText();
        String contraseña= txtContrasena.getText();

        Usuario usuarioEncontrado = controladorPrincipal.getUsuarioRepositorio().obtenerPorCedula(id);


        if(usuarioEncontrado != null){
            controladorPrincipal.mostrarAlerta("Éxito, inicio de sesión exitoso", Alert.AlertType.INFORMATION);

            irPanelCliente();
            cerrarVentana();
        } else {
            controladorPrincipal.mostrarAlerta("Error, numero de identificacion o contraseña incorrectos", Alert.AlertType.ERROR);
        }
    }


    /**
     * metodo para ir al panel de usuario
     */
    public void irPanelCliente() {
        navegarVentana("/panelCliente.fxml", "Banco - Panel Cliente");

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

            PanelClienteControlador controlador = loader.getController();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);

            // Mostrar la nueva ventana
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * metodo para cerrar la ventana
     */
    public void cerrarVentana(){
        Stage stage = (Stage) txtIdentificacion.getScene().getWindow();
        stage.close();
    }


}
