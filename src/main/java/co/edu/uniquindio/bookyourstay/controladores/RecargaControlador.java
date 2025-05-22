package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.modelo.BilleteraVirtual;
import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.servicios.BilleteraService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecargaControlador {

    @FXML
    private TextField txtMonto;

    private final ControladorPrincipal controladorPrincipal;

    public RecargaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void recargarBilletera(ActionEvent e){
        try{
            Cliente cliente = (Cliente) controladorPrincipal.getUsuarioActual();
            BilleteraVirtual billetera = cliente.getBilleteraVirtual();
            float monto = Float.parseFloat(txtMonto.getText());

            controladorPrincipal.getBilleteraService().recargar(billetera, monto);
            limpiarCampos();
            controladorPrincipal.mostrarAlerta("Recarga exitosa", Alert.AlertType.INFORMATION);
        } catch (Exception ex){
            controladorPrincipal.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void cancelar(ActionEvent e){
        limpiarCampos();
    }

    public void limpiarCampos(){
        txtMonto.setText("");
    }


}
