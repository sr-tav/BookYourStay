package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AlojamientosRentablesControlador {
    @FXML
    private TableColumn<Alojamiento, String> ciudad;

    @FXML
    private TableColumn<Alojamiento, String> descripcion;

    @FXML
    private TableColumn<Alojamiento, String> huespedes;

    @FXML
    private TableColumn<Alojamiento, String> id;

    @FXML
    private TableColumn<Alojamiento, String> nombre;

    @FXML
    private TableColumn<Alojamiento, String> precio;

    @FXML
    private TableView<Alojamiento> tablaAlojamientosPopulares;
}
