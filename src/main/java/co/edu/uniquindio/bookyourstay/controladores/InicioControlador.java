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
import javafx.scene.control.ListCell;
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
    private ListView<Alojamiento> listaAlojamientos;

    @FXML
    private ListView<Oferta> listaOfertas;

    private final ControladorPrincipal controladorPrincipal;

    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mostrarAlojamientosAleatorios();
        mostrarOfertas();
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
        List<Alojamiento> aleatorios = controladorPrincipal.getAlojamientoService().obtenerAlojamientosAleatorios(5);

        // Limpiar el ListView y añadir directamente los objetos
        listaAlojamientos.getItems().clear();
        listaAlojamientos.getItems().addAll(aleatorios);

        // Personalizar cómo se muestran los alojamientos (opcional, solo si aún no lo hiciste)
        listaAlojamientos.setCellFactory(lv -> new ListCell<Alojamiento>() {
            @Override
            protected void updateItem(Alojamiento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNombre() + " - " + item.getCiudad()); // o lo que necesites mostrar
                }
            }
        });
    }


    public void mostrarOfertas(){
        List<Oferta> ofertas = controladorPrincipal.getOfertaRepositorio().listar();

        // Limpiar el ListView y añadir directamente los objetos
        listaOfertas.getItems().clear();
        listaOfertas.getItems().addAll(ofertas);

        // Personalizar cómo se muestran los alojamientos (opcional, solo si aún no lo hiciste)
        listaOfertas.setCellFactory(lv -> new ListCell<Oferta>() {
            @Override
            protected void updateItem(Oferta item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getAlojamiento().getNombre() + " - " + item.getPorcentaje()); // o lo que necesites mostrar
                }
            }
        });
    }


}

