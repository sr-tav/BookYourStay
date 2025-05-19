package co.edu.uniquindio.bookyourstay.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelClienteControlador implements Initializable {

    @FXML
    private Tab tab1;


    @FXML
    private Tab tab2;


    @FXML
    private Tab tab3;


    @FXML
    private Tab tab4;



    private void cargarTab(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            cargarTab(tab1, "/reserva.fxml");
            cargarTab(tab2, "/misReservas.fxml");
            cargarTab(tab3, "/miCuenta.fxml");
            cargarTab(tab4, "/recarga.fxml");
            //Agregar los otros tabs…
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
