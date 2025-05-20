package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class ReservaControlador {

    @FXML
    private TextField ciudadTextField;

    @FXML
    private DatePicker fechaEntradaPicker;

    @FXML
    private DatePicker fechaSalidaPicker;

    @FXML
    private ListView<Alojamiento> listaAlojamientos;

    @FXML
    private Spinner<Alojamiento> spinnerHuespedes;

}

