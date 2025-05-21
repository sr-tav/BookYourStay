package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class EditarAlojamientoControlador {

    @FXML
    private TextField txtNombre, txtCiudad, txtDescripcion, txtPrecio;
    @FXML
    private ComboBox<Alojamiento> comboTipo;
    @FXML
    private Spinner<Integer> capacidadSpinner;
    @FXML
    private VBox vboxServicios;
    @FXML
    private ImageView imagenPreview;

    private final ControladorPrincipal controladorPrincipal;

    private Alojamiento alojamiento;

    public EditarAlojamientoControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    /**
    public void guardarCambios(ActionEvent actionEvent) {
        try {
            float precio = Float.parseFloat(txtPrecio.getText());
            Image imagen = imagenPreview.getImage();

            List<String> serviciosSeleccionados = new ArrayList<>();
            for (Node node : vboxServicios.getChildren()) {
                if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                    serviciosSeleccionados.add(checkBox.getText());
                }
            }

            // Llamamos al factory para actualizar el alojamiento
            controladorPrincipal.getAdministradorService().actualizarAlojamiento(
                    alojamiento,
                    comboTipo.getValue(),
                    txtNombre.getText(),
                    txtCiudad.getText(),
                    txtDescripcion.getText(),
                    precio,
                    capacidadSpinner.getValue(),
                    imagen,
                    serviciosSeleccionados
            );

            // Guardamos los cambios si usas un repositorio explícito
            controladorPrincipal.getAlojamientoRepositorio().actualizar(alojamiento);

            controladorPrincipal.mostrarAlerta("Alojamiento actualizado correctamente", Alert.AlertType.INFORMATION);
            iActualizacion.actualizarTabla();
            ((Stage) txtNombre.getScene().getWindow()).close();

        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta("Error al guardar los cambios: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
     */


}
