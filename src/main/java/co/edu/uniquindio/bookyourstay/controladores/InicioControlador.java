package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Oferta;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.bookyourstay.servicios.AlojamientoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InicioControlador implements Initializable {

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ListView<String> listaAlojamientos;

    @FXML
    private ListView<Oferta> listaOfertas;

    private final AlojamientoService alojamientoService = new AlojamientoService(new AlojamientoRepositorio());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarAlojamientosAleatorios();
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

    public void irIniciarSesion(ActionEvent actionEvent) {
        navegarVentana("/login.fxml", "BookYourStay - Iniciar Sesión");
    }

    public void irRegistrarse(ActionEvent actionEvent) {
        navegarVentana("/registro.fxml", "BookYourStay - Registrarse");
    }

    public void mostrarAlojamientosAleatorios() {
        List<Alojamiento> aleatorios = alojamientoService.obtenerAlojamientosAleatorios(5);

        listaAlojamientos.getItems().clear();
        for (Alojamiento a : aleatorios) {
            listaAlojamientos.getItems().add(a.getNombre() + " - " + a.getCiudad());
        }
    }

}

