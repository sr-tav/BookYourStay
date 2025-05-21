package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Oferta;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GestionOfertasControlador implements Initializable, IActualizacion {

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableColumn<Oferta, String> colAlojamiento;

    @FXML
    private TableColumn<Oferta, String> colFin;

    @FXML
    private TableColumn<Oferta, String> colId;

    @FXML
    private TableColumn<Oferta, String> colInicio;

    @FXML
    private TableColumn<Oferta, String> colPorcentaje;

    @FXML
    private TableView<Oferta> tablaOfertas;

    private ObservableList<Oferta> ofertaObservableList;

    public FXMLLoader navegarVentana(String nombreArchivoFxml, String tituloVentana) {
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

            return loader;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void irNuevaOferta(ActionEvent actionEvent) {
        FXMLLoader loader = navegarVentana("/nuevaOferta.fxml", "Administrador - crear oferta");
        NuevaOfertaControlador controlador = loader.getController();
        controlador.setInterfazActualizacion(this);
    }

    @Override
    public void actualizarTabla() {
        cargarOfertas();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));
        colAlojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getAlojamiento().getNombre())));
        colInicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        colFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFin().toString()));
        colPorcentaje.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.format("%.2f", cellData.getValue().getPorcentaje()))
        );

        ofertaObservableList = FXCollections.observableArrayList();
        cargarOfertas();

        tablaOfertas.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            boolean seleccionado = newSel != null;
            btnEliminar.setDisable(!seleccionado);
            btnEditar.setDisable(!seleccionado);
        });
    }

    public void cargarOfertas() {
        List<Oferta> ofertas = ControladorPrincipal.getInstancia()
                .getOfertaRepositorio().listar();

        ofertaObservableList.setAll(ofertas);

        tablaOfertas.setItems(ofertaObservableList);
    }
}

