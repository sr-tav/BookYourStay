package co.edu.uniquindio.bookyourstay.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelAdministradorControlador implements Initializable {

    @FXML
    private Tab tab1;


    @FXML
    private Tab tab2;


    @FXML
    private Tab tab3;


    @FXML
    private Tab tab4;


    @FXML
    private Tab tab5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            cargarTab(tab1, "/gestionAlojamiento.fxml");
            cargarTab(tab2, "/gestionOfertas.fxml");
            cargarTab(tab3, "/alojamientosPopulares.fxml");
            cargarTab(tab4, "/alojamientosRentables.fxml");
            cargarTab(tab5, "/estadisticas.fxml");
            //Agregar los otros tabs…
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void cargarTab(Tab tab, String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent content = loader.load();
        tab.setContent(content);
    }
}
