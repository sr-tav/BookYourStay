package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.AlojamientoFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NuevoAlojamientoControlador implements Initializable {

    @FXML
    private Spinner<Integer> capacidadSpinner;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private ImageView imagenPreview;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ListView<String> serviciosListView;

    @FXML
    private ComboBox<String> comboTipo;

    @FXML
    private VBox vboxServicios;

    @FXML
    private Label lblServicios;


    private final ControladorPrincipal controladorPrincipal;

    public NuevoAlojamientoControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }



    public void registrarAlojamiento(ActionEvent actionEvent) {
        try {

            float precio = Float.parseFloat(txtPrecio.getText());

            Image imagen = imagenPreview.getImage();

            controladorPrincipal.getAdministradorService().registrarAlojamiento(
                    comboTipo.getValue(), // Tipo como String
                    txtNombre.getText(),
                    txtCiudad.getText(),
                    txtDescripcion.getText(),
                    precio,
                    capacidadSpinner.getValue(),
                    imagen,
                    serviciosListView.getItems()
            );

            controladorPrincipal.mostrarAlerta("Alojamiento registrado correctamente", Alert.AlertType.INFORMATION);

            limpiarFormulario();

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * metodo para seleccionar una imagen
     * @param e
     */
    public void seleccionarImagen(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        Stage stage = (Stage) imagenPreview.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image imagen = new Image(file.toURI().toString());
            imagenPreview.setImage(imagen);
        }
    }


    private void limpiarFormulario() {
        comboTipo.setValue(null);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboTipo.getItems().addAll(controladorPrincipal.getAlojamientoService().obtenerTiposAlojamiento());
        /**
        comboTipo.setOnAction(event -> {
            String tipoSeleccionado = comboTipo.getValue();
            try {
                List<String> servicios = AlojamientoFactory.obtenerServiciosPorTipo(tipoSeleccionado);
                lblServicios.setText(String.join(", ", servicios));
            } catch (Exception e) {
                lblServicios.setText("Tipo inválido");
            }
        });
         */
    }
}

