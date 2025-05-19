package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MisReservasControlador implements Initializable {

    @FXML
    private TableColumn<Reserva, String> alojamiento;

    @FXML
    private TableColumn<Reserva, String> cliente;

    @FXML
    private TableColumn<Reserva, String> fin;

    @FXML
    private TableColumn<Reserva, String> huespedes;

    @FXML
    private TableColumn<Reserva, String> id;

    @FXML
    private TableColumn<Reserva, String> inicio;

    @FXML
    private TableView<Reserva> tablaReservas;

    private ObservableList<Reserva> reservaObservableList;

    private final ControladorPrincipal controladorPrincipal;

    public MisReservasControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        cliente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().toString()));
        alojamiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAlojamiento().toString()));
        inicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        fin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaFin().toString()));
        huespedes.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getNumHuespedes())));

        reservaObservableList = FXCollections.observableArrayList();
        cargarReservas();
    }

    private void cargarReservas() {
        Usuario usuario = controladorPrincipal.getUsuarioActual();
        reservaObservableList.setAll(controladorPrincipal.getReservaRepositorio().obtenerPorCliente(usuario.getCedula()));
        tablaReservas.setItems(reservaObservableList);
    }
}

