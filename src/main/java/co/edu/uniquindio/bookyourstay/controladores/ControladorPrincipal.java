package co.edu.uniquindio.bookyourstay.controladores;

import co.edu.uniquindio.bookyourstay.servicios.AdministradorService;
import co.edu.uniquindio.bookyourstay.servicios.AutenticacionService;
import co.edu.uniquindio.bookyourstay.servicios.ClienteService;
import javafx.scene.control.Alert;
import lombok.Getter;

public class ControladorPrincipal {

    private static ControladorPrincipal instancia;
    @Getter
    private final ClienteService clienteService;
    @Getter
    private final AdministradorService administradorService;
    @Getter
    private final AutenticacionService autenticacionService;

    private ControladorPrincipal() {
        this.clienteService = new ClienteService();
        this.administradorService = new AdministradorService();
        this.autenticacionService = new AutenticacionService();
    }

    public static ControladorPrincipal getInstancia() {
        if (instancia == null) {
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }
}
