package co.edu.uniquindio.bookyourstay.controladores;
import co.edu.uniquindio.bookyourstay.modelo.Administrador;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.repositorios.*;
import co.edu.uniquindio.bookyourstay.servicios.*;
import javafx.scene.control.Alert;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControladorPrincipal {

    private static ControladorPrincipal instancia;

    private final UsuarioRepositorio usuarioRepositorio;
    private final AlojamientoRepositorio alojamientoRepositorio;
    private final ReservaRepositorio reservaRepositorio;
    private final ReseniaRepositorio resenaRepositorio;
    private final OfertaRepositorio ofertaRepositorio;

    // Servicios compartidos
    private final ClienteService clienteService;
    private final AutenticacionService autenticacionService;
    private final AdministradorService administradorService;
    private final ReservaService reservaService;
    private final AlojamientoService alojamientoService;

    private Usuario usuarioActual;

    private ControladorPrincipal() {
        // Inicializar repositorios únicos
        this.usuarioRepositorio = new UsuarioRepositorio();
        this.alojamientoRepositorio = new AlojamientoRepositorio();
        this.reservaRepositorio = new ReservaRepositorio();
        this.resenaRepositorio = new ReseniaRepositorio();
        this.ofertaRepositorio = new OfertaRepositorio();


        // Inicializar servicios, pasando los mismos repositorios
        this.autenticacionService = new AutenticacionService(usuarioRepositorio);
        this.clienteService = new ClienteService(usuarioRepositorio, autenticacionService, alojamientoRepositorio, resenaRepositorio, reservaRepositorio);
        this.administradorService = new AdministradorService(alojamientoRepositorio, ofertaRepositorio, reservaRepositorio);
        this.reservaService = new ReservaService(reservaRepositorio, usuarioRepositorio);
        this.alojamientoService = new AlojamientoService(alojamientoRepositorio);
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
