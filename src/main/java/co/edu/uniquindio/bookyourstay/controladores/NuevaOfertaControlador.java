package co.edu.uniquindio.bookyourstay.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class NuevaOfertaControlador {

    @FXML
    private Button btnGuardar;

    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private TextField txtIdAlojamiento;

    @FXML
    private TextField txtPorcentaje;

    private IActualizacion iActualizacion;

    private final ControladorPrincipal controladorPrincipal;

    public NuevaOfertaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void guardarOferta(ActionEvent event) {
        try {
            String idAlojamiento = txtIdAlojamiento.getText();
            LocalDate inicio = dpFechaInicio.getValue();
            LocalDate fin = dpFechaFin.getValue();
            float porcentaje = Float.parseFloat(txtPorcentaje.getText());

            controladorPrincipal.getAdministradorService().crearOfertaEspecial(idAlojamiento, inicio, fin, porcentaje);
            limpiarFormulario();
            iActualizacion.actualizarTabla();
            controladorPrincipal.mostrarAlerta("Oferta creada correctamente.", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            // Aquí manejas errores, por ejemplo con una alerta
            controladorPrincipal.mostrarAlerta("Error al crear la oferta: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormulario() {
        txtIdAlojamiento.setText("");
        dpFechaFin.setValue(null);
        dpFechaFin.setValue(null);
        txtPorcentaje.setText("");
    }

    public void setInterfazActualizacion(IActualizacion iActualizacion){
        this.iActualizacion = iActualizacion;
    }

}

