package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        Usuario usuario = controladorPrincipal.getUsuarioActual();

        txtNombre.setText(usuario.getNombre());
        txtCedula.setText(usuario.getCedula());
        txtTelefono.setText(usuario.getTelefono());
        txtCorreo.setText(usuario.getEmail());
    }

    public void guardarCambios(ActionEvent e) {
        Usuario usuario = controladorPrincipal.getUsuarioActual();

        usuario.setNombre(txtNombre.getText());
        usuario.setCedula(txtCedula.getText());
        usuario.setTelefono(txtTelefono.getText());
        usuario.setEmail(txtCorreo.getText());

        controladorPrincipal.mostrarAlerta("Perfil actualizado con éxito", Alert.AlertType.INFORMATION);

        cerrarVentana();
    }

    public void eliminarCuenta(ActionEvent e){
        Usuario usuario = controladorPrincipal.getUsuarioActual();
        controladorPrincipal.getUsuarioRepositorio().eliminar(usuario);
        cerrarVentana();
    }

    public void cerrarVentana(){
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    public void cambioContrasenia(ActionEvent e){
        Usuario usuario = controladorPrincipal.getUsuarioActual();
        controladorPrincipal.getAutenticacionService().enviarCodigoRecuperacion(usuario.getEmail());
        irCambioContrasenia();
    }

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

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void irCambioContrasenia() {
        navegarVentana("/cambioContrasenia.fxml", "Banco - Registro de Cliente");
    }

}

